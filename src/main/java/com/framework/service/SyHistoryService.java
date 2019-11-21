package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyHistoryImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统状态明细
 * 作者:     Auto
 * 日期:     2019/8/20
**********************************************
*/
public interface SyHistoryService {
	/**
	 * 新增实体对象
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public SyHistoryImpl insert(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 删除实体对象
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public int delete(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 更新实体对象
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 查询实体列表
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public List<SyHistoryImpl> select(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 取得单一对象
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public SyHistoryImpl get(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyHistoryImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public List<SyHistoryImpl> selectTree(SyHistoryImpl syHistory) throws CoException;

	/**
	 * 新增系统历史
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public SyHistoryImpl insert(Object object) throws CoException;
}