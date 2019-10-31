package com.lsts.inspection.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.ImageTool;
import com.lsts.advicenote.service.MessageService;
import com.lsts.approve.service.CertificateByService;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.ReportItemValue;
import com.lsts.inspection.bean.ReportPageCheckInfo;
import com.lsts.inspection.bean.SysAutoIssueLog;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.lsts.inspection.service.InspectionZZJDService;
import com.lsts.inspection.service.ReportItemValueService;
import com.lsts.inspection.service.ReportPageCheckInfoService;
import com.lsts.inspection.service.SysAutoIssueLogService;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.Report;
import com.lsts.report.bean.ReportItem;
import com.lsts.report.service.ReportItemService;
import com.lsts.report.service.ReportService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 检验报告业务控制器
 * 
 * @ClassName InspectionReportAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-25 上午10:35:00
 */
@Controller
@RequestMapping("report/batch")
public class ReportBatchAction extends
		SpringSupportAction<InspectionInfo, InspectionInfoService> {

	@Autowired
	private InspectionInfoService infoService;
	@Autowired
	private ReportItemValueService reportItemValueService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportItemService reportItemService;
	@Autowired
	private InspectionService inspectionService;
	@Autowired
	private InspectionZZJDInfoService inspectionZZJDInfoService;
	@Autowired
	private InspectionZZJDService inspectionZZJDService;
	@Autowired
	private ImageTool imageTool;
	@Autowired
	private	ReportPageCheckInfoService reportPageCheckInfoService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private CertificateByService certificateByService;
	@Autowired
	private InspectionCommonService inspectionCommonService;
	@Autowired
	private SysAutoIssueLogService sysAutoIssueLogService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private MessageService messageService;

	/**
	 * 审核或签发报告时show report list
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showReports")
	public String showReportList(HttpServletRequest request, String ids)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String userId = user.getSysUser().getId();
		List<Map<String, Object>> mapList = infoService.queryInfo(ids, userId, null);
		request.getSession().setAttribute("mapList", mapList);
		return "app/flow/report/report_batch_left";
	}
	
	/**
	 * 审核或签发报告时show report list
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showReportPages")
	public String showReportPageList(HttpServletRequest request, String ids)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String userId = user.getSysUser().getId();
		List<Map<String, Object>> mapList = infoService.queryPageInfo(ids, userId);
		request.setAttribute("mapList", mapList);
		return "app/flow/report/page/report_batch_left";
	}

	/**
	 * 审核或签发报告时show report list（制造监督检验）
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "zzjd_showReports")
	public String showZZJDReportList(HttpServletRequest request, String ids)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String userId = user.getSysUser().getId();
		List<Map<String, Object>> mapList = inspectionZZJDService
				.queryZZJDInfo(ids, userId);
		request.getSession().setAttribute("mapList", mapList);
		return "app/flow/report/report_zzjd_batch_left";
	}

	/**
	 * 审核或签发报告时，show report content
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doCheck")
	public String doCheck(HttpServletRequest request, String id,
			String report_id) {
		try {
			getReportData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取打印数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/flow/report/report_batch";
	}
	
	/**
	 * 分页单独审核时，show report content
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doPageCheck")
	@SuppressWarnings("unchecked")
	public String doPageCheck(HttpServletRequest request, String id,
			String report_id) {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User)curUser.getSysUser();
			Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
			Org org = TS_Util.getCurOrg(curUser);
			// 获取报告分页单独审核信息
			List<String> pageIndexList = reportPageCheckInfoService
			.queryPageInfo(id, report_id, emp.getId()); // 报告检验项目信息
			request.setAttribute("pageIndexList", pageIndexList);
			getReportData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取打印数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/flow/report/page/report_batch";
	}

	/**
	 * 审核或签发报告 show report content （制造监督检验）
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doZZJDCheck")
	public String doZZJDCheck(HttpServletRequest request, String id,
			String report_id) {
		try {
			getReportZZJDData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取打印数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/flow/report/report_zzjd_batch";
	}

	/**
	 * 获取报告内容
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	private void getReportData(HttpServletRequest request, String id,
			String report_id) throws Exception {
		InspectionInfo inspectionInfo = infoService.get(id); // 业务信息
		Report report = reportService.get(report_id);
		List<ReportItemValue> reportItemValueList = reportItemValueService
				.queryByInspectionInfoId(id, report_id); // 报告检验项目信息
		List<ReportItem> reportItemList = reportItemService
				.queryByReportId(report_id); // 报告目录信息

		// 电子签名
		Map<String, Object> imgMap = new HashMap<String, Object>();
		// 检验人员电子签名
		imgMap.put("check_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getCheck_op_id()));
		// 审核人员电子签名
		imgMap.put("examine_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getExamine_id()));
		// 签发（批准）人员电子签名
		imgMap.put("issue_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getIssue_id()));
		// 编制（录入）人员电子签名
		imgMap.put("enter_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getEnter_op_id()));
		// 报告图片信息
		request.getSession().setAttribute("PICTURE",reportItemValueService.getPic(id));
		// 报告分页单独检验审核信息（含电子签名）
		request.setAttribute("PAGE_CHECK_IMAGES",reportPageCheckInfoService.getReportPageInfo(id, report_id));

		request.getSession().setAttribute("report_id", report_id); // 报告ID
		request.getSession().setAttribute("inspectionInfo", inspectionInfo); // 业务信息
		request.getSession().setAttribute("org_id", inspectionInfo.getCheck_unit_id()); // 报告检验部门ID
		request.getSession().setAttribute("report", report); // 报告信息
		request.getSession().setAttribute("reportItemValueList", // 检查项目信息
				reportItemValueList);
		request.getSession().setAttribute("reportItemList", // 报告目录信息
				reportItemList);
		request.setAttribute("IMAGES", // 人员电子签名
				imgMap);
	}

	/**
	 * 获取报告内容 （监督检验）
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getReportZZJDData(HttpServletRequest request, String id,
			String report_id) throws Exception {
		InspectionInfo inspectionInfo = infoService.get(id); // 1、获取业务信息
		InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
				.getByInfoId(id); // 2、获取制造监督检验明细数据

		if (StringUtil.isEmpty(report_id)) {
			report_id = inspectionInfo.getReport_type();
		}
		Report report = reportService.get(report_id);
		List<ReportItemValue> reportItemValueList = inspectionZZJDService
				.getReportItemValueInfo(id, report_id);	// 报告检验项目表信息

		List<ReportItem> reportItemList = reportItemService
				.queryByReportId(report_id); // 报告目录信息

		// 电子签名
		Map<String, Object> imgMap = new HashMap<String, Object>();
		// 检验人员电子签名
		imgMap.put("check_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getCheck_op_id()));
		// 审核人员电子签名
		imgMap.put("examine_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getExamine_id()));
		// 签发（批准）人员电子签名
		imgMap.put("issue_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getIssue_id()));
		// 编制（录入）人员电子签名
		imgMap.put("enter_op_img", imageTool.getEmployeeImg(inspectionInfo
				.getEnter_op_id()));
		
		Map<String,Object> zzjdInfoMap = new HashMap<String,Object>();
		zzjdInfoMap = inspectionService.beanToMap(inspectionZZJDInfo);
		
		
		request.getSession().setAttribute("report_id", report_id); // 报告ID
		request.getSession().setAttribute("inspectionInfo", inspectionInfo); // 业务信息
		request.getSession().setAttribute("INSPECTIONZZJDINFO",
				zzjdInfoMap); // 监督检验明细数据
		request.getSession().setAttribute("report", report); // 报告信息
		request.getSession().setAttribute("reportItemList", // 报告目录信息
				reportItemList);
		request.getSession().setAttribute("REPORTITEMVALUE", // 报告检验项目表信息
				reportItemValueList);
		request.setAttribute("IMAGES", // 人员电子签名
				imgMap);
	}

	/**
	 * 报告批量审核、签发
	 * 
	 * @param request
	 * @param dataStr --
	 *            参数json串
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "flow_batchCheck")
	@ResponseBody
	public Map<String, Object> flow_batchCheck(String dataStr,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		// 流程提交
		Map<String, Object> map = new HashMap<String, Object>();
		String type = request.getParameter("type"); // 操作类型（04：报告审核 05：报告签发）
		String op_name = "04".equals(type)?"审核日期":"签发日期";
		
		String device_sort_code = request.getParameter("device_sort_code");
		String pre_device_type = "";
		if(StringUtil.isNotEmpty(device_sort_code)){
			if("7310".equals(device_sort_code)){
				pre_device_type = "F";
			}else{
				pre_device_type = device_sort_code.substring(0, 1);
			}
		}
		
		// 是否是批量操作（0：否 1：是）
		String isBatch = request.getParameter("isBatch"); 
		// 报告审批流程（0：二级审核 1：一级审核）
		String check_flow = request.getParameter("check_flow");
		
		// 流程编号ID
		String acc_id = request.getParameter("acc_id");
		// 业务ID
		String ins_info_id = request.getParameter("infoId");
		String ids[] = ins_info_id.split(",");
		
		// 步骤号
		String flow_num = request.getParameter("flow_num");

		String revise_conclusion = dataMap.get("revise_conclusion").toString(); // 结论

		String op_time = dataMap.get("op_time").toString(); // 操作时间（审核/签发日期）
		Date cur_op_date = null;
		if(StringUtil.isNotEmpty(op_time)){
			cur_op_date = DateUtil.convertStringToDate("yyyy-MM-dd", op_time);
			Date cur_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime()) ;
			if(cur_op_date.after(cur_date)){
				return JsonWrapper.failureWrapperMsg("日期逻辑错误，"+op_name+"不能晚于当前日期，请重新选择！");
			}
		}else{
			return JsonWrapper.failureWrapperMsg(op_name+"不能为空，请选择！");
		}
		
		// 验证审核或签发日期是否存在逻辑错误
		// 审核日期不能早于编制日期，签发日期不能早于审核日期
		for (int i = 0; i < ids.length; i++) {
			// 业务信息
			InspectionInfo inspectionInfo = infoService.get(ids[i]);
			if ("04".equals(type)) { 
				// 验证电梯审核日期是否早于编制日期
				if("3".equals(pre_device_type)){
					Date enter_date = inspectionInfo.getEnter_time(); // 编制日期
					if (enter_date == null) {
						// 编制日期为空时，默认设置为检验日期+1天
						String advance_time = DateUtil.format(inspectionInfo.getAdvance_time(), "yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", advance_time));
						// 编制日期（机电五部2015-08-24要求，编制日期=检验日期+1天）
						calendar.add(Calendar.DATE, 1);
						enter_date = calendar.getTime();
					} else {
						enter_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.format(enter_date, "yyyy-MM-dd"));
					}
					if (cur_op_date.before(enter_date)) {
						return JsonWrapper.failureWrapperMsg(inspectionInfo.getReport_sn() + "审核日期不能早于编制日期，请检查！");
					}
				}
			} else if ("05".equals(type)) { // 验证签发日期是否早于审核日期
				Date check_date = inspectionInfo.getExamine_Date(); // 审核日期
				check_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.format(check_date, "yyyy-MM-dd"));
				if (cur_op_date.before(check_date)) {
					return JsonWrapper.failureWrapperMsg(inspectionInfo.getReport_sn() + "签发日期不能早于审核日期，请检查！");
				}
			}
		}
		
		String revise_remark = dataMap.get("revise_remark").toString(); // 不通过原因
		if("请在此处填写报告退回原因！".equals(revise_remark)){
			revise_remark = "无";
		}
		// if (ins_info_id.indexOf(",") != -1) {
		String acc_ids[] = acc_id.split(",");
		
		
		Map<String, Object> emps = new HashMap<String, Object>(); 			// 签发授权签字人集合	
		Map<String, Object> com_emps = new HashMap<String, Object>(); 		// 相同单位签字人集合	
		Map<String, String> send_sn_map = new HashMap<String, String>();	// 报告领取通知内容之报告编号集合
		Map<String, String> send_num_map = new HashMap<String, String>();	// 报告领取通知内容之车牌号集合
		
		// 获取所有签发授权签字人信息
		emps = certificateByService.getAllIssues();
	
		for (int i = 0; i < ids.length; i++) {
			String next_op_id = "";		// 签发人ID（employee表主键）
			String next_op_name = "";	// 签发人姓名
			String rule = "";			// 实际分配规则（1、相同使用单位优先分配；2、量少优先分配；）
			boolean is_auto_issue = false;	// 是否是自动分配
			
			// 业务信息
			InspectionInfo inspectionInfo = infoService.get(ids[i]);
			Report report = reportService.get(inspectionInfo.getReport_type());
			
			if(StringUtil.isEmpty(pre_device_type)){
				if(StringUtil.isNotEmpty(inspectionInfo.getFk_tsjc_device_document_id())){
					if ("11111111111111111111111111111111".equals(inspectionInfo.getFk_tsjc_device_document_id())) {
						// 获取监检业务信息
						InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService.getByInfoId(ids[i]);
						if(inspectionZZJDInfo != null){
							if (StringUtil.isNotEmpty(inspectionZZJDInfo.getDevice_type_code())) {
								if ("7310".equals(inspectionZZJDInfo.getDevice_type_code())) {
									pre_device_type = "F";
								} else {
									// 获取设备类别前缀（大类）
									pre_device_type = inspectionZZJDInfo.getDevice_type_code().substring(0, 1);
								}
							}
						}
					} else {
						DeviceDocument deviceDocument = deviceService.get(inspectionInfo.getFk_tsjc_device_document_id());
						if (deviceDocument != null) {
							if(StringUtil.isNotEmpty(deviceDocument.getDevice_sort_code())){
								if("7310".equals(deviceDocument.getDevice_sort_code())){
									pre_device_type = "F";
								}else if("2610".equals(deviceDocument.getDevice_sort_code())){
									pre_device_type = "0";
								}else{
									pre_device_type = deviceDocument.getDevice_sort_code().substring(0, 1);
								}
							}
							if (StringUtil.isEmpty(pre_device_type)) {
								if(StringUtil.isNotEmpty(deviceDocument.getDevice_sort())){
									if("7310".equals(deviceDocument.getDevice_sort())){
										pre_device_type = "F";
									}else if("2600".equals(deviceDocument.getDevice_sort())){
										pre_device_type = "0";
									}else{
										pre_device_type = deviceDocument.getDevice_sort().substring(0, 1);
									}
								}
							}
						}
					}
				}
			}
			
			if(StringUtil.isEmpty(check_flow)){
				if(inspectionInfo!=null){
					if(StringUtil.isNotEmpty(inspectionInfo.getReport_type())){
						check_flow = report.getCheck_flow();
					}
				}
			}
			
			if (type.equals("04") && "0".equals(check_flow) && "1".equals(revise_conclusion)) {
				// 获取报告自动分配签发人start......
				// 2017-07-03应张展彬要求修改，包括压力容器，所有设备均由系统进行自动分配签发人
				// 2017-10-13应张展彬要求修改，增加西藏报告，不进行自动分配，由审核人手动选择签发人
				// 2017-12-12应明子涵要求修改，增加新疆报告，不进行自动分配，由审核人手动选择签发人
				// 2018-03-29应明子涵要求修改，增加九寨报告，不进行自动分配，由审核人手动选择签发人
				if("100069".equals(inspectionInfo.getCheck_unit_id()) || "100090".equals(inspectionInfo.getCheck_unit_id()) || "100091".equals(inspectionInfo.getCheck_unit_id())){
					next_op_id = dataMap.get("next_sub_op").toString();
					next_op_name = dataMap.get("next_op_name").toString();
					if(StringUtil.isNotEmpty(next_op_name)){
						if(next_op_name.equals(inspectionInfo.getEnter_op_name()) || next_op_name.equals(user.getName())){
							return JsonWrapper.failureWrapperMsg("您选择的签发人员（"+next_op_name+"）已参与报告" + inspectionInfo.getReport_sn()
							+ "的签名，请重新选择！");
						}
					}else{
						return JsonWrapper.failureWrapperMsg("请选择签发人再提交！");
					}
					is_auto_issue = false;
				}else{
					if(com_emps.containsKey(inspectionInfo.getReport_com_name())){
						next_op_id = String.valueOf(com_emps.get(inspectionInfo.getReport_com_name()));
						next_op_name = String.valueOf(emps.get(next_op_id));
						rule = "1";
					}else{
						Map<String, Object> infoMaps = inspectionCommonService.autoIssue(inspectionInfo, pre_device_type);
						if(Boolean.valueOf(String.valueOf(infoMaps
								.get("success")))){
							next_op_id = String.valueOf(infoMaps.get("next_op_id"));
							next_op_name = String.valueOf(infoMaps.get("next_op_name"));
							rule = infoMaps.get("rule")!=null?String.valueOf(infoMaps.get("rule")):"";
							//if("1".equals(rule)){
								com_emps.put(inspectionInfo.getReport_com_name(), next_op_id);
							//}
						}else{
							return JsonWrapper.failureWrapperMsg(String.valueOf(infoMaps.get("msg")));
						}
					}
					is_auto_issue = true;
				}
				// 获取报告自动分配签发人end......
			}
			map.put("ins_info_id", ids[i]);
			map.put("acc_id", acc_ids[i]);
			map.put("revise_conclusion", revise_conclusion);
			map.put("op_time", op_time);
			map.put("type", type);
			map.put("flow_num", flow_num);
			// revise_conclusion（结论：1：通过 2：不通过）
			if ("1".equals(revise_conclusion) && type.equals("04")) {
				// 如果审核状态是通过，则进入下一个环节
				map.put("revise_remark", "结论：通过\n" + revise_remark);
				if("1".equals(check_flow)){
					map.put("flag", "0");	// 如果是0就不用指定下一步操作人
				}else{
					map.put("flag", "参数判断是否获取下一步指定人");
				}
		
				// 获取报告页单独审核信息，如果存在报告页待审核或者审核不通过的情况，本次审核不能保存并不能提交至下一环节
				// 报告页单独审核信息
				List<ReportPageCheckInfo> list = reportPageCheckInfoService.queryPageInfos(ids[i], inspectionInfo.getReport_type());
				String returnMsg = "";
				for(ReportPageCheckInfo reportPageCheckInfo : list){
					if(StringUtil.isNotEmpty(reportPageCheckInfo.getAudit_user_id())){
						if (!"1".equals(reportPageCheckInfo.getData_status())) {
							String page_name = reportPageCheckInfo.getPage_name();
							String data_status = "0".equals(reportPageCheckInfo.getData_status()) ? "待审核" : "审核不通过"; 
							if (returnMsg.length()>0) {
								returnMsg += ";\r\n";
							}
							returnMsg += "报告书编号："+inspectionInfo.getReport_sn()+"，报告页【"+ page_name +"】" + data_status;
						}
					}
				}
				if (StringUtil.isNotEmpty(returnMsg)) {
					returnMsg += "。\r\n请等待所有报告页审核通过后，再操作！";
					return JsonWrapper.failureWrapperMsg(returnMsg);
				}else{
					if("0".equals(check_flow)){
						if(StringUtil.isEmpty(next_op_id)){
							return JsonWrapper.failureWrapperMsg("当前无在线签字人员，详询质量部028-86607892！");
						}else{
							if(is_auto_issue){
								// 记录系统自动随机分配报告签发日志
								SysAutoIssueLog issueLog = new SysAutoIssueLog();
								issueLog.setBusiness_id(inspectionInfo.getId());
								issueLog.setReport_sn(inspectionInfo.getReport_sn());
								issueLog.setReport_com_name(inspectionInfo.getReport_com_name().trim());
								issueLog.setCheck_unit_id(inspectionInfo.getCheck_unit_id());
								issueLog.setDevice_type(pre_device_type);
								issueLog.setOp_user_id(user.getId());
								issueLog.setOp_user_name(user.getName());
								issueLog.setOp_action(Constant.SYS_OP_ACTION_AUTO_ISSUE);
								issueLog.setOp_remark(Constant.SYS_OP_ACTION_AUTO_ISSUE + "至【"+next_op_name+"】");
								issueLog.setOp_time(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime()));
								issueLog.setTo_user_id(next_op_id);
								issueLog.setTo_user_name(next_op_name);
								issueLog.setIssue_type(rule);
								sysAutoIssueLogService.save(issueLog);
								
								// 标记报告为系统自动随机分配签发报告
								inspectionInfo.setIs_auto_issue("1");
								infoService.save(inspectionInfo);
							}
						}
						map.put("next_sub_op", next_op_id);
						map.put("next_op_name", next_op_name);
					}
					inspectionService.flow_saveCheck(map);
					// 提交到下一个环节
					inspectionService.subFlowProcess(map, request);
				}
			} else if ("1".equals(revise_conclusion) && type.equals("05")) {
				map.put("revise_remark", "结论：通过\n" + revise_remark);
				map.put("flag", "0");// 如果是0，就不用指定下一步操作人
				// 返写业务信息（返写审批人信息等）
				inspectionService.flow_saveCheck(map);
				// 返写设备信息
				inspectionService.dealDeviceInfo(map);	// 签发通过时，返写设备信息
				// 将报告流程提交至下一步环节
				inspectionService.subFlowProcess(map, request);
				
				/*
				 * 2018-03-19根据张展彬要求，改即时推送为定时推送，故此不再进行单份报告提醒推送
				// 常压罐车和汽车罐车定检报告，当报告书签发后，给报告安全管理人员的联系电话推短信提醒
				// 签发后是否发送消息提醒（1：微信 2：短信 3：微信和短信 0：不发送 ）
				if(StringUtil.isNotEmpty(report.getIssue_msg_type())){
					if(!"0".equals(report.getIssue_msg_type())){
						String item_names = Factory.getSysPara().getProperty("GC_REPORT_ITEM_NAMES");
						if (StringUtil.isEmpty(item_names)) {
							item_names = Constant.GC_REPORT_ITEM_NAMES;
						}
						// 获取发送内容
						Map<String, Object> content_map = reportItemValueService.getGCContent(ids[i], inspectionInfo.getReport_type(), item_names);
						// 获取发送目标号码
						String destNumber = content_map.get("SECURITY_TEL")+"";
						// 获取发送内容
						String content = Constant.getGcNoticeContent(content_map);
						
						if(StringUtil.isNotEmpty(destNumber) && StringUtil.isNotEmpty(content)){
							boolean isMobile = TS_Util.checkMobile(destNumber.trim());
							if(isMobile){
								if("1".equals(report.getIssue_msg_type())){
									// 发送微信
									messageService.sendWxMsg(request, ids[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
											content, destNumber.trim());
								}else if("2".equals(report.getIssue_msg_type())){
									// 发送短信
									messageService.sendMoMsg(request, ids[i], content, destNumber.trim());
								}else if("3".equals(report.getIssue_msg_type())){
									// 发送微信和短信
									messageService.sendWxMsg(request, ids[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
											content, destNumber.trim());
									messageService.sendMoMsg(request, ids[i], content, destNumber.trim());
								}
							}
						}
					}
				}
				*/
				
				// 2018-03-19根据张展彬要求，改即时单份推送为一批多份推送提醒,此处进行发送统计
				if (StringUtil.isNotEmpty(report.getIssue_msg_type())) {
					if (!"0".equals(report.getIssue_msg_type())) {
						String item_names = Factory.getSysPara().getProperty("GC_REPORT_ITEM_NAMES");
						if (StringUtil.isEmpty(item_names)) {
							item_names = Constant.GC_REPORT_ITEM_NAMES;
						}
						// 获取发送内容
						Map<String, Object> content_map = reportItemValueService.getGCContent(ids[i],
								inspectionInfo.getReport_type(), item_names);
						// 获取发送目标号码
						String destNumber = content_map.get("SECURITY_TEL") + "";
						
						// 同一个号码，只发送一条提醒信息
						// 组合报告编号
						if(send_sn_map.containsKey(destNumber)){
							String send_sn = send_sn_map.get(destNumber);
							if(StringUtil.isNotEmpty(send_sn)){
								send_sn += "、" + inspectionInfo.getReport_sn();
							}
							send_sn_map.put(destNumber, send_sn);
						}else{
							send_sn_map.put(destNumber, inspectionInfo.getReport_sn());
						}
						// 组合车牌号
						String car_num = content_map.get("LADLE_CAR_NUMBER")+"";
						if(StringUtil.isEmpty(car_num)){
							car_num = content_map.get("INTERNAL_NUM")+"";
						}else{
							if("null".equals(car_num)){
								car_num = content_map.get("INTERNAL_NUM")+"";
							}
						}
						if(send_num_map.containsKey(destNumber)){
							String send_num = send_num_map.get(destNumber);
							if(StringUtil.isNotEmpty(send_num)){
								send_num += "、" + car_num;
							}
							send_num_map.put(destNumber, send_num);
						}else{
							send_num_map.put(destNumber, car_num);
						}
						
						// 标记报告领取提醒通知状态
						inspectionInfo.setIs_send_draw_msg("1");
						infoService.save(inspectionInfo);
					}
				}
			} else if ("2".equals(revise_conclusion) && type.equals("04")) {
				// 如果审核状态是不通过，则返回上一环节
				map.put("revise_remark", "结论：不通过\n" + revise_remark);
				// 回退到上一步
				inspectionService.returApprove(map, request);
			} else if ("2".equals(revise_conclusion) && type.equals("05")) {
				// 报告签发时，回退可以回退至上一步（报告审核环节），也可以回退至报告录入环节
				String backStep = request.getParameter("backType");
				map.put("revise_remark", "结论：不通过\n" + revise_remark);
				map.put("backStep", backStep);
				inspectionService.returApprove(map, request);
			}
		}
		
		// 常压罐车和汽车罐车定检报告，当报告书签发后，给报告安全管理人员的联系电话推短信提醒
		// 发送报告领取提醒start...
		String is_draw = Factory.getSysPara().getProperty("GC_REPORT_DRAW");
		if(StringUtil.isEmpty(is_draw)){
			is_draw = "0";
		}
		// 是否发送消息提醒（1：微信 2：短信 3：微信和短信 0：不发送 ）
		if(!"0".equals(is_draw)){
			for (Iterator iterator = send_sn_map.keySet().iterator(); iterator.hasNext();) {
				String destNumber = (String) iterator.next();
				String content = Constant.getGcNoticeContent2(send_num_map.get(destNumber), send_sn_map.get(destNumber));
				if ("1".equals(is_draw)) {
					// 发送微信
					messageService.sendWxMsg(null, ids[0], Constant.INSPECTION_CORPID,
							Constant.INSPECTION_PWD, content, destNumber.trim());
				} else if ("2".equals(is_draw)) {
					// 发送短信
					messageService.sendMoMsg(null, ids[0], content, destNumber.trim());
				} else if ("3".equals(is_draw)) {
					// 发送微信和短信
					messageService.sendWxMsg(null, ids[0], Constant.INSPECTION_CORPID,
							Constant.INSPECTION_PWD, content, destNumber.trim());
					messageService.sendMoMsg(null, ids[0], content, destNumber.trim());
				}
			}
		}
		// 发送报告领取提醒end...
		
		Map<String, Object> return_map = new HashMap<String, Object>();
		return_map.put("success", true);
		return_map.put("isBatch", isBatch);
		return_map.put("info_ids", ins_info_id);
		return return_map;
	}

	@RequestMapping(value = "jsReportBatchCheck")
	@ResponseBody
	public Map<String, Object> jsReportBatchCheck(String dataStr, String checkParams, HttpServletRequest request)
			throws Exception {
		// 返回参数容器
		Map<String, Object> wapper = new HashMap<String, Object>();
		// 当前登陆人
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// form表单参数
			JSONObject dataMap = JSONObject.fromString(dataStr);
			// 审核/签发报告的相关信息
			JSONObject reportsDataMap = JSONObject.fromString(checkParams);
			// 流程提交
			String type = reportsDataMap.get("type").toString(); // 操作类型（04：报告审核 05：报告签发）
			String op_name = "04".equals(type) ? "审核日期" : "签发日期";
			// 设备类别
			String device_sort_code = reportsDataMap.get("device_sort_code").toString();
			String pre_device_type = "";
			if (StringUtil.isNotEmpty(device_sort_code)) {
				if ("7310".equals(device_sort_code)) {
					pre_device_type = "F";
				} else {
					pre_device_type = device_sort_code.substring(0, 1);
				}
			}

			// 是否是批量操作（0：否 1：是）
			String isBatch = reportsDataMap.get("isBatch").toString();
			// 报告审批流程（0：二级审核 1：一级审核）
			String check_flow = reportsDataMap.get("check_flow").toString();

			// 流程编号ID
			// String acc_id = reportsDataMap.get("acc_id").toString();
			JSONArray acc_ids = JSONArray.fromObject(reportsDataMap.get("acc_id"));
			// 业务ID
			JSONArray ids = JSONArray.fromObject(reportsDataMap.get("ids"));

			// 步骤号
			String flow_num = reportsDataMap.get("flow_num").toString();// request.getParameter("flow_num");

			String revise_conclusion = dataMap.get("revise_conclusion").toString(); // 结论

			String op_time = dataMap.get("op_time").toString(); // 操作时间（审核/签发日期）
			Date cur_op_date = null;
			if (StringUtil.isNotEmpty(op_time)) {
				cur_op_date = DateUtil.convertStringToDate("yyyy-MM-dd", op_time);
				Date cur_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime());
				if (cur_op_date.after(cur_date)) {
					return JsonWrapper.failureWrapperMsg("日期逻辑错误，" + op_name + "不能晚于当前日期，请重新选择！");
				}
			} else {
				return JsonWrapper.failureWrapperMsg(op_name + "不能为空，请选择！");
			}

			// 验证审核或签发日期是否存在逻辑错误
			// 审核日期不能早于编制日期，签发日期不能早于审核日期
			for (int i = 0; i < ids.length(); i++) {
				// 业务信息
				InspectionInfo inspectionInfo = infoService.get(ids.get(i).toString());
				if ("04".equals(type)) {
					// 验证电梯审核日期是否早于编制日期
					if ("3".equals(pre_device_type)) {
						Date enter_date = inspectionInfo.getEnter_time(); // 编制日期
						if (enter_date == null) {
							// 编制日期为空时，默认设置为检验日期+1天
							String advance_time = DateUtil.format(inspectionInfo.getAdvance_time(), "yyyy-MM-dd");
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", advance_time));
							// 编制日期（机电五部2015-08-24要求，编制日期=检验日期+1天）
							calendar.add(Calendar.DATE, 1);
							enter_date = calendar.getTime();
						} else {
							enter_date = DateUtil.convertStringToDate("yyyy-MM-dd",
									DateUtil.format(enter_date, "yyyy-MM-dd"));
						}
						if (cur_op_date.before(enter_date)) {
							return JsonWrapper.failureWrapperMsg(inspectionInfo.getReport_sn() + "审核日期不能早于编制日期，请检查！");
						}
					}
				} else if ("05".equals(type)) { // 验证签发日期是否早于审核日期
					Date check_date = inspectionInfo.getExamine_Date(); // 审核日期
					check_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.format(check_date, "yyyy-MM-dd"));
					if (cur_op_date.before(check_date)) {
						return JsonWrapper.failureWrapperMsg(inspectionInfo.getReport_sn() + "签发日期不能早于审核日期，请检查！");
					}
				}
			}

			String revise_remark = dataMap.get("revise_remark").toString(); // 不通过原因
			if ("请在此处填写报告退回原因！".equals(revise_remark)) {
				revise_remark = "无";
			}
			// if (ins_info_id.indexOf(",") != -1) {
			// String acc_ids[] = acc_id.split(",");

			Map<String, Object> emps = new HashMap<String, Object>(); // 签发授权签字人集合
			Map<String, Object> com_emps = new HashMap<String, Object>(); // 相同单位签字人集合
			Map<String, String> send_sn_map = new HashMap<String, String>(); // 报告领取通知内容之报告编号集合
			Map<String, String> send_num_map = new HashMap<String, String>(); // 报告领取通知内容之车牌号集合

			// 获取所有签发授权签字人信息
			emps = certificateByService.getAllIssues();

			for (int i = 0; i < ids.length(); i++) {
				String next_op_id = ""; // 签发人ID（employee表主键）
				String next_op_name = ""; // 签发人姓名
				String rule = ""; // 实际分配规则（1、相同使用单位优先分配；2、量少优先分配；）
				boolean is_auto_issue = false; // 是否是自动分配

				// 业务信息
				InspectionInfo inspectionInfo = infoService.get(ids.getString(i));
				Report report = reportService.get(inspectionInfo.getReport_type());

				if (StringUtil.isEmpty(pre_device_type)) {
					if (StringUtil.isNotEmpty(inspectionInfo.getFk_tsjc_device_document_id())) {
						if ("11111111111111111111111111111111".equals(inspectionInfo.getFk_tsjc_device_document_id())) {
							// 获取监检业务信息
							InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
									.getByInfoId(ids.getString(i));
							if (inspectionZZJDInfo != null) {
								if (StringUtil.isNotEmpty(inspectionZZJDInfo.getDevice_type_code())) {
									if ("7310".equals(inspectionZZJDInfo.getDevice_type_code())) {
										pre_device_type = "F";
									} else {
										// 获取设备类别前缀（大类）
										pre_device_type = inspectionZZJDInfo.getDevice_type_code().substring(0, 1);
									}
								}
							}
						} else {
							DeviceDocument deviceDocument = deviceService
									.get(inspectionInfo.getFk_tsjc_device_document_id());
							if (deviceDocument != null) {
								if (StringUtil.isNotEmpty(deviceDocument.getDevice_sort_code())) {
									if ("7310".equals(deviceDocument.getDevice_sort_code())) {
										pre_device_type = "F";
									} else if ("2610".equals(deviceDocument.getDevice_sort_code())) {
										pre_device_type = "0";
									} else {
										pre_device_type = deviceDocument.getDevice_sort_code().substring(0, 1);
									}
								}
								if (StringUtil.isEmpty(pre_device_type)) {
									if (StringUtil.isNotEmpty(deviceDocument.getDevice_sort())) {
										if ("7310".equals(deviceDocument.getDevice_sort())) {
											pre_device_type = "F";
										} else if ("2600".equals(deviceDocument.getDevice_sort())) {
											pre_device_type = "0";
										} else {
											pre_device_type = deviceDocument.getDevice_sort().substring(0, 1);
										}
									}
								}
							}
						}
					}
				}

				if (StringUtil.isEmpty(check_flow)) {
					if (inspectionInfo != null) {
						if (StringUtil.isNotEmpty(inspectionInfo.getReport_type())) {
							check_flow = report.getCheck_flow();
						}
					}
				}

				if (type.equals("04") && "0".equals(check_flow) && "1".equals(revise_conclusion)) {
					// 获取报告自动分配签发人start......
					// 2017-07-03应张展彬要求修改，包括压力容器，所有设备均由系统进行自动分配签发人
					// 2017-10-13应张展彬要求修改，增加西藏报告，不进行自动分配，由审核人手动选择签发人
					// 2017-12-12应明子涵要求修改，增加新疆报告，不进行自动分配，由审核人手动选择签发人
					// 2018-03-29应明子涵要求修改，增加九寨报告，不进行自动分配，由审核人手动选择签发人
					if ("100069".equals(inspectionInfo.getCheck_unit_id())
							|| "100090".equals(inspectionInfo.getCheck_unit_id())
							|| "100091".equals(inspectionInfo.getCheck_unit_id())) {
						next_op_id = dataMap.get("next_sub_op").toString();
						next_op_name = dataMap.get("next_op_name").toString();
						if (StringUtil.isNotEmpty(next_op_name)) {
							if (next_op_name.equals(inspectionInfo.getEnter_op_name())
									|| next_op_name.equals(user.getName())) {
								return JsonWrapper.failureWrapperMsg("您选择的签发人员（" + next_op_name + "）已参与报告"
										+ inspectionInfo.getReport_sn() + "的签名，请重新选择！");
							}
						} else {
							return JsonWrapper.failureWrapperMsg("请选择签发人再提交！");
						}
						is_auto_issue = false;
					} else {
						if (com_emps.containsKey(inspectionInfo.getReport_com_name())) {
							next_op_id = String.valueOf(com_emps.get(inspectionInfo.getReport_com_name()));
							next_op_name = String.valueOf(emps.get(next_op_id));
							rule = "1";
						} else {
							Map<String, Object> infoMaps = inspectionCommonService.autoIssue(inspectionInfo,
									pre_device_type);
							if (Boolean.valueOf(String.valueOf(infoMaps.get("success")))) {
								next_op_id = String.valueOf(infoMaps.get("next_op_id"));
								next_op_name = String.valueOf(infoMaps.get("next_op_name"));
								rule = infoMaps.get("rule") != null ? String.valueOf(infoMaps.get("rule")) : "";
								// if("1".equals(rule)){
								com_emps.put(inspectionInfo.getReport_com_name(), next_op_id);
								// }
							} else {
								return JsonWrapper.failureWrapperMsg(String.valueOf(infoMaps.get("msg")));
							}
						}
						is_auto_issue = true;
					}
					// 获取报告自动分配签发人end......
				}
				map.put("ins_info_id", ids.getString(i));
				map.put("acc_id", acc_ids.getString(i));
				map.put("revise_conclusion", revise_conclusion);
				map.put("op_time", op_time);
				map.put("type", type);
				map.put("flow_num", flow_num);
				// revise_conclusion（结论：1：通过 2：不通过）
				if ("1".equals(revise_conclusion) && type.equals("04")) {
					// 如果审核状态是通过，则进入下一个环节
					map.put("revise_remark", "结论：通过\n" + revise_remark);
					if ("1".equals(check_flow)) {
						map.put("flag", "0"); // 如果是0就不用指定下一步操作人
					} else {
						map.put("flag", "参数判断是否获取下一步指定人");
					}

					// 获取报告页单独审核信息，如果存在报告页待审核或者审核不通过的情况，本次审核不能保存并不能提交至下一环节
					// 报告页单独审核信息
					List<ReportPageCheckInfo> list = reportPageCheckInfoService.queryPageInfos(ids.getString(i),
							inspectionInfo.getReport_type());
					String returnMsg = "";
					for (ReportPageCheckInfo reportPageCheckInfo : list) {
						if (StringUtil.isNotEmpty(reportPageCheckInfo.getAudit_user_id())) {
							if (!"1".equals(reportPageCheckInfo.getData_status())) {
								String page_name = reportPageCheckInfo.getPage_name();
								String data_status = "0".equals(reportPageCheckInfo.getData_status()) ? "待审核" : "审核不通过";
								if (returnMsg.length() > 0) {
									returnMsg += ";\r\n";
								}
								returnMsg += "报告书编号：" + inspectionInfo.getReport_sn() + "，报告页【" + page_name + "】"
										+ data_status;
							}
						}
					}
					if (StringUtil.isNotEmpty(returnMsg)) {
						returnMsg += "。\r\n请等待所有报告页审核通过后，再操作！";
						return JsonWrapper.failureWrapperMsg(returnMsg);
					} else {
						if ("0".equals(check_flow)) {
							if (StringUtil.isEmpty(next_op_id)) {
								return JsonWrapper.failureWrapperMsg("当前无在线签字人员，详询质量部028-86607892！");
							} else {
								if (is_auto_issue) {
									// 记录系统自动随机分配报告签发日志
									SysAutoIssueLog issueLog = new SysAutoIssueLog();
									issueLog.setBusiness_id(inspectionInfo.getId());
									issueLog.setReport_sn(inspectionInfo.getReport_sn());
									issueLog.setReport_com_name(inspectionInfo.getReport_com_name().trim());
									issueLog.setCheck_unit_id(inspectionInfo.getCheck_unit_id());
									issueLog.setDevice_type(pre_device_type);
									issueLog.setOp_user_id(user.getId());
									issueLog.setOp_user_name(user.getName());
									issueLog.setOp_action(Constant.SYS_OP_ACTION_AUTO_ISSUE);
									issueLog.setOp_remark(
											Constant.SYS_OP_ACTION_AUTO_ISSUE + "至【" + next_op_name + "】");
									issueLog.setOp_time(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern,
											DateUtil.getCurrentDateTime()));
									issueLog.setTo_user_id(next_op_id);
									issueLog.setTo_user_name(next_op_name);
									issueLog.setIssue_type(rule);
									sysAutoIssueLogService.save(issueLog);

									// 标记报告为系统自动随机分配签发报告
									inspectionInfo.setIs_auto_issue("1");
									infoService.save(inspectionInfo);
								}
							}
							map.put("next_sub_op", next_op_id);
							map.put("next_op_name", next_op_name);
						}
						inspectionService.flow_saveCheck(map);
						// 提交到下一个环节
						inspectionService.subFlowProcess(map, request);
					}
				} else if ("1".equals(revise_conclusion) && type.equals("05")) {
					map.put("revise_remark", "结论：通过\n" + revise_remark);
					map.put("flag", "0");// 如果是0，就不用指定下一步操作人
					// 返写业务信息（返写审批人信息等）
					inspectionService.flow_saveCheck(map);
					// 返写设备信息
					inspectionService.dealDeviceInfo(map); // 签发通过时，返写设备信息
					// 将报告流程提交至下一步环节
					inspectionService.subFlowProcess(map, request);

					// 2018-03-19根据张展彬要求，改即时单份推送为一批多份推送提醒,此处进行发送统计
					if (StringUtil.isNotEmpty(report.getIssue_msg_type())) {
						if (!"0".equals(report.getIssue_msg_type())) {
							String item_names = Factory.getSysPara().getProperty("GC_REPORT_ITEM_NAMES");
							if (StringUtil.isEmpty(item_names)) {
								item_names = Constant.GC_REPORT_ITEM_NAMES;
							}
							// 获取发送内容
							Map<String, Object> content_map = reportItemValueService.getGCContent(ids.getString(i),
									inspectionInfo.getReport_type(), item_names);
							// 获取发送目标号码
							String destNumber = content_map.get("SECURITY_TEL") + "";

							// 同一个号码，只发送一条提醒信息
							// 组合报告编号
							if (send_sn_map.containsKey(destNumber)) {
								String send_sn = send_sn_map.get(destNumber);
								if (StringUtil.isNotEmpty(send_sn)) {
									send_sn += "、" + inspectionInfo.getReport_sn();
								}
								send_sn_map.put(destNumber, send_sn);
							} else {
								send_sn_map.put(destNumber, inspectionInfo.getReport_sn());
							}
							// 组合车牌号
							String car_num = content_map.get("LADLE_CAR_NUMBER") + "";
							if (StringUtil.isEmpty(car_num)) {
								car_num = content_map.get("INTERNAL_NUM") + "";
							} else {
								if ("null".equals(car_num)) {
									car_num = content_map.get("INTERNAL_NUM") + "";
								}
							}
							if (send_num_map.containsKey(destNumber)) {
								String send_num = send_num_map.get(destNumber);
								if (StringUtil.isNotEmpty(send_num)) {
									send_num += "、" + car_num;
								}
								send_num_map.put(destNumber, send_num);
							} else {
								send_num_map.put(destNumber, car_num);
							}

							// 标记报告领取提醒通知状态
							inspectionInfo.setIs_send_draw_msg("1");
							infoService.save(inspectionInfo);
						}
					}
				} else if ("2".equals(revise_conclusion) && type.equals("04")) {
					// 如果审核状态是不通过，则返回上一环节
					map.put("revise_remark", "结论：不通过\n" + revise_remark);
					// 回退到上一步
					inspectionService.returApprove(map, request);
				} else if ("2".equals(revise_conclusion) && type.equals("05")) {
					// 报告签发时，回退可以回退至上一步（报告审核环节），也可以回退至报告录入环节
					String backStep = request.getParameter("backType");
					map.put("revise_remark", "结论：不通过\n" + revise_remark);
					map.put("backStep", backStep);
					inspectionService.returApprove(map, request);
				}
			}

			// 常压罐车和汽车罐车定检报告，当报告书签发后，给报告安全管理人员的联系电话推短信提醒
			// 发送报告领取提醒start...
			String is_draw = Factory.getSysPara().getProperty("GC_REPORT_DRAW");
			if (StringUtil.isEmpty(is_draw)) {
				is_draw = "0";
			}
			// 是否发送消息提醒（1：微信 2：短信 3：微信和短信 0：不发送 ）
			if (!"0".equals(is_draw)) {
				for (Iterator iterator = send_sn_map.keySet().iterator(); iterator.hasNext();) {
					String destNumber = (String) iterator.next();
					String content = Constant.getGcNoticeContent2(send_num_map.get(destNumber),
							send_sn_map.get(destNumber));
					if ("1".equals(is_draw)) {
						// 发送微信
						messageService.sendWxMsg(null, ids.getString(0), Constant.INSPECTION_CORPID,
								Constant.INSPECTION_PWD, content, destNumber.trim());
					} else if ("2".equals(is_draw)) {
						// 发送短信
						messageService.sendMoMsg(null, ids.getString(0), content, destNumber.trim());
					} else if ("3".equals(is_draw)) {
						// 发送微信和短信
						messageService.sendWxMsg(null, ids.getString(0), Constant.INSPECTION_CORPID,
								Constant.INSPECTION_PWD, content, destNumber.trim());
						messageService.sendMoMsg(null, ids.getString(0), content, destNumber.trim());
					}
				}
			}

			// 发送报告领取提醒end...
			wapper.put("success", true);
			wapper.put("isBatch", isBatch);
			wapper.put("info_ids", ids);
		} catch (Exception e) {
			e.printStackTrace();
			wapper.put("success", false);
			wapper.put("msg", e.getMessage());
		}
		return wapper;
	}
	
	/**
	 * 报告批量审核、签发
	 * 
	 * @param request
	 * @param dataStr --
	 *            参数json串
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchCheckPage")
	@ResponseBody
	public Map<String, Object> batchCheckPage(String dataStr,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		// 环节类型（04：审核 05：签发）
		String type = request.getParameter("type"); // 操作类型（04：报告审核 05：报告签发）
		// 审核类型，是否是批量操作（0：否 1：是）
		String isBatch = request.getParameter("isBatch"); 
		// 流程编号ID
		String acc_id = request.getParameter("acc_id");
		// 业务ID
		String ins_info_id = request.getParameter("infoId");
		// 审核结果
		String revise_conclusion = dataMap.get("revise_conclusion").toString(); // 结论
		String op_time = dataMap.get("op_time").toString(); // 操作时间
		String revise_remark = dataMap.get("revise_remark").toString(); // 不通过原因
		if("请在此处填写报告退回原因！".equals(revise_remark)){
			revise_remark = "无";
		}
		
		String ids[] = ins_info_id.split(",");
		String acc_ids[] = acc_id.split(",");
		for (int i = 0; i < ids.length; i++) {
			InspectionInfo inspectionInfo = infoService.get(ids[i]);
			// 步骤号
			String flow_num = inspectionInfo.getFlow_note_id();
	
			// 查询报告页单独审核信息
			List<ReportPageCheckInfo> reportPageInfoList = 
				reportPageCheckInfoService.queryUnCheckPageInfos(ids[i], 
						inspectionInfo.getReport_type(), emp.getId());
			// 1：审核通过，更新报告页单独审核信息表审核状态
			// 2：审核不通过，返回上一环节
			if ("1".equals(revise_conclusion) && type.equals("04")) {
				for (ReportPageCheckInfo reportPageCheckInfo : reportPageInfoList) {
					String data_status = reportPageCheckInfo.getData_status();
					if ("0".equals(data_status)) {
						String item_name = "AUDIT_DATE"+reportPageCheckInfo.getPage_index();	// 报告页参数，审核日期						
						List<ReportItemValue> list  = reportItemValueService.getItemByItemName(ids[i], inspectionInfo.getReport_type(), item_name);
						// 判断报告参数是否有数据，有就执行更新数据操作，没有就执行插入数据操作
						if(list.size()>0){
							String item_id = list.get(0).getId();
							reportItemValueService.updateItemValue(item_id, ids[i], inspectionInfo.getReport_type(), item_name, op_time);
						}else{
							// 新增报告参数值表
							ReportItemValue itemValue = new ReportItemValue();
				        	itemValue.setFk_report_id(inspectionInfo.getReport_type());
				        	itemValue.setFk_inspection_info_id(ids[i]);
				        	itemValue.setItem_name(item_name);
				        	itemValue.setItem_value(op_time);
				        	itemValue.setItem_type("String");
				        	reportItemValueService.save(itemValue);
						}
						
						reportPageCheckInfo.setCheck_user_id(emp.getId());
						reportPageCheckInfo.setCheck_user_name(emp.getName());
						reportPageCheckInfo.setCheck_desc(revise_remark);	// 审核备注
						reportPageCheckInfo.setCheck_date(new Date());
						// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过 2：审核不通过 99：删除）
						reportPageCheckInfo.setData_status("1");
						reportPageCheckInfoService.save(reportPageCheckInfo);	// 更新报告页单独审核信息表审核状态
		        		
		        		String page_name = reportPageCheckInfo.getPage_name();
		        		if (StringUtil.isEmpty(page_name)) {
		        			// 报告页索引信息
		        			List<ReportItem> itemlist = reportItemService.queryByReportId(inspectionInfo.getReport_type());
		        			for (ReportItem reportItem : itemlist) {
		            			if (reportItem.getPage_index().equals(reportPageCheckInfo.getPage_index())) {
		            				page_name = reportItem.getItme_name();
		    					}
		    				}
						}
		        		logService.setLogs(ids[i], "报告页【"+page_name+"】单独审核", "结论：审核通过（" + revise_remark + "）", curUser.getId(), curUser
								.getName(), new Date(), request.getRemoteAddr());
					}
				}
			}else if ("2".equals(revise_conclusion) && type.equals("04")) {
				for (ReportPageCheckInfo reportPageCheckInfo : reportPageInfoList) {
					String data_status = reportPageCheckInfo.getData_status();
					if ("0".equals(data_status)) {
						reportPageCheckInfo.setCheck_user_id(emp.getId());
						reportPageCheckInfo.setCheck_user_name(emp.getName());
						reportPageCheckInfo.setCheck_desc(revise_remark);	// 审核备注
						reportPageCheckInfo.setCheck_date(new Date());
						// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过 2：审核不通过 99：删除）
						reportPageCheckInfo.setData_status("2");
						reportPageCheckInfoService.save(reportPageCheckInfo);	// 更新报告页单独审核信息表审核状态
						
						String page_name = reportPageCheckInfo.getPage_name();
		        		if (StringUtil.isEmpty(page_name)) {
		        			// 报告页索引信息
		        			List<ReportItem> itemlist = reportItemService.queryByReportId(inspectionInfo.getReport_type());
		        			for (ReportItem reportItem : itemlist) {
		            			if (reportItem.getPage_index().equals(reportPageCheckInfo.getPage_index())) {
		            				page_name = reportItem.getItme_name();
		    					}
		    				}
						}
						
						logService.setLogs(ids[i], "报告页【"+page_name+"】单独审核", "结论：审核不通过（" + revise_remark + "）", curUser.getId(), curUser
								.getName(), new Date(), request.getRemoteAddr());
					}
				}
				String flow_name = inspectionInfo.getFlow_note_name();
				// 当前处于“报告审核”环节中，分页单独审核不通过时，业务信息才自动退回至“报告录入”环节
				if ("报告审核".equals(flow_name)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ins_info_id", ids[i]);
					map.put("acc_id", acc_ids[i]);
					map.put("revise_conclusion", revise_conclusion);
					map.put("op_time", op_time);
					map.put("type", type);
					map.put("flow_num", flow_num);
					map.put("revise_remark", "结论：不通过\n" + revise_remark);
					// 回退到上一步（报告录入）
					inspectionService.returnReport(map, request);
				}
			}
		}
		return JsonWrapper.successWrapper(isBatch);
	}
}
