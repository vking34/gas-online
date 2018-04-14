package dataLayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;
import java.lang.*;


public class user_db {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/gas_online";

    static final String USER = "root";
    static final String PASS = "";
    static final String driver = "com.mysql.jdbc.Driver";
    private String username;

    public user_db()
    {

    }
    public user_db(String name)
    {
        this.username = name;
    }

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

            //step 5: close connection

            connection.close();
            statement.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    public String getOrderHistory()
    {
        // step 1: Set parameters
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            // step 2: register JDBC driver
            Class.forName(this.driver);

            // step 3: open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT o.date, o.billCode, concat(o.address, ', ', r.ward, ', ', r.district) as address, c.brand, c.size, c.price, o.status FROM user u, gas_order o, gas_cylinder c, region r, warehouse w WHERE u.username = '"+ this.username +"' AND u.phoneNumber = o.phoneNumber AND o.warehouse = w.houseCode AND w.regionCode = r.code AND o.gasCode = c.code ORDER BY o.date DESC ;";
            System.out.println(sql);

            // execute
            ResultSet rs = statement.executeQuery(sql);

            JsonArray data = new JsonArray();

            while (rs.next())
            {
                JsonObject row = new JsonObject();
                row.addProperty("date", rs.getString("date"));
                row.addProperty("billCode", rs.getString("billCode"));
                row.addProperty("address", rs.getString("address"));
                row.addProperty("brand", rs.getString("brand"));
                row.addProperty("size", rs.getInt("size"));
                row.addProperty("price", rs.getInt("price"));
                row.addProperty("status", rs.getString("status"));
                data.add(row);
            }

            // step 5: close
            rs.close();
            connection.close();
            statement.close();

            String jsonString = data.toString();
            if(jsonString.equals("[]"))
            {
                return null;
            }
            else
            {
                System.out.println(jsonString);
                return jsonString;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
