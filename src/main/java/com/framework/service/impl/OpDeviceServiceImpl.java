package com.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.common.Cons;
import com.framework.bean.impl.OpCardOperationImpl;
import com.framework.bean.impl.OpCardPowerImpl;
import com.framework.bean.impl.OpCardWorkImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.OpCardOperationMapper;
import com.framework.dao.OpCardPowerMapper;
import com.framework.dao.OpCardWorkMapper;
import com.framework.dao.OpDeviceConditionMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.dao.OpWorkOrderCardMapper;
import com.framework.dao.OpWorkOrderConditionMapper;
import com.framework.exception.CoException;
import com.framework.service.OpDeviceService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 卡牌管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
@Service("opDeviceService")
public class OpDeviceServiceImpl extends CommonServiceImpl implements OpDeviceService {
	@Autowired
	private OpDeviceMapper opDeviceMapper;
	
	@Autowired
	private OpDeviceConditionMapper opDeviceConditionMapper;
	
	@Autowired
	private OpCardOperationMapper opCardOperationMapper;
	
	@Autowired
	private OpCardPowerMapper opCardPowerMapper;
	
	@Autowired
	private OpCardWorkMapper opCardWorkMapper;
	
	@Autowired
	private OpWorkOrderCardMapper opWorkOrderCardMapper;
	
	@Autowired
	private OpWorkOrderConditionMapper opWorkOrderConditionMapper;

	@Autowired
	public OpDeviceMapper getOpDeviceMapper() {
		return opDeviceMapper;
	}

	@Autowired
	public void setOpDeviceMapper(OpDeviceMapper opDeviceMapper) {
		this.opDeviceMapper = opDeviceMapper;
	}

	/**
	 * 新增实体对象
	 * @param opDevice
	 */
	public OpDeviceImpl insert(OpDeviceImpl opDevice) throws CoException {
		this.opDeviceMapper.insert(opDevice);
		this.insertOpCard(opDevice);
		return opDevice;
	}

	/**
	 * 删除实体对象
	 * @param opDevice
	 */
	public int delete(OpDeviceImpl opDevice) throws CoException {
		return this.opDeviceMapper.delete(opDevice);
	}

