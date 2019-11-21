package com.framework.action.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.util.CoUtil;
import com.framework.util.UtValid;
import com.framework.util.PageUtil;
import com.framework.action.BaseController;
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.bean.impl.MaOrgnizationLevelImpl;
import com.framework.dao.MaCompanyConditionMapper;
import com.framework.service.MaCompanyService;
import com.framework.service.MaOrgnizationLevelService;


@Controller
@CrossOrigin
@RequestMapping("/common/common")
public class CommonController extends BaseController{

	@Autowired
	private MaCompanyService maCompanyService;
	
	@Autowired
	private MaCompanyConditionMapper maCompanyConditionMapper;
	
	@Autowired
	private MaOrgnizationLevelService maOrgnizationLevelService;

	@Autowired
	public MaCompanyConditionMapper getMaCompanyConditionMapper() {
		return maCompanyConditionMapper;
	}

	@Autowired
	public void setMaCompanyConditionMapper(MaCompanyConditionMapper maCompanyConditionMapper) {
		this.maCompanyConditionMapper = maCompanyConditionMapper;
	}

	@Autowired
	public MaOrgnizationLevelService getMaOrgnizationLevelService() {
		return maOrgnizationLevelService;
	}

	@Autowired
	public void setMaOrgnizationLevelService(MaOrgnizationLevelService maOrgnizationLevelService) {
		this.maOrgnizationLevelService = maOrgnizationLevelService;
	}

	@Autowired
	public MaCompanyService getMaCompanyService() {
		return maCompanyService;
	}

	@Autowired
	public void setMaCompanyService(MaCompanyService maCompanyService) {
		this.maCompanyService = maCompanyService;
	}

	
	/**
	 * 查询组织层级结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/findTreeMaOrgnizationLevel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String findTreeMaOrgnizationLevel(@RequestBody String requestParams) {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			MaOrgnizationLevelImpl maOrgizationLevel = new MaOrgnizationLevelImpl();
			List<MaOrgnizationLevelImpl> listMaOrgnizationLevel = maOrgnizationLevelService.select(maOrgizationLevel);
			str = JSON.toJSONString(listMaOrgnizationLevel);
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 查询组织层级结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/findTreeMaOrgnizationLevel2", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String findTreeMaOrgnizationLevel2(@RequestBody String requestParams) {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaOrgnizationLevelImpl maOrgizationLevel = new MaOrgnizationLevelImpl();
			String idString = requestJson.getString("idMaCompany");
			if(null != idString && idString.length() > 0) {
				Long idMaCompany = Long.valueOf(requestJson.getString("idMaCompany"));
				maOrgizationLevel.setIdMaCompany(idMaCompany);
				List<MaOrgnizationLevelImpl> listMaOrgnizationLevel = maOrgnizationLevelService.select(maOrgizationLevel);
				if(null == listMaOrgnizationLevel || listMaOrgnizationLevel.size() == 0) {
					maOrgizationLevel = new MaOrgnizationLevelImpl();
					maOrgizationLevel.setTitle("");
					maOrgizationLevel.setValue("");
					listMaOrgnizationLevel.add(maOrgizationLevel);
				}
				str = JSON.toJSONString(listMaOrgnizationLevel);
			}
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 查询组织结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/findTreeMaCompany", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String findTreeMaCompany() {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			MaCompanyImpl maCompany = new MaCompanyImpl();
			List<MaCompanyImpl> mapMaCompany = (List<MaCompanyImpl>) maCompanyConditionMapper.select(maCompany);
			str = JSON.toJSONString(mapMaCompany);
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

}

