package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 组织
 * 作者:    Auto
 * 日期:    2019/7/24
 **********************************************
 */
public interface MaOrgnizationConditionMapper extends BaseMapper {
	
	List<Map<String, Object>> findListByPid(String object);
	
	List<Map<String, Object>> findTree(MaOrgnizationImpl maOrgnization);
	
	List<Map<String, Object>> findOrgTree(Object object);

	List<MaOrgnizationImpl> selectOrgTree(MaOrgnizationImpl maOrgnization);
	
	/**
	 * 查询带班人  查检员 电工
	 * @param form
	 * @param session
	 * @return
	 */
	List<MaOrgnizationImpl> selectElectricianTree(MaOrgnizationImpl maOrgnization);
	
}