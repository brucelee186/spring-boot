package com.framework.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.framework.bean.impl.MaCompanyImpl;

public interface BaseMapper {
	
	Long insert(Object object);
	int delete(Object object);
	int update(Object object);
	List<?> select(Object object);
	List<?> selectTree(Object object);
	int count(Object object);
	Object get(Object object);
	Object handleByQuery(Object object);
	List<?> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);	
	
}
