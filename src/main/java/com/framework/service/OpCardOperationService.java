package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpCardOperationImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 操作牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
public interface OpCardOperationService {
	/**
	 * 新增实体对象
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public OpCardOperationImpl insert(OpCardOperationImpl opCardOperation) throws CoException;

	/**
	 * 删除实体对象
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public int delete(OpCardOperationImpl opCardOperation) throws CoException;

	/**
	 * 更新实体对象
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpCardOperationImpl opCardOperation) throws CoException;

	/**
	 * 查询实体列表
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public List<OpCardOperationImpl> select(OpCardOperationImpl opCardOperation) throws CoException;

	/**
	 * 取得单一对象
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public OpCardOperationImpl get(OpCardOperationImpl opCardOperation) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpCardOperationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opCardOperation
	 * @return
	 * @throws CoException
	 */
	public List<OpCardOperationImpl> selectTree(OpCardOperationImpl opCardOperation) throws CoException;

}