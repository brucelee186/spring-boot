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
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.service.OpWorkOrderEmployeeService;
import com.framework.service.OpWorkOrderService;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 工单组队员工
 * 作者:    Auto
 * 日期:    2019/8/21
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/operation/opWorkOrderEmployee")
public class OpWorkOrderEmployeeController extends BaseController{

	@Autowired
	private OpWorkOrderEmployeeService opWorkOrderEmployeeService;

	@Autowired
	public OpWorkOrderEmployeeService getOpWorkOrderEmployeeService() {
		return opWorkOrderEmployeeService;
	}

	@Autowired
	public void setOpWorkOrderEmployeeService(OpWorkOrderEmployeeService opWorkOrderEmployeeService) {
		this.opWorkOrderEmployeeService = opWorkOrderEmployeeService;
	}
	
	@Autowired
	private OpWorkOrderService OpWorkOrderService;

	@Autowired
	public OpWorkOrderService getOpWorkOrderService() {
		return OpWorkOrderService;
	}

	@Autowired
	public void setOpWorkOrderService(OpWorkOrderService opWorkOrderService) {
		OpWorkOrderService = opWorkOrderService;
	}

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
			String sqlWhere = CoUtil.assemblyWhere(requestJson, "opWorkOrderEmployee");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			if("ma".equals(codeMaRole)) {
			    String relationFilter = "idMaCompany";
			    sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
			    }
			PageInfo<OpWorkOrderEmployeeImpl> page;
			page = opWorkOrderEmployeeService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<OpWorkOrderEmployeeImpl> list = page.getList();
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
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			if ("u".equals(editState)) {
				Long id = Long.valueOf(idString);
				opWorkOrderEmployee.setIdOpWorkOrder(id);
				opWorkOrderEmployee = opWorkOrderEmployeeService.get(opWorkOrderEmployee);
			}
			resultJson.put("code", 200);
			resultJson.put("opWorkOrderEmployee", opWorkOrderEmployee);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 查询工单下的员工
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/select", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String select(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String idOpWorkOrderString = requestJson.getString("idOpWorkOrder");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			JSONArray listAry = null ;
			Long idOpWorkOrder = Long.valueOf(idOpWorkOrderString);
			opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
			List<OpWorkOrderEmployeeImpl> list = opWorkOrderEmployeeService.select(opWorkOrderEmployee);
			listAry = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
			resultJson.put("code", 200);
			resultJson.put("opWorkOrderEmployee", listAry);
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
			Long idOpWorkOrderEmployee = 0L;
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			if(null != idMaCompany) {
			    opWorkOrderEmployee.setIdMaCompany(idMaCompany);
			}
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idOpWorkOrderEmployee = Long.valueOf(idString);
			}

			opWorkOrderEmployee.setEditState(editState);
			Long idOpWorkOrder = requestJson.getJSONObject("idOpWorkOrder").getLong("value");
			if(!UtValid.blank(idOpWorkOrder)) {
				resultJson.put("msg", "工单号不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
			Long idMaUser = requestJson.getJSONObject("idMaUser").getLong("value");
			if(!UtValid.blank(idMaUser)) {
				resultJson.put("msg", "用户不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setIdMaUser(idMaUser);
			String nameMaUser = requestJson.getJSONObject("nameMaUser").getString("value");
			if(!UtValid.blank(nameMaUser)) {
				resultJson.put("msg", "用户名不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setNameMaUser(nameMaUser);
			Double hours = requestJson.getJSONObject("hours").getDouble("value");
			if(!UtValid.blank(hours)) {
				resultJson.put("msg", "工时不可为空!");
				return resultJson.toString();
			}
			opWorkOrderEmployee.setHours(hours);

			if ("i".equals(editState)) {
				opWorkOrderEmployee.setCreateUser(userId);
				opWorkOrderEmployee.setModifiedUser(userId);
				this.setCommonField(opWorkOrderEmployee);
				opWorkOrderEmployeeService.insert(opWorkOrderEmployee);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				opWorkOrderEmployee.setId(id);
				opWorkOrderEmployee.setModifiedUser(userId);
				this.setCommonField(opWorkOrderEmployee);
				opWorkOrderEmployeeService.update(opWorkOrderEmployee);
			} else if ("d".equals(editState)) {
				opWorkOrderEmployeeService.delete(opWorkOrderEmployee);
			}
			resultJson.put("code", 200);
			resultJson.put("id", opWorkOrderEmployee.getId());
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
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			opWorkOrderEmployee.setId(id);
			opWorkOrderEmployee.setModifiedUser(userId);
			this.setCommonField(opWorkOrderEmployee);
			opWorkOrderEmployeeService.delete(opWorkOrderEmployee);
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
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			opWorkOrderEmployee.setIdMaCompany(idMaCompany);
			List<OpWorkOrderEmployeeImpl> treeOpWorkOrderEmployee = (List<OpWorkOrderEmployeeImpl>) opWorkOrderEmployeeService.selectTree(opWorkOrderEmployee);
			tree = JSON.toJSONString(treeOpWorkOrderEmployee);
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
	 * 更新员工工时
	*/
	@ResponseBody
	@RequestMapping(value="/editEmployeeWorkTime", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String editEmployeeWorkTime(@RequestBody String requestParams) {
		JSONObject resultJson =  new JSONObject();
		try {
			opWorkOrderEmployeeService.editEmployeeWorkTime(requestParams);
			resultJson.put("code", 200);
			
			// 添加日志
			resultJson = JSON.parseObject(requestParams);
			//工单id
			Long idOpWorkOrder = resultJson.getLong("idOpWorkOrder");
			String userId = resultJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrder.setId(idOpWorkOrder);
			opWorkOrder.setStatus("co");
			opWorkOrder.setModifiedUser(userId);
			super.syLogService.insert(opWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	
	/**
	 *  查询登录人工时
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectLoginWorkHours", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String selectLoginWorkHours(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			String id = requestJson.getString("idMaUser");
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			JSONArray listAry = null ;
			Long idMaUser = Long.valueOf(id);
			opWorkOrderEmployee.setIdMaUser(idMaUser);
			List<OpWorkOrderEmployeeImpl> list = opWorkOrderEmployeeService.select(opWorkOrderEmployee);
			listAry = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
			resultJson.put("code", 200);
			resultJson.put("opWorkOrderEmployee", listAry);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	
	/**
	 *  查询工单详细工时
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectEmployeeWorkTimeDetail", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String selectEmployeeWorkTimeDetail(@RequestBody String requestParams) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
			JSONArray listAry = null ;
			Long idOpWorkOrder = requestJson.getLong("idOpWorkOrder");
			opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
			List<OpWorkOrderEmployeeImpl> list = opWorkOrderEmployeeService.selectEmployeeWorkTimeDetail(opWorkOrderEmployee);
			listAry = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
			resultJson.put("code", 200);
			resultJson.put("opWorkOrderEmployee", listAry);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
}

