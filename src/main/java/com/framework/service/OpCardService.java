package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpCardImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 卡牌管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
public interface OpCardService {
	/**
	 * 新增实体对象
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public OpCardImpl insert(OpCardImpl opCard) throws CoException;

	/**
	 * 删除实体对象
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public int delete(OpCardImpl opCard) throws CoException;

	/**
	 * 更新实体对象
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpCardImpl opCard) throws CoException;

	/**
	 * 查询实体列表
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public List<OpCardImpl> select(OpCardImpl opCard) throws CoException;

	/**
	 * 取得单一对象
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public OpCardImpl get(OpCardImpl opCard) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opCard
	 * @return
	 * @throws CoException
	 */
	public List<OpCardImpl> selectTree(OpCardImpl opCard) throws CoException;

}