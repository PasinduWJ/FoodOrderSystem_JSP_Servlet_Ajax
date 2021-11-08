
package dao;

import util.DbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UserModel;
import util.DateTimeSetting;

public class UserDao {
    
    private static Connection conn = null;
    
    public static UserModel getUserByUserName(String userName) throws Exception{
        conn = DbConnector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserModel userModel = new UserModel();
        try{
                stmt = conn.prepareStatement("Select * from user where uName=?");
                stmt.setString(1, userName);
                rs= stmt.executeQuery();

                if(rs.next()){
                    userModel.setUserName(rs.getString("uName"));
                    userModel.setFirstName(rs.getString("fName"));
                    userModel.setLastName(rs.getString("lName"));
                    userModel.setpNumber(rs.getInt("pNumber"));
                    userModel.setPassword(rs.getString("password"));
                    userModel.setState(rs.getBoolean("state"));
                    userModel.setcDateTime(DateTimeSetting.setDateTime(rs.getTimestamp("cDateTime")));
                    userModel.setuDateTime(DateTimeSetting.setDateTime(rs.getTimestamp("uDateTime")));
                    userModel.setUpdateBy(rs.getString("updateBy"));
                    return userModel;
                }
        }catch(Exception e){
            throw new Exception("Database Table error " +e);
        }        
        finally{
            if (rs != null) 
                rs.close();
            if (stmt != null) 
                stmt.close();
            if (conn != null) 
                 conn.close();
        }
        return userModel;
    }
    
    public static boolean registerUser(UserModel userModel) throws Exception{
        conn = DbConnector.getConnection();
        System.out.println(userModel.getPassword());
        boolean status = false;
        try{
                int rs = conn.createStatement().executeUpdate("Insert into user values('"+ userModel.getUserName() +
                        "','"+ userModel.getFirstName()+ "','"+ userModel.getLastName()+ "',"+ userModel.getpNumber()+
                        ",'"+ userModel.getPassword() + "',true,'"+ userModel.getcDateTime()+
                       "','"+ userModel.getcDateTime()+"','"+ userModel.getUserName() +"' );");
                if(rs>0)
                    status = true;
        }catch(Exception e){
            throw new Exception("User register database error " +e);
        }        
        finally{
            if(conn != null)
                conn.close();
        }
        return status;
    }
}
