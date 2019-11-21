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
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.OpDeviceService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 设备管理
 * 作者:    Auto
 * 日期:    2019/8/17
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opDevice")
public class OpDeviceController extends BaseController{

	@Autowired
	private OpDeviceService opDeviceService;

	@Autowired
	public OpDeviceService getOpDeviceService() {
		return opDeviceService;
	}

	@Autowired
	public void setOpDeviceService(OpDeviceService opDeviceService) {
		this.opDeviceService = opDeviceService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opDevice");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpDeviceImpl> page;
			page = opDeviceService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpDeviceImpl> list = page.getList();
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
	 * 查询电箱下的开关
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchCabinetDevice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String searchCabinetDevice(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idOpDeviceCabinet = requestJson.getLong("idOpDeviceCabinet");
			OpDeviceImpl opDevice = new OpDeviceImpl();
			opDevice.setIdOpDeviceCabinet(idOpDeviceCabinet);
			List<OpDeviceImpl> listOpDevice = opDeviceService.select(opDevice);
			resultJson.put("code", 200);
			resultJson.put("data", listOpDevice);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 更新电箱下的开关状态(关锁后触发)
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCabinetDevice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String updateCabinetDevice(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idOpDeviceCabinet = requestJson.getLong("idOpDeviceCabinet");
			String strOn = requestJson.getString("on");
			String strOf = requestJson.getString("of");
			OpDeviceImpl opDevice = new OpDeviceImpl();
			opDevice.setStrOn(strOn);
			opDevice.setStrOf(strOf);
			opDevice.setIdOpDeviceCabinet(idOpDeviceCabinet);
			opDeviceService.updateCabinetDevice(opDevice);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 编辑
	 * @param opDevice
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
			Long idOpDevice = 0L;
			OpDeviceImpl opDevice = new OpDeviceImpl();
			if(null != idMaCompany) {
			    opDevice.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpDevice = Long.valueOf(idString);
			}

			opDevice.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "设备名称不可为空!");
				return resultJson.toString();
			}
			opDevice.setName(name);
			String code = requestJson.getJSONObject("code").getString("value");
			opDevice.setCode(code);
			Long idOpRoomOperation = requestJson.getJSONObject("idOpRoomOperation").getLong("value");
			if(!UtValid.blank(idOpRoomOperation)) {
				resultJson.put("msg", "操作室不可为空!");
				return resultJson.toString();
			}
			opDevice.setIdOpRoomOperation(idOpRoomOperation);
			Long idOpRoomElectric = requestJson.getJSONObject("idOpRoomElectric").getLong("value");
			if(!UtValid.blank(idOpRoomElectric)) {
				resultJson.put("msg", "主电室不可为空!");
				return resultJson.toString();
			}
			opDevice.setIdOpRoomElectric(idOpRoomElectric);
			Long idOpDeviceCabinet = requestJson.getJSONObject("idOpDeviceCabinet").getLong("value");
			if(!UtValid.blank(idOpDeviceCabinet)) {
				resultJson.put("msg", "开关柜不可为空!");
				return resultJson.toString();
			}
			opDevice.setIdOpDeviceCabinet(idOpDeviceCabinet);
			String status = requestJson.getJSONObject("status").getString("value");
			if(!UtValid.blank(status)) {
				resultJson.put("msg", "电力状态不可为空!");
				return resultJson.toString();
			}
			opDevice.setStatus(status);
			
			if ("i".equals(editState)) {
				opDevice.setCreateUser(userId);
				opDevice.setModifiedUser(userId);
				this.setCommonField(opDevice);
				opDeviceService.insert(opDevice);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opDevice.setId(id);
				opDevice.setModifiedUser(userId);
				this.setCommonField(opDevice);
				opDeviceService.update(opDevice);
			} else if ("d".equals(editState)) {
				opDeviceService.delete(opDevice);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opDevice.getId());
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
			OpDeviceImpl opDevice = new OpDeviceImpl();
			opDevice.setId(id);
			opDevice.setTag("d");
			opDevice.setEditState("u");
			opDevice.setModifiedUser(userId);
			this.setCommonField(opDevice);
			opDeviceService.update(opDevice);
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
			OpDeviceImpl opDevice = new OpDeviceImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opDevice.setIdMaCompany(idMaCompany);
			List<OpDeviceImpl> treeOpDevice = (List<OpDeviceImpl>) opDeviceService.selectTree(opDevice);
			tree = JSON.toJSONString(treeOpDevice);
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
	 * 根据操作室查询设备
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectDevice", method = { RequestMethod.GET, RequestMethod.POST })
	public String select(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceImpl opDevice = new OpDeviceImpl();
			Long idOpRoomOperation = requestJson.getJSONObject("userdata").getJSONObject("idOpRoomOperation").getLong("value");
			opDevice.setIdOpRoomOperation(idOpRoomOperation);
			opDevice.setSort("id");
			opDevice.setOrder("ASC");
			List<OpDeviceImpl> treeOpDevice = (List<OpDeviceImpl>) opDeviceService.select(opDevice);
			tree = JSON.toJSONString(treeOpDevice);
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
	 * 查询操作室的设备
	 */
	@ResponseBody
	@RequestMapping(value="/getCardListOfOperateOffice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getCardListOfOperateOffice(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceImpl opDevice = new OpDeviceImpl();
			Long idOpRoomOperation = requestJson.getJSONObject("userdata").getJSONObject("idOpRoomOperation").getLong("value");
			opDevice.setIdOpRoomOperation(idOpRoomOperation);
			opDevice.setSort("id");
			opDevice.setOrder("ASC");
			List<OpDeviceImpl> treeOpDevice = (List<OpDeviceImpl>) opDeviceService.select(opDevice);
			resultJson.put("code", 200);
			resultJson.put("data", treeOpDevice);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 开关停电
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/powerOff", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	private String powerOff(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			OpDeviceImpl opDevice = new OpDeviceImpl();
			opDevice.setId(id);
			opDevice.setTag("d");
			opDevice.setEditState("u");
			opDevice.setModifiedUser(userId);
			this.setCommonField(opDevice);
			//opDeviceService.powerOff(opDevice);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
}

