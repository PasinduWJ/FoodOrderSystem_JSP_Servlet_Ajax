$(document).ready(function () {
    $('#btn-reg').click(function ()
    {
        var userName = $('#userName').val();
        var password = $('#password').val();
        var con_password = $('#con_password').val();
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var pNumber = $('#pNumber').val();

        if (!userName ) {
            show("alert-info", "User Name Empty");
        } else if (!firstName) {
            show("alert-info", "First Name Empty");
        } else if (!lastName) {
            show("alert-info", "Last Name Empty");
        } else if (!pNumber) {
            show("alert-info", "Phone Number Empty");
        } else if (!password) {
            show("alert-info", "Password Empty");
        } else if (!con_password) {
            show("alert-info", "Confirm Password Empty");
        } else if (con_password.toString() !== password.toString()) {
            show("alert-info", "Password & Confirm Password not Match");
        } else {
            $.ajax({
                type: "POST",
                url: "register",
                data: {"userName": userName,
                        "password": password,
                        "con_password": con_password,
                        "firstName": firstName,
                        "lastName": lastName,
                        "pNumber": pNumber},
                success: function (data) {
                    if (data == 'True') {
                        $(location).attr('href', 'login.jsp');
                    } else if(data == 'error'){
                        show("alert-warning", "User Name Already use");
                    } else if(data == 'not_match'){
                        show("alert-danger", "Password Not match! Try Again...");
                    } else if(data == 'empty'){
                        show("alert-warning", "Fill Form...");
                    } else if(data == 'wrong'){
                        show("alert-warning", "Somthing wrong...");
                    } else{
                        show("alert-warning", data.toString());
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

//var color = "alert-info";
//var message = "All ok";
function show(color, message) {
    $('#alertBox').removeClass("d-none");
    $('#alertBox').addClass(color);
    $('#message').text(message);
}


