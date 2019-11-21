package com.framework.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.framework.exception.CoException;
import com.framework.dao.OpDeviceConditionMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.dao.OpProUnitMapper;
import com.framework.dao.OpWorkOrderCardConditionMapper;
import com.framework.dao.OpWorkOrderConditionMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.framework.bean.common.Cons;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpProUnitImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpWorkOrderService;
import com.framework.service.SyLogService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 工单管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
@Service("opWorkOrderService")
public class OpWorkOrderServiceImpl extends CommonServiceImpl implements OpWorkOrderService {
	@Autowired
	private OpWorkOrderMapper opWorkOrderMapper;
	
	@Autowired
	private OpDeviceMapper opDeviceMapper;
	
	@Autowired
	private OpProUnitMapper opProUnitMapper;

	@Autowired
	private OpWorkOrderConditionMapper opWorkOrderConditionMapper;
	
	@Autowired
	private OpDeviceConditionMapper opDeviceConditionMapper;
	
	@Autowired
	private OpWorkOrderCardConditionMapper opWorkOrderCardConditionMapper;
	
	@Autowired
	private SyLogService syLogService;
	
	@Autowired
	public OpWorkOrderMapper getOpWorkOrderMapper() {
		return opWorkOrderMapper;
	}

	@Autowired
	public void setOpWorkOrderMapper(OpWorkOrderMapper opWorkOrderMapper) {
		this.opWorkOrderMapper = opWorkOrderMapper;
	}

	/**
	 * 新增实体对象
	 * @param opWorkOrder
	 */
	public OpWorkOrderImpl insert(OpWorkOrderImpl opWorkOrder) throws CoException {
		this.opWorkOrderMapper.insert(opWorkOrder);
		return opWorkOrder;
	}

