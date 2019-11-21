package com.framework.service;

import java.util.List;

import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.bean.impl.SyNotificationImpl;
import com.framework.exception.CoException;
import com.github.pagehelper.PageInfo;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 提示推送
 * 作者:     Auto
 * 日期:     2019/8/26
**********************************************
*/
public interface SyNotificationService {
	/**
	 * 新增实体对象
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl insert(SyNotificationImpl syNotification) throws CoException;
	
	/**
	 * 根据工单状态发送推送信息
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl insert(OpWorkOrderImpl opWorkOrder) throws CoException;
	
	/**
	 * 根据工单状态发送推送信息
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl insert(Object obj) throws CoException;	

	/**
	 * 删除实体对象
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public int delete(SyNotificationImpl syNotification) throws CoException;

	/**
	 * 更新实体对象
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public boolean update(SyNotificationImpl syNotification) throws CoException;

	/**
	 * 查询实体列表
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public List<SyNotificationImpl> select(SyNotificationImpl syNotification) throws CoException;

	/**
	 * 取得单一对象
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl get(SyNotificationImpl syNotification) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<SyNotificationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public List<SyNotificationImpl> selectTree(SyNotificationImpl syNotification) throws CoException;
	
	/**
	 *  发送推送信息
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl sendNotification(SyNotificationImpl syNotification) throws CoException;

}