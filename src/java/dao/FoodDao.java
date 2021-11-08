package dao;

import util.DbConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FoodModel;
import util.DateTimeSetting;

public class FoodDao {

    public static List<FoodModel> getAllFoodList() throws Exception {
        Connection conn = DbConnector.getConnection();
        ResultSet rs = null;
        List<FoodModel> foodList = new ArrayList();
        try {
            if (conn != null) {
                rs = conn.createStatement().executeQuery("select * from food where state = true");
                while (rs.next()) {
                    foodList.add(new FoodModel(
                            rs.getInt("id"), rs.getString("fName"), rs.getDouble("uPrice"),
                            rs.getInt("maxOrder"), rs.getBoolean("available"),
                            DateTimeSetting.setDateTime(rs.getTimestamp("uDateTime")), rs.getString("updateBy"),
                            rs.getBoolean("available")));
                }
                return foodList;
            }
        } catch (Exception e) {
            throw new Exception("Database Table error " +e);
        } finally {
            if (rs != null) 
                rs.close();
            if (conn != null) 
                conn.close();
            
        }
        return foodList;
    }
    
    public static List<FoodModel> getAllFoodListForUser() throws Exception {
        Connection conn = DbConnector.getConnection();
        ResultSet rs = null;
        List<FoodModel> foodList = new ArrayList();
        try {
            if (conn != null) {
                rs = conn.createStatement().executeQuery("select * from food");
                while (rs.next()) {
                    foodList.add(new FoodModel(
                            rs.getInt("id"), rs.getString("fName"), rs.getDouble("uPrice"),
                            rs.getInt("maxOrder"), rs.getBoolean("available"),
                            DateTimeSetting.setDateTime(rs.getTimestamp("uDateTime")), rs.getString("updateBy"),
                            rs.getBoolean("available")));
                }
                return foodList;
            }
        } catch (Exception e) {
            throw new Exception("Database Table error " +e);
        } finally {
            if (rs != null) 
                rs.close();
            if (conn != null) 
                conn.close();
            
        }
        return foodList;
    }

    public static int getMaxOrdersByFoodId(int fId) throws Exception {
        Connection conn = DbConnector.getConnection();
        ResultSet rs = null;
        int maxOder = 0;
        try {
            rs = conn.createStatement().executeQuery("select maxOrder from food where id= " + fId + " and state=true");
            if (rs.next()) {
                maxOder = rs.getInt("maxOrder");
            }
        } catch (Exception e) {
            throw new Exception("Database Table error " +e);
        } finally {
            if (rs != null) 
                rs.close();
            if (conn != null) 
                conn.close();
            
        }
        return maxOder;
    }
    
    
       public static Boolean editFoodItem(FoodModel fModel) throws Exception{
        Connection conn = DbConnector.getConnection();
        try{
            if(conn != null){
                System.out.println(fModel.getId());
                int rs = conn.createStatement().executeUpdate("UPDATE food SET fName = '"+fModel.getfName()+"', uPrice = "+fModel.getuPrice()+
                                                                ",maxOrder = "+fModel.getMaxOrder()+", available = "+fModel.getAvailable()+
                                                                ",uDateTime = '"+fModel.getuDateTime()+ "',updateBy = '"+ fModel.getUpdateBy() +"' where id ="+fModel.getId());
                System.out.println("dddddddd");
                if(rs>0)
                    return true;
            }
        }catch(SQLException e){
            throw new Exception("Database Table error " + e );
        }finally{
            if(conn != null)
                conn.close();
        }
        return false;
    }
    
}
