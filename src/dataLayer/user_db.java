package dataLayer;

import java.sql.*;
import java.lang.*;


public class user_db {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";

    public boolean isValidUserLogin(String username, String password)
    {
        // step 1: set the parameters
        boolean isValidUser = false;
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try
        {
            // step 2: register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT * FROM user WHERE username = \"" + username + "\" AND password = \"" + password + "\"";

            System.out.println(sql);

            ResultSet rs = statement.executeQuery(sql);

            // step 5: extract data from result set
            if(rs.next()){
                isValidUser = true;
            }

            // step 6: close
            rs.close();
            statement.close();
            connection.close();

        }
        catch (SQLException se)
        {
            // handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e)
        {
            // handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            // block used to close resources
            try {
                if(statement != null)
                {
                    statement.close();
                }
            }
            catch (SQLException se2)
            {
            }

            try {
                if(connection != null)
                {
                    connection.close();
                }
            }
            catch (SQLException se3)
            {
                se3.printStackTrace();
            }
        }
        System.out.println("Closing DB connection ...");

        return isValidUser;
    }
}
