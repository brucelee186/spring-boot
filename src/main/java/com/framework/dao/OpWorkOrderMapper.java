package com.framework.dao;

import com.framework.dao.common.CommonMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.framework.exception.CoException;
import com.framework.bean.impl.OpWorkOrderImpl;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 工单管理
 * 作者:    Auto
 * 日期:    2019/9/29
 **********************************************
 */
public interface OpWorkOrderMapper extends CommonMapper {

	/**
	 * 新增实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public Long insert(OpWorkOrderImpl opWorkOrder);

	/**
	 * 删除实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public int delete(OpWorkOrderImpl opWorkOrder);

	/**
	 * 更新实体对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public int update(OpWorkOrderImpl opWorkOrder);

	/**
	 * 查询实体列表
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> select(OpWorkOrderImpl opWorkOrder);

	/**
	 * 查询实体对象记录数
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public int count(OpWorkOrderImpl opWorkOrder);

	/**
	 * 取得单一对象
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderImpl get(OpWorkOrderImpl opWorkOrder);

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

	/**
	 * 查询树型实体
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderImpl> selectTree(OpWorkOrderImpl opWorkOrder);

}