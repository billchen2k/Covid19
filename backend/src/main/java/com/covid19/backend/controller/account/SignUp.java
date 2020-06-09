package com.covid19.backend.controller.account;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.service.account.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * 注册用户控制器
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 11:30
 **/
@RestController
@Api(tags = "用户控制器")
@Validated
public class SignUp extends BaseController {

	@Autowired
	public SignUpService signUpService;

	@PostMapping("/user/signUp")
	@ApiOperation(value = "注册新用户的接口", notes = "token_id 字段暂时未用上")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名，长度在 3 - 30 之间"),
			@ApiImplicitParam(name = "email", value = "注册邮箱"),
			@ApiImplicitParam(name = "password", value = "密码，应该为原文，加密由后端实现"),
			@ApiImplicitParam(name = "gender", value = "以整数形式表示的性别。0 - 女；1 - 男； 2 - 其他")
	})
	public Result signUp(@RequestParam("username") @Size(min = 2, max = 30) String username,
						 @RequestParam("email") @Email String email,
						 @RequestParam("password") @Size(min = 4) String password,
						 @RequestParam("gender") String gender,
						 HttpServletRequest request) {

		if (signUpService.checkIfUserExists(username)) {
			return Result.error(1101, "用户名已存在");
		}
		if (signUpService.checkIfMailExists(email)) {
			return Result.error(1102, "邮箱已存在");
		}
		return Result.ok(signUpService.signUp(username, password, email, gender, request));
	}

}
