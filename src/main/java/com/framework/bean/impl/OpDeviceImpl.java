package com.framework.bean.impl;

import java.util.ArrayList;
import java.util.List;

import com.framework.bean.OpDevice;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 卡牌管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public class OpDeviceImpl extends OpDevice {
	private static final long serialVersionUID = 1L;
	
	private String nameOpRoomOperation;
	
	private String nameOpRoomElectric;
	
	private Long idOpWorkOrder;
	
	private String nameOpDeviceCabinet;
	
	private String strOn;
	
	private String strOf;
	
	
	private ArrayList<Long> arrListOpDevice;
	
	private ArrayList<Long> arrOn;
	
	private ArrayList<Long> arrOf;
	
	private String[] strArr;
	
	private String[] strArrOn;
	
	private String[] strArrOf;
	
	private List<OpDeviceImpl> listOpDevice;
	
	private List<OpWorkOrderCardImpl> listOpWorkOrderCard;
	
	private String nameStatus;
	
	public List<OpWorkOrderCardImpl> getListOpWorkOrderCard() {
		return listOpWorkOrderCard;
	}

	public void setListOpWorkOrderCard(List<OpWorkOrderCardImpl> listOpWorkOrderCard) {
		this.listOpWorkOrderCard = listOpWorkOrderCard;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	public List<OpDeviceImpl> getListOpDevice() {
		return listOpDevice;
	}

	public void setListOpDevice(List<OpDeviceImpl> listOpDevice) {
		this.listOpDevice = listOpDevice;
	}

	public ArrayList<Long> getArrListOpDevice() {
		return arrListOpDevice;
	}

	public void setArrListOpDevice(ArrayList<Long> arrListOpDevice) {
		this.arrListOpDevice = arrListOpDevice;
	}

	public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	public String[] getStrArrOn() {
		return strArrOn;
	}

	public void setStrArrOn(String[] strArrOn) {
		this.strArrOn = strArrOn;
	}

	public String[] getStrArrOf() {
		return strArrOf;
	}

	public void setStrArrOf(String[] strArrOf) {
		this.strArrOf = strArrOf;
	}

	public ArrayList<Long> getArrOn() {
		return arrOn;
	}

	public void setArrOn(ArrayList<Long> arrOn) {
		this.arrOn = arrOn;
	}

	public ArrayList<Long> getArrOf() {
		return arrOf;
	}

	public void setArrOf(ArrayList<Long> arrOf) {
		this.arrOf = arrOf;
	}

	public String getStrOn() {
		return strOn;
	}

	public void setStrOn(String strOn) {
		this.strOn = strOn;
	}

	public String getStrOf() {
		return strOf;
	}

	public void setStrOf(String strOf) {
		this.strOf = strOf;
	}

	public String getNameOpDeviceCabinet() {
		return nameOpDeviceCabinet;
	}

	public void setNameOpDeviceCabinet(String nameOpDeviceCabinet) {
		this.nameOpDeviceCabinet = nameOpDeviceCabinet;
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

	public Long getIdOpWorkOrder() {
		return idOpWorkOrder;
	}

	public void setIdOpWorkOrder(Long idOpWorkOrder) {
		this.idOpWorkOrder = idOpWorkOrder;
	}

	
}