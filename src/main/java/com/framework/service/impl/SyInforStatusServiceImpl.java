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
import com.framework.dao.SyInforStatusMapper;
import com.framework.bean.impl.SyInforStatusImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.SyInforStatusService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统状态信息
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("syInforStatusService")
public class SyInforStatusServiceImpl extends CommonServiceImpl implements SyInforStatusService {
	@Autowired
	private SyInforStatusMapper syInforStatusMapper;

	@Autowired
	public SyInforStatusMapper getSyInforStatusMapper() {
		return syInforStatusMapper;
	}

	@Autowired
	public void setSyInforStatusMapper(SyInforStatusMapper syInforStatusMapper) {
		this.syInforStatusMapper = syInforStatusMapper;
	}

	/**
	 * 新增实体对象
	 * @param syInforStatus
	 */
	public SyInforStatusImpl insert(SyInforStatusImpl syInforStatus) throws CoException {
		this.syInforStatusMapper.insert(syInforStatus);
		return syInforStatus;
	}

	/**
	 * 删除实体对象
	 * @param syInforStatus
	 */
	public int delete(SyInforStatusImpl syInforStatus) throws CoException {
		return this.syInforStatusMapper.delete(syInforStatus);
	}

	/**
	 * 更新实体对象
	 * @param syInforStatus
	 */
	public boolean update(SyInforStatusImpl syInforStatus) throws CoException {
		boolean result = true;
		this.syInforStatusMapper.update(syInforStatus);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syInforStatus
	 */
	@SuppressWarnings("unchecked")
	public List<SyInforStatusImpl> select(SyInforStatusImpl syInforStatus) throws CoException {
		return (List<SyInforStatusImpl>) this.syInforStatusMapper.select(syInforStatus);
	}

	/**
	 * 查询单个实体
	 * @param syInforStatus
	 */
	public SyInforStatusImpl get(SyInforStatusImpl syInforStatus) throws CoException {
		return (SyInforStatusImpl) this.syInforStatusMapper.get(syInforStatus);
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
	public PageInfo<SyInforStatusImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syInforStatusMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyInforStatusImpl> page = new PageInfo<SyInforStatusImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syInforStatus
	 */
	@SuppressWarnings("unchecked")
	public List<SyInforStatusImpl> selectTree(SyInforStatusImpl syInforStatus) throws CoException {
		return (List<SyInforStatusImpl>) this.syInforStatusMapper.selectTree(syInforStatus);
	}
}