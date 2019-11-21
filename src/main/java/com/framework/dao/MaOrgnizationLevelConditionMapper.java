package com.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 组织
 * 作者:    Auto
 * 日期:    2019/7/30
 **********************************************
 */
public interface MaOrgnizationLevelConditionMapper extends BaseMapper {
	
	List<Map<String,String>> findNameId(@Param("nameColumn") String nameColumn, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

}