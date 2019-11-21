package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 卡牌管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public interface OpDeviceConditionMapper extends BaseMapper {

	List<OpDeviceImpl> getOpWorkOderToDevice(OpDeviceImpl opDevice);
	
	int updateCabinetDevice(OpDeviceImpl opDevice);
	
	int updateCabinetDeviceOn(OpDeviceImpl opDevice);
	
	int updateCabinetDeviceOff(OpDeviceImpl opDevice);
	
	int updateOpDeviceOpRoomStatus(OpDeviceImpl opDevice);

	List<OpDeviceImpl> selectOpDevice(OpDeviceImpl opDevice);

	List<OpDeviceImpl> selectOpWorkOrderContainsDevice(OpWorkOrderImpl opWorkOrderImpl);

	List<OpDeviceImpl> getOpWorkOderToOpration(OpDeviceImpl opDevice);

	List<OpDeviceImpl> getAllDevice(OpDeviceImpl innDeviceImpl);

	List<OpDeviceImpl> getTheDeviceToOpWorkOrder(OpDeviceImpl opDeviceImpl);
	
	// 查询需要主电室发牌的设备
	List<OpDeviceImpl> selectOpDeviceOpRoom(OpDeviceImpl opDeviceImpl);

	OpDeviceImpl getTheDeviceTomidOpWorkOrder(OpDeviceImpl opDeviceImpl);

	List<OpDeviceImpl> getAllDeviceLog(OpDeviceImpl opDeviceImpl);

	//查询该设备下所有工单状态是at
	List<OpDeviceImpl> findTheOpdeviceToOpWorkOrderCard(OpDeviceImpl opDeviceImpl);
	//查询该设备下所有工单状态是td
	List<OpDeviceImpl> findTheOpdeviceToOpWorkOrderCardForTd(OpDeviceImpl opDeviceImpl);
	
	// 查询主工单下的所有设备
	List<OpDeviceImpl> searchOpDeviceByMidOpWorkOrder(OpDeviceImpl opDeviceImpl);
	
	// 初始化主电室驳回主工单下的所有设备
	int updateOpDeviceRejectByMidOpWorkOrder(OpDeviceImpl opDevice);
	
	// 查询操作室驳回工单对应的所有设备
	List<OpDeviceImpl> searchOpDeviceByOpRoomOperation(OpDeviceImpl opDevice);
}