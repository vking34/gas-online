package webapp.order;

import dataLayer.user_db;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "orderHistory")
public class orderHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(StringUtils.containsIgnoreCase(request.getHeader("user-agent"), "Android")) {
            // read json
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            json = br.readLine();
            System.out.println(json);
            br.close();

            // convert to JSONObject
            JSONArray userArr = new JSONArray(json);
            JSONObject user = userArr.getJSONObject(0);
            user_db userDb = new user_db(user.getString("username"));
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            String outJson = userDb.getOrderHistory();
            if(outJson == null)
            {
                outJson = "[]";
            }
            out.println(outJson);
            out.close();
        }
        else {
            try {
                Cookie[] cookies = request.getCookies();
                Cookie cookie = null;

                for(int i = 0; i < cookies.length ; i++)
                {
                    cookie = cookies[i];
                    System.out.println("name: "+ cookie.getName() + " value: "+ cookie.getValue());
                    if (cookie.getName().equals("username") && cookie.getValue() != null)
                    {
                        user_db userDb = new user_db(cookie.getValue());
                        String jsonString = userDb.getOrderHistory();
                        String jsonArray = "{\"data\": " + jsonString + "}";
                        System.out.println(jsonArray);
                        response.setContentType("application/json; charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println(jsonArray);
                        out.close();
                        return;
                    }
                }

                response.sendRedirect("/login");
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
                response.sendRedirect("/login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Get cookie...");

        try {

            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;

            for(int i = 0; i < cookies.length ; i++)
            {
                cookie = cookies[i];
                System.out.println("name: "+ cookie.getName() + " value: "+ cookie.getValue());
                if (cookie.getName().equals("username") && cookie.getValue() != null)
                {
                    request.getRequestDispatcher("/orderSites/orderHistory.html").forward(request, response);
                    return;
                }
            }

            response.sendRedirect("/login");
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            response.sendRedirect("/login");
        }
    }
}
