package com.framework.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.MaOrgnizationLevel;
import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.bean.impl.MaOrgnizationLevelImpl;
import com.framework.bean.impl.OpProLineImpl;
import com.framework.dao.MaOrgnizationConditionMapper;
import com.framework.dao.MaOrgnizationLevelMapper;
import com.framework.dao.MaOrgnizationMapper;
import com.framework.exception.CoException;
import com.framework.service.MaOrgnizationService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 组织
 * 作者:     Auto
 * 日期:     2019/7/24
**********************************************
*/
@Service("maOrgnizationService")
public class MaOrgnizationServiceImpl extends CommonServiceImpl implements MaOrgnizationService {
	@Autowired
	private MaOrgnizationMapper maOrgnizationMapper;
	
	@Autowired
	private MaOrgnizationLevelMapper maOrgnizationLevelMapper;
	
	@Autowired
	private MaOrgnizationConditionMapper maOrgnizationConditionMapper;

	@Autowired
	public MaOrgnizationConditionMapper getMaOrgnizationConditionMapper() {
		return maOrgnizationConditionMapper;
	}

	@Autowired
	public void setMaOrgnizationConditionMapper(MaOrgnizationConditionMapper maOrgnizationConditionMapper) {
		this.maOrgnizationConditionMapper = maOrgnizationConditionMapper;
	}

	@Autowired
	public MaOrgnizationMapper getMaOrgnizationMapper() {
		return maOrgnizationMapper;
	}

	@Autowired
	public void setMaOrgnizationMapper(MaOrgnizationMapper maOrgnizationMapper) {
		this.maOrgnizationMapper = maOrgnizationMapper;
	}

	/**
	 * 新增实体对象
	 * @param maOrgnization
	 */
	public MaOrgnizationImpl insert(MaOrgnizationImpl maOrgnization) throws CoException {
		this.setMaOrgnizationLevel(maOrgnization);
		this.maOrgnizationMapper.insert(maOrgnization);
		return maOrgnization;
	}

	/**
	 * 删除实体对象
	 * @param maOrgnization
	 */
	public int delete(MaOrgnizationImpl maOrgnization) throws CoException {
		return this.maOrgnizationMapper.delete(maOrgnization);
	}

	/**
	 * 更新实体对象
	 * @param maOrgnization
	 */
	public boolean update(MaOrgnizationImpl maOrgnization) throws CoException {
		boolean result = true;
		this.setMaOrgnizationLevel(maOrgnization);
		this.maOrgnizationMapper.update(maOrgnization);
		return result;
	}
	/**
	 * 查询实体列表
	 * @param maOrgnization
	 */
	@SuppressWarnings("unchecked")
	public List<MaOrgnizationImpl> select(MaOrgnizationImpl maOrgnization) throws CoException {
		return (List<MaOrgnizationImpl>) this.maOrgnizationMapper.select(maOrgnization);
	}
	/**
	 * 查询单个实体
	 * @param maOrgnization
	 */
	public MaOrgnizationImpl get(MaOrgnizationImpl maOrgnization) throws CoException {
		return (MaOrgnizationImpl) this.maOrgnizationMapper.get(maOrgnization);
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
	public PageInfo<MaOrgnizationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list =maOrgnizationMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaOrgnizationImpl> page = new PageInfo<MaOrgnizationImpl>(list);
		return page;
	}
	 
	 /**
	  * 查询子节点
	  */
	 public List<Map<String, Object>> findTree(String pid) {
		    // 查找根节点
		    List<Map<String, Object>> list = maOrgnizationConditionMapper.findListByPid(pid);
		    List<Map<String, Object>> children;
		    for (Map<String, Object>  m: list) {
		    	pid = m.get("value").toString();
		        children = findTree(pid);
		        //查询组织机构的子节点，并赋值给元素“children”
		        if (children != null && children.size() != 0) { 
		            m.put("children",children);
		        } 
		    }
		    return list;
		}
	 
	 /**
	  * 查询子节点
	  */
	 public List<Map<String, Object>> findTree(MaOrgnizationImpl maOrgnization) {
		 // 查找根节点
		 Long pidMaOrgnization = maOrgnization.getPidMaOrgnization();
		 List<Map<String, Object>> list = maOrgnizationConditionMapper.findTree(maOrgnization);
		 List<Map<String, Object>> children;
		 for (Map<String, Object>  m: list) {
			 pidMaOrgnization = Long.valueOf(m.get("value").toString());
			 maOrgnization.setPidMaOrgnization(pidMaOrgnization);
			 children = findTree(maOrgnization);
			 //查询组织机构的子节点，并赋值给元素“children”
			 if (children != null && children.size() != 0) { 
				 m.put("children",children);
			 } 
		 }
		 return list;
	 }
	 
	 /**
	  * 查询子节点
	  */
	 public List<Map<String, Object>> findOrgTree(String pid) {
		 // 查找根节点
		 List<Map<String, Object>> list = maOrgnizationConditionMapper.findListByPid(pid);
		 List<Map<String, Object>> children;
		 for (Map<String, Object>  m: list) {
			 String tPid = m.get("value").toString();
			 children = findTree(tPid);
			 // 查询组织机构的子节点，并赋值给元素“children”
			 if (children != null && children.size() != 0) { 
				 m.put("children",children);
			 } 
		 }
		 return list;
	 }
	 
	/**
	 * 查询树型实体
	 * @param maOrgnization
	 */
	@SuppressWarnings("unchecked")
	public List<MaOrgnizationImpl> selectTree(MaOrgnizationImpl maOrgnization) throws CoException {
		return (List<MaOrgnizationImpl>) this.maOrgnizationMapper.selectTree(maOrgnization);
	}
	
	/**
	 * 查询树型实体
	 *  @param maOrgnization
	 */
	public List<MaOrgnizationImpl> selectOrgTree(MaOrgnizationImpl maOrgnization) {
		 // 查找根节点
		return (List<MaOrgnizationImpl>) this.maOrgnizationConditionMapper.selectOrgTree(maOrgnization);
	}
	
	/**
	 * 取得组织等级
	 * @param maOrgnization
	 */
	private void setMaOrgnizationLevel(MaOrgnizationImpl maOrgnization) {
		Long idMaOrgnizationLevel = maOrgnization.getIdMaOrgnizationLevel();
		MaOrgnizationLevelImpl maOrgnizationLevel = new MaOrgnizationLevelImpl();
		maOrgnizationLevel = maOrgnizationLevelMapper.get(maOrgnizationLevel);
		Integer levelMaOrgnizationLevel = 0;
		if(null != maOrgnizationLevel) {
			levelMaOrgnizationLevel = maOrgnizationLevel.getLevel();
		}
		maOrgnization.setLevelMaOrgnizationLevel(levelMaOrgnizationLevel);
	}
	
	/**
	 * 查询带班人  查检员 电工
	 * @param form
	 * @param session
	 * @return
	 */
	public List<MaOrgnizationImpl> selectElectricianTree(MaOrgnizationImpl maOrgnization) throws CoException{
		return (List<MaOrgnizationImpl>) this.maOrgnizationConditionMapper.selectElectricianTree(maOrgnization);
	}
	 
}