	/**
	 * 更新实体对象
	 * @param opDevice
	 */
	public boolean update(OpDeviceImpl opDevice) throws CoException {
		boolean result = true;
		this.opDeviceMapper.update(opDevice);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDevice
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceImpl> select(OpDeviceImpl opDevice) throws CoException {
		return (List<OpDeviceImpl>) this.opDeviceMapper.select(opDevice);
	}

	/**
	 * 查询单个实体
	 * @param opDevice
	 */
	public OpDeviceImpl get(OpDeviceImpl opDevice) throws CoException {
		return (OpDeviceImpl) this.opDeviceMapper.get(opDevice);
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
	public PageInfo<OpDeviceImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceImpl> page = new PageInfo<OpDeviceImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDevice
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceImpl> selectTree(OpDeviceImpl opDevice) throws CoException {
		return (List<OpDeviceImpl>) this.opDeviceMapper.selectTree(opDevice);
	}
	
	/**
	 * 自动添加操作牌,停电牌,工作牌
	 * @param opDevice
	 */
	private void insertOpCard(OpDeviceImpl opDevice) {
		String userId = opDevice.getCreateUser();
		Long idMaCompany = opDevice.getIdMaCompany();
		Long idOpDevice = opDevice.getId();
		OpDeviceImpl opDeviceTemp = new OpDeviceImpl();
		opDeviceTemp.setId(idOpDevice);
		// 操作室
		Long idOpRoomOperation = opDevice.getIdOpRoomOperation();
		// 主电室
		Long idOpRoomElectric = opDevice.getIdOpRoomElectric();
		// 操作牌
		OpCardOperationImpl opCardOperation = new OpCardOperationImpl();
		opCardOperation.setIdOpDevice(idOpDevice);
		opCardOperation.setIdOpRoomOperation(idOpRoomOperation);
		opCardOperation.setIdOpRoomElectric(idOpRoomElectric);
		opCardOperation.setEditState("i");
		opCardOperation.setCreateUser(userId);
		opCardOperation.setModifiedUser(userId);
		opCardOperation.setIdMaCompany(idMaCompany);
		setCommonField(opCardOperation);
		opCardOperationMapper.insert(opCardOperation);
		Long idOpCardOperation = opCardOperation.getId();
		opDeviceTemp.setIdOpCardOperation(idOpCardOperation);
		// 停电牌
		OpCardPowerImpl opCardPower = new OpCardPowerImpl();
		opCardPower.setIdOpDevice(idOpDevice);		
		opCardPower.setIdOpRoomOperation(idOpRoomOperation);
		opCardPower.setIdOpRoomElectric(idOpRoomElectric);
		opCardPower.setEditState("i");
		opCardPower.setCreateUser(userId);
		opCardPower.setModifiedUser(userId);
		opCardPower.setIdMaCompany(idMaCompany);
		setCommonField(opCardPower);
		opCardPowerMapper.insert(opCardPower);
		Long idOpCardPower = opCardPower.getId();
		opDeviceTemp.setIdOpCardPower(idOpCardPower);
		// 工作牌
		OpCardWorkImpl opCardWork = new OpCardWorkImpl();
		opCardWork.setIdOpDevice(idOpDevice);			
		opCardWork.setIdOpRoomOperation(idOpRoomOperation);
		opCardWork.setIdOpRoomElectric(idOpRoomElectric);
		opCardWork.setEditState("i");
		opCardWork.setCreateUser(userId);
		opCardWork.setModifiedUser(userId);
		opCardWork.setIdMaCompany(idMaCompany);
		setCommonField(opCardWork);
		opCardWorkMapper.insert(opCardWork);
		Long idOpCardWork = opCardWork.getId();
		opDeviceTemp.setIdOpCardWork(idOpCardWork);
		
		opDeviceTemp.setEditState("u");
		opDeviceTemp.setModifiedUser(userId);
		setCommonField(opDeviceTemp);
		opDeviceMapper.update(opDeviceTemp);
		
	}

	/**
	 * 停电
	 */
	public boolean setDevicePowerOff2(OpDeviceImpl opDevice) throws CoException {
		boolean res = true;	
		try {
			Long idOpDevice = opDevice.getId();
			opDevice.setEditState("u");
			// 更新开关为断电状态
			// 有电：on(power on),断电of(power off)
			opDevice.setStatus("of");
			setCommonField(opDevice);
			opDeviceMapper.update(opDevice);
			// 根据idOpDevice 取得所有关联工单
			// 更新工单状态为可以更新停电设备，系统计划任务会自动每30秒批量更新一次这样的工单设备停电数量。
			// 更新所有工单的停电设备数量
			//OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			//opWorkOrderCard.setIdOpDevice(idOpDevice);
			// 有电：on(power on),断电of(power off)
			//opWorkOrderCard.setStatus("of");
			//List<OpWorkOrderCardImpl> listOpWorkOrderCard = (List<OpWorkOrderCardImpl>) opWorkOrderCardMapper.select(opWorkOrderCard);
			// 通过工单统计查询出与本开关相关的所有工单，并更新【设备送停电数量】:countOpDevicePower
			// 查询出所有工单对应的开关申请数量与停电量相同的数据。并更新工单状态。
			// 申请停电设备数量与停电设备数量不相等时，不予以更新(节省资源，提升系统性能)。
			// 只更新开关状态，考虑到并发问题，剩下的状态更新采用计划任务，暂定10秒一查询并更新。
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	/**
	 * 停电
	 */
	public boolean setDevicePowerOff(OpDeviceImpl opDevice) throws CoException {
		boolean res = true;	
		try {
//			Long idOpDevice = opDevice.getId();
//			opDevice.setEditState("u");
//			// 更新开关为断电状态
//			// 有电：on(power on),断电of(power off)
//			opDevice.setStatus("of");
//			setCommonField(opDevice);
//			opDeviceMapper.update(opDevice);
			// 根据idOpDevice 取得所有关联工单
			// 更新工单状态为可以更新停电设备，系统计划任务会自动每30秒批量更新一次这样的工单设备停电数量。
			// 更新所有工单的停电设备数量
			//OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			//opWorkOrderCard.setIdOpDevice(idOpDevice);
			// 有电：on(power on),断电of(power off)
			//opWorkOrderCard.setStatus("of");
			//List<OpWorkOrderCardImpl> listOpWorkOrderCard = (List<OpWorkOrderCardImpl>) opWorkOrderCardMapper.select(opWorkOrderCard);
			// 通过工单统计查询出与本开关相关的所有工单，并更新【设备送停电数量】:countOpDevicePower
			// 查询出所有工单对应的开关申请数量与停电量相同的数据。并更新工单状态。
			// 申请停电设备数量与停电设备数量不相等时，不予以更新(节省资源，提升系统性能)。
			// 只更新开关状态，考虑到并发问题，剩下的状态更新采用计划任务，暂定10秒一查询并更新。
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderConditionMapper.selectPoWorkOrder(opWorkOrder);
			Long[] arrId = null;
			if(null != listOpWorkOrder && listOpWorkOrder.size() > 0) {
				for (int i = 0; i < listOpWorkOrder.size(); i++) {
					opWorkOrder = new OpWorkOrderImpl();
					opWorkOrder = listOpWorkOrder.get(i);
					Long idOpWorkOrder = opWorkOrder.getId();
					arrId[i] = idOpWorkOrder;
				}
			}
			
			// 批量更新已经停电的工单
			OpWorkOrderImpl opWorkOrderPoBatch = new OpWorkOrderImpl();
			opWorkOrderPoBatch.setArrId(arrId);
			opWorkOrderPoBatch.setModifiedDate(new Date());
			opWorkOrderPoBatch.setModifiedUser(Cons.ID_SYS);
			
			// 停电完成:po(power outage)
			opWorkOrderPoBatch.setStatus("po");
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	/**
	 * 更新开关柜对应的开关状态
	 * @param opDevice
	 * @return
	 * @throws CoException
	 */
	public boolean updateCabinetDevice(OpDeviceImpl opDevice) throws CoException {
		boolean res = true;
		try {
			String strOn = opDevice.getStrOn();
			String strOf = opDevice.getStrOf();
			// 更新开关状态为开
			if (null != strOn && strOn.length() > 0) {
				String[] strArrOn = strOn.split(",");
				opDevice.setStrArr(strArrOn);
				opDevice.setStatus("on");
				opDevice.setMidOpWorkOrderCard(0L);
				opDevice.setStatusOpRoomElectric("or");
				opDevice.setStatusOpRoomOperation("or");
				opDevice.setCountSubOpCard(0);
				opDevice.setCountOpCard(0);
				opDevice.setCountMainOpCard(0);
				opDevice.setMidOpWorkOrder(0L);
				opDeviceConditionMapper.updateCabinetDeviceOn(opDevice);
				// 逻辑删除工单中间表
			}
			
			// 更新开关状态为关
			if (null != strOf && strOf.length() > 0) {
				String[] strArrOf = strOf.split(",");
				opDevice.setStrArr(strArrOf);
				opDevice.setStatus("of");
				opDeviceConditionMapper.updateCabinetDeviceOff(opDevice);
			}
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 主电室查询该设备所在工单是否完全发牌完成
	 * @param opDeviceImpl
	 * @return
	 */
	public List<OpDeviceImpl> getTheDeviceToOpWorkOrder(OpDeviceImpl opDeviceImpl) throws CoException{
		OpDeviceImpl getOpDevice = opDeviceConditionMapper.getTheDeviceTomidOpWorkOrder(opDeviceImpl);
		Long midOpWorkOrder = getOpDevice.getMidOpWorkOrder();
		opDeviceImpl.setMidOpWorkOrder(midOpWorkOrder);
		return opDeviceConditionMapper.getTheDeviceToOpWorkOrder(opDeviceImpl);
	}

	/**
	 * 批量更新主电室可发牌的主工单
	 */
	public boolean updateOpDeviceOpRoomStatus(OpDeviceImpl opDevice) throws CoException {
		List<OpDeviceImpl> listOpDevice = opDeviceConditionMapper.selectOpDeviceOpRoom(opDevice);
		if(null != listOpDevice && listOpDevice.size() > 0) {
			opDevice = new OpDeviceImpl();
			opDevice.setListOpDevice(listOpDevice);
			opDeviceConditionMapper.updateOpDeviceOpRoomStatus(opDevice);
		}
		return true;
	}
	
	/**
	 * 查询工单下所有设备
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> getAllDevice(OpDeviceImpl opDeviceImpl) throws CoException{
		return opDeviceConditionMapper.getAllDevice(opDeviceImpl);
	}
	
	/**
	 * 查询工单下所有设备日志
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceImpl> getAllDeviceLog(OpDeviceImpl opDeviceImpl) throws CoException{
		return opDeviceConditionMapper.getAllDeviceLog(opDeviceImpl);
	}
}