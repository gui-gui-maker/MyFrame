package com.lsts.report.web;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.report.bean.ReportTransfer;
import com.lsts.report.bean.ReportTransferRecord;
import com.lsts.report.service.ReportTransferRecordService;
import com.lsts.report.service.ReportTransferService;

import util.TS_Util;

/**
 * 业务服务部前后台报告交接业务控制器
 * 
 * @ClassName ReportTransferAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-03 下午02:12:00
 */
@Controller
@RequestMapping("report/transfer")
public class ReportTransferAction extends
		SpringSupportAction<ReportTransfer, ReportTransferService> {
	@Autowired
	private ReportTransferService reportTransferService;
	@Autowired
	private ReportTransferRecordService reportTransferRecordService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private OrgManagerImpl orgManager;

	
	// 保存并提交业务服务部前后台报告交接业务信息
	@RequestMapping(value = "commitBasic")
	@ResponseBody
	public HashMap<String, Object> commitBasic(HttpServletRequest request,
			@RequestBody
			ReportTransfer reportTransfer) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			ReportTransfer info = reportTransferService.commitBasic(
					reportTransfer, request);
			wrapper.put("success", true);
			wrapper.put("obj", info);
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return wrapper;
	}
	
	/**
	 * 打印报告时，自动提交后台检验报告报送
	 * @param ids -- 报告业务信息ID
	 * 
	 * @author GaoYa
	 * @date 2017-04-12 上午11:03:00
	 */
	@RequestMapping(value = "autoCommit")
	@ResponseBody
	public HashMap<String, Object> autoCommit(String ids)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User) user.getSysUser();
		Org org = (Org) uu.getOrg();
		
		try {
			// 业务服务部才自动提交后台检验报告报送
			// 罐车中心不提交
			// 2017-11-23邓雯雯要求，增加曹鹏自动报送功能
			// 获取非业务服务部的自动报送人姓名
			String user_name = Factory.getSysPara().getProperty("REPORT_TRANSFER_USER_NAME");
			if (StringUtil.isEmpty(user_name)) {
				user_name = Constant.REPORT_TRANSFER_USER_NAME;
			}			
			if("fuwu".equals(org.getOrgCode()) || user_name.contains(uu.getName())){
				//String ids = request.getParameter("ids");
				String[] info_ids = ids.split(",");

				int count = 0;
				String com_name = "";
				String report_sn = "";
				String org_id = "";
				String org_name = "";
				ReportTransfer reportTransfer = new ReportTransfer();
				// 获取业务流水号
				String sn = TS_Util.getReportTransferSn(Integer.valueOf(count), conn);
				reportTransfer.setSn(sn);
				reportTransfer.setData_status("1"); // 数据状态（1：已提交）
				reportTransfer.setEnter_op_id(uu.getId()); // 此处不用到电子签名，所以不使用employee
				reportTransfer.setEnter_op_name(uu.getName());
				reportTransfer.setEnter_date(new Date());
				reportTransfer.setCommit_user_id(uu.getId());
				reportTransfer.setCommit_user_name(uu.getName());
				reportTransfer.setCommit_date(new Date());
				reportTransferService.save(reportTransfer);

				for (int i = 0; i < info_ids.length; i++) {
					InspectionInfo info = inspectionInfoService.get(info_ids[i]);
					if (info != null) {
						if(StringUtil.isNotEmpty(info.getCheck_unit_id())){
							Org check_org = orgManager.get(info.getCheck_unit_id());
							// 机电类的才自动生成后台检验报告报送前台记录
							if(check_org.getOrgCode().contains("jd")){
								List<ReportTransferRecord> list = reportTransferRecordService.getInfos(info.getReport_com_name(), info.getReport_sn());
								if(list.isEmpty()){
									count++;
									ReportTransferRecord recordInfo = new ReportTransferRecord();
									// 获取业务流水号
									recordInfo.setSn(TS_Util.getReportTransferRecordSn(Integer.valueOf(count), conn));
									recordInfo.setReport_count(1);
									recordInfo.setData_status("1"); // 数据状态（1：已提交）

									if (StringUtil.isNotEmpty(info.getReport_com_name())) {
										recordInfo.setCom_name(info.getReport_com_name());
										if (StringUtil.isNotEmpty(com_name)) {
											com_name += ",";
										}
										com_name += info.getReport_com_name();
									}
									if (StringUtil.isNotEmpty(info.getReport_sn())) {
										recordInfo.setReport_sn(info.getReport_sn());
										if (StringUtil.isNotEmpty(report_sn)) {
											report_sn += ",";
										}
										report_sn += info.getReport_sn();
									}
									if (StringUtil.isEmpty(org_id)) {
										org_id = info.getCheck_unit_id();
									}
									if (StringUtil.isNotEmpty(org_id)) {
										recordInfo.setOrg_name(check_org.getOrgName());
										org_name = check_org.getOrgName();
									}
									recordInfo.setOrg_id(info.getCheck_unit_id());
									recordInfo.setInfo_id(info.getId());
									recordInfo.setDevice_id(info.getFk_tsjc_device_document_id());
									recordInfo.setReport_id(info.getReport_type());
									recordInfo.setAdvance_fees(info.getAdvance_fees());
									recordInfo.setCommit_user_id(uu.getId());
									recordInfo.setCommit_user_name(uu.getName());
									recordInfo.setCommit_time(new Date());
									recordInfo.setReportTransfer(reportTransfer);
									recordInfo.setRemark("系统自动生成");
									reportTransferRecordService.save(recordInfo);
								}
							}
						}
					}
				}
			
				reportTransfer.setOrg_id(org_id);
				reportTransfer.setOrg_name(org_name);
				reportTransfer.setCom_name(com_name); 	// 记录单位名称，方便查询
				reportTransfer.setReport_sn(report_sn); // 记录报告编号，方便查询
				reportTransfer.setReport_count(String.valueOf(count));
				reportTransferService.save(reportTransfer);
			}
			wrapper.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		// 释放连接
		Factory.getDB().freeConnetion(conn);
		return wrapper;
	}

	// 获取业务服务部前后台报告交接业务信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return reportTransferService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取业务服务部前后台报告交接业务信息失败！");
		}
	}
	
	// 获取业务服务部前后台报告交接业务信息（用于打印）
	@RequestMapping(value = "getPrintDetail")
	@ResponseBody
	public HashMap<String, Object> getPrintDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return reportTransferService.getPrintDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取业务服务部前后台报告交接业务信息失败！");
		}
	}

	// 删除业务服务部前后台报告交接业务信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportTransferService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}

	// 业务服务部前后台报告交接业务提交
	@RequestMapping(value = "commit")
	@ResponseBody
	public HashMap<String, Object> commit(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> wrapper = JsonWrapper.successWrapper();
		boolean isSuccess = reportTransferService.commit(ids, request);
		wrapper.put("success", isSuccess);
		return wrapper;

	}
	
	// 签收业务服务部前后台报告交接业务信息
	@RequestMapping(value = "receive")
	@ResponseBody
	public HashMap<String, Object> receive(HttpServletRequest request, String ids, String report_transfer_id) throws Exception {
		HashMap<String, Object> wrapper = JsonWrapper.successWrapper();
		HashMap<String, Object> map = reportTransferService.receiveChoose(request, ids, report_transfer_id);
		wrapper.put("success", true);
		wrapper.put("info_id", map.get("info_ids"));
		wrapper.put("device_ids", map.get("device_ids"));
		wrapper.put("report_types", map.get("report_types"));
		wrapper.put("bigClasss", map.get("bigClasss"));
		wrapper.put("bhg_report_sn", map.get("bhg_report_sn"));
		return wrapper;
	}
	
	// 批量签收业务服务部前后台报告交接业务
	@RequestMapping(value = "receives")
	@ResponseBody
	public HashMap<String, Object> receives(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> wrapper = JsonWrapper.successWrapper();
		HashMap<String, Object> map = reportTransferService.receives(ids, request);
		wrapper.put("success", true);
		wrapper.put("info_id", map.get("info_ids"));
		wrapper.put("device_ids", map.get("device_ids"));
		wrapper.put("report_types", map.get("report_types"));
		wrapper.put("bigClasss", map.get("bigClasss"));
		wrapper.put("bhg_report_sn", map.get("bhg_report_sn"));
		return wrapper;
	}
	
	// 获取报告信息
	@RequestMapping(value = "queryReportInfos")
	@ResponseBody
	public HashMap<String, Object> getReportInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", reportTransferService.queryReportInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取报告信息失败！");
		}
		return map;
	}
	
	/**
	 * 工作服务统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "selectTj")
	@ResponseBody
	public HashMap<String,Object> selectTj(HttpServletRequest request ,HttpServletResponse response) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {
			String name=request.getParameter("name");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");

			List<Map<String, String>> list=reportTransferService.gzfwTj(name,startTime,endTime);
			
			 data.put("success", true);
			 data.put("data", list);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
			data.put("msg", "查询失败！");
		}
    	return  data;
	}
}
