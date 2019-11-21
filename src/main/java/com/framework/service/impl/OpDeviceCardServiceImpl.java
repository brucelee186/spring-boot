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
import com.framework.dao.OpDeviceCardMapper;
import com.framework.bean.impl.OpDeviceCardImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceCardService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 设备牌
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("opDeviceCardService")
public class OpDeviceCardServiceImpl extends CommonServiceImpl implements OpDeviceCardService {
	@Autowired
	private OpDeviceCardMapper opDeviceCardMapper;

	@Autowired
	public OpDeviceCardMapper getOpDeviceCardMapper() {
		return opDeviceCardMapper;
	}

	@Autowired
	public void setOpDeviceCardMapper(OpDeviceCardMapper opDeviceCardMapper) {
		this.opDeviceCardMapper = opDeviceCardMapper;
	}

	/**
	 * 新增实体对象
	 * @param opDeviceCard
	 */
	public OpDeviceCardImpl insert(OpDeviceCardImpl opDeviceCard) throws CoException {
		this.opDeviceCardMapper.insert(opDeviceCard);
		return opDeviceCard;
	}

	/**
	 * 删除实体对象
	 * @param opDeviceCard
	 */
	public int delete(OpDeviceCardImpl opDeviceCard) throws CoException {
		return this.opDeviceCardMapper.delete(opDeviceCard);
	}

	/**
	 * 更新实体对象
	 * @param opDeviceCard
	 */
	public boolean update(OpDeviceCardImpl opDeviceCard) throws CoException {
		boolean result = true;
		this.opDeviceCardMapper.update(opDeviceCard);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDeviceCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceCardImpl> select(OpDeviceCardImpl opDeviceCard) throws CoException {
		return (List<OpDeviceCardImpl>) this.opDeviceCardMapper.select(opDeviceCard);
	}

	/**
	 * 查询单个实体
	 * @param opDeviceCard
	 */
	public OpDeviceCardImpl get(OpDeviceCardImpl opDeviceCard) throws CoException {
		return (OpDeviceCardImpl) this.opDeviceCardMapper.get(opDeviceCard);
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
	public PageInfo<OpDeviceCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceCardMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceCardImpl> page = new PageInfo<OpDeviceCardImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDeviceCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceCardImpl> selectTree(OpDeviceCardImpl opDeviceCard) throws CoException {
		return (List<OpDeviceCardImpl>) this.opDeviceCardMapper.selectTree(opDeviceCard);
	}
}