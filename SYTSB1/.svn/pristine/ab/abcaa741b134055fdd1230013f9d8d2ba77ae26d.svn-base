package com.lsts.org.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.dao.EmployeeDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.dao.EmployeeCertDao;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.bean.Contract;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.humanresources.service.ContractManager;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.dao.EnterSearchDao;
import com.lsts.statistics.bean.DeviceCountDTO;
import com.lsts.statistics.dao.AnalyseDao;
import com.scts.cspace.file.bean.FileCache;
import com.scts.cspace.log.bean.SearchLog;
import com.scts.cspace.log.dao.SearchLogDao;
import com.scts.cspace.resource.bean.QueryResourceInfo;
import com.scts.cspace.resource.service.TjyptResourceInfoService;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.service.TjyptResourceSpaceService;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.dao.MaintenanceInfoDao;

import util.ReportUtil;

@Service("enterSearchService")
public class EnterSearchService extends EntityManageImpl<EnterInfo, EnterSearchDao>{
	 private static Calendar calS=Calendar.getInstance();  
	 private static Pattern   p   =   Pattern.compile("\\d{4}-\\d{2}-\\d{2}");//定义整则表达式  
	@Autowired
	private EnterSearchDao enterSearchDao;
	@Autowired
	private InspectionInfoDao inspectionInfoDao; 
	@Autowired
	private EmployeeBaseDao employeeBaseDao; 
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private EmployeeCertDao employeeCertDao;
	@Autowired
	private SearchLogDao searchLogDao;
	@Autowired
	private TjyptResourceInfoService tjyptResourceInfoService;
	@Autowired
	private AnalyseDao analyseDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private MaintenanceInfoDao maintenanceInfoDao;
	@Autowired
	private TjyptResourceSpaceService spaceService; 
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	
	/**
	 * 查询使用单位
	 * author pingZhou
	 * @param com_name
	 * @param map
	 * @return
	 */
	public HashMap<String, Object> searchUseByComname(String com_name) {
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<Object> comlist = enterSearchDao.getUseComByName(com_name);
		int l = comlist.size();
		/*if(comlist.size()>10){
			l=10;
		}*/
		for (int i = 0; i < l; i++) {
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			Object[] obj = (Object[]) comlist.get(i);
			/*map1.put("com_id", (obj[0]==null)?"":obj[0]);
			map1.put("com_name", (obj[1]==null)?"":obj[1]);
			map1.put("device_count", (obj[2]==null)?"":obj[2]);*/
			JSONObject s = new JSONObject();
			//list.add(map1);
			s.put("com_id", (obj[0]==null)?"":obj[0]);
			s.put("com_name", (obj[1]==null)?"":obj[1]);
			s.put("device_count", (obj[2]==null)?"":obj[2]);
			list.add(s);
			
		}
		map.put("comList", list);
		
		return map;
	}
	
	/**
	 * 查询维保单位信息
	 * author pingZhou
	 * @param com_name
	 * @return
	 */
	public HashMap<String, Object> searchMaintainByComname(String com_name) {
		HashMap<String, Object> map =  new HashMap<String, Object>();
			List<JSONObject> list = new ArrayList<JSONObject>();
			List<Object> comlist = enterSearchDao.getMaintainComByName(com_name);
			int l = comlist.size();
			/*if(comlist.size()>10){
				l=10;
			}*/
			for (int i = 0; i < l; i++) {
				JSONObject map1 = new JSONObject();
				Object[] obj = (Object[]) comlist.get(i);
				map1.put("com_id", (obj[0]==null)?"":obj[0]);
				map1.put("com_name", (obj[1]==null)?"":obj[1]);
				map1.put("device_count", (obj[2]==null)?"":obj[2]);
				list.add(map1);
				
			}
			map.put("comList", list);
			
			return map;
		}
	public HashMap<String, Object> searchAll(String name, HashMap<String, Object> map, String view1) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(view1==null||StringUtil.isEmpty(view1)){
			//记录查询日志,“更多”进入时不再重复记录
			SearchLog log = new SearchLog();
			log.setOp_id(user.getId());
			log.setOp_name(user.getName());
			log.setOp_time(new Date());
			log.setWord(name);
			searchLogDao.save(log);
		}
		//查云平台数据 1个人空间 9 院空间 7 部门空间
		//院空间
		HashMap<String, Object> ymap1 = tjyptResourceInfoService.queryResource(name,"9");
		List<QueryResourceInfo> ylist1 = (List<QueryResourceInfo>) ymap1.get("queryInfo");
		List<JSONObject> ylist1j = new ArrayList<JSONObject>();
		for (int i = 0; i < ylist1.size(); i++) {
			QueryResourceInfo info = ylist1.get(i);
			ylist1j.add(info.to_Json());
		}
		
		List<FileCache> ylist11 = (List<FileCache>) ymap1.get("dbplist");
		List<JSONObject> ylist11j = new ArrayList<JSONObject>();
		for (int i = 0; i < ylist11.size(); i++) {
			FileCache info = ylist11.get(i);
			ylist11j.add(info.to_Json());
		}
		map.put("cloadList11", ylist11j);
		map.put("cloadList11C", ylist11j.size());
		//部门空间
		HashMap<String, Object> ymap2 = tjyptResourceInfoService.queryResource(name, "7");
		List<QueryResourceInfo> ylist2 = (List<QueryResourceInfo>) ymap2.get("queryInfo");
		List<JSONObject> ylist2j = new ArrayList<JSONObject>();
		for (int i = 0; i < ylist2.size(); i++) {
			QueryResourceInfo info = ylist2.get(i);
			ylist2j.add(info.to_Json());
		}
		map.put("cloadList1", ylist1j);
		map.put("cloadList1C", ylist1j.size());
		map.put("cloadList2", ylist2j);
		map.put("cloadList2C", ylist2j.size());
		
		
		
