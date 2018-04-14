package dataLayer;
import appLayer.orderDetails;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class order_db {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";
    static final String driver = "com.mysql.jdbc.Driver";

    public String insertOrder(orderDetails order)
    {

        // step 1: Set parameters
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            // step 2: register JDBC driver
            Class.forName(driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "INSERT INTO gas_order(warehouse, gasCode, phoneNumber, date, address, status) SELECT w.houseCode, " + order.getGasCode() +", '" + order.getPhoneNumber() +"', '2018-04-17', '" + order.getAddress() +  "', 'A' FROM warehouse w WHERE w.regionCode = '" + order.getRegionCode() + "';";
            System.out.println(sql);

            statement.executeUpdate(sql);

            //step 5: close connections
            System.out.println("Order was inserted.");

            sql = "SELECT c.name as cylinder, concat(r.ward, ', ', r.district) as region FROM gas_cylinder c, region r WHERE c.code = " + order.getGasCode() + " AND r.code = '" + order.getRegionCode() +"';";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            JsonObject details = new JsonObject();
            details.addProperty("cylinder", rs.getString("cylinder"));
            details.addProperty("address", order.getAddress() + ", " + rs.getString("region"));
            details.addProperty("phoneNumber", order.getPhoneNumber());
            details.addProperty("status", true);
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
