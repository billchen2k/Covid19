package com.covid19.backend.controller;

import com.covid19.backend.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于继承的基础类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-26 12:10
 **/
@Slf4j
public class BaseController {

	@Autowired
	public Result result;

	@Value("${logging.request}")
	private Boolean ifLoggingRequest;

	/**
	 * 在所有 Controller 执行之前调用的方法
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void common(HttpServletRequest request, HttpServletResponse response) {
		if(ifLoggingRequest) {
			Map<String, String> parameterMap =  new HashMap<>();
			for(String param : Collections.list(request.getParameterNames())) {
				if (request.getParameter(param) != null) {
					parameterMap.put(param, request.getParameter(param));
				}
			}
			log.info(String.format("[%s] %s : %s", request.getMethod(), request.getServletPath(), parameterMap.toString()));
		}
	}
}
