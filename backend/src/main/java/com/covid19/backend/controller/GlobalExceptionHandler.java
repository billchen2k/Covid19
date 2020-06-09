package com.covid19.backend.controller;

import com.covid19.backend.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * 全局异常处理类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-24 11:46
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 全局错误处理
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public Result ExceptionHandler(HttpServletRequest request, Exception e){
		//从request里获取请求参数
		Map<String, Object> response = new HashMap<>();
		Map<String, String> parameterMap =  new HashMap<>();
		for(String param : Collections.list(request.getParameterNames())) {
			if (request.getParameter(param) != null) {
				 parameterMap.put(param, request.getParameter(param));
			}
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
		response.put("parameters", parameterMap);
		response.put("stacktrace", sw.toString());
		Result result = Result.error(Result.CODE_ERROR, e.getMessage(), response);
		log.error("<" + request.getServletPath() + "> : " + e.getMessage() + "\n\tParameters: " + parameterMap.toString() + "\n\tStacktrace: " + sw.toString());
		return result;
	}


}
