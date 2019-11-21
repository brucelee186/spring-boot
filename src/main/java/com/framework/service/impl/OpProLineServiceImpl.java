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
import com.framework.dao.OpProLineMapper;
import com.framework.bean.impl.OpProLineImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpProLineService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 生产线
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
@Service("opProLineService")
public class OpProLineServiceImpl extends CommonServiceImpl implements OpProLineService {
	@Autowired
	private OpProLineMapper opProLineMapper;

	@Autowired
	public OpProLineMapper getOpProLineMapper() {
		return opProLineMapper;
	}

	@Autowired
	public void setOpProLineMapper(OpProLineMapper opProLineMapper) {
		this.opProLineMapper = opProLineMapper;
	}

	/**
	 * 新增实体对象
	 * @param opProLine
	 */
	public OpProLineImpl insert(OpProLineImpl opProLine) throws CoException {
		this.opProLineMapper.insert(opProLine);
		return opProLine;
	}

	/**
	 * 删除实体对象
	 * @param opProLine
	 */
	public int delete(OpProLineImpl opProLine) throws CoException {
		return this.opProLineMapper.delete(opProLine);
	}

	/**
	 * 更新实体对象
	 * @param opProLine
	 */
	public boolean update(OpProLineImpl opProLine) throws CoException {
		boolean result = true;
		this.opProLineMapper.update(opProLine);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opProLine
	 */
	@SuppressWarnings("unchecked")
	public List<OpProLineImpl> select(OpProLineImpl opProLine) throws CoException {
		return (List<OpProLineImpl>) this.opProLineMapper.select(opProLine);
	}

	/**
	 * 查询单个实体
	 * @param opProLine
	 */
	public OpProLineImpl get(OpProLineImpl opProLine) throws CoException {
		return (OpProLineImpl) this.opProLineMapper.get(opProLine);
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
	public PageInfo<OpProLineImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opProLineMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpProLineImpl> page = new PageInfo<OpProLineImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opProLine
	 */
	@SuppressWarnings("unchecked")
	public List<OpProLineImpl> selectTree(OpProLineImpl opProLine) throws CoException {
		return (List<OpProLineImpl>) this.opProLineMapper.selectTree(opProLine);
	}
}