package com.framework.action.operation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.action.BaseController;
import com.framework.bean.impl.OpDeviceGroupImpl;
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpRoomElectricImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.OpDeviceMapper;
import com.framework.exception.CoException;
import com.framework.service.OpDeviceService;
import com.framework.service.OpRoomElectricService;
import com.framework.service.OpWorkOrderService;
import com.framework.util.CoUtil;
import com.framework.util.PageUtil;
import com.framework.util.UtValid;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.And;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 电器室管理
 * 作者:    Auto
 * 日期:    2019/8/15
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opRoomElectric")
public class OpRoomElectricController extends BaseController{

	@Autowired
	private OpRoomElectricService opRoomElectricService;

	@Autowired
	public OpRoomElectricService getOpRoomElectricService() {
		return opRoomElectricService;
	}

	@Autowired
	public void setOpRoomElectricService(OpRoomElectricService opRoomElectricService) {
		this.opRoomElectricService = opRoomElectricService;
	}

	@Autowired
	private OpDeviceService opDeviceService;
	
	public OpDeviceService getOpDeviceService() {
		return opDeviceService;
	}

	public void setOpDeviceService(OpDeviceService opDeviceService) {
		this.opDeviceService = opDeviceService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opRoomElectric");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpRoomElectricImpl> page;
			page = opRoomElectricService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpRoomElectricImpl> list = page.getList();
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
	 * @param opRoomElectric
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
			Long idOpRoomElectric = 0L;
			OpRoomElectricImpl opRoomElectric = new OpRoomElectricImpl();
			if(null != idMaCompany) {
			    opRoomElectric.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpRoomElectric = Long.valueOf(idString);
			}

			opRoomElectric.setEditState(editState);
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "电器室名称不可为空!");
				return resultJson.toString();
			}
			opRoomElectric.setName(name);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			if(!UtValid.blank(idMaUser)) {
				resultJson.put("msg", "操作人不可为空!");
				return resultJson.toString();
			}
			opRoomElectric.setIdMaUser(idMaUser);
			String code = requestJson.getJSONObject("code").getString("value");
			opRoomElectric.setCode(code);

			if ("i".equals(editState)) {
				opRoomElectric.setCreateUser(userId);
				opRoomElectric.setModifiedUser(userId);
				this.setCommonField(opRoomElectric);
				opRoomElectricService.insert(opRoomElectric);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opRoomElectric.setId(id);
				opRoomElectric.setModifiedUser(userId);
				this.setCommonField(opRoomElectric);
				opRoomElectricService.update(opRoomElectric);
			} else if ("d".equals(editState)) {
				opRoomElectricService.delete(opRoomElectric);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opRoomElectric.getId());
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
			OpRoomElectricImpl opRoomElectric = new OpRoomElectricImpl();
			opRoomElectric.setId(id);
			opRoomElectric.setTag("d");
			opRoomElectric.setEditState("u");
			opRoomElectric.setModifiedUser(userId);
			this.setCommonField(opRoomElectric);
			opRoomElectricService.update(opRoomElectric);
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
			OpRoomElectricImpl opRoomElectric = new OpRoomElectricImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opRoomElectric.setIdMaCompany(idMaCompany);
			List<OpRoomElectricImpl> treeOpRoomElectric = (List<OpRoomElectricImpl>) opRoomElectricService.selectTree(opRoomElectric);
			tree = JSON.toJSONString(treeOpRoomElectric);
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
	 * 查询主电室的设备
	 */
	@ResponseBody
	@RequestMapping(value="/getCardListOfPowerOffice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getCardListOfPowerOffice(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpDeviceImpl opDevice = new OpDeviceImpl();
			Long idOpRoomElectric = requestJson.getJSONObject("userdata").getJSONObject("idOpRoomElectric").getLong("value");
			opDevice.setIdOpRoomElectric(idOpRoomElectric);
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
	 * 根据条件查询工单（停电序列）
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOrdersPowersOffice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getOrdersPowersOffice(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpDevice = requestJson.getJSONObject("userdata").getJSONObject("idOpDevice").getLong("value");
			//显示详情
			String desc = requestJson.getJSONObject("userdata").getString("desc");
			opWorkOrder.setDesc(desc);
			opWorkOrder.setIdOpDevice(idOpDevice);
			Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
			opWorkOrder.setIdOpRoomElectric(idOpRoomElectric);
			List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) opWorkOrderService.getOrdersPowersOffice(opWorkOrder);
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
	
	/**
	 * 发牌（停电牌）
	 */
	@ResponseBody
	@RequestMapping(value="/grantPowerCardsForOrder", method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json; charset=utf-8")
	public String grantPowerCardsForOrder(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();//写入日志使用
			String statusOpRoomElectric = requestJson.getString("statusOpRoomElectric");
			opWorkOrder.setRequest(request);
			if("rw".contentEquals(statusOpRoomElectric)) {//驳回
				OpDeviceGroupImpl opDeviceGroup = opRoomElectricService.updateReturnPowerCardsForOrder(requestJson, request);
				opWorkOrder.setOpDeviceGroup(opDeviceGroup);
			}else {//还牌
				OpDeviceGroupImpl opDeviceGroup = opRoomElectricService.updateGrantPowerCardsForOrder(requestJson);
				opWorkOrder.setOpDeviceGroup(opDeviceGroup);
			}
			
			resultJson.put("msg", "发牌成功");
			resultJson.put("code", 200);
			
			
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			
			opWorkOrder.setId(idOpWorkOrder);
			opWorkOrder.setStatus("esd");
			// sd:发放工作牌, rd:发放停电牌, rw:拒绝
			if("rw".equals(statusOpRoomElectric)) {
				statusOpRoomElectric = "re";
			}else {
				//更新设备(操作牌发放数量)
				String opDeviceArr = " 开关设备: " + requestJson.getJSONArray("idDeviceArr").toJSONString();
				//电工数组
				String idElectricianArr = " 电工人员: " + requestJson.getJSONArray("idElectricianArr").toJSONString();
				opWorkOrder.setInformation("\n\r" + idElectricianArr + "\n\r" + opDeviceArr);
				// 主电室发牌: esd
			}
			
			opWorkOrder.setModifiedDate(new Date());
			String userId = requestJson.getString("userId");
			opWorkOrder.setModifiedUser(userId);
			opWorkOrder.setStatusOpRoomElectric(statusOpRoomElectric);
			super.syLogService.insert(opWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 *   送电序列
	 */
	@ResponseBody
	@RequestMapping(value="/getOrdersPowersSend", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getOrdersPowersSend(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpDevice = requestJson.getJSONObject("userdata").getJSONObject("idOpDevice").getLong("value");
			//显示详情
			String desc = requestJson.getJSONObject("userdata").getString("desc");
			opWorkOrder.setIdOpDevice(idOpDevice);
			opWorkOrder.setDesc(desc);
			Long idOpRoomElectric = requestJson.getLong("idOpRoomElectric");
			opWorkOrder.setIdOpRoomElectric(idOpRoomElectric);
			List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) opWorkOrderService.getOrdersPowersSend(opWorkOrder);
			resultJson.put("code", 200);
			resultJson.put("data", treeOpWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 发牌（送电牌）
	 
	@ResponseBody
	@RequestMapping(value="/grantPowerCardsForOrder", method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json; charset=utf-8")
	public String grantSendPowerCardsForOrder(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			opRoomElectricService.updateGrantSendPowerCardsForOrder(requestJson);
			resultJson.put("msg", "发牌成功");
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}*/
	/**
	 * 判断该设备下的工单是否完全发牌完成
	 */
	@ResponseBody
	@RequestMapping(value="/getTheDeviceToOpWorkOrder", method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json; charset=utf-8")
	public String grantSendPowerCardsForOrder(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long idOpdevice = requestJson.getLong("idOpdevice");
			String status = requestJson.getString("status");
			OpDeviceImpl opDeviceImpl = new OpDeviceImpl();
			opDeviceImpl.setId(idOpdevice);
			opDeviceImpl.setStatus(status);
			List<OpDeviceImpl> list = opDeviceService.getTheDeviceToOpWorkOrder(opDeviceImpl);
			resultJson.put("data", list);
			resultJson.put("idOpdevice", idOpdevice);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

