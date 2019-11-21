package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpRoomOperationImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 操作室
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
public interface OpRoomOperationService {
	/**
	 * 新增实体对象
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public OpRoomOperationImpl insert(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 删除实体对象
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public int delete(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 更新实体对象
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 查询实体列表
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public List<OpRoomOperationImpl> select(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 取得单一对象
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public OpRoomOperationImpl get(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpRoomOperationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opRoomOperation
	 * @return
	 * @throws CoException
	 */
	public List<OpRoomOperationImpl> selectTree(OpRoomOperationImpl opRoomOperation) throws CoException;

	/**
	 * 发牌
	 * @param requestJson
	 * @throws CoException
	 */
	public void updateGrantCardsForOrder(JSONObject requestJson) throws CoException;

	/**
	 * 批量更新设备状态
	 * @param requestJson
	 * @throws CoException
	 */
	public boolean updateOpDeviceStatus(OpDeviceImpl opDevice) throws CoException;

	/**
	 * 手机app点击还牌，执行一次性将工单所有的牌全部还完
	 * @param requestJson
	 * @throws CoException
	 */
	public void appReturnBackOpDeviceInfo(JSONObject requestJson) throws CoException;

	/**
	 * 操作室发牌驳回
	 * @param requestJson
	 * @throws CoException
	 */
	public void updateReturntCardsForOrder(JSONObject requestJson) throws CoException;
}