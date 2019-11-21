package com.framework.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpDivide;
import org.springframework.stereotype.Service;
import com.framework.exception.CoException;
import com.framework.dao.OpCardOperationMapper;
import com.framework.dao.OpDeviceMapper;
import com.framework.bean.impl.OpCardOperationImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.OpCardOperationService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 操作牌
 * 作者:     Auto
 * 日期:     2019/8/17
**********************************************
*/
@Service("opCardOperationService")
public class OpCardOperationServiceImpl extends CommonServiceImpl implements OpCardOperationService {
	@Autowired
	private OpCardOperationMapper opCardOperationMapper;
	
	@Autowired
	private OpDeviceMapper opDeviceMapper;

	@Autowired
	public OpCardOperationMapper getOpCardOperationMapper() {
		return opCardOperationMapper;
	}

	@Autowired
	public void setOpCardOperationMapper(OpCardOperationMapper opCardOperationMapper) {
		this.opCardOperationMapper = opCardOperationMapper;
	}

	/**
	 * 新增实体对象
	 * @param opCardOperation
	 */
	public OpCardOperationImpl insert(OpCardOperationImpl opCardOperation) throws CoException {
		setCommonField(opCardOperation);
		this.opCardOperationMapper.insert(opCardOperation);
		return opCardOperation;
	}

	/**
	 * 删除实体对象
	 * @param opCardOperation
	 */
	public int delete(OpCardOperationImpl opCardOperation) throws CoException {
		return this.opCardOperationMapper.delete(opCardOperation);
	}

	/**
	 * 更新实体对象
	 * @param opCardOperation
	 */
	public boolean update(OpCardOperationImpl opCardOperation) throws CoException {
		boolean result = true;
		setCommonField(opCardOperation);
		this.opCardOperationMapper.update(opCardOperation);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param opCardOperation
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardOperationImpl> select(OpCardOperationImpl opCardOperation) throws CoException {
		return (List<OpCardOperationImpl>) this.opCardOperationMapper.select(opCardOperation);
	}

	/**
	 * 查询单个实体
	 * @param opCardOperation
	 */
	public OpCardOperationImpl get(OpCardOperationImpl opCardOperation) throws CoException {
		return (OpCardOperationImpl) this.opCardOperationMapper.get(opCardOperation);
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
	public PageInfo<OpCardOperationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = opCardOperationMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<OpCardOperationImpl> page = new PageInfo<OpCardOperationImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param opCardOperation
	 */
	@SuppressWarnings("unchecked")
	public List<OpCardOperationImpl> selectTree(OpCardOperationImpl opCardOperation) throws CoException {
		return (List<OpCardOperationImpl>) this.opCardOperationMapper.selectTree(opCardOperation);
	}
	
	/**
	 *  赋值操作室,主电室
	 * @param opCardOperation
	 */
	private void setCommonField(OpCardOperationImpl opCardOperation) {
		OpDeviceImpl opDevice = new OpDeviceImpl();
		Long idOpDevice = opCardOperation.getIdOpDevice();
		opDevice.setId(idOpDevice);
		opDevice = (OpDeviceImpl) opDeviceMapper.get(opDevice);
		if(null != opDevice) {
			// 操作室
			Long idOpRoomOperation = opDevice.getIdOpRoomOperation();
			// 主电室
			Long idOpRoomElectric = opDevice.getIdOpRoomElectric();
			opCardOperation.setIdOpRoomOperation(idOpRoomOperation);
			opCardOperation.setIdOpRoomElectric(idOpRoomElectric);
		}
	}
}