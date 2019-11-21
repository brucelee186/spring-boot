package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 工单组队员工
 * 作者:    Auto
 * 日期:    2019/8/21
 **********************************************
 */
public interface OpWorkOrderEmployeeConditionMapper extends BaseMapper {
	/**
	 *  查询工单详细工时
	 * @param requestParams
	 * @return
	 */
	List<OpWorkOrderEmployeeImpl> selectEmployeeWorkTimeDetail(OpWorkOrderEmployeeImpl opWorkOrderEmployee);

}