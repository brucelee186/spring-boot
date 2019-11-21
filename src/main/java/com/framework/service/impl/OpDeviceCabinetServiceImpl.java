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
import com.framework.dao.OpDeviceCabinetMapper;
import com.framework.bean.impl.OpDeviceCabinetImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpDeviceCabinetService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 设备开关柜
 * 作者:     Auto
 * 日期:     2019/8/27
**********************************************
*/
@Service("opDeviceCabinetService")
public class OpDeviceCabinetServiceImpl extends CommonServiceImpl implements OpDeviceCabinetService {
	@Autowired
	private OpDeviceCabinetMapper opDeviceCabinetMapper;

	/**
	 * 新增实体对象
	 * @param opDeviceCabinet
	 */
	public OpDeviceCabinetImpl insert(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		this.opDeviceCabinetMapper.insert(opDeviceCabinet);
		return opDeviceCabinet;
	}

	/**
	 * 删除实体对象
	 * @param opDeviceCabinet
	 */
	public int delete(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		Long id = opDeviceCabinet.getId();
		if(null != id) {
			opDeviceCabinet.setTag("d");
			return this.opDeviceCabinetMapper.update(opDeviceCabinet);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param opDeviceCabinet
	 */
	public boolean update(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		boolean result = true;
		this.opDeviceCabinetMapper.update(opDeviceCabinet);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opDeviceCabinet
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceCabinetImpl> select(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		return (List<OpDeviceCabinetImpl>) this.opDeviceCabinetMapper.select(opDeviceCabinet);
	}

	/**
	 * 查询单个实体
	 * @param opDeviceCabinet
	 */
	public OpDeviceCabinetImpl get(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		return (OpDeviceCabinetImpl) this.opDeviceCabinetMapper.get(opDeviceCabinet);
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
	public PageInfo<OpDeviceCabinetImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opDeviceCabinetMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpDeviceCabinetImpl> page = new PageInfo<OpDeviceCabinetImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opDeviceCabinet
	 */
	@SuppressWarnings("unchecked")
	public List<OpDeviceCabinetImpl> selectTree(OpDeviceCabinetImpl opDeviceCabinet) throws CoException {
		return (List<OpDeviceCabinetImpl>) this.opDeviceCabinetMapper.selectTree(opDeviceCabinet);
	}
}