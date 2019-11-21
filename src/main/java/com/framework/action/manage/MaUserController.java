package com.framework.action.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import com.framework.bean.impl.MaUserImpl;
import com.framework.exception.CoException;
import com.framework.service.MaUserService;
import com.framework.util.CoUtil;
import com.framework.util.Md5Util;
import com.framework.util.PageUtil;
import com.framework.util.UtIpAddress;
import com.framework.util.UtValid;
import com.github.pagehelper.PageInfo;

import sun.misc.BASE64Decoder;

/*
**********************************************
 * 项目名称: AnSteel
 * 模块名称: 控制层 -> 用户管理
 * 作者:    Auto
 * 日期:    2019/8/12
**********************************************
*/
@Controller
@CrossOrigin
@RequestMapping("/manage/maUser")
public class MaUserController extends BaseController{

	@Autowired
	private MaUserService maUserService;

	@Autowired
	public MaUserService getMaUserService() {
		return maUserService;
	}

	@Autowired
	public void setMaUserService(MaUserService maUserService) {
		this.maUserService = maUserService;
	}

	/**
	 * 查询
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getX5Page", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getX5Page(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		Integer pageNo = requestJson.getInteger("pageNo");
		Integer pageSize = requestJson.getInteger("pageSize");
		String orderBy = requestJson.getString("orderBy");
		String sqlWhere = CoUtil.assemblyWhere(requestJson, "maUser");
		
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		String loginName = requestJson.getJSONObject("userdata").getJSONObject("loginName").getString("value");
		Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
		//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
		String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
		if("ma".equals(codeMaRole)) {
			String relationFilter = "idMaCompany";
			sqlWhere = CoUtil.assemblyWhereFilter(requestJson, sqlWhere, relationFilter);
		}
		PageInfo<MaUserImpl> page;
		try {
			page = maUserService.selectPage(pageNo, pageSize, sqlWhere, orderBy);
			List<MaUserImpl> list = page.getList();
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
	 * @param maUser
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String edit(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject requestJson = JSON.parseObject(requestParams);
		JSONObject resultJson = new JSONObject();
		try {
			String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
			String editState = requestJson.getJSONObject("userdata").getJSONObject("editState").getString("value");
			//带班人:le, 点检员:ch, 操作工:op, 电工:el,管理员:ad, 员工:em, 工长:ma
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			Long idMaUser = 0L;
			if("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				idMaUser = Long.valueOf(idString);
			}

			MaUserImpl maUser = new MaUserImpl();
			if(null != idMaCompany) {
				maUser.setIdMaCompany(idMaCompany);
			}			
			maUser.setEditState(editState);
			String loginName = requestJson.getJSONObject("loginName").getString("value");
			if(!UtValid.blank(loginName)) {
				resultJson.put("msg", "登录名不可为空!");
				return resultJson.toString();
			}
			maUser.setLoginName(loginName);
			String password = requestJson.getJSONObject("password").getString("value");
			
			if("i".equals(editState) && !UtValid.blank(password)) {
				resultJson.put("msg", "密码不可为空!");
				return resultJson.toString();
			}
			
			if("u".equals(editState) && !UtValid.blank(password)) {
				maUser.setPassword(null);
			} else {
				password = CoUtil.Md5(password, null);
				maUser.setPassword(password);
			}
			String name = requestJson.getJSONObject("name").getString("value");
			if(!UtValid.blank(name)) {
				resultJson.put("msg", "用户名不可为空!");
				return resultJson.toString();
			}
			maUser.setName(name);
			Long idMaRole = requestJson.getJSONObject("idMaRole").getLong("value");
			if(!UtValid.blank(idMaRole)) {
				resultJson.put("msg", "主角色不可为空!");
				return resultJson.toString();
			}
			maUser.setIdMaRole(idMaRole);
			String badgeNumber = requestJson.getJSONObject("badgeNumber").getString("value");
			maUser.setBadgeNumber(badgeNumber);
			String email = requestJson.getJSONObject("email").getString("value");
			if(email.length() > 0 && !UtValid.email(email)) {
				resultJson.put("msg", "邮箱地址格式不正确!");
				return resultJson.toString();
			}
			maUser.setEmail(email);
			Long idMaOrgnization = requestJson.getJSONObject("idMaOrgnization").getLong("value");
			maUser.setIdMaOrgnization(idMaOrgnization);
			String cellphone = requestJson.getJSONObject("cellphone").getString("value");
			if(!UtValid.blank(cellphone)) {
				resultJson.put("msg", "手机不可为空!");
				return resultJson.toString();
			}
			if(!UtValid.cellphone(cellphone)) {
				resultJson.put("msg", "手机格式不正确!");
				return resultJson.toString();
			}
			maUser.setCellphone(cellphone);
			//maEmployee 员工管理
			// 如果是员工管理,不验证公司
//			if( "maUser".equals(tagPageCode)) {
//				Long idMaCompany = requestJson.getJSONObject("idMaCompany").getLong("value");
//				if(!UtValid.blank(idMaCompany)) {
//					resultJson.put("msg", "公司不可为空!");
//					return resultJson.toString();
//				}
//			} else {
//				Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
//				maUser.setIdMaCompany(idMaCompany);
//			}
			maUser.setRequest(request);
			if ("i".equals(editState)) {
				maUser.setCreateUser(userId);
				maUser.setModifiedUser(userId);
				this.setCommonField(maUser);
				maUserService.insert(maUser);
			} else if ("u".equals(editState)) {
				String idString = requestJson.getJSONObject("userdata").getJSONObject("id").getString("value");
				Long id = Long.valueOf(idString);
				maUser.setId(id);
				this.setCommonField(maUser);
				maUserService.update(maUser);
			} else if ("d".equals(editState)) {
				maUserService.delete(maUser);
			}
			resultJson.put("code", 200);
			resultJson.put("id", maUser.getId());
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
	public String delete(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSON.parseObject(requestParams);
		String idString = requestJson.getString("id");
		String userId = requestJson.getJSONObject("userdata").getJSONObject("uidMaUser").getString("value");
		try {
			Long id = Long.valueOf(idString);
			MaUserImpl maUser = new MaUserImpl();
			maUser.setId(id);
			maUser.setModifiedUser(userId);
			maUser.setRequest(request);
			this.setCommonField(maUser);
			maUserService.delete(maUser);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}


	/**
	 * 查询用户信息
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/get",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String get(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSONObject.parseObject(requestParams);
		String uidMaUser = requestJson.getString("uidMaUser");
		try {
			MaUserImpl maUserImpl = new MaUserImpl();
			maUserImpl.setUid(uidMaUser);
			maUserImpl = maUserService.get(maUserImpl);
			if(maUserImpl == null) {
				resultJson.put("msg", "该用户不存在!");
				return resultJson.toString();
			}
			resultJson.put("maEntity", maUserImpl);
			resultJson.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}

	/**
	 * 更新用户信息
	 * @param requestParams
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String update(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		JSONObject requestJson = JSONObject.parseObject(requestParams);
		String uidMaUser = requestJson.getString("uidMaUser");
		int flg = requestJson.getInteger("flg");
		try {
			MaUserImpl maUser = new MaUserImpl();
			maUser.setUid(uidMaUser);
			if(flg == 0) {
				//更新用户名
				String name = requestJson.getString("key");
				maUser.setName(name);
			} else if(flg == 1) {
				//更新用户电话
				String cellphone = requestJson.getString("key");
				maUser.setCellphone(cellphone);
			} else if(flg == 2) {
				//更新头像
				String imgStr = requestJson.getString("key");
				//base64图片编码
				String [] base64= imgStr.split(",");
				String fileSrc = base64[1].replace(" ", "+");
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				byte[] b = decoder.decodeBuffer(fileSrc);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
		
				//获取上传到服务器上的文件路径
				//String savePath = request.getSession().getServletContext().getRealPath("/");
				String savePath = "D:/upload/";
				//文件夹名
				String folderPath = "image" + File.separator;
				//判断有没有此文件夹，没有则新建一个
				File targetFile = new File(savePath + folderPath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();				
				}
				//
				String fileName = uidMaUser + ".jpg";
				//用用户主键做保存的图片的（名字+文件类型）
				File filePath = new File(savePath + folderPath  + fileName);
				try {
					//创建图片
					filePath.createNewFile();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try {
					//图片信息
					OutputStream out = new FileOutputStream(filePath);				
					//写入信息
					out.write(b);
					//
					out.flush();
					out.close();
					//maUser.setProfilePhoto("images/" + fileName);//头像路径
					maUser.setProfilePhoto(fileName);
				} catch (Exception e) {
					e.printStackTrace();
					resultJson.put("code", 500);
					resultJson.put("msg", e.toString());
				}
			} else if(flg == 3) {
				maUser = maUserService.get(maUser);
				//新密码
				String password = Md5Util.Md5(requestJson.getString("key"), null);
				//原密码
				String opassword = Md5Util.Md5(requestJson.getString("key1"), null);
				if(opassword!=null && opassword.equals(maUser.getPassword())) {
					maUser.setUid(uidMaUser);
					maUser.setPassword(password);
				} else {
					resultJson.put("code", 500);
					resultJson.put("msg", "原密码错误");
					return resultJson.toString();
				}
			}
			Long id = requestJson.getLong("id");
			maUser.setId(id);
			String codeMaRole = requestJson.getString("codeMaRole");
			maUser.setCodeMaRole(codeMaRole);
			String uidMaCompany =requestJson.getString("uidMaCompany");
			maUser.setUidMaCompany(uidMaCompany);
			Long idMaRole = requestJson.getLong("idMaRole");
			maUser.setIdMaRole(idMaRole);
			this.setCommonField(maUser);
			maUserService.update(maUser);
			resultJson.put("code", 200);
			resultJson.put("id", maUser.getUid());
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
	public String selectTree(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaUserImpl maUser = new MaUserImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			maUser.setIdMaCompany(idMaCompany);
			if("".equals(codeMaRole)) {
				codeMaRole = null;
			}
			maUser.setCodeMaRole(codeMaRole);
			List<MaUserImpl> treeMaUser = (List<MaUserImpl>) maUserService.selectTree(maUser);
			tree = JSON.toJSONString(treeMaUser);
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
	 * 查询带班人  查检员 电工
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectPreTree", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectPreTree(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		String tree = "";
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaUserImpl maUser = new MaUserImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			String codeMaRole = requestJson.getJSONObject("userdata").getJSONObject("codeMaRole").getString("value");
			Long idMaOrgnization = requestJson.getJSONObject("userdata").getJSONObject("idMaOrgnization").getLong("value");
			maUser.setIdMaCompany(idMaCompany);
			maUser.setCodeMaRole(codeMaRole);
			maUser.setIdMaOrgnization(idMaOrgnization);
			List<MaUserImpl> treeMaUser = (List<MaUserImpl>) maUserService.selectPreTree(maUser);
			//tree = JSON.toJSONString(treeMaUser);
			resultJson.put("code", 200);
			resultJson.put("data", treeMaUser);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
	/**
	 * 查询带班人  查检员
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectMaUserByRole", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectMaUserByRole(@RequestBody String requestParams, HttpServletRequest request) {
		JSONObject resultJson = new JSONObject();
		try {
			JSONObject requestJson = JSON.parseObject(requestParams);
			MaUserImpl maUser = new MaUserImpl();
			Long idMaCompany = requestJson.getJSONObject("userdata").getJSONObject("idMaCompany").getLong("value");
			if(null == idMaCompany) {
				resultJson.put("code", 500);
				resultJson.put("msg", "公司不可为空!");
				return resultJson.toString();
			}
			String codeMaRole = requestJson.getJSONObject("codeMaRole").getString("value");
			if(null == codeMaRole || "".equals(codeMaRole)) {
				resultJson.put("code", 500);
				resultJson.put("msg", "角色不可为空!");
				return resultJson.toString();
			}
			maUser.setIdMaCompany(idMaCompany);
			maUser.setCodeMaRole(codeMaRole);
			List<MaUserImpl> listMaUser = maUserService.select(maUser);
			resultJson.put("code", 200);
			resultJson.put("data", listMaUser);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("code", 500);
			resultJson.put("msg", e.toString());
		}
		return resultJson.toString();
	}
	
}

