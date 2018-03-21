package webapp;

import appLayer.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import java.lang.Throwable;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.SourceTree;


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
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getPassword1());
        System.out.println(user.getPhoneNumber());
        System.out.println(user.getEmail());

        registerError re = new registerError();
        re.setUsernameError("username exist");
        re.setPhoneNumberError("phone exist");

        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(re.toString());
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
