package dataLayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;
import java.lang.*;


public class user_db {

    private String username;

    static Connection connection = db.getConnection();

    public user_db(){}

    public user_db(String name)
    {
        this.username = name;
    }

    public boolean isValidUserLogin(String username, String password)
    {
        // step 1: set the parameters
        boolean isValidUser = false;
        Statement statement = null;
        String sql = "";

        try
        {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

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

        }
        System.out.println("Closing DB connection ...");

        return isValidUser;
    }

    public int isExistingUser(String username, String phoneNumber, String email)
    {
        // step 1: set parameters
        int isExistingUser = 0;
//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
//            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            //step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT * FROM user WHERE username ='" + username + "';";

            System.out.println(sql);

            ResultSet rs = statement.executeQuery(sql);

            // step 5: extract data from result set

            if(rs.next())
            {
                isExistingUser = 1;     // usernameError
            }
            else {
                sql = "SELECT * FROM user WHERE phoneNumber ='" + phoneNumber + "';";
                System.out.println(sql);

                rs = statement.executeQuery(sql);
                if(rs.next())
                {
                    isExistingUser = 2; // phoneNumberError
                }
                else {
                    sql = "SELECT * FROM user WHERE email ='" + email + "';";
                    System.out.println(sql);

                    rs = statement.executeQuery(sql);
                    if(rs.next())
                    {
                        isExistingUser = 3; // emailError
                    }
                }
            }

            //step 6: close connection
            rs.close();
//            connection.close();
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

//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "INSERT INTO user (username, password, phoneNumber, email, firstName, lastName) VALUES ('" +usename+ "', '" + password + "', '" + phoneNumber + "', '"+ email + "', '"+ firstName + "', '" + lastName + "');";
            System.out.println(sql);

            statement.executeUpdate(sql);

            sql = "INSERT INTO address (userID) SELECT id FROM user WHERE username = '" + usename + "';";

            System.out.println("inserted User");
            //step 5: close connection

//            connection.close();
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
//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            //step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT o.date, o.phoneNumber, concat(o.address, ', ', r.ward, ' ward, ', r.district, ' district') as address, c.brand, c.size, c.price, o.status FROM user u, gas_order o, gas_cylinder c, region r, warehouse w WHERE u.username = '"+ this.username +"' AND (u.id = o.userID OR u.phoneNumber = o.phoneNumber) AND o.warehouse = w.houseCode AND w.regionCode = r.code AND o.gasCode = c.code ORDER BY o.date ASC;";
            System.out.println(sql);

            // execute
            ResultSet rs = statement.executeQuery(sql);

            JsonArray data = new JsonArray();

            while (rs.next())
            {
                JsonObject row = new JsonObject();
                row.addProperty("date", rs.getString("date"));
                row.addProperty("phoneNumber", rs.getString("phoneNumber"));
                row.addProperty("address", rs.getString("address"));
                row.addProperty("brand", rs.getString("brand"));
                row.addProperty("size", rs.getInt("size"));
                row.addProperty("price", rs.getInt("price"));
                row.addProperty("status", rs.getString("status"));
                data.add(row);
            }

            // step 5: close
            rs.close();
//            connection.close();
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

    public String getInfo(){

        // step 1: Set parameters
//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Cj8vhyB73s6LdBGMuKjD1onnecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "SELECT concat(ucase(left(firstName, 1)),substr(firstName, 2), ' ', ucase(left(lastName, 1)),substr(lastName, 2)) as name, phoneNumber, email, point FROM user WHERE username = '" + this.username + "';";

            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);

            JsonObject info = new JsonObject();
            if (rs.next()){
                info.addProperty("status", true);
                info.addProperty("name", rs.getString("name"));
                info.addProperty("phoneNumber", rs.getString("phoneNumber"));
                info.addProperty("email", rs.getString("email"));
                info.addProperty("point", rs.getInt("point"));
            }
            else {
                info.addProperty("status", false);
            }
            return info.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePass(String username, String newPass){
        // step 1: Set parameters
//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "UPDATE user SET password = '" + newPass + "' WHERE username = '" + username + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);

            //step 5: close connections
//            connection.close();
            statement.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePhone(String username, String newPhoneNumber){
        // step 1: Set parameters
//        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            // step 2: register JDBC driver
//            Class.forName(db.driver);
//
//            // step 3: open a connection
//            System.out.println("Connecting to database...");
//            connection = DriverManager.getConnection(db.DB_URL, db.USER, db.PASS);

//            connection = db.getConnection();

            // step 4: execute a query
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            sql = "UPDATE user SET phoneNumber = '" + newPhoneNumber + "' WHERE username = '"+ username + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);

            //step 5: close connections
//            connection.close();
            statement.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
