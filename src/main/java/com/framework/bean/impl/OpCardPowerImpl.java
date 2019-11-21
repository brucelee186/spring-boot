package com.framework.bean.impl;

import com.framework.bean.OpCardPower;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 停电牌
 * 作者:    Auto
 * 日期:    2019/8/17
 **********************************************
 */
public class OpCardPowerImpl extends OpCardPower {
	private static final long serialVersionUID = 1L;
	
	private String nameOpDevice;
	
	private String nameOpRoomOperation; 
	
	private String nameOpRoomElectric;

	public String getNameOpRoomOperation() {
		return nameOpRoomOperation;
	}

	public void setNameOpRoomOperation(String nameOpRoomOperation) {
		this.nameOpRoomOperation = nameOpRoomOperation;
	}

	public String getNameOpRoomElectric() {
		return nameOpRoomElectric;
	}

	public void setNameOpRoomElectric(String nameOpRoomElectric) {
		this.nameOpRoomElectric = nameOpRoomElectric;
	}

	public String getNameOpDevice() {
		return nameOpDevice;
	}

	public void setNameOpDevice(String nameOpDevice) {
		this.nameOpDevice = nameOpDevice;
	}
	
}