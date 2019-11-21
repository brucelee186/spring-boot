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
import com.framework.bean.impl.SyStatusImpl;
import com.framework.service.SyStatusService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 系统状态
 * 作者:    Auto
 * 日期:    2019/8/21
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syStatus")
public class SyStatusController extends BaseController{

	@Autowired
	private SyStatusService syStatusService;

	/**
	 * 查询
	 * @param requestParams
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syStatus");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyStatusImpl> page;
			page = syStatusService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyStatusImpl> list = page.getList();
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
	public String toEdit(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String idString = requestJson.getString("id");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			SyStatusImpl syStatus = new SyStatusImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				syStatus.setId(id);
				syStatus = syStatusService.get(syStatus);
			}
			resultJson.put("code", 200);
			resultJson.put("syStatus", syStatus);
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
	public String edit(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idSyStatus = 0L;
			SyStatusImpl syStatus = new SyStatusImpl();
			if(null != idMaCompany) {
			    syStatus.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyStatus = Long.valueOf(idString);
			}

			syStatus.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "状态名称不可为空!");
				return resultJson.toString();
			}
			syStatus.setName(name);
			String statusTable = requestJson.getJSONObject("statusTable").getString("value");
			if(!UtValid.blank(statusTable)) {
				resultJson.put("msg", "表不可为空!");
				return resultJson.toString();
			}
			syStatus.setStatusTable(statusTable);
			String statusColumn = requestJson.getJSONObject("statusColumn").getString("value");
			if(!UtValid.blank(statusColumn)) {
				resultJson.put("msg", "列不可为空!");
				return resultJson.toString();
			}
			syStatus.setStatusColumn(statusColumn);
			String code = requestJson.getJSONObject("code").getString("value");
			if(!UtValid.blank(code)) {
				resultJson.put("msg", "编码不可为空!");
				return resultJson.toString();
			}
			SyStatusImpl syStatusValidate = new SyStatusImpl();
			syStatusValidate.setCode(code);
			if("u".equals(editState)) {
				syStatusValidate.setTagMapper("unique");
				syStatusValidate.setCode(null);
				syStatusValidate.setCodeUnique(code);
				syStatusValidate.setIdUnique(idSyStatus);
			}
			List<SyStatusImpl> listSyStatus = syStatusService.select(syStatusValidate);
			if(null != listSyStatus && listSyStatus.size() > 0) {
				resultJson.put("msg", "编码已存在!");
				return resultJson.toString();
			}
			syStatus.setCode(code);
			String remark = requestJson.getJSONObject("remark").getString("value");
			syStatus.setRemark(remark);

			if ("i".equals(editState)) {
				syStatus.setCreateUser(userId);
				syStatus.setModifiedUser(userId);
				this.setCommonField(syStatus);
				syStatusService.insert(syStatus);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syStatus.setId(id);
				syStatus.setModifiedUser(userId);
				this.setCommonField(syStatus);
				syStatusService.update(syStatus);
			} else if ("d".equals(editState)) {
				syStatusService.delete(syStatus);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syStatus.getId());
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
	public String delete(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			SyStatusImpl syStatus = new SyStatusImpl();
			syStatus.setId(id);
			syStatus.setModifiedUser(userId);
			this.setCommonField(syStatus);
			syStatusService.delete(syStatus);
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
	public String selectTree(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			SyStatusImpl syStatus = new SyStatusImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syStatus.setIdMaCompany(idMaCompany);
			List<SyStatusImpl> treeSyStatus = (List<SyStatusImpl>) syStatusService.selectTree(syStatus);
			tree = JSON.toJSONString(treeSyStatus);
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

