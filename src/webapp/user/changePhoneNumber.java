package webapp.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import appLayer.User;
import org.json.JSONObject;
import webapp.from_cookie.cookies;

@WebServlet(name = "changePhoneNumber")
public class changePhoneNumber extends cookies {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = super.get_username(request);
        if (username == null) {
            response.sendRedirect("/login");
        } else {
            // read json string of request
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            json = br.readLine();
            System.out.println(json);
            br.close();

            // parse to JSON Object
            JSONObject data = new JSONObject(json);

            User user = new User(username, data.getString("password"));
            String status = "{\"status\":";

            if (user.isValidUserCredentials() && user.changePhone(data.getString("newPhoneNumber"))) {
                status += " true}";
            } else {
                status += " false}";
            }

            try {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print(status);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
