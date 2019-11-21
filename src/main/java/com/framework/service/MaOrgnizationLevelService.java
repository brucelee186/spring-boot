package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.alibaba.fastjson.JSONArray;
import com.framework.bean.impl.MaOrgnizationLevelImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 组织
 * 作者:     Auto
 * 日期:     2019/7/30
**********************************************
*/
public interface MaOrgnizationLevelService {
	/**
	 * 新增实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationLevelImpl insert(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;

	/**
	 * 删除实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public int delete(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;

	/**
	 * 更新实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;

	/**
	 * 查询实体列表
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationLevelImpl> select(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;

	/**
	 * 取得单一对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationLevelImpl get(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaOrgnizationLevelImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;
	
	/**
	 * 取得Select
	 * @param nameColumn
	 * @param sqlWhere
	 * @param orderBy
	 * @return
	 */
	public JSONArray findNameId(String nameColumn, String sqlWhere, String orderBy);
	
	/**
	 * 查询树型实体
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationLevelImpl> selectTree(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException;	


}