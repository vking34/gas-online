package webapp;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u1 = new User();

        u1.setUsername(request.getParameter("username"));
        u1.setPassword(request.getParameter("password"));

        if(u1.isValidUserCredentials() == true)
        {
            Cookie cookie = new Cookie("username", u1.getUsername());
            cookie.setMaxAge(30*60);
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
        else
        {
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("/login.html").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request,response);
    }

}
