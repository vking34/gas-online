package tips;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "tipNumber")
public class tipNumber extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("tip number");

        String path = request.getServletPath();

        request.getRequestDispatcher("/tips/tips-article-" + path.substring(path.length()-1) + ".html").forward(request, response);

//        try{
//
//            request.getRequestDispatcher("/tips/tips-article-" + i + ".html").forward(request, response);
//        }catch (Exception e)
//        {
//            System.out.println(e.toString());
//        }
    }
}
