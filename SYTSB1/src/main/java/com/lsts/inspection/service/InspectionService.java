package com.lsts.inspection.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.khnt.base.Factory;
import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowDefinitionManager;
import com.khnt.bpm.ext.service.FlowWorktaskManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.crud.web.support.QueryCondition;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.codetable.bean.CodeTableValue;
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
import com.lsts.common.dao.CodeTablesDao;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.Accessory;
import com.lsts.device.bean.BoilerPara;
import com.lsts.device.bean.CablewayPara;
import com.lsts.device.bean.CranePara;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.bean.DeviceWarningDeal;
import com.lsts.device.bean.DeviceWarningStatus;
import com.lsts.device.bean.ElevatorPara;
import com.lsts.device.bean.EnginePara;
import com.lsts.device.bean.PressurevesselsPara;
import com.lsts.device.bean.RidesPara;
import com.lsts.device.dao.AccessoryDao;
import com.lsts.device.dao.BoilerParaDao;
import com.lsts.device.dao.CablewayParaDao;
import com.lsts.device.dao.CraneParaDao;
import com.lsts.device.dao.DeviceDao;
import com.lsts.device.dao.DeviceWarningDealDao;
import com.lsts.device.dao.DeviceWarningStatusDao;
import com.lsts.device.dao.ElevatorParaDao;
import com.lsts.device.dao.EngineParaDao;
import com.lsts.device.dao.PressurevesselsParaDao;
import com.lsts.device.dao.RidesParaDao;
import com.lsts.device.service.DeviceService;
import com.lsts.device.service.DeviceWarningStatusService;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.dao.EmployeeCertDao;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentCentreDao;
import com.lsts.equipment2.dao.EquipmentLoanDao;
import com.lsts.inspection.bean.Drawlog;
import com.lsts.inspection.bean.GetInspection;
import com.lsts.inspection.bean.Inspection;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionInfoPay;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.bean.ReportItemValue;
import com.lsts.inspection.bean.ReportPageCheckInfo;
import com.lsts.inspection.bean.ReportPerAudit;
import com.lsts.inspection.bean.SysAutoIssueLog;
import com.lsts.inspection.dao.DrawlogDao;
import com.lsts.inspection.dao.InspectionDao;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.inspection.dao.ReportItemValueDao;
import com.lsts.inspection.dao.ReportPageCheckInfoDao;
import com.lsts.inspection.dao.ReportPerDao;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.dao.EnterDao;
import com.lsts.qualitymanage.dao.QualityXybzFileDao;
import com.lsts.report.bean.Report;
import com.lsts.report.bean.ReportDraw;
import com.lsts.report.bean.ReportErrorRecordInfo;
import com.lsts.report.bean.ReportItem;
import com.lsts.report.bean.SetReportItem;
import com.lsts.report.dao.ReportDao;
import com.lsts.report.dao.ReportDrawDao;
import com.lsts.report.dao.ReportErrorRecordInfoDao;
import com.lsts.report.dao.ReportItemDao;
import com.lsts.report.service.ReportDrawService;
import com.lsts.report.service.ReportService;
import com.lsts.sinspection.bean.SupervisionInspection;
import com.lsts.sinspection.dao.SupervisionInspectionDao;
import com.ming.webreport.MREngine;
import com.scts.payment.bean.InspectionInfoDTO;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.service.InspectionPayInfoService;
import com.scts.payment.service.PayInfoCache;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Base64Utils;
//import util.ConnMJc;
import util.TS_Util;
//import util.TransManager;



/**
 * 科室报检   servier
 * 
 * @author 肖慈边 2014-2-13
 */
@Service("inspectionService")
public class InspectionService extends EntityManageImpl<Inspection, InspectionDao> {
	private Logger logger = Logger.getLogger(this.getClass());
	//数据状态
	public static final String USRSTATUS="1";//使用状态
	public static final String DETSTATUS="2";//删除状态

	@Autowired
	private	SysLogService logService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ProcessManager processManager;
	@Autowired
	InspectionCommonService inspectionCommonService;
	@Autowired
	private InspectionDao inspectionDao;
	@Autowired
	ReportPageCheckInfoService reportPageCheckInfoService;
	@Autowired
	private InspectionInfoDao inspectionInfoDao ;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private InspectionInfoDao infoDao;
	@Autowired
	SysAutoIssueLogService sysAutoIssueLogService;
	@Autowired
	private ReportItemDao itemDao;
	@Autowired
	CertificateByService certificateByService;
	@Autowired
	private ReportPerDao perDao;
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private ActivityManager activityManager;
	
	@Autowired
	private ReportItemValueDao reportItemValueDao;
	
	@Autowired
	private BoilerParaDao boilerParaDao;
	
	@Autowired
	private EnterDao enterDao;
	
	@Autowired
	private CraneParaDao craneParaDao;
	
	@Autowired
	private ElevatorParaDao elevatorParaDao;
	
	@Autowired
	private EngineParaDao engineParaDao;
	
	@Autowired
	private PressurevesselsParaDao pressurevesselsParaDao;
	
	@Autowired
	private RidesParaDao ridesParaDao ;
	@Autowired
	private AccessoryDao accDao ;
	@Autowired
	private CablewayParaDao calDao ;
	@Autowired
	MessageService messageService;
	@Autowired
	private ReportDrawDao reportDrawDao;
	
	@Autowired
	FlowWorktaskManager flowExtManager;
	@Autowired
	FlowDefinitionManager flowDefManager;
	@Autowired
	SupervisionInspectionDao supDao;
	@Autowired
	private DeviceWarningDealDao deviceWarningDealDao;
	@Autowired
	private DeviceWarningStatusDao deviceWarningStatusDao;
	@Autowired
	private DeviceWarningStatusService deviceWarningStatusService;
	@Autowired
	private ImageTool imageTool;
	@Autowired
	private ReportItemValueService reportItemValueService;
	@Autowired
	private ReportPageCheckInfoDao reportPageCheckInfoDao;
	@Autowired
    QualityXybzFileDao qualityXybzFileDao;
	@Autowired
    EquipmentLoanDao equipmentLoanDao;
	@Autowired
	EquipmentCentreDao equipmentCentreDao;
	//@Autowired
  //  EquipmentDao baseEquipment2Dao;
	@Autowired
    EquipmentBoxDao equipmentBoxDao;
	
	@Autowired
	private InspectionInfoPayService inspectionInfoPayService;
	@Autowired
	private ReportErrorRecordInfoDao reportErrorRecordInfoDao;
	@Autowired
	private EmployeeCertDao employeeCertDao;
	@Autowired
	private EmployeesDao employeesDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CodeTablesDao codeTablesDao;
	@Autowired
	private ReportDrawService reportDrawService;
	@Autowired
	private InspectionPayInfoService inspectionPayInfoService;
	@Autowired
	ReportDrawSignService reportDrawSignService;
	@Autowired
	InspectionZZJDInfoService inspectionZZJDInfoService;
	
	@Autowired
	DrawlogDao drawlogDao;
	
	 /**
     * 
     *保存报检信息
     * @return
     * @throws Exception
     */
	public Inspection flow_saveBasic(Inspection inspection,String sc) throws Exception
    {
		
		//获取connection
    	Connection conn = Factory.getDB().getConnetion();
    	
    	
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Org org = TS_Util.getCurOrg(curUser);
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		
    //查询检验负责人和参检人员
		//InspectionHallPara para =  hallDao.get(inspection.getFk_hall_para_id());
		
    	//报检业务表插入数据
		int cont =0;
    	for (InspectionInfo info : inspection.getInspectionInfo()) {
			
    		cont++;
    		info.setInspection(inspection);
    		
    		info.setEnter_time(new Date());
    	
    		info.setEnter_op_id(emp.getId());
    		
    		info.setEnter_op_name(emp.getName());
    		
    		info.setEnter_unit_id(org.getId());
    		//info.setReport_com_name(inspection.getCom_name());
    		

    		info.setData_status("1");
    		if(info.getSn()==null){
    		//获取业务流水号
    		String sn = TS_Util.getSn(cont,conn) ;
    		
    		info.setSn(sn);
    		}
		}
		//获取业务报检表单号
		try{
    	String num = TS_Util.getYWBJNumber();
    	inspection.setAccept_no(num);
    	
    	inspection.setInspection_time(new Date());
    	//设备状态
    	inspection.setData_status(USRSTATUS);
//    	inspection.setAccept_no(map.get("acc_no").toString());
    //	inspection.setCheck_type(map.get("check_type").toString());
    	
    	inspection.setEnter_op(user.getName());
    	inspectionDao.save(inspection);
	    Factory.getDB().freeConnetion(conn);//释放连接
		}catch(Exception e){
			e.printStackTrace();
		}
		return inspection;
    }
	public Inspection flow_updateBasic(Inspection inspection,String ids,String cur_user_id,String op_name,String check_name) throws Exception {
		
   
		for (InspectionInfo info : inspection.getInspectionInfo()) {
			
    		info.setInspection(inspection);
    		if(op_name.equals("")||op_name==null||op_name.equals("null")){
    			String sql ="select t.name text from employee t where t.id='"+cur_user_id+"'";
    			List lis =inspectionInfoDao.createSQLQuery(sql).list();
    			op_name=lis.get(0).toString();
    			
    		}
    		if(check_name.equals("")||check_name==null||check_name.equals("null")){
    			StringBuffer sb = new StringBuffer();
    			String checkOpId="";
    			String checkId=info.getCheck_op_id();
    			if(checkId.contains(",")){
    				String[] chek=checkId.split(",");
    				for (int i = 0; i < chek.length; i++) {
    					sb.append("'"+chek[i]+"',");
					}
    				checkOpId=sb.toString().substring(0,sb.toString().length()-1);
    			}else{
    				checkOpId=checkId;
    			}
    			String sql ="select wmsys.wm_concat(t.name) text from employee t where t.id in ("+checkOpId+")";
    			List lis2 =inspectionInfoDao.createSQLQuery(sql).list();
    			check_name=lis2.get(0).toString();
    			
    		}
    		
		
		//判断是否多个ID
				if(ids.indexOf(",")!=-1){
					
					String id[] =ids.split(",");
					
					for(int i=0;i<id.length;i++){
						
						
						//InspectionInfo info=infoDao.get(id[i]);
						
						inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set item_op_id='"+cur_user_id+"', item_op_name ='"+op_name+"',check_op_name='"+check_name+"', advance_time=to_date('"+DateUtil.getDateTime("yyyy-MM-dd",info.getAdvance_time())+"','yyyy-MM-dd'),check_op_id='"+info.getCheck_op_id()+"' item_op_id= where id =?",info.getId() ).executeUpdate();
						//inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set operation_status='2' where id =?",info.inspection.getId() ).executeUpdate();
			
					}
					
				}else{
					//InspectionInfo info=infoDao.get(ids);
					inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO   set item_op_id='"+cur_user_id+"',item_op_name ='"+op_name+"',check_op_name='"+check_name+"', advance_time=to_date('"+DateUtil.getDateTime("yyyy-MM-dd",info.getAdvance_time())+"','yyyy-MM-dd'),check_op_id='"+info.getCheck_op_id()+"' where id = ?",info.getId()).executeUpdate();
					//inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set operation_status='2' where id =?",info.inspection.getId() ).executeUpdate();
				}
		}
		return inspection;
    }
	
	/**
     * 
     *保存报检信息
     * @return
     * @throws Exception
     */
	public synchronized HashMap<String, Object> saveBasic(Inspection inspection, HttpServletRequest request) throws Exception{
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);		

		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			String device_type = "";
			String device_category = "";
			String device_sort_code = "";
			String check_type = inspection.getCheck_type();
			String check_op_name = URLDecoder.decode(request.getParameter("check_op_name"), "utf-8");
			
