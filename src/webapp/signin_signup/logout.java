package webapp.signin_signup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout")
public class logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie ck = new Cookie("username", "");
        ck.setMaxAge(0);
        response.addCookie(ck);
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie ck = new Cookie("username", "");
        ck.setMaxAge(0);
        response.addCookie(ck);
        response.sendRedirect("/");

//        Cookie ck[] = request.getCookies();
//
//        if(ck!=null)
//        {
//            ck[0].setMaxAge(0);
//            response.addCookie(ck[0]);
//        }
//
//        response.sendRedirect("/");

    }
}
