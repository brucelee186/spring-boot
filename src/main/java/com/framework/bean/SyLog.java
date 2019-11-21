package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 系统日志
 * 作者:    Neo Yin
 * 日期:    2019/9/16
 **********************************************
 */
public class SyLog extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 操作人编号
	private String uidMaUser;
	// 操作人
	private String nameMaUser;
	// 角色
	private String codeMaRole;
	// 角色名称
	private String nameMaRole;
	// 操作人
	private String name;
	// 机组编号
	private Long idOpProUnit;
	// 机组名称
	private String nameOpProUnit;
	// 工单编号
	private Long idOpWorkOrder;
	// 工单名称
	private String nameOpWorkOrder;
	// 点检员(派发人)
	private Long idChecker;
	// 点检员姓名
	private String nameChecker;
	// 带班人
	private Long idLeader;
	// 带班人姓名
	private String nameLeader;
	// 计划时间(小时)
	private Double dateExpect;
	// 计划工时(小时)
	private Double hoursExpect;
	// 组队员工列表
	private String listOpWorkOrderEmployee;
	// 取牌开关设备列表
	private String listOpDevice;
	// 工时录入列表
	private String listOpWorkOrderEmployeeHours;
	// 工时总计
	private Double hoursTotal;
	// 工作牌申请数量
	private Integer countOpCardApply;
	// 工作牌发放数量
	private Integer countOpCardSend;
	// 描述
	private String description;
	// 工单生成时间
	private Date dateCreated;
	// 组队完成时间
	private Date dateGrouped;
	// 取牌完成时间
	private Date dateTaken;
	// 作业完成时间
	private Date dateOptioned;
	// 还牌完成时间
	private Date dateReturned;
	// 录入完成时间
	private Date dateInputted;
	// 操作
	private String atcion;
	// 系统日志
	private String log;
	// 备注
	private String remark;
	// 状态标识(e:enable 有效, d:disable 无效,d:drift 草稿,s:submit 提交,a:approving 审批,r:驳回,c:complete 完成)
	private String status;
	// 状态名称
	private String nameStatus;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getIdOpProUnit() {
		return idOpProUnit;
	}

	public void setIdOpProUnit(Long idOpProUnit) {
		this.idOpProUnit = idOpProUnit;
	}
	public String getNameOpProUnit() {
		return nameOpProUnit;
	}

	public void setNameOpProUnit(String nameOpProUnit) {
		this.nameOpProUnit = nameOpProUnit;
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
	public Long getIdChecker() {
		return idChecker;
	}

	public void setIdChecker(Long idChecker) {
		this.idChecker = idChecker;
	}
	public String getNameChecker() {
		return nameChecker;
	}

	public void setNameChecker(String nameChecker) {
		this.nameChecker = nameChecker;
	}
	public Long getIdLeader() {
		return idLeader;
	}

	public void setIdLeader(Long idLeader) {
		this.idLeader = idLeader;
	}
	public String getNameLeader() {
		return nameLeader;
	}

	public void setNameLeader(String nameLeader) {
		this.nameLeader = nameLeader;
	}
	public Double getDateExpect() {
		return dateExpect;
	}

	public void setDateExpect(Double dateExpect) {
		this.dateExpect = dateExpect;
	}
	public Double getHoursExpect() {
		return hoursExpect;
	}

	public void setHoursExpect(Double hoursExpect) {
		this.hoursExpect = hoursExpect;
	}
	public String getListOpWorkOrderEmployee() {
		return listOpWorkOrderEmployee;
	}

	public void setListOpWorkOrderEmployee(String listOpWorkOrderEmployee) {
		this.listOpWorkOrderEmployee = listOpWorkOrderEmployee;
	}
	public String getListOpDevice() {
		return listOpDevice;
	}

	public void setListOpDevice(String listOpDevice) {
		this.listOpDevice = listOpDevice;
	}
	public String getListOpWorkOrderEmployeeHours() {
		return listOpWorkOrderEmployeeHours;
	}

	public void setListOpWorkOrderEmployeeHours(String listOpWorkOrderEmployeeHours) {
		this.listOpWorkOrderEmployeeHours = listOpWorkOrderEmployeeHours;
	}
	public Double getHoursTotal() {
		return hoursTotal;
	}

	public void setHoursTotal(Double hoursTotal) {
		this.hoursTotal = hoursTotal;
	}
	public Integer getCountOpCardApply() {
		return countOpCardApply;
	}

	public void setCountOpCardApply(Integer countOpCardApply) {
		this.countOpCardApply = countOpCardApply;
	}
	public Integer getCountOpCardSend() {
		return countOpCardSend;
	}

	public void setCountOpCardSend(Integer countOpCardSend) {
		this.countOpCardSend = countOpCardSend;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateGrouped() {
		return dateGrouped;
	}

	public void setDateGrouped(Date dateGrouped) {
		this.dateGrouped = dateGrouped;
	}
	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}
	public Date getDateOptioned() {
		return dateOptioned;
	}

	public void setDateOptioned(Date dateOptioned) {
		this.dateOptioned = dateOptioned;
	}
	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	public Date getDateInputted() {
		return dateInputted;
	}

	public void setDateInputted(Date dateInputted) {
		this.dateInputted = dateInputted;
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
	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
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