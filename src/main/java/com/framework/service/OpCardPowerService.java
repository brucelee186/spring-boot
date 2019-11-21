package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpCardPowerImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 停电牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
public interface OpCardPowerService {
	/**
	 * 新增实体对象
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public OpCardPowerImpl insert(OpCardPowerImpl opCardPower) throws CoException;

	/**
	 * 删除实体对象
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public int delete(OpCardPowerImpl opCardPower) throws CoException;

	/**
	 * 更新实体对象
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpCardPowerImpl opCardPower) throws CoException;

	/**
	 * 查询实体列表
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public List<OpCardPowerImpl> select(OpCardPowerImpl opCardPower) throws CoException;

	/**
	 * 取得单一对象
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public OpCardPowerImpl get(OpCardPowerImpl opCardPower) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpCardPowerImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opCardPower
	 * @return
	 * @throws CoException
	 */
	public List<OpCardPowerImpl> selectTree(OpCardPowerImpl opCardPower) throws CoException;

}