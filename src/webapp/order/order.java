package webapp.order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import appLayer.orderDetails;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataLayer.order_db;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import webapp.from_cookie.cookies;

@WebServlet(name = "orderForm")
public class order extends cookies {

    private static final long serialVersionUID = 1L;
    private Boolean status = false;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Just received POST request from 'Order Form'...");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }
        br.close();

//        Gson gson = new Gson();
//
//        orderDetails ord = gson.fromJson(json, orderDetails.class);
//
//        System.out.println(ord.getGasCode());
//        System.out.println(ord.getPhoneNumber());
//        System.out.println(ord.getAddress());
//        System.out.println(ord.getRegionCode());

        JSONObject ord = new JSONObject(json);
        System.out.println(ord.getString("phoneNumber"));
        System.out.println(ord.getString("ward"));
        System.out.println(ord.getString("address"));
        System.out.println(ord.getInt("gasCode"));

        String username = null;
        if(!StringUtils.containsIgnoreCase(request.getHeader("user-agent"), "Android"))
        {
            System.out.println("Getting username...");
            username = super.get_username(request);
        }
        else {

            try{
                username = ord.getString("username");
            }catch (JSONException e)
            {
                username = null;
                e.printStackTrace();
            }
        }

        order_db orderDb = new order_db();
        String outJson;
        if(username != null )
        {
            outJson = orderDb.insertOrder(username, ord);
        }
        else {
            outJson = orderDb.insertOrder(ord);
        }

        System.out.println(outJson);

        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (outJson != null) {
                out.print(outJson);
                System.out.println(outJson);
                status = true;
            }
            else {
                out.print("{ \"status\":false }");
                System.out.println("status: false");
            }
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        String username = super.get_username(request);
        if(username != null && status){
            orderDb.updateAddress(username, ord);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/orderSites/orderForm.html").forward(request,response);
    }
}
