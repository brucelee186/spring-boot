package com.framework.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.bean.impl.MaRoleImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 公司
 * 作者:     Auto
 * 日期:     2019/7/24
**********************************************
*/
public interface MaCompanyService {
	/**
	 * 新增实体对象
	 * @param maCompany
	 * @return
	 * @throws CoException
	 */
	public MaCompanyImpl insert(MaCompanyImpl maCompany) throws CoException;

	/**
	 * 删除实体对象
	 * @param maCompany
	 * @return
	 * @throws CoException
	 */
	public int delete(MaCompanyImpl maCompany) throws CoException;

	/**
	 * 更新实体对象
	 * @param maCompany
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaCompanyImpl maCompany) throws CoException;

	/**
	 * 查询实体列表
	 * @param maCompany
	 * @return
	 * @throws CoException
	 */
	public List<MaCompanyImpl> select(MaCompanyImpl maCompany) throws CoException;

	/**
	 * 取得单一对象
	 * @param maCompany
	 * @return
	 * @throws CoException
	 */
	public MaCompanyImpl get(MaCompanyImpl maCompany) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaCompanyImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;
	
	
	
	/**
	 * 查询节点
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findTree(String pid);
	
	/**
	 * 查询树型实体
	 * @param maRole
	 * @return
	 * @throws CoException
	 */
	public List<MaCompanyImpl> selectTree(MaCompanyImpl maCompany) throws CoException;	

}