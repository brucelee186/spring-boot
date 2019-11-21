package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 设备电工牌
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface OpDeviceElectricianCardService {
	/**
	 * 新增实体对象
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public OpDeviceElectricianCardImpl insert(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianCardImpl> select(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public OpDeviceElectricianCardImpl get(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceElectricianCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianCardImpl> selectTree(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

	/**
	 * 查询手机端停电序列送电序列
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianCardImpl> getStopOrSendPower(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException;

}