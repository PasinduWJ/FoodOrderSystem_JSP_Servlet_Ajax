var loger;
var food = [];
var order = [];
var foodAdd = [];
var allOrders = [];

$(document).ready(function () {
    
    $('#logOutIdClass').removeClass("d-none");
    $('#sessUserName').html(loger);
        
        
    $('#logOutId').click(function () {
        sessionStorage.clear();
        $(location).attr('href', 'login.jsp');
    });
    $('#addOder').click(function () {
        $('#addForm').removeClass("d-none");
        $.ajax({
            type: "POST",
            url: "UserPage",
            data: {"userName": loger},
            success: function (data) {
                foodAdd = data["food"];
                allOrders = data["allOrders"];
                buildFoodTable();
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
         });
    });
    $('#order-can-btn').click(function () {
        $('#addForm').addClass("d-none");
    });

    $('#oderNow').click(function () {
        if (food) {
            var sendOder = [];
            food.forEach(function (dt) {
                if (($("#Q" + dt.id + "").text()) > 0) {
                    item = {};
                    item["fId"] = dt.id;
                    item["quantity"] = $("#Q" + dt.id + "").text();
                    sendOder.push(item);
                }
            });

            $.ajax({
                type: "POST",
                url: "Order",
                data: {"userName": loger, "newOrder": JSON.stringify(sendOder)},
                success: function (data) {
                    if (data.toString() === "True") {
                        userLoad();
                        show("alert-info", "Oder Added...");
                        $('#totalPrice').html("0.00");
                        $('#addForm').addClass("d-none");
                    } else if(data.toString() === "False"){
                        show("alert-danger", "Oder Add fail...");
                    }else if(data.toString() === "OverLoad"){
                        show("alert-danger", "Oder Add fail... Food Are Not Awailable..");
                    }else {
                        show("alert-danger", data.toString());
                    }
                },
                error: function (request, error) {
                    alert(" Can't do because: " + error);
                }
            });
        }
    });

});

function userLoad() {
    loger = sessionStorage.getItem("loger");
    if (!loger) {
        $(location).attr('href', 'login.jsp');
    } else {

        $.ajax({
            type: "GET",
            url: "UserPage",
            data: {"userName": loger},
            success: function (data) {
                console.log(data);
                food = data["food"];
                order = data["order"];
                buildOrderTable();
                totalBill();
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    }
}


function buildFoodTable() {
    $('#foodTable').empty();
    if (foodAdd) {
        foodAdd.forEach(function (dt) {
            var orders = getCurrentOrdersId(dt.id);
            var avl = dt.maxOrder - orders;
            if (dt.available.toString() === 'true') {
                $('#foodTable').append(
                        "<tr>" +
                        "<td scope='row'></td>" +
                        "<td>" + dt.fName + "</td>" +
                        "<td>" + dt.uPrice + "</td>" +
                        "<td id=Q" + dt.id + ">0</td>" +
                        "<td>"+ avl  +" Only </td>" +
                        "<td>" +
                        "<i onclick= (clickAdd(" + dt.id + "," + dt.uPrice + ","+ dt.maxOrder +"," + orders + ")) class='fas fa-plus-square' style='color:green; font-size:30px; margin:5px;'></i>" +
                        "<i onclick= (clickRemove(" + dt.id + "," + dt.uPrice + ")) class='fas fa-minus-square' style='color:red; font-size:30px; margin:5px;'></i>" +
                        "</td>" +
                        "</tr>"
                        );
            } else {
                $('#foodTable').append(
                        "<tr>" +
                        "<td scope='row'></td>" +
                        "<td>" + dt.fName + "</td>" +
                        "<td>" + dt.uPrice + "</td>" +
                        "<td>Not Awailable Today </td>" +
                        "<td></td>" +
                        "</td>"

                        );
            }

        });
    }
}
function buildOrderTable() {
    $('#orderTable').empty();
        order.forEach(function (dt) {
            if (dt.delivery.toString() === 'false') {
                $('#orderTable').append(
                        "<tr>" +
                        "<td scope='row'></td>" +
                        "<td>" + fNameGetById(dt.fId) + "</td>" +
                        "<td>" + dt.uDateTime + "</td>" +
                        "<td> Wait </td>" +
                        "<td >" +
                        "<span id='edQ" + dt.id + "'> " + dt.quantity + "</span> " +
                        "<input id='inputEdit" + dt.id + "' class='d-none' type='number' min='0' style='width: 70px;' value=" + dt.quantity + ">" +
                        "<i id='btnEditDone" + dt.id + "' onclick= (clickEditDone(" + dt.id +  ","+ dt.fId+ ","+ dt.quantity+ ")) class='fas fa-check-square d-none' style='color:green; font-size:30px; margin:5px;'></i>" +
                        "<i id='btnEditClose" + dt.id + "' onclick= (clickEditClose(" + dt.id + ")) class='fas fa-window-close d-none' style='color:red; font-size:30px; margin:5px;'></i>" +
                        "</td>" +
                        "<td>" +
                        "<i onclick= (clickEdit(" + dt.id + ")) class='fas fa-pen-square' style='color:green; font-size:30px; margin:5px;'></i>" +
                        "<i onclick= (clickDelete(" + dt.id + ")) class='fas fa-trash-alt' style='color:red; font-size:30px; margin:5px;'></i>" +
                        "</td>"
                        + "</tr>"
                        );

            }else{
                $('#orderTable').append(
                        "<tr>" +
                        "<td scope='row'></td>" +
                        "<td>" + fNameGetById(dt.fId) + "</td>" +
                        "<td>" + dt.uDateTime + "</td>" +
                        "<td> Send </td>" +
                        "<td >" + dt.quantity + "</td>" +
                        "<td></td>"+ 
                        "</tr>"
                        );
            }
        }
                    );
            
        
}

function clickEditDone(id, fId, oldQuantity) {
    $("#inputEdit" + id).addClass("d-none");
    $("#btnEditDone" + id).addClass("d-none");
    $("#btnEditClose" + id).addClass("d-none");
    $("#edQ" + id).removeClass("d-none");

    var quantity = $("#inputEdit" + id).val();
    $.ajax({
        type: "GET",
        url: "Order",
        data: {"userName":loger, "id": id,"fId":fId, "quantity": quantity,"oldQuantity": oldQuantity, "status": "edit"},
        success: function (data) {
            if (data.toString() === "False") {
                show("alert-info", "Oder Edit fail...");
            }else if (data.toString() === "OverLoad") {
                show("alert-info", "Oder Edit fail... Food Not Available");
            } else if(data["order"]){
                order = data["order"];
                buildOrderTable();
                totalBill();
                show("alert-warning", "Oder Edited...");
            }else{
                 show("alert-warning", data.toString());
            }
        },
        error: function (request, error) {
            console.log(arguments);
            alert(" Can't do because: " + error);
        }
    });
}


function clickEdit(id) {
    $("#inputEdit" + id).removeClass("d-none");
    $("#btnEditDone" + id).removeClass("d-none");
    $("#btnEditClose" + id).removeClass("d-none");
    $("#edQ" + id).addClass("d-none");
}


function clickEditClose(id) {
    $("#inputEdit" + id).addClass("d-none");
    $("#btnEditDone" + id).addClass("d-none");
    $("#btnEditClose" + id).addClass("d-none");
    $("#edQ" + id).removeClass("d-none");
}

function clickDelete(id) {
    if (confirm("Are You Sure Delete Order?")) {
        $.ajax({
            type: "GET",
            url: "Order",
            data: {"id": id,"userName":loger, "status": "delete"},
            success: function (data) {
                if (data.toString() === "False") {
                    show("alert-info", "Oder Delete fail...");
                } else {
                    order = data["order"];
                    buildOrderTable();
                    totalBill();
                    show("alert-warning", "Oder Deleted...");
                }
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    }
}


function clickAdd(id, uPrice, maxOrder, orders) {
    if(orders < maxOrder){
        var i = $("#Q" + id + "").text();
        if(i<(maxOrder - orders)){
            i++;
            $("#Q" + id + "").html(i);
            var tPrice = parseFloat($('#totalPrice').text());
            tPrice += uPrice;
            $('#totalPrice').html(tPrice.toFixed(2));
        }
    }
    
}

function getCurrentOrdersId(id) {
    var orders = 0;
    allOrders.forEach(function (dt) {
            if(id === dt.fId){
                orders += dt.quantity ;
            }
        });
    return orders;
}

function clickRemove(id, uPrice) {
    var i = $("#Q" + id + "").text();
    if (i > 0) {
        i--;
        $("#Q" + id + "").html(i);
        var tPrice = parseFloat($('#totalPrice').text());
        tPrice -= uPrice;
        $('#totalPrice').html(tPrice.toFixed(2));
    }
}

function totalBill() {
    var tBill = 0;
    if (order) {
        order.forEach(function (dt) {
            tBill += (dt.quantity * uPriceGetById(dt.fId));
        });
    }
    $('#totalBill').html(tBill.toFixed(2));
}


function fNameGetById(idd) {
    var fName = "Not Now";
    food.forEach(function (dt) {
        if (parseInt(idd) === dt.id) {
            fName = dt.fName;
        }
    });
    return fName;
}

function uPriceGetById(idd) {
    var uPrice = 0;
    food.forEach(function (dt) {
        if (parseInt(idd) === dt.id) {
            uPrice = dt.uPrice;
        }
    });
    return uPrice;
}

function show(color, message) {
    $('#alertBox').removeClass("d-none");
    $('#alertBox').addClass(color);
    $('#message').text(message);
}
