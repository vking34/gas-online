package webapp;

import dataLayer.user_db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "orderHistory")
public class orderHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.println(jsonString);
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
                    request.getRequestDispatcher("/orderHistory.html").forward(request, response);
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

    public Cookie getCookie(HttpServletRequest request, String name)
    {
        try {
            Cookie[] cookies = request.getCookies();

            if(cookies != null)
            {
                for(Cookie cookie : cookies)
                {
                    if(cookie.getName().equals(name))
                        return cookie;
                }
            }

            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
