﻿package com.framework.dao;

import com.framework.dao.common.CommonMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaOrgnizationImpl;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 组织管理
 * 作者:    Auto
 * 日期:    2019/10/11
 **********************************************
 */
public interface MaOrgnizationMapper extends CommonMapper {

	/**
	 * 新增实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public Long insert(MaOrgnizationImpl maOrgnization);

	/**
	 * 删除实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public int delete(MaOrgnizationImpl maOrgnization);

	/**
	 * 更新实体对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public int update(MaOrgnizationImpl maOrgnization);

	/**
	 * 查询实体列表
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> select(MaOrgnizationImpl maOrgnization);

	/**
	 * 查询实体对象记录数
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public int count(MaOrgnizationImpl maOrgnization);

	/**
	 * 取得单一对象
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationImpl get(MaOrgnizationImpl maOrgnization);

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

	/**
	 * 查询树型实体
	 * @param maOrgnization
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationImpl> selectTree(MaOrgnizationImpl maOrgnization);

}