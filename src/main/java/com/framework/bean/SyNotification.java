package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 提示推送
 * 作者:    Neo Yin
 * 日期:    2019/9/16
 **********************************************
 */
public class SyNotification extends BaseBean implements Serializable {
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
	// 标题
	private String title;
	// 提示内容
	private String content;
	// 警告内容
	private String alert;
	// 别名
	private String alias;
	// 标签组
	private String groups;
	// 指定用户
	private String registrationId;
	// 备注
	private String remark;
	// 状态(已经发送 s:sended,未发送 u:unsend,失败 f:failed,失效i:invalid)
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
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
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