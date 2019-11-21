package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 设备电工牌
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public interface OpDeviceElectricianCardConditionMapper extends BaseMapper {
	List<OpDeviceElectricianCardImpl> getStopOrSendPower(OpDeviceElectricianCardImpl opDeviceElectricianCard);
}