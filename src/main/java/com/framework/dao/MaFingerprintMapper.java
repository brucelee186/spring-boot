package com.framework.dao;

import com.framework.dao.common.CommonMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaFingerprintImpl;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/9/19
 **********************************************
 */
public interface MaFingerprintMapper extends CommonMapper {

	/**
	 * 新增实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public Long insert(MaFingerprintImpl maFingerprint);

	/**
	 * 删除实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public int delete(MaFingerprintImpl maFingerprint);

	/**
	 * 更新实体对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public int update(MaFingerprintImpl maFingerprint);

	/**
	 * 查询实体列表
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> select(MaFingerprintImpl maFingerprint);

	/**
	 * 查询实体对象记录数
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public int count(MaFingerprintImpl maFingerprint);

	/**
	 * 取得单一对象
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public MaFingerprintImpl get(MaFingerprintImpl maFingerprint);

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

	/**
	 * 查询树型实体
	 * @param maFingerprint
	 * @return
	 * @throws CoException
	 */
	public List<MaFingerprintImpl> selectTree(MaFingerprintImpl maFingerprint);

}