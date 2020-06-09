package com.covid19.backend.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 认证相关的工具类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-24 11:07
 **/
public class Authentication {

	/**
	 * 返回当前录的的 UID
	 *
	 * @param request
	 * @return 如果已登录则返回 UID，失败则返回 null，表示未登录
	 */
	public static Long getCurrentUid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object uids = session.getAttribute("uid");
		if (uids == null) {
			return null;
		} else {
			return Long.parseLong(uids.toString());
		}
	}
}
