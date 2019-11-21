package com.framework.action.manage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.action.BaseController;
import com.framework.bean.impl.MaOrgnizationImpl;
import com.framework.bean.impl.OpProLineImpl;
import com.framework.exception.CoException;
import com.framework.service.MaOrgnizationService;
import com.framework.util.CoUtil;
import com.framework.util.PageUtil;
import com.framework.util.UtValid;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/8/13
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maOrgnization")
public class MaOrgnizationController extends BaseController{

	@Autowired
	private MaOrgnizationService maOrgnizationService;

	@Autowired
	public MaOrgnizationService getMaOrgnizationService() {
		return maOrgnizationService;
	}

	@Autowired
	public void setMaOrgnizationService(MaOrgnizationService maOrgnizationService) {
		this.maOrgnizationService = maOrgnizationService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "maOrgnization");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
				String relationFilter = "idMaCompany";
				sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			}
			PageInfo<MaOrgnizationImpl> page;
			page = maOrgnizationService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaOrgnizationImpl> list = page.getList();
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
	 * @param maOrgnization
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
			Long idMaOrgnization = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaOrgnization = Long.valueOf(idString);
			}

			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setEditState(editState);
			Long pidMaOrgnization = requestJson.getJSONObject("pidMaOrgnization").getLong("value");
			if(null == pidMaOrgnization) {
				pidMaOrgnization = 0L;
			}
			maOrgnization.setPidMaOrgnization(pidMaOrgnization);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "组织名称不可为空!");
				return resultJson.toString();
			}
			maOrgnization.setName(name);			
			Long idMaCompany = requestJson.getJSONObject("idMaCompany").getLong("value");
			if(!UtValid.blank(idMaCompany)) {
				resultJson.put("msg", "公司不可为空!");
				return resultJson.toString();
			}
			maOrgnization.setIdMaCompany(idMaCompany);
			Long idMaOrgnizationLevel = requestJson.getJSONObject("idMaOrgnizationLevel").getLong("value");
			if(!UtValid.blank(idMaOrgnizationLevel)) {
				resultJson.put("msg", "组织不可为空!");
				return resultJson.toString();
			}
			maOrgnization.setIdMaOrgnizationLevel(idMaOrgnizationLevel);
//			Integer levelMaOrgnizationLevel = requestJson.getJSONObject("levelMaOrgnizationLevel").getInteger("value");
//			if(!UtValid.blank(levelMaOrgnizationLevel)) {
//				resultJson.put("msg", "组织等级不可为空!");
//				return resultJson.toString();
//			}
//			maOrgnization.setLevelMaOrgnizationLevel(levelMaOrgnizationLevel);

			if ("i".equals(editState)) {
				maOrgnization.setCreateUser(userId);
				maOrgnization.setModifiedUser(userId);
				this.setCommonField(maOrgnization);
				maOrgnizationService.insert(maOrgnization);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maOrgnization.setId(id);
				this.setCommonField(maOrgnization);
				maOrgnizationService.update(maOrgnization);
			} else if ("d".equals(editState)) {
				maOrgnizationService.delete(maOrgnization);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maOrgnization.getId());
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
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setId(id);
			maOrgnization.setTag("d");
			maOrgnizationService.update(maOrgnization);
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
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setIdMaCompany(idMaCompany);
			List<MaOrgnizationImpl> treeMaOrgnization = (List<MaOrgnizationImpl>) maOrgnizationService.selectTree(maOrgnization);
			tree = JSON.toJSONString(treeMaOrgnization);
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
	@RequestMapping(value = "/findOrgTree", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String findOrgTree() {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			List<Map<String, Object>> listMaOrgnization = maOrgnizationService.findOrgTree("0");
			str = JSON.toJSONString(listMaOrgnization);
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return str;
	}
	
	/**
	 * 查询组织结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/findTree", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findTree() {
		String str = "";
		JSONObject resultJson = new JSONObject();
		try {
			List<Map<String, Object>> mapMaOrgnization = maOrgnizationService.findTree("0");
			str = JSON.toJSON(mapMaOrgnization).toString();
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return str;
	}
	
	/**
	 * 查询组织结构下拉树
	 * @return
	 */
	@RequestMapping(value = "/selectPidTree", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String selectPidTree(@RequestBody String requestParams) {
		String str = "";
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		try {
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setIdMaCompany(idMaCompany);
			maOrgnization.setPidMaOrgnization(0L);
			List<Map<String, Object>> mapMaOrgnization = maOrgnizationService.findTree(maOrgnization);
			str = JSON.toJSON(mapMaOrgnization).toString();
			resultJson.put("code", 200);
			resultJson.put("tree", str);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return str;
	}
	
	/**
	 * app端查询车间  工段 
	 * @param requestParams
	 * @return
	 */
	@RequestMapping(value = "/selectOrgTree", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectOrgTree(@RequestBody String requestParams) {
		String str = "";
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		try {
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long pidMaOrgnization = requestJson.getJSONObject("userdata").getJSONObject("pidMaOrgnization").getLong("value");
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setIdMaCompany(idMaCompany);
			maOrgnization.setPidMaOrgnization(pidMaOrgnization);
			List<MaOrgnizationImpl> mapMaOrgnization = (List<MaOrgnizationImpl>)maOrgnizationService.selectOrgTree(maOrgnization);
			//str = JSON.toJSON(mapMaOrgnization).toString();
			resultJson.put("code", 200);
			resultJson.put("data", mapMaOrgnization);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 主电室发牌查询电工人员
	 * @param requestParams
	 * @return
	 */
	@RequestMapping(value = "/selectElectricianTree", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectElectricianTree(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		try {
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long pidMaOrgnization = requestJson.getJSONObject("userdata").getJSONObject("pidMaOrgnization").getLong("value");
			MaOrgnizationImpl maOrgnization = new MaOrgnizationImpl();
			maOrgnization.setIdMaCompany(idMaCompany);
			maOrgnization.setPidMaOrgnization(pidMaOrgnization);
			List<MaOrgnizationImpl> mapMaOrgnization = (List<MaOrgnizationImpl>)maOrgnizationService.selectElectricianTree(maOrgnization);
			//str = JSON.toJSON(mapMaOrgnization).toString();
			resultJson.put("code", 200);
			resultJson.put("data", mapMaOrgnization);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

