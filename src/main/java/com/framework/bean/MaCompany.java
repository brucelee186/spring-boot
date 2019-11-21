package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 公司管理
 * 作者:    Neo Yin
 * 日期:    2019/8/12
 **********************************************
 */
public class MaCompany extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// ID
	private Long id;
	// 采番编号
	private String uid;
	// 登录编号
	private String idMaUser;
	// 公司名称
	private String name;
	// 电话
	private String tel;
	// 传真
	private String fax;
	// 电子邮件
	private String mail;
	// 公司主页
	private String webUrl;
	// 公司简介
	private String description;
	// 状态标识(e:enable 有效, d:disable 无效,d:drift 草稿,s:submit 提交,a:approving 审批,r:驳回,c:complete 完成)
	private String status;
	// 记录标识(d:删除, h:隐藏, n:正常)
	private String tag;
	// 备注
	private String remark;
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
	public String getIdMaUser() {
		return idMaUser;
	}

	public void setIdMaUser(String idMaUser) {
		this.idMaUser = idMaUser;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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