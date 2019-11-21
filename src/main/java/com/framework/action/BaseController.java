package com.framework.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.framework.bean.common.BaseBean;
import com.framework.bean.common.Cons;
import com.framework.bean.common.SessionInfo;
import com.framework.service.SyHistoryService;
import com.framework.service.SyLogService;
import com.framework.service.SyNotificationService;
import com.framework.util.CoUtil;
import com.framework.util.UtIpAddress;
import com.framework.util.UtSetCommonField;


/**
 * @author Administrator
 *
 */
public abstract class BaseController {
	
	@Autowired
	protected SyLogService syLogService;
	
	@Autowired
	protected SyHistoryService syHistoryService;
	
	@Autowired
	protected SyNotificationService syNotificationService;
	
	// 请求线程
	private static ThreadLocal<HttpServletRequest>	request_threadLocal	= new ThreadLocal<HttpServletRequest>();

	// 响应线程
	private static ThreadLocal<HttpServletResponse>	reponse_threadLocal	= new ThreadLocal<HttpServletResponse>();	
	
	protected HttpServletRequest request; 
    protected HttpServletResponse response;  
    protected HttpSession session; 
    
    
	public void setRequest(HttpServletRequest request) {
		request_threadLocal.set(request);
	}

	public HttpServletRequest getRequest() {
		return request_threadLocal.get();
	}

	public void removeRequest() {
		request_threadLocal.remove();
	}

	public void setResponse(HttpServletResponse response) {
		reponse_threadLocal.set(response);
	}

	public HttpServletResponse getResponse() {
		return reponse_threadLocal.get();
	}

	public void removeResponse() {
		reponse_threadLocal.remove();
	}
	
    
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    } 
    
	/**	
	 * 取得会话基础类
	 * @return
	 */
	public SessionInfo getSessionInfo() {
		HttpServletRequest request = this.request_threadLocal.get();
		HttpSession session = request.getSession();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
		return sessionInfo;
	}
	
	public String getUserId() {
		String userId = this.getSessionInfo().getManager().getUserId();
		return userId;
	}
	
	public void setCommonField(Object object) {
		UtSetCommonField.setCommonField(object);
	}

}
