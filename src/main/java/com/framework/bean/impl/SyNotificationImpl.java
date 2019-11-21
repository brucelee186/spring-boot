package com.framework.bean.impl;

import com.framework.bean.SyNotification;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 提示推送
 * 作者:    Auto
 * 日期:    2019/8/26
 **********************************************
 */
public class SyNotificationImpl extends SyNotification {
	private static final long serialVersionUID = 1L;
	
	private MaUserImpl maUser;
	
	private OpWorkOrderImpl opWorkOrder;
	
	private String nameStatus;
	
	private String nameMaOrgnization;

	public String getNameMaOrgnization() {
		return nameMaOrgnization;
	}

	public void setNameMaOrgnization(String nameMaOrgnization) {
		this.nameMaOrgnization = nameMaOrgnization;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	public OpWorkOrderImpl getOpWorkOrder() {
		return opWorkOrder;
	}

	public void setOpWorkOrder(OpWorkOrderImpl opWorkOrder) {
		this.opWorkOrder = opWorkOrder;
	}

	public MaUserImpl getMaUser() {
		return maUser;
	}

	public void setMaUser(MaUserImpl maUser) {
		this.maUser = maUser;
	}
	
}