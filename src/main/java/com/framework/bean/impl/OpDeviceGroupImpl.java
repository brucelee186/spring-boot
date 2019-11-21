package com.framework.bean.impl;

import java.util.List;

import com.framework.bean.OpDeviceGroup;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 设备电工组
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public class OpDeviceGroupImpl extends OpDeviceGroup {
	private static final long serialVersionUID = 1L;
	
	private List<OpWorkOrderImpl> listOpWorkOrder;

	public List<OpWorkOrderImpl> getListOpWorkOrder() {
		return listOpWorkOrder;
	}

	public void setListOpWorkOrder(List<OpWorkOrderImpl> listOpWorkOrder) {
		this.listOpWorkOrder = listOpWorkOrder;
	}
	
}