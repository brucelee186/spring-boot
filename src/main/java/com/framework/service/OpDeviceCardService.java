package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceCardImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 设备牌
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface OpDeviceCardService {
	/**
	 * 新增实体对象
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public OpDeviceCardImpl insert(OpDeviceCardImpl opDeviceCard) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceCardImpl opDeviceCard) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceCardImpl opDeviceCard) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceCardImpl> select(OpDeviceCardImpl opDeviceCard) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public OpDeviceCardImpl get(OpDeviceCardImpl opDeviceCard) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDeviceCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceCardImpl> selectTree(OpDeviceCardImpl opDeviceCard) throws CoException;

}