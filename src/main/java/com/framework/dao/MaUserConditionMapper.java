package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.MaUserImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/8/9
 **********************************************
 */
public interface MaUserConditionMapper extends BaseMapper {
	List<MaUserImpl> selectPreTree(MaUserImpl mauser);
	
	List<MaUserImpl> selectOpDeviceGroupUser(MaUserImpl mauser);
	
	List<MaUserImpl> selectMainWorkOrderLeader(MaUserImpl mauser);
}