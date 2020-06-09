package com.covid19.backend.service.account;

import com.covid19.backend.model.User;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import com.covid19.backend.utils.Encrypt;
import com.covid19.backend.utils.Utils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 更新用户信息的服务
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-28 11:18
 **/
@Service
public class UpdateUserInfoService extends BaseService {


	/**
	 * 更新密码的服务
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @param request
	 * @return -1 未登录，-2 旧密码不匹配，0 成功
	 */
	public int updatePassword(String oldPass, String newPass, HttpServletRequest request){
		Long uid = Authentication.getCurrentUid(request);
		oldPass = Encrypt.SHA1(oldPass);
		if(uid == null){
			return -1;
		}
		User oldUser = userMapper.selectUserByID(uid);
		if(!oldPass.equals(oldUser.getPassword())){
			return -2;
		}
		oldUser.setPassword(Encrypt.SHA1(newPass));
		oldUser.setPassword_change_date(Utils.getMySQLDateTime());
		userMapper.updateUserInfo(oldUser);
		return 0;
	}

	/**
	 * 更新用户名的服务
	 * @param newUsername 新的用户名
	 * @param request
	 * @return -1 未登录，-2 存在重复用户名，0 成功
	 */
	public int updateUsername(String newUsername, HttpServletRequest request){
		Long uid = Authentication.getCurrentUid(request);
		if(uid == null){
			return -1;
		}
		User oldUser = userMapper.selectUserByID(uid);
		if(userMapper.selectUserByUsername(newUsername)!= null){
			return -2;
		}
		oldUser.setUsername(newUsername);
		userMapper.updateUserInfo(oldUser);
		return 0;
	}

	/**
	 * 更新邮箱
	 *
	 * @param newEmail 新的邮箱
	 * @param request
	 * @return -1 未登录，-2 存在重复邮箱，0 成功
	 */
	public int updateEmail(String newEmail, HttpServletRequest request) {
		Long uid = Authentication.getCurrentUid(request);
		if (uid == null) {
			return -1;
		}
		User oldUser = userMapper.selectUserByID(uid);
		if (userMapper.selectUserByEmail(newEmail) != null) {
			return -2;
		}
		oldUser.setEmail(newEmail);
		userMapper.updateUserInfo(oldUser);
		return 0;
	}

	/**
	 * 更新性别
	 * @param newGender
	 * @param request
	 * @return -1 未登录 0 成功
	 */
	public int updateGender(String newGender, HttpServletRequest request) {
		Long uid = Authentication.getCurrentUid(request);
		if (uid == null) {
			return -1;
		}
		User oldUser = userMapper.selectUserByID(uid);
		oldUser.setGender(newGender);
		userMapper.updateUserInfo(oldUser);
		return 0;
	}


	/**
	 * 更新用户角色
	 * @param newRole 新角色
	 * @param request
	 * @return -1 未登录 0 成功
	 */
	public int updateRole(String newRole, HttpServletRequest request){
		Long uid = Authentication.getCurrentUid(request);
		if (uid == null) {
			return -1;
		}
		
		User oldUser = userMapper.selectUserByID(uid);
		oldUser.setRole(newRole);
		userMapper.updateUserInfo(oldUser);
		return 0;
	}
}
