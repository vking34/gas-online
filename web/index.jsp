<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/10/18
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Gas Online</title>
  </head>
  <body>
  <h1>Hello World</h1>
  <p>Body text. This is my first webapp JSP page.</p>
  <h2>let's get started.</h2>
  <h3>update</h3>
  <%
    Date date = new Date();
    out.print("<h4>" + date.toString() + "</h4>");
  %>

  </body>
</html>
