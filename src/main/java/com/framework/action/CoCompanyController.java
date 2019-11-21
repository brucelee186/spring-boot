package com.framework.action;


import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.impl.MaCompanyImpl;
import com.framework.exception.CoException;
import com.framework.service.MaCompanyService;
import com.framework.util.CoUtil;
import com.framework.util.PageUtil;
import com.github.pagehelper.PageInfo;


@Controller
@CrossOrigin
@RequestMapping(value = "/m_company")
public class CoCompanyController {
	
	
	@Resource
	private MaCompanyService maCompanyService;

	@ResponseBody
	@RequestMapping(value = "/getX5Page", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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
			resultJson.put("page", listAry);
			resultJson.put("totalCount", page.getTotal());
			resultJson.put("pagesQty", page.getPages());
			resultJson.put("isHasPreviousPage", page.isHasPreviousPage());
			resultJson.put("isHasNextPage", page.isHasNextPage());
			resultJson.put("code", 200);
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
		}
		return PageUtil.toX5JSONObject(resultJson).toString();
	}

	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String add(@RequestBody String requestParams) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String idLogin = requestJson.getJSONObject("idLogin").getString("value");
			String name = requestJson.getJSONObject("name").getString("value");
			String tel = requestJson.getJSONObject("tel").getString("value");
			String fax = requestJson.getJSONObject("fax").getString("value");
			String mail = requestJson.getJSONObject("mail").getString("value");
			String webUrl = requestJson.getJSONObject("webUrl").getString("value");
			String description = requestJson.getJSONObject("description").getString("value");
			
			MaCompanyImpl maCompany = new MaCompanyImpl();
			
			String uuid = UUID.randomUUID().toString();
			// 激活状态
			maCompany.setTag("n");
			maCompany.setUid(uuid);
			//maCompany.setIdLogin(idLogin);
			maCompany.setName(name);
			maCompany.setTel(tel);
			maCompany.setFax(fax);
			maCompany.setMail(mail);
			maCompany.setWebUrl(webUrl);
			maCompany.setDescription(description);
			maCompanyService.insert(maCompany);
			resultJson.put("code", 200);
			resultJson.put("id", maCompany.getId());

		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String update(@RequestBody String requestParams) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
			Long id = Long.valueOf(idString);
			MaCompanyImpl maCompany = new MaCompanyImpl();
			maCompany.setId(id);
			maCompany = maCompanyService.get(maCompany);
			if (maCompany.equals(null)) {
				resultJson.put("code", 500);
				resultJson.put("msg", "entity does not exist");
				return resultJson.toString();
			} else {
				maCompany = new MaCompanyImpl();
				String idLogin = requestJson.getJSONObject("idLogin").getString("value");
				String name = requestJson.getJSONObject("name").getString("value");
				String tel = requestJson.getJSONObject("tel").getString("value");
				String fax = requestJson.getJSONObject("fax").getString("value");
				String mail = requestJson.getJSONObject("mail").getString("value");
				String webUrl = requestJson.getJSONObject("webUrl").getString("value");
				String description = requestJson.getJSONObject("description").getString("value");
				
				
				String uuid = UUID.randomUUID().toString();
				maCompany.setId(id);
				//maCompany.setIdLogic(uuid);
				//maCompany.setIdLogin(idLogin);
				maCompany.setName(name);
				maCompany.setTel(tel);
				maCompany.setFax(fax);
				maCompany.setMail(mail);
				maCompany.setWebUrl(webUrl);
				maCompany.setDescription(description);
				maCompanyService.update(maCompany);
				resultJson.put("code", 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 501);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
			Long id = Long.valueOf(idString);
			MaCompanyImpl maCompany = new MaCompanyImpl();
			maCompany.setId(id);
			maCompany = maCompanyService.get(maCompany);
			if (maCompany.equals(null)) {
				resultJson.put("code", 500);
				resultJson.put("msg", "entity does not exist");
				return resultJson.toString();
			} else {
				maCompany = new MaCompanyImpl();
				String idLogin = requestJson.getJSONObject("idLogin").getString("value");
				String name = requestJson.getJSONObject("name").getString("value");
				String tel = requestJson.getJSONObject("tel").getString("value");
				String fax = requestJson.getJSONObject("fax").getString("value");
				String mail = requestJson.getJSONObject("mail").getString("value");
				String webUrl = requestJson.getJSONObject("webUrl").getString("value");
				String description = requestJson.getJSONObject("description").getString("value");
				
				
				String uuid = UUID.randomUUID().toString();
				maCompany.setId(id);
				//maCompany.setIdLogic(uuid);
				//maCompany.setIdLogin(idLogin);
				maCompany.setName(name);
				maCompany.setTel(tel);
				maCompany.setFax(fax);
				maCompany.setMail(mail);
				maCompany.setWebUrl(webUrl);
				maCompany.setDescription(description);
				maCompanyService.update(maCompany);
				resultJson.put("code", 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 501);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(@RequestBody String requestParams) {
		
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		
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

}
