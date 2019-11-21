package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.util.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 用户管理
 * 作者:    Neo Yin
 * 日期:    2019/10/8
 **********************************************
 */
public class MaUser extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 登录名
	private String loginName;
	// 密码
	private String password;
	// 用户名
	private String name;
	// 主角色
	private Long idMaRole;
	// 主角色名称
	private String nameMaRole;
	// 主角色编码
	private String codeMaRole;
	// 公司
	private Long idMaCompany;
	// 公司名称
	private String nameMaCompany;
	// 公司编号
	private String uidMaCompany;
	// 组织
	private Long idMaOrgnization;
	// 组织名称
	private String nameMaOrgnization;
	// 手机
	private String cellphone;
	// 头像
	private String profilePhoto;
	// 工号
	private String badgeNumber;
	// 邮箱地址
	private String email;
	// 性别
	private String gender;
	// 指纹索引(服务器端)
	private Integer indexMaFingerprint;
	// 指纹编码(服务器端)
	private String codeMaFingerprint;
	// 指纹(手机端)
	private String fingerPrint;
	// 备注
	private String remark;
	// 状态标识(e:enable 有效, d:disable 无效,d:drift 草稿,s:submit 提交,a:approving 审批,r:驳回,c:complete 完成)
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
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getIdMaRole() {
		return idMaRole;
	}

	public void setIdMaRole(Long idMaRole) {
		this.idMaRole = idMaRole;
	}
	public String getNameMaRole() {
		return nameMaRole;
	}

	public void setNameMaRole(String nameMaRole) {
		this.nameMaRole = nameMaRole;
	}
	public String getCodeMaRole() {
		return codeMaRole;
	}

	public void setCodeMaRole(String codeMaRole) {
		this.codeMaRole = codeMaRole;
	}
	public Long getIdMaCompany() {
		return idMaCompany;
	}

	public void setIdMaCompany(Long idMaCompany) {
		this.idMaCompany = idMaCompany;
	}
	public String getNameMaCompany() {
		return nameMaCompany;
	}

	public void setNameMaCompany(String nameMaCompany) {
		this.nameMaCompany = nameMaCompany;
	}
	public String getUidMaCompany() {
		return uidMaCompany;
	}

	public void setUidMaCompany(String uidMaCompany) {
		this.uidMaCompany = uidMaCompany;
	}
	public Long getIdMaOrgnization() {
		return idMaOrgnization;
	}

	public void setIdMaOrgnization(Long idMaOrgnization) {
		this.idMaOrgnization = idMaOrgnization;
	}
	public String getNameMaOrgnization() {
		return nameMaOrgnization;
	}

	public void setNameMaOrgnization(String nameMaOrgnization) {
		this.nameMaOrgnization = nameMaOrgnization;
	}
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getIndexMaFingerprint() {
		return indexMaFingerprint;
	}

	public void setIndexMaFingerprint(Integer indexMaFingerprint) {
		this.indexMaFingerprint = indexMaFingerprint;
	}
	public String getCodeMaFingerprint() {
		return codeMaFingerprint;
	}

	public void setCodeMaFingerprint(String codeMaFingerprint) {
		this.codeMaFingerprint = codeMaFingerprint;
	}
	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
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
	@JsonSerialize(using = JsonDateTimeSerializer.class)
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
	@JsonSerialize(using = JsonDateTimeSerializer.class)
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