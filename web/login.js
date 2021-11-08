$(document).ready(function () {
    $('#btn-login').click(function ()
    {
        var userName = $('#userName').val();
        var password = $('#password').val();
        var loger = "user";
        
        if($('#adminLogCheck').is(':checked')){
            loger = "admin";
        }

        if (userName.toString() === "") {
            show("alert-info", "User Name Empty");
        } else if (password.toString() === "") {
            show("alert-info", "Password Empty");
        } else {
            $.ajax({
                type: "POST",
                url: "login",
                data: {"userName": userName, "password": password, "loger": loger},
                success: function (data) {
                    if (data == 'User') {
                        sessionStorage.setItem("loger", userName);
                        $(location).attr('href', 'userPage.jsp');
                    } else if(data == 'Admin'){
                        sessionStorage.setItem("loger", userName);
                        $(location).attr('href', 'adminPage.jsp');
                    } else if(data == 'error_uName'){
                        show("alert-warning", "User Name Error! Please regidter");
                    } else if(data == 'error_uPassword'){
                        show("alert-danger", "Wrong Password!! Try Again...");
                    } else {
                        show("alert-danger", data.toString());
                    }
                },
                error: function (request, error) {
                    console.log(arguments);
                    alert(" Can't do because: " + error);
                }
            });
        }

    });


});

function show(color, message) {
    $('#alertBox').removeClass("d-none");
    $('#alertBox').addClass(color);
    $('#message').text(message);
}