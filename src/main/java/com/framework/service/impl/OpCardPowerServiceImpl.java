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
import com.framework.dao.OpCardPowerMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.bean.impl.OpCardOperationImpl;
import com.framework.bean.impl.OpCardPowerImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpCardPowerService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 停电牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
@Service("opCardPowerService")
public class OpCardPowerServiceImpl extends CommonServiceImpl implements OpCardPowerService {
	@Autowired
	private OpCardPowerMapper opCardPowerMapper;
	
	@Autowired
	private OpDeviceMapper opDeviceMapper;

	@Autowired
	public OpCardPowerMapper getOpCardPowerMapper() {
		return opCardPowerMapper;
	}

	@Autowired
	public void setOpCardPowerMapper(OpCardPowerMapper opCardPowerMapper) {
		this.opCardPowerMapper = opCardPowerMapper;
	}

	/**
	 * 新增实体对象
	 * @param opCardPower
	 */
	public OpCardPowerImpl insert(OpCardPowerImpl opCardPower) throws CoException {
		setCommonField(opCardPower);
		this.opCardPowerMapper.insert(opCardPower);
		return opCardPower;
	}

	/**
	 * 删除实体对象
	 * @param opCardPower
	 */
	public int delete(OpCardPowerImpl opCardPower) throws CoException {
		return this.opCardPowerMapper.delete(opCardPower);
	}

	/**
	 * 更新实体对象
	 * @param opCardPower
	 */
	public boolean update(OpCardPowerImpl opCardPower) throws CoException {
		boolean result = true;
		setCommonField(opCardPower);
		this.opCardPowerMapper.update(opCardPower);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opCardPower
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardPowerImpl> select(OpCardPowerImpl opCardPower) throws CoException {
		return (List<OpCardPowerImpl>) this.opCardPowerMapper.select(opCardPower);
	}

	/**
	 * 查询单个实体
	 * @param opCardPower
	 */
	public OpCardPowerImpl get(OpCardPowerImpl opCardPower) throws CoException {
		return (OpCardPowerImpl) this.opCardPowerMapper.get(opCardPower);
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
	public PageInfo<OpCardPowerImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opCardPowerMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpCardPowerImpl> page = new PageInfo<OpCardPowerImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opCardPower
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardPowerImpl> selectTree(OpCardPowerImpl opCardPower) throws CoException {
		return (List<OpCardPowerImpl>) this.opCardPowerMapper.selectTree(opCardPower);
	}
	
	/**
	 *  赋值操作室,主电室
	 * @param opCardOperation
	 */
	private void setCommonField(OpCardPowerImpl opCardPower) {
		OpDeviceImpl opDevice = new OpDeviceImpl();
		Long idOpDevice = opCardPower.getIdOpDevice();
		opDevice.setId(idOpDevice);
		opDevice = (OpDeviceImpl) opDeviceMapper.get(opDevice);
		if(null != opDevice) {
			// 操作室
			Long idOpRoomOperation = opDevice.getIdOpRoomOperation();
			// 主电室
			Long idOpRoomElectric = opDevice.getIdOpRoomElectric();
			opCardPower.setIdOpRoomOperation(idOpRoomOperation);
			opCardPower.setIdOpRoomElectric(idOpRoomElectric);
		}
	}	
}