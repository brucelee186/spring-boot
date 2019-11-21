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
import com.framework.dao.MaRoleMapper;
import com.framework.bean.impl.MaRoleImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.MaRoleService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 角色管理
 * 作者:     Auto
 * 日期:     2019/8/12
**********************************************
*/
@Service("maRoleService")
public class MaRoleServiceImpl extends CommonServiceImpl implements MaRoleService {
	@Autowired
	private MaRoleMapper maRoleMapper;

	@Autowired
	public MaRoleMapper getMaRoleMapper() {
		return maRoleMapper;
	}

	@Autowired
	public void setMaRoleMapper(MaRoleMapper maRoleMapper) {
		this.maRoleMapper = maRoleMapper;
	}

	/**
	 * 新增实体对象
	 * @param maRole
	 */
	public MaRoleImpl insert(MaRoleImpl maRole) throws CoException {
		this.maRoleMapper.insert(maRole);
		return maRole;
	}

	/**
	 * 删除实体对象
	 * @param maRole
	 */
	public int delete(MaRoleImpl maRole) throws CoException {
		return this.maRoleMapper.delete(maRole);
	}

	/**
	 * 更新实体对象
	 * @param maRole
	 */
	public boolean update(MaRoleImpl maRole) throws CoException {
		boolean result = true;
		this.maRoleMapper.update(maRole);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param maRole
	 */
	@SuppressWarnings("unchecked")
	public List<MaRoleImpl> select(MaRoleImpl maRole) throws CoException {
		return (List<MaRoleImpl>) this.maRoleMapper.select(maRole);
	}

	/**
	 * 查询单个实体
	 * @param maRole
	 */
	public MaRoleImpl get(MaRoleImpl maRole) throws CoException {
		return (MaRoleImpl) this.maRoleMapper.get(maRole);
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
	public PageInfo<MaRoleImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = maRoleMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaRoleImpl> page = new PageInfo<MaRoleImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param maRole
	 */
	@SuppressWarnings("unchecked")
	public List<MaRoleImpl> selectTree(MaRoleImpl maRole) throws CoException {
		return (List<MaRoleImpl>) this.maRoleMapper.selectTree(maRole);
	}
}