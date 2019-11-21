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
import com.framework.dao.OpProLineMapper;
import com.framework.dao.OpProUnitMapper;
import com.framework.bean.impl.OpProAreaImpl;
import com.framework.bean.impl.OpProLineImpl;
import com.framework.bean.impl.OpProUnitImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpProUnitService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 机组
 * 作者:     Auto
 * 日期:     2019/8/14
**********************************************
*/
@Service("opProUnitService")
public class OpProUnitServiceImpl extends CommonServiceImpl implements OpProUnitService {
	@Autowired
	private OpProUnitMapper opProUnitMapper;
	
	@Autowired
	private OpProLineMapper opProLineMapper;
	
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

	@Autowired
	public OpProLineMapper getOpProLineMapper() {
		return opProLineMapper;
	}

	@Autowired
	public void setOpProLineMapper(OpProLineMapper opProLineMapper) {
		this.opProLineMapper = opProLineMapper;
	}

	@Autowired
	public OpProUnitMapper getOpProUnitMapper() {
		return opProUnitMapper;
	}

	@Autowired
	public void setOpProUnitMapper(OpProUnitMapper opProUnitMapper) {
		this.opProUnitMapper = opProUnitMapper;
	}

	/**
	 * 新增实体对象
	 * @param opProUnit
	 */
	public OpProUnitImpl insert(OpProUnitImpl opProUnit) throws CoException {
		setCommonFiled(opProUnit);
		this.opProUnitMapper.insert(opProUnit);
		return opProUnit;
	}

	/**
	 * 删除实体对象
	 * @param opProUnit
	 */
	public int delete(OpProUnitImpl opProUnit) throws CoException {
		return this.opProUnitMapper.delete(opProUnit);
	}

	/**
	 * 更新实体对象
	 * @param opProUnit
	 */
	public boolean update(OpProUnitImpl opProUnit) throws CoException {
		boolean result = true;
		setCommonFiled(opProUnit);
		this.opProUnitMapper.update(opProUnit);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opProUnit
	 */
	@SuppressWarnings("unchecked")
	public List<OpProUnitImpl> select(OpProUnitImpl opProUnit) throws CoException {
		return (List<OpProUnitImpl>) this.opProUnitMapper.select(opProUnit);
	}

	/**
	 * 查询单个实体
	 * @param opProUnit
	 */
	public OpProUnitImpl get(OpProUnitImpl opProUnit) throws CoException {
		return (OpProUnitImpl) this.opProUnitMapper.get(opProUnit);
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
	public PageInfo<OpProUnitImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opProUnitMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpProUnitImpl> page = new PageInfo<OpProUnitImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opProUnit
	 */
	@SuppressWarnings("unchecked")
	public List<OpProUnitImpl> selectTree(OpProUnitImpl opProUnit) throws CoException {
		return (List<OpProUnitImpl>) this.opProUnitMapper.selectTree(opProUnit);
	}
	
	private void setCommonFiled(OpProUnitImpl opProUnit) {
		Long idOpProArea = opProUnit.getIdOpProArea();
		OpProAreaImpl opProArea = new OpProAreaImpl();
		opProArea.setId(idOpProArea);
		opProArea = (OpProAreaImpl) opProAreaMapper.get(opProArea);
		Long idOpProLine = opProArea.getIdOpProLine();
		opProUnit.setIdOpProLine(idOpProLine);
		
	}
}