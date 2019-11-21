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
import com.framework.dao.MaManagerMapper;
import com.framework.bean.impl.MaManagerImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.MaManagerService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 管理员
 * 作者:     Auto
 * 日期:     2019/7/19
**********************************************
*/
@Service("maManagerService")
public class MaManagerServiceImpl extends CommonServiceImpl implements MaManagerService {
	@Autowired
	private MaManagerMapper maManagerMapper;

	@Autowired
	public MaManagerMapper getMaManagerMapper() {
		return maManagerMapper;
	}

	@Autowired
	public void setMaManagerMapper(MaManagerMapper maManagerMapper) {
		this.maManagerMapper = maManagerMapper;
	}

	/**
	 * 新增实体对象
	 * @param maManager
	 */
	public MaManagerImpl insert(MaManagerImpl maManager) throws CoException {
		this.maManagerMapper.insert(maManager);
		return maManager;
	}

	/**
	 * 删除实体对象
	 * @param maManager
	 */
	public int delete(MaManagerImpl maManager) throws CoException {
		return this.maManagerMapper.delete(maManager);
	}

	/**
	 * 更新实体对象
	 * @param maManager
	 */
	public boolean update(MaManagerImpl maManager) throws CoException {
		boolean result = true;
		this.maManagerMapper.update(maManager);
		return result;
	}
	/**
	 * 查询实体列表
	 * @param maManager
	 */
	@SuppressWarnings("unchecked")
	public List<MaManagerImpl> select(MaManagerImpl maManager) throws CoException {
		return (List<MaManagerImpl>) this.maManagerMapper.select(maManager);
	}
	/**
	 * 查询单个实体
	 * @param maManager
	 */
	public MaManagerImpl get(MaManagerImpl maManager) throws CoException {
		return (MaManagerImpl) this.maManagerMapper.get(maManager);
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
	public PageInfo<MaManagerImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list =maManagerMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaManagerImpl> page = new PageInfo<MaManagerImpl>(list);
		return page;
	}
}