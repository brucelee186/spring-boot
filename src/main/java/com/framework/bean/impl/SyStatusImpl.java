package com.framework.bean.impl;

import com.framework.bean.SyStatus;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统状态
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public class SyStatusImpl extends SyStatus {
	private static final long serialVersionUID = 1L;
	
	private Long idUnique;

	public Long getIdUnique() {
		return idUnique;
	}

	public void setIdUnique(Long idUnique) {
		this.idUnique = idUnique;
	}
	
	
}