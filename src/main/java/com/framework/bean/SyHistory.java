package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统状态明细
 * 作者:    Neo Yin
 * 日期:    2019/8/20
 **********************************************
 */
public class SyHistory extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 工单号
	private Long idOpWorkOrder;
	// 用户编号
	private Long idMaUser;
	// 用户编号
	private String uidMaUser;
	// 用户名
	private String nameMaUser;
	// 角色
	private String codeMaRole;
	// 角色名称
	private String nameMaRole;
	// 操作
	private String atcion;
	// 系统日志
	private String log;
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
	public Long getIdOpWorkOrder() {
		return idOpWorkOrder;
	}

	public void setIdOpWorkOrder(Long idOpWorkOrder) {
		this.idOpWorkOrder = idOpWorkOrder;
	}
	public Long getIdMaUser() {
		return idMaUser;
	}

	public void setIdMaUser(Long idMaUser) {
		this.idMaUser = idMaUser;
	}
	public String getUidMaUser() {
		return uidMaUser;
	}

	public void setUidMaUser(String uidMaUser) {
		this.uidMaUser = uidMaUser;
	}
	public String getNameMaUser() {
		return nameMaUser;
	}

	public void setNameMaUser(String nameMaUser) {
		this.nameMaUser = nameMaUser;
	}
	public String getCodeMaRole() {
		return codeMaRole;
	}

	public void setCodeMaRole(String codeMaRole) {
		this.codeMaRole = codeMaRole;
	}
	public String getNameMaRole() {
		return nameMaRole;
	}

	public void setNameMaRole(String nameMaRole) {
		this.nameMaRole = nameMaRole;
	}
	public String getAtcion() {
		return atcion;
	}

	public void setAtcion(String atcion) {
		this.atcion = atcion;
	}
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
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