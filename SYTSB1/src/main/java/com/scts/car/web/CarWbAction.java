package com.scts.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.exception.KhntException;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.service.CarInfoManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.scts.car.bean.CarWb;
import com.scts.car.service.CarWbManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("carWbAction")
public class CarWbAction extends SpringSupportAction<CarWb, CarWbManager> {

	@Autowired
	private CarWbManager carWbManager;
	@Autowired
	private CarInfoManager carInfoManager;
	@Autowired
	private SysLogService logService;

	/**
	 * 保存车辆维保信息
	 * 
	 * @param request
	 * @param carWb
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarWb carWb) throws Exception {
		return carWbManager.saveInfo(request, carWb);
	}

	/**
	 * 删除申请
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteInfo")
	@ResponseBody
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		return carWbManager.deleteInfo(request, id);
	}

	/**
	 * 获取签名及印章图片
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSignPictures")
	@ResponseBody
	public HashMap<String, Object> getSignPictures(HttpServletRequest request, String fleetDealId, String officeDealId,
			String sealId) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(fleetDealId) && !"null".equals(fleetDealId)) {
				hashMap.put("fleet_signPicture", carWbManager.getSignPictures(fleetDealId, "0"));
			}
			if (StringUtil.isNotEmpty(officeDealId) && !"null".equals(officeDealId)) {
				hashMap.put("office_signPicture", carWbManager.getSignPictures(officeDealId, "0"));
			}
			if (StringUtil.isNotEmpty(sealId) && !"null".equals(sealId)) {
				hashMap.put("seal_signPicture", carWbManager.getSignPictures(sealId, "1"));
			}
			hashMap.put("success", true);
		} catch (Exception e) {
			hashMap.put("success", false);
			e.printStackTrace();
		}
		return hashMap;
	}

	/**
	 * 提交车辆维保申请
	 * 
	 * @param request
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(HttpServletRequest request, String id, String flowId, String typeCode)
			throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = carWbManager.subFolw(request, id, flowId, typeCode);
		if ((boolean) hashMap.get("success")) {
			CarWb carWb = (CarWb) hashMap.get("carWb");
			// 获取下一步操作人信息
			List<?> list = carWbManager.getNextCheckerInfo(request, carWb);
			String nextCheckNameTemp = "";
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String nextCheckerName = "";
					String nextCheckerTel = "";
					nextCheckerName = obj[0] != null ? obj[0].toString() : "";
					nextCheckerTel = obj[1] != null ? obj[1].toString() : "";
					nextCheckNameTemp = StringUtil.isEmpty(nextCheckNameTemp) ? nextCheckerName
							: nextCheckNameTemp + "," + nextCheckerName;
					if (StringUtil.isNotEmpty(nextCheckerTel)) {
						request.setAttribute("nextCheckerName", nextCheckerName);
						request.setAttribute("nextCheckerTel", nextCheckerTel);
						// 发送提醒消息
						try {
							carWbManager.sendMsg(request, carWb);
						} catch (Exception e) {
							e.printStackTrace();
							KhntException kh = new KhntException(e);
							log.error(kh.getMessage());
						}
					}
				}
			}
			// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
			logService.setLogs(carWb.getId(), "提交申请", "提交申请至" + nextCheckNameTemp + "，操作结果：车队负责人待审核",
					curUser != null ? curUser.getId() : "未获取到操作用户编号",
					curUser != null ? curUser.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
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
	 * @throws Exception
	 */
	@RequestMapping("checkPass")
	@ResponseBody
	public HashMap<String, Object> checkPass(HttpServletRequest request, String areaFlag, String id, String flowId,
			String activityId, String typeCode) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = carWbManager.checkPass(request, areaFlag, id, flowId, activityId, typeCode);
		if ((boolean) hashMap.get("success")) {
			CarWb carWb = (CarWb) hashMap.get("carWb");
			if ("3".equals(carWb.getDataStatus())) {
				CarInfo carInfo = carInfoManager.get(carWb.getFkCarId());
				carInfo.setRepairType("1");// 维保中
				carInfoManager.save(carInfo);
			}
			String nextCheckNameTemp = "";
			if ("3".equals(areaFlag)) {
				String checkerName = curUser.getName();
				request.setAttribute("checkerName", checkerName);
				// 发送提醒消息
				try {
					carWbManager.sendMsg(request, carWb);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
				}
			} else {
				// 获取下一步操作人信息
				List<?> list = carWbManager.getNextCheckerInfo(request, carWb);
				if (list != null && list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						Object[] obj = (Object[]) list.get(i);
						String nextCheckerName = "";
						String nextCheckerTel = "";
						nextCheckerName = obj[0] != null ? obj[0].toString() : "";
						nextCheckerTel = obj[1] != null ? obj[1].toString() : "";
						nextCheckNameTemp = StringUtil.isEmpty(nextCheckNameTemp) ? nextCheckerName
								: nextCheckNameTemp + "," + nextCheckerName;
						if (StringUtil.isNotEmpty(nextCheckerTel)) {
							request.setAttribute("nextCheckerName", nextCheckerName);
							request.setAttribute("nextCheckerTel", nextCheckerTel);
							// 发送提醒消息
							try {
								carWbManager.sendMsg(request, carWb);
							} catch (Exception e) {
								e.printStackTrace();
								KhntException kh = new KhntException(e);
								log.error(kh.getMessage());
							}
						}
					}
				}
			}
			// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
			logService.setLogs(carWb.getId(),
					"2".equals(areaFlag) ? "车队负责人审核"
							: "3".equals(areaFlag) ? "办公室责人审核、盖章" : "",
					"2".equals(areaFlag) ? "车队负责人审核通过，并提交至" + nextCheckNameTemp + "审核，操作结果：办公室负责人待审核"
							: "3".equals(areaFlag) ? "办公室责人审核、盖章完成，操作结果：已签字盖章" : "",
					curUser != null ? curUser.getId() : "未获取到操作用户编号",
					curUser != null ? curUser.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
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
	 * @throws Exception
	 */
	@RequestMapping("checkNoPass")
	@ResponseBody
	public HashMap<String, Object> checkNoPass(HttpServletRequest request, String areaFlag, String id, String flowId,
			String activityId, String typeCode, String processId) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		HashMap<String, Object> hashMap = carWbManager.checkNoPass(request, areaFlag, id, flowId, activityId, typeCode,
				processId);
		if ((boolean) hashMap.get("success")) {
			CarWb carWb = (CarWb) hashMap.get("carWb");
			CarInfo carInfo = carInfoManager.get(carWb.getFkCarId());
			carInfo.setRepairType(null);// 恢复维保状态为空
			carInfoManager.save(carInfo);
			// 发送消息提醒
			request.setAttribute("checkerName", curUser.getName());
			try {
				carWbManager.sendMsg(request, carWb);
			} catch (Exception e) {
				e.printStackTrace();
				KhntException kh = new KhntException(e);
				log.error(kh.getMessage());
			}
			// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
			logService.setLogs(carWb.getId(),
					"2".equals(areaFlag) ? "车队负责人审核"
							: "3".equals(areaFlag) ? "办公室责人审核、盖章" : "",
					"2".equals(areaFlag) ? "车队负责人审核，操作结果：不通过" : "3".equals(areaFlag) ? "办公室责人审核，操作结果：不通过" : "",
					curUser != null ? curUser.getId() : "未获取到操作用户编号",
					curUser != null ? curUser.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
		}
		return hashMap;
	}

	/**
	 * 根据业务id获取任务参数
	 * 
	 * @param request
	 * @param areaFlag
	 * @param id
	 * @return
	 */
	@RequestMapping("getWorktaskParam")
	@ResponseBody
	public HashMap<String, Object> getWorktaskParam(HttpServletRequest request, String areaFlag, String id) {
		return carWbManager.getWorktaskParam(request, id);
	}

	/**
	 * 获取流转过程
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request) throws Exception {
		Map<String, Object> map = carWbManager.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/common/flow_card", map);
		return mav;
	}

	/**
	 * 标记车辆维保已完成
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "done")
	@ResponseBody
	public HashMap<String, Object> done(HttpServletRequest request, String id) throws Exception {
		return carWbManager.done(request, id);
	}

	/**
	 * 微信端获取我的申请事项信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryMyApplyList")
	@ResponseBody
	public HashMap<String, Object> queryMyApplyList(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String dataStatus = request.getParameter("dataStatus");
		return carWbManager.queryMyApplyList(curUser.getId(), dataStatus);
	}

	/**
	 * 微信端获取待办事项信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryPendingList")
	@ResponseBody
	public HashMap<String, Object> queryPendingList(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String dataStatus = request.getParameter("dataStatus");
		return carWbManager.queryPendingList(curUser.getId(), dataStatus);
	}

	/**
	 * 微信端获取已办理事项信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryDealedList")
	@ResponseBody
	public HashMap<String, Object> queryDealedList(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String dataStatus = request.getParameter("dataStatus");
		return carWbManager.queryDealedList(curUser.getId(), dataStatus);
	}

	/**
	 * 获取维保申请打印信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPrintInfo")
	@ResponseBody
	public HashMap<String, Object> getPrintInfo(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		CarWb carWb = carWbManager.get(id);
		HashMap<String, Object> hashMap = this.getSignPictures(request, carWb.getFleetDealId(), carWb.getOfficeDealId(),
				carWb.getSealId());
		hashMap.put("data", carWb);
		return hashMap;
	}
}