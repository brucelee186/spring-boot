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
import com.framework.bean.impl.SyNotificationImpl;
import com.framework.service.SyNotificationService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 提示推送
 * 作者:    Auto
 * 日期:    2019/8/26
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syNotification")
public class SyNotificationController extends BaseController{

	@Autowired
	private SyNotificationService syNotificationService;

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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syNotification");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyNotificationImpl> page;
			page = syNotificationService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyNotificationImpl> list = page.getList();
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
			SyNotificationImpl syNotification = new SyNotificationImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				syNotification.setId(id);
				syNotification = syNotificationService.get(syNotification);
			}
			resultJson.put("code", 200);
			resultJson.put("syNotification", syNotification);
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
			Long idSyNotification = 0L;
			SyNotificationImpl syNotification = new SyNotificationImpl();
			if(null != idMaCompany) {
			    syNotification.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyNotification = Long.valueOf(idString);
			}

			syNotification.setEditState(editState);
			Long idOpWorkOrder = requestJson.getJSONObject("idOpWorkOrder").getLong("value");
			syNotification.setIdOpWorkOrder(idOpWorkOrder);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			syNotification.setIdMaUser(idMaUser);
			String uidMaUser = requestJson.getJSONObject("uidMaUser").getString("value");
			syNotification.setUidMaUser(uidMaUser);
			String nameMaUser = requestJson.getJSONObject("nameMaUser").getString("value");
			syNotification.setNameMaUser(nameMaUser);
			String nameMaRole = requestJson.getJSONObject("nameMaRole").getString("value");
			syNotification.setNameMaRole(nameMaRole);
			String title = requestJson.getJSONObject("title").getString("value");
			if(!UtValid.blank(title)) {
				resultJson.put("msg", "标题不可为空!");
				return resultJson.toString();
			}
			syNotification.setTitle(title);
			String content = requestJson.getJSONObject("content").getString("value");
			if(!UtValid.blank(content)) {
				resultJson.put("msg", "提示内容不可为空!");
				return resultJson.toString();
			}
			syNotification.setContent(content);
			String alert = requestJson.getJSONObject("alert").getString("value");
			if(!UtValid.blank(alert)) {
				resultJson.put("msg", "警告内容不可为空!");
				return resultJson.toString();
			}
			syNotification.setAlert(alert);
			String remark = requestJson.getJSONObject("remark").getString("value");
			syNotification.setRemark(remark);

			if ("i".equals(editState)) {
				syNotification.setCreateUser(userId);
				syNotification.setModifiedUser(userId);
				this.setCommonField(syNotification);
				syNotificationService.insert(syNotification);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syNotification.setId(id);
				syNotification.setModifiedUser(userId);
				this.setCommonField(syNotification);
				syNotificationService.update(syNotification);
			} else if ("d".equals(editState)) {
				syNotificationService.delete(syNotification);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syNotification.getId());
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
			SyNotificationImpl syNotification = new SyNotificationImpl();
			syNotification.setId(id);
			syNotification.setModifiedUser(userId);
			this.setCommonField(syNotification);
			syNotificationService.delete(syNotification);
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
			SyNotificationImpl syNotification = new SyNotificationImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syNotification.setIdMaCompany(idMaCompany);
			List<SyNotificationImpl> treeSyNotification = (List<SyNotificationImpl>) syNotificationService.selectTree(syNotification);
			tree = JSON.toJSONString(treeSyNotification);
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

