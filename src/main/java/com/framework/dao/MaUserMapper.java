package com.framework.dao;

import com.framework.dao.common.CommonMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.framework.exception.CoException;
import com.framework.bean.impl.MaUserImpl;
/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 模型层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/9/19
 **********************************************
 */
public interface MaUserMapper extends CommonMapper {

	/**
	 * 新增实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public Long insert(MaUserImpl maUser);

	/**
	 * 删除实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public int delete(MaUserImpl maUser);

	/**
	 * 更新实体对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public int update(MaUserImpl maUser);

	/**
	 * 查询实体列表
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> select(MaUserImpl maUser);

	/**
	 * 查询实体对象记录数
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public int count(MaUserImpl maUser);

	/**
	 * 取得单一对象
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public MaUserImpl get(MaUserImpl maUser);

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> selectPage(Integer pageNo, Integer pageSize, @Param("sqlWhere") String sqlWhere, @Param("orderBy") String orderBy);

	/**
	 * 查询树型实体
	 * @param maUser
	 * @return
	 * @throws CoException
	 */
	public List<MaUserImpl> selectTree(MaUserImpl maUser);

}