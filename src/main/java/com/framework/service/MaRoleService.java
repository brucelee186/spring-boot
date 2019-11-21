package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaRoleImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 角色管理
 * 作者:     Auto
 * 日期:     2019/8/12
**********************************************
*/
public interface MaRoleService {
	/**
	 * 新增实体对象
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public MaRoleImpl insert(MaRoleImpl maRole) throws CoException;

	/**
	 * 删除实体对象
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public int delete(MaRoleImpl maRole) throws CoException;

	/**
	 * 更新实体对象
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaRoleImpl maRole) throws CoException;

	/**
	 * 查询实体列表
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public List<MaRoleImpl> select(MaRoleImpl maRole) throws CoException;

	/**
	 * 取得单一对象
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public MaRoleImpl get(MaRoleImpl maRole) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaRoleImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public List<MaRoleImpl> selectTree(MaRoleImpl maRole) throws CoException;

}