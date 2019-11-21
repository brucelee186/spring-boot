package com.framework.bean.impl;

import com.framework.bean.OpProUnit;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 机组
 * 作者:    Auto
 * 日期:    2019/8/14
 **********************************************
 */
public class OpProUnitImpl extends OpProUnit {
	private static final long serialVersionUID = 1L;
	
	private String nameOpProArea;
	
	private String nameOpProLine;

	public String getNameOpProLine() {
		return nameOpProLine;
	}

	public void setNameOpProLine(String nameOpProLine) {
		this.nameOpProLine = nameOpProLine;
	}

	public String getNameOpProArea() {
		return nameOpProArea;
	}

	public void setNameOpProArea(String nameOpProArea) {
		this.nameOpProArea = nameOpProArea;
	}
	
}