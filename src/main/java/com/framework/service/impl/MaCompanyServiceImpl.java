package com.framework.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.exception.CoException;
import com.framework.dao.MaCompanyConditionMapper;
import com.framework.dao.MaCompanyMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.bean.impl.MaRoleImpl;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.service.MaCompanyService;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 公司
 * 作者:     Auto
 * 日期:     2019/7/24
**********************************************
*/
@Service("maCompanyService")
public class MaCompanyServiceImpl extends CommonServiceImpl implements MaCompanyService {
	@Autowired
	private MaCompanyMapper maCompanyMapper;
	
	@Autowired
	private MaCompanyConditionMapper maCompanyConditionMapper;

	@Autowired
	public MaCompanyConditionMapper getMaCompanyConditionMapper() {
		return maCompanyConditionMapper;
	}

	@Autowired
	public void setMaCompanyConditionMapper(MaCompanyConditionMapper maCompanyConditionMapper) {
		this.maCompanyConditionMapper = maCompanyConditionMapper;
	}

	@Autowired
	public MaCompanyMapper getMaCompanyMapper() {
		return maCompanyMapper;
	}

	@Autowired
	public void setMaCompanyMapper(MaCompanyMapper maCompanyMapper) {
		this.maCompanyMapper = maCompanyMapper;
	}

	/**
	 * 新增实体对象
	 * @param maCompany
	 */
	public MaCompanyImpl insert(MaCompanyImpl maCompany) throws CoException {
		this.maCompanyMapper.insert(maCompany);
		return maCompany;
	}

	/**
	 * 删除实体对象
	 * @param maCompany
	 */
	public int delete(MaCompanyImpl maCompany) throws CoException {
		return this.maCompanyMapper.delete(maCompany);
	}

	/**
	 * 更新实体对象
	 * @param maCompany
	 */
	public boolean update(MaCompanyImpl maCompany) throws CoException {
		boolean result = true;
		this.maCompanyMapper.update(maCompany);
		return result;
	}
	/**
	 * 查询实体列表
	 * @param maCompany
	 */
	@SuppressWarnings("unchecked")
	public List<MaCompanyImpl> select(MaCompanyImpl maCompany) throws CoException {
		return (List<MaCompanyImpl>) this.maCompanyMapper.select(maCompany);
	}
	/**
	 * 查询单个实体
	 * @param maCompany
	 */
	public MaCompanyImpl get(MaCompanyImpl maCompany) throws CoException {
		return (MaCompanyImpl) this.maCompanyMapper.get(maCompany);
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
	public PageInfo<MaCompanyImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = maCompanyMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<MaCompanyImpl> page = new PageInfo<MaCompanyImpl>(list);
		return page;
	}
	 
		/**
		 * 查询树型实体
		 * @param maRole
		 */
		@SuppressWarnings("unchecked")
		public List<MaCompanyImpl> selectTree(MaCompanyImpl maCompany) throws CoException {
			return (List<MaCompanyImpl>) this.maCompanyMapper.selectTree(maCompany);
		}	 

		/**
		 * 查询组织结构下拉树
		 * @return
		 */
		@RequestMapping(value = "/findTree", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public List<Map<String, Object>> findTree(String pid) {
		    // 查找根节点
		    List<Map<String, Object>> list = maCompanyConditionMapper.findListByPid(pid);
		    List<Map<String, Object>> children;
		    for (Map<String, Object>  m: list) {
		    	String tPid = m.get("value").toString();
		        children = findTree(tPid);
		        if (children != null && children.size() != 0) { //查询组织机构的子节点，并赋值给元素“children”
		            m.put("children",children);
		        } 
		    }
		    return list;
		}
		
		/**
		 * 查询组织结构下拉树
		 * @return
		 */
		@RequestMapping(value = "/findSelect", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public List<Map<String, Object>> findSelect(String pid) {
			// 查找根节点
			List<Map<String, Object>> list = maCompanyConditionMapper.findListByPid(pid);
			List<Map<String, Object>> children;
			for (Map<String, Object>  m: list) {
				String tPid = m.get("value").toString();
				children = findTree(tPid);
				if (children != null && children.size() != 0) { //查询组织机构的子节点，并赋值给元素“children”
					m.put("children",children);
				} 
			}
			return list;
		}
}