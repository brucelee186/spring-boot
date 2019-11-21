package com.framework.bean.impl;

import com.framework.bean.OpCard;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 卡牌管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public class OpCardImpl extends OpCard {
	private static final long serialVersionUID = 1L;
	
	private String nameOpRoomOperation;
	
	private String nameOpRoomElectric;
	
	private String nameOpDevice;

	public String getNameOpDevice() {
		return nameOpDevice;
	}

	public void setNameOpDevice(String nameOpDevice) {
		this.nameOpDevice = nameOpDevice;
	}

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
	
}