package com.covid19.backend.controller;

import com.covid19.backend.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于继承的基础类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 12:10
 **/
public class BaseController {

	@Autowired
	public Result result;

	/**
	 * 在所有 Controller 执行之前调用的方法
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void common(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getSession().getId());
//		result.setSessionid(request.getSession().getId());
	}
}