			//添加新的报检信息
			int cont = 0;
			for (InspectionInfo info : inspection.getInspectionInfo()) {
				cont++;
				if (info.getSn() == null) {
					// 获取业务流水号
					String sn = TS_Util.getSn(Integer.valueOf(cont), conn);
					info.setSn(sn);
				}
				String rwdId=request.getParameter("rwdId");
				String rwdSn=request.getParameter("rwdSn");
				info.setContract_task_id(rwdId);
				info.setContract_task_sn(rwdSn);
				
				DeviceDocument deviceDocument = null;
				if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
					deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
					if(deviceDocument != null){
						if(StringUtil.isNotEmpty(deviceDocument.getDevice_sort_code())){
							device_sort_code = deviceDocument.getDevice_sort_code();
							device_type = deviceDocument.getDevice_sort_code().substring(0,1);
							device_category = deviceDocument.getDevice_sort_code().substring(0,2);
						}
						if(StringUtil.isEmpty(device_type) || StringUtil.isEmpty(device_category)){
							if(StringUtil.isNotEmpty(deviceDocument.getDevice_sort())){
								device_type = deviceDocument.getDevice_sort().substring(0, 1);
								device_category = deviceDocument.getDevice_sort().substring(0, 2);
							}
						}
					}
				}
				// 验证是否是电梯定检报告
				if("3".equals(device_type) && "3".equals(check_type)){
					Date check_date = info.getAdvance_time();
					Date inspection_date = DateUtil.convertStringToDate("yyyy-MM-dd", "2017-05-01");
					if(check_date.after(inspection_date)){
						returnMap.put("success", false);
						returnMap.put("msg", "2017-05-01后的电梯定检报告，请使用移动设备录入原始记录！");
						return returnMap;
					}
				}
				// 验证参检人员是否包含编制人自己，不包含则提示编制人必须选择自己在内
				if (!check_op_name.contains(emp.getName())) {
					returnMap.put("success", false);
					returnMap.put("msg", "参检人员必须包含编制人自己，请检查！");
					return returnMap;
				}else{
					String check_op_id = info.getCheck_op_id();
					if(StringUtil.isNotEmpty(check_op_id)){
						String[] check_op_ids = check_op_id.split(",");
						for(int i=0; i<check_op_ids.length; i++){
							Employee check_op_emp = employeesDao.get(check_op_ids[i]);
							boolean has_cert = false;
							if(check_op_emp != null){
								List<EmployeeCert> certList = employeeCertDao.queryEmployeeCertByBasicId(check_op_ids[i]);
								for(EmployeeCert cert : certList){
									//if(cert_types.contains(cert.getCert_type())){
										if ("3".equals(device_type)) {	// 电梯
											if ("TS".equals(cert.getCert_type()) || "DT".equals(cert.getCert_type())
													|| "DT-1".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("1".equals(device_type)) {	// 锅炉
											if ("GS".equals(cert.getCert_type()) || "GL-1".equals(cert.getCert_type())
													|| "GL-2".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("2".equals(device_type)) {	// 压力容器
											if("23".equalsIgnoreCase(device_category)){
												if ("QP".equals(cert.getCert_type()) || "QP-1".equals(cert.getCert_type())
														|| "QP-2".equals(cert.getCert_type())){
													HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
													if (Boolean.valueOf(String.valueOf(map
															.get("success")))) {
														has_cert = true;
													} else {
														return map;
													}
												}
											}else{
												if ("RS".equals(cert.getCert_type()) || "RQ-1".equals(cert.getCert_type())
														|| "RQ-2".equals(cert.getCert_type())){
													HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
													if (Boolean.valueOf(String.valueOf(map
															.get("success")))) {
														has_cert = true;
													} else {
														return map;
													}
												}
											}
										}else if ("4".equals(device_type)) {	// 起重机
											if ("QS".equals(cert.getCert_type()) || "QZ-1".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("5".equals(device_type)) {	// 厂车
											if ("NS".equals(cert.getCert_type()) || "NC-1".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("6".equals(device_type)) {	// 游乐设施
											if ("YS".equals(cert.getCert_type()) || "YL-1".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("8".equals(device_type)) {	// 压力管道
											if ("DS".equals(cert.getCert_type()) || "GD-1".equals(cert.getCert_type())
													|| "GD-2".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("9".equals(device_type)) {	// 客运索道
											if ("SS".equals(cert.getCert_type()) || "SD-1".equals(cert.getCert_type())){
												HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
												if (Boolean.valueOf(String.valueOf(map
														.get("success")))) {
													has_cert = true;
												} else {
													return map;
												}
											}
										}else if ("F".equals(device_type) || "7310".equals(device_sort_code)) {	// 安全附件
											if ("7310".equals(device_sort_code)){
												if ("安全阀".equals(cert.getCert_type())){
													HashMap<String, Object> map = validateCert(cert.getCert_type(), cert.getCert_end_date(), check_op_emp.getName());
													if (Boolean.valueOf(String.valueOf(map
															.get("success")))) {
														has_cert = true;
													} else {
														return map;
													}
												}
											}else{
												// 其他类暂时先验证通过
												has_cert = true;
											}
										}else{
											// 其他类暂时先验证通过
											has_cert = true;
										}
									//}
										// 2017-06-21经科管部确认，证书以S结尾并且不等于GSS的为检验师资格证书
								}
								// 验证所选参检人员有无检验资格证书
								if(!has_cert){
									returnMap.put("success", false);
									returnMap.put("msg", "系统暂无"+check_op_emp.getName()+"的相关检验资格证书，请联系科研技术管理部028-86607894。");
									return returnMap;
								}
								
							}
						}
					}
					// 验证参检人员的检验资格证书end.
				}
				
				info.setCheck_op_name(check_op_name);
				info.setFee_status("1");
				info.setData_status("1");
				info.setIs_back("1"); // 默认的状态为1，提交后变成0，退回也变成1
				info.setIs_mobile("0"); // 是否移动检验，默认为0（0：否 1：是）
				info.setCheck_unit_id(org.getId());
				info.setEnter_unit_id(org.getId());

				info.setReport_com_name(inspection.getCom_name());
				info.setReport_com_address(inspection.getCom_address());
				// 保存检验类型和检验日期到业务表

				info.setEnter_op_id(emp.getId());
				info.setEnter_op_name(emp.getName());
				info.setIs_copy("0");// 不是复制报告
				info.setCreate_time(new Date());
				info.setLast_mdy_time(new Date());

				if (deviceDocument != null) {
					// 验证2个月内是否已录入报告，有纠错程序除外（质量部要求）start......
					if(StringUtil.isNotEmpty(deviceDocument.getDevice_registration_code())){
						// 定检报告，验证同一个设备2个月内是否已录入报告，有纠错程序除外（质量部要求）
						// 检验类别（3：定检 2：监检）
						// 质量部谢方2017-08-22提出修改，增加不同的报告模板出具时，则不受此限制
						if ("3".equals(check_type)) {
							// 2017-08-22 张展彬提出修改
							// 配合2017年联合国世界旅游组织第22届全体大会保障性检验，暂时放开电梯定检报告出具周期限制
							// 2017-09-11 张展彬提出修改，重启开启电梯定检报告出具周期限制
							// 2017-09-12 张展彬提出修改，暂时放开电梯定检报告出具周期限制
							// 2017-09-12 增加电梯定检报告出具周期（2个月）限制开关可控功能
							String vali = Factory.getSysPara().getProperty("REPORT_VALIDATE_REPEAT_2M");
							boolean validate_repeat = true;
							if(StringUtil.isNotEmpty(vali)){
								if("true".equals(vali.trim()) || "false".equals(vali.trim())){
									validate_repeat = Boolean.parseBoolean(vali.trim());
								}
							}
							if(validate_repeat){
								String inspection_id = info.getInspection() != null ? info.getInspection().getId() : "";
								if(StringUtil.isEmpty(inspection_id)){
									inspection_id = inspection.getId() != null ? inspection.getId() : "";
								}
								List<InspectionInfo> info_list = infoDao
										.getNewInfoByDeviceId(info.getFk_tsjc_device_document_id(), inspection_id);
								if (info_list != null) {
									if (info_list.size() > 0) {
										for (InspectionInfo old_info : info_list) {
											if (StringUtil.isNotEmpty(old_info.getInspection_conclusion())) {
												if (!"不合格".equals(old_info.getInspection_conclusion().trim())) {
													if(old_info.getReport_type().equals(info.getReport_type())){
														HashMap<String, Object> checkRepeatMap = validateRepeat(old_info,
																deviceDocument.getDevice_registration_code());
														if (!Boolean.valueOf(String.valueOf(checkRepeatMap.get("success")))) {
															return checkRepeatMap;
														}
													}
												}
											}
										}
									}
								}
							}else{
								if(!"3".equals(device_type)){
									String inspection_id = info.getInspection() != null ? info.getInspection().getId() : "";
									if(StringUtil.isEmpty(inspection_id)){
										inspection_id = inspection.getId() != null ? inspection.getId() : "";
									}
									List<InspectionInfo> info_list = infoDao
											.getNewInfoByDeviceId(info.getFk_tsjc_device_document_id(), inspection_id);
									if (info_list != null) {
										if (info_list.size() > 0) {
											for (InspectionInfo old_info : info_list) {
												if (StringUtil.isNotEmpty(old_info.getInspection_conclusion())) {
													if (!"不合格".equals(old_info.getInspection_conclusion().trim())) {
														if(old_info.getReport_type().equals(info.getReport_type())){
															HashMap<String, Object> checkRepeatMap = validateRepeat(old_info,
																	deviceDocument.getDevice_registration_code());
															if (!Boolean.valueOf(String.valueOf(checkRepeatMap.get("success")))) {
																return checkRepeatMap;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					// 验证2个月内是否已录入报告，有纠错程序除外（质量部要求）end......

					// 记录设备是否报检状态
					if (!"/".equals(deviceDocument.getDevice_registration_code())
							&& !"无此项".equals(deviceDocument.getDevice_registration_code())) {
						List<DeviceDocument> list = deviceDao
								.queryDeviceDocumentByDeviceCode(deviceDocument.getDevice_registration_code());
						if (list.size() > 1) {
							returnMap.put("success", false);
							returnMap.put("msg", "所选设备中存在设备注册代码重复的设备，请检查！");
							return returnMap;
						}
					}
					// deviceDocument.setIs_cur_check("2"); // 当前是否报检（1：未报检
					// 2：当前报检中）
					// deviceDao.save(deviceDocument); // 记录该设备当前是否正在报检中
					deviceDao.updateDeviceInfo(info.getFk_tsjc_device_document_id()); // 记录该设备当前是否正在报检中
				}
				info.setInspection(inspection);
			}

			if (inspection.getAccept_no() == null || "".endsWith(inspection.getAccept_no())) {
				String num = TS_Util.getYWBJNumber();
				inspection.setAccept_no(num);
			}
			inspection.setInspection_time(new Date());
			// 设备状态
			inspection.setData_status(USRSTATUS);
			inspection.setEnter_op(user.getName());
			inspectionDao.save(inspection);

			returnMap.put("success", true);
			returnMap.put("msg", "保存成功");
			/*logService.setLogs("", "报告报检", "报检信息录入系统", user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	private HashMap<String, Object> validateRepeat(InspectionInfo old_info, String device_registration_code){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date enter_time = old_info.getEnter_time();
		if(enter_time == null){
			enter_time = old_info.getAdvance_time();
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(enter_time); // 录入/检验日期
		c2.setTime(new Date()); // 当前日期
		// 设置时间为0时   
		c1.set(java.util.Calendar.HOUR_OF_DAY, 0);   
		c1.set(java.util.Calendar.MINUTE, 0);   
		c1.set(java.util.Calendar.SECOND, 0);   
		c2.set(java.util.Calendar.HOUR_OF_DAY, 0);   
		c2.set(java.util.Calendar.MINUTE, 0);   
		c2.set(java.util.Calendar.SECOND, 0);   
		
		// 计算两个日期相差的天数   
        int days = ((int) (c2.getTime().getTime() / 1000) - (int) (c1   
               .getTime().getTime() / 1000)) / 3600 / 24; 
		
		// 计算月份差（只能计算同一年的日期，翻年的日期不适应该方法）
		// int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		if (days <= 60) {
			List<ReportErrorRecordInfo> record_infos = reportErrorRecordInfoDao
					.queryInfoByInfoIds(old_info.getId());
			if (record_infos.isEmpty()) {
				returnMap.put("success", false);
				if(StringUtil.isNotEmpty(old_info.getFlow_note_name())){
					if ("打印报告".equals(old_info.getFlow_note_name())
							|| "报告领取".equals(old_info.getFlow_note_name())
							|| "报告归档".equals(old_info.getFlow_note_name())) {
						returnMap.put("msg",
								"设备" + (device_registration_code != null
										? device_registration_code : "")
										+ "在短期内已出过报告（已签发），如若需要重新出报告，请先提交纠错申请！");
					} else {
						returnMap.put("msg",
								"设备" + (device_registration_code != null
										? device_registration_code : "")
										+ "在短期内已出过报告（未签发），不能重复，请检查！");
					}
				} else {
					returnMap.put("msg", old_info.getEnter_op_name() + "同志已报检该设备（"
							+ (device_registration_code != null ? device_registration_code : "") + "），不能重复，请核实！");
				}
			}else{
				returnMap.put("success", true);
			}
		}else{
			returnMap.put("success", true);
		}
		return returnMap;
	}
	
	
	private HashMap<String, Object> validateCert(String cert_type, Date cert_end_date, String check_op_name){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Date cur_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime());
			if(cert_end_date.before(cur_date)){
				returnMap.put("success", false);
				returnMap.put("msg", check_op_name+"的检验证书（"+cert_type+"）已过期，请联系科研技术管理部028-86607894。");
			}else{
				returnMap.put("success", true);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	 /**
     * 
     *保存安全阀信息
     * @return
     * @throws Exception
     */
	public Inspection flow_saveAccessory(Inspection inspection,String acc_no) throws Exception
    {
		
		//获取connection
    	Connection conn = Factory.getDB().getConnetion();
    
    	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
    	//报检业务表插入数据
		int cont =0;
    	for (InspectionInfo info : inspection.getInspectionInfo()) {
			
    		cont++;
    		
    		info.setInspection(inspection);
    		
    		info.setEnter_time(new Date());
    	
    		info.setEnter_op_id(emp.getId());
    		
    		info.setEnter_op_name(emp.getName());
    		
    		info.setEnter_unit_id(org.getId());
    		
    		info.setData_status("1");
    		if(info.getSn()==null){
    		//获取业务流水号
    		String sn = TS_Util.getSn(cont,conn) ;
    		
    		info.setSn(sn);
    		}
		}
    	inspection.setInspection_time(new Date());
    	//设备状态
    	inspection.setData_status(USRSTATUS);
    	
    	inspection.setEnter_op(user.getName());

    	inspectionDao.save(inspection);

		return inspection;
    }
	
	/**
	 * 回退流程
	 * @param infoId 业务id
	 * @param flowId 流程id
	 * @return
	 */

public void returApprove(Map<String, Object> map,HttpServletRequest request) throws Exception{
		
		String infoId=  map.get("ins_info_id").toString();
		
		String acc_id = map.get("acc_id").toString();
		
		String flow_num = map.get("flow_num").toString();
		
		InspectionInfo  info  = infoDao.get(infoId);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		if(map.get("backStep")!=null){
			
			String backStep = map.get("backStep").toString();
			
			JSONObject dataBus = new JSONObject();
			
			dataBus.put("flag", backStep);
			
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			
		}
		if(map.get("nextHandle")!=null){
			
			String nextHandle = map.get("nextHandle").toString();
			
			JSONObject dataBus = new JSONObject();
			
			dataBus.put("flag", nextHandle);
			
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			
		}
		
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);
		
		if(info.getFlow_note_id().equals(flow_num)){
		
			Map<String, Object> flowMap = flowExtManager.returnedActivity(paramMap);
			
			//获取退回步骤Id
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
			
			

			//写入日志
			
			String step_name = info.getFlow_note_name();
			
			String op_conclusion = "从"+step_name+"环节回退到"+list.get(0).getName()+"环节。";
			
			String re_name = step_name+"回退";
			 	
			String revise_remark = map.get("revise_remark")==null?"":map.get("revise_remark").toString();
			
			if(!"".equals(revise_remark)){
				
				op_conclusion=op_conclusion +map.get("revise_remark").toString();
				
			}
			
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User)curUser.getSysUser();
			// Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
			// Org org = TS_Util.getCurOrg(curUser);
			
			//String address = request.getRemoteHost();
			
			
			info.setFlow_note_id(list.get(0).getActivityId());
			
			// info.setFlow_note_num(map.get("status").toString());
			
			info.setFlow_note_name(list.get(0).getName());
			//info.setIs_report_input("1");
			info.setIs_back("1");	// 1：退回报检环节
			
			// 录入人自己撤回报告时，不记录审核、签发信息（只有非录入人退回才记录审核、签发信息）
			// 2017-07-10邹益平提出要求，退回后，再次审核或签发时，审批名字应为空白，故此处不再进行记录
			/*if (!emp.getId().equals(info.getEnter_op_id())) {
				if (step_name.contains("审核")) {
					info.setExamine_name(emp.getName());
					info.setExamine_id(emp.getId());
					info.setExamine_Date(new Date());				
				}else if (step_name.contains("签发")) {
					info.setIssue_id(emp.getId());
					info.setIssue_name(emp.getName());
					info.setIssue_Date(new Date());
				}
			}*/
			infoDao.save(info);

			logService.setLogs(infoId, re_name, op_conclusion, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
			/*//所有环节退回到科室报检，清空流程信息，重新走流程
			if(list.get(0).getName().equals("科室报检")){
				String sql="update TZSB_INSPECTION_INFO set fk_flow_index_id='',flow_note_num='',flow_note_name='',is_flow='',is_report_input='1' where id in ("+IdFormat.formatIdStr(infoId)+")";
				infoDao.createSQLQuery(sql).executeUpdate();
			}*/
		}
		

	}

	/**
	 * 报告撤回（已自动分配签发的报告）
	 * @param map -- 业务信息集合
	 *   
	 * @return 无
	 * @author GaoYa
	 * @date 2017-07-19 上午10:45:00
	 */

	public void returnIssueReports(Map<String, Object> map, HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		
		String infoId = map.get("ins_info_id").toString();
		String acc_id = map.get("acc_id").toString();
		String flow_num = map.get("flow_num").toString();

		InspectionInfo info = infoDao.get(infoId);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (map.get("backStep") != null) {
			String backStep = map.get("backStep").toString();
			JSONObject dataBus = new JSONObject();
			dataBus.put("flag", backStep);
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
		}
		
		if (map.get("nextHandle") != null) {
			String nextHandle = map.get("nextHandle").toString();
			JSONObject dataBus = new JSONObject();
			dataBus.put("flag", nextHandle);
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
		}

		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);

		if (info.getFlow_note_id().equals(flow_num)) {
			Map<String, Object> flowMap = flowExtManager.returnedActivity(paramMap);
			// 获取退回步骤Id
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);

			// 写入日志
			String step_name = info.getFlow_note_name();
			String op_conclusion = "从" + step_name + "环节撤回到" + list.get(0).getName() + "环节。";
			String re_name =  "报告撤回";
			String revise_remark = map.get("revise_remark") == null ? "" : map.get("revise_remark").toString();
			if (!"".equals(revise_remark)) {
				op_conclusion = op_conclusion + map.get("revise_remark").toString();
			}
			info.setFlow_note_id(list.get(0).getActivityId());
			info.setFlow_note_name(list.get(0).getName());
			//info.setIs_report_input("1");
			//info.setIs_back("1"); 
			infoDao.save(info);

			logService.setLogs(infoId, re_name, op_conclusion, user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());
		}
	}
	
	/**
	 * 分页审核时回退流程
	 * @param infoId 业务id
	 * @param flowId 流程id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void returnReport(Map<String, Object> map, HttpServletRequest request) throws Exception{
		String infoId=  map.get("ins_info_id").toString();
		String acc_id = map.get("acc_id").toString();
		String flow_num = map.get("flow_num").toString();
		InspectionInfo  info  = infoDao.get(infoId);
		Map<String,Object> paramMap = new HashMap();
		if(map.get("backStep")!=null){
			String backStep = map.get("backStep").toString();
			JSONObject dataBus = new JSONObject();
			dataBus.put("flag", backStep);
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
		}
		if(map.get("nextHandle")!=null){
			String nextHandle = map.get("nextHandle").toString();
			JSONObject dataBus = new JSONObject();
			dataBus.put("flag", nextHandle);
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
		}
		
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);
		
		if(info.getFlow_note_id().equals(flow_num)){
			Map<String, Object> flowMap = flowExtManager.returnedActivity(paramMap);
			//获取退回步骤Id
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
			//写入日志
			String step_name = info.getFlow_note_name();
			String op_conclusion = "从"+step_name+"环节回退到"+list.get(0).getName()+"环节。";
			String re_name = step_name+"回退";
			String revise_remark = map.get("revise_remark")==null?"":map.get("revise_remark").toString();
			if(!"".equals(revise_remark)){
				op_conclusion=op_conclusion +map.get("revise_remark").toString();
			}
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			info.setFlow_note_id(list.get(0).getActivityId());
			info.setFlow_note_name(list.get(0).getName());
			info.setIs_report_input("1");
			info.setIs_back("1");	// 1：退回报检环节
			
			/* 分页审核时，不将分页审核人信息记录成主审核人员信息
			// 录入人自己撤回报告时，不记录审核、签发信息（只有非录入人退回才记录审核、签发信息）
			// 2017-07-10邹益平提出要求，退回后，再次审核或签发时，审批名字应为空白，故此处不再进行记录
			if (!emp.getId().equals(info.getEnter_op_id())) {
				if (step_name.contains("审核")) {
					info.setExamine_name(emp.getName());
					info.setExamine_id(emp.getId());
					info.setExamine_Date(new Date());				
				}else if (step_name.contains("签发")) {
					info.setIssue_id(emp.getId());
					info.setIssue_name(emp.getName());
					info.setIssue_Date(new Date());
				}
			}
			*/
			infoDao.save(info);
			logService.setLogs(infoId, re_name, op_conclusion, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
		}
	}
	
	
	public void dealDeviceInfo(Map<String, Object> map) throws Exception {
		// 业务ID
		String infoId = map.get("ins_info_id").toString();
		// 获取业务对象
		InspectionInfo info = infoDao.get(infoId);
		if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
			// 获取设备对象
			DeviceDocument doc = deviceDao.get(info.getFk_tsjc_device_document_id());
			if (doc != null && doc.getId() != null) {
				if (!"11111111111111111111111111111111".equals(doc.getId())) {
					if (doc.getDevice_sort_code().startsWith("3")) {// 判断设备类型是电梯
						if (StringUtil.isNotEmpty(info.getInspection_conclusion())) {
							//if (info.getInspection_conclusion().equals("合格") || info.getInspection_conclusion().equals("复检合格")) {
								// 合格或复检合格就返写下次检验日期         
								// 2016-09-22修改为无论结论是否合格，都返写  by GaoYa
								// 回写更新人和更新时间
								// doc.setLast_upd_by(emp.getName());
								// doc.setLast_upd_date(new Date());
								if (info.getAdvance_time() != null && !info.getAdvance_time().equals("")) {
									doc.setInspect_date(info.getAdvance_time());
								}
								// 判断检验部门不为空
								if (info.getCheck_unit_id() != null) {
									// 判断以前下次检验日期是否为空 为空就不插入下次检验日期
									if (!(info.getLast_check_time() == null && info.getLast_check_time().equals(""))) {
										doc.setInspect_next_date(info.getLast_check_time());
									}
								}
							//}
								
							// 返写设备二维码编号
							if (StringUtil.isNotEmpty(info.getDevice_qr_code())) {
								doc.setDevice_qr_code(info.getDevice_qr_code().trim());
							}
							// 返写设备使用登记证编号
							if (StringUtil.isNotEmpty(info.getRegistration_num())) {
								doc.setRegistration_num(info.getRegistration_num().trim());
							}
						}
					} else {
						// 回写更新人和更新时间
						// doc.setLast_upd_by(emp.getName());
						// doc.setLast_upd_date(new Date());
						if (info.getAdvance_time() != null && !info.getAdvance_time().equals("")) {
							doc.setInspect_date(info.getAdvance_time());
						}
						// 判断检验部门不为空
						if (info.getCheck_unit_id() != null) {
							// 判断以前下次检验日期是否为空 为空就不插入下次检验日期
							if (info.getLast_check_time()!=null) {
								doc.setInspect_next_date(info.getLast_check_time());
							}
							// 判断锅炉和压力容器检验结论
							if (doc.getDevice_sort_code().startsWith("1")
									|| doc.getDevice_sort_code().startsWith("2")) {
								if (StringUtil.isNotEmpty(info.getInspection_conclusion())) {
									if (!info.getInspection_conclusion().equals("停止运行")
											&& !info.getInspection_conclusion().equals("5级")
											&& !info.getInspection_conclusion().equals("Ⅳ级")) {
										if (info.getLast_check_time() == null || info.getLast_check_time().equals("")) {
											doc.setInspect_next_date(null);
										}
									}
								}
							}
						}
					}
					doc.setSend_status("0");
					// 报告签发后，将设备状态设置为在用状态
					doc.setDevice_status("2"); // 2：在用
					deviceDao.save(doc);
				}
			}
		}
	}
	/**
	 * 提交流程
	 * @param infoId 业务id
	 * @param flowId 流程id
	 * @return
	 */
	
public  synchronized void  subFlowProcess(Map<String, Object> map,HttpServletRequest request) throws Exception{
		
		
		
		String acc_id = map.get("acc_id").toString();
		
		String flow_num = map.get("flow_num").toString();
		
		String flag = map.get("flag").toString();
		
		Map<String,Object> paramMap = new HashMap();
		
		JSONObject dataBus = new JSONObject();
		
		if((!"0".equals(flag)&&!"1".equals(flag))){
		
			String next_sub_op  = map.get("next_sub_op").toString();
			
			String next_op_name  = map.get("next_op_name").toString();
			
			//數據總線獲取的指定下一步操作人
			JSONArray  pts = new JSONArray();
			JSONObject pt = new JSONObject();
			pt.put("id", next_sub_op);
			pt.put("name", next_op_name);
			pts.put(pt);
		
			dataBus.put("paticipator", pts);
	
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			
		}
		//判断是否是产品监检流程
		if(map.get("cj")!=null){
		
			if("1".equals(map.get("flag").toString())){
					
					 
					 //flag=0 表示直接走到报告打印的步骤
					 dataBus.put("flag", 0);
					 
					 
					 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
				}else{
				
					 //flag=1表示直接走到报告审核的步骤
					 dataBus.put("flag", 1);
					 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
				}
		}
		
		InspectionInfo  info  = null;
		try {
			info  = (InspectionInfo) map.get("info");
		}catch(Exception e) {
			
		}
		if(null == info) {
			String infoId=  map.get("ins_info_id").toString();
			info  = infoDao.get(infoId);
		}

		
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);
//		paramMap.put(FlowExtWorktaskParam.SERVICE_ID,infoId);

		
		paramMap.put(FlowExtWorktaskParam.SERVICE_ID,info.getId());
		paramMap.put(FlowExtWorktaskParam.BPM_USER,new BpmUserImpl(map.get("userid").toString(), map.get("username").toString(), null, null, null));
		
		
		if(info.getFlow_note_id().equals(flow_num)){
			
				Map<String, Object> flowMap = flowExtManager.submitActivity(paramMap);
			
		
			//获取下一步流程步骤
				List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
				
				
				
				//写入日志
				String step_name = info.getFlow_note_name();
				
				String op_conclusion = "从"+step_name+"环节进入"+list.get(0).getName()+"环节。";
				 	
				String revise_remark = map.get("revise_remark")==null?"":map.get("revise_remark").toString();
				
				if(!"".equals(revise_remark)){
					
					op_conclusion=op_conclusion +map.get("revise_remark").toString();
					
				}
				//CurrentSessionUser user = SecurityUtil.getSecurityUser();
				
				info.setFlow_note_id(list.get(0).getActivityId());
				
				
				info.setFlow_note_name(list.get(0).getName());
				
				info.setIs_back("0");//提交后状态变成0
				infoDao.save(info);
				try {
					logService.setLogs(info.getId(), step_name, op_conclusion, map.get("userid").toString(), map.get("username").toString(), new Date(), request.getRemoteAddr());
					if ("报告录入".equals(step_name)) {
						String to_user_name = "提交至"+map.get("next_op_name").toString();
						logService.setLogs(info.getId(), "提交报告审核", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
					}
					if("报告审核".equals(step_name)){
						String to_user_name = "提交至"+map.get("next_op_name").toString();
						logService.setLogs(info.getId(), step_name+"提交", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
	
		
	}


/**
 * 提交流程(20190401)
 * @param infoId 业务id
 * @param flowId 流程id
 * @return
 */

public  synchronized void  subFlowProcessNew(Map<String, Object> map,HttpServletRequest request) throws Exception{
	
	long stl = System.currentTimeMillis();
	
	String acc_id = map.get("acc_id").toString();
	
	String flow_num = map.get("flow_num").toString();
	
	String flag = map.get("flag").toString();
	
	Map<String,Object> paramMap = new HashMap();
	
	JSONObject dataBus = new JSONObject();
	
	if((!"0".equals(flag)&&!"1".equals(flag))){
	
		String next_sub_op  = map.get("next_sub_op").toString();
		
		String next_op_name  = map.get("next_op_name").toString();
		
		//數據總線獲取的指定下一步操作人
		JSONArray  pts = new JSONArray();
		JSONObject pt = new JSONObject();
		pt.put("id", next_sub_op);
		pt.put("name", next_op_name);
		pts.put(pt);
	
		dataBus.put("paticipator", pts);

		paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
		
	}
	//判断是否是产品监检流程
	if(map.get("cj")!=null){
	
		if("1".equals(map.get("flag").toString())){
				
				 
				 //flag=0 表示直接走到报告打印的步骤
				 dataBus.put("flag", 0);
				 
				 
				 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			}else{
			
				 //flag=1表示直接走到报告审核的步骤
				 dataBus.put("flag", 1);
				 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			}
	}
	String infoId = map.get("infoId").toString();
	String flowName = map.get("flowName").toString();
	String flowId = map.get("flowId").toString();
	
	
	paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);
//	paramMap.put(FlowExtWorktaskParam.SERVICE_ID,infoId);

	
	paramMap.put(FlowExtWorktaskParam.SERVICE_ID,infoId);
	paramMap.put(FlowExtWorktaskParam.BPM_USER,new BpmUserImpl(map.get("userid").toString(), map.get("username").toString(), null, null, null));
	
	
	if(flowId.equals(flow_num)){
		
			Map<String, Object> flowMap = flowExtManager.submitActivity(paramMap);
		
	
		//获取下一步流程步骤
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
			
			
			
			//写入日志
			String step_name = flowName;
			
			String op_conclusion = "从"+step_name+"环节进入"+list.get(0).getName()+"环节。";
			 	
			String revise_remark = map.get("revise_remark")==null?"":map.get("revise_remark").toString();
			
			if(!"".equals(revise_remark)){
				
				op_conclusion=op_conclusion +map.get("revise_remark").toString();
				
			}
			//CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			 long etl = System.currentTimeMillis();
			 System.out.println("流程提交---------------------------"+ (etl-stl));
			

			infoDao.saveSubFlag(infoId,list.get(0).getActivityId(),list.get(0).getName(),"0");
			 long et2 = System.currentTimeMillis();
			 System.out.println("流程提交修改---------------------------"+ (et2-etl));
			
			try {
				logService.setLogs(infoId, step_name, op_conclusion, map.get("userid").toString(), map.get("username").toString(), new Date(), request.getRemoteAddr());
				if ("报告录入".equals(step_name)) {
					String to_user_name = "提交至"+map.get("next_op_name").toString();
					logService.setLogs(infoId, "提交报告审核", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
				}
				if("报告审核".equals(step_name)){
					String to_user_name = "提交至"+map.get("next_op_name").toString();
					logService.setLogs(infoId, step_name+"提交", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
				}
				 long et3 = System.currentTimeMillis();
				 System.out.println("流程提交日志---------------------------"+ (et3-et2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	

	
}
	
	/**
	 * 启动流程
	 * @param infoId 业务id
	 * @param flowId 流程id
	 * @return
	 */
	public void StarFlowProcess(HashMap<String,String> map,HttpServletRequest request) {
		try{
		//业务ID
		String infoId = map.get("infoId");
		
		
		String flowId= map.get("flowId");
		
		InspectionInfo  info = infoDao.get(infoId);
		
		Map<String, Object> Param= new HashMap<String, Object>();
		
		//判断是否已经启动流程
		if(!"2".equals(info.getIs_flow())){
		
			//获取流程名称
			String flow_name = flowDefManager.get(flowId).getFlowname();
			
			String flow_type =  flowDefManager.get(flowId).getFlowtype();
			//流程业务ID
			Param.put(FlowExtWorktaskParam.SERVICE_ID, infoId);
			//业务标题
			Param.put(FlowExtWorktaskParam.SERVICE_TITLE,flow_name);
			//流程ID
			Param.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
			//第一个环节任务处理方式
			Param.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	
			Param.put(FlowExtWorktaskParam.SERVICE_TYPE, flow_type);
			
			
			
			//启动流程
			Map<String, Object> flowMap=flowExtManager.startFlowProcess(Param);
			//获取下一步流程步骤
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
			//流程ID插入业务表
			info.setFk_flow_index_id(flowId);
			//改变业务表状态 1 未进入流程 2 进入流程
			info.setIs_flow("2");
			
			info.setFlow_note_id(list.get(0).getActivityId());//流程状态 01 任务分配02 报告录入 03 报告审核 04 报告签发  05 打印报告 06领取报告 07报告归档
			
			//info.setFlow_note_num(map.get("status").toString());
			
			info.setFlow_note_name(list.get(0).getName());
			info.setIs_report_input("1");
			info.setIs_back("0");
			infoDao.save(info);
			
			
			
			//写入日志
			String op_conclusion="从科室报检环节进入报告录入环节。";
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			String address = request.getRemoteAddr();
			
			//logService.setLogs(infoId, "检验任务生成", op_conclusion, emp.getId(), emp.getName(), new Date(), request.getRemoteAddr());
			logService.setLogs(infoId, "科室报检", op_conclusion, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
			
		
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	/**
     * 
     *保存報告审核信息
     * @return
     * @throws Exception
     */
	public void flow_saveCheck(Map<String, Object> map) throws Exception {
		String id = map.get("ins_info_id").toString();
		String type = map.get("type").toString();

		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		
		try {
			InspectionInfo info = infoDao.get(id);
			SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
			// 判断流程修改相应数据
			if ("04".equals(type)) {// 报告审核
				info.setExamine_name(emp.getName());
				info.setExamine_id(emp.getId());
				info.setExamine_Date(fomat.parse(map.get("op_time").toString()));
				info.setExamine_Date2(new Date());	// 审核时间（实际审核日期，不显示于报告，仅用于数据统计）
			}
			if ("05".equals(type)) {// 报告签发
				info.setIssue_id(emp.getId());
				info.setIssue_name(emp.getName());
				info.setIssue_Date(fomat.parse(map.get("op_time").toString()));
				info.setIssue_Date2(new Date());	// 签发时间（实际签发日期，不显示于报告，仅用于数据统计）
				info.setIs_issue("1"); 		// 是否已签发（默认否，0：否 1：是）
				info.setSend_status("0");	// 数据当前是否已传输（默认否，0：否 1：是）	
			}
			infoDao.save(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 
     *保存報告配置信息
     * @return
     * @throws Exception
     */
	public void saveItem(Map<String, Object> map) {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User)curUser.getSysUser();
			Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
			
			String id = map.get("id").toString();
			//客运索道预收金额
			if(map.get("fee")!=null&&!"".equals(map.get("fee").toString().trim())){
	    		String fee = map.get("fee").toString();
	    		Double doc = Double.valueOf(fee.toString());
	    		reportItemValueDao.createSQLQuery("update tzsb_inspection_info set advance_fees='"+doc+"'  where id='"+id+"'").executeUpdate();
	    	}
			
			String report_item = map.get("report_item").toString();
			String xsqnum = map.get("xsqnum").toString();
			// 业务表保存報告項目
			infoDao.createSQLQuery("update tzsb_inspection_info set report_item='"+report_item+"',xsqts='"+xsqnum+"' where id='"+id+"'").executeUpdate();
			 
			JSONObject dataMap =JSONObject.fromString(map.get("dataMap").toString());
			
			JSONArray item_page_id;
			JSONArray page_name;
			JSONArray inspect_seq;
			JSONArray audit_seq;
			JSONArray eval_pic_seq;
			JSONArray inspect_man;
			JSONArray inspect_man_id;
			JSONArray audit_man;
			JSONArray audit_man_id;
			JSONArray eval_pic_man;
			JSONArray eval_pic_man_id;
			
			//判断是否有数据
			item_page_id = getArrayByKey(dataMap,"item_page_id");
			page_name = getArrayByKey(dataMap,"page_name");
			inspect_seq = getArrayByKey(dataMap,"inspect_seq");
			audit_seq = getArrayByKey(dataMap,"audit_seq");
			eval_pic_seq = getArrayByKey(dataMap,"eval_pic_seq");
			
			inspect_man = getArrayByKey(dataMap,"page_inspection_op");
			inspect_man_id = getArrayByKey(dataMap,"page_inspection_id");
			audit_man = getArrayByKey(dataMap,"page_check_op");
			audit_man_id = getArrayByKey(dataMap,"page_check_id");
			eval_pic_man = getArrayByKey(dataMap,"page_eval_pic_op");
			eval_pic_man_id = getArrayByKey(dataMap,"page_eval_pic_id");
			 
			 String report_type = dataMap.get("report_type").toString();
			 //判斷檢驗人員是否勾選
			if (inspect_seq != null) {
				for (int i = 0; i < inspect_seq.length(); i++) {
					boolean flag = false;
					String item_id = "";
					String item_name = "INSPECT_MAN_PTR" + inspect_seq.get(i);

					if (inspect_man.length() < 1) {
						continue;
					}
					if (inspect_man_id.length() < 1) {
						continue;
					}
					if (inspect_man.get(i) == null || "".equals(inspect_man.get(i))) {
						perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='" + report_type
								+ "' and fk_inspection_info_id='" + id + "' and item_name='" + item_name + "'")
								.executeUpdate();
						continue;
					}
					if (inspect_man_id.get(i) == null || "".equals(inspect_man_id.get(i))) {
						perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='" + report_type
								+ "' and fk_inspection_info_id='" + id + "' and item_name='" + item_name + "'")
								.executeUpdate();
						continue;
					}

					List<ReportItemValue> list = reportItemValueDao.getItemByItemName(id, report_type, item_name);
					if (list.size() > 0) {
						flag = true;
						item_id = list.get(0).getId();
					}

					String tt = inspect_man.get(i).toString();
					String tt_id = inspect_man_id.get(i).toString();
					if (tt.indexOf("，") != -1) {
						tt = tt.replace("，", ",");
					}
					if (tt_id.indexOf("，") != -1) {
						tt_id = tt_id.replace("，", ",");
					}
					// 判斷報告參數表是否有數據 有就更新數據 沒有就插入新數據
					if (flag) {
						reportItemValueDao.createSQLQuery("update tzsb_report_item_value set fk_report_id='"
								+ report_type + "', fk_inspection_info_id='" + id + "', item_name='" + item_name
								+ "',item_value='" + tt + "',item_type='String' where id='" + item_id + "'")
								.executeUpdate();
					} else {
						ReportItemValue itemValue = new ReportItemValue();
						itemValue.setFk_report_id(report_type);
						itemValue.setFk_inspection_info_id(id);
						itemValue.setItem_name(item_name);
						itemValue.setItem_value(tt);
						itemValue.setItem_type("String");
						reportItemValueDao.save(itemValue);
					}

					// 先刪除報告人員記錄表後再保存
					ReportPerAudit per = new ReportPerAudit();
					perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='" + report_type
							+ "' and fk_inspection_info_id='" + id + "' and item_name='" + item_name + "'")
							.executeUpdate();
					per.setFk_inspection_info_id(id);
					per.setFk_report_id(report_type);
					per.setItem_value(tt);
					per.setItem_name(item_name);
					per.setItem_value_id(tt_id);
					perDao.save(per);
				}
			}
			 
			 //判斷審核人員是否勾選
			 if(audit_seq!=null){
				 for(int i=0;i<audit_seq.length();i++){
					 boolean flag = false;
					 String item_id = "";
					 String item_name = "AUDIT_MAN_PTR"+audit_seq.get(i);
					 
					 
					if(audit_man.length()<1){
							continue;
					}
					if(audit_man_id.length()<1){
							continue;
					}
					if(audit_man.get(i) == null || "".equals(audit_man.get(i))){
						perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='"+report_type+"' and fk_inspection_info_id='"+id+"' and item_name='"+item_name+"'").executeUpdate();
						// 查询报告页单独审核信息
			    		List<ReportPageCheckInfo> reportPageInfoList = 
			    			reportPageCheckInfoDao.queryPage(id, report_type, audit_seq.get(i).toString());
			    		if (reportPageInfoList.size()>0) {
							for (ReportPageCheckInfo pageInfo : reportPageInfoList) {
								// 先删除报告页单独检测审核信息表后再保存
								// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过
								// 2：审核不通过 99：删除）
								pageInfo.setData_status("99");
								pageInfo.setMdy_user_id(emp.getId()); // 最后修改人ID
								pageInfo.setMdy_user_name(emp.getName()); // 最后修改人姓名
								pageInfo.setMdy_date(new Date()); // 最后修改时间
								reportPageCheckInfoDao.save(pageInfo);
							}
						}
						continue;
					}
					if(audit_man_id.get(i) == null || "".equals(audit_man_id.get(i))){
						perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='"+report_type+"' and fk_inspection_info_id='"+id+"' and item_name='"+item_name+"'").executeUpdate();
						// 查询报告页单独审核信息
			    		List<ReportPageCheckInfo> reportPageInfoList = 
			    			reportPageCheckInfoDao.queryPage(id, report_type, audit_seq.get(i).toString());
			    		if (reportPageInfoList.size()>0) {
							for (ReportPageCheckInfo pageInfo : reportPageInfoList) {
								// 先删除报告页单独检测审核信息表后再保存
								// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过
								// 2：审核不通过 99：删除）
								pageInfo.setData_status("99");
								pageInfo.setMdy_user_id(emp.getId()); // 最后修改人ID
								pageInfo.setMdy_user_name(emp.getName()); // 最后修改人姓名
								pageInfo.setMdy_date(new Date()); // 最后修改时间
								reportPageCheckInfoDao.save(pageInfo);
							}
						}
						continue;
					}
					
					 List<ReportItemValue> list  = reportItemValueDao.getItemByItemName(id, report_type, item_name);
					 if(list.size()>0){
						 flag = true;
						 item_id = list.get(0).getId();
					 }
		        	//判斷報告參數表是否有數據 有就更新數據 沒有就插入新數據
		        	if(flag){
		        		reportItemValueDao.createSQLQuery("update tzsb_report_item_value set fk_report_id='"+report_type+"', fk_inspection_info_id='"+id+"', item_name='"+item_name+"',item_value='"+audit_man.get(i).toString()+"',item_type='String' where id='"+item_id+"'").executeUpdate();
		        	}else{
		        		ReportItemValue itemValue = new ReportItemValue();
		        		itemValue.setFk_report_id(report_type);
		        		itemValue.setFk_inspection_info_id(id);
		        		itemValue.setItem_name(item_name);
		        		itemValue.setItem_value(audit_man.get(i).toString());
		        		itemValue.setItem_type("String");
		        		reportItemValueDao.save(itemValue);
		        	}
		        	
		        	//先刪除報告人員記錄表後再保存
		        	ReportPerAudit per =  new ReportPerAudit();
		        	perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='"+report_type+"' and fk_inspection_info_id='"+id+"' and item_name='"+item_name+"'").executeUpdate();
		        	per.setFk_inspection_info_id(id);
		        	per.setFk_report_id(report_type);
		        	per.setItem_value(audit_man.get(i).toString());
		        	per.setItem_name(item_name);
		        	per.setItem_value_id(audit_man_id.get(i).toString());
		        	perDao.save(per);
		        	
		        	HashMap<String, Object> pageInfoMap = new HashMap<String, Object>();
		        	pageInfoMap.put("info_id", id);
		        	pageInfoMap.put("report_type", report_type);
		        	pageInfoMap.put("page_index", audit_seq.get(i).toString());
		        	pageInfoMap.put("page_name", page_name.get(Integer.parseInt(audit_seq.get(i).toString())-1).toString());
		        	pageInfoMap.put("fk_report_item_id", item_page_id.get(Integer.parseInt(audit_seq.get(i).toString())-1).toString());
		        	pageInfoMap.put("audit_user_id", audit_man_id.get(i).toString());
		        	pageInfoMap.put("audit_user_name", audit_man.get(i).toString());
		        	// 查询报告页单独审核信息
		    		List<ReportPageCheckInfo> reportPageInfoList = 
		    			reportPageCheckInfoDao.queryPage(id, report_type, audit_seq.get(i).toString());
		    		if (reportPageInfoList.size()>0) {
		    			for (ReportPageCheckInfo pageInfo : reportPageInfoList) {
							String audit_user_id = pageInfo.getAudit_user_id();
							if (!audit_user_id.equals(audit_man_id.get(i).toString())) {
								// 先删除报告页单独检测审核信息表后再保存
								// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过 2：审核不通过 99：删除）
								pageInfo.setData_status("99");
								pageInfo.setMdy_user_id(emp.getId());		// 最后修改人ID
								pageInfo.setMdy_user_name(emp.getName());	// 最后修改人姓名
								pageInfo.setMdy_date(new Date());			// 最后修改时间
								reportPageCheckInfoDao.save(pageInfo);
									
								saveReportPageCheckInfo(pageInfoMap);
							}else{
								String data_status = pageInfo.getData_status();
								if ("2".equals(data_status)) {
									pageInfo.setData_status("0");	
									reportPageCheckInfoDao.save(pageInfo);
								}
							}
						}
					}else{
						saveReportPageCheckInfo(pageInfoMap);
					}
				 }
			 }
			 
			 //判断评片人员是否勾选
			 if(eval_pic_seq!=null){
				 for(int i=0;i<eval_pic_seq.length();i++){
					if(eval_pic_man.length()<1){
						continue;
					}
					if(eval_pic_man_id.length()<1){
						continue;
					}
					if(eval_pic_man.get(i) == null || "".equals(eval_pic_man.get(i))){
						continue;
					}
					if(eval_pic_man_id.get(i) == null || "".equals(eval_pic_man_id.get(i))){
						continue;
					}
					 
					 boolean flag = false;
					 String item_id = "";
					 String item_name = "EVAL_PIC_MAN_PTR"+eval_pic_seq.get(i);
					 
					 List<ReportItemValue> list  = reportItemValueDao.getItemByItemName(id, report_type, item_name);
					 if(list.size()>0){
						 flag = true;
						 item_id = list.get(0).getId();
					 }
		        	//判斷報告參數表是否有數據 有就更新數據 沒有就插入新數據
		        	if(flag){
		        		reportItemValueDao.createSQLQuery("update tzsb_report_item_value set fk_report_id='"+report_type+"', fk_inspection_info_id='"+id+"', item_name='"+item_name+"',item_value='"+eval_pic_man.get(i).toString()+"',item_type='String' where id='"+item_id+"'").executeUpdate();
		        	}else{
		        		ReportItemValue itemValue = new ReportItemValue();
		        		itemValue.setFk_report_id(report_type);
		        		itemValue.setFk_inspection_info_id(id);
		        		itemValue.setItem_name(item_name);
		        		itemValue.setItem_value(eval_pic_man.get(i).toString());
		        		itemValue.setItem_type("String");
		        		reportItemValueDao.save(itemValue);
		        	}
		        	
		        	//先刪除報告人員記錄表後再保存
		        	ReportPerAudit per =  new ReportPerAudit();
		        	perDao.createSQLQuery("delete  base_reports_per_audit where fk_report_id='"+report_type+"' and fk_inspection_info_id='"+id+"' and item_name='"+item_name+"'").executeUpdate();
		        	per.setFk_inspection_info_id(id);
		        	per.setFk_report_id(report_type);
		        	per.setItem_value(eval_pic_man.get(i).toString());
		        	per.setItem_name(item_name);
		        	per.setItem_value_id(eval_pic_man_id.get(i).toString());
		        	perDao.save(per);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	private void saveReportPageCheckInfo(Map<String, Object> map){
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		ReportPageCheckInfo reportPageCheckInfo =  new ReportPageCheckInfo();
    	reportPageCheckInfo.setFk_inspection_info_id(map.get("info_id").toString());
    	reportPageCheckInfo.setFk_report_id(map.get("report_type").toString());
    	reportPageCheckInfo.setPage_index(map.get("page_index").toString());	// 报告页索引
    	reportPageCheckInfo.setFk_report_item_id(map.get("fk_report_item_id").toString());
    	reportPageCheckInfo.setPage_name(map.get("page_name").toString());
    	reportPageCheckInfo.setAudit_user_id(map.get("audit_user_id").toString());
    	reportPageCheckInfo.setAudit_user_name(map.get("audit_user_name").toString());
    	reportPageCheckInfo.setCreate_user_id(emp.getId()); 
    	reportPageCheckInfo.setCreate_user_name(emp.getName());
    	reportPageCheckInfo.setCreate_date(new Date());	
    	reportPageCheckInfo.setMdy_user_id(emp.getId());		// 最后修改人ID
    	reportPageCheckInfo.setMdy_user_name(emp.getName());	// 最后修改人姓名
    	reportPageCheckInfo.setMdy_date(new Date());			// 最后修改时间
    	reportPageCheckInfo.setData_status("0");	// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过 2：审核不通过 99：删除）
    	reportPageCheckInfoDao.save(reportPageCheckInfo);
	}
	
	public HashMap<String, Object>  reportCopy(Map<String, Object> map) throws Exception
    {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		
		String infoId = map.get("infoId").toString();
		
		String[] originIds = new String[1];
    	if(infoId.indexOf(",")!=-1){
    		originIds = infoId.split(",",0);
    	} else {
    		originIds[0] = infoId;
    	}
		
		String id = map.get("id").toString();
		
		String report_type = map.get("report_type").toString();
		String old_report_type = map.get("old_report_type").toString();
		
		// 查询要复制的报告书内容
		List<ReportItemValue>	list = reportItemValueDao.createQuery("from "
		+ "ReportItemValue where fk_report_id='"+old_report_type+"' and fk_inspection_info_id='"+id+"'").list();
		
		for(int num = 0 ; num < originIds.length ; num++){
			
			
			// 先刪除報告除了报告书编号以为的其他数据
			reportItemValueDao.createSQLQuery("delete tzsb_report_item_value where fk_inspection_info_id='"+originIds[num]+"' and fk_report_id='"+report_type+"' and item_name <> 'REPORT_SN' and item_name <> 'report_sn'").executeUpdate();
			
			// 查询原报告相关业务信息、设备信息
	    	InspectionInfo info = infoDao.get(originIds[num]);
	    	Inspection inspection = info.getInspection();
			DeviceDocument deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
			// 设备类型（1：锅炉 2：压力容器 3：电梯 4：起重机...等）
			String device_type = deviceDocument.getDevice_sort_code().substring(0,1);
			if(StringUtil.isEmpty(device_type)){
				device_type = deviceDocument.getDevice_sort().substring(0,1);
			}
			for (int i = 0; i < list.size(); i++) {
				ReportItemValue item_value = new ReportItemValue();
				// 不复制报告书编号、设备注册代码等
				if ("4".equals(device_type) || "5".equals(device_type)) {
					// 起重机，机电五部2015-07-02要求，只复制检验项目表数据，不复制其他内容
					// 厂车，机电六部彭宇辉2017-06-28要求，只复制检验项目表数据，不复制其他内容
					if (list.get(i).getItem_name().startsWith("DTDQ") || list.get(i).getItem_name().startsWith("QZDJP")
							|| list.get(i).getItem_name().startsWith("JYJGP")
							|| list.get(i).getItem_name().startsWith("JYJLP")
							|| list.get(i).getItem_name().startsWith("BZP")
							|| list.get(i).getItem_name().startsWith("QZP")) {
						item_value.setItem_name(list.get(i).getItem_name());
						item_value.setItem_value(list.get(i).getItem_value());
						item_value.setItem_type(list.get(i).getItem_type());
						item_value.setFk_report_id(info.getReport_type());
						item_value.setFk_inspection_info_id(originIds[num]);
						reportItemValueDao.save(item_value);

						if (list.get(i).getItem_name().equals("INSPECTION_CONCLUSION")) {
							infoDao.createSQLQuery("update tzsb_inspection_info set INSPECTION_CONCLUSION ='"
									+ list.get(i).getItem_value() + "' where id='" + originIds[num] + "'")
									.executeUpdate();
						}
					}
				} /*
					 * else if("5".equals(device_type)){ //
					 * 机电五部刘久林2015-11-23要求，复制厂车报告，不复制制造单位
					 * if(!list.get(i).getItem_name().equals("REPORT_SN") &&
					 * !list.get(i).getItem_name().equals("report_sn") &&
					 * !list.get(i).getItem_name().equals(
					 * "DEVICE_REGISTRATION_CODE") &&
					 * !list.get(i).getItem_name().equals("DEVICE_CODE") &&
					 * !list.get(i).getItem_name().equals(
					 * "INSPECTION_CONFIRM_STR") &&
					 * !list.get(i).getItem_name().equals(
					 * "INSPECTION_AUDIT_STR") &&
					 * !list.get(i).getItem_name().equals("INSPECTION_OP_STR")
					 * && !list.get(i).getItem_name().equals(
					 * "LAST_INSPECTION_DATE") //&&
					 * !item.getStringValue("ITEM_NAME",i).equals(
					 * "INSPECTION_CONCLUSION") &&
					 * !list.get(i).getItem_name().equals("INSPECTION_DATE") &&
					 * !list.get(i).getItem_name().equals("SN") &&
					 * !list.get(i).getItem_name().equals("DESIGN_UNITS_NAME")
					 * && !list.get(i).getItem_name().equals("MAKE_UNITS_NAME")
					 * && !list.get(i).getItem_name().equals("JGHZZH") &&
					 * !list.get(i).getItem_name().equals("JGHZH") &&
					 * !list.get(i).getItem_name().equals("INTERNAL_NUM") &&
					 * !list.get(i).getItem_name().endsWith("#") ) {
					 * item_value.setItem_name(list.get(i).getItem_name());
					 * item_value.setItem_value(list.get(i).getItem_value());
					 * item_value.setItem_type(list.get(i).getItem_type());
					 * item_value.setFk_report_id(info.getReport_type());
					 * item_value.setFk_inspection_info_id(originIds[num]);
					 * reportItemValueDao.save(item_value);
					 * 
					 * if(list.get(i).getItem_name().equals(
					 * "INSPECTION_CONCLUSION")){
					 * 
					 * infoDao.createSQLQuery(
					 * "update tzsb_inspection_info set INSPECTION_CONCLUSION ='"
					 * +list.get(i).getItem_value()+"' where id='"
					 * +originIds[num]+"'").executeUpdate();
					 * 
					 * } } }
					 */ else if ("2".equals(device_type)) {
					// 罐车中心王川2017-03-20要求，罐车的报告改为全复制（日期除外）
					// 罐车属于压力容器，所以此处不另做设备类别判断处理
					if (!list.get(i).getItem_name().equals("REPORT_SN")
							&& !list.get(i).getItem_name().equals("report_sn")
							&& !list.get(i).getItem_name().equals("DEVICE_REGISTRATION_CODE")
							&& !list.get(i).getItem_name().equals("INSPECTION_CONFIRM_STR")
							&& !list.get(i).getItem_name().equals("INSPECTION_AUDIT_STR")
							&& !list.get(i).getItem_name().equals("INSPECTION_OP_STR")
							&& !list.get(i).getItem_name().equals("LAST_INSPECTION_DATE")
							&& !list.get(i).getItem_name().equals("INSPECTION_DATE")
							&& !list.get(i).getItem_name().contains("DATE")
							&& !list.get(i).getItem_name().endsWith("#")) {

						item_value.setItem_name(list.get(i).getItem_name());
						item_value.setItem_value(list.get(i).getItem_value());
						item_value.setItem_type(list.get(i).getItem_type());
						item_value.setFk_report_id(info.getReport_type());
						item_value.setFk_inspection_info_id(originIds[num]);
						reportItemValueDao.save(item_value);

						if (list.get(i).getItem_name().equals("INSPECTION_CONCLUSION")) {
							infoDao.createSQLQuery("update tzsb_inspection_info set INSPECTION_CONCLUSION ='"
									+ list.get(i).getItem_value() + "' where id='" + originIds[num] + "'")
									.executeUpdate();
						}
					}
				} else {
					// 2017-02-13机电三部纪纲提出
					// 使用单位、设备代码、设备型号、出厂编号、单位内编号、设备技术参数栏（层站、载重量、额定速度、控制方式）都不复制
					// 二维码编号、使用登记证号属唯一字段，都不复制
					if (!list.get(i).getItem_name().equals("REPORT_SN")
							&& !list.get(i).getItem_name().equals("report_sn")
							&& !list.get(i).getItem_name().equals("COM_NAME")
							&& !list.get(i).getItem_name().equals("DEVICE_REGISTRATION_CODE")
							&& !list.get(i).getItem_name().equals("DEVICE_CODE")
							&& !list.get(i).getItem_name().equals("DEVICE_QR_CODE")
							&& !list.get(i).getItem_name().equals("INSPECTION_CONFIRM_STR")
							&& !list.get(i).getItem_name().equals("INSPECTION_AUDIT_STR")
							&& !list.get(i).getItem_name().equals("INSPECTION_OP_STR")
							&& !list.get(i).getItem_name().equals("LAST_INSPECTION_DATE")
							// &&
							// !item.getStringValue("ITEM_NAME",i).equals("INSPECTION_CONCLUSION")
							&& !list.get(i).getItem_name().equals("INSPECTION_DATE")
							&& !list.get(i).getItem_name().equals("SN")
							&& !list.get(i).getItem_name().equals("DESIGN_UNITS_NAME")
							&& !list.get(i).getItem_name().equals("JGHZZH")
							&& !list.get(i).getItem_name().equals("JGHZH")
							&& !list.get(i).getItem_name().equals("DEVICE_MODEL")
							&& !list.get(i).getItem_name().equals("FACTORY_CODE")
							&& !list.get(i).getItem_name().equals("INTERNAL_NUM")
							&& !list.get(i).getItem_name().equals("REGISTRATION_NUM")
							&& !list.get(i).getItem_name().equals("P30002005")
							&& !list.get(i).getItem_name().equals("P_DTZS")
							&& !list.get(i).getItem_name().equals("P30002004")
							&& !list.get(i).getItem_name().equals("P30002003")
							&& !list.get(i).getItem_name().equals("P30001001")
							&& !list.get(i).getItem_name().endsWith("#")) {
						// 报告书复制时，所有类型的电梯定检报告，不应复制出厂日期，而保留原有设备的出厂日期。监督检验报告书复制不变
						// 2018-04-28机电二部冯伟提出修改
						if(list.get(i).getItem_name().equals("MAKE_DATE")){
							if("3".equals(device_type) && "3".equals(inspection.getCheck_type())){
								continue;
							}
						}
						item_value.setItem_name(list.get(i).getItem_name());
						item_value.setItem_value(list.get(i).getItem_value());
						item_value.setItem_type(list.get(i).getItem_type());
						item_value.setFk_report_id(info.getReport_type());
						item_value.setFk_inspection_info_id(originIds[num]);
						reportItemValueDao.save(item_value);

						if (list.get(i).getItem_name().equals("INSPECTION_CONCLUSION")) {
							infoDao.createSQLQuery("update tzsb_inspection_info set INSPECTION_CONCLUSION ='"
									+ list.get(i).getItem_value() + "' where id='" + originIds[num] + "'")
									.executeUpdate();
						}
					}
				}
			}
	    	
	    	info.setIs_copy("1"); //打一个标识符 表示这份报告是复制的
	    	//判断是否有报告书编号
////	    	if(info.getReport_sn()==null||"".equals(info.getReport_sn())){
//	    		
//	    		
//	    		CurrentSessionUser user = SecurityUtil.getSecurityUser();
//	    		
//	    		String check_type = info.getInspection().getCheck_type();
//	    		
//	    		DeviceDocument device = deviceDao.get(info.getFk_tsjc_device_document_id());
//	    		
//	    		
//	    		
//	    		try{
//	    			
//	    			String	report_sn = 	reportService.generateReportCode(device.getDevice_sort_code(), check_type, report_type);
//	    			
//	    			info.setReport_sn(report_sn);
//	    			
//	    			infoDao.save(info);
//	    			
//	    		}catch(Exception e){
//	    			e.printStackTrace();
//	    		}
//	    		
//	    		
//	    		
//	    		
//	    	}
//	    	
//	    	
//	    	
	    	
	    	
	    	
	    	
		}
		
		
		resMap.put("success", true);
		
		return resMap;
    }
	 /**
     * 
     * @param hall
     * @param user
     * @param map 前台提供的参数
     * @return
     * @throws Exception
     */
	public HashMap<String, Object>  getDetail(String id)
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
		
		String op_name_ids="";
		String op_name_names="";
		//获取报检信息
		Inspection 	inspection = inspectionDao.get(id);
		//获取设备信息
		String sql="select t.id,t1.device_sort,t1.device_name, t1.device_registration_code, t.report_type,t.advance_time, t.check_op, t.check_tel,t.fk_tsjc_device_document_id,t.is_recheck,t.advance_fees,t.sn,t.is_report_input,t.fk_flow_index_id,t.is_flow,t.flow_note_id,t.flow_note_name,t.check_op_id,t.check_op_name,t.is_back,t.report_sn from tzsb_inspection_info t , base_device_document t1 where t.fk_tsjc_device_document_id = t1.id and t.data_status = '1' and t.is_back='1' and t.fk_inspection_id='"+id+"'";
		List inlist = new ArrayList();
		List list = inspectionDao.createSQLQuery(sql.toString()).list();
		if (list != null && list.size() > 0) {
			
			Object[] obj = list.toArray();
			for (int i = 0; i < obj.length; i++) {
				Object[] oo = (Object[]) obj[i];
				GetInspection getI = new GetInspection();
				getI.setId(oo[0].toString());
				getI.setDevice_sort(oo[1]==null?null:oo[1].toString());
				getI.setDevice_name(oo[2]==null?null:oo[2].toString());
				getI.setDevice_registration_code(oo[3]==null?null:oo[3].toString());
				getI.setReport_type(oo[4]==null?null:oo[4].toString());
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
				getI.setAdvance_time(oo[5] == null ? null : format.parse(oo[5].toString()));
				getI.setCheck_op(oo[6]==null?null:oo[6].toString());
				getI.setCheck_tel(oo[7]==null?null:oo[7].toString());
				getI.setFk_tsjc_device_document_id(oo[8]==null?null:oo[8].toString());	
				getI.setIs_recheck(oo[9]==null?null:oo[9].toString());	
				//getI.setAdvance_fees(Integer.valueOf((oo[10]==null?null:oo[10].toString())));	
				getI.setSn(oo[11]==null?null:oo[11].toString());
				getI.setIs_report_input(oo[12]==null?null:oo[12].toString());	
				getI.setFk_flow_index_id(oo[13]==null?null:oo[13].toString());	
				getI.setIs_flow(oo[14]==null?null:oo[14].toString());	
				getI.setFlow_note_id(oo[15]==null?null:oo[15].toString());	
				getI.setFlow_note_name(oo[16]==null?null:oo[16].toString());
				getI.setCheck_op_id(oo[17]==null?null:oo[17].toString());
				getI.setCheck_op_name(oo[18]==null?null:oo[18].toString());
				getI.setIs_back(oo[19]==null?null:oo[19].toString());
				op_name_ids=oo[17]==null?null:oo[17].toString();
				op_name_names=oo[18]==null?null:oo[18].toString();
				getI.setReport_sn(oo[20]==null?null:oo[20].toString());
				inlist.add(getI);
			}
			;
		}
		map.put("inspectionDatas",inlist);
		map.put("inspection",inspection);
		map.put("op_name_ids", op_name_ids);
		map.put("op_name_names", op_name_names);
		map.put("success", true);
		}catch( Exception e){
			e.printStackTrace();
		}
		return map;
    }
	
	/**
     * 
     * @param 
     * @param user
     * @param map 前台提供的参数
     * @return
     * @throws Exception
     */
	public HashMap<String, Object>  getAllot(String id) throws Exception
    {	
		String ids="";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(id.contains(",")){
			
			String arr[] = id.split(",");
			 for (int i = 0; i < arr.length; i++) {
				 ids+="'"+arr[i]+"',";
			 }
			 ids=ids.substring(0, ids.length()-1);
		}else{
			ids="'"+id+"'";
		}
		
		
		//获取报检信息
		//Inspection 	inspection = inspectionDao.get(id);
		//获取设备信息
		String sql="select t.id,t2.com_name,t2.check_type,t1.device_sort_code,t1.device_name, t1.device_registration_code, t.report_type,t.advance_time, t.fk_tsjc_device_document_id,t.item_op_id,t.check_op_id,t.sn,t.check_unit_id,t.is_report_input from tzsb_inspection_info t , base_device_document t1,tzsb_inspection t2 where t.fk_tsjc_device_document_id = t1.id and t2.id=t.fk_inspection_id /*and t.data_status = '1'*/ and t.id in("+ids+")";
		List inlist = new  ArrayList();
		JSONArray jsons = new JSONArray();
		List list = inspectionDao.createSQLQuery(sql.toString()).list();
		
		if (list != null && list.size() > 0) {
			
			Object[] obj = list.toArray();
			for (int i = 0; i < obj.length; i++) {
				Object[] oo = (Object[]) obj[i];
				GetInspection getI = new GetInspection();
				getI.setId(oo[0].toString());
				getI.setCompany_name(oo[1]==null?null:oo[1].toString());
				getI.setCheck_type(oo[2]==null?null:oo[2].toString());
				
				getI.setDevice_sort(oo[3]==null?null:oo[3].toString());
				getI.setDevice_name(oo[4]==null?null:oo[4].toString());
				getI.setDevice_registration_code(oo[5]==null?null:oo[5].toString());
				getI.setReport_type(oo[6]==null?null:oo[6].toString());
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
				getI.setAdvance_time(oo[7] == null ? null : format.parse(oo[7].toString()));
				getI.setFk_tsjc_device_document_id(oo[8]==null?null:oo[8].toString());	
				getI.setItem_op_id(oo[9]==null?null:oo[9].toString());
				getI.setCheck_op_id(oo[10]==null?null:oo[10].toString());
				getI.setSn(oo[11]==null?null:oo[11].toString());
				
				getI.setCheck_unit_id(oo[11]==null?null:oo[11].toString());
				getI.setIs_report_input(oo[12]==null?null:oo[12].toString());
				inlist.add(getI);
			}
			;
		}
		map.put("inspectionDatas",inlist);
		//map.put("inspection",inspection);
		map.put("success", true);
		return map;
    }
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  flow_reportInput(Map map) throws Exception
    {	
		HashMap<String, Object> listMap = new HashMap<String, Object>();
		//获取业务信息表
		InspectionInfo info = infoDao.get(map.get("id").toString());
		//获取设备信息表
		DeviceDocument doc = deviceDao.get(info.getFk_tsjc_device_document_id());
		//获取报告表
		List<ReportItem>	itemlist = itemDao.createQuery("from ReportItem where fk_reports_id='"+map.get("report_type").toString()+"' order by page_index asc").list();
		//获取报告配置项目表
		List<ReportPerAudit>	perlist = perDao.createQuery("from ReportPerAudit t where t.fk_inspection_info_id='"+map.get("id").toString()+"'").list();
		
		//查询配置页面是否许选择审核、签发人员
		HashMap mapManName = new HashMap();
		HashMap mapManId = new HashMap();
		for(int i = 0 ; i < perlist.size(); i++ ){
			ReportPerAudit item =  perlist.get(i);
			mapManName.put(item.getItem_name(),item.getItem_value());
			mapManId.put(item.getItem_name(),item.getItem_value_id());
		}
		
		//循环剪切报告配置项目
		String[] report_item=new String[1];
		String tt= info.getReport_item();
		//判断是否有值
		if(info.getReport_item()!=null){
			//判断是否有逗号
			if(info.getReport_item().indexOf(",")!=-1){
				report_item = info.getReport_item().split(",");
			}else{
				report_item[0] =tt;
			}
		}
		
		List<SetReportItem>  setList = new ArrayList<SetReportItem>();
		//配置页面显示
		for(int i = 0;i<itemlist.size();i++){
			SetReportItem  item = new SetReportItem();
			item.setItem_page_id(itemlist.get(i).getId());
			//判断页面是否必须选择
			if("1".endsWith(itemlist.get(i).getIs_must())){
				item.setIs_must("yes");
				item.setPage_index(itemlist.get(i).getPage_index());
				item.setIndex_text(itemlist.get(i).getItme_name()+"-------"+itemlist.get(i).getPage_index());
			}else{
				item.setIs_must("no");
				String disSelect="";
				if(report_item!=null){
					for(int j=0;j<report_item.length;j++){
						if(itemlist.get(i).getPage_index().equals(report_item[j])){
							disSelect="checked";
							break;
						}
					}
				}
				item.setIs_disCheck(disSelect);
				item.setPage_index(itemlist.get(i).getPage_index());
				item.setIndex_text(itemlist.get(i).getItme_name()+"-------"+itemlist.get(i).getPage_index());
			}
			//判断是否有检验员
			if("2".endsWith(itemlist.get(i).getIs_inspect_man())){
				item.setIs_inspection("2");
				item.setPage_inspection_op("");
			}else{
				item.setIs_inspection("1");
				item.setPage_inspection_op(mapManName.get("INSPECT_MAN_PTR"+(i+1))==null?"":mapManName.get("INSPECT_MAN_PTR"+(i+1)).toString());
				item.setPage_inspection_id(mapManId.get("INSPECT_MAN_PTR"+(i+1))==null?"":mapManId.get("INSPECT_MAN_PTR"+(i+1)).toString());
				//item.setInspect_seq(i);
				item.setInspect_seq(Integer.valueOf(itemlist.get(i).getPage_index()));
			}
			
			//判断是否有审核人员
			if("2".endsWith(itemlist.get(i).getIs_audit_man())){
				item.setIs_check("2");
				item.setPage_check_op("");
			}else{
				item.setIs_check("1");
				item.setPage_check_op(mapManName.get("AUDIT_MAN_PTR"+(i+1))==null?"":mapManName.get("AUDIT_MAN_PTR"+(i+1)).toString());
				item.setPage_check_id(mapManId.get("AUDIT_MAN_PTR"+(i+1))==null?"":mapManId.get("AUDIT_MAN_PTR"+(i+1)).toString());
				item.setAudit_seq(Integer.valueOf(itemlist.get(i).getPage_index()));
			}
			
			//判断是否有评片人员
			if(StringUtil.isEmpty(itemlist.get(i).getIs_eval_pic_man()) || "2".endsWith(itemlist.get(i).getIs_eval_pic_man())){
				item.setIs_eval_pic("2");
				item.setPage_eval_pic_op("");
			}else{
				item.setIs_eval_pic("1");
				item.setPage_eval_pic_op(mapManName.get("EVAL_PIC_MAN_PTR"+(i+1))==null?"":mapManName.get("EVAL_PIC_MAN_PTR"+(i+1)).toString());
				item.setPage_eval_pic_id(mapManId.get("EVAL_PIC_MAN_PTR"+(i+1))==null?"":mapManId.get("EVAL_PIC_MAN_PTR"+(i+1)).toString());
				item.setEval_pic_seq(Integer.valueOf(itemlist.get(i).getPage_index()));
			}
			item.setPage_name(itemlist.get(i).getItme_name());
			setList.add(item);
		}
		listMap.put("reportItems", setList);
		listMap.put("status", "add");
		listMap.put("advance_fees", info.getAdvance_fees()!=null?info.getAdvance_fees():"0");
		listMap.put("deviceSort", doc.getDevice_sort_code().substring(0, 1));
		listMap.put("success", true);
		return listMap;
    }
	
	
	public HashMap<String, Object>  getPara(String id) throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String sql="select id from TZSB_INSPECTION where fk_hall_para_id='"+id+"'";
		
		List list = inspectionDao.createSQLQuery(sql.toString()).list();
		
		if(list.size()>0){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		
		return map;
    }
	
	public HashMap<String, Object>  getRportIs(String id) throws Exception
    {	
		
		HashMap map = new HashMap();
		 InspectionInfo info = infoDao.get(id);
		
		if(info.getIs_report_input().equals("2")){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		
		return map;
    }
	
	
	public HashMap<String, Object>  getInfo(String id) throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		List list = 	infoDao.createQuery("from InspectionInfo t1,DeviceDocument t2 where  t1.fk_tsjc_device_document_id=t2.id and t1.id='"+id+"'").list();
		
		if(list.size()>0){
			Object[]  obj = (Object[]) list.get(0);
			
			InspectionInfo info =(InspectionInfo) obj[0];
			
			Inspection  inspection = info.getInspection();
			
			DeviceDocument  device =(DeviceDocument) obj[1];
			
			map.put("InspectionInfo",info );
			map.put("Inspection",inspection );
			map.put("DeviceDocument",device );
		}
		map.put("success", true);
		
		return map;
    }
	
	public HashMap<String, Object>  getFlowInfo() throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
	List list = infoDao.createSQLQuery("SELECT  T.ACTIVITY_NAME,subStr(t.TITLE,0, instr(t.TITLE,'-')-1) as flowName,count(t.id) as num, a.activity_id,a.function, b.fk_flow_index_id"
			+ "  FROM V_PUB_WORKTASK T, TZSB_INSPECTION_INFO b,TZSB_INSPECTION T1,flow_activity a where "
			+ " t.SERVICE_ID = b.id and B.FK_INSPECTION_ID=T1.ID and b.export_pdf is null  and b.flow_note_id = a.activity_id and a.id = t.ACTIVITY_ID and T.STATUS='0' and b.data_status<>'99' and t.HANDLER_ID='"+user.getUserId()+"'"
			+ " group by t.ACTIVITY_NAME,  a.function,a.activity_id, b.fk_flow_index_id,subStr(t.TITLE,0, instr(t.TITLE,'-')-1)").list();
		
		list.size();
		
		map.put("flowData", list);
		map.put("success", true);
		
		return map;
    }
	
	public HashMap<String, Object>  getFlowTanker() throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
	List list = infoDao.createSQLQuery("SELECT  T.ACTIVITY_NAME,subStr(t.TITLE,0, instr(t.TITLE,'-')-1) as flowName,count(t.id) as num, a.activity_id,a.function, b.fk_flow_index_id"
			+ "  FROM V_PUB_WORKTASK T, TZSB_INSPECTION_INFO b,TZSB_INSPECTION T1,flow_activity a where "
			+ " t.SERVICE_ID = b.id and B.FK_INSPECTION_ID=T1.ID  and b.export_pdf is null  and b.flow_note_id = a.activity_id and a.id = t.ACTIVITY_ID and T.STATUS='0'  and t.TITLE like '%常压%' and b.data_status<>'99' and t.HANDLER_ID='"+user.getUserId()+"'"
			+ " group by t.ACTIVITY_NAME,  a.function,a.activity_id, b.fk_flow_index_id,subStr(t.TITLE,0, instr(t.TITLE,'-')-1)").list();
		
		list.size();
		
		map.put("flowData", list);
		map.put("success", true);
		
		return map;
    }
	
	// 根据业务ID和流程环节id查询信息
	public String getActivity_id(String info_id, String flow_note_id){
		return inspectionDao.getActivity_id(info_id, flow_note_id);
    }
	
	public HashMap<String, Object>  getFlowStep(String ins_info_id) throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		List<SysLog> list = infoDao.createQuery("  from SysLog where business_id ='"+ins_info_id+"' order by op_time,id asc").list();
		
		list.size();
	
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", infoDao.get(ins_info_id).getSn());
		map.put("success", true);
		
		return map;
    }
	
	
	
	public HashMap<String, Object>  checkCer(Map<String, Object> map) throws Exception
    {

		
		HashMap<String, Object> resmap = new HashMap<String, Object>();
		
		String infoId = map.get("infoId").toString();
		
		String userId = map.get("userId").toString(); 
		
		String report_type = map.get("report_type").toString();  
		
		String pageIndex = map.get("pageIndex").toString(); 
		
		String returns = "ok";
		
		String[] id = new String[1];
		
		if(userId.indexOf(",")!=-1){
			id = userId.split(",",0);
		} else {
			id[0] = userId;
		}
		
		if(!userId.equals("")){
			
			if(pageIndex!=null){
			//获取报告需要的证书
			List list = 	infoDao.createSQLQuery("select t1.cert_code, t3.name, t.sn"
			+ " from tzsb_inspection_info t,base_reports_cert t1,pub_code_table t2,"
			+ "pub_code_table_values t3 where t.report_type = t1.fk_reports_id "
			+ "and t1.cert_code = t3.value and t2.code='BASE_LETTER' and t.id= '"+infoId+"' ").list();
			
			
			
			for(int i = 0 ; i < list.size(); i++){
				if(!returns.equals("ok")){
					break;
				}
				for(int j = 0 ; j < id.length ; j++){
					/************* 2011-11-04 解决证书无结束日期时，应确定为证书有效的问题dengk ******************/
				int count =	infoDao.createSQLQuery("select t.*  from base_role_letter t where t.employee_id = '"+id[j]+"' and t.stutas='1' and t.code = '"+((String[])(list.get(i)))[0]+"' and t.start_date < trunc(sysdate) and (t.end_date >= trunc(sysdate) or t.end_date is null)").executeUpdate() ;
					/************* 2011-11-04 解决证书无结束日期时，应确定为证书有效的问题dengk end ******************/
					
					if(count==0)
					{
						returns = "业务流水号："+((String[])(list.get(i)))[2]+"选择人员需要证书："+((String[])(list.get(i)))[1];
						break;
					}
						
				}
			}
			list.get(0);
			
			}
		}
		
		return resmap;
	
    }
	public HashMap<String, Object>  getDeviceType(String id) throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		List list = 	infoDao.createQuery("from InspectionInfo t1,DeviceDocument t2 where  t1.fk_tsjc_device_document_id=t2.id and t1.id='"+id+"'").list();
		if(list.size()>0){
			Object[]  obj = (Object[]) list.get(0);
			
			
			DeviceDocument  device =(DeviceDocument) obj[1];
			
			
			map.put("device_id",device.getId());
			
			map.put("device_type", device.getDevice_sort_code().substring(0,1));
			
			map.put("success", true);
		}
		
		return map;
    }
	
	
	
	public void delReport(String ids, HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String id[] = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			InspectionInfo info = infoDao.get(id[i]);
			String op_conclusion = "从" + info.getFlow_note_name() + "进入报告作废。";
			
			inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set data_status='99' where id =?", id[i])
					.executeUpdate();
			if (StringUtil.isNotEmpty(info.getReport_sn())) {
				// 根据报告编号获取在用报告业务信息
				List<InspectionInfo> list = inspectionInfoDao.queryByReport_sn(info.getReport_sn());
				boolean can_add = true;
				for(InspectionInfo inspectionInfo : list){
					if(!inspectionInfo.getId().equals(info.getId())){
						can_add = false;
					}
				}
				if(can_add){
					inspectionDao
					.createSQLQuery(
							"insert into  TZSB_DEL_CODE(ID,REPORT_TYPE,REPORT_SN)  VALUES('" + StringUtil.getUUID()
									+ "','" + info.getReport_type() + "','" + info.getReport_sn() + "')")
					.executeUpdate();
				}
			}
			if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
				DeviceDocument deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
				deviceDocument.setIs_cur_check("1"); // 当前是否报检（1：未报检 2：当前报检中）
				deviceDao.save(deviceDocument);
			}
			// 写入日志
			logService.setLogs(id[i], "报告作废", op_conclusion, user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());
		}
	}
		
public void delAccessory(String ids) throws Exception {
		
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				
				InspectionInfo info=infoDao.get(id[i]);
				
				inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set data_status='99' where id =?",info.getInspection().getId() ).executeUpdate();
				

				
			}
			
		}else{
			InspectionInfo info=infoDao.get(ids);
			inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set data_status='99' where id = ?",info.getInspection().getId()).executeUpdate();

		}
		
	}

	
	
	
	public void del(HttpServletRequest request, String ids) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			for(int i=0;i<id.length;i++){
				inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set data_status='2' where id =?",id[i] ).executeUpdate();
				
				inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set data_status='99' where fk_inspection_id =?",id[i] ).executeUpdate();
				
				//inspectionDao.createSQLQuery("update  TZSB_INSPECTION_HALL_PARA  set is_rec='1' where id =?",info.inspection.getFk_hall_para_id() ).executeUpdate();

				List<InspectionInfo> list = inspectionInfoDao.getInspectionInfos(id[i]);
				for (InspectionInfo info : list) {
					if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
						DeviceDocument deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
						deviceDocument.setIs_cur_check("1");	// 当前是否报检（1：未报检 2：当前报检中）
						deviceDao.save(deviceDocument);	
					}
					// 写入日志
					logService.setLogs(info.getId(), "报告作废", "业务报检时删除", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
				}
				
			}
			
		}else{
			inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set data_status='2' where id = ?",ids).executeUpdate();
			inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set data_status='99' where fk_inspection_id =?",ids ).executeUpdate();
			//inspectionDao.createSQLQuery("update  TZSB_INSPECTION_HALL_PARA  set is_rec='1' where id = ?", info.inspection.getFk_hall_para_id()).executeUpdate();
			List<InspectionInfo> list = inspectionInfoDao.getInspectionInfos(ids);
			for (InspectionInfo info : list) {
				if (StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())) {
					DeviceDocument deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
					deviceDocument.setIs_cur_check("1");	// 当前是否报检（1：未报检 2：当前报检中）
					deviceDao.save(deviceDocument);	
				}
				// 写入日志
				logService.setLogs(info.getId(), "报告作废", "业务报检时删除", user.getId(), user
						.getName(), new Date(), request.getRemoteAddr());
			}
		}
		
	}
	
	
	
	public Map<String,Object>  getReportInfo(String ins_info_id, HttpServletRequest request) 
			throws Exception
    {	
		
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			Map<String,Object> returnMap
				= new HashMap<String,Object>();
			//定义变量 begin
			Map<String,Object> infoMap = new HashMap<String,Object>();
			Map<String,Object> comMap = new HashMap<String,Object>();
			Map<String,Object> docMap = new HashMap<String,Object>();
    		Map<String,Object> repMap = new HashMap<String,Object>();
    		Map<String,Object> boilerMap = new HashMap<String,Object>();
    		Map<String,Object> elevatorMap = new HashMap<String,Object>();
    		Map<String,Object> vesselMap = new HashMap<String,Object>();
    		Map<String,Object> craneMap = new HashMap<String,Object>();
    		Map<String,Object> engineMap = new HashMap<String,Object>();
    		Map<String,Object> rideMap = new HashMap<String,Object>();
    		Map<String,Object> supMap  = new HashMap<String,Object>();
    		Map<String,Object> calMap  = new HashMap<String,Object>();
    		Map<String,Object> imgMap  = new HashMap<String,Object>();
    		
			//定义变量 end 
			//获取检验信息已经设备基本信息 begin
//			List basicInfolist = getInspectionBasicInfo(ins_info_id);
			//分三次查询
			
//			Object[] basicInfoObj = (Object[])basicInfolist.get(0);
//			InspectionInfo inspectionInfo = (InspectionInfo)basicInfoObj[0];
//    		DeviceDocument deviceDocument = (DeviceDocument)basicInfoObj[1];
//    		Report report = (Report)basicInfoObj[2];
    		
    		
    		
			InspectionInfo inspectionInfo = infoDao.get(ins_info_id);
			//是否复制报告
			String iscopy = inspectionInfo.getIs_copy();
			
			DeviceDocument deviceDocument = deviceDao.get(inspectionInfo.getFk_tsjc_device_document_id());
			
			EnterInfo  enter = enterDao.get(deviceDocument.getFk_company_info_use_id());
			
			comMap = beanToMap(enter);
			
			// 下次检验日期加一年并减一天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inspectionInfo.getAdvance_time());
			calendar.add(Calendar.YEAR, 1);	
			if (!deviceDocument.getDevice_sort_code().startsWith("9")) {
				//	客运索道（device_sort_code为9开头的4位数字）报告只显示到年月，不需要精确到年月日，故此处不用减去一天
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);	
			}
			inspectionInfo.setLast_check_time(calendar.getTime());
	
			Report report = reportDao.get(inspectionInfo.getReport_type());
			SupervisionInspection  supervision = getSupervision(ins_info_id);
			
    		infoMap = beanToMap(inspectionInfo);
    		docMap = beanToMap(deviceDocument);
    		repMap = beanToMap(report);
    		if(supervision!=null){
    			supMap = beanToMap(supervision);
    		}
    		//获取检验信息已经设备基本信息 end
    		
    		//获取设备参数表信息内容 begin
    		//锅炉参数
    		Collection<BoilerPara> boilers = deviceDocument.boiler ;
    		//虽然是循环，实际上一个设备在参数表中只有一条记录，所以Map对象放在了外层
    		for(BoilerPara boiler : boilers) {
    			
    			boilerMap = beanToMap(boiler);
    		}
    		
    		Collection<CablewayPara> cals = deviceDocument.cableway ;
    		//客运索道
    		for(CablewayPara cal : cals) {
    			//System.out.println(cal.getJbjscs_1());
    			calMap = beanToMap(cal);
    		}
    		
    		//压力容器参数
    		Collection<PressurevesselsPara> vessels = deviceDocument.pressurevessels ;
    		for(PressurevesselsPara vessel : vessels) {
    			vesselMap = beanToMap(vessel);
    		}
    		
    		//电梯参数
    		Collection<ElevatorPara> elevators = deviceDocument.elevatorParas ;
    		for(ElevatorPara elevator : elevators) {
    			elevatorMap = beanToMap(elevator);
    		}
    		
    		//起重参数
    		Collection<CranePara> cranes = deviceDocument.crane ;
    		for(CranePara crane : cranes) {
    			craneMap = beanToMap(crane);
    		}
    		
    		//场内机动车参数
    		Collection<EnginePara> engines = deviceDocument.engine ;
    		for(EnginePara engine : engines) {
    			engineMap = beanToMap(engine);
    		}
    		
    		//游乐设施参数
    		Collection<RidesPara> rides = deviceDocument.ridesPara ;
    		for(RidesPara ride : rides) {
    			rideMap = beanToMap(ride);
    		}
    		//获取设备参数表信息内容 end
    		
    		//获取检验信息报告保存内容 begin
    		List<ReportItemValue> reportItemValueList 
    			= getReportItemValueInfo(ins_info_id,
    				StringUtil.transformNull(inspectionInfo.getReport_type()));
    		
    		//获取检验信息报告保存内容 end
    		
    		// 获取报告页单独检验审核信息表 begin
    		// 获取报告配置项目表
    		Map<String,Object> page_check_imgMap  = new HashMap<String,Object>();	// 报告页单独检验审核人员图片集
    		List<ReportPerAudit> perlist = perDao.createQuery("from ReportPerAudit t where t.fk_inspection_info_id='"+ins_info_id+"' and t.fk_report_id='"+inspectionInfo.getReport_type()+"'").list();
    		for(ReportPerAudit reportPerAudit : perlist){
    			String item_name = reportPerAudit.getItem_name();
    			if (item_name.startsWith("INSPECT_MAN_PTR")) {
					String image_name = "INSPECT_MAN_PICTURE" + item_name.substring("INSPECT_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
    			if (item_name.startsWith("AUDIT_MAN_PTR")) {
					String image_name = "AUDIT_MAN_PICTURE" + item_name.substring("AUDIT_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
    			if (item_name.startsWith("EVAL_PIC_MAN_PTR")) {
					String image_name = "EVAL_PIC_MAN_PICTURE" + item_name.substring("EVAL_PIC_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
    		}
    		// 获取报告页单独检验审核信息表 end 
    		
    		//开始打包返回数据
    		//以下部分为报告模版设置时固定需要的内容
    		Map<String,Object> paraMap = new HashMap<String,Object>();
    		
    		paraMap.put("report_item", inspectionInfo.getReport_item());
    		paraMap.put("total_page", 
    				(inspectionInfo.getReport_item().split(",")).length);
    		paraMap.put("TotalP", 
    				(inspectionInfo.getReport_item().split(",")).length);
    		paraMap.put("report_root_path", report.getRootpath());
    		paraMap.put("report_file", report.getReport_file());
    		paraMap.put("MRDataSet", report.getMrdataset());
    		
    		//放入判断是否复制报告的标识符
    		//System.out.println(iscopy+"="+ins_info_id+"========$$$$$$$$$$$$$$$$$$$$$$$$$$$==================="+infoMap.size());
    		
    		returnMap.put("ISCOPY", iscopy);
    		
    		returnMap.put("INSPECTIONINFO", infoMap);
    		
    		//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&"+infoMap.get("sn"));
    		
    		// 电子签名
    		// 检验人员电子签名
    		imgMap.put("check_op_img", imageTool.getEmployeeImg(inspectionInfo.getCheck_op_id()));
    		// 审核人员电子签名
    		imgMap.put("examine_op_img", imageTool.getEmployeeImg(inspectionInfo.getExamine_id()));
    		// 签发（批准）人员电子签名
    		imgMap.put("issue_op_img", imageTool.getEmployeeImg(inspectionInfo.getIssue_id()));
    		// 编制（录入）人员电子签名
    		imgMap.put("enter_op_img", imageTool.getEmployeeImg(inspectionInfo.getEnter_op_id()));   		
    		
    		
    		
    		/*
    		String type = request.getParameter("type");
    		// input:报告录入 
    		if (type.equals("input")) {
    			// 获取适用当前设备类别、检验类别的可用检验依据start
    			String jyyjStr = "";
        		// 1、获取设备类别
        		String device_sort = deviceDocument.getDevice_sort();	// 二类
    			String device_sort_code = deviceDocument.getDevice_sort_code();	// 三类
    			// 2、获取检验类别
    			String check_type = inspectionInfo.getInspection().getCheck_type();
    			// 3、根据设备类别、检验类别查询可用的检验依据
        		List<QualityXybzFile> jyyjFileList = qualityXybzFileDao.getFileInfos(device_sort, device_sort_code, check_type);
        		// 4、构造检验依据文件名称
        		for(QualityXybzFile file : jyyjFileList){
        			if (jyyjStr.length()>0) {
        				jyyjStr += "、";
    				}
        			jyyjStr += file.getFileName();
        		}
        		returnMap.put("JYYJFILES", jyyjStr);
        		// 获取适用当前设备类别、检验类别的可用检验依据end
        		
        		// 获取检验仪器设备start
        		List<EquipmentBox> boxList = new ArrayList<EquipmentBox>();	// 设备箱列表
        		List<BaseEquipment2> qtEquipmentList = new ArrayList<BaseEquipment2>();	// 其他检验设备列表
        		
        		List<EquipmentLoan> loanList = equipmentLoanDao.getInfos(
        				emp.getId(), emp.getOrg().getId());
        		for(EquipmentLoan equipmentLoan : loanList){
        			if(equipmentLoan.getLoanTime()!=null && equipmentLoan.getRepayTime()!=null){
        				Date loanTime = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", equipmentLoan.getLoanTime()));
            			Date repayTime = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", equipmentLoan.getRepayTime()));
        				if (inspectionInfo.getAdvance_time().after(loanTime) && 
            					inspectionInfo.getAdvance_time().before(repayTime)) {
            				List<EquipmentCentre> list = equipmentCentreDao.getInfos(equipmentLoan.getId());
            				for(EquipmentCentre equipmentCentre : list){
            					if(StringUtil.isNotEmpty(equipmentCentre.getFk_equipment_id())){
            						BaseEquipment2 baseEquipment2 = baseEquipment2Dao.get(equipmentCentre.getFk_equipment_id());
            						qtEquipmentList.add(baseEquipment2);
            					}
            					if(StringUtil.isNotEmpty(equipmentCentre.getFk_box_id())){
            						EquipmentBox equipmentBox = equipmentBoxDao.get(equipmentCentre.getFk_box_id());
            						List<BaseEquipment2> equipmentList = equipmentBox.getBaseEquipment2s();
            						if (equipmentList==null || equipmentList.isEmpty()) {
            							equipmentList = baseEquipment2Dao.getEquipments(equipmentBox.getId());
            							equipmentBox.setBaseEquipment2s(equipmentList);
    								}
            						boxList.add(equipmentBox);
            					}
            				}
    					}
        			}
        			if(equipmentLoan.getLoanTime()!=null && equipmentLoan.getRepayTime()==null){
        				Date loanTime = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", equipmentLoan.getLoanTime()));
        				Date cur_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime());
        				if (inspectionInfo.getAdvance_time().after(loanTime) && 
            					inspectionInfo.getAdvance_time().before(cur_date)) {
            				List<EquipmentCentre> list = equipmentCentreDao.getInfos(equipmentLoan.getId());
            				for(EquipmentCentre equipmentCentre : list){
            					if(StringUtil.isNotEmpty(equipmentCentre.getFk_equipment_id())){
            						BaseEquipment2 baseEquipment2 = baseEquipment2Dao.get(equipmentCentre.getFk_equipment_id());
            						qtEquipmentList.add(baseEquipment2);
            					}
            					if(StringUtil.isNotEmpty(equipmentCentre.getFk_box_id())){
            						EquipmentBox equipmentBox = equipmentBoxDao.get(equipmentCentre.getFk_box_id());
            						List<BaseEquipment2> equipmentList = equipmentBox.getBaseEquipment2s();
            						if (equipmentList==null || equipmentList.isEmpty()) {
            							equipmentList = baseEquipment2Dao.getEquipments(equipmentBox.getId());
            							equipmentBox.setBaseEquipment2s(equipmentList);
    								}
            						boxList.add(equipmentBox);
            					}
            				}
    					}
        			}
        		}
        		returnMap.put("boxList", boxList);
        		returnMap.put("qtEquipmentList", qtEquipmentList);
        		// 获取检验仪器设备end
    		}
    		*/
    		returnMap.put("COMPANY", comMap);
    		returnMap.put("supvision", supMap);
    		returnMap.put("DEVICEDOCUMENT", docMap);
    		returnMap.put("DEVICE_SORT_CODE", deviceDocument.getDevice_sort_code());
    		returnMap.put("BOILERPARA", boilerMap);
    		returnMap.put("VESSELPARA", vesselMap);
    		returnMap.put("ELEVATORPARA", elevatorMap);
    		returnMap.put("CRANEPARA", craneMap);
    		returnMap.put("ENGINEPARA", engineMap);
    		returnMap.put("RIDEPARA", rideMap);
    		returnMap.put("CALPARA", calMap);
    		returnMap.put("REPORT", repMap);
    		returnMap.put("REPORTITEMVALUE", reportItemValueList);
    		returnMap.put("REPORTPARA", paraMap);
    		returnMap.put("IMAGES", imgMap);
    		returnMap.put("PAGE_CHECK_IMAGES", page_check_imgMap);// 报告页单独检验审核人员图片集
    		returnMap.put("PICTURE", reportItemValueService.getPic(ins_info_id));
    		returnMap.put("org_id", inspectionInfo.getCheck_unit_id());
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw e ;
		}
		
		
    }
	
	public Map<String,Object>  getReportPageInfo(String ins_info_id) 
		throws Exception{	
		try {
			Map<String,Object> returnMap
				= new HashMap<String,Object>();
			//定义变量 begin
			Map<String,Object> infoMap = new HashMap<String,Object>();
			Map<String,Object> docMap = new HashMap<String,Object>();
			Map<String,Object> repMap = new HashMap<String,Object>();
			Map<String,Object> boilerMap = new HashMap<String,Object>();
			Map<String,Object> elevatorMap = new HashMap<String,Object>();
			Map<String,Object> vesselMap = new HashMap<String,Object>();
			Map<String,Object> craneMap = new HashMap<String,Object>();
			Map<String,Object> engineMap = new HashMap<String,Object>();
			Map<String,Object> rideMap = new HashMap<String,Object>();
			Map<String,Object> supMap  = new HashMap<String,Object>();
			Map<String,Object> calMap  = new HashMap<String,Object>();
			Map<String,Object> imgMap  = new HashMap<String,Object>();
			
			//定义变量 end 
			//获取检验信息已经设备基本信息 begin
			InspectionInfo inspectionInfo = infoDao.get(ins_info_id);
			//是否复制报告
			String iscopy = inspectionInfo.getIs_copy();
			
			DeviceDocument deviceDocument = deviceDao.get(inspectionInfo.getFk_tsjc_device_document_id());
			
			// 下次检验日期加一年并减一天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inspectionInfo.getAdvance_time());
			calendar.add(Calendar.YEAR, 1);	
			if (!deviceDocument.getDevice_sort_code().startsWith("9")) {
				//	客运索道（device_sort_code为9开头的4位数字）报告只显示到年月，不需要精确到年月日，故此处不用减去一天
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);	
			}
			inspectionInfo.setLast_check_time(calendar.getTime());
		
			Report report = reportDao.get(inspectionInfo.getReport_type());
			SupervisionInspection  supervision = getSupervision(ins_info_id);
			
			infoMap = beanToMap(inspectionInfo);
			docMap = beanToMap(deviceDocument);
			repMap = beanToMap(report);
			if(supervision!=null){
				supMap = beanToMap(supervision);
			}
			//获取检验信息已经设备基本信息 end
			
			//获取设备参数表信息内容 begin
			//锅炉参数
			Collection<BoilerPara> boilers = deviceDocument.boiler ;
			//虽然是循环，实际上一个设备在参数表中只有一条记录，所以Map对象放在了外层
			for(BoilerPara boiler : boilers) {
				
				boilerMap = beanToMap(boiler);
			}
			
			Collection<CablewayPara> cals = deviceDocument.cableway ;
			//客运索道
			for(CablewayPara cal : cals) {
				//System.out.println(cal.getJbjscs_1());
				calMap = beanToMap(cal);
			}
			
			//压力容器参数
			Collection<PressurevesselsPara> vessels = deviceDocument.pressurevessels ;
			for(PressurevesselsPara vessel : vessels) {
				vesselMap = beanToMap(vessel);
			}
			
			//电梯参数
			Collection<ElevatorPara> elevators = deviceDocument.elevatorParas ;
			for(ElevatorPara elevator : elevators) {
				elevatorMap = beanToMap(elevator);
			}
			
			//起重参数
			Collection<CranePara> cranes = deviceDocument.crane ;
			for(CranePara crane : cranes) {
				craneMap = beanToMap(crane);
			}
			
			//场内机动车参数
			Collection<EnginePara> engines = deviceDocument.engine ;
			for(EnginePara engine : engines) {
				engineMap = beanToMap(engine);
			}
			
			//游乐设施参数
			Collection<RidesPara> rides = deviceDocument.ridesPara ;
			for(RidesPara ride : rides) {
				rideMap = beanToMap(ride);
			}
			//获取设备参数表信息内容 end
			
			//获取检验信息报告保存内容 begin
			List<ReportItemValue> reportItemValueList 
				= getReportItemValueInfo(ins_info_id,
					StringUtil.transformNull(inspectionInfo.getReport_type()));			
			//获取检验信息报告保存内容 end
			
			// 获取报告页单独检验审核参数值信息表 begin
			// 获取报告配置项目表
			Map<String,Object> page_check_imgMap  = new HashMap<String,Object>();	// 报告页单独检验审核人员图片集
			List<ReportPerAudit> perlist = perDao.createQuery("from ReportPerAudit t where t.fk_inspection_info_id='"+ins_info_id+"' and t.fk_report_id='"+inspectionInfo.getReport_type()+"'").list();
			for(ReportPerAudit reportPerAudit : perlist){
				String item_name = reportPerAudit.getItem_name();
				if (item_name.startsWith("INSPECT_MAN_PTR")) {
					String image_name = "INSPECT_MAN_PICTURE" + item_name.substring("INSPECT_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
				if (item_name.startsWith("AUDIT_MAN_PTR")) {
					String image_name = "AUDIT_MAN_PICTURE" + item_name.substring("AUDIT_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
				if (item_name.startsWith("EVAL_PIC_MAN_PTR")) {
					String image_name = "EVAL_PIC_MAN_PICTURE" + item_name.substring("EVAL_PIC_MAN_PTR".length());
					page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
				}
			}
			// 获取报告页单独检验审核参数值信息表 end 
			// 获取报告页单独审核信息表 begin
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User)curUser.getSysUser();
			Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
			//Org org = TS_Util.getCurOrg(curUser);
			
			String report_item = "";
			List<String> pageIndexList = reportPageCheckInfoDao.queryPageInfo(
					ins_info_id, inspectionInfo.getReport_type(), emp.getId()); 
			for (int i = 0; i < pageIndexList.size(); i++) {
				if(report_item.length()>0){
					report_item += ",";
				}
				report_item += pageIndexList.get(i);
			}
			
			// 获取报告页单独审核信息表 end
			
			//开始打包返回数据
			//以下部分为报告模版设置时固定需要的内容
			Map<String,Object> paraMap = new HashMap<String,Object>();
			
			paraMap.put("report_item", report_item);
			paraMap.put("total_page", 
					(report_item.split(",")).length);
			paraMap.put("TotalP", 
					(inspectionInfo.getReport_item().split(",")).length);
			paraMap.put("report_root_path", report.getRootpath());
			paraMap.put("report_file", report.getReport_file());
			paraMap.put("MRDataSet", report.getMrdataset());
			
			//放入判断是否复制报告的标识符
			//System.out.println(iscopy+"="+ins_info_id+"========$$$$$$$$$$$$$$$$$$$$$$$$$$$==================="+infoMap.size());
			
			returnMap.put("ISCOPY", iscopy);
			
			returnMap.put("INSPECTIONINFO", infoMap);
			
			//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&"+infoMap.get("sn"));
			
			// 电子签名
			// 检验人员电子签名
			imgMap.put("check_op_img", imageTool.getEmployeeImg(inspectionInfo.getCheck_op_id()));
			// 审核人员电子签名
			imgMap.put("examine_op_img", imageTool.getEmployeeImg(inspectionInfo.getExamine_id()));
			// 签发（批准）人员电子签名
			imgMap.put("issue_op_img", imageTool.getEmployeeImg(inspectionInfo.getIssue_id()));
			// 编制（录入）人员电子签名
			imgMap.put("enter_op_img", imageTool.getEmployeeImg(inspectionInfo.getEnter_op_id()));   		
			
			returnMap.put("supvision", supMap);
			returnMap.put("DEVICEDOCUMENT", docMap);
			returnMap.put("BOILERPARA", boilerMap);
			returnMap.put("VESSELPARA", vesselMap);
			returnMap.put("ELEVATORPARA", elevatorMap);
			returnMap.put("CRANEPARA", craneMap);
			returnMap.put("ENGINEPARA", engineMap);
			returnMap.put("RIDEPARA", rideMap);
			returnMap.put("CALPARA", calMap);
			returnMap.put("REPORT", repMap);
			returnMap.put("REPORTITEMVALUE", reportItemValueList);
			returnMap.put("REPORTPARA", paraMap);
			returnMap.put("IMAGES", imgMap);
			returnMap.put("PAGE_CHECK_IMAGES", page_check_imgMap);// 报告页单独检验审核人员图片集
			returnMap.put("PICTURE", reportItemValueService.getPic(ins_info_id));
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw e ;
		}
	}
	
	//获取检验信息已经设备基本信息
	public List getInspectionBasicInfo(String ins_info_id) throws Exception {
		try{
//			String sql=" from InspectionInfo info,DeviceDocument doc,Report rp"
//				+" where info.fk_tsjc_device_document_id = doc.id "
//				+" and info.report_type = rp.id and info.id=?";
			String sql = " from InspectionInfo info left join DeviceDocument doc on info.fk_tsjc_device_document_id = doc.id " +
					" left join Report rp on info.report_type = rp.id left join SupervisionInspection sup on info.id= sup.fk_inspection_info_id and info.id=?";
			List list = inspectionDao
					.createQuery(sql,new String[]{ins_info_id}).list();
			return list ;
		} catch (Exception e) {
			throw e ;
		}
	}
	
	//获取检验信息基本信息
	public SupervisionInspection getSupervision(String ins_info_id) throws Exception {
		
		List<SupervisionInspection> list=infoDao.createQuery("from SupervisionInspection where fk_inspection_info_id='"+ins_info_id+"'").list();
		
		if(list.size()>0){
			
			return list.get(0);
		}else{
		
			return null;		
		}
	}
	
	public List getReportItemValueInfo(String ins_info_id, String report_type) 
			throws Exception {
		try{
			String sql=" from ReportItemValue where fk_inspection_info_id=?"
				+ " and fk_report_id=? ";
			List list = inspectionDao
				.createQuery(sql,new String[]{ins_info_id,report_type}).list();
			return list ;
		} catch (Exception e) {
			throw e ;
		}
	}
	
	
	
	
	
	/**
	 * @author 廖增伟
	 * @param PostBackValue
	 * @param report_type
	 * @param ins_info_id
	 * @throws Exception 
	 */
	public void PostBack(String[] PostBackValue, MREngine engine, HttpServletRequest request,
			HttpServletResponse response) throws KhntException {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		//Org org = TS_Util.getCurOrg(curUser);
		try {
			String ins_info_id = request.getParameter("ins_info_id");
			String device_id = request.getParameter("device_id");
			String report_type = request.getParameter("report_type");
			// 根据设备ID获取设备基础信息bean
			DeviceDocument devicedoc = deviceDao.get(device_id);
			// 根据业务信息ID获取业务信息bean
			InspectionInfo insInfo = inspectionInfoDao.get(ins_info_id);
			// 获取检验类型
			String check_type = insInfo.getInspection().getCheck_type();
			insInfo.setCheck_category_code(check_type);
			insInfo.setCheck_category_name(codeTablesDao.getValueByCode("BASE_CHECK_TYPE", check_type));
			// 获取设备类型
			String device_type = devicedoc.getDevice_sort_code();
			// 为每个参数表设置一个字段名的集合
			Set<String> _boilerMap = new HashSet<String>();
			Set<String> _elevatorMap = new HashSet<String>();
			Set<String> _vesselMap = new HashSet<String>();
			Set<String> _craneMap = new HashSet<String>();
			Set<String> _engineMap = new HashSet<String>();
			Set<String> _rideMap = new HashSet<String>();
			Set<String> _accMap = new HashSet<String>();
			Set<String> _docMap = new HashSet<String>();
			Set<String> _calMap = new HashSet<String>();

			// 将bean字段放入相应集合
			_boilerMap = beanFieldSet(BoilerPara.class);
			_elevatorMap = beanFieldSet(ElevatorPara.class);
			_vesselMap = beanFieldSet(PressurevesselsPara.class);
			_craneMap = beanFieldSet(CranePara.class);
			_engineMap = beanFieldSet(EnginePara.class);
			_accMap = beanFieldSet(Accessory.class);
			_rideMap = beanFieldSet(RidesPara.class);
			//_docMap = beanFieldSet(DeviceDocument.class);
			_docMap = DeviceDocument.bean_to_set();
			_calMap = beanFieldSet(CablewayPara.class);
			// 定义设备类型标志位
			boolean b_boiler = false;
			boolean b_vessel = false;
			boolean b_elevator = false;
			boolean b_crane = false;
			boolean b_engine = false;
			boolean b_acc = false;
			boolean b_ride = false;
			boolean b_cal = false;
			// 定义设备单数bean
			BoilerPara p_boiler = null;
			PressurevesselsPara p_vessel = null;
			ElevatorPara p_elevator = null;
			CranePara p_crane = null;
			EnginePara p_engine = null;
			RidesPara p_ride = null;
			Accessory p_acc = null;
			CablewayPara p_cal = null;
			// DeviceDocument p_doc = null ;
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("1")) {
				Collection<BoilerPara> c = devicedoc.getBoiler();
				for (BoilerPara it : c) {
					p_boiler = boilerParaDao.get(it.getId());
				}
				b_boiler = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("2")) {
				Collection<PressurevesselsPara> c = devicedoc.getPressurevessels();
				for (PressurevesselsPara it : c) {
					p_vessel = pressurevesselsParaDao.get(it.getId());
				}
				b_vessel = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("3")) {
				Collection<ElevatorPara> c = devicedoc.getElevatorParas();
				for (ElevatorPara it : c) {
					p_elevator = elevatorParaDao.get(it.getId());
				}
				b_elevator = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("4")) {
				Collection<CranePara> c = devicedoc.getCrane();
				for (CranePara it : c) {
					p_crane = craneParaDao.get(it.getId());
				}
				b_crane = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("5")) {
				Collection<EnginePara> c = devicedoc.getEngine();
				for (EnginePara it : c) {
					p_engine = engineParaDao.get(it.getId());
				}
				b_engine = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("6")) {
				Collection<RidesPara> c = devicedoc.getRidesPara();
				for (RidesPara it : c) {
					p_ride = ridesParaDao.get(it.getId());
				}
				b_ride = true;
			}
			// 根据设备类型获得相应的参数表bean
			if (devicedoc.getDevice_sort_code().startsWith("9")) {
				Collection<CablewayPara> c = devicedoc.getCableway();
				for (CablewayPara it : c) {
					p_cal = calDao.get(it.getId());
				}
				b_cal = true;
			}

			if (devicedoc.getDevice_sort_code().startsWith("Q")) {
				Collection<Accessory> c = devicedoc.getAccessory();
				for (Accessory it : c) {
					p_acc = accDao.get(it.getId());
				}
				b_acc = true;
			}
			// 首先删除删除tzsb_report_item_value表中之前保存的数据
			// 报告书编号，额外每页单独选择的检验员、审核人不删除
			String DelSql = "delete from tzsb_report_item_value where "
					// +" fk_inspection_info_id = '"+ins_info_id+"' "
					+ " fk_inspection_info_id = ? "
					// ---+" and fk_report_id = ? "
					+ " and upper(item_name) <> 'REPORT_SN' ";
			// inspectionDao.createSQLQuery(DelSql).
			inspectionDao.createSQLQuery(DelSql, ins_info_id).
			// new String[]{ins_info_id}).
			// ins_info_id).
					executeUpdate();

			String device_model = ""; // 设备型式
			int dtcs = 0; // 电梯层数
			double sdxc = 0; // 索道斜长（带小数）
			int ppc_zcsl = 0; // 碰碰车座舱数量
			int xhc_czrs = 0; // 小火车乘座人数
			int sc_clzs = 0; // 赛车车辆总数
			double qz_q = 0; // 起重量
			double qz_lj = 0; // 起重力矩
			//double qzkd = 0; // 起重机械跨度

			String enter_time = ""; // 录入时间
			String inspection_date = ""; // 检验日期
			String year = "";
			String month = "";

			String inspection_conclusion = ""; // 检验结论
			String device_qr_code = "";			// 设备二维码编号
			String check_unit_id = insInfo.getCheck_unit_id(); // 检验部门

			// 开始处理报表内容数据
			for (int i = 0; i < PostBackValue.length; i++) {
				// 如果为空则不处理
				if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))) {
					// 将报检单位写入检验业务信息表
					if (PostBackValue[i].toUpperCase().equals("COM_NAME")) {
						insInfo.setReport_com_name(engine.getParameter(PostBackValue[i]));
					}
					// 将报检单位地址写入业务信息表和设备主表
					if (PostBackValue[i].toUpperCase().equals("COM_ADDRESS")) {
						insInfo.setReport_com_address(engine.getParameter(PostBackValue[i]));
						devicedoc.setUse_site_address(engine.getParameter(PostBackValue[i]));
					}
					// 将检验联系人、联系人电话写入业务信息表
					if (PostBackValue[i].toUpperCase().equals("CHECK_OP")) {
						insInfo.setCheck_op(engine.getParameter(PostBackValue[i]));
					}
					
					// 返写设备安全管理员
					if (PostBackValue[i].toUpperCase().equals("SECURITY_OP")) {
						if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))) {
							devicedoc.setSecurity_op(engine.getParameter(PostBackValue[i]));
							insInfo.setSecurity_op(engine.getParameter(PostBackValue[i]));
						}
					}
					
					// 返写设备安全管理员联系电话
					if (PostBackValue[i].toUpperCase().equals("SECURITY_TEL")) {
						if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))) {
							devicedoc.setSecurity_tel(engine.getParameter(PostBackValue[i]));
							insInfo.setSecurity_tel(engine.getParameter(PostBackValue[i]));
						}
					}
					
					// 2018-03-13应张展彬要求增加
					// 返写设备注册代码到info业务表，以供报告查询
					if (PostBackValue[i].toUpperCase().equals("DEVICE_REGISTRATION_CODE")) {
						if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))) {
							insInfo.setDevice_registration_code(engine.getParameter(PostBackValue[i]));
						}
					}
					// 返写设备代码
					if (PostBackValue[i].toUpperCase().equals("DEVICE_CODE")) {
						if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))) {
							devicedoc.setDevice_code(engine.getParameter(PostBackValue[i]));
						}
					}
					// 返写联系电话
					if (PostBackValue[i].toUpperCase().equals("CHECK_TEL")) {
						insInfo.setCheck_tel(engine.getParameter(PostBackValue[i]));
					}
					
					// 将维保单位返写报告业务信息表
					if (PostBackValue[i].toUpperCase().equals("MAINTAIN_UNIT_NAME")) {
						insInfo.setMaintain_unit_name(engine.getParameter(PostBackValue[i]));
					}
					// 将制造单位返写报告业务信息表
					if (PostBackValue[i].toUpperCase().equals("MAKE_UNITS_NAME")) {
						insInfo.setMake_units_name(engine.getParameter(PostBackValue[i]));
					}
					// 将安装/施工单位返写报告业务信息表
					if (PostBackValue[i].toUpperCase().equals("CONSTRUCTION_UNITS_NAME")) {
						insInfo.setConstruction_units_name(engine.getParameter(PostBackValue[i]));
					}
					
					// 将维保单位联系人、电话写入设备信息表
					if (PostBackValue[i].equals("MAINTENANCE_MAN")) {
						devicedoc.setMaintenance_man(engine.getParameter(PostBackValue[i]));
					}
					if (PostBackValue[i].toUpperCase().equals("MAINTENANCE_TEL")) {
						devicedoc.setMaintenance_tel(engine.getParameter(PostBackValue[i]));
					}
					
					// 返写检验类别1
					if (PostBackValue[i].equals("SGLB")) {	// SGLB：施工类别（监检）
						if(device_type.startsWith("4")){	// 4：起重机
							insInfo.setCheck_type_code(engine.getParameter(PostBackValue[i]));
							if("1".equals(engine.getParameter(PostBackValue[i]))){
								insInfo.setCheck_type_name("新装");
							}else if("2".equals(engine.getParameter(PostBackValue[i]))){
								insInfo.setCheck_type_name("移装");
							}else if("3".equals(engine.getParameter(PostBackValue[i]))){
								insInfo.setCheck_type_name("改造");
							}else if("4".equals(engine.getParameter(PostBackValue[i]))){
								insInfo.setCheck_type_name("重大修理");
							}
						}else if(device_type.startsWith("3")){// 3：电梯（安装、改造、修理）
							insInfo.setCheck_type_code(engine.getParameter(PostBackValue[i]));
							insInfo.setCheck_type_name(engine.getParameter(PostBackValue[i]));
						}
					}

					// 返写检验类别2
					if (PostBackValue[i].equals("CHECK_TYPE")) {// CHECK_TYPE：检验类别（定期、首次）
						if(device_type.startsWith("4")){		// 4：起重机
							insInfo.setCheck_type_code(engine.getParameter(PostBackValue[i]));
							insInfo.setCheck_type_name(engine.getParameter(PostBackValue[i]));
						}
					}
					
					// 检验结论写入到主表中和业务信息表
					if (PostBackValue[i].toUpperCase().equals("INSPECTION_CONCLUSION")) {
						devicedoc.setInspect_conclusion(engine.getParameter(PostBackValue[i]));
						insInfo.setInspection_conclusion(engine.getParameter(PostBackValue[i]));

						// conlustion
						// = engine.getParameter(PostBackValue[i]);
						// 如果下次检验日期为“/则往设备信息主表和检验记录表里保存空
						// if(conlustion.equals("不合格")){
						// insInfo
						// .setLast_check_time("");
						// }
						//
						inspection_conclusion = engine.getParameter(PostBackValue[i]);// 获取检验结论
					}
					// 将电梯二维码编号写入业务信息表，签发后写入设备信息表
					if (PostBackValue[i].equals("DEVICE_QR_CODE")) {
						if(engine.getParameter(PostBackValue[i])!=null){
							insInfo.setDevice_qr_code(engine.getParameter(PostBackValue[i]).trim());
						}else{
							insInfo.setDevice_qr_code("");
						}
					}
					// 将电梯使用登记证号写入设备信息表
					if (PostBackValue[i].equals("REGISTRATION_NUM")) {
						if(engine.getParameter(PostBackValue[i])!=null){
							devicedoc.setRegistration_num(engine.getParameter(PostBackValue[i]).trim());
							insInfo.setRegistration_num(engine.getParameter(PostBackValue[i]).trim());
						}else{
							devicedoc.setRegistration_num("");
							insInfo.setRegistration_num("");
						}
					}
					// 常压罐车检验结论特殊处理
					if ("2610".equals(device_type)) {
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT1")) {
							if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))
									&& "√".equals(engine.getParameter(PostBackValue[i]))) {
								devicedoc.setInspect_conclusion("允许使用");
								insInfo.setInspection_conclusion("允许使用");
							}
						}
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT3")) {
							if (StringUtil.isNotEmpty(PostBackValue[i]) && "√".equals(PostBackValue[i])) {
								devicedoc.setInspect_conclusion("停止使用");
								insInfo.setInspection_conclusion("停止使用");
							}
						}
					}

					// 汽车罐车、压力容器检验结论（非合格、不合格）特殊处理
					if (devicedoc.getDevice_sort().startsWith("2") || devicedoc.getDevice_sort().startsWith("1")) {
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT1")) {
							if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))
									&& "√".equals(engine.getParameter(PostBackValue[i]))) {
								devicedoc.setInspect_conclusion("符合要求");
								insInfo.setInspection_conclusion("符合要求");
							}
						}
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT2")) {
							if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))
									&& "√".equals(engine.getParameter(PostBackValue[i]))) {
								devicedoc.setInspect_conclusion("基本符合要求");
								insInfo.setInspection_conclusion("基本符合要求");
							}
						}
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT3")) {
							if (StringUtil.isNotEmpty(PostBackValue[i]) && "√".equals(PostBackValue[i])) {
								devicedoc.setInspect_conclusion("不符合要求");
								insInfo.setInspection_conclusion("不符合要求");
							}
						}
					}

					// 超高压压力容器检验结论（非合格、不合格）特殊处理
					if ("2110".equals(device_type)) {
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT1")) {
							if (StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))
									&& "√".equals(engine.getParameter(PostBackValue[i]))) {
								devicedoc.setInspect_conclusion("继续使用");
								insInfo.setInspection_conclusion("继续使用");
							}
						}
						if (PostBackValue[i].toUpperCase().equals("JYJL_SELECT3")) {
							if (StringUtil.isNotEmpty(PostBackValue[i]) && "√".equals(PostBackValue[i])) {
								devicedoc.setInspect_conclusion("判废");
								insInfo.setInspection_conclusion("判废");
							}
						}
					}

					// 判断不合格报告 下次检验日期为‘/’ ，不写入主表和设备表只保存报表表
					//
					// if
					// (PostBackValue[i].toUpperCase().equals("INSPECTION_CONCLUSION"))
					// {
					// String conlustion
					// = engine.getParameter(PostBackValue[i]);
					// //如果下次检验日期为“/则往设备信息主表和检验记录表里保存空
					// if(conlustion.equals("不合格")){
					//
					// }
					// if(!"".equals(last_check_time)){
					// SimpleDateFormat format
					// = new SimpleDateFormat("yyyy-MM-dd");
					//
					// Date date = format.parse(
					// (last_check_time.replace("/", "-")).toString());
					// insInfo
					// .setLast_check_time(date);
					//
					// }
					// }

					// 下次检验时间写入业务信息表
					// （主表在领导确认报告合格之后某一个环节写入）
					if (PostBackValue[i].toUpperCase().equals("LAST_INSPECTION_DATE")) {
						String last_check_time = engine.getParameter(PostBackValue[i]);
						// 如果下次检验日期为“/则往设备信息主表和检验记录表里保存空
						if (last_check_time.equals("/") || last_check_time.equals("")
								|| last_check_time.equals("待受检单位申请后确定") || inspection_conclusion.equals("不合格")) {
							last_check_time = "";
						}
						if (!"".equals(last_check_time)) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

							Date date = format.parse((last_check_time.replace("/", "-")).toString());
							insInfo.setLast_check_time(date);

						}
					}

					// 起重机下次检验日期特殊处理（因业务需要，起重机下次检验日期报告中只精确到年月）
					if (PostBackValue[i].toUpperCase().equals("LAST_INSPECTION_DATE_Y")) {
						if(StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))){
							year = engine.getParameter(PostBackValue[i]);
						}
					}
					if (PostBackValue[i].toUpperCase().equals("LAST_INSPECTION_DATE_M")) {
						if(StringUtil.isNotEmpty(engine.getParameter(PostBackValue[i]))){
							month = engine.getParameter(PostBackValue[i]);
						}
					}
					if (device_type.startsWith("4")) {
						// 起重取参数

						// 起重量
						if (PostBackValue[i].toUpperCase().equals("P40002004")) {
							try {
								qz_q = new Double(engine.getParameter(PostBackValue[i]));
							} catch (Exception e) {
								qz_q = 0;
							}

						}
						// 起重力矩
						if (PostBackValue[i].toUpperCase().equals("P40002005")) {
							try {
								qz_lj = new Double(engine.getParameter(PostBackValue[i]));
							} catch (Exception e) {
								qz_lj = 0;
							}

						}

						// 起重跨度
						/*
						 * if (PostBackValue[i].toUpperCase().equals("P_KD")) { try { qzkd = new
						 * Double(engine.getParameter(PostBackValue[i])); } catch (Exception e) { qzkd =
						 * 0; } }
						 */
					}
					
					if (StringUtil.isNotEmpty(year) && StringUtil.isNotEmpty(month)) {
						if("/".equals(year.trim()) && "/".equals(month.trim())){
							insInfo.setLast_check_time(null);
							devicedoc.setInspect_next_date(null);
						}else{
							String last_day = TS_Util.getLastDayOfMonth(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()));
							String str = year.trim() + "-" + month.trim() + (StringUtil.isNotEmpty(last_day)?"-"+last_day:"-28");
							insInfo.setLast_check_time(
									DateUtil.convertStringToDate("yyyy-MM-dd", str));
							devicedoc.setInspect_next_date(
									DateUtil.convertStringToDate("yyyy-MM-dd", str));
						}
					}

					// 客运索道的下次检验日期
					if (PostBackValue[i].toUpperCase().equals("KY_LAST_INSPECTION_DATE")) {
						String last_check_time = engine.getParameter(PostBackValue[i]);
						// 如果下次检验日期为“/则往设备信息主表和检验记录表里保存空
						if (last_check_time.equals("/") || last_check_time.equals("")
								|| last_check_time.equals("待受检单位申请后确定")) {
							last_check_time = "";
						}
						if (!"".equals(last_check_time)) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							String[] date_arr = last_check_time.split("-");
							if (date_arr.length == 2) {
								last_check_time += "-01"; // 客运索道报告，下次检验日期只显示年月，但返现检验业务信息时，需要精确到年月日（默认为月初1号）
							}
							Date date = format.parse((last_check_time.replace("/", "-")).toString());
							insInfo.setLast_check_time(date);

						}
					}
					// 编制日期写入业务信息表
					// （主表在领导确认报告合格之后某一个环节写入）
					if (PostBackValue[i].toUpperCase().equals("DRAFT_DATE")) {
						enter_time = engine.getParameter(PostBackValue[i]);
						// 如果编制日期为“/则往设备信息主表和检验记录表里保存空
						if (enter_time.equals("/") || enter_time.equals("") || enter_time.equals("待受检单位申请后确定")) {
							enter_time = "";
						}
						if (!"".equals(enter_time)) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

							Date date = format.parse((enter_time.replace("/", "-")).toString());
							insInfo.setEnter_time(date);

						}
					}

					// 如果安装日期不为空，则回写到设备信息表
					if (PostBackValue[i].toUpperCase().equals("INSTALL_FINISH_DATE")) {
						if (!engine.getParameter(PostBackValue[i]).equals("")) {
							String install_date = engine.getParameter(PostBackValue[i]);
							devicedoc.setInstall_finish_date(install_date);
						}
					}

					// 如果检验时间不为空，则回写如业务信息表
					if (PostBackValue[i].toUpperCase().equals("INSPECTION_DATE")) {
						if (!engine.getParameter(PostBackValue[i]).equals("")) {

							String check_date = engine.getParameter(PostBackValue[i]);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							inspection_date = check_date;
							insInfo.setAdvance_time(format.parse(check_date));
						}
					}

					if (PostBackValue[i].toUpperCase().equals("REPORT_SN")) {

						insInfo.setReport_sn(engine.getParameter(PostBackValue[i]));

						// 由于report_sn在打开报表时候就已经在tsjy_report_item_value
						// 插入了记录，并且生成了之后一直不会删除
						// 所以直接进入下一次循环
						continue;
					}

					// 获取电梯层数
					if (PostBackValue[i].toUpperCase().equals("P30002005")) {
						try {
							dtcs = Integer.parseInt((String) engine.getParameter(PostBackValue[i].trim()));
						} catch (Exception e) {
							dtcs = 0;
						}
					}
					// 获取索道斜长
					if (PostBackValue[i].toUpperCase().equals("JBJSCS_3")) {
						try {
							sdxc = Double.parseDouble((String) engine.getParameter(PostBackValue[i].trim()));
						} catch (Exception e) {
							sdxc = 0;
						}
					}

					// 获取设备型号
					if (PostBackValue[i].toUpperCase().equals("DEVICE_MODEL")) {
						device_model = engine.getParameter(PostBackValue[i].trim());
						devicedoc.setDevice_model(device_model);
						insInfo.setDevice_model(device_model);
					}
					
					// 获取设备出厂编号
					if (PostBackValue[i].toUpperCase().equals("FACTORY_CODE")) {
						String factory_code = engine.getParameter(PostBackValue[i].trim());
						devicedoc.setFactory_code(factory_code);
						insInfo.setFactory_code(factory_code);
					}
					
					// 获取设备单位内编号
					if (PostBackValue[i].toUpperCase().equals("INTERNAL_NUM")) {
						String internal_num = engine.getParameter(PostBackValue[i].trim());
						devicedoc.setInternal_num(internal_num);
						insInfo.setInternal_num(internal_num);
					}
					
					// 获取设备制造日期
					if (PostBackValue[i].toUpperCase().equals("MAKE_DATE")) {
						String make_date = engine.getParameter(PostBackValue[i].trim());
						devicedoc.setMake_date(make_date);
						insInfo.setMake_date(make_date);
					}
					
					// 获取设备使用地点
					if (PostBackValue[i].toUpperCase().equals("DEVICE_USE_PLACE")) {
						String device_use_place = engine.getParameter(PostBackValue[i].trim());
						devicedoc.setDevice_use_place(device_use_place);
						insInfo.setDevice_use_place(device_use_place);
					}
					
					// 获取限速器报告编号
					if (PostBackValue[i].toUpperCase().equals("REPORT_SN_XSQ")) {
						insInfo.setReport_sn_xsq(engine.getParameter(PostBackValue[i].trim()));
					}

					// 获取碰碰车座舱数量
					if (PostBackValue[i].toUpperCase().equals("XN_PARA_8")) {
						try {
							ppc_zcsl = Integer.parseInt((String) engine.getParameter(PostBackValue[i].trim()));
						} catch (Exception e) {
							ppc_zcsl = 0;
						}
					}

					// 获取赛车车辆总数
					if (PostBackValue[i].toUpperCase().equals("XN_PARA_12")) {
						try {
							sc_clzs = Integer.parseInt((String) engine.getParameter(PostBackValue[i].trim()));
						} catch (Exception e) {
							sc_clzs = 0;
						}
					}

					// 获取小火车乘座人数
					if (PostBackValue[i].toUpperCase().equals("XN_PARA_6")) {
						try {
							xhc_czrs = Integer.parseInt((String) engine.getParameter(PostBackValue[i].trim()));
						} catch (Exception e) {
							xhc_czrs = 0;
						}
					}

					if (PostBackValue[i].toUpperCase().indexOf("selectRow") != -1) {
						continue;
					}
					try {
						if (b_boiler && _boilerMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;
							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_boiler, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_vessel && _vesselMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;
							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_vessel, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_cal && _calMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;
							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							// System.out.println(PostBackValue[i].toLowerCase()+"="+backValue);
							SetParaValue(p_cal, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_elevator && _elevatorMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");

							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}

							SetParaValue(p_elevator, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_engine && _engineMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;
							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_engine, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_crane && _craneMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;
							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_crane, PostBackValue[i].toLowerCase(), backValue);
						}

						if (b_ride && _rideMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;

							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_ride, PostBackValue[i].toLowerCase(), backValue);

						}
						if (b_acc && _accMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;

							// 判断是否为/ 如果是就便问空
							if (backValue.equals("/")) {
								backValue = "";
							}
							SetParaValue(p_acc, PostBackValue[i].toLowerCase(), backValue);
						}

						if (_docMap.contains(PostBackValue[i].toUpperCase())) {
							String backValue = engine.getParameter(PostBackValue[i]).replaceAll("'", "’");
							;

							// 判断是否为/ 如果是就便问空
							// if(backValue.equals("/")){
							// backValue = "";
							// }

							SetParaValue(devicedoc, PostBackValue[i].toLowerCase(), backValue);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}else{
					// 将电梯二维码编号写入业务信息表，签发后写入设备信息表
					if (PostBackValue[i].equals("DEVICE_QR_CODE")) {
						insInfo.setDevice_qr_code("");
					}
					// 将电梯使用登记证号写入设备信息表
					if (PostBackValue[i].equals("REGISTRATION_NUM")) {
						devicedoc.setRegistration_num("");
					}
				}

				reportItemValueDao.insertReportItemValue(StringUtil.getUUID(), report_type, PostBackValue[i].trim(),
						engine.getParameter(PostBackValue[i]), ins_info_id);
			}

			// 生成报告书编号
			/*try {
				if (insInfo.getReport_sn() == null || "".equals(insInfo.getReport_sn())) {
					// String report_sn =
					// reportService.generateReportCode(device_type, check_type,
					// report_type);
					//String report_sn = this.synReportSN("0", ins_info_id, device_type, check_type, report_type);
					Map<String, Object> reportSnMap = inspectionCommonService.startReport("cpSaveReport", check_type, request, response);
					String report_sn = String.valueOf(reportSnMap.get("report_sn"));
					// 报告输编号插入业务主表
					insInfo.setReport_sn(report_sn);

					// reportItemValueDao.createSQLQuery("delete
					// TZSB_REPORT_ITEM_VALUE where
					// fk_report_id='"+report_type+"' and
					// upper(item_name)='REPORT_SN' and
					// fk_inspection_info_id='"+ins_info_id+"'").executeUpdate();
					// 报告输编号插入报告数据保存表

					// reportItemValueDao.createSQLQuery("delete
					// TZSB_REPORT_ITEM_VALUE where
					// fk_report_id='"+report_type+"' and
					// upper(item_name)='REPORT_SN' and
					// fk_inspection_info_id='"+ins_info_id+"'").executeUpdate();
					//
					// reportItemValueDao.createSQLQuery("insert into
					// TZSB_REPORT_ITEM_VALUE(id,fk_report_id,item_name,item_value,fk_inspection_info_id)
					// values('"+StringUtil.getUUID()+"','"+report_type+"','REPORT_SN','"+report_sn+"','"+ins_info_id+"')").executeUpdate();
					reportItemValueDao.createSQLQuery("update  TZSB_REPORT_ITEM_VALUE set item_value='" + report_sn
							+ "'where fk_report_id='" + report_type
							+ "' and item_name='REPORT_SN' and fk_inspection_info_id='" + ins_info_id + "'")
							.executeUpdate();
				} else {
					// 报告书编号更新报告数据保存表
					reportItemValueDao.createSQLQuery("update  TZSB_REPORT_ITEM_VALUE set item_value='"
							+ insInfo.getReport_sn() + "'where fk_report_id='" + report_type
							+ "' and item_name='REPORT_SN' and fk_inspection_info_id='" + ins_info_id + "'")
							.executeUpdate();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}*/

			// 报告是否录入 2是已经录入
			insInfo.setIs_report_input("2");
			insInfo.setIs_copy("0");// 不是复制报告

			Date cur_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime());		
			if (device_type.startsWith("3") || device_type.startsWith("4") || device_type.startsWith("5")
					|| device_type.startsWith("6") || device_type.startsWith("9")) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", inspection_date));
				// 起重机编制日期（机电五部2015-08-24要求，编制日期=检验日期+1天）
				// 游乐设施编制日期（机电五部2015-09-25龚高科要求，编制日期=检验日期+1天）
				// 客运索道编制日期（机电五部2015-10-15龚高科要求，编制日期=检验日期+1天）
				// 厂车编制日期（机电五部2015-11-24龚高科要求，编制日期=检验日期+1天）
				calendar.add(Calendar.DATE, 1);
				Date draft_date = insInfo.getEnter_time();
				if (calendar.getTime().after(cur_date)) {
					draft_date = cur_date;
				} else {
					if(insInfo.getEnter_time()==null){
						draft_date = calendar.getTime();
					}
				}
				insInfo.setEnter_time(draft_date);
				// insInfo.setEnter_time(DateUtil.convertStringToDate("yyyy-MM-dd",
				// DateUtil.getDate(calendar.getTime())));
			} else if (device_type.startsWith("8")) {
				Date draft_date = insInfo.getEnter_time();
				if (StringUtil.isNotEmpty(enter_time)) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", enter_time));
					if (calendar.getTime().after(cur_date)) {
						draft_date = cur_date;
					} else {
						if(insInfo.getEnter_time()==null){
							draft_date = calendar.getTime();
						}
					}
				} else {
					draft_date = DateUtil.convertStringToDate("yyyy-MM-dd", inspection_date);
				}
				insInfo.setEnter_time(draft_date);
			} else {
				if(insInfo.getEnter_time() == null){
					insInfo.setEnter_time(DateUtil.convertStringToDate("yyyy-MM-dd", inspection_date));
				}
			}
			insInfo.setEnter_op_name(emp.getName());

			// 返写基础表
			deviceDao.save(devicedoc);

			if (b_boiler) {
				if (p_boiler != null) {
					boilerParaDao.save(p_boiler);
				}
			}
			if (b_vessel) {
				if (p_vessel != null) {
					pressurevesselsParaDao.save(p_vessel);
				}
			}

			if (b_cal) {
				if (p_cal != null) {
					calDao.save(p_cal);
				}
			}
			if (b_elevator) {
				if (p_elevator != null) {
					elevatorParaDao.save(p_elevator);
				}
			}
			if (b_crane) {
				if (p_crane != null) {
					craneParaDao.save(p_crane);
				}
			}
			if (b_engine) {
				if (p_engine != null) {
					engineParaDao.save(p_engine);
				}
			}
			if (b_ride) {
				if (p_ride != null) {
					ridesParaDao.save(p_ride);
				}
			}

			// 计算电梯收费金额
			int dtfy = 0; // 打折后，四舍五入
			double dt_money = 0; // 不打折，也不四舍五入

			Report report = reportService.get(insInfo.getReport_type());
			String report_name = report.getReport_name();
			if (report_name.contains("病床")) {
				if(StringUtil.isNotEmpty(check_unit_id)){
					if (!Constant.CHECK_UNIT_100090.equals(check_unit_id) && !Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
						if (check_type.equals("3")) {// 定期检验
							// 判断是否有限速器
							if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
								// 判断电梯层数
								if (dtcs <= 5) {
									dtfy = (int) Math.round((500));
								} else {
									dtfy = (int) Math.round(500 + (dtcs - 5) * 50);
								}
							} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
								if (dtcs <= 5) {
									dtfy = (int) Math.round(500 * 1.3 + 200);
								} else {
									dtfy = (int) Math.round((500 + (dtcs - 5) * 50) * 1.3 + 200);
								}

							} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
								if (dtcs <= 5) {
									dtfy = (int) Math.round(500 * 1.3 + 400);
								} else {
									dtfy = (int) Math.round((500 + (dtcs - 5) * 50) * 1.3 + 400);
								}
							}
						}else if (check_type.equals("2")) {// 监督检验
							// 判断电梯层数
							if (dtcs <= 5) {
								dtfy = (int) Math.round(500 * 1.5);
							} else {
								dtfy = (int) Math.round((500 + (dtcs - 5) * 50) * 1.5);
							}
						}
					}else{
						// 2017-12-14应明子涵要求，新疆特检突击队报告费用由检验员手动输入，不再自动计算价格biu~biu~
						// 2018-03-29应明子涵要求，九寨特检突击队报告费用由系统自动计算价格biu~biu~
						// 九寨沟的计算公式均无1.3和1.5的系数，故此处去掉
						if (Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
							if (check_type.equals("3")) {// 定期检验
								// 判断是否有限速器
								if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
									// 判断电梯层数
									if (dtcs <= 5) {
										dtfy = (int) Math.round((500));
									} else {
										dtfy = (int) Math.round(500 + (dtcs - 5) * 50);
									}
								} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
									if (dtcs <= 5) {
										dtfy = (int) Math.round(500 + 200);
									} else {
										dtfy = (int) Math.round((500 + (dtcs - 5) * 50) + 200);
									}

								} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
									if (dtcs <= 5) {
										dtfy = (int) Math.round(500 + 400);
									} else {
										dtfy = (int) Math.round((500 + (dtcs - 5) * 50) + 400);
									}
								}
							} else if (check_type.equals("2")) {// 监督检验
								// 判断电梯层数
								if (dtcs <= 5) {
									dtfy = (int) Math.round(500);
								} else {
									dtfy = (int) Math.round((500 + (dtcs - 5) * 50));
								}
							}
						}
					}
				}
			} else {
				if(StringUtil.isNotEmpty(check_unit_id)){
					if (!Constant.CHECK_UNIT_100090.equals(check_unit_id) && !Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
						// 从2015-08-01开始，检验的电梯，不再打折；2015-08-01以前检验的电梯，均打9折
						Date check_date = insInfo.getAdvance_time();
						Date no_cheap_date = DateUtil.convertStringToDate("yyyy-MM-dd", "2015-08-01");

						if (check_date.before(no_cheap_date)) {
							if (check_type.equals("3")) {// 定期检验
								// 区分设备类型
								if (devicedoc.getDevice_sort().equals("3100") || devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")) {
									// 判断是否有限速器
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										// 判断电梯层数
										if (dtcs <= 5) {
											dtfy = (int) Math.round((550 * 0.9));
										} else {
											dtfy = (int) Math.round(((550 + (dtcs - 5) * 55) * 0.9));
										}
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dtfy = (int) Math.round(((550 * 1.3 + 200) * 0.9));
										} else {
											dtfy = (int) Math.round((((550 + (dtcs - 5) * 55) * 1.3 + 200) * 0.9));
										}

									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										if (dtcs <= 5) {
											dtfy = (int) Math.round(((550 * 1.3 + 400) * 0.9));
										} else {
											dtfy = (int) Math.round((((550 + (dtcs - 5) * 55) * 1.3 + 400) * 0.9));
										}
									}
								}
								// else
								// if(devicedoc.getDevice_sort_code().equals("3170")){//病床电梯
								// //判断是否有限速器
								// if(insInfo.getXsqts().equals("0")){//没有勾选限速器
								// //判断电梯层数
								// if(dtcs<=5){
								// dtfy=(int)Math.round((500*0.9));
								// }else{
								// dtfy=(int)Math.round(((500+(dtcs-5)*50)*0.9));
								// }
								// }else if(insInfo.getXsqts().equals("1")){//勾选一个限速器
								// if(dtcs<=5){
								// dtfy=(int)Math.round(((500*1.3+200)*0.9));
								// }else{
								// dtfy=(int)Math.round((((500+(dtcs-5)*50)*1.3+200)*0.9));
								// }
								//
								// }else if(insInfo.getXsqts().equals("2")){//勾选两个限速器
								// if(dtcs<=5){
								// dtfy=(int)Math.round(((500*1.3+400)*0.9));
								// }else{
								// dtfy=(int)Math.round((((500+(dtcs-5)*50)*1.3+400)*0.9));
								// }
								// }
								//
								// }
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dtfy = (int) (400 * 0.9);
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dtfy = (int) (400 * 0.9);
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dtfy = (int) ((400+200) * 0.9);
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dtfy = (int) ((400+200+200) * 0.9);
									}
								}
							} else if (check_type.equals("2")) {// 监督检验
								// 区分设备类型
								if (devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")
										|| devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort().equals("3100")) {

									// 判断电梯层数
									if (dtcs <= 5) {
										dtfy = (int) Math.round((550 * 1.5 * 0.9));
									} else {
										dtfy = (int) Math.round(((550 + (dtcs - 5) * 55) * 1.5 * 0.9));
									}

								}
								// else
								// if(devicedoc.getDevice_sort_code().equals("3170")){
								// //判断电梯层数
								// if(dtcs<=5){
								// dtfy=(int) Math.round((500*1.5*0.9));
								// }else{
								// dtfy=(int)Math.round(((500+(dtcs-5)*50)*1.5*0.9));
								// }
								//
								// }
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dtfy = (int) (600 * 0.9);
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dtfy = (int) (600 * 0.9);
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dtfy = (int) ((600+200) * 0.9);
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dtfy = (int) ((600+200+200) * 0.9);
									}
								}
							}
						} else {
							if (check_type.equals("3")) {// 定期检验
								// 区分设备类型
								if (devicedoc.getDevice_sort().equals("3100") || devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")) {
									// 判断是否有限速器
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										// 判断电梯层数
										if (dtcs <= 5) {
											dt_money = 550;
										} else {
											dt_money = (550 + (dtcs - 5) * 55);
										}
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 * 1.3 + 200;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) * 1.3 + 200;
										}

									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										if (dtcs <= 5) {
											dt_money = 550 * 1.3 + 400;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) * 1.3 + 400;
										}
									}
								}
								// else
								// if(devicedoc.getDevice_sort_code().equals("3170")){//病床电梯
								// //判断是否有限速器
								// if(insInfo.getXsqts().equals("0")){//没有勾选限速器
								// //判断电梯层数
								// if(dtcs<=5){
								// dt_money=(int)Math.round((500));
								// }else{
								// dt_money=(int)Math.round(((500+(dtcs-5)*50)));
								// }
								// }else if(insInfo.getXsqts().equals("1")){//勾选一个限速器
								// if(dtcs<=5){
								// dt_money=(int)Math.round(((500*1.3+200)));
								// }else{
								// dt_money=(int)Math.round((((500+(dtcs-5)*50)*1.3+200)));
								// }
								//
								// }else if(insInfo.getXsqts().equals("2")){//勾选两个限速器
								// if(dtcs<=5){
								// dt_money=(int)Math.round(((500*1.3+400)));
								// }else{
								// dt_money=(int)Math.round((((500+(dtcs-5)*50)*1.3+400)));
								// }
								// }
								//
								// }
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dt_money = 400;
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dt_money = 400;
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dt_money = 400+200;
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dt_money = 400+200+200;
									}
								}
							} else if (check_type.equals("2")) {// 监督检验
								// 区分设备类型
								if (devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")
										|| devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort().equals("3100")) {

									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										// 判断电梯层数

										if (dtcs <= 5) {
											dt_money = 550 * 1.5;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) * 1.5;
										}

									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 * 1.5 + 200;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) * 1.5 + 200;
										}
									} else if (insInfo.getXsqts().equals("2")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 * 1.5 + 400;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) * 1.5 + 400;
										}
									}
								}
								// else
								// if(devicedoc.getDevice_sort_code().equals("3170")){
								// //判断电梯层数
								// if(dtcs<=5){
								// dt_money=(int) Math.round((500*1.5));
								// }else{
								// dt_money=(int)Math.round(((500+(dtcs-5)*50)*1.5));
								// }
								//
								// }
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dt_money = 600;
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dt_money = 600;
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dt_money = 600+200;
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dt_money = 600+200+200;
									}
								}
							}
						}
					}else{
						// 2017-12-14应明子涵要求，新疆特检突击队报告费用由检验员手动输入，不再自动计算价格biu~biu~
						// 2018-03-29应明子涵要求，九寨特检突击队报告费用由系统自动计算价格biu~biu~
						// 九寨沟的计算公式均无1.3和1.5的系数，故此处去掉
						if(Constant.CHECK_UNIT_100091.equals(check_unit_id)){
							if (check_type.equals("3")) {// 定期检验
								// 区分设备类型
								if (devicedoc.getDevice_sort().equals("3100") || devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")) {
									// 判断是否有限速器
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										// 判断电梯层数
										if (dtcs <= 5) {
											dt_money = 550;
										} else {
											dt_money = (550 + (dtcs - 5) * 55);
										}
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 + 200;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) + 200;
										}

									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										if (dtcs <= 5) {
											dt_money = 550 + 400;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) + 400;
										}
									}
								}
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dt_money = 400;
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dt_money = 400;
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dt_money = 400+200;
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dt_money = 400+200+200;
									}
								}
							} else if (check_type.equals("2")) {// 监督检验
								// 区分设备类型
								if (devicedoc.getDevice_sort_code().equals("3410")
										|| devicedoc.getDevice_sort_code().equals("3420")
										|| devicedoc.getDevice_sort().equals("3200")
										|| devicedoc.getDevice_sort().equals("3100")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										// 判断电梯层数
										if (dtcs <= 5) {
											dt_money = 550 ;
										} else {
											dt_money = (550 + (dtcs - 5) * 55);
										}

									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 + 200;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) + 200;
										}
									} else if (insInfo.getXsqts().equals("2")) {// 勾选一个限速器
										if (dtcs <= 5) {
											dt_money = 550 + 400;
										} else {
											dt_money = (550 + (dtcs - 5) * 55) + 400;
										}
									}
								}
								else if (devicedoc.getDevice_sort().equals("3300")) {
										dt_money = 600;
								}else if (devicedoc.getDevice_sort_code().equals("3430")) {
									if (insInfo.getXsqts().equals("0")) {// 没有勾选限速器
										dt_money = 600;
									} else if (insInfo.getXsqts().equals("1")) {// 勾选一个限速器
										dt_money = 600+200;
									} else if (insInfo.getXsqts().equals("2")) {// 勾选两个限速器
										dt_money = 600+200+200;
									}
								}
							}
						
						}
					}
				}
			}

			if (devicedoc.getDevice_sort().startsWith("3")) {
				if(StringUtil.isNotEmpty(check_unit_id)){
					if (!Constant.CHECK_UNIT_100090.equals(check_unit_id) && !Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
						if (dtfy == 0) {
							if (inspection_conclusion.equals("复检合格")) {
								insInfo.setAdvance_fees((double) Math.round(dt_money * 0.3));
							} else {
								insInfo.setAdvance_fees(dt_money);
							}

						} else {
							if (inspection_conclusion.equals("复检合格")) {
								insInfo.setAdvance_fees((double) Math.round(dtfy * 0.3));
							} else {
								insInfo.setAdvance_fees((double) dtfy);
							}
						}
					}else{
						// 2017-12-14应明子涵要求，新疆特检突击队报告费用由检验员手动输入，不再自动计算价格biu~biu~
						// 2018-04-28应明子涵要求，九寨沟特检突击队报告费用由系统自动计算价格biu~biu~
						if(Constant.CHECK_UNIT_100091.equals(check_unit_id)){
							insInfo.setAdvance_fees(dt_money);
						}
					}
				}
			} else if (devicedoc.getDevice_sort().startsWith("6")) {
				// 游乐设施费用计算
				if ("6900".equals(devicedoc.getDevice_sort())) { // 6900：小火车类
					if (check_type.equals("3")) {
						// 定期检验
						if (xhc_czrs <= 10) {
							// 10人以内（含10人）
							dt_money = 140;
						} else if (11 <= xhc_czrs && xhc_czrs <= 30) {
							// 11人到30人（含30人）
							dt_money = 160;
						} else {
							dt_money = 180;
						}
					} else if (check_type.equals("2")) {
						// 监督检验
						if (xhc_czrs <= 10) {
							// 10人以内（含10人）
							dt_money = 168;
						} else if (11 <= xhc_czrs && xhc_czrs <= 30) {
							// 11人到30人（含30人）
							dt_money = 192;
						} else {
							dt_money = 216;
						}
					}
				} else if ("6A00".equals(devicedoc.getDevice_sort())) { // 6A00：碰碰车类
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort_code();
					}
					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						double pay_value = inspectionInfoPay.getPay_value();
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							dt_money = ppc_zcsl * pay_value;
						}
					}
				} else if ("6800".equals(devicedoc.getDevice_sort())) { // 6800：赛车类
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort_code();
					}
					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						double pay_value = inspectionInfoPay.getPay_value();
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							dt_money = sc_clzs * pay_value;
						}
					}
				} else { // 6A00：游乐设施除了（小火车和碰碰车的其他类型）
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort_code();
					}

					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						double pay_value = inspectionInfoPay.getPay_value();
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							dt_money = pay_value;
						}

					}

					if (list.size() == 0) {
						if (StringUtil.isEmpty(device_model)) {
							device_model = devicedoc.getDevice_sort();
						}
						List<InspectionInfoPay> list1 = inspectionInfoPayService.getByDeviceSortCodes(device_model);
						for (InspectionInfoPay inspectionInfoPay : list1) {
							double pay_value = inspectionInfoPay.getPay_value();
							String check_typep = inspectionInfoPay.getCheck_type();
							if (check_type.equals(check_typep)) {
								dt_money = pay_value;
							}

						}
					}

				}
				if(insInfo.getAdvance_fees() == null){
					if (dt_money != 0) {
						insInfo.setAdvance_fees((double) dt_money);
					}
				}else{
					if(insInfo.getAdvance_fees() == 0){
						if (dt_money != 0) {
							insInfo.setAdvance_fees((double) dt_money);
						}
					}
				}
			} else if (devicedoc.getDevice_sort().startsWith("9")) {

				if (StringUtil.isEmpty(device_model)) {
					device_model = devicedoc.getDevice_sort_code();
				}
				// 客运索道费用计算
				// 9100：客运架空索道 9300：客运拖牵索道
				if ("9100".equals(devicedoc.getDevice_sort())) {
					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						int pay_key = StringUtil.isNotEmpty(inspectionInfoPay.getPay_key())
								? Integer.parseInt(inspectionInfoPay.getPay_key()) : 0;
						double pay_value = inspectionInfoPay.getPay_value();
						// 在基数的基础上，斜长每增加100米，费用增加费用基数的3%
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							double jsxc = sdxc - pay_key;
							if (jsxc < 100) {
								dt_money = pay_value;
							} else {
								int t = (int) (jsxc / 100);
								dt_money = pay_value + (t * pay_value * 0.03);
							}
						}
					}
				} else if ("9300".equals(devicedoc.getDevice_sort())) {
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort_code();
					}
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort();
					}

					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						double pay_value = inspectionInfoPay.getPay_value();
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							dt_money = pay_value;
						}

					}
				}
				if(insInfo.getAdvance_fees() == null){
					if (dt_money != 0) {
						insInfo.setAdvance_fees((double) dt_money);
					}
				}else{
					if(insInfo.getAdvance_fees() == 0){
						if (dt_money != 0) {
							insInfo.setAdvance_fees((double) dt_money);
						}
					}
				}
			} else if (devicedoc.getDevice_sort().startsWith("4")) {
				if(StringUtil.isNotEmpty(check_unit_id)){
					if (!Constant.CHECK_UNIT_100090.equals(check_unit_id)) {
						if ("2".equals(check_type) || "3".equals(check_type)) {
							double money = 0;
							// 起重机费用计算
							Collection<CranePara> crane = devicedoc.getCrane();
							int cws = 0;
							for (CranePara cranePara : crane) {
								if (cranePara.getP_cws() != null && StringUtil.isNotEmpty(cranePara.getP_cws())) {
									cws = Integer.parseInt(cranePara.getP_cws());
								}
							}
							// 2016-01-27修改：桥门式起重机暂时取消自动计算金额功能，以用户手动输入为准（因起重量多数值（例如：30/15）的特殊性）
							/*
							 * if("4170".equals(devicedoc.getDevice_sort_code())
							 * ){// 电动单梁起重机 money = 300; double jsxc =
							 * qzkd-13.5; if(jsxc>=5){ int t = (int) (jsxc / 5);
							 * money =money + t * 50; } }else
							 * if((devicedoc.getDevice_sort().startsWith("41")
							 * ||devicedoc.getDevice_sort().startsWith("42"))
							 * &&!"4170".equals(devicedoc.getDevice_sort_code())
							 * ){//双梁桥、 门式起重机 if(qz_q<=20){//起重量Q<=20 money =
							 * 400; }else{//起重量Q<=20 double jsxc = qz_q - 20;
							 * money =500; //每增加10t加收50元 if(jsxc>=10){ int t =
							 * (int) (jsxc / 10); money =500 + t * 50; } }
							 * double jsxc = qzkd-13.5; if(jsxc>=5){ int t =
							 * (int) (jsxc / 5); money =money + t * 50; } }else
							 */if (devicedoc.getDevice_sort().startsWith("43")) {// 塔式起重机
								// 检验类别（check_type，2：监检 3：定检）
								if ("2".equals(check_type)) {
									// qz_lj = qz_lj / 10000;
									// 监检的起重力矩单位是t(t.m)，所以这里不需要除以10000
								} else if ("3".equals(check_type)) {
									// 定检的起重力矩单位是N.m，所以这里需要除以10000
									qz_lj = qz_lj / 10000;
								}

								if (qz_lj <= 25) {// 起重力矩<=25
									money = 500;
								} else if (qz_lj > 25 && qz_lj <= 40) {// 25<起重力矩<=40
									money = 550;
								} else if (qz_lj > 40 && qz_lj <= 60) {// 40<起重力矩<=60
									dt_money = 600;
								} else if (qz_lj > 60 && qz_lj <= 80) {// 60<起重力矩<=80
									money = 650;
								} else {// 起重力矩>80
									money = 700;
								}

							} else if (devicedoc.getDevice_sort().startsWith("44")
									&& !"4450".equals(devicedoc.getDevice_sort_code())) {// 流动式起重机（排除铁路起重机）
								if (qz_q <= 16) {// 起重量Q<=16
									money = 400;
								} else {// 起重量Q<=16
									double jsxc = qz_q - 16;
									money = 500;
									// 每增加10t加收50元
									if (jsxc >= 10) {
										int t = (int) (jsxc / 10);
										money = 500 + t * 50;
									}
								}
							} else if (devicedoc.getDevice_sort().startsWith("47")) {// 门坐式起重机
								if (qz_q <= 10) {// 起重量Q<=10
									money = 500;
								} else {// 起重量Q<=16
									double jsxc = qz_q - 10;
									money = 600;
									// 每增加10t加收50元
									if (jsxc >= 10) {
										int t = (int) (jsxc / 10);
										money = 600 + t * 50;
									}
								}

							} else if ("4D00".equals(devicedoc.getDevice_sort())) {// 机械式停车设备
								money = 60 * cws;
							} else {
								// 起重机械的其他类型的起重机
								if (StringUtil.isEmpty(device_model)) {
									device_model = devicedoc.getDevice_sort_code();
								}
								List<InspectionInfoPay> list = inspectionInfoPayService
										.getByDeviceSortCodes(device_model);
								for (InspectionInfoPay inspectionInfoPay : list) {
									double pay_value = inspectionInfoPay.getPay_value();
									String check_typep = inspectionInfoPay.getCheck_type();
									if (check_type.equals(check_typep)) {
										money = pay_value;
									}

								}

								if (list.size() == 0) {
									if (StringUtil.isEmpty(device_model)) {
										device_model = devicedoc.getDevice_sort();
									}

									List<InspectionInfoPay> list1 = inspectionInfoPayService
											.getByDeviceSortCodes(device_model);
									for (InspectionInfoPay inspectionInfoPay : list1) {
										double pay_value = inspectionInfoPay.getPay_value();
										String check_typep = inspectionInfoPay.getCheck_type();
										if (check_type.equals(check_typep)) {
											money = pay_value;
										}

									}
								}
							}

							if ("2".equals(check_type)) {
								dt_money = money * 2;
							} else if ("3".equals(check_type)) {
								dt_money = money;
							}
						}
						if (dt_money != 0) {
							// 2016-01-27修改：桥门式起重机暂时取消自动计算金额功能，以用户手动输入为准（因起重量多数值（例如：30/15）的特殊性）
							if (!(devicedoc.getDevice_sort().startsWith("41")
									&& devicedoc.getDevice_sort().startsWith("42"))) {
								if (insInfo.getAdvance_fees() == null) {
									if (dt_money != 0) {
										insInfo.setAdvance_fees((double) dt_money);
									}
								} else {
									if (insInfo.getAdvance_fees() == 0) {
										if (dt_money != 0) {
											insInfo.setAdvance_fees((double) dt_money);
										}
									}
								}
							}
						}
					}else{
						// 新疆特检突击队起重机报告费用由检验员手动输入，不再自动计算价格
					}
				}
				
			} else if (devicedoc.getDevice_sort().startsWith("5")) {
				double money = 0;
				// 厂内机动车费用计算
				if ("2".equals(check_type) || "3".equals(check_type)) {
					if (StringUtil.isEmpty(device_model)) {
						device_model = devicedoc.getDevice_sort_code();
					}
					List<InspectionInfoPay> list = inspectionInfoPayService.getByDeviceSortCodes(device_model);
					for (InspectionInfoPay inspectionInfoPay : list) {
						double pay_value = inspectionInfoPay.getPay_value();
						String check_typep = inspectionInfoPay.getCheck_type();
						if (check_type.equals(check_typep)) {
							money = pay_value;
						}

					}

					if (list.size() == 0) {
						if (StringUtil.isEmpty(device_model)) {
							device_model = devicedoc.getDevice_sort();
						}

						List<InspectionInfoPay> list1 = inspectionInfoPayService.getByDeviceSortCodes(device_model);
						for (InspectionInfoPay inspectionInfoPay : list1) {
							double pay_value = inspectionInfoPay.getPay_value();
							String check_typep = inspectionInfoPay.getCheck_type();
							if (check_type.equals(check_typep)) {
								money = pay_value;
							}

						}
					}

					if ("3".equals(check_type)) {
						dt_money = money;
					} else if ("2".equals(check_type)) {
						dt_money = money * 2;
					}
				}
				if(insInfo.getAdvance_fees() == null){
					if (dt_money != 0) {
						insInfo.setAdvance_fees((double) dt_money);
					}
				}else{
					if(insInfo.getAdvance_fees() == 0){
						if (dt_money != 0) {
							insInfo.setAdvance_fees((double) dt_money);
						}
					}
				}
			}

			// 西藏报告收费标准，直梯（有机房、无机房）定检1000元整，扶梯定检800元整，杂物梯定检400元整
			// 新疆报告收费标准，直梯（有机房、无机房）定检1000元整，扶梯定检800元整，杂物梯定检400元整
			if (StringUtil.isNotEmpty(check_unit_id)) {
				// 100069：赴藏特检突击队
				if (Constant.CHECK_UNIT_100069.equals(check_unit_id)) {
					double xz_money = 1000.00;
					if (devicedoc.getDevice_sort().equals("3100") || devicedoc.getDevice_sort().equals("3200")) {
						xz_money = 1000.00;
					} else if (devicedoc.getDevice_sort().equals("3400")) {
						if (devicedoc.getDevice_sort_code().equals("3430")) {
							xz_money = 400.00;
						}
					} else if (devicedoc.getDevice_sort().equals("3300")) {
						xz_money = 800.00;
					}
					insInfo.setAdvance_fees(xz_money);
				}
				// 100090：新疆特检突击队
				// 2017-12-14应明子涵要求，新疆特检突击队报告费用由检验员手动输入，不再自动计算价格biu~biu~
				/*if (Constant.CHECK_UNIT_100090.equals(check_unit_id)) {
					double xj_money = 1000.00;
					if (devicedoc.getDevice_sort().equals("3100") || devicedoc.getDevice_sort().equals("3200")) {
						xj_money = 1000.00;
					} else if (devicedoc.getDevice_sort().equals("3400")) {
						if (devicedoc.getDevice_sort_code().equals("3430")) {
							xj_money = 400.00;
						}
					} else if (devicedoc.getDevice_sort().equals("3300")) {
						xj_money = 800.00;
					}
					insInfo.setAdvance_fees(xj_money);
				}*/
			}

			// System.out.println("计算所得费用为------------------------------------------------------------"+dt_money);
			insInfo.setLast_mdy_time(new Date());
			insInfo.setEnter_time2(new Date());	// 报告实际编制日期
			// 保存回写的业务信息和设备基础信息
			inspectionInfoDao.save(insInfo);
			deviceDao.save(devicedoc);

			// 插入处理记录表
			// writeWaring(user,device_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SetParaValue(BaseEntity obj ,String key , String value) 
			throws Exception {
		//String Method = "set"+changFisrtUpper(key);
		
		Method method=null;
		if(key.equals("first_install_date")||key.equals("remake_date")||key.equals("repair_date")||key.equals("install_finish_date")){
			method =obj.getClass().getMethod("set"+changFisrtUpper(key),
					Date.class);
			SimpleDateFormat far  = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			if(method!= null) {
				if (StringUtil.isNotEmpty(value)) {
					date = far.parse(value);
				}
				method.invoke(obj, date) ;
			}
		}else{
			if(obj != null) {
				method = obj.getClass().getMethod("set"+changFisrtUpper(key),
						String.class);
				if(method!= null) {
					method.invoke(obj, value) ;
				}
			}
		}
	}
	
	public Map<String,Object> beanToMap(Object obj) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] field =  obj.getClass().getDeclaredFields();
		for(Field fd : field) {
			if(fd.getName().equals("serialVersionUID"))
				continue ;
			try{
				//System.out.println(changFisrtUpper(fd.getName()));
				//System.out.println("get"+changFisrtUpper(fd.getName()));
			Method method = obj.getClass()
					.getMethod("get"+changFisrtUpper(fd.getName()), null);
			
			if(method!= null) {
				method.getName();
				Object obj1 = method.invoke(obj, null) ;
				if (fd.getName().equals("last_check_time")) {
					map.put(fd.getName().toUpperCase(), DateUtil.getDateTime("yyyy-MM-dd", (Date)obj1));
				}else{
					map.put(fd.getName().toUpperCase(), obj1);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		return map ;
	}
	
	public Set<String> beanFieldSet(Class clazz)throws Exception {
		Set<String> set = new HashSet<String>();
		Field[] field =  clazz.getDeclaredFields();
		for(Field fd : field) {
			if(fd.getName().equals("serialVersionUID"))
				continue ;
			set.add(fd.getName().toUpperCase());
		}
		return set ;
	}
	
	private static String changFisrtUpper(String str){
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}
	
	// 报告领取保存（填写领取单）
	public HashMap<String, Object> flow_saveDrow(String info_id
			,ReportDraw reportDraw) throws Exception{
		
		//InspectionInfo inspectionInfo = inspectionInfoDao.get(info_id);
		/*String report_sn = "";
		String report_type = "";
		if (inspectionInfo != null) {
			if (StringUtil.isNotEmpty(inspectionInfo.getReport_sn())) {
				report_sn = inspectionInfo.getReport_sn();	// 报告书编号
			}
			Report report = reportDao.get(inspectionInfo
					.getReport_type());
			if (report != null) {
				if (StringUtil.isNotEmpty(report.getReport_name())) {
					report_type = report.getReport_name(); // 报告类型
				}
			}
		}*/
		reportDraw.setPulldown_time(new Date());
		reportDraw.getInspectionInfo().setId(info_id);
		reportDrawDao.save(reportDraw);
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		//wrapper.put("report_sn", report_sn);
		//wrapper.put("report_type", report_type);
		
		return wrapper;
		
	} 
	
	// 报告领取保存（填写领取单）
	public HashMap<String, Object> flow_saveDrow1(String info_id) throws Exception{	
		InspectionInfo inspectionInfo = inspectionInfoDao
				.get(info_id);
		String sn = "";
		String report_type = "";
		if (inspectionInfo != null) {
			if (StringUtil.isNotEmpty(inspectionInfo.getSn())) {
				sn = inspectionInfo.getSn();	// 业务流水号
			}
			Report report = reportDao.get(inspectionInfo
					.getReport_type());
			if (report != null) {
				if (StringUtil.isNotEmpty(report.getReport_name())) {
					report_type = report.getReport_name(); // 报告类型
				}
			}
		}
		//reportDraw.setPulldown_time(new Date());
		//reportDraw.getInspectionInfo().setId(info_id);
		//reportDrawDao.save(reportDraw);
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("sn", sn);
		wrapper.put("report_type", report_type);
		
		return wrapper;
		
	} 
	

	
	
	public void flow_reportEnd(HashMap map,HttpServletRequest request) 
			throws Exception{
		
		String info_id = map.get("infoId").toString();
		
		String process_id =  map.get("process_id").toString();
		
		String flow_num =  map.get("flow_num").toString();
		
		InspectionInfo info = infoDao.get(info_id);
		
		Map<String,Object> paramMap = new HashMap();
		
		paramMap.put(FlowExtWorktaskParam.PROCESS_ID, process_id);
		
		if(info.getFlow_note_id().equals(flow_num)){
		try {
			flowExtManager.finishProcess(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
			
			String step_name = info.getFlow_note_name();
			
			String op_conclusion = "进入"+step_name+"环节环节,流程结束。";
			
			
			info.setFlow_note_id("");
			
			info.setFlow_note_num("99");//99表示流程结束
			
			info.setFlow_note_name("报告归档");
			
			info.setReport_end_date(new Date());
			
			infoDao.save(info);
			
			if(StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())){
				DeviceDocument deviceDocument = deviceDao.get(info.getFk_tsjc_device_document_id());
				deviceDocument.setIs_cur_check("1");	// 当前是否报检（1：未报检 2：当前报检中）
				deviceDao.save(deviceDocument);			// 报告归档时，设备当前报检结束。
			}
			

			//写入日志
			
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			String address = request.getRemoteHost();
			logService.setLogs(info_id, "报告归档", op_conclusion, 
					user.getId(), 
					user.getName(), 
					new Date(), request.getRemoteAddr());
			
		}
		
		
	}
	
	/**
	 * 报告归档新（20190401）
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	public void flow_reportEndNew(HashMap map,HttpServletRequest request) 
			throws Exception{
		
		String info_id = map.get("infoId").toString();
		
		String process_id =  map.get("process_id").toString();
		
		String flow_num =  map.get("flow_num").toString();
		String flowId =  map.get("flowId").toString();
		String deviceId =  map.get("deviceId").toString();
		String flowName =  map.get("flowName").toString();
		
		//InspectionInfo info = infoDao.get(info_id);
		
		Map<String,Object> paramMap = new HashMap();
		
		paramMap.put(FlowExtWorktaskParam.PROCESS_ID, process_id);
		
		if(flowId.equals(flow_num)){
		try {
			flowExtManager.finishProcess(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
			
			String step_name = flowName;
			
			String op_conclusion = "进入"+step_name+"环节环节,流程结束。";
			
			
			
			infoDao.saveSubEnd(info_id,"","99","报告归档");
			
			if(StringUtil.isNotEmpty(deviceId)){
				DeviceDocument deviceDocument = deviceDao.get(deviceId);
				deviceDocument.setIs_cur_check("1");	// 当前是否报检（1：未报检 2：当前报检中）
				deviceDao.save(deviceDocument);			// 报告归档时，设备当前报检结束。
			}
			

			//写入日志
			
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			String address = request.getRemoteHost();
			logService.setLogs(info_id, "报告归档", op_conclusion, 
					user.getId(), 
					user.getName(), 
					new Date(), request.getRemoteAddr());
			
		}
		
		
	}

	public Inspection subRport(HashMap map
			,HttpServletRequest request) throws Exception{
		
		Connection conn = Factory.getDB().getConnetion();
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		String ids = map.get("ids").toString();
		
		String report_type = map.get("report_type").toString();
		
		String count = map.get("count").toString();
		
		InspectionInfo  info = new InspectionInfo();
		
		Inspection  insp = new Inspection();
		
		SupervisionInspection sup = supDao.get(ids);
			
			info.setFk_tsjc_device_document_id("123456789");
			//插入报检表
			insp.setFk_unit_id(org.getId());
			
			insp.setEnter_op(emp.getName());
			
			insp.setCom_name(sup.getMade_unit_name());
			
			insp.setData_status("1");
			
			//插入业务信息表
			info.setReport_type(report_type);
			
			if(info.getSn()==null){
	    		//获取业务流水号
	    		String sn = TS_Util.getSn(Integer.valueOf(count),conn) ;
	    		
	    		info.setSn(sn);
	    	}
			
			info.setEnter_op_id(emp.getId());
    		
    		info.setEnter_op_name(emp.getName());
    		
    		info.setEnter_unit_id(org.getId());
    		
    		info.setCheck_op_id(emp.getId());
    		
    		info.setCheck_op_name(emp.getName());
    		
    		info.setItem_op_id(emp.getId());
    		
    		info.setItem_op_name(emp.getName());
    		
    		info.setCheck_unit_id(org.getId());
    		
    		info.setInspection(insp);
    		
    		info.setData_status("1");
    		
    		
    		inspectionDao.save(insp);
    		
    	
    		
    		infoDao.save(info);
    		
    		map.put("infoId", info.getId());
			//改变状态
    		sup.setStatus("2");
    		
    		sup.setFk_inspection_info_id(info.getId());

    		supDao.save(sup);
		
//		wrapper.put("sn", sn);
//		wrapper.put("report_type", report_type);
//		
		
		Factory.getDB().freeConnetion(conn);//释放连接
		
		StarFlowProcess(map, request);
		
		return insp;
		
	} 
	
	public JSONArray getArrayByKey(JSONObject dataMap,String key){
		JSONArray ret =null;
		try{
			ret = dataMap.getJSONArray(key);
		}catch(Exception e){
			try {
				String tt = dataMap.get(key).toString();
				if(tt.split(",").length>1){
					tt = tt.replace(",", "，");
				}
				ret=JSONArray.fromObject("[\""+tt+"\"]");
				
			} catch (Exception e2) {
				ret=null;
			}
			
		}
		return ret;
	}
	
	public static void main(String[] args) {
		/*JSONArray a = new JSONArray();
		String tt = "["+"伏利蓉，阿雷"+"]";
		a = JSONArray.fromObject(tt);
		System.out.println(a.toString());*/
		String str = "Mon Dec 31 00:00:00 CST 2012";
        Date date = parse(str, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        //System.out.printf("%tF %<tT%n", date);
	}
	
	public void flow_print(Map<String, Object> map) throws Exception{
		String id = map.get("ins_info_id").toString();
		InspectionInfo info = infoDao.get(id);
		info.setPrint_time(new Date());
		infoDao.save(info);
	}

	public String getPage(String checkType, String device_code) throws Exception {
		String jsonstring = "";
		try {
			// String sql="from BaseFlowConfig t where t.check_type
			// ="+checkType+" and t.device_type like
			// '"+device_code.substring(0,1)+"%'";
			if ("7310".equals(device_code)) {
				device_code = "F";
			}
			String sql = "select  distinct t.fk_report_id as code,r.report_name as text,r.sort_order from base_unit_flow t,base_reports r where t.fk_report_id=r.id and r.flag='1' and t.check_type ="
					+ checkType + " and t.device_type like '" + device_code.substring(0, 1)
					+ "%' order by r.sort_order desc";
			// List list= inspectionDao.createQuery(sql).list();
			List list = inspectionDao.createSQLQuery(sql).list();
			Object[] objArr = list.toArray();
			for (int i = 0; i < objArr.length; i++) {
				Object[] oo = (Object[]) objArr[i];
				// BaseFlowConfig baseFlowConfig=(BaseFlowConfig)list.get(i);
				jsonstring += "{\"id\":\"" + oo[0] + "\",\"text\":\"" + oo[1] + "\"},";

			}

			if (jsonstring.endsWith(",")) {
				jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
			}
			jsonstring = "[" + jsonstring + "]";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonstring;
	}
	
	public String getContracts() throws Exception {
		String jsonstring = "";
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			Org org = TS_Util.getCurOrg(curUser);
			
			String sql = "select t.id as id,t.contract_no as text from CONTRACT_INFO t where t.data_status !='99' and instr(t.inspect_depart_id,'"+org.getId()+"')>0";
			List list = inspectionDao.createSQLQuery(sql).list();
			Object[] objArr = list.toArray();
			for (int i = 0; i < objArr.length; i++) {
				Object[] oo = (Object[]) objArr[i];
				jsonstring += "{\"id\":\"" + oo[0] + "\",\"text\":\"" + oo[1] + "\"},";
			}
			if (jsonstring.endsWith(",")) {
				jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
			}
			jsonstring = "[" + jsonstring + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonstring;
	}
	
	
	public String subDep(String ids,String checktypes,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String msg="";
		String flow_code="";
		String ywid="";
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
	
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		if(ids.indexOf(",")!=-1){
			String [] temp = ids.split(",");
			
			for(int i = 0;i<temp.length;i++){
				//2019-01-14 添加 "and t.data_status <> '99'" 避免报检时删除了的设备提交后任然提交了流程
				String sql="select t.id tid,d.device_sort_code,d.device_name,t.is_flow,t.is_report_input,d.id did,d.inspect_next_date,"
						+ "t.advance_time,t.is_back,t.report_type,d.device_registration_code "
						+ "from TZSB_INSPECTION_INFO t, base_device_document d "
						+ "where d.id=t.fk_tsjc_device_document_id and t.data_status <> '99' and t.fk_inspection_id='"+temp[i]+"'";
				List list =inspectionDao.createSQLQuery(sql).list();
				Object[] objArr = list.toArray();
				//Object[] obj = (Object[]) objArr[i];
				
				for (int j = 0; j < list.size(); j++) {
					Object[] obj2 = (Object[]) objArr[j];
					ywid=obj2[0].toString();
					
					// 验证2个月内是否已录入报告，有纠错程序除外（质量部2017-01-16要求）
					if(StringUtil.isNotEmpty(ywid)){
						InspectionInfo info = inspectionInfoDao.get(ywid);
						String device_registration_code = obj2[10].toString();
						if(StringUtil.isNotEmpty(info.getFk_tsjc_device_document_id())){
							List<InspectionInfo> info_list = inspectionInfoDao
									.getNewInfoByDeviceId(info.getFk_tsjc_device_document_id(), temp[i]);
							if (info_list != null) {
								if (info_list.size() > 0) {
									for (InspectionInfo old_info : info_list) {
										if(StringUtil.isNotEmpty(old_info.getInspection_conclusion())){
											if(!"不合格".equals(old_info.getInspection_conclusion().trim())){
												HashMap<String, Object> returnMap = validateRepeat(old_info,
														device_registration_code);
												if (!Boolean.valueOf(String.valueOf(returnMap.get("success")))) {
													return String.valueOf(returnMap.get("msg"));
												}
											}
										}
										
									}
								}
							} 
						}
					}
					
					if(obj2[8]!=null){
						if(obj2[8].toString().equals("1")){
							String device_sort_code = obj2[1].toString();
							String device_big_class = device_sort_code.substring(0,1);
							if("7310".equals(device_sort_code)){
								device_big_class = "F";
							}
							String hql = "select t.flow_id from FLOW_SERVICE_CONFIG t,BASE_UNIT_FLOW t1 where t.id=t1.FK_FLOW_ID and t1.DEVICE_TYPE like'"+device_big_class+"%' and t1.CHECK_TYPE in ("+checktypes+") and t.org_id='"+org.getId()+"'";
							hql += " and t1.FK_REPORT_ID='"+obj2[9].toString()+"'";
							
							List list2 = inspectionDao.createSQLQuery(hql).list(); 
							if(list2.size()>0){
								Object[] objArr2 = list2.toArray();
								//Object[] obj2= (Object[]) objArr2[j];
								flow_code = objArr2[0].toString();
								
								map.put("infoId", ywid);
								map.put("flowId", flow_code);
								map.put("status", "1");
								if(obj2[3]==null){
									// 先判断业务是否已经启动了流程
									// 业务流程表有数据就说明已经启动了流程，不再重新启动流程，没数据就启动流程
									String process_id = inspectionDao.getProcess(ywid);
									if(StringUtil.isEmpty(process_id)){
										this.StarFlowProcess(map, request);
									}
								}else{
									//判断只有退回的业务才能再次提交继续流程
									if(obj2[4].equals("1")){
										map2.put("ins_info_id", ywid);
										com.khnt.bpm.core.bean.Process  process = processManager.getServiceProcess(ywid);
										List<Activity> activities = activityManager.getCurrentServiceActivity(process.getBusinessId());
										if(activities.size()>0){
											map2.put("acc_id", activities.get(0).getId());
											map2.put("flow_num", activities.get(0).getActivityId());
										}
										map2.put("flag", "0");
										this.subFlowProcess(map2, request);
									}	
									
								}
								// start 添加设备预警处理记录 2014-11-05 11:55:00 update by GaoYa
								saveRecord(curUser, obj2);
								// end 添加设备预警处理记录 2014-11-05 11:55:00 update by GaoYa
								msg="提交成功！";
							}else{
								//break;
								msg="批量提交失败，设备名称："+obj2[2].toString()+"未设置业务流程，请联系系统管理员配置！";
								return msg;
							}
						}else{
							continue;
						}
					}else{
						continue;
					}
					//System.out.println(obj[0].toString()+obj[1].toString()+"----------");
				
					
					
					
				}
				
			}
			
		} else{
			String sql="select t.id tid,d.device_sort_code,d.device_name,t.is_flow,t.is_report_input,d.id did,d.inspect_next_date,"
					+ "t.advance_time,t.is_back,t.report_type  from TZSB_INSPECTION_INFO t, base_device_document d "
					+ "where d.id=t.fk_tsjc_device_document_id  and t.data_status <> '99' and t.fk_inspection_id='"+ids+"' ";
			List list =inspectionDao.createSQLQuery(sql).list();
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Object[] objArr = list.toArray();
					Object[] obj = (Object[]) objArr[i];
					
					if(obj[8]!=null){
						if(obj[8].toString().equals("1")){//如果等于1才提交 ，保检提交统一变换成0，提交前默认等于 1
							String device_sort_code = obj[1].toString();
							String device_big_class = device_sort_code.substring(0,1);
							if("7310".equals(device_sort_code)){
								device_big_class = "F";
							}
							String hql = "select t.flow_id from FLOW_SERVICE_CONFIG t,BASE_UNIT_FLOW t1 where t.id=t1.FK_FLOW_ID and t1.DEVICE_TYPE like'"+device_big_class+"%' and t1.CHECK_TYPE in ("+checktypes+") and t.org_id='"+org.getId()+"'";
							hql += " and t1.FK_REPORT_ID='"+obj[9].toString()+"'";
							
							List list2 = inspectionDao.createSQLQuery(hql).list(); 
							if(list2.size()>0){
								Object[] objArr2 = list2.toArray();
								//Object[] obj2= (Object[]) objArr2[i];
								flow_code = objArr2[0].toString();
								ywid=obj[0].toString();
								map.put("infoId", ywid);
								map.put("flowId", flow_code);
								map.put("status", "1");
								//System.out.println(obj[3]);
								if(obj[3]==null){
									// 先判断业务是否已经启动了流程
									// 业务流程表有数据就说明已经启动了流程，不再重新启动流程，没数据就启动流程
									String process_id = inspectionDao.getProcess(ywid);
									if(StringUtil.isEmpty(process_id)){
										this.StarFlowProcess(map, request);
									}
								}else{
									//if(obj[4].toString().equals("1")){
										map2.put("ins_info_id", ywid);
										com.khnt.bpm.core.bean.Process  process = processManager.getServiceProcess(ywid);
										List<Activity> activities = activityManager.getCurrentServiceActivity(process.getBusinessId());
										if(activities.size()>0){
											map2.put("acc_id", activities.get(0).getId());
											map2.put("flow_num", activities.get(0).getActivityId());
										}
										map2.put("flag", "0");
										this.subFlowProcess(map2, request);
										
									//}	
								}
								// start 添加设备预警处理记录 2014-11-05 11:55:00 update by GaoYa
								saveRecord(curUser, obj);
								// end 添加设备预警处理记录 2014-11-05 11:55:00 update by GaoYa
								msg="提交成功！";
							}else{
								msg="提交失败，设备名称："+obj[2].toString()+"未设置业务流程，请联系系统管理员配置！";
								return msg;
							}
						
							
						}else{
							continue;
						}
					}else{
						continue;
					}
					
					
				}
			}
			
		
		}
		
		return msg;
		/*//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set operation_status='1' where id =?",id[i] ).executeUpdate();
				
			}
			
		}else{
		
			inspectionDao.createSQLQuery("update  TZSB_INSPECTION  set operation_status='1' where id = ?", ids).executeUpdate();
			
		}*/
		
	}
	
	public void saveRecord(CurrentSessionUser user,Object[] obj2){
		String next_check_date = String.valueOf(obj2[6]);	// 设备下次检验日期
		String check_date = String.valueOf(obj2[7]);			// 预检日期
		try {
			Date next_date = null;
			Date chk_date = null;
			if (StringUtil.isNotEmpty(next_check_date) && !"null".equals(next_check_date)) {
				next_date = DateUtil.convertStringToDate("yyyy-MM-dd", next_check_date);
			}
			if (StringUtil.isNotEmpty(check_date) && !"null".equals(check_date)) {
				chk_date = DateUtil.convertStringToDate("yyyy-MM-dd", check_date);
			}
			
			Date cur_date = new Date();	// 当前日期
			if (next_date!=null && next_date.before(cur_date)) {
				QueryCondition qc=new QueryCondition(DeviceWarningStatus.class);
	        	qc.addCondition("fk_base_device_document_id", "=",  obj2[5].toString());
	        	qc.addCondition("active_flag", "=", '1');
	        	List<DeviceWarningStatus> dwslist=deviceWarningStatusService.query(qc);
	        	DeviceWarningStatus dws = null;
	        	if(dwslist.size()>0){
	        		dws = dwslist.get(0);
	        	}else{
	        		dws = new DeviceWarningStatus();	// 预警处理状态
	        	}
	        	dws.setDeal_status("100");	// 处理状态（100：已报检）
	    		dws.setDeal_time(cur_date);	// 处理日期
	    		dws.setFk_base_device_document_id(obj2[5].toString());	// 设备id
	    		dws.setActive_flag('1');	// 活动状态
	    		dws.setSend_status("0");	// 发送状态
	            dws.setInspection_date(chk_date);	// 预检日期
	            deviceWarningStatusDao.save(dws);
				
				DeviceWarningDeal record = new DeviceWarningDeal();	// 预警处理情况记录
				record.setDeal_man(user.getName());	// 处理人
				record.setDeal_man_id(user.getId());	// 处理人id
				record.setDeal_unit(user.getUnit().getOrgName()+"。");	// 处理单位
				record.setDeal_unit_id(user.getUnit().getId());			// 处理单位id
				record.setDeal_time(cur_date);							// 处理日期
				record.setFk_base_device_document_id(obj2[5].toString());	// 设备id
				record.setDeal_status("100");							// 处理状态（100：已报检）
				record.setSend_status("0"); 	// 数据传输状态（0：未传输 1：已传输）
				deviceWarningDealDao.save(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public void savePlan(Map<String, Object> map) throws Exception
    {
		
		
		
		String  id=map.get("device_id").toString();
		String op_ids=map.get("op_ids").toString();
		String check_ids=map.get("check_ids").toString();
		String op_name=map.get("op_name").toString();
		String check_name=map.get("check_name").toString();
		String inc_time = map.get("inc_time").toString();
		
		String ids="";
		//判断是否多个id
	     StringBuffer json = new StringBuffer();
				if(id.indexOf(",")!=-1){
					String temp[]=id.split(",");
				
					for (int i = 0; i < temp.length; i++) {
						
						json.append("'").append(temp[i] ).append("'");
						if(i!=temp.length-1){
							json.append(",");
						}
					
						
					}
					ids =json.toString();
				}else{
					ids=json.append("'").append(id).append("'").toString();
				}
		
				//inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set advance_time=to_date('"+inc_time+"','yyyy-MM-dd'), item_op_id='"+op_ids+"',check_op_id='"+check_ids+"',OP_NAME='"+op_name+"',CHECK_NAME='"+check_name+"',IS_PLAN='2' where id in("+ids+")").executeUpdate();
				inspectionDao.createSQLQuery("update  TZSB_INSPECTION_INFO  set advance_time=to_date('"+inc_time+"','yyyy-MM-dd'), item_op_id='"+op_ids+"',check_op_id='"+check_ids+"',IS_PLAN='2' where id in("+ids+")").executeUpdate();
    	
		
    }
	public void backINfo(String ids) throws Exception {
		// TODO Auto-generated method stub
		// 判断是否多个ID
		if (ids.indexOf(",") != -1) {

			String id[] = ids.split(",");

			for (int i = 0; i < id.length; i++) {

				inspectionDao
						.createSQLQuery(
								"update  TZSB_INSPECTION  set operation_status='99' where id =?",
								id[i]).executeUpdate();

			}

		} else {

			inspectionDao
					.createSQLQuery(
							"update  TZSB_INSPECTION  set operation_status='99' where id = ?",
							ids).executeUpdate();

		}

	}
	
    public static Date parse(String str, String pattern, Locale locale) {
        if(str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public static String format(Date date, String pattern, Locale locale) {
        if(date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }

    public HashMap<String, Object> getOrg(String code) throws Exception{	
    	
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	
    	String sql="from  DeviceDocument t,EnterInfo t1 where t.fk_company_info_use_id=t1.id and t.device_registration_code='"+code+"' and t.device_status!='99'";
    	
		List list =deviceDao.createQuery(sql).list();
		
//		List<SupervisionInspection> list=infoDao.createQuery("from SupervisionInspection where fk_inspection_info_id='"+ins_info_id+"'").list();
		
		
		if(list.size()>0){
			
			Object[]  obj = (Object[]) list.get(0);
			
			EnterInfo enter =(EnterInfo) obj[1];
			
			wrapper.put("id", enter.getId());
			
			wrapper.put("com_name", enter.getCom_name());
			
			wrapper.put("com_address", enter.getCom_address());
			
			wrapper.put("success",true);
			
		}else{
			
			wrapper.put("success",false);
			
		}
    	
		

		
		
		
		
		return wrapper;
		
	} 
    
    
    public  void send_data() throws Exception
 	{/*

    	Connection conn_o =ConnMJc.getConnection();;
	//本地数据库连接
     	Connection conn_m = Factory.getDB().getConnetion();

     	
     	boolean mState = false;
		boolean oState = false;
		// 获取当前自动提交状态
		mState = conn_m.getAutoCommit();
		oState = conn_o.getAutoCommit();
		
		conn_m.setAutoCommit(false);
     	conn_o.setAutoCommit(false);
     	
     	//查询需要更新的设备数据表
//     	String sql = "select t.* from base_device_document t where t.send_status='0' and t.device_sort_code like '3%' and  rownum<=10  order by  t.id asc";
//     	
//     	
//     	Statement deviceStatement = conn_m.createStatement();
//		ResultSet executedevice = deviceStatement.executeQuery(sql);
		
		
		String hql="from  DeviceDocument t where  t.send_status='0' and t.device_sort_code like '3%' and t.inspect_next_date is not null  order by  t.id asc";
		
	
    	
		List listDoc =deviceDao.createQuery(hql).setFirstResult(0).setMaxResults(200).list();
		

	
     	
//		System.out.println("设备台数："+executedevice.next()+"="+executedevice.getRow());
     	
     	
     	//查询需要更新的企业信息表
//     	String sql_c = "select * from tsjc_company_info t where t.send_status='0' ORDER BY t.id asc";
//     	
//     	Statement orgStatement = conn_m.createStatement();
//		ResultSet executeorg = orgStatement.executeQuery(sql_c);
//		
		String hqlCom="from  EnterInfo t where t.send_status='0'   order by  t.id asc";
		
		List listcom =deviceDao.createQuery(hqlCom).setFirstResult(200).setMaxResults(1).list();    
		
		
//     	String sql_war = "select * from tzsb_warning_status t where t.send_status='0' ORDER BY t.id asc";
//     	
//     	Statement warStatement = conn_m.createStatement();
//		ResultSet executewar = warStatement.executeQuery(sql_war);
     	
     	
 		
     
     	try {
     		
     		
     		
     		if(listcom.size()>0){
	 	    	
     				for(int i=0;i<listcom.size();i++){
     			
     					EnterInfo enter =(EnterInfo)listcom.get(i);
 		    	
 			
 			
 					if(enter!=null){
 						
 						String type = TransManager.comDeal(enter.getId(),enter,conn_o);
 						
 					
 						if(type.equals("true")){
 						
 						
	 						enter.setSend_status("0");
	 					
	 						enterDao.save(enter);
 						}
 						
 						
 						
 						
 						
 						String sql_rel = "select * from BASE_COM_TYPE_RELATION t  where t.com_id='"+enter.getId()+"'";
 						
 						//单位类型表

 						Statement relStatement = conn_m.createStatement();
						ResultSet executerel = relStatement.executeQuery(sql_rel);
 						
 						if(executerel.next()){
 							
 								EnterRelation relation = relationDao.get(executerel.getString("id"));
 								
 								String com_rela = TransManager.comRela(executerel.getString("id"),relation,enter.getId(),conn_o);	
 								
 							
 							
 							
 						}
 						relStatement.close();
 						executerel.close();
 						
 						
 						
 						}
 						
 					
 				
 	    	
			}
     		}
//     		orgStatement.close();
//     		executeorg.close();
     		

     		//设备同步
     		
     		if(listDoc.size()>0){
     			
     			
     			for(int i=0;i<listDoc.size();i++){
     			
     				
     				
     				DeviceDocument sendEntity =(DeviceDocument)listDoc.get(i);
     				
     			
					//如果设备存在同步到市局
					if(sendEntity!=null){
						
	 						
	 						String type = TransManager.transDeal(sendEntity.getId(),sendEntity,conn_o);
 						

	 						if(type.equals("true")){
	 					//改变传送状态
	 							sendEntity.setSend_status("0");
	 							deviceDao.save(sendEntity);
	 					
	 						}
	 				//设备参数表
	 						
	 						
//	 						String sql_el = "select * from BASE_DEVICE_ELEVATOR_PARA t where t.fk_tsjc_device_document_id='"+executedevice.getString("id")+"' ";
//					     	
//					     	Statement elStatement = conn_m.createStatement();
//							ResultSet executeel = elStatement.executeQuery(sql_el);
							
//							String hql_el="from  ElevatorPara t where t.fk_tsjc_device_document_id";
//					    	
//							List listDoc =deviceDao.createQuery(hql).list();
	 						
	 						Collection<ElevatorPara> co = sendEntity.getElevatorParas();
	 						
	 						List list = new ArrayList();
	 						
	 						list.addAll(co);
	 						
	 						if(list.size()>0){
	 							
	 							ElevatorPara para =	(ElevatorPara)list.get(0);
	 							
	 							
	 							String type_eleinfo = TransManager.transEleinfo(sendEntity.getId(),para.getId(),para,conn_o);
	 							
	 						}
	 						
					}	
	 					
							
//	 						
//	 						
//	 						if(executeel.next()){
//	 							
//	 							ElevatorPara ele = elevatorParaDao.get(executeel.getString("id"));
//	 							
//	 							
//	 							String type_eleinfo = TransManager.transEleinfo(executedevice.getString("id"),executeel.getString("id"),ele,conn_o);
//	 							
//	 							
//	 						}	
//	 						elStatement.close();
//	 						executeel.close();
	 						
 						
//	 				
	 					// 业务信息是否存在
	 						
							
							String hqlinc="from  InspectionInfo t where t.fk_tsjc_device_document_id='"+sendEntity.getId()+"'";
							
							List listInc =deviceDao.createQuery(hqlinc).list(); 
	 						
							
							
	 						
	 					//一台设备有多个业务信息	
	 						if(listInc.size()>0){
	 							
	 							InspectionInfo info  =(InspectionInfo)listInc.get(0) ;
	 					
	 							
	 				
	 							String type_incinfo = TransManager.transIncinfo(info.getId(),info,conn_o);
	 							
 							
	 								//检验信息是否存在
//			 						Inspection inc = info.getInspection();
//			 						
//			 						if(inc!=null){
//			 							
//			 							String type_inc = TransManager.transInc(inc.getId(),inc,conn_o);
//			 							
//
////		 								}
//			 							
//			 						}
	 							
	 									 						
	 				
	 						}

//	 						
//	 						
	 						
	 						
	 						}
						
	    				
//					
//	    	
				}
//     				
//     				
//     			}
     		
     		
     		
//     		if(executedevice.next()){
//		 	    	
//		 	    		
//		 		    	String flag = "yes";
//		 				boolean isCarry = false ;
//		 			
//		 					
//		 				
//
//		 					
//		 						
//		 				DeviceDocument sendEntity = deviceDao.get(executedevice.getString("id"));
//		 						
//		 					
//		 					//如果设备存在同步到市局
//		 					if(sendEntity!=null){
//		 						
//				 						
//				 						String type = TransManager.transDeal(executedevice.getString("id"),sendEntity,conn_o);
//			 						
//		
//				 						if(type.equals("true")){
//				 					//改变传送状态
//				 							sendEntity.setSend_status("1");
//				 							deviceDao.save(sendEntity);
//				 					
//				 						}
//				 				//设备参数表
//				 						
//				 						
//				 						String sql_el = "select * from BASE_DEVICE_ELEVATOR_PARA t where t.fk_tsjc_device_document_id='"+executedevice.getString("id")+"' ";
//								     	
//								     	Statement elStatement = conn_m.createStatement();
//										ResultSet executeel = elStatement.executeQuery(sql_el);
//									
//										
//				 						
//				 						
//				 						if(executeel.next()){
//				 							
//				 							ElevatorPara ele = elevatorParaDao.get(executeel.getString("id"));
//				 							
//				 							
//				 							String type_eleinfo = TransManager.transEleinfo(executedevice.getString("id"),executeel.getString("id"),ele,conn_o);
//				 							
//				 							
//				 						}	
//				 						elStatement.close();
//				 						executeel.close();
//				 						
//				 				//机电注册登记表	
////				 						NamedEntity sp_info = NamedEntity.newInstance("tsjc_device_special");
////				 						
////				 						sp_info.setConnection(conn_m);
////				 						sp_info.setValue("fk_tsjc_device_document_id", ReEntity.getStringValue("id",i));
////				 						sp_info.search();
////				 						
////				 						if(sp_info.getResultCount()>0){
////				 							
////				 							String type_spinfo = tran.transSpinfo(sp_info.getStringValue("id",0),sp_info,conn_o);
////				 							
////				 							
////				 						}
//				 						
//				 						
//				 				
//				 					// 业务信息是否存在
//				 						String sql_info = "select * from TZSB_INSPECTION_INFO t where t.fk_tsjc_device_document_id='"+executedevice.getString("id")+"' ";
//				 						
//				 						Statement infoStatement = conn_m.createStatement();
//										ResultSet executeinfo = infoStatement.executeQuery(sql_info);
//				 						
//										
//				 						
//				 					//一台设备有多个业务信息	
//				 						if(executeinfo.next()){
//				 							
//				 							InspectionInfo info  = infoDao.get(executeinfo.getString("id"));
//				 					
//				 							System.out.println(info);
//				 				
//				 							String type_incinfo = TransManager.transIncinfo(executeinfo.getString("id"),info,conn_o);
//				 							
//			 							
//				 								//检验信息是否存在
//						 						Inspection inc = info.getInspection();
//						 						
//						 						if(inc!=null){
//						 							
//						 							String type_inc = TransManager.transInc(inc.getId(),inc,conn_o);
//						 							
//
////					 								}
//						 							
//						 						}
//				 							
//				 									 						
//				 				
//				 						}
//				 						infoStatement.close();
//				 						executeinfo.close();
//				 						
//				 						
//				 						
//				 						
//				 						}
//		 						
//		 	    					conn_o.commit();
//		 					
//		 	    	
//		 				}
     		
//     		deviceStatement.close();
//     		executedevice.close();
//预警处理状态同步
     		
//     		if(executewar.next()){
//     			//for(int i = 0 ; i < executewar.getRow() ; i++) {
//     			
//     			//处理状态表
//     		
//     				
//					
//					
//					
//     				DeviceWarningStatus	war_status = statusDao.get(executewar.getString("id"));
//					
//					
//					
//					if(war_status!=null){
//						
//						String type_spinfo = TransManager.transWarStatus(executewar.getString("id"),war_status,conn_o);
//						if(type_spinfo.equals("true")){
//		 					//改变传送状态
//							
//							
//							war_status.setSend_status("1");
//							
//							statusDao.save(war_status);
//							
//		 						
//					}
//				//处理情况表	
//					
//					
//						String sql_deal = "select * from tzsb_warning_deal t where t.fk_tsjc_warning_status_id='"+executewar.getString("id")+"' ";
//				     	
//				     	Statement dealStatement = conn_m.createStatement();
//						ResultSet executedeal = dealStatement.executeQuery(sql_deal);
//						
//						
//					
//					
//					if(executedeal.next()){
//						
//						DeviceWarningDeal  war_deal = delDao.get(executedeal.getString("id"));
//				 			
//				 		String type_deal = TransManager.transWarDeal(executedeal.getString("id"),war_deal,conn_o);
//				 		
//				 		
//					
//					}
//					dealStatement.close();
//					executedeal.close();
//     			}
//     			
//     		}
     		
//     		warStatement.close();
//     		executewar.close();
 					
     		conn_m.commit();
     		conn_o.commit();
			
		} catch (Exception e)
		{
		
			try
			{
				conn_m.rollback();
				
				conn_o.rollback();
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
			e.printStackTrace();
			
		} finally
		{
			try
			{
				// 默认自动提交状态归位
				conn_m.setAutoCommit(mState);
				conn_o.setAutoCommit(oState);
				// 释放数据库连接
				Factory.getDB().freeConnetion(conn_m);
				ConnMJc.freeConnection(conn_o);
			} catch (Exception e)
			{
				e.printStackTrace();
				 
				
			}
		}
 	*/}
    
	
    
	public HashMap<String, Object>  getShowInfo() throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		try{
//		List list = infoDao.createQuery("from InspectionInfo  where flow_note_name = '报告领取' and rownum<50 order by advance_time desc ").list();
		List list = infoDao.createSQLQuery("select * from (select t.report_com_name, t.report_sn, decode(t1.check_type,'3','定期检验','2','监督检验','其他检验'), to_char(t.advance_time,'yyyy-MM-dd') from TZSB_INSPECTION_INFO t, TZSB_INSPECTION T1,(select report_com_name,max(id) id from  TZSB_INSPECTION_INFO where flow_note_name = '报告领取'group by report_com_name) t2 where t.fk_inspection_id = t1.id and t.id=t2.id and t.flow_note_name = '报告领取'  order by t.advance_time  desc) where rownum<50  ").list();
		
		
		

		
		Object o = list.get(0);
	
		map.put("flowData", list);
		map.put("success", true);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return map;
    }
	
    //报告录入不合格报告写入预警记录
	private void writeWaring(CurrentSessionUser user,String deviceId) throws Exception{
	
				QueryCondition qc=new QueryCondition(DeviceWarningStatus.class);
	        	qc.addCondition("fk_base_device_document_id", "=",  deviceId);
	        	qc.addCondition("active_flag", "=", '1');
	        	List<DeviceWarningStatus> dwslist=deviceWarningStatusService.query(qc);
	        	DeviceWarningStatus dws = null;
	        	if(dwslist.size()>0){
	        		dws = dwslist.get(0);
	        	}else{
	        		dws = new DeviceWarningStatus();	// 预警处理状态
	        	}

				Date cur_date = new Date();	// 当前日期
	        	dws.setDeal_status("104");	// 处理状态（104：已报检,不合格）
	    		dws.setDeal_time(cur_date);	// 处理日期
	    		dws.setFk_base_device_document_id(deviceId);	// 设备id
	    		dws.setActive_flag('1');	// 活动状态
	    		dws.setSend_status("0");	// 发送状态
	           // dws.setInspection_date(chk_date);	// 预检日期
	            deviceWarningStatusDao.save(dws);
				
				DeviceWarningDeal record = new DeviceWarningDeal();	// 预警处理情况记录
				record.setDeal_man(user.getName());	// 处理人
				record.setDeal_man_id(user.getId());	// 处理人id
				record.setDeal_unit(user.getUnit().getOrgName()+"。");	// 处理单位
				record.setDeal_unit_id(user.getUnit().getId());			// 处理单位id
				record.setDeal_time(cur_date);							// 处理日期
				record.setFk_base_device_document_id(deviceId);	// 设备id
				record.setDeal_status("104");							// 处理状态（104已报检,不合格）
				record.setSend_status("0"); 	// 数据传输状态（0：未传输 1：已传输）
				deviceWarningDealDao.save(record);
			}
		

	public List<Object> deviceCountByTanker(Date startDate, Date endDate) {
		List<Object> mapResule = new ArrayList<Object>();
		try {
			String totalCount= deviceDao.getDeviceTotal();//获取常压罐车设备数量
			String checkCount= deviceDao.getDeviceCheck();//获取常压罐车已经检验数量
			String uncheckCount=deviceDao.getDeviceUnCheck();//获取常压罐车未检验数量
			
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("totalCount", totalCount);
				map.put("checkCount", checkCount);
				map.put("uncheckCount", uncheckCount);
				mapResule.add(map);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return mapResule;
	} 
    
	public List<Object> deviceCountByMedia(Date startDate, Date endDate) {
		List<Object> mapResule = new ArrayList<Object>();
		try {
			List<CodeTableValue> cvlist = deviceDao.getMedia();//获取介质
			int total = 0;
			
			for (int j = 0; j < cvlist.size(); j++) {
				Map<String, String> map = new HashMap<String, String>();
				String count = deviceDao
						.deviceCountByMedia(cvlist.get(j).getValue(), startDate, endDate);
				map.put("count", count);
				total += Integer.parseInt(count);
				map.put("mediaCode", cvlist.get(j).getName());
				
				mapResule.add(map);
			}
				
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return mapResule;
	} 
	
public HashMap<String, Object>  getWeixinInfo() throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		try{
			
			
		List list = infoDao.createSQLQuery("select * from (select t.username,t.querytime,t.querycontent, t.returncontent from querymessageinfo t  order by t.querytime desc) where rownum<=11").list();
		
		List list2 = infoDao.createSQLQuery("select count(t.id) from querymessageinfo t").list();
		
		Object o = list.get(0);
	
		map.put("flowData", list);
		map.put("sum", list2.get(0).toString());
		map.put("success", true);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return map;
    }

	public List<ReportDraw> mobileReportDrawSave(HttpServletRequest request,Map<String, Object> data) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();  // 获取当前用户登录信息
		List<ReportDraw> list = new ArrayList<ReportDraw>();
		String imgString = data.get("sign_file").toString();
		String base64ImagePath = "";
		if(!StringUtil.isEmpty(imgString)){
			 // 上传图片  
			String path = request.getServletContext().getRealPath("/upload/sign/");
			String id = UUID.randomUUID().toString();
	        String fileName =id + ".txt"; 
	        File filepath = new File(path,fileName);
	        //判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	            filepath.getParentFile().mkdirs();
	        }
	        FileWriter fw;
	        try {
	        	fw = new FileWriter(filepath);
	        	BufferedWriter bw = new BufferedWriter (fw);
	    		bw.write(imgString);
	        	bw.flush();
	        	bw.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	    	} 
	        base64ImagePath = filepath.getAbsolutePath();
		}
		//自动生成一个批次号
		Date date = new Date();
		long num = date.getTime();
		String pulldownNumber = curUser.getId().substring(curUser.getId().length()-5, curUser.getId().length())+String.valueOf(num);
		
		JSONArray items = JSONArray.fromString(data.get("rows").toString());
		//List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
		for (int i = 0; i < items.length(); i++) {
			JSONObject obj = items.getJSONObject(i);
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(obj, Map.class); 
			ReportDraw reportDraw1 = new ReportDraw();
			reportDraw1.setIdcard(data.get("idcard").toString());
			reportDraw1.setJob_unit(map.get("report_com_name").toString());
			reportDraw1.setLinkmode(data.get("linkmode").toString());
			reportDraw1.setPulldown_op(data.get("pulldown_op").toString());
			InspectionInfo iif = new InspectionInfo();
			iif.setId(map.get("id").toString());
			reportDraw1.setInspectionInfo(iif);
			reportDraw1.setPulldown_time(DateUtil.convertStringToDate("yyyy-MM-dd", 
					DateUtil.getDateTime("yyyy-MM-dd",new Date())));
			reportDraw1.setReport_sn(map.get("report_sn").toString());
			reportDraw1.setData_status("0");	// 数据状态（0：正常  99：已删除）
			reportDraw1.setMdy_user_id(curUser.getId());
			reportDraw1.setMdy_user_name(curUser.getName());
			reportDraw1.setMdy_date(new Date());
			reportDraw1.setSign_file(base64ImagePath);
			reportDraw1.setDraw_sn(pulldownNumber);
//			reportDraw1.setPulldown_num(pulldownNumber);
			reportDraw1.setEvaluate(data.get("evaluate").toString());
			reportDrawDao.save(reportDraw1);
			list.add(reportDraw1);
			
			// 流程参数
			map.put("flag", "0");
			this.subFlowProcess(map, request);
	
		/*	20190326 多余，没用
		 * InspectionInfo inspectionInfo = inspectionInfoDao.get(map.get("id").toString());
			if (inspectionInfo != null) {
				InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
				inspectionInfoDTO.setId(inspectionInfo.getId());
				DeviceDocument deviceDocument = deviceService
						.get(inspectionInfo.getFk_tsjc_device_document_id());
				inspectionInfoDTO.setDevice_area_name(codeTablesDao
						.getDeviceAreaName(deviceDocument
								.getDevice_area_code())); // 安装区域
				inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code());
				inspectionInfoDTO
						.setReport_com_name(StringUtil
								.isNotEmpty(inspectionInfo
										.getReport_com_name()) ? inspectionInfo
								.getReport_com_name()
								: inspectionInfo.getInspection()
										.getCom_name()); // 报告书使用单位
				inspectionInfoDTOList.add(inspectionInfoDTO);
			}*/
		}
		return list;
	}
	public ReportDraw mobileReportDrawSave2(HttpServletRequest request, Map<String, Object> params) throws Exception {
		//保存签名文件
		String imgString = params.get("sign_file").toString();
		String base64ImagePath = "";
		if(!StringUtil.isEmpty(imgString)){
			 // 上传图片  
			String path = request.getServletContext().getRealPath("/upload/reportDraw/sign/");
			String id = UUID.randomUUID().toString();
	        String fileName =id + ".txt"; 
	        File filepath = new File(path,fileName);
	        //判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	            filepath.getParentFile().mkdirs();
	        }
	        FileWriter fw;
	        try {
	        	fw = new FileWriter(filepath);
	        	BufferedWriter bw = new BufferedWriter (fw);
	    		bw.write(imgString);
	        	bw.flush();
	        	bw.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	    	} 
	        base64ImagePath = filepath.getAbsolutePath();
		}
		// 获取当前用户登录信息
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();  
		//生成一个报告领取存档对象，并组装
		ReportDraw reportDraw = new ReportDraw();
		reportDraw.setIdcard(params.get("idcard").toString());
		reportDraw.setLinkmode(params.get("linkmode").toString());
		reportDraw.setPulldown_op(params.get("pulldown_op").toString());
		reportDraw.setEvaluate(params.get("evaluate").toString());
		reportDraw.setPulldown_time(new Date());
		reportDraw.setData_status("0");	// 数据状态（0：正常  99：已删除）
		reportDraw.setMdy_user_id(curUser.getId());
		reportDraw.setMdy_user_name(curUser.getName());
		reportDraw.setMdy_date(new Date());
		reportDraw.setSign_file(base64ImagePath);
		reportDrawDao.save(reportDraw);
		
		//查询报告进入下一个流程
		String report_ids = params.get("report_ids").toString().replace(",", "','");
		
		String sql = "select t1.id,v.activity_id,"+ //
	       "v.flow_num_id,"+  //
	       "t5.regional_name,"+  //
	       "t1.report_com_name,"+ //
	       "t1.report_sn "+ //
	  "from tzsb_inspection_info          t1,"+
	       "base_device_document          t2,"+
	       "base_company_info             t3,"+
	       "sys_org                       t4,"+
	       "base_administrative_divisions t5,"+
	      " base_reports                  t7,"+
	       "V_PUB_WORKTASK                v "+
	 "where t1.id = v.SERVICE_ID "+
	   "and v.STATUS = '0' "+
	   "and t4.id = t1.enter_unit_id(+) "+
	   "and t1.report_type = t7.id "+
	   "and t2.device_area_code = t5.regional_code(+) "+
	   "and t3.id = t2.fk_company_info_use_id(+) "+
	   "and t1.fk_tsjc_device_document_id = t2.id "+
	   "and t1.flow_note_id = v.flow_num_id "+
	   "and t1.data_status <> '99' "+
	   "and t1.id not in (select t8.fk_inspection_info_id "+
	                       "from tzsb_report_draw t8 "+
	                      "where t8.DATA_STATUS != '99') "+
	   "and v.activity_name = '报告领取' "+
	   "and v.handler_id = ? "+
	   "and t1.id in ('"+report_ids+"') ";
		List<Object[]> infos =  inspectionInfoDao.createSQLQuery(sql,curUser.getId()).list();
	
		List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
		
		for (Object[] obj : infos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj[0]);
			map.put("ins_info_id", obj[0]);
			map.put("regional_name", obj[3]);
			map.put("area_name", obj[3]);
			map.put("report_com_name", obj[4]);
			map.put("activity_id", obj[1]);
			map.put("acc_id", obj[1]);
			map.put("flow_num_id", obj[2]);
			map.put("flow_num", obj[2]);
			map.put("report_sn", obj[5]);
			// 流程参数
			map.put("flag", "0");
			this.subFlowProcess(map, request);
			InspectionInfo inspectionInfo = inspectionInfoDao.get(obj[0].toString());
			
			if (inspectionInfo != null) {
				inspectionInfo.setFk_report_draw_id(reportDraw.getId());
				inspectionInfo.setIs_draw("1");
				
				InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
				inspectionInfoDTO.setId(inspectionInfo.getId());
				DeviceDocument deviceDocument = deviceService
						.get(inspectionInfo.getFk_tsjc_device_document_id());
				inspectionInfoDTO.setDevice_area_name(codeTablesDao
						.getDeviceAreaName(deviceDocument
								.getDevice_area_code())); // 安装区域
				inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code());
				inspectionInfoDTO
						.setReport_com_name(StringUtil
								.isNotEmpty(inspectionInfo
										.getReport_com_name()) ? inspectionInfo
								.getReport_com_name()
								: inspectionInfo.getInspection()
										.getCom_name()); // 报告书使用单位
				inspectionInfoDTOList.add(inspectionInfoDTO);
			}
		}
		return reportDraw;
	}
    
	/**
	 * 验证报告是否领取
	 * @param fkInspectionInfoId
	 * @return
	 */
	public List<ReportDraw> queryReportDraw(String fkInspectionInfoId){
		String sql="from ReportDraw where inspectionInfo.id =? ";
		return inspectionDao.createQuery(sql, fkInspectionInfoId).list();
	}
	
	/**
	 * 查询批量领取报告编号
	 * @param fkInspectionInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryReportDrawAll(String str){
		String sql="select ID from tzsb_report_draw where FK_INSPECTION_INFO_ID in "+str+" ";
		Query query=inspectionDao.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	public List<Map<String, Object>> queryReportDrawSignFile(String fkInspectionInfoId){
		String sql="select b.id,b.SIGN_FILE from TZSB_INSPECTION_INFO a ,tzsb_report_draw b \n "
				+ "where a.id=b.FK_INSPECTION_INFO_ID=? ";
		Query query=inspectionDao.createSQLQuery(sql, fkInspectionInfoId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	public List<ReportDraw> mobileReportDrawSaveSgnFile(HttpServletRequest request,Map<String, Object> data) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();  // 获取当前用户登录信息
		//日期格式化
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		List<ReportDraw> list = new ArrayList<ReportDraw>();
		String imgString = data.get("sign_file").toString();
		String base64ImagePath = "";
		if(!StringUtil.isEmpty(imgString)){
			String id = UUID.randomUUID().toString();
	        String fileName1 =id + ".jpg"; 
	        String paths = request.getServletContext().getRealPath("/");
	        String pp=paths+"upload\\report\\sign\\"+fileName1;
			File filepath = new File(paths+"upload\\report\\sign\\"+fileName1);
			//判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	            filepath.getParentFile().mkdirs();
	        }
	        String ps=pp.replaceAll("\\\\", "\\/");
	        String str = imgString.toString().substring(22,imgString.toString().length()-1);
	        System.out.println(ps);
			Base64Utils.Base64ToImage(str,ps);
			base64ImagePath=paths+"upload\\report\\sign\\"+fileName1;
		}
		//自动生成一个批次号
		Date date = new Date();
		long num = date.getTime();
		String pulldownNumber = curUser.getId().substring(curUser.getId().length()-5, curUser.getId().length())+String.valueOf(num);
		
		Set<Entry<String, Object>> entrySet = data.entrySet();
		
		Iterator<Entry<String, Object>> it = entrySet.iterator();
		
		List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		while(it.hasNext()){
			Entry<String, Object> me = it.next();
			String key = me.getKey();
			String value = (String) me.getValue();
			map.put(key, value);
		}
		list1.add(map);
		for (int i = 0; i < list1.size(); i++) {
			String fkInspectionInfoId=list1.get(i).get("fkInspectionInfoId").toString();
			String reportDrawSignId=list1.get(i).get("reportDrawSignId").toString();
			String evaluate=list1.get(i).get("evaluate").toString();
			for(int k=0;k<fkInspectionInfoId.split(",").length;k++){
				List<ReportDraw> queryList=this.queryReportDraw(fkInspectionInfoId.split(",")[k].toString());
				if(queryList.size()>0){
					String drawId=queryList.get(0).getId();
					//String fkInspecInfoId = fkInspectionInfoId.split(",")[k].toString();
					ReportDraw reportDraw1=reportDrawService.get(drawId);
					reportDraw1.setPulldown_time(DateUtil.convertStringToDate("yyyy-MM-dd", 
					DateUtil.getDateTime("yyyy-MM-dd",new Date())));
					reportDraw1.setLinkmode(list1.get(i).get("linkmode").toString());
					reportDraw1.setPulldown_op(list1.get(i).get("pulldown_op").toString());
					reportDraw1.setMdy_user_id(curUser.getId());
					reportDraw1.setMdy_user_name(curUser.getName());
					reportDraw1.setMdy_date(new Date());
					reportDraw1.setSign_file(base64ImagePath);
//					reportDraw1.setPulldown_num(pulldownNumber);
					reportDraw1.setBorrow_sn(pulldownNumber);
					reportDraw1.setEvaluate(evaluate);
					reportDrawDao.save(reportDraw1);
					list.add(reportDraw1);
				}
			}
			//删除report_draw_sign表数据（报告领取消息推送，关联表信息）
			inspectionPayInfoService.deleteReportDrawSign(reportDrawSignId);
		}
		
		return list;
	}
	
	/**
	 * 报告领取签名保存
	 * @param request
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public ReportDrawSign mobileReportDrawSignSaveSgnFile(HttpServletRequest request,Map<String, Object> data) throws Exception {
		//日期格式化，获取准备要存放图片的文件夹
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		String dates=df.format(date);
		
		//插查询原来已签名图片并删除
		String reportDrawSignId = data.get("reportDrawSignId").toString();
		ReportDrawSign pojo = reportDrawSignService.get(reportDrawSignId);
		//获取图片存放的path
		String serverPath = request.getServletContext().getRealPath("/");
		String oldImgPath = (serverPath+pojo.getSignRelativeFile()).replaceAll("\\\\", "\\/");
		File oldFile = new File(oldImgPath);
		if(oldFile.exists()){
			oldFile.delete();
		}
		//保存新图片
		String signRelativeFile="upload\\report\\sign\\"+dates+"\\"+UUID.randomUUID().toString()+".jpg";//相对路径
		String newImgPath = serverPath+signRelativeFile;
		String imgString = data.get("sign_file").toString();
		saveImg(newImgPath,imgString);
		//更新领取表
		pojo.setSignRelativeFile(signRelativeFile);
		pojo.setSignFile(newImgPath);
		pojo.setEvaluate(data.get("evaluate").toString());
		//pojo.setPulldown_op(data.get("pulldown_op").toString());
		pojo.setPulldown_tel(data.get("linkmode").toString());
		pojo.setImgVersion((pojo.getImgVersion()==null?0:pojo.getImgVersion())+1);
		
		//内存存储保存操作
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		@SuppressWarnings("unchecked")
		ConcurrentHashMap<String, Object> draw = (ConcurrentHashMap<String, Object>)context.getAttribute("reportDraw");
		if(null == draw)
		{
			draw = new ConcurrentHashMap<String, Object>();
			context.setAttribute("reportDraw", draw);
		}
		draw.put(reportDrawSignId, reportDrawSignId);
		return pojo;
	}
	private void saveImg(String filePath,String imgString){
		File f = new File(filePath);
		//判断路径是否存在，如果不存在就创建一个
        if (!f.getParentFile().exists()) { 
        	f.getParentFile().mkdirs();
        }
        String str = imgString.toString().substring(22,imgString.toString().length()-1);
        filePath = filePath.replaceAll("\\\\", "\\/");
        //把字符集图片保存成ipg图片
		Base64Utils.Base64ToImage(str,filePath);
	}
	/**
	 * 给info表添加审核时间，表示报告被查看过。
	 */
	public void addExamineDate(String id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Employee emp = ((User)user.getSysUser()).getEmployee();
		String ids = "'"+id.replaceAll(",", "','")+"'";
		try{
			String sql="update TZSB_INSPECTION_INFO set examine_date=sysdate,examine_id='"+emp.getId()
					+"',examine_name='"+emp.getName()+"' where id in("+ids+")";
			infoDao.createSQLQuery(sql).executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	/**
	 * 给info表添加签发时间，表示报告被查看过。
	 */
	public void addIssueDate(String id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Employee emp = ((User)user.getSysUser()).getEmployee();
		String ids = "'"+id.replaceAll(",", "','")+"'";
		try{
			String sql="update TZSB_INSPECTION_INFO set issue_date=sysdate,issue_id='"+emp.getId()
					+"',issue_name='"+emp.getName()+"' where id in("+ids+")";
			infoDao.createSQLQuery(sql).executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	/**
	 * 新版移动app专用
	 * @param info
	 * @param doc
	 * @throws Exception
	 */
	 
	public void dealDeviceInfo(InspectionInfo info, DeviceDocument doc) throws Exception{
		if (doc != null && doc.getId() != null) {
			if (!"11111111111111111111111111111111".equals(doc.getId())) {
				if (doc.getDevice_sort_code().startsWith("3")) {// 判断设备类型是电梯
					if (StringUtil.isNotEmpty(info.getInspection_conclusion())) {
						//if (info.getInspection_conclusion().equals("合格") || info.getInspection_conclusion().equals("复检合格")) {
							// 合格或复检合格就返写下次检验日期         
							// 2016-09-22修改为无论结论是否合格，都返写  by GaoYa
							// 回写更新人和更新时间
							// doc.setLast_upd_by(emp.getName());
							// doc.setLast_upd_date(new Date());
							if (info.getAdvance_time() != null && !info.getAdvance_time().equals("")) {
								doc.setInspect_date(info.getAdvance_time());
							}
							// 判断检验部门不为空
							if (info.getCheck_unit_id() != null) {
								// 判断以前下次检验日期是否为空 为空就不插入下次检验日期
								if (!(info.getLast_check_time() == null && info.getLast_check_time().equals(""))) {
									doc.setInspect_next_date(info.getLast_check_time());
								}
							}
						//}
							
						// 返写设备二维码编号
						if (StringUtil.isNotEmpty(info.getDevice_qr_code())) {
							doc.setDevice_qr_code(info.getDevice_qr_code().trim());
						}
						// 返写设备使用登记证编号
						if (StringUtil.isNotEmpty(info.getRegistration_num())) {
							doc.setRegistration_num(info.getRegistration_num().trim());
						}
					}
				} else {
					// 回写更新人和更新时间
					// doc.setLast_upd_by(emp.getName());
					// doc.setLast_upd_date(new Date());
					if (info.getAdvance_time() != null && !info.getAdvance_time().equals("")) {
						doc.setInspect_date(info.getAdvance_time());
					}
					// 判断检验部门不为空
					if (info.getCheck_unit_id() != null) {
						// 判断以前下次检验日期是否为空 为空就不插入下次检验日期
						if (info.getLast_check_time()!=null) {
							doc.setInspect_next_date(info.getLast_check_time());
						}
						// 判断锅炉和压力容器检验结论
						if (doc.getDevice_sort_code().startsWith("1")
								|| doc.getDevice_sort_code().startsWith("2")) {
							if (StringUtil.isNotEmpty(info.getInspection_conclusion())) {
								if (!info.getInspection_conclusion().equals("停止运行")
										&& !info.getInspection_conclusion().equals("5级")
										&& !info.getInspection_conclusion().equals("Ⅳ级")) {
									if (info.getLast_check_time() == null || info.getLast_check_time().equals("")) {
										doc.setInspect_next_date(null);
									}
								}
							}
						}
					}
				}
				doc.setSend_status("0");
				// 报告签发后，将设备状态设置为在用状态
				doc.setDevice_status("2"); // 2：在用
				deviceDao.save(doc);
			}
		}
	}
	/**
	 * 新版移动app专用
	 * @param request
	 * @param obj
	 * @param formData
	 * @param info
	 * @throws Exception
	 */
	public synchronized void flowProcess(HttpServletRequest request,JSONObject obj,JSONObject formData, InspectionInfo info) throws Exception{
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
			paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, obj.getString("v_activityid"));
			paramMap.put(FlowExtWorktaskParam.SERVICE_ID,info.getId());
			
			String flag = obj.getString("flag");
			JSONObject dataBus = new JSONObject();
			try {
				String cj = obj.getString("cj");
				//判断是否是产品监检流程
				if(StringUtil.isNotEmpty(cj))
				{
					if("1".equals(flag)){
						 //flag=0 表示直接走到报告打印的步骤
						 dataBus.put("flag", 0);
					}else{
						 //flag=1表示直接走到报告审核的步骤
						 dataBus.put("flag", 1);
					}
				}
			}catch(Exception e) {
				//e.printStackTrace();
				System.out.println("info not cj >>>"+e.getMessage());
			}
			if((!"0".equals(flag)&&!"1".equals(flag))){
				//數據總線獲取的指定下一步操作人
				JSONArray  pts = new JSONArray();
				JSONObject pt = new JSONObject();
				pt.put("id", obj.getString("next_op_id"));
				pt.put("name", obj.getString("next_op_name"));
				pts.put(pt);
				dataBus.put("paticipator", pts);
			}
			paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
			//提交流程
			Map<String, Object> flowMap = flowExtManager.submitActivity(paramMap);
			//获取下一步流程步骤
			List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
			
			String currentActivity = info.getFlow_note_name();
			String nextActivityId = list.get(0).getActivityId();
			String nextActivity = list.get(0).getName();
			//写入日志
			String op_conclusion = "从"+currentActivity+"环节进入"+nextActivity+"环节。";
			op_conclusion += StringUtil.isEmpty(formData.getString("revise_remark"))?"\"结论：通过\\n无\"":"结论：通过\\n"+StringUtil.isEmpty(formData.getString("revise_remark"));
			
			try {
				logService.setLogs(info.getId(), currentActivity, op_conclusion, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
				if ("报告录入".equals(currentActivity)) {
					String to_user_name = "提交至" + obj.getString("next_op_name");
					logService.setLogs(info.getId(), "提交报告审核", to_user_name, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
				}
				if("报告审核".equals(currentActivity)){
					String to_user_name = "提交至" + obj.getString("next_op_name");
					logService.setLogs(info.getId(), currentActivity+"提交", to_user_name, user.getId(), user.getName(), new Date(), request.getRemoteAddr());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			info.setFlow_note_id(nextActivityId);
			info.setFlow_note_name(nextActivity);
			info.setIs_back("0");//提交后状态变成0
			
	}
	
	
	/**
	 * 新版移动app专用
	 * @param info
	 * @param ddoc
	 * @return
	 * @throws Exception
	 */
	public String getBigClass(InspectionInfo info,DeviceDocument ddoc) throws Exception
	{
		String bigClass = "";
		String deviceSortCode = ddoc.getDevice_sort_code();
		String deviceSort = ddoc.getDevice_sort();
		//这里不能改成ddoc.getId(),ddoc不一定存在
		if ("11111111111111111111111111111111".equals(info.getFk_tsjc_device_document_id())) {
			// 获取监检业务信息
			InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService.getByInfoId(info.getId());
			if(inspectionZZJDInfo != null){
				if (StringUtil.isNotEmpty(inspectionZZJDInfo.getDevice_type_code())) {
					if ("7310".equals(inspectionZZJDInfo.getDevice_type_code())) {
						bigClass = "F";
					} else {
						// 获取设备类别前缀（大类）
						bigClass = inspectionZZJDInfo.getDevice_type_code().substring(0, 1);
					}
				}
			}
		}
		else if(StringUtil.isNotEmpty(deviceSortCode))
		{
			if("7310".equals(deviceSortCode)){
				bigClass = "F";
			}else if("2610".equals(deviceSortCode)){
				bigClass = "0";
			}else{
				bigClass = deviceSortCode.substring(0, 1);
			}
		}
		else if(StringUtil.isNotEmpty(deviceSort))
		{
			if("7310".equals(deviceSort)){
				bigClass = "F";
			}else if("2600".equals(deviceSort)){
				bigClass = "0";
			}else{
				bigClass = deviceSort.substring(0, 1);
			}
		}
		return bigClass;
	}
	/**
	 * 新版移动app专用
	 * @param info
	 * @param deviceBigClass
	 * @param currentOpDate
	 * @throws Exception
	 */
	public void validateEnterTime(InspectionInfo info,String deviceBigClass,Date currentOpDate) throws Exception 
	{
		Date enter_date = info.getEnter_time(); // 编制日期
		if (enter_date == null) {
			if ("3".equals(deviceBigClass) 
					|| "4".equals(deviceBigClass) 
					|| "5".equals(deviceBigClass)
					|| "6".equals(deviceBigClass) 
					|| "9".equals(deviceBigClass)) {
				// 编制日期为空时，默认设置为检验日期+1天
				String advance_time = DateUtil.format(info.getAdvance_time(), "yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", advance_time));
				// 编制日期（机电五部2015-08-24要求，编制日期=检验日期+1天）
				calendar.add(Calendar.DATE, 1);
				enter_date = calendar.getTime();
			} else {
				enter_date = DateUtil.convertStringToDate("yyyy-MM-dd",
						DateUtil.format(info.getAdvance_time(), "yyyy-MM-dd"));
			}
		} else {
			enter_date = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.format(enter_date, "yyyy-MM-dd"));
		}
		if ("3".equals(deviceBigClass) && currentOpDate.before(enter_date)) {
			throw new Exception(info.getReport_sn() + "审核日期不能早于编制日期，请检查！");
		}
	}
	
	/**
	 * 异步提交流程
	 * @param map
	 * @param request
	 */
	/**
	 * @param map
	 * @param request
	 * @throws Exception 
	 */
	public void subFlowProcessNew2(HashMap<String, Object> map, HttpServletRequest request) throws Exception {
		Object activity = null;
		String infoId = map.get("infoId").toString();
		
			
			 activity = inspectionDao.getActivtyInfo(infoId);
			if(activity==null) {
				throw new KhntException("查询流程信息失败！");
			}else{
				Object [] activitys = (Object[]) activity;
				String acc_id = activitys[1].toString();
				
	
				String flag = map.get("flag").toString();
				
				Map<String,Object> paramMap = new HashMap();
				
				JSONObject dataBus = new JSONObject();
				
				if((!"0".equals(flag)&&!"1".equals(flag))){
				
					String next_sub_op  = map.get("next_sub_op").toString();
					
					String next_op_name  = map.get("next_op_name").toString();
					
					//數據總線獲取的指定下一步操作人
					JSONArray  pts = new JSONArray();
					JSONObject pt = new JSONObject();
					pt.put("id", next_sub_op);
					pt.put("name", next_op_name);
					pts.put(pt);
				
					dataBus.put("paticipator", pts);
	
					paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
					
				}
				//判断是否是产品监检流程
				if(map.get("cj")!=null){
				
					if("1".equals(map.get("flag").toString())){
							
							 
							 //flag=0 表示直接走到报告打印的步骤
							 dataBus.put("flag", 0);
							 
							 
							 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
						}else{
						
							 //flag=1表示直接走到报告审核的步骤
							 dataBus.put("flag", 1);
							 paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
						}
				}
				
				
				
				paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, acc_id);
				
				paramMap.put(FlowExtWorktaskParam.SERVICE_ID,infoId);
				paramMap.put(FlowExtWorktaskParam.BPM_USER,new BpmUserImpl(map.get("userid").toString(), map.get("username").toString(), null, null, null));
				
			
					Map<String, Object> flowMap = flowExtManager.submitActivity(paramMap);
				
			
				//获取下一步流程步骤
					List<Activity> list = (List) flowMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
					
					
					
					//写入日志
					String step_name = activitys[0].toString();
					
					String op_conclusion = "从"+step_name+"环节进入"+list.get(0).getName()+"环节。";
					 	
					String revise_remark = map.get("revise_remark")==null?"":map.get("revise_remark").toString();
					
					if(!"".equals(revise_remark)){
						
						op_conclusion=op_conclusion +map.get("revise_remark").toString();
						
					}
					//CurrentSessionUser user = SecurityUtil.getSecurityUser(); 

					infoDao.saveSubFlag(infoId,list.get(0).getActivityId(),list.get(0).getName(),"0"); 
					try {
					
						logService.setLogs(infoId, step_name, op_conclusion, map.get("userid").toString(), map.get("username").toString(), new Date(), request.getRemoteAddr());
						if ("报告录入".equals(step_name)) {
							String to_user_name = "提交至"+map.get("next_op_name").toString();
							logService.setLogs(infoId, "提交报告审核", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
						}
						if("报告审核".equals(step_name)){
							String to_user_name = "提交至"+map.get("next_op_name").toString();
							logService.setLogs(infoId, step_name+"提交", to_user_name, map.get("userid").toString(),  map.get("username").toString(), new Date(), request.getRemoteAddr());
						} 
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			}

		

	}
}


