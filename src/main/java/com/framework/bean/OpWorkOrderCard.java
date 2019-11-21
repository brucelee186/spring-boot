﻿package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 工单牌
 * 作者:    Neo Yin
 * 日期:    2019/9/20
 **********************************************
 */
public class OpWorkOrderCard extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 工单
	private Long idOpWorkOrder;
	// 主工单
	private Long midOpWorkOrder;
	// 设备
	private Long idOpDevice;
	// 设备状态
	private String statusOpDevice;
	// 操作牌
	private Long idOpCardOperation;
	// 操作牌状态
	private String statusOpCardOperation;
	// 操作牌发放时间
	private Date dateSendOpCardOperation;
	// 操作牌持有者
	private Long idMaUserOpCardOperation;
	// 停电牌
	private Long idOpCardPower;
	// 停电牌状态
	private String statusOpCardPower;
	// 停电牌发放时间
	private Date dateSendOpCardPower;
	// 停电牌持有者
	private Long idMaUserOpCardPower;
	// 工作牌
	private Long idOpCardWork;
	// 工作牌状态
	private String statusOpCardWork;
	// 工作牌发放时间
	private Date dateSendOpCardWork;
	// 工作牌持有者
	private Long idMaUserOpCardWork;
	// 带班人
	private Long idLeader;
	// 带班人卡片状态
	private String statusLeader;
	// 操作室
	private Long idOpRoomOperation;
	// 停电组编号
	private Long idOpDeviceGroupPowerOff;
	// 送电组编号
	private Long idOpDeviceGroupPowerOn;
	// 操作室卡片状态
	private String statusOpRoomOperation;
	// 主电室
	private Long idOpRoomElectric;
	// 主电室卡片状态
	private String statusRoomElectric;
	// 状态
	private String status;
	// 操作牌类型
	private String typeOpCardOperation;
	// 记录标识(d:删除, h:隐藏, n:正常)
	private String tag;
	// 创建人编号
	private String createUser;
	// 创建时间
	private Date createDate;
	// IP地址
	private String createIp;
	// 修改人编号
	private String modifiedUser;
	// 修改时间
	private Date modifiedDate;
	// IP地址
	private String modifiedIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public Long getIdMaCompany() {
		return idMaCompany;
	}

	public void setIdMaCompany(Long idMaCompany) {
		this.idMaCompany = idMaCompany;
	}
	public Long getIdOpWorkOrder() {
		return idOpWorkOrder;
	}

	public void setIdOpWorkOrder(Long idOpWorkOrder) {
		this.idOpWorkOrder = idOpWorkOrder;
	}
	public Long getMidOpWorkOrder() {
		return midOpWorkOrder;
	}

	public void setMidOpWorkOrder(Long midOpWorkOrder) {
		this.midOpWorkOrder = midOpWorkOrder;
	}
	public Long getIdOpDevice() {
		return idOpDevice;
	}

	public void setIdOpDevice(Long idOpDevice) {
		this.idOpDevice = idOpDevice;
	}
	public String getStatusOpDevice() {
		return statusOpDevice;
	}

	public void setStatusOpDevice(String statusOpDevice) {
		this.statusOpDevice = statusOpDevice;
	}
	public Long getIdOpCardOperation() {
		return idOpCardOperation;
	}

	public void setIdOpCardOperation(Long idOpCardOperation) {
		this.idOpCardOperation = idOpCardOperation;
	}
	public String getStatusOpCardOperation() {
		return statusOpCardOperation;
	}

	public void setStatusOpCardOperation(String statusOpCardOperation) {
		this.statusOpCardOperation = statusOpCardOperation;
	}
	public Date getDateSendOpCardOperation() {
		return dateSendOpCardOperation;
	}

	public void setDateSendOpCardOperation(Date dateSendOpCardOperation) {
		this.dateSendOpCardOperation = dateSendOpCardOperation;
	}
	public Long getIdMaUserOpCardOperation() {
		return idMaUserOpCardOperation;
	}

	public void setIdMaUserOpCardOperation(Long idMaUserOpCardOperation) {
		this.idMaUserOpCardOperation = idMaUserOpCardOperation;
	}
	public Long getIdOpCardPower() {
		return idOpCardPower;
	}

	public void setIdOpCardPower(Long idOpCardPower) {
		this.idOpCardPower = idOpCardPower;
	}
	public String getStatusOpCardPower() {
		return statusOpCardPower;
	}

	public void setStatusOpCardPower(String statusOpCardPower) {
		this.statusOpCardPower = statusOpCardPower;
	}
	public Date getDateSendOpCardPower() {
		return dateSendOpCardPower;
	}

	public void setDateSendOpCardPower(Date dateSendOpCardPower) {
		this.dateSendOpCardPower = dateSendOpCardPower;
	}
	public Long getIdMaUserOpCardPower() {
		return idMaUserOpCardPower;
	}

	public void setIdMaUserOpCardPower(Long idMaUserOpCardPower) {
		this.idMaUserOpCardPower = idMaUserOpCardPower;
	}
	public Long getIdOpCardWork() {
		return idOpCardWork;
	}

	public void setIdOpCardWork(Long idOpCardWork) {
		this.idOpCardWork = idOpCardWork;
	}
	public String getStatusOpCardWork() {
		return statusOpCardWork;
	}

	public void setStatusOpCardWork(String statusOpCardWork) {
		this.statusOpCardWork = statusOpCardWork;
	}
	public Date getDateSendOpCardWork() {
		return dateSendOpCardWork;
	}

	public void setDateSendOpCardWork(Date dateSendOpCardWork) {
		this.dateSendOpCardWork = dateSendOpCardWork;
	}
	public Long getIdMaUserOpCardWork() {
		return idMaUserOpCardWork;
	}

	public void setIdMaUserOpCardWork(Long idMaUserOpCardWork) {
		this.idMaUserOpCardWork = idMaUserOpCardWork;
	}
	public Long getIdLeader() {
		return idLeader;
	}

	public void setIdLeader(Long idLeader) {
		this.idLeader = idLeader;
	}
	public String getStatusLeader() {
		return statusLeader;
	}

	public void setStatusLeader(String statusLeader) {
		this.statusLeader = statusLeader;
	}
	public Long getIdOpRoomOperation() {
		return idOpRoomOperation;
	}

	public void setIdOpRoomOperation(Long idOpRoomOperation) {
		this.idOpRoomOperation = idOpRoomOperation;
	}
	public Long getIdOpDeviceGroupPowerOff() {
		return idOpDeviceGroupPowerOff;
	}

	public void setIdOpDeviceGroupPowerOff(Long idOpDeviceGroupPowerOff) {
		this.idOpDeviceGroupPowerOff = idOpDeviceGroupPowerOff;
	}
	public Long getIdOpDeviceGroupPowerOn() {
		return idOpDeviceGroupPowerOn;
	}

	public void setIdOpDeviceGroupPowerOn(Long idOpDeviceGroupPowerOn) {
		this.idOpDeviceGroupPowerOn = idOpDeviceGroupPowerOn;
	}
	public String getStatusOpRoomOperation() {
		return statusOpRoomOperation;
	}

	public void setStatusOpRoomOperation(String statusOpRoomOperation) {
		this.statusOpRoomOperation = statusOpRoomOperation;
	}
	public Long getIdOpRoomElectric() {
		return idOpRoomElectric;
	}

	public void setIdOpRoomElectric(Long idOpRoomElectric) {
		this.idOpRoomElectric = idOpRoomElectric;
	}
	public String getStatusRoomElectric() {
		return statusRoomElectric;
	}

	public void setStatusRoomElectric(String statusRoomElectric) {
		this.statusRoomElectric = statusRoomElectric;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeOpCardOperation() {
		return typeOpCardOperation;
	}

	public void setTypeOpCardOperation(String typeOpCardOperation) {
		this.typeOpCardOperation = typeOpCardOperation;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedIp() {
		return modifiedIp;
	}

	public void setModifiedIp(String modifiedIp) {
		this.modifiedIp = modifiedIp;
	}
}