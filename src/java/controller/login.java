package controller;

import dao.AdminDao;
import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdminModel;
import model.UserModel;
import util.PasswordEncrypt;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String loger = request.getParameter("loger");
        try {
////////////////////User Login/////////////////////////////////            
            if(loger.equals("user")){
                
                String userName = request.getParameter("userName");
                UserModel userModel = UserDao.getUserByUserName(userName);

                if (userName.equals(userModel.getUserName())) {
                    if (password.equals(PasswordEncrypt.pDecode(userModel.getPassword()))) {
                        response.getWriter().write("User");
                    } else {
                        response.getWriter().write("error_uPassword");
                    }

                } else {
                    response.getWriter().write("error_uName");
                }
                
////////////////////////Admin Login///////////////////////////////////////////                
            }else if(loger.equals("admin")){
                String adminName = request.getParameter("userName");
                AdminModel adminModel = AdminDao.getAdminByAdminName(adminName);
                if (adminName.equals(adminModel.getAdminName())) {
                    if (password.equals(PasswordEncrypt.pDecode(adminModel.getPassword()))) {
                        response.getWriter().write("Admin");
                    } else {
                        response.getWriter().write("error_uPassword");
                    }
                } else {
                    response.getWriter().write("error_uName");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            response.getWriter().write(e.toString());
        }
    }
}
