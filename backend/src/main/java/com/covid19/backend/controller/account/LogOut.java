package com.covid19.backend.controller.account;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.service.account.LogOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登出
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 10:14
 **/

@RestController
@Api(tags = "用户控制器")
public class LogOut extends BaseController {

	@Autowired
	public LogOutService logOutService;

	@GetMapping("/user/logOut")
	@ApiOperation(value = "登出接口")
	public Result logOut(HttpServletRequest request){
		switch (logOutService.LogOut(request)){
			case -1:
				return Result.error(Result.CODE_UNAUTHORIZED, "未登录账号。请重试");
			default:
				return Result.ok();
		}
	}

}
