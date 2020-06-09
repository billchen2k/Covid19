package com.covid19.backend.utils;

import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * 普通的一些工具类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-24 11:07
 **/
public class Utils {

	/**
	 * @return 返回 MySQL 标准格式的日期时间
	 */
	public static String getMySQLDateTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return currentDate;
	}

	/**
	 * @return 返回格式化后无空格的日期时间
	 */
	public static String getFormattedDateTime(){
		String currentDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return currentDate;
	}

	/**
	 * @return 返回程序运行的绝对路径
	 */
	public static String getAbsoluteRunnigPath(){

		return System.getProperty("user.dir");
//
//		try{
//			File path = new File(ResourceUtils.getURL("classpath:").getPath());
//			if (!path.exists()) path = new File("");
//			return path.getAbsolutePath();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
	}
	/**
	 * 返回长度为 length 的自定义字符串
	 * @param length 指定长度
	 * @return 返回一个字符串
	 */
	public static String RandomStringGenerator(int length){
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(str.length());
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * @return 返回 ISO 标准的时间
	 */
	public static String getISODateTime() {
		LocalDateTime time = LocalDateTime.now();
		return time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	/**
	 * @param request 传入请求
	 * @return 真正的IP地址
	 */
	public static String getRealRemoteIP(HttpServletRequest request) {
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
}
