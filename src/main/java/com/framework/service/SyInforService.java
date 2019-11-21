package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.SyInforImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 系统信息
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
public interface SyInforService {
	/**
	 * 新增实体对象
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public SyInforImpl insert(SyInforImpl syInfor) throws CoException;

	/**
	 * 删除实体对象
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public int delete(SyInforImpl syInfor) throws CoException;

	/**
	 * 更新实体对象
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyInforImpl syInfor) throws CoException;

	/**
	 * 查询实体列表
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public List<SyInforImpl> select(SyInforImpl syInfor) throws CoException;

	/**
	 * 取得单一对象
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public SyInforImpl get(SyInforImpl syInfor) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyInforImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syInfor
	 * @return
	 * @throws CoException
	 */
	public List<SyInforImpl> selectTree(SyInforImpl syInfor) throws CoException;

}