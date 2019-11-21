package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpCardWorkImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 工作牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
public interface OpCardWorkService {
	/**
	 * 新增实体对象
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public OpCardWorkImpl insert(OpCardWorkImpl opCardWork) throws CoException;

	/**
	 * 删除实体对象
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public int delete(OpCardWorkImpl opCardWork) throws CoException;

	/**
	 * 更新实体对象
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpCardWorkImpl opCardWork) throws CoException;

	/**
	 * 查询实体列表
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public List<OpCardWorkImpl> select(OpCardWorkImpl opCardWork) throws CoException;

	/**
	 * 取得单一对象
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public OpCardWorkImpl get(OpCardWorkImpl opCardWork) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpCardWorkImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opCardWork
	 * @return
	 * @throws CoException
	 */
	public List<OpCardWorkImpl> selectTree(OpCardWorkImpl opCardWork) throws CoException;

}