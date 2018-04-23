package webapp.user;

import appLayer.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        json = br.readLine();
        System.out.println(json);
        br.close();

        // parse to JSON object
        JSONObject user = new JSONObject(json);

        System.out.println(user.getString("username"));
        System.out.println(user.getString("password"));

        User u = new User(user.getString("username"), user.getString("password"));


        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (u.isValidUserCredentials()) {
                Cookie cookie = new Cookie("username", u.getUsername());
                cookie.setMaxAge(45*60);
                response.addCookie(cookie);
                out.print("{\"url\":\"/\",\"status\":true}");
            }
            else {
                out.print("{ \"status\":false }");
            }
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/userSites/login.html").forward(request,response);
    }
}