		String view = "";
		String userId = "";
		List<EmployeeBase> empList =employeeBaseDao.queryEmployeesByNameAndStatus(name, "4");
		if(empList.size()>1){
			empList =employeeBaseDao.queryEmployeesByNameAndDepart(name, "4");
		}
		//如果查出人员则检验人员权限
		if(empList.size()==0){
			empList =employeeBaseDao.queryEmployeesByNameAndStatus(name, "3");
		}
		Boolean role = false;
		Boolean rsrole = false;
		Boolean flag = false;
		if(name.equals(user.getName())){
			role = true;
			rsrole = true;
			flag = true;
		}else if(empList.size()>0){
			
			for (int i = 0; i < empList.size(); i++) {
				flag = true;
				EmployeeBase empb = empList.get(i);
				String orgName = user.getDepartment().getOrgName();
				String sql = "select count(r.id) from sys_user u,sys_role r,sys_user_role ur where"
						+ " u.id = ur.user_id and ur.role_id = r.id and (r.name like '%"+empb.getWorkDepartmentName()
						+"%' or r.name='院长' or r.name='财务查看') and u.id = ?";
				List<Object> roleList = enterSearchDao.createSQLQuery(sql,user.getId()).list();
				if(Integer.parseInt(roleList.get(0).toString())>0){
					role = true;
				}
			}
			
			String sql1 = "select o.org_name from sys_org o ,sys_user u where u.org_id = o.id and u.EMPLOYEE_ID = ?";
			List<Object> orgList = enterSearchDao.createSQLQuery(sql1,empList.get(0).getId()).list();
			if(orgList.size()>0){
				flag = true;
				String orgName = user.getDepartment().getOrgName();
				String sql = "select count(r.id) from sys_user u,sys_role r,sys_user_role ur where"
						+ " u.id = ur.user_id and ur.role_id = r.id and (r.name like '%"+orgList.get(0).toString()
						+"%' or r.name='院长' or r.name='财务查看') and u.id = ?";
				List<Object> roleList = enterSearchDao.createSQLQuery(sql,user.getId()).list();
				if(Integer.parseInt(roleList.get(0).toString())>0){
					role = true;
				}
				
			}
			
			String sql2 = "select count(r.id) from sys_user u,sys_role r,sys_user_role ur where"
					+ " u.id = ur.user_id and ur.role_id = r.id and r.name='人事查看'  and u.id = ?";
			List<Object> roleList2= enterSearchDao.createSQLQuery(sql2,user.getId()).list();
			if(Integer.parseInt(roleList2.get(0).toString())>0){
				rsrole = true;
			}
		}
		
		//人员信息
		if(empList.size()>0&&flag){
			for (int i = 0; i < empList.size(); i++) {
				//取人员其他信息
				//检验信息
				List<JSONObject>  inspList = searchInspectByUser(empList.get(i).getId());
				map.put("inspList"+i, inspList);
				map.put("inspListC"+i, inspList.size());
				//合同信息
				List<Contract> contracts =  contractManager.getByEmpId(empList.get(i).getId());
				map.put("contracts"+i, contracts);
				map.put("contractsC"+i, contracts.size());
				//证书信息
				List<EmployeeCert> cartList = employeeCertDao.queryEmployeeCertByBasicId(empList.get(i).getId());
				
				//查询证书附件
				for (int j = 0; j < cartList.size(); j++) {
					EmployeeCert cert  = cartList.get(j);
					String sqlFile = "select t.id,t.file_name from PUB_ATTACHMENT t where t.business_id=?";
					List<Object> list = employeeCertDao.createSQLQuery(sqlFile, cert.getId()).list();
					String certFile = null;
					String certFileName = null;
					for (int k = 0; k < list.size(); k++) {
						Object [] obj = (Object[]) list.get(k);
						if(certFile==null){
							certFile = obj[0].toString();
							certFileName = obj[1].toString();
						}else{
							certFile = certFile + "," + obj[0].toString();
							certFileName = certFileName + "," + obj[1].toString();
						}
					}
					cert.setCert_file(certFile);
					cert.setCert_file_name(certFileName);
				}
				map.put("cartList"+i, cartList);
				map.put("cartListC"+i, cartList.size());
			}
			view = "app/research/ser_user_list";
			map.put("emp", empList.get(0));
			map.put("view", view);
			map.put("role",role);
			map.put("rsrole",rsrole);
			return map;
		}
		//使用单位信息
		HashMap<String, Object> map1 = searchUseByComname(name);
		List<JSONObject> list1 = (List<JSONObject>) map1.get("comList");
		map.put("comListC", list1.size());
		map.put("comList", list1);
		//维保单位信息
		HashMap<String, Object> map2 = searchMaintainByComname(name);
		List<JSONObject> list2 = (List<JSONObject>) map2.get("comList");
		map.put("maintainComListC", list2.size());
		map.put("maintainComList", list2);
		
		if(list1.size()>0||list2.size()>0){
			view = "app/research/ser_com_list";
		}
		
