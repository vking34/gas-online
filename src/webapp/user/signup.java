package webapp.user;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import com.google.gson.Gson;
import org.json.JSONObject;


@WebServlet(name = "signup")
public class signup extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Just received POST request from 'Sign Up'...");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";

        json = br.readLine();
        System.out.println(json);
        br.close();

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        System.out.println(user.getUsername());

        int errorCode = user.isExistingUser(user.getUsername(), user.getPhoneNumber(), user.getEmail());
        System.out.println(errorCode);
        try{
            response.setContentType("application/json; chatset=UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject mess = new JSONObject();
            mess.put("status", false);

            switch (errorCode) {
                case 1:
                    mess.put("errorCode", 1);   // usernameError
                    break;
                case 2:
                    mess.put("errorCode", 2);   // phoneNumberError
                    break;
                case 3:
                    mess.put("errorCode", 3);   // emailError
                    break;
                default:                        // errorCode = 0
                    mess.put("status", true);
                    mess.put("url", "/welcome");
            }
            String outJson = mess.toString();
            out.print(outJson);
            out.close();
        }
        catch (Exception e){
         e.printStackTrace();
        }
        if(errorCode == 0){
            user.insertDB();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/userSites/signup.html").forward(request, response);
    }
}
