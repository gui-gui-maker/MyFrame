package com.scts.payment.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.log.service.SysLogService;
import com.scts.payment.bean.InspectionChangeMoney;
import com.scts.payment.bean.InspectionChangeMoneyDTO;
import com.scts.payment.service.InspectionChangeMoneyService;

/**
 * 金额修改审批流程明细表管理控制器
 * 
 * @ClassName InspectionChangeMoneyAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-16 下午03:49:00
 */
@Controller
@RequestMapping("inspectionChangeMoney")
public class InspectionChangeMoneyAction extends
		SpringSupportAction<InspectionChangeMoney, InspectionChangeMoneyService> {
	@Autowired
	private InspectionChangeMoneyService inspectionChangeMoneyService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SysLogService logService;

	/**
	 * 保存
	 * 
	 * @param request
	 * @param inspectionChangeMoney
	 * @throws Exception
	 * @author GaoYa
	 * @date 2016-11-16 下午03:56:00
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request, InspectionChangeMoney inspectionChangeMoney)
			throws Exception {
		try {
			inspectionChangeMoneyService.saveInfo(request, inspectionChangeMoney);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(inspectionChangeMoney);
	}

	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			inspectionChangeMoneyService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}

	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = inspectionChangeMoneyService.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/payment/money_flow_card", map);
		return mav;
	}

	/**
	 * 审核（财务部孙宇艺、雷兰）
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "check")
	@ResponseBody
	public HashMap<String, Object> check(@RequestBody InspectionChangeMoneyDTO entity, HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		try {
			// 审核结果（1：审核通过 2：审核不通过）
			String check_result = entity.getData_status(); 
			String check_remark = entity.getCheck_remark(); // 审核结果说明（审核不通过时填写）
			if (StringUtil.isEmpty(check_remark)) {
				check_remark = "无";
			}
			
			// 发送信息类型（1：发送微信 2：发送短信 3：发送微信和短信）
			String send_msg_type = entity.getSend_msg_type(); 

			String check_result_txt = "";
			String[] ids = entity.getId().split(",");
			for (int i = 0; i < ids.length; i++) {
				// 1、保存审核基本信息
				InspectionChangeMoney inspectionChangeMoney = inspectionChangeMoneyService.get(ids[i]);
				inspectionChangeMoney.setData_status(entity.getData_status());
				inspectionChangeMoney.setCheck_remark(check_remark);
				inspectionChangeMoney.setCheck_emp_id(emp.getId()); // 审核人ID
				inspectionChangeMoney.setCheck_emp_name(emp.getName()); // 审核人姓名
				inspectionChangeMoney.setCheck_date(
						DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 审核时间

				// 审核结果（1：审核通过 2：审核不通过）
				if ("1".equals(check_result)) {
					check_result_txt = "审核通过";
					inspectionChangeMoney.setData_status("1"); // 数据状态（1：审核通过）

					// 2、审核通过时，将修改的新金额返写到报告业务信息表中
					InspectionInfo inspectionInfo = inspectionInfoService
							.get(inspectionChangeMoney.getFk_inspection_info_id());
					inspectionInfo.setReceivable(inspectionChangeMoney.getChange_money());
					inspectionInfo.setAdvance_remark(inspectionChangeMoney.getRemarks());
					inspectionInfoService.save(inspectionInfo);
				} else {
					check_result_txt = "审核不通过";
					inspectionChangeMoney.setData_status("2"); // 数据状态（2：审核不通过，流程结束）
				}
				inspectionChangeMoneyService.save(inspectionChangeMoney);

				// 3、记录日志
				// 3.1、记录审核日志
				logService.setLogs(ids[i], "审核金额修改", check_result_txt + "，备注：" + check_remark, curUser.getId(),
						curUser.getName(), new Date(), request.getRemoteAddr());
				// 3.2、记录报告业务日志
				logService.setLogs(inspectionChangeMoney.getFk_inspection_info_id(), "审核金额修改",
						check_result_txt + "，备注：" + check_remark, curUser.getId(), curUser.getName(), new Date(),
						request.getRemoteAddr());

				// 4、审核后发送提醒消息给申请人
				// 4.1、记录发送消息提醒日志
				logService.setLogs(ids[i], "开始发送审核情况提醒申请人", "发送给" + inspectionChangeMoney.getCreate_emp_name(), curUser.getId(),
						curUser.getName(), new Date(), request.getRemoteAddr());
				// 4.2、发送消息提醒
				Employee employee = employeesService.get(inspectionChangeMoney.getCheck_emp_id());
				String destNumber = employee.getMobileTel();
				String content = Constant.getChangeMoneyCheckNoticeContent(inspectionChangeMoney.getReport_com_name(),
						inspectionChangeMoney.getReport_sn(), emp.getName(), check_result_txt);

				// 发送信息类型（1：微信 2：短信 3：微信和短信 4：不发送）
				if (StringUtil.isEmpty(send_msg_type)) {
					send_msg_type = "1"; // 默认发送微信
				}
				if ("1".equals(send_msg_type)) {
					// 发送微信
					messageService.sendWxMsg(request, ids[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
							content, destNumber);
				} else if ("2".equals(send_msg_type)) {
					// 发送短信
					messageService.sendMoMsg(request, ids[i], content, destNumber);
				} else if ("3".equals(send_msg_type)) {
					// 发送微信
					messageService.sendWxMsg(request, ids[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
							content, destNumber);
					// 发送短信
					messageService.sendMoMsg(request, ids[i], content, destNumber);
				}
			}
			wrapper.put("success", true);
			wrapper.put("data", entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "审核失败，请重试！");
		}
		return wrapper;
	}
}
