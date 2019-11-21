package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpProUnitImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 机组
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
public interface OpProUnitService {
	/**
	 * 新增实体对象
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public OpProUnitImpl insert(OpProUnitImpl opProUnit) throws CoException;

	/**
	 * 删除实体对象
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public int delete(OpProUnitImpl opProUnit) throws CoException;

	/**
	 * 更新实体对象
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpProUnitImpl opProUnit) throws CoException;

	/**
	 * 查询实体列表
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public List<OpProUnitImpl> select(OpProUnitImpl opProUnit) throws CoException;

	/**
	 * 取得单一对象
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public OpProUnitImpl get(OpProUnitImpl opProUnit) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpProUnitImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opProUnit
	 * @return
	 * @throws CoException
	 */
	public List<OpProUnitImpl> selectTree(OpProUnitImpl opProUnit) throws CoException;

}