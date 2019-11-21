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
import com.framework.dao.MaCompanyMapper;
import com.framework.dao.MaOrgnizationMapper;
import com.framework.dao.MaRoleMapper;
import com.framework.dao.MaUserConditionMapper;
import com.framework.dao.MaUserMapper;
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.bean.impl.MaRoleImpl;
import com.framework.bean.impl.MaUserImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.MaUserService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 用户管理
 * 作者:     Auto
 * 日期:     2019/8/9
**********************************************
*/
@Service("maUserService")
public class MaUserServiceImpl extends CommonServiceImpl implements MaUserService {
	@Autowired
	private MaUserMapper maUserMapper;

	@Autowired
	private MaUserConditionMapper maUserConditionMapper;
	
	@Autowired
	private MaRoleMapper maRoleMapper;
	
	@Autowired
	public MaUserConditionMapper getMaUserConditionMapper() {
		return maUserConditionMapper;
	}

	@Autowired
	public void setMaUserConditionMapper(MaUserConditionMapper maUserConditionMapper) {
		this.maUserConditionMapper = maUserConditionMapper;
	}

	@Autowired
	private MaOrgnizationMapper maOrgnizationMapper;
	
	@Autowired
	public MaOrgnizationMapper getMaOrgnizationMapper() {
		return maOrgnizationMapper;
	}

	@Autowired
	public void setMaOrgnizationMapper(MaOrgnizationMapper maOrgnizationMapper) {
		this.maOrgnizationMapper = maOrgnizationMapper;
	}

	@Autowired
	private MaCompanyMapper maCompanyMapper;

	@Autowired
	public MaCompanyMapper getMaCompanyMapper() {
		return maCompanyMapper;
	}

	@Autowired
	public void setMaCompanyMapper(MaCompanyMapper maCompanyMapper) {
		this.maCompanyMapper = maCompanyMapper;
	}

	@Autowired
	public MaRoleMapper getMaRoleMapper() {
		return maRoleMapper;
	}

	@Autowired
	public void setMaRoleMapper(MaRoleMapper maRoleMapper) {
		this.maRoleMapper = maRoleMapper;
	}

	@Autowired
	public MaUserMapper getMaUserMapper() {
		return maUserMapper;
	}

	@Autowired
	public void setMaUserMapper(MaUserMapper maUserMapper) {
		this.maUserMapper = maUserMapper;
	}

	/**
	 * 新增实体对象
	 * @param maUser
	 */
	public MaUserImpl insert(MaUserImpl maUser) throws CoException {
		this.setCommonFiled(maUser);
		this.maUserMapper.insert(maUser);
		return maUser;
	}

	/**
	 * 删除实体对象
	 * @param maUser
	 */
	public int delete(MaUserImpl maUser) throws CoException {
		Long id = maUser.getId();
		if(null != id) {
			maUser.setTag("d");
			return this.maUserMapper.update(maUser);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param maUser
	 */
	public boolean update(MaUserImpl maUser) throws CoException {
		boolean result = true;
		this.setCommonFiled(maUser);
		this.maUserMapper.update(maUser);
		return result;
	}
	/**
	 * 查询实体列表
	 * @param maUser
	 */
	@SuppressWarnings("unchecked")
	public List<MaUserImpl> select(MaUserImpl maUser) throws CoException {
		return (List<MaUserImpl>) this.maUserMapper.select(maUser);
	}
	/**
	 * 查询单个实体
	 * @param maUser
	 */
	public MaUserImpl get(MaUserImpl maUser) throws CoException {
		return (MaUserImpl) this.maUserMapper.get(maUser);
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
	public PageInfo<MaUserImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List<MaUserImpl> listMaUser = (List<MaUserImpl>) maUserMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaUserImpl> page = new PageInfo<MaUserImpl>(listMaUser);
		return page;
	}
	 
	 /**
	  * 设置Bean
	  * @param maUser
	  */
	 private void setCommonFiled(MaUserImpl maUser) {
		Long idMaRole = maUser.getIdMaRole();
		Long idMaCompany = maUser.getIdMaCompany();
		Long idMaOrgnization = maUser.getIdMaOrgnization();
		MaRoleImpl maRole = new MaRoleImpl();
		maRole.setId(idMaRole);
		maRole = (MaRoleImpl) maRoleMapper.get(maRole);
		
		MaCompanyImpl maCompany = new MaCompanyImpl();
		maCompany.setId(idMaCompany);
		maCompany = (MaCompanyImpl) maCompanyMapper.get(maCompany);
		String nameMaCompany = maCompany.getName();
		String uidMaCompany = maCompany.getUid();
		String nameMaRole = maRole.getName();
		String codeMaRole = maRole.getCode();
		
		String nameMaOrgnization = "";
		if(null != idMaOrgnization) {
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setId(idMaOrgnization);
			maOrgnization = (MaOrgnizationImpl) maOrgnizationMapper.get(maOrgnization);
			nameMaOrgnization = maOrgnization.getName();
		}
		maUser.setNameMaRole(nameMaRole);
		maUser.setCodeMaRole(codeMaRole);
		maUser.setNameMaCompany(nameMaCompany);
		maUser.setUidMaCompany(uidMaCompany);
		maUser.setNameMaOrgnization(nameMaOrgnization);
	}
	 
	/**
	 * 查询树型实体
	 * @param maUser
	 */
	@SuppressWarnings("unchecked")
	public List<MaUserImpl> selectTree(MaUserImpl maUser) throws CoException {
		return (List<MaUserImpl>) this.maUserMapper.selectTree(maUser);
	}
	
	/**
	 * 查询点检员 带班人
	 * @param maUser
	 */
	public List<MaUserImpl> selectPreTree(MaUserImpl maUser) throws CoException {
		return (List<MaUserImpl>) this.maUserConditionMapper.selectPreTree(maUser);
	}
	
}