package controller;

import com.google.gson.Gson;
import dao.FoodDao;
import dao.OrderDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.DateTimeSetting;

@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));
//////////////// Delete Order By User/////////////////////////////////
        if (status.equals("delete")) {
            try {
                if (OrderDao.deleteOrderById(id, userName, DateTimeSetting.getCurrentDateTime())) {
                    response.setContentType("application/json");
                    response.getWriter().write(setJsonOrder(userName));
                } else {
                    response.getWriter().write("False");
                }
            } catch (Exception e) {
                response.getWriter().write(e.toString());
            }
/////////////////Edit Order By User///////////////////////////////////////
        } else if (status.equals("edit")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int oldQuantity = Integer.parseInt(request.getParameter("oldQuantity"));
            try {

                boolean valid = true;
                int fId = Integer.parseInt(request.getParameter("fId"));
                if (FoodDao.getMaxOrdersByFoodId(fId) <= (OrderDao.getAllOrdersByFoodId(fId) + quantity - oldQuantity - 1)) {
                    valid = false;
                }
                if (valid) {
                    OrderModel editOrder = new OrderModel(id, userName, quantity, DateTimeSetting.getCurrentDateTime());
                    if (OrderDao.editOrder(editOrder)) {
                        response.setContentType("application/json");
                        response.getWriter().write(setJsonOrder(userName));
                    } else {
                        response.getWriter().write("False");
                    }
                } else {
                    response.getWriter().write("OverLoad");
                }
            } catch (Exception e) {
                response.getWriter().write(e.toString());
            }

        }
//        else if (status.equals("getOrders")) {
//            int allOrders;
//            try {
//                allOrders = OrderDao.getCurrentOrdersByFoodId(id);
//                response.getWriter().write(allOrders+"");
//            } catch (Exception e) {
//                response.getWriter().write(e.toString());
//            }
//
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        List<OrderModel> order = new ArrayList();
/////////////////////////Add New Order///////////////////////////////
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(request.getParameter("newOrder"));
            List list = (ArrayList) obj;
            boolean valid = true;
            for (int i = 0; i < list.size(); i++) {
                JSONObject job = (JSONObject) list.get(i);
                int fId = Integer.parseInt(job.get("fId").toString());
                if (FoodDao.getMaxOrdersByFoodId(fId) <= OrderDao.getAllOrdersByFoodId(fId)) {
                    valid = false;
                    break;
                }
                order.add(new OrderModel(userName, fId, Integer.parseInt(job.get("quantity").toString()),
                        DateTimeSetting.getCurrentDateTime()));
            }
            if (valid) {
                if (OrderDao.saveOrder(order)) {
                    response.getWriter().write("True");
                } else {
                    response.getWriter().write("False");
                }
            } else {
                response.getWriter().write("OverLoad");
            }

        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }

    }

    private static String setJsonOrder(String userName) throws Exception {
        List<OrderModel> orderList = OrderDao.getOrderListByUserName(userName);
        Map resMap = new HashMap();
        resMap.put("order", orderList);

        Gson gson = new Gson();
        String json = gson.toJson(resMap);
        return json;
    }

}
