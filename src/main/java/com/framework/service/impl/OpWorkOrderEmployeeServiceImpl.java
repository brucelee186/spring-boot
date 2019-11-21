package com.framework.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;


import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.framework.exception.CoException;
import com.framework.dao.OpWorkOrderEmployeeConditionMapper;
import com.framework.dao.OpWorkOrderEmployeeMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpWorkOrderEmployeeService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 工单组队员工
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
@Service("opWorkOrderEmployeeService")
public class OpWorkOrderEmployeeServiceImpl extends CommonServiceImpl implements OpWorkOrderEmployeeService {
	@Autowired
	private OpWorkOrderEmployeeMapper opWorkOrderEmployeeMapper;

	@Autowired
	public OpWorkOrderEmployeeMapper getOpWorkOrderEmployeeMapper() {
		return opWorkOrderEmployeeMapper;
	}

	@Autowired
	public void setOpWorkOrderEmployeeMapper(OpWorkOrderEmployeeMapper opWorkOrderEmployeeMapper) {
		this.opWorkOrderEmployeeMapper = opWorkOrderEmployeeMapper;
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
	private OpWorkOrderEmployeeConditionMapper opWorkOrderEmployeeConditionMapper;
	
	public OpWorkOrderEmployeeConditionMapper getOpWorkOrderEmployeeConditionMapper() {
		return opWorkOrderEmployeeConditionMapper;
	}

	public void setOpWorkOrderEmployeeConditionMapper(
			OpWorkOrderEmployeeConditionMapper opWorkOrderEmployeeConditionMapper) {
		this.opWorkOrderEmployeeConditionMapper = opWorkOrderEmployeeConditionMapper;
	}

	/**
	 * 新增实体对象
	 * @param opWorkOrderEmployee
	 */
	public OpWorkOrderEmployeeImpl insert(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		this.opWorkOrderEmployeeMapper.insert(opWorkOrderEmployee);
		return opWorkOrderEmployee;
	}

	/**
	 * 删除实体对象
	 * @param opWorkOrderEmployee
	 */
	public int delete(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		Long id = opWorkOrderEmployee.getId();
		if(null != id) {
			opWorkOrderEmployee.setTag("d");
			return this.opWorkOrderEmployeeMapper.update(opWorkOrderEmployee);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param opWorkOrderEmployee
	 */
	public boolean update(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		boolean result = true;
		this.opWorkOrderEmployeeMapper.update(opWorkOrderEmployee);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opWorkOrderEmployee
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderEmployeeImpl> select(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		return (List<OpWorkOrderEmployeeImpl>) this.opWorkOrderEmployeeMapper.select(opWorkOrderEmployee);
	}

	/**
	 * 查询单个实体
	 * @param opWorkOrderEmployee
	 */
	public OpWorkOrderEmployeeImpl get(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		return (OpWorkOrderEmployeeImpl) this.opWorkOrderEmployeeMapper.get(opWorkOrderEmployee);
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
	public PageInfo<OpWorkOrderEmployeeImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opWorkOrderEmployeeMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpWorkOrderEmployeeImpl> page = new PageInfo<OpWorkOrderEmployeeImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opWorkOrderEmployee
	 */
	@SuppressWarnings("unchecked")
	public List<OpWorkOrderEmployeeImpl> selectTree(OpWorkOrderEmployeeImpl opWorkOrderEmployee) throws CoException {
		return (List<OpWorkOrderEmployeeImpl>) this.opWorkOrderEmployeeMapper.selectTree(opWorkOrderEmployee);
	}
	
	/**
	 * 修改工单状态并插入工单组队队员
	 * @param opWorkOrder
	 * @param opWorkOrderEmployee
	 * @param iArr
	 * @param uArr
	 * @throws CoException
	 */
	public void editOpWorkOrder(OpWorkOrderImpl opWorkOrder,OpWorkOrderEmployeeImpl opWorkOrderEmployee,JSONArray iArr,JSONArray uArr) throws CoException {
		
		
		//逻辑删除上次的数据
		for (int i = 0; i < iArr.size(); i++) {
			JSONObject item = iArr.getJSONObject(i);
			Long id = item.getLong("id");
			opWorkOrderEmployee.setId(id);
			this.opWorkOrderEmployeeMapper.delete(opWorkOrderEmployee);
		}
		//新增本次组队信息
		StringBuilder listOpWorkOrderEmployee = new StringBuilder();;
		for (int i = 0; i < uArr.size(); i++) {
			JSONObject item = uArr.getJSONObject(i);
			Long idMaUser = item.getLong("idMaUser");
			String nameMaUser = item.getString("nameMaUser");
			opWorkOrderEmployee.setIdMaUser(idMaUser);
			opWorkOrderEmployee.setNameMaUser(nameMaUser);
			opWorkOrderEmployee.setEditState("i");
			opWorkOrderEmployee.setCodeMaRole("em");
			opWorkOrderEmployee.setModifiedDate(new Date());
			//opWorkOrderEmployee.setModifiedUser(modifiedUser);
			listOpWorkOrderEmployee.append(nameMaUser+"{"+ idMaUser +"}");
			this.setCommonField(opWorkOrderEmployee);
			this.opWorkOrderEmployeeMapper.insert(opWorkOrderEmployee);
		}
		//opWorkOrder.setListOpWorkOrderEmployee(listOpWorkOrderEmployee.toString());
		this.setCommonField(opWorkOrder);
		this.opWorkOrderMapper.update(opWorkOrder);
	}
	
	/**
	 * 更新员工工时
	 * @param requestParams
	 * @throws CoException
	 */
	public void editEmployeeWorkTime(String requestParams) throws CoException {
		JSONObject requestJson = JSON.parseObject(requestParams);
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		//员工数组
		JSONArray empArray = requestJson.getJSONArray("empArray");
		//总工时
		Double hoursActual = requestJson.getDouble("hoursActual");
		for (int i = 0; i < empArray.size(); i++) {
			JSONObject item = empArray.getJSONObject(i);
			//String idString = requestJson.getString("id");
			Long id = item.getLong("id");//Long.valueOf(idString);
			double hours = item.getDouble("value");
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			opWorkOrderEmployee.setId(id);
			opWorkOrderEmployee.setHours(hours);
			opWorkOrderEmployee.setModifiedUser(userId);
			opWorkOrderEmployee.setModifiedDate(new Date());
			this.setCommonField(opWorkOrderEmployee);
			opWorkOrderEmployeeMapper.update(opWorkOrderEmployee);
		}
		OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
		//工单id
		String idString = requestJson.getString("idOpWorkOrder");
		Long idOpWorkOrder = Long.valueOf(idString);
		opWorkOrder.setId(idOpWorkOrder);
		opWorkOrder.setHoursActual(hoursActual);
		opWorkOrder.setModifiedUser(userId);
		opWorkOrder.setStatusComplete("co");
		opWorkOrder.setModifiedDate(new Date());
		this.setCommonField(opWorkOrder);
		opWorkOrderMapper.update(opWorkOrder);
	}

	/**
	 *  查询工单详细工时
	 * @param requestParams
	 * @return
	 */
	public List<OpWorkOrderEmployeeImpl> selectEmployeeWorkTimeDetail(OpWorkOrderEmployeeImpl opWorkOrderEmployee){
		return opWorkOrderEmployeeConditionMapper.selectEmployeeWorkTimeDetail(opWorkOrderEmployee);
	}
}