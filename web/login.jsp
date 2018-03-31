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

    <link rel="stylesheet" href="css/accounts.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Arimo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/app.css">

    <%--another sample--%>

    <%--<link rel="stylesheet" href="css/user.css">--%>
    <%--<script src="js/user.js"></script>--%>
    <%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>
    <%--<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

    <%--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">--%>
    <%--<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />--%>

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

<div class="container">
    <h1 class="text-center logo my-4">
        <a href="/login">Login</a>
    </h1>
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-6 col-sm-8">
            <div class="card">
                <div class="card-body">
                    <%--<h3 class="card-title">Log in</h3>--%>
                    <form action="/login" method="post" novalidate>
                        Username:<br>
                        <input name="username" id="username" type="text">
                        <br><br>
                        Password:<br>
                        <input name="password" id="password" type="password">
                        <br><br>
                        <button type="submit" class="btn btn-primary btn-block">Log in</button>
                    </form>
                </div>
                <div class="card-footer text-muted text-center">
                    Create a new account? <a href="/signup">Sign up</a>
                </div>
            </div>
            <div class="text-center py-2">
                <small>
                    <a href="#" class="text-muted">Forgot your password?</a>
                </small>
            </div>
        </div>
    </div>

    <%--<div class="text-center" style="padding:50px 0">--%>
        <%--<div class="logo">login</div>--%>
        <%--<!-- Main Form -->--%>
        <%--<div class="login-form-1">--%>
            <%--<form id="login-form" class="text-left">--%>
                <%--<div class="login-form-main-message"></div>--%>
                <%--<div class="main-login-form">--%>
                    <%--<div class="login-group">--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="lg_username" class="sr-only">Username</label>--%>
                            <%--<input type="text" class="form-control" id="lg_username" name="lg_username" placeholder="username">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="lg_password" class="sr-only">Password</label>--%>
                            <%--<input type="password" class="form-control" id="lg_password" name="lg_password" placeholder="password">--%>
                        <%--</div>--%>
                        <%--<div class="form-group login-group-checkbox">--%>
                            <%--<input type="checkbox" id="lg_remember" name="lg_remember">--%>
                            <%--<label for="lg_remember">remember</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>--%>
                <%--</div>--%>
                <%--<div class="etc-login-form">--%>
                    <%--<p>forgot your password? <a href="#">click here</a></p>--%>
                    <%--<p>new user? <a href="#">create new account</a></p>--%>
                <%--</div>--%>
            <%--</form>--%>
        <%--</div>--%>
        <%--<!-- end:Main Form -->--%>
    <%--</div>--%>


</div>

<%--<h1>Welcome</h1>--%>
<%--<h2>Login</h2>--%>

<%--<form action="/login" method="post">--%>
    <%--Username: <input type="text" name="username" width="30">--%>
    <%--Password: <input type="password" name="password" id="30">--%>
    <%--<input type="submit" value="login"/>--%>
<%--</form>--%>
    <%--<p style="color: red">${errorMessage}</p>--%>
</body>
</html>
