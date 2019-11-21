package com.framework.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpDeviceGroupImpl;
import com.framework.bean.impl.OpRoomElectricImpl;
import com.framework.exception.CoException;
import com.github.pagehelper.PageInfo;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 电器室管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
public interface OpRoomElectricService {
	/**
	 * 新增实体对象
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public OpRoomElectricImpl insert(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 删除实体对象
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public int delete(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 更新实体对象
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 查询实体列表
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public List<OpRoomElectricImpl> select(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 取得单一对象
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public OpRoomElectricImpl get(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpRoomElectricImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opRoomElectric
	 * @return
	 * @throws CoException
	 */
	public List<OpRoomElectricImpl> selectTree(OpRoomElectricImpl opRoomElectric) throws CoException;

	/**
	 * 发牌（停电牌）
	 * @param requestJson
	 * @throws CoException
	 */
	public OpDeviceGroupImpl updateGrantPowerCardsForOrder(JSONObject requestJson) throws CoException;

	/**
	 * 主电室发牌驳回
	 * @param requestJson
	 * @return
	 */
	public OpDeviceGroupImpl updateReturnPowerCardsForOrder(JSONObject requestJson, HttpServletRequest request) throws CoException;
	
	/**
	 * 发牌（送电牌）
	 * @param requestJson
	 * @throws CoException
	 
	public void updateGrantSendPowerCardsForOrder(JSONObject requestJson) throws CoException;
*/
}