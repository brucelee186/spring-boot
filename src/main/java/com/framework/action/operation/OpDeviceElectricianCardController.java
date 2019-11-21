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
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.service.OpDeviceElectricianCardService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 设备电工牌
 * 作者:    Auto
 * 日期:    2019/9/6
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opDeviceElectricianCard")
public class OpDeviceElectricianCardController extends BaseController{

	@Autowired
	private OpDeviceElectricianCardService opDeviceElectricianCardService;

	/**
	 * 查询
	 * @param requestParams
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opDeviceElectricianCard");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpDeviceElectricianCardImpl> page;
			page = opDeviceElectricianCardService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpDeviceElectricianCardImpl> list = page.getList();
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
	public String toEdit(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String idString = requestJson.getString("id");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				opDeviceElectricianCard.setId(id);
				opDeviceElectricianCard = opDeviceElectricianCardService.get(opDeviceElectricianCard);
			}
			resultJson.put("code", 200);
			resultJson.put("opDeviceElectricianCard", opDeviceElectricianCard);
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
	public String edit(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			Long idOpDeviceElectricianCard = 0L;
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			if(null != idMaCompany) {
			    opDeviceElectricianCard.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpDeviceElectricianCard = Long.valueOf(idString);
			}

			opDeviceElectricianCard.setEditState(editState);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpDevice(idOpDevice);
			Long idOpDeviceGroup = requestJson.getJSONObject("idOpDeviceGroup").getLong("value");
			if(!UtValid.blank(idOpDeviceGroup)) {
				resultJson.put("msg", "设备电工组不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpDeviceGroup(idOpDeviceGroup);
			Long idChecker = requestJson.getJSONObject("idChecker").getLong("value");
			if(!UtValid.blank(idChecker)) {
				resultJson.put("msg", "派发人编号不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdChecker(idChecker);
			String nameChecker = requestJson.getJSONObject("nameChecker").getString("value");
			opDeviceElectricianCard.setNameChecker(nameChecker);
			String listIdElectrician = requestJson.getJSONObject("listIdElectrician").getString("value");
			if(!UtValid.blank(listIdElectrician)) {
				resultJson.put("msg", "互保工友编号列表不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setListIdElectrician(listIdElectrician);
			String listNameElectrician = requestJson.getJSONObject("listNameElectrician").getString("value");
			if(!UtValid.blank(listNameElectrician)) {
				resultJson.put("msg", "互保工友不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setListNameElectrician(listNameElectrician);
			String listElectrician = requestJson.getJSONObject("listElectrician").getString("value");
			if(!UtValid.blank(listElectrician)) {
				resultJson.put("msg", "互保工友列表不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setListElectrician(listElectrician);
			Long idOpCardOperation = requestJson.getJSONObject("idOpCardOperation").getLong("value");
			if(!UtValid.blank(idOpCardOperation)) {
				resultJson.put("msg", "操作牌不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpCardOperation(idOpCardOperation);
			Long idMaUserOpCardOperation = requestJson.getJSONObject("idMaUserOpCardOperation").getLong("value");
			if(!UtValid.blank(idMaUserOpCardOperation)) {
				resultJson.put("msg", "操作牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdMaUserOpCardOperation(idMaUserOpCardOperation);
			Long idOpCardPower = requestJson.getJSONObject("idOpCardPower").getLong("value");
			if(!UtValid.blank(idOpCardPower)) {
				resultJson.put("msg", "停电牌不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpCardPower(idOpCardPower);
			Long idMaUserOpCardPower = requestJson.getJSONObject("idMaUserOpCardPower").getLong("value");
			if(!UtValid.blank(idMaUserOpCardPower)) {
				resultJson.put("msg", "停电牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdMaUserOpCardPower(idMaUserOpCardPower);
			Long idOpCardWork = requestJson.getJSONObject("idOpCardWork").getLong("value");
			if(!UtValid.blank(idOpCardWork)) {
				resultJson.put("msg", "工作牌不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpCardWork(idOpCardWork);
			Long idMaUserOpCardWork = requestJson.getJSONObject("idMaUserOpCardWork").getLong("value");
			if(!UtValid.blank(idMaUserOpCardWork)) {
				resultJson.put("msg", "工作牌持有者不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdMaUserOpCardWork(idMaUserOpCardWork);
			Long idOpRoomOperation = requestJson.getJSONObject("idOpRoomOperation").getLong("value");
			if(!UtValid.blank(idOpRoomOperation)) {
				resultJson.put("msg", "操作室不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpRoomOperation(idOpRoomOperation);
			Long idOpRoomElectric = requestJson.getJSONObject("idOpRoomElectric").getLong("value");
			if(!UtValid.blank(idOpRoomElectric)) {
				resultJson.put("msg", "主电室不可为空!");
				return resultJson.toString();
			}
			opDeviceElectricianCard.setIdOpRoomElectric(idOpRoomElectric);

			if ("i".equals(editState)) {
				opDeviceElectricianCard.setCreateUser(userId);
				opDeviceElectricianCard.setModifiedUser(userId);
				this.setCommonField(opDeviceElectricianCard);
				opDeviceElectricianCardService.insert(opDeviceElectricianCard);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opDeviceElectricianCard.setId(id);
				opDeviceElectricianCard.setModifiedUser(userId);
				this.setCommonField(opDeviceElectricianCard);
				opDeviceElectricianCardService.update(opDeviceElectricianCard);
			} else if ("d".equals(editState)) {
				opDeviceElectricianCardService.delete(opDeviceElectricianCard);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opDeviceElectricianCard.getId());
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
	public String delete(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			opDeviceElectricianCard.setId(id);
			opDeviceElectricianCard.setModifiedUser(userId);
			this.setCommonField(opDeviceElectricianCard);
			opDeviceElectricianCardService.delete(opDeviceElectricianCard);
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
	public String selectTree(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opDeviceElectricianCard.setIdMaCompany(idMaCompany);
			List<OpDeviceElectricianCardImpl> treeOpDeviceElectricianCard = (List<OpDeviceElectricianCardImpl>) opDeviceElectricianCardService.selectTree(opDeviceElectricianCard);
			tree = JSON.toJSONString(treeOpDeviceElectricianCard);
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
	 * 电工登录手机端查询送电停电序列
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStopOrSendPower", method = { RequestMethod.GET, RequestMethod.POST })
	public String getStopOrSendPower(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			Long idElectrician = requestJson.getLong("idElectrician");
			Long idMaCompany = requestJson.getLong("idMaCompany");
			String statusElectrician = requestJson.getString("statusElectrician");
			opDeviceElectricianCard.setIdElectrician(idElectrician);
			opDeviceElectricianCard.setIdMaCompany(idMaCompany);
			opDeviceElectricianCard.setStatusElectrician(statusElectrician);
			List<OpDeviceElectricianCardImpl> treeOpDeviceElectricianCard = (List<OpDeviceElectricianCardImpl>) opDeviceElectricianCardService.getStopOrSendPower(opDeviceElectricianCard);
			resultJson.put("code", 200);
			resultJson.put("data", treeOpDeviceElectricianCard);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

