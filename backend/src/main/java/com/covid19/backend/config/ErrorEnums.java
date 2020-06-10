package com.covid19.backend.config;

import lombok.Getter;

/**
 * 全局返回代码类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-06-10 10:04
 **/
@Getter
public enum ErrorEnums {

	CODE_SUCCESS(200, "成功"),
	CODE_ERROR(500, "出现未知异常"),
//	CODE_VALIDATE_FAILED(405, "验证失败"),
	CODE_NOT_FOUND(404, "无法找到"),
 	CODE_UNAUTHORIZED(401, "权限不足"),
	CODE_FORBIDDEN(403, "禁止访问");

	public int code;
	public String msg;

	ErrorEnums(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
