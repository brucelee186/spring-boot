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
import com.framework.bean.impl.SyInforImpl;
import com.framework.service.SyInforService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 系统信息
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syInfor")
public class SyInforController extends BaseController{

	@Autowired
	private SyInforService syInforService;

	@Autowired
	public SyInforService getSyInforService() {
		return syInforService;
	}

	@Autowired
	public void setSyInforService(SyInforService syInforService) {
		this.syInforService = syInforService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syInfor");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyInforImpl> page;
			page = syInforService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyInforImpl> list = page.getList();
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
	 * @param syInfor
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
			Long idSyInfor = 0L;
			SyInforImpl syInfor = new SyInforImpl();
			if(null != idMaCompany) {
			    syInfor.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyInfor = Long.valueOf(idString);
			}

			syInfor.setEditState(editState);
			String codeType = requestJson.getJSONObject("codeType").getString("value");
			if(!UtValid.blank(codeType)) {
				resultJson.put("msg", "类型不可为空!");
				return resultJson.toString();
			}
			syInfor.setCodeType(codeType);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "科目名称不可为空!");
				return resultJson.toString();
			}
			syInfor.setName(name);
			String code = requestJson.getJSONObject("code").getString("value");
			if(!UtValid.blank(code)) {
				resultJson.put("msg", "编码不可为空!");
				return resultJson.toString();
			}
			syInfor.setCode(code);
			String value = requestJson.getJSONObject("value").getString("value");
			syInfor.setValue(value);
			String statusTable = requestJson.getJSONObject("statusTable").getString("value");
			syInfor.setStatusTable(statusTable);
			String statusColumn = requestJson.getJSONObject("statusColumn").getString("value");
			syInfor.setStatusColumn(statusColumn);
			Integer orderIndex = requestJson.getJSONObject("orderIndex").getInteger("value");
			syInfor.setOrderIndex(orderIndex);

			if ("i".equals(editState)) {
				syInfor.setCreateUser(userId);
				syInfor.setModifiedUser(userId);
				this.setCommonField(syInfor);
				syInforService.insert(syInfor);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syInfor.setId(id);
				syInfor.setModifiedUser(userId);
				this.setCommonField(syInfor);
				syInforService.update(syInfor);
			} else if ("d".equals(editState)) {
				syInforService.delete(syInfor);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syInfor.getId());
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
			SyInforImpl syInfor = new SyInforImpl();
			syInfor.setId(id);
			syInfor.setTag("d");
			syInfor.setEditState("u");
			syInfor.setModifiedUser(userId);
			this.setCommonField(syInfor);
			syInforService.update(syInfor);
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
			SyInforImpl syInfor = new SyInforImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syInfor.setIdMaCompany(idMaCompany);
			List<SyInforImpl> treeSyInfor = (List<SyInforImpl>) syInforService.selectTree(syInfor);
			tree = JSON.toJSONString(treeSyInfor);
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

