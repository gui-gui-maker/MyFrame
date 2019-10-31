package com.lsts.inspection.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportRecordParse;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.ReportItemRecordService;
import com.lsts.inspection.service.ReportItemValueService;
import com.lsts.inspection.service.ReportRecordParseService;
import com.lsts.log.service.SysLogService;

/**
 * 移动端原始记录检验项目解析控制器
 * 
 * @ClassName ReportRecordParseAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-27 上午11:20:00
 */
@Controller
@RequestMapping("report/record/parse")
public class ReportRecordParseAction extends
		SpringSupportAction<ReportRecordParse, ReportRecordParseService> {

	@Autowired
	private ReportRecordParseService reportRecordParseService;
	@Autowired
	private InspectionInfoService inspectionInfoManager;
	@Autowired
	private	SysLogService logService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private InspectionCommonService inspectionCommonService;
	@Autowired
	private ReportItemValueService reportItemValueService;
	@Autowired
	private ReportItemRecordService reportItemRecordService;
	/**
	 * 保存
	 * 
	 * @param request
	 * @param reportRecordParse
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			ReportRecordParse reportRecordParse) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			reportRecordParse.setData_status("0");	// 状态（0：启用 99：停用）
			reportRecordParse.setLast_mdy_uid(user.getId());
			reportRecordParse.setLast_mdy_uname(user.getName());
			reportRecordParse.setLast_mdy_date(new Date());
			reportRecordParseService.save(reportRecordParse);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(reportRecordParse);
	}

	// 删除移动端原始记录检验项目解析信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		reportRecordParseService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	//-----------------新版app-------------------------------------------------------------
	
	
	/**
	 * 原始记录转换生成报告
	 * 
	 * @param request
	 * 
	 * @return 
	 * @throws 
	 * @author GaoYa
	 * @since 2018-08-06 17:19:00
	 */
	@ResponseBody
	@RequestMapping("beginParse")
	public HashMap<String, Object> beginParse(HttpServletRequest request) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {

			// 获取请求参数
			Map<String, String> reqParamsMap = reportRecordParseService.getReqParams(request);

			// 获取报告业务ID
			String fkInfoId = reqParamsMap.get("INFO_ID");

			// 获取报告业务信息
			InspectionInfo info = inspectionInfoManager.get(fkInfoId);

			// 原始记录转换成报告
			reportRecordParseService.dealRecordToReport(request, fkInfoId);
/*
			// 处理报告书编号
			if(StringUtil.isEmpty(info.getReport_sn())) {
				reportItemValueService.saveReportSn(info);
			}
			*/
			// 更新原始记录转换报告状态
			reportRecordParseService.updateRecordStatus(info);

			// 设置返回参数	
			returnMap.put("success", true);
			returnMap.put("infoId", fkInfoId);
			
			/*// 启动流程
			HashMap<String, Object> startFlowResultMap = reportRecordParseService.startFlow(request, info);

			// 自动提交审核，提交流程
			boolean start_result = Boolean.valueOf(String.valueOf(startFlowResultMap.get("success")));
			// 流程启动成功时，自动提交审核
			if (start_result) {
				HashMap<String, Object> subFlowResultMap = inspectionInfoManager.subFlow(request, reqParamsMap, info);
				boolean sub_result = Boolean.valueOf(String.valueOf(subFlowResultMap.get("success")));
				if (sub_result) {
					// 更新原始记录状态并记录操作日志
					reportRecordParseService.updateRecord(request, info);
				}
				return subFlowResultMap;
			} else {
				return startFlowResultMap;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "原始记录转换报告失败.");
		}
		return returnMap;
	}
	
	/**
	 * 启动流程并提交审核
	 * 
	 * @param request
	 * 
	 * @return 
	 * @throws 
	 * @author GaoYa
	 * @since 2018-08-13 09:05:00
	 */
	@ResponseBody
	@RequestMapping("startFlow")
	public HashMap<String, Object> startFlow(HttpServletRequest request) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			// 获取请求参数
			Map<String, String> reqParamsMap = reportRecordParseService.getReqParams(request);

			// 获取报告业务ID
			String fkInfoId = reqParamsMap.get("INFO_ID");

			// 获取报告业务信息
			InspectionInfo info = inspectionInfoManager.get(fkInfoId);
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			info.setRecordConfirmId(((User)user.getSysUser()).getEmployee().getId());
			info.setRecordConfirmOp(((User)user.getSysUser()).getEmployee().getName());
			//老的app保存了这些
			info.setIs_report_confirm(reqParamsMap.get("CONCLUSION"));
			info.setReport_confirm_remark(reqParamsMap.get("REMARK"));
			String remark = "";
			if("1".equals(reqParamsMap.get("CONCLUSION"))){
				remark += "校核通过"; 
			}else{
				remark += "校核不通过";
			}
			if(StringUtil.isNotEmpty(reqParamsMap.get("REMARK"))){
				remark += "，原因："+reqParamsMap.get("REMARK");
			}
			inspectionInfoManager.save(info);
			logService.setLogs(info.getId(), "原始记录校核", remark, user.getId(),
					user.getName(), new Date(), request.getRemoteAddr());
			if(!"1".equals(reqParamsMap.get("CONCLUSION")))
			{
				throw new Exception("校核不通过："+remark);
			}
			// 启动流程,建议通过才启动流程
			HashMap<String, Object> startFlowResultMap = reportRecordParseService.startFlow(request, info);

			// 自动提交审核，提交流程
			boolean start_result = Boolean.valueOf(String.valueOf(startFlowResultMap.get("success")));
			// 流程启动成功时，自动提交审核
			if (start_result) {
				//添加报告书编号
				createReportSn(info);
				//提交审核
				HashMap<String, Object> subFlowResultMap = inspectionInfoManager.subFlow(request, reqParamsMap, info);
				boolean sub_result = Boolean.valueOf(String.valueOf(subFlowResultMap.get("success")));
				if (sub_result) {
					info.setRecordFlow("2");
					// 更新原始记录校核状态 有误。。。
					//reportRecordParseService.updateRecordFlow(info);
					// 记录操作日志
					reportRecordParseService.addRecordLog(request, info);
				}
				return subFlowResultMap;
			} else {
				return startFlowResultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "提交失败！");
		}
		return returnMap;
	}
	public void createReportSn(InspectionInfo info) throws Exception{
		// 2、生成报告书编号
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
				Map<String, Object> reportSnMap = inspectionCommonService.createReportSn("moCreateReport", info.getId(),
						report_type, check_type, device_type);
				report_sn = String.valueOf(reportSnMap.get("report_sn"));
				// 报告输编号插入业务主表
				info.setReport_sn(report_sn);
				inspectionInfoManager.save(info);
			}
		} else {
			report_sn = info.getReport_sn();
		}
		// 2.4 将报告书编号更新到报告检验项目参数表
		reportItemValueService.updateItemValue(info.getId(), report_type, "REPORT_SN", report_sn);
		// 2.5 将报告书编号更新到原始记录检验项目参数表
		reportItemRecordService.updateItemValue(info.getId(), report_type, "REPORT_SN", report_sn);
	}
	
	// 启用对应关系
    @RequestMapping(value = "enable")
    @ResponseBody
    public HashMap<String, Object> enable(HttpServletRequest request) throws Exception {	
        return reportRecordParseService.enable(request);
    }
    
    // 停用对应关系
    @RequestMapping(value = "disable")
    @ResponseBody
    public HashMap<String, Object> disable(HttpServletRequest request) throws Exception {	
        return reportRecordParseService.disable(request);
    }
    
    // 对比对应关系
    @RequestMapping(value = "compare")
    @ResponseBody
    public HashMap<String, Object> compare(HttpServletRequest request) throws Exception {	
        return reportRecordParseService.compare(request);
    }
    
    // 取消对应关系错误标记
    @RequestMapping(value = "cancelTags")
    @ResponseBody
    public HashMap<String, Object> cancelTags(HttpServletRequest request) throws Exception {	
        return reportRecordParseService.cancelTags(request);
    }
	//-----------------新版app-------------------------------------------------------------
	
}
