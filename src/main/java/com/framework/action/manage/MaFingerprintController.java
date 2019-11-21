package com.framework.action.manage;

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
import com.framework.bean.impl.MaFingerprintImpl;
import com.framework.service.MaFingerprintService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/10/8
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maFingerprint")
public class MaFingerprintController extends BaseController{

	@Autowired
	private MaFingerprintService maFingerprintService;

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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "maFingerprint");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<MaFingerprintImpl> page;
			page = maFingerprintService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaFingerprintImpl> list = page.getList();
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
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				maFingerprint.setId(id);
				maFingerprint.setRequest(request);
				maFingerprint = maFingerprintService.get(maFingerprint);
			}
			resultJson.put("code", 200);
			resultJson.put("maFingerprint", maFingerprint);
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
			Long idMaFingerprint = 0L;
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			if(null != idMaCompany) {
			    maFingerprint.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaFingerprint = Long.valueOf(idString);
			}

			maFingerprint.setEditState(editState);
			Integer index = requestJson.getJSONObject("index").getInteger("value");
			if(!UtValid.blank(index)) {
				resultJson.put("msg", "指纹索引不可为空!");
				return resultJson.toString();
			}
			MaFingerprintImpl maFingerprintValidate = new MaFingerprintImpl();
			maFingerprintValidate.setIndex(index);
			if("u".equals(editState)) {
				maFingerprintValidate.setIndex(null);
				maFingerprintValidate.setIndexUnique(index);
				maFingerprintValidate.setTagMapper("unique");
			}
			List<MaFingerprintImpl> listMaFingerprint = maFingerprintService.select(maFingerprintValidate);
			if(null != listMaFingerprint && listMaFingerprint.size() > 0) {
				resultJson.put("msg", "指纹索引已存在!");
				return resultJson.toString();
			}
			maFingerprint.setIndex(index);
			maFingerprint.setRequest(request);

			if ("i".equals(editState)) {
				maFingerprint.setCreateUser(userId);
				maFingerprint.setModifiedUser(userId);
				this.setCommonField(maFingerprint);
				maFingerprintService.insert(maFingerprint);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maFingerprint.setId(id);
				maFingerprint.setModifiedUser(userId);
				this.setCommonField(maFingerprint);
				maFingerprintService.update(maFingerprint);
			} else if ("d".equals(editState)) {
				maFingerprintService.delete(maFingerprint);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maFingerprint.getId());
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
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			maFingerprint.setId(id);
			maFingerprint.setModifiedUser(userId);
			maFingerprint.setRequest(request);
			this.setCommonField(maFingerprint);
			maFingerprintService.delete(maFingerprint);
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
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			maFingerprint.setIdMaCompany(idMaCompany);
			maFingerprint.setRequest(request);
			List<MaFingerprintImpl> treeMaFingerprint = (List<MaFingerprintImpl>) maFingerprintService.selectTree(maFingerprint);
			tree = JSON.toJSONString(treeMaFingerprint);
			resultJson.put("code", 200);
			resultJson.put("tree", tree);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	
	
	/**
	 * 上传指纹内容索引以及绑定用户编号
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUserFingerprintIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String getUserFingerprintIndex(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String json = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			String uidMaUser = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long idMaUser = requestJson.getLong("idMaUser");
			maFingerprint.setIdMaCompany(idMaCompany);
			maFingerprint.setIdMaUser(idMaUser);
			maFingerprint = maFingerprintService.getUserFingerprintIndex(maFingerprint);
			Integer index = maFingerprint.getIndex();
			if(null == index) {
				resultJson.put("code", 500);
				resultJson.put("msg", "系统维护中，请稍后再试。");
				return json;
			}
			json = JSON.toJSONString(maFingerprint);
			resultJson.put("code", 200);
			resultJson.put("tree", json);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 查询需要上传的用户指纹信息
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectFingerprintByCabinet", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectFingerprintByCabinet(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String json = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			//String uidMaUser = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			//Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long idOpDeviceCabinet = requestJson.getLong("idOpDeviceCabinet");
			//Long idMaUser = requestJson.getLong("idMaUser");
			//maFingerprint.setIdMaCompany(idMaCompany);
			//maFingerprint.setIdMaUser(idMaUser);
			maFingerprint.setIdOpDeviceCabinet(idOpDeviceCabinet);
			List<MaFingerprintImpl> listFingerprint = maFingerprintService.selectFingerprintByCabinet(maFingerprint);
			resultJson.put("code", 200);
			resultJson.put("data", listFingerprint);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 上传指纹内容索引以及绑定用户编号
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserFingerprint", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateUserFingerprint(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String json = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			String uidMaUser = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long idMaUser = requestJson.getLong("idMaUser");
			String code = requestJson.getString("code");
			//Integer index = requestJson.getInteger("index");
			maFingerprint.setIdMaCompany(idMaCompany);
			maFingerprint.setCreateUser(uidMaUser);
			maFingerprint.setIdMaUser(idMaUser);
			maFingerprint.setCode(code);
			//maFingerprint.setIndex(index);
			maFingerprint.setRequest(request);
			boolean res = maFingerprintService.updateUserFingerprintV2(maFingerprint);
			maFingerprint.setCode("");
			if(!res) {
				resultJson.put("code", 500);
				resultJson.put("msg", "指纹分配失败,请联系系统管理员.");
				return json;
			}
			resultJson.put("code", 200);
			resultJson.put("data", "");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 更新指纹信息
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadUserFingerprint", method = { RequestMethod.GET, RequestMethod.POST })
	public String uploadUserFingerprint(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String json = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaFingerprintImpl maFingerprint = new MaFingerprintImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			maFingerprint.setIdMaCompany(idMaCompany);
			maFingerprint.setTagMapper("uploadUserFingerprint");
			List<MaFingerprintImpl> listMaFingerprint= maFingerprintService.select(maFingerprint);
		
			json = JSON.toJSONString(listMaFingerprint);
			resultJson.put("code", 200);
			resultJson.put("tree", json);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return json;
	}

}

