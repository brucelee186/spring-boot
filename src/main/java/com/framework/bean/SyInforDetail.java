﻿package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统信息明细
 * 作者:    Neo Yin
 * 日期:    2019/8/19
 **********************************************
 */
public class SyInforDetail extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 父科目
	private Long idSyInfor;
	// 父编码
	private String codeSyInfor;
	// 科目名称
	private String name;
	// 编码
	private String code;
	// 值
	private String value;
	// 类型(主记录:m,明细记录:d)
	private String flag;
	// 表
	private String statusTable;
	// 列
	private String statusColumn;
	// 排序编号
	private Integer orderIndex;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 记录标识(d:删除, h:隐藏, n:正常)
	private String tag;
	// 更新人编号
	private String createUser;
	// 更新时间
	private Date createDate;
	// IP地址
	private String createIp;
	// 修改人编号
	private String modifiedUser;
	// 修改时间
	private Date modifiedDate;
	// IP地址
	private String modifiedIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public Long getIdMaCompany() {
		return idMaCompany;
	}

	public void setIdMaCompany(Long idMaCompany) {
		this.idMaCompany = idMaCompany;
	}
	public Long getIdSyInfor() {
		return idSyInfor;
	}

	public void setIdSyInfor(Long idSyInfor) {
		this.idSyInfor = idSyInfor;
	}
	public String getCodeSyInfor() {
		return codeSyInfor;
	}

	public void setCodeSyInfor(String codeSyInfor) {
		this.codeSyInfor = codeSyInfor;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStatusTable() {
		return statusTable;
	}

	public void setStatusTable(String statusTable) {
		this.statusTable = statusTable;
	}
	public String getStatusColumn() {
		return statusColumn;
	}

	public void setStatusColumn(String statusColumn) {
		this.statusColumn = statusColumn;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedIp() {
		return modifiedIp;
	}

	public void setModifiedIp(String modifiedIp) {
		this.modifiedIp = modifiedIp;
	}
}