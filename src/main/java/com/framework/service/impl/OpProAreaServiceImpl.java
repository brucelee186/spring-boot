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
import com.framework.dao.OpProAreaMapper;
import com.framework.bean.impl.OpProAreaImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpProAreaService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 区域
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
@Service("opProAreaService")
public class OpProAreaServiceImpl extends CommonServiceImpl implements OpProAreaService {
	@Autowired
	private OpProAreaMapper opProAreaMapper;

	@Autowired
	public OpProAreaMapper getOpProAreaMapper() {
		return opProAreaMapper;
	}

	@Autowired
	public void setOpProAreaMapper(OpProAreaMapper opProAreaMapper) {
		this.opProAreaMapper = opProAreaMapper;
	}

	/**
	 * 新增实体对象
	 * @param opProArea
	 */
	public OpProAreaImpl insert(OpProAreaImpl opProArea) throws CoException {
		this.opProAreaMapper.insert(opProArea);
		return opProArea;
	}

	/**
	 * 删除实体对象
	 * @param opProArea
	 */
	public int delete(OpProAreaImpl opProArea) throws CoException {
		return this.opProAreaMapper.delete(opProArea);
	}

	/**
	 * 更新实体对象
	 * @param opProArea
	 */
	public boolean update(OpProAreaImpl opProArea) throws CoException {
		boolean result = true;
		this.opProAreaMapper.update(opProArea);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opProArea
	 */
	@SuppressWarnings("unchecked")
	public List<OpProAreaImpl> select(OpProAreaImpl opProArea) throws CoException {
		return (List<OpProAreaImpl>) this.opProAreaMapper.select(opProArea);
	}

	/**
	 * 查询单个实体
	 * @param opProArea
	 */
	public OpProAreaImpl get(OpProAreaImpl opProArea) throws CoException {
		return (OpProAreaImpl) this.opProAreaMapper.get(opProArea);
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
	public PageInfo<OpProAreaImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opProAreaMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpProAreaImpl> page = new PageInfo<OpProAreaImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opProArea
	 */
	@SuppressWarnings("unchecked")
	public List<OpProAreaImpl> selectTree(OpProAreaImpl opProArea) throws CoException {
		return (List<OpProAreaImpl>) this.opProAreaMapper.selectTree(opProArea);
	}
}