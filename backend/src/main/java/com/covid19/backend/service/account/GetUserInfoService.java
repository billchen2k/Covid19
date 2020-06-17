package com.covid19.backend.service.account;

import com.covid19.backend.model.User;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;


@Service
public class GetUserInfoService extends BaseService {

	/**
	 * 取得当前登陆的用户信息
	 */
	public User getCurrentUserInfo(HttpServletRequest request){
		Long uid = Authentication.getCurrentUid(request);
		return uid == null ? null : userMapper.selectUserByID(uid);
	}

	/**
	 * 根据邮件获取用户信息
	 */
	public User getUserInfoByEmail(String email){
		return userMapper.selectUserByEmail(email);
	}
}
