package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceElectricianGroupImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 设备电工组
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface OpDeviceElectricianGroupService {
	/**
	 * 新增实体对象
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public OpDeviceElectricianGroupImpl insert(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianGroupImpl> select(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public OpDeviceElectricianGroupImpl get(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceElectricianGroupImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDeviceElectricianGroup
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianGroupImpl> selectTree(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException;

}