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
import com.framework.bean.impl.MaOrgnizationLevelImpl;
import com.framework.service.MaOrgnizationLevelService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 组织层级
 * 作者:    Auto
 * 日期:    2019/8/13
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maOrgnizationLevel")
public class MaOrgnizationLevelController extends BaseController{

	@Autowired
	private MaOrgnizationLevelService maOrgnizationLevelService;

	@Autowired
	public MaOrgnizationLevelService getMaOrgnizationLevelService() {
		return maOrgnizationLevelService;
	}

	@Autowired
	public void setMaOrgnizationLevelService(MaOrgnizationLevelService maOrgnizationLevelService) {
		this.maOrgnizationLevelService = maOrgnizationLevelService;
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
		String sqlWhere = CoUtil.assemblyWhere(requestJson, "maOrgnizationLevel");
		PageInfo<MaOrgnizationLevelImpl> page;
		try {
			page = maOrgnizationLevelService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaOrgnizationLevelImpl> list = page.getList();
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
	 * @param maOrgnizationLevel
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
			Long idMaOrgnizationLevel = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaOrgnizationLevel = Long.valueOf(idString);
			}

			MaOrgnizationLevelImpl maOrgnizationLevel = new MaOrgnizationLevelImpl();
			maOrgnizationLevel.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "层级名称不可为空!");
				return resultJson.toString();
			}
			maOrgnizationLevel.setName(name);
			Long idMaCompany = requestJson.getJSONObject("idMaCompany").getLong("value");
			if(!UtValid.blank(idMaCompany)) {
				resultJson.put("msg", "公司名称不可为空!");
				return resultJson.toString();
			}
			maOrgnizationLevel.setIdMaCompany(idMaCompany);
			Integer level = requestJson.getJSONObject("level").getInteger("value");
			if(!UtValid.blank(level)) {
				resultJson.put("msg", "组织层级不可为空!");
				return resultJson.toString();
			}
			maOrgnizationLevel.setLevel(level);

			if ("i".equals(editState)) {
				maOrgnizationLevel.setCreateUser(userId);
				maOrgnizationLevel.setModifiedUser(userId);
				this.setCommonField(maOrgnizationLevel);
				maOrgnizationLevelService.insert(maOrgnizationLevel);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maOrgnizationLevel.setId(id);
				this.setCommonField(maOrgnizationLevel);
				maOrgnizationLevelService.update(maOrgnizationLevel);
			} else if ("d".equals(editState)) {
				maOrgnizationLevelService.delete(maOrgnizationLevel);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maOrgnizationLevel.getId());
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
			MaOrgnizationLevelImpl maOrgnizationLevel = new MaOrgnizationLevelImpl();
			maOrgnizationLevel.setId(id);
			maOrgnizationLevel.setTag("d");
			maOrgnizationLevelService.update(maOrgnizationLevel);
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
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			MaOrgnizationLevelImpl maOrgnizationLevel = new MaOrgnizationLevelImpl();
			maOrgnizationLevel.setIdMaCompany(idMaCompany);
			List<MaOrgnizationLevelImpl> treeMaOrgnizationLevel = (List<MaOrgnizationLevelImpl>) maOrgnizationLevelService.selectTree(maOrgnizationLevel);
			tree = JSON.toJSONString(treeMaOrgnizationLevel);
			resultJson.put("code", 200);
			resultJson.put("tree", tree);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return tree;
	}
	/**
	 * 查询组织结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/findNameId", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String findNameId(@RequestBody String requestParams) {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			String nameColumn = "orgLevelName";
			String sqlWhere = "1 = 1";
			String orderBy = "id";
			JSONArray mapMaOrgnization = maOrgnizationLevelService.findNameId(nameColumn, sqlWhere, orderBy);
			//str = JSON.toJSON(mapMaOrgnization).toString();
			resultJson.put("code", 200);
			resultJson.put("list", mapMaOrgnization);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
}

