package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaUserImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 用户管理
 * 作者:     Auto
 * 日期:     2019/8/9
**********************************************
*/
public interface MaUserService {
	/**
	 * 新增实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public MaUserImpl insert(MaUserImpl maUser) throws CoException;

	/**
	 * 删除实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public int delete(MaUserImpl maUser) throws CoException;

	/**
	 * 更新实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaUserImpl maUser) throws CoException;

	/**
	 * 查询实体列表
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> select(MaUserImpl maUser) throws CoException;

	/**
	 * 取得单一对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public MaUserImpl get(MaUserImpl maUser) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaUserImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;
	
	/**
	 * 查询树型实体
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> selectTree(MaUserImpl maUser) throws CoException;

	/**
	 * 查询点检员 带班人
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> selectPreTree(MaUserImpl maUser) throws CoException;

}