package com.framework.dao;

import java.util.List;

import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.common.BaseMapper;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 工单牌
 * 作者:    Auto
 * 日期:    2019/8/21
 **********************************************
 */
public interface OpWorkOrderCardConditionMapper extends BaseMapper {

	//查询设备关联的工单表的中间表数量
	List<OpWorkOrderCardImpl> selectOpWorkOrderCard(OpWorkOrderCardImpl opWorkOrderCardImpl);

	//查询主工单的中间表总数
	List<OpWorkOrderImpl> selectOpWorkOrder(OpWorkOrderCardImpl opWorkOrderCardImpl);

	/**
	 * 根据主工单id查询所有工单
	 * @param selOpWorkOrderCard
	 * @return
	 */
	List<OpWorkOrderCardImpl> selectOpWorkOrderCardList(OpWorkOrderCardImpl selOpWorkOrderCard);

	/**
	 * //工单跨操作室发牌，部分牌发放完成，不可以拒绝发牌
	 * @param opWorkOrderCard
	 * @return
	 */
	List<OpWorkOrderCardImpl> returnTheOpWorkOrderGrantCard(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 删除驳回工单对应的中单表
	int deleteByMidOpWrokOrder(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 删除驳回工单对应的中单表
	int updateByMidOpWrokOrder(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 取得无副牌的设备工单中间表
	List<OpWorkOrderCardImpl> selectOpWorkOrderCardCountSubZero(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 查询主工单下还有副工单的工单设备中单表
	List<OpWorkOrderCardImpl> selectOpWorkOrderCardCountSubLargerThanOne(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 查询当前工单对应的副工单(副牌大于1的情况下一定会有副工单)
	List<OpWorkOrderCardImpl> selectOpWorkOrderCardCountSubDetail(OpWorkOrderCardImpl opWorkOrderCard);

	// 删除驳回工单对应的中单表
	int updateByMidOpWrokOrderSubCard(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 查询为副牌的设备
	List<OpWorkOrderCardImpl> selectOpWorkOrderCardCountSubSecond(OpWorkOrderCardImpl opWorkOrderCard);
	
	// 如果为副牌数量减1即可
	int updateByMidOpWrokOrderSubSecond(OpWorkOrderCardImpl opWorkOrderCard);
}