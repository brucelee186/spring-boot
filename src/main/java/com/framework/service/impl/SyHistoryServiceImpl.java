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
import com.framework.dao.SyHistoryMapper;
import com.framework.bean.impl.SyHistoryImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.SyHistoryService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统状态明细
 * 作者:     Auto
 * 日期:     2019/8/20
**********************************************
*/
@Service("syHistoryService")
public class SyHistoryServiceImpl extends CommonServiceImpl implements SyHistoryService {
	@Autowired
	private SyHistoryMapper syHistoryMapper;

	@Autowired
	public SyHistoryMapper getSyHistoryMapper() {
		return syHistoryMapper;
	}

	@Autowired
	public void setSyHistoryMapper(SyHistoryMapper syHistoryMapper) {
		this.syHistoryMapper = syHistoryMapper;
	}

	/**
	 * 新增实体对象
	 * @param syHistory
	 */
	public SyHistoryImpl insert(SyHistoryImpl syHistory) throws CoException {
		this.syHistoryMapper.insert(syHistory);
		return syHistory;
	}

	/**
	 * 删除实体对象
	 * @param syHistory
	 */
	public int delete(SyHistoryImpl syHistory) throws CoException {
		return this.syHistoryMapper.delete(syHistory);
	}

	/**
	 * 更新实体对象
	 * @param syHistory
	 */
	public boolean update(SyHistoryImpl syHistory) throws CoException {
		boolean result = true;
		this.syHistoryMapper.update(syHistory);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syHistory
	 */
	@SuppressWarnings("unchecked")
	public List<SyHistoryImpl> select(SyHistoryImpl syHistory) throws CoException {
		return (List<SyHistoryImpl>) this.syHistoryMapper.select(syHistory);
	}

	/**
	 * 查询单个实体
	 * @param syHistory
	 */
	public SyHistoryImpl get(SyHistoryImpl syHistory) throws CoException {
		return (SyHistoryImpl) this.syHistoryMapper.get(syHistory);
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
	public PageInfo<SyHistoryImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syHistoryMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyHistoryImpl> page = new PageInfo<SyHistoryImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syHistory
	 */
	@SuppressWarnings("unchecked")
	public List<SyHistoryImpl> selectTree(SyHistoryImpl syHistory) throws CoException {
		return (List<SyHistoryImpl>) this.syHistoryMapper.selectTree(syHistory);
	}

	/**
	 * 新增系统历史
	 * @param syHistory
	 * @return
	 * @throws CoException
	 */
	public SyHistoryImpl insert(Object object) throws CoException {
		Class<?> clazz = object.getClass();
		Class<?> clazzSuper = clazz.getSuperclass();
		Class<?> clazzGrand = clazzSuper.getSuperclass();
		return null;
	}
}