package com.framework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.bean.MaUser;
import com.framework.bean.common.Cons;
import com.framework.bean.impl.MaUserImpl;
import com.framework.bean.impl.OpDeviceElectricianCardImpl;
import com.framework.bean.impl.OpDeviceGroupImpl;
import com.framework.bean.impl.OpWorkOrderEmployeeImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.bean.impl.SyNotificationImpl;
import com.framework.dao.MaUserConditionMapper;
import com.framework.dao.MaUserMapper;
import com.framework.dao.OpDeviceElectricianCardMapper;
import com.framework.dao.OpWorkOrderEmployeeMapper;
import com.framework.dao.SyNotificationMapper;
import com.framework.exception.CoException;
import com.framework.service.SyNotificationService;
import com.framework.service.impl.common.CommonServiceImpl;
import com.framework.util.UtJPush;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
**********************************************
 * 项目名称: 
 * 模块名称：基础资料 -> 提示推送
 * 作者:     Auto
 * 日期:     2019/8/26
**********************************************
*/
@Service("syNotificationService")
public class SyNotificationServiceImpl extends CommonServiceImpl implements SyNotificationService {
	@Autowired
	private SyNotificationMapper syNotificationMapper;
	
	@Autowired
	private MaUserMapper maUserMapper;
	
	@Autowired
	private OpWorkOrderEmployeeMapper opWorkOrderEmployeeMapper;
	
	@Autowired
	private OpDeviceElectricianCardMapper opDeviceElectricianCardMapper;
	
	@Autowired
	private MaUserConditionMapper maUserConditionMapper;

	/**
	 * 新增实体对象
	 * @param syNotification
	 */
	public SyNotificationImpl insert(SyNotificationImpl syNotification) throws CoException {
		this.syNotificationMapper.insert(syNotification);
		return syNotification;
	}

	/**
	 * 删除实体对象
	 * @param syNotification
	 */
	public int delete(SyNotificationImpl syNotification) throws CoException {
		Long id = syNotification.getId();
		if(null != id) {
			syNotification.setTag("d");
			return this.syNotificationMapper.update(syNotification);
		} else {
			return 0;
		}
	}

	/**
	 * 更新实体对象
	 * @param syNotification
	 */
	public boolean update(SyNotificationImpl syNotification) throws CoException {
		boolean result = true;
		this.syNotificationMapper.update(syNotification);
		return result;
	}

	/**
	 * 查询实体列表
	 * @param syNotification
	 */
	@SuppressWarnings("unchecked")
	public List<SyNotificationImpl> select(SyNotificationImpl syNotification) throws CoException {
		return (List<SyNotificationImpl>) this.syNotificationMapper.select(syNotification);
	}

	/**
	 * 查询单个实体
	 * @param syNotification
	 */
	public SyNotificationImpl get(SyNotificationImpl syNotification) throws CoException {
		return (SyNotificationImpl) this.syNotificationMapper.get(syNotification);
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
	public PageInfo<SyNotificationImpl> selectPage(Integer pageNo, Integer pageSize, String sqlWhere, String orderBy) throws CoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		sqlWhere = (StringUtils.isBlank(sqlWhere) ? "1=1" : sqlWhere);
		orderBy = (StringUtils.isBlank(orderBy) ? " id desc" : orderBy);
		PageHelper.startPage(pageNo, pageSize);
		List list = syNotificationMapper.selectPage(pageNo, pageSize, sqlWhere, orderBy);
		PageInfo<SyNotificationImpl> page = new PageInfo<SyNotificationImpl>(list);
		return page;
	}

	/**
	 * 查询树型实体
	 * @param syNotification
	 */
	@SuppressWarnings("unchecked")
	public List<SyNotificationImpl> selectTree(SyNotificationImpl syNotification) throws CoException {
		return (List<SyNotificationImpl>) this.syNotificationMapper.selectTree(syNotification);
	}

