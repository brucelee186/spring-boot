package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 公司
 * 作者:    Auto
 * 日期:    2019/7/24
 **********************************************
 */
public interface MaCompanyConditionMapper extends BaseMapper {
	
	List<Map<String, Object>> findListByPid(String object);

}