package com.framework.service.common;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaCompanyImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 公司
 * 作者:     Auto
 * 日期:     2019/7/24
**********************************************
*/
public interface BaseService {

	/**
	 * 查询节点
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findTree(String pid);

}