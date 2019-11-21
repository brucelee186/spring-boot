package com.framework.bean;

import java.io.Serializable;
import java.util.Date;
import com.framework.bean.common.BaseBean;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 设备开关
 * 作者:    Neo Yin
 * 日期:    2019/9/20
 **********************************************
 */
public class OpDevice extends BaseBean implements Serializable {
private static final long serialVersionUID = 1L;
	// 编号
	private Long id;
	// 采番编号
	private String uid;
	// 公司
	private Long idMaCompany;
	// 设备名称
	private String name;
	// 设备编号
	private String code;
	// 操作室
	private Long idOpRoomOperation;
	// 主电室
	private Long idOpRoomElectric;
	// 开关柜
	private Long idOpDeviceCabinet;
	// 操作牌
	private Long idOpCardOperation;
	// 停电牌
	private Long idOpCardPower;
	// 工作牌
	private Long idOpCardWork;
	// 主操作牌
	private Long midOpWorkOrderCard;
	// 主工单
	private Long midOpWorkOrder;
	// 主操作牌数量
	private Integer countMainOpCard;
	// 副操作牌数量
	private Integer countSubOpCard;
	// 操作牌总数量
	private Integer countOpCard;
	// 操作室状态
	private String statusOpRoomOperation;
	// 主电室状态
	private String statusOpRoomElectric;
	// 电力状态
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
	public Long getIdOpRoomOperation() {
		return idOpRoomOperation;
	}

	public void setIdOpRoomOperation(Long idOpRoomOperation) {
		this.idOpRoomOperation = idOpRoomOperation;
	}
	public Long getIdOpRoomElectric() {
		return idOpRoomElectric;
	}

	public void setIdOpRoomElectric(Long idOpRoomElectric) {
		this.idOpRoomElectric = idOpRoomElectric;
	}
	public Long getIdOpDeviceCabinet() {
		return idOpDeviceCabinet;
	}

	public void setIdOpDeviceCabinet(Long idOpDeviceCabinet) {
		this.idOpDeviceCabinet = idOpDeviceCabinet;
	}
	public Long getIdOpCardOperation() {
		return idOpCardOperation;
	}

	public void setIdOpCardOperation(Long idOpCardOperation) {
		this.idOpCardOperation = idOpCardOperation;
	}
	public Long getIdOpCardPower() {
		return idOpCardPower;
	}

	public void setIdOpCardPower(Long idOpCardPower) {
		this.idOpCardPower = idOpCardPower;
	}
	public Long getIdOpCardWork() {
		return idOpCardWork;
	}

	public void setIdOpCardWork(Long idOpCardWork) {
		this.idOpCardWork = idOpCardWork;
	}
	public Long getMidOpWorkOrderCard() {
		return midOpWorkOrderCard;
	}

	public void setMidOpWorkOrderCard(Long midOpWorkOrderCard) {
		this.midOpWorkOrderCard = midOpWorkOrderCard;
	}
	public Long getMidOpWorkOrder() {
		return midOpWorkOrder;
	}

	public void setMidOpWorkOrder(Long midOpWorkOrder) {
		this.midOpWorkOrder = midOpWorkOrder;
	}
	public Integer getCountMainOpCard() {
		return countMainOpCard;
	}

	public void setCountMainOpCard(Integer countMainOpCard) {
		this.countMainOpCard = countMainOpCard;
	}
	public Integer getCountSubOpCard() {
		return countSubOpCard;
	}

	public void setCountSubOpCard(Integer countSubOpCard) {
		this.countSubOpCard = countSubOpCard;
	}
	public Integer getCountOpCard() {
		return countOpCard;
	}

	public void setCountOpCard(Integer countOpCard) {
		this.countOpCard = countOpCard;
	}
	public String getStatusOpRoomOperation() {
		return statusOpRoomOperation;
	}

	public void setStatusOpRoomOperation(String statusOpRoomOperation) {
		this.statusOpRoomOperation = statusOpRoomOperation;
	}
	public String getStatusOpRoomElectric() {
		return statusOpRoomElectric;
	}

	public void setStatusOpRoomElectric(String statusOpRoomElectric) {
		this.statusOpRoomElectric = statusOpRoomElectric;
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