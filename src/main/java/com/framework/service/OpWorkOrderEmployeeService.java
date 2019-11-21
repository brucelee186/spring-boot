package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.alibaba.fastjson.JSONArray;
import com.framework.bean.OpWorkOrder;
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 工单组队员工
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
public interface OpWorkOrderEmployeeService {
	/**
	 * 新增实体对象
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderEmployeeImpl insert(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 删除实体对象
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public int delete(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 更新实体对象
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public boolean update(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 查询实体列表
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderEmployeeImpl> select(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 取得单一对象
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public OpWorkOrderEmployeeImpl get(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<OpWorkOrderEmployeeImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param opWorkOrderEmployee
	 * @return
	 * @throws CoException
	 */
	public List<OpWorkOrderEmployeeImpl> selectTree(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException;

	/**
	 * 修改工单状态并插入工单组队队员
	 * @param opWorkOrder
	 * @param opWorkOrderEmployee
	 * @param iArr
	 * @param uArr
	 * @throws CoException
	 */
	public void editOpWorkOrder(OpWorkOrderImpl opWorkOrder,OpWorkOrderEmployeeImpl opWorkOrderEmployee,JSONArray iArr,JSONArray uArr) throws CoException;

	/**
	 * 更新员工工时
	 * @param requestParams
	 * @throws CoException
	 */
	public void editEmployeeWorkTime(String requestParams) throws CoException;

	/**
	 *  查询工单详细工时
	 * @param requestParams
	 * @return
	 */
	public List<OpWorkOrderEmployeeImpl> selectEmployeeWorkTimeDetail(OpWorkOrderEmployeeImpl opWorkOrderEmployee);
}