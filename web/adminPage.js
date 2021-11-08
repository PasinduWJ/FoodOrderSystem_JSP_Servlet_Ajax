var loger;
var food = [];
var order = [];
var foodItems = [];
var allOrders = [];

$(document).ready(function () {

    $('#logOutIdClass').removeClass("d-none");
    $('#sessUserName').html(loger);


    $('#logOutId').click(function () {
        sessionStorage.clear();
        $(location).attr('href', 'login.jsp');
    });
    
    $('#addItems').click(function () {
        $('#addForm').removeClass("d-none");
        $.ajax({
            type: "POST",
            url: "adminPage",
            data: {"userName": loger},
            success: function (data) {
                foodItems = data["food"];
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

    $('#setAll').click(function () {
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
                data: {"adminName": loger, "newOrder": JSON.stringify(sendOder)},
                success: function (data) {
                    if (data.toString() === "True") {
                        userLoad();
                        show("alert-info", "Oder Added...");
                        $('#addForm').addClass("d-none");
                    } else if (data.toString() === "False") {
                        show("alert-danger", "Oder Add fail...");
                    } else if (data.toString() === "OverLoad") {
                        show("alert-danger", "Oder Add fail... Food Are Not Awailable..");
                    } else {
                        show("alert-danger", data.toString());
                    }
                },
                error: function (request, error) {
                    alert(" Can't do because: " + error);
                }
            });
        }
    });
    
    
    $('#add-newItem-btn').click(function () {
//        $('#foodTable').append(
//                "<tr id='newRowAdd' >" +
//                    "<td scope='row'></td>" +
//                    
//                    "<td >" +
//                    "<p>"+ dt.fName+"</p>"+
//                    "<input id='inputName" + dt.id + "' class='d-none' type='text' style='width: 230px;' value='"+dt.fName+"' >" +
//                    "</td>" +
//                    
//                    "<td>" + 
//                    "<p>"+ dt.uPrice+"</p>"+
//                    "<input id='inputuPrice" + dt.id + "' class='d-none' type='number' min=0 style='width: 100px;' value='"+dt.uPrice+"' >" +
//                    "</td>" +
//                    
//                    "<td>" + 
//                    "<p>"+ dt.maxOrder+"</p>"+
//                    "<input id='inputMaxOrder" + dt.id + "' class='d-none' type='number' min=0 style='width: 100px;' value='"+dt.maxOrder+"' >" +
//                    "</td>" +
//
//                    "<td>" + avl + " Only </td>"
//            );
    });

});


function userLoad() {
    loger = sessionStorage.getItem("loger");
    if (!loger) {
        $(location).attr('href', 'login.jsp');
    } else {

//        $.ajax({
//            type: "GET",
//            url: "UserPage",
//            data: {"userName": loger},
//            success: function (data) {
//                console.log(data);
//                food = data["food"];
//                order = data["order"];
//                buildOrderTable();
//                totalBill();
//            },
//            error: function (request, error) {
//                console.log(arguments);
//                alert(" Can't do because: " + error);
//            }
//        });
    }
}

function buildFoodTable() {
    $('#foodTable').empty();
    if (foodItems) {
        foodItems.forEach(function (dt) {
            var orders = getCurrentOrdersId(dt.id);
            var avl = dt.maxOrder - orders;

            $('#foodTable').append(
                    "<tr id='foodTavleTR"+ dt.id+"' >" +
                    "<td scope='row'></td>" +
                    
                    "<td >" +
                    "<p>"+ dt.fName+"</p>"+
                    "<input id='inputName" + dt.id + "' class='d-none' type='text' style='width: 230px;' value='"+dt.fName+"' >" +
                    "</td>" +
                    
                    "<td>" + 
                    "<p>"+ dt.uPrice+"</p>"+
                    "<input id='inputuPrice" + dt.id + "' class='d-none' type='number' min=0 style='width: 100px;' value='"+dt.uPrice+"' >" +
                    "</td>" +
                    
                    "<td>" + 
                    "<p>"+ dt.maxOrder+"</p>"+
                    "<input id='inputMaxOrder" + dt.id + "' class='d-none' type='number' min=0 style='width: 100px;' value='"+dt.maxOrder+"' >" +
                    "</td>" +

                    "<td>" + avl + " Only </td>"
                    
                    );
            if (dt.available) {
                $('#foodTable td:last').after(
                        "<td> " +
                        "<div class='form-check form-switch'>"+
                        " <input id='inputAvailable" + dt.id + "' class='form-check-input' type='checkbox' role='switch' checked>" +
                        "</div> "+
                        "</td> "
                        );
            } else {
                $('#foodTable td:last').after(
                        "<td> " +
                        "<div class='form-check form-switch'>"+
                        " <input id='inputAvailable" + dt.id + "' class='form-check-input' type='checkbox' role='switch' >" +
                        "</div> "+
                        "</td> "
                        );
            }
            $('#foodTable td:last').after(
                    "<td>" +
                    "<i id='btnEditDone" + dt.id + "' onclick= (clickEditDone(" + dt.id + ")) class='fas fa-check-square d-none' style='color:blue; font-size:30px; margin:5px;'></i>" +
                    "<i id='btnEditClose" + dt.id + "' onclick= (clickEditClose(" + dt.id + ")) class='fas fa-window-close d-none' style='color:maroon; font-size:30px; margin:5px;'></i>" +
                    "<i id='btnEditClick" + dt.id + "' onclick= (clickEdit(" + dt.id + ")) class='fas fa-pen-square' style='color:green; font-size:30px; margin:5px;'></i>" +
                    "<i onclick= (clickDelete(" + dt.id + ")) class='fas fa-trash-alt' style='color:red; font-size:30px; margin:5px;'></i>" +
                    "</td>" +
                    "</tr>"
                    );



        });
    }
}

function getCurrentOrdersId(id) {
    var orders = 0;
    allOrders.forEach(function (dt) {
        if (id === dt.fId) {
            orders += dt.quantity;
        }
    });
    return orders;
}

function clickEdit(id){
$("#foodTavleTR"+id).children('td').children('p').addClass('d-none');    
$("#foodTavleTR"+id).children('td').children('input').removeClass('d-none'); 
$("#btnEditClick"+id).addClass('d-none'); 
$("#btnEditDone"+id).removeClass('d-none'); 
$("#btnEditClose"+id).removeClass('d-none'); 
}

function clickEditClose(id){
$("#foodTavleTR"+id).children('td').children('p').removeClass('d-none');    
$("#foodTavleTR"+id).children('td').children('input').addClass('d-none'); 
$("#btnEditClick"+id).removeClass('d-none'); 
$("#btnEditDone"+id).addClass('d-none'); 
$("#btnEditClose"+id).addClass('d-none'); 
}

function clickEditDone(id){
     var fName = $("#inputName" + id).val();
     var uPrice = $("#inputuPrice" + id).val();
     var maxOrder = $("#inputMaxOrder" + id).val();
     var available = false;
     if($("#inputAvailable"+id).is(':checked')){
         available = true;
     }
    $.ajax({
        type: "GET",
        url: "adminPage",
        data: {"adminName":loger, "id": id,"fName":fName, "uPrice": uPrice,
            "maxOrder": maxOrder,"available":available, "status": "edit"},
        success: function (data) {
            if (data.toString() === "False") {
                show("alert-info", "Food Item Edit fail... try with another Food Name");
            }else if (data.toString() === "MaxOrder") {
                show("alert-info", "Food Item Edit fail... Already order that Food");
            } else if(data["food"]){
                console.log(data);
                foodItems = data["food"];
                allOrders = data["allOrders"];
                buildFoodTable();
                show("alert-info", "Food Item Edited...");
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

  
function show(color, message) {
    $('#alertBox').removeClass("d-none");
    $('#alertBox').addClass(color);
    $('#message').text(message);
}