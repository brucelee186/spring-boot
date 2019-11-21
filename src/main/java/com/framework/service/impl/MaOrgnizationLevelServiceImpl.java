package com.framework.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.framework.bean.impl.MaOrgnizationLevelImpl;
import com.framework.dao.MaOrgnizationLevelConditionMapper;
import com.framework.dao.MaOrgnizationLevelMapper;
import com.framework.exception.CoException;
import com.framework.service.MaOrgnizationLevelService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 组织层级
 * 作者:     Auto
 * 日期:     2019/8/13
**********************************************
*/
@Service("maOrgnizationLevelService")
public class MaOrgnizationLevelServiceImpl extends CommonServiceImpl implements MaOrgnizationLevelService {
	@Autowired
	private MaOrgnizationLevelMapper maOrgnizationLevelMapper;
	
	@Autowired
	private MaOrgnizationLevelConditionMapper maOrgnizationLevelConditionMapper;

	@Autowired
	public MaOrgnizationLevelConditionMapper getMaOrgnizationLevelConditionMapper() {
		return maOrgnizationLevelConditionMapper;
	}

	@Autowired
	public void setMaOrgnizationLevelConditionMapper(MaOrgnizationLevelConditionMapper maOrgnizationLevelConditionMapper) {
		this.maOrgnizationLevelConditionMapper = maOrgnizationLevelConditionMapper;
	}

	@Autowired
	public MaOrgnizationLevelMapper getMaOrgnizationLevelMapper() {
		return maOrgnizationLevelMapper;
	}

	@Autowired
	public void setMaOrgnizationLevelMapper(MaOrgnizationLevelMapper maOrgnizationLevelMapper) {
		this.maOrgnizationLevelMapper = maOrgnizationLevelMapper;
	}

	/**
	 * 新增实体对象
	 * @param maOrgnizationLevel
	 */
	public MaOrgnizationLevelImpl insert(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		this.maOrgnizationLevelMapper.insert(maOrgnizationLevel);
		return maOrgnizationLevel;
	}

	/**
	 * 删除实体对象
	 * @param maOrgnizationLevel
	 */
	public int delete(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		return this.maOrgnizationLevelMapper.delete(maOrgnizationLevel);
	}

	/**
	 * 更新实体对象
	 * @param maOrgnizationLevel
	 */
	public boolean update(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		boolean result = true;
		this.maOrgnizationLevelMapper.update(maOrgnizationLevel);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param maOrgnizationLevel
	 */
	@SuppressWarnings("unchecked")
	public List<MaOrgnizationLevelImpl> select(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		return (List<MaOrgnizationLevelImpl>) this.maOrgnizationLevelMapper.select(maOrgnizationLevel);
	}

	/**
	 * 查询单个实体
	 * @param maOrgnizationLevel
	 */
	public MaOrgnizationLevelImpl get(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		return (MaOrgnizationLevelImpl) this.maOrgnizationLevelMapper.get(maOrgnizationLevel);
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
	public PageInfo<MaOrgnizationLevelImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = maOrgnizationLevelMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaOrgnizationLevelImpl> page = new PageInfo<MaOrgnizationLevelImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param maOrgnizationLevel
	 */
	@SuppressWarnings("unchecked")
	public List<MaOrgnizationLevelImpl> selectTree(MaOrgnizationLevelImpl maOrgnizationLevel) throws CoException {
		return (List<MaOrgnizationLevelImpl>) this.maOrgnizationLevelMapper.selectTree(maOrgnizationLevel);
	}
	
	/**
	 * 取得下拉框
	 * @param nameColumn
	 * @param sqlWhere
	 * @param orderBy
	 * @return
	 */
	public JSONArray findNameId(String nameColumn, String sqlWhere, String orderBy) {
		List<Map<String, String>> result = maOrgnizationLevelConditionMapper.findNameId(nameColumn, sqlWhere, orderBy);
		return JSONArray.parseArray(JSON.toJSONString(result));
	}		
}