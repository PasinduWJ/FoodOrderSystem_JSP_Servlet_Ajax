
package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;
import util.DateTimeSetting;
import util.PasswordEncrypt;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String pNumber = request.getParameter("pNumber");
        String password = request.getParameter("password");
        String con_password = request.getParameter("con_password");
        if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty() && con_password != null) {
            if (password.equals(con_password)) {
                try {
                    UserModel userModel = UserDao.getUserByUserName(userName);
                    if(userModel.getUserName() == null){
                        userModel.setUserName(userName);
                        userModel.setFirstName(firstName);
                        userModel.setLastName(lastName);
                        userModel.setpNumber(Integer.parseInt(pNumber));
                        userModel.setPassword(PasswordEncrypt.pEncode(password));
                        userModel.setcDateTime(DateTimeSetting.getCurrentDateTime());
                        if(UserDao.registerUser(userModel)){
                            response.getWriter().write("True");
                        }else{
                            response.getWriter().write("wrong");
                        }
                    }else{
                        response.getWriter().write("error");
                    }
                } catch (Exception e) {
                    response.getWriter().write(e.toString());
                }

            } else {
                response.getWriter().write("not_match");
            }
        } else {
            response.getWriter().write("empty");
        }

    }
}
