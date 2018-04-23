package webapp.from_cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class cookies extends HttpServlet {

    public String get_username(HttpServletRequest request){
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;

            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                System.out.println("name: " + cookie.getName() + " value: " + cookie.getValue());
                if (cookie.getName().equals("username") && cookie.getValue() != null){
                    String username = cookie.getValue();
                    return username;
                }
            }
        }catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
