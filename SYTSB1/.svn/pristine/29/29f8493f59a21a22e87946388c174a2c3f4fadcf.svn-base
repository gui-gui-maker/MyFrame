package com.lsts.report.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportError;
import com.lsts.report.bean.ReportErrorRecord;
import com.lsts.report.bean.ReportErrorRecordDTO;
import com.lsts.report.bean.ReportErrorRecordInfo;
import com.lsts.report.dao.ReportErrorDao;
import com.lsts.report.dao.ReportErrorRecordDao;
import com.lsts.report.dao.ReportErrorRecordInfoDao;
import com.scts.car.dao.CarApplyDao;

import util.TS_Util;

/**
 * 检验报告/证书不符合纠正流转业务逻辑对象
 * @ClassName ReportErrorRecordService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-23 14:05:00
 */
@Service("reportErrorRecordService")
public class ReportErrorRecordService extends EntityManageImpl<ReportErrorRecord, ReportErrorRecordDao> {
	@Autowired
	private ReportErrorRecordDao reportErrorRecordDao;
	@Autowired
	private ReportErrorRecordInfoDao reportErrorRecordInfoDao;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private SysLogService logService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private ReportErrorDao reportErrorDao;
	@Autowired
	private CarApplyDao carApplyDao;
	
	/**
	 * 
	 * 保存纠正流转表
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveBasic(ReportErrorRecord reportErrorRecord,
			HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User)user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		String report_sns = "";
		String new_report_sns = "";
		try {
			// 1、保存报告不符合纠正明细数据
			for (ReportErrorRecordInfo info : reportErrorRecord.getReportErrorRecordInfo()) {
				if (StringUtil.isNotEmpty(info.getReport_sn())) {
					if (StringUtil.isNotEmpty(info.getReport_sn().trim())) {
						if (report_sns.length()>0) {
							report_sns += ",";
						}
						report_sns += info.getReport_sn().trim();
						List<InspectionInfo> list1 = inspectionInfoDao.queryInspectionInfo(info.getReport_sn().trim());
						if(list1.size() == 0){
							//wrapper.put("success", false);
							//wrapper.put("msg", "报告书编号（"+info.getReport_sn()+"）不存在，请核查！");
							//return wrapper;
						}else{
							if (list1.size()!=1) {
								wrapper.put("success", false);
								wrapper.put("msg", "报告书编号（"+info.getReport_sn().trim()+"）存在重号的报告，请联系系统管理员！");
								return wrapper;
							}else{
								info.setInfo_id(list1.get(0).getId());
								info.setReport_id(list1.get(0).getReport_type());
							}
						}
					}
				}
				
				if (StringUtil.isNotEmpty(info.getNew_report_sn())) {
					if (StringUtil.isNotEmpty(info.getNew_report_sn().trim())) {
						if (new_report_sns.length()>0) {
							new_report_sns += ",";
						}
						new_report_sns += info.getNew_report_sn().trim();
						List<InspectionInfo> list2 = inspectionInfoDao.queryInspectionInfo(info.getNew_report_sn().trim());
						if(list2.size() == 0){
							String report_status = info.getReport_status();	// 1：已封存 2：已打印
							if("1".equals(report_status)){	// 已封存的验证，已打印的不验证（已封存新报告书编号必填，已打印的可不填写新报告编号）
								//wrapper.put("success", false);
								//wrapper.put("msg", "新报告书编号（"+info.getNew_report_sn()+"）不存在，请核查！");
								//return wrapper;
							}
						}else{
							if (list2.size()!=1) {
								wrapper.put("success", false);
								wrapper.put("msg", "新报告书编号（"+info.getNew_report_sn()+"）存在重号的报告，请联系系统管理员！");
								return wrapper;
							}else{
								info.setNew_info_id(list2.get(0).getId());
								info.setNew_report_id(list2.get(0).getReport_type());
							}
						}
					}
				}
				
				info.setMdy_user_id(emp.getId());
				info.setMdy_user_name(emp.getName());
				info.setMdy_date(new Date());
				info.setData_status("0");	// 数据状态（0：正常）
				info.setReportErrorRecord(reportErrorRecord);
			}
			
			// 2、保存不符合纠正流转主表		
			if (reportErrorRecord.getError_from().equals("2")) {	// 2：部门自查
				String error_dep_id = reportErrorRecord.getError_dep_id();
				String error_dep_name = reportErrorRecord.getError_dep_name();
				if(StringUtil.isEmpty(error_dep_id)){
					reportErrorRecord.setError_dep_id(user.getDepartment().getId());
				}
				if(StringUtil.isEmpty(error_dep_name)){
					reportErrorRecord.setError_dep_name(user.getDepartment().getOrgName());
				}
			}
			
			// 3、生成不符合纠正编号
			// 编号规则：1、质量部 2015-001 2、检验部门 JD1-2015-001
			String pre_sn = "";
			String org_code = user.getDepartment().getOrgCode();
			if(org_code.contains("jd") || org_code.contains("cy")){
				pre_sn += org_code.toUpperCase() + "-";				
			}
			pre_sn += TS_Util.getCurYear() + "-";
			if (StringUtil.isEmpty(reportErrorRecord.getId())) {
				reportErrorRecord.setSn(pre_sn+queryNextSn(pre_sn));	// 纠正流转表编号
			}else{
				if(StringUtil.isEmpty(reportErrorRecord.getSn())){
					reportErrorRecord.setSn(pre_sn+queryNextSn(pre_sn));	// 纠正流转表编号
				}
			}
			
			if(StringUtil.isNotEmpty(reportErrorRecord.getFind_user_id())){
				Employee employee = employeeManager.get(reportErrorRecord.getFind_user_id());
				if (employee != null) {
					reportErrorRecord.setFind_user_name(employee.getName());
				}
			}
			
			if(StringUtil.isEmpty(reportErrorRecord.getError_user_id()) || StringUtil.isEmpty(reportErrorRecord.getError_user_name())){
				reportErrorRecord.setError_user_id(emp.getId());	// 需要关联责任人录入的报告信息，故此处取Employee ID
				reportErrorRecord.setError_user_name(emp.getName());
			}
			reportErrorRecord.setReport_sn(report_sns);	// 报告书编号（多个以逗号分隔）
			reportErrorRecord.setNew_report_sn(new_report_sns);	// 新报告书编号（多个以逗号分隔）
			reportErrorRecord.setData_status("01"); // 数据状态（01：检验员已提交纠正流转表，等待业务服务部确认）
			reportErrorRecord.setDeal_user_id(emp.getId());		// 检验员ID
			reportErrorRecord.setDeal_user_name(emp.getName());	// 检验员姓名
			reportErrorRecord.setDeal_date(new Date());		// 处理日期
			reportErrorRecord.setIs_borrow_report("0");		// 是否借报告（数据字典IS_BORROW_REPORT 0：否 1：是）	
			reportErrorRecordDao.save(reportErrorRecord);
			
			logService.setLogs(reportErrorRecord.getId(), "提交不符合纠正", "检验员提交检验报告/证书不符合纠正流转表", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());
			
			wrapper.put("success", true);
			wrapper.put("obj", reportErrorRecord);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return wrapper;
	}
	
	/**
	 * 
	 * 保存纠正流转表（需要返写不符合报告主表）
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveBasic2(ReportErrorRecord reportErrorRecord,
			HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User) user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
		String report_sns = "";
		String new_report_sns = "";
		try {
			String report_error_id = request.getParameter("report_error_id");
			// 1、保存报告不符合纠正明细数据
			for (ReportErrorRecordInfo info : reportErrorRecord.getReportErrorRecordInfo()) {
				if (StringUtil.isNotEmpty(info.getReport_sn())) {
					if (StringUtil.isNotEmpty(info.getReport_sn().trim())) {
						List<ReportErrorRecordInfo> recordInfos = reportErrorRecordInfoDao
								.queryByReportSn(info.getInfo_id(), info.getReport_sn().trim(), reportErrorRecord.getId());
						if (!recordInfos.isEmpty()) {
							wrapper.put("success", false);
							wrapper.put("msg", "系统检测到报告编号（" + info.getReport_sn().trim() + "）已提交过纠正申请，不能重复提交，请检查！");
							return wrapper;
						} else {
							if (report_sns.length() > 0) {
								report_sns += ",";
							}
							report_sns += info.getReport_sn().trim();
							List<InspectionInfo> list1 = inspectionInfoDao.queryInspectionInfo(info.getReport_sn().trim());
							if (list1.size() == 0) {
								// wrapper.put("success", false);
								// wrapper.put("msg",
								// "系统未能识别报告编号（"+info.getReport_sn()+"），请检查编号是否填写正确并确认该报告由您本人录入！");
								// return wrapper;
							} else {
								if (list1.size() != 1) {
									wrapper.put("success", false);
									wrapper.put("msg", "报告编号（" + info.getReport_sn() + "）重号，请联系系统管理员！");
									return wrapper;
								} else {
									info.setInfo_id(list1.get(0).getId());
									info.setReport_id(list1.get(0).getReport_type());
									
									for(InspectionInfo inspectionInfo : list1){
										inspectionInfo.setIs_cur_error("1");
										inspectionInfoDao.save(inspectionInfo);
										
										logService.setLogs(inspectionInfo.getId(), "报告纠错",
												"报告正在纠错中", user.getId(), user.getName(),
												new Date(), request.getRemoteAddr());
									}
								}
							}
						}
					}
				}

				if (StringUtil.isNotEmpty(info.getNew_report_sn())) {
					if (StringUtil.isNotEmpty(info.getNew_report_sn().trim())) {
						List<ReportErrorRecordInfo> recordInfos = reportErrorRecordInfoDao
								.queryByNewReportSn(info.getNew_info_id(), info.getNew_report_sn().trim());
						if (!recordInfos.isEmpty()) {
							wrapper.put("success", false);
							wrapper.put("msg", "系统检测到新报告编号（" + info.getNew_report_sn().trim() + "）已提交过纠正申请，不能重复提交，请检查！");
							return wrapper;
						} else {
							if (new_report_sns.length() > 0) {
								new_report_sns += ",";
							}
							new_report_sns += info.getNew_report_sn().trim();
							List<InspectionInfo> list2 = inspectionInfoDao.queryInspectionInfo(info.getNew_report_sn().trim());
							if (list2.size() == 0) {
								/*
								 * String report_status = info.getReport_status();
								 * // 1：已封存 2：已打印 if("1".equals(report_status)){ //
								 * 已封存的验证，已打印的不验证（已封存新报告书编号必填，已打印的可不填写新报告编号）
								 * wrapper.put("success", false); wrapper.put("msg",
								 * "系统未能识别新报告编号（"+info.getNew_report_sn()+
								 * "），请检查编号是否填写正确并确认该报告由您本人录入！"); return wrapper; }
								 */
								wrapper.put("success", false);
								wrapper.put("msg", "系统未能识别新报告编号（" + info.getNew_report_sn().trim() + "），请检查编号是否填写正确！");
								return wrapper;
							} else {
								if (list2.size() != 1) {
									wrapper.put("success", false);
									wrapper.put("msg", "新报告书编号（" + info.getNew_report_sn().trim() + "）重号，请联系系统管理员！");
									return wrapper;
								} else {
									info.setNew_info_id(list2.get(0).getId());
									info.setNew_report_id(list2.get(0).getReport_type());
								}
							}
						}
					}
				}
				info.setMdy_user_id(emp.getId());
				info.setMdy_user_name(emp.getName());
				info.setMdy_date(new Date());
				info.setData_status("0"); // 数据状态（0：正常）
				info.setReportErrorRecord(reportErrorRecord);
			}

			// 2、保存不符合纠正流转主表
			if (reportErrorRecord.getError_from().equals("2")) { // 2：部门自查
				String error_dep_id = reportErrorRecord.getError_dep_id();
				String error_dep_name = reportErrorRecord.getError_dep_name();
				if (StringUtil.isEmpty(error_dep_id)) {
					reportErrorRecord.setError_dep_id(user.getDepartment().getId());
				}
				if (StringUtil.isEmpty(error_dep_name)) {
					reportErrorRecord.setError_dep_name(user.getDepartment().getOrgName());
				}
			}

			// 3、生成不符合纠正编号
			// 编号规则：1、质量部 2015-001 2、检验部门 JD1-2015-001
			String pre_sn = "";
			String org_code = user.getDepartment().getOrgCode();
			if (org_code.contains("jd") || org_code.contains("cy")) {
				pre_sn += org_code.toUpperCase() + "-";
			}
			pre_sn += TS_Util.getCurYear() + "-";
			if (StringUtil.isEmpty(reportErrorRecord.getId())) {
				reportErrorRecord.setSn(pre_sn + queryNextSn(pre_sn)); // 纠正流转表编号
			} else {
				if (StringUtil.isEmpty(reportErrorRecord.getSn())) {
					reportErrorRecord.setSn(pre_sn + queryNextSn(pre_sn)); // 纠正流转表编号
				}
			}

			if (StringUtil.isNotEmpty(reportErrorRecord.getFind_user_id())) {
				Employee employee = employeeManager.get(reportErrorRecord.getFind_user_id());
				if (employee != null) {
					reportErrorRecord.setFind_user_name(employee.getName());
				}
			}

			if (StringUtil.isEmpty(reportErrorRecord.getError_user_id())
					|| StringUtil.isEmpty(reportErrorRecord.getError_user_name())) {
				// 需要关联责任人录入的报告信息，故此处取EmployeeID
				reportErrorRecord.setError_user_id(emp.getId()); 
				reportErrorRecord.setError_user_name(emp.getName());
			}
			reportErrorRecord.setReport_sn(report_sns); 		// 报告书编号（多个以逗号分隔）
			reportErrorRecord.setNew_report_sn(new_report_sns); // 新报告书编号（多个以逗号分隔）
			reportErrorRecord.setData_status("01"); 			// 数据状态（01：检验员已提交纠正流转表，等待业务服务部确认）
			reportErrorRecord.setDeal_user_id(emp.getId()); 	// 检验员ID
			reportErrorRecord.setDeal_user_name(emp.getName()); // 检验员姓名
			reportErrorRecord.setDeal_date(new Date()); // 处理日期
			reportErrorRecord.setIs_borrow_report("0"); // 是否借报告（数据字典IS_BORROW_REPORT
														// 0：否 1：是）
			reportErrorRecord.setFk_report_error_id(report_error_id); // 纠正流转表与不符合报告关联
			reportErrorRecordDao.save(reportErrorRecord);

			logService.setLogs(reportErrorRecord.getId(), "提交不符合纠正", "检验员提交检验报告/证书不符合纠正流转表", user.getId(),
					user.getName(), new Date(), request.getRemoteAddr());

			if (StringUtil.isNotEmpty(report_error_id)) {
				// 返写质量部不符合报告
				ReportError reportError = reportErrorDao.get(report_error_id);
				reportError.setDeal_remark("不符合纠正编号：" + reportErrorRecord.getSn()); // 将“不符合纠正”编号返写到“不符合报告”“处理结果中”
				reportError.setData_status("04"); // 04：检验员已纠正，质量部待确认2016-09-02修改
				reportError.setDeal_user_id(emp.getId());
				reportError.setDeal_user_name(emp.getName());
				reportError.setDeal_date(new Date());
				reportErrorDao.save(reportError);
				
				// 记录日志
				logService.setLogs(reportError.getId(), "不符合报告纠正处理",
						"检验员提交检验报告/证书不符合纠正流转表（不符合纠正编号：" + reportErrorRecord.getSn() + "）", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
				
				// 发送消息提醒部门负责人确认
				try {
					sendMsg(request, reportError);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
				}
			}
			wrapper.put("success", true);
			wrapper.put("obj", reportErrorRecord);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return wrapper;
	}
	
	/**
	 * 消息提醒推送
	 * 
	 * @param request
	 *            -- 请求对象
	 * @param reportError
	 *            -- 不符合报告对象           
	 * @return
	 * @author GaoYa
	 * @date 2018-07-24 上午11:06:00
	 */
	@SuppressWarnings("unchecked")
	private void sendMsg(HttpServletRequest request, ReportError reportError) {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("errorOp", reportError.getDeal_user_name());
		map1.put("errorSn", reportError.getSn());
		/*String wx_check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3"
				+ "&redirect_uri=http://kh.scsei.org.cn/car/apply/weixinUserInfo.do?businessId=" + carApply.getId()
				+ "&businessStatus=" + carApply.getData_status() + "&response_type=code&scope=snsapi_base&state=STATE";
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("url", wx_check_url);*/
		try {
			String msg_code = "reportError_confirm";
			// 获取发送内容
			String content = Constant.getReportErrorWxTips(reportError.getDeal_user_name(), reportError.getSn());
			String mobile = "";

			List<Map<String, Object>> leaders = carApplyDao.queryDepLeaders();
			for (Iterator iterator = leaders.iterator(); iterator.hasNext();) {
				Map<String, Object> dataMap = (Map<String, Object>) iterator.next();
				String org_id = dataMap.get("org_id").toString();
				if (org_id.equals(reportError.getError_dep_id())) {
					mobile = dataMap.get("mobile_tel").toString();
					break;
				}
			}
			if (StringUtil.isEmpty(mobile)) {
				String[] emp_ids = null;
				if ("100025".equals(reportError.getError_dep_id())) {
					// 财务部
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_CW_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_CW_EMP_ID.split(",");
					}
				} else if ("100030".equals(reportError.getError_dep_id())) {
					// 科管部
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_KG_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_KG_EMP_ID.split(",");
					}
				}
				for (int j = 0; j < emp_ids.length; j++) {
					Employee employee = employeesService.get(emp_ids[j]);
					// 获取发送目标号码
					if (StringUtil.isEmpty(mobile)) {
						mobile = employee.getMobileTel();
					}
				}
			}
			if(StringUtil.isNotEmpty(mobile)) {
				// 发送即时短信和微信提醒（发送类型以系统配置为准）
				messageService.sendMassageByConfig(request, reportError.getId(), mobile, content, msg_code,
						null, map1, null, null, Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	
	/**
	 * 获取检验报告/证书不符合纠正流转信息
	 * 
	 * @param id --
	 *            检验报告/证书不符合纠正流转主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询检验报告/证书不符合纠正流转主表
			ReportErrorRecord reportErrorRecord = reportErrorRecordDao.get(id);
			// 查询检验报告/证书不符合纠正流转明细表
			Collection<ReportErrorRecordInfo> list = reportErrorRecordInfoDao.getInfos(id);
			map.put("data", reportErrorRecord);
			map.put("reportErrorRecordInfo", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	// 获取不符合纠正流转表编号后三位序号
	public String queryNextSn(String sn_pre){
		return reportErrorRecordDao.queryNextSn(sn_pre);
	}

	// 根据业务信息ID查询业务详细信息（报告信息）
	public List<ReportErrorRecordDTO> queryReportInfos(String ids) throws Exception{
		return reportErrorRecordDao.queryReportInfos(ids);
	}

	/**
	 * 根据业务ID查询报告不符合纠正流转情况
	 * 
	 * @param id -- 检验业务id
	 * @return
	 * @author GaoYa
	 * @date 2015-11-19 上午11:13:00
	 */
	public List<ReportErrorRecordDTO> queryReportErrors(String id) throws Exception{
		return reportErrorRecordDao.queryReportErrors(id);
	}
	
	// 根据不符合报告ID查询不符合纠正流转表
	public HashMap<String, Object> queryRecordID(String report_error_id) throws Exception{
		return reportErrorRecordDao.queryRecordID(report_error_id);
	}
	
	// 外借报告
	public HashMap<String, Object> borrow(HttpServletRequest request, String borrow_user_id, String borrow_desc)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try{
				String temp[] = ids.split(",");
				for(int i=0;i<temp.length;i++){
					ReportErrorRecord reportErrorRecord = reportErrorRecordDao.get(temp[i]);
					reportErrorRecord.setIs_borrow_report("1");	// 是否借报告（数据字典ba_sf 0：否 1：是）		
					reportErrorRecord.setBorrow_op_id(user.getId());		// 外借经办人ID
					reportErrorRecord.setBorrow_op_name(user.getName());	// 外借经办人姓名
					
					if(StringUtil.isNotEmpty(reportErrorRecord.getBorrow_user_id())){	// 外借人ID
						Employee employee = employeeManager.get(reportErrorRecord.getBorrow_user_id());	
						if (employee != null) {
							reportErrorRecord.setBorrow_user_name(employee.getName());	// 外借人姓名
						}
					}else{
						Employee employee = employeeManager.get(borrow_user_id);	
						if (employee != null) {
							reportErrorRecord.setBorrow_user_name(employee.getName());	// 外借人姓名
						}
						reportErrorRecord.setBorrow_user_id(borrow_user_id);
					}
					reportErrorRecord.setBorrow_date(new Date());	// 外借日期
					reportErrorRecord.setBorrow_desc(borrow_desc);
					reportErrorRecordDao.save(reportErrorRecord);
					
					logService.setLogs(reportErrorRecord.getId(), "外借报告", "检验报告/证书不符合纠正，外借报告", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
				}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			// 1、查询检验报告/证书不符合纠正流转明细表
			List<ReportErrorRecordInfo> list = reportErrorRecordInfoDao.getInfos(id);
			if (!list.isEmpty()) {
				for (ReportErrorRecordInfo reportErrorRecordInfo : list) {
					// 2、删除检验报告/证书不符合纠正流转明细表（TZSB_REPORT_PRINT_RECORD）
					reportErrorRecordInfoDao
							.createSQLQuery("update TZSB_REPORT_ERROR_INFO set data_status='99' where id = ? ",
									reportErrorRecordInfo.getId())
							.executeUpdate();
					if (StringUtil.isNotEmpty(reportErrorRecordInfo.getInfo_id())) {
						inspectionInfoDao
								.createSQLQuery("update tzsb_inspection_info set is_cur_error='0' where id = ? ",
										reportErrorRecordInfo.getInfo_id())
								.executeUpdate();
					}
				}
			}
			// 3、删除报送打印主表（TZSB_REPORT_PRINT）
			reportErrorRecordDao
					.createSQLQuery("update TZSB_REPORT_ERROR_RECORD set data_status='04' where id = ? ", id)
					.executeUpdate();
			// 4、写入日志
			logService.setLogs(id, "删除不符合纠正流转表", "删除检验报告/证书不符合纠正流转表", user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 获取不符合纠正流转表流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();		
		List<SysLog> list = reportErrorRecordDao.createQuery("  from SysLog where business_id ='"+id+"' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", reportErrorRecordDao.get(id).getSn());
		map.put("success", true);
		return map;
    }
	
	// 根据报告书编号查询报告不符合纠正记录
	public List<ReportErrorRecord> getInfos(String report_sn) {
		return reportErrorRecordDao.getInfos(report_sn);
	}
}
