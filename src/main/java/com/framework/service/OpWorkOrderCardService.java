package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpWorkOrderCardImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 工单牌
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
public interface OpWorkOrderCardService {
	/**
	 * 新增实体对象
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderCardImpl insert(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 删除实体对象
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public int delete(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 更新实体对象
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 查询实体列表
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderCardImpl> select(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 取得单一对象
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderCardImpl get(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpWorkOrderCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opWorkOrderCard
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderCardImpl> selectTree(OpWorkOrderCardImpl opWorkOrderCard) throws CoException;

	/**
	 * 新增工单牌信息
	 * @param requestJson
	 * @throws CoException
	 */
	public void editWorkOderCardInfo(JSONObject requestJson) throws CoException;

	/**
	 * 查询工单及设备信息
	 * @param requestJson
	 * @throws CoException
	 */
	public JSONObject getWorkOderAndOpDeviceToOprationInfo(JSONObject requestJson) throws CoException;

	/**
	 * 查询工单及设备信息
	 * @param requestJson
	 * @throws CoException
	 */
	public JSONObject getWorkOderAndOpDeviceToStopInfo(JSONObject requestJson) throws CoException;
}