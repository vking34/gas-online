package webapp;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u1 = new User();

        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password"));

        if(u1.isValidUserCredentials(request.getParameter("username"), request.getParameter("password")) == true)
        {
            request.getRequestDispatcher("/").forward(request,response);
        }
        else
        {
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);

//        PrintWriter out = response.getWriter();
//        out.print("Login name: " + request.getParameter("username") + " Password: " + request.getParameter("password"));
    }

}
