package dataLayer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class product_db {

    static Connection connection = db.getConnection();

    public String getProducts(){
        Statement statement = null;
        String sql = null;


        try {
            statement = connection.createStatement();

            sql = "(SELECT type, name, price, brand, img FROM gas_device) UNION (SELECT 1 as type, name, price, brand, img FROM gas_cylinder);";

            ResultSet rs = statement.executeQuery(sql);

            JSONArray products = new JSONArray();

            while (rs.next()){
                JSONObject product = new JSONObject();
                product.put("type", rs.getInt("type"));
                product.put("name", rs.getString("name"));
                product.put("price", rs.getInt("price"));
                product.put("brand", rs.getString("brand"));
                product.put("img", rs.getString("img"));
                products.put(product);
            }

            return products.toString();
        } catch (SQLException e) {
            return null;
        }
    }
}
