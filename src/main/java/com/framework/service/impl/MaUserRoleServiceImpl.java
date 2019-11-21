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
import com.framework.dao.MaUserRoleMapper;
import com.framework.bean.impl.MaUserRoleImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.MaUserRoleService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 用户角色
 * 作者:     Auto
 * 日期:     2019/8/8
**********************************************
*/
@Service("maUserRoleService")
public class MaUserRoleServiceImpl extends CommonServiceImpl implements MaUserRoleService {
	@Autowired
	private MaUserRoleMapper maUserRoleMapper;

	@Autowired
	public MaUserRoleMapper getMaUserRoleMapper() {
		return maUserRoleMapper;
	}

	@Autowired
	public void setMaUserRoleMapper(MaUserRoleMapper maUserRoleMapper) {
		this.maUserRoleMapper = maUserRoleMapper;
	}

	/**
	 * 新增实体对象
	 * @param maUserRole
	 */
	public MaUserRoleImpl insert(MaUserRoleImpl maUserRole) throws CoException {
		this.maUserRoleMapper.insert(maUserRole);
		return maUserRole;
	}

	/**
	 * 删除实体对象
	 * @param maUserRole
	 */
	public int delete(MaUserRoleImpl maUserRole) throws CoException {
		return this.maUserRoleMapper.delete(maUserRole);
	}

	/**
	 * 更新实体对象
	 * @param maUserRole
	 */
	public boolean update(MaUserRoleImpl maUserRole) throws CoException {
		boolean result = true;
		this.maUserRoleMapper.update(maUserRole);
		return result;
	}
	/**
	 * 查询实体列表
	 * @param maUserRole
	 */
	@SuppressWarnings("unchecked")
	public List<MaUserRoleImpl> select(MaUserRoleImpl maUserRole) throws CoException {
		return (List<MaUserRoleImpl>) this.maUserRoleMapper.select(maUserRole);
	}
	/**
	 * 查询单个实体
	 * @param maUserRole
	 */
	public MaUserRoleImpl get(MaUserRoleImpl maUserRole) throws CoException {
		return (MaUserRoleImpl) this.maUserRoleMapper.get(maUserRole);
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
	public PageInfo<MaUserRoleImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list =maUserRoleMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaUserRoleImpl> page = new PageInfo<MaUserRoleImpl>(list);
		return page;
	}
}