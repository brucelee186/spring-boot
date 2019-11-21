package com.framework.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.common.Cons;
import com.framework.bean.impl.MaFingerprintImpl;
import com.framework.bean.impl.MaUserImpl;
import com.framework.dao.MaFingerprintConditionMapper;
import com.framework.dao.MaFingerprintMapper;
import com.framework.dao.MaUserMapper;
import com.framework.exception.CoException;
import com.framework.service.MaFingerprintService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 用户管理
 * 作者:     Auto
 * 日期:     2019/9/19
**********************************************
*/
@Service("maFingerprintService")
public class MaFingerprintServiceImpl extends CommonServiceImpl implements MaFingerprintService {
	@Autowired
	private MaFingerprintMapper maFingerprintMapper;

	@Autowired
	private MaFingerprintConditionMapper maFingerprintConditionMapper;
	
	@Autowired
	private MaUserMapper maUserMapper;

	/**
	 * 新增实体对象
	 * @param maFingerprint
	 */
	public MaFingerprintImpl insert(MaFingerprintImpl maFingerprint) throws CoException {
		this.maFingerprintMapper.insert(maFingerprint);
		return maFingerprint;
	}

	/**
	 * 删除实体对象
	 * @param maFingerprint
	 */
	public int delete(MaFingerprintImpl maFingerprint) throws CoException {
		Long id = maFingerprint.getId();
		if(null != id) {
			maFingerprint.setTag("d");
			return this.maFingerprintMapper.update(maFingerprint);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param maFingerprint
	 */
	public boolean updateUserFingerprint(MaFingerprintImpl maFingerprint) throws CoException {
		boolean result = true;
		String msg = "";
		MaUserImpl maUser = new MaUserImpl();
		Long idMaUser = maFingerprint.getIdMaUser();
		maUser.setId(idMaUser);
		String code = maFingerprint.getCode();
		Integer indexMaFingerprint = maFingerprint.getIndex();
		maUser = maUserMapper.get(maUser);
		if(null != idMaUser && null != maUser) {
			Integer indexMaFingerprintInner = maUser.getIndexMaFingerprint();
			if(null == indexMaFingerprintInner || indexMaFingerprintInner == 0) {
				maUser = new MaUserImpl();
				maUser.setId(idMaUser);
				maUser.setIndexMaFingerprint(indexMaFingerprint);
				maUser.setEditState("u");
				maUser.setModifiedUser(Cons.ID_SYS);
				maUser.setModifiedIp(Cons.IP_SYS);
				this.setCommonField(maUser);
				maUserMapper.update(maUser);
			}
			
			maFingerprint = new MaFingerprintImpl();
			maFingerprint.setIndex(indexMaFingerprint);
			maFingerprint = maFingerprintMapper.get(maFingerprint);
			Long idMaFingerprint = maFingerprint.getId();
			indexMaFingerprint = maFingerprint.getIndex();
			maFingerprint = new MaFingerprintImpl();
			maFingerprint.setCode(code);
			maFingerprint.setIdMaUser(idMaUser);
			maFingerprint.setId(idMaFingerprint);
			// 状态标识(e:enable 有效, d:disable 无效)
			maFingerprint.setEditState("u");
			maFingerprint.setModifiedUser(Cons.ID_SYS);
			maFingerprint.setModifiedIp(Cons.IP_SYS);
			this.setCommonField(maFingerprint);
			maFingerprintMapper.update(maFingerprint);
		}
		else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 更新实体对象
	 * @param maFingerprint
	 */
	public boolean updateUserFingerprintV2(MaFingerprintImpl maFingerprint) throws CoException {
		boolean result = true;
		try {
			String msg = "";
			MaUserImpl maUser = new MaUserImpl();
			Long idMaUser = maFingerprint.getIdMaUser();
			maUser.setId(idMaUser);
			String codeMaFingerprint = maFingerprint.getCode();
			//Integer indexMaFingerprint = maFingerprint.getIndex();
			maUser = maUserMapper.get(maUser);
			if(null != idMaUser && null != maUser) {
				maUser = new MaUserImpl();
				maUser.setId(idMaUser);
				maUser.setCodeMaFingerprint(codeMaFingerprint);
				maUser.setEditState("u");
				maUser.setModifiedUser(Cons.ID_SYS);
				maUser.setModifiedIp(Cons.IP_SYS);
				this.setCommonField(maUser);
				maUserMapper.update(maUser);
			}
			else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	
	/**
	 * 更新实体对象
	 * @param maFingerprint
	 */
	public MaFingerprintImpl getUserFingerprintIndex(MaFingerprintImpl maFingerprint) throws CoException {
		MaUserImpl maUser = new MaUserImpl();
		Long idMaUser = maFingerprint.getIdMaUser();
		maUser.setId(idMaUser);
		maUser = maUserMapper.get(maUser);
		if(null != idMaUser && null != maUser) {
			Integer indexMaFingerprint = maUser.getIndexMaFingerprint();
			if(null == indexMaFingerprint || indexMaFingerprint == 0) {
				maFingerprint = new MaFingerprintImpl();
				maFingerprint.setIdMaUser(0L);
			}
			else {
				maFingerprint = new MaFingerprintImpl();
				maFingerprint.setIndex(indexMaFingerprint);
			}
			maFingerprint = maFingerprintMapper.get(maFingerprint);
			maFingerprint.setCode("");
		}
		return maFingerprint;
	}
	
	/**
	 * 更新用户指纹
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaFingerprintImpl maFingerprint) throws CoException {
		boolean result = true;
		this.maFingerprintMapper.update(maFingerprint);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param maFingerprint
	 */
	@SuppressWarnings("unchecked")
	public List<MaFingerprintImpl> select(MaFingerprintImpl maFingerprint) throws CoException {
		return (List<MaFingerprintImpl>) this.maFingerprintMapper.select(maFingerprint);
	}

	/**
	 * 查询单个实体
	 * @param maFingerprint
	 */
	public MaFingerprintImpl get(MaFingerprintImpl maFingerprint) throws CoException {
		return (MaFingerprintImpl) this.maFingerprintMapper.get(maFingerprint);
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
	public PageInfo<MaFingerprintImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List<MaFingerprintImpl> listMaFingerprint = (List<MaFingerprintImpl>) maFingerprintMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaFingerprintImpl> page = new PageInfo<MaFingerprintImpl>(listMaFingerprint);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param maFingerprint
	 */
	@SuppressWarnings("unchecked")
	public List<MaFingerprintImpl> selectTree(MaFingerprintImpl maFingerprint) throws CoException {
		return (List<MaFingerprintImpl>) this.maFingerprintMapper.selectTree(maFingerprint);
	}

	@Override
	public List<MaFingerprintImpl> selectFingerprintByCabinet(MaFingerprintImpl maFingerprint) throws CoException {
		return maFingerprintConditionMapper.selectFingerprintByCabinet(maFingerprint);
	}
}