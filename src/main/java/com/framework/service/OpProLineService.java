﻿package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpProLineImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 生产线
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
public interface OpProLineService {
	/**
	 * 新增实体对象
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public OpProLineImpl insert(OpProLineImpl opProLine) throws CoException;

	/**
	 * 删除实体对象
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public int delete(OpProLineImpl opProLine) throws CoException;

	/**
	 * 更新实体对象
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpProLineImpl opProLine) throws CoException;

	/**
	 * 查询实体列表
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public List<OpProLineImpl> select(OpProLineImpl opProLine) throws CoException;

	/**
	 * 取得单一对象
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public OpProLineImpl get(OpProLineImpl opProLine) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpProLineImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opProLine
	 * @return
	 * @throws CoException
	 */
	public List<OpProLineImpl> selectTree(OpProLineImpl opProLine) throws CoException;

}