package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.SyStatusDetailImpl;
import com.framework.dao.common.BaseMapper;
import com.framework.exception.CoException;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 系统状态明细
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public interface SyStatusDetailConditionMapper extends BaseMapper {
	
	/**
	 * 查询树型实体
	 * @param syStatusDetail
	 * @return
	 * @throws CoException
	 */
	public List<SyStatusDetailImpl> selectTreeValue(SyStatusDetailImpl syStatusDetail);

}