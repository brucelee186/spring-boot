package com.framework.action.operation;

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
import com.framework.bean.impl.OpCardImpl;
import com.framework.service.OpCardService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 卡牌管理
 * 作者:    Auto
 * 日期:    2019/8/15
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opCard")
public class OpCardController extends BaseController{

	@Autowired
	private OpCardService opCardService;

	@Autowired
	public OpCardService getOpCardService() {
		return opCardService;
	}

	@Autowired
	public void setOpCardService(OpCardService opCardService) {
		this.opCardService = opCardService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opCard");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpCardImpl> page;
			page = opCardService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpCardImpl> list = page.getList();
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
	 * @param opCard
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
			Long idOpCard = 0L;
			OpCardImpl opCard = new OpCardImpl();
			if(null != idMaCompany) {
			    opCard.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpCard = Long.valueOf(idString);
			}

			opCard.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opCard.setIdOpDevice(idOpDevice);
			String idOpRoomOperation = requestJson.getJSONObject("idOpRoomOperation").getString("value");
			if(!UtValid.blank(idOpRoomOperation)) {
				resultJson.put("msg", "操作室不可为空!");
				return resultJson.toString();
			}
			opCard.setIdOpRoomOperation(idOpRoomOperation);
			Long idOpRoomElectric = requestJson.getJSONObject("idOpRoomElectric").getLong("value");
			if(!UtValid.blank(idOpRoomElectric)) {
				resultJson.put("msg", "主电室不可为空!");
				return resultJson.toString();
			}
			opCard.setIdOpRoomElectric(idOpRoomElectric);

			if ("i".equals(editState)) {
				opCard.setCreateUser(userId);
				opCard.setModifiedUser(userId);
				this.setCommonField(opCard);
				opCardService.insert(opCard);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opCard.setId(id);
				opCard.setModifiedUser(userId);
				this.setCommonField(opCard);
				opCardService.update(opCard);
			} else if ("d".equals(editState)) {
				opCardService.delete(opCard);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opCard.getId());
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
			OpCardImpl opCard = new OpCardImpl();
			opCard.setId(id);
			opCard.setTag("d");
			opCard.setEditState("u");
			opCard.setModifiedUser(userId);
			this.setCommonField(opCard);
			opCardService.update(opCard);
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
			OpCardImpl opCard = new OpCardImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opCard.setIdMaCompany(idMaCompany);
			List<OpCardImpl> treeOpCard = (List<OpCardImpl>) opCardService.selectTree(opCard);
			tree = JSON.toJSONString(treeOpCard);
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

