package com.framework.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.impl.SyStatusDetailImpl;
import com.framework.bean.impl.SyStatusImpl;
import com.framework.dao.SyStatusDetailConditionMapper;
import com.framework.dao.SyStatusDetailMapper;
import com.framework.dao.SyStatusMapper;
import com.framework.exception.CoException;
import com.framework.service.SyStatusDetailService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统状态明细
 * 作者:     Auto
 * 日期:     2019/8/19
**********************************************
*/
@Service("syStatusDetailService")
public class SyStatusDetailServiceImpl extends CommonServiceImpl implements SyStatusDetailService {
	@Autowired
	private SyStatusDetailMapper syStatusDetailMapper;
	
	@Autowired
	private SyStatusDetailConditionMapper syStatusDetailConditionMapper;
	
	@Autowired
	private SyStatusMapper syStatusMapper;

	@Autowired
	public SyStatusDetailMapper getSyStatusDetailMapper() {
		return syStatusDetailMapper;
	}

	@Autowired
	public void setSyStatusDetailMapper(SyStatusDetailMapper syStatusDetailMapper) {
		this.syStatusDetailMapper = syStatusDetailMapper;
	}

	/**
	 * 新增实体对象
	 * @param syStatusDetail
	 */
	public SyStatusDetailImpl insert(SyStatusDetailImpl syStatusDetail) throws CoException {
		setCommonField(syStatusDetail);
		String code = this.getCode(syStatusDetail);
		syStatusDetail.setCode(code);
		this.syStatusDetailMapper.insert(syStatusDetail);
		return syStatusDetail;
	}

	/**
	 * 删除实体对象
	 * @param syStatusDetail
	 */
	public int delete(SyStatusDetailImpl syStatusDetail) throws CoException {
		return this.syStatusDetailMapper.delete(syStatusDetail);
	}

	/**
	 * 更新实体对象
	 * @param syStatusDetail
	 */
	public boolean update(SyStatusDetailImpl syStatusDetail) throws CoException {
		boolean result = true;
		setCommonField(syStatusDetail);
		this.syStatusDetailMapper.update(syStatusDetail);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syStatusDetail
	 */
	@SuppressWarnings("unchecked")
	public List<SyStatusDetailImpl> select(SyStatusDetailImpl syStatusDetail) throws CoException {
		return (List<SyStatusDetailImpl>) this.syStatusDetailMapper.select(syStatusDetail);
	}

	/**
	 * 查询单个实体
	 * @param syStatusDetail
	 */
	public SyStatusDetailImpl get(SyStatusDetailImpl syStatusDetail) throws CoException {
		return (SyStatusDetailImpl) this.syStatusDetailMapper.get(syStatusDetail);
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
	public PageInfo<SyStatusDetailImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syStatusDetailMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyStatusDetailImpl> page = new PageInfo<SyStatusDetailImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syStatusDetail
	 */
	@SuppressWarnings("unchecked")
	public List<SyStatusDetailImpl> selectTree(SyStatusDetailImpl syStatusDetail) throws CoException {
		return (List<SyStatusDetailImpl>) this.syStatusDetailMapper.selectTree(syStatusDetail);
	}
	
	/**
	 * 查询树型实体
	 * @param syStatusDetail
	 */
	@SuppressWarnings("unchecked")
	public List<SyStatusDetailImpl> selectTreeValue(SyStatusDetailImpl syStatusDetail) throws CoException {
		return (List<SyStatusDetailImpl>) this.syStatusDetailConditionMapper.selectTreeValue(syStatusDetail);
	}
	
	/**
	 * 设备主表信息
	 * @param syStatusDetail
	 */
	private void setCommonField(SyStatusDetailImpl syStatusDetail) {
		SyStatusImpl syStatus = new SyStatusImpl();
		Long idSyStatus = syStatusDetail.getIdSyStatus();
		syStatus.setId(idSyStatus);
		syStatus = (SyStatusImpl) syStatusMapper.get(syStatus);
		String codeSyStatus = syStatus.getCode();
		String statusTable = syStatus.getStatusTable();
		String statusColumn = syStatus.getStatusColumn();
		syStatusDetail.setCodeSyStatus(codeSyStatus);
		syStatusDetail.setStatusTable(statusTable);
		syStatusDetail.setStatusColumn(statusColumn);
	}
	
	/**
	 * 取得明细编码
	 * @param syStatusDetail
	 * @return
	 */
	public String getCode(SyStatusDetailImpl syStatusDetail){
		String code = null;
		// 获取当前类型日期最近的数据
		Long idMaCompany = syStatusDetail.getIdMaCompany();
		Long idSyStatus = syStatusDetail.getIdSyStatus();
		String codeSyStatus = syStatusDetail.getCodeSyStatus();
		SyStatusDetailImpl syStatusDetailTemp = new SyStatusDetailImpl();
		syStatusDetailTemp.setIdMaCompany(idMaCompany);
		syStatusDetailTemp.setSort("CAST(codeOrder AS DECIMAL)");
		syStatusDetailTemp.setOrder("DESC");
		syStatusDetailTemp.setCodeSyStatus(codeSyStatus);
		syStatusDetailTemp = (SyStatusDetailImpl) syStatusDetailMapper.get(syStatusDetailTemp);
		 if (syStatusDetailTemp == null) {
			 // 如果当前类型下没有数据，把类型加1作为code值
			code = codeSyStatus + 1;
		 }else{
			 // 如果当前类型下有数据，获取类型后的编号继续累加
			code = syStatusDetailTemp.getCode();
			if (code.length() >= 2) {
				String count = code.substring(codeSyStatus.length(), code.length());
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher matcher = pattern.matcher(count);
				if (matcher.matches()) {
					int countTemp = Integer.parseInt(count);
					countTemp++;
					code = codeSyStatus + countTemp; 
				}
			}
		}
		return code;
	}
}