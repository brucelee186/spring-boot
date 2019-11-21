package com.framework.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.common.Cons;
import com.framework.bean.impl.MaUserImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.bean.impl.SyLogImpl;
import com.framework.dao.MaUserMapper;
import com.framework.dao.OpWorkOrderMapper;
import com.framework.dao.SyLogMapper;
import com.framework.dao.SyNotificationMapper;
import com.framework.exception.CoException;
import com.framework.service.SyLogService;
import com.framework.service.SyNotificationService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 系统日志
 * 作者:     Auto
 * 日期:     2019/8/21
**********************************************
*/
@Service("syLogService")
public class SyLogServiceImpl extends CommonServiceImpl implements SyLogService {
	@Autowired
	private SyLogMapper syLogMapper;
	
	@Autowired
	private MaUserMapper maUserMapper;
	
	@Autowired
	private OpWorkOrderMapper opWorkOrderMapper;
	
	@Autowired
	private SyNotificationMapper syNotificationMapper;
	
	@Autowired
	private SyNotificationService syNotificationService;

	@Autowired
	public SyLogMapper getSyLogMapper() {
		return syLogMapper;
	}

	@Autowired
	public void setSyLogMapper(SyLogMapper syLogMapper) {
		this.syLogMapper = syLogMapper;
	}

	/**
	 * 新增实体对象
	 * @param syLog
	 */
	public SyLogImpl insert(SyLogImpl syLog) throws CoException {
		this.syLogMapper.insert(syLog);
		return syLog;
	}

