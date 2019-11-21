package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpDeviceImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 卡牌管理
 * 作者:    Auto
 * 日期:    2019/8/15
 **********************************************
 */
public interface OpDeviceMapper extends BaseMapper {

	public List<OpDeviceImpl> getOpWorkOderToDevice(OpDeviceImpl opDevice);

}