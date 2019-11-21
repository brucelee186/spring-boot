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
import com.framework.bean.impl.MaRoleImpl;
import com.framework.service.MaRoleService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 角色管理
 * 作者:    Auto
 * 日期:    2019/8/12
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maRole")
public class MaRoleController extends BaseController{

	@Autowired
	private MaRoleService maRoleService;

	@Autowired
	public MaRoleService getMaRoleService() {
		return maRoleService;
	}

	@Autowired
	public void setMaRoleService(MaRoleService maRoleService) {
		this.maRoleService = maRoleService;
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
		String sqlWhere = CoUtil.assemblyWhere(requestJson, "maRole");
		PageInfo<MaRoleImpl> page;
		try {
			page = maRoleService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaRoleImpl> list = page.getList();
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
	 * @param maRole
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idMaRole = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaRole = Long.valueOf(idString);
			}

			MaRoleImpl maRole = new MaRoleImpl();
			maRole.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "角色名称不可为空!");
				return resultJson.toString();
			}
			maRole.setName(name);
			String code = requestJson.getJSONObject("code").getString("value");
			if(!UtValid.blank(code)) {
				resultJson.put("msg", "编码不可为空!");
				return resultJson.toString();
			}
			maRole.setCode(code);
			Integer level = requestJson.getJSONObject("level").getInteger("value");
			if(!UtValid.blank(level)) {
				resultJson.put("msg", "等级不可为空!");
				return resultJson.toString();
			}
			maRole.setLevel(level);
			String description = requestJson.getJSONObject("description").getString("value");
			maRole.setDescription(description);

			if ("i".equals(editState)) {
				maRole.setCreateUser(userId);
				maRole.setModifiedUser(userId);
				this.setCommonField(maRole);
				maRoleService.insert(maRole);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maRole.setId(id);
				this.setCommonField(maRole);
				maRoleService.update(maRole);
			} else if ("d".equals(editState)) {
				maRoleService.delete(maRole);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maRole.getId());
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
			MaRoleImpl maRole = new MaRoleImpl();
			maRole.setId(id);
			maRole.setTag("d");
			maRoleService.update(maRole);
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
	@RequestMapping(value="/selectTree", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectTree(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			MaRoleImpl maRole = new MaRoleImpl();
			List<MaRoleImpl> treeMaRole = (List<MaRoleImpl>) maRoleService.selectTree(maRole);
			tree = JSON.toJSONString(treeMaRole);
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

