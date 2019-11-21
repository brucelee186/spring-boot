package com.framework.bean.impl;

import java.util.List;

import com.framework.bean.MaCompany;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 公司
 * 作者:    Auto
 * 日期:    2019/7/24
 **********************************************
 */
public class MaCompanyImpl extends MaCompany {
	private static final long serialVersionUID = 1L;
	
	private MaOrgnizationLevelImpl maOrgnizationLevel;
	
	private List<MaOrgnizationLevelImpl> listMaOrgnizationLevel;

	public List<MaOrgnizationLevelImpl> getListMaOrgnizationLevel() {
		return listMaOrgnizationLevel;
	}

	public void setListMaOrgnizationLevel(List<MaOrgnizationLevelImpl> listMaOrgnizationLevel) {
		this.listMaOrgnizationLevel = listMaOrgnizationLevel;
	}

	public MaOrgnizationLevelImpl getMaOrgnizationLevel() {
		return maOrgnizationLevel;
	}

	public void setMaOrgnizationLevel(MaOrgnizationLevelImpl maOrgnizationLevel) {
		this.maOrgnizationLevel = maOrgnizationLevel;
	}
	
	
}