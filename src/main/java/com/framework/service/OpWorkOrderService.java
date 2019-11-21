package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 工单管理
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface OpWorkOrderService {
	/**
	 * 新增实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderImpl insert(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 删除实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public int delete(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 更新实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpWorkOrderImpl opWorkOrder) throws CoException;
	
	/**
	 * 批量更新停电完成工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public boolean updateWorkOrderPo(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 查询实体列表
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> select(OpWorkOrderImpl opWorkOrder) throws CoException;
	
	/**
	 * 查询所有参与送断电的工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> searchWorkOrderCabinet(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 取得单一对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderImpl get(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpWorkOrderImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> selectTree(OpWorkOrderImpl opWorkOrder) throws CoException;
	
	/**
	 * 根据设备主键查询操作室工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getIdOpDeviceToOpWorkOrder(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 根据设备主键查主电室工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getOrdersPowersOffice(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 *	 查询送电序列
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getOrdersPowersSend(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 	查询还牌序列
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getReturnCardOrders(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 查询电工历史工单
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> selectHistoryOpWorkOrder(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 查询首页工单列表
	 * @param opWorkOrderImpl
	 * @return
	 */
	public List<OpWorkOrderImpl> getOpreatePage(OpWorkOrderImpl opWorkOrderImpl) throws CoException;

	/**
	 * 查询历史工单
	 * @param opWorkOrderImpl
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getHistoryOpWorkOrder(OpWorkOrderImpl opWorkOrderImpl) throws CoException;

	/**
	 * 查询工单详细
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderImpl getOpreaterDetailInfo(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 更新工单状态
	 * @param opWorkOrder
	 * @throws CoException
	 */
	public void updateOpWorkOrderStatus(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * 查询该工单是否被其他操作室还牌
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getTheOpWorkOrderToOtherReturn(OpWorkOrderImpl opWorkOrder) throws CoException;

	/**
	 * //工单跨操作室发牌，部分牌发放完成，不可以拒绝发牌
	 * @param opWorkOrderCard
	 * @return
	 */
	public List<OpWorkOrderCardImpl> returnTheOpWorkOrderGrantCard(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

}