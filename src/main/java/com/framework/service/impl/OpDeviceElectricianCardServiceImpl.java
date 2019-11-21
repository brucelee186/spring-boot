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
import com.framework.dao.OpDeviceElectricianCardConditionMapper;
import com.framework.dao.OpDeviceElectricianCardMapper;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceElectricianCardService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 设备电工牌
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("opDeviceElectricianCardService")
public class OpDeviceElectricianCardServiceImpl extends CommonServiceImpl implements OpDeviceElectricianCardService {
	@Autowired
	private OpDeviceElectricianCardMapper opDeviceElectricianCardMapper;

	@Autowired
	public OpDeviceElectricianCardMapper getOpDeviceElectricianCardMapper() {
		return opDeviceElectricianCardMapper;
	}

	@Autowired
	public void setOpDeviceElectricianCardMapper(OpDeviceElectricianCardMapper opDeviceElectricianCardMapper) {
		this.opDeviceElectricianCardMapper = opDeviceElectricianCardMapper;
	}

	@Autowired
	private OpDeviceElectricianCardConditionMapper opDeviceElectricianCardConditionMapper;
	
	public OpDeviceElectricianCardConditionMapper getOpDeviceElectricianCardConditionMapper() {
		return opDeviceElectricianCardConditionMapper;
	}

	public void setOpDeviceElectricianCardConditionMapper(
			OpDeviceElectricianCardConditionMapper opDeviceElectricianCardConditionMapper) {
		this.opDeviceElectricianCardConditionMapper = opDeviceElectricianCardConditionMapper;
	}

	/**
	 * 新增实体对象
	 * @param opDeviceElectricianCard
	 */
	public OpDeviceElectricianCardImpl insert(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		this.opDeviceElectricianCardMapper.insert(opDeviceElectricianCard);
		return opDeviceElectricianCard;
	}

	/**
	 * 删除实体对象
	 * @param opDeviceElectricianCard
	 */
	public int delete(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		return this.opDeviceElectricianCardMapper.delete(opDeviceElectricianCard);
	}

	/**
	 * 更新实体对象
	 * @param opDeviceElectricianCard
	 */
	public boolean update(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		boolean result = true;
		this.opDeviceElectricianCardMapper.update(opDeviceElectricianCard);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDeviceElectricianCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceElectricianCardImpl> select(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		return (List<OpDeviceElectricianCardImpl>) this.opDeviceElectricianCardMapper.select(opDeviceElectricianCard);
	}

	/**
	 * 查询单个实体
	 * @param opDeviceElectricianCard
	 */
	public OpDeviceElectricianCardImpl get(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		return (OpDeviceElectricianCardImpl) this.opDeviceElectricianCardMapper.get(opDeviceElectricianCard);
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
	public PageInfo<OpDeviceElectricianCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceElectricianCardMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceElectricianCardImpl> page = new PageInfo<OpDeviceElectricianCardImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDeviceElectricianCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceElectricianCardImpl> selectTree(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException {
		return (List<OpDeviceElectricianCardImpl>) this.opDeviceElectricianCardMapper.selectTree(opDeviceElectricianCard);
	}
	
	/**
	 * 查询手机端停电序列送电序列
	 * @param opDeviceElectricianCard
	 * @return
	 * @throws CoException
	 */
	public List<OpDeviceElectricianCardImpl> getStopOrSendPower(OpDeviceElectricianCardImpl opDeviceElectricianCard) throws CoException{
		return (List<OpDeviceElectricianCardImpl>) this.opDeviceElectricianCardConditionMapper.getStopOrSendPower(opDeviceElectricianCard);
	}
}