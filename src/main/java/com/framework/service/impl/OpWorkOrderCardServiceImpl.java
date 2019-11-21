package com.framework.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.framework.exception.CoException;
import com.framework.dao.OpCardOperationMapper;
import com.framework.dao.OpCardPowerMapper;
import com.framework.dao.OpCardWorkMapper;
import com.framework.dao.OpDeviceConditionMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.dao.OpWorkOrderCardMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpCardOperationImpl;
import com.framework.bean.impl.OpCardPowerImpl;
import com.framework.bean.impl.OpCardWorkImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceService;
import com.framework.service.OpWorkOrderCardService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 工单牌
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
@Service("opWorkOrderCardService")
public class OpWorkOrderCardServiceImpl extends CommonServiceImpl implements OpWorkOrderCardService {
	@Autowired
	private OpWorkOrderCardMapper opWorkOrderCardMapper;

	@Autowired
	public OpWorkOrderCardMapper getOpWorkOrderCardMapper() {
		return opWorkOrderCardMapper;
	}

	@Autowired
	public void setOpWorkOrderCardMapper(OpWorkOrderCardMapper opWorkOrderCardMapper) {
		this.opWorkOrderCardMapper = opWorkOrderCardMapper;
	}
	
	//工单表
	@Autowired
	private OpWorkOrderMapper opWorkOrderMapper;
	
	public OpWorkOrderMapper getOpWorkOrderMapper() {
		return opWorkOrderMapper;
	}

	public void setOpWorkOrderMapper(OpWorkOrderMapper opWorkOrderMapper) {
		this.opWorkOrderMapper = opWorkOrderMapper;
	}

	//操作牌表
	@Autowired
	private OpCardOperationMapper opCardOperationMapper;
	
	public OpCardOperationMapper getOpCardOperationMapper() {
		return opCardOperationMapper;
	}

	public void setOpCardOperationMapper(OpCardOperationMapper opCardOperationMapper) {
		this.opCardOperationMapper = opCardOperationMapper;
	}

	//停电牌表
	@Autowired
	private OpCardPowerMapper opCardPowerMapper;
	
	public OpCardPowerMapper getOpCardPowerMapper() {
		return opCardPowerMapper;
	}

	public void setOpCardPowerMapper(OpCardPowerMapper opCardPowerMapper) {
		this.opCardPowerMapper = opCardPowerMapper;
	}
	
	//操作牌表
	@Autowired
	private OpCardWorkMapper opCardWorkMapper;
	
	public OpCardWorkMapper getOpCardWorkMapper() {
		return opCardWorkMapper;
	}

	public void setOpCardWorkMapper(OpCardWorkMapper opCardWorkMapper) {
		this.opCardWorkMapper = opCardWorkMapper;
	}

	//设备表
	@Autowired
	private OpDeviceMapper opDeviceMapper;
	
	public OpDeviceMapper getOpDeviceMapper() {
		return opDeviceMapper;
	}

	public void setOpDeviceMapper(OpDeviceMapper opDeviceMapper) {
		this.opDeviceMapper = opDeviceMapper;
	}

	@Autowired
	private OpDeviceConditionMapper opDeviceConditionMapper;
	
	public OpDeviceConditionMapper getOpDeviceConditionMapper() {
		return opDeviceConditionMapper;
	}

	public void setOpDeviceConditionMapper(OpDeviceConditionMapper opDeviceConditionMapper) {
		this.opDeviceConditionMapper = opDeviceConditionMapper;
	}

	/**
	 * 新增实体对象
	 * @param opWorkOrderCard
	 */
	public OpWorkOrderCardImpl insert(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		this.opWorkOrderCardMapper.insert(opWorkOrderCard);
		return opWorkOrderCard;
	}

	/**
	 * 删除实体对象
	 * @param opWorkOrderCard
	 */
	public int delete(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		return this.opWorkOrderCardMapper.delete(opWorkOrderCard);
	}

