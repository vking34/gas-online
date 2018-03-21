<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/20/18
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/userObj.js"></script>
</head>
<body>

<h1>Create a new account.</h1>

<form action="/signup" method="post">
    First name: <input type="text" name="firstName" id="firstName" width="40"/>
    Last name: <input type="text" name="lastName" id="lastName" width="40"/><br><br>
    Username: <input type="text" name="username" id="username" width="40"/>
    <small id="usernameError"></small>
    <br><br>
    Password: <input type="password" name="password" id="password"/><br><br>
    Confirm password: <input type="password" name="password1" id="password1"/><br><br>
    Phone number: <input type="text" name="phoneNumber" id="phoneNumber" width="11"/><br><br>
    <small id="phoneNumberError"></small>
    Email: <input type="text" name="email" id="email" width="60"/><br><br>
    <small id="emailError"></small>
    <button class="btn btn-primary" type="button" onclick="sendObj()">Create</button>
</form>
<ul id="error"></ul>

</body>
</html>
