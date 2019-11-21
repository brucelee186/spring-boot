package com.framework.action.system;

import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.util.CoUtil;
import com.framework.util.UtValid;
import com.framework.util.PageUtil;
import com.framework.action.BaseController;
import com.framework.bean.impl.SyHistoryImpl;
import com.framework.service.SyHistoryService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 系统状态明细
 * 作者:    Auto
 * 日期:    2019/9/26
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syHistory")
public class SyHistoryController extends BaseController{

	@Autowired
	private SyHistoryService syHistoryService;

	/**
	 * 查询
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getX5Page", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getX5Page(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Integer pageNo = requestJson.getInteger("pageNo");
			Integer pageSize = requestJson.getInteger("pageSize");
			String orderBy = requestJson.getString("orderBy");
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syHistory");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyHistoryImpl> page;
			page = syHistoryService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyHistoryImpl> list = page.getList();
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
	 * 跳转编辑
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/toEdit", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String toEdit(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String idString = requestJson.getString("id");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			SyHistoryImpl syHistory = new SyHistoryImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				syHistory.setId(id);
				syHistory.setRequest(request);
				syHistory = syHistoryService.get(syHistory);
			}
			resultJson.put("code", 200);
			resultJson.put("syHistory", syHistory);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 编辑
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idSyHistory = 0L;
			SyHistoryImpl syHistory = new SyHistoryImpl();
			if(null != idMaCompany) {
			    syHistory.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyHistory = Long.valueOf(idString);
			}

			syHistory.setEditState(editState);
			Long idOpWorkOrder = requestJson.getJSONObject("idOpWorkOrder").getLong("value");
			syHistory.setIdOpWorkOrder(idOpWorkOrder);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			syHistory.setIdMaUser(idMaUser);
			String uidMaUser = requestJson.getJSONObject("uidMaUser").getString("value");
			syHistory.setUidMaUser(uidMaUser);
			String nameMaUser = requestJson.getJSONObject("nameMaUser").getString("value");
			syHistory.setNameMaUser(nameMaUser);
			String nameMaRole = requestJson.getJSONObject("nameMaRole").getString("value");
			syHistory.setNameMaRole(nameMaRole);
			String atcion = requestJson.getJSONObject("atcion").getString("value");
			syHistory.setAtcion(atcion);
			String log = requestJson.getJSONObject("log").getString("value");
			syHistory.setLog(log);
			String remark = requestJson.getJSONObject("remark").getString("value");
			syHistory.setRemark(remark);
			syHistory.setRequest(request);

			if ("i".equals(editState)) {
				syHistory.setCreateUser(userId);
				syHistory.setModifiedUser(userId);
				this.setCommonField(syHistory);
				syHistoryService.insert(syHistory);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syHistory.setId(id);
				syHistory.setModifiedUser(userId);
				this.setCommonField(syHistory);
				syHistoryService.update(syHistory);
			} else if ("d".equals(editState)) {
				syHistoryService.delete(syHistory);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syHistory.getId());
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 删除
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			SyHistoryImpl syHistory = new SyHistoryImpl();
			syHistory.setId(id);
			syHistory.setModifiedUser(userId);
			syHistory.setRequest(request);
			this.setCommonField(syHistory);
			syHistoryService.delete(syHistory);
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
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectTree", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectTree(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			SyHistoryImpl syHistory = new SyHistoryImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syHistory.setIdMaCompany(idMaCompany);
			syHistory.setRequest(request);
			List<SyHistoryImpl> treeSyHistory = (List<SyHistoryImpl>) syHistoryService.selectTree(syHistory);
			tree = JSON.toJSONString(treeSyHistory);
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

