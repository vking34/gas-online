package webapp.signin_signup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dataLayer.user_db;
import org.json.JSONObject;

@WebServlet(name = "changePass")
public class changePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;

            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                System.out.println("name: " + cookie.getName() + " value: " + cookie.getValue());
                if (cookie.getName().equals("username") && cookie.getValue() != null) {
                    String username = cookie.getValue();
                    break;
                }
            }
            response.sendRedirect("/login");
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        // read json string of request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        json = br.readLine();
        System.out.println(json);
        br.close();

        // parse to JSON Object
        JSONObject pass = new JSONObject(json);

        System.out.println(pass.getString("oldPass"));
        System.out.println(pass.getString("newPass"));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;

            for(int i = 0; i < cookies.length ; i++)
            {
                cookie = cookies[i];
                System.out.println("name: "+ cookie.getName() + " value: "+ cookie.getValue());
                if (cookie.getName().equals("username") && cookie.getValue() != null)
                {
                    request.getRequestDispatcher("/userSites/changePassword.html").forward(request, response);
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
