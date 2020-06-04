package  user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import utils.Encrypt;
import utils.DatabaseManager;
import utils.Utils;
import bean.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "register")

public class Register extends HttpServlet {
    private DatabaseManager db = new DatabaseManager();
    private ResultSet rs = null;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String date = Utils.getCurrentDateTime();
        password = Encrypt.SHA1(password);

        HttpSession session = request.getSession();
        if (session.getAttribute("logied_uid") != null){
            // 已登录账号
            response.sendRedirect("/view.jsp");
            return;
        }
        db.getConnection();
        try {
            rs = db.executeQuery("select * from user where username = '"+name+"';");
            if(!rs.next()) {

                db.executeUpdate("insert into user(username,password,email,registration_date,role)values ('"
                             + name + "','" + password + "','"+ email +"','"+ date +"','"+"', 'user');");
                //从管理界面手动创建
                if(request.getParameter("isFromManage") != null){
                    session.setAttribute("update_user_stat", "添加成功");
                    response.sendRedirect("/manageuser.jsp");
                }
                request.setAttribute("stat", "registration_success");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            } else {
                if (request.getParameter("isFromManage") != null) {
                    session.setAttribute("update_user_stat", "无法添加：用户名已存在。");
                    response.sendRedirect("/manageuser.jsp");
                }
                request.setAttribute("stat","already_exist");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



