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
import com.framework.bean.impl.OpCardOperationImpl;
import com.framework.service.OpCardOperationService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 操作牌
 * 作者:    Auto
 * 日期:    2019/8/17
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opCardOperation")
public class OpCardOperationController extends BaseController{

	@Autowired
	private OpCardOperationService opCardOperationService;

	@Autowired
	public OpCardOperationService getOpCardOperationService() {
		return opCardOperationService;
	}

	@Autowired
	public void setOpCardOperationService(OpCardOperationService opCardOperationService) {
		this.opCardOperationService = opCardOperationService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opCardOperation");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpCardOperationImpl> page;
			page = opCardOperationService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpCardOperationImpl> list = page.getList();
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
	 * @param opCardOperation
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
			Long idOpCardOperation = 0L;
			OpCardOperationImpl opCardOperation = new OpCardOperationImpl();
			if(null != idMaCompany) {
			    opCardOperation.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpCardOperation = Long.valueOf(idString);
			}

			opCardOperation.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opCardOperation.setIdOpDevice(idOpDevice);
			Integer countMain = requestJson.getJSONObject("countMain").getInteger("value");
			opCardOperation.setCountMain(countMain);
			Integer countSub = requestJson.getJSONObject("countSub").getInteger("value");
			opCardOperation.setCountSub(countSub);
			Integer count = requestJson.getJSONObject("count").getInteger("value");
			opCardOperation.setCount(count);

			if ("i".equals(editState)) {
				opCardOperation.setCreateUser(userId);
				opCardOperation.setModifiedUser(userId);
				this.setCommonField(opCardOperation);
				opCardOperationService.insert(opCardOperation);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opCardOperation.setId(id);
				opCardOperation.setModifiedUser(userId);
				this.setCommonField(opCardOperation);
				opCardOperationService.update(opCardOperation);
			} else if ("d".equals(editState)) {
				opCardOperationService.delete(opCardOperation);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opCardOperation.getId());
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
			OpCardOperationImpl opCardOperation = new OpCardOperationImpl();
			opCardOperation.setId(id);
			opCardOperation.setTag("d");
			opCardOperation.setEditState("u");
			opCardOperation.setModifiedUser(userId);
			this.setCommonField(opCardOperation);
			opCardOperationService.update(opCardOperation);
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
			OpCardOperationImpl opCardOperation = new OpCardOperationImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opCardOperation.setIdMaCompany(idMaCompany);
			List<OpCardOperationImpl> treeOpCardOperation = (List<OpCardOperationImpl>) opCardOperationService.selectTree(opCardOperation);
			tree = JSON.toJSONString(treeOpCardOperation);
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

