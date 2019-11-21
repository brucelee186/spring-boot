package com.framework.bean.impl;

import com.framework.bean.MaOrgnization;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 组织
 * 作者:    Auto
 * 日期:    2019/7/24
 **********************************************
 */
public class MaOrgnizationImpl extends MaOrgnization {
	private static final long serialVersionUID = 1L;
	
	private String nameMaCompany;
	
	private String namePareantOrg;
	
	private String nameMaOrgnizationLevel;
	
	//主电室发牌查询电工人员信息时使用
	private Long cjId;
	private String cjNm;
	private Long gdId;
	private String gdNm;
	private Long gzId;
	private String gzNm;
	private String profilePhoto;

	public String getNameMaOrgnizationLevel() {
		return nameMaOrgnizationLevel;
	}

	public void setNameMaOrgnizationLevel(String nameMaOrgnizationLevel) {
		this.nameMaOrgnizationLevel = nameMaOrgnizationLevel;
	}

	public String getNameMaCompany() {
		return nameMaCompany;
	}

	public void setNameMaCompany(String nameMaCompany) {
		this.nameMaCompany = nameMaCompany;
	}

	public String getNamePareantOrg() {
		return namePareantOrg;
	}

	public void setNamePareantOrg(String namePareantOrg) {
		this.namePareantOrg = namePareantOrg;
	}
	
	//主电室发牌查询电工人员信息时使用
	public Long getCjId() {
		return cjId;
	}

	public void setCjId(Long cjId) {
		this.cjId = cjId;
	}

	public String getCjNm() {
		return cjNm;
	}

	public void setCjNm(String cjNm) {
		this.cjNm = cjNm;
	}

	public Long getGdId() {
		return gdId;
	}

	public void setGdId(Long gdId) {
		this.gdId = gdId;
	}

	public String getGdNm() {
		return gdNm;
	}

	public void setGdNm(String gdNm) {
		this.gdNm = gdNm;
	}

	public Long getGzId() {
		return gzId;
	}

	public void setGzId(Long gzId) {
		this.gzId = gzId;
	}

	public String getGzNm() {
		return gzNm;
	}

	public void setGzNm(String gzNm) {
		this.gzNm = gzNm;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	//主电室发牌查询电工人员信息时使用
	
}