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
import com.framework.dao.OpDeviceElectricianGroupMapper;
import com.framework.bean.impl.OpDeviceElectricianGroupImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceElectricianGroupService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 设备电工组
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("opDeviceElectricianGroupService")
public class OpDeviceElectricianGroupServiceImpl extends CommonServiceImpl implements OpDeviceElectricianGroupService {
	@Autowired
	private OpDeviceElectricianGroupMapper opDeviceElectricianGroupMapper;

	@Autowired
	public OpDeviceElectricianGroupMapper getOpDeviceElectricianGroupMapper() {
		return opDeviceElectricianGroupMapper;
	}

	@Autowired
	public void setOpDeviceElectricianGroupMapper(OpDeviceElectricianGroupMapper opDeviceElectricianGroupMapper) {
		this.opDeviceElectricianGroupMapper = opDeviceElectricianGroupMapper;
	}

	/**
	 * 新增实体对象
	 * @param opDeviceElectricianGroup
	 */
	public OpDeviceElectricianGroupImpl insert(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		this.opDeviceElectricianGroupMapper.insert(opDeviceElectricianGroup);
		return opDeviceElectricianGroup;
	}

	/**
	 * 删除实体对象
	 * @param opDeviceElectricianGroup
	 */
	public int delete(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		return this.opDeviceElectricianGroupMapper.delete(opDeviceElectricianGroup);
	}

	/**
	 * 更新实体对象
	 * @param opDeviceElectricianGroup
	 */
	public boolean update(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		boolean result = true;
		this.opDeviceElectricianGroupMapper.update(opDeviceElectricianGroup);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDeviceElectricianGroup
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceElectricianGroupImpl> select(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		return (List<OpDeviceElectricianGroupImpl>) this.opDeviceElectricianGroupMapper.select(opDeviceElectricianGroup);
	}

	/**
	 * 查询单个实体
	 * @param opDeviceElectricianGroup
	 */
	public OpDeviceElectricianGroupImpl get(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		return (OpDeviceElectricianGroupImpl) this.opDeviceElectricianGroupMapper.get(opDeviceElectricianGroup);
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
	public PageInfo<OpDeviceElectricianGroupImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceElectricianGroupMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceElectricianGroupImpl> page = new PageInfo<OpDeviceElectricianGroupImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDeviceElectricianGroup
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceElectricianGroupImpl> selectTree(OpDeviceElectricianGroupImpl opDeviceElectricianGroup) throws CoException {
		return (List<OpDeviceElectricianGroupImpl>) this.opDeviceElectricianGroupMapper.selectTree(opDeviceElectricianGroup);
	}
}