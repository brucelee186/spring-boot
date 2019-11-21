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
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.service.MaCompanyService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 公司管理
 * 作者:    Auto
 * 日期:    2019/8/12
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maCompany")
public class MaCompanyController extends BaseController{

	@Autowired
	private MaCompanyService maCompanyService;

	@Autowired
	public MaCompanyService getMaCompanyService() {
		return maCompanyService;
	}

	@Autowired
	public void setMaCompanyService(MaCompanyService maCompanyService) {
		this.maCompanyService = maCompanyService;
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
		String sqlWhere = CoUtil.assemblyWhere(requestJson, "maCompany");
		PageInfo<MaCompanyImpl> page;
		try {
			page = maCompanyService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaCompanyImpl> list = page.getList();
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
	 * @param maCompany
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
			Long idMaCompany = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaCompany = Long.valueOf(idString);
			}

			MaCompanyImpl maCompany = new MaCompanyImpl();
			maCompany.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "公司名称不可为空!");
				return resultJson.toString();
			}
			maCompany.setName(name);
			String tel = requestJson.getJSONObject("tel").getString("value");
			if(!UtValid.blank(tel)) {
				resultJson.put("msg", "电话不可为空!");
				return resultJson.toString();
			}
			if(!UtValid.phone(tel)) {
				resultJson.put("msg", "电话格式不正确!");
				return resultJson.toString();
			}
			maCompany.setTel(tel);
			String fax = requestJson.getJSONObject("fax").getString("value");
			if(fax.length() > 0 && !UtValid.fax(fax)) {
				resultJson.put("msg", "传真格式不正确!");
				return resultJson.toString();
			}
			maCompany.setFax(fax);
			String mail = requestJson.getJSONObject("mail").getString("value");
			if(mail.length() > 0 && !UtValid.email(mail)) {
				resultJson.put("msg", "电子邮件格式不正确!");
				return resultJson.toString();
			}
			maCompany.setMail(mail);
			String webUrl = requestJson.getJSONObject("webUrl").getString("value");
			if(webUrl.length() > 0 && !UtValid.url(webUrl)) {
				resultJson.put("msg", "公司主页格式不正确!");
				return resultJson.toString();
			}
			maCompany.setWebUrl(webUrl);
			String description = requestJson.getJSONObject("description").getString("value");
			maCompany.setDescription(description);

			if ("i".equals(editState)) {
				maCompany.setCreateUser(userId);
				maCompany.setModifiedUser(userId);
				this.setCommonField(maCompany);
				maCompanyService.insert(maCompany);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maCompany.setId(id);
				this.setCommonField(maCompany);
				maCompanyService.update(maCompany);
			} else if ("d".equals(editState)) {
				maCompanyService.delete(maCompany);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maCompany.getId());
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
			MaCompanyImpl maCompany = new MaCompanyImpl();
			maCompany.setId(id);
			maCompany.setTag("d");
			maCompanyService.update(maCompany);
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
	public String selectTree() {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			MaCompanyImpl maCompany = new MaCompanyImpl();
			List<MaCompanyImpl> treeMaCompany = (List<MaCompanyImpl>) maCompanyService.selectTree(maCompany);
			tree = JSON.toJSONString(treeMaCompany);
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

