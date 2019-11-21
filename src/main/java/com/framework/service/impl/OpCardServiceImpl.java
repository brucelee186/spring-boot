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
import com.framework.dao.OpCardMapper;
import com.framework.bean.impl.OpCardImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpCardService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 卡牌管理
 * 作者:     Auto
 * 日期:     2019/8/15
**********************************************
*/
@Service("opCardService")
public class OpCardServiceImpl extends CommonServiceImpl implements OpCardService {
	@Autowired
	private OpCardMapper opCardMapper;

	@Autowired
	public OpCardMapper getOpCardMapper() {
		return opCardMapper;
	}

	@Autowired
	public void setOpCardMapper(OpCardMapper opCardMapper) {
		this.opCardMapper = opCardMapper;
	}

	/**
	 * 新增实体对象
	 * @param opCard
	 */
	public OpCardImpl insert(OpCardImpl opCard) throws CoException {
		this.opCardMapper.insert(opCard);
		return opCard;
	}

	/**
	 * 删除实体对象
	 * @param opCard
	 */
	public int delete(OpCardImpl opCard) throws CoException {
		return this.opCardMapper.delete(opCard);
	}

	/**
	 * 更新实体对象
	 * @param opCard
	 */
	public boolean update(OpCardImpl opCard) throws CoException {
		boolean result = true;
		this.opCardMapper.update(opCard);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardImpl> select(OpCardImpl opCard) throws CoException {
		return (List<OpCardImpl>) this.opCardMapper.select(opCard);
	}

	/**
	 * 查询单个实体
	 * @param opCard
	 */
	public OpCardImpl get(OpCardImpl opCard) throws CoException {
		return (OpCardImpl) this.opCardMapper.get(opCard);
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
	public PageInfo<OpCardImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opCardMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpCardImpl> page = new PageInfo<OpCardImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opCard
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardImpl> selectTree(OpCardImpl opCard) throws CoException {
		return (List<OpCardImpl>) this.opCardMapper.selectTree(opCard);
	}
}