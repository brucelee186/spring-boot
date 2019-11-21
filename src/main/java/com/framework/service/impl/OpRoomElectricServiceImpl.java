package com.framework.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.bean.impl.OpDeviceGroupImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpRoomElectricImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.OpDeviceConditionMapper;
import com.framework.dao.OpDeviceElectricianCardMapper;
import com.framework.dao.OpDeviceGroupMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.dao.OpRoomElectricMapper;
import com.framework.dao.OpWorkOrderCardConditionMapper;
import com.framework.dao.OpWorkOrderCardMapper;
import com.framework.dao.OpWorkOrderConditionMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.framework.exception.CoException;
import com.framework.service.OpRoomElectricService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.util.UtIpAddress;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 电器室管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
@Service("opRoomElectricService")
public class OpRoomElectricServiceImpl extends CommonServiceImpl implements OpRoomElectricService {
	@Autowired
	private OpRoomElectricMapper opRoomElectricMapper;
	
	@Autowired
	private OpDeviceConditionMapper opDeviceConditionMapper;

	@Autowired
	private OpWorkOrderConditionMapper opWorkOrderConditionMapper;
	
	@Autowired
	private OpWorkOrderCardConditionMapper opWorkOrderCardConditionMapper;
	
	
	@Autowired
	public OpRoomElectricMapper getOpRoomElectricMapper() {
		return opRoomElectricMapper;
	}

	@Autowired
	public void setOpRoomElectricMapper(OpRoomElectricMapper opRoomElectricMapper) {
		this.opRoomElectricMapper = opRoomElectricMapper;
	}

	public OpWorkOrderConditionMapper getOpWorkOrderConditionMapper() {
		return opWorkOrderConditionMapper;
	}

	public void setOpWorkOrderConditionMapper(OpWorkOrderConditionMapper opWorkOrderConditionMapper) {
		this.opWorkOrderConditionMapper = opWorkOrderConditionMapper;
	}
	
	public OpWorkOrderCardConditionMapper getOpWorkOrderCardConditionMapper() {
		return opWorkOrderCardConditionMapper;
	}

