package webapp.signin_signup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import dataLayer.user_db;

@WebServlet(name = "profile")
public class profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = "";

        try {

            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;

            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                System.out.println("name: " + cookie.getName() + " value: " + cookie.getValue());
                if (cookie.getName().equals("username") && cookie.getValue() != null){
                    username = cookie.getValue();
                    break;
                }
            }

            user_db user = new user_db(username);

            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(user.getInfo());
            out.close();
        }catch(NullPointerException e)
        {
            e.printStackTrace();
            response.sendRedirect("/login");
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
                    request.getRequestDispatcher("/userSites/profile.html").forward(request, response);
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
