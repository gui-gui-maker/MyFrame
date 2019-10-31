/**
 * 
 */
package com.lsts.common.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.config.bean.RtPersonDirVersion;
import com.khnt.rtbox.config.dao.RtPageDao;
import com.khnt.rtbox.config.service.RtPersonDirManager;
import com.khnt.rtbox.config.service.RtPersonDirVersionManager;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateBy;
import com.lsts.approve.bean.CertificateRule;
import com.lsts.approve.service.CertificateByService;
import com.lsts.approve.service.CertificateRuleService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.dao.DeviceDao;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.SysAutoIssueLog;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.inspection.dao.ReportItemValueDao;
import com.lsts.inspection.service.SysAutoIssueLogService;
import com.lsts.log.bean.SysDataMdyLog;
import com.lsts.log.dao.SysDataMdyLogDao;
import com.lsts.report.bean.Report;
import com.lsts.report.service.ReportService;

/**
 * 报告通用业务控制器
 * 
 * @ClassName InspectionCommonService
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-04-14 上午10:35:00
 */
@Service("inspectionCommonService")
public class InspectionCommonService {

	@Autowired
	private ReportService reportService;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private ReportItemValueDao reportItemValueDao;
	@Autowired
	private SysDataMdyLogDao sysDataMdyLogDao;
	@Autowired
	private CertificateByService certificateByService;
	@Autowired
	private CertificateRuleService certificateRuleService;
	@Autowired
	private SysAutoIssueLogService sysAutoIssueLogService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private EmployeeBaseDao employeeBaseDao;
	@Autowired
	private RtPersonDirManager rtPersonDirManager;
	@Autowired
	private RtPageDao rtPageDao;
	@Autowired
	private RtPersonDirVersionManager rtPersonDirVersionManager;
	
