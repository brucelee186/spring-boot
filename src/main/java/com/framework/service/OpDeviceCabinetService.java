package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpDeviceCabinetImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 设备开关柜
 * 作者:     Auto
 * 日期:     2019/8/27
**********************************************
*/
public interface OpDeviceCabinetService {
	/**
	 * 新增实体对象
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public OpDeviceCabinetImpl insert(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

	/**
	 * 删除实体对象
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public int delete(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

	/**
	 * 更新实体对象
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

	/**
	 * 查询实体列表
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceCabinetImpl> select(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

	/**
	 * 取得单一对象
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public OpDeviceCabinetImpl get(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpDeviceCabinetImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opDeviceCabinet
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceCabinetImpl> selectTree(OpDeviceCabinetImpl opDeviceCabinet) throws CoException;

}