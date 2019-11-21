package com.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.OpDevice;
import com.framework.bean.OpWorkOrderCard;
import com.framework.bean.common.Cons;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpRoomOperationImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.OpDeviceConditionMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.dao.OpRoomOperationMapper;
import com.framework.dao.OpWorkOrderCardConditionMapper;
import com.framework.dao.OpWorkOrderCardMapper;
import com.framework.dao.OpWorkOrderConditionMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.framework.exception.CoException;
import com.framework.service.OpRoomOperationService;
import com.framework.service.OpWorkOrderService;
import com.framework.service.SyLogService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sun.tools.jar.resources.jar;
import sun.util.logging.resources.logging;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 操作室
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
@Service("opRoomOperationService")
public class OpRoomOperationServiceImpl extends CommonServiceImpl implements OpRoomOperationService {
	
	@Autowired
	private OpRoomOperationMapper opRoomOperationMapper;

	@Autowired
	public OpRoomOperationMapper getOpRoomOperationMapper() {
		return opRoomOperationMapper;
	}

	@Autowired
	public void setOpRoomOperationMapper(OpRoomOperationMapper opRoomOperationMapper) {
		this.opRoomOperationMapper = opRoomOperationMapper;
	}


	@Autowired
	private OpWorkOrderMapper opWorkOrderMapper;
	
	public OpWorkOrderMapper getOpWorkOrderMapper() {
		return opWorkOrderMapper;
	}

	public void setOpWorkOrderMapper(OpWorkOrderMapper opWorkOrderMapper) {
		this.opWorkOrderMapper = opWorkOrderMapper;
	}

	@Autowired
	private OpWorkOrderCardMapper opWorkOrderCardMapper;

	public OpWorkOrderCardMapper getOpWorkOrderCardMapper() {
		return opWorkOrderCardMapper;
	}

	public void setOpWorkOrderCardMapper(OpWorkOrderCardMapper opWorkOrderCardMapper) {
		this.opWorkOrderCardMapper = opWorkOrderCardMapper;
	}

	@Autowired
	private OpDeviceConditionMapper opDeviceConditionMapper;

	public OpDeviceConditionMapper getOpDeviceConditionMapper() {
		return opDeviceConditionMapper;
	}

	public void setOpDeviceConditionMapper(OpDeviceConditionMapper opDeviceConditionMapper) {
		this.opDeviceConditionMapper = opDeviceConditionMapper;
	}


	@Autowired
	private OpDeviceMapper opDeviceMapper;
	
	public OpDeviceMapper getOpDeviceMapper() {
		return opDeviceMapper;
	}

	public void setOpDeviceMapper(OpDeviceMapper opDeviceMapper) {
		this.opDeviceMapper = opDeviceMapper;
	}

	@Autowired
	private OpWorkOrderConditionMapper opWorkOrderConditionMapper;
	
	public OpWorkOrderConditionMapper getOpWorkOrderConditionMapper() {
		return opWorkOrderConditionMapper;
	}

	public void setOpWorkOrderConditionMapper(OpWorkOrderConditionMapper opWorkOrderConditionMapper) {
		this.opWorkOrderConditionMapper = opWorkOrderConditionMapper;
	}

	@Autowired
	private OpWorkOrderCardConditionMapper opWorkOrderCardConditionMapper;
	
	public OpWorkOrderCardConditionMapper getOpWorkOrderCardConditionMapper() {
		return opWorkOrderCardConditionMapper;
	}

	public void setOpWorkOrderCardConditionMapper(OpWorkOrderCardConditionMapper opWorkOrderCardConditionMapper) {
		this.opWorkOrderCardConditionMapper = opWorkOrderCardConditionMapper;
	}

	/**
	 * 新增实体对象
	 * @param opRoomOperation
	 */
	public OpRoomOperationImpl insert(OpRoomOperationImpl opRoomOperation) throws CoException {
		this.opRoomOperationMapper.insert(opRoomOperation);
		return opRoomOperation;
	}

	/**
	 * 删除实体对象
	 * @param opRoomOperation
	 */
	public int delete(OpRoomOperationImpl opRoomOperation) throws CoException {
		return this.opRoomOperationMapper.delete(opRoomOperation);
	}

