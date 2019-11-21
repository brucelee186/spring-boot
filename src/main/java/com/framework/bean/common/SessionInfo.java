package com.framework.bean.common;

import java.util.Date;

import com.framework.bean.impl.MaManagerImpl;
import com.framework.bean.impl.MaUserImpl;

public class SessionInfo {
	
	// 用户编号
	private String userId;
	
	// 用户姓名
	private String userName;

	// 当前服务器路径
	private String contextPath;
	
	// 登录时间
	private Date loginDate;
	
	// ip地址
	private String ip;
	
	// 管理员
	private MaUserImpl manager;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public MaUserImpl getManager() {
		return manager;
	}

	public void setManager(MaUserImpl manager) {
		this.manager = manager;
	}

}
