package com.covid19.backend.service.account;

import com.covid19.backend.model.User;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Encrypt;
import com.covid19.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LogInService extends BaseService {

	@Value("${session.max-day}")
	private int SESSION_MAX_DAY;
	/**
	 * 登陆用户
	 * @param identifier 可以为用户名或密码
	 * @param password
	 * @param request HTTP 请求
	 * @return 返回 0 成功，-1 用户不存在，-2 密码错误，-3 尝试登录另一个账号
	 */
	public int userLogin(String identifier, String password, HttpServletRequest request) {
		User toLogInUser;
		password = Encrypt.SHA1(password);
		HttpSession session = request.getSession();

		String loginMethod;
		if(identifier.contains("@")){
			//输入的是邮件

			toLogInUser = userMapper.selectUserByEmail(identifier);
			loginMethod = "email";
		}
		else{
			toLogInUser = userMapper.selectUserByUsername(identifier);
			loginMethod = "username";
		}

		if(toLogInUser == null){
			//用户不存在
			return -1;
		}

		if(password.equals(toLogInUser.getPassword())){
			if(session.getAttribute("uid") != null){
				//已经有一个登录账号
				if(Integer.parseInt(session.getAttribute("uid").toString()) != toLogInUser.getUser_id()){
					return -3;
				}
			}
			//登录成功，初始化 session，
			// !!!登录成功后会在 session 中记录 uid 参数
			session.setAttribute("uid", toLogInUser.getUser_id());
			session.setMaxInactiveInterval(60 * 60 * 24 * SESSION_MAX_DAY);
			return 0;
		}
		else{
			//密码错误
			return -2;
		}

	}


}
