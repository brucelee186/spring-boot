package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.common.BaseMapper;
import com.github.pagehelper.PageInfo;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 工单管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public interface OpWorkOrderConditionMapper extends BaseMapper {

	List<OpWorkOrderImpl> getIdOpDeviceToOpWorkOrder(OpWorkOrderImpl opWorkOrder);

	List<OpWorkOrderImpl> getOrdersPowersOffice(OpWorkOrderImpl opWorkOrder);
	
	// 查询所有停电工完成工单
	List<OpWorkOrderImpl> selectPoWorkOrder(OpWorkOrderImpl opWorkOrder);
	
	// 查询所有参与送断电的工单
	List<OpWorkOrderImpl> searchWorkOrderCabinet(OpWorkOrderImpl opWorkOrder);
	
	// 批量更新停电完成工单
	int updateWorkOrderPo(OpWorkOrderImpl opWorkOrder);

	OpWorkOrderImpl getOrdersIdCheckerInfo(OpWorkOrderImpl opWorkOrder);

	List<OpWorkOrderImpl> getOrdersPowersSend(OpWorkOrderImpl opWorkOrder);

	List<OpWorkOrderImpl> getReturnCardOrders(OpWorkOrderImpl opWorkOrder);
	
	//查询电工历史工单
	List<OpWorkOrderImpl> selectHistoryOpWorkOrder(OpDeviceElectricianCardImpl opDeviceElectricianCard);

	//查询首页工单列表
	List<OpWorkOrderImpl> getOpreatePage(OpWorkOrderImpl opWorkOrderImpl);
	
	//查询历史工单
	List<OpWorkOrderImpl> getHistoryOpWorkOrder(OpWorkOrderImpl opWorkOrderImpl);

	//查询工单详细
	OpWorkOrderImpl getOpreaterDetailInfo(OpWorkOrderImpl opWorkOrder);
	
	//查询该工单是否被其他操作室还牌
	List<OpWorkOrderImpl> getTheOpWorkOrderToOtherReturn(OpWorkOrderImpl opWorkOrderImpl);
	
	// 根据主工单编号查询所有关联的工单
	List<OpWorkOrderImpl> selectOpWorkOrderByMid(OpWorkOrderImpl opWorkOrderImpl);
	
	// 更新驳回关联的工单
	int updateRejectOpWorkOrderByMid(OpWorkOrderImpl opWorkOrderImpl);
	
}