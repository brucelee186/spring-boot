package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceGroupImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 设备电工组
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface OpDeviceGroupService {
	/**
	 * 新增实体对象
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public OpDeviceGroupImpl insert(OpDeviceGroupImpl opDeviceGroup) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceGroupImpl opDeviceGroup) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceGroupImpl opDeviceGroup) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceGroupImpl> select(OpDeviceGroupImpl opDeviceGroup) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public OpDeviceGroupImpl get(OpDeviceGroupImpl opDeviceGroup) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceGroupImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDeviceGroup
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceGroupImpl> selectTree(OpDeviceGroupImpl opDeviceGroup) throws CoException;

}