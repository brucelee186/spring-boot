package com.framework.bean.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.framework.bean.OpWorkOrder;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 工单管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public class OpWorkOrderImpl extends OpWorkOrder {
	private static final long serialVersionUID = 1L;
	
	// 工长名字
	private String nameManager;
	
	// 机组名称
	private String nameOpProUnit;
	
	// 带班人名字
	private String nameLeader;
	
	// 点检员名字
	private String nameChecker;
	
	// 状态名称
	private String nameStatus;
	
	// 设备主键
	private Long idOpDevice;
	
	//主电室id
	private Long idOpRoomElectric;
	
	//操作室id
	private Long idOpRoomOperation;
	
	//操作室传值方便
	private net.sf.json.JSONArray opDeviceArr;
	
	private JSONArray jsonGroup;
	
	// 编号数组
	private Long[] arrId;
	
	// 可变长编号数组
	private ArrayList<Long> arrListId;
	
	private String uidMaUser;
	
	private String dateLog;
	
	private Long idMaUser;
	
	private String nameMaUser;
	
	private String nameMaOrgnization;
	
	private Long idOpDeviceCabinet;
	
	private List<MaUserImpl> listMaUser;
	
	private List<MaUserImpl> listMaUserElectrician;
	
	private String information;
	
	private List<OpWorkOrderImpl> listOpWorkOrder;
	
	private OpDeviceGroupImpl opDeviceGroup;
	
	private String statusOpRoomOperation;
	
	private String statusOpRoomElectric;
	
	private String uidMaUserOpRoomOperation;
	
	private String uidMaUserOpRoomElectric;
	
	private String desc;
	
	private String workOrderDate;
	
	private String groupDeviceName;
	
	public List<MaUserImpl> getListMaUserElectrician() {
		return listMaUserElectrician;
	}
	public void setListMaUserElectrician(List<MaUserImpl> listMaUserElectrician) {
		this.listMaUserElectrician = listMaUserElectrician;
	}
	public String getUidMaUserOpRoomOperation() {
		return uidMaUserOpRoomOperation;
	}
	public void setUidMaUserOpRoomOperation(String uidMaUserOpRoomOperation) {
		this.uidMaUserOpRoomOperation = uidMaUserOpRoomOperation;
	}
	public String getUidMaUserOpRoomElectric() {
		return uidMaUserOpRoomElectric;
	}
	public void setUidMaUserOpRoomElectric(String uidMaUserOpRoomElectric) {
		this.uidMaUserOpRoomElectric = uidMaUserOpRoomElectric;
	}
	public String getGroupDeviceName() {
		return groupDeviceName;
	}
	public void setGroupDeviceName(String groupDeviceName) {
		this.groupDeviceName = groupDeviceName;
	}
	public String getStatusOpRoomOperation() {
		return statusOpRoomOperation;
	}
	public void setStatusOpRoomOperation(String statusOpRoomOperation) {
		this.statusOpRoomOperation = statusOpRoomOperation;
	}
	public String getStatusOpRoomElectric() {
		return statusOpRoomElectric;
	}
	public void setStatusOpRoomElectric(String statusOpRoomElectric) {
		this.statusOpRoomElectric = statusOpRoomElectric;
	}
	public OpDeviceGroupImpl getOpDeviceGroup() {
		return opDeviceGroup;
	}
	public void setOpDeviceGroup(OpDeviceGroupImpl opDeviceGroup) {
		this.opDeviceGroup = opDeviceGroup;
	}
	public List<OpWorkOrderImpl> getListOpWorkOrder() {
		return listOpWorkOrder;
	}
	public void setListOpWorkOrder(List<OpWorkOrderImpl> listOpWorkOrder) {
		this.listOpWorkOrder = listOpWorkOrder;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public List<MaUserImpl> getListMaUser() {
		return listMaUser;
	}
	public void setListMaUser(List<MaUserImpl> listMaUser) {
		this.listMaUser = listMaUser;
	}
	public Long getIdOpDeviceCabinet() {
		return idOpDeviceCabinet;
	}
	public void setIdOpDeviceCabinet(Long idOpDeviceCabinet) {
		this.idOpDeviceCabinet = idOpDeviceCabinet;
	}
	public String getNameMaUser() {
		return nameMaUser;
	}
	public void setNameMaUser(String nameMaUser) {
		this.nameMaUser = nameMaUser;
	}
	public String getNameMaOrgnization() {
		return nameMaOrgnization;
	}
	public void setNameMaOrgnization(String nameMaOrgnization) {
		this.nameMaOrgnization = nameMaOrgnization;
	}
	public JSONArray getJsonGroup() {
		return jsonGroup;
	}
	public void setJsonGroup(JSONArray jsonGroup) {
		this.jsonGroup = jsonGroup;
	}
	public Long getIdMaUser() {
		return idMaUser;
	}
	public void setIdMaUser(Long idMaUser) {
		this.idMaUser = idMaUser;
	}
	public String getDateLog() {
		return dateLog;
	}
	public void setDateLog(String dateLog) {
		this.dateLog = dateLog;
	}
	public String getUidMaUser() {
		return uidMaUser;
	}
	public void setUidMaUser(String uidMaUser) {
		this.uidMaUser = uidMaUser;
	}
	public ArrayList<Long> getArrListId() {
		return arrListId;
	}
	public void setArrListId(ArrayList<Long> arrListId) {
		this.arrListId = arrListId;
	}
	public Long[] getArrId() {
		return arrId;
	}
	public void setArrId(Long[] arrId) {
		this.arrId = arrId;
	}
	public String getNameManager() {
		return nameManager;
	}
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}
	public String getNameStatus() {
		return nameStatus;
	}
	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}
	public String getNameOpProUnit() {
		return nameOpProUnit;
	}
	public void setNameOpProUnit(String nameOpProUnit) {
		this.nameOpProUnit = nameOpProUnit;
	}
	public String getNameChecker() {
		return nameChecker;
	}
	public void setNameChecker(String nameChecker) {
		this.nameChecker = nameChecker;
	}
	public String getNameLeader() {
		return nameLeader;
	}
	public void setNameLeader(String nameLeader) {
		this.nameLeader = nameLeader;
	}
	
	public Long getIdOpDevice() {
		return idOpDevice;
	}

	public void setIdOpDevice(Long idOpDevice) {
		this.idOpDevice = idOpDevice;
	}
	
	public net.sf.json.JSONArray getOpDeviceArr() {
		return opDeviceArr;
	}

	public void setOpDeviceArr(net.sf.json.JSONArray opDeviceArr) {
		this.opDeviceArr = opDeviceArr;
	}
	public Long getIdOpRoomElectric() {
		return idOpRoomElectric;
	}
	public void setIdOpRoomElectric(Long idOpRoomElectric) {
		this.idOpRoomElectric = idOpRoomElectric;
	}
	public Long getIdOpRoomOperation() {
		return idOpRoomOperation;
	}
	public void setIdOpRoomOperation(Long idOpRoomOperation) {
		this.idOpRoomOperation = idOpRoomOperation;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getWorkOrderDate() {
		return workOrderDate;
	}
	public void setWorkOrderDate(String workOrderDate) {
		this.workOrderDate = workOrderDate;
	}
	
	
}