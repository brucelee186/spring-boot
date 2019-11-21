package com.framework.bean.impl;

import com.framework.bean.MaOrgnizationLevel;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 组织
 * 作者:    Auto
 * 日期:    2019/7/30
 **********************************************
 */
public class MaOrgnizationLevelImpl extends MaOrgnizationLevel {
	private static final long serialVersionUID = 1L;
	
	private String nameMaCompany;
	
	private String orgLevelName;

	public String getOrgLevelName() {
		return orgLevelName;
	}

	public void setOrgLevelName(String orgLevelName) {
		this.orgLevelName = orgLevelName;
	}

	public String getNameMaCompany() {
		return nameMaCompany;
	}

	public void setNameMaCompany(String nameMaCompany) {
		this.nameMaCompany = nameMaCompany;
	}

}