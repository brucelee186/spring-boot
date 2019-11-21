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
import com.framework.bean.impl.OpCardWorkImpl;
import com.framework.service.OpCardWorkService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 工作牌
 * 作者:    Auto
 * 日期:    2019/8/17
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opCardWork")
public class OpCardWorkController extends BaseController{

	@Autowired
	private OpCardWorkService opCardWorkService;

	@Autowired
	public OpCardWorkService getOpCardWorkService() {
		return opCardWorkService;
	}

	@Autowired
	public void setOpCardWorkService(OpCardWorkService opCardWorkService) {
		this.opCardWorkService = opCardWorkService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opCardWork");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpCardWorkImpl> page;
			page = opCardWorkService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpCardWorkImpl> list = page.getList();
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
	 * @param opCardWork
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
			Long idOpCardWork = 0L;
			OpCardWorkImpl opCardWork = new OpCardWorkImpl();
			if(null != idMaCompany) {
			    opCardWork.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpCardWork = Long.valueOf(idString);
			}

			opCardWork.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opCardWork.setIdOpDevice(idOpDevice);

			if ("i".equals(editState)) {
				opCardWork.setCreateUser(userId);
				opCardWork.setModifiedUser(userId);
				this.setCommonField(opCardWork);
				opCardWorkService.insert(opCardWork);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opCardWork.setId(id);
				opCardWork.setModifiedUser(userId);
				this.setCommonField(opCardWork);
				opCardWorkService.update(opCardWork);
			} else if ("d".equals(editState)) {
				opCardWorkService.delete(opCardWork);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opCardWork.getId());
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
			OpCardWorkImpl opCardWork = new OpCardWorkImpl();
			opCardWork.setId(id);
			opCardWork.setTag("d");
			opCardWork.setEditState("u");
			opCardWork.setModifiedUser(userId);
			this.setCommonField(opCardWork);
			opCardWorkService.update(opCardWork);
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
			OpCardWorkImpl opCardWork = new OpCardWorkImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opCardWork.setIdMaCompany(idMaCompany);
			List<OpCardWorkImpl> treeOpCardWork = (List<OpCardWorkImpl>) opCardWorkService.selectTree(opCardWork);
			tree = JSON.toJSONString(treeOpCardWork);
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

