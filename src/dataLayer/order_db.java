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
//    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";
//
//    static final String USER = "root";
//    static final String PASS = "";
//    static final String driver = "com.mysql.jdbc.Driver";

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
            Class.forName(db.driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            // Check for being in the service scope
            sql = "SELECT * FROM region WHERE ward = '" + order.getString("ward") + "';";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next())
            {
                return null;
            }

            sql = "INSERT INTO gas_order(warehouse, gasCode, phoneNumber, address, date, status) SELECT w.houseCode, " + order.getInt("gasCode") +", '" + order.getString("phoneNumber") +"', '"+ order.getString("address") + "', '" + dateString + "', 'A' FROM warehouse w, region r WHERE w.regionCode = r.code AND r.ward ='" + order.getString("ward") + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);
            System.out.println("Order was inserted.");

//            sql = "SELECT c.name as cylinder, concat(r.ward, ' ward, ', r.district, ' district') as region FROM gas_cylinder c, region r WHERE c.code = " + order.getInt("gasCode") + " AND r.ward = '" + order.getString("ward") +"';";
            sql = "SELECT name as cylinder FROM gas_cylinder WHERE code = " + order.getInt("gasCode") +";";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            JsonObject details = new JsonObject();
            details.addProperty("cylinder", rs.getString("cylinder"));
//            details.addProperty("address", order.getString("address") + ", " + rs.getString("region"));
//            details.addProperty("phoneNumber", order.getString("phoneNumber"));
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

    public String insertOrder(String username, JSONObject order)
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
            Class.forName(db.driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            // Check for being in the service scope
            sql = "SELECT * FROM region WHERE ward = '" + order.getString("ward") + "';";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next())
            {
                return null;
            }

            sql = "INSERT INTO gas_order(warehouse, userID, gasCode, phoneNumber, address, date, status) SELECT w.houseCode, u.id, " + order.getInt("gasCode") +", '" + order.getString("phoneNumber") +"', '"+ order.getString("address") + "', '" + dateString + "', 'A' FROM user u, warehouse w, region r WHERE u.username = '" + username + "' AND w.regionCode = r.code AND r.ward ='" + order.getString("ward") + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);
            System.out.println("Order was inserted.");

//            sql = "SELECT c.name as cylinder, concat(r.ward, ' ward, ', r.district, ' district') as region FROM gas_cylinder c, region r WHERE c.code = " + order.getInt("gasCode") + " AND r.ward = '" + order.getString("ward") +"';";
            sql = "SELECT name as cylinder FROM gas_cylinder WHERE code = " + order.getInt("gasCode") +";";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            JsonObject details = new JsonObject();
            details.addProperty("cylinder", rs.getString("cylinder"));
//            details.addProperty("address", order.getString("address") + ", " + rs.getString("region"));
//            details.addProperty("phoneNumber", order.getString("phoneNumber"));
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

    public boolean updateAddress(String username, JSONObject order){

        // step 1: Set parameters
        Connection connection = null;
        Statement statement = null;
        String sql = "";
        try{
            // step 2: register JDBC driver
            Class.forName(db.driver);

            // step 3: open a connection
            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

            // step 4: execute a query
            statement = connection.createStatement();

            sql = "SELECT u.id FROM user u, address a WHERE u.id = a.userID AND u.username = '" + username +"';";

            ResultSet rs = statement.executeQuery(sql);
            JSONObject pos = order.getJSONObject("pos");

            if(rs.next())
            {
                sql = "UPDATE address SET address = '"+ order.getString("address") +"', ward = '" + order.getString("ward") +"', lat = "+ pos.getDouble("lat") + ", lng = " + pos.getDouble("lng") + " WHERE userID = (SELECT id FROM user WHERE username = '"+ username + "');";
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
            else {
                sql = "INSERT INTO address (address, ward, lat, lng, userID) VALUES ('" + order.getString("address") +"','" + order.getString("ward") + "'," + pos.getDouble("lat") +"," + pos.getDouble("lng") +", (SELECT id FROM user WHERE username = '" + username + "'));";
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
            System.out.println("updated Address");

            //step 5: close connections
            connection.close();
            statement.close();

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
