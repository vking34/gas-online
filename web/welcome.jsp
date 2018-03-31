<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/10/18
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="css/home.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Arimo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/app.css">
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

<h1>Create successfully</h1>
<p>Please try to login</p>

</body>
</html>
