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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.framework.exception.CoException;
import com.framework.util.CoUtil;
import com.framework.util.UtValid;
import com.framework.util.PageUtil;
import com.framework.action.BaseController;
import com.framework.bean.impl.OpDeviceCardImpl;
import com.framework.service.OpDeviceCardService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 设备牌
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opDeviceCard")
public class OpDeviceCardController extends BaseController{

	@Autowired
	private OpDeviceCardService opDeviceCardService;

	@Autowired
	public OpDeviceCardService getOpDeviceCardService() {
		return opDeviceCardService;
	}

	@Autowired
	public void setOpDeviceCardService(OpDeviceCardService opDeviceCardService) {
		this.opDeviceCardService = opDeviceCardService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opDeviceCard");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpDeviceCardImpl> page;
			page = opDeviceCardService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpDeviceCardImpl> list = page.getList();
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
	 * @param opDeviceCard
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
			Long idOpDeviceCard = 0L;
			OpDeviceCardImpl opDeviceCard = new OpDeviceCardImpl();
			if(null != idMaCompany) {
			    opDeviceCard.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpDeviceCard = Long.valueOf(idString);
			}

			opDeviceCard.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpDevice(idOpDevice);
			Long idOpCardOperation = requestJson.getJSONObject("idOpCardOperation").getLong("value");
			if(!UtValid.blank(idOpCardOperation)) {
				resultJson.put("msg", "操作牌不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpCardOperation(idOpCardOperation);
			Long idMaUserOpCardOperation = requestJson.getJSONObject("idMaUserOpCardOperation").getLong("value");
			if(!UtValid.blank(idMaUserOpCardOperation)) {
				resultJson.put("msg", "操作牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdMaUserOpCardOperation(idMaUserOpCardOperation);
			Long idOpCardPower = requestJson.getJSONObject("idOpCardPower").getLong("value");
			if(!UtValid.blank(idOpCardPower)) {
				resultJson.put("msg", "停电牌不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpCardPower(idOpCardPower);
			Long idMaUserOpCardPower = requestJson.getJSONObject("idMaUserOpCardPower").getLong("value");
			if(!UtValid.blank(idMaUserOpCardPower)) {
				resultJson.put("msg", "停电牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdMaUserOpCardPower(idMaUserOpCardPower);
			Long idOpCardWork = requestJson.getJSONObject("idOpCardWork").getLong("value");
			if(!UtValid.blank(idOpCardWork)) {
				resultJson.put("msg", "工作牌不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpCardWork(idOpCardWork);
			Long idMaUserOpCardWork = requestJson.getJSONObject("idMaUserOpCardWork").getLong("value");
			if(!UtValid.blank(idMaUserOpCardWork)) {
				resultJson.put("msg", "工作牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdMaUserOpCardWork(idMaUserOpCardWork);
			Long idOpRoomOperation = requestJson.getJSONObject("idOpRoomOperation").getLong("value");
			if(!UtValid.blank(idOpRoomOperation)) {
				resultJson.put("msg", "操作室不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpRoomOperation(idOpRoomOperation);
			Long idOpRoomElectric = requestJson.getJSONObject("idOpRoomElectric").getLong("value");
			if(!UtValid.blank(idOpRoomElectric)) {
				resultJson.put("msg", "主电室不可为空!");
				return resultJson.toString();
			}
			opDeviceCard.setIdOpRoomElectric(idOpRoomElectric);

			if ("i".equals(editState)) {
				opDeviceCard.setCreateUser(userId);
				opDeviceCard.setModifiedUser(userId);
				this.setCommonField(opDeviceCard);
				opDeviceCardService.insert(opDeviceCard);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opDeviceCard.setId(id);
				opDeviceCard.setModifiedUser(userId);
				this.setCommonField(opDeviceCard);
				opDeviceCardService.update(opDeviceCard);
			} else if ("d".equals(editState)) {
				opDeviceCardService.delete(opDeviceCard);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opDeviceCard.getId());
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
			OpDeviceCardImpl opDeviceCard = new OpDeviceCardImpl();
			opDeviceCard.setId(id);
			opDeviceCard.setTag("d");
			opDeviceCard.setEditState("u");
			opDeviceCard.setModifiedUser(userId);
			this.setCommonField(opDeviceCard);
			opDeviceCardService.update(opDeviceCard);
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
			OpDeviceCardImpl opDeviceCard = new OpDeviceCardImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opDeviceCard.setIdMaCompany(idMaCompany);
			List<OpDeviceCardImpl> treeOpDeviceCard = (List<OpDeviceCardImpl>) opDeviceCardService.selectTree(opDeviceCard);
			tree = JSON.toJSONString(treeOpDeviceCard);
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

