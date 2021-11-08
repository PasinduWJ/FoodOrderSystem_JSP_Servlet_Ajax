
package dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import util.DbConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.OrderModel;
import util.DateTimeSetting;

public class OrderDao {
    
    public static boolean saveOrder(List<OrderModel> order) throws Exception{
        Connection conn = DbConnector.getConnection();
        int rs = 0;
         try{
            if(conn != null){
                Iterator it = order.iterator();
                String s="";
                while(it.hasNext()){
                    OrderModel oModel = (OrderModel)it.next();
                    s = s + "('" + oModel.getUserName() +"',"+ oModel.getfId()+","+ oModel.getQuantity() +
                            ",'"+ oModel.getuDateTime()+"','"+ oModel.getuDateTime() +"','" + oModel.getUserName() +"')," ;
                }
                rs = conn.createStatement().executeUpdate("insert into fOrder(uName,fId,quantity,cDateTime,uDateTime,updateBy)"
                        + " values" + s.substring(0, s.length()-1) + ";");  
                if(rs>0)
                    return true;
            }
        }catch(Exception e){
            throw new Exception("Database Table error " +e);
        }        
        finally{
            if(conn != null)
                conn.close();
        }
        return false;
    }
    
    public static List<OrderModel> getOrderListByUserName(String username) throws Exception{
        Connection conn = DbConnector.getConnection();
        List<OrderModel> orderList = new ArrayList();
        try{
            if(conn != null){
                ResultSet rs = conn.createStatement().executeQuery("select * from fOrder where uName = '" + username +"' and state=true  order by uDateTime desc");
                while(rs.next()){
                    orderList.add(new OrderModel(rs.getInt("id"), rs.getInt("fId"),rs.getInt("quantity"),
                            DateTimeSetting.setDateTime(rs.getTimestamp("uDateTime")),rs.getBoolean("delivery")));
                }
                return orderList;
            }
        }catch(Exception e){
            throw new Exception("Database Table error " +e);
        }        
        finally{
            if(conn != null)
                conn.close();
        }
        return orderList;
    }
    
    
     public static Boolean deleteOrderById(int id,String userName, String uDateTime) throws Exception{
         Connection conn = DbConnector.getConnection();
        try{
            if(conn != null){
                int rs = conn.createStatement().executeUpdate("UPDATE fOrder SET uDateTime='"+ uDateTime+"',updateBy='"+userName+"',state=false  where id = " + id);
                if(rs>0)
                    return true;
            }
        }catch(SQLException e){
            throw new Exception("Database Table error " +e);
        }finally{
            if(conn != null)
                conn.close();
        }
        return false;
    }
   
     
    public static Boolean editOrder(OrderModel eOrder) throws Exception{
        Connection conn = DbConnector.getConnection();
        try{
            if(conn != null){
                int rs = conn.createStatement().executeUpdate("UPDATE fOrder SET quantity = "+eOrder.getQuantity()+", uDateTime = '"+eOrder.getuDateTime()+
                        "',updateBy = '"+eOrder.getUserName() +"' where id = " + eOrder.getId() );
                if(rs>0)
                    return true;
            }
        }catch(SQLException e){
            throw new Exception("Database Table error " +e);
        }finally{
            if(conn != null)
                conn.close();
        }
        return false;
    }
    
    
    public static JsonArray getAllOrders() throws Exception{
        Connection conn = DbConnector.getConnection();
        ResultSet rs = null;
        JsonArray allOrders = new JsonArray();
        try{
                rs = conn.createStatement().executeQuery("select fId, quantity from fOrder where state = true");
                while(rs.next()){
                    JsonObject job = new JsonObject();
                    job.addProperty("fId", rs.getInt("fId"));
                    job.addProperty("quantity", rs.getInt("quantity"));
                    allOrders.add(job);
            }
        }catch(Exception e){
            throw new Exception("Database Table error " +e);
        } finally {
            if (rs != null) 
                rs.close();
            if (conn != null) 
                 conn.close();
             
        }
        return allOrders;
    }
    
    public static int getAllOrdersByFoodId(int fId) throws Exception{
        Connection conn = DbConnector.getConnection();
        ResultSet rs = null;
        int currentOders=0;
        try{
                rs = conn.createStatement().executeQuery("select quantity from fOrder where fId= "+ fId +" and state=true");
                while(rs.next()){
                    currentOders += rs.getInt("quantity");
            }
        }catch(Exception e){
            throw new Exception("Database Table error " +e);
        }        
        finally{
            if (rs != null) 
                rs.close();
            if (conn != null) 
                 conn.close();
        }
        return currentOders;
    }
}
