package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 卡牌管理
 * 作者:    Neo Yin
 * 日期:    2019/8/15
 **********************************************
 */
public class OpCard extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 工单
	private Long idOpWorkOrder;
	// 设备
	private Long idOpDevice;
	// 带班人
	private Long idLeader;
	// 带班人卡片状态
	private String statusLeader;
	// 操作室
	private String idOpRoomOperation;
	// 操作室卡片状态
	private String statusOpRoomOperation;
	// 主电室
	private Long idOpRoomElectric;
	// 主电室卡片状态
	private String statusRoomElectric;
	// 电工
	private Long idElectrician;
	// 电工卡片状态
	private String statusElectrician;
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
	public Long getIdOpDevice() {
		return idOpDevice;
	}

	public void setIdOpDevice(Long idOpDevice) {
		this.idOpDevice = idOpDevice;
	}
	public Long getIdLeader() {
		return idLeader;
	}

	public void setIdLeader(Long idLeader) {
		this.idLeader = idLeader;
	}
	public String getStatusLeader() {
		return statusLeader;
	}

	public void setStatusLeader(String statusLeader) {
		this.statusLeader = statusLeader;
	}
	public String getIdOpRoomOperation() {
		return idOpRoomOperation;
	}

	public void setIdOpRoomOperation(String idOpRoomOperation) {
		this.idOpRoomOperation = idOpRoomOperation;
	}
	public String getStatusOpRoomOperation() {
		return statusOpRoomOperation;
	}

	public void setStatusOpRoomOperation(String statusOpRoomOperation) {
		this.statusOpRoomOperation = statusOpRoomOperation;
	}
	public Long getIdOpRoomElectric() {
		return idOpRoomElectric;
	}

	public void setIdOpRoomElectric(Long idOpRoomElectric) {
		this.idOpRoomElectric = idOpRoomElectric;
	}
	public String getStatusRoomElectric() {
		return statusRoomElectric;
	}

	public void setStatusRoomElectric(String statusRoomElectric) {
		this.statusRoomElectric = statusRoomElectric;
	}
	public Long getIdElectrician() {
		return idElectrician;
	}

	public void setIdElectrician(Long idElectrician) {
		this.idElectrician = idElectrician;
	}
	public String getStatusElectrician() {
		return statusElectrician;
	}

	public void setStatusElectrician(String statusElectrician) {
		this.statusElectrician = statusElectrician;
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