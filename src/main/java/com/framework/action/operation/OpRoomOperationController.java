package com.framework.action.operation;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpRoomOperationImpl;
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.OpDeviceService;
import com.framework.service.OpRoomOperationService;
import com.framework.service.OpWorkOrderService;
import com.framework.service.SyLogService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 操作室管理
 * 作者:    Auto
 * 日期:    2019/8/15
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opRoomOperation")
public class OpRoomOperationController extends BaseController{
	
	@Autowired
	private OpDeviceService opDeviceService;
	
	public OpDeviceService getOpDeviceService() {
		return opDeviceService;
	}

	public void setOpDeviceService(OpDeviceService opDeviceService) {
		this.opDeviceService = opDeviceService;
	}

	@Autowired
	private OpRoomOperationService opRoomOperationService;

	@Autowired
	public OpRoomOperationService getOpRoomOperationService() {
		return opRoomOperationService;
	}

	@Autowired
	public void setOpRoomOperationService(OpRoomOperationService opRoomOperationService) {
		this.opRoomOperationService = opRoomOperationService;
	}

	@Autowired
	private OpWorkOrderService opWorkOrderService;
	
	public OpWorkOrderService getOpWorkOrderService() {
		return opWorkOrderService;
	}

	public void setOpWorkOrderService(OpWorkOrderService opWorkOrderService) {
		this.opWorkOrderService = opWorkOrderService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opRoomOperation");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpRoomOperationImpl> page;
			page = opRoomOperationService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpRoomOperationImpl> list = page.getList();
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
	 * @param opRoomOperation
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
			Long idOpRoomOperation = 0L;
			OpRoomOperationImpl opRoomOperation = new OpRoomOperationImpl();
			if(null != idMaCompany) {
			    opRoomOperation.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpRoomOperation = Long.valueOf(idString);
			}

			opRoomOperation.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "操作室名称不可为空!");
				return resultJson.toString();
			}
			opRoomOperation.setName(name);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			if(!UtValid.blank(idMaUser)) {
				resultJson.put("msg", "操作人不可为空!");
				return resultJson.toString();
			}
			opRoomOperation.setIdMaUser(idMaUser);
			String code = requestJson.getJSONObject("code").getString("value");
			opRoomOperation.setCode(code);

			if ("i".equals(editState)) {
				opRoomOperation.setCreateUser(userId);
				opRoomOperation.setModifiedUser(userId);
				this.setCommonField(opRoomOperation);
				opRoomOperationService.insert(opRoomOperation);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opRoomOperation.setId(id);
				opRoomOperation.setModifiedUser(userId);
				this.setCommonField(opRoomOperation);
				opRoomOperationService.update(opRoomOperation);
			} else if ("d".equals(editState)) {
				opRoomOperationService.delete(opRoomOperation);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opRoomOperation.getId());
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
			OpRoomOperationImpl opRoomOperation = new OpRoomOperationImpl();
			opRoomOperation.setId(id);
			opRoomOperation.setTag("d");
			opRoomOperation.setEditState("u");
			opRoomOperation.setModifiedUser(userId);
			this.setCommonField(opRoomOperation);
			opRoomOperationService.update(opRoomOperation);
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
			OpRoomOperationImpl opRoomOperation = new OpRoomOperationImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opRoomOperation.setIdMaCompany(idMaCompany);
			List<OpRoomOperationImpl> treeOpRoomOperation = (List<OpRoomOperationImpl>) opRoomOperationService.selectTree(opRoomOperation);
			tree = JSON.toJSONString(treeOpRoomOperation);
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
	 * 发牌（操作牌）
	 */
	@ResponseBody
	@RequestMapping(value="/grantCardsForOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public String grantCardsForOrder(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		//String uidMaUserOpRoomOperation = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			String status = requestJson.getString("status");
			if("sd".equals(status)) {
				opRoomOperationService.updateGrantCardsForOrder(requestJson);
			}else {
				opRoomOperationService.updateReturntCardsForOrder(requestJson);
			}
			
			resultJson.put("msg", "发牌成功");
			resultJson.put("code", 200);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			//更新设备(操作牌发放数量)
			JSONArray opDeviceArr = requestJson.getJSONArray("opDeviceArr");
			
			// 发牌:sd, 还牌:rd, re:驳回(只在操作发牌时,还牌不可拒绝)
			String statusOpRoomOperation = requestJson.getString("status");
			String userId = requestJson.getString("userId");
			opWorkOrder.setUserId(userId);
			opWorkOrder.setId(idOpWorkOrder);
			opWorkOrder.setJsonGroup(opDeviceArr);
			opWorkOrder.setStatusOpRoomOperation(statusOpRoomOperation);
			
			// 操作室发牌: osd 
			opWorkOrder.setStatus("osd");
			opWorkOrder.setModifiedDate(new Date());
			opWorkOrder.setModifiedUser(userId);
			opWorkOrder.setUidMaUserOpRoomOperation(userId);
			super.syLogService.insert(opWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 还牌（操作牌）
	 * 手机app点击还牌，执行一次性将工单所有的牌全部还完
	 */
	@ResponseBody
	@RequestMapping(value="/appReturnBackOpDeviceInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String appReturnBackOpDeviceInfo(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		//String uidMaUserOpRoomOperation = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			opRoomOperationService.appReturnBackOpDeviceInfo(requestJson);
			resultJson.put("msg", "还牌成功");
			resultJson.put("status", "pd");
			resultJson.put("code", 200);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			
			// 发牌:sd, 还牌:rd, re:驳回(只在操作发牌时,还牌不可拒绝)
			String statusOpRoomOperation = "rd";
			String userId = requestJson.getString("userId");
			opWorkOrder.setUserId(userId);
			opWorkOrder.setId(idOpWorkOrder);
			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
			opDeviceImpl.setMidOpWorkOrder(idOpWorkOrder);
			List<OpDeviceImpl> getOpDevices = (List<OpDeviceImpl>)opDeviceService.getAllDeviceLog(opDeviceImpl);
			opWorkOrder.setJsonGroup(JSONArray.parseArray(JSON.toJSONString(getOpDevices)));
			opWorkOrder.setStatusOpRoomOperation(statusOpRoomOperation);
			
			// 操作室发牌: osd 
			opWorkOrder.setStatus("osd");
			opWorkOrder.setModifiedDate(new Date());
			opWorkOrder.setModifiedUser(userId);
			opWorkOrder.setUidMaUserOpRoomOperation(userId);
			super.syLogService.insert(opWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 	查询还牌序列
	 */
	@ResponseBody
	@RequestMapping(value="/getReturnCardOrders", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getOrdersPowersSend(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpDevice = requestJson.getJSONObject("userdata").getJSONObject("idOpDevice").getLong("value");
			opWorkOrder.setIdOpDevice(idOpDevice);
			Long idOpRoomOperation = requestJson.getLong("idOpRoomOperation");
			opWorkOrder.setIdOpRoomOperation(idOpRoomOperation);
			List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) opWorkOrderService.getReturnCardOrders(opWorkOrder);
			for (int i = 0; i < treeOpWorkOrder.size(); i++) {
				OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
				Date createDate = opWorkOrderImpl.getCreateDate();
				if(createDate != null) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String createString = simpleDateFormat.format(createDate);
			        opWorkOrderImpl.setWorkOrderDate(createString);
				}
			}
			resultJson.put("code", 200);
			resultJson.put("data", treeOpWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	//查询该工单是否被其他操作室还牌
	@ResponseBody
	@RequestMapping(value="/getTheOpWorkOrderToOtherReturn", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getTheOpWorkOrderToOtherReturn(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			opWorkOrder.setId(idOpWorkOrder);
			Long idOpRoomOperation = requestJson.getLong("idOpRoomOperation");
			opWorkOrder.setIdOpRoomOperation(idOpRoomOperation);
			List<OpWorkOrderImpl> list = (List<OpWorkOrderImpl>) opWorkOrderService.getTheOpWorkOrderToOtherReturn(opWorkOrder);
			resultJson.put("code", 200);
			resultJson.put("idOpWorkOrder",idOpWorkOrder);
			resultJson.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	//工单跨操作室发牌，部分牌发放完成，不可以拒绝发牌
	@ResponseBody
	@RequestMapping(value="/returnTheOpWorkOrderGrantCard", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String returnTheOpWorkOrderGrantCard(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			List<OpWorkOrderCardImpl> list = (List<OpWorkOrderCardImpl>) opWorkOrderService.returnTheOpWorkOrderGrantCard(opWorkOrderCard);
			resultJson.put("code", 200);
			resultJson.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

