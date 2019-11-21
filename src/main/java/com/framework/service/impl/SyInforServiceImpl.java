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
import com.framework.dao.SyInforMapper;
import com.framework.bean.impl.SyInforImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.SyInforService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统信息
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("syInforService")
public class SyInforServiceImpl extends CommonServiceImpl implements SyInforService {
	@Autowired
	private SyInforMapper syInforMapper;

	@Autowired
	public SyInforMapper getSyInforMapper() {
		return syInforMapper;
	}

	@Autowired
	public void setSyInforMapper(SyInforMapper syInforMapper) {
		this.syInforMapper = syInforMapper;
	}

	/**
	 * 新增实体对象
	 * @param syInfor
	 */
	public SyInforImpl insert(SyInforImpl syInfor) throws CoException {
		this.syInforMapper.insert(syInfor);
		return syInfor;
	}

	/**
	 * 删除实体对象
	 * @param syInfor
	 */
	public int delete(SyInforImpl syInfor) throws CoException {
		return this.syInforMapper.delete(syInfor);
	}

	/**
	 * 更新实体对象
	 * @param syInfor
	 */
	public boolean update(SyInforImpl syInfor) throws CoException {
		boolean result = true;
		this.syInforMapper.update(syInfor);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syInfor
	 */
	@SuppressWarnings("unchecked")
	public List<SyInforImpl> select(SyInforImpl syInfor) throws CoException {
		return (List<SyInforImpl>) this.syInforMapper.select(syInfor);
	}

	/**
	 * 查询单个实体
	 * @param syInfor
	 */
	public SyInforImpl get(SyInforImpl syInfor) throws CoException {
		return (SyInforImpl) this.syInforMapper.get(syInfor);
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
	public PageInfo<SyInforImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syInforMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyInforImpl> page = new PageInfo<SyInforImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syInfor
	 */
	@SuppressWarnings("unchecked")
	public List<SyInforImpl> selectTree(SyInforImpl syInfor) throws CoException {
		return (List<SyInforImpl>) this.syInforMapper.selectTree(syInfor);
	}
}