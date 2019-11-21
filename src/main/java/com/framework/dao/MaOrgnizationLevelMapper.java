package com.framework.dao;

import com.framework.dao.common.CommonMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaOrgnizationLevelImpl;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 组织层级
 * 作者:    Auto
 * 日期:    2019/10/11
 **********************************************
 */
public interface MaOrgnizationLevelMapper extends CommonMapper {

	/**
	 * 新增实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public Long insert(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 删除实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public int delete(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 更新实体对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public int update(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 查询实体列表
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationLevelImpl> select(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 查询实体对象记录数
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public int count(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 取得单一对象
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public MaOrgnizationLevelImpl get(MaOrgnizationLevelImpl maOrgnizationLevel);

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationLevelImpl> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

	/**
	 * 查询树型实体
	 * @param maOrgnizationLevel
	 * @return
	 * @throws CoException
	 */
	public List<MaOrgnizationLevelImpl> selectTree(MaOrgnizationLevelImpl maOrgnizationLevel);

}