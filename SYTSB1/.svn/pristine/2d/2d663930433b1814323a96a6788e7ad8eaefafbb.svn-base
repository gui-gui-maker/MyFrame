package com.scts.car.service;

import com.khnt.base.Factory;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.dao.CarInfoDao;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.dao.AttachmentsDao;
import com.lsts.constant.Constant;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.log.service.SysLogService;
import com.scts.car.bean.CarWb;
import com.scts.car.dao.CarWbDao;

import util.TS_Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carWbManager")
public class CarWbManager extends EntityManageImpl<CarWb, CarWbDao> {
	@Autowired
	CarWbDao carWbDao;
	@Autowired
	FlowExtManager flowExtManager;
	@Autowired
	SysLogService logService;
	@Autowired
	SysLogDao logDao;
	@Autowired
	AttachmentDao attachmentDao;
	@Autowired
	AttachmentsDao attachmentsDao;
	@Autowired
	UserDao userDao;
	@Autowired
	private CarInfoDao carInfoDao;
	@Autowired
	private MessageService messageService;

	/**
	 * 保存车辆维保申请
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarWb carWb) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String pageStatus = request.getParameter("pageStatus");
		if ("add".equals(pageStatus)) {
			carWb.setCreateUserId(curUser.getId());
			carWb.setCreateUserName(curUser.getName());
			carWb.setCreateDate(new Date());
		} else if ("modify".equals(pageStatus)) {
			List<?> list = carWbDao.getFkCarIdByIdSql(request, carWb.getId());
			if (list != null && list.size() != 0) {
				Object[] obj = (Object[]) list.get(0);
				carInfoDao.updateRepairType(obj[0].toString(), "");// 恢复维保状态为空
			}
			carWb.setLastModifyUserId(curUser.getId());
			carWb.setLastModifyUserName(curUser.getName());
			carWb.setLastModifyDate(new Date());
		}
		try {
			if (StringUtil.isEmpty(carWb.getSn())) {
				String pre_sn = TS_Util.getCurYearMonth();
				carWb.setSn(pre_sn + carWbDao.queryNextSn(pre_sn));
			}
			carWbDao.save(carWb);
			CarInfo carInfo = carInfoDao.get(carWb.getFkCarId());
			carInfo.setRepairType("2"); // 维保申请中
			carInfoDao.save(carInfo);
			hashMap.put("success", true);
			hashMap.put("carWb", carWb);
			hashMap.put("msg", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			hashMap.put("success", false);
			hashMap.put("msg", "保存失败！");
		}
		return hashMap;
	}

	/**
	 * 删除车辆维保申请
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		try {
			CarWb carWb = carWbDao.get(id);
			carWb.setDataStatus("99");
			carWbDao.save(carWb);
			CarInfo carInfo = carInfoDao.get(carWb.getFkCarId());
			carInfo.setRepairType(null);// 恢复维保状态为空
			carInfoDao.save(carInfo);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 提交车辆维保申请
	 * 
	 * @param request
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @return
	 */
	public HashMap<String, Object> subFolw(HttpServletRequest request, String id, String flowId, String typeCode) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		CarWb carWb = carWbDao.get(id);
		// 流程提交参数
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// ---------------------------必要参数---------------------------
		paramMap.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		paramMap.put(FlowExtWorktaskParam.SERVICE_ID, id);
		// --------------------------------------------------------------
		paramMap.put(FlowExtWorktaskParam.SERVICE_TITLE, "车辆维保申请-" + carWb.getApplyUserName());
		paramMap.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		paramMap.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		try {
			flowExtManager.startFlowProcess(paramMap);
			carWb.setDataStatus("1"); // 车队负责人待审核
			carWbDao.save(carWb);
			hashMap.put("carWb", carWb);
			hashMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("success", false);
		}
		return hashMap;
	}

	/**
	 * 审核车辆维保申请（审核通过）
	 * 
	 * @param request
	 * @param areaFlag
	 * @param id
	 * @param flowId
	 * @param activityId
	 * @param typeCode
	 * @return
	 */
	public HashMap<String, Object> checkPass(HttpServletRequest request, String areaFlag, String id, String flowId,
			String activityId, String typeCode) {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// ------------- 必要参数 -----------------------
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		CarWb carWb = carWbDao.get(id);
		if ("2".equals(areaFlag)) {
			carWb.setFleetDealId(curUser.getId());
			carWb.setFleetDealName(curUser.getName());
			carWb.setFleetDealDate(new Date());
			carWb.setFleetDealResult("1");
			carWb.setFleetDealRemark("");
			carWb.setDataStatus("2"); // 办公室负责人待审核
		} else if ("3".equals(areaFlag)) {
			carWb.setOfficeDealId(curUser.getId());
			carWb.setOfficeDealName(curUser.getName());
			carWb.setOfficeDealDate(new Date());
			carWb.setOfficeDealResult("1");
			carWb.setOfficeDealRemark("");
			carWb.setSealUserId(curUser.getId());
			carWb.setSealUserName(curUser.getName());
			carWb.setSealData(new Date());
			carWb.setSealId(Factory.getSysPara().getProperty("CARWB_SEAL_ID"));
			carWb.setDataStatus("3"); // 车队负责人已审核
		} 
		try {
			flowExtManager.submitActivity(paramMap);
			carWbDao.save(carWb);
			hashMap.put("carWb", carWb);
			hashMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("success", false);
		}
		return hashMap;
	}

	/**
	 * 审核车辆维保申请（审核不通过）
	 * 
	 * @param request
	 * @param areaFlag
	 * @param id
	 * @param flowId
	 * @param activityId
	 * @param typeCode
	 * @param processId
	 * @return
	 */
	public HashMap<String, Object> checkNoPass(HttpServletRequest request, String areaFlag, String id, String flowId,
			String activityId, String typeCode, String processId) {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// ------------- 必要参数 -----------------------
		paramMap.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
		CarWb carWb = carWbDao.get(id);
		if ("2".equals(areaFlag)) {
			carWb.setFleetDealId(curUser.getId());
			carWb.setFleetDealName(curUser.getName());
			carWb.setFleetDealDate(new Date());
			carWb.setFleetDealResult("0"); // 不通过
			carWb.setFleetDealRemark("");

		} else if ("3".equals(areaFlag)) {
			carWb.setOfficeDealId(curUser.getId());
			carWb.setOfficeDealName(curUser.getName());
			carWb.setOfficeDealDate(new Date());
			carWb.setOfficeDealResult("0"); // 不通过
			carWb.setOfficeDealRemark("");
		}
		try {
			flowExtManager.finishProcess(paramMap);
			carWb.setDataStatus("9");
			carWbDao.save(carWb);
			hashMap.put("carWb", carWb);
			hashMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("success", false);
		}
		return hashMap;
	}

	/**
	 * 发送消息
	 * 
	 * @param request
	 * @param carWb
	 * @throws Exception
	 */
	public void sendMsg(HttpServletRequest request, CarWb carWb) throws Exception {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		try {
			System.out.println("发送消息开始=====================================");
			if ("1".equals(carWb.getDataStatus()) || "2".equals(carWb.getDataStatus())) {
				String nextCheckerName = (String) request.getAttribute("nextCheckerName");
				String nextCheckerTel = (String) request.getAttribute("nextCheckerTel");
				map1.put("carNum", carWb.getCarNum());
				map1.put("applyUserName", carWb.getApplyUserName());
				map1.put("applyDate", DateUtil.format(carWb.getApplyDate(), Constant.defaultDatePattern));
				map1.put("nextCheckerName", nextCheckerName);
				// 发送消息给下一步处理人
				messageService.sendMassageByConfig(request, carWb.getId(), nextCheckerTel, null, "carWb_apply_auditor",
						null, map1, null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
				// 发送消息给申请人
				messageService.sendMassageByConfig(request, carWb.getId(),
						userDao.get(carWb.getApplyUserId()).getEmployee().getMobileTel(), null, "carWb_apply_user",
						null, map1, null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
			} else if ("3".equals(carWb.getDataStatus())) {
				String checkerName = (String) request.getAttribute("checkerName");
				map1.put("carNum", carWb.getCarNum());
				map1.put("applyDate", DateUtil.format(carWb.getApplyDate(), Constant.defaultDatePattern));
				map1.put("checkerName", checkerName);
				// 发送消息给申请人
				messageService.sendMassageByConfig(request, carWb.getId(),
						userDao.get(carWb.getApplyUserId()).getEmployee().getMobileTel(), null, "carWb_apply_pass",
						null, map1, null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
			} else if ("9".equals(carWb.getDataStatus())) {
				String checkerName = (String) request.getAttribute("checkerName");
				map1.put("carNum", carWb.getCarNum());
				map1.put("applyDate", DateUtil.format(carWb.getApplyDate(), Constant.defaultDatePattern));
				map1.put("checkerName", checkerName);
				// 发送消息给申请人
				messageService.sendMassageByConfig(request, carWb.getId(),
						userDao.get(carWb.getApplyUserId()).getEmployee().getMobileTel(), null, "carWb_apply_fail",
						null, map1, null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
			}
			System.out.println("发送消息结束=====================================");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 获取签名及印章图片
	 * 
	 * @param param     参数
	 * @param paramType 参数类型 0:userId,1:pictureId
	 * @return
	 * @throws Exception
	 */
	public String getSignPictures(String param, String paramType) throws Exception {
		String filePath = "";
		if ("0".equals(paramType)) {
			String employeeId = userDao.get(param).getEmployee().getId();
			filePath = attachmentsDao.queryByBusinessId(employeeId);
		} else if ("1".equals(paramType)) {
			filePath = attachmentDao.get(param).getFilePath();
		}
		return filePath;
	}

	/**
	 * 根据业务id获取任务参数信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public HashMap<String, Object> getWorktaskParam(HttpServletRequest request, String id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			List<?> list = carWbDao.getWorktaskParam(request, id);
			Object[] obj = (Object[]) list.get(0);
			hashMap.put("id", obj[0]);
			hashMap.put("title", obj[1]);
			hashMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("success", false);
		}
		return hashMap;
	}

	/**
	 * 获取下一步处理人信息
	 * 
	 * @param request
	 * @param carWb
	 * @return
	 */
	public List<?> getNextCheckerInfo(HttpServletRequest request, CarWb carWb) {
		List<?> list = carWbDao.getNextCheckerInfo(request, carWb);
		return list;
	}

	/**
	 * 获取流转过程
	 * 
	 * @param business_id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getFlowStep(String business_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = logDao.getBJLogs(business_id);
		CarWb carWb = carWbDao.get(business_id);
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("identifier", "车辆维保申请审核<br />（车牌号：" + carWb.getCarNum() + "）");
		map.put("success", true);
		return map;
	}

	/**
	 * 标记车辆维保已完成
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> done(HttpServletRequest request, String id) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		try {
			CarWb carWb = carWbDao.get(id);
			carWb.setDataStatus("5");
			carWbDao.save(carWb);
			CarInfo carInfo = carInfoDao.get(carWb.getFkCarId());
			carInfo.setRepairType(null);// 恢复维保状态为空
			carInfoDao.save(carInfo);
			// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
			logService.setLogs(carWb.getId(), "车辆维保申请标记完成", "标记已完成维保", curUser != null ? curUser.getId() : "未获取到操作用户编号",
					curUser != null ? curUser.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 微信端获取我的申请事项信息
	 * 
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryMyApplyList(String userId, String dataStatus) throws Exception {
		try {
			List<CarWb> carWbList = carWbDao.queryMyApplyList(userId, dataStatus);
			return JsonWrapper.successWrapper(carWbList);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取我的申请事项失败！");
		}
	}

	/**
	 * 微信端获取待办事项信息
	 * 
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryPendingList(String userId, String dataStatus) throws Exception {
		List<CarWb> carWbList = new ArrayList<CarWb>();
		try {
			List<?> list = carWbDao.queryPendingList(userId, dataStatus);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					carWbList.add(carWbDao.get(list.get(i).toString()));
				}
			}
			return JsonWrapper.successWrapper(carWbList);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取待办事项失败！");
		}
	}

	/**
	 * 微信端获取已办理的事项信息
	 * 
	 * @param userId
	 * @param dataStatus
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryDealedList(String userId, String dataStatus) throws Exception {
		try {
			List<CarWb> carWbList = carWbDao.queryDealedList(userId, dataStatus);
			return JsonWrapper.successWrapper(carWbList);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取我的申请事项失败！");
		}
	}
}
