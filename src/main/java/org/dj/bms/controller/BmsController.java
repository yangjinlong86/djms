package org.dj.bms.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.dj.bms.model.User;

/**
 * @ClassName: BmsController
 * @Description: 基类
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:14:17
 */

public class BmsController {
	/**
	 * 日志
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	public User getCurrentUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

	public Map<String, Object> getParameterAndUser(HttpServletRequest request) {
		Map<String, Object> params = getParameterMap(request);
		// 当前用户信息
		User user = getCurrentUser();
		// 部门ID
		params.put("deptId", user.getDeptId());
		// 单位ID
		params.put("corpId", user.getCorpId());
		return params;
	}

	/**
	 * 获取所有参数
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 得到页面所有参数的key
		Enumeration<?> key = request.getParameterNames();
		while (key.hasMoreElements()) {// 是否有元素
			Object o = key.nextElement();// 得到元素
			if (o instanceof String) {
				String k = (String) o;
				Object v = request.getParameter(k);// 得到页面参数
				params.put(k, v);
			}
		}
		return params;
	}
}
