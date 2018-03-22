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

    <link rel="stylesheet" href="css/accounts.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Arimo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/app.css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/userObj.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Gas Order</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainMenu" aria-controls="mainMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainMenu">
            <form class="form-inline ml-auto">
                <a href="/login" class="btn btn-outline-secondary">Log in</a>
                <a href="/signup" class="btn btn-primary ml-2">Sign up</a>
            </form>
        </div>
    </div>
</nav>

<h1 class="text-center logo my-4">
    <a href="/login">Sign up</a>
</h1>

<div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="card">
            <div class="card-body">
                <%--<h3 class="card-title">Sign up</h3>--%>
                <form method="post" novalidate>
                    First name:
                    <input type="text" name="firstName" id="firstName" width="40"/><br><br>
                    Last name:
                    <input type="text" name="lastName" id="lastName" width="40"/><br><br>
                    Username:
                    <input type="text" name="username" id="username" width="40"/>
                    <small id="usernameError"></small>
                    <br><br>
                    Password:
                    <input type="password" name="password" id="password"/><br><br>
                    Confirm password:
                    <input type="password" name="password1" id="password1"/><br><br>
                    Phone number:
                    <input type="text" name="phoneNumber" id="phoneNumber" width="11"/><br><br>
                    <small id="phoneNumberError"></small>
                    Email:
                    <input type="text" name="email" id="email" width="60"/><br><br>
                    <small id="emailError"></small>
                    <button type="button" onclick="sendObj()" class="btn btn-primary btn-block">Create an account</button>
                </form>
            </div>
            <div class="card-footer text-muted text-center">
                Already have an account? <a href="/login">Log in</a>
            </div>
        </div>
    </div>
</div>

<%--<form action="/signup" method="post">--%>
    <%--First name: <input type="text" name="firstName" id="firstName" width="40"/>--%>
    <%--Last name: <input type="text" name="lastName" id="lastName" width="40"/><br><br>--%>
    <%--Username: <input type="text" name="username" id="username" width="40"/>--%>
    <%--<small id="usernameError"></small>--%>
    <%--<br><br>--%>
    <%--Password: <input type="password" name="password" id="password"/><br><br>--%>
    <%--Confirm password: <input type="password" name="password1" id="password1"/><br><br>--%>
    <%--Phone number: <input type="text" name="phoneNumber" id="phoneNumber" width="11"/><br><br>--%>
    <%--<small id="phoneNumberError"></small>--%>
    <%--Email: <input type="text" name="email" id="email" width="60"/><br><br>--%>
    <%--<small id="emailError"></small>--%>
    <%--<button class="btn btn-primary" type="button" onclick="sendObj()">Create</button>--%>
<%--</form>--%>
<%--<ul id="error"></ul>--%>

</body>
</html>
