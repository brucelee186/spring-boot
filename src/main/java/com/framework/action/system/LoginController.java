package com.framework.action.system;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.framework.bean.common.SessionInfo;
import com.framework.bean.impl.MaUserImpl;
import com.framework.bean.impl.OpDeviceCabinetImpl;
import com.framework.bean.impl.OpRoomElectricImpl;
import com.framework.bean.impl.OpRoomOperationImpl;
import com.framework.exception.CoException;
import com.framework.service.MaManagerService;
import com.framework.service.MaUserService;
import com.framework.service.OpDeviceCabinetService;
import com.framework.service.OpRoomElectricService;
import com.framework.service.OpRoomOperationService;
import com.framework.util.CoUtil;
import com.framework.util.Md5Util;

@CrossOrigin
@Controller
@RequestMapping(value = "/system")
public class LoginController {
	
	@Resource 
	private MaManagerService maManagerService;
	
	@Resource 
	private MaUserService maUserService;
	
	@Resource 
	private OpRoomOperationService opRoomOperationService;
	
	@Resource 
	private OpRoomElectricService opRoomElectricService;
	
	@Resource 
	private OpDeviceCabinetService opDeviceCabinetService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Login(@RequestBody String requestParams, HttpServletRequest request, HttpSession session) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String loginName = requestJson.getString("username");
			MaUserImpl maUser = new MaUserImpl();
			maUser.setLoginName(loginName);
			maUser = maUserService.get(maUser);
			if (maUser == null) {
				return CoUtil.getUserNotExistErrorResp().toString();
			}
			String password = Md5Util.Md5(requestJson.getString("password"), null);
			if (!maUser.getPassword().equals(password)) {
				return CoUtil.getErrorResp("密码错误").toString();
			}
			maUser.setPassword("");

			Long idMaUser = maUser.getId();
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma, 操作室:ro, 主电室:re
			String codeMaRole = maUser.getCodeMaRole();
			// 如果是操作室控制人员取得操作室编号
			if(null != codeMaRole && "ro".equals(codeMaRole)) {
				OpRoomOperationImpl opRoomOperation = new OpRoomOperationImpl();
				opRoomOperation.setIdMaUser(idMaUser);
				opRoomOperation = opRoomOperationService.get(opRoomOperation);
				if(null == opRoomOperation) {
					return CoUtil.getErrorResp("操作室人员无此权限!").toString();
				}
				Long idOpRoomOperation = opRoomOperation.getId();
				maUser.setIdOpRoomOperation(idOpRoomOperation);
			}
			
			// 如果是主电室控制人员取得主电室编号
			// 带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma, 操作室:ro, 主电室:re
			if(null != codeMaRole && "re".equals(codeMaRole)) {
				OpRoomElectricImpl opRoomElectric = new OpRoomElectricImpl();
				opRoomElectric.setIdMaUser(idMaUser);
				opRoomElectric = opRoomElectricService.get(opRoomElectric);
				if(null == opRoomElectric) {
					return CoUtil.getErrorResp("主电室人员无此权限!").toString();
				}
				Long idOpRoomElectric = opRoomElectric.getId();
				maUser.setIdOpRoomElectric(idOpRoomElectric);
			}
			
			// 如果是电器柜那么取得电器柜信息. cm:电器柜
			if(null != codeMaRole && "cm".equals(codeMaRole)) {
				OpDeviceCabinetImpl opDeviceCabinet = new OpDeviceCabinetImpl();
				opDeviceCabinet.setIdMaUser(idMaUser);
				opDeviceCabinet = opDeviceCabinetService.get(opDeviceCabinet);
				if(null == opDeviceCabinet) {
					return CoUtil.getErrorResp("无登录电器柜权限!").toString();
				}
				Long idOpDeviceCabinet = opDeviceCabinet.getId();
				maUser.setIdOpDeviceCabinet(idOpDeviceCabinet);
			}
			
			// 初始化sessoin
			String userName  = maUser.getLoginName();
			String uidMaUser  = maUser.getUid();
			maUser.setUidMaUser(uidMaUser);
			Date loginDate = new Date();
			// 默认服务器ip
			String contextPath = request.getContextPath();
			
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(uidMaUser);
			sessionInfo.setUserName(userName);
			sessionInfo.setLoginDate(loginDate);
			sessionInfo.setContextPath(contextPath);
			sessionInfo.setManager(maUser);
			
			session.setAttribute("sessionInfo", sessionInfo);
			CoUtil.getSession().setAttribute("sessionInfo", sessionInfo);
			// RedisUtil.del("sessionInfo");
			JSONObject jsonSessionInfor = new JSONObject();
			jsonSessionInfor.put("sessionInfo", sessionInfo);
			// RedisUtil.set("sessionInfo", jsonSessionInfor.toString());
			resultJson.put("code", 200);
			resultJson.put("entity", maUser);
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
		}
		return resultJson.toString();

	}
	
	@ResponseBody
	@RequestMapping(value = "/loginFingerprint", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String LoginFingerprint(@RequestBody String requestParams, HttpServletRequest request, HttpSession session) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			Integer indexMaFingerprint = requestJson.getInteger("index");
			MaUserImpl maUser = new MaUserImpl();
			maUser.setIndexMaFingerprint(indexMaFingerprint);
			maUser = maUserService.get(maUser);
			if (maUser == null) {
				return CoUtil.getUserNotExistErrorResp().toString();
			}
			maUser.setPassword("");
			resultJson.put("code", 200);
			resultJson.put("entity", maUser);
		} catch (CoException e) {
			e.printStackTrace();
			resultJson.put("code", 500);
		}
		return resultJson.toString();
		
	}

//	@CrossOrigin
//	@ResponseBody
//	@RequestMapping(value = "/exit_login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//	public String exitLogin(@RequestBody String requestParams) {
//		JSONObject requestJson = JSON.parseObject(requestParams);
//		JSONObject result = new JSONObject();
//
//		try {
//			String sessionId = requestJson.getString("sessionId");
//			String loginStateId = requestJson.getString("loginStateId");
//			RedisUtil.del(sessionId);
//			RedisUtil.del(loginStateId);
//			result.put("code", 200);
//		} catch (Exception e) {
//			result.put("code", 500);
//			result.put("msg", e.getMessage());
//		}
//
//		return result.toString();
//	}

//	@CrossOrigin
//	@ResponseBody
//	@RequestMapping(value = "/edit_password", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//	public String edit_password(@RequestBody String requestParams) {
//		JSONObject requestJson = JSON.parseObject(requestParams);
//		JSONObject resultJson = new JSONObject();
//		JSONObject sessionResult = new JSONObject();
//		JSONObject session = serviceSys.getCurrent(sessionResult, requestJson);
//		if (session == null) {
//			return sessionResult.toString();
//		}
//		resultJson.put("logind", sessionResult.getJSONObject("logind"));
//
//		YzManager yzManager = service.get(session.getJSONObject("user"));
//
//		String password = requestJson.getJSONObject("password").getString("value");
//		if (!Md5Util.Md5(requestJson.getString("oldPwd"), yzManager.getSalt()).equals(yzManager.getPassword())) {
//			resultJson.put("code", 500);
//			resultJson.put("msg", "原密码不正确");
//			return resultJson.toString();
//		}
//		yzManager.setPassword(Md5Util.Md5(password, yzManager.getSalt()));
//		yzManager.setOldPassword(Md5Util.Md5(password, yzManager.getSalt()));
//		service.update(yzManager);
//
//		resultJson.put("code", 200);
//
//		return resultJson.toString();
//	}
}
