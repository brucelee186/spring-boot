package com.framework.bean.impl;

import java.util.List;

import com.framework.bean.OpWorkOrder;
import com.framework.bean.OpWorkOrderCard;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 工单牌
 * 作者:    Auto
 * 日期:    2019/8/21
 **********************************************
 */
public class OpWorkOrderCardImpl extends OpWorkOrderCard {
	private static final long serialVersionUID = 1L;
	
	private String nameOpDevice;
	private String nameMaUserOpCardOperation;
	private String nameMaUserOpCardPower;
	private String nameMaUserOpCardWork;
	private String nameOpRoomOperation;
	private String nameOpRoomElectric;
	
	private List<OpWorkOrderImpl> listOpWorkOrder;
	
	private List<OpWorkOrderCardImpl> listOpWorkOrderCard;
	
	public List<OpWorkOrderCardImpl> getListOpWorkOrderCard() {
		return listOpWorkOrderCard;
	}
	public void setListOpWorkOrderCard(List<OpWorkOrderCardImpl> listOpWorkOrderCard) {
		this.listOpWorkOrderCard = listOpWorkOrderCard;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<OpWorkOrderImpl> getListOpWorkOrder() {
		return listOpWorkOrder;
	}
	public void setListOpWorkOrder(List<OpWorkOrderImpl> listOpWorkOrder) {
		this.listOpWorkOrder = listOpWorkOrder;
	}
	public String getNameOpDevice() {
		return nameOpDevice;
	}
	public void setNameOpDevice(String nameOpDevice) {
		this.nameOpDevice = nameOpDevice;
	}
	public String getNameMaUserOpCardOperation() {
		return nameMaUserOpCardOperation;
	}
	public void setNameMaUserOpCardOperation(String nameMaUserOpCardOperation) {
		this.nameMaUserOpCardOperation = nameMaUserOpCardOperation;
	}
	public String getNameMaUserOpCardPower() {
		return nameMaUserOpCardPower;
	}
	public void setNameMaUserOpCardPower(String nameMaUserOpCardPower) {
		this.nameMaUserOpCardPower = nameMaUserOpCardPower;
	}
	public String getNameMaUserOpCardWork() {
		return nameMaUserOpCardWork;
	}
	public void setNameMaUserOpCardWork(String nameMaUserOpCardWork) {
		this.nameMaUserOpCardWork = nameMaUserOpCardWork;
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