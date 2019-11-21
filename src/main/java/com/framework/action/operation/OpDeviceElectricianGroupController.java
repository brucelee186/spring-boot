﻿package com.framework.action.operation;

import java.util.List;
import java.util.Date;
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
import com.framework.bean.impl.OpDeviceElectricianGroupImpl;
import com.framework.service.OpDeviceElectricianGroupService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 设备电工组
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opDeviceElectricianGroup")
public class OpDeviceElectricianGroupController extends BaseController{

	@Autowired
	private OpDeviceElectricianGroupService opDeviceElectricianGroupService;

	@Autowired
	public OpDeviceElectricianGroupService getOpDeviceElectricianGroupService() {
		return opDeviceElectricianGroupService;
	}

	@Autowired
	public void setOpDeviceElectricianGroupService(OpDeviceElectricianGroupService opDeviceElectricianGroupService) {
		this.opDeviceElectricianGroupService = opDeviceElectricianGroupService;
	}

	/**
	 * 查询
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getX5Page", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getX5Page(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Integer pageNo = requestJson.getInteger("pageNo");
			Integer pageSize = requestJson.getInteger("pageSize");
			String orderBy = requestJson.getString("orderBy");
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opDeviceElectricianGroup");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpDeviceElectricianGroupImpl> page;
			page = opDeviceElectricianGroupService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpDeviceElectricianGroupImpl> list = page.getList();
			JSONArray listAry = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
			resultJson.put("code", 200);
			resultJson.put("page", listAry);
			resultJson.put("totalCount", page.getTotal());
			resultJson.put("pagesQty", page.getPages());
			resultJson.put("isHasPreviousPage", page.isHasPreviousPage());
			resultJson.put("isHasNextPage", page.isHasNextPage());
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return PageUtil.toX5JSONObject(resultJson).toString();
	}

	/**
	 * 编辑
	 * @param opDeviceElectricianGroup
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idOpDeviceElectricianGroup = 0L;
			OpDeviceElectricianGroupImpl opDeviceElectricianGroup = new OpDeviceElectricianGroupImpl();
			if(null != idMaCompany) {
			    opDeviceElectricianGroup.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpDeviceElectricianGroup = Long.valueOf(idString);
			}

			opDeviceElectricianGroup.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "工单名称不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianGroup.setName(name);

			if ("i".equals(editState)) {
				opDeviceElectricianGroup.setCreateUser(userId);
				opDeviceElectricianGroup.setModifiedUser(userId);
				this.setCommonField(opDeviceElectricianGroup);
				opDeviceElectricianGroupService.insert(opDeviceElectricianGroup);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opDeviceElectricianGroup.setId(id);
				opDeviceElectricianGroup.setModifiedUser(userId);
				this.setCommonField(opDeviceElectricianGroup);
				opDeviceElectricianGroupService.update(opDeviceElectricianGroup);
			} else if ("d".equals(editState)) {
				opDeviceElectricianGroupService.delete(opDeviceElectricianGroup);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opDeviceElectricianGroup.getId());
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 删除
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			OpDeviceElectricianGroupImpl opDeviceElectricianGroup = new OpDeviceElectricianGroupImpl();
			opDeviceElectricianGroup.setId(id);
			opDeviceElectricianGroup.setTag("d");
			opDeviceElectricianGroup.setEditState("u");
			opDeviceElectricianGroup.setModifiedUser(userId);
			this.setCommonField(opDeviceElectricianGroup);
			opDeviceElectricianGroupService.update(opDeviceElectricianGroup);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 查询树型结构
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectTree", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectTree(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceElectricianGroupImpl opDeviceElectricianGroup = new OpDeviceElectricianGroupImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opDeviceElectricianGroup.setIdMaCompany(idMaCompany);
			List<OpDeviceElectricianGroupImpl> treeOpDeviceElectricianGroup = (List<OpDeviceElectricianGroupImpl>) opDeviceElectricianGroupService.selectTree(opDeviceElectricianGroup);
			tree = JSON.toJSONString(treeOpDeviceElectricianGroup);
			resultJson.put("code", 200);
			resultJson.put("tree", tree);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return tree;
	}

}