	/**
	 * 更新实体对象
	 * @param opWorkOrderCard
	 */
	public boolean update(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		boolean result = true;
		this.opWorkOrderCardMapper.update(opWorkOrderCard);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opWorkOrderCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderCardImpl> select(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		return (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard);
	}

	/**
	 * 查询单个实体
	 * @param opWorkOrderCard
	 */
	public OpWorkOrderCardImpl get(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		return (OpWorkOrderCardImpl) this.opWorkOrderCardMapper.get(opWorkOrderCard);
	}

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	 @Override
	 @SuppressWarnings("unchecked")
	public PageInfo<OpWorkOrderCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		sqlWhere += " GROUP BY id ";
		List list = opWorkOrderCardMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpWorkOrderCardImpl> page = new PageInfo<OpWorkOrderCardImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opWorkOrderCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderCardImpl> selectTree(OpWorkOrderCardImpl opWorkOrderCard) throws CoException {
		return (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.selectTree(opWorkOrderCard);
	}
	
	/**
	 * 新增工单牌信息
	 * @param requestJson
	 * @throws CoException
	 */
	public void editWorkOderCardInfo(JSONObject requestJson) throws CoException {
		//操作人
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
		//改变工单状态为：取牌
		Long idOpWorkOrder = requestJson.getJSONObject("userdata").getJSONObject("idOpWorkOrder").getLong("value");
		//设备数组
		JSONArray opDeviceArr = requestJson.getJSONObject("opDeviceArr").getJSONArray("value");
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		opWorkOrder.setId(idOpWorkOrder);
		opWorkOrder.setStatus("ai");
		opWorkOrder.setCountOpCardApply(opDeviceArr.size());
		this.setCommonField(opWorkOrder);
		this.opWorkOrderMapper.update(opWorkOrder);
		//更新设备数组中间表
		for(int i=0; i< opDeviceArr.size(); i++) {
			JSONObject item = opDeviceArr.getJSONObject(i);
			//设备主键
			Long idOpDevice = item.getLong("idOpDevice");
			//更新设备表状态
			OpDeviceImpl opDevice = new OpDeviceImpl();
			//判断是否为主牌
			Long midOpWorkOrderCard = 0L;
			Long obj_midOpWorkOrderCard = item.getLong("midOpWorkOrderCard");
			if(null != obj_midOpWorkOrderCard && !"undefined".equals(obj_midOpWorkOrderCard.toString())) {
				midOpWorkOrderCard = item.getLong("midOpWorkOrderCard");
				opDevice.setStatusOpRoomOperation("ws");
			}
			opDevice.setId(idOpDevice);
			opDevice.setModifiedUser(userId);
			this.setCommonField(opDevice);
			this.opDeviceMapper.update(opDevice);
			
			//根据设备主键查询操作牌信息
			OpCardOperationImpl opCardOperation = new OpCardOperationImpl();
			opCardOperation.setIdOpDevice(idOpDevice);
			opCardOperation = (OpCardOperationImpl) this.opCardOperationMapper.get(opCardOperation);
			
			//根据设备主键查询停电牌信息
			OpCardPowerImpl opCardPower = new OpCardPowerImpl();
			opCardPower.setIdOpDevice(idOpDevice);
			opCardPower = (OpCardPowerImpl) this.opCardPowerMapper.get(opCardPower);
			
			//根据设备主键查询工作牌信息
			OpCardWorkImpl opCardWork = new OpCardWorkImpl();
			opCardWork.setIdOpDevice(idOpDevice);
			opCardWork = (OpCardWorkImpl) this.opCardWorkMapper.get(opCardWork);
			
			//新增工单牌信息
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			opWorkOrderCard.setIdMaCompany(idMaCompany);
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			opWorkOrderCard.setIdOpDevice(idOpDevice);
			opWorkOrderCard.setIdOpCardOperation(opCardOperation.getId());
			opWorkOrderCard.setIdOpRoomElectric(opCardOperation.getIdOpRoomElectric());
			//opWorkOrderCard.setStatusOpCardOperation(opCardOperation.getStatus());
			opWorkOrderCard.setIdMaUserOpCardOperation(opCardOperation.getIdOpRoomOperation());
			opWorkOrderCard.setIdOpCardPower(opCardPower.getId());
			//opWorkOrderCard.setStatusOpCardPower(opCardPower.getStatus());
			opWorkOrderCard.setIdMaUserOpCardPower(opCardPower.getIdOpRoomOperation());
			opWorkOrderCard.setIdOpCardWork(opCardWork.getId());
			//opWorkOrderCard.setStatusOpCardWork(opCardWork.getStatus());
			opWorkOrderCard.setIdMaUserOpCardWork(opCardWork.getIdOpRoomElectric());
			opWorkOrderCard.setIdLeader(Long.getLong(userId));//带班人
			//opWorkOrderCard.setStatusLeader(statusLeader);//带班人卡片状态
			opWorkOrderCard.setIdOpRoomOperation(opCardOperation.getIdOpRoomOperation());//操作室
			//opWorkOrderCard.setStatusOpRoomOperation(statusOpRoomOperation);//操作室卡片状态
			//if(midOpWorkOrderCard == 0) {
				opWorkOrderCard.setStatus("or");//主牌
			/*} else {
				opWorkOrderCard.setStatus("d");//副牌
			}*/
			opWorkOrderCard.setStatusOpCardOperation("at");
			opWorkOrderCard.setCreateUser(userId);
			opWorkOrderCard.setModifiedUser(userId);
			opWorkOrderCard.setEditState("i");
			OpWorkOrderImpl opWorkOrderTemp = new OpWorkOrderImpl();
			opWorkOrderTemp.setId(idOpWorkOrder);
			opWorkOrderTemp = opWorkOrderMapper.get(opWorkOrderTemp);
			Long idLeader = opWorkOrderTemp.getIdLeader();
			opWorkOrderCard.setIdLeader(idLeader);
			this.setCommonField(opWorkOrderCard);
			this.opWorkOrderCardMapper.insert(opWorkOrderCard);
		}
	}
	
	/**
	 * 查询工单及设备信息（操作牌）
	 * @param requestJson
	 * @throws CoException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getWorkOderAndOpDeviceToOprationInfo(JSONObject requestJson) throws CoException {
		JSONObject json = new JSONObject();
		//工单
		String idOpWorkOrder = requestJson.getJSONObject("userdata").getJSONObject("idOpWorkOrder").getString("value");
		//查询工单信息
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		Long id = Long.valueOf(idOpWorkOrder);
		opWorkOrder.setId(id);
		List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) this.opWorkOrderMapper.select(opWorkOrder);
		//查询设备信息
		OpDeviceImpl opDevice = new OpDeviceImpl();
		Long idOpRoomOperation = requestJson.getLong("idOpRoomOperation");
		opDevice.setIdOpWorkOrder(id);
		opDevice.setIdOpRoomOperation(idOpRoomOperation);
		List<OpDeviceImpl> treeOpDevice = this.opDeviceConditionMapper.getOpWorkOderToOpration(opDevice);//操作室设备
		OpWorkOrderImpl data = treeOpWorkOrder.get(0);
		data.setOpDeviceArr(net.sf.json.JSONArray.fromObject(treeOpDevice));
		JSONArray arr = new JSONArray();
		arr.add(data);
		json.put("data", arr);
		
		//json.put("treeOpDevice", treeOpDevice);
		//json.put("treeOpWorkOrder", treeOpWorkOrder);
		return json;
	}
	/**
	 * 查询工单及设备信息（停电牌）
	 * @param requestJson
	 * @throws CoException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getWorkOderAndOpDeviceToStopInfo(JSONObject requestJson) throws CoException {
		JSONObject json = new JSONObject();
		//工单
		String idOpWorkOrder = requestJson.getJSONObject("userdata").getJSONObject("idOpWorkOrder").getString("value");
		//查询工单信息
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		Long id = Long.valueOf(idOpWorkOrder);
		opWorkOrder.setId(id);
		List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) this.opWorkOrderMapper.select(opWorkOrder);
		//查询设备信息
		OpDeviceImpl opDevice = new OpDeviceImpl();
		Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
		opDevice.setIdOpWorkOrder(id);
		opDevice.setIdOpRoomElectric(idOpRoomElectric);
		List<OpDeviceImpl> treeOpDevice = this.opDeviceConditionMapper.getOpWorkOderToDevice(opDevice);//主电室设备
		OpWorkOrderImpl data = treeOpWorkOrder.get(0);
		data.setOpDeviceArr(net.sf.json.JSONArray.fromObject(treeOpDevice));
		JSONArray arr = new JSONArray();
		arr.add(data);
		json.put("data", arr);
		
		//json.put("treeOpDevice", treeOpDevice);
		//json.put("treeOpWorkOrder", treeOpWorkOrder);
		return json;
	}
}