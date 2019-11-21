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
import com.framework.bean.impl.SyStatusDetailImpl;
import com.framework.service.SyStatusDetailService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 系统状态明细
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/system/syStatusDetail")
public class SyStatusDetailController extends BaseController{

	@Autowired
	private SyStatusDetailService syStatusDetailService;

	@Autowired
	public SyStatusDetailService getSyStatusDetailService() {
		return syStatusDetailService;
	}

	@Autowired
	public void setSyStatusDetailService(SyStatusDetailService syStatusDetailService) {
		this.syStatusDetailService = syStatusDetailService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "syStatusDetail");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<SyStatusDetailImpl> page;
			if(null == orderBy) {
				orderBy = "codeSyStatus, orderIndex";
			}
			page = syStatusDetailService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<SyStatusDetailImpl> list = page.getList();
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
	 * @param syStatusDetail
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
			Long idSyStatusDetail = 0L;
			SyStatusDetailImpl syStatusDetail = new SyStatusDetailImpl();
			if(null != idMaCompany) {
			    syStatusDetail.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idSyStatusDetail = Long.valueOf(idString);
			}

			syStatusDetail.setEditState(editState);
			Long idSyStatus = requestJson.getJSONObject("idSyStatus").getLong("value");
			if(!UtValid.blank(idSyStatus)) {
				resultJson.put("msg", "父科目不可为空!");
				return resultJson.toString();
			}
			syStatusDetail.setIdSyStatus(idSyStatus);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "科目名称不可为空!");
				return resultJson.toString();
			}
			syStatusDetail.setName(name);
			String value = requestJson.getJSONObject("value").getString("value");
			syStatusDetail.setValue(value);
			Integer orderIndex = requestJson.getJSONObject("orderIndex").getInteger("value");
			syStatusDetail.setOrderIndex(orderIndex);
			String remark = requestJson.getJSONObject("remark").getString("value");
			syStatusDetail.setRemark(remark);

			if ("i".equals(editState)) {
				syStatusDetail.setCreateUser(userId);
				syStatusDetail.setModifiedUser(userId);
				this.setCommonField(syStatusDetail);
				syStatusDetailService.insert(syStatusDetail);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				syStatusDetail.setId(id);
				syStatusDetail.setModifiedUser(userId);
				this.setCommonField(syStatusDetail);
				syStatusDetailService.update(syStatusDetail);
			} else if ("d".equals(editState)) {
				syStatusDetailService.delete(syStatusDetail);
			}
			resultJson.put("code", 200);
			resultJson.put("id", syStatusDetail.getId());
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
			SyStatusDetailImpl syStatusDetail = new SyStatusDetailImpl();
			syStatusDetail.setId(id);
			syStatusDetail.setTag("d");
			syStatusDetail.setEditState("u");
			syStatusDetail.setModifiedUser(userId);
			this.setCommonField(syStatusDetail);
			syStatusDetailService.update(syStatusDetail);
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
			SyStatusDetailImpl syStatusDetail = new SyStatusDetailImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			syStatusDetail.setIdMaCompany(idMaCompany);
			List<SyStatusDetailImpl> treeSyStatusDetail = (List<SyStatusDetailImpl>) syStatusDetailService.selectTree(syStatusDetail);
			tree = JSON.toJSONString(treeSyStatusDetail);
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
	 * 查询树型结构
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectTreeValue", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectTreeValue(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			SyStatusDetailImpl syStatusDetail = new SyStatusDetailImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			String codeSyStatus = requestJson.getJSONObject("userdata").getJSONObject("codeSyStatus").getString("value");
			syStatusDetail.setIdMaCompany(idMaCompany);
			syStatusDetail.setCodeSyStatus(codeSyStatus);
			List<SyStatusDetailImpl> treeSyStatusDetail = (List<SyStatusDetailImpl>) syStatusDetailService.selectTreeValue(syStatusDetail);
			tree = JSON.toJSONString(treeSyStatusDetail);
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

