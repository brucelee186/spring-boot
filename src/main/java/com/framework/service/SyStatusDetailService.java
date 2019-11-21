package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyStatusDetailImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统状态明细
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface SyStatusDetailService {
	/**
	 * 新增实体对象
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public SyStatusDetailImpl insert(SyStatusDetailImpl syStatusDetail) throws CoException;

	/**
	 * 删除实体对象
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public int delete(SyStatusDetailImpl syStatusDetail) throws CoException;

	/**
	 * 更新实体对象
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyStatusDetailImpl syStatusDetail) throws CoException;

	/**
	 * 查询实体列表
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusDetailImpl> select(SyStatusDetailImpl syStatusDetail) throws CoException;

	/**
	 * 取得单一对象
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public SyStatusDetailImpl get(SyStatusDetailImpl syStatusDetail) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyStatusDetailImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusDetailImpl> selectTree(SyStatusDetailImpl syStatusDetail) throws CoException;
	
	/**
	 * 查询树型实体
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusDetailImpl> selectTreeValue(SyStatusDetailImpl syStatusDetail) throws CoException;

}