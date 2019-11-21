package com.framework.action.operation;

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
import com.framework.bean.impl.OpCardPowerImpl;
import com.framework.service.OpCardPowerService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 停电牌
 * 作者:    Auto
 * 日期:    2019/8/17
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opCardPower")
public class OpCardPowerController extends BaseController{

	@Autowired
	private OpCardPowerService opCardPowerService;

	@Autowired
	public OpCardPowerService getOpCardPowerService() {
		return opCardPowerService;
	}

	@Autowired
	public void setOpCardPowerService(OpCardPowerService opCardPowerService) {
		this.opCardPowerService = opCardPowerService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opCardPower");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpCardPowerImpl> page;
			page = opCardPowerService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpCardPowerImpl> list = page.getList();
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
	 * @param opCardPower
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
			Long idOpCardPower = 0L;
			OpCardPowerImpl opCardPower = new OpCardPowerImpl();
			if(null != idMaCompany) {
			    opCardPower.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpCardPower = Long.valueOf(idString);
			}

			opCardPower.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opCardPower.setIdOpDevice(idOpDevice);

			if ("i".equals(editState)) {
				opCardPower.setCreateUser(userId);
				opCardPower.setModifiedUser(userId);
				this.setCommonField(opCardPower);
				opCardPowerService.insert(opCardPower);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opCardPower.setId(id);
				opCardPower.setModifiedUser(userId);
				this.setCommonField(opCardPower);
				opCardPowerService.update(opCardPower);
			} else if ("d".equals(editState)) {
				opCardPowerService.delete(opCardPower);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opCardPower.getId());
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
			OpCardPowerImpl opCardPower = new OpCardPowerImpl();
			opCardPower.setId(id);
			opCardPower.setTag("d");
			opCardPower.setEditState("u");
			opCardPower.setModifiedUser(userId);
			this.setCommonField(opCardPower);
			opCardPowerService.update(opCardPower);
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
			OpCardPowerImpl opCardPower = new OpCardPowerImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opCardPower.setIdMaCompany(idMaCompany);
			List<OpCardPowerImpl> treeOpCardPower = (List<OpCardPowerImpl>) opCardPowerService.selectTree(opCardPower);
			tree = JSON.toJSONString(treeOpCardPower);
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

