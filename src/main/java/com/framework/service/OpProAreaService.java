package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpProAreaImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 区域
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
public interface OpProAreaService {
	/**
	 * 新增实体对象
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public OpProAreaImpl insert(OpProAreaImpl opProArea) throws CoException;

	/**
	 * 删除实体对象
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public int delete(OpProAreaImpl opProArea) throws CoException;

	/**
	 * 更新实体对象
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpProAreaImpl opProArea) throws CoException;

	/**
	 * 查询实体列表
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public List<OpProAreaImpl> select(OpProAreaImpl opProArea) throws CoException;

	/**
	 * 取得单一对象
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public OpProAreaImpl get(OpProAreaImpl opProArea) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpProAreaImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opProArea
	 * @return
	 * @throws CoException
	 */
	public List<OpProAreaImpl> selectTree(OpProAreaImpl opProArea) throws CoException;

}