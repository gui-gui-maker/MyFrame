package com.lsts.report.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.ReportCancel;
import com.lsts.report.service.ReportCancelService;

/**
 * 报告纠正控制器
 * 
 * @ClassName ReportCancelAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-07-10 12:25:00
 */
@Controller
@RequestMapping("report/cancel")
public class ReportCancelAction extends
		SpringSupportAction<ReportCancel, ReportCancelService> {

	@Autowired
	private ReportCancelService reportCancelService;

	/**
	 * 保存
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,
			ReportCancel reportCancel) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			if(reportCancel.getError_from().startsWith("4,")){	// 不符合来源（4：外部输入）
				reportCancel.setError_from(reportCancel.getError_from().split(",")[1]);	
			}else{
				reportCancel.setError_from(reportCancel.getError_from().split(",")[0]);	
			}
			reportCancel.setApply_user_id(curUser.getId());		// 责任人id
			reportCancel.setApply_user_name(curUser.getName());	// 责任人姓名
			reportCancel.setApply_date(new Date());				// 登记纠正日期
			reportCancel.setApply_dep_id(curUser.getDepartment().getId());	// 责任部门id
			reportCancel.setApply_dep_name(curUser.getDepartment().getOrgName());	// 责任部门名称
			String curDate = DateUtil.getDateTime("yyyyMMdd", new Date());
			if (StringUtil.isEmpty(reportCancel.getId())) {
				reportCancel.setSn(curDate+reportCancelService.queryNextSn(curDate));	// 纠正编号
			}else{
				if(StringUtil.isEmpty(reportCancel.getSn())){
					reportCancel.setSn(curDate+reportCancelService.queryNextSn(curDate));	// 纠正编号
				}
			}
			reportCancel.setData_status("01");	// 数据状态（01：已申请纠正，等待部门负责人确认）
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "报告纠正申请保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		return super.detail(request, id);
	}

	
	
	/**
	 * 责任部门负责人审核纠正申请
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "dep_head_check")
	@ResponseBody
	public HashMap<String, Object> dep_head_check(HttpServletRequest request, String id, String check) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setApply_dep_head_id(curUser.getId());		// 责任部门负责人ID
			reportCancel.setApply_dep_head_name(curUser.getName());	// 责任部门负责人姓名
			reportCancel.setApply_head_check_date(new Date());		// 责任部门负责人审核时间
			if ("0".equals(check)) {	// 0：责任部门负责人审核通过
				reportCancel.setData_status("03");	// 数据状态（03：责任部门负责人已审核通过，等待质保工程师审核）
			}else {
				reportCancel.setData_status("02");	// 数据状态（02：责任部门负责人审核未通过，请修改纠正申请或重新提交纠正申请）
			}
			reportCancel.setApply_head_check_result(check);	// 责任部门负责人审核结果
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "责任部门负责人审核失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 质保工程师审核纠正申请
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "qua_dep_check")
	@ResponseBody
	public HashMap<String, Object> qua_dep_check(HttpServletRequest request, String id, String check) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setQua_dep_check_id(curUser.getId());		// 质保工程师ID
			reportCancel.setQua_dep_check_name(curUser.getName());	// 质保工程师姓名
			reportCancel.setQua_dep_check_date(new Date());			// 质保工程师审核时间
			if ("0".equals(check)) {	// 0：质保工程师审核通过
				reportCancel.setData_status("04");	// 数据状态（03：质保工程师已审核通过，等待检验员纠正）
			}else {
				reportCancel.setData_status("05");	// 数据状态（02：质保工程师审核未通过，等待检验员修改或重新提交纠正申请）
			}
			reportCancel.setQua_dep_check_result(check);	// 质保工程师审核结果
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "质保工程师审核失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 责任人（检验员）纠正
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "app_deal")
	@ResponseBody
	public HashMap<String, Object> app_deal(HttpServletRequest request, String id, String new_report_sn, String deal_remark) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setNew_report_sn(new_report_sn);		// 新报告书编号
			reportCancel.setDeal_remark(deal_remark);			// 责任人备注
			reportCancel.setDeal_user_id(curUser.getId());		// 责任人ID
			reportCancel.setDeal_user_name(curUser.getName());	// 责任人姓名
			reportCancel.setDeal_date(new Date());				// 责任人纠正时间
			
			reportCancel.setData_status("06");	// 数据状态（06：检验员已纠正，等待业务服务部确认）	
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "责任人纠正失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 责任部门负责人纠正完成情况
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "app_dep_finish")
	@ResponseBody
	public HashMap<String, Object> app_dep_finish(HttpServletRequest request, String id, String dep_head_remark) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setDep_head_id(curUser.getId());		// 部门负责人ID
			reportCancel.setDep_head_name(curUser.getName());	// 部门负责人姓名
			reportCancel.setDep_head_check_date(new Date());	// 部门负责人审核时间
			reportCancel.setDep_head_remark(dep_head_remark);	// 部门负责人备注完成情况
			reportCancel.setData_status("09");	// 数据状态（09：纠正已完成，等待质量部确认纠正结果）	
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "责任部门负责人纠正完成情况失败，请重试！");
		}
		return wrapper;
	}

	/**
	 * 业务服务部确认纠正措施完成情况
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "confirm")
	@ResponseBody
	public HashMap<String, Object> confirm(HttpServletRequest request, String id, String confirm_result) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setConfirm_user_id(curUser.getId());		// 业务服务部经办人ID
			reportCancel.setConfirm_user_name(curUser.getName());	// 业务服务部经办人姓名
			reportCancel.setConfirm_date(new Date());				// 业务服务部确认时间
			reportCancel.setConfirm_result(confirm_result);			// 业务服务部确认结果
			reportCancel.setData_status("07");	// 数据状态（07：业务服务部已确认，等待责任部门负责人确认完成情况）	
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "业务服务部确认纠正措施完成情况失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 质量监督管理部负责人纠正结果
	 * 
	 * @param request
	 * @param reportCancel
	 * @throws Exception
	 */
	@RequestMapping(value = "qua_finish")
	@ResponseBody
	public HashMap<String, Object> qua_finish(HttpServletRequest request, String id, String qua_end_result) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportCancel reportCancel = reportCancelService.get(id);
			reportCancel.setQua_head_id(curUser.getId());		// 业务服务部经办人ID
			reportCancel.setQua_head_name(curUser.getName());	// 业务服务部经办人姓名
			reportCancel.setQua_head_check_date(new Date());	// 业务服务部确认时间
			reportCancel.setQua_end_result(qua_end_result);		// 质量部纠正结果
			reportCancel.setData_status("08");	// 数据状态（08：质量监督管理部负责人已审核，纠正完成）	
			reportCancelService.save(reportCancel);
			wrapper.put("success", true);
			wrapper.put("data", reportCancel);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "质量监督管理部负责人保存纠正结果失败，请重试！");
		}
		return wrapper;
	}
}
