package com.framework.bean.impl;

import com.framework.bean.SyLog;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统日志
 * 作者:    Auto
 * 日期:    2019/8/21
 **********************************************
 */
public class SyLogImpl extends SyLog {
	private static final long serialVersionUID = 1L;
	
	private OpWorkOrderImpl  opWorkOrder;

	public OpWorkOrderImpl getOpWorkOrder() {
		return opWorkOrder;
	}

	public void setOpWorkOrder(OpWorkOrderImpl opWorkOrder) {
		this.opWorkOrder = opWorkOrder;
	}
	
}