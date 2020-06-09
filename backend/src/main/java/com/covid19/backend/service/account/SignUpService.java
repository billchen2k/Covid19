package com.covid19.backend.service.account;

import com.covid19.backend.model.User;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Encrypt;
import com.covid19.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 注册服务类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 11:29
 **/

@Service
public class SignUpService extends BaseService {

	@Autowired
	public GetUserInfoService getUserInfoService;

	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return true 则重复
	 */
	public Boolean checkIfUserExists(String username){
		return userMapper.selectUserByUsername(username) == null ?
				false : true;
	}

	/**
	 * 检查邮箱是否重复
	 * @param email
	 * @return true 则重复
	 */
	public Boolean checkIfMailExists(String email){
		return userMapper.selectUserByEmail(email) == null ?
				false : true;
	}

	/**
	 * 提供注册的服务
	 * @param username
	 * @param password 用户的密码 (未经过 SHA1 加密)
	 * @param email
	 * @param gender
	 * @param request
	 * @return 刚刚新注册好的用户类
	 */
	public User signUp(String username, String password, String email, String gender, HttpServletRequest request){
		User newUser = new User();
		// password = Encrypt.SHA1(password);
		password = Encrypt.SHA1(password);
		newUser.setUsername(username).setPassword(password).setEmail(email).setGender(gender);
		newUser.setRegistration_ip(Utils.getRealRemoteIP(request)).setRole("user").setPassword_change_date(Utils.getMySQLDateTime());
		userMapper.insertUser(newUser);
		return getUserInfoService.getUserInfoByEmail(email);
	}
	
}
