package com.framework.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaFingerprintImpl;
/*
**********************************************
 * 项目名称: 
 * 模块名称：业务层接口 -> 用户管理
 * 作者:     Auto
 * 日期:     2019/9/19
**********************************************
*/
public interface MaFingerprintService {
	/**
	 * 新增实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public MaFingerprintImpl insert(MaFingerprintImpl maFingerprint) throws CoException;

	/**
	 * 删除实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public int delete(MaFingerprintImpl maFingerprint) throws CoException;

	/**
	 * 更新实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public boolean update(MaFingerprintImpl maFingerprint) throws CoException;
	
	/**
	 * 更新用户指纹
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public boolean updateUserFingerprint(MaFingerprintImpl maFingerprint) throws CoException;
	
	/**
	 * 更新用户指纹
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public boolean updateUserFingerprintV2(MaFingerprintImpl maFingerprint) throws CoException;
	
	/**
	 * 查询用户指纹索引
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public MaFingerprintImpl getUserFingerprintIndex(MaFingerprintImpl maFingerprint) throws CoException;

	/**
	 * 查询实体列表
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> select(MaFingerprintImpl maFingerprint) throws CoException;
	
	/**
	 * 查询实体列表
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> selectFingerprintByCabinet(MaFingerprintImpl maFingerprint) throws CoException;

	/**
	 * 取得单一对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public MaFingerprintImpl get(MaFingerprintImpl maFingerprint) throws CoException;

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public PageInfo<MaFingerprintImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException;

	/**
	 * 查询树型实体
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> selectTree(MaFingerprintImpl maFingerprint) throws CoException;

}