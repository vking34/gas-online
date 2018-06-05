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

import dataLayer.join_db;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import webapp.from_cookie.cookies;

@WebServlet(name = "getLastOrder")
public class getLastOrder extends cookies {
    String username = "";
    String lastOrder = "";
    join_db data = new join_db();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Get last order from android");
        // read json
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        json = br.readLine();
        System.out.println("json: " + json);
        br.close();

        JSONObject user = new JSONObject(json);
        username = user.getString("username");

        lastOrder = data.getLastOrder(username);
        System.out.println("last order: " + lastOrder);

        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(lastOrder);
            out.close();
        }
        catch (Exception  e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Get last order from browser");
        username = super.get_username(request);
        if (username != null) {

            lastOrder = data.getLastOrder(username);
            System.out.println(lastOrder);

            try {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print(lastOrder);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
