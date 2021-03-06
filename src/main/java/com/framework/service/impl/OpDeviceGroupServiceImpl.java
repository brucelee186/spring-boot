﻿package com.framework.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.framework.exception.CoException;
import com.framework.dao.OpDeviceGroupMapper;
import com.framework.bean.impl.OpDeviceGroupImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceGroupService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 设备电工组
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("opDeviceGroupService")
public class OpDeviceGroupServiceImpl extends CommonServiceImpl implements OpDeviceGroupService {
	@Autowired
	private OpDeviceGroupMapper opDeviceGroupMapper;

	@Autowired
	public OpDeviceGroupMapper getOpDeviceGroupMapper() {
		return opDeviceGroupMapper;
	}

	@Autowired
	public void setOpDeviceGroupMapper(OpDeviceGroupMapper opDeviceGroupMapper) {
		this.opDeviceGroupMapper = opDeviceGroupMapper;
	}

	/**
	 * 新增实体对象
	 * @param opDeviceGroup
	 */
	public OpDeviceGroupImpl insert(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		this.opDeviceGroupMapper.insert(opDeviceGroup);
		return opDeviceGroup;
	}

	/**
	 * 删除实体对象
	 * @param opDeviceGroup
	 */
	public int delete(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		return this.opDeviceGroupMapper.delete(opDeviceGroup);
	}

	/**
	 * 更新实体对象
	 * @param opDeviceGroup
	 */
	public boolean update(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		boolean result = true;
		this.opDeviceGroupMapper.update(opDeviceGroup);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDeviceGroup
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceGroupImpl> select(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		return (List<OpDeviceGroupImpl>) this.opDeviceGroupMapper.select(opDeviceGroup);
	}

	/**
	 * 查询单个实体
	 * @param opDeviceGroup
	 */
	public OpDeviceGroupImpl get(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		return (OpDeviceGroupImpl) this.opDeviceGroupMapper.get(opDeviceGroup);
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
	public PageInfo<OpDeviceGroupImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceGroupMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceGroupImpl> page = new PageInfo<OpDeviceGroupImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDeviceGroup
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceGroupImpl> selectTree(OpDeviceGroupImpl opDeviceGroup) throws CoException {
		return (List<OpDeviceGroupImpl>) this.opDeviceGroupMapper.selectTree(opDeviceGroup);
	}
}