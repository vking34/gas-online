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
    <%--<link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />--%>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>--%>

    <link rel="stylesheet" href="css/home.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Arimo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/app.css">

      <%--another sample--%>
    <%--<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <%--<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>--%>
    <%--<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

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

  <br><br><br>
  <div class="container">
    <div class="row">
      <div class="span8">
        <form action="/order" method="post" class="form-horizontal" id="billingform" accept-charset="utf-8">

          <div class="control-group">
            <label for="phoneNumber" class="control-label">
              Phone Number
            </label>
            <div class="controls">
              <input name="phoneNumber" type="text" value="" id="phoneNumber">
              <span class="help-inline"> *We will call you right after you sumbit to confirm the address.</span>
            </div>
          </div>

          <div class="control-group">
            <label for="address" class="control-label">
              Street Address
            </label>
            <div class="controls"><input name="address" placeholder="254D Bach Mai" type="text" value="" id="address"><span class="help-inline"> The delivery address </span>
            </div>
          </div>

          <div class="control-group">
            <label for="district" class="control-label">
              District
            </label>
            <div class="controls">
              <select name="district" id="district">
                <option value=""></option>
                <option value="HBT">Hai Ba Trung</option>
                <option value="DD">Dong Da</option>
                <option value="BD">Ba Dinh</option>
                <option value="HK">Hoan Kiem</option>
                <span class="help-inline"> Default: Set address to be your current position.</span>
              </select>

            </div>
          </div>

          <div class="control-group">
            <label for="ward" class="control-label">
              Ward
            </label>
            <div class="controls">
              <%--<input name="ward" type="text" value="" id="ward">--%>
                <select name="ward" id="ward">
                  <option value=""></option>
                  <option value="BHT_BM">Bach Mai</option>
                  <option value="BHT_BK">Bach Khoa</option>
                  <option value="BHT_PH">Pho Hue</option>
                  <option value="BHT_DT">Dong Tam</option>
                  <option value="DD_PM">Phuong Mai</option>
                  <option value="DD_OCD">O Cho Dua</option>
                </select>
            </div>
          </div>

          <div class="form-actions">
            <button type="submit" class="btn btn-large btn-primary">Order now</button>
          </div>
        </form>
      </div> <!-- .span8 -->
    </div>
  </div>

  <%
    Date date = new Date();
    out.print("<p>" + date.toString() + "</p>");
  %>

  </body>
</html>
