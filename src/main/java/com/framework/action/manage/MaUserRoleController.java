package com.framework.action.manage;

import java.util.List;
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
import com.framework.bean.impl.MaUserRoleImpl;
import com.framework.service.MaUserRoleService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 用户角色
 * 作者:    Auto
 * 日期:    2019/8/8
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maUserRole")
public class MaUserRoleController extends BaseController{

	@Autowired
	private MaUserRoleService maUserRoleService;

	@Autowired
	public MaUserRoleService getMaUserRoleService() {
		return maUserRoleService;
	}

	@Autowired
	public void setMaUserRoleService(MaUserRoleService maUserRoleService) {
		this.maUserRoleService = maUserRoleService;
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
		JSONObject requestJson = JSON.parseObject(requestParams);
		Integer pageNo = requestJson.getInteger("pageNo");
		Integer pageSize = requestJson.getInteger("pageSize");
		String orderBy = requestJson.getString("orderBy");
		String sqlWhere = CoUtil.assemblyWhere(requestJson, "maUserRole");
		PageInfo<MaUserRoleImpl> page;
		try {
			page = maUserRoleService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaUserRoleImpl> list = page.getList();
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
	 * @param maUserRole
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String userId = requestJson.getJSONObject("userdata").getJSONObject("userId").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idMaUserRole = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaUserRole = Long.valueOf(idString);
			}

			MaUserRoleImpl maUserRole = new MaUserRoleImpl();
			maUserRole.setEditState(editState);
			Long idUser = requestJson.getJSONObject("idUser").getLong("value");
			if(!UtValid.blank(idUser)) {
				resultJson.put("msg", "用户编号不可为空!");
				return resultJson.toString();
			}
			maUserRole.setIdUser(idUser);
			String nameUser = requestJson.getJSONObject("nameUser").getString("value");
			maUserRole.setNameUser(nameUser);
			Long idRole = requestJson.getJSONObject("idRole").getLong("value");
			if(!UtValid.blank(idRole)) {
				resultJson.put("msg", "角色编号不可为空!");
				return resultJson.toString();
			}
			maUserRole.setIdRole(idRole);
			String nameRole = requestJson.getJSONObject("nameRole").getString("value");
			maUserRole.setNameRole(nameRole);

			if ("i".equals(editState)) {
				maUserRole.setCreateUser(userId);
				maUserRole.setModifiedUser(userId);
				this.setCommonField(maUserRole);
				maUserRoleService.insert(maUserRole);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maUserRole.setId(id);
				this.setCommonField(maUserRole);
				maUserRoleService.update(maUserRole);
			} else if ("d".equals(editState)) {
				maUserRoleService.delete(maUserRole);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maUserRole.getId());
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
		try {
			Long id = Long.valueOf(idString);
			MaUserRoleImpl maUserRole = new MaUserRoleImpl();
			maUserRole.setId(id);
			maUserRole.setTag("d");
			maUserRoleService.update(maUserRole);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

}

