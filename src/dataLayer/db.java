package dataLayer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class db {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://btdc0xcbj-mysql.services.clever-cloud.com:3306/btdc0xcbj";

    static final String USER = "utuu6mcqmehz7exr";
    static final String PASS = "j8vhyB73s6LdBGMuKjD1";
    static final String driver = "com.mysql.jdbc.Driver";


    static Connection connection = null;

    static {
        try
        {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/DSpool");
            connection = ds.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

}
