package com.lsts.report.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportError;
import com.lsts.report.bean.ReportErrorDTO;
import com.lsts.report.bean.ReportErrorInfo;
import com.lsts.report.dao.ReportErrorDao;
import com.lsts.report.dao.ReportErrorInfoDao;

import util.TS_Util;

/**
 * 不符合报告业务逻辑对象
 * @ClassName ReportErrorService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 14:05:00
 */
@Service("reportErrorService")
public class ReportErrorService extends EntityManageImpl<ReportError, ReportErrorDao> {
	@Autowired
	private ReportErrorDao reportErrorDao;
	@Autowired
	private ReportErrorInfoDao reportErrorInfoDao;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private AttachmentManager attachmentManager;
	
	
	/**
	 * 获取不符合编号获取不符合报告
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-08-07 10:42:00
	 */
	public ReportError getInfosBySn(String sn, String report_sn) {
		return reportErrorDao.getInfosBySn(sn, report_sn);
	}
	
	/**
	 * 
	 * 保存不符合报告主表
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveBasic(ReportError reportError,
			HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User)user.getSysUser();
		Org org = TS_Util.getCurOrg(user);
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		String report_sns = "";
		try {
			// 1、保存不符合报告明细数据
			String error_category = "";
			for (ReportErrorInfo info : reportError.getReportErrorInfo()) {
				if(StringUtil.isEmpty(error_category)){
					error_category = info.getError_category();
				}else{
					if(!error_category.equals(info.getError_category())){
						wrapper.put("success", false);
						wrapper.put("msg", "一次只能提交相同项目的不符合报告，请重新填写！");
						return wrapper;
					}
				}
				if (StringUtil.isNotEmpty(info.getReport_sn())) {
					if (StringUtil.isNotEmpty(info.getReport_sn().trim())) {
						List<ReportErrorInfo> recordInfos = reportErrorInfoDao.queryByReportSn(info.getInfo_id(),
								info.getReport_sn().trim());
						if (!recordInfos.isEmpty()) {
							wrapper.put("success", false);
							wrapper.put("msg", "系统检测到报告编号（" + info.getReport_sn().trim() + "）已提交过不符合报告，不能重复提交，请检查！");
							return wrapper;
						} else {
							if (report_sns.length() > 0) {
								report_sns += ",";
							}
							report_sns += info.getReport_sn().trim();
							List<InspectionInfo> list1 = inspectionInfoDao.queryInspectionInfo(info.getReport_sn().trim());
							if (list1.size() == 0) {
								/*
								 * wrapper.put("success", false); wrapper.put("msg",
								 * "报告书编号（"+info.getReport_sn()+"）不存在，请核查！"); return
								 * wrapper;
								 */
							} else {
								if (list1.size() != 1) {
									wrapper.put("success", false);
									wrapper.put("msg", "报告书编号（" + info.getReport_sn().trim() + "）存在重号的报告，请联系系统管理员！");
									return wrapper;
								} else {
									info.setInfo_id(list1.get(0).getId());
									info.setReport_id(list1.get(0).getReport_type());
								}
							}
						}
					}
				}

				if (StringUtil.isEmpty(info.getId())) {
					info.setCreate_user_id(emp.getId());
					info.setCreate_user_name(emp.getName());
					info.setCreate_date(new Date());
				}
				info.setMdy_user_id(emp.getId());
				info.setMdy_user_name(emp.getName());
				info.setMdy_date(new Date());
				info.setData_status("0"); // 数据状态（0：正常）
				info.setReportError(reportError);
				/*reportErrorInfoDao.save(info);*/
			}

			// 2、保存不符合报告主表
			if (StringUtil.isNotEmpty(reportError.getError_dep_id())) { // 责任部门ID
				Org dep = orgManager.get(reportError.getError_dep_id());
				reportError.setError_dep_name(dep.getOrgName()); // 责任部门名称
			}

			// 3、生成不符合报告编号（编号规则：2015-001 ）
			String pre_sn = "";
			String org_code = user.getDepartment().getOrgCode();
			if (org_code.contains("jd") || org_code.contains("cy")) {
				pre_sn += org_code.toUpperCase() + "-";
			}
			pre_sn += TS_Util.getCurYear() + "-";
			if (StringUtil.isEmpty(reportError.getId())) {
				reportError.setSn(pre_sn + queryNextSn(pre_sn)); // 不符合编号
			} else {
				if (StringUtil.isEmpty(reportError.getSn())) {
					reportError.setSn(pre_sn + queryNextSn(pre_sn)); // 不符合编号
				}
			}

			if (StringUtil.isNotEmpty(reportError.getFind_user_id())) {
				Employee employee = employeeManager.get(reportError.getFind_user_id());
				if (employee != null) {
					reportError.setFind_user_name(employee.getName());
				}
			}

			reportError.setError_category(error_category); 	// 不符合项目类型（0：报告 1：其他）
			reportError.setReport_sn(report_sns); // 报告书编号（多个以逗号分隔）
			reportError.setData_status("01"); // 数据状态（01：质量部已记录，待审核）

			boolean isAdd = true;
			if (StringUtil.isEmpty(reportError.getId())) {
				reportError.setCreate_user_id(emp.getId());
				reportError.setCreate_user_name(emp.getName());
				reportError.setCreate_date(new Date());
				isAdd = true;
			} else {
				isAdd = false;
			}
			reportErrorDao.save(reportError);

			if (isAdd) {
				// 获取发送内容
				String content = Constant.getReportErrorNoticeContent(reportError.getSn(),
						DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));
				// 获取部门主任用户信息ID
				String employee_id = employeesService.getEmpIDByRoleName(Constant.ROLE_NAME_BMZR, org.getId());
				if (StringUtil.isNotEmpty(employee_id)) {
					// 获取部门主任用户信息
					Employee employee = employeesService.get(employee_id);
					if (employee != null) {
						// 获取发送目标（部门主任）手机号码
						String destNumber = employee.getMobileTel();
						if (StringUtil.isNotEmpty(destNumber)) {
							// 发送微信
							messageService.sendWxMsg(request, reportError.getId(), Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD, content, destNumber);
						}
					}
				}
				logService.setLogs(reportError.getId(), "提交不符合报告", "质量部提交不符合报告", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			} else {
				logService.setLogs(reportError.getId(), "修改不符合报告", "质量部修改不符合报告", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}

			wrapper.put("success", true);
			wrapper.put("obj", reportError);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return wrapper;
	}
	
	/**
	 * 获取不符合报告信息
	 * 
	 * @param id --
	 *            不符合报告主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询不符合报告主表
			ReportError reportError = reportErrorDao.get(id);
			// 查询检验员纠正见证照片
			List<Attachment> att_list = attachmentManager.getBusAttachment(id);
			// 查询不符合报告明细表
			Collection<ReportErrorInfo> list = reportErrorInfoDao.getInfos(id);
			map.put("data", reportError);
			map.put("reportErrorInfo", list);
			map.put("attachs", att_list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	// 获取不符合报告编号后三位序号
	public String queryNextSn(String sn_pre){
		return reportErrorDao.queryNextSn(sn_pre);
	}

	// 根据业务信息ID查询业务详细信息（报告信息）
	public List<ReportErrorDTO> queryReportInfos(String ids) throws Exception{
		return reportErrorDao.queryReportInfos(ids);
	}
	
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			// 1、查询不符合报告明细表
			List<ReportErrorInfo> list = reportErrorInfoDao.getInfos(id);
			if (!list.isEmpty()) {
				for (ReportErrorInfo reportErrorInfo : list) {
					// 2、删除不符合报告明细表
					reportErrorInfoDao
							.createSQLQuery(
									"update TZSB_REPORT_ERROR_INFO2 set data_status='99' where id = ? ",
									reportErrorInfo.getId()).executeUpdate();
				}
			}
			// 3、删除报不符合报告主表
			reportErrorDao
					.createSQLQuery(
							"update TZSB_REPORT_ERROR set data_status='99' where id = ? ",
							id).executeUpdate();
			// 4、写入日志
			logService.setLogs(id, "删除不符合报告主表", "删除不符合报告主表",
					user.getId(), user.getName(), new Date(), request
							.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 获取不符合报告流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();		
		List<SysLog> list = reportErrorDao.createQuery("  from SysLog where business_id ='"+id+"' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", reportErrorDao.get(id).getSn());
		map.put("success", true);
		return map;
    }
	
	// 不符合报告3天到期提醒
	public void reportErrorSendMsg() {
		try {
			List<ReportError> reportErrorList = reportErrorDao.getInfos();
			for (ReportError reportError : reportErrorList) {
				
				Date cur_date = new Date(); // 当前日期
				Calendar cal = Calendar.getInstance();
				cal.setTime(cur_date);
				int day1 = cal.get(Calendar.DAY_OF_YEAR);
				//long time1 = cal.getTimeInMillis();

				Date solve_end_date = reportError.getSolve_end_date(); // 整改期限
				cal.setTime(solve_end_date);
				int day2 = cal.get(Calendar.DAY_OF_YEAR);
				//long time2 = cal.getTimeInMillis();

				int between_days = day2 - day1;
				//long between_days = (time2 - time1) / (1000 * 3600 * 24);
				if (between_days == 3) {
					String[] userId = reportError.getError_user_id().split(",");
					for(String userIds:userId){  //循环人员ID
						//获取电话
						//Employee employee = employeesService.get(userIds);
						String user = reportErrorDao.gettol(userIds).toString();
						String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
								.substring(1,user.toString().length()-1)).replaceAll("'$1'");
						
						// 获取发送内容
						String content =  Constant.getReportErrorNoticeContent(
								reportError.getSn(), DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));

						// 发送微信
						messageService.sendWxMsg(null, reportError.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, destNumber);	
						// 发送短信
						messageService.sendMoMsg(null, reportError.getId(), content, destNumber);	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
