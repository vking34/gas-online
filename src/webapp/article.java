package webapp;

import appLayer.Article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
//import com.sun.org.apache.xpath.internal.SourceTree;
//import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.IOUtils;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name="article")
public class article extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // This will store all received articles
    List<Article> articles = new LinkedList<Article>();

    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        System.out.println("Just received POST request...");

        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        System.out.println(json);
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        Article article = mapper.readValue(json, Article.class);

        // 4. Set response type to JSON
        response.setContentType("application/json");

        // 5. Add article to List<Article>
        articles.add(article);

        // 6. Send List<Article> as JSON to client
        mapper.writeValue(response.getOutputStream(), articles);

    }

    private void convert(HttpServletRequest request)
    {
        System.out.println("!");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("!");
        try {
            Article article = mapper.readValue(request.getReader(), Article.class);
            System.out.println("!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.getRequestDispatcher("/article.html").forward(request,response);
    }
}

