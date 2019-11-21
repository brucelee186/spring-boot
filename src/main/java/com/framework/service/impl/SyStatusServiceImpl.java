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
import com.framework.dao.SyStatusMapper;
import com.framework.bean.impl.SyStatusImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.SyStatusService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统状态
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
@Service("syStatusService")
public class SyStatusServiceImpl extends CommonServiceImpl implements SyStatusService {
	@Autowired
	private SyStatusMapper syStatusMapper;

	@Autowired
	public SyStatusMapper getSyStatusMapper() {
		return syStatusMapper;
	}

	@Autowired
	public void setSyStatusMapper(SyStatusMapper syStatusMapper) {
		this.syStatusMapper = syStatusMapper;
	}

	/**
	 * 新增实体对象
	 * @param syStatus
	 */
	public SyStatusImpl insert(SyStatusImpl syStatus) throws CoException {
		this.syStatusMapper.insert(syStatus);
		return syStatus;
	}

	/**
	 * 删除实体对象
	 * @param syStatus
	 */
	public int delete(SyStatusImpl syStatus) throws CoException {
		Long id = syStatus.getId();
		if(null != id) {
			syStatus.setTag("d");
			return this.syStatusMapper.update(syStatus);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param syStatus
	 */
	public boolean update(SyStatusImpl syStatus) throws CoException {
		boolean result = true;
		this.syStatusMapper.update(syStatus);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syStatus
	 */
	@SuppressWarnings("unchecked")
	public List<SyStatusImpl> select(SyStatusImpl syStatus) throws CoException {
		return (List<SyStatusImpl>) this.syStatusMapper.select(syStatus);
	}

	/**
	 * 查询单个实体
	 * @param syStatus
	 */
	public SyStatusImpl get(SyStatusImpl syStatus) throws CoException {
		return (SyStatusImpl) this.syStatusMapper.get(syStatus);
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
	public PageInfo<SyStatusImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syStatusMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyStatusImpl> page = new PageInfo<SyStatusImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syStatus
	 */
	@SuppressWarnings("unchecked")
	public List<SyStatusImpl> selectTree(SyStatusImpl syStatus) throws CoException {
		return (List<SyStatusImpl>) this.syStatusMapper.selectTree(syStatus);
	}
}