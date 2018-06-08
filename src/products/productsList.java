package products;

import dataLayer.product_db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "productsList")
public class productsList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String products = new product_db().getProducts();
        System.out.println(products);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(products);
        out.close();

    }
}