	/**
	 *  发送推送信息
	 * @param syNotification
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl sendNotification(SyNotificationImpl syNotification) throws CoException {
		if(null != syNotification) {
			
			boolean res = UtJPush.jpushAndroid(syNotification);
			String status = syNotification.getStatus();
			// 发送未发送信息
			if("u".equals(status)) {
				// 成功更新状态为已发送 s:sended
				// 状态(已经发送 s:sended, 未发送 u:unsend, 失败 f:failed, 失效i:invalid)
				if(res) {
					syNotification.setStatus("s");
				}
				// 失败更新状态为发送失败 f:failed
				else {
					syNotification.setStatus("f");
				}
			}
			// 发送推送失败信息
			else if ("f".equals(status)) {
				// 成功更新状态为已发送 s:sended
				// 状态(已经发送 s:sended, 未发送 u:unsend, 失败 f:failed, 失效i:invalid)
				if(res) {
					syNotification.setStatus("s");
				}
				// 失败更新状态为发送失效 i:invalid
				else {
					syNotification.setStatus("i");
				}
			}
				
			syNotification.setModifiedUser(Cons.ID_SYS);
			syNotification.setModifiedIp(Cons.IP_SYS);
			syNotification.setEditState("u");
			this.setCommonField(syNotification);
			syNotificationMapper.update(syNotification);
		}
		return null;
	}

	/**
	 * 根据工单状态发送推送信息
	 * @param opWorkOrder
	 * @return
	 * @throws CoException
	 */
	public SyNotificationImpl insert(OpWorkOrderImpl opWorkOrder) throws CoException {
		try {
			String statusOpWorkOrder = opWorkOrder.getStatus();
			// 创建完成
			if("or".equals(statusOpWorkOrder)) {
				insertOpWorkOrderOr(opWorkOrder);
			}
			// 组队完成
			else if ("gi".equals(statusOpWorkOrder)) {
				insertOpWorkOrderGi(opWorkOrder);
			}
			// 停电完成
			else if ("po".equals(statusOpWorkOrder)) {
				insertOpWorkOrderPo(opWorkOrder);
			}
			// 操作室发工作牌
			else if ("osd".equals(statusOpWorkOrder)) {
				insertOpWorkOrderOsd(opWorkOrder);
			}
			// 主电室发工作牌
			else if ("esd".equals(statusOpWorkOrder)) {
				insertOpWorkOrderEsd(opWorkOrder);
			}
			// 作业开始
			else if ("ws".equals(statusOpWorkOrder)) {
				insertOpWorkOrderWs(opWorkOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 推送创建工单信息予点检员,带班人
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderOr(OpWorkOrderImpl opWorkOrder) {
		Long idOpWorkOrder = opWorkOrder.getId();
		Long idMaCompany = opWorkOrder.getIdMaCompany();
		// 创建带班人推送
		Long idLeader = opWorkOrder.getIdLeader();
		// 点检员(派发人)编号
		Long idChecker = opWorkOrder.getIdChecker();
		List<Long> arrListId = new ArrayList<Long>();
		arrListId.add(idLeader);
		arrListId.add(idChecker);
		
		String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
		String nameOpWorkOrder = opWorkOrder.getName();
		String uidMaUser = opWorkOrder.getModifiedUser();
		String description = opWorkOrder.getDescription();
		MaUserImpl maUser = new MaUserImpl();
		maUser.setArrListId(arrListId);
		maUser.setTagMapper("selectUserForNotification");
		
		List<MaUserImpl> listMaUser = (List<MaUserImpl>) maUserMapper.select(maUser);
		if(null != listMaUser && listMaUser.size() > 0) {
			for (int i = 0; i < listMaUser.size(); i++) {
				maUser = new MaUserImpl();
				maUser = listMaUser.get(i);
				String nameMaUser = maUser.getName();
				SyNotificationImpl syNotification = new SyNotificationImpl();
				syNotification.setTitle("NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "创建");
				String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "创建。请您继续留意后续工作。工单信息：" 
				+ nameOpWorkOrder;
				syNotification.setContent(content);
				syNotification.setAlert(content);
				syNotification.setMaUser(maUser);
				syNotification.setOpWorkOrder(opWorkOrder);
				this.insertNotification(syNotification);
			}
		}
	}
	
	/**
	 * 创建组队推送信息
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderGi(OpWorkOrderImpl opWorkOrder) {
		Long idOpWorkOrder = opWorkOrder.getId();
		Long idMaCompany = opWorkOrder.getIdMaCompany();
		String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
		String nameOpWorkOrder = opWorkOrder.getName();
		String uidMaUser = opWorkOrder.getModifiedUser();
		OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
		opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
		List<OpWorkOrderEmployeeImpl> listOpWorkOrderEmployee = (List<OpWorkOrderEmployeeImpl>)opWorkOrderEmployeeMapper.select(opWorkOrderEmployee);
		if(null != listOpWorkOrderEmployee && listOpWorkOrderEmployee.size() > 0) {
			ArrayList<Long> arrListId = new ArrayList<Long>();
			for (int i = 0; i < listOpWorkOrderEmployee.size(); i++) {
				opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
				opWorkOrderEmployee = (OpWorkOrderEmployeeImpl) listOpWorkOrderEmployee.get(i);
				Long idMaUser = opWorkOrderEmployee.getIdMaUser();
				arrListId.add(idMaUser);
			}
			MaUserImpl maUser = new MaUserImpl();
			maUser.setArrListId(arrListId);
			maUser.setTagMapper("selectUserForNotification");
			List<MaUserImpl> listMaUser = (List<MaUserImpl>) maUserMapper.select(maUser);
			if(null != listMaUser && listMaUser.size() > 0) {
				for (int i = 0; i < listMaUser.size(); i++) {
					maUser = new MaUserImpl();
					maUser = listMaUser.get(i);
					String cellphone = maUser.getCellphone();
					String nameMaUser = maUser.getName();
					Long idMaOrgnization = maUser.getIdMaOrgnization();
					SyNotificationImpl syNotification = new SyNotificationImpl();
					syNotification.setTitle("NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "完成组队");
					String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "完成组队。请您继续留意后续工作。 工单信息：" 
					+ nameOpWorkOrder;
					syNotification.setContent(content);
					syNotification.setAlert(content);
					syNotification.setMaUser(maUser);
					syNotification.setOpWorkOrder(opWorkOrder);
					this.insertNotification(syNotification);
				}
			}
		}
		
	}
	
	/**
	 * 创建设备停电完成推送信息
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderPo(OpWorkOrderImpl opWorkOrder) {
		Long idOpWorkOrder = opWorkOrder.getId();
		Long idMaCompany = opWorkOrder.getIdMaCompany();
		String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
		String nameOpWorkOrder = opWorkOrder.getName();
		if(null == nameMaUserOpWorkOrder) {
			nameMaUserOpWorkOrder = "";
		}
		String uidMaUser = opWorkOrder.getModifiedUser();
		// 创建带班人推送
		Long idLeader = opWorkOrder.getIdLeader();
		// 点检员(派发人)编号
		Long idChecker = opWorkOrder.getIdChecker();
		List<Long> arrListId = new ArrayList<Long>();
		arrListId.add(idLeader);
		arrListId.add(idChecker);
		OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
		opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
		MaUserImpl maUser = new MaUserImpl();
		maUser.setId(idLeader);
		maUser = maUserMapper.get(maUser);
		if(null != maUser) {
			String cellphone = maUser.getCellphone();
			String nameMaUser = maUser.getName();
			Long idMaOrgnization = maUser.getIdMaOrgnization();
			SyNotificationImpl syNotification = new SyNotificationImpl();
			syNotification.setTitle("NO." +  idOpWorkOrder + " 工单对应所有设备已停电完成，您可以开始作业了");
			String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单对应所有设备已停电完成。您可以开始作业了。工单信息：" 
					+ nameOpWorkOrder;
			syNotification.setContent(content);
			syNotification.setAlert(content);
			syNotification.setMaUser(maUser);
			syNotification.setOpWorkOrder(opWorkOrder);
			this.insertNotification(syNotification);
		}
	}
	
	/**
	 * 创建开始作业推送信息
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderWs(OpWorkOrderImpl opWorkOrder) {
		Long idOpWorkOrder = opWorkOrder.getId();
		Long idMaCompany = opWorkOrder.getIdMaCompany();
		String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
		String nameOpWorkOrder = opWorkOrder.getName();
		if(null == nameMaUserOpWorkOrder) {
			nameMaUserOpWorkOrder = "";
		}
		String uidMaUser = opWorkOrder.getModifiedUser();
		// 创建带班人推送
		Long idLeader = opWorkOrder.getIdLeader();
		// 点检员(派发人)编号
		Long idChecker = opWorkOrder.getIdChecker();
		List<Long> arrListId = new ArrayList<Long>();
		//arrListId.add(idLeader);
		//arrListId.add(idChecker);
		OpWorkOrderEmployeeImpl opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
		opWorkOrderEmployee.setIdOpWorkOrder(idOpWorkOrder);
		List<OpWorkOrderEmployeeImpl> listOpWorkOrderEmployee = (List<OpWorkOrderEmployeeImpl>)opWorkOrderEmployeeMapper.select(opWorkOrderEmployee);
		if(null != listOpWorkOrderEmployee && listOpWorkOrderEmployee.size() > 0) {
			for (int i = 0; i < listOpWorkOrderEmployee.size(); i++) {
				opWorkOrderEmployee = new OpWorkOrderEmployeeImpl();
				opWorkOrderEmployee = (OpWorkOrderEmployeeImpl) listOpWorkOrderEmployee.get(i);
				Long idMaUser = opWorkOrderEmployee.getIdMaUser();
				arrListId.add(idMaUser);
			}
			MaUserImpl maUser = new MaUserImpl();
			maUser.setArrListId(arrListId);
			maUser.setTagMapper("selectUserForNotification");
			List<MaUserImpl> listMaUser = (List<MaUserImpl>) maUserMapper.select(maUser);
			if(null != listMaUser && listMaUser.size() > 0) {
				for (int i = 0; i < listMaUser.size(); i++) {
					maUser = new MaUserImpl();
					maUser = listMaUser.get(i);
					String cellphone = maUser.getCellphone();
					String nameMaUser = maUser.getName();
					Long idMaOrgnization = maUser.getIdMaOrgnization();
					SyNotificationImpl syNotification = new SyNotificationImpl();
					syNotification.setTitle("NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "发起开始作业");
					String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单已由" + nameMaUserOpWorkOrder + "发起开始作业。请您继续配合后续工作。工单信息：" 
							+ nameOpWorkOrder;					
					syNotification.setContent(content);
					syNotification.setAlert(content);
					syNotification.setMaUser(maUser);
					syNotification.setOpWorkOrder(opWorkOrder);
					this.insertNotification(syNotification);
				}
			}
		}
	}
	
	
	/**
	 * 操作室发牌
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderOsd(OpWorkOrderImpl opWorkOrder) {
		String statusOpWorkOrder = opWorkOrder.getStatusOpRoomOperation();
		// 操作室驳回拒绝发牌
		if("re".equals(statusOpWorkOrder)) {
			Long idOpWorkOrder = opWorkOrder.getId();
			Long idMaCompany = opWorkOrder.getIdMaCompany();
			// 创建带班人推送
			Long idLeader = opWorkOrder.getIdLeader();
			
			String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
			String nameOpWorkOrder = opWorkOrder.getName();
			String uidMaUser = opWorkOrder.getModifiedUser();
			MaUserImpl maUser = new MaUserImpl();
			maUser.setId(idLeader);
			maUser = maUserMapper.get(maUser);
			if(null != maUser && null != maUser.getCellphone()) {
				String nameMaUser = maUser.getName();
				String uidMaUserOpRoomOperation = opWorkOrder.getUidMaUserOpRoomOperation();
				String nameMaUserOpRoomOperation = "";
				String cellphoneOpRoomOperation = "";
				String nameOpRoomOperation = "操作室";
				if(null != uidMaUserOpRoomOperation) {
					MaUserImpl maUserOpRoomOperaion = new MaUserImpl();
					maUserOpRoomOperaion.setUid(uidMaUserOpRoomOperation);
					maUserOpRoomOperaion.setTagMapper("getOpRoomOperaion");
					maUserOpRoomOperaion = maUserMapper.get(maUserOpRoomOperaion);
					nameMaUserOpRoomOperation = maUserOpRoomOperaion.getName();
					nameOpRoomOperation = maUserOpRoomOperaion.getNameOpRoomOperation();
					cellphoneOpRoomOperation = " 。电话：" + maUser.getCellphone();
				}
				SyNotificationImpl syNotification = new SyNotificationImpl();
				syNotification.setTitle("NO." +  idOpWorkOrder + " 工单已由" + nameOpRoomOperation + "驳回");
				String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单已由" + nameOpRoomOperation + "驳回。如有疑问请您联系操作室管理员" 
				+ nameMaUserOpRoomOperation 
				+ cellphoneOpRoomOperation 
				+ "。工单信息：" 
						+ nameOpWorkOrder;
				syNotification.setContent(content);
				syNotification.setAlert(content);
				syNotification.setMaUser(maUser);
				syNotification.setOpWorkOrder(opWorkOrder);
				this.insertNotification(syNotification);
			}
		}
	}
	/**
	 * 主电室发工作牌
	 * @param opWorkOrder
	 */
	private void insertOpWorkOrderEsd(OpWorkOrderImpl opWorkOrder) {
		Long idOpWorkOrder = opWorkOrder.getId();
		Long idMaCompany = opWorkOrder.getIdMaCompany();
		String statusOpRoomElectric = opWorkOrder.getStatusOpRoomElectric();
		// 主电室驳回停电申请(工作牌申请)
		if("re".equals(statusOpRoomElectric)) {
			// 取得主工单对应的所有带班人
			MaUserImpl maUserLeader = new MaUserImpl();
			maUserLeader.setMidOpWorkOrder(idOpWorkOrder);
			MaUserImpl maUserRe = new MaUserImpl();
			maUserRe.setId(opWorkOrder.getIdMaUser());
			maUserRe.setTagMapper("getOpRoomElectric");
			maUserRe = maUserMapper.get(maUserRe);
			String nameMaUserOpRoomEletric = maUserRe.getName();
			String nameOpRoomElectric = maUserRe.getNameOpRoomElectric();
			String cellphoneOpRoomEletric = " 。电话：" + maUserRe.getCellphone();
			List<MaUserImpl> listMaUserLeader = (List<MaUserImpl>) maUserConditionMapper.selectMainWorkOrderLeader(maUserLeader);
			if(null != listMaUserLeader && listMaUserLeader.size() > 0) {
				for (int i = 0; i < listMaUserLeader.size(); i++) {
					MaUserImpl maUserInner = new MaUserImpl();
					maUserInner = listMaUserLeader.get(i);
					String nameMaUser = maUserInner.getName();
					SyNotificationImpl syNotification = new SyNotificationImpl();
					idOpWorkOrder = maUserInner.getIdOpWorkOrder();
					String nameOpWorkOrder = maUserInner.getNameOpWorkOrder();
					syNotification.setTitle("NO." +  idOpWorkOrder + " 工单已由" + nameOpRoomElectric + "驳回");
					String content = nameMaUser + "您好。 NO." +  idOpWorkOrder + " 工单已由" + nameOpRoomElectric + "驳回。如有疑问请您联系主电室管理员"
							+ nameMaUserOpRoomEletric 
							+ cellphoneOpRoomEletric 
							+ "。工单信息：" 
							+ nameOpWorkOrder;					
					syNotification.setContent(content);
					syNotification.setAlert(content);
					syNotification.setMaUser(maUserInner);
					syNotification.setOpWorkOrder(opWorkOrder);
					syNotification.setIdMaCompany(idMaCompany);
					this.insertNotification(syNotification);
				}
			}
		} 
		else {
			OpDeviceGroupImpl opDeviceGroup = opWorkOrder.getOpDeviceGroup();
			if(null != opDeviceGroup) {
				Long idOpDeviceGroup = opDeviceGroup.getId();
				if(null != idOpDeviceGroup && idOpDeviceGroup > 0) {
					String nameMaUserOpWorkOrder = opWorkOrder.getNameMaUser();
					String nameOpWorkOrder = opWorkOrder.getName();
					if(null == nameMaUserOpWorkOrder) {
						nameMaUserOpWorkOrder = "";
					}
					OpDeviceElectricianCardImpl opDeviceElectricianCard = new OpDeviceElectricianCardImpl();
					opDeviceElectricianCard.setIdOpDeviceGroup(idOpDeviceGroup);
					MaUserImpl maUser = new MaUserImpl();
					maUser.setIdOpDeviceGroup(idOpDeviceGroup);
					List<MaUserImpl> listMaUser = maUserConditionMapper.selectOpDeviceGroupUser(maUser);
					if(null != listMaUser && listMaUser.size() > 0) {
						for (int i = 0; i < listMaUser.size(); i++) {
							maUser = listMaUser.get(i);
							String nameMaUser = maUser.getName();
							String groupDeviceCode = maUser.getGroupDeviceCode();
							SyNotificationImpl syNotification = new SyNotificationImpl();
							if("sd".equals(statusOpRoomElectric)) {
								syNotification.setTitle("停电作业通知");
								String content = nameMaUser + "您好。 设备【" +  groupDeviceCode + " 】准备停电,请您查看手机停电序列进行相关操作。请注意安全！";					
								syNotification.setContent(content);
								syNotification.setAlert(content);
							}
							else if ("rd".equals(statusOpRoomElectric)) {
								syNotification.setTitle("送电作业通知");
								String content = nameMaUser + "您好。 设备【" +  groupDeviceCode + " 】准备送电,请您查看手机送电序列进行相关操作。请注意安全！";					
								syNotification.setContent(content);
								syNotification.setAlert(content);
							}
							else {
								break;
							}
							syNotification.setMaUser(maUser);
							syNotification.setOpWorkOrder(opWorkOrder);
							syNotification.setIdMaCompany(idMaCompany);
							this.insertNotification(syNotification);
						}
					}
				}
			}
		}
	}

	@Override
	public SyNotificationImpl insert(Object obj) throws CoException {
		return null;
	}
	
	/**
	 * 插入工单推送信息
	 * @param syNotification
	 */
	private void insertNotification(SyNotificationImpl syNotification) {
		MaUserImpl maUser = syNotification.getMaUser();
		OpWorkOrderImpl opWorkOrder = syNotification.getOpWorkOrder();
		if(null != maUser && null != opWorkOrder) {
			Long idMaOrgnization = maUser.getIdMaOrgnization();
			Long idMaCompany = maUser.getIdMaCompany();
			Long idOpWorkOrder = opWorkOrder.getId();
			
			Long idMaUser = maUser.getId();
			String uidMaUser = maUser.getUid();
			String nameMaUser = maUser.getName();
			String codeMaRole = maUser.getCodeMaRole();
			String nameMaRole = maUser.getNameMaRole();
			String cellphone = maUser.getCellphone();
			
			
			syNotification.setIdMaCompany(idMaCompany);
			syNotification.setIdOpWorkOrder(idOpWorkOrder);
			syNotification.setIdMaUser(idMaUser);
			syNotification.setUidMaUser(uidMaUser);
			syNotification.setNameMaUser(nameMaUser);
			syNotification.setCodeMaRole(codeMaRole);
			syNotification.setNameMaRole(nameMaRole);
			syNotification.setAlias(cellphone);
			syNotification.setRegistrationId(uidMaUser);
			
			syNotification.setEditState("i");
			syNotification.setCreateUser(uidMaUser);
			if(null != idMaOrgnization) {
				syNotification.setGroups(idMaOrgnization.toString());
			}
			this.setCommonField(syNotification);
			syNotificationMapper.insert(syNotification);
		}
	}
}