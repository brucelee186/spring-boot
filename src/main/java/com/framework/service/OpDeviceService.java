package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 卡牌管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
public interface OpDeviceService {
	/**
	 * 新增实体对象
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public OpDeviceImpl insert(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceImpl opDevice) throws CoException;
	
	/**
	 * 更新实体对象
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public boolean updateOpDeviceOpRoomStatus(OpDeviceImpl opDevice) throws CoException;
	
	/**
	 * 更新开关柜对应的开关状态
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public boolean updateCabinetDevice(OpDeviceImpl opDevice) throws CoException;
	
	/**
	 * 停电
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public boolean setDevicePowerOff(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> select(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public OpDeviceImpl get(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> selectTree(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 主电室查询该设备所在工单是否完全发牌完成
	 * @param opDeviceImpl
	 * @return
	 */
	public List<OpDeviceImpl> getTheDeviceToOpWorkOrder(OpDeviceImpl opDeviceImpl) throws CoException;

	/**
	 * 查询工单下所有设备
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> getAllDevice(OpDeviceImpl opDeviceImpl) throws CoException;

	/**
	 * 查询工单下所有设备日志
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> getAllDeviceLog(OpDeviceImpl opDeviceImpl) throws CoException;

}