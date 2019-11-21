package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyInforStatusImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统状态信息
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface SyInforStatusService {
	/**
	 * 新增实体对象
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public SyInforStatusImpl insert(SyInforStatusImpl syInforStatus) throws CoException;

	/**
	 * 删除实体对象
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public int delete(SyInforStatusImpl syInforStatus) throws CoException;

	/**
	 * 更新实体对象
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyInforStatusImpl syInforStatus) throws CoException;

	/**
	 * 查询实体列表
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public List<SyInforStatusImpl> select(SyInforStatusImpl syInforStatus) throws CoException;

	/**
	 * 取得单一对象
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public SyInforStatusImpl get(SyInforStatusImpl syInforStatus) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyInforStatusImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syInforStatus
	 * @return
	 * @throws CoException
	 */
	public List<SyInforStatusImpl> selectTree(SyInforStatusImpl syInforStatus) throws CoException;

}