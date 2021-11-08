
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="register.js"></script>
        <title>Register</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jspf" %>
        <br><br>
        <br><br>
        <div class="container">
            <br><br>
            <h2>Registration page</h2>
            <br><br>
            
            <div class="alert d-none" id="alertBox" role="alert">
                <p id="message"></p>
            </div>
                <div class="mb-3">
                    <label class="form-label"> User Name </label>
                    <input class="form-control" placeholder="Enter User Name" required id="userName">
                </div>
                <div class="mb-3">
                    <label class="form-label"> First Name </label>
                    <input class="form-control" placeholder="Enter First Name" name="fName" id="firstName">
                </div>
                <div class="mb-3">
                    <label class="form-label"> Last Name </label>
                    <input class="form-control" placeholder="Enter Last Name" name="lName" id="lastName">
                </div>
                <div class="mb-3">
                    <label class="form-label"> Phone Number </label>
                    <input type="number" class="form-control" placeholder="Enter Phone Number" required id="pNumber">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label"> Password </label>
                    <input type="password" max="9" class="form-control" placeholder="Enter password" name="password" required id="password">
                </div>

                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label"> Confirm Password </label>
                    <input type="password" class="form-control" placeholder="Enter password" name="con_password" required id="con_password">
                </div>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button type="button" class="btn btn-primary" id="btn-reg"> Register </button>
                <a href="login.jsp">Login</a> 
</div>
        </div>
            <br><br>
    </body>
</html>
