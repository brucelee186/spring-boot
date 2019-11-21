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
import com.framework.bean.impl.OpWorkOrderCardImpl;
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.bean.impl.SyLogImpl;
import com.framework.service.OpWorkOrderCardService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 工单牌
 * 作者:    Auto
 * 日期:    2019/8/21
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opWorkOrderCard")
public class OpWorkOrderCardController extends BaseController{

	@Autowired
	private OpWorkOrderCardService opWorkOrderCardService;

	@Autowired
	public OpWorkOrderCardService getOpWorkOrderCardService() {
		return opWorkOrderCardService;
	}

	@Autowired
	public void setOpWorkOrderCardService(OpWorkOrderCardService opWorkOrderCardService) {
		this.opWorkOrderCardService = opWorkOrderCardService;
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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opWorkOrderCard");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpWorkOrderCardImpl> page;
			page = opWorkOrderCardService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpWorkOrderCardImpl> list = page.getList();
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
	 * @param opWorkOrderCard
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
			Long idOpWorkOrderCard = 0L;
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			if(null != idMaCompany) {
			    opWorkOrderCard.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpWorkOrderCard = Long.valueOf(idString);
			}

			opWorkOrderCard.setEditState(editState);
			Long idOpWorkOrder = requestJson.getJSONObject("idOpWorkOrder").getLong("value");
			if(!UtValid.blank(idOpWorkOrder)) {
				resultJson.put("msg", "工单不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpWorkOrder(idOpWorkOrder);
			Long idOpDevice = requestJson.getJSONObject("idOpDevice").getLong("value");
			if(!UtValid.blank(idOpDevice)) {
				resultJson.put("msg", "设备不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpDevice(idOpDevice);
			Long idOpCardOperation = requestJson.getJSONObject("idOpCardOperation").getLong("value");
			if(!UtValid.blank(idOpCardOperation)) {
				resultJson.put("msg", "操作牌不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpCardOperation(idOpCardOperation);
			Long idMaUserOpCardOperation = requestJson.getJSONObject("idMaUserOpCardOperation").getLong("value");
			if(!UtValid.blank(idMaUserOpCardOperation)) {
				resultJson.put("msg", "操作牌持有者不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdMaUserOpCardOperation(idMaUserOpCardOperation);
			Long idOpCardPower = requestJson.getJSONObject("idOpCardPower").getLong("value");
			if(!UtValid.blank(idOpCardPower)) {
				resultJson.put("msg", "停电牌不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpCardPower(idOpCardPower);
			Long idMaUserOpCardPower = requestJson.getJSONObject("idMaUserOpCardPower").getLong("value");
			if(!UtValid.blank(idMaUserOpCardPower)) {
				resultJson.put("msg", "停电牌持有者不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdMaUserOpCardPower(idMaUserOpCardPower);
			Long idOpCardWork = requestJson.getJSONObject("idOpCardWork").getLong("value");
			if(!UtValid.blank(idOpCardWork)) {
				resultJson.put("msg", "工作牌不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpCardWork(idOpCardWork);
			Long idMaUserOpCardWork = requestJson.getJSONObject("idMaUserOpCardWork").getLong("value");
			if(!UtValid.blank(idMaUserOpCardWork)) {
				resultJson.put("msg", "工作牌持有者不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdMaUserOpCardWork(idMaUserOpCardWork);
			Long idOpRoomOperation = requestJson.getJSONObject("idOpRoomOperation").getLong("value");
			if(!UtValid.blank(idOpRoomOperation)) {
				resultJson.put("msg", "操作室不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpRoomOperation(idOpRoomOperation);
			Long idOpRoomElectric = requestJson.getJSONObject("idOpRoomElectric").getLong("value");
			if(!UtValid.blank(idOpRoomElectric)) {
				resultJson.put("msg", "主电室不可为空!");
				return resultJson.toString();
			}
			opWorkOrderCard.setIdOpRoomElectric(idOpRoomElectric);

			if ("i".equals(editState)) {
				opWorkOrderCard.setCreateUser(userId);
				opWorkOrderCard.setModifiedUser(userId);
				this.setCommonField(opWorkOrderCard);
				opWorkOrderCardService.insert(opWorkOrderCard);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opWorkOrderCard.setId(id);
				opWorkOrderCard.setModifiedUser(userId);
				this.setCommonField(opWorkOrderCard);
				opWorkOrderCardService.update(opWorkOrderCard);
			} else if ("d".equals(editState)) {
				opWorkOrderCardService.delete(opWorkOrderCard);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opWorkOrderCard.getId());
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
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			opWorkOrderCard.setId(id);
			opWorkOrderCard.setTag("d");
			opWorkOrderCard.setEditState("u");
			opWorkOrderCard.setModifiedUser(userId);
			this.setCommonField(opWorkOrderCard);
			opWorkOrderCardService.update(opWorkOrderCard);
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
			OpWorkOrderCardImpl opWorkOrderCard = new OpWorkOrderCardImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opWorkOrderCard.setIdMaCompany(idMaCompany);
			List<OpWorkOrderCardImpl> treeOpWorkOrderCard = (List<OpWorkOrderCardImpl>) opWorkOrderCardService.selectTree(opWorkOrderCard);
			tree = JSON.toJSONString(treeOpWorkOrderCard);
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
	@RequestMapping(value = "/editWorkOderCardInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String editWorkOderCardInfo(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			opWorkOrderCardService.editWorkOderCardInfo(requestJson);
			resultJson.put("code", 200);
			
			// 记录日志
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			// 改变工单状态为：取牌
			Long idOpWorkOrder = requestJson.getJSONObject("userdata").getJSONObject("idOpWorkOrder").getLong("value");
			// 设备数组
			JSONArray opDeviceArr = requestJson.getJSONObject("opDeviceArr").getJSONArray("value");
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setId(idOpWorkOrder);
			opWorkOrder.setUidMaUser(userId);
			opWorkOrder.setIdMaCompany(idMaCompany);
			// ai(applying for card) 点击取牌后,组队信息不可修改,取牌信息可随意修改
			opWorkOrder.setStatus("ai");
			opWorkOrder.setJsonGroup(opDeviceArr);
			opWorkOrder.setModifiedUser(userId);
			this.setCommonField(opWorkOrder);
			syLogService.insert(opWorkOrder);

			//resultJson.put("id", opWorkOrder.getId());
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 查询工单及设备信息(发牌序列)
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWorkOderAndOpDeviceInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getWorkOderAndOpDeviceInfo(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String flgLong = requestJson.getString("idOpRoomOperation");//判断是主电室还是操作室
			if(flgLong == null) {
				resultJson = opWorkOrderCardService.getWorkOderAndOpDeviceToStopInfo(requestJson);
			}else {
				resultJson = opWorkOrderCardService.getWorkOderAndOpDeviceToOprationInfo(requestJson);
			}
			resultJson.put("code", 200);
			//resultJson.put("msg", 200);
			//resultJson.put("id", opWorkOrder.getId());
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

