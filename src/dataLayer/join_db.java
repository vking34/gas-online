package dataLayer;

import com.google.gson.JsonObject;

import java.sql.*;

public class join_db {

    // JDBC driver name and database URL
//    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";
//
//    static final String USER = "root";
//    static final String PASS = "";

//    static final String driver = "com.mysql.jdbc.Driver";
    static Connection connection = db.getConnection();
    Statement statement = null;
    String sql = "";

    public String getLastOrder(String username){

        JsonObject lastOrder = new JsonObject();
        try {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

            //step 4: execute a query
            statement = connection.createStatement();
            sql = "SELECT phoneNumber, address, ward, lat, lng FROM user, address WHERE user.id = address.userID AND user.username = '" + username +"';";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            JsonObject pos = new JsonObject();
            lastOrder.addProperty("phoneNumber", rs.getString("phoneNumber"));
            lastOrder.addProperty("address", rs.getString("address"));
            lastOrder.addProperty("ward", rs.getString("ward"));
            pos.addProperty("lat", rs.getDouble("lat"));
            pos.addProperty("lng", rs.getDouble("lng"));
//            lastOrder.addProperty("pos", pos.toString());
            lastOrder.add("pos", pos);
            lastOrder.addProperty("status", true);

            // step 5: close
            rs.close();
//            connection.close();
            statement.close();

            return lastOrder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            lastOrder.addProperty("status", false);
            return lastOrder.toString();
        }
    }
}
