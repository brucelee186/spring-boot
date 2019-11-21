package com.framework.bean.impl;

import com.framework.bean.MaUser;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/8/9
 **********************************************
 */
public class MaUserImpl extends MaUser {
	private static final long serialVersionUID = 1L;
	
	private String uidMaUser;
	
	private String nameMaOrgnization;
	
	private Long idOpRoomOperation;
	
	private Long idOpRoomElectric;
	
	private Long idOpWorkOrder;
	
	private Long midOpWorkOrder;
	
	private Long idOpDeviceGroup;
	
	private String groupDeviceCode;
	
	private String nameOpWorkOrder;
	
	private Long idOpDeviceCabinet;
	
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

	public Long getIdOpDeviceCabinet() {
		return idOpDeviceCabinet;
	}

	public void setIdOpDeviceCabinet(Long idOpDeviceCabinet) {
		this.idOpDeviceCabinet = idOpDeviceCabinet;
	}

	public String getNameOpWorkOrder() {
		return nameOpWorkOrder;
	}

	public void setNameOpWorkOrder(String nameOpWorkOrder) {
		this.nameOpWorkOrder = nameOpWorkOrder;
	}

	public Long getMidOpWorkOrder() {
		return midOpWorkOrder;
	}

	public void setMidOpWorkOrder(Long midOpWorkOrder) {
		this.midOpWorkOrder = midOpWorkOrder;
	}

	public Long getIdOpWorkOrder() {
		return idOpWorkOrder;
	}

	public void setIdOpWorkOrder(Long idOpWorkOrder) {
		this.idOpWorkOrder = idOpWorkOrder;
	}

	public String getGroupDeviceCode() {
		return groupDeviceCode;
	}

	public void setGroupDeviceCode(String groupDeviceCode) {
		this.groupDeviceCode = groupDeviceCode;
	}

	public Long getIdOpDeviceGroup() {
		return idOpDeviceGroup;
	}

	public void setIdOpDeviceGroup(Long idOpDeviceGroup) {
		this.idOpDeviceGroup = idOpDeviceGroup;
	}

	public String getNameMaOrgnization() {
		return nameMaOrgnization;
	}

	public void setNameMaOrgnization(String nameMaOrgnization) {
		this.nameMaOrgnization = nameMaOrgnization;
	}

	public Long getIdOpRoomOperation() {
		return idOpRoomOperation;
	}

	public void setIdOpRoomOperation(Long idOpRoomOperation) {
		this.idOpRoomOperation = idOpRoomOperation;
	}

	public Long getIdOpRoomElectric() {
		return idOpRoomElectric;
	}

	public void setIdOpRoomElectric(Long idOpRoomElectric) {
		this.idOpRoomElectric = idOpRoomElectric;
	}

	public String getUidMaUser() {
		return uidMaUser;
	}

	public void setUidMaUser(String uidMaUser) {
		this.uidMaUser = uidMaUser;
	}
}