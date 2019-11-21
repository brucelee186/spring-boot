package com.framework.bean.impl;

import com.framework.bean.OpDeviceCabinet;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 设备开关柜
 * 作者:    Auto
 * 日期:    2019/8/27
 **********************************************
 */
public class OpDeviceCabinetImpl extends OpDeviceCabinet {
	private static final long serialVersionUID = 1L;
	
	private String nameMaUser;
	
	private String locationOpDeviceCabinet;
	
	public String getLocationOpDeviceCabinet() {
		return locationOpDeviceCabinet;
	}

	public void setLocationOpDeviceCabinet(String locationOpDeviceCabinet) {
		this.locationOpDeviceCabinet = locationOpDeviceCabinet;
	}

	public String getNameMaUser() {
		return nameMaUser;
	}

	public void setNameMaUser(String nameMaUser) {
		this.nameMaUser = nameMaUser;
	}
	
	
}