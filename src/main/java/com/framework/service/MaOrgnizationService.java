package com.framework.service;

import java.util.List;
import java.util.Map;

import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.exception.CoException;
import com.github.pagehelper.PageInfo;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 组织
 * 作者:     Auto
 * 日期:     2019/7/24
**********************************************
*/
public interface MaOrgnizationService {
	/**
	 * 新增实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationImpl insert(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 删除实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public int delete(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 更新实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 查询实体列表
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> select(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 取得单一对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationImpl get(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaOrgnizationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;
	
	
	/**
	 * 查询节点
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findTree(String pid);
	
	/**
	 * 查询节点
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findTree(MaOrgnizationImpl maOrgnization);
	
	/**
	 * 查询节点
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findOrgTree(String pid);
	
	/**
	 * 查询树型实体
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> selectTree(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 查询车间 工段节点
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> selectOrgTree(MaOrgnizationImpl maOrgnization) throws CoException;

	/**
	 * 查询带班人  查检员 电工
	 * @param form
	 * @param session
	 * @return
	 */
	public List<MaOrgnizationImpl> selectElectricianTree(MaOrgnizationImpl maOrgnization) throws CoException;
	

}