	/**
	 * 删除实体对象
	 * @param opWorkOrder
	 */
	public int delete(OpWorkOrderImpl opWorkOrder) throws CoException {
		Long id = opWorkOrder.getId();
		if(null != id) {
			opWorkOrder.setTag("d");
			return this.opWorkOrderMapper.update(opWorkOrder);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param opWorkOrder
	 */
	public boolean update(OpWorkOrderImpl opWorkOrder) throws CoException {
		boolean result = true;
		this.opWorkOrderMapper.update(opWorkOrder);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opWorkOrder
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderImpl> select(OpWorkOrderImpl opWorkOrder) throws CoException {
		return (List<OpWorkOrderImpl>) this.opWorkOrderMapper.select(opWorkOrder);
	}

	/**
	 * 查询单个实体
	 * @param opWorkOrder
	 */
	public OpWorkOrderImpl get(OpWorkOrderImpl opWorkOrder) throws CoException {
		return (OpWorkOrderImpl) this.opWorkOrderMapper.get(opWorkOrder);
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
	public PageInfo<OpWorkOrderImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opWorkOrderMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpWorkOrderImpl> page = new PageInfo<OpWorkOrderImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opWorkOrder
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderImpl> selectTree(OpWorkOrderImpl opWorkOrder) throws CoException {
		return (List<OpWorkOrderImpl>) this.opWorkOrderMapper.selectTree(opWorkOrder);
	}
	
	/**
	 * 自动赋值
	 * @param opWorkOrder
	 */
	private void setCommonField(OpWorkOrderImpl opWorkOrder) {
	}
	
	public List<OpWorkOrderImpl> getIdOpDeviceToOpWorkOrder(OpWorkOrderImpl opWorkOrder) throws CoException{
		return (List<OpWorkOrderImpl>) this.opWorkOrderConditionMapper.getIdOpDeviceToOpWorkOrder(opWorkOrder);
	}
	
	/**
	 * 根据设备主键查主电室工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getOrdersPowersOffice(OpWorkOrderImpl opWorkOrder) throws CoException{
		return (List<OpWorkOrderImpl>) this.opWorkOrderConditionMapper.getOrdersPowersOffice(opWorkOrder);
	}

	
	/**
	 *	 查询送电序列
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getOrdersPowersSend(OpWorkOrderImpl opWorkOrder) throws CoException{
		return (List<OpWorkOrderImpl>) this.opWorkOrderConditionMapper.getOrdersPowersSend(opWorkOrder);
	}
	
	/**
	 * 	查询还牌序列
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getReturnCardOrders(OpWorkOrderImpl opWorkOrder) throws CoException{
		return (List<OpWorkOrderImpl>) this.opWorkOrderConditionMapper.getReturnCardOrders(opWorkOrder);
	}

	/**
	 * 查询电工历史工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> selectHistoryOpWorkOrder(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException{
		return (List<OpWorkOrderImpl>) this.opWorkOrderConditionMapper.selectHistoryOpWorkOrder(opDeviceElectricianCard);
	}

	/**
	 * 批量更新停电完成工单
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public boolean updateWorkOrderPo(OpWorkOrderImpl opWorkOrder) throws CoException {
		boolean res = true;
		// 只更新开关状态，考虑到并发问题，剩下的状态更新采用计划任务，暂定10秒一查询并更新。
		List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderConditionMapper.selectPoWorkOrder(opWorkOrder);
		try {
			if(null != listOpWorkOrder && listOpWorkOrder.size() > 0) {
				ArrayList<Long> arrListId = new ArrayList<Long>();
				for (int i = 0; i < listOpWorkOrder.size(); i++) {
					opWorkOrder = new OpWorkOrderImpl();
					opWorkOrder = listOpWorkOrder.get(i);
					Long idOpWorkOrder = opWorkOrder.getId();
					arrListId.add(idOpWorkOrder);
				}
				
				// 批量更新已经停电的工单
				OpWorkOrderImpl opWorkOrderPoBatch = new OpWorkOrderImpl();
				opWorkOrderPoBatch.setListOpWorkOrder(listOpWorkOrder);
				opWorkOrderPoBatch.setArrListId(arrListId);
				opWorkOrderPoBatch.setModifiedDate(new Date());
				opWorkOrderPoBatch.setModifiedUser(Cons.ID_SYS);
				opWorkOrderPoBatch.setModifiedIp(Cons.IP_SYS);
				
				// 停电完成:po(power outage)
				opWorkOrderPoBatch.setStatus("po");
				opWorkOrderConditionMapper.updateWorkOrderPo(opWorkOrderPoBatch);
				
				// 创建日志
				for (int i = 0; i < listOpWorkOrder.size(); i++) {
					opWorkOrder = new OpWorkOrderImpl();
					opWorkOrder = listOpWorkOrder.get(i);
					opWorkOrder.setStatus("po");
					syLogService.insert(opWorkOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	/**
	 * 查询所有参与送断电的工单
	 */
	public List<OpWorkOrderImpl> searchWorkOrderCabinet(OpWorkOrderImpl opWorkOrder) throws CoException {
		return opWorkOrderConditionMapper.searchWorkOrderCabinet(opWorkOrder);
	}

	/**
	 * 查询首页工单列表
	 * @param opWorkOrderImpl
	 * @return
	 */
	public List<OpWorkOrderImpl> getOpreatePage(OpWorkOrderImpl opWorkOrderImpl) throws CoException{
		return opWorkOrderConditionMapper.getOpreatePage(opWorkOrderImpl);
	}
	
	/**
	 * 查询历史工单
	 * @param opWorkOrderImpl
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getHistoryOpWorkOrder(OpWorkOrderImpl opWorkOrderImpl) throws CoException{
		return opWorkOrderConditionMapper.getHistoryOpWorkOrder(opWorkOrderImpl);
	}
	
	/**
	 * 查询工单详细
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderImpl getOpreaterDetailInfo(OpWorkOrderImpl opWorkOrder) throws CoException{
		return opWorkOrderConditionMapper.getOpreaterDetailInfo(opWorkOrder);
	}
	
	/**
	 * 更新工单状态
	 * @param opWorkOrder
	 * @throws CoException
	 */
	public void updateOpWorkOrderStatus(OpWorkOrderImpl opWorkOrder) throws CoException{
		
		opWorkOrderMapper.update(opWorkOrder);
		Long idOpWorkOrder  = opWorkOrder.getId();
		OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
		opWorkOrderImpl.setId(idOpWorkOrder);
		List<OpDeviceImpl> list = opDeviceConditionMapper.selectOpWorkOrderContainsDevice(opWorkOrderImpl);
		for (int i = 0; i < list.size(); i++) {
			OpDeviceImpl opDeviceImpl = list.get(i);
			String statusOpRoomOperation  = opDeviceImpl.getStatusOpRoomOperation();
			if(!"wr".equals(statusOpRoomOperation)) {
				opDeviceImpl.setStatusOpRoomOperation("wr");
				opDeviceImpl.setModifiedDate(new Date());
				String modifiedUser = opWorkOrder.getModifiedUser();
				opDeviceImpl.setModifiedUser(modifiedUser);
				opDeviceMapper.update(opDeviceImpl);
			}
		}
		OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
	}
	
	/**
	 * 查询该工单是否被其他操作室还牌
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> getTheOpWorkOrderToOtherReturn(OpWorkOrderImpl opWorkOrder) throws CoException{
		return opWorkOrderConditionMapper.getTheOpWorkOrderToOtherReturn(opWorkOrder);
	}
	
	/**
	 * //工单跨操作室发牌，部分牌发放完成，不可以拒绝发牌
	 * @param opWorkOrderCard
	 * @return
	 */
	public List<OpWorkOrderCardImpl> returnTheOpWorkOrderGrantCard(OpWorkOrderCardImpl opWorkOrderCard) throws CoException{
		return opWorkOrderCardConditionMapper.returnTheOpWorkOrderGrantCard(opWorkOrderCard);
	}

}