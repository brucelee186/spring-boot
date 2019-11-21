package com.framework.bean.impl;

import com.framework.bean.MaFingerprint;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/9/19
 **********************************************
 */
public class MaFingerprintImpl extends MaFingerprint {
	private static final long serialVersionUID = 1L;
	
	private String indexMaUser;
	
	private Integer indexMaFingerprint;
	
	private Long idOpDeviceCabinet;
	
	private Long idOpWorkOrder;
	
	private String nameOpWorkOrder;
	
	private String nameMaUser;

	public String getNameMaUser() {
		return nameMaUser;
	}

	public void setNameMaUser(String nameMaUser) {
		this.nameMaUser = nameMaUser;
	}

	public Integer getIndexMaFingerprint() {
		return indexMaFingerprint;
	}

	public void setIndexMaFingerprint(Integer indexMaFingerprint) {
		this.indexMaFingerprint = indexMaFingerprint;
	}

	public Long getIdOpDeviceCabinet() {
		return idOpDeviceCabinet;
	}

	public void setIdOpDeviceCabinet(Long idOpDeviceCabinet) {
		this.idOpDeviceCabinet = idOpDeviceCabinet;
	}

	public String getIndexMaUser() {
		return indexMaUser;
	}

	public void setIndexMaUser(String indexMaUser) {
		this.indexMaUser = indexMaUser;
	}

	public Long getIdOpWorkOrder() {
		return idOpWorkOrder;
	}

	public void setIdOpWorkOrder(Long idOpWorkOrder) {
		this.idOpWorkOrder = idOpWorkOrder;
	}

	public String getNameOpWorkOrder() {
		return nameOpWorkOrder;
	}

	public void setNameOpWorkOrder(String nameOpWorkOrder) {
		this.nameOpWorkOrder = nameOpWorkOrder;
	}
	
}