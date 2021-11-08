
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.AdminModel;
import model.UserModel;
import util.DateTimeSetting;
import util.DbConnector;

public class AdminDao {
     private static Connection conn = null;
    
    public static AdminModel getAdminByAdminName(String aName) throws Exception{
        conn = DbConnector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AdminModel adminModel = new AdminModel();
        try{
                stmt = conn.prepareStatement("Select * from admin where aName=?");
                stmt.setString(1, aName);
                rs= stmt.executeQuery();
                if(rs.next()){
                    adminModel.setAdminName(rs.getString("aName"));
                    adminModel.setpNumber(rs.getInt("pNumber"));
                    adminModel.setPassword(rs.getString("password"));
                    adminModel.setRole(rs.getString("aRole"));
                    adminModel.setState(rs.getBoolean("state"));
                    adminModel.setcDateTime(DateTimeSetting.setDateTime(rs.getTimestamp("cDateTime")));
                    adminModel.setuDateTime(DateTimeSetting.setDateTime(rs.getTimestamp("uDateTime")));
                    adminModel.setUpdateBy(rs.getString("updateBy"));
                    
                    return adminModel;
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
        return adminModel;
    }
}
