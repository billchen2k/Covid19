package com.covid19.backend.controller.account;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.service.account.UpdateUserInfoService;
import com.covid19.backend.utils.Authentication;
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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 更新用户信息的控制器
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-28 11:26
 **/
@RestController
@Api(tags = "用户控制器")
@Validated
public class UpdateUserInfo extends BaseController {

	@Autowired
	UpdateUserInfoService updateUserInfoService;

	@PostMapping("/user/updatePassword")
	@ApiOperation(value = "更新用户密码", notes = "对当前登录的用户起作用，必须先登录才能使用。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "oldPass", value = "旧密码，传入原文"),
			@ApiImplicitParam(name = "newPass", value = "新密码，传入原文")
	})
	public Result updatePassword(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, HttpServletRequest request){
		switch (updateUserInfoService.updatePassword(oldPass, newPass, request)){
			case -1:
				return Result.error(Result.CODE_UNAUTHORIZED, "用户未登录，请先登录");
			case -2:
				return Result.error(Result.CODE_VALIDATE_FAILED, "原密码错误");
			default:
				return Result.ok();
		}
	}


	@PostMapping("/user/updateUsername")
	@ApiOperation(value = "更新用户名", notes = "对当前登录的用户起作用，必须先登录才能使用。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "newUsername", value = "新的用户名")
	})
	public Result updateUsername(@RequestParam("newUsername") String newUsername, HttpServletRequest request) {
		switch (updateUserInfoService.updateUsername(newUsername, request)) {
			case -1:
				return Result.error(Result.CODE_UNAUTHORIZED, "用户未登录，请先登录");
			case -2:
				return Result.error(Result.CODE_VALIDATE_FAILED, "用户名与其他用户重复");
			default:
				return Result.ok();
		}
	}

	@PostMapping("/user/updateEmail")
	@ApiOperation(value = "更新邮箱", notes = "对当前登录的用户起作用，必须先登录才能使用。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "newEmail", value = "新的邮箱")
	})
	public Result updateEmail(@RequestParam("newEmail") String newEmail, HttpServletRequest request) {
		switch (updateUserInfoService.updateEmail(newEmail, request)) {
			case -1:
				return Result.error(Result.CODE_UNAUTHORIZED, "用户未登录，请先登录");
			case -2:
				return Result.error(Result.CODE_VALIDATE_FAILED, "新邮箱与其他用户重复");
			default:
				return Result.ok();
		}
	}

	@PostMapping("/user/updateOtherInfo")
	@ApiOperation( value = "更新除用户名密码、邮箱以外的其他信息", notes = "参数均为非必要的，有哪个参数就更新哪个字段。\n和其他接口一样必须先登录才能使用。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "newGender", value = "新的性别，0 女 1 男 2 其他"),
			@ApiImplicitParam(name = "newRole",  value = "新的角色，用户 user 或管理员 admin")
	})
	public Result updateOtherInfo(@RequestParam(name = "newGender", required = false) String newGender,
								  @RequestParam(name = "newRole", required = false) String newRole, HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("newGender", "[unchanged]");
		resultMap.put("newRole", "[unchanged]");
		if(Authentication.getCurrentUid(request) == null){
			return Result.error(Result.CODE_UNAUTHORIZED, "用户未登录，请先登录");
		}

		if(newGender != null){
			updateUserInfoService.updateGender(newGender, request);
			resultMap.put("newGender", newGender);
		}
		if(newRole != null){
			updateUserInfoService.updateRole(newRole, request);
			resultMap.put("newRole", newRole);
		}
		return Result.ok(resultMap);
	}

}
