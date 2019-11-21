package com.framework.action.operation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.dao.OpWorkOrderMapper;
import com.framework.service.OpWorkOrderEmployeeService;
import com.framework.service.OpWorkOrderService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 工单管理
 * 作者:    Auto
 * 日期:    2019/8/19
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opWorkOrder")
public class OpWorkOrderController extends BaseController{

	@Autowired
	private OpWorkOrderService opWorkOrderService;

	@Autowired
	public OpWorkOrderService getOpWorkOrderService() {
		return opWorkOrderService;
	}

	@Autowired
	public void setOpWorkOrderService(OpWorkOrderService opWorkOrderService) {
		this.opWorkOrderService = opWorkOrderService;
	}

	@Autowired
	private OpWorkOrderEmployeeService opWorkOrderEmployeeService;
	
	public OpWorkOrderEmployeeService getOpWorkOrderEmployeeService() {
		return opWorkOrderEmployeeService;
	}

	public void setOpWorkOrderEmployeeService(OpWorkOrderEmployeeService opWorkOrderEmployeeService) {
		this.opWorkOrderEmployeeService = opWorkOrderEmployeeService;
	}

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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opWorkOrder");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			}
			PageInfo<OpWorkOrderImpl> page;
			page = opWorkOrderService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpWorkOrderImpl> list = page.getList();
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
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				opWorkOrder.setId(id);
				opWorkOrder = opWorkOrderService.getOpreaterDetailInfo(opWorkOrder);
			}
			resultJson.put("code", 200);
			resultJson.put("opWorkOrder", opWorkOrder);
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
			Long idOpWorkOrder = 0L;
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			if(null != idMaCompany) {
			    opWorkOrder.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpWorkOrder = Long.valueOf(idString);
			}

			opWorkOrder.setCodeMaRole(codeMaRole);
			opWorkOrder.setEditState(editState);
			/*String nameOpProUnit = requestJson.getJSONObject("nameOpProUnit").getString("value");//机组
			//String name = requestJson.getJSONObject("name").getString("value");
			String name = nameOpProUnit;
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "工单名称不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setName(name);*///OO机组XX设备。进行OOOXX检修。对应停电工作。
			Long idOpProUnit = requestJson.getJSONObject("idOpProUnit").getLong("value");
			if(!UtValid.blank(idOpProUnit)) {
				resultJson.put("msg", "机组不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setIdOpProUnit(idOpProUnit);
			Long idChecker = requestJson.getJSONObject("idChecker").getLong("value");
			if(!UtValid.blank(idChecker)) {
				resultJson.put("msg", "点检员不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setIdChecker(idChecker);
			Long idLeader = requestJson.getJSONObject("idLeader").getLong("value");
			if(!UtValid.blank(idLeader)) {
				resultJson.put("msg", "带班人不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setIdLeader(idLeader);
			Double dateExpect = requestJson.getJSONObject("dateExpect").getDouble("value");
			if(!UtValid.blank(dateExpect)) {
				resultJson.put("msg", "计划时间不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setDateExpect(dateExpect);
			Double hoursExpect = requestJson.getJSONObject("hoursExpect").getDouble("value");
			if(!UtValid.blank(hoursExpect)) {
				resultJson.put("msg", "计划工时不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setHoursExpect(hoursExpect);
			String description = requestJson.getJSONObject("description").getString("value");
			if(!UtValid.blank(description)) {
				resultJson.put("msg", "工序描述不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setDescription(description);
			/*Double hoursActual = requestJson.getJSONObject("hoursActual").getDouble("value");
			opWorkOrder.setHoursActual(hoursActual);
			String description = requestJson.getJSONObject("description").getString("value");
			opWorkOrder.setDescription(description);
			String status = requestJson.getJSONObject("status").getString("value");
			if(!UtValid.blank(status)) {
				resultJson.put("msg", "状态不可为空!");
				return resultJson.toString();
			}
			opWorkOrder.setStatus(status);*/
			opWorkOrder.setRequest(request);
			if ("i".equals(editState)) {
				String nameOpProUnit = requestJson.getJSONObject("nameOpProUnit").getString("value");//机组
				String nameLeader = requestJson.getJSONObject("nameLeader").getString("value");//带班人
				String nameChecker = requestJson.getJSONObject("nameChecker").getString("value");//点检人
				String name = "【" + nameOpProUnit + "】" +  description;
				opWorkOrder.setName(name);//OO机组XX设备。进行OOOXX检修。对应停电工作。
				opWorkOrder.setNameOpProUnit(nameOpProUnit);
				opWorkOrder.setNameLeader(nameLeader);
				opWorkOrder.setNameChecker(nameChecker);
				opWorkOrder.setCreateUser(userId);
				opWorkOrder.setModifiedUser(userId);
				if("ma".equals(codeMaRole)) {
					Long idManager = requestJson.getJSONObject("idMaUser").getLong("value");//工长id
					String nameManager = requestJson.getJSONObject("idName").getString("value");//工长
					opWorkOrder.setIdManager(idManager);
					opWorkOrder.setNameManager(nameManager);
				}
				// or:工单创建完毕
				opWorkOrder.setStatus("or");
				this.setCommonField(opWorkOrder);
				opWorkOrderService.insert(opWorkOrder);
				// 插入工单创建完毕创建日志
				super.syLogService.insert(opWorkOrder);
				// 创建推送信息
				// super.syNotificationService.insert(opWorkOrder);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opWorkOrder.setId(id);
				opWorkOrder.setModifiedUser(userId);
				this.setCommonField(opWorkOrder);
				opWorkOrderService.update(opWorkOrder);
			} else if ("d".equals(editState)) {
				opWorkOrderService.delete(opWorkOrder);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opWorkOrder.getId());
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
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setId(id);
			opWorkOrder.setModifiedUser(userId);
			opWorkOrder.setRequest(request);
			this.setCommonField(opWorkOrder);
			opWorkOrderService.delete(opWorkOrder);
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
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opWorkOrder.setIdMaCompany(idMaCompany);
			List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) opWorkOrderService.selectTree(opWorkOrder);
			tree = JSON.toJSONString(treeOpWorkOrder);
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
	 * 保存工单数据信息
	 * @param opWorkOrder
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editWorkOderInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String editWorkOderInfo(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			//String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			//获取上次插入工单组队信息
			JSONArray iArr = requestJson.getJSONArray("iArr");
			//获取组队人员
			JSONArray uArr = requestJson.getJSONArray("uArr");
			//实例化
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			//公司Id
			if(null != idMaCompany) {
			    opWorkOrderEmployee.setIdMaCompany(idMaCompany);
			}
			//工单
			Long idOpWorkOrder = requestJson.getJSONObject("idOpWorkOrder").getLong("value");
			if(!UtValid.blank(idOpWorkOrder)) {
				resultJson.put("msg", "工单号不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
			//工时
			Double hours = requestJson.getJSONObject("hours").getDouble("value");
			if(!UtValid.blank(hours)) {
				resultJson.put("msg", "工时不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setHours(hours);
			opWorkOrderEmployee.setCreateUser(userId);
			opWorkOrderEmployee.setModifiedUser(userId);
			
			//更新工单状态
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setStatus("gi");
			String idString = requestJson.getJSONObject("idOpWorkOrder").getString("value");
			Long id = Long.valueOf(idString);
			opWorkOrder.setId(id);
			opWorkOrder.setModifiedUser(userId);
			opWorkOrder.setDateGrouped(new Date());
			opWorkOrder.setRequest(request);
			opWorkOrderEmployeeService.editOpWorkOrder(opWorkOrder, opWorkOrderEmployee, iArr, uArr);
			opWorkOrder.setJsonGroup(uArr);
			syLogService.insert(opWorkOrder);
			resultJson.put("code", 200);
			resultJson.put("id", opWorkOrder.getId());
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 *  更新工单表状态
	 */
	@ResponseBody
	@RequestMapping(value = "/editStatus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String editStatus(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String status = requestJson.getJSONObject("userdata").getJSONObject("status").getString("value");
			//更新工单状态
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			//查询当前工单状态
			String idString = requestJson.getJSONObject("userdata").getJSONObject("idOpWorkOrder").getString("value");
			Long id = Long.valueOf(idString);
			opWorkOrder.setId(id);
			OpWorkOrderImpl getOpWorkOrder = opWorkOrderService.get(opWorkOrder);;
			String sta = getOpWorkOrder.getStatus();
			Integer appCount = getOpWorkOrder.getCountOpCardApply();
			Integer sendCount = getOpWorkOrder.getCountOpCardSend();
			Integer powerCount = getOpWorkOrder.getCountOpDevicePower();
			opWorkOrder.setRequest(request);
			if(sta.equals("po") && (appCount != powerCount)) {
				resultJson.put("msg", "取牌失败!");
				resultJson.put("code", 500);
				return resultJson.toString();
			} else if(status.equals("pi")){
				opWorkOrder.setStatus(status);
				opWorkOrder.setModifiedUser(userId);
				opWorkOrder.setCountOpCardSend(0);
				opWorkOrder.setDateReturned(new Date());
				this.setCommonField(opWorkOrder);
				//opWorkOrderService.update(opWorkOrder);
				opWorkOrderService.updateOpWorkOrderStatus(opWorkOrder);
			}else{
				opWorkOrder.setStatus(status);
				if(status.equals("ws")) {
					
					opWorkOrder.setDatesStartup(new Date());
				}else {
					// 作业完成时间
					opWorkOrder.setDateOptioned(new Date());
				}
				opWorkOrder.setModifiedUser(userId);
				this.setCommonField(opWorkOrder);
				opWorkOrderService.update(opWorkOrder);
			}
			syLogService.insert(opWorkOrder);
			resultJson.put("status", status);
			resultJson.put("code", 200);
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	
	/**
	 * 根据条件查询工单 发牌序列
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOrdersOperateOffice", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String getOrdersOperateOffice(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			Long idOpDevice = requestJson.getJSONObject("userdata").getJSONObject("idOpDevice").getLong("value");
			//显示详情
			String desc = requestJson.getJSONObject("userdata").getString("desc");
			opWorkOrder.setDesc(desc);
			opWorkOrder.setIdOpDevice(idOpDevice);
			Long idOpRoomOperation = requestJson.getLong("idOpRoomOperation");
			opWorkOrder.setIdOpRoomOperation(idOpRoomOperation);
			List<OpWorkOrderImpl> treeOpWorkOrder = (List<OpWorkOrderImpl>) opWorkOrderService.getIdOpDeviceToOpWorkOrder(opWorkOrder);
			//王玉鑫操作室创建时间
			for(int i=0; i<treeOpWorkOrder.size();i++) {
				OpWorkOrderImpl opWorkOrderImpl = treeOpWorkOrder.get(i);
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
	 *	查询工单（根据权限）
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMaRoleToX5Page", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getMaRoleToX5Page(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Integer pageNo = requestJson.getInteger("pageNo");
			Integer pageSize = requestJson.getInteger("pageSize");
			String orderBy = requestJson.getString("orderBy");
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opWorkOrder");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			}
			if("ch".equals(codeMaRole)) {
				String idMaUser = requestJson.getJSONObject("userdata").getJSONObject("idMaUser").getString("value");
			    sqlWhere = "a.idChecker = '" + idMaUser +"'";
			}
			if("le".equals(codeMaRole)) {
				String idMaUser = requestJson.getJSONObject("userdata").getJSONObject("idMaUser").getString("value");
			    sqlWhere = "a.idLeader = '" + idMaUser +"'";
			}
			if("em".equals(codeMaRole)) {
				String idMaUser = requestJson.getJSONObject("userdata").getJSONObject("idMaUser").getString("value");
				sqlWhere = "e.idMaUser = '" + idMaUser +"'";
			}
			PageInfo<OpWorkOrderImpl> page;
			page = opWorkOrderService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpWorkOrderImpl> list = page.getList();
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
	 * 查询电箱对应的工单
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchWorkOrderCabinet", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String searchWorkOrderCabinet(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idOpDeviceCabinet = requestJson.getLong("idOpDeviceCabinet");
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setIdOpDeviceCabinet(idOpDeviceCabinet);
			List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderService.searchWorkOrderCabinet(opWorkOrder);
			resultJson.put("code", 200);
			resultJson.put("data", listOpWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}	
	
	/**
	 * 查询电工历史工单
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectHistoryOpWorkOrder", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String selectHistoryOpWorkOrder(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			//String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idMaCompany = requestJson.getLong("idMaCompany");
			Long idElectrician = requestJson.getLong("idElectrician");
			OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
			opDeviceElectricianCard.setIdMaCompany(idMaCompany);
			opDeviceElectricianCard.setIdElectrician(idElectrician);
			List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderService.selectHistoryOpWorkOrder(opDeviceElectricianCard);
			resultJson.put("code", 200);
			resultJson.put("data", listOpWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}	
	
	
	/**
	 * 查询
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOpreatePage", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getOpreatePage(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Integer pageNo = requestJson.getInteger("pageNo");
			Integer pageSize = requestJson.getInteger("pageSize");
			String orderBy = requestJson.getString("orderBy");
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opWorkOrder");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
			Long idMaCompany = requestJson.getLong("idMaCompany");
			Long idMaUser = requestJson.getLong("idMaUser");
			if("le".equals(codeMaRole) ) {
				opWorkOrderImpl.setIdLeader(idMaUser);
			}
			if("ch".equals(codeMaRole)){
				opWorkOrderImpl.setIdChecker(idMaUser);
			}
			if("ma".equals(codeMaRole)) {
				opWorkOrderImpl.setIdManager(idMaUser);
			}
			if("em".equals(codeMaRole)) {
				opWorkOrderImpl.setIdMaUser(idMaUser);
			}
			opWorkOrderImpl.setIdMaCompany(idMaCompany);
			//PageInfo<OpWorkOrderImpl> page = opWorkOrderService.getOpreatePage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpWorkOrderImpl> list = opWorkOrderService.getOpreatePage(opWorkOrderImpl);
			//List<OpWorkOrderImpl> list = page.getList();
			JSONArray listAry = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
			resultJson.put("code", 200);
			resultJson.put("page", listAry);
			/*resultJson.put("totalCount", page.getTotal());
			resultJson.put("pagesQty", page.getPages());
			resultJson.put("isHasPreviousPage", page.isHasPreviousPage());
			resultJson.put("isHasNextPage", page.isHasNextPage());*/
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return PageUtil.toX5JSONObject(resultJson).toString();
	}
	
	//操作人员历史工单
	@ResponseBody
	@RequestMapping(value="/getHistoryOpWorkOrder", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getHistoryOpWorkOrder(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			Long idMaCompany = requestJson.getLong("idMaCompany");
			OpWorkOrderImpl opWorkOrderImpl = new OpWorkOrderImpl();
			opWorkOrderImpl.setIdMaCompany(idMaCompany);
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getString("codeMaRole");
			Long idMaUser = requestJson.getLong("idMaUser");
			if("le".equals(codeMaRole) ) {
				opWorkOrderImpl.setIdLeader(idMaUser);
			}
			if("ch".equals(codeMaRole)){
				opWorkOrderImpl.setIdChecker(idMaUser);
			}
			if("ma".equals(codeMaRole)) {
				opWorkOrderImpl.setIdManager(idMaUser);
			}
			if("em".equals(codeMaRole)) {
				opWorkOrderImpl.setIdMaUser(idMaUser);
			}
			List<OpWorkOrderImpl> listOpWorkOrder = opWorkOrderService.getHistoryOpWorkOrder(opWorkOrderImpl);
			resultJson.put("code", 200);
			resultJson.put("data", listOpWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}	
}

