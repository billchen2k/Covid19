package com.covid19.backend.service.account;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登出服务
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 10:15
 **/
@Service
public class LogOutService {
	/**
	 * 登出
	 * @param request
	 * @return
	 * -1 未登录
	 * 0 成功登出
	 */
	public int LogOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("uid") == null){
			return -1;
		}
		else{
			session.removeAttribute("uid");
			return 0;
		}
	}
}
