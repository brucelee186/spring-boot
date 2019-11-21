package com.framework.action.operation;

import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.util.CoUtil;
import com.framework.util.UtValid;
import com.framework.util.PageUtil;
import com.framework.action.BaseController;
import com.framework.bean.impl.OpDeviceCabinetImpl;
import com.framework.service.OpDeviceCabinetService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 设备开关柜
 * 作者:    Auto
 * 日期:    2019/10/10
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opDeviceCabinet")
public class OpDeviceCabinetController extends BaseController{

	@Autowired
	private OpDeviceCabinetService opDeviceCabinetService;

	/**
	 * 查询
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getX5Page", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getX5Page(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Integer pageNo = requestJson.getInteger("pageNo");
			Integer pageSize = requestJson.getInteger("pageSize");
			String orderBy = requestJson.getString("orderBy");
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opDeviceCabinet");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpDeviceCabinetImpl> page;
			page = opDeviceCabinetService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpDeviceCabinetImpl> list = page.getList();
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
	public String toEdit(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String idString = requestJson.getString("id");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			OpDeviceCabinetImpl opDeviceCabinet = new OpDeviceCabinetImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				opDeviceCabinet.setId(id);
				opDeviceCabinet.setRequest(request);
				opDeviceCabinet = opDeviceCabinetService.get(opDeviceCabinet);
			}
			resultJson.put("code", 200);
			resultJson.put("opDeviceCabinet", opDeviceCabinet);
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
	public String edit(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idOpDeviceCabinet = 0L;
			OpDeviceCabinetImpl opDeviceCabinet = new OpDeviceCabinetImpl();
			if(null != idMaCompany) {
			    opDeviceCabinet.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpDeviceCabinet = Long.valueOf(idString);
			}

			opDeviceCabinet.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "开关柜名称不可为空!");
				return resultJson.toString();
			}
			opDeviceCabinet.setName(name);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			if(!UtValid.blank(idMaUser)) {
				resultJson.put("msg", "管理员不可为空!");
				return resultJson.toString();
			}
			opDeviceCabinet.setIdMaUser(idMaUser);
			String code = requestJson.getJSONObject("code").getString("value");
			opDeviceCabinet.setCode(code);
//			Double longitude = requestJson.getJSONObject("longitude").getDouble("value");
//			opDeviceCabinet.setLongitude(longitude);
//			Double latitude = requestJson.getJSONObject("latitude").getDouble("value");
//			opDeviceCabinet.setLatitude(latitude);
			String location = requestJson.getJSONObject("location").getString("value");
			if(!UtValid.blank(location)) {
				resultJson.put("msg", "地址不可为空!");
				return resultJson.toString();
			}
			opDeviceCabinet.setLocation(location);
			String remark = requestJson.getJSONObject("remark").getString("value");
			opDeviceCabinet.setRemark(remark);
			opDeviceCabinet.setRequest(request);

			if ("i".equals(editState)) {
				opDeviceCabinet.setCreateUser(userId);
				opDeviceCabinet.setModifiedUser(userId);
				this.setCommonField(opDeviceCabinet);
				opDeviceCabinetService.insert(opDeviceCabinet);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opDeviceCabinet.setId(id);
				opDeviceCabinet.setModifiedUser(userId);
				this.setCommonField(opDeviceCabinet);
				opDeviceCabinetService.update(opDeviceCabinet);
			} else if ("d".equals(editState)) {
				opDeviceCabinetService.delete(opDeviceCabinet);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opDeviceCabinet.getId());
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
	public String delete(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			OpDeviceCabinetImpl opDeviceCabinet = new OpDeviceCabinetImpl();
			opDeviceCabinet.setId(id);
			opDeviceCabinet.setModifiedUser(userId);
			opDeviceCabinet.setRequest(request);
			this.setCommonField(opDeviceCabinet);
			opDeviceCabinetService.delete(opDeviceCabinet);
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
	public String selectTree(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceCabinetImpl opDeviceCabinet = new OpDeviceCabinetImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opDeviceCabinet.setIdMaCompany(idMaCompany);
			opDeviceCabinet.setRequest(request);
			List<OpDeviceCabinetImpl> treeOpDeviceCabinet = (List<OpDeviceCabinetImpl>) opDeviceCabinetService.selectTree(opDeviceCabinet);
			tree = JSON.toJSONString(treeOpDeviceCabinet);
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

