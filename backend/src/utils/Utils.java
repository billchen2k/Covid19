package utils;

import bean.Asset;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

//实用工具类
public class Utils {

    public static String getCurrentDateTime(){
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return currentDate;
    }

    public static String getRealRemoteIP(HttpServletRequest request){
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public static Asset parseAsset(ResultSet rs) {
        try {
            Asset one = new Asset(rs.getInt("assetid"),
                    rs.getString("assetname"),
                    rs.getString("assettype").equals("pic") ? 0 : 1,
                    rs.getString("category"),
                    rs.getString("url"),
                    rs.getString("acq_date"),
                    rs.getString("upload_date"),
                    rs.getString("uploader_uid"),
                    rs.getString("last_modified_date"),
                    rs.getString("scale"),
                    rs.getString("country"),
                    rs.getString("location"),
                    rs.getString("latitude"),
                    rs.getString("longitude"));
            return one;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User parseUser(ResultSet rs) {
        try {
            System.out.println("Parsing User...");
            User one = new User(rs.getInt("uid"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("registration_date"),
                    rs.getString("role"),
                    rs.getString("password_last_changed"));
            return one;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void cleanCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie voidIdentifier = new Cookie("identifier", null);
        voidIdentifier.setPath("/");
        voidIdentifier.setMaxAge(0);
        Cookie voidPassword = new Cookie("password", null);
        voidPassword.setPath("/");
        voidPassword.setMaxAge(0);
        response.addCookie(voidIdentifier);
        response.addCookie(voidPassword);
    }
}
