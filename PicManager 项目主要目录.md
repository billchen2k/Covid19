## PicManager 项目主要目录

之前的这个项目是前后端一起的，所以会有会多 servlet 最后是跳转到一个 jsp 页面用来展示内容。现在我们前后端分离的话应该就是所有的类都应该

### /doc

存储文档的文件夹，是上学期用于上交的。

### /lib

Java 包。

### /out

编译目标主要文件夹，可以删除。

### /src

源代码文件。主要的 Java 文件都在这里。但是这里面的层次不是很清晰，你可以重构成 MVC 架构或者 MSVC 等。（比如分成 model, controller, service 文件夹，model 就类似于现在的 bean，controller 就是直接映射网页地址，service 用于）其中 V 视图层就不用做了，交给前端来显示。

目前的这个项目是把 controller service 什么的放在一起了，一个类把地址映射，操作数据库，返回值什么的都做完了。分层的结构可以参考我另一个项目的后端结构：

<img src="https://billc.oss-cn-shanghai.aliyuncs.com/img/2020-05-26-d7suQU.png" alt="image-20200526212411083" style="zoom:50%;" />

dao 是 spring 框架里的东西，可以不用管，其他的就分为几个大文件夹 controller, model, service, utils。这个项目现在也在 github 上 [https://github.com/jiyanapp/jiyan-backend](https://github.com/jiyanapp/jiyan-backend)，不过比较复杂。你可以看怎么好维护自己看着舒服来处理结构就可以了。

#### /src/bean

这里存放的是 Java Bean。一种特别的对象，只具有 `getter` 和 `setter` 以及静态属性。可以映射到数据中不同的数据库表，每个属性对应一个字段。比如 ResultSet 类是 JDBC 的查找结果类，需要把类中的属性解析出来。

#### /src/utils

工具文件夹。一些经常用的函数，工具，转换格式之类的，就可以放在在这里。其中 `DatabaseManager` 是负责管理数据库的类，`Config` 里存储的是配置。当时都写了注释的。`Encrypt` 是加密类，当时是用于加密密码的。

在 `utils` 类中，有两个方法叫做 `parseUser` 和 `parseAsset`，就是把 MySQL 的查询结果注入到 bean 中，方便操作。不过由于当时项目很小，就把他们全部夹杂在一起了。其实应该把这种方法独立出来到新的类。



比如，对于 Login 这个类，我加一下额外的注释：

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import utils.Encrypt;
import utils.DatabaseManager;
import utils.Utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


// 这里设置了一个 Sevelet 名字，稍后可以在 web/WEB-INF/web.xml 里配置地址映射
@WebServlet(name = "Login")
public class Login extends HttpServlet {
    
    // 建立数据库连接，准备数据库返回值
	private DatabaseManager db = new DatabaseManager();
	private ResultSet rs = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        // 这里存储了一个 session，是存储在服务器上的
		HttpSession session = request.getSession();
        
        // 从请求中提取 cookie 和参数
        // 对于请求参数，Attribute 是 Java 运行环境内部的， Parameter 是浏览器地址栏里传过来的。由于前后端分离，我们用的应该是 parameter 居多。
		Cookie cookies[] = request.getCookies();
		String identifier = "";
		String password = "";
		if(request.getAttribute("AutoLogin") != null){
            
            // 这里是取浏览器中是否有记住的用户名和密码信息
			for(Cookie one: cookies){
				if(one.getName().equals("identifier")){
					identifier = one.getValue();
				}
				if(one.getName().equals("password")){
					password = one.getValue();
				}
			}
		}
		else{
            // 如果没有密码，就从请求中获取参数
			identifier = request.getParameter("identifier");
			password = request.getParameter("password");
            // 加密类中加密密码
			password = Encrypt.SHA1(password);
		}
		
        // 为这个客户端新建一个半个小时的 session，半小时后没操作就失效了，就不登陆了。
		session.setMaxInactiveInterval(60 * 30);
		db.getConnection();
		try{
            // 执行数据库查询
			rs = db.executeQuery("SELECT * from `user` WHERE username='" + identifier + "';");
            // 这里就是从结果判断是否找到用户之类的，比较好理解
			if (!rs.next()){
				rs = db.executeQuery("SELECT * from `user` WHERE email='" + identifier + "';");
				if (!rs.next()){
					//邮箱和用户名都找不到
					Utils.cleanCookie(request, response);
					request.setAttribute("stat", "user_not_found");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}
			}
			rs.last();
			if(password.equals(rs.getString("password"))){
				//密码正确
				session.setAttribute("to_notify_success", "true");
				//在session中记录已登录的用户信息
				session.setAttribute("logined_uid", rs.getString("uid"));
				session.setAttribute("logined_username", rs.getString("username"));
				session.setAttribute("logined_user_details", rs);
				session.setAttribute("logined_user_role", rs.getString("role"));

				String ipAddress = Utils.getRealRemoteIP(request);
				//写日志
				String logSQL = "INSERT INTO `picmanager`.`log`(`uid`, `username`,  `assetid`, `assetname`, `type`, `date`, `request_ip`, `notes`) VALUES ('" + rs.getString("uid") +"', '"
						+  rs.getString("username") + "', '-1', 'N/A', 'login', '" + Utils.getCurrentDateTime() + "', '" + ipAddress + "', NULL);";
                
                // 这里执行了一条插入语句，当时是写在日志表里
				db.executeUpdate(logSQL);
                
				if(request.getParameter("ifRememberPassword")!= null){
                    // 和记住密码有关，这里是写入到 cookie 中。但由于现在的项目是前后端分离所以一般不需要操作 cookie 。该操作由前端完成
					Cookie cookiePassword = new Cookie("password", password);
					Cookie cookieIdentifier = new Cookie("identifier", identifier);
					cookiePassword.setMaxAge(24 * 60 * 60 * 3);
					cookieIdentifier.setMaxAge(24 * 60 * 60 * 3);
					cookiePassword.setPath("/");
					cookieIdentifier.setPath("/");
					response.addCookie(cookiePassword);
					response.addCookie(cookieIdentifier);
				}

				System.out.println(request.getParameter("ifRememberPassword"));
				response.sendRedirect("view");

				return;
			}
			else{
				//密码错误，调用工具类中清除 cookie
           
				Utils.cleanCookie(request, response);
				request.setAttribute("stat", "wrong_password");
                
                // 这里是登陆成功后转发到静态页面 index.jsp。但前后端分离的话，这里就不需要跳转了，就需要直接把数据以 json 的格式展示出来。这个 json 可能要用一个第三方的库，可以上网了解一下。使用 PrintWriter out = response.getWriter(); out.println(.....) 这种直接把 json 以字符串的格式打印出来。
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			request.setAttribute("stat", "database_error");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		//request.setAttribute("stat", "wrong_password");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<i>USE POST INSTEAD.</i>");
	}
}

```

为了方便地打印 json，你可以新建一个 model 叫做返回类，包含有一个 success，message，timestamp 和 data 属性。所有的返回结果都按照一样的格式打印即可。

### /web

jsp 页面。由于是前后端分离，所以只需要用到 WEB-INF 下的 web.xml，其他文件就不用管了。

在 web.xml 这里配置路由器，参照格式来就可以了。映射的 servlet 必须要是 servlet 类，也就是继承了 `HttpServlet`。并且加上`@WebServlet` 注解。

