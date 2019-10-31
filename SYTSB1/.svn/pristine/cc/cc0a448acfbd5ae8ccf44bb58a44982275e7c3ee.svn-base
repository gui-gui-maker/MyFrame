package com.lsts.report.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.Report;
import com.lsts.report.bean.ReportError;
import com.lsts.report.bean.ReportErrorInfo;
import com.lsts.report.bean.ReportErrorRecord;
import com.lsts.report.bean.ReportErrorRecordDTO;
import com.lsts.report.bean.ReportErrorRecordInfo;
import com.lsts.report.dao.ReportErrorDao;
import com.lsts.report.service.ReportErrorInfoService;
import com.lsts.report.service.ReportErrorRecordInfoService;
import com.lsts.report.service.ReportErrorRecordService;
import com.lsts.report.service.ReportErrorService;
import com.lsts.report.service.ReportService;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.service.InspectionPayInfoService;

/**
 * 检验报告/证书不符合纠正流转表控制器
 * 
 * @ClassName ReportErrorRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-23 14:16:00
 */
@Controller
@RequestMapping("report/error/record")
public class ReportErrorRecordAction extends
		SpringSupportAction<ReportErrorRecord, ReportErrorRecordService> {

	@Autowired
	private ReportErrorRecordService reportErrorRecordService;
	@Autowired
	private ReportErrorRecordInfoService reportErrorRecordInfoService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private ReportErrorService reportErrorService;
	@Autowired
	private ReportErrorInfoService reportErrorInfoService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private InspectionPayInfoService inspectionPayInfoService;
	@Autowired
	private ReportErrorDao reportErrorDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeesService employeesService;
	/**
	 * 保存
	 * 
	 * @param request
	 * @param reportErrorRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			@RequestBody
			ReportErrorRecord reportErrorRecord) throws Exception {
		try {
			return reportErrorRecordService.saveBasic(
					reportErrorRecord, request);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	
	/**
	 * 保存（需要返写不符合报告主表）
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic2")
	@ResponseBody
	public HashMap<String, Object> saveBasic2(HttpServletRequest request,
			@RequestBody
			ReportErrorRecord reportErrorRecord) throws Exception {
		try {
			return reportErrorRecordService.saveBasic2(
					reportErrorRecord, request);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	
	// 获取检验报告/证书不符合纠正流转表信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return reportErrorRecordService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取检验报告/证书不符合纠正流转表信息失败！");
		}
	}
	
	// 获取不符合报告详情，生成不符合纠正流转表
	@RequestMapping(value = "getReportErrorDetail")
	@ResponseBody
	public HashMap<String, Object> getReportErrorDetail(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			ReportError reportError = reportErrorService.get(id);
			Collection<ReportErrorInfo> list = reportError.getReportErrorInfo();
			if(list.isEmpty()){
				list = reportErrorInfoService.getInfos(id);
				reportError.setReportErrorInfo(list);
			}
			
			ReportErrorRecord reportErrorRecord = new ReportErrorRecord();
			BeanUtils.copyProperties(reportErrorRecord, reportError);
			reportErrorRecord.setId(null);
			reportErrorRecord.setReport_error_sn(Constant.REPORT_ERROR_FILENAME+"-"+reportError.getSn());
			reportErrorRecord.setFind_date(new Date());
			List<ReportErrorRecordInfo> list2 = new ArrayList<ReportErrorRecordInfo>();
			for(ReportErrorInfo reportErrorInfo : list){
				ReportErrorRecordInfo reportErrorRecordInfo = new ReportErrorRecordInfo();
				BeanUtils.copyProperties(reportErrorRecordInfo, reportErrorInfo);
				reportErrorRecordInfo.setId(null);
				// 获取业务信息，查询报告状态
				if(StringUtil.isNotEmpty(reportErrorInfo.getInfo_id())){
					InspectionInfo inspectionInfo = inspectionInfoService.get(reportErrorInfo.getInfo_id());
					String flow_note_name = inspectionInfo.getFlow_note_name();
					if("打印报告".equals(flow_note_name)){
						reportErrorRecordInfo.setReport_status("1");	// 1：已封存
					}else if("报告领取".equals(flow_note_name) || "报告归档".equals(flow_note_name)){
						reportErrorRecordInfo.setReport_status("2");	// 2：已打印
					}
				}
				list2.add(reportErrorRecordInfo);
			}
			reportErrorRecord.setReportErrorRecordInfo(list2);
			
			map.put("data", reportErrorRecord);
			map.put("reportErrorRecordInfo", list2);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取检验报告/证书不符合纠正流转表信息失败！");
		}
	}
	
	/**
	 * 业务服务部确认纠正措施完成情况
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "confirm")
	@ResponseBody
	public HashMap<String, Object> confirm(HttpServletRequest request, String id, String confirm_result)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			ReportErrorRecord reportErrorRecord = reportErrorRecordService.get(id);
			// 1、查询检验报告/证书不符合纠正流转明细列表
			List<ReportErrorRecordInfo> list = reportErrorRecordInfoService.getInfos(id);
			if (!list.isEmpty()) {
				for (ReportErrorRecordInfo reportErrorRecordInfo : list) {
					// 检验员新报告书编号未填写时，业务服务部不能进行纠正确认
					if(reportErrorRecordInfo.getNew_report_sn() == null){
						wrapper.put("success", false);
						wrapper.put("msg", "亲，请等待检验员补充新报告书编号后再进行确认操作哦！");
						return wrapper;
					}else{
						if (StringUtil.isEmpty(reportErrorRecordInfo.getNew_report_sn().trim())) {
							wrapper.put("success", false);
							wrapper.put("msg", "亲，请等待检验员补充新报告书编号后再进行确认操作哦！");
							return wrapper;
						} else {
							List<InspectionInfo> list2 = inspectionInfoService
									.queryInspectionInfo(reportErrorRecordInfo.getNew_report_sn().trim());
							if (list2.size() == 0) {
								// wrapper.put("success", false);
								// wrapper.put("msg",
								// "新报告书编号（"+reportErrorRecordInfo.getNew_report_sn()+"）不存在，请核查！");
								// return wrapper;
							} else {
								if (list2.size() != 1) {
									wrapper.put("success", false);
									wrapper.put("msg", "新报告书编号（" + reportErrorRecordInfo.getNew_report_sn().trim()
											+ "）存在重号的报告，请联系系统管理员！");
									return wrapper;
								}
							}
						}
					}
					

					if (StringUtil.isNotEmpty(reportErrorRecordInfo.getReport_sn().trim())) {
						List<InspectionInfo> list1 = inspectionInfoService
								.queryInspectionInfo(reportErrorRecordInfo.getReport_sn().trim());
						if (list1.size() == 0) {
							// wrapper.put("success", false);
							// wrapper.put("msg",
							// "报告书编号（"+reportErrorRecordInfo.getReport_sn()+"）不存在，请核查！");
							// return wrapper;
						} else {
							if (list1.size() != 1) {
								wrapper.put("success", false);
								wrapper.put("msg",
										"报告书编号（" + reportErrorRecordInfo.getReport_sn().trim() + "）存在重号的报告，请联系系统管理员！");
								return wrapper;
							}
						}

						if (StringUtil.isNotEmpty(reportErrorRecordInfo.getNew_report_sn().trim())) {
							// 新报报告编号不一致时，才作废老报告
							if (!reportErrorRecordInfo.getReport_sn()
									.equals(reportErrorRecordInfo.getNew_report_sn().trim())) {
								// 2、作废纠错报告
								for (InspectionInfo inspectionInfo : list1) {
									if (!"99".equals(inspectionInfo.getData_status())) {
										inspectionInfo.setData_status("99"); // 99：已作废
										inspectionInfo.setApprresult("9"); // 9：已纠正作废
										inspectionInfo.setIs_cur_error("0"); // 当前正在纠错中（0：否 1：是）
										if(StringUtil.isNotEmpty(inspectionInfo.getIs_mobile())){
											if("1".equals(inspectionInfo.getIs_mobile())){
												inspectionInfo.setYsjl_data_status("99");	// 移动设备出具报告，报告作废时，原始记录也进行作废处理
											}
										}
										inspectionInfoService.save(inspectionInfo); // 2、将原报告进行作废处理，修改数据状态为99，表示报告已作废
										// 3、原报告作废写入日志
										logService.setLogs(inspectionInfo.getId(), "报告作废", "业务服务部确认不符合纠正，报告作废",
												curUser.getId(), curUser.getName(), new Date(),
												request.getRemoteAddr());
									}
								}
							}else{
								// 2、更新老报告状态
								for (InspectionInfo inspectionInfo : list1) {
									if (!"99".equals(inspectionInfo.getData_status())) {										
										inspectionInfo.setIs_cur_error("0"); // 当前正在纠错中（0：否
																				// 1：是）
										inspectionInfoService.save(inspectionInfo); // 2、将原报告进行作废处理，修改数据状态为99，表示报告已作废
										// 3、原报告作废写入日志
										logService.setLogs(inspectionInfo.getId(), "报告纠错", "业务服务部确认不符合纠正措施",
												curUser.getId(), curUser.getName(), new Date(),
												request.getRemoteAddr());
									}
								}
							}

							// 新报报告编号不一致时，才返写新报告收费状态和收费记录
							if (!reportErrorRecordInfo.getReport_sn()
									.equals(reportErrorRecordInfo.getNew_report_sn().trim())) {
								// 3、老报告已收费时，返写新报告收费状态和收费记录
								if (StringUtil.isNotEmpty(reportErrorRecordInfo.getInfo_id())
										&& StringUtil.isNotEmpty(reportErrorRecordInfo.getNew_info_id())) {
									InspectionInfo oldInfo = inspectionInfoService
											.get(reportErrorRecordInfo.getInfo_id());
									InspectionInfo newInfo = inspectionInfoService
											.get(reportErrorRecordInfo.getNew_info_id());
									// 老报告已经收费时，才更新新报告的收费状态和收费记录
									if ("2".equals(oldInfo.getFee_status())) {
										if (newInfo.getAdvance_fees() != null && oldInfo.getAdvance_fees() != null) {
											if (newInfo.getAdvance_fees() <= oldInfo.getAdvance_fees()) {
												// 新报告金额小于等于纠错报告金额时，修改新报告收费状态为已收费并返写收费记录
												List<InspectionPayInfo> payInfoLists = inspectionPayInfoService
														.queryList(reportErrorRecordInfo.getInfo_id());
												for (InspectionPayInfo info : payInfoLists) {
													String fk_info_id = info.getFk_inspection_info_id();
													if (fk_info_id.contains(reportErrorRecordInfo.getInfo_id())) {
														info.setOld_info_id(fk_info_id);
														fk_info_id = fk_info_id.replace(
																reportErrorRecordInfo.getInfo_id(),
																reportErrorRecordInfo.getNew_info_id());
														info.setFk_inspection_info_id(fk_info_id);
														inspectionPayInfoService.save(info); // 3.1、替换已收费业务中作废报告id为新报告id

														// 3.2、返写发票号、缴费日期至新报告
														InspectionInfo info2 = inspectionInfoService
																.get(reportErrorRecordInfo.getNew_info_id());
														info2.setInvoice_no(info.getInvoice_no()); // 发票号
														info2.setInvoice_date(info.getPay_date()); // 缴费日期
														// 缴费方式 1 现金缴费 2 转账 3
														// 免收费 4 现金及转账 5 pos 6
														// 现金及pos 7 转账及pos
														info2.setFee_type(info.getPay_type());
														// 收费状态（0 默认 1 待收费 2 已收费
														// 3 借报告 4 借发票 5
														// 借报告和发票）
														info2.setFee_status("2");
														inspectionInfoService.save(info2);
													}
												}
											} else {
												// 新报告金额大于纠错报告金额时，需补差价，只更新差价至实收金额，不进行收费状态更新，也不返写收费记录
												double diff_price = newInfo.getAdvance_fees()
														- oldInfo.getAdvance_fees();
												newInfo.setReceivable(diff_price);
												newInfo.setAdvance_remark("纠错报告差价" + diff_price + "元");
												inspectionInfoService.save(newInfo);
											}
										}
									}
								}
							}
						}
						
						// 纠错报告如报告模板为老版本，自动启用老版本模板
						if(StringUtil.isNotEmpty(reportErrorRecordInfo.getReport_id())){
							Report report = reportService.get(reportErrorRecordInfo.getReport_id());
							if(report != null){
								// 报告版本（0：新版本 1：老版本）
								if ("1".equals(report.getReport_version())) {
									report.setFlag("1");	// 报告模板状态（1：启用）
									report.setRemark(Constant.REPORT_ERROR_REMARK);	// 备注
									report.setMdy_user_id(curUser.getId()); 	// 最后修改人ID
									report.setMdy_user_name(curUser.getName()); // 最后修改人姓名
									report.setMdy_date(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern,
											DateUtil.getCurrentDateTime())); 	// 最后修改时间
									reportService.save(report);
								}
							}
						}
					}
				}
			}

			reportErrorRecord.setConfirm_user_id(curUser.getId()); // 业务服务部经办人ID
			reportErrorRecord.setConfirm_user_name(curUser.getName()); // 业务服务部经办人姓名
			reportErrorRecord.setConfirm_date(new Date()); // 业务服务部确认时间
			reportErrorRecord.setConfirm_result(confirm_result); // 业务服务部确认结果
			reportErrorRecord.setData_status("02"); // 数据状态（02：业务服务部已确认，等待责任部门负责人确认完成情况）
			reportErrorRecordService.save(reportErrorRecord); // 4、业务服务部确认不符合纠正流转表

			wrapper.put("success", true);
			wrapper.put("data", reportErrorRecord);

			// 5、写入日志
			logService.setLogs(id, "确认不符合纠正措施", "业务服务部确认检验报告/证书不符合纠正措施", curUser.getId(), curUser.getName(), new Date(),
					request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "业务服务部确认纠正措施失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 责任部门负责人确认纠正完成情况
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "app_dep_finish")
	@ResponseBody
	public HashMap<String, Object> app_dep_finish(HttpServletRequest request, String id, String dep_head_remark) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			// 1、责任部门负责人确认纠正完成情况
			ReportErrorRecord reportErrorRecord = reportErrorRecordService.get(id);
			reportErrorRecord.setDep_head_id(curUser.getId());		// 部门负责人ID
			reportErrorRecord.setDep_head_name(curUser.getName());	// 部门负责人姓名
			reportErrorRecord.setDep_head_check_date(new Date());	// 部门负责人审核时间
			reportErrorRecord.setDep_head_remark(dep_head_remark);	// 部门负责人备注完成情况
			reportErrorRecord.setData_status("03");	// 数据状态（03：责任部门负责人已确认，纠正已完成）	
			reportErrorRecordService.save(reportErrorRecord);	
			
			// 2、存在质量部不符合报告时，同步确认不符合报告
			if(reportErrorRecord.getReport_error_sn().length()>13){
				String report_error_sn = reportErrorRecord.getReport_error_sn().substring(13);
				ReportError reportError = reportErrorService.getInfosBySn(report_error_sn, reportErrorRecord.getReport_sn());
				if(reportError != null){
					reportError.setDep_head_id(curUser.getId());		// 部门负责人ID
					reportError.setDep_head_name(curUser.getName());	// 部门负责人姓名
					reportError.setDep_head_check_date(new Date());		// 部门负责人确认时间
					reportError.setDep_head_remark(dep_head_remark);	// 部门负责人备注完成情况
					reportError.setData_status("05");					// 数据状态（05：责任部门负责人已确认，质量部待确认）	
					reportErrorService.save(reportError);				// 2.1、责任部门负责人确认纠正完成情况
					// 2.2、写入日志
					logService.setLogs(reportError.getId(), "责任部门负责人确认纠正", "责任部门负责人确认纠正完成情况",
							curUser.getId(), curUser.getName(), new Date(), request
									.getRemoteAddr());
				}
			}
			
			// 3、写入日志
			logService.setLogs(id, "确认不符合纠正完成情况", "责任部门负责人确认检验报告/证书不符合纠正完成情况",
					curUser.getId(), curUser.getName(), new Date(), request
							.getRemoteAddr());
			
			wrapper.put("success", true);
			wrapper.put("data", reportErrorRecord);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "责任部门负责人确认纠正完成情况失败，请重试！");
		}
		return wrapper;
	}
	
	// 业务服务部外借报告
    @RequestMapping(value = "borrowReport")
    @ResponseBody
    public HashMap<String, Object> borrowReport(HttpServletRequest request, String borrow_user_id, String borrow_desc) throws Exception {	
        return reportErrorRecordService.borrow(request, borrow_user_id, borrow_desc);
    }
	
	// 获取报告信息
	@RequestMapping(value = "queryReportInfos")
	@ResponseBody
	public HashMap<String, Object> getReportInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", reportErrorRecordService.queryReportInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取报告信息失败！");
		}
		return map;
	}
	
	// 根据不符合报告ID查询不符合纠正流转表
	@RequestMapping(value = "queryRecordID")
	@ResponseBody
	public HashMap<String, Object> queryRecordID(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String report_error_id = request.getParameter("report_error_id");
		try {
			map.put("data", reportErrorRecordService.queryRecordID(report_error_id));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("查询不符合纠正流转表失败！");
		}
		return map;
	}
	
	// 删除检验报告/证书不符合纠正流转表信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportErrorRecordService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = reportErrorRecordService
				.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/report/flow_card", map);
		return mav;
	}
	
	// 打印报告前，验证报告是否在纠错流程中未确认
	@RequestMapping(value = "queryReportErrors")
	@ResponseBody
	public HashMap<String, Object> queryReportErrors(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		String msg = "";
		try {
			String[] info_ids = ids.split(",");
			for (int i = 0; i < info_ids.length; i++) {
				List<ReportErrorRecordDTO> list = reportErrorRecordService.queryReportErrors(info_ids[i]);
				if(!list.isEmpty()){
					for(ReportErrorRecordDTO reportErrorRecordDTO : list){
						if("01".equals(reportErrorRecordDTO.getData_status())){
							msg += "亲，您所选的报告（"+reportErrorRecordDTO.getReport_sn()+"）正在不符合纠正流转中，当前还未确认，暂时不能打印哦！"; 
						}
					}
				}
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取不符合纠正信息失败！");
		}
		if (StringUtil.isNotEmpty(msg)) {
			return JsonWrapper.failureWrapperMsg(msg);
		}
		return map;
	}
	
	/**
	 * 发微信和短信
	 * 
	 * @param request
	 * @param id -- 不符合报告业务id
	 * @throws Exception
	 * 
	 * @author GaoYa
	 * @date 2015-12-17 17:50:00
	 */
	@RequestMapping(value = "sendMsgs")
	@ResponseBody
	public HashMap<String, Object> sendMsgs(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String[] id_list = ids.split(",");
			for (int i = 0; i < id_list.length; i++) {
				ReportErrorRecord reportErrorRecord = reportErrorRecordService.get(id_list[i]);
				List<ReportErrorRecordInfo> infoList = reportErrorRecordInfoService.getInfos(id_list[i]);
				// 申请日期
				String apply_date = "";
				// 报告份数
				int report_count = 0;
				// 使用单位
				String report_com_name = "";
				if (!infoList.isEmpty()) {
					report_count = infoList.size();
					for (ReportErrorRecordInfo recordInfo : infoList) {
						if (StringUtil.isNotEmpty(recordInfo.getInfo_id())) {
							if(StringUtil.isEmpty(apply_date)){
								apply_date = DateUtil.format(recordInfo.getMdy_date(), Constant.defaultDatePattern);
							}
							
							InspectionInfo info = inspectionInfoService.get(recordInfo.getInfo_id());
							if (report_com_name.length() > 0) {
								if (!report_com_name.contains(info.getReport_com_name())) {
									report_com_name += "," + info.getReport_com_name();
								}
							} else {
								report_com_name += info.getReport_com_name();
							}
						}
					}
				}
				// Employee employee =
				// employeesService.get(reportError.getError_user_id());
				// 获取发送目标号码
				// String destNumber = employee.getMobileTel();
				String[] userId = reportErrorRecord.getDeal_user_id().split(",");//经确认消息发送给申请人（2018-07-25张芸）
				for (String userIds : userId) { // 循环人员ID
					// 获取电话
					Employee employee = employeesService.get(userIds);
					String destNumber = employee.getMobileTel();
					/*String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
							.matcher(mobile_phone.toString().substring(1, mobile_phone.toString().length() - 1)).replaceAll("'$1'");*/

					// 获取发送内容
					String content = Constant.getReportErrorNoticeContent2(apply_date, report_count, report_com_name);
					// 发送微信
					messageService.sendWxMsg(request, id_list[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
							content, destNumber);
					// 发送短信
					messageService.sendMoMsg(request, id_list[i], content, destNumber);
				}
			}
			return JsonWrapper.successWrapper("消息发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("消息发送失败！");
		}
	}
}
