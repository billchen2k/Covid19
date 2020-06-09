package com.covid19.backend.controller.account;
import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.User;
import com.covid19.backend.service.account.GetUserInfoService;
import com.covid19.backend.service.account.LogInService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

@RestController
@Api(tags = "用户控制器", value = "和用户认证、登录相关的控制器")
@Validated



public class LogIn extends BaseController {

	@Autowired
	LogInService logInService;

	@Autowired
	GetUserInfoService getUserInfoService;

	@PostMapping("/user/logIn") // Spring

	//Swagger
	@ApiOperation(value = "登录接口", notes = "用于登录用户。每一个 session 的有效期为 30 天，如果用户长时间没有启动应用应当在启动时发送一次登录请求。")
	//Swagger
	@ApiImplicitParams({
			@ApiImplicitParam(name = "password", value = "经过 SHA1 加密之后的密码字符串"),
			@ApiImplicitParam(name = "identifier", value = "认证名，可以为用户名或者邮箱，后端会自动验证")
	})
	//Spring
	public Result<User> logIn(@RequestParam(value = "password", required = true) @Size(min = 0, max = 40) String password,
							  @RequestParam(value = "identifier", required = true) @Size(min = 0, max = 30) String identifier,
							  HttpServletRequest request) {
		switch(logInService.userLogin(identifier, password, request)){
			case -2:
				return Result.error(1002, "密码错误");
			case -1:
				return  Result.error(1001,"用户 " + identifier + " 不存在");
			case -3:
				return Result.error(Result.CODE_UNAUTHORIZED, "已登录账号，切换账号请先登出", getUserInfoService.getCurrentUserInfo(request));
			default:
				return Result.ok(getUserInfoService.getCurrentUserInfo(request));
		}
	}
}
