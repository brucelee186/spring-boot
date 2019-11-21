package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.MaFingerprintImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/9/19
 **********************************************
 */
public interface MaFingerprintConditionMapper extends BaseMapper {
	
	// 查询电器柜对应的指纹信息
	public List<MaFingerprintImpl> selectFingerprintByCabinet(MaFingerprintImpl maFingerprint);
}