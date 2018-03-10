<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/10/18
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Welcome</h1>
<h2>Login</h2>

<form action="/login" method="post">
    Username: <input type="text" name="username" width="30">
    Password: <input type="password" name="password" id="30">
    <input type="submit" value="login"/>
</form>
    <p style="color: red">${errorMessage}</p>
</body>
</html>
