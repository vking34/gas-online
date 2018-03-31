package dataLayer;

import java.sql.*;
import java.lang.*;


public class user_db {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";
    static final String driver = "com.mysql.jdbc.Driver";

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
            Class.forName(driver);

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

    public int isExistingUser(String username, String phoneNumber, String email)
    {
        // step 1: set parameters
        int isExistingUser = 0;
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            // step 2: register JDBC driver
            Class.forName(driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT * FROM user WHERE username =\"" + username + "\";";

            System.out.println(sql);

            ResultSet rs = statement.executeQuery(sql);

            // step 5: extract data from result set

            if(rs.next())
            {
                isExistingUser = 1;
            }
            else {
                sql = "SELECT * FROM user WHERE phoneNumber =\"" + phoneNumber + "\";";
                System.out.println(sql);

                rs = statement.executeQuery(sql);
                if(rs.next())
                {
                    isExistingUser = 2;
                }
                else {
                    sql = "SELECT * FROM user WHERE email =\"" + email + "\";";
                    System.out.println(sql);

                    rs = statement.executeQuery(sql);
                    if(rs.next())
                    {
                        isExistingUser = 3;
                    }
                }
            }
            //step 6: close connection
            rs.close();
            connection.close();
            statement.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return isExistingUser;
    }

    public boolean insert(String usename, String password, String phoneNumber, String email, String firstName, String lastName)
    {

        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            // step 2: register JDBC driver
            Class.forName(driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "INSERT INTO user (username, password, phoneNumber, email, firstName, lastName) VALUES ('" +usename+ "', '" + password + "', '" + phoneNumber + "', '"+ email + "', '"+ firstName + "', '" + lastName + "');";
            System.out.println(sql);

             statement.executeUpdate(sql);

            //step 6: close connection

            connection.close();
            statement.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
