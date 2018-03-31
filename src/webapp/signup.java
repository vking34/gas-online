package webapp;

import appLayer.Redirect;
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

import appLayer.registerError;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name = "signup")
public class signup extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("Just received POST request from 'Sign Up'...");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }
        br.close();
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();


        User user = mapper.readValue(json, User.class);
//        System.out.println(user);

//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println(user.getPassword1());
//        System.out.println(user.getPhoneNumber());
//        System.out.println(user.getEmail());

//        registerError re = new registerError();
//        re.setUsernameError("username exist");
//        re.setPhoneNumberError("phone exist");
//
//        try {
//            response.setContentType("application/json");
//            PrintWriter out = response.getWriter();
//            out.println(re.toString());
//            out.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

//        Redirect r = new Redirect();
//        r.setStatus(true);
//        r.setUrl("/");
//        System.out.println(r.toString());
//
//        try{
//            response.setContentType("application/json");
//            PrintWriter out = response.getWriter();
//            out.println(r.toString());
//            out.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        int errorCode = user.isExistingUser(user.getUsername(), user.getPhoneNumber(), user.getEmail());
        if( errorCode != 0)
        {
            registerError re = new registerError();
            if(errorCode == 1)
            {
                re.setUsernameError(user.getUsername() + " exists!");
                System.out.println("username error!!!");
            }
            else if(errorCode == 2)
            {
                re.setPhoneNumberError(user.getPhoneNumber() + " exists!");
                System.out.println("phoneNumber error !!!");
            }
            else {
                re.setEmailError(user.getEmail() + " exists!");
                System.out.println("email error!!!");
            }

            try {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.println(re.toString());
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {

            user.insertDB();
            System.out.println("inserted.");
            Redirect r = new Redirect();
            r.setUrl("/welcome");
            r.setStatus(true);
            System.out.println(r.toString());

            try{
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(r.toString());
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
