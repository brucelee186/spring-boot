package com.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.framework.action.BaseController;

/**
 * 响应编码拦截器
 *
 * @author Neo.Yin
 * @version 1.0	2019-07-19	Neo.Yin		created.
 * @version <ver>
 */
public class RequestResponseInterceptor implements HandlerInterceptor {

	private static final Logger	logger	= Logger.getLogger(RequestResponseInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
		if (object instanceof BaseController) {
			BaseController bc = (BaseController) object;
			bc.removeRequest();
			bc.removeResponse();
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if (object instanceof BaseController) {
			BaseController bc = (BaseController) object;
			bc.setRequest(request);
			bc.setResponse(response);
		}
		
		return true;
	}
}
