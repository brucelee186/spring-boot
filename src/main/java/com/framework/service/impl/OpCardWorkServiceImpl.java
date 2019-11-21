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
import com.framework.dao.OpCardWorkMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.bean.impl.OpCardPowerImpl;
import com.framework.bean.impl.OpCardWorkImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpCardWorkService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 工作牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
@Service("opCardWorkService")
public class OpCardWorkServiceImpl extends CommonServiceImpl implements OpCardWorkService {
	@Autowired
	private OpCardWorkMapper opCardWorkMapper;
	
	@Autowired
	private OpDeviceMapper opDeviceMapper;

	@Autowired
	public OpCardWorkMapper getOpCardWorkMapper() {
		return opCardWorkMapper;
	}

	@Autowired
	public void setOpCardWorkMapper(OpCardWorkMapper opCardWorkMapper) {
		this.opCardWorkMapper = opCardWorkMapper;
	}

	/**
	 * 新增实体对象
	 * @param opCardWork
	 */
	public OpCardWorkImpl insert(OpCardWorkImpl opCardWork) throws CoException {
		setCommonField(opCardWork);
		this.opCardWorkMapper.insert(opCardWork);
		return opCardWork;
	}

	/**
	 * 删除实体对象
	 * @param opCardWork
	 */
	public int delete(OpCardWorkImpl opCardWork) throws CoException {
		return this.opCardWorkMapper.delete(opCardWork);
	}

	/**
	 * 更新实体对象
	 * @param opCardWork
	 */
	public boolean update(OpCardWorkImpl opCardWork) throws CoException {
		boolean result = true;
		setCommonField(opCardWork);
		this.opCardWorkMapper.update(opCardWork);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opCardWork
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardWorkImpl> select(OpCardWorkImpl opCardWork) throws CoException {
		return (List<OpCardWorkImpl>) this.opCardWorkMapper.select(opCardWork);
	}

	/**
	 * 查询单个实体
	 * @param opCardWork
	 */
	public OpCardWorkImpl get(OpCardWorkImpl opCardWork) throws CoException {
		return (OpCardWorkImpl) this.opCardWorkMapper.get(opCardWork);
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
	public PageInfo<OpCardWorkImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opCardWorkMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpCardWorkImpl> page = new PageInfo<OpCardWorkImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opCardWork
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardWorkImpl> selectTree(OpCardWorkImpl opCardWork) throws CoException {
		return (List<OpCardWorkImpl>) this.opCardWorkMapper.selectTree(opCardWork);
	}
	
	/**
	 *  赋值操作室,主电室
	 * @param opCardOperation
	 */
	private void setCommonField(OpCardWorkImpl opCardWork) {
		OpDeviceImpl opDevice = new OpDeviceImpl();
		Long idOpDevice = opCardWork.getIdOpDevice();
		opDevice.setId(idOpDevice);
		opDevice = (OpDeviceImpl) opDeviceMapper.get(opDevice);
		if(null != opDevice) {
			// 操作室
			Long idOpRoomOperation = opDevice.getIdOpRoomOperation();
			// 主电室
			Long idOpRoomElectric = opDevice.getIdOpRoomElectric();
			opCardWork.setIdOpRoomOperation(idOpRoomOperation);
			opCardWork.setIdOpRoomElectric(idOpRoomElectric);
		}
	}		
}