	public void setOpWorkOrderCardConditionMapper(OpWorkOrderCardConditionMapper opWorkOrderCardConditionMapper) {
		this.opWorkOrderCardConditionMapper = opWorkOrderCardConditionMapper;
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
	private OpDeviceGroupMapper opDeviceGroupMapper;
	
	public OpDeviceGroupMapper getOpDeviceGroupMapper() {
		return opDeviceGroupMapper;
	}

	public void setOpDeviceGroupMapper(OpDeviceGroupMapper opDeviceGroupMapper) {
		this.opDeviceGroupMapper = opDeviceGroupMapper;
	}

	@Autowired
	private OpDeviceElectricianCardMapper opDeviceElectricianCardMapper;
	
	public OpDeviceElectricianCardMapper getOpDeviceElectricianCardMapper() {
		return opDeviceElectricianCardMapper;
	}

	public void setOpDeviceElectricianCardMapper(OpDeviceElectricianCardMapper opDeviceElectricianCardMapper) {
		this.opDeviceElectricianCardMapper = opDeviceElectricianCardMapper;
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

	/**
	 * 新增实体对象
	 * @param opRoomElectric
	 */
	public OpRoomElectricImpl insert(OpRoomElectricImpl opRoomElectric) throws CoException {
		this.opRoomElectricMapper.insert(opRoomElectric);
		return opRoomElectric;
	}

	/**
	 * 删除实体对象
	 * @param opRoomElectric
	 */
	public int delete(OpRoomElectricImpl opRoomElectric) throws CoException {
		return this.opRoomElectricMapper.delete(opRoomElectric);
	}

	/**
	 * 更新实体对象
	 * @param opRoomElectric
	 */
	public boolean update(OpRoomElectricImpl opRoomElectric) throws CoException {
		boolean result = true;
		this.opRoomElectricMapper.update(opRoomElectric);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opRoomElectric
	 */
	@SuppressWarnings("unchecked")
	public List<OpRoomElectricImpl> select(OpRoomElectricImpl opRoomElectric) throws CoException {
		return (List<OpRoomElectricImpl>) this.opRoomElectricMapper.select(opRoomElectric);
	}

	/**
	 * 查询单个实体
	 * @param opRoomElectric
	 */
	public OpRoomElectricImpl get(OpRoomElectricImpl opRoomElectric) throws CoException {
		return (OpRoomElectricImpl) this.opRoomElectricMapper.get(opRoomElectric);
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
	public PageInfo<OpRoomElectricImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opRoomElectricMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpRoomElectricImpl> page = new PageInfo<OpRoomElectricImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opRoomElectric
	 */
	@SuppressWarnings("unchecked")
	public List<OpRoomElectricImpl> selectTree(OpRoomElectricImpl opRoomElectric) throws CoException {
		return (List<OpRoomElectricImpl>) this.opRoomElectricMapper.selectTree(opRoomElectric);
	}
	
	/**
	 * 发牌（停电牌）
	 * @param requestJson
	 * @throws CoException
	 */
	@SuppressWarnings("unchecked")
	public OpDeviceGroupImpl updateGrantPowerCardsForOrder(JSONObject requestJson) throws CoException {
		
		OpDeviceGroupImpl opDeviceGroup = null;
		//{"codeMaRole":{"value":"re"},"idMaCompany":3,"idOpRoomElectric":1,"userId":"5e1ae34c-3a54-4787-8d3c-79a6861130af","idOpRoomOperation":1},
		//角色
		String codeMaRole = requestJson.getJSONObject("codeMaRole").getString("value");
		//String loginName = requestJson.getJSONObject("loginName").getString("value");
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// new Date()为获取当前系统时间
		String nowDate = df.format(new Date());
		//设备数组
		JSONArray idDeviceArr = requestJson.getJSONArray("idDeviceArr");
		//电工数组
		JSONArray idElectricianArr = requestJson.getJSONArray("idElectricianArr");
		Long idMaCompany = requestJson.getLong("idMaCompany");
		//
		String userId = requestJson.getString("userId");
		//主电室id
		Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
		//主电室状态
		String statusOpRoomElectric = requestJson.getString("statusOpRoomElectric");
		if(statusOpRoomElectric.equals("rw")) {
			//工单主键
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setId(idOpWorkOrder);
			opWorkOrder.setIdMaCompany(idMaCompany);
			opWorkOrder.setStatus(statusOpRoomElectric);//驳回
			opWorkOrder.setModifiedDate(new Date());
			opWorkOrder.setModifiedUser(userId);
			this.setCommonField(opWorkOrder);
			opWorkOrderMapper.update(opWorkOrder);
			//查询工单中间表 设置驳回
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			//opWorkOrderCard.setIdOpDevice(idOpDevice);
			opWorkOrderCard.setIdMaCompany(idMaCompany);
			opWorkOrderCard.setGroup("id");
			List<OpWorkOrderCardImpl>list = (List<OpWorkOrderCardImpl>)opWorkOrderCardMapper.select(opWorkOrderCard);
			for(int i=0;i<list.size(); i++) {
				OpWorkOrderCardImpl opWorkOrderCardImpl = list.get(i);
				OpWorkOrderCardImpl opWorkOrderCardImpl2 = new OpWorkOrderCardImpl();
				opWorkOrderCardImpl2.setId(opWorkOrderCardImpl.getId());
				opWorkOrderCardImpl2.setStatusOpCardOperation(opWorkOrderCardImpl.getStatusOpCardOperation());
				opWorkOrderCardImpl2.setModifiedDate(new Date());
				opWorkOrderCardImpl2.setModifiedUser(userId);
				this.setCommonField(opWorkOrderCardImpl2);
				opWorkOrderCardMapper.update(opWorkOrderCardImpl2);
				
				//查询工单中间表关联的设备
				//设备主键
				Long idOpDevice = opWorkOrderCardImpl.getIdOpDevice();
				OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
				opDeviceImpl.setId(idOpDevice);
				OpDeviceImpl getOpDevice = (OpDeviceImpl)opDeviceMapper.get(opDeviceImpl);
				String opRoomElectricStatus = getOpDevice.getStatusOpRoomElectric();
				if("ws".equals(opRoomElectricStatus)) {
					Long opdeviceId = getOpDevice.getId();
					OpDeviceImpl innnerOpDevice = new OpDeviceImpl();
					innnerOpDevice.setId(opdeviceId);
					innnerOpDevice.setStatusOpRoomElectric("or");
					innnerOpDevice.setStatusOpRoomOperation("or");
					innnerOpDevice.setMidOpWorkOrderCard(0L);
					innnerOpDevice.setMidOpWorkOrder(0L);
					innnerOpDevice.setStatusOpRoomOperation("or");
					innnerOpDevice.setStatusOpRoomElectric("or");
					innnerOpDevice.setCountSubOpCard(0);
					innnerOpDevice.setCountOpCard(0);
					innnerOpDevice.setCountMainOpCard(0);
					innnerOpDevice.setStatus("on");
					innnerOpDevice.setModifiedDate(new Date());
					innnerOpDevice.setModifiedUser(userId);
					this.setCommonField(innnerOpDevice);
					opDeviceMapper.update(innnerOpDevice);
				}
				if("sd".equals(opRoomElectricStatus)) {
					Long opdeviceId = getOpDevice.getId();
					int countSubOpCard = getOpDevice.getCountSubOpCard();
					OpDeviceImpl innnerOpDevice = new OpDeviceImpl();
					innnerOpDevice.setId(opdeviceId);
					if(countSubOpCard > 0) {//拒绝发牌 副工单 -1
						innnerOpDevice.setCountSubOpCard(countSubOpCard - 1);
					}
					innnerOpDevice.setModifiedDate(new Date());
					innnerOpDevice.setModifiedUser(userId);
					this.setCommonField(innnerOpDevice);
					opDeviceMapper.update(innnerOpDevice);
				}
			}
		} else {
			//主电室id
			//Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
			//设备电工组
			opDeviceGroup = new OpDeviceGroupImpl();
			opDeviceGroup.setIdMaCompany(idMaCompany);
			opDeviceGroup.setName(nowDate);
			opDeviceGroup.setStatus("or");
			opDeviceGroup.setEditState("i");
			opDeviceGroup.setCreateUser(userId);
			opDeviceGroup.setModifiedUser(userId);
			opDeviceGroup.setCreateDate(new Date());
			opDeviceGroup.setCreateUser(userId);
			opDeviceGroup.setModifiedUser(userId);
			this.setCommonField(opDeviceGroup);
			opDeviceGroupMapper.insert(opDeviceGroup);
			//互保工友列表
			String listIdElectrician = "";
			//互保工友
			String listNameElectrician = "";
			//互保工友列表
			String listElectrician = "";
			for (int j = 0; j < idElectricianArr.size(); j++) {
				JSONObject item = idElectricianArr.getJSONObject(j);
				if(j > 0) {
					listIdElectrician += ",";
					listNameElectrician += ",";
					listElectrician += ",";
				}
				listIdElectrician += item.getString("id");//主键//编号
				listNameElectrician += item.getString("name");//员工//列表
				listElectrician += item.getString("id") + "("+ item.getString("name") +")";
			}
			//设备组
			Long idOpDeviceGroup = opDeviceGroup.getId();
			for(int i=0; i < idDeviceArr.size(); i++) {
				JSONObject item = idDeviceArr.getJSONObject(i);
				Long idOpDevice = item.getLong("id");
				//td 停电   sd 送电
				String staFlg = item.getString("staFlg");
				//查询设备中主工单牌
				OpDeviceImpl innerOpDevice = new OpDeviceImpl();
				innerOpDevice.setId(idOpDevice);
				OpDeviceImpl getOpDevice = (OpDeviceImpl)opDeviceMapper.get(innerOpDevice);
				Long midOpWorkOrder = getOpDevice.getMidOpWorkOrder();//设备中的主工单id
				//设备表()
				OpDeviceImpl opDevice = new OpDeviceImpl();
				opDevice.setId(idOpDevice);
				if("td".equals(staFlg)) {//停电
					opDevice.setStatusOpRoomElectric("sd");//设备状态 sd已发工作牌  rd已还工作牌,已发停电牌
					//opDevice.setStatusOpRoomOperation("wr");
				}else {//送电
					opDevice.setStatusOpRoomElectric("rd");//设备状态 sd已发工作牌  rd已还工作牌,已发停电牌
					//opDevice.setStatusOpRoomOperation("wr");
				}
				opDevice.setModifiedUser(userId);
				opDevice.setModifiedDate(new Date());
				this.setCommonField(opDevice);
				opDeviceMapper.update(opDevice);
				//查询主牌所在工单的点检人
				OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
				opWorkOrder.setId(idOpDevice);
				OpWorkOrderImpl opWorkOrderImpl =  this.opWorkOrderConditionMapper.getOrdersIdCheckerInfo(opWorkOrder);
				Long idChecker = opWorkOrderImpl.getIdChecker();//派发人id
				String nameChecker = opWorkOrderImpl.getNameChecker();//派发人
				
				Long idOpCardOperation = item.getLong("idOpCardOperation");
				Long idOpCardPower = item.getLong("idOpCardPower");
				Long idOpCardWork = item.getLong("idOpCardWork");
				Long idOpRoomOperation = item.getLong("idOpRoomOperation");
				//主电室id
				//Long idOpRoomElectric = item.getLong("idOpRoomElectric");
				if(statusOpRoomElectric.equals("sd")) {
					//电工卡片状态 wo持有工作牌 
					//查询opWorkOrderCard中间表，更新中间表停电牌持有人
					OpWorkOrderCardImpl opWorkOrderCardImpl = new OpWorkOrderCardImpl();
					opWorkOrderCardImpl.setIdOpDevice(idOpDevice);
					opWorkOrderCardImpl.setStatusOpCardOperation("td");
					opWorkOrderCardImpl.setGroup("id");
					List<OpWorkOrderCardImpl>opList = (List<OpWorkOrderCardImpl>)this.opWorkOrderCardMapper.select(opWorkOrderCardImpl);
					for (int j = 0; j < opList.size(); j++) {
						OpWorkOrderCardImpl opItem = opList.get(j);
						Long idOpWorkOrder = opItem.getId();//中间表主键
						//更新中间表停电牌持有人及停电组编号
						OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl(); 
						opWorkOrderCard.setId(idOpWorkOrder);
						opWorkOrderCard.setIdMaUserOpCardPower(idOpDeviceGroup);//工作牌
						opWorkOrderCard.setIdOpDeviceGroupPowerOff(idOpDeviceGroup);
						opWorkOrderCard.setModifiedDate(new Date());
						opWorkOrderCard.setModifiedUser(userId);
						this.setCommonField(opWorkOrderCard);
						this.opWorkOrderCardMapper.update(opItem);
					}
				}else {
					//电工卡片状态 wo持有工作牌 po持有停电牌
					//查询opWorkOrderCard中间表，更新中间表停电牌持有人
					OpWorkOrderCardImpl opWorkOrderCardImpl = new OpWorkOrderCardImpl();
					opWorkOrderCardImpl.setIdOpDevice(idOpDevice);
					opWorkOrderCardImpl.setStatusOpCardOperation("rd");
					opWorkOrderCardImpl.setGroup("id");
					List<OpWorkOrderCardImpl>opList = (List<OpWorkOrderCardImpl>)this.opWorkOrderCardMapper.select(opWorkOrderCardImpl);
					for (int j = 0; j < opList.size(); j++) {
						OpWorkOrderCardImpl opItem = opList.get(j);
						Long idOpWorkOrder = opItem.getId();//中间表主键
						//更新中间表停电牌持有人及停电组编号
						OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl(); 
						opWorkOrderCard.setId(idOpWorkOrder);
						opWorkOrderCard.setIdMaUserOpCardPower(idOpDeviceGroup);//停电牌
						opWorkOrderCard.setIdOpDeviceGroupPowerOn(idOpDeviceGroup);
						opWorkOrderCard.setModifiedDate(new Date());
						opWorkOrderCard.setModifiedUser(userId);
						this.setCommonField(opWorkOrderCard);
						this.opWorkOrderCardMapper.update(opItem);
					}
				}
				
				//SELECT a.idChecker,a.nameChecker FROM opWorkOrder AS a LEFT JOIN opDevice AS b ON b.midOpWorkOrderCard = a.id WHERE b.id =  12
				for (int j = 0; j < idElectricianArr.size(); j++) {
					JSONObject jtem = idElectricianArr.getJSONObject(j);
					Long idElectrician = jtem.getLong("id");//电工
					String idElectricianName = jtem.getString("name");
					//设备电工牌()
					OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
					opDeviceElectricianCard.setIdMaCompany(idMaCompany);//公司主键
					opDeviceElectricianCard.setIdOpDevice(idOpDevice);//设备主键
					opDeviceElectricianCard.setIdOpDeviceGroup(idOpDeviceGroup);//设备电工组
					//主电室停电状态时工作牌给电工sd
					if(statusOpRoomElectric.equals("sd")) {
						opDeviceElectricianCard.setIdMaUserOpCardWork(idElectrician);//电工
						opDeviceElectricianCard.setStatusElectrician("wo");//电工卡片状态 wo持有工作牌 po持有停电牌
						opDeviceElectricianCard.setDateSendOpCardWork(new Date());
					}else {
						//主电室送电状态时停电牌给电工
						opDeviceElectricianCard.setIdMaUserOpCardWork(idElectrician);//电工
						opDeviceElectricianCard.setStatusElectrician("po");//电工卡片状态 wo持有工作牌 po持有停电牌
						opDeviceElectricianCard.setDateSendOpCardPower(new Date());
					}
					String listIdElectrician1 = "";
					String listNameElectrician1 = "";
					String listElectrician1 = "";
					if(listIdElectrician.contains(idElectrician.toString())) {
						String list1[]=listIdElectrician.split(",");
						for(int k=0; k<list1.length; k++) {
							if(list1[k].equals(idElectrician.toString())) {
								continue;
							}else {
								listIdElectrician1 += list1[k];
								if(k != list1.length -1) {
									listIdElectrician1 += ",";
								}
							}
							
						}
						String list2[]=listNameElectrician.split(",");
						for(int k=0; k<list2.length; k++) {
							if(list2[k].equals(idElectricianName)) {
								continue;
							}else {
								listNameElectrician1 += list2[k];
								if(k != list2.length-1) {
									listNameElectrician1 += ",";
								}
							}
						}
						String list3[]=listElectrician.split(",");
						for(int k=0; k<list3.length; k++) {
							if(list3[k].equals(idElectrician.toString() + "("+ idElectricianName +")")) {
								continue;
							}else {
								listElectrician1 += list3[k];
								if(k != list3.length -1) {
									listElectrician1 += ",";
								}
							}
						}
					}
					opDeviceElectricianCard.setIdElectrician(idElectrician);
					opDeviceElectricianCard.setListNameElectrician(listNameElectrician1);//互保
					opDeviceElectricianCard.setListIdElectrician(listIdElectrician1);
					opDeviceElectricianCard.setListElectrician(listElectrician1);
					opDeviceElectricianCard.setIdChecker(idChecker);//派发人
					opDeviceElectricianCard.setNameChecker(nameChecker);
					opDeviceElectricianCard.setIdOpCardOperation(idOpCardOperation);
					opDeviceElectricianCard.setIdOpCardPower(idOpCardPower);
					opDeviceElectricianCard.setIdOpCardWork(idOpCardWork);
					opDeviceElectricianCard.setIdOpRoomOperation(idOpRoomOperation);
					opDeviceElectricianCard.setIdOpRoomElectric(idOpRoomElectric);
					
					//opDeviceElectricianCard.setStatusElectrician("po");
					//操作牌持有者
					opDeviceElectricianCard.setIdMaUserOpCardOperation(0L);
					//工作牌持有者
					opDeviceElectricianCard.setIdMaUserOpCardWork(idElectrician);
					//停电牌持有者
					opDeviceElectricianCard.setIdMaUserOpCardPower(idOpRoomElectric);
					opDeviceElectricianCard.setEditState("i");
					opDeviceElectricianCard.setCreateUser(userId);
					opDeviceElectricianCard.setModifiedUser(userId);
					opDeviceElectricianCard.setCreateDate(new Date());
					opDeviceElectricianCard.setMidOpWorkOrder(midOpWorkOrder);
					this.setCommonField(opDeviceElectricianCard);
					opDeviceElectricianCardMapper.insert(opDeviceElectricianCard);
				}
			}
		}
		return opDeviceGroup;
	}
	
	/**
	 * 主电室发牌驳回
	 * @param requestJson
	 * @return
	 */
	public OpDeviceGroupImpl updateReturnPowerCardsForOrder(JSONObject requestJson, HttpServletRequest request) throws CoException{
		OpDeviceGroupImpl opDeviceGroup = new OpDeviceGroupImpl();
		//{"codeMaRole":{"value":"re"},"idMaCompany":3,"idOpRoomElectric":1,"userId":"5e1ae34c-3a54-4787-8d3c-79a6861130af","idOpRoomOperation":1},
		//角色
		String codeMaRole = requestJson.getJSONObject("codeMaRole").getString("value");
		//String loginName = requestJson.getJSONObject("loginName").getString("value");
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// new Date()为获取当前系统时间
		String nowDate = df.format(new Date());
		//设备数组
		JSONArray idDeviceArr = requestJson.getJSONArray("idDeviceArr");
		//电工数组
		JSONArray idElectricianArr = requestJson.getJSONArray("idElectricianArr");
		Long idMaCompany = requestJson.getLong("idMaCompany");
		//
		String userId = requestJson.getString("userId");
		//主电室id
		Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
		//主电室状态
		String statusOpRoomElectric = requestJson.getString("statusOpRoomElectric");
		
		//驳回工单
		// 查询本工单关联的所有工单
		//工单主键
		Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		opWorkOrder.setId(idOpWorkOrder);

		// 主电室驳回
		String modifiedIp = UtIpAddress.getIpAddr(request);
		
		// 1 开关对应的主工单无副工单(副牌)的情况下,初始化主工单对应开关状态
		// 取得无副牌的设备工单中间表
		OpWorkOrderCardImpl opWorkOrderCard1 = new OpWorkOrderCardImpl();
		opWorkOrderCard1.setIdOpWorkOrder(idOpWorkOrder);
		List<OpWorkOrderCardImpl> listOpWorkOrderCard1 = opWorkOrderCardConditionMapper.selectOpWorkOrderCardCountSubZero(opWorkOrderCard1);
		if(null != listOpWorkOrderCard1 && listOpWorkOrderCard1.size() > 0) {
			// 初始化主工单对应开关状态
			OpDeviceImpl opDeviceInner1 = new OpDeviceImpl();
			opDeviceInner1.setListOpWorkOrderCard(listOpWorkOrderCard1);
			
			// 初始化关联的设备
			opDeviceConditionMapper.updateOpDeviceRejectByMidOpWorkOrder(opDeviceInner1);
			
			// 驳回工单对应的工单中单表
			OpWorkOrderCardImpl opWorkOrderInner1 = new OpWorkOrderCardImpl();
			opWorkOrderInner1.setModifiedUser(userId);
			opWorkOrderInner1.setModifiedIp(modifiedIp);
			opWorkOrderInner1.setListOpWorkOrderCard(listOpWorkOrderCard1);
			opWorkOrderInner1.setMidOpWorkOrder(0L);
			opWorkOrderCardConditionMapper.updateByMidOpWrokOrder(opWorkOrderInner1);
		}
		
		// 2 开关对应的主工单有副工单(副牌)的情况下,开关挑选任意一条副工单数据为主工单, 并且更新所有原主工单中单表主工单编号为当前工单的编号,并且副牌数量减一
		// 查询主工单下还有副工单的工单设备中间表
		OpWorkOrderCardImpl opWorkOrderCard2 = new OpWorkOrderCardImpl();
		opWorkOrderCard2.setIdOpWorkOrder(idOpWorkOrder);
		List<OpWorkOrderCardImpl> listOpWorkOrderCard2 = opWorkOrderCardConditionMapper.selectOpWorkOrderCardCountSubLargerThanOne(opWorkOrderCard2);
		if(null != listOpWorkOrderCard2 && listOpWorkOrderCard2.size() > 0) {
			// 驳回工单对应的工单中单表
			OpWorkOrderCardImpl opWorkOrderInner2 = new OpWorkOrderCardImpl();
			opWorkOrderInner2.setModifiedUser(userId);
			opWorkOrderInner2.setModifiedIp(modifiedIp);
			opWorkOrderInner2.setListOpWorkOrderCard(listOpWorkOrderCard2);
			opWorkOrderInner2.setIdOpDevice(0L);
			opWorkOrderInner2.setMidOpWorkOrder(0L);
			opWorkOrderCardConditionMapper.updateByMidOpWrokOrder(opWorkOrderInner2);
			
		}
		// 开关挑选任意一条副工单数据为主工单(主工单相同,工单编号不同的中间表数据)
		// 查询当前工单对应的副工单(副牌大于1的情况下一定会有副工单)
		List<OpWorkOrderCardImpl> listOpWorkOrderCard3 = opWorkOrderCardConditionMapper.selectOpWorkOrderCardCountSubDetail(opWorkOrderCard2);
		if(null != listOpWorkOrderCard3 && listOpWorkOrderCard3.size() > 0) {
			// 批量更新设备中单表状态
			OpWorkOrderCardImpl opWorkOrderInner3 = new OpWorkOrderCardImpl();
			opWorkOrderInner3.setModifiedUser(userId);
			opWorkOrderInner3.setModifiedIp(modifiedIp);
			opWorkOrderInner3.setListOpWorkOrderCard(listOpWorkOrderCard3);
			opWorkOrderCardConditionMapper.updateByMidOpWrokOrderSubCard(opWorkOrderInner3);
		}
		
		// 3 如果为副牌 那么设备副牌数量减一即可
		OpWorkOrderCardImpl opWorkOrderCard4 = new OpWorkOrderCardImpl();
		opWorkOrderCard4.setIdOpWorkOrder(idOpWorkOrder);
		// 查询为副牌的设备
		List<OpWorkOrderCardImpl> listOpWorkOrderCard4 = opWorkOrderCardConditionMapper.selectOpWorkOrderCardCountSubSecond(opWorkOrderCard4);
		
		if(null != listOpWorkOrderCard4 && listOpWorkOrderCard4.size() > 0) {
			// 批量更新设备中单表状态
			OpWorkOrderCardImpl opWorkOrderInner4 = new OpWorkOrderCardImpl();
			opWorkOrderInner4.setModifiedUser(userId);
			opWorkOrderInner4.setModifiedIp(modifiedIp);
			opWorkOrderInner4.setListOpWorkOrderCard(listOpWorkOrderCard4);
			opWorkOrderCardConditionMapper.updateByMidOpWrokOrderSubSecond(opWorkOrderInner4);
		}
		

		OpWorkOrderImpl opWorkOrderInner = new OpWorkOrderImpl();
		opWorkOrderInner.setStatus("re");
		opWorkOrderInner.setStatusComplete("co");
		opWorkOrderInner.setModifiedDate(new Date());
		opWorkOrderInner.setModifiedUser(userId);
		opWorkOrderInner.setModifiedIp(modifiedIp);
		opWorkOrderInner.setId(idOpWorkOrder);
		opWorkOrderMapper.update(opWorkOrderInner);
		
		
//		if(null != listOpWorkOrder && listOpWorkOrder.size() > 0) {
//			OpWorkOrderImpl opWorkOrderInner = new OpWorkOrderImpl();
//			// 驳回
//			opWorkOrderInner.setStatus("re");
//			opWorkOrderInner.setStatusComplete("co");
//			opWorkOrderInner.setModifiedDate(new Date());
//			opWorkOrderInner.setModifiedUser(userId);
//			opWorkOrderInner.setModifiedIp(modifiedIp);
//			opWorkOrderInner.setListOpWorkOrder(listOpWorkOrder);
//			opWorkOrderConditionMapper.updateRejectOpWorkOrderByMid(opWorkOrderInner);
//			
//			// 恢复设备状态
//			// 查询主工单对应的所有设备
//			OpDeviceImpl opDeviceInner = new OpDeviceImpl();
//			opDeviceInner.setIdOpWorkOrder(idOpWorkOrder);
//			List<OpDeviceImpl> listOpDevice = opDeviceConditionMapper.searchOpDeviceByMidOpWorkOrder(opDeviceInner);
//			opDeviceInner.setListOpDevice(listOpDevice);
//			// 初始化关联的所有设备
//			opDeviceConditionMapper.updateOpDeviceRejectByMidOpWorkOrder(opDeviceInner);
//			
//			// 删除所有工单中单表
//			OpWorkOrderCardImpl opWorkOrderInner2 = new OpWorkOrderCardImpl();
//			opWorkOrderInner2.setModifiedUser(userId);
//			opWorkOrderInner2.setModifiedIp(modifiedIp);
//			opWorkOrderInner2.setListOpWorkOrder(listOpWorkOrder);
//			opWorkOrderInner2.setIdOpDevice(0L);
//			opWorkOrderInner2.setMidOpWorkOrder(0L);
//			opWorkOrderCardConditionMapper.updateByMidOpWrokOrder(opWorkOrderInner2);
//			
//			opDeviceGroup.setListOpWorkOrder(listOpWorkOrder);
//		}
		
//		opWorkOrder.setIdMaCompany(idMaCompany);
//		opWorkOrder.setStatus("re");//驳回
//		opWorkOrder.setModifiedDate(new Date());
//		opWorkOrder.setModifiedUser(userId);
//		this.setCommonField(opWorkOrder);
//		opWorkOrderMapper.update(opWorkOrder);
		//主电室拒绝必然是主工单，故使用工单ID查询关联的工单中间表及设备
//		//查询工单中间表 设置驳回
//		OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
//		opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
//		//opWorkOrderCard.setIdOpDevice(idOpDevice);
//		opWorkOrderCard.setIdMaCompany(idMaCompany);
//		opWorkOrderCard.setGroup("id");
//		List<OpWorkOrderCardImpl>list = (List<OpWorkOrderCardImpl>)opWorkOrderCardMapper.select(opWorkOrderCard);
//		for(int i=0;i<list.size(); i++) {
//			OpWorkOrderCardImpl opWorkOrderCardImpl = list.get(i);
//			OpWorkOrderCardImpl opWorkOrderCardImpl2 = new OpWorkOrderCardImpl();
//			opWorkOrderCardImpl2.setId(opWorkOrderCardImpl.getId());
//			opWorkOrderCardImpl2.setStatusOpCardOperation("re");//驳回
//			opWorkOrderCardImpl2.setModifiedDate(new Date());
//			opWorkOrderCardImpl2.setModifiedUser(userId);
//			this.setCommonField(opWorkOrderCardImpl2);
//			opWorkOrderCardMapper.update(opWorkOrderCardImpl2);
//			
//			//查询工单中间表关联的设备
//			//设备主键初始化
//			Long idOpDevice = opWorkOrderCardImpl.getIdOpDevice();
//			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
//			opDeviceImpl.setId(idOpDevice);
//			OpDeviceImpl getOpDevice = (OpDeviceImpl)opDeviceMapper.get(opDeviceImpl);
//			String opRoomOperation = getOpDevice.getStatusOpRoomOperation();
//			Long opdeviceId = getOpDevice.getId();
//			OpDeviceImpl innnerOpDevice = new OpDeviceImpl();
//			innnerOpDevice.setId(opdeviceId);
//			innnerOpDevice.setMidOpWorkOrderCard(0L);
//			innnerOpDevice.setMidOpWorkOrder(0L);
//			if(!"ws".equals(opRoomOperation)) {//操作室状态是否为待发牌
//				innnerOpDevice.setStatusOpRoomOperation("or");
//			}
//			innnerOpDevice.setStatusOpRoomElectric("or");
//			innnerOpDevice.setCountSubOpCard(0);
//			innnerOpDevice.setCountOpCard(0);
//			innnerOpDevice.setCountMainOpCard(0);
//			innnerOpDevice.setStatus("on");
//			innnerOpDevice.setModifiedDate(new Date());
//			innnerOpDevice.setModifiedUser(userId);
//			this.setCommonField(innnerOpDevice);
//			opDeviceMapper.update(innnerOpDevice);
//		}
		return opDeviceGroup;
	}
	
	/**
	 * 发牌（送电牌）
	 * @param requestJson
	 * @throws CoException
	 
	public void updateGrantSendPowerCardsForOrder(JSONObject requestJson) throws CoException{
		
	}*/
}