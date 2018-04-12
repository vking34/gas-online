package dataLayer;
import appLayer.orderDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class order_db {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";
    static final String driver = "com.mysql.jdbc.Driver";

    public boolean insertOrder(orderDetails order)
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

//            sql = ""

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return true;
    }

}
