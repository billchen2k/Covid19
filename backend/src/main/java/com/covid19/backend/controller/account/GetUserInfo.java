package com.covid19.backend.controller.account;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.User;
import com.covid19.backend.service.account.GetUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户信息的控制器
 * @author billchen
 * @version 1.0
 * @create 2020-02-24 10:53
 **/
@RestController
@Api(tags = "用户控制器", value = "和用户有关的控制器")
public class GetUserInfo extends BaseController {

	@Autowired
	public GetUserInfoService getUserInfoService;

	@GetMapping("/user/getUserInfo")
	@ApiOperation(value = "获取已登录用户信息", notes = "可以获取当前已登录的用户信息")
	public Result<User> getUserInfo(HttpServletRequest request) {
		User user = getUserInfoService.getCurrentUserInfo(request);
		return user == null ?
				Result.error(Result.CODE_UNAUTHORIZED, "用户未登录，请先登录") :
				Result.ok(user);
	}

//
//	@GetMapping("/user/getUserByInfo")
//	@ApiOperation(value = "获取用户个人信息")
//	public String getUser(@RequestParam(value = "id") String id) {
//		return id;
//	}
}
