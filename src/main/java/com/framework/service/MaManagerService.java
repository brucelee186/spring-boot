package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaManagerImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 管理员
 * 作者:     Auto
 * 日期:     2019/7/19
**********************************************
*/
public interface MaManagerService {
	/**
	 * 新增实体对象
	 * @param maManager
	 * @return
	 * @throws CoException
	 */
	public MaManagerImpl insert(MaManagerImpl maManager) throws CoException;

	/**
	 * 删除实体对象
	 * @param maManager
	 * @return
	 * @throws CoException
	 */
	public int delete(MaManagerImpl maManager) throws CoException;

	/**
	 * 更新实体对象
	 * @param maManager
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaManagerImpl maManager) throws CoException;

	/**
	 * 查询实体列表
	 * @param maManager
	 * @return
	 * @throws CoException
	 */
	public List<MaManagerImpl> select(MaManagerImpl maManager) throws CoException;

	/**
	 * 取得单一对象
	 * @param maManager
	 * @return
	 * @throws CoException
	 */
	public MaManagerImpl get(MaManagerImpl maManager) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaManagerImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

}