		map.put("view", view);
		return map;
	}
	
	/**
	 * 查人员出具的报告信息
	 * author pingZhou
	 * @param com_name
	 * @return
	 */
	public List<JSONObject> searchInspectByUser(String userId) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		
			String hql = "select t.id, t.report_com_name,t.advance_time,i.check_type,"
					+ "	d.device_name,o.org_name,t.check_op_name,r.report_name,t.enter_op_name "
					+ "	from TZSB_INSPECTION_INFO t, sys_org o, base_device_document d, "
					+ "	TZSB_INSPECTION i,base_reports r where t.data_status <> '99' "
					+ "	and d.id = t.fk_tsjc_device_document_id "
					+ "	and i.id = t.fk_inspection_id "
					+ "	and t.check_unit_id = o.id(+) "
					+ "	and r.id = t.report_type and "
					+ "	t.enter_op_id = ?  order by t.advance_time desc nulls last";
			List<Object> comlist = inspectionInfoDao.createSQLQuery(hql,userId).list();
			for (int i = 0; i < comlist.size(); i++) {
				JSONObject  s = new JSONObject(); 
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				Object[] obj = (Object[]) comlist.get(i);
				s.put("insp_id", (obj[0]==null)?"":obj[0].toString());
				s.put("com_name", (obj[1]==null)?"":obj[1].toString());
				s.put("invoice_date", (obj[2]==null)?"":obj[2].toString());
				s.put("check_type", (obj[3]==null)?"":obj[3].toString());
				s.put("device_name", (obj[4]==null)?"":obj[4].toString());
				s.put("org_name", (obj[5]==null)?"":obj[5].toString());
				String op_name = (obj[6]==null)?"":obj[6].toString();
				String name = (obj[8]==null)?"":obj[8].toString();
				if(op_name.indexOf(name)!=-1){
					int n = op_name.indexOf(name);
					int l1 = name.length();
					String names =  op_name.substring(0,n)
							+"<p style='color: red;text-align: left;display:inline; '>"
					+op_name.substring(n,n+l1)+"</p>"
							+op_name.substring(n+l1,op_name.length());
					s.put("check_op_name", names);
				}else{
					s.put("check_op_name", (obj[6]==null)?"":obj[6].toString());
				}
				
				s.put("report_name", (obj[7]==null)?"":obj[7].toString());
				list.add(s);
				
			}
			return list;
		}

	/**
	 * 查询设备的检验信息
	 * author pingZhou
	 * @param device_id
	 * @return
	 */
	public HashMap<String, Object> searchInspectionByDevice(String device_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String hql = "select t.id,t.report_com_name, t.sn, t.report_sn,  t.advance_time,  r.report_name, t.check_op_name,"
						+ "  o.org_name,  t.inspection_conclusion"
						 +"  from TZSB_INSPECTION_INFO t, base_reports r,sys_org o"
						 +"  where r.id = t.report_type  and o.id = t.check_unit_id"
						+ "	 and t.fk_tsjc_device_document_id=?  and t.data_status<>'99' order by t.advance_time desc nulls last";
				List<Object> comlist = inspectionInfoDao.createSQLQuery(hql,device_id).list();
				System.out.println("=----------------------------"+device_id);
				for (int i = 0; i < comlist.size(); i++) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					Object[] obj = (Object[]) comlist.get(i);
					map1.put("insp_id", (obj[0]==null)?"":obj[0].toString());
					map1.put("com_name", (obj[1]==null)?"":obj[1].toString());
					map1.put("sn", (obj[2]==null)?"":obj[2].toString());
					map1.put("report_sn", (obj[3]==null)?"":obj[3].toString());
					map1.put("advance_time", (obj[4]==null)?"":obj[4].toString());
					map1.put("report_name", (obj[5]==null)?"":obj[5].toString());
					map1.put("check_op_name", (obj[6]==null)?"":obj[6].toString());
					map1.put("org_name", (obj[7]==null)?"":obj[7].toString());
					map1.put("inspection_conclusion", (obj[8]==null)?"":obj[8].toString());
					list.add(map1);
					
				}
			map.put("inspList", list);
		return map;
	}

	public HashMap<String, Object> searchDeviceByCom(String com_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String hql = "select t.id, t.registration_num, t.device_registration_code, t.device_sort,"
					       +"	t.device_name, t.device_model,  t.device_sort_code, com.id com_id,"
					       +"	(select count(info.id) from tzsb_inspection_info info"
					       + " 	where info.data_status<>'99' and info.fk_tsjc_device_document_id = t.id) report_count "
					       +"	from BASE_DEVICE_DOCUMENT t, base_company_info com"
					       +" 	where com.id = t.fk_company_info_use_id and t.device_status<>'99' and com.id=?";
				List<Object> comlist = inspectionInfoDao.createSQLQuery(hql,com_id).list();
				for (int i = 0; i < comlist.size(); i++) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					Object[] obj = (Object[]) comlist.get(i);
					map1.put("id", (obj[0]==null)?"":obj[0].toString());
					map1.put("registration_num", (obj[1]==null)?"":obj[1].toString());
					map1.put("device_registration_code", (obj[2]==null)?"":obj[2].toString());
					map1.put("device_sort", (obj[3]==null)?"":obj[3].toString());
					map1.put("device_name", (obj[4]==null)?"":obj[4].toString());
					map1.put("device_model", (obj[5]==null)?"":obj[5].toString());
					map1.put("device_sort_code", (obj[6]==null)?"":obj[6].toString());
					map1.put("com_id", (obj[7]==null)?"":obj[7].toString());
					map1.put("report_count", (obj[8]==null)?"":obj[8].toString());
					list.add(map1);
					
				}
			map.put("deviceList", list);
		return map;
	}

	public HashMap<String, Object> searchDeviceByMCom(String com_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String hql = "select t.id, t.registration_num, t.device_registration_code, t.device_sort,"
					       +"	t.device_name, t.device_model,  t.device_sort_code, com.id com_id,"
					       +"	(select count(info.id) from tzsb_inspection_info info"
					       + "	 where info.data_status<>'99' and info.fk_tsjc_device_document_id = t.id) report_count"
					       +"	from BASE_DEVICE_DOCUMENT t, base_company_info com"
					       +" 	where com.id = t.FK_MAINTAIN_UNIT_ID and t.device_status<>'99' and com.id=?";
				List<Object> comlist = inspectionInfoDao.createSQLQuery(hql,com_id).list();
				for (int i = 0; i < comlist.size(); i++) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					Object[] obj = (Object[]) comlist.get(i);
					map1.put("id", (obj[0]==null)?"":obj[0].toString());
					map1.put("registration_num", (obj[1]==null)?"":obj[1].toString());
					map1.put("device_registration_code", (obj[2]==null)?"":obj[2].toString());
					map1.put("device_sort", (obj[3]==null)?"":obj[3].toString());
					map1.put("device_name", (obj[4]==null)?"":obj[4].toString());
					map1.put("device_model", (obj[5]==null)?"":obj[5].toString());
					map1.put("device_sort_code", (obj[6]==null)?"":obj[6].toString());
					map1.put("com_id", (obj[7]==null)?"":obj[7].toString());
					map1.put("report_count", (obj[8]==null)?"":obj[8].toString());
					list.add(map1);
					
				}
			map.put("deviceList", list);
		return map;
	}
	
	
	/**
	 * 查询个人的年度统计数据
	 * author pingZhou
	 * @param startDate
	 * @param endDate
	 * @param org_id
	 * @return
	 */
		public HashMap<String, Object> annualStaCountByUser(String startDate,
				String endDate, String user_id,HttpServletRequest request) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				
			
			String year = startDate.substring(0, 4);
			//普通人员权限
			boolean rolem = true;
			//检验人员权限
			boolean rolej = false;
			//服务部人员权限
			boolean rolef = false;
			//服务部人员权限
			boolean roley = false;
			//部门负责人
			boolean rolebf = false;
			String sql = "select o.org_name from sys_org o,sys_user u where u.org_id = o.id and u.id=?";
			List<Object> list = employeeDao.createSQLQuery(sql, user_id).list();
			String org_name = "";
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i)!=null){
					org_name = org_name+","+list.get(i).toString();
				}
			}
			if(org_name.indexOf("检验")!=-1&&org_name.indexOf("部")!=-1){
				rolej=true;
			} 
			if(org_name.indexOf("业务服务部")!=-1){
				rolef=true;
			} 
			if(org_name.indexOf("院党政领导")!=-1){
				roley = true;
			}
			
			String sqlf="select count(r.id) from sys_user u,sys_role r,sys_user_role ur"
					+ " where u.id =ur.user_id and r.id = ur.role_id  and r.name='部门负责人'  and u.id=?";
			List<Object> listf = employeeDao.createSQLQuery(sqlf, user_id).list();
			if(listf.size()>0&&listf.get(0)!=null){
				if(Integer.parseInt(listf.get(0).toString())>0){
					rolebf = true;
				}
			}
			
			
			request.setAttribute("rolej", rolej);
			request.setAttribute("rolef", rolef);
			request.setAttribute("roley", roley);
			request.setAttribute("rolebf", rolebf);
			//报告相关数据
			if(rolej||rolef){
			
				//个人检验报告相关数据
				List<DeviceCountDTO> reportList = analyseDao.inspectCountByUserS(user_id);
				if (reportList.size()>0){
					map.put("report",reportList.get(0));
				}
				//全部
				List<DeviceCountDTO> reportListAll = enterSearchDao.inspectCountByDate();
				if (reportList.size()>0){
					map.put("reportAll",reportListAll.get(0));
				}
			
			}
			//人员证书信息
			String sqlc = "select e.id,e.name,e.org_id from employee e,sys_user u where u.employee_id = e.id  and u.id=?";
			List<Object> listc = employeeDao.createSQLQuery(sqlc, user_id).list();
			String empId = "";
			String empName = "";
			String orgId = "";
			if(listc.size()>0&&listc.get(0)!=null){
				Object [] obj = (Object[]) listc.get(0);
				empId = obj[0].toString();
				empName = obj[1].toString();
				orgId = obj[2].toString();
			}
			
			//云平台
			TjyptResourceSpace space = spaceService.openPersonalSpace(
					user_id, "1");
		if(space==null){
			map.put("useSize", "0");
			map.put("maxSize","0");
			map.put("bl", 0);
			map.put("spaceId", null);
		}else{
			map.put("useSize", (Float.parseFloat(space.getSpace_use_size())/1024/1024));
			map.put("maxSize", (Float.parseFloat(space.getSpace_max_size())/1024/1024));
			map.put("bl", (Float.parseFloat(space.getSpace_use_size())/
					Float.parseFloat(space.getSpace_max_size())*100));
			map.put("spaceId", space.getId());
		}
		
			if(StringUtil.isNotEmpty(empId)){
				request.setAttribute("name", empName);
				if(rolej){
					//当年检验设备
					String sqld1 = "select count(d.id) dcount from base_device_document d where d.id in("
							+"select t.fk_tsjc_device_document_id from TZSB_INSPECTION_INFO t where t.enter_op_id=?"
							+"and t.data_status <>'99' and to_char(t.advance_time,'yyyy')='"+year+"')";
					
					List<Object> dList = enterSearchDao.createSQLQuery(sqld1, empId).list();
					if(dList.size()>0&&dList.get(0)!=null){
						int dCount = Integer.parseInt(dList.get(0).toString());
						
						String sqldS = "select count(d.id) dcount from base_device_document d where d.id in("
								+"select t.fk_tsjc_device_document_id from TZSB_INSPECTION_INFO t where "
								+" t.data_status <>'99' and to_char(t.advance_time,'yyyy')='"+year+"')";
						
						List<Object> dLists = enterSearchDao.createSQLQuery(sqldS).list();
						int dCountS = Integer.parseInt(dLists.get(0).toString());
						
						map.put("dCount", dCount);
						map.put("dCountS", dCountS);
						map.put("dbl", (Float.parseFloat(dList.get(0).toString())/
								Float.parseFloat(dLists.get(0).toString())*100));
					}
				}
				//入职时间情况
				String sqld = "select to_char(eb.INTO_WORK_DATE,'yyyy-MM-dd') from tjy2_rl_employee_base eb   where eb.id=? and eb.INTO_WORK_DATE is not null";
				List<Object> dateList = enterSearchDao.createSQLQuery(sqld, empId).list();
				String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(dateList.size()>0&&dateList.get(0)!=null){
					String enterDate = dateList.get(0).toString();
					String enterTime =  remainDateToString(enterDate,now);
					System.out.println("-------------日期-----------------"+enterTime);
					if(StringUtil.isNotEmpty(enterTime)){
						int l1 = enterTime.indexOf("年");
						int l2 = enterTime.indexOf("个月");
						map.put("enterYear", enterTime.substring(0, l1));
						map.put("enterMonth", enterTime.substring(l1+1, l2));
						map.put("enterDay", enterTime.substring(l2+2, enterTime.length()-1));
						System.out.println(enterTime.substring(0, l1)+"-------------日期---"+enterTime.substring(l1+1, l2)+"--------------"+enterTime.substring(l2+2, enterTime.length()-1));
					}
					
					
				}
				
				
				List<EmployeeCert> cartList = employeeCertDao.queryEmployeeCertByBasicId(empId);
				List<JSONObject> cartListm = new ArrayList<JSONObject>(); 
				for (EmployeeCert cert:cartList) {
					JSONObject json = cert.toJson();
					//String type = json.getString("cert_type");
					//String typeName = enterSearchDao.getCodeName(type,"BASE_CERT_TYPE");
					//json.put("cert_type", typeName);
					cartListm.add(json);
				}
				request.setAttribute("cartList", cartListm);
				//map.put("cartList", cartListm);
				map.put("cartListC", cartListm.size());
			
		EmployeeBase empbase = employeeBaseDao.get(empId);
			//省份证
			String empIdCard = empbase.getEmpIdCard();
			map.put("empbase", empbase);
			map.put("empIdCard", empIdCard);
			map.put("empTitle", empbase.getEmpTitle());
			
		/*	String initialEducation = empbase.getInitialEducation();
			String mbaEducation = empbase.getMbaEducation();
			//学历
			String education = (mbaEducation!=null&&StringUtil.isNotEmpty(mbaEducation))?mbaEducation:initialEducation;
			map.put("education", education);*/
			
			//培训
			String sqlpx = "select sum(s.day1) sday,s.dd from (select sum(to_number(to_char(to_date(to_char(CL_SJ, 'yyyy') || '-' ||"
					+ "nvl(t.CL_ZD_R1, '01-01'), 'yyyy-mm-dd') -  to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.CL_QD_R1, '01-01'), "
					+ "'yyyy-mm-dd')))) as day1, count(t.cl_zd_dd1) counts, t.cl_zd_dd1 dd "
					+ "from TJY2_PXFBXD t where to_char(CL_SJ, 'yyyy') = '"+year+"' and ABOLISH is null "
					+ "and t.cl_zd_dd1 is not null  and t.cl_ccid like '%"+empId+"%' group by t.cl_zd_dd1 "
					+ " union  "
					+ "select sum(to_number(to_char(to_date(to_char(CL_SJ, 'yyyy') || '-' || "
					+ "nvl(t.CL_ZD_R2, '01-01'), 'yyyy-mm-dd') - "
					+ "to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.CL_QD_R2, '01-01'), 'yyyy-mm-dd')))) as day1, "
					+ "count(t.cl_zd_dd2) counts, t.cl_zd_dd2 dd from TJY2_PXFBXD t where to_char(CL_SJ, 'yyyy') = '"+year+"' "
					+ "and ABOLISH is null and t.cl_zd_dd2 is not null and t.cl_ccid like '%"+empId+"%' "
					+ "group by t.cl_zd_dd2 "
					+ "union "
					+ " select sum(to_number(to_char(to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.CL_ZD_R3, '01-01'), "
					+ "'yyyy-mm-dd') - to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.CL_QD_R3, '01-01'), "
					+ "'yyyy-mm-dd')))) as day1, count(t.cl_zd_dd3) counts, t.cl_zd_dd3 dd from TJY2_PXFBXD t "
					+ "where to_char(CL_SJ, 'yyyy') = '2016' and ABOLISH is null and t.cl_zd_dd3 is not null "
					+ "and t.cl_ccid like '%"+empId+"%' group by t.cl_zd_dd3 "
					+ " union"
					+ " select sum(to_number(to_char(to_date(to_char(CL_SJ, 'yyyy') || '-' || "
					+ " nvl(t.CL_ZD_R4, '01-01'), 'yyyy-mm-dd') -  to_date(to_char(CL_SJ, 'yyyy') || '-' || "
					+ " nvl(t.CL_QD_R4, '01-01'),  'yyyy-mm-dd')))) as day1, count(t.cl_zd_dd4) counts, "
					+ " t.cl_zd_dd4 dd  from TJY2_PXFBXD t "
					+ "where to_char(CL_SJ, 'yyyy') = '"+year+"' and ABOLISH is null  and t.cl_zd_dd4 is not null "
					+ "and t.cl_ccid like '%"+empId+"%'"
					+ " group by t.cl_zd_dd4 ) s group by s.dd order by sum(s.day1) desc";
			
				List<Object> listpx = employeeDao.createSQLQuery(sqlpx).list();
				String pxdd = "";
				String pxts = "";
				int pxSum = 0;
				List<JSONObject> listpxj = new ArrayList<JSONObject>();
				if(listpx.size()>0&&listpx.get(0)!=null){
					Object [] obj = (Object[]) listpx.get(0);
					pxts = (obj[0]==null)?"":obj[0].toString();
					pxdd = (obj[1]==null)?"":obj[1].toString();
					//培训最多的地点
					map.put("pxdd", pxdd);
					//培训最多的地点的培训时间
					map.put("pxts", pxts);
				}
				for (int i = 0; i < listpx.size(); i++) {
					Object [] obj = (Object[]) listpx.get(i);
					String ts = (obj[0]==null)?"":obj[0].toString();
					pxSum = pxSum + Integer.parseInt(ts);
					if(roley||rolebf){
						//院长才需要查询
						pxts = (obj[0]==null)?"":obj[0].toString();
						pxdd = (obj[1]==null)?"":obj[1].toString();
						boolean sn = false;
						String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd+"%'";
						List<Object> lista = employeeDao.createSQLQuery(sqla).list();
						if(lista.size()>0&&lista.get(0)!=null){
							if(Integer.parseInt(lista.get(0).toString())>0){
								sn = true;
							}
						}
						if(sn){
							continue;
						}
						JSONObject json = new JSONObject();
						
						
						if("龙泉".equals(pxdd)){
							pxdd = "龙泉驿区";
						}
						json.put("pxts", pxts);
						json.put("pxdd", pxdd);
						if(ReportUtil.getBaiDuGPS(pxdd)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd))){
							String jwd = ReportUtil.getBaiDuGPS(pxdd);
							json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
							json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
						}
						
						System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd));
						listpxj.add(json);
					}
				}
				if(roley||rolebf){
				//出差
				String sqlcc = "select sum(ss.sday), ss.dd from ( select sum(s.d2) sday, s.dd "
						+ "from (select to_number(to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.cl_zd_r1, '01-01'), "
						+ " 'yyyy-mm-dd') -  to_date(to_char(CL_SJ, 'yyyy') || '-' ||   nvl(t.cl_qd_r1, '01-01'), "
						+ "'yyyy-mm-dd')) + 1 d2, t.cl_zd_dd1 dd from TJY2_CLFBXD t where t.cl_qd_r1 is not null and t.cl_ccr like '%"+empName+"%'  "
						+ " and length(t.cl_qd_r1) = 5  and to_char(t.cl_sj, 'yyyy') =  '"+year+"'   and length(t.cl_zd_r1) = 5) s "
						+ " group by s.dd "
						+ "union "
						+ " select sum(s.d2) sday, s.dd "
						+ "from (select to_number(to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.cl_zd_r2, '01-01'),  'yyyy-mm-dd') - "
						+ "to_date(to_char(CL_SJ, 'yyyy') || '-' || nvl(t.cl_qd_r2, '01-01'), 'yyyy-mm-dd')) + 1 d2, "
						+ " t.cl_zd_dd2 dd  from TJY2_CLFBXD t where t.cl_qd_r2 is not null and t.cl_ccr like '%"+empName+"%' "
						+ "  and length(t.cl_qd_r2) = 5  and to_char(t.cl_sj, 'yyyy') =  '"+year+"'   and length(t.cl_zd_r2) = 5) s  group by s.dd "
						+ " union "
						+ "select sum(s.d2) sday, s.dd  from (select to_number(to_date(to_char(CL_SJ, 'yyyy') || '-' || "
						+ " nvl(t.cl_zd_r3, '01-01'), 'yyyy-mm-dd') -  to_date(to_char(CL_SJ, 'yyyy') || '-' ||  nvl(t.cl_qd_r3, '01-01'), "
						+ "'yyyy-mm-dd')) + 1 d2,  t.cl_zd_dd3 dd  from TJY2_CLFBXD t  where t.cl_qd_r3 is not null "
						+ "and length(t.cl_qd_r3) = 5 and t.cl_ccr like '%"+empName+"%'  and to_char(t.cl_sj, 'yyyy') =  '"+year+"'  "
						+ " and length(t.cl_zd_r3) = 5) s  group by s.dd "
						+ "union "
						+ " select sum(s.d2) sday, s.dd from (select to_number(to_date(to_char(CL_SJ, 'yyyy') || '-' || "
						+ " nvl(t.cl_zd_r4, '01-01'),  'yyyy-mm-dd') -  to_date(to_char(CL_SJ, 'yyyy') || '-' ||"
						+ " nvl(t.cl_qd_r4, '01-01'), 'yyyy-mm-dd')) + 1 d2,  t.cl_zd_dd4 dd  from TJY2_CLFBXD t "
						+ " where t.cl_qd_r4 is not null and t.cl_ccr like '%"+empName+"%'  and length(t.cl_qd_r4) = 5 "
						+ "and to_char(t.cl_sj, 'yyyy') =  '"+year+"'   and length(t.cl_zd_r4) = 5) s   group by s.dd) ss group by ss.dd order by sum(ss.sday) desc";
				List<Object> listcc = employeeDao.createSQLQuery(sqlcc).list();
				
				for (int i = 0; i < listcc.size(); i++) {
					Object [] obj = (Object[]) listcc.get(i);
					String ts = (obj[0]==null)?"":obj[0].toString();
					/*if(ts.indexOf("-")==-1){
						pxSum = pxSum + Integer.parseInt(ts);
					}*/
					
						String pxts1 = (obj[0]==null)?"0":obj[0].toString();
						if(pxts1.indexOf("-")!=-1){
							System.out.println("--------------负----"+pxts1);
							continue;
						}
						String pxdd1 = (obj[1]==null)?"":obj[1].toString();
						String[] dd1 = pxdd1.split("，");
						String[] dd2 = pxdd1.split(" ");
						String[] dd3 = pxdd1.split("、");
						String[] dd4 = pxdd1.split("。");
						String[] dd5 = pxdd1.split(".");
						if(dd1.length>1){
							for (int j = 0; j < dd1.length; j++) {
								//院长才需要查询
								JSONObject json = new JSONObject();
								pxts1 =  new DecimalFormat("#########.##").format(
										Float.parseFloat(pxts1)/dd1.length)+"";
								pxdd1 = dd1[j];
								boolean sn = false;
								String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
								List<Object> lista = employeeDao.createSQLQuery(sqla).list();
								if(lista.size()>0&&lista.get(0)!=null){
									if(Integer.parseInt(lista.get(0).toString())>0){
										sn = true;
									}
								}
								if(sn){
									continue;
								}
								if("龙泉".equals(pxdd)){
									pxdd = "龙泉驿区";
								}
								json.put("pxts", pxts1);
								json.put("pxdd", pxdd1);
								if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
									String jwd = ReportUtil.getBaiDuGPS(pxdd1);
									json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
									json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
								}
								
								System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
								listpxj.add(json);
							}
						}else if(dd2.length>1){
							for (int j = 0; j < dd2.length; j++) {
								//院长才需要查询
								JSONObject json = new JSONObject();
								pxts1 =  new DecimalFormat("#########.##").format(
										Float.parseFloat(pxts1)/dd2.length)+"";
								pxdd1 = dd2[j];
								boolean sn = false;
								String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
								List<Object> lista = employeeDao.createSQLQuery(sqla).list();
								if(lista.size()>0&&lista.get(0)!=null){
									if(Integer.parseInt(lista.get(0).toString())>0){
										sn = true;
									}
								}
								if(sn){
									continue;
								}
								if("龙泉".equals(pxdd)){
									pxdd = "龙泉驿区";
								}
								json.put("pxts", pxts1);
								json.put("pxdd", pxdd1);
								if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
									String jwd = ReportUtil.getBaiDuGPS(pxdd1);
									json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
									json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
								}
								
								System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
								listpxj.add(json);
							}
						}else if(dd3.length>1){
							for (int j = 0; j < dd3.length; j++) {
								//院长才需要查询
								JSONObject json = new JSONObject();
								pxts1 =  new DecimalFormat("#########.##").format(
										Float.parseFloat(pxts1)/dd3.length)+"";
								pxdd1 = dd3[j];
								boolean sn = false;
								String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
								List<Object> lista = employeeDao.createSQLQuery(sqla).list();
								if(lista.size()>0&&lista.get(0)!=null){
									if(Integer.parseInt(lista.get(0).toString())>0){
										sn = true;
									}
								}
								if(sn){
									continue;
								}
								if("龙泉".equals(pxdd)){
									pxdd = "龙泉驿区";
								}
								json.put("pxts", pxts1);
								json.put("pxdd", pxdd1);
								if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
									String jwd = ReportUtil.getBaiDuGPS(pxdd1);
									json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
									json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
								}
								
								System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
								listpxj.add(json);
							}
						}else if(dd4.length>1){
							for (int j = 0; j < dd4.length; j++) {
								//院长才需要查询
								JSONObject json = new JSONObject();
								pxts1 =  new DecimalFormat("#########.##").format(
										Float.parseFloat(pxts1)/dd4.length)+"";
								pxdd1 = dd4[j];
								boolean sn = false;
								String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
								List<Object> lista = employeeDao.createSQLQuery(sqla).list();
								if(lista.size()>0&&lista.get(0)!=null){
									if(Integer.parseInt(lista.get(0).toString())>0){
										sn = true;
									}
								}
								if(sn){
									continue;
								}
								if("龙泉".equals(pxdd)){
									pxdd = "龙泉驿区";
								}
								json.put("pxts", pxts1);
								json.put("pxdd", pxdd1);
								if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
									String jwd = ReportUtil.getBaiDuGPS(pxdd1);
									json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
									json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
								}
								
								System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
								listpxj.add(json);
							}
						}else if(dd5.length>1){
							for (int j = 0; j < dd5.length; j++) {
								//院长才需要查询
								JSONObject json = new JSONObject();
								pxts1 =  new DecimalFormat("#########.##").format(
										Float.parseFloat(pxts1)/dd5.length)+"";
								pxdd1 = dd5[j];
								boolean sn = false;
								String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
								List<Object> lista = employeeDao.createSQLQuery(sqla).list();
								if(lista.size()>0&&lista.get(0)!=null){
									if(Integer.parseInt(lista.get(0).toString())>0){
										sn = true;
									}
								}
								if(sn){
									continue;
								}
								if("龙泉".equals(pxdd)){
									pxdd = "龙泉驿区";
								}
								json.put("pxts", pxts1);
								json.put("pxdd", pxdd1);
								if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
									String jwd = ReportUtil.getBaiDuGPS(pxdd1);
									json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
									json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
								}
								
								System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
								listpxj.add(json);
							}
						}else{
						
					
						
							//院长才需要查询
							JSONObject json = new JSONObject();
							pxts1 = (obj[0]==null)?"":obj[0].toString();
							pxdd1 = (obj[1]==null)?"":obj[1].toString();
							boolean sn = false;
							String sqla="select count(t.id) from V_AREA_CODE t where t.REGIONAL_NAME like '%"+pxdd1+"%'";
							List<Object> lista = employeeDao.createSQLQuery(sqla).list();
							if(lista.size()>0&&lista.get(0)!=null){
								if(Integer.parseInt(lista.get(0).toString())>0){
									sn = true;
								}
							}
							if(sn){
								continue;
							}
							if("龙泉".equals(pxdd)){
								pxdd = "龙泉驿区";
							}
							json.put("pxts", pxts1);
							json.put("pxdd", pxdd1);
							if(ReportUtil.getBaiDuGPS(pxdd1)!=null&&StringUtil.isNotEmpty(ReportUtil.getBaiDuGPS(pxdd1))){
								String jwd = ReportUtil.getBaiDuGPS(pxdd1);
								json.put("jwd_x", Float.parseFloat(jwd.split(",")[0]));
								json.put("jwd_y", Float.parseFloat(jwd.split(",")[1]));
							}
							
							System.out.println("-----------经纬度---------"+ReportUtil.getBaiDuGPS(pxdd1));
							listpxj.add(json);
						}
						}
				}
				
				
				if(roley||rolebf){
					request.setAttribute("listpxj", listpxj);
				}
				//map.put("listpxj", listpxj);
				
				//全年的培训时间
				map.put("pxSum", pxSum);
				
				if(roley){
					
					List<Object> list1 = maintenanceInfoDao.getInfosByAdvaceOrg(null);
					List<JSONObject> listAdvance = new ArrayList<JSONObject>();
					for (int i = 0; i < list1.size(); i++) {
						Object [] obj = (Object[]) list1.get(i);
						JSONObject json = new JSONObject();
						json.put("pro_desc", obj[0]==null?"":obj[0].toString());
						json.put("advance_date", obj[1]==null?"":obj[1].toString());
						listAdvance.add(json);
					}
					
					request.setAttribute("listAdvance", listAdvance);
					int sum = maintenanceInfoDao.getCountByAdvaceOrg(null);
					//建议总条数
					map.put("advanceSum", sum);
					
				}else if(rolebf){
					List<Object> list1 = maintenanceInfoDao.getInfosByAdvaceOrg(orgId);
					List<JSONObject> listAdvance = new ArrayList<JSONObject>();
					for (int i = 0; i < list1.size(); i++) {
						Object [] obj = (Object[]) list1.get(i);
						JSONObject json = new JSONObject();
						json.put("pro_desc", obj[0]==null?"":obj[0].toString());
						json.put("advance_date", obj[1]==null?"":obj[1].toString());
						listAdvance.add(json);
					}
					request.setAttribute("listAdvance", listAdvance);
					int sumo = maintenanceInfoDao.getCountByAdvaceOrg(orgId);
					int sum = maintenanceInfoDao.getCountByAdvaceOrg(null);
					//建议总条数
					map.put("advanceCount", sum);
					map.put("advanceSum", sumo);
					String advanceBl =  new DecimalFormat("#########.##").format(
							Float.parseFloat(sumo+"")/sum*100)+"";
					map.put("advanceBl", advanceBl);
				}else{
					List<MaintenanceInfo> listAdvance = maintenanceInfoDao.getInfosByAdvaceUser(empId);
					request.setAttribute("listAdvance", listAdvance);
					//建议总条数
					map.put("advanceSum", listAdvance.size());
					int sum = maintenanceInfoDao.getCountByAdvaceOrg(null);
					String advanceBl =  new DecimalFormat("#########.##").format(
							Float.parseFloat(listAdvance.size()+"")/sum*100)+"";
					map.put("advanceBl", advanceBl);
				}
				
				//具体建议
				//map.put("listAdvance", listAdvance);
				
				
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return map;
		}

		 public static String remainDateToString(String startDateStr, String endDateStr){  
		        java.util.Date startDate = null;  
		        java.util.Date endDate= null;  
		        try {  
		            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);  
		        } catch (ParseException e) {  
		            e.printStackTrace();  
		            return "";  
		        }  
		        try {  
		            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);  
		        } catch (ParseException e) {  
		            e.printStackTrace();  
		            return "";  
		        }  
		        calS.setTime(startDate);  
		        int startY = calS.get(Calendar.YEAR);  
		        int startM = calS.get(Calendar.MONTH);  
		        int startD = calS.get(Calendar.DATE);  
		        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);  
		          
		        calS.setTime(endDate);  
		        int endY = calS.get(Calendar.YEAR);  
		        int endM = calS.get(Calendar.MONTH);  
		        //处理2011-01-10到2011-01-10，认为服务为一天  
		        int endD = calS.get(Calendar.DATE)+1;  
		        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);  
		          
		        StringBuilder sBuilder = new StringBuilder();  
		        if (endDate.compareTo(startDate)<0) {  
		            return sBuilder.append("过期").toString();  
		        }  
		        int lday = endD-startD;  
		        if (lday<0) {  
		            endM = endM -1;  
		            lday = startDayOfMonth+ lday;  
		        }  
		        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年  
		        if (lday == endDayOfMonth) {  
		            endM = endM+1;  
		            lday =0;  
		        }  
		        int mos = (endY - startY)*12 + (endM- startM);  
		        int lyear = mos/12;  
		        int lmonth = mos%12;  
		        if (lyear >0) {  
		            sBuilder.append(lyear+"年");  
		        }else{
		        	sBuilder.append("0年");  
		        }
		        if (lmonth > 0) {  
		            sBuilder.append(lmonth+"个月");  
		        }else{
		        	sBuilder.append("0个月");
		        }
		        if (lday >0 ) {  
		            sBuilder.append(lday+"天");  
		        }else{
		        	sBuilder.append("0天");  
		        }
		        return sBuilder.toString();  
		    }

		public void sendWeixinStaUrl(HttpServletRequest request,String text) {
			 if(text==null){
		        	return;
		    }
			String hql = "from EmployeeBase t where  t.empPhone is not null and t.empStatus in ('3','4')";
	        List<EmployeeBase> list = employeeBaseDao.createQuery(hql).list();
			//Employee employee = employeesService.get("");
			// 获取发送目标号码
	       /* String []users = {"13980717755"};
	        for (int i = 0; i < users.length; i++) {
	        	String destNumber =users[i];
				// 获取发送内容
				String content = "张哥您好，下面是四个领导的2016年度账单，请您阅视，";
				content =content +" 蒋青： http://pt.scsei.org.cn/weiXinAnnualSta/weiXinStaQuery.do?userName=13980788181 ";
				content =content +" 林涛： http://pt.scsei.org.cn/weiXinAnnualSta/weiXinStaQuery.do?userName=13981919103 ";
				content =content +" 黄坚： http://pt.scsei.org.cn/weiXinAnnualSta/weiXinStaQuery.do?userName=13981714371 ";
				content =content +" 阳晓薇： http://pt.scsei.org.cn/weiXinAnnualSta/weiXinStaQuery.do?userName=13060002978 ";
				
				
				System.out.println("-------------内容："+content+" ");
				// 发送微信
				messageService.sendWxMsg(request, "", Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
						content, destNumber);
			}*/
	        for (int i = 0; i < list.size(); i++) {
	        	EmployeeBase  employee =  list.get(i);
	        	String destNumber = employee.getEmpPhone();
				// 获取发送内容
				String content = text+" http://pt.scsei.org.cn/weiXinAnnualSta/weiXinStaQuery.do?userName="+destNumber;
				System.out.println("-------------内容："+content+" ");
				// 发送微信
				/*messageService.sendWxMsg(request, "", Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
						content, destNumber);*/
			}
		
		}  
		
}
