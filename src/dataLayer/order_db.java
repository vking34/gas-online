package dataLayer;
import appLayer.orderDetails;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;

public class order_db {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";
    static final String driver = "com.mysql.jdbc.Driver";

    public String insertOrder(JSONObject order)
    {

        // step 1: Set parameters
        Connection connection = null;
        Statement statement = null;
        String sql = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        try{
            // step 2: register JDBC driver
            Class.forName(driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "INSERT INTO gas_order(warehouse, gasCode, phoneNumber, date, address, status) SELECT w.houseCode, " + order.getInt("gasCode") +", '" + order.getString("phoneNumber") +"', '"+ dateString + "', '" + order.getString("address") +  "', 'A' FROM warehouse w, region r WHERE w.regionCode = r.code AND r.ward ='" + order.getString("ward") + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);
            System.out.println("Order was inserted.");

            sql = "SELECT c.name as cylinder, concat(r.ward, ' ward, ', r.district, ' district') as region FROM gas_cylinder c, region r WHERE c.code = " + order.getInt("gasCode") + " AND r.ward = '" + order.getString("ward") +"';";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            JsonObject details = new JsonObject();
            details.addProperty("cylinder", rs.getString("cylinder"));
            details.addProperty("address", order.getString("address") + ", " + rs.getString("region"));
            details.addProperty("phoneNumber", order.getString("phoneNumber"));
            details.addProperty("status", true);

            //step 5: close connections
            connection.close();
            statement.close();

            return details.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