	/**
	 * 电脑报告生成和平板报告生成保存，主要是为了同步，避免生成报告编号的时候由于同步操作而重复
	 * @param flag
	 *            -- 调用分支标识
	 * @param info_id
	 *            -- 报告业务ID
	 * @param report_type
	 *            -- 报告模板类型ID
	 * @param check_type
	 *            -- 检验类别
	 * @param device_type
	 *            -- 设备类别
	 * @return
	 * @author GaoYa
	 * @date 2017-04-25 上午11:12:00
	 */
	public synchronized Map<String, Object> createReportSn(String flag, String info_id, String report_type,
			String check_type, String device_type) throws KhntException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (flag.equals("moCreateReport") || flag.equals("saveReport") || flag.equals("saveRecord") || flag.equals("saveZZJDRecord")) { 
				// moCreateReport：原始记录生成报告时，生成报告编号
				// saveReport：保存报告时，生成报告编号
				// saveRecord：保存原始记录时，生成报告编号
				String report_sn = this.synReportSN("0", info_id, device_type, check_type, report_type);
				map.put("report_sn", report_sn);
			} else if (flag.equals("createReportSn")) {
				// 保存报告前，生成报告编号
				this.synReportSN("1", info_id, "", "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 生成报告证书编号
	 * @param flad -- 
	 *            0：系统自动生成 1：用户手动点击按钮生成
	 * @param infoId -- 
	 *            业务ids
	 * @param device_type --
	 *            设备类别
	 * @param check_type --
	 *            检验类型
	 * @param report_id --
	 *            报告ID
	 * @return
	 * @author GaoYa
	 * @date 2015-12-06 上午10:00:00
	 */
	public synchronized String synReportSN(String flag, String infoId, String device_type,
			String check_type, String report_id) throws Exception {
		String report_sn = "";
		if ("0".equals(flag)) {
			String org_id = "";
			String temp[] = infoId.split(",");
			for (int i = 0; i < temp.length; i++) {
				InspectionInfo info = inspectionInfoDao.get(temp[i]);
				if (StringUtil.isEmpty(org_id)) {
					org_id = info.getCheck_unit_id();
					break;
				}
			}
			report_sn = reportService.generateReportCode(device_type, check_type, report_id, org_id);
		} else {
			flow_addNum(infoId);
		}
		return report_sn;
	}
	
	public synchronized void flow_addNum(String infoId) throws Exception {
		String temp[] = infoId.split(",");
		for (int i = 0; i < temp.length; i++) {
			InspectionInfo info = inspectionInfoDao.get(temp[i]);
			String check_type = info.getInspection().getCheck_type();
			String report_type = info.getReport_type();
			DeviceDocument device = deviceDao.get(info.getFk_tsjc_device_document_id());
			synchronized (this){
				String report_sn = reportService.generateReportCode(device.getDevice_sort_code(), check_type, report_type,
						info.getCheck_unit_id());
				inspectionInfoDao.createSQLQuery(
						"update  TZSB_INSPECTION_INFO  set report_sn='" + report_sn + "' where id ='" + temp[i] + "'")
						.executeUpdate();
				reportItemValueDao.createSQLQuery(
						"insert into  TZSB_REPORT_ITEM_VALUE(id,fk_report_id,item_name,item_value,fk_inspection_info_id)  values('"
								+ StringUtil.getUUID() + "','" + report_type + "','REPORT_SN','" + report_sn + "','"
								+ temp[i] + "')")
						.executeUpdate();
			}
		}
	}
	
	/**
	 * 对象属性值对比方法
	 * @param infoMap -- 对比对象基础信息（包含表名、业务ID、操作信息）
	 * @param oldMap -- 原数据集	
	 * @param newMap -- 新数据集
	 * @param request -- 请求对象
	 * @return 无
	 * @author GaoYa
	 * @date 2017-04-29 下午17:02:00
	 */
	public void compareMapOfBean(Map<String, String> infoMap, Map<String, String> oldMap, Map<String, String> newMap, HttpServletRequest request){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			Iterator it = newMap.keySet().iterator(); 
			while(it.hasNext()){ 
			    String key; 
			    String new_value; 
			    String old_value;
			    key = it.next().toString(); 
			    new_value = newMap.get(key); 
			    old_value = oldMap.get(key);
			    if(new_value == null && old_value == null){
			    	continue;
			    }
			    if(new_value == null && old_value == ""){
			    	continue;
			    }
			    if(new_value == "" && old_value == null){
			    	continue;
			    }
			    if (!new_value.equals(old_value)) {
			    	SysDataMdyLog sysDataMdyLog = new SysDataMdyLog();
			    	sysDataMdyLog.setBusiness_id(infoMap.get("business_id"));
			    	sysDataMdyLog.setTable_code(infoMap.get("table_code"));
			    	sysDataMdyLog.setTable_name(infoMap.get("table_name"));
			    	sysDataMdyLog.setColumn_name(key);
			    	sysDataMdyLog.setOld_value(old_value);
			    	sysDataMdyLog.setNew_value(new_value);
			    	sysDataMdyLog.setOp_action(infoMap.get("op_action"));
			    	sysDataMdyLog.setOp_remark(infoMap.get("op_remark"));
			    	sysDataMdyLog.setOp_user_id(user.getId());
			    	sysDataMdyLog.setOp_user_name(user.getName());
			    	sysDataMdyLog.setOp_time(new Date());
			    	sysDataMdyLog.setOp_ip(request.getRemoteAddr());
			    	sysDataMdyLogDao.save(sysDataMdyLog);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象属性值对比方法
	 * @param infoMap -- 对比对象基础信息（包含表名、业务ID、操作信息）
	 * @param oldMap -- 源数据集	
	 * @param newMap -- 新数据集
	 * @param request -- 请求对象
	 * @return 对比差异集合
	 * @author GaoYa
	 * @date 2017-04-29 下午17:02:00
	 */
	public Map<String, String> compareMap(Map<String, String> infoMap, Map<String, String> oldMap, Map<String, String> newMap, HttpServletRequest request){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		Map<String, String> diff_Map = new HashMap<String, String>();
		try {
			Iterator it = newMap.keySet().iterator(); 
			while(it.hasNext()){ 
			    String key; 
			    String new_value; 
			    String old_value;
			    key = it.next().toString(); 
			    new_value = newMap.get(key); 
			    old_value = oldMap.get(key);
			    if(new_value == null && old_value == null){
			    	continue;
			    }
			    if(new_value == null && old_value == ""){
			    	continue;
			    }
			    if(new_value == "" && old_value == null){
			    	continue;
			    }
			    if (!new_value.equals(old_value)) {
			    	SysDataMdyLog sysDataMdyLog = new SysDataMdyLog();
			    	sysDataMdyLog.setBusiness_id(infoMap.get("business_id"));
			    	sysDataMdyLog.setTable_code(infoMap.get("table_code"));
			    	sysDataMdyLog.setTable_name(infoMap.get("table_name"));
			    	sysDataMdyLog.setColumn_name(key);
			    	sysDataMdyLog.setOld_value(old_value);
			    	sysDataMdyLog.setNew_value(new_value);
			    	sysDataMdyLog.setOp_action(infoMap.get("op_action"));
			    	sysDataMdyLog.setOp_remark(infoMap.get("op_remark"));
			    	sysDataMdyLog.setOp_user_id(user.getId());
			    	sysDataMdyLog.setOp_user_name(user.getName());
			    	sysDataMdyLog.setOp_time(new Date());
			    	sysDataMdyLog.setOp_ip(request.getRemoteAddr());
			    	sysDataMdyLogDao.save(sysDataMdyLog);
			    	
			    	diff_Map.put(key.toUpperCase(), new_value);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff_Map;
	}
	
	/**
	 * 自动分配签发人
	 * 
	 * 1、是否允许签发人参与其他流程（重复签字）：是，否（默认）
	 * 2、单人分配（无任何特殊情况下的单人分配）
	 * 3、是否可签本部门报告：是，否（默认）
	 * 4、请假与候补分配
	 * 5、是否匹配相同单位：是（默认），否 
	 * 6、优先分配规则：量少优先分配（默认），随机分配    
	 * 
	 * @param inspectionInfo -- 报告业务信息对象
	 * @param device_type -- 设备类别
	 * 
	 * @return 分配结果集
	 * @author GaoYa
	 * @date 2017-07-02 上午09:30:00
	 */
	public Map<String, Object> autoIssue(InspectionInfo inspectionInfo, String device_type) {
		Map<String, Object> returnMaps = new HashMap<String, Object>();
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			// 1、根据设备类别获取分配规则
			Report report = reportService.get(inspectionInfo.getReport_type());
			List<CertificateRule> rule_list = certificateRuleService.queryRuleCode(device_type, inspectionInfo.getCheck_unit_id(),
					report.getIs_issue(), report.getReport_name().trim(), inspectionInfo.getReport_type());
			if (rule_list.isEmpty()) {
				returnMaps.put("success", false);
				returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
			} else {
				if(rule_list.size()!=1){
					returnMaps.put("success", false);
					returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
				}else{
					CertificateRule rule = rule_list.get(0);
					if(rule == null){
						returnMaps.put("success", false);
						returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
					}else{
						// 是否允许审核、签发可为同一人（1：是 0：否）
						String is_same_person = rule.getIs_same_person();				
						// 是否可签本部门报告（1：是 0：否）
						String is_allow_selfdep = rule.getIs_allow_self();
						// 是否匹配相同单位（1：是 0：否）
						String is_same_unit = rule.getIs_same_unit();
						// 优先分配规则（1：量少优先分配 2：随机分配 3：指定分配）
						String first_rule = rule.getCertificate_rule();
						
						String enter_user_id = "";		// 录入人ID
						String examine_user_id = "";	// 审核人ID
						if(StringUtil.isNotEmpty(is_same_person)){
							// 是否允许编制、审核、签发可为同一人（1：是 0：否）
							if("0".equals(is_same_person)){
								enter_user_id = employeesService.queryUserIdByEmpId(inspectionInfo.getEnter_op_id());
								examine_user_id = user.getId();
							}
						}
						//获取本部门录入人员、审核人员的角色
						List<String> enterUserRole = employeeBaseDao.getEmployeeRole(enter_user_id,inspectionInfo.getEnter_unit_id());
						List<String> examineUserRole = employeeBaseDao.getEmployeeRole(examine_user_id,inspectionInfo.getEnter_unit_id());
						// 2、根据报告类型、检验部门查询可签发人员信息（启用并且在岗状态）
						List<CertificateBy> list = new ArrayList<CertificateBy>();
						//判断报告是否是氧舱年度检验报告/证书和固定式压力容器年度检验报告/证书
						if("氧舱年度检查报告".equals(report.getReport_name().replaceAll(" ", "")) || "固定式压力容器年度检查报告".equals(report.getReport_name().replaceAll(" ", ""))) {
							String is_ministerofaduit_person = null;
							if(enterUserRole.contains("部门负责人")) {
								is_ministerofaduit_person = "1";//设置条件为可审核部长的签发人
							}else {
								if(null != examineUserRole && examineUserRole.size() !=0) {
									if(examineUserRole.contains("部门负责人")) {
										is_ministerofaduit_person = "1";//设置条件为可审核部长的签发人
									}else {
										is_ministerofaduit_person = "0";//设置条件为不可审核部长的签发人
									}
								}else {
									is_ministerofaduit_person = "0";//设置条件为不可审核部长的签发人
								}
							}
							list = certificateByService.queryByReport(inspectionInfo.getReport_type(),
									inspectionInfo.getCheck_unit_id(), is_allow_selfdep, is_ministerofaduit_person);
						}else {
							list = certificateByService.queryByReport(inspectionInfo.getReport_type(),
									inspectionInfo.getCheck_unit_id(), is_allow_selfdep, null);
						}
						if (list.isEmpty()) {
							// 3、根据设备类别、检验部门查询可签发人员信息（启用并且在岗状态）
							// 3.1、根据设备类别是否为压力管道、报告出具人是否为部长查询可签发人员（启用并且在岗状态）
							String is_ministerofaduit_person = null;
							if("8".equals(device_type)) {
								if(enterUserRole.contains("部门负责人")) {
									is_ministerofaduit_person = "1";//设置条件为可审核部长的签发人
								}else {
									if(null != examineUserRole && examineUserRole.size() !=0) {
										if(examineUserRole.contains("部门负责人")) {
											is_ministerofaduit_person = "1";//设置条件为可审核部长的签发人
										}else {
											is_ministerofaduit_person = "0";//设置条件为不可审核部长的签发人
										}
									}else {
										is_ministerofaduit_person = "0";//设置条件为不可审核部长的签发人
									}
								}
							}
							list = certificateByService.queryByDeviceDep(device_type, inspectionInfo.getCheck_unit_id(),
									is_allow_selfdep,is_ministerofaduit_person);
						}
						if (!list.isEmpty()) {
							// 4、根据分配规则，进行分配
							// 4.1、可签发人数为1时，并且此人非报告审核人员时，根本不用纠结，直接提交签发即可（单一分配：无任何特殊情况下的单人分配）
							if (list.size() == 1) {
								boolean allow_issue = true;
								CertificateBy cert = list.get(0);
								if (cert != null) {
									if(StringUtil.isEmpty(enter_user_id) && StringUtil.isEmpty(examine_user_id)){
										allow_issue = true;
									}else{
										if(enter_user_id.equals(cert.getUser_id())){
											allow_issue = false;
										}
										if(examine_user_id.equals(cert.getUser_id())){
											allow_issue = false;
										}
									}
									if(allow_issue){
										returnMaps.put("success", true);
										returnMaps.put("next_op_id", cert.getUser_id());
										returnMaps.put("next_op_name", cert.getUser_name());
										returnMaps.put("rule", "4"); // 单人分配
									}else{
										returnMaps.put("success", false);
										returnMaps.put("msg", "当前可签字人员"+cert.getUser_name()+"已参与报告签字不能进行签发，详询质量部028-86607892！");
									}
								}
							} else {
								// 4.2、可签发人数大于1时，根据分配规则进行分配
								if ("1".equals(is_same_unit)) {
									// 4.2.1、相同单位分配
									returnMaps = getLotIssue(list, inspectionInfo, device_type, enter_user_id, examine_user_id);
								}

								if (!Boolean.valueOf(String.valueOf(returnMaps.get("success")))) {
									// 4.2.3、优先分配
									returnMaps = getLightIssue(list, device_type, first_rule, enter_user_id, examine_user_id);
								}
							}
						} else {
							returnMaps.put("success", false);
							returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMaps.put("success", false);
			returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
		}
		return returnMaps;
	}
	
	/**
	 * 相同单位分配
	 * @param inspectionInfo -- 报告业务信息对象
	 * @param device_type -- 设备类别
	 * @param enter_user_id -- 报告编制人ID
	 * @param examine_user_id -- 报告审核人ID
	 * 
	 * @return 分配结果集
	 * @author GaoYa
	 * @date 2017-07-02 上午09:47:00
	 */
	private Map<String, Object> getLotIssue(List<CertificateBy> list, InspectionInfo inspectionInfo, String device_type, String enter_user_id, String examine_user_id){	
		Map<String, Object> returnMaps = new HashMap<String, Object>();		
		try {
			// 相同使用单位优先分配：获取3个月内相同单位报告签发人
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -3);
			Date start_date = calendar.getTime();
			String start_time = DateUtil.getDateTime(Constant.defaultDatePattern, start_date);

			String emp_id = "";
			String emp_name = "";
			
			Map<String, Object> qf_users = new HashMap<String, Object>(); 	// 当前可签字人集合	
			for(CertificateBy cert : list){
				qf_users.put(cert.getUser_id(), cert.getUser_id());
			}
			// 根据单位名称、检验部门、设备类别等获取3个月内相同单位已分配的签发人信息
			List<SysAutoIssueLog> issueLogList = sysAutoIssueLogService.getInfos(
					inspectionInfo.getReport_com_name().trim(), inspectionInfo.getCheck_unit_id(), device_type, start_time,
					DateUtil.getCurrentDateTime());
			boolean allow = true;
			for(SysAutoIssueLog issueLog : issueLogList){
				if(StringUtil.isNotEmpty(examine_user_id) && StringUtil.isNotEmpty(enter_user_id)){
					if(examine_user_id.equals(issueLog.getTo_user_id()) || enter_user_id.equals(issueLog.getTo_user_id())){
						allow = false;
						continue;
					}else{
						allow = true;
					}
				}else{
					allow = true;
				}
				if(allow){
					if(qf_users.containsKey(issueLog.getTo_user_id())){
						emp_id = issueLog.getTo_user_id();
						emp_name = issueLog.getTo_user_name();
						break;
					}
				}
			}
			if(StringUtil.isNotEmpty(emp_id)){
				returnMaps.put("success", true);
				returnMaps.put("next_op_id", emp_id);
				returnMaps.put("next_op_name", emp_name);
				returnMaps.put("rule", "1");	// 相同单位分配
			}else{
				returnMaps.put("success", false);
				if(allow){
					returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
				}else{
					returnMaps.put("msg", "当前可签字人员"+emp_name+"已参与报告签字不能进行签发，详询质量部028-86607892！");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnMaps;
	}
	
	/**
	 * 优先分配
	 * @param list -- 多人可签人员集合
	 * @param device_type -- 设备类别
	 * @param first_rule -- 优先分配规则（1：量少优先分配 2：随机分配 3：指定分配）
	 * @param enter_user_id -- 报告编制人ID
	 * @param examine_user_id -- 报告审核人ID
	 * 
	 * @return 分配结果集
	 * @author GaoYa
	 * @date 2017-07-02 上午09:50:00
	 */
	private Map<String, Object> getLightIssue(List<CertificateBy> list, String device_type, String first_rule, String enter_user_id, String examine_user_id){
		Map<String, Object> returnMaps = new HashMap<String, Object>();
		String emp_id = "";
		String emp_name = "";
		String rule = "";
		int issue_count = 0;
		
		boolean allow = true;
		if("1".equals(first_rule)){
			for(CertificateBy cert : list){
				if(StringUtil.isNotEmpty(examine_user_id)  && StringUtil.isNotEmpty(enter_user_id)){
					if(examine_user_id.equals(cert.getUser_id()) || enter_user_id.equals(cert.getUser_id())){
						allow = false;
					}else{
						allow = true;
					}
				}else{
					allow = true;
				}
				
				if(allow){
					// 获取可签人员累计分配签发量
					int count = sysAutoIssueLogService.getCount(cert.getUser_id(), cert.getMayCertDeptId(), device_type);
					// 对比所有可签人员的分配签发量，取量少人员作为分配对象
					if(StringUtil.isEmpty(emp_id)){
						emp_id = cert.getUser_id();
						emp_name = cert.getUser_name();
						issue_count = count;
					}else{
						if(count < issue_count){
							emp_id = cert.getUser_id();
							emp_name = cert.getUser_name();
							issue_count = count;
						}
					}
				}
			}
			rule = "2";	// 量少优先分配
		} else if("2".equals(first_rule)){
			for(CertificateBy cert : list){
				if(StringUtil.isNotEmpty(examine_user_id)  && StringUtil.isNotEmpty(enter_user_id)){
					if(examine_user_id.equals(cert.getUser_id()) || enter_user_id.equals(cert.getUser_id())){
						allow = false;
					}else{
						allow = true;
						break;
					}
				}else{
					allow = true;
				}
				
				if(allow){
					emp_id = cert.getUser_id();
					emp_name = cert.getUser_name();
					rule = "3";	// 随机分配
				}
			}
		}else{
			for(CertificateBy cert : list){
				if(StringUtil.isNotEmpty(examine_user_id)  && StringUtil.isNotEmpty(enter_user_id)){
					if(examine_user_id.equals(cert.getUser_id()) || enter_user_id.equals(cert.getUser_id())){
						allow = false;
					}else{
						allow = true;
					}
				}else{
					allow = true;
				}
				
				if(allow){
					if("1".equals(cert.getIs_substitute_person())){
						emp_id = cert.getUser_id();
						emp_name = cert.getUser_name();
						rule = "5";	// 指定分配
						break;
					}else{
						if(StringUtil.isEmpty(emp_id)){
							emp_id = cert.getUser_id();
							emp_name = cert.getUser_name();
							rule = "5";	// 指定分配
						}
					}
				}
			}
		}
		
		if(StringUtil.isNotEmpty(emp_id)){
			returnMaps.put("success", true);
			returnMaps.put("next_op_id", emp_id);
			returnMaps.put("next_op_name", emp_name);					
			returnMaps.put("rule", rule);
		}else{
			returnMaps.put("success", false);
			if(allow){
				returnMaps.put("msg", "当前无在岗可签字人员，详询质量部028-86607892！");
			}else{
				returnMaps.put("msg", "当前可签字人员"+emp_name+"已参与报告签字不能进行签发，详询质量部028-86607892！");
			}
		}
		return returnMaps;
	}
	/**
	 * 保存报告时初始化RtPersonDir目录树
	 * @param info
	 * @throws Exception 
	 */
	public void initRtPersonDir(InspectionInfo info) throws Exception {
		Report report = reportService.get(info.getReport_type());
		RtPersonDir rtPersonDir = rtPersonDirManager.getByBusinessId(info.getId(), report.getRtboxCode());
		// 处理rtPersonDir未设置的情况
		if (rtPersonDir == null) {
			String rtDirJson = report.getRtDefaultDirJson();
			if(StringUtil.isEmpty(rtDirJson)) {
				rtDirJson = rtPageDao.getNewByCode(report.getRtboxCode()).getRtDirJson();
			}
			rtPersonDir = new RtPersonDir();
			rtPersonDir.setFkBusinessId(info.getId());
			rtPersonDir.setRtCode(report.getRtboxCode());
			rtPersonDir.setRtDirJson(rtDirJson);
			rtPersonDir.setFkCreateUserId(SecurityUtil.getSecurityUser().getId());
			rtPersonDir.setCreateTime(new Date());
			rtPersonDir.setVersion(RtPath.MINVERSION);// 版本号
			rtPersonDirManager.save(rtPersonDir);
			// 保存历史记录
			RtPersonDirVersion dirVersion = new RtPersonDirVersion();
			dirVersion.setRtPersonDir(rtPersonDir);
			dirVersion.setFkBusinessId(info.getId());
			dirVersion.setRtCode(report.getRtboxCode());
			dirVersion.setRtDirJson(rtDirJson);
			dirVersion.setFkCreateUserId(SecurityUtil.getSecurityUser().getId());
			dirVersion.setCreateTime(new Date());
			dirVersion.setVersion(RtPath.MINVERSION);// 版本号
			this.rtPersonDirVersionManager.save(dirVersion);
		}
	}
}
