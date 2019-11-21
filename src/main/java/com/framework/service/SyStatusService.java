package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyStatusImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统状态
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface SyStatusService {
	/**
	 * 新增实体对象
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public SyStatusImpl insert(SyStatusImpl syStatus) throws CoException;

	/**
	 * 删除实体对象
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public int delete(SyStatusImpl syStatus) throws CoException;

	/**
	 * 更新实体对象
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyStatusImpl syStatus) throws CoException;

	/**
	 * 查询实体列表
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusImpl> select(SyStatusImpl syStatus) throws CoException;

	/**
	 * 取得单一对象
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public SyStatusImpl get(SyStatusImpl syStatus) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyStatusImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syStatus
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusImpl> selectTree(SyStatusImpl syStatus) throws CoException;

}