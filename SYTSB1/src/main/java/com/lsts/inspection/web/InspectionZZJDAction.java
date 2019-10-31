package com.lsts.inspection.web;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.IAttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.handle.export.RtSaveAsst;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.ImageTool;
import com.lsts.common.service.CodeTablesService;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeePrinterService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJD;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.ReportItemValue;
import com.lsts.inspection.bean.SysAutoIssueLog;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.lsts.inspection.service.InspectionZZJDService;
import com.lsts.inspection.service.ReportItemValueService;
import com.lsts.inspection.service.SysAutoIssueLogService;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.Report;
import com.lsts.report.bean.ReportDraw;
import com.lsts.report.bean.ReportItem;
import com.lsts.report.service.ReportDrawService;
import com.lsts.report.service.ReportItemService;
import com.lsts.report.service.ReportService;
import com.ming.webreport.MREngine;
import com.scts.payment.bean.InspectionInfoDTO;

import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 制造监督检验报检业务控制器
 * 
 * @ClassName InspectionZZJDAction
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-13 下午05:20:00
 */
@Controller
@RequestMapping("inspection/zzjd")
public class InspectionZZJDAction extends
		SpringSupportAction<InspectionZZJD, InspectionZZJDService> {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private InspectionZZJDService inspectionZZJDService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportItemService reportItemService;
	@Autowired
	private EmployeePrinterService employeePrinterService;
	@Autowired
	private ImageTool imageTool;
	@Autowired
	private InspectionZZJDInfoService inspectionZZJDInfoService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private ReportDrawService reportDrawService;
	@Autowired
	private CodeTablesService codeTablesService;
	@Autowired
	private ReportItemValueService reportItemValueService;
	@Autowired
	private InspectionService inspectionService;
	@Autowired
	private InspectionCommonService inspectionCommonService;
	@Autowired
	private SysAutoIssueLogService sysAutoIssueLogService;
	@Autowired
	private IAttachmentManager attachmentManager;
	
	@RequestMapping(value = "getbglxName")
	@ResponseBody
	public HashMap<String, Object> getbglxName(HttpServletRequest request){
		String id=request.getParameter("bglxId");
		try {
			String name=inspectionZZJDService.getbglxNameById(id);
			return JsonWrapper.successWrapper(name);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	// 保存报告录入信息
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			@RequestBody
			InspectionZZJD inspectionZZJD) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			InspectionZZJD info = inspectionZZJDService.saveBasic(
					inspectionZZJD, request);
			if(info!=null){
				wrapper.put("success", true);
				wrapper.put("obj", info);
			}else{
				wrapper.put("success", false);
				wrapper.put("msg", "保存失败！系统存在重复监检员信息，请联系系统管理员！");
			}
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return wrapper;
	}

	// 获取报告录入信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return inspectionZZJDService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取报告录入信息失败！");
		}
	}

	// 删除检验
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			inspectionZZJDService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}

	// 提交数据到“报告送审”环节
	@RequestMapping(value = "commit")
	@ResponseBody
	public HashMap<String, Object> commit(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> wrapper = JsonWrapper.successWrapper();
		boolean isSuccess = inspectionZZJDService.commit(ids, request);
		wrapper.put("success", isSuccess);
		return wrapper;

	}

	// 查询待处理业务流程信息
	@RequestMapping(value = "getFlowInfo")
	@ResponseBody
	public ModelAndView getFlowInfo(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = inspectionZZJDService.getFlowInfo();
		ModelAndView mav = new ModelAndView("app/flow/misson_zzjd_index", map);
		return mav;

	}

	// 查询检验参数信息
	@RequestMapping(value = "flow_reportInput")
	@ResponseBody
	public ModelAndView flow_reportInput(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", request.getParameter("id"));
		map.put("report_type", request.getParameter("report_type"));
		map.put("device_type_code", request.getParameter("device_type_code"));
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap = inspectionZZJDService.flow_reportInput(map);
		ModelAndView mav = new ModelAndView("app/flow/set_report_zzjd_item",
				outMap);
		return mav;
	}

	// 保存报告设置信息
	@RequestMapping(value = "saveItem")
	@ResponseBody
	public Map<String, Object> saveItem(String dataStr,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("fk_inspection_info_id");
		String report_item = request.getParameter("report_item");
		String xsqnum = request.getParameter("xsq");

		map.put("id", id);
		map.put("report_item", report_item);
		map.put("dataMap", dataStr);
		map.put("xsqnum", xsqnum);
		inspectionZZJDService.saveItem(map);
		return JsonWrapper.successWrapper(id);
	}

	// 加载报告（录入）信息
	@RequestMapping(value = "reportInfoLoad")
	public ModelAndView reportInfoLoad(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ins_info_id = request.getParameter("ins_info_id");
		String report_type = request.getParameter("report_type");
		String type = request.getParameter("type");
		try {
			Map<String, Object> map = inspectionZZJDService
					.getReportInfo(ins_info_id);
			for (String key : map.keySet()) {
				request.setAttribute(key, map.get(key));
			}
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoId", ins_info_id);
		map.put("report_type", report_type);

		ModelAndView mav = null;
		if (type.equals("input")) {
			mav = new ModelAndView("app/flow/report/report_zzjd_input", map); // 报告录入页面
		} else {
			mav = new ModelAndView("app/flow/report/report_zzjd_audit"); // 报告审核页面
		}
		return mav;
	}

	/**
	 * 保存报告
	 * 
	 * @author GaoYa
	 */
	@RequestMapping(value = "reportInfoInput")
	public void flow_reportInfo_input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 业务ID
		String ins_info_id = request.getParameter("ins_info_id");
		System.out.println("=============报告保存开始============");
		WebApplicationContext webApplicationContext = ContextLoader
				.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext
				.getServletContext();

		MREngine engine = new MREngine(request, response, servletContext);
		String[] PostBackValue = engine.getParameterNames();
		inspectionZZJDService.saveReport(PostBackValue, engine, request
				.getParameter("report_type"), ins_info_id);
		System.out.println("=============报告保存结束============");
	}

	// 提交审核
	@RequestMapping(value = "subCheck")
	@ResponseBody
	public HashMap<String, Object> subCheck(HttpServletRequest request)
			throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		
		String ins_info_id = request.getParameter("infoId");
		String acc_id = request.getParameter("acc_id");
		String flow_num = request.getParameter("flow_num");
		String check_flow = request.getParameter("check_flow");
		String sub_op_id = request.getParameter("sub_op_id");
		String sub_op_name = request.getParameter("sub_op_name");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("flow_num", flow_num);
		map.put("flag", "指定下一步审核人员");
		map.put("next_sub_op", sub_op_id);
		map.put("next_op_name", sub_op_name);		
		try {
			String[] temp = ins_info_id.split(",");
			String[] tp = acc_id.split(",");
			for (int i = 0; i < temp.length; i++) {
				map.put("ins_info_id", temp[i]);
				
				String pre_device_type = "";
				InspectionInfo info = inspectionInfoService.get(temp[i]);
				// 获取监检业务信息
				InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService.getByInfoId(temp[i]);
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
				
				// 报告审批流程（0：二级审核 1：一级审核）
				// 安全阀校验报告只有一级审核，所以此处交由系统进行自动随机分配签发人
				if ("1".equals(check_flow)) {
					// 自动随机分配start......
					String next_op_id = ""; // 签发人ID（employee表主键）
					String next_op_name = ""; // 签发人姓名
					String rule = ""; // 实际分配规则（1、相同单位优先分配；2、量少优先分配；）

					// 除压力容器之外其他设备均由系统进行自动随机分配签发人
					Map<String, Object> infoMaps = inspectionCommonService.autoIssue(info, pre_device_type);
					if (Boolean.valueOf(String.valueOf(infoMaps.get("success")))) {
						next_op_id = String.valueOf(infoMaps.get("next_op_id"));
						next_op_name = String.valueOf(infoMaps.get("next_op_name"));
						rule = infoMaps.get("rule") != null ? String.valueOf(infoMaps.get("rule")) : "";
					} else {
						return JsonWrapper.failureWrapperMsg(String.valueOf(infoMaps.get("msg")));
					}

					if (StringUtil.isEmpty(next_op_id)) {
						return JsonWrapper.failureWrapperMsg("当前无在岗签字人员，详询质量部028-86607892！");
					} else {
						// 记录系统自动随机分配报告签发日志
						SysAutoIssueLog issueLog = new SysAutoIssueLog();
						issueLog.setBusiness_id(info.getId());
						issueLog.setReport_sn(info.getReport_sn());
						issueLog.setReport_com_name(info.getReport_com_name().trim());
						issueLog.setCheck_unit_id(info.getCheck_unit_id());
						issueLog.setDevice_type(pre_device_type);
						issueLog.setOp_user_id(user.getId());
						issueLog.setOp_user_name(user.getName());
						issueLog.setOp_action(Constant.SYS_OP_ACTION_AUTO_ISSUE);
						issueLog.setOp_remark(Constant.SYS_OP_ACTION_AUTO_ISSUE + "至【" + next_op_name + "】");
						issueLog.setOp_time(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern,
								DateUtil.getCurrentDateTime()));
						issueLog.setTo_user_id(next_op_id);
						issueLog.setTo_user_name(next_op_name);
						issueLog.setIssue_type(rule);
						sysAutoIssueLogService.save(issueLog);

						// 标记报告为系统自动随机分配签发报告
						info.setIs_auto_issue("1");
						inspectionInfoService.save(info);
					}
					map.put("next_sub_op", next_op_id);
					map.put("next_op_name", next_op_name);
					// 自动随机分配end......
				}
				map.put("acc_id", tp[i]);
				inspectionZZJDService.subFlowProcess(map, request);
			}
			map.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("提交失败！");
		}
		return map;
	}

	// 报告审核、报告签发时，进行批量退回报告
	@RequestMapping(value = "backReport")
	@ResponseBody
	public HashMap<String, Object> backReport(String dataStr,
			HttpServletRequest request) throws Exception {
		JSONObject dataMap = JSONObject.fromString(dataStr);
		String ins_info_id = request.getParameter("infoId");
		String acc_id = request.getParameter("acc_id");
		String flow_num = request.getParameter("flow_num");
		String type = request.getParameter("type");
		String revise_remark = dataMap.get("revise_remark").toString();
		if("请在此处填写报告退回原因！".equals(revise_remark)){
			revise_remark = "无";
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("flow_num", flow_num);
		if (type.equals("05")) { // type（05：报告签发 04：报告审核）
			String backStep = request.getParameter("backType");
			map.put("backStep", backStep);
			map.put("revise_remark", "签发结果：不通过，原因：" + revise_remark);
		} else {
			map.put("revise_remark", "审核结果：不通过，原因：" + revise_remark);
		}

		try {
			String[] temp = ins_info_id.split(",");
			String[] tp = acc_id.split(",");
			for (int i = 0; i < temp.length; i++) {
				map.put("ins_info_id", temp[i]);
				map.put("acc_id", tp[i]);
				inspectionZZJDService.backFlow(map, request);
			}
			map.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("批量退回失败！");
		}
		return map;
	}

	// 保存报告审核、签发
	@RequestMapping(value = "flow_saveCheck")
	@ResponseBody
	public Map<String, Object> flow_saveCheck(String dataStr,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		Map<String, Object> map = new HashMap<String, Object>();
		String type = request.getParameter("type");
		// 流程编号ID
		String acc_id = request.getParameter("acc_id");
		// 业务ID
		String ins_info_id = request.getParameter("infoId");
		// 步骤号
		String flow_num = request.getParameter("flow_num");
		String revise_conclusion = dataMap.get("revise_conclusion").toString();
		String op_time = dataMap.get("op_time").toString();
		String revise_remark = dataMap.get("revise_remark").toString();
		if("请在此处填写报告退回原因！".equals(revise_remark)){
			revise_remark = "无";
		}
		
		// 报告审批流程（0：二级审核 1：一级审核）
		String check_flow = request.getParameter("check_flow");
		// 1、获取报告业务信息
		InspectionInfo inspectionInfo = inspectionInfoService.get(ins_info_id);
		// 2、获取监检业务数据
		InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
				.getByInfoId(ins_info_id); 
		String pre_device_type = "";
		if(inspectionZZJDInfo != null){
			if(StringUtil.isNotEmpty(inspectionZZJDInfo.getDevice_type_code())){
				if("7310".equals(inspectionZZJDInfo.getDevice_type_code())){
					pre_device_type = "F";
				}else{
					// 获取设备类别前缀（大类）
					pre_device_type = inspectionZZJDInfo.getDevice_type_code().substring(0, 1); 
				}
			}
		}
		
		if(StringUtil.isEmpty(check_flow)){
			if(inspectionInfo!=null){
				if(StringUtil.isNotEmpty(inspectionInfo.getReport_type())){
					Report report = reportService.get(inspectionInfo.getReport_type());
					check_flow = report.getCheck_flow();
				}
			}
		}
		
		// 04：报告审核
		if (type.equals("04") && "0".equals(check_flow) && "1".equals(revise_conclusion)) { 
			// 获取报告自动分配签发人start......
			// 2017-07-03修改，包括压力容器，所有设备均由系统进行自动分配签发人
			String next_op_id = ""; // 签发人ID（employee表主键）
			String next_op_name = ""; // 签发人姓名
			String rule = ""; // 实际分配规则（1、相同使用单位优先分配；2、量少优先分配；）
			boolean is_auto_issue = false;	// 是否是自动分配
			
			/*if ("2".equals(pre_device_type)) {
				next_op_id = dataMap.get("next_sub_op").toString();
				next_op_name = dataMap.get("next_op_name").toString();
				is_auto_issue = false;
			} else {*/
				Map<String, Object> infoMaps = inspectionCommonService.autoIssue(inspectionInfo, pre_device_type);
				if (Boolean.valueOf(String.valueOf(infoMaps.get("success")))) {
					next_op_id = String.valueOf(infoMaps.get("next_op_id"));
					next_op_name = String.valueOf(infoMaps.get("next_op_name"));
					rule = infoMaps.get("rule") != null ? String.valueOf(infoMaps.get("rule")) : "";
				} else {
					return JsonWrapper.failureWrapperMsg(String.valueOf(infoMaps.get("msg")));
				}
				is_auto_issue = true;
			//}

			if (StringUtil.isEmpty(next_op_id)) {
				return JsonWrapper.failureWrapperMsg("当前无在线签字人员，详询质量部028-86607892！");
			} else {
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
					issueLog.setOp_remark(Constant.SYS_OP_ACTION_AUTO_ISSUE + "至【" + next_op_name + "】");
					issueLog.setOp_time(
							DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime()));
					issueLog.setTo_user_id(next_op_id);
					issueLog.setTo_user_name(next_op_name);
					issueLog.setIssue_type(rule);
					sysAutoIssueLogService.save(issueLog);

					// 标记报告为系统自动随机分配签发报告
					inspectionInfo.setIs_auto_issue("1");
					inspectionInfoService.save(inspectionInfo);
				}
			}
			map.put("next_sub_op", next_op_id);
			map.put("next_op_name", next_op_name);
			// 获取报告自动分配签发人end......
		}
		map.put("ins_info_id", ins_info_id);
		map.put("acc_id", acc_id);
		map.put("revise_conclusion", revise_conclusion);
		map.put("op_time", op_time);
		map.put("type", type);
		map.put("flow_num", flow_num);

		// 报告审核（type：04），如果审核通过（revise_conclusion：1），则进入下一个环节；如果审核不通过（revise_conclusion：2），则返回上一环节
		// 报告签发（type：05），如果审核通过（revise_conclusion：1），则进入下一个环节；如果审核不通过（revise_conclusion：2），则返回上一环节（报告审核）或报告送审环节（可进行报告修改）
		if (StringUtil.isEmpty(revise_remark.trim())) {
			revise_remark = "1".equals(revise_conclusion) ? "无" : "未填写";
		}
		if ("1".equals(revise_conclusion) && type.equals("04")) {
			map.put("revise_remark", "审核结果：通过，备注：" + revise_remark + "。");
			if("1".equals(check_flow)){
				map.put("flag", "0");	// 如果是0就不用指定下一步操作人
			}else{
				map.put("flag", "参数判断是否获取下一步指定人");
			}
			inspectionZZJDService.flow_saveCheck(map); // 1、报告审核时，更新业务信息
			inspectionZZJDService.subFlowProcess(map, request); // 2、提交到下一步步骤
		} else if ("1".equals(revise_conclusion) && type.equals("05")) {
			map.put("revise_remark", "签发结果：通过，备注：" + revise_remark + "。");
			map.put("flag", "0"); // 如果flag是0就不用指定下一步操作人
			inspectionZZJDService.flow_saveCheck(map); // 1、报告签发时，更新业务信息
			inspectionZZJDService.subFlowProcess(map, request); // 2、提交到下一步步骤
		} else if ("2".equals(revise_conclusion) && type.equals("04")) {
			map.put("revise_remark", "审核结果：不通过，原因：" + revise_remark + "。");
			inspectionZZJDService.backFlow(map, request); // 回退到上一步
		} else if ("2".equals(revise_conclusion) && type.equals("05")) {
			String backStep = request.getParameter("backType");
			map.put("revise_remark", "签发结果：不通过，原因：" + revise_remark + "。");
			map.put("backStep", backStep);
			inspectionZZJDService.backFlow(map, request);
		}
		return JsonWrapper.successWrapper(ins_info_id);
	}

	/**
	 * 打印报告show
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showPrint")
	public String showPrint(HttpServletRequest request, String id)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String userId = user.getSysUser().getId();
		String printType = request.getParameter("printType"); // 打印方式（1：补打
		// else 流程中打印）
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (StringUtil.isNotEmpty(printType) && "1".equals(printType)) {
			mapList = inspectionZZJDService.queryZZJDInfo(id);
		} else {
			mapList = inspectionZZJDService.queryZZJDInfo(id, userId);
		}
		request.getSession().setAttribute("mapList", mapList);
		return "app/query/report_zzjd_print_left";
	}

	/**
	 * 打印报告
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @param report_id --
	 *            检验报告ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "doPrint")
	public String doPrint(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取打印数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/query/report_zzjd_print";
	}

	/**
	 * 获取打印内容
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @param report_id --
	 *            检验报告ID
	 * @return
	 * @throws Exception
	 */
	private void getPrintData(HttpServletRequest request, String id,
			String report_id) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		InspectionInfo inspectionInfo = inspectionInfoService.get(id); // 1、获取业务信息
		InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
				.getByInfoId(id); // 2、获取监督检验明细数据

		if (StringUtil.isEmpty(report_id)) {
			report_id = inspectionInfo.getReport_type();
		}
		Report report = reportService.get(report_id);
		List<ReportItem> reportItemList = reportItemService
				.queryByReportId(report_id); // 报告项目
		List<ReportItemValue> reportItemValueList = reportItemValueService
				.queryByInspectionInfoId(id, report_id); // 报告检验项目

		String printer_name = employeePrinterService.queryPrinterName(emp.getId(), Constant.PRINTER_TYPE_R);
		// 检验人员电子签名
		request.setAttribute("check_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getCheck_op_id()));
		// 审核人员电子签名
		request.setAttribute("examine_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getExamine_id()));
		// 签发人员电子签名
		request.setAttribute("issue_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getIssue_id()));

		Map<String,Object> zzjdInfoMap = new HashMap<String,Object>();
		zzjdInfoMap = inspectionService.beanToMap(inspectionZZJDInfo);
		
		request.getSession().setAttribute("report_id", report_id); // 报告ID
		request.getSession().setAttribute("inspectionInfo", inspectionInfo); // 业务信息
		request.getSession().setAttribute("INSPECTIONZZJDINFO",
				zzjdInfoMap); // 监督检验明细数据
		request.getSession().setAttribute("report", report); // 报告信息
		request.getSession().setAttribute("reportItemList", // 报告项目
				reportItemList);
		request.getSession().setAttribute("reportItemValueList", // 检查项目
				reportItemValueList);
		request.getSession().setAttribute("printer_name", printer_name); // 人员打印机名称
	}

	// 报告打印
	@RequestMapping(value = "flow_print")
	@ResponseBody
	public synchronized HashMap<String, Object> flow_print(
			HttpServletRequest request) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String ins_info_id = request.getParameter("infoId");
			String acc_id = request.getParameter("acc_id");
			String flow_num = request.getParameter("flow_num");
			String ids[] = ins_info_id.split(",");
			String acc_ids[] = acc_id.split(",");
			for (int i = 0; i < ids.length; i++) {
				map.put("ins_info_id", ids[i]);
				map.put("acc_id", acc_ids[i]);
				map.put("flow_num", flow_num);
				map.put("flag", "0"); // flag如果是0就不用指定下一步操作人
				inspectionZZJDService.flow_print(map); // 1、打印报告时，更新业务信息（更新打印时间）
				inspectionZZJDService.subFlowProcess(map, request); // 2、提交至下一步流程
			}
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取信息失败！");
		}
	}

	/**
	 * 保存报告打印记录
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @param report_id --
	 *            检验报告ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "insertPrintRecord")
	public String insertPrintRecord(HttpServletRequest request) {
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String id = request.getParameter("id");
			getPrintData(request, id, null);
			logService.setLogs(id, Constant.SYS_OP_ACTION_PRINT,
					Constant.SYS_OP_ACTION_PRINT, curUser.getId(), curUser
							.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			log.error("保存报告打印日志出错：" + e.getMessage());
			e.printStackTrace();
			log.debug(e.toString());
		}

		return "app/query/report_zzjd_print";
	}

	/**
	 * 报告领取（保存领取登记）
	 * 
	 * @param request
	 * @param reportDraw
	 * @throws Exception
	 */
	@RequestMapping(value = "flow_saveDraw")
	@ResponseBody
	public HashMap<String, Object> flow_saveDraw(HttpServletRequest request,
			ReportDraw reportDraw) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			String acc_id = request.getParameter("acc_id");
			String[] acc_ids = acc_id.split(",");
			String flow_num = request.getParameter("flow_num");
			String info_id = reportDraw.getInspectionInfo().getId();
			String[] info_ids = info_id.split(",");
			List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
			for (int i = 0; i < info_ids.length; i++) {
				ReportDraw reportDraw1 = new ReportDraw();
				reportDraw1.setIdcard(reportDraw.getIdcard());
				reportDraw1.setJob_unit(reportDraw.getJob_unit());
				reportDraw1.setLinkmode(reportDraw.getLinkmode());
				reportDraw1.setPulldown_op(reportDraw.getPulldown_op());
				reportDraw1.setInspectionInfo(reportDraw.getInspectionInfo());
				reportDraw1.setRemark(reportDraw.getRemark());
				reportDraw1.setPulldown_time(new Date());
				reportDraw1.getInspectionInfo().setId(info_ids[i]);
				reportDraw1.setReport_sn(reportDraw.getReport_sn());
				reportDrawService.save(reportDraw1);

				// 流程参数
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("acc_id", acc_ids[i]);
				map.put("ins_info_id", info_ids[i]);
				map.put("flag", "0"); // 如果flag是0就不用指定下一步操作人
				map.put("flow_num", flow_num);
				inspectionZZJDService.subFlowProcess(map, request);

				// 获取报告领取单打印内容
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(info_ids[i]);
				if (inspectionInfo != null) {
					InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
					inspectionInfoDTO.setId(inspectionInfo.getId());
					inspectionInfoDTO
							.setReport_com_name(StringUtil
									.isNotEmpty(inspectionInfo
											.getReport_com_name()) ? inspectionInfo
									.getReport_com_name()
									: reportDraw.getJob_unit()); // 制造单位
					inspectionInfoDTOList.add(inspectionInfoDTO);
				}
			}

			wrapper.put("success", true);
			reportDraw.setPulldown_time(new Date());
			reportDraw.setInspectionInfoDTOList(inspectionInfoDTOList);
			// 获取报告领取打印内容
			wrapper.put("printContent", reportDraw);
			wrapper.put("op_user_name", curUser.getName());
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "保存报告领取信息失败，请重试！");
			log.debug(e.toString());
		}
		return wrapper;
	}

	// 报告归档
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "flow_reportEnd")
	@ResponseBody
	public HashMap<String, Object> flow_reportEnd(HttpServletRequest request)
			throws Exception {
		HashMap map = new HashMap();
		try {
			String info_id = request.getParameter("infoId");
			String process_id = request.getParameter("process_id");
			String flow_num = request.getParameter("flow_num");
			String ids[] = info_id.split(",");
			String p_ids[] = process_id.split(",");
			for (int i = 0; i < ids.length; i++) {
				map.put("infoId", ids[i]);
				map.put("process_id", p_ids[i]);
				map.put("flow_num", flow_num);
				inspectionZZJDService.flow_reportEnd(map, request);
			}
			map.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("归档失败！");
		}
		return map;
	}

	/**
	 * 查看报告
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @param report_id --
	 *            检验报告ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showReport")
	public String showReport(HttpServletRequest request, String id)
			throws Exception {
		getPrintData(request, id, null);
		return "app/query/report_zzjd_detail";
	}

	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = inspectionZZJDService.getFlowStep(request
				.getParameter("ins_info_id"));
		ModelAndView mav = new ModelAndView("app/flow/flow_card", map);
		return mav;
	}

	// 报告作废
	@RequestMapping(value = "delReport")
	@ResponseBody
	public HashMap<String, Object> delReport(HttpServletRequest request)
			throws Exception {
		String ids = request.getParameter("ids");
		inspectionZZJDService.delReport(ids, request);
		return JsonWrapper.successWrapper(ids);
	}

	// 撤回报告（未审核状态下的报告才能撤回）
	@RequestMapping(value = "revokeReport")
	@ResponseBody
	public HashMap<String, Object> revokeReport(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ins_info_id = request.getParameter("infoId");
		String acc_id = request.getParameter("acc_id");
		String flow_num = request.getParameter("flow_num");
		String[] ids = ins_info_id.split(",");
		String[] tp = acc_id.split(",");
		for (int i = 0; i < ids.length; i++) {
			map.put("ins_info_id", ids[i]);
			map.put("acc_id", tp[i]);
			map.put("flow_num", flow_num);
			inspectionZZJDService.backFlow(map, request);
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 上传Excel并解析数据（暂只支持2003版本的excel）
	 * 
	 * @param request
	 * @param fileId --
	 *            文件Id
	 * @return JSON
	 * @author GaoYa
	 * @date 2015-01-14 下午01:30:00
	 */
	@RequestMapping(value = "importData")
	@ResponseBody
	public HashMap<String, Object> parse(String fileId, String report_type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			File file = new File(attachmentManager.download(fileId).getFilePath());
			if (file.canRead()) {
				List<InspectionZZJDInfo> infoList = new ArrayList<InspectionZZJDInfo>();
				Map<String, Object> device_types = new HashMap<String, Object>(); // 压力容器相关的所有设备类别码表值集合
				List<String> errors = new ArrayList<String>(); // 错误信息

				// 获取设备类别
				device_types = codeTablesService.getDevice_types(
							Constant.CT_BASE_DEVICE_TYPE, "");

				// 解析Excel文件
				parseExcelDatas(file, report_type, infoList, device_types,
						errors);
				if (errors.isEmpty()) {
					// 解析完Excel后将文件删除
					// file.delete();
					// 返回解析数据到页面
					map.put("infoList", infoList);
					map.put("file_path", file.getName());
					map.put("success", true);
					return map;
					//return JsonWrapper.successWrapper(infoList);
				} else {
					//file.delete();
					return JsonWrapper.failureWrapper("导入失败：" + errors);
				}
			} else {
				//file.delete();
				return JsonWrapper.failureWrapper("导入文件不可读！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//file.delete();
			log.debug(e.toString());
			return JsonWrapper.failureWrapper("导入失败：" + e.getMessage());
		}
	}

	/**
	 * 解析Excel
	 * 
	 * @param xlsfile --
	 *            Excel文件
	 * @param infoList --
	 *            制造监督检验明细数据集合
	 * @param errors --
	 *            错误信息
	 * @param request
	 * @return
	 * @author GaoYa
	 * @date 2015-01-14 下午01:45:00
	 */
	public void parseExcelDatas(File xlsfile, String device_type,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors)
			throws Exception {
		Workbook excelfile = null;
		try {
			// 创建工作簿
			excelfile = createWorkbook(xlsfile);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("读excel文件失败！");
			return;
		}

		// 获取Excel文件中的第一个工作表对象
		Sheet sheet = excelfile.getSheetAt(0);

		if ("1".equals(device_type)) {
			// 读取工作表数据（压力容器制造监督检验）
			readYLRQZZJJExcelSheet(sheet, infoList, device_types, errors);
		} else if ("2".equals(device_type)) {
			// 读取工作表数据（车用气瓶安装监督检验）
			readCYQPAZJJExcelSheet(sheet, infoList, device_types, errors);
		} else if ("3".equals(device_type)) {
			// 读取工作表数据（批量制造压力容器监督检验证书）
			readPLZZYLRQJJExcelSheet(sheet, infoList, device_types, errors);
		} else if ("4".equals(device_type)) {
			// 读取工作表数据（压力容器改造与重大修理监督检验证书）
			readYLRQGZYZDXLJJExcelSheet(sheet, infoList, device_types, errors);
		} else if ("5".equals(device_type)) {
			// 读取工作表数据（爆破片装置安全性能监督检验证书）
			readBPPZZAQXNJJExcelSheet(sheet, infoList, device_types, errors);
		}else if ("7".equals(device_type)) {
			// 读取工作表数据（压力容器受压元件、受压部件制造监督检验证书）
			readCYFTZZJJExcelSheet(sheet, infoList, device_types, errors);
		}else if ("6".equals(device_type)) {
			// 读取工作表数据（气瓶制造监督检验证书）
			readQPZZJJExcelSheet(sheet, infoList, device_types, errors);
		}else if ("8".equals(device_type)) {
			// 读取工作表数据（安全阀安全性能监督检验证书）
			readAQFAQXNJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("9".equals(device_type)) {
			// 读取工作表数据（压力管道元件监检证书（埋弧焊钢管））
			readYLGDYJ_MHHGG_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("10".equals(device_type)) {
			// 读取工作表数据（压力管道元件监检证书（聚乙烯管））
			readYLGDYJ_JYXG_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("11".equals(device_type)) {
			// 读取工作表数据（压力管道元件监检证书（低温管件））
			// 低温管件的制造监检证书模板与埋弧焊钢管一样，所以此处调用同一个方法
			readYLGDYJ_MHHGG_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("12".equals(device_type)) {
			// 读取工作表数据（金属常压罐体制造委托监督检验证书）
			readYLRQ_JSCYGT_ZZWTJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("13".equals(device_type)) {
			// 读取工作表数据（压力管道安装安全质量监督检验证书）
			readYLGD_AZAQZJJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("14".equals(device_type)) {
			// 读取工作表数据（特种设备制造监检证书（锅炉））
			readGL_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("15".equals(device_type)) {
			// 读取工作表数据（特种设备制造监检证书（锅炉部件、组件））
			readGL_BJZJ_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("16".equals(device_type)) {
			// 读取工作表数据（特种设备制造监督检验证书（储气井））
			// 储气井的制造监检证书模板与压力容器一样，所以此处调用同一个方法
			readYLRQZZJJExcelSheet(sheet, infoList, device_types, errors);
		}else if ("17".equals(device_type)) {
			// 读取工作表数据（锅炉设计文件安全鉴定及节能审查报告）
			readGLSJWJJDSCExcelSheet(sheet, infoList, device_types, errors);
		}else if ("18".equals(device_type)) {
			// 读取工作表数据（安全阀校验报告）
			readAQFJYBGExcelSheet(sheet, infoList, device_types, errors);
		}else if ("19".equals(device_type)) {
			// 读取工作表数据（特种设备制造监督检验证书（出口锅炉及锅炉部件、组件））
			readCKGL_BJZJ_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("20".equals(device_type)) {
			// 读取工作表数据（特种设备制造监督检验证书（出口压力容器））
			readCKYLRQ_ZZJDJYZSExcelSheet(sheet, infoList, device_types, errors);
		}else if ("21".equals(device_type)) {
			// 读取工作表数据（封头委托制造监督检验证书）
			readCYFTZZJJExcelSheet(sheet, infoList, device_types, errors);
		}
		
		// 读取工作表数据（超高压水晶釜委托检验报告）
		// 2016-11-29取消批量导入功能，改使用传统模式录入报告
		// readCGYSJFWTJYBGExcelSheet(sheet, infoList, device_types, errors);
		
		

		// 遍历工作簿中的工作表
		/*
		 * for (int numSheets = 0; numSheets <
		 * excelfile.getNumberOfSheets();numSheets++){ if
		 * (excelfile.getSheetAt(numSheets) != null){ Sheet sheet =
		 * excelfile.getSheetAt(numSheets); // 根据序号获取工作表
		 * if(sheet.getSheetName().contains("Sheet1")) { // 解析Sheet1工作表
		 * readExcelSheet1(sheet, infoList, errors); } } }
		 */
	}
	
	/**
	 * 解析工作表（锅炉设计文件安全鉴定及节能审查报告）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2016-03-13 上午11:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readGLSJWJJDSCExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.GL_SJWJJDSC_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.GL_SJWJJDSC_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.GL_SJWJJDSC_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.GL_SJWJJDSC_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.GL_SJWJJDSC_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row,
					Constant.GL_SJWJJDSC_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 序号
			/* 2016-05-04锅炉事业部刘亚玲提出，序号原手填的方式改为由报告书编号中的序号自动生成
			String device_no = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(device_no.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 序号不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setDevice_no(device_no);
			}
			*/
			// 鉴定人
			String inspection_user_name1 = readCellData(row, 0);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 鉴定人不能为空！");
				return;
			}

			// 鉴定日期
			String inspection_date = readCellData(row, 1);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if (inspection_date.trim().length() == 10) {
					if (inspection_date.trim().contains("/")
							|| inspection_date.trim().contains(".")) {
						inspection_date = inspection_date.trim().replaceAll(
								"[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(
										Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 鉴定日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				} else {
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 鉴定日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 鉴定日期不能为空！");
				return;
			}

			// 图号
			String device_pic_no = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 图号不能为空！");
				return;
			}

			// 型号
			String device_model = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 型号不能为空！");
				return;
			}

			// 锅炉名称
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 锅炉不能为空！");
				return;
			}

			// 制造单位
			String made_unit_name = readCellData(row, 5);
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 预收金额
			String advance_fees = readCellData(row, 6);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			// 锅炉类别
			String device_type_name = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 锅炉类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 锅炉类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 锅炉类别不能为空！");
				return;
			}
			// 设计属性
			String design_property = readCellData(row, 8);
			if (StringUtil.isNotEmpty(design_property.trim())) {
				inspectionZZJDInfo.setDesign_property(design_property.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计属性不能为空！");
				return;
			}

			// 鉴定属性
			String check_property = readCellData(row, 9);
			if (StringUtil.isEmpty(check_property.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 鉴定属性不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setCheck_property(check_property);
			}

			// 审查属性
			String exam_property = readCellData(row, 10);
			if (StringUtil.isNotEmpty(exam_property.trim())) {
				inspectionZZJDInfo.setExam_property(exam_property.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行审查属性不能为空！");
				return;
			}

			// 额定出力（t/h）或（MW）
			String rated_output = readCellData(row, 11);
			if (StringUtil.isNotEmpty(rated_output.trim())) {
				inspectionZZJDInfo.setRated_output(rated_output.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 额定出力（t/h）或（MW）不能为空！");
				return;
			}

			// 额定出口压力（MPa）
			String device_pressure = readCellData(row, 12);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 额定出口压力（MPa）不能为空！");
				return;
			}

			// 锅炉给水（回水）温度（℃）
			String use_temp2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(use_temp2.trim())) {
				inspectionZZJDInfo.setUse_temp2(use_temp2.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 锅炉给水（回水）温度（℃）不能为空！");
				return;
			}

			// 额定出口温度（℃）
			String use_temp = readCellData(row, 14);
			if (StringUtil.isNotEmpty(use_temp.trim())) {
				inspectionZZJDInfo.setUse_temp(use_temp.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 额定出口温度（℃）不能为空！");
				return;
			}

			// 设计燃料种类
			String design_fuel_type = readCellData(row, 15);
			if (StringUtil.isNotEmpty(design_fuel_type.trim())) {
				inspectionZZJDInfo.setDesign_fuel_type(design_fuel_type.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计燃料种类不能为空！");
				return;
			}

			// 结构形式
			String struct_type = readCellData(row, 16);
			if (StringUtil.isNotEmpty(struct_type.trim())) {
				inspectionZZJDInfo.setStruct_type(struct_type.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 结构形式不能为空！");
				return;
			}

			// 燃烧方式
			String burn_mode = readCellData(row, 17);
			if (StringUtil.isNotEmpty(burn_mode.trim())) {
				inspectionZZJDInfo.setBurn_mode(burn_mode.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 燃烧方式不能为空！");
				return;
			}

			// 燃料低位发热量不低于（MJ/Kg）
			String calorific_value = readCellData(row, 18);
			if (StringUtil.isNotEmpty(calorific_value.trim())) {
				inspectionZZJDInfo.setCalorific_value(calorific_value.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 燃料低位发热量不低于（MJ/Kg）不能为空！");
				return;
			}

			// 燃烧机型号
			String burner_model = readCellData(row, 19);
			if (StringUtil.isNotEmpty(burner_model.trim())) {
				inspectionZZJDInfo.setBurner_model(burner_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 燃烧机型号不能为空！");
				return;
			}

			// 稳定工况范围（%）
			String work_range = readCellData(row, 20);
			if (StringUtil.isNotEmpty(work_range.trim())) {
				inspectionZZJDInfo.setWork_range(work_range.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行稳定工况范围（%）不能为空！");
				return;
			}

			// 设计热效率（%）
			String design_heat = readCellData(row, 21);
			if (StringUtil.isNotEmpty(design_heat.trim())) {
				inspectionZZJDInfo.setDesign_heat(design_heat.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行设计热效率（%）不能为空！");
				return;
			}

			// 总图编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_ztbh = readCellData(row, 22);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_ztbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_ztbh(glsjwj_jdqd_ztbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 总图编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_bttbh = readCellData(row, 23);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_bttbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_bttbh(glsjwj_jdqd_bttbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 对流管束编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_dlgsbh = readCellData(row, 24);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_dlgsbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_dlgsbh(glsjwj_jdqd_dlgsbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 对流管束编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_gtbh = readCellData(row, 25);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_gtbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_gtbh(glsjwj_jdqd_gtbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 过热器编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_grqbh = readCellData(row, 26);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_grqbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_grqbh(glsjwj_jdqd_grqbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 过热器编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 减温器编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_jwqbh = readCellData(row, 27);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_jwqbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_jwqbh(glsjwj_jdqd_jwqbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 减温器编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 省煤器编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_smqbh = readCellData(row, 28);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_smqbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_smqbh(glsjwj_jdqd_smqbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 省煤器编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 再热器编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_zrqbh = readCellData(row, 29);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_zrqbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_zrqbh(glsjwj_jdqd_zrqbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 再热器编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_rylxttbh = readCellData(row, 30);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_rylxttbh.trim())) {
				inspectionZZJDInfo.setGlsjwj_jdqd_rylxttbh(glsjwj_jdqd_rylxttbh
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 强度计算汇总表编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_qdjshzbbh = readCellData(row, 31);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_qdjshzbbh.trim())) {
				inspectionZZJDInfo
						.setGlsjwj_jdqd_qdjshzbbh(glsjwj_jdqd_qdjshzbbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 强度计算汇总表编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}
			//2018年9月份增加
			// 主给水管道编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_zjsgdbh = readCellData(row, 32);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_zjsgdbh.trim())) {
				inspectionZZJDInfo
						.setGlsjwj_jdqd_zjsgdbh(glsjwj_jdqd_zjsgdbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 主给水管道编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}
			
			// 主蒸汽管道编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_zzqgdbh = readCellData(row, 33);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_zzqgdbh.trim())) {
				inspectionZZJDInfo
						.setGlsjwj_jdqd_zzqgdbh(glsjwj_jdqd_zzqgdbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 主蒸汽管道编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}
			
			// 再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_zrzqldgdbh = readCellData(row, 34);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_zrzqldgdbh.trim())) {
				inspectionZZJDInfo
						.setGlsjwj_jdqd_zrzqldgdbh(glsjwj_jdqd_zrzqldgdbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}
			
			// 再热蒸汽热段管道编号/设计/审核/批准人员/批准日期/备注
			String glsjwj_jdqd_zrzqrdgdbh = readCellData(row, 35);
			if (StringUtil.isNotEmpty(glsjwj_jdqd_zrzqrdgdbh.trim())) {
				inspectionZZJDInfo
						.setGlsjwj_jdqd_zrzqrdgdbh(glsjwj_jdqd_zrzqrdgdbh.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line
						+ "行 再热蒸汽热段管道编号/设计/审核/批准人员/批准日期/备注不能为空！");
				return;
			}

			// 制造许可级别
			String made_license_level = readCellData(row, 36);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可级别不能为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 37);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号不能为空！");
				return;
			}

			// 制造单位地址
			String made_unit_addr = readCellData(row, 38);
			if (StringUtil.isEmpty(made_unit_addr.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位地址不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_addr(made_unit_addr);
			}

			// 备注
			String remark = readCellData(row, 39);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 本体耗钢量（吨）
			String weight_steels = readCellData(row, 40);
			if (StringUtil.isNotEmpty(weight_steels.trim())) {
				inspectionZZJDInfo.setWeight_steels(weight_steels.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 本体耗钢量（吨）不能为空！");
				return;
			}
			
			// 是否自编号
			String is_self_sn = readCellData(row, 41);
			if (StringUtil.isNotEmpty(is_self_sn.trim())) {
				if("是".equals(is_self_sn.trim())){
					inspectionZZJDInfo.setIs_self_sn("1");	// 是否自编号（0：否 1：是）
				}else{
					inspectionZZJDInfo.setIs_self_sn("0");
				}			
			}else{
				inspectionZZJDInfo.setIs_self_sn("0");
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}

	/**
	 * 解析工作表（安全阀安全性能监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-23 下午04:26:00
	 */
	@SuppressWarnings("unchecked")
	private void readAQFAQXNJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.AQF_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.AQF_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.AQF_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.AQF_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.AQF_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.AQF_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 许可证编号
			String made_license_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品型号
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品型号为空！");
				return;
			}

			// 产品适用温度
			String use_temp = readCellData(row, 5);
			if (StringUtil.isNotEmpty(use_temp.trim())) {
				inspectionZZJDInfo.setUse_temp(use_temp.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品适用温度为空！");
				return;
			}

			// 产品适用介质
			String device_medium = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品适用介质为空！");
				return;
			}

			// 出厂编号1
			String factory_code_1 = readCellData(row, 7);
			if (StringUtil.isNotEmpty(factory_code_1.trim())) {
				inspectionZZJDInfo.setFactory_code_1(factory_code_1
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 出厂编号1为空！");
				return;
			}
			
			// 出厂编号2
			String factory_code_2 = readCellData(row, 8);
			if (StringUtil.isNotEmpty(factory_code_2.trim())) {
				inspectionZZJDInfo.setFactory_code_2(factory_code_2
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 出厂编号2为空！");
				return;
			}

			// 产品数量
			String device_count = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品数量为空！");
				return;
			}

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 14);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}

	/**
	 * 解析工作表（车用气瓶安装监督检验）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            安装监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-01-14 下午02:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readCYQPAZJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.QP_AZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.QP_AZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.QP_AZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.QP_AZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.QP_AZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.QP_AZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 设备类别（车用气瓶属于无缝气瓶）
			inspectionZZJDInfo.setDevice_type_code("2310"); // 无缝气瓶：2310

			// 车辆牌号
			String device_car_num = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(device_car_num.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 车辆牌号不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setDevice_car_num(device_car_num.trim());
			}

			// 安装单位名称
			String install_unit_name = readCellData(row, 1);
			if (StringUtil.isNotEmpty(install_unit_name.trim())) {
				inspectionZZJDInfo.setInstall_unit_name(install_unit_name
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装单位名称不能为空！");
				return;
			}

			// 安装许可证编号
			String install_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(install_license_code.trim())) {
				inspectionZZJDInfo.setInstall_license_code(install_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装许可证编号不能为空！");
				return;
			}

			// 安装日期
			String install_date = readCellData(row, 3);
			if (StringUtil.isNotEmpty(install_date.trim())) {
				inspectionZZJDInfo.setInstall_date(install_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装日期不能为空！");
				return;
			}

			// 工作介质
			String device_medium = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 工作介质不能为空！");
				return;
			}

			// 公称压力
			String device_pressure = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 公称压力不能为空！");
				return;
			}

			// 气瓶制造单位
			String made_unit_name = readCellData(row, 6);
			if (StringUtil.isNotEmpty(made_unit_name.trim())) {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 气瓶制造单位为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 7);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期不能为空！");
				return;
			}

			// 气瓶编号
			String device_no = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 气瓶编号为空！");
				return;
			}
			
			// 设备代码
			String device_code = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码不能为空！");
				return;
			}

			/*// 气瓶文件审查
			String device_file_check = readCellData(row, 10);
			if (StringUtil.isNotEmpty(device_file_check.trim())) {
				inspectionZZJDInfo.setDevice_file_check(device_file_check
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 气瓶文件审查为空！");
				return;
			}

			// 文件审查日期
			String file_check_date = readCellData(row, 11);
			if (StringUtil.isNotEmpty(file_check_date.trim())) {
				inspectionZZJDInfo.setFile_check_date(file_check_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 文件审查日期为空！");
				return;
			}

			// 安装资料审查
			String install_data_check = readCellData(row, 12);
			if (StringUtil.isNotEmpty(install_data_check.trim())) {
				inspectionZZJDInfo.setInstall_data_check(install_data_check
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装资料审查为空！");
				return;
			}

			// 安装资料审查日期
			String data_check_date = readCellData(row, 13);
			if (StringUtil.isNotEmpty(data_check_date.trim())) {
				inspectionZZJDInfo.setData_check_date(data_check_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装资料审查日期为空！");
				return;
			}

			// 气瓶外观检查
			String device_surface_check = readCellData(row, 14);
			if (StringUtil.isNotEmpty(device_surface_check.trim())) {
				inspectionZZJDInfo.setDevice_surface_check(device_surface_check
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 气瓶外观检查为空！");
				return;
			}

			// 气瓶外观检查日期
			String surface_check_date = readCellData(row, 15);
			if (StringUtil.isNotEmpty(surface_check_date.trim())) {
				inspectionZZJDInfo.setSurface_check_date(surface_check_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 气瓶外观检查日期为空！");
				return;
			}

			// 安装质量检查
			String install_quality_check = readCellData(row, 16);
			if (StringUtil.isNotEmpty(install_quality_check.trim())) {
				inspectionZZJDInfo
						.setInstall_quality_check(install_quality_check.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装质量检查为空！");
				return;
			}

			// 安装质量检查日期
			String quality_check_date = readCellData(row, 17);
			if (StringUtil.isNotEmpty(quality_check_date.trim())) {
				inspectionZZJDInfo.setQuality_check_date(quality_check_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装质量检查日期为空！");
				return;
			}

			// 泄漏试验确认
			String leak_test_check = readCellData(row, 18);
			if (StringUtil.isNotEmpty(leak_test_check.trim())) {
				inspectionZZJDInfo.setLeak_test_check(leak_test_check.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 泄漏试验确认为空！");
				return;
			}

			// 泄漏试验日期
			String leak_check_date = readCellData(row, 19);
			if (StringUtil.isNotEmpty(leak_check_date.trim())) {
				inspectionZZJDInfo.setLeak_check_date(leak_check_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 泄漏试验日期为空！");
				return;
			}

			// 安装竣工资料审查
			String finish_data_check = readCellData(row, 20);
			if (StringUtil.isNotEmpty(finish_data_check.trim())) {
				inspectionZZJDInfo.setFinish_data_check(finish_data_check
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装竣工资料审查为空！");
				return;
			}

			// 安装竣工资料审查日期
			String finish_check_date = readCellData(row, 21);
			if (StringUtil.isNotEmpty(finish_check_date.trim())) {
				inspectionZZJDInfo.setFinish_check_date(finish_check_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装竣工资料审查日期为空！");
				return;
			}
			
			// 对安装单位质量体系运转情况的评价
			String install_evaluate = readCellData(row, 22);
			if (StringUtil.isNotEmpty(install_evaluate.trim())) {
				inspectionZZJDInfo.setInstall_evaluate(install_evaluate.trim());
			}

			// 记事栏
			String remark = readCellData(row, 23);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}*/

			// 监检员1
			String inspection_user_name1 = readCellData(row, 10);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1不能为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}				
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期不能为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 13);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（安全阀校验）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2016-08-25 下午05:13:00
	 */
	@SuppressWarnings("unchecked")
	private void readAQFJYBGExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.AQF_JYBG_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.AQF_JYBG_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.AQF_JYBG_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.AQF_JYBG_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.AQF_JYBG_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.QP_AZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			
			List<String> report_sns = new ArrayList<String>();
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 设备类别（车用气瓶属于无缝气瓶）
			inspectionZZJDInfo.setDevice_type_code("7310"); // 安全阀：7310

			// 是否自编号（0：否 1：是）
			String is_self_sn = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isNotEmpty(is_self_sn.trim())) {
				if("是".equals(is_self_sn)){
					inspectionZZJDInfo.setIs_self_sn("1");
				}else{
					inspectionZZJDInfo.setIs_self_sn("0");	
				}
			}else{
				inspectionZZJDInfo.setIs_self_sn("0");	
			}

			// 报告编号
			String report_sn = readCellData(row, 1);
			if (StringUtil.isNotEmpty(report_sn.trim())) {
				if(report_sns.contains(report_sn.trim())){
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 报告编号重复，请检查您上传的excel数据是否重复！");
					return;
				}else{
					List<InspectionInfo> list = inspectionInfoService.queryInspectionInfo(report_sn.trim());
					if(list.isEmpty()){
						report_sns.add(report_sn
								.trim());
						inspectionZZJDInfo.setReport_sn(report_sn
								.trim());
					}else{
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 报告编号系统已存在，请检查！");
						return;
					}
				}
			}

			// 使用单位
			String com_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(com_name.trim())) {
				inspectionZZJDInfo.setCom_name(com_name
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用单位不能为空！");
				return;
			}

			// 单位地址
			String com_addr = readCellData(row, 3);
			if (StringUtil.isNotEmpty(com_addr.trim())) {
				inspectionZZJDInfo.setCom_addr(com_addr.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 单位地址不能为空！");
				return;
			}

			// 邮政编码
			String com_zip_code = readCellData(row, 4);
			if (StringUtil.isNotEmpty(com_zip_code.trim())) {
				inspectionZZJDInfo.setCom_zip_code(com_zip_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 邮政编码不能为空！");
				return;
			}

			// 阀门型号
			String device_model = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 阀门型号不能为空！");
				return;
			}

			// 执行标准
			String safely_tech_standard = readCellData(row, 6);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 执行标准不能为空！");
				return;
			}

			// 编号类型
			String code_type = readCellData(row, 7);
			if (StringUtil.isNotEmpty(code_type.trim())) {
				inspectionZZJDInfo.setCode_type(code_type.trim());
			}

			// 编号值
			String code_value = readCellData(row, 8);
			if (StringUtil.isNotEmpty(code_value.trim())) {
				inspectionZZJDInfo.setCode_value(code_value.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 编号值不能为空！");
				return;
			}
			
			// 联系人
			String check_op = readCellData(row, 9);
			if (StringUtil.isNotEmpty(check_op.trim())) {
				inspectionZZJDInfo.setCheck_op(check_op.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行  联系人部门为空！");
				return;
			}

			// 联系电话
			String check_tel = readCellData(row, 10);
			if (StringUtil.isNotEmpty(check_tel.trim())) {
				inspectionZZJDInfo.setCheck_tel(check_tel
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 联系电话不能为空！");
				return;
			}

			// 产品编号
			String device_no = readCellData(row, 11);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编号不能为空！");
				return;
			}

			// 安装位置
			String device_use_place = readCellData(row, 12);
			if (StringUtil.isNotEmpty(device_use_place.trim())) {
				inspectionZZJDInfo.setDevice_use_place(device_use_place
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装位置不能为空！");
				return;
			}

			// 安全阀类型
			String device_model_2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(device_model_2.trim())) {
				inspectionZZJDInfo.setDevice_model_2(device_model_2.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安全阀类型不能为空！");
				return;
			}

			// 工作压力
			String device_pressure = readCellData(row, 14);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 工作压力不能为空！");
				return;
			}

			// 工作介质
			String device_medium = readCellData(row, 15);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 工作介质不能为空！");
				return;
			}

			// 要求整定压力
			String yqzdyl = readCellData(row, 16);
			if (StringUtil.isNotEmpty(yqzdyl.trim())) {
				inspectionZZJDInfo
						.setYqzdyl(yqzdyl.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 要求整定压力不能为空！");
				return;
			}

			// 校验方式
			String check_method = readCellData(row, 17);
			if (StringUtil.isNotEmpty(check_method.trim())) {
				inspectionZZJDInfo.setCheck_method(check_method
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验方式不能为空！");
				return;
			}

			// 校验介质
			String check_medium = readCellData(row, 18);
			if (StringUtil.isNotEmpty(check_medium.trim())) {
				inspectionZZJDInfo.setCheck_medium(check_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验介质不能为空！");
				return;
			}

			// 整定压力
			String zdyl = readCellData(row, 19);
			if (StringUtil.isNotEmpty(zdyl.trim())) {
				inspectionZZJDInfo.setZdyl(zdyl.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 整定压力不能为空！");
				return;
			}

			// 密封试验压力
			String mfsyyl = readCellData(row, 20);
			if (StringUtil.isNotEmpty(mfsyyl.trim())) {
				inspectionZZJDInfo.setMfsyyl(mfsyyl
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 密封试验压力不能为空！");
				return;
			}

			// 校验结果
			String inspection_conclusion = readCellData(row, 21);
			if (StringUtil.isNotEmpty(inspection_conclusion.trim())) {
				inspectionZZJDInfo.setInspection_conclusion(inspection_conclusion
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验结果不能为空！");
				return;
			}
			
			// 维护检修情况说明
			String remark = readCellData(row, 22);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 校验日期
			String inspection_date = readCellData(row, 23);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if (inspection_date.trim().length() == 10) {
					if (inspection_date.trim().contains("/") || inspection_date.trim().contains(".")) {
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(
								DateUtil.convertStringToDate(Constant.defaultDatePattern, inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验日期为“" + inspection_date.trim()
								+ "”格式错误，格式必须为：“2016-01-01”！");
						return;
					}
				} else {
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验日期为“" + inspection_date.trim()
							+ "”格式错误，格式必须为：“2016-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验日期为空！");
				return;
			}
			
			// 下次校验日期
			String last_inspection_date = readCellData(row, 24);
			if (StringUtil.isNotEmpty(last_inspection_date.trim())) {
				if (last_inspection_date.trim().length() == 10) {
					if (last_inspection_date.trim().contains("/") || last_inspection_date.trim().contains(".")) {
						last_inspection_date = last_inspection_date.trim().replaceAll("[./—]", "-");
						inspectionZZJDInfo.setLast_inspection_date(last_inspection_date);
					}else{
						inspectionZZJDInfo.setLast_inspection_date(last_inspection_date.trim());
					}
				} else {
					// 检验结果为不合格时，下次校验日期可为/，王仁生2017-06-20要求修改
					if ("合格".equals(inspection_conclusion.trim())) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 下次校验日期为“" + last_inspection_date.trim()
								+ "”格式错误，格式必须为：“2017-01-01”！");
						return;
					}else{
						inspectionZZJDInfo.setLast_inspection_date(last_inspection_date.trim());
					}
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 下次校验日期不能为空！");
				return;
			}

			// 校验员
			String inspection_user_name1 = readCellData(row, 25);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 校验员不能为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 26);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（超高压水晶釜委托检验报告）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2016-11-29 下午03:28:00
	 */
	/*@SuppressWarnings("unchecked")
	private void readCGYSJFWTJYBGExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.AQF_JYBG_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 " + Constant.AQF_JYBG_INFO_TITLES.length + " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.AQF_JYBG_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.AQF_JYBG_INFO_TITLES[i].equalsIgnoreCase(columnName)) {
							errors.add(
									"导入文件中【Sheet1】格式错误，列 " + columnName + " 必须改为 " + Constant.AQF_JYBG_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.QP_AZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();

			// 设备名称
			String device_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备名称不能为空！");
				return;
			}

			// 检验类别
			String check_type = readCellData(row, 1);
			if (StringUtil.isNotEmpty(check_type.trim())) {
				inspectionZZJDInfo.setCheck_type(check_type.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验类别不能为空！");
				return;
			}

			// 容器类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 容器类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 容器类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 容器类别不能为空！");
				return;
			}

			// 设备代码
			String device_code = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码不能为空！");
				return;
			}

			// 单位内编号
			String device_no = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 单位内编号不能为空！");
				return;
			}

			// 使用登记证编号
			String com_registration_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(com_registration_num.trim())) {
				inspectionZZJDInfo.setCom_registration_num(com_registration_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用登记证编号不能为空！");
				return;
			}

			// 制造单位
			String made_unit_name = readCellData(row, 6);
			if (StringUtil.isNotEmpty(made_unit_name.trim())) {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位不能为空！");
				return;
			}

			// 安装单位
			String install_unit_name = readCellData(row, 7);
			if (StringUtil.isNotEmpty(install_unit_name.trim())) {
				inspectionZZJDInfo.setInstall_unit_name(install_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装单位不能为空！");
				return;
			}

			// 使用单位
			String com_name = readCellData(row, 8);
			if (StringUtil.isNotEmpty(com_name.trim())) {
				inspectionZZJDInfo.setCom_name(com_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用单位不能为空！");
				return;
			}

			// 使用单位地址
			String com_addr = readCellData(row, 9);
			if (StringUtil.isNotEmpty(com_addr.trim())) {
				inspectionZZJDInfo.setCom_addr(com_addr.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 单位地址不能为空！");
				return;
			}

			// 设备使用地点
			String device_use_place = readCellData(row, 10);
			if (StringUtil.isNotEmpty(device_use_place.trim())) {
				inspectionZZJDInfo.setDevice_use_place(device_use_place.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备使用地点不能为空！");
				return;
			}

			// 使用单位统一社会信用代码
			String com_code = readCellData(row, 11);
			if (StringUtil.isNotEmpty(com_code.trim())) {
				inspectionZZJDInfo.setCom_code(com_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用单位统一社会信用代码不能为空！");
				return;
			}

			// 邮政编码
			String com_zip_code = readCellData(row, 12);
			if (StringUtil.isNotEmpty(com_zip_code.trim())) {
				inspectionZZJDInfo.setCom_zip_code(com_zip_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 邮政编码不能为空！");
				return;
			}

			// 安全管理人员
			String security_op = readCellData(row, 13);
			if (StringUtil.isNotEmpty(security_op.trim())) {
				inspectionZZJDInfo.setSecurity_op(security_op.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安全管理人员不能为空！");
				return;
			}

			// 联系电话
			String security_tel = readCellData(row, 14);
			if (StringUtil.isNotEmpty(security_tel.trim())) {
				inspectionZZJDInfo.setSecurity_tel(security_tel.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 联系电话不能为空！");
				return;
			}

			// 设计使用年限（年）
			String design_date = readCellData(row, 15);
			if (StringUtil.isNotEmpty(design_date.trim())) {
				inspectionZZJDInfo.setDesign_date(design_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计使用年限（年）不能为空！");
				return;
			}

			// 投入使用日期（年月）
			String install_date = readCellData(row, 16);
			if (StringUtil.isNotEmpty(install_date.trim())) {
				inspectionZZJDInfo.setInstall_date(install_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行  投入使用日期（年月）不能为空！");
				return;
			}

			// 主体结构型式
			String struct_type = readCellData(row, 17);
			if (StringUtil.isNotEmpty(struct_type.trim())) {
				inspectionZZJDInfo.setStruct_type(struct_type.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 主体结构型式不能为空！");
				return;
			}

			// 运行状态
			String run_status = readCellData(row, 18);
			if (StringUtil.isNotEmpty(run_status.trim())) {
				inspectionZZJDInfo.setRun_status(run_status.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 运行状态不能为空！");
				return;
			}

			// 容积m³
			String device_volume = readCellData(row, 19);
			if (StringUtil.isNotEmpty(device_volume.trim())) {
				inspectionZZJDInfo.setDevice_volume(device_volume.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 容积m³不能为空！");
				return;
			}

			// 内径mm
			String bz1 = readCellData(row, 20);
			if (StringUtil.isNotEmpty(bz1.trim())) {
				inspectionZZJDInfo.setBz1(bz1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 内径mm不能为空！");
				return;
			}

			// 设计压力Mpa
			String device_pressure = readCellData(row, 21);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计压力Mpa不能为空！");
				return;
			}

			// 设计温度℃
			String use_temp = readCellData(row, 22);
			if (StringUtil.isNotEmpty(use_temp.trim())) {
				inspectionZZJDInfo.setUse_temp(use_temp.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计温度℃不能为空！");
				return;
			}

			// 使用压力Mpa
			String zdyl = readCellData(row, 23);
			if (StringUtil.isNotEmpty(zdyl.trim())) {
				inspectionZZJDInfo.setZdyl(zdyl.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用压力Mpa不能为空！");
				return;
			}
			// 使用温度℃
			String use_temp2 = readCellData(row, 24);
			if (StringUtil.isNotEmpty(use_temp2.trim())) {
				inspectionZZJDInfo.setUse_temp2(use_temp2.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用温度℃不能为空！");
				return;
			}

			// 工作介质
			String device_medium = readCellData(row, 25);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 工作介质不能为空！");
				return;
			}

			// 检验依据
			String safely_tech_standard = readCellData(row, 26);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验依据不能为空！");
				return;
			}

			// 问题及其处理
			String inspection_events = readCellData(row, 27);
			if (StringUtil.isNotEmpty(inspection_events.trim())) {
				inspectionZZJDInfo.setInspection_events(inspection_events.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 问题及其处理不能为空！");
				return;
			}

			// 安全状况等级评定（级）
			String device_model_2 = readCellData(row, 28);
			if (StringUtil.isNotEmpty(device_model_2.trim())) {
				inspectionZZJDInfo.setDevice_model_2(device_model_2.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安全状况等级评定（级）不能为空！");
				return;
			}

			// 检验结论
			String inspection_conclusion = readCellData(row, 29);
			if (StringUtil.isNotEmpty(inspection_conclusion.trim())) {
				inspectionZZJDInfo.setInspection_conclusion(inspection_conclusion.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验结论不能为空！");
				return;
			}

			// 压力Mpa
			String param_pressure = readCellData(row, 30);
			if (StringUtil.isNotEmpty(param_pressure.trim())) {
				inspectionZZJDInfo.setParam_pressure(param_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 压力Mpa不能为空！");
				return;
			}

			// 温度℃
			String param_temp = readCellData(row, 31);
			if (StringUtil.isNotEmpty(param_temp.trim())) {
				inspectionZZJDInfo.setParam_temp(param_temp.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 温度℃不能为空！");
				return;
			}

			// 介质
			String param_medium = readCellData(row, 32);
			if (StringUtil.isNotEmpty(param_medium.trim())) {
				inspectionZZJDInfo.setParam_medium(param_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 介质不能为空！");
				return;
			}

			// 其他
			String param_other = readCellData(row, 33);
			if (StringUtil.isNotEmpty(param_other.trim())) {
				inspectionZZJDInfo.setParam_other(param_other.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 其他不能为空！");
				return;
			}

			// 下次委托检验日期（年月）
			String last_check_date = readCellData(row, 34);
			if (StringUtil.isNotEmpty(last_check_date.trim())) {
				inspectionZZJDInfo.setLast_check_date(last_check_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 下次委托检验日期（年月）不能为空！");
				return;
			}

			// 说明
			String remark = readCellData(row, 35);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 检验人员
			String check_op_name = readCellData(row, 36);
			if (StringUtil.isNotEmpty(check_op_name.trim())) {
				inspectionZZJDInfo.setCheck_op_name(check_op_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验人员不能为空！");
				return;
			}

			// 编制日期
			String inspection_date = readCellData(row, 37);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if (inspection_date.trim().length() == 10) {
					if (inspection_date.trim().contains("/") || inspection_date.trim().contains(".")) {
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(
								DateUtil.convertStringToDate(Constant.defaultDatePattern, inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 编制日期为“" + inspection_date.trim()
								+ "”格式错误，格式必须为：“2016-01-01”！");
						return;
					}
				} else {
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 编制日期为“" + inspection_date.trim()
							+ "”格式错误，格式必须为：“2016-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 编制日期不能为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 38);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}*/
	
	/**
	 * 解析工作表（批量制造压力容器监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-01-14 下午02:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readPLZZYLRQJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.PLYLRQ_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.PLYLRQ_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.PLYLRQ_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.PLYLRQ_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.PLYLRQ_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.PLYLRQ_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品批号
			String device_no = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品批号为空！");
				return;
			}

			// 设备代码
			String device_code = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码为空！");
				return;
			}

			// 设计单位
			String design_unit_name = readCellData(row, 7);
			if (StringUtil.isNotEmpty(design_unit_name.trim())) {
				inspectionZZJDInfo.setDesign_unit_name(design_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计单位为空！");
				return;
			}

			// 设计许可证编号
			String design_license_code = readCellData(row, 8);
			if (StringUtil.isNotEmpty(design_license_code.trim())) {
				inspectionZZJDInfo.setDesign_license_code(design_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计许可证编号为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号为空！");
				return;
			}

			// 设计日期
			String design_date = readCellData(row, 10);
			if (StringUtil.isNotEmpty(design_date.trim())) {
				inspectionZZJDInfo.setDesign_date(design_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计日期为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 11);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 12);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 备注、说明
			String remark = readCellData(row, 13);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 监捡所抽的产品编号（可不填写）
			String check_device_no = readCellData(row, 14);
			if (StringUtil.isNotEmpty(check_device_no.trim())) {
				inspectionZZJDInfo
						.setCheck_device_no(check_device_no.trim());
			}
			
			// 本证书适用的产品编号（可不填写）
			String trial_device_no = readCellData(row, 15);
			if (StringUtil.isNotEmpty(trial_device_no.trim())) {
				inspectionZZJDInfo
						.setTrial_device_no(trial_device_no.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 16);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 17);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 18);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 19);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（压力容器改造与重大修理监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-05-08 下午03:15:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLRQGZYZDXLJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLRQ_GZYZDXL_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLRQ_GZYZDXL_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLRQ_GZYZDXL_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLRQ_GZYZDXL_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLRQ_GZYZDXL_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLRQ_GZYZDXL_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 施工单位
			String construction_units_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(construction_units_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 施工单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setConstruction_unit_name(construction_units_name.trim());
			}

			// 施工单位组织机构代码
			/*String construction_units_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(construction_units_code.trim())) {
				inspectionZZJDInfo.setConstruction_unit_code(construction_units_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 施工单位组织机构代码为空！");
				return;
			}*/

			// 改造修理许可级别
			/*String construction_license_level = readCellData(row, 2);
			if (StringUtil.isNotEmpty(construction_license_level.trim())) {
				inspectionZZJDInfo.setConstruction_license_level(construction_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 改造修理许可级别为空！");
				return;
			}*/
			
			// 许可证编号
			String construction_license_code = readCellData(row, 1); // 从0开始，表示第一列
			if (StringUtil.isEmpty(construction_license_code.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行许可证编号为空！");
				return;
			} else {
				inspectionZZJDInfo.setConstruction_license_code(construction_license_code.trim());
			}

			// 施工类别
			String construction_type = readCellData(row, 2);
			if (StringUtil.isNotEmpty(construction_type.trim())) {
				inspectionZZJDInfo.setConstruction_type(construction_type
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 施工类别为空！");
				return;
			}

			// 使用单位
			String com_name = readCellData(row, 3); // 从0开始，表示第一列
			if (StringUtil.isEmpty(com_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setCom_name(com_name.trim());
			}

			// 设备使用地点
			String device_use_place = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_use_place.trim())) {
				inspectionZZJDInfo.setDevice_use_place(device_use_place
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备使用地点为空！");
				return;
			}
			
			// 使用单位组织机构代码
			/*String com_code = readCellData(row, 7);
			if (StringUtil.isNotEmpty(com_code.trim())) {
				inspectionZZJDInfo.setCom_code(com_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用单位组织机构代码为空！");
				return;
			}*/
			
			// 设备类别
			String device_type_name = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}
			
			// 使用登记证编号
			String com_registration_num = readCellData(row, 6);
			if (StringUtil.isNotEmpty(com_registration_num.trim())) {
				inspectionZZJDInfo.setCom_registration_num(com_registration_num
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 使用登记证编号为空！");
				return;
			}

			// 设备代码
			String device_code = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码为空！");
				return;
			}

			// 设备名称
			String device_name = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备名称为空！");
				return;
			}

			// 竣工日期
			String repair_finish_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(repair_finish_date.trim())) {
				inspectionZZJDInfo.setRepair_finish_date(repair_finish_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 竣工日期为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 10);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号为空！");
				return;
			}

			

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 11);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 改造与重大修理项目
			String repair_project = readCellData(row, 12);
			if (StringUtil.isNotEmpty(repair_project.trim())) {
				inspectionZZJDInfo.setRepair_project(repair_project.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 15);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 16);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（爆破片装置安全性能监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-01-14 下午02:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readBPPZZAQXNJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.ANFJ_BPPJJ_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.ANFJ_BPPJJ_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.ANFJ_BPPJJ_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.ANFJ_BPPJJ_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.ANFJ_BPPJJ_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.ANFJ_BPPJJ_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			inspectionZZJDInfo.setDevice_type_code("F220");
			inspectionZZJDInfo.setDevice_type_name("爆破片装置");
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证号为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}
			
			// 产品型式型号
			String device_model = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品型式型号为空！");
				return;
			}

			// 产品批号
			String device_no = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品批号为空！");
				return;
			}

			// 产品数量
			String device_count = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品数量为空！");
				return;
			}
			
			// 产品标准
			String device_standard = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_standard.trim())) {
				inspectionZZJDInfo.setDevice_standard(device_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品标准为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 7);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 8);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 9);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 10);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}
			
			// 材料牌号
			String material_num = readCellData(row, 11);
			if (StringUtil.isNotEmpty(material_num.trim())) {
				inspectionZZJDInfo.setMaterial_num(material_num.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 材料牌号为空！");
				return;
			}
			
			// 文件、材料、标记移植（确认日期1：项目表1-3）
			String confirm_date1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(confirm_date1.trim())) {
				inspectionZZJDInfo.setConfirm_date1(confirm_date1.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 文件、材料、标记移植为空！");
				return;
			}
			
			// 爆破试验及标定爆破压力（确认日期2：项目表4-6）
			String confirm_date2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(confirm_date2.trim())) {
				inspectionZZJDInfo.setConfirm_date2(confirm_date2.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 爆破试验及标定爆破压力为空！");
				return;
			}
			
			// 产品质量证明书及外观、标志（确认日期3：项目表7-8）
			String confirm_date3 = readCellData(row, 14);
			if (StringUtil.isNotEmpty(confirm_date3.trim())) {
				inspectionZZJDInfo.setConfirm_date3(confirm_date3.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品质量证明书及外观、标志为空！");
				return;
			}

			// 记事
			String inspection_events = readCellData(row, 15);
			if (StringUtil.isNotEmpty(inspection_events.trim())) {
				inspectionZZJDInfo.setInspection_events(inspection_events
						.trim());
			} 

			// 其他说明
			String remark = readCellData(row, 16);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 预收金额
			String advance_fees = readCellData(row, 17);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（承压封头制造监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-07-13 上午09:48:00
	 */
	@SuppressWarnings("unchecked")
	private void readCYFTZZJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.CYFT_ZZJJ_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.CYFT_ZZJJ_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.CYFT_ZZJJ_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.CYFT_ZZJJ_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.CYFT_ZZJJ_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.CYFT_ZZJJ_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			//inspectionZZJDInfo.setDevice_type_name("压力容器封头、压力容器承压部件");
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}
			
			/*// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}*/
			
			// 设备类别
			String device_type_name = readCellData(row, 1);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}
			
			// 产品编（批）号
			String device_no = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编（批）号为空！");
				return;
			}
			
			// 型式规格
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 型式规格为空！");
				return;
			}

			// 材料
			String material_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(material_num.trim())) {
				inspectionZZJDInfo.setMaterial_num(material_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 主要受压元件材料牌号为空！");
				return;
			}

			// 数量
			String device_count = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 数量为空！");
				return;
			}
			
			// 来料加工
			String device_processing = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_processing.trim())) {
				if ("是".equals(device_processing.trim())) {
					inspectionZZJDInfo.setDevice_processing("√");
					inspectionZZJDInfo.setDevice_processing2("");
				} else {
					inspectionZZJDInfo.setDevice_processing2("√");
					inspectionZZJDInfo.setDevice_processing("");
				}

			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 来料加工为空！");
				return;
			}

			// 部件图号
			String device_pic_no = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 部件图号为空！");
				return;
			}
			
			// 制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}
			

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			}
			
			// 备注
			String remark = readCellData(row, 11);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 15);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（气瓶制造监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-07-13 上午11:17:00
	 */
	@SuppressWarnings("unchecked")
	private void readQPZZJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.QP_ZZJJ_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.QP_ZZJJ_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.QP_ZZJJ_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.QP_ZZJJ_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.QP_ZZJJ_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.QP_ZZJJ_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}


			// 许可证号
			String made_license_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 许可证号为空！");
				return;
			}
			
			// 设备类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}
			
			// 产品型号
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品型号为空！");
				return;
			}
			
			// 产品批号
			String device_batch_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_batch_num.trim())) {
				inspectionZZJDInfo.setDevice_batch_num(device_batch_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品批号为空！");
				return;
			}

			// 产品数量
			String device_count = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品数量为空！");
				return;
			}			

			// 产品编号
			String device_no = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编号为空！");
				return;
			}
			
			// 产品标准
			String device_standard = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_standard.trim())) {
				inspectionZZJDInfo.setDevice_standard(device_standard.trim());
			}else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品标准为空！");
				return;
			}
			
			// 制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}
			
			// 本批产品编号中不包括
			String remark = readCellData(row, 10);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 监检标志标注部位
			String safely_tech_standard = readCellData(row, 11);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检标志标注部位为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}
			
			// 预收金额
			String advance_fees = readCellData(row, 15);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（压力容器制造监督检验、特种设备制造监督检验证书（储气井））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-01-14 下午02:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLRQZZJJExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLRQ_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLRQ_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLRQ_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLRQ_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLRQ_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
	 			continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLRQ_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品编号
			String device_no = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编号为空！");
				return;
			}

			// 设备代码
			String device_code = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码为空！");
				return;
			}

			// 设计单位
			String design_unit_name = readCellData(row, 7);
			if (StringUtil.isNotEmpty(design_unit_name.trim())) {
				inspectionZZJDInfo.setDesign_unit_name(design_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计单位为空！");
				return;
			}

			// 设计许可证编号
			String design_license_code = readCellData(row, 8);
			if (StringUtil.isNotEmpty(design_license_code.trim())) {
				inspectionZZJDInfo.setDesign_license_code(design_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计许可证编号为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号为空！");
				return;
			}

			// 设计日期
			String design_date = readCellData(row, 10);
			if (StringUtil.isNotEmpty(design_date.trim())) {
				inspectionZZJDInfo.setDesign_date(design_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计日期为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 11);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 12);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 备注、说明
			String remark = readCellData(row, 13);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 15);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 16);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 17);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（压力管道元件监检证书（埋弧焊钢管）、（低温管件））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-24 上午10:26:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLGDYJ_MHHGG_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLGDYJ_MHHGG_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品规格
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品规格为空！");
				return;
			}

			// 产品批号
			String device_batch_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_batch_num.trim())) {
				inspectionZZJDInfo.setDevice_batch_num(device_batch_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品批号为空！");
				return;
			}

			// 制造标准
			String device_standard = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_standard.trim())) {
				inspectionZZJDInfo.setDevice_standard(device_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造标准为空！");
				return;
			}

			// 部件编号及管号
			String device_no_2 = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_no_2.trim())) {
				inspectionZZJDInfo.setDevice_no_2(device_no_2
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 部件编号及管号为空！");
				return;
			}
			
			// 产品数量
			String device_count = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品数量为空！");
				return;
			}

			// 产品制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品制造日期为空！");
				return;
			}
			
			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 14);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（压力管道元件监检证书（聚乙烯管））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-24 上午10:39:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLGDYJ_JYXG_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLGDYJ_JYXG_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品规格
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品规格为空！");
				return;
			}

			// 产品批号
			String device_batch_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_batch_num.trim())) {
				inspectionZZJDInfo.setDevice_batch_num(device_batch_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品批号为空！");
				return;
			}

			// 制造标准
			String device_standard = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_standard.trim())) {
				inspectionZZJDInfo.setDevice_standard(device_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造标准为空！");
				return;
			}

			// 部件编号及管号
			String device_no_2 = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_no_2.trim())) {
				inspectionZZJDInfo.setDevice_no_2(device_no_2
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 部件编号及管号为空！");
				return;
			}
			
			// 等级/型号
			String device_model_2 = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_model_2.trim())) {
				inspectionZZJDInfo.setDevice_model_2(device_model_2
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 等级/型号为空！");
				return;
			}
			
			// 产品数量
			String device_count = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品数量为空！");
				return;
			}

			// 产品制造日期
			String made_date = readCellData(row, 10);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品制造日期为空！");
				return;
			}
			
			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 11);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 15);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（金属常压罐体制造委托监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-24 上午10:39:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLRQ_JSCYGT_ZZWTJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLRQ_JSCYGT_ZZWTJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}
			
			// 许可证级别及编号
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 许可证级别及编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品型号
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品型号为空！");
				return;
			}

			// 产品编号
			String device_no = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编号为空！");
				return;
			}

			// 制造完成日期
			String made_date = readCellData(row, 6);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造完成日期为空！");
				return;
			}

			// 发动机编号
			String engine_no = readCellData(row, 7);
			if (StringUtil.isNotEmpty(engine_no.trim())) {
				inspectionZZJDInfo.setEngine_no(engine_no
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 发动机编号为空！");
				return;
			}
			
			// 车辆识别代码
			String device_car_num = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_car_num.trim())) {
				inspectionZZJDInfo.setDevice_car_num(device_car_num
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 车辆识别代码为空！");
				return;
			}
			
			// 设计压力
			String device_pressure = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计压力为空！");
				return;
			}

			// 设计温度
			String use_temp = readCellData(row, 10);
			if (StringUtil.isNotEmpty(use_temp.trim())) {
				inspectionZZJDInfo.setUse_temp(use_temp
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计温度为空！");
				return;
			}
			
			// 介质
			String device_medium = readCellData(row, 11);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 介质为空！");
				return;
			}

			// 容积
			String device_volume = readCellData(row, 12);
			if (StringUtil.isNotEmpty(device_volume.trim())) {
				inspectionZZJDInfo.setDevice_volume(device_volume
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 容积为空！");
				return;
			}
			
			// 有关安全技术监察规程（相关标准）
			String safely_tech_standard = readCellData(row, 13);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 15);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 16);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 17);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（压力管道安装安全质量监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-24 上午10:39:00
	 */
	@SuppressWarnings("unchecked")
	private void readYLGD_AZAQZJJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.YLGD_AZAQZLJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.YLGD_AZAQZLJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.YLGD_AZAQZLJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.YLGD_AZAQZLJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.YLGD_AZAQZLJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.YLGD_AZAQZLJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 建设单位
			String construction_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(construction_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 建设单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setConstruction_unit_name(construction_unit_name);
			}
			
			// 安装单位
			String install_unit_name = readCellData(row, 1);
			if (StringUtil.isNotEmpty(install_unit_name.trim())) {
				inspectionZZJDInfo.setInstall_unit_name(install_unit_name
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装单位为空！");
				return;
			}

			// 设计单位
			String design_unit_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(design_unit_name.trim())) {
				inspectionZZJDInfo.setDesign_unit_name(design_unit_name
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计单位为空！");
				return;
			}
			
			// 工程名称
			String project_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(project_name.trim())) {
				inspectionZZJDInfo.setProject_name(project_name
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 工程名称为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 管道名称
			String device_name = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道名称为空！");
				return;
			}

			// 管道编号
			String device_no = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道编号为空！");
				return;
			}
			
			// 管道材质
			String device_meterial = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_meterial.trim())) {
				inspectionZZJDInfo.setDevice_meterial(device_meterial.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道材质为空！");
				return;
			}

			// 管道等级
			String device_model_2 = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_model_2.trim())) {
				inspectionZZJDInfo.setDevice_model_2(device_model_2.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道等级为空！");
				return;
			}

			// 管道规格
			String device_model = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道规格为空！");
				return;
			}
			
			// 管道长度
			String device_length = readCellData(row, 10);
			if (StringUtil.isNotEmpty(device_length.trim())) {
				inspectionZZJDInfo.setDevice_length(device_length
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 管道长度为空！");
				return;
			}
			
			// 设计压力
			String device_pressure = readCellData(row, 11);
			if (StringUtil.isNotEmpty(device_pressure.trim())) {
				inspectionZZJDInfo.setDevice_pressure(device_pressure.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计压力为空！");
				return;
			}

			// 设计温度
			String use_temp = readCellData(row, 12);
			if (StringUtil.isNotEmpty(use_temp.trim())) {
				inspectionZZJDInfo.setUse_temp(use_temp
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计温度为空！");
				return;
			}
			
			// 输送介质
			String device_medium = readCellData(row, 13);
			if (StringUtil.isNotEmpty(device_medium.trim())) {
				inspectionZZJDInfo.setDevice_medium(device_medium.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 输送介质为空！");
				return;
			}

			// 安装竣工日期
			String install_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(install_date.trim())) {
				inspectionZZJDInfo.setInstall_date(install_date
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 安装竣工日期为空！");
				return;
			}
			
			// 检验结论
			String inspection_conclusion = readCellData(row, 15);
			if (StringUtil.isNotEmpty(inspection_conclusion.trim())) {
				inspectionZZJDInfo.setInspection_conclusion(inspection_conclusion
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验结论为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 16);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 17);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 18);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 19);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（特种设备制造监检证书（锅炉））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-28 20:00:00
	 */
	@SuppressWarnings("unchecked")
	private void readGL_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.GL_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.GL_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.GL_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.GL_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.GL_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.GL_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}

			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 设备品种（名称）
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备品种（名称）为空！");
				return;
			}

			// 产品型号
			String device_model = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品型号为空！");
				return;
			}

			// 产品编（批）号
			String device_no = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编（批）号为空！");
				return;
			}

			// 设备代码
			String device_code = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_code.trim())) {
				inspectionZZJDInfo.setDevice_code(device_code.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备代码为空！");
				return;
			}

			// 产品总图图号
			String device_pic_no = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品总图图号为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}

			// 备注、说明
			String remark = readCellData(row, 10);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 14);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（特种设备制造监检证书（锅炉部件、组件））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-10-28 21:10:00
	 */
	@SuppressWarnings("unchecked")
	private void readGL_BJZJ_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.GL_BJZJ_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.GL_BJZJ_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.GL_BJZJ_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.GL_BJZJ_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.GL_BJZJ_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.GL_BJZJ_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 设备品种（名称）
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备品种（名称）为空！");
				return;
			}

			// 产品编（批）号
			String device_no = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编（批）号为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 7);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}
			
			// 备注
			String remark = readCellData(row, 8);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 9);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 10);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 12);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
	
	/**
	 * 解析工作表（特种设备制造监督检验证书（出口锅炉及锅炉部件、组件））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2017-08-16 11:36:00
	 */
	@SuppressWarnings("unchecked")
	private void readCKGL_BJZJ_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.CKGL_BJZJ_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.CKGL_BJZJ_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.CKGL_BJZJ_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.CKGL_BJZJ_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.CKGL_BJZJ_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.CKGL_BJZJ_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可级别不能为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号不能为空！");
				return;
			}

			// 设备类别
			String device_type_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别不能为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称不能为空！");
				return;
			}
			
			// 产品型号
			String device_model = readCellData(row, 5);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}

			// 产品编（批）号
			String device_no = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编（批）号不能为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号不能为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 8);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期不能为空！");
				return;
			}
			
			// 备注
			String remark = readCellData(row, 9);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程不能为空！");
				return;
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1不能为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期不能为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 14);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}

	/**
	 * 创建工作簿（暂只支持2003版本的excel）
	 * 
	 * @param importfile --
	 *            Excel文件
	 * @return Workbook -- 工作簿
	 * @author GaoYa
	 * @date 2013-12-11 上午10:30:00
	 */
	public Workbook createWorkbook(File importfile) throws Exception {
		// 创建对Excel工作簿文件的引用
		Workbook wb = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(importfile);
			if (importfile.getName().toLowerCase().endsWith("xlsx")) {
				wb = new XSSFWorkbook(fis);
			}
			if (importfile.getName().toLowerCase().endsWith("xls")) {
				wb = new HSSFWorkbook(fis);
			}
			fis.close();
		} catch (Exception e) {
			logger.info("Reading excel file error that " + e);
			log.debug(e.toString());
			throw new Exception("读excel文件失败！");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					log.debug(e.toString());
					e.printStackTrace();
				}
			}
		}
		return wb;
	}

	/**
	 * 读取单元格内容
	 * 
	 * @param row --
	 *            行
	 * @param col --
	 *            列
	 * @return String -- 单元格内容
	 * @author GaoYa
	 * @date 2013-12-11 上午10:30:00
	 */
	public String readCellData(Row row, int col) {
		Cell cell = row.getCell((short) col);
		String value = "";
		if (cell != null) {
			int cellType = cell.getCellType();

			switch (cellType) {
			case Cell.CELL_TYPE_FORMULA:
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				/*
				 * if ("@".equals(cell.getCellStyle().getDataFormatString())) {
				 * value =
				 * Constant.defaultDecimalFormat.format(cell.getNumericCellValue()); }
				 * else if ("General".equals(cell.getCellStyle()
				 * .getDataFormatString())) { value =
				 * Constant.defaultDecimalFormat.format(cell.getNumericCellValue()); }
				 * else { value =
				 * DateUtil.getDateTime(Constant.defaultDatePattern,HSSFDateUtil.getJavaDate(cell
				 * .getNumericCellValue())); }
				 */
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Date date = HSSFDateUtil.getJavaDate(d);
					value = DateUtil.getDateTime(Constant.defaultDatePattern,
							date);
				} else {
					DecimalFormat decimalFormat = new DecimalFormat();
					decimalFormat.setDecimalSeparatorAlwaysShown(false);
					decimalFormat.setGroupingUsed(false);
					value = decimalFormat.format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				if (cell.getBooleanCellValue()) {
					value = "true";
				} else {
					value = "false";
				}
				break;
			}
		} else {
			value = "";
		}
		return value.trim();
	}
	
	private boolean validateNullRow(Row row, int cell_length){
		boolean nullRow = true;
		for (int i = 0; i < cell_length; i++) {
			String columnName = readCellData(row, i);
			if(StringUtil.isNotEmpty(columnName)){
				nullRow = false;
			}
		}
		return nullRow;
	}
	
	// 修改报告编号
    @RequestMapping(value = "self_sn")
    @ResponseBody
	public HashMap<String, Object> self_sn(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser(); // 获取当前登录用户信息

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String info_id = request.getParameter("id");
		String report_sn = request.getParameter("report_sn");

		List<InspectionInfo> list = inspectionInfoService.queryInspectionInfo(report_sn);
		if (!list.isEmpty()) {
			wrapper.put("success", false);
			wrapper.put("msg", "查询到系统存在重复的报告编号，请检查！");
		} else {
			InspectionInfo info = inspectionInfoService.get(info_id);
			info.setReport_sn(report_sn);
			inspectionInfoService.save(info);

			// 写入日志
			logService.setLogs(info_id, "自编号", "修改报告编号为" + report_sn, user.getId(), user.getName(), new Date(),
					request != null ? request.getRemoteAddr() : "");

			wrapper.put("success", true);
			wrapper.put("msg", "保存成功！");
		}
		return wrapper;
	}
    
    // 获取报告导入源文件上传存放路径
 	@RequestMapping(value = "getUploadFileInfos")
 	@ResponseBody
 	public HashMap<String, Object> getUploadFileInfos(HttpServletRequest request)
 			throws Exception {
 		HashMap<String, Object> wrapper = new HashMap<String, Object>();
 		String info_id = request.getParameter("info_ids");
 		String file_paths = "";
 		try {
 			String[] info_ids = info_id.split(",");
 			for (int i = 0; i < info_ids.length; i++) {
 				InspectionZZJDInfo zzjd_info = inspectionZZJDInfoService.getByInfoId(info_ids[i]);
 				InspectionZZJD zzjd = zzjd_info.getInspectionZZJD();
 				if(StringUtil.isNotEmpty(file_paths)){
 					file_paths += ",";
 				}
 				file_paths += zzjd.getFile_path();
			}
 			wrapper.put("success", true);
			wrapper.put("file_paths", file_paths);
 			return wrapper;
 		} catch (Exception e) {
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("读取报告录入信息失败！");
 		}
 	} 
 	/**
	 * 解析工作表（证书数据表（出口压力容器））
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author HuQianHui
	 * @date 2018-10-22 下午03:00:00
	 */
 	@SuppressWarnings("unchecked")
	private void readCKYLRQ_ZZJDJYZSExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.CKYLRQ_ZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.CKYLRQ_ZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.CKYLRQ_ZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.CKYLRQ_ZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.CKYLRQ_ZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}
			
			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.CKYLRQ_ZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位不能为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}

			// 制造许可级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可级别不能为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号不能为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称不能为空！");
				return;
			}

			// 产品编号
			String device_no = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编号不能为空！");
				return;
			}

			// 设计单位
			String design_unit_name = readCellData(row, 5);
			if (StringUtil.isNotEmpty(design_unit_name.trim())) {
				inspectionZZJDInfo.setDesign_unit_name(design_unit_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计单位为空！");
				return;
			}

			// 设计许可证编号
			String design_license_code = readCellData(row, 6);
			if (StringUtil.isNotEmpty(design_license_code.trim())) {
				inspectionZZJDInfo.setDesign_license_code(design_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计许可证编号为空！");
				return;
			}

			// 产品图号
			String device_pic_no = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品图号为空！");
				return;
			}

			// 设计日期
			String design_date = readCellData(row, 8);
			if (StringUtil.isNotEmpty(design_date.trim())) {
				inspectionZZJDInfo.setDesign_date(design_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设计日期为空！");
				return;
			}

			// 制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 有关安全技术监察规程为空！");
				return;
			}
			
			// 备注、说明
			String remark = readCellData(row, 11);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}
			
			// 监检员1
			String inspection_user_name1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 15);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}
 	
 	
	/**
	 * 解析工作表（封头委托制造监督检验证书）
	 * 
	 * @param sheet --
	 *            工作表
	 * @param infoList --
	 *            监检报告信息集合
	 * @param device_types --
	 *            设备类别对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2015-07-13 上午09:48:00
	 */
	@SuppressWarnings("unchecked")
	private void readFTWTZZJDExcelSheet(Sheet sheet,
			List<InspectionZZJDInfo> infoList,
			Map<String, Object> device_types, List<String> errors) {
		int line = 0;
		// 迭代工作表行
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			// 验证首行标题行数据格式
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.FT_WTZZJD_INFO_TITLES.length) {
					errors.add("导入文件中【Sheet1】格式错误，必须含有 "
							+ Constant.FT_WTZZJD_INFO_TITLES.length
							+ " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.FT_WTZZJD_INFO_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.FT_WTZZJD_INFO_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中【Sheet1】格式错误，列 " + columnName
									+ " 必须改为 "
									+ Constant.FT_WTZZJD_INFO_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			// 验证是否是空行，如果是空行就跳过，反之读取数据
			boolean nullRow = validateNullRow(row, Constant.FT_WTZZJD_INFO_TITLES.length);
			if (nullRow) {
				continue;
			}
			
			InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
			//inspectionZZJDInfo.setDevice_type_name("压力容器封头、压力容器承压部件");
			// 制造单位
			String made_unit_name = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isEmpty(made_unit_name.trim())) {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造单位为空！");
				return;
			} else {
				inspectionZZJDInfo.setMade_unit_name(made_unit_name);
			}
			
			/*// 制造许可证级别
			String made_license_level = readCellData(row, 1);
			if (StringUtil.isNotEmpty(made_license_level.trim())) {
				inspectionZZJDInfo.setMade_license_level(made_license_level
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可级别为空！");
				return;
			}

			// 制造许可证编号
			String made_license_code = readCellData(row, 2);
			if (StringUtil.isNotEmpty(made_license_code.trim())) {
				inspectionZZJDInfo.setMade_license_code(made_license_code
						.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造许可证编号为空！");
				return;
			}*/
			
			// 设备类别
			String device_type_name = readCellData(row, 1);
			if (StringUtil.isNotEmpty(device_type_name.trim())) {
				try {
					// String device_type_code =
					// codeTablesService.getValueByName("device_classify",
					// device_type_name);
					String device_type_code = "";
					if (device_types.containsKey(device_type_name.trim())) {
						device_type_code = String.valueOf(device_types
								.get(device_type_name.trim()));
					}
					if (StringUtil.isNotEmpty(device_type_code)) {
						inspectionZZJDInfo
								.setDevice_type_code(device_type_code);
					} else {
						errors.add("导入文件中【Sheet1】数据有误，第" + line
								+ "行 设备类别填写错误，请检查！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
					errors
							.add("导入文件中【Sheet1】数据有误，第" + line
									+ "行 设备类别填写错误，请检查！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 设备类别为空！");
				return;
			}

			// 产品名称
			String device_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				inspectionZZJDInfo.setDevice_name(device_name.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品名称为空！");
				return;
			}
			
			// 产品编（批）号
			String device_no = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_no.trim())) {
				inspectionZZJDInfo.setDevice_no(device_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 产品编（批）号为空！");
				return;
			}
			
			// 型式规格
			String device_model = readCellData(row, 4);
			if (StringUtil.isNotEmpty(device_model.trim())) {
				inspectionZZJDInfo.setDevice_model(device_model.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 型式规格为空！");
				return;
			}

			// 材料
			String material_num = readCellData(row, 5);
			if (StringUtil.isNotEmpty(material_num.trim())) {
				inspectionZZJDInfo.setMaterial_num(material_num.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 主要受压元件材料牌号为空！");
				return;
			}

			// 数量
			String device_count = readCellData(row, 6);
			if (StringUtil.isNotEmpty(device_count.trim())) {
				inspectionZZJDInfo.setDevice_count(device_count.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 数量为空！");
				return;
			}
			
			// 来料加工
			String device_processing = readCellData(row, 7);
			if (StringUtil.isNotEmpty(device_processing.trim())) {
				if ("是".equals(device_processing.trim())) {
					inspectionZZJDInfo.setDevice_processing("√");
					inspectionZZJDInfo.setDevice_processing2("");
				} else {
					inspectionZZJDInfo.setDevice_processing2("√");
					inspectionZZJDInfo.setDevice_processing("");
				}

			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 来料加工为空！");
				return;
			}

			// 部件图号
			String device_pic_no = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_pic_no.trim())) {
				inspectionZZJDInfo.setDevice_pic_no(device_pic_no.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 部件图号为空！");
				return;
			}
			
			// 制造日期
			String made_date = readCellData(row, 9);
			if (StringUtil.isNotEmpty(made_date.trim())) {
				inspectionZZJDInfo.setMade_date(made_date.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 制造日期为空！");
				return;
			}
			

			// 有关安全技术监察规程
			String safely_tech_standard = readCellData(row, 10);
			if (StringUtil.isNotEmpty(safely_tech_standard.trim())) {
				inspectionZZJDInfo.setSafely_tech_standard(safely_tech_standard.trim());
			}
			
			// 备注
			String remark = readCellData(row, 11);
			if (StringUtil.isNotEmpty(remark.trim())) {
				inspectionZZJDInfo.setRemark(remark.trim());
			}

			// 监检员1
			String inspection_user_name1 = readCellData(row, 12);
			if (StringUtil.isNotEmpty(inspection_user_name1.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name1(inspection_user_name1.trim());
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检员1为空！");
				return;
			}

			// 监检员2（可不填写）
			String inspection_user_name2 = readCellData(row, 13);
			if (StringUtil.isNotEmpty(inspection_user_name2.trim())) {
				inspectionZZJDInfo
						.setInspection_user_name2(inspection_user_name2.trim());
			}/*
				 * else { errors.add("导入文件中【Sheet1】数据有误，第" + line + "行
				 * 监检员2为空！"); return; }
				 */

			// 监检日期
			String inspection_date = readCellData(row, 14);
			if (StringUtil.isNotEmpty(inspection_date.trim())) {
				if(inspection_date.trim().length()==10){
					if(inspection_date.trim().contains("/") || inspection_date.trim().contains(".")){
						inspection_date = inspection_date.trim().replaceAll("[./—]", "-");
					}
					try {
						inspectionZZJDInfo.setInspection_date(DateUtil
								.convertStringToDate(Constant.defaultDatePattern,
										inspection_date.trim()));
					} catch (ParseException pe) {
						errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
								+ inspection_date.trim()
								+ "”格式错误，格式必须为：“2015-01-01”！");
						return;
					}
				}else{
					errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为“"
							+ inspection_date.trim()
							+ "”格式错误，格式必须为：“2015-01-01”！");
					return;
				}
			} else {
				errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 监检日期为空！");
				return;
			}

			// 预收金额
			String advance_fees = readCellData(row, 15);
			if (StringUtil.isNotEmpty(advance_fees.trim())) {
				inspectionZZJDInfo.setAdvance_fees(Double
						.parseDouble(advance_fees));
			}

			if (errors.isEmpty()) {
				infoList.add(inspectionZZJDInfo);
			}
		}
	}

	/**
	 * 批量业务报告录入保存（新报表）
	 * @param request
	 * @param list
	 * @throws Exception
	 */
	@RequestMapping(value = "reportInfoInputNew")
	@ResponseBody
	public HashMap<String, Object> flow_reportInfo_input_new(HttpServletRequest request, @RequestBody List<Map<String, Object>> list) throws Exception {
		try {
			Map<String, RtExportData> map = RtSaveAsst.transToMap(list, RtPageType.TABLE);
			System.out.println("=============报告保存开始============");
			this.inspectionZZJDService.saveReportNew(request,map);
			System.out.println("=============报告保存结束============");
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	/**
	 * 批量业务加载报告（录入）信息(新报表)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "reportInfoLoadNew")
	@ResponseBody
	public HashMap<String, Object> reportInfoLoadNew(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String fk_report_id = request.getParameter("fk_report_id");
			String input = request.getParameter("input");
			String code_ext = request.getParameter("code_ext");
			String fk_inspection_info_id = request.getParameter("fk_inspection_info_id");
			if (StringUtil.isEmpty(fk_inspection_info_id)) {
				fk_inspection_info_id = id;
			}
			String nowpage = request.getParameter("now");
			String page = nowpage;
			List<Map<String, String>> list = this.inspectionZZJDService.getReportInfoNew(fk_inspection_info_id, input, page,code_ext);
			HashMap<String, Object> pageMap = reportItemValueService.getRecordModelDir(fk_inspection_info_id, null);
			map.put("data", list);
			map.put("pageMap", pageMap);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
