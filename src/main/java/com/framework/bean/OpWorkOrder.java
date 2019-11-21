package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.util.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 工单管理
 * 作者:    Neo Yin
 * 日期:    2019/9/24
 **********************************************
 */
public class OpWorkOrder extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 工单名称
	private String name;
	// 机组编号
	private Long idOpProUnit;
	// 机组
	private String nameOpProUnit;
	// 工长编号
	private Long idManager;
	// 工长
	private String nameManager;
	// 带班人编号
	private Long idLeader;
	// 带班人
	private String nameLeader;
	// 点检员编号
	private Long idChecker;
	// 点检员(派发人)
	private String nameChecker;
	// 计划时间(小时)
	private Double dateExpect;
	// 计划工时(小时)
	private Double hoursExpect;
	// 实际工时(小时)
	private Double hoursActual;
	// 操作牌申请数量
	private Integer countOpCardApply;
	// 操作牌发放数量
	private Integer countOpCardSend;
	// 设备送停电数量
	private Integer countOpDevicePower;
	// 描述
	private String description;
	// 备注
	private String remark;
	// 工单生成时间
	private Date dateCreated;
	// 组队完成时间
	private Date dateGrouped;
	// 取牌完成时间
	private Date dateTaken;
	// 作业开始时间
	private Date datesStartup;
	// 作业完成时间
	private Date dateOptioned;
	// 还牌完成时间
	private Date dateReturned;
	// 录入完成时间
	private Date dateInputted;
	// 状态
	private String status;
	// 完成状态(co:completed 已完成, un:uncompleted 未完成)
	private String statusComplete;
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
	public Long getIdManager() {
		return idManager;
	}

	public void setIdManager(Long idManager) {
		this.idManager = idManager;
	}
	public String getNameManager() {
		return nameManager;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
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
	public Double getHoursActual() {
		return hoursActual;
	}

	public void setHoursActual(Double hoursActual) {
		this.hoursActual = hoursActual;
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
	public Integer getCountOpDevicePower() {
		return countOpDevicePower;
	}

	public void setCountOpDevicePower(Integer countOpDevicePower) {
		this.countOpDevicePower = countOpDevicePower;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateGrouped() {
		return dateGrouped;
	}

	public void setDateGrouped(Date dateGrouped) {
		this.dateGrouped = dateGrouped;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDatesStartup() {
		return datesStartup;
	}

	public void setDatesStartup(Date datesStartup) {
		this.datesStartup = datesStartup;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateOptioned() {
		return dateOptioned;
	}

	public void setDateOptioned(Date dateOptioned) {
		this.dateOptioned = dateOptioned;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDateInputted() {
		return dateInputted;
	}

	public void setDateInputted(Date dateInputted) {
		this.dateInputted = dateInputted;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusComplete() {
		return statusComplete;
	}

	public void setStatusComplete(String statusComplete) {
		this.statusComplete = statusComplete;
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