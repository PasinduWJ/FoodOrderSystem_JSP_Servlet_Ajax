
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dao.AdminDao;
import dao.FoodDao;
import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FoodModel;
import model.OrderModel;
import util.DateTimeSetting;

@WebServlet(name = "adminPage", urlPatterns = {"/adminPage"})
public class adminPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String adminName = request.getParameter("adminName");
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));
/////////////////Edit Order By User///////////////////////////////////////
        if (status.equals("edit")) {
            String fName = request.getParameter("fName");
            double uPrice = Double.parseDouble(request.getParameter("uPrice"));
            int maxOrder = Integer.parseInt(request.getParameter("maxOrder"));
            boolean available = Boolean.parseBoolean(request.getParameter("available"));
            try {
                boolean valid = true;
                if ( maxOrder <= OrderDao.getAllOrdersByFoodId(id)) {
                    valid = false;
                }
                if (valid) {
                    FoodModel fModel = new FoodModel(id, fName, uPrice, maxOrder, available, DateTimeSetting.getCurrentDateTime(), adminName);
                    if (FoodDao.editFoodItem(fModel)) {
                        response.setContentType("application/json");
                        response.getWriter().write(setFoodItemForFoodItemTable());
                    } else {
                        response.getWriter().write("False");
                    }
                } else {
                    response.getWriter().write("MaxOrder");
                }
            } catch (Exception e) {
                response.getWriter().write(e.toString());
            }

        }
//////////////// Delete Order By User/////////////////////////////////
        else if (status.equals("delete")) {
            try {
                if (OrderDao.deleteOrderById(id, adminName, DateTimeSetting.getCurrentDateTime())) {
                    response.setContentType("application/json");
//                    response.getWriter().write(setJsonOrder(adminName));
                } else {
                    response.getWriter().write("False");
                }
            } catch (Exception e) {
                response.getWriter().write(e.toString());
            }
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//////////////// Food Item Table Load////////////////////////////
        try {
            
            response.setContentType("application/json");
            response.getWriter().write(setFoodItemForFoodItemTable());

        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    private static String setFoodItemForFoodItemTable() throws Exception {
        List<FoodModel> foodList = FoodDao.getAllFoodList();
            JsonArray allOrders = OrderDao.getAllOrders();
            
            Map resMap = new HashMap();
            resMap.put("food", foodList);
            resMap.put("allOrders", allOrders);

            Gson gson = new Gson();
            String json = gson.toJson(resMap);
            System.out.println(json);
            return json;
    }

}
