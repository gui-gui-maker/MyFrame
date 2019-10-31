package com.lsts.inspection.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.ImageTool;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.employee.service.EmployeePrinterService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.Inspection;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportBHGRecord;
import com.lsts.inspection.bean.ReportItemRecord;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.ReportBHGRecordService;
import com.lsts.inspection.service.ReportItemRecordService;
import com.lsts.inspection.service.ReportItemValueService;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.Report;
import com.lsts.report.service.ReportService;
import com.ming.webreport.MREngine;
import com.scts.cspace.path.service.TjyptResourcePathService;

import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 移动端原始记录业务控制器
 * 
 * @ClassName ReportItemRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-14 上午09:35:00
 */
@Controller
@RequestMapping("report/item/record")
public class ReportItemRecordAction extends
		SpringSupportAction<ReportItemRecord, ReportItemRecordService> {

	@Autowired
	private ReportItemRecordService reportItemRecordService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private EmployeePrinterService employeePrinterService;
	@Autowired
	private ImageTool imageTool;
	@Autowired
	private	SysLogService logService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private ReportBHGRecordService reportBHGRecordService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private InspectionCommonService inspectionCommonService;
	@Autowired
	private ReportItemValueService reportItemValueService;
	@Autowired
	TjyptResourcePathService tjyptResourcePathService;
	
	/**
	 * 移动端上传数据（电梯类原始记录）
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-12 下午02:45:00
	 */
	@RequestMapping("saveMobileInsp")
	@ResponseBody
	public HashMap<String, Object> saveMobileInsp(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject object = JSONObject.fromString(request.getParameter("data"));
			if(object==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}
			map = reportItemRecordService.saveMobileInsp(object,map,request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，原始记录数据上传失败！");
		}
		return map;
	}
	/**
	 * 新版app保存原始记录
	 * @param request
	 * @return
	 */
	@RequestMapping("appSaveInspectionInfo")
	@ResponseBody
	private HashMap<String, Object> appSaveInspectionInfo(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			String data = request.getParameter("data");
			JSONObject object = JSONObject.fromString(data);
			if(object==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}
			InspectionInfo info =  this.reportItemRecordService.saveMap(request,object,null);
			map.put("infoId", info.getId());
			map.put("data", info);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	/**
	 * 移动端上传数据（常压罐车原始记录）
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-04-08 上午11:14:00
	 */
	@RequestMapping("saveMobileTank")
	@ResponseBody
	public HashMap<String, Object> saveGcMobile(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject object = JSONObject.fromString(request.getParameter("data"));
			if(object==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}
			map = reportItemRecordService.saveGcMobile(object,map,request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，原始记录数据上传失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取所有机电、承压检验部门人员信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-12 下午05:45:00
	 */
	@RequestMapping("queryEmployeeList")
	@ResponseBody
	public Map<String, Object> queryEmployeeList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			map.put("success", true);
			System.out.println("###################################--------------------------------------###########################################33ueryEmployeeList");
			map.put("user", reportItemRecordService.queryEmployeeList());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取部门人员信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据部门ID获取部门人员信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-02-12 下午02:59:00
	 */
	@RequestMapping("queryEmployeesByOrgId")
	@ResponseBody
	public Map<String, Object> queryEmployeesByOrgId(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String org_id = request.getParameter("org_id");
			map.put("success", true);
			map.put("user", reportItemRecordService.queryEmployeesByOrgId(org_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取部门人员信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取电梯检验部门信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-02-26 上午10:59:00
	 */
	@RequestMapping("queryDtOrgs")
	@ResponseBody
	public Map<String, Object> queryDtOrgs(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			map.put("success", true);
			map.put("orgList", reportItemRecordService.queryDtOrgs());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取电梯检验部门信息失败！");
		}
		return map;
	}
	
	// 删除原始记录
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception{		
		reportItemRecordService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	// 原始记录生成报告并启动流程
	@RequestMapping(value = "startFlow")
	@ResponseBody
	public Map<String, Object> startFlow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1、将原始记录生成报告并开始流程
		map = reportItemRecordService.startFlow(request);
		String success = String.valueOf(map.get("success"));
		if("true".equals(success)){
			// 2、生成报告书编号
			String ids = request.getParameter("info_ids");
			String[] temp = ids.split(","); // 报检业务id
			for (int i = 0; i < temp.length; i++) {
				InspectionInfo info = inspectionInfoService.get(temp[i]);
				String report_type = info.getReport_type();
				String report_sn = "";
				if (StringUtil.isEmpty(info.getReport_sn())) {
					String check_type = info.getInspection().getCheck_type();
					String device_id = info.getFk_tsjc_device_document_id();
					// 获取设备类型
					String device_type = "";
					if (StringUtil.isNotEmpty(device_id)) {
						// 根据设备ID获取设备基础信息bean
						DeviceDocument devicedoc = deviceService.get(device_id);
						if (devicedoc != null) {
							if (StringUtil.isNotEmpty(devicedoc.getDevice_sort_code())) {
								device_type = devicedoc.getDevice_sort_code();
							}
						}

					}
					synchronized (this){
						Map<String, Object> reportSnMap = inspectionCommonService.createReportSn("moCreateReport", temp[i],
								report_type, check_type, device_type);
						report_sn = String.valueOf(reportSnMap.get("report_sn"));
						// 报告输编号插入业务主表
						info.setReport_sn(report_sn);
						inspectionInfoService.save(info);
					}
				} else {
					report_sn = info.getReport_sn();
				}
				// 2.4 将报告书编号更新到报告检验项目参数表
				reportItemValueService.updateItemValue(temp[i], report_type, "REPORT_SN", report_sn);
				// 2.5 将报告书编号更新到原始记录检验项目参数表
				reportItemRecordService.updateItemValue(temp[i], report_type, "REPORT_SN", report_sn);
			}
		}
		return map;
	}
	
	/**
	 * 罐车原始记录生成报告并启动流程
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-04-08 下午14:50:00
	 */
	@RequestMapping(value = "startFlowTanker")
	@ResponseBody
	public Map<String, Object> startFlowTanker(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1、将原始记录生成报告并开始流程
		map = reportItemRecordService.startFlowTanker(request);
		String success = String.valueOf(map.get("success"));
		if ("true".equals(success)) {
			// 2、生成报告书编号
			String ids = request.getParameter("info_ids");
			String[] temp = ids.split(","); // 报检业务id
			for (int i = 0; i < temp.length; i++) {
				InspectionInfo info = inspectionInfoService.get(temp[i]);
				String report_type = info.getReport_type();
				String report_sn = "";
				if (StringUtil.isEmpty(info.getReport_sn())) {
					String check_type = info.getInspection().getCheck_type();
					String device_id = info.getFk_tsjc_device_document_id();
					// 获取设备类型
					String device_type = "";
					if (StringUtil.isNotEmpty(device_id)) {
						// 根据设备ID获取设备基础信息bean
						DeviceDocument devicedoc = deviceService.get(device_id);
						if (devicedoc != null) {
							if (StringUtil.isNotEmpty(devicedoc.getDevice_sort_code())) {
								device_type = devicedoc.getDevice_sort_code();
							}
						}

					}
					synchronized (this) {
						Map<String, Object> reportSnMap = inspectionCommonService.createReportSn("moCreateReport",
								temp[i], report_type, check_type, device_type);
						report_sn = String.valueOf(reportSnMap.get("report_sn"));
						// 报告输编号插入业务主表
						info.setReport_sn(report_sn);
						inspectionInfoService.save(info);
					}
				} else {
					report_sn = info.getReport_sn();
				}
				// 2.4 将报告书编号更新到报告检验项目参数表
				reportItemValueService.updateItemValue(temp[i], report_type, "REPORT_SN", report_sn);
				// 2.5 将报告书编号更新到原始记录检验项目参数表
				reportItemRecordService.updateItemValue(temp[i], report_type, "REPORT_SN", report_sn);
			}
		}
		return map;
	}
	
	// 罐车原始记录生成报告并启动流程
	/*@RequestMapping(value = "startFlowTanker")
	@ResponseBody
	public Map<String, Object> startFlowTanker(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		String checktypes = request.getParameter("checktypes");
		map = reportItemRecordService.startFlowTanker(ids, checktypes, request);
		return map;

	}*/
	
	// 原始记录生成报告
	@RequestMapping(value = "createReport")
	@ResponseBody
	public Map<String, Object> createReport(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String ids = request.getParameter("ids");
		map = reportItemRecordService.createReport(ids, request);
		return map;

	}
	
	/**
	 * 移动端审核、签发报告
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-23 下午03:54:00
	 */
	@RequestMapping("mo_batchCheck")
	@ResponseBody
	public HashMap<String, Object> mo_batchCheck(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject jsonObject = JSONObject.fromString(request.getParameter("data"));
			if(jsonObject==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}
			map = reportItemRecordService.mo_batchCheck(jsonObject, map, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，操作失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取报告审核人员信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-25 上午11:19:00
	 */
	@RequestMapping("getShUsersList")
	@ResponseBody
	public Map<String, Object> getShUsersList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String org_id = request.getParameter("org_id");
			map.put("success", true);
			map.put("userList", reportItemRecordService.getShUsersList(org_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取报告审核人员信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取报告签发人员信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-25 上午11:29:00
	 */
	@RequestMapping("getQfUsersList")
	@ResponseBody
	public Map<String, Object> getQfUsersList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String org_id = request.getParameter("org_id");
			map.put("success", true);
			map.put("userList", reportItemRecordService.getQfUsersList(org_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取报告签发人员信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取人员角色信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-25 上午11:29:00
	 */
	@RequestMapping("getUserRoles")
	@ResponseBody
	public Map<String, Object> getUserRoles(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String user_id = request.getParameter("user_id");
			map.put("success", true);
			map.put("roleStr", reportItemRecordService.getUserRoles(user_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取人员角色信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取待处理业务信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-13 下午13:00:00
	 */
	@RequestMapping("getTasks")
	@ResponseBody
	public Map<String, Object> getTasks(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String user_id = request.getParameter("user_id");
			map.put("success", true);
			map.put("taskList", reportItemRecordService.getTasks(user_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取待处理业务信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据设备ID获取设备信息（批量）
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-21 下午18:51:00
	 */
	@RequestMapping("getDevices")
	@ResponseBody
	public Map<String, Object> getDevices(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String device_id = request.getParameter("device_id");
			map.put("success", true);
			map.put("deviceList", reportItemRecordService.getDevices(device_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，根据设备ID获取设备信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据设备车牌号获取设备信息（罐车）
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-04-03 上午10:33:00
	 */
	@RequestMapping("getDeviceByInternal_num")
	@ResponseBody
	public Map<String, Object> getDeviceByInternal_num(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String internal_num = request.getParameter("internal_num");
			map = reportItemRecordService.getDeviceByInternal_num(internal_num);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，根据设备车牌号获取设备信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据罐车业务ID获取设备信息（批量）
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-21 下午18:51:00
	 */
	@RequestMapping("getDevicesByInfoId")
	@ResponseBody
	public Map<String, Object> getDevicesByInfoId(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String info_id = request.getParameter("infoid");
			map.put("success", true);
			map.put("deviceList", reportItemRecordService.getDevicesByInfoId(info_id));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，根据设备ID获取设备信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据单位ID和单位名称获取设备信息
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-22 下午14:51:00
	 */
	@RequestMapping("getDeviceList")
	@ResponseBody
	public Map<String, Object> getDeviceList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String com_id = request.getParameter("com_id");
			String com_name = request.getParameter("com_name");
			map.put("success", true);
			map.put("deviceList", reportItemRecordService.getDeviceList(com_id, com_name));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，根据单位ID和单位名称获取设备信息失败！");
		}
		return map;
	}
	
	/**
	 * 移动端获取待校核原始记录
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-03-01 下午15:16:00
	 */
	@RequestMapping("getRecords")
	@ResponseBody
	public Map<String, Object> getRecords(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String user_id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");
			if(StringUtil.isEmpty(user_id)){
				map.put("success", false);
				map.put("msg", "登录已过期，请重新登录！");
				return map;
			}else{
				map.put("success", true);
				map.put("recordList", reportItemRecordService.getRecords(user_id, user_name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取待校核原始记录失败！");
		}
		return map;
	}
	
	/**
	 * 移动端校核原始记录
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2017-03-03 上午09:58:00
	 */
	@RequestMapping(value = "mo_confirmRecord")
	@ResponseBody
	public HashMap<String, Object> mo_confirmRecord(HttpServletRequest request)
			throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		try{
			String info_ids = request.getParameter("info_ids");		// 业务ID
			String user_id = request.getParameter("user_id");		// 校核用户ID
			String user_name = request.getParameter("user_name");	// 校核用户姓名
			String confirm_result = request.getParameter("confirm_result");	// 校核结果
			String confirm_remark = request.getParameter("confirm_remark");	// 校核结果备注
			
			if(StringUtil.isEmpty(user_id) || StringUtil.isEmpty(info_ids)){
				map.put("success", false);
				map.put("msg", "登录已失效，请重新登录！");
				return map;
			}else{
				// 获取employee信息（报告书内所有业务人员全部使用的是employee，故此处要获取employee表数据）
				Employee employee = employeesService.queryEmployeeByUser(user_id, user_name);
				if(employee != null){
					String[] info_id_list = info_ids.split(",");
					for (int i = 0; i < info_id_list.length; i++) {
						InspectionInfo info = inspectionInfoService.get(info_id_list[i]);
						info.setIs_report_confirm(confirm_result);
						info.setReport_confirm_remark(confirm_remark);
						info.setConfirm_id(employee.getId());
						info.setConfirm_name(user_name);
						inspectionInfoService.save(info);
						
						String remark = "";
						if("1".equals(confirm_result)){
							remark += "校核通过"; 
						}else{
							remark += "校核不通过"; 
						}
						if(StringUtil.isNotEmpty(confirm_remark)){
							remark += "，原因："+confirm_remark;
						}
						logService.setLogs(info.getId(), "原始记录校核", remark, user_id,
								user_name, new Date(), request.getRemoteAddr());
					}
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("msg", "获取人员信息失败，请尝试重新登录！");
					return map;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "原始记录校核失败！");
		}
		return map;
	}
	
	/**
	 * 移动端根据业务ID获取原始记录
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2017-03-03 上午09:58:00
	 */
	@RequestMapping(value = "getRecordByInfoId")
	@ResponseBody
	public HashMap<String, Object> getRecordByInfoId(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String info_id = request.getParameter("info_id"); // 业务ID

			if (StringUtil.isEmpty(info_id)) {
				map.put("success", false);
				map.put("msg", "登录已失效，请重新登录！");
				return map;
			} else {
				// 根据业务ID获取业务数据
				InspectionInfo info = inspectionInfoService.get(info_id);
				Inspection inspection = info.getInspection();
				DeviceDocument device = null;
				Report report = null;
				if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
					device = deviceService.get(info.getFk_tsjc_device_document_id());
				}
				if (StringUtil.isNotEmpty(info.getReport_type())) {
					report = reportService.get(info.getReport_type());
				}

				Map<String, Object> itemRecordMap = new HashMap<String, Object>();
				Map<String, Object> bhgRecordMap = new HashMap<String, Object>();
				List<Map<String, Object>> nomalPicList = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> bhgPicList = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> zzjyPicList = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> signPicList = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> gcPicList = new ArrayList<Map<String, Object>>();
				
				
				// 根据业务ID获取原始记录数据
				List<ReportItemRecord> itemRecordlist = reportItemRecordService.queryByInfoId(info_id);
				for (ReportItemRecord record : itemRecordlist) {
					String item_value = record.getItem_value();
					if(StringUtil.isEmpty(item_value)){
						item_value = "";
					}else{
						if("null".equals(item_value)){
							item_value = "";
						}
					}
					itemRecordMap.put(record.getItem_name(), item_value);
				}
				// 根据业务ID获取原始记录检验项目不合格问题来源记录
				List<ReportBHGRecord> bhgRecordList = reportBHGRecordService.queryBHGRecords(info_id);
				for (ReportBHGRecord record : bhgRecordList) {
					String bhg_value = record.getBhg_value();
					if(StringUtil.isEmpty(bhg_value)){
						bhg_value = "";
					}else{
						if("null".equals(bhg_value)){
							bhg_value = "";
						}
					}
					bhgRecordMap.put(record.getBhg_name(), bhg_value);
				}

				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("userId", "");
				returnMap.put("userName", "");
				returnMap.put("fk_company_info_use_id",
						device.getFk_company_info_use_id() != null ? device.getFk_company_info_use_id() : "");
				returnMap.put("depId", info.getCheck_unit_id() != null ? info.getCheck_unit_id() : "");
				

				Map<String, Object> sumTableMap = new HashMap<String, Object>();
				sumTableMap.put("check_type", inspection.getCheck_type() != null ? inspection.getCheck_type() : "");
				sumTableMap.put("xsqts", info.getXsqts() != null ? info.getXsqts() : "");
				sumTableMap.put("report_type", report.getReport_code() != null ? report.getReport_code() : "");
				sumTableMap.put("check_op_id", info.getCheck_op_id() != null ? info.getCheck_op_id() : "");
				sumTableMap.put("check_op_name", info.getCheck_op_name() != null ? info.getCheck_op_name() : "");
				sumTableMap.put("check_status", "");
				sumTableMap.put("use_company", info.getReport_com_name() != null ? info.getReport_com_name() : "");
				sumTableMap.put("submitid", info.getId() != null ? info.getId() : "");
				sumTableMap.put("inspection_date", info.getAdvance_time() != null ? info.getAdvance_time() : "");
				sumTableMap.put("device_id",
						info.getFk_tsjc_device_document_id() != null ? info.getFk_tsjc_device_document_id() : "");
				sumTableMap.put("reportname",
						report.getReport_name() != null ? report.getReport_name() : "");
				
				// 原始记录版本号（为空代表老版本，"1"代表2号修改单新版本、"2"代表西藏的版本、"3"代表新疆的版本、"4"代表九寨沟的版本）
				String report_version = "";
				if(StringUtil.isNotEmpty(report.getReport_name())){
					if(report.getReport_name().contains("2号修改单")){
						report_version = "1";
					}else if(report.getReport_name().contains("西藏")){
						report_version = "2";
					}else if(report.getReport_name().contains("新疆")){
						report_version = "3";
					}else if(report.getReport_name().contains("九寨")){
						report_version = "4";
					}
				}
				sumTableMap.put("report_version", report_version);
				
				// 根据业务ID获取图片
				List<Attachment> attachments = attachmentManager.getBusAttachment(info_id);
				if(!attachments.isEmpty()){
					SysParaInf sp = Factory.getSysPara();
					for (Attachment attachment : attachments) {
						String attachmentPath = sp.getProperty("mo_attachmentPath");
						if ("bhg_pic".equals(attachment.getJy_pic_category())) {
							attachmentPath = sp.getProperty("mo_bhg_attachmentPath");
							Map<String, Object> picMap = new HashMap<String, Object>();
							picMap.put("picpath", attachmentPath);
							picMap.put("picname", attachment.getFilePath());
							bhgPicList.add(picMap);
						} else if ("zzjy_pic".equals(attachment.getJy_pic_category())) {
							attachmentPath = sp.getProperty("mo_zzjy_attachmentPath");
							Map<String, Object> picMap = new HashMap<String, Object>();
							picMap.put("picpath", attachmentPath);
							picMap.put("picname", attachment.getFilePath());
							zzjyPicList.add(picMap);
						} else if ("EXAMINE_NAME".equals(attachment.getJy_pic_category())) {
							attachmentPath = sp.getProperty("mo_sign_attachmentPath");
							Map<String, Object> picMap = new HashMap<String, Object>();
							picMap.put("picpath", attachmentPath);
							picMap.put("picname", attachment.getFilePath());
							signPicList.add(picMap);
						} else if ("GC_8_CHDBWT".equals(attachment.getJy_pic_category())
								|| "GC_17_QXWZSYT".equals(attachment.getJy_pic_category())
								|| "GC_18_QXWZSYT".equals(attachment.getJy_pic_category())
								|| "GC_19_QXWZSYT".equals(attachment.getJy_pic_category())) {
							attachmentPath = sp.getProperty("mo_gc_attachmentPath");
							Map<String, Object> picMap = new HashMap<String, Object>();
							picMap.put("picpath", attachmentPath);
							picMap.put("pictype", attachment.getJy_pic_category());
							picMap.put("picname", attachment.getFilePath());
							gcPicList.add(picMap);
						} else {
							Map<String, Object> picMap = new HashMap<String, Object>();
							picMap.put("picpath", attachmentPath);
							picMap.put("picname", attachment.getFilePath());
							nomalPicList.add(picMap);
						}
					}
				}
				
				returnMap.put("sumtable", sumTableMap);
				returnMap.put("reportitemvalue", itemRecordMap);
				returnMap.put("reportbhgitemvalue", bhgRecordMap);
				returnMap.put("XC_PIC", nomalPicList);
				returnMap.put("BHG_PIC", bhgPicList);
				returnMap.put("ZZJY_PIC", zzjyPicList);
				returnMap.put("SIGN_PIC", signPicList);
				returnMap.put("GC_PIC", gcPicList);
				
				map.put("data", returnMap);
				map.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取原始记录失败！");
		}
		return map;
	}
	
	/**
	 * 查看原始记录（电梯）
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
	@RequestMapping(value = "showRecord")
	public String showRecord(HttpServletRequest request, String id,
			String report_id) throws Exception {
		getPrintData(request, id, report_id);
		return "app/mobile/record_detail_show";
	}
	
	/**
	 * 查看原始记录（罐车）
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
	@RequestMapping(value = "showGcRecord")
	public String showGcRecord(HttpServletRequest request, String id,
			String report_id) throws Exception {
		getPrintData(request, id, report_id);
		return "app/tanker/gc_record_detail_show";
	}
	
	// 修改原始记录前打开目录（电梯）
	@RequestMapping(value = "openCatalog")
	@ResponseBody
	public ModelAndView openCatalog(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", request.getParameter("id"));
		map.put("report_id", request.getParameter("report_id"));
		map.put("device_id", request.getParameter("device_id"));

		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap = reportItemRecordService.openCatalog(map);
		ModelAndView mav = new ModelAndView("app/mobile/set_record_item", outMap);
		return mav;
	}
	
	// 修改原始记录前打开目录（罐车）
	@RequestMapping(value = "openGcCatalog")
	@ResponseBody
	public ModelAndView openGcCatalog(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", request.getParameter("id"));
		map.put("report_id", request.getParameter("report_id"));
		map.put("device_id", request.getParameter("device_id"));

		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap = reportItemRecordService.openGcCatalog(map);
		ModelAndView mav = new ModelAndView("app/tanker/set_gc_record_item", outMap);
		return mav;
	}
	
	// 保存原始记录目录页码信息
	@RequestMapping(value = "saveItem")
	@ResponseBody
	public Map<String, Object> saveItem(String dataStr, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", request.getParameter("id"));
		map.put("report_id", request.getParameter("report_id"));
		map.put("record_item", request.getParameter("record_item"));
		map.put("dataMap", dataStr);
		map.put("xsqnum", request.getParameter("xsq"));

		reportItemRecordService.saveItem(map);
		return JsonWrapper.successWrapper("");
	}
	
	// 保存原始记录目录页码信息（罐车）
	@RequestMapping(value = "saveGcItem")
	@ResponseBody
	public Map<String, Object> saveGcItem(String dataStr, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", request.getParameter("id"));
		map.put("report_id", request.getParameter("report_id"));
		map.put("record_item", request.getParameter("record_item"));
		map.put("dataMap", dataStr);
		reportItemRecordService.saveGcItem(map);
		return JsonWrapper.successWrapper("");
	}
	
	/**
	 * 打开原始记录修改页面（电梯）
	 * @param id --
	 *            报告业务ID
	 * @param report_id --
	 *            报告类型ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyRecord")
	public String modifyRecord(HttpServletRequest request, String id,
			String report_id) throws Exception {
		getPrintData(request, id, report_id);
		return "app/mobile/record_detail_modify";
	}
	
	/**
	 * 打开原始记录修改页面（罐车）
	 * @param id --
	 *            报告业务ID
	 * @param report_id --
	 *            报告类型ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyGcRecord")
	public String modifyGcRecord(HttpServletRequest request, String id,
			String report_id) throws Exception {
		getPrintData(request, id, report_id);
		return "app/tanker/gc_record_modify";
	}
	
	/**
	 * 修改原始记录保存（电梯）
	 * @param request
	 * @param response
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-04-27 下午03:54:00
	 */
	@RequestMapping(value = "saveRecord")
	public void saveRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			
			// 1、获取原始记录修改前的内容
			String id = request.getParameter("id");
			Map<String, String> old_Map = reportItemRecordService.queryRecordByInfoId(id);
			
			// 2、保存原始记录内容
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			ServletContext servletContext = webApplicationContext.getServletContext();
			MREngine engine = new MREngine(request, response, servletContext);
			String[] PostBackValue = engine.getParameterNames();
			reportItemRecordService.saveRecord(PostBackValue, engine, request, response);
			
			// 3、生成报告书编号
			// 3.1 根据业务信息ID获取业务信息bean
			InspectionInfo insInfo = inspectionInfoService.get(id);
			String check_type = insInfo.getInspection().getCheck_type();
			String report_type = insInfo.getReport_type();
			String device_id = insInfo.getFk_tsjc_device_document_id();
			String report_sn = "";
			if (StringUtil.isEmpty(insInfo.getReport_sn())) {
				// 获取设备类型
				String device_type = "";
				if (StringUtil.isNotEmpty(device_id)) {
					// 根据设备ID获取设备基础信息bean
					DeviceDocument devicedoc = deviceService.get(device_id);
					if (devicedoc != null) {
						if (StringUtil.isNotEmpty(devicedoc.getDevice_sort_code())) {
							device_type = devicedoc.getDevice_sort_code();
						}
					}
				}
				// 3.2 不存在报告编号，就让它获得存在
				synchronized (this){
					Map<String, Object> reportSnMap = inspectionCommonService.createReportSn("saveRecord", id, report_type,
							check_type, device_type);
					report_sn = String.valueOf(reportSnMap.get("report_sn"));
					insInfo.setReport_sn(report_sn);
					inspectionInfoService.save(insInfo);
				}
			} else {
				// 3.3 存在报告编号，就让它继续存在
				report_sn = insInfo.getReport_sn();
			}

			if (StringUtil.isNotEmpty(report_sn)) {
				// 3.4 将报告书编号更新到报告检验项目参数表
				reportItemValueService.updateItemValue(id, report_type, "REPORT_SN", report_sn);
				// 3.5 将报告书编号更新到原始记录检验项目参数表
				reportItemRecordService.updateItemValue(id, report_type, "REPORT_SN", report_sn);
			}

			// 4、获取原始记录修改后的新内容
			Map<String, String> new_Map = reportItemRecordService.queryRecordByInfoId(id);
			
			// 5、将原始记录修改前后的内容进行对比并记录区别
			Map<String, String> infoMap = new HashMap<String, String>();	
	    	infoMap.put("table_code", "tzsb_report_item_record");	// 数据库表名代码
			infoMap.put("table_name", "原始记录检验项目参数表");	// 数据库表名标题
			infoMap.put("op_action", "修改原始记录内容");			// 操作动作
			infoMap.put("op_remark", "电脑端修改原始记录内容");		// 操作描述
			infoMap.put("business_id", id);							// 业务ID
			// 5.1、修改前后对比并返回有差异的数据集合 
			Map<String, String> diff_Map = inspectionCommonService.compareMap(infoMap, old_Map, new_Map, request);
			
			// 6、删除修改前后无差异的已作废数据
			// 6.1、获取已作废的原始记录数据
			List<ReportItemRecord> delRecordList = reportItemRecordService.queryDelByInfoId(id);
			for(ReportItemRecord record : delRecordList){
				if(!diff_Map.containsKey(record.getItem_name())){
					// 6.2、删除无差异的已作废数据（物理删除）
					reportItemRecordService.deleteBusiness(record.getId());
				}
			}
			
			// 7、记录日志
			logService.setLogs(id, "修改原始记录", "电脑端修改原始记录", user.getId(), user.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改原始记录保存（罐车）
	 * @param request
	 * @param response
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-04-27 下午03:54:00
	 */
	@RequestMapping(value = "saveGcRecord")
	public void saveGcRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			
			// 1、获取原始记录修改前的内容
			String id = request.getParameter("id");
			Map<String, String> old_Map = reportItemRecordService.queryRecordByInfoId(id);
			
			// 2、保存原始记录内容
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			ServletContext servletContext = webApplicationContext.getServletContext();
			MREngine engine = new MREngine(request, response, servletContext);
			String[] PostBackValue = engine.getParameterNames();
			reportItemRecordService.saveGcRecord(PostBackValue, engine, request, response);
			
			// 3、生成报告书编号
			// 3.1 根据业务信息ID获取业务信息bean
			InspectionInfo insInfo = inspectionInfoService.get(id);
			String check_type = insInfo.getInspection().getCheck_type();
			String report_type = insInfo.getReport_type();
			String device_id = insInfo.getFk_tsjc_device_document_id();
			String report_sn = "";
			if (StringUtil.isEmpty(insInfo.getReport_sn())) {
				// 获取设备类型
				String device_type = "";
				if (StringUtil.isNotEmpty(device_id)) {
					// 根据设备ID获取设备基础信息bean
					DeviceDocument devicedoc = deviceService.get(device_id);
					if (devicedoc != null) {
						if (StringUtil.isNotEmpty(devicedoc.getDevice_sort_code())) {
							device_type = devicedoc.getDevice_sort_code();
						}
					}
				}
				// 3.2 不存在报告编号，就让它获得存在
				synchronized (this){
					Map<String, Object> reportSnMap = inspectionCommonService.createReportSn("saveRecord", id, report_type,
							check_type, device_type);
					report_sn = String.valueOf(reportSnMap.get("report_sn"));
					insInfo.setReport_sn(report_sn);
					inspectionInfoService.save(insInfo);
				}
			} else {
				// 3.3 存在报告编号，就让它继续存在
				report_sn = insInfo.getReport_sn();
			}

			if (StringUtil.isNotEmpty(report_sn)) {
				// 3.4 将报告书编号更新到报告检验项目参数表
				reportItemValueService.updateItemValue(id, report_type, "REPORT_SN", report_sn);
				// 3.5 将报告书编号更新到原始记录检验项目参数表
				reportItemRecordService.updateItemValue(id, report_type, "REPORT_SN", report_sn);
			}

			// 4、获取原始记录修改后的新内容
			Map<String, String> new_Map = reportItemRecordService.queryRecordByInfoId(id);
			
			// 5、将原始记录修改前后的内容进行对比并记录区别
			Map<String, String> infoMap = new HashMap<String, String>();	
	    	infoMap.put("table_code", "tzsb_report_item_record");	// 数据库表名代码
			infoMap.put("table_name", "原始记录检验项目参数表");	// 数据库表名标题
			infoMap.put("op_action", "修改原始记录内容");			// 操作动作
			infoMap.put("op_remark", "电脑端修改原始记录内容");		// 操作描述
			infoMap.put("business_id", id);							// 业务ID
			// 5.1、修改前后对比并返回有差异的数据集合 
			Map<String, String> diff_Map = inspectionCommonService.compareMap(infoMap, old_Map, new_Map, request);
			
			// 6、删除修改前后无差异的已作废数据
			// 6.1、获取已作废的原始记录数据
			List<ReportItemRecord> delRecordList = reportItemRecordService.queryDelByInfoId(id);
			for(ReportItemRecord record : delRecordList){
				if(!diff_Map.containsKey(record.getItem_name())){
					// 6.2、删除无差异的已作废数据（物理删除）
					reportItemRecordService.deleteBusiness(record.getId());
				}
			}
			
			// 7、记录日志
			logService.setLogs(id, "修改原始记录", "电脑端修改原始记录", user.getId(), user.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取信息表
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
		
		InspectionInfo inspectionInfo = inspectionInfoService.get(id); // 报检业务
		DeviceDocument deviceDocument = deviceService.get(inspectionInfo.getFk_tsjc_device_document_id());
		Report report = reportService.get(report_id);
		List<ReportItemRecord> reportItemRecordList = reportItemRecordService
				.queryByInspectionInfoId(id, report_id); // 报告检验项目
		/*
		List<ReportItem> reportItemList = reportItemService
				.queryByReportId(report_id); // 报告项目
		 */
		String printer_name = employeePrinterService.queryPrinterName(emp.getId(), Constant.PRINTER_TYPE_R);
		
		// 检验人员电子签名
		request.setAttribute("check_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getCheck_op_id()));
		// 审核人员电子签名
		request.setAttribute("examine_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getExamine_id()));
		// 签发（批准）人员电子签名
		request.setAttribute("issue_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getIssue_id()));
		// 编制（录入）人员电子签名
		request.setAttribute("enter_op_img",
				imageTool.getEmployeeImg(inspectionInfo.getEnter_op_id()));
		// 校核人员手写签名图片
		if(StringUtil.isNotEmpty(inspectionInfo.getConfirm_id())){
			// 存在校核人员时，直接获取校核人员的电子签名
			request.setAttribute("exam_op_img",
					imageTool.getEmployeeImg(inspectionInfo.getConfirm_id()));
		}else{
			// 不存在校核人员时，获取提交原始记录时上传的校核人员手写签字图片
			request.setAttribute("exam_op_img",
					imageTool.getRecordImg(inspectionInfo.getId()));
		}
		
		// 报告图片信息
		request.getSession().setAttribute("PICTURE",reportItemRecordService.getPicFromRecord(id));

		// 以下部分为原始记录模版设置时固定需要的内容
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("report_item", inspectionInfo.getYsjl_item());
		paraMap.put("total_page", 
				(inspectionInfo.getYsjl_item().split(",")).length);
		paraMap.put("TotalP", 
				(inspectionInfo.getYsjl_item().split(",")).length);
		paraMap.put("report_root_path", report.getRootpath());
		paraMap.put("report_file", report.getYsjl_file());
		paraMap.put("MRDataSet", report.getMrdataset());
		
		request.getSession().setAttribute("REPORTPARA", paraMap); // 报告ID
		request.getSession().setAttribute("report_id", report_id); // 报告ID
		request.getSession().setAttribute("deviceDocument", deviceDocument); // 设备信息
		request.getSession().setAttribute("inspectionInfo", inspectionInfo); // 报检信息
		request.getSession().setAttribute("report", report); 		// 报告信息
		request.getSession().setAttribute("reportItemRecordList", 	// 检查项目
				reportItemRecordList);
		/*request.getSession().setAttribute("reportItemList", 		// 报告项目
				reportItemList);*/
		request.getSession().setAttribute("printer_name", printer_name); // 人员打印机名称
		if (inspectionInfo.getAdvance_time() != null) {
			String advance_time = DateUtil.getDateTime("yyyy-MM-dd",
					inspectionInfo.getAdvance_time());
			request.getSession().setAttribute("JGHZH",
					inspectionInfoService.queryJGHZH(advance_time)); // 获取检验机构核准证号
		}

	}
	
	/**
	 * 打印原始记录show
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
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryRecordInfos(id);
		request.getSession().setAttribute("mapList", mapList);
		return "app/mobile/record_print_left";
	}
	
	/**
	 * 打印原始记录
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
		return "app/mobile/record_print";
	}

	// 打印原始记录
	@RequestMapping(value = "print_record")
	public synchronized String print_record(
			HttpServletRequest request, String id,
			String report_id, String isLast, String op_type) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String ids[] = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
				inspectionInfo.setIs_print_ysjl("1");//标记原始记录为已打印
				inspectionInfoService.save(inspectionInfo);
				logService.setLogs(ids[i], "打印原始记录", "打印原始记录", curUser.getId(),
						curUser.getName(), new Date(), request.getRemoteAddr());
			}
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("记录原始记录打印日志出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/mobile/record_print";
	}
	
	/**
	 * 打印原始记录show
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showGcPrint")
	public String showGcPrint(HttpServletRequest request, String id)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryGcRecordInfos(id);
		request.getSession().setAttribute("mapList", mapList);
		return "app/tanker/gc_record_print_left";
	}
	
	/**
	 * 打印原始记录
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
	@RequestMapping(value = "doGcPrint")
	public String doGcPrint(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取打印数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/tanker/gc_record_print";
	}

	// 打印原始记录
	@RequestMapping(value = "print_gc_record")
	public synchronized String print_gc_record(
			HttpServletRequest request, String id,
			String report_id, String isLast, String op_type) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String ids[] = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
				inspectionInfo.setIs_print_ysjl("1");//标记原始记录为已打印
				inspectionInfoService.save(inspectionInfo);
				logService.setLogs(ids[i], "打印原始记录", "打印原始记录", curUser.getId(),
						curUser.getName(), new Date(), request.getRemoteAddr());
			}
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("记录原始记录打印日志出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/tanker/gc_record_print";
	}
	
	/**
	 * 校核原始记录show（电梯）
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showCheck")
	public String showCheck(HttpServletRequest request, String id)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryRecordInfos(id);
		request.getSession().setAttribute("mapList", mapList);
		return "app/mobile/record_check_left";
	}
	
	/**
	 * 校核原始记录show（电梯）
	 * 
	 * @param request
	 * @param ids --
	 *            业务信息ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showChecks")
	public String showRecordList(HttpServletRequest request, String ids)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryRecordInfos(ids);
		request.getSession().setAttribute("mapList", mapList);
		return "app/mobile/record_batch_left";
	}
	
	/**
	 * 校核原始记录（电梯）
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
	@RequestMapping(value = "doCheck")
	public String doCheck(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取原始记录数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/mobile/record_check";
	}
	
	/**
	 * 校核原始记录时，show content（电梯）
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doChecks")
	public String doChecks(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取原始记录数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/mobile/record_batch";
	}
	
	/**
	 * 批量校核（电梯）
	 * 
	 * @param request
	 * @param dataStr --
	 *            参数json串
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchCheck")
	@ResponseBody
	public Map<String, Object> batchCheck(String dataStr,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		String emp_id = emp.getId();
		String emp_name = emp.getName();
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		// 流程提交
		Map<String, Object> map = new HashMap<String, Object>();
		// 业务ID
		String info_id = request.getParameter("ids");
		String is_report_confirm = dataMap.get("is_report_confirm").toString(); 		// 校核结果	
		String report_confirm_remark = dataMap.get("report_confirm_remark").toString(); // 不通过原因
		if(StringUtil.isNotEmpty(info_id)){
    		String[] ids=info_id.split(",");
			for (String id : ids) {
				// 1、保存校核信息
				InspectionInfo inspectionInfo = inspectionInfoService.get(id);
				inspectionInfo.setIs_report_confirm(is_report_confirm);
				if (StringUtil.isNotEmpty(report_confirm_remark)) {
					inspectionInfo.setReport_confirm_remark(report_confirm_remark);
				}
				/*inspectionInfo.setConfirm_Date(
						DateUtil.convertStringToDate(Constant.defaultDatePattern, DateUtil.getCurrentDateTime()));*/
				inspectionInfo.setConfirm_id(emp_id);
				inspectionInfo.setConfirm_name(emp_name);
				inspectionInfoService.save(inspectionInfo);

				// 2、记录日志
				String op_desc = "";
				if ("2".equals(is_report_confirm)) {
					op_desc = "校核不通过，" + report_confirm_remark;
				} else {
					op_desc = "校核通过";
				}
				logService.setLogs(id, "原始记录校核", op_desc, user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
    		map.put("success", true);
    		map.put("msg", "校核成功！");
    	}else{
    		map.put("success", false);
    		map.put("msg", "请选择要校核的报告！");
    	}
		return JsonWrapper.successWrapper("1");
	}
	
	/**
	 * 校核原始记录show（罐车）
	 * 
	 * @param request
	 * @param id --
	 *            报检业务ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showGcCheck")
	public String showGcCheck(HttpServletRequest request, String id)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryGcRecordInfos(id);
		request.getSession().setAttribute("mapList", mapList);
		return "app/tanker/gc_record_check_left";
	}
	
	/**
	 * 校核原始记录show（罐车）
	 * 
	 * @param request
	 * @param ids --
	 *            业务信息ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showGcChecks")
	public String showGcRecordList(HttpServletRequest request, String ids)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList = reportItemRecordService.queryGcRecordInfos(ids);
		request.getSession().setAttribute("mapList", mapList);
		return "app/tanker/gc_record_batch_left";
	}
	
	/**
	 * 校核原始记录（罐车）
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
	@RequestMapping(value = "doGcCheck")
	public String doGcCheck(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取原始记录数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/tanker/gc_record_check";
	}
	
	/**
	 * 校核原始记录时，show content（罐车）
	 * 
	 * @param request
	 * @param id --
	 *            业务信息ID
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doGcChecks")
	public String doGcChecks(HttpServletRequest request, String id,
			String report_id) {
		try {
			getPrintData(request, id, report_id);
		} catch (Exception e) {
			log.error("获取原始记录数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return "app/tanker/gc_record_batch";
	}
	
	/**
	 * 批量校核（罐车）
	 * 
	 * @param request
	 * @param dataStr --
	 *            参数json串
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchGcCheck")
	@ResponseBody
	public Map<String, Object> batchGcCheck(String dataStr,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		String emp_id = emp.getId();
		String emp_name = emp.getName();
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		// 流程提交
		Map<String, Object> map = new HashMap<String, Object>();
		// 业务ID
		String info_id = request.getParameter("ids");
		String is_report_confirm = dataMap.get("is_report_confirm").toString(); 		// 校核结果	
		String report_confirm_remark = dataMap.get("report_confirm_remark").toString(); // 不通过原因
		if(StringUtil.isNotEmpty(info_id)){
    		String[] ids=info_id.split(",");
			for (String id : ids) {
				// 1、保存校核信息
				InspectionInfo inspectionInfo = inspectionInfoService.get(id);
				inspectionInfo.setIs_report_confirm(is_report_confirm);
				if (StringUtil.isNotEmpty(report_confirm_remark)) {
					inspectionInfo.setReport_confirm_remark(report_confirm_remark);
				}
				/*inspectionInfo.setConfirm_Date(
						DateUtil.convertStringToDate(Constant.defaultDatePattern, DateUtil.getCurrentDateTime()));*/
				inspectionInfo.setConfirm_id(emp_id);
				inspectionInfo.setConfirm_name(emp_name);
				inspectionInfoService.save(inspectionInfo);

				// 2、记录日志
				String op_desc = "";
				if ("2".equals(is_report_confirm)) {
					op_desc = "校核不通过，" + report_confirm_remark;
				} else {
					op_desc = "校核通过";
				}
				logService.setLogs(id, "原始记录校核", op_desc, user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
    		map.put("success", true);
    		map.put("msg", "校核成功！");
    	}else{
    		map.put("success", false);
    		map.put("msg", "请选择要校核的报告！");
    	}
		return JsonWrapper.successWrapper("1");
	}
	
	
	
	/**
	 * 移动端校验原始记录是否已经提交
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-10-23 上午09:45:00
	 */
	@RequestMapping("checkYsjls")
	@ResponseBody
	public HashMap<String, Object> checkYsjls(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String check_codes = request.getParameter("check_codes");
			if(StringUtil.isEmpty(check_codes)){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}
			map = reportItemRecordService.check_Ysjls(map, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，校验原始记录提交状态失败！");
		}
		return map;
	}
	/**
	 * 查询用户employee信息
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping("getUser")
	@ResponseBody
	public HashMap<String, Object> getUser(String id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			User user = reportItemRecordService.getUser(id);
			map.put("data", user.getEmployee());
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "查询用户信息失败！");
		}
		
		return map;
	}
	/**
	 * 查询待处理任务条数
	 * author pingZhou
	 * @param request
	 * @return
	 */
	@RequestMapping("allDealCount")
	@ResponseBody
	public HashMap<String, Object> allDealCount(HttpServletRequest request){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			List<HashMap<String, Object>> countMap =  this.reportItemRecordService.allDealCount(request);
			map.put("data", countMap);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			map.put("success", false);
			map.put("msg", "退回失败！");
			
		}
		return map;
	}
	//-----------------------------移动端代码----------------------------
	/**
	 * 移动端获取所有机电、承压检验部门人员信息
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("getCheckUsersList")
	@ResponseBody
	public Map<String, Object> getCheckUsersList(HttpServletRequest request,String orgId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			String codeupdateTime = request.getParameter("codeupdateTime");
			if(codeupdateTime==null||"null".equals(codeupdateTime)) {
				map.put("codeTable", reportItemRecordService.queryCodeTable(null,null));
				
			}else {
				String codeIds = reportItemRecordService.queryCodesByIds(codeupdateTime);
				map.put("codeTable", reportItemRecordService.queryCodeTable(null,codeIds));
				map.put("codeIds", codeIds.replace(",", "','"));
			}
			
			map.put("user", reportItemRecordService.getCheckUsersList(orgId));
			
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，获取部门人员信息失败！");
		}
		return map;
	}

	
	
	/**
	 * pc端分页保存
	 * author pingZhou
	 * @param request
	 * @param list
	 * @return
	 */
	@RequestMapping("saveMap")
	@ResponseBody
	private HashMap<String, Object> saveMap(HttpServletRequest request, @RequestBody List<Map<String, Object>> list) {
		try {

			String page = request.getParameter("page");
			this.reportItemRecordService.saveMapPc(request,list, page);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	/**
	 * 转交他人录入
	 * author pingZhou
	 * @param request
	 * @return
	 */
	@RequestMapping("saveInspToOther")
	@ResponseBody
	private HashMap<String, Object> saveInspToOther(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String data = request.getParameter("data");
			
			JSONObject object = JSONObject.fromString(data);
			
			InspectionInfo info =  this.reportItemRecordService.saveInspToOther(request,object);
			map.put("success", true);
			map.put("data", info);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			map.put("success", true);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 查询原始记录数据
	 */
	@RequestMapping("getInspData")
	@ResponseBody
	private HashMap<String, Object> getInspData(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {


			String infoId = request.getParameter("infoId");
			if(infoId==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}

			map =  this.reportItemRecordService.getInspData(map,request,infoId);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	
	/**
	 * 提交校核
	 * author pingZhou
	 * @param request
	 * @return
	 */
	@RequestMapping("subToConfirm")
	@ResponseBody
	private HashMap<String, Object> subToConfirm(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String data = request.getParameter("data");
			
			JSONObject object = JSONObject.fromString(data);
			
			InspectionInfo info = this.reportItemRecordService.subConfirm(request,object);
			
			map.put("success", true);
			map.put("data", info);
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	
	
	/**
	 * 提交校核（不包括数据保存）
	 * author pingZhou
	 * @param request
	 * @return
	 */
	@RequestMapping("subToConfirmWithoutData")
	@ResponseBody
	private HashMap<String, Object> subToConfirmWithoutData(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			String data = request.getParameter("data");
			
			JSONObject object = JSONObject.fromString(data);
			if(object==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}

			if(!object.has("nextOpId")||object.get("nextOpId")==null){
				map.put("success", false);
				map.put("msg", "请选择校核人员！");
				return map;
			}
			
			this.reportItemRecordService.subToConfirmWithoutData(request, map);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	
	@RequestMapping("getCopyData")
	@ResponseBody
	private HashMap<String, Object> getCopyData(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {


			String infoId = request.getParameter("infoId");
			if(infoId==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}

			this.reportItemRecordService.getCopyData(map,request,infoId);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	/**
	 * 分页复制
	 * author pingZhou
	 * @param request
	 * @return
	 */
	@RequestMapping("getCopyDataByPage")
	@ResponseBody
	private HashMap<String, Object> getCopyDataByPage(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {


			String infoId = request.getParameter("infoId");
			if(infoId==null){
				map.put("success", false);
				map.put("msg", "请传入参数！");
				return map;
			}

			this.reportItemRecordService.getCopyDataByPage(map,request,infoId);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	
	/**
	 * 退回原始记录录入
	 * author pingZhou
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("backToInput")
	@ResponseBody
	public HashMap<String, Object> backToInput(HttpServletRequest request,String ids){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			 this.reportItemRecordService.backToInput(request,ids,null,null);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			map.put("success", false);
			map.put("msg", "退回失败！");
			
		}
		return map;
	}
	
	
	
	/**
	 * 获取码表信息
	 * author pingZhou
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping("queryCodeTable")
	@ResponseBody
	public Map<String, Object> queryCodeTable(HttpServletRequest request,String code){
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			
			map.put("codeTable", reportItemRecordService.queryCodeTable(code,null));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取码表信息失败！");
		}
		return map;
	}
	@RequestMapping("xxx")
	@ResponseBody
	private void xxx(HttpServletRequest request,int len) throws InterruptedException {
		  // 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(8);
        long st = System.currentTimeMillis(); 
        for (int i = 0; i < len; i++) {
            System.out.println("创建线程" + i);
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
						reportItemRecordService.queryEmployeeList();
						reportItemRecordService.getTasks("402884c4477c9bac01477ff32fc6005b");
						//tjyptResourcePathService.queryResourceNeedReceive("1");

					} catch (ParseException e) {
						
						e.printStackTrace();
					}
                }
            };
            // 在未来某个时间执行给定的命令
            service.execute(run);
        }
        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        long et = System.currentTimeMillis();
        System.out.println("all thread complete"+ (et-st));
	}
	
	
	@RequestMapping("test")
	@ResponseBody
	public Map<String, Object> test(HttpServletRequest request,String c) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		int len = Integer.parseInt(c);
		 // 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < len; i++) {
            System.out.println("创建线程" + i);
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
						reportItemRecordService.queryEmployeeList();
						//reportItemRecordService.getTasks("402884c4477c9bac01477ff32fc6005b");
						//tjyptResourcePathService.queryResourceNeedReceive("1");

					} catch (ParseException e) {
						
						e.printStackTrace();
					}
                }
            };
            // 在未来某个时间执行给定的命令
            service.execute(run);
        }
        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");

		
		return map;
	}
	
}
