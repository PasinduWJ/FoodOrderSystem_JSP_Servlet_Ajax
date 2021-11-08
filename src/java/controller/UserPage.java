package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.FoodDao;
import dao.OrderDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FoodModel;
import model.OrderModel;

@WebServlet(name = "UserPage", urlPatterns = {"/UserPage"})
public class UserPage extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FoodModel> foodList = FoodDao.getAllFoodList();
            JsonArray allOrders = OrderDao.getAllOrders();
            
            Map resMap = new HashMap();
            resMap.put("food", foodList);
            resMap.put("allOrders", allOrders);

            Gson gson = new Gson();
            String json = gson.toJson(resMap);
            response.setContentType("application/json");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName");
            List<FoodModel> foodList = FoodDao.getAllFoodListForUser();
            List<OrderModel> orderList = OrderDao.getOrderListByUserName(userName);
            
            Map resMap = new HashMap();
            resMap.put("food", foodList);
            resMap.put("order", orderList);

            Gson gson = new Gson();
            String json = gson.toJson(resMap);
            response.setContentType("application/json");
            System.out.println(json);
            response.getWriter().write(json);

        } catch (Exception e) {
           response.getWriter().write(e.toString());
        }
    }

}
