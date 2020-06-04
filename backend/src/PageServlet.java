import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "PageServlet")
public class PageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().write("<html>");
        resp.getWriter().write("<head>");
        resp.getWriter().write("</head>");
        resp.getWriter().write("<body>");
        resp.getWriter().write("<form action='login' method = 'get'>");
        resp.getWriter().write("用户名:<input type = 'text' name = 'uname' value = ''/> <br/>");
        resp.getWriter().write("密码 :<input type = 'password' name = 'pwd' value = ''/> <br/>");
        resp.getWriter().write("<input type='submit' value = '登陆'/> <br/>");
        resp.getWriter().write("</form>");
        resp.getWriter().write("</body>");
        resp.getWriter().write("</html>");
    }
}
