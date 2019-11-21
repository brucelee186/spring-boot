package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyLogImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统日志
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
public interface SyLogService {
	/**
	 * 新增实体对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl insert(SyLogImpl syLog) throws CoException;

	/**
	 * 删除实体对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public int delete(SyLogImpl syLog) throws CoException;

	/**
	 * 更新实体对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyLogImpl syLog) throws CoException;

	/**
	 * 查询实体列表
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public List<SyLogImpl> select(SyLogImpl syLog) throws CoException;

	/**
	 * 取得单一对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl get(SyLogImpl syLog) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyLogImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public List<SyLogImpl> selectTree(SyLogImpl syLog) throws CoException;
	
	
	/**
	 * 新增日志对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl insert(Object object) throws CoException;	
	
	/**
	 * 新增计划任务日志
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl insertTask(Object object) throws CoException;	

}