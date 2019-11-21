package com.framework.bean.impl;

import com.framework.bean.SyStatusDetail;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统状态明细
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public class SyStatusDetailImpl extends SyStatusDetail {
	private static final long serialVersionUID = 1L;
	
	private String nameSyStatus;

	public String getNameSyStatus() {
		return nameSyStatus;
	}

	public void setNameSyStatus(String nameSyStatus) {
		this.nameSyStatus = nameSyStatus;
	}
	
	
}