	/**
	 * 更新实体对象
	 * @param opRoomOperation
	 */
	public boolean update(OpRoomOperationImpl opRoomOperation) throws CoException {
		boolean result = true;
		this.opRoomOperationMapper.update(opRoomOperation);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opRoomOperation
	 */
	@SuppressWarnings("unchecked")
	public List<OpRoomOperationImpl> select(OpRoomOperationImpl opRoomOperation) throws CoException {
		return (List<OpRoomOperationImpl>) this.opRoomOperationMapper.select(opRoomOperation);
	}

	/**
	 * 查询单个实体
	 * @param opRoomOperation
	 */
	public OpRoomOperationImpl get(OpRoomOperationImpl opRoomOperation) throws CoException {
		return (OpRoomOperationImpl) this.opRoomOperationMapper.get(opRoomOperation);
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
	public PageInfo<OpRoomOperationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opRoomOperationMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpRoomOperationImpl> page = new PageInfo<OpRoomOperationImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opRoomOperation
	 */
	@SuppressWarnings("unchecked")
	public List<OpRoomOperationImpl> selectTree(OpRoomOperationImpl opRoomOperation) throws CoException {
		return (List<OpRoomOperationImpl>) this.opRoomOperationMapper.selectTree(opRoomOperation);
	}
	
	/**
	 * 发牌
	 * @param requestJson
	 * @throws CoException
	 */
	@SuppressWarnings("unchecked")
	public void updateGrantCardsForOrder(JSONObject requestJson) throws CoException{
		//uidMaUser,codeMaRole,idMaCompany,loginName
		//更新设备(操作牌发放数量)
		JSONArray opDeviceArr = requestJson.getJSONArray("opDeviceArr");
		//工单主键
		String idWorkOrderString = requestJson.getString("idOpWorkOrder");
		Long idOpWorkOrder = Long.valueOf(idWorkOrderString);
		//登录人主键
		String userId = requestJson.getString("userId");
		//带班人
		String idLeaderString= requestJson.getString("idLeader");
		Long idLeader = Long.valueOf(idLeaderString);
		//查询操作牌已发放数量
		OpWorkOrderImpl innerOpWorkOrderImpl = new OpWorkOrderImpl();
		innerOpWorkOrderImpl.setId(idOpWorkOrder);
		OpWorkOrderImpl getOpWorkOrder = (OpWorkOrderImpl)opWorkOrderMapper.get(innerOpWorkOrderImpl);
		int countOpCardSend = getOpWorkOrder.getCountOpCardSend();
		//取牌状态
		//待发牌(待发操作牌，停电牌): ws
		//已发牌(已发操作牌，停电牌): sd
		//待还牌(待还操作牌，工作牌): wr
		//已还牌(已还操作牌，工作牌): rd
		String status = requestJson.getString("status");
		//操作牌申请数量
		Integer countOpCardApply = requestJson.getInteger("countOpCardApply");
		//20191008一次性还牌
		if(status.equals("rd")) {//已还牌
			OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
			opWorkOrderImpl.setId(idOpWorkOrder);
			// 还牌结束
			opWorkOrderImpl.setStatus("pd");//已还牌
			opWorkOrderImpl.setModifiedUser(userId);
			opWorkOrderImpl.setModifiedDate(new Date());
			this.setCommonField(opWorkOrderImpl);
			opWorkOrderMapper.update(opWorkOrderImpl);
			//查询工单下所有设备
			OpDeviceImpl innDeviceImpl = new OpDeviceImpl();
			innDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
			List<OpDeviceImpl> getOpDevices = (List<OpDeviceImpl>)opDeviceConditionMapper.getAllDevice(innDeviceImpl);
			for(int i=0;i<getOpDevices.size();i++) {
				OpDeviceImpl getOpDeviceiImpl = getOpDevices.get(i);
				OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
				Long idOpDevice = getOpDeviceiImpl.getId();//设备主键
				Long midOpWorkOrderCard = getOpDeviceiImpl.getMidOpWorkOrderCard();//主操作牌
				if(midOpWorkOrderCard == 0) {
					opDeviceImpl.setMidOpWorkOrderCard(midOpWorkOrderCard);//主操作牌
				} else {
					OpDeviceImpl innerDeviceImpl = new OpDeviceImpl();
					innerDeviceImpl.setId(idOpDevice);
					innerDeviceImpl = (OpDeviceImpl)opDeviceMapper.get(innerDeviceImpl);
					Long innerMidOpWorkOrderCard = innerDeviceImpl.getMidOpWorkOrderCard();
					//更新主工单主键
					opDeviceImpl.setMidOpWorkOrderCard(innerMidOpWorkOrderCard);//主操作牌
				}
				opDeviceImpl.setStatusOpRoomOperation(status);//更该设备变为已还牌
				opDeviceImpl.setUserId(userId);
				opDeviceImpl.setId(idOpDevice);
				opDeviceImpl.setModifiedDate(new Date());
				opDeviceImpl.setModifiedUser(userId);
				//Long midOpWorkOrder = getOpDeviceiImpl.getMidOpWorkOrder();
				this.setCommonField(opDeviceImpl);
				opDeviceMapper.update(opDeviceImpl);
				//操作牌
				Long idOpCardOperation = getOpDeviceiImpl.getIdOpCardOperation();
				Long midOpWorkOrder = getOpDeviceiImpl.getMidOpWorkOrder();
				OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
				opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
				opWorkOrderCard.setIdOpDevice(idOpDevice);
				opWorkOrderCard.setGroup("id");
				//查询工单中间表
				List<OpWorkOrderCardImpl> list= (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard);
				for (int j = 0; j < list.size(); j++) {
					OpWorkOrderCardImpl opWorkOrderCard1 = list.get(j);
					Long idOpRoomElectric = opWorkOrderCard1.getIdOpRoomElectric();//主电室
					//工单中间表主键
					Long idOpWorkOrderCard = opWorkOrderCard1.getId();
					opWorkOrderCard.setId(idOpWorkOrderCard);
					if(status.equals("sd")) {//已发牌
						opWorkOrderCard.setStatusOpCardOperation("td");//取牌完成:td
					} 
					// 还牌结束 rd
					else {
						opWorkOrderCard.setStatusOpCardOperation(status);
					}
					if(midOpWorkOrderCard == 0) {
						opWorkOrderCard.setTypeOpCardOperation("m");
						opWorkOrderCard.setIdOpCardPower(idOpRoomElectric);
						opDeviceImpl.setMidOpWorkOrderCard(idOpWorkOrderCard);//主操作牌
						//更新主工单主键
						opWorkOrderCard.setMidOpWorkOrder(idOpWorkOrder);
						opDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
						opWorkOrderCard.setIdOpCardOperation(idOpCardOperation);////操作牌
					} else {
						OpDeviceImpl innerDeviceImpl = new OpDeviceImpl();
						innerDeviceImpl.setId(idOpDevice);
						innerDeviceImpl = (OpDeviceImpl)opDeviceMapper.get(innerDeviceImpl);
						Long innerMidOpWorkOrderCard = innerDeviceImpl.getMidOpWorkOrderCard();
						//更新主工单主键
						opWorkOrderCard.setMidOpWorkOrder(midOpWorkOrder);
						opDeviceImpl.setMidOpWorkOrderCard(innerMidOpWorkOrderCard);//主操作牌
					}
					opWorkOrderCard.setIdMaUserOpCardOperation(idLeader);//中间表操作牌持有者
					opWorkOrderCard.setIdMaUserOpCardPower(idOpRoomElectric);//中间表停电牌持有者
					opWorkOrderCard.setUserId(userId);
					opWorkOrderCard.setModifiedDate(new Date());
					opWorkOrderCard.setModifiedUser(userId);
					this.setCommonField(opWorkOrderCard);
					opWorkOrderCardMapper.update(opWorkOrderCard);
				}
			}
			
		}else {
			// 操作室状态为sd发牌，rd还牌
			if((opDeviceArr.size() + countOpCardSend) == countOpCardApply) {
				OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
				opWorkOrderImpl.setId(idOpWorkOrder);
				if(status.equals("sd")) {//已发牌
					opWorkOrderImpl.setStatus("ad");//发牌结束 ad
					opWorkOrderImpl.setDateTaken(new Date());
					// 操作牌发放数量
					opWorkOrderImpl.setCountOpCardSend(countOpCardApply);
				} else if (status.equals("rd")) {// 还牌结束
					opWorkOrderImpl.setStatus("pd");//已还牌 pd
				} else {//拒绝
					opWorkOrderImpl.setStatus("re");//驳回 re
				}
				opWorkOrderImpl.setModifiedUser(userId);
				opWorkOrderImpl.setModifiedDate(new Date());
				this.setCommonField(opWorkOrderImpl);
				opWorkOrderMapper.update(opWorkOrderImpl);
			}else {
				OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
				opWorkOrderImpl.setId(idOpWorkOrder);
				opWorkOrderImpl.setModifiedUser(userId);
				opWorkOrderImpl.setModifiedDate(new Date());
				opWorkOrderImpl.setCountOpCardSend(opDeviceArr.size());
				/*if(status.equals("sd")) {//部分牌发放完成
					opWorkOrderImpl.setStatus("pt");
					opWorkOrderImpl.setDateTaken(new Date());
				}*/
				this.setCommonField(opWorkOrderImpl);
				opWorkOrderMapper.update(opWorkOrderImpl);
			}
		}
		
		//更新设备
		for (int i = 0; i < opDeviceArr.size(); i++) {
			//设备
			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
			JSONObject item = opDeviceArr.getJSONObject(i);
			
			Long idOpDevice = item.getLong("idOpDevice");//设备主键
			//查询设备主工单Id
			OpDeviceImpl innerOpDevice = new OpDeviceImpl();
			innerOpDevice.setId(idOpDevice);
			OpDeviceImpl getOpdevice = (OpDeviceImpl)opDeviceMapper.get(innerOpDevice);
			Long midOpWorkOrder = getOpdevice.getMidOpWorkOrder();
			//操作牌
			Long idOpCardOperation = getOpdevice.getIdOpCardOperation();
			//Long idOpCardPower = item.getLong("idOpCardPower");//停电牌
			Long midOpWorkOrderCard = item.getLong("midOpWorkOrderCard");//主操作牌
			Integer countSubOpCard = item.getInteger("countSubOpCard");//副操作牌
			
			/*OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
			opWorkOrderImpl.setIdOpDevice(idOpDevice);
			opWorkOrderImpl.setStatus("ai");//带取牌
			List<OpWorkOrderImpl> opList = (List<OpWorkOrderImpl>) this.opWorkOrderMapper.select(opWorkOrderImpl);
			if (opList.size() == 0) {
				opDeviceImpl.setStatusOpRoomOperation(status);//操作室状态
			}*/
		
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			opWorkOrderCard.setIdOpDevice(idOpDevice);
			opWorkOrderCard.setGroup("id");
			//查询工单中间表
			List<OpWorkOrderCardImpl> list= (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard);
			for (int j = 0; j < list.size(); j++) {
				OpWorkOrderCardImpl opWorkOrderCard1 = list.get(j);
				Long idOpRoomElectric = opWorkOrderCard1.getIdOpRoomElectric();//主电室
				Long idOpRoomOperation = opWorkOrderCard1.getIdOpRoomOperation();//操作室
				//工单中间表主键
				Long idOpWorkOrderCard = opWorkOrderCard1.getId();
				opWorkOrderCard.setId(idOpWorkOrderCard);
				if(status.equals("sd")) {//已发牌
					opWorkOrderCard.setStatusOpCardOperation("td");//取牌完成:td
					//发牌状态时，操作牌给带班人，停电牌给主电室
					opWorkOrderCard.setIdMaUserOpCardOperation(idLeader);//操作牌持有者
					opWorkOrderCard.setIdMaUserOpCardPower(idOpRoomElectric);//停电牌持有者
				} 
				// 还牌结束 rd
				else {
					opWorkOrderCard.setStatusOpCardOperation(status);
					//还牌状态时，操作牌给操作室，工作牌给主电室
					opWorkOrderCard.setIdMaUserOpCardOperation(idOpRoomOperation);//操作牌持有者
					opWorkOrderCard.setIdMaUserOpCardWork(idOpRoomElectric);//工作牌持有者
				}
				if(midOpWorkOrderCard == 0) {
					opWorkOrderCard.setTypeOpCardOperation("m");
					opWorkOrderCard.setIdOpCardPower(idOpRoomElectric);
					opDeviceImpl.setMidOpWorkOrderCard(idOpWorkOrderCard);//主操作牌
					//更新主工单主键
					opWorkOrderCard.setMidOpWorkOrder(idOpWorkOrder);
					opDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
					opWorkOrderCard.setIdOpCardOperation(idOpCardOperation);////操作牌
				} else {
					OpDeviceImpl innerDeviceImpl = new OpDeviceImpl();
					innerDeviceImpl.setId(idOpDevice);
					innerDeviceImpl = (OpDeviceImpl)opDeviceMapper.get(innerDeviceImpl);
					Long innerMidOpWorkOrderCard = innerDeviceImpl.getMidOpWorkOrderCard();
					//更新主工单主键
					opWorkOrderCard.setMidOpWorkOrder(midOpWorkOrder);
					opDeviceImpl.setMidOpWorkOrderCard(innerMidOpWorkOrderCard);//主操作牌
				}
				//opWorkOrderCard.setIdMaUserOpCardOperation(idLeader);//中间表操作牌持有者
				//opWorkOrderCard.setIdMaUserOpCardPower(idOpRoomElectric);//中间表停电牌持有者
				opWorkOrderCard.setUserId(userId);
				opWorkOrderCard.setModifiedDate(new Date());
				opWorkOrderCard.setModifiedUser(userId);
				this.setCommonField(opWorkOrderCard);
				opWorkOrderCardMapper.update(opWorkOrderCard);
			}
			
			//查询该设备下中间表待发牌工单数量：如果等于0，则更改设备状态
			OpWorkOrderCardImpl opWorkOrderCard2 = new OpWorkOrderCardImpl();
			opWorkOrderCard2.setIdOpDevice(idOpDevice);
			if(status.equals("sd")) {
				opWorkOrderCard2.setStatusOpCardOperation("at");//已申请取牌:at
			}else {
				opWorkOrderCard2.setStatusOpCardOperation("ar");//已申请还牌:ar
			}
			//opWorkOrderCard2.setMidOpWorkOrder(midOpWorkOrder);
			opWorkOrderCard2.setGroup("id");
			List<OpWorkOrderCardImpl> opList = (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard2);
			if (opList.size() == 0) {//发牌完成
				if(status.equals("sd")) {
					opDeviceImpl.setStatusOpRoomOperation("sd");//操作室状态 已发牌(已发操作牌)
				}else {
					/*if ("rd".equals(status)) {// 已还牌:rd
						opDeviceImpl.setStatusOpRoomOperation("rd");//操作室状态 已还牌(已还操作牌，工作牌)
						opDeviceImpl.setStatusOpRoomElectric("wr");
					}*/
					
				}
			}
			//Long idOpRoomOpration = opWorkOrderCard1.getIdOpRoomOperation();//操作室
			if(status.equals("sd")) {//成功发牌
				if(midOpWorkOrderCard == 0) {
					opDeviceImpl.setCountMainOpCard(1);//主牌数量
					//opDeviceImpl.setStatusOpRoomElectric("ws");//此操作有计划任务执行
					//opDeviceImpl.setStatusOpRoomOperation("sd");
				}else {
					opDeviceImpl.setCountSubOpCard(countSubOpCard + 1);//副牌数量
				}
				//opDeviceImpl.setStatusOpRoomOperation(status);//操作室状态
			}//发牌拒绝主副牌不增加
			
			//opDeviceImpl.setStatusOpRoomOperation(status);//操作室状态
			opDeviceImpl.setUserId(userId);
			opDeviceImpl.setId(idOpDevice);
			opDeviceImpl.setModifiedDate(new Date());
			opDeviceImpl.setModifiedUser(userId);
			//opDeviceImpl.setStatusOpRoomOperation(status);
			if("re".equals(status)) {//发牌拒绝
				if(midOpWorkOrder.equals(idOpWorkOrder)) {
					OpWorkOrderCardImpl selOpWorkOrderCard = new OpWorkOrderCardImpl();
					selOpWorkOrderCard.setMidOpWorkOrder(midOpWorkOrder);
					//主工单被拒绝  所有主工单关联的工单都要被拒绝
					List<OpWorkOrderCardImpl> opWorkOrderList = (List<OpWorkOrderCardImpl>)opWorkOrderCardConditionMapper.selectOpWorkOrderCardList(selOpWorkOrderCard);
					for(int j=0; j<opWorkOrderList.size(); j++) {
						OpWorkOrderCardImpl workOrder = opWorkOrderList.get(j);
						Long idWorkCard = workOrder.getId();
						Long idWork = workOrder.getIdOpWorkOrder();
						//拒绝工单
						OpWorkOrderImpl innerWorkOrder = new OpWorkOrderImpl();
						innerWorkOrder.setId(idWork);
						innerWorkOrder.setStatus("re");//拒绝
						innerWorkOrder.setModifiedDate(new Date());
						innerWorkOrder.setModifiedUser(userId);
						this.setCommonField(innerWorkOrder);
						opWorkOrderMapper.update(innerWorkOrder);
						//拒绝工单中间表
						OpWorkOrderCardImpl opWorkOrderCardImpl = new OpWorkOrderCardImpl();
						opWorkOrderCardImpl.setId(idWorkCard);
						opWorkOrderCardImpl.setStatusOpCardOperation("re");
						opWorkOrderCardImpl.setModifiedDate(new Date());
						opWorkOrderCardImpl.setModifiedUser(userId);
						this.setCommonField(opWorkOrderCardImpl);
						opWorkOrderCardMapper.update(opWorkOrderCardImpl);
					}
					//主工单拒绝  设备初始化
					opDeviceImpl.setStatusOpRoomElectric("or");//初始化
					opDeviceImpl.setStatusOpRoomOperation("or");
					opDeviceImpl.setMidOpWorkOrderCard(0L);
					opDeviceImpl.setMidOpWorkOrder(0L);
					opDeviceImpl.setStatusOpRoomOperation("or");
					opDeviceImpl.setStatusOpRoomElectric("or");
					opDeviceImpl.setCountSubOpCard(0);
					opDeviceImpl.setCountOpCard(0);
					opDeviceImpl.setCountMainOpCard(0);
					opDeviceImpl.setStatus("on");
					this.setCommonField(opDeviceImpl);
					opDeviceMapper.update(opDeviceImpl);
				} else if(midOpWorkOrderCard == 0){
					//主工单拒绝  设备初始化
					opDeviceImpl.setStatusOpRoomElectric("or");
					opDeviceImpl.setStatusOpRoomOperation("or");
					opDeviceImpl.setMidOpWorkOrderCard(0L);
					opDeviceImpl.setMidOpWorkOrder(0L);
					opDeviceImpl.setStatusOpRoomOperation("or");
					opDeviceImpl.setStatusOpRoomElectric("or");
					opDeviceImpl.setCountSubOpCard(0);
					opDeviceImpl.setCountOpCard(0);
					opDeviceImpl.setCountMainOpCard(0);
					opDeviceImpl.setStatus("on");
					this.setCommonField(opDeviceImpl);
					opDeviceMapper.update(opDeviceImpl);
				}else {
					if(midOpWorkOrder != 0) {
						opDeviceImpl.setStatusOpRoomOperation("sd");
					}
					this.setCommonField(opDeviceImpl);
					opDeviceMapper.update(opDeviceImpl);
				}
			}else {
				this.setCommonField(opDeviceImpl);
				opDeviceMapper.update(opDeviceImpl);
				//跨操作室发牌 全部牌发放完成 更改主电室状态or->ws为待发牌状态
				if(status.equals("sd") && (i == opDeviceArr.size() - 1)) {//成功发牌
					if((opDeviceArr.size() + countOpCardSend) == countOpCardApply) {
						OpDeviceImpl innerOpDeviceImpl = new OpDeviceImpl();
						innerOpDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);//当前工单
						innerOpDeviceImpl.setStatusOpRoomElectric("or");//主电室停电牌状态为初始化
						//查询当前工单所包含设备主电室状态为or初始化的设备，更改其状态为ws待发牌状态
						List<OpDeviceImpl> opDeviceImpls = (List<OpDeviceImpl>)opDeviceMapper.select(innerOpDeviceImpl);
						for (int j = 0; j < opDeviceImpls.size(); j++) {
							OpDeviceImpl getOpDeviceImpl = opDeviceImpls.get(j);
							Long nowOpdeviceIdLong = getOpDeviceImpl.getId();//设备主键
							OpDeviceImpl setOpdeviceImpl = new OpDeviceImpl();
							setOpdeviceImpl.setId(nowOpdeviceIdLong);
							setOpdeviceImpl.setStatusOpRoomElectric("ws");
							opDeviceMapper.update(setOpdeviceImpl);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 操作室发牌驳回
	 * @param requestJson
	 * @throws CoException
	 */
	public void updateReturntCardsForOrder(JSONObject requestJson) throws CoException{
		//工单主键
		String idWorkOrderString = requestJson.getString("idOpWorkOrder");
		Long idOpWorkOrder = Long.valueOf(idWorkOrderString);
		//登录人主键
		String userId = requestJson.getString("userId");
		//带班人
		String idLeaderString= requestJson.getString("idLeader");
		Long idLeader = Long.valueOf(idLeaderString);
		//查询操作牌已发放数量
		OpWorkOrderImpl innerOpWorkOrderImpl = new OpWorkOrderImpl();
		innerOpWorkOrderImpl.setId(idOpWorkOrder);
		OpWorkOrderImpl getOpWorkOrder = (OpWorkOrderImpl)opWorkOrderMapper.get(innerOpWorkOrderImpl);
		int countOpCardSend = getOpWorkOrder.getCountOpCardSend();
		//取牌状态 驳回re
		String status = requestJson.getString("status");
		//操作牌申请数量
		Integer countOpCardApply = requestJson.getInteger("countOpCardApply");
		//拒绝工单
		OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
		opWorkOrderImpl.setId(idOpWorkOrder);
		opWorkOrderImpl.setStatus("re");//驳回
		opWorkOrderImpl.setStatusComplete("co");
		opWorkOrderImpl.setModifiedUser(userId);
		opWorkOrderImpl.setModifiedDate(new Date());
		this.setCommonField(opWorkOrderImpl);
		opWorkOrderMapper.update(opWorkOrderImpl);
		//查询拒绝工单的工单中间表
		OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
		opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
		opWorkOrderCard.setGroup("id");
		//查询工单中间表
		List<OpWorkOrderCardImpl> list= (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard);
		for (int i = 0; i < list.size(); i++) {
			OpWorkOrderCardImpl opWorkOrderCardImpl = list.get(i);
			//拒绝设备表
			//设备主键
			Long idOpdeviceLong = opWorkOrderCardImpl.getIdOpDevice();
			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
			opDeviceImpl.setId(idOpdeviceLong);
			//查询设备是否存在于多个工单，若只有一个工单则直接初始化设备状态
			//状态为at已申请取牌
			// 申请取牌驳回:re(reject)
			// 已申请取牌:at(apply for to take card)
			// 取牌完成:td(applying for take card done)
			// 已申请还牌ar(apply for to return card)
			// 还牌完成rd:(apply for to return card done)
			
			// 查询已申请取牌中间表 statusOpCardOperation = 'at' 已申请取牌
			// statusOpCardOperation IN('td','ar','rd')
			List<OpDeviceImpl> atList = (List<OpDeviceImpl>)this.opDeviceConditionMapper.findTheOpdeviceToOpWorkOrderCard(opDeviceImpl);
			
			// 查询取牌完成中间表 td statusOpCardOperation IN('td') 
			List<OpDeviceImpl> tdList = (List<OpDeviceImpl>)this.opDeviceConditionMapper.findTheOpdeviceToOpWorkOrderCardForTd(opDeviceImpl);
			if(atList.size() == 1) {
				OpDeviceImpl innerOpdevice = new OpDeviceImpl();
				innerOpdevice.setId(idOpdeviceLong);
				if(tdList.size() == 0) {
					innerOpdevice.setStatusOpRoomOperation("or");
				} else {
					innerOpdevice.setStatusOpRoomOperation("sd");
				}
				innerOpdevice.setUserId(userId);
				innerOpdevice.setModifiedDate(new Date());
				innerOpdevice.setModifiedUser(userId);
				this.setCommonField(innerOpdevice);
				opDeviceMapper.update(innerOpdevice);
			}
			
			//拒绝工单中间表
			//中间表主键
			Long idLong = opWorkOrderCardImpl.getId();
			OpWorkOrderCardImpl innerOpWorkOrderCard = new OpWorkOrderCardImpl();
			innerOpWorkOrderCard.setId(idLong);
			innerOpWorkOrderCard.setStatusOpCardOperation("re");
			// 驳回逻辑删除中间表
			innerOpWorkOrderCard.setTag("d");
			innerOpWorkOrderCard.setIdOpDevice(0L);
			innerOpWorkOrderCard.setMidOpWorkOrder(0L);
			innerOpWorkOrderCard.setUserId(userId);
			innerOpWorkOrderCard.setModifiedDate(new Date());
			innerOpWorkOrderCard.setModifiedUser(userId);
			this.setCommonField(innerOpWorkOrderCard);
			opWorkOrderCardMapper.update(innerOpWorkOrderCard);
		}
	}
	
	
	/**
	 * 操作室发牌驳回
	 * @param requestJson
	 * @throws CoException
	 */
	public void updateReturntCardsForOrder2(JSONObject requestJson) throws CoException{
		//工单主键
		Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
		//登录人主键
		String userId = requestJson.getString("userId");
		//带班人
		String idLeaderString= requestJson.getString("idLeader");
		Long idLeader = Long.valueOf(idLeaderString);
		//查询操作牌已发放数量
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		opWorkOrder.setId(idOpWorkOrder);
		
		// 查询操作室驳回工单对应的所有设备
		OpDeviceImpl opDevice = new OpDeviceImpl();
		opDevice.setIdOpWorkOrder(idOpWorkOrder);
		// 主副为空的设备
		List<OpDeviceImpl> listOpDeviceCountMainZero = new ArrayList<OpDeviceImpl>();
		List<OpDeviceImpl> listOpDevice =  opDeviceConditionMapper.searchOpDeviceByOpRoomOperation(opDevice);
		if (null != listOpDevice && listOpDevice.size() > 0) {
			for (int i = 0; i < listOpDevice.size(); i++) {
				OpDeviceImpl opDeviceInner = new OpDeviceImpl();
				opDeviceInner = listOpDevice.get(i);
				// 没有主牌的的设备状态初始化
				// 主副为空的设备
				Integer countMainOpCard = opDeviceInner.getCountMainOpCard();
				if(null == countMainOpCard || 0 == countMainOpCard) {
					listOpDeviceCountMainZero.add(opDeviceInner);
				}
			}
		}

	}
	
	/**
	 * 手机app点击还牌，执行一次性将工单所有的牌全部还完
	 * @param requestJson
	 * @throws CoException
	 */
	public void appReturnBackOpDeviceInfo(JSONObject requestJson) throws CoException{
		//工单主键
		String idWorkOrderString = requestJson.getString("idOpWorkOrder");
		Long idOpWorkOrder = Long.valueOf(idWorkOrderString);
		//登录人主键
		String userId = requestJson.getString("userId");
		//带班人
		String idLeaderString= requestJson.getString("idLeader");
		Long idLeader = Long.valueOf(idLeaderString);
		
		OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
		opWorkOrderImpl.setId(idOpWorkOrder);
		// 还牌结束
		opWorkOrderImpl.setStatus("pd");//已还牌
		opWorkOrderImpl.setModifiedUser(userId);
		opWorkOrderImpl.setModifiedDate(new Date());
		opWorkOrderImpl.setDateReturned(new Date());
		this.setCommonField(opWorkOrderImpl);
		opWorkOrderMapper.update(opWorkOrderImpl);
		//查询工单下所有设备
		OpDeviceImpl innDeviceImpl = new OpDeviceImpl();
		innDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
		List<OpDeviceImpl> getOpDevices = (List<OpDeviceImpl>)opDeviceConditionMapper.getAllDevice(innDeviceImpl);
		for(int i=0;i<getOpDevices.size();i++) {
			OpDeviceImpl getOpDeviceiImpl = getOpDevices.get(i);
			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
			Long idOpDevice = getOpDeviceiImpl.getId();//设备主键
			Long midOpWorkOrderCard = getOpDeviceiImpl.getMidOpWorkOrderCard();//主操作牌
			//操作牌
			Long idOpCardOperation = getOpDeviceiImpl.getIdOpCardOperation();
			Long midOpWorkOrder = getOpDeviceiImpl.getMidOpWorkOrder();
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			opWorkOrderCard.setIdOpDevice(idOpDevice);
			opWorkOrderCard.setGroup("id");
			//查询工单中间表
			List<OpWorkOrderCardImpl> list= (List<OpWorkOrderCardImpl>) this.opWorkOrderCardMapper.select(opWorkOrderCard);
			for (int j = 0; j < list.size(); j++) {
				OpWorkOrderCardImpl opWorkOrderCard1 = list.get(j);
				Long idOpRoomElectric = opWorkOrderCard1.getIdOpRoomElectric();//主电室
				Long idOpRoomOperation = opWorkOrderCard1.getIdOpRoomOperation();//操作室
				//工单中间表主键
				Long idOpWorkOrderCard = opWorkOrderCard1.getId();
				opWorkOrderCard.setId(idOpWorkOrderCard);
				opWorkOrderCard.setStatusOpCardOperation("rd");
				if(midOpWorkOrderCard == 0) {
					opWorkOrderCard.setTypeOpCardOperation("m");
					opWorkOrderCard.setIdOpCardPower(idOpRoomElectric);
					opDeviceImpl.setMidOpWorkOrderCard(idOpWorkOrderCard);//主操作牌
					//更新主工单主键
					opWorkOrderCard.setMidOpWorkOrder(idOpWorkOrder);
					opDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
					opWorkOrderCard.setIdOpCardOperation(idOpCardOperation);////操作牌
				} else {
					OpDeviceImpl innerDeviceImpl = new OpDeviceImpl();
					innerDeviceImpl.setId(idOpDevice);
					innerDeviceImpl = (OpDeviceImpl)opDeviceMapper.get(innerDeviceImpl);
					Long innerMidOpWorkOrderCard = innerDeviceImpl.getMidOpWorkOrderCard();
					//更新主工单主键
					opWorkOrderCard.setMidOpWorkOrder(midOpWorkOrder);
					opDeviceImpl.setMidOpWorkOrderCard(innerMidOpWorkOrderCard);//主操作牌
				}
				//opWorkOrderCard.setIdMaUserOpCardOperation(idLeader);//中间表操作牌持有者
				//opWorkOrderCard.setIdMaUserOpCardPower(idOpRoomElectric);//中间表停电牌持有者
				//还牌状态时，操作牌给操作室，工作牌给主电室
				opWorkOrderCard.setIdMaUserOpCardOperation(idOpRoomOperation);//操作牌持有者
				opWorkOrderCard.setIdMaUserOpCardWork(idOpRoomElectric);//工作牌持有者
				// 还牌后逻辑删除工牌中间表
				// opWorkOrderCard.setTag("d");
				opWorkOrderCard.setUserId(userId);
				opWorkOrderCard.setModifiedDate(new Date());
				opWorkOrderCard.setModifiedUser(userId);
				this.setCommonField(opWorkOrderCard);
				opWorkOrderCardMapper.update(opWorkOrderCard);
			}
		}
	}
	/**
	 * 批量更新设备状态
	 * @param requestJson
	 * @throws CoException
	 */
	public boolean updateOpDeviceStatus(OpDeviceImpl opDevice) throws CoException{
		boolean res = true;
		// 只更新开关状态，考虑到并发问题，剩下的状态更新采用计划任务，暂定10秒一查询并更新。
		//查询主电室状态为sd已发牌的设备主键及主工单
		List<OpDeviceImpl> listOpDevice = opDeviceConditionMapper.selectOpDevice(opDevice);
		try {
			if(null != listOpDevice && listOpDevice.size() > 0) {
				for (int i = 0; i < listOpDevice.size(); i++) {
					OpDeviceImpl opDeviceImpl = listOpDevice.get(i);
					Long id = opDeviceImpl.getId();//设备主键
					Long midOpWorkOrder = opDeviceImpl.getMidOpWorkOrder();//主工单Id
					OpWorkOrderCardImpl opWorkOrderCardImpl = new OpWorkOrderCardImpl();
					opWorkOrderCardImpl.setIdOpDevice(id);//设备主键
					opWorkOrderCardImpl.setMidOpWorkOrder(midOpWorkOrder);//主工单id
					//查询设备关联的工单中间表
					List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderCardConditionMapper.selectOpWorkOrder(opWorkOrderCardImpl);
					//查询关联的工单的中间表状态（已还牌rd）的
					List<OpWorkOrderCardImpl> listOpWorkOrderCard = opWorkOrderCardConditionMapper.selectOpWorkOrderCard(opWorkOrderCardImpl);
					//工单和工单中间表rd（换牌状态相等，则更新设备状态为换牌）
					if(null !=listOpWorkOrder && listOpWorkOrder.size() > 0) {
						if(listOpWorkOrder.size() == listOpWorkOrderCard.size()) {
							OpDeviceImpl opDeviceImpl2 = new OpDeviceImpl();
							opDeviceImpl2.setId(id);
							//opDeviceImpl2.setStatusOpRoomOperation("rd");
							opDeviceImpl2.setStatusOpRoomElectric("wr");
							opDeviceImpl2.setModifiedDate(new Date());
							this.setCommonField(opDeviceImpl2);
							opDeviceMapper.update(opDeviceImpl2);
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
}