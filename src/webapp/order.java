package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import appLayer.orderDetails;
import com.google.gson.Gson;

@WebServlet(name = "orderForm")
public class order extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Just received POST request from 'Order Form'...");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }
        br.close();

        Gson gson = new Gson();

        orderDetails ord = gson.fromJson(json, orderDetails.class);

        System.out.println(ord.getGasCode());
        System.out.println(ord.getPhoneNumber());
        System.out.println(ord.getAddress());
        System.out.println(ord.getRegionCode());





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/orderForm.html").forward(request,response);
    }
}