	/**
	 * 删除实体对象
	 * @param syLog
	 */
	public int delete(SyLogImpl syLog) throws CoException {
		Long id = syLog.getId();
		if(null != id) {
			syLog.setTag("d");
			return this.syLogMapper.update(syLog);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param syLog
	 */
	public boolean update(SyLogImpl syLog) throws CoException {
		boolean result = true;
		this.syLogMapper.update(syLog);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syLog
	 */
	@SuppressWarnings("unchecked")
	public List<SyLogImpl> select(SyLogImpl syLog) throws CoException {
		return (List<SyLogImpl>) this.syLogMapper.select(syLog);
	}

	/**
	 * 查询单个实体
	 * @param syLog
	 */
	public SyLogImpl get(SyLogImpl syLog) throws CoException {
		return (SyLogImpl) this.syLogMapper.get(syLog);
	}

	/**
	 * 查询分页对象
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 * @throws CoException
	 */
	 @Override
	 @SuppressWarnings("unchecked")
	public PageInfo<SyLogImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syLogMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyLogImpl> page = new PageInfo<SyLogImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syLog
	 */
	@SuppressWarnings("unchecked")
	public List<SyLogImpl> selectTree(SyLogImpl syLog) throws CoException {
		return (List<SyLogImpl>) this.syLogMapper.selectTree(syLog);
	}

	/**
	 * 新增日志对象
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl insert(Object obj)  {
		try {
			Class<?> clazz = obj.getClass();
			Class<?> clazzSuper = clazz.getSuperclass();
			Class<?> clazzGrand = clazzSuper.getSuperclass();
			SyLogImpl syLog = new SyLogImpl();
			String className = clazzSuper.getSimpleName();
			// 工单日志
			if(null != className && "OpWorkOrder".equals(className)) {
				// 取得工单状态
				Field fi_status = clazzSuper.getDeclaredField("status");
				fi_status.setAccessible(true);
				Object obj_status = fi_status.get(obj);
				
				Field fi_userId = clazzSuper.getDeclaredField("modifiedUser");
				fi_userId.setAccessible(true);
				Object obj_userId = fi_userId.get(obj);
				
				String codeMaRole = "";
				Field fi_codeMaRole = clazzGrand.getDeclaredField("codeMaRole");
				fi_codeMaRole.setAccessible(true);
				Object obj_codeMaRole = fi_codeMaRole.get(obj);
				if(null != obj_codeMaRole) {
					codeMaRole = (String) obj_codeMaRole;
				}
				
				OpWorkOrderImpl opWorkOrder = (OpWorkOrderImpl) obj;
				HttpServletRequest request = opWorkOrder.getRequest();
				syLog.setOpWorkOrder(opWorkOrder);
				syLog.setRequest(request);
				
				String uidMaUser = "";
				
				if(null != obj_userId) {
					uidMaUser = obj_userId.toString();
				}
				
				// 更新操作人
				syLog.setUidMaUser(uidMaUser);
				opWorkOrder.setUidMaUser(uidMaUser);
				
				String nameMaUser = ""; 
				String nameMaRole  = ""; 
				Long idMaUser = 0L;
				MaUserImpl maUser = new MaUserImpl();
				maUser.setUid(uidMaUser);
				maUser = (MaUserImpl) maUserMapper.get(maUser);
				if(null != maUser) {
					nameMaUser = maUser.getName();
					codeMaRole = maUser.getCodeMaRole(); 
					nameMaRole = maUser.getNameMaRole();
					idMaUser = maUser.getId();
					syLog.setNameMaUser(nameMaUser);
					opWorkOrder.setNameMaUser(nameMaUser);
					opWorkOrder.setCodeMaRole(codeMaRole);
					opWorkOrder.setNameMaRole(nameMaRole);
					opWorkOrder.setIdMaUser(idMaUser);
					syLog.setNameMaUser(nameMaUser);
					syLog.setNameMaRole(nameMaRole);
				}
				
				// 取得工单操作日期
				//Date dateCreated = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				Date dateCreated = new Date();
				Field fi_dateCreated = clazzSuper.getDeclaredField("dateCreated");
				fi_dateCreated.setAccessible(true);
				Object obj_dateCreated = fi_dateCreated.get(obj);
				if(null != obj_dateCreated) {
					dateCreated = (Date) obj_dateCreated;
					syLog.setDateCreated(dateCreated);
				}
				String dateLog = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateCreated);
				opWorkOrder.setDateLog(dateLog);
				if(null != obj_status) {
					String status = obj_status.toString();
					// 取得工单编号
					Long idOpWorkOrder = 0L;
					Field fi_idOpWorkOrder = clazzSuper.getDeclaredField("id");
					fi_idOpWorkOrder.setAccessible(true);
					Object obj_idOpWorkOrder = fi_idOpWorkOrder.get(obj);
					if(null != obj_idOpWorkOrder) {
						idOpWorkOrder = (Long) obj_idOpWorkOrder;
						syLog.setIdOpWorkOrder(idOpWorkOrder);
					}
					
					OpWorkOrderImpl opWorkOrderOrigin = new OpWorkOrderImpl();
					opWorkOrderOrigin.setId(idOpWorkOrder);
					opWorkOrderOrigin = (OpWorkOrderImpl) opWorkOrderMapper.get(opWorkOrderOrigin);
					
					Long idMaCompany = 0L;
					Field fi_idMaCompany = clazzSuper.getDeclaredField("idMaCompany");
					fi_idMaCompany.setAccessible(true);
					Object obj_idMaCompany = fi_idMaCompany.get(obj);
					if(null != obj_idMaCompany) {
						idMaCompany = (Long) obj_idMaCompany;
					}
					
					if(null != opWorkOrderOrigin && 0L == idMaCompany) {
						idMaCompany = opWorkOrderOrigin.getIdMaCompany();
						fi_idMaCompany.set(obj, idMaCompany);
						//syLog.setOpWorkOrder(opWorkOrderInner);
					}
					
					syLog.setIdMaCompany(idMaCompany);
					
					// 带班人如果为null,重新从源工单取值
					Field fi_idLeader = clazzSuper.getDeclaredField("idLeader");
					fi_idLeader.setAccessible(true);
					Object obj_idLeader = fi_idLeader.get(obj);
					if(null == obj_idLeader && null != opWorkOrderOrigin) {
						Long idLeader = opWorkOrderOrigin.getIdLeader();
						fi_idLeader.set(obj, idLeader);
					}
					
					String nameOpWorkOrder = "";
					Field fi_nameOpWorkOrder = clazzSuper.getDeclaredField("name");
					fi_nameOpWorkOrder.setAccessible(true);
					Object obj_nameOpWorkOrder = fi_nameOpWorkOrder.get(obj);
					if(null != obj_nameOpWorkOrder) {
						nameOpWorkOrder = (String) obj_nameOpWorkOrder;
					} else {
						if(null != opWorkOrderOrigin) {
							nameOpWorkOrder = opWorkOrderOrigin.getName();
						}
						fi_nameOpWorkOrder.set(obj, nameOpWorkOrder);
					}
					
					// 工单创建完成
					if("or".equals(status)) {
						this.insertLogOpWorkOrderOr(syLog, obj, clazzSuper);
					}
					// 组队
					else if ("gi".equals(status)) {
						this.insertLogOpWorkOrderGi(syLog);
					}
					// 操作室发牌
					else if ("osd".equals(status)) {
						this.insertLogOpWorkOrderOsd(syLog);
					}
					// 主电室发牌
					else if ("esd".equals(status)) {
						this.insertLogOpWorkOrderEsd(syLog);
					}
					// 取牌完成
					else if ("ai".equals(status)) {
						this.insertLogOpWorkOrderAi(syLog);
					}
					// 停电完成
					else if ("po".equals(status)) {
						this.insertLogOpWorkOrderPo(syLog);
					}
					// 作业开始
					else if ("ws".equals(status)) {
						this.insertLogOpWorkOrderWs(syLog);
					}
					// 作业完成
					else if ("wd".equals(status)) {
						this.insertLogOpWorkOrderWd(syLog);
					}
					// 还牌
					else if ("pi".equals(status)) {
						this.insertLogOpWorkOrderPi(syLog);
					}
					// 工单录入完成
					else if ("co".equals(status)) {
						this.insertLogOpWorkOrderCo(syLog);
					}
					
					// 创建推送
					syNotificationService.insert((OpWorkOrderImpl)obj);
				}
			}
			syLog.setEditState("i");
			syLog.setCreateUser(Cons.ID_SYS);
			this.setCommonField(syLog);
			syLogMapper.insert(syLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建工单
	 * @param syLog
	 */
	private void insertLogOpWorkOrderOr(SyLogImpl syLog, Object obj, Class<?> clazzSuper) {
		OpWorkOrderImpl opWorkOrder = (OpWorkOrderImpl) obj;
		if(null != opWorkOrder) {
			try {
				Long idOpWorkOrder = opWorkOrder.getId();
				String nameMaUser = opWorkOrder.getNameMaUser();
				String nameMaRole = opWorkOrder.getNameMaRole();
				Long idMaUser = opWorkOrder.getIdMaUser();
				String dateLog = opWorkOrder.getDateLog();
				String log = "";
				syLog.setAtcion("创建工单(NO." + idOpWorkOrder + ")");
				log += "创建工单(NO." + idOpWorkOrder + ")" + "\n\r";
				
				log += "创建人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
				log += "创建日期: " + dateLog + "\n\r";
				
				// dateCreated
				
				// 取得机组编号
				Long idOpProUnit = 0L;
				Field fi_idOpProUnit = clazzSuper.getDeclaredField("idOpProUnit");
				fi_idOpProUnit.setAccessible(true);
				Object obj_idOpProUnit = fi_idOpProUnit.get(obj);
				if(null != obj_idOpProUnit) {
					idOpProUnit = (Long) obj_idOpProUnit;
					syLog.setIdOpProUnit(idOpProUnit);
				}
				
				// 取得机组名称
				String nameOpProUnit = "";
				Field fi_nameOpProUnit = clazzSuper.getDeclaredField("nameOpProUnit");
				fi_nameOpProUnit.setAccessible(true);
				Object obj_nameOpProUnit = fi_nameOpProUnit.get(obj);
				if(null != obj_nameOpProUnit) {
					nameOpProUnit = obj_nameOpProUnit.toString();
					syLog.setNameOpProUnit(nameOpProUnit);
				}
				log += "机组: " + nameOpProUnit + "(" + idOpProUnit + ")\n\r";
				
				// 取得点检员编号
				Long idChecker = 0L;
				Field fi_idChecker = clazzSuper.getDeclaredField("idChecker");
				fi_idChecker.setAccessible(true);
				Object obj_idChecker = fi_idChecker.get(obj);
				if(null != obj_idChecker) {
					idChecker = (Long) obj_idChecker;
					syLog.setIdChecker(idChecker);
				}
				
				// 取得点检员姓名
				String nameChecker = "";
				Field fi_nameChecker = clazzSuper.getDeclaredField("nameChecker");
				fi_nameChecker.setAccessible(true);
				Object obj_nameChecker = fi_nameChecker.get(obj);
				if(null != obj_nameChecker) {
					nameChecker = (String) obj_nameChecker;
					syLog.setNameChecker(nameChecker);
				}
				log += "点检员: " +  nameChecker + "(" + idChecker + ")\n\r";
				
				// 计划时间
				Double dateExpect = 0.0;
				Field fi_dateExpect = clazzSuper.getDeclaredField("dateExpect");
				fi_dateExpect.setAccessible(true);
				Object obj_dateExpect = fi_dateExpect.get(obj);
				if(null != obj_dateExpect) {
					dateExpect = (Double) obj_dateExpect;
					syLog.setDateExpect(dateExpect);
				}
				log += "计划时间: " + dateExpect + "\n\r";
				
				// 计划工时
				Double hoursExpect = 0.0;
				Field fi_hoursExpect = clazzSuper.getDeclaredField("hoursExpect");
				fi_hoursExpect.setAccessible(true);
				Object obj_hoursExpect = fi_hoursExpect.get(obj);
				if(null != obj_hoursExpect) {
					hoursExpect = (Double) obj_hoursExpect;
					syLog.setHoursExpect(hoursExpect);
				}
				log += "计划工时: " + hoursExpect + "\n\r";
				
				// 
				String description = "";
				Field fi_description = clazzSuper.getDeclaredField("description");
				fi_description.setAccessible(true);
				Object obj_description = fi_description.get(obj);
				if(null != obj_description) {
					description = (String) obj_description;
					syLog.setDescription(description);
				}
				log += "工序描述: " + description + "\n\r";
				syLog.setLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 组队
	 * @param syLog
	 */
	private void insertLogOpWorkOrderGi(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String jsonGroup = opWorkOrder.getJsonGroup().toJSONString();
			syLog.setAtcion("工单组队(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "工单组队(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "组队人员: (" + jsonGroup + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 取牌完成
	 * @param syLog
	 */
	private void insertLogOpWorkOrderAi(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String jsonGroup = opWorkOrder.getJsonGroup().toJSONString();
			syLog.setAtcion("工单取牌(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "工单取牌(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "取牌信息: (" + jsonGroup + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 停电完成
	 * @param syLog
	 */
	private void insertLogOpWorkOrderPo(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String nameOpWorkOrder = opWorkOrder.getName();
			if(null == nameMaUser) {
				nameMaUser = "";
			}
			if(null == nameMaRole) {
				nameMaRole = "电工";
			}
			if(null == idMaUser) {
				idMaUser = 0L;
			}
			syLog.setAtcion("工单停电完成(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "停电完成(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "工单信息: (" + nameOpWorkOrder + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 作业开始
	 * @param syLog
	 */
	private void insertLogOpWorkOrderWs(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String nameOpWorkOrder = opWorkOrder.getName();
			syLog.setAtcion("工单作业开始(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "作业开始(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "工单信息: (" + nameOpWorkOrder + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 作业完成
	 * @param syLog
	 */
	private void insertLogOpWorkOrderWd(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String nameOpWorkOrder = opWorkOrder.getName();
			syLog.setAtcion("工单作业完成(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "作业完成(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "工单信息: (" + nameOpWorkOrder + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 作业完成
	 * @param syLog
	 */
	private void insertLogOpWorkOrderPi(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String nameOpWorkOrder = opWorkOrder.getName();
			syLog.setAtcion("工单还牌完成(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "还牌完成(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "工单信息: (" + nameOpWorkOrder + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 作业完成
	 * @param syLog
	 */
	private void insertLogOpWorkOrderCo(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			String nameMaRole = opWorkOrder.getNameMaRole();
			Long idMaUser = opWorkOrder.getIdMaUser();
			String dateLog = opWorkOrder.getDateLog();
			String nameOpWorkOrder = opWorkOrder.getName();
			syLog.setAtcion("工单工时录入完成(NO." + idOpWorkOrder + ")");
			String log = "";
			log += "工时录入完成(NO." + idOpWorkOrder + ")" + "\n\r";
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "工单信息: (" + nameOpWorkOrder + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 * 操作室发牌
	 * @param syLog
	 */
	private void insertLogOpWorkOrderOsd(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			if(null == nameMaUser) {
				nameMaUser = "";
			}
			String nameMaRole = opWorkOrder.getNameMaRole();
			if(null == nameMaRole) {
				nameMaRole = "操作室管理员";
			}
			Long idMaUser = opWorkOrder.getIdMaUser();
			if(null == idMaUser) {
				idMaUser = 0L;
			}
			
			String statusOpRoomOperation = opWorkOrder.getStatusOpRoomOperation();
			
			String dateLog = opWorkOrder.getDateLog();
			String jsonGroup = opWorkOrder.getJsonGroup().toJSONString();
			String log = "";
			
			// 发牌:sd
			if("sd".equals(statusOpRoomOperation)) {
				syLog.setAtcion("操作室发放操作牌(NO." + idOpWorkOrder + ")");
				log += "操作室发放操作牌(NO." + idOpWorkOrder + ")" + "\n\r";
			}
			// 还牌:rd
			else if ("rd".equals(statusOpRoomOperation)) {
				syLog.setAtcion("操作室收到操作牌(NO." + idOpWorkOrder + ")");
				log += "操作室收到操作牌(NO." + idOpWorkOrder + ")" + "\n\r";
			}
			// 驳回(只在操作发牌时,还牌不可驳回):re
			else if ("re".equals(statusOpRoomOperation)) {
				syLog.setAtcion("操作室驳回操作牌申请(NO." + idOpWorkOrder + ")");
				log += "操作室驳回操作牌申请(NO." + idOpWorkOrder + ")" + "\n\r";
			}
			log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
			log += "日期: " + dateLog + "\n\r";
			log += "操作牌信息: (" + jsonGroup + ")\n\r";
			syLog.setLog(log);
		}
	}
	
	/**
	 *  主电室发牌
	 * @param syLog
	 */
	private void insertLogOpWorkOrderEsd(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		if(null != opWorkOrder) {
			Long idOpWorkOrder = opWorkOrder.getId();
			String nameMaUser = opWorkOrder.getNameMaUser();
			if(null == nameMaUser) {
				nameMaUser = "";
			}
			String nameMaRole = opWorkOrder.getNameMaRole();
			if(null == nameMaRole) {
				nameMaRole = "主电室管理员";
			}
			Long idMaUser = opWorkOrder.getIdMaUser();
			if(null == idMaUser) {
				idMaUser = 0L;
			}
			String dateLog = opWorkOrder.getDateLog();
			String infor = opWorkOrder.getInformation();
			String log = "";
			String statusOpRoomElectric = opWorkOrder.getStatusOpRoomElectric();
			String nameCard = "";
			// re:主电室驳回
			if("re".equals(statusOpRoomElectric)) {
				syLog.setAtcion("主电室驳回工作牌申请(NO." + idOpWorkOrder + ")");
				log += "主电室驳回工作牌申请(NO." + idOpWorkOrder + ")" + "\n\r";
				log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
				log += "日期: " + dateLog + "\n\r";
			}
			else {
				// sd:发放工作牌
				if("sd".equals(statusOpRoomElectric)) {
					nameCard = "工作牌";
				}
				// rd:发放停电牌
				else if ("rd".equals(statusOpRoomElectric)) {
					nameCard = "停电牌";
				}
				String noOpWorkOrder = "";
				if(null != idOpWorkOrder && idOpWorkOrder > 0) {
					noOpWorkOrder = "(NO." + idOpWorkOrder + ")";
				}
				syLog.setAtcion("主电室发放" + nameCard + noOpWorkOrder);
				log += "主电室发放" + nameCard + noOpWorkOrder + "\n\r";
				log += "操作人: " + nameMaUser + "[" + nameMaRole + "]" + "(" + idMaUser + ")\n\r";
				log += "日期: " + dateLog + "\n\r";
				log += "发放" + nameCard + "信息: (" + infor + ")\n\r";
			}
			syLog.setLog(log);
		}
	}

	/**
	 * 新增计划任务日志
	 * @param syLog
	 * @return
	 * @throws CoException
	 */
	public SyLogImpl insertTask(Object obj) throws CoException {
		try {
			Class<?> clazz = obj.getClass();
			Class<?> clazzSuper = clazz.getSuperclass();
			Class<?> clazzGrand = clazzSuper.getSuperclass();
			SyLogImpl syLog = new SyLogImpl();
			String className = clazzSuper.getSimpleName();
			// 工单计划任务日志
			if(null != className && "OpWorkOrder".equals(className)) {
				
				// 取得工单状态
				Field fi_status = clazzSuper.getDeclaredField("status");
				fi_status.setAccessible(true);
				Object obj_status = fi_status.get(obj);
				if(null != obj_status) {
					OpWorkOrderImpl opWorkOrder = (OpWorkOrderImpl) obj;
					String status = opWorkOrder.getStatus();
					// 停电完成:po(power outage)
					if("po".equals(status)) {
						syLog.setOpWorkOrder(opWorkOrder);
						this.insertTaskLogPo(syLog);
					}
					// 创建计划任务推送
					syNotificationService.insert(opWorkOrder);
				}
				syLog.setEditState("i");
				syLog.setCreateUser(Cons.ID_SYS);
				syLog.setCreateIp(Cons.IP_SYS);
				this.setCommonField(syLog);
				syLogMapper.insert(syLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 插入已经停电完成的工单日志
	 * @param opWorkOrder
	 */
	private void insertTaskLogPo(SyLogImpl syLog) {
		OpWorkOrderImpl opWorkOrder = syLog.getOpWorkOrder();
		Long idOpWorkOrder = opWorkOrder.getId();
		String uidMaUser = opWorkOrder.getModifiedUser();
		syLog.setUidMaUser(uidMaUser);
		ArrayList<Long> arrListId = opWorkOrder.getArrListId();
		String dateLog = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		syLog.setAtcion("工单停电完成(NO." + idOpWorkOrder + ")");
		String log = "";
		log += "停电完成(NO." + idOpWorkOrder + ")" + "\n\r";
		log += "系统更新可开始作业工单" + "\n\r";
		log += "操作人: " + "System." + "\n\r";
		log += "日期: " + dateLog + "\n\r";
		log += "工单批量信息: (" + arrListId + ")\n\r";
		syLog.setLog(log);
	}
}