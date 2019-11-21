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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.util.CoUtil;
import com.framework.util.UtValid;
import com.framework.util.PageUtil;
import com.framework.action.BaseController;
import com.framework.bean.impl.SyInforStatusImpl;
import com.framework.service.SyInforStatusService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 系统状态信息
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syInforStatus")
public class SyInforStatusController extends BaseController{

	@Autowired
	private SyInforStatusService syInforStatusService;

	@Autowired
	public SyInforStatusService getSyInforStatusService() {
		return syInforStatusService;
	}

	@Autowired
	public void setSyInforStatusService(SyInforStatusService syInforStatusService) {
		this.syInforStatusService = syInforStatusService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syInforStatus");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyInforStatusImpl> page;
			page = syInforStatusService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyInforStatusImpl> list = page.getList();
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
	 * @param syInforStatus
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
			Long idSyInforStatus = 0L;
			SyInforStatusImpl syInforStatus = new SyInforStatusImpl();
			if(null != idMaCompany) {
			    syInforStatus.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyInforStatus = Long.valueOf(idString);
			}

			syInforStatus.setEditState(editState);
			Long idSyInfor = requestJson.getJSONObject("idSyInfor").getLong("value");
			if(!UtValid.blank(idSyInfor)) {
				resultJson.put("msg", "父科目不可为空!");
				return resultJson.toString();
			}
			syInforStatus.setIdSyInfor(idSyInfor);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "科目名称不可为空!");
				return resultJson.toString();
			}
			syInforStatus.setName(name);
			String code = requestJson.getJSONObject("code").getString("value");
			if(!UtValid.blank(code)) {
				resultJson.put("msg", "编码不可为空!");
				return resultJson.toString();
			}
			syInforStatus.setCode(code);
			String value = requestJson.getJSONObject("value").getString("value");
			syInforStatus.setValue(value);
			String statusTable = requestJson.getJSONObject("statusTable").getString("value");
			syInforStatus.setStatusTable(statusTable);
			String statusColumn = requestJson.getJSONObject("statusColumn").getString("value");
			syInforStatus.setStatusColumn(statusColumn);
			Integer orderIndex = requestJson.getJSONObject("orderIndex").getInteger("value");
			syInforStatus.setOrderIndex(orderIndex);

			if ("i".equals(editState)) {
				syInforStatus.setCreateUser(userId);
				syInforStatus.setModifiedUser(userId);
				this.setCommonField(syInforStatus);
				syInforStatusService.insert(syInforStatus);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syInforStatus.setId(id);
				syInforStatus.setModifiedUser(userId);
				this.setCommonField(syInforStatus);
				syInforStatusService.update(syInforStatus);
			} else if ("d".equals(editState)) {
				syInforStatusService.delete(syInforStatus);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syInforStatus.getId());
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
			SyInforStatusImpl syInforStatus = new SyInforStatusImpl();
			syInforStatus.setId(id);
			syInforStatus.setTag("d");
			syInforStatus.setEditState("u");
			syInforStatus.setModifiedUser(userId);
			this.setCommonField(syInforStatus);
			syInforStatusService.update(syInforStatus);
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
			SyInforStatusImpl syInforStatus = new SyInforStatusImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syInforStatus.setIdMaCompany(idMaCompany);
			List<SyInforStatusImpl> treeSyInforStatus = (List<SyInforStatusImpl>) syInforStatusService.selectTree(syInforStatus);
			tree = JSON.toJSONString(treeSyInforStatus);
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

