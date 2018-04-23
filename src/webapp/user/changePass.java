package webapp.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import appLayer.User;
import org.json.JSONObject;
import webapp.from_cookie.cookies;

@WebServlet(name = "changePass")
public class changePass extends cookies {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = super.get_username(request);
        if(username == null)
        {
            response.sendRedirect("/login");
        }
        else
        {
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

            User user = new User(username, pass.getString("oldPass"));
            String status = "{\"status\":";

            if(user.isValidUserCredentials() && user.changePassword(pass.getString("newPass"))){
                status += " true}";
            }
            else {
                status += " false}";
            }

            try {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print(status);
                out.close();
            }
            catch (Exception  e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        try {
//            Cookie[] cookies = request.getCookies();
//            Cookie cookie = null;
//
//            for(int i = 0; i < cookies.length; i++)
//            {
//                cookie = cookies[i];
//                System.out.println("name: "+ cookie.getName() + " value: "+ cookie.getValue());
//                if (cookie.getName().equals("username") && cookie.getValue() != null)
//                {
//                    request.getRequestDispatcher("/userSites/changePassword.html").forward(request, response);
//                    return;
//                }
//            }
//            response.sendRedirect("/login");
//        }
//        catch (NullPointerException e)
//        {
//            e.printStackTrace();
//            response.sendRedirect("/login");
//        }

        String username = this.get_username(request);
        if(username == null)
        {
            response.sendRedirect("/login");
        }
        else {
            request.getRequestDispatcher("/userSites/changePassword.html").forward(request, response);
        }
    }
}
