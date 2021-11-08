
<%@page import="model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="login.js"></script>
        <title>Login</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jspf" %>
        <br><br>

        <div class="container">
            <h2>Login page</h2>
            <br><br>
            <div class="alert d-none" id="alertBox" role="alert">
                <p id="message"></p>
            </div>

            <form >
                <div class="mb-3">
                    <label class="form-label">User Name</label>
                    <input class="form-control" placeholder="Enter User Name" name="userName" id="userName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" placeholder="Enter password" name="password" id="password">
                </div>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" role="switch" id="adminLogCheck">
                    <label class="form-check-label"  for="flexSwitchCheckDefault"> Admin </label>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button type="button" class="btn btn-primary" id="btn-login"> logIn </button>
                    <a href="register.jsp">Register</a> 
                </div>
            </form>
        </div>
    </body>
</html>
