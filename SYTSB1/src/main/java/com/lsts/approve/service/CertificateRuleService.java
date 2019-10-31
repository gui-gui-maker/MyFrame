package com.lsts.approve.service;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateBy;
import com.lsts.approve.bean.CertificateRule;
import com.lsts.approve.bean.SysRecord;
import com.lsts.approve.dao.CertificateByDao;
import com.lsts.approve.dao.CertificateRuleDao;
import com.lsts.approve.dao.SysRecordDao;
import com.lsts.approve.util.CopyFieldValueUtil;
import com.lsts.approve.util.IpUtil;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.report.dao.ReportDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("certificateRuleService")
public class CertificateRuleService  extends EntityManageImpl<CertificateRule,CertificateRuleDao>{
	@Autowired
	private CertificateRuleDao certificateRuleDao;
	@Autowired
	private CertificateByDao certificateByDao;
	@Autowired
	private SysRecordDao sysRecordDao;
	@Autowired
	private ReportDao reportDao;

	public CertificateRule getByDeviceClassify(String device_classify_id) throws Exception{
		CertificateRule certificateRule = null;
		try{
			@SuppressWarnings("unchecked")
			List<CertificateRule> list = certificateRuleDao.createQuery("from CertificateRule where device_classify_id = ?", device_classify_id).list();
			if(list!=null&&list.size()>0){
				certificateRule =  list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return certificateRule;
	}


	@SuppressWarnings("unchecked")
	public HashMap<String,Object> detail(String device, String dept, String report) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		CertificateRule certificateRule = null;
		try{
			List<CertificateRule> list = null;
			String hql=null;
			if(!StringUtil.isEmpty(report)){
				hql="from CertificateRule where device_classify_id=? and dept_id=? and report_id=?";
				list = certificateRuleDao.createQuery(hql, device,dept,report).list();
			}else{
				hql="from CertificateRule where device_classify_id=? and dept_id=?";
				list = certificateRuleDao.createQuery(hql, device,dept).list();
			}
			List<CertificateBy> list2 = null;
			int count1 = 0;//正常
			int count2 = 0;//请假
			int count3 = 0;//停用
			if(list!=null&&list.size()>0){
				certificateRule =  list.get(0);
				hql="from CertificateBy where fk_rule=?";
				list2 = certificateByDao.createQuery(hql, certificateRule.getId()).list();
				certificateRule.setList(list2);
				for (CertificateBy c : list2) {
					//停用
					if("0".equals(c.getStatus())){
						count3++;
					}
					//正常
					else if("0".equals(c.getIs_vacation())){
						count1++;
					}
					//请假
					else if("1".equals(c.getIs_vacation())){
						count2++;
					}
				}
			}
			map.put("success", true);
			map.put("data", certificateRule);
			map.put("count1", count1);
			map.put("count2", count2);
			map.put("count3", count3);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}


	/**
	 * 根据设备类别查询优先分配规则
	 * 
	 * @param device_type --
	 *            设备类别
	 * @param dep_id --
	 *            检验部门ID
	 * @param report_name --
	 *            报告名称
	 * @param is_issue --
	 *            是否是签发指定报告类型（0：否 1：是）      
	 * @param report_id --
	 *            报告类型ID 
	 *           
	 * @return
	 * @author GaoYa
	 * @date 2017-06-28 上午09:34:00
	 */
	public List<CertificateRule> queryRuleCode(String device_type, String dep_id, String is_issue, String report_name, String report_id) {
		return certificateRuleDao.queryRuleCode(device_type, dep_id, is_issue, report_name, report_id);
	}
	
	public CertificateRule saves(HttpServletRequest request,CertificateRule rule)  throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String account = user.getSysUser().getName();
		String requestIp = IpUtil.getIpAddr(request);
		try{
			//完善手机端提交数据
			String report_id = rule.getReport_id();
			if(!StringUtil.isEmpty(report_id)&&StringUtil.isEmpty(rule.getReport_name())){
				Object[] reportObject =  (Object[]) reportDao.createSQLQuery("select id, report_name2,report_name from base_reports where id = ?", report_id).uniqueResult();
				String report_name = "";
				if(reportObject[1]!=null) {
					report_name = reportObject[1].toString();
				}else if(reportObject[2]!=null){
					report_name = reportObject[2].toString();
				}
				rule.setReport_name(report_name);
			}
			List<CertificateBy> lst = rule.getList();
			if(lst.size()>0){
				for(CertificateBy cb : lst){
					if(StringUtil.isEmpty(cb.getReport_id())){
						cb.setReport_id(report_id);
					}
					if(StringUtil.isEmpty(cb.getReport_name())){
						cb.setReport_name(rule.getReport_name());
					}
					if(StringUtil.isEmpty(cb.getDeviceTypeId())){
						cb.setDeviceTypeId(rule.getDevice_classify_id());
					}
					if(StringUtil.isEmpty(cb.getDeviceType())){
						cb.setDeviceType(rule.getDevice_classify_name());
					}
					if(StringUtil.isEmpty(cb.getMayCertDeptId())){
						cb.setMayCertDeptId(rule.getDept_id());
					}
					if(StringUtil.isEmpty(cb.getMayCertDept())){
						cb.setMayCertDept(rule.getDept());
					}
					if(StringUtil.isEmpty(cb.getFk_rule())){
						cb.setFk_rule(rule.getId());
					}
					//补全移动端取值问题
					if(StringUtil.isEmpty(cb.getUser_name())){
						Object[] us = (Object[]) certificateRuleDao.createSQLQuery("select t.id,t.account,t.name,t.org_id,o.org_name from sys_user t,sys_org o where t.org_id= o.id and t.id=?",cb.getUser_id()).uniqueResult();
						cb.setUser_account(us[1].toString());
						cb.setUser_name(us[2].toString());
						cb.setDeptId(us[3].toString());
						cb.setDept(us[4].toString());
					}
				}
			}
			
			
			String id = rule.getId();
			List<CertificateBy> new_list = null;
			if(StringUtil.isEmpty(id)){
				certificateRuleDao.save(rule);
				//记录新增
				SysRecord sr = new SysRecord();
				sr.setAccount(account);
				sr.setIp_address(requestIp);
				sr.setOp_time(new Date());
				sr.setAction("add");
				sr.setBean_name(rule.getClass().getName());
				sr.setTable_name(rule.getClass().getAnnotation(Table.class).name());
				sr.setBusiness_id(rule.getId());
				sr.setOld_record(null);
				new_list = rule.getList();
				//避免数据字符串长度超长
				rule.setList(null);
				sr.setNew_record(JSONObject.fromBean(rule).toString());
				sysRecordDao.save(sr);
			}else{
				CertificateRule old_rule = certificateRuleDao.get(id);
				new_list = rule.getList();
				rule.setList(null);
				//old_rule.setList(rule.getList());
				if(!JSONObject.fromBean(rule).toString().equals(JSONObject.fromBean(old_rule).toString())){
					//记录更新操作
					SysRecord sr = new SysRecord();
					sr.setAccount(account);
					sr.setIp_address(requestIp);
					sr.setOp_time(new Date());
					sr.setAction("update");
					sr.setBean_name(rule.getClass().getName());
					sr.setTable_name(rule.getClass().getAnnotation(Table.class).name());
					sr.setBusiness_id(rule.getId());
					sr.setOld_record(JSONObject.fromBean(old_rule).toString());
					sr.setNew_record(JSONObject.fromBean(rule).toString());
					sysRecordDao.save(sr);
					CopyFieldValueUtil.copy(old_rule, rule);
					rule.setId(null);
					certificateRuleDao.save(old_rule);
					rule = old_rule;
				}
			}
			//删除
			@SuppressWarnings("unchecked")
			List<CertificateBy> old_list = certificateByDao.createQuery("from CertificateBy where fk_rule = ?",rule.getId()).list();
			List<CertificateBy> both_olist = new ArrayList<CertificateBy>();
			List<CertificateBy> both_nlist = new ArrayList<CertificateBy>();
			if(old_list!=null&&old_list.size()>0&&new_list!=null&&new_list.size()>0){
				for (CertificateBy oby : old_list) {
					for(CertificateBy nby :new_list){
						if(oby.getId().equals(nby.getId())){
							//更新
							both_olist.add(oby);
							both_nlist.add(nby);
							//比较
							String oby_str = JSONObject.fromBean(oby).toString();
							String nby_str = JSONObject.fromBean(nby).toString();
							if(!oby_str.equals(nby_str)){
								//记录更新操作
								SysRecord sr = new SysRecord();
								sr.setAccount(account);
								sr.setIp_address(requestIp);
								sr.setOp_time(new Date());
								sr.setAction("update");
								sr.setBean_name(oby.getClass().getName());
								sr.setTable_name(oby.getClass().getAnnotation(Table.class).name());
								sr.setBusiness_id(oby.getId());
								sr.setOld_record(JSONObject.fromBean(oby).toString());
								sr.setNew_record(JSONObject.fromBean(nby).toString());
								sysRecordDao.save(sr);
								////更新
								CopyFieldValueUtil.copy(oby, nby);
								nby.setId(null);
								certificateByDao.save(oby);
							}
							break;
						}
					}
				}
				old_list.removeAll(both_olist);
				if(old_list.size()>0){
					//删除
					for(CertificateBy by : old_list){
						String by_id = by.getId();
						//记录删除。。。。。
						SysRecord sr = new SysRecord();
						sr.setAccount(account);
						sr.setIp_address(requestIp);
						sr.setOp_time(new Date());
						sr.setAction("delete");
						sr.setBean_name(by.getClass().getName());
						sr.setTable_name(by.getClass().getAnnotation(Table.class).name());
						sr.setBusiness_id(by_id);
						sr.setOld_record(JSONObject.fromBean(by).toString());
						sr.setNew_record(null);
						sysRecordDao.save(sr);
						certificateByDao.createQuery("delete from CertificateBy where id = ?",by_id).executeUpdate();
						
					}
				}
				new_list.removeAll(both_nlist);
				if(new_list.size()>0){
					//添加
					for(CertificateBy by : new_list){
						by.setFk_rule(rule.getId());
						certificateByDao.save(by);
						//记录添加。。。。。
						SysRecord sr = new SysRecord();
						sr.setAccount(account);
						sr.setIp_address(requestIp);
						sr.setOp_time(new Date());
						sr.setAction("add");
						sr.setBean_name(by.getClass().getName());
						sr.setTable_name(by.getClass().getAnnotation(Table.class).name());
						sr.setBusiness_id(by.getId());
						sr.setOld_record(null);
						sr.setNew_record(JSONObject.fromBean(by).toString());
						sysRecordDao.save(sr);
					}
				}
				new_list.addAll(both_olist);
			}else if((old_list==null||old_list.size()==0)&&(new_list!=null&&new_list.size()>0)){
				//添加
				for(CertificateBy by : new_list){
					by.setFk_rule(rule.getId());
					certificateByDao.save(by);
					//记录添加。。。。。
					SysRecord sr = new SysRecord();
					sr.setAccount(account);
					sr.setIp_address(requestIp);
					sr.setOp_time(new Date());
					sr.setAction("add");
					sr.setBean_name(by.getClass().getName());
					sr.setTable_name(by.getClass().getAnnotation(Table.class).name());
					sr.setBusiness_id(by.getId());
					sr.setOld_record(null);
					sr.setNew_record(JSONObject.fromBean(by).toString());
					sysRecordDao.save(sr);
				}
			}else if((old_list!=null&&old_list.size()>0)&&(new_list==null||new_list.size()==0)){
				for(CertificateBy by : old_list){
					
					String by_id = by.getId();
					//记录删除。。。。。
					SysRecord sr = new SysRecord();
					sr.setAccount(account);
					sr.setIp_address(requestIp);
					sr.setOp_time(new Date());
					sr.setAction("delete");
					sr.setBean_name(by.getClass().getName());
					sr.setTable_name(by.getClass().getAnnotation(Table.class).name());
					sr.setBusiness_id(by_id);
					sr.setOld_record(JSONObject.fromBean(by).toString());
					sr.setNew_record(null);
					sysRecordDao.save(sr);
					certificateByDao.createQuery("delete from CertificateBy where id = ?",by_id).executeUpdate();
					
				}
			}
			//还原对象
			rule.setList(new_list);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return rule;
	}

	public HashMap<String, Object> getUsers() throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			List<Object> list = certificateRuleDao.createSQLQuery("select t.id,t.account,t.name,t.org_id,o.org_name from sys_user t,sys_org o where t.org_id= o.id").list();
			map.put("success", true);
			map.put("data", list);
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	public List<Map<String,Object>> getUsersData() {
		return certificateRuleDao.getUsersData();
	}

	public List<Map<String,Object>> getTreeData() {
		return certificateRuleDao.getTreeData();
	}
	
	public synchronized HashMap<String, Object> savesByFlat(JSONObject json,HashMap<String, Object> map,HttpServletRequest request
			) throws KhntException, ParseException,Exception{
		Object usersdata = json.get("list");
		String id = json.getString("id");
		String device_classify_id = json.getString("device_classify_id");
		String device_classify_name = json.getString("device_classify_name");
		String dept_id = json.getString("dept_id");
		String dept = json.getString("dept");
		String report_id = json.getString("report_id");
		String report_name = json.getString("report_name");
		String is_same_person = json.getString("is_same_person");
		String is_allow_self = json.getString("is_allow_self");
		String is_same_unit = json.getString("is_same_unit");
		String certificate_rule = json.getString("certificate_rule");
		List<CertificateBy> list = new ArrayList<CertificateBy>();
		
		if(usersdata != null && !"".equals(usersdata.toString()) && !"null".equals(usersdata.toString())){
			JSONArray users = JSONArray.fromObject(usersdata.toString());
			for (int i = 0; i < users.length(); i++) {
				//CertificateBy certificateBy = new CertificateBy();
				Object user = users.get(i);
				CertificateBy certificateBy = JSON.parseObject(user.toString(), CertificateBy.class);
				//User user = employeesDao.getUser(userData.getString("user_id"));
				/*if(StringUtil.isNotEmpty(userData.getString("id"))){
					certificateBy = certificateByDao.get(userData.getString("id"));
					if(!certificateBy.getUser_id().equals(user.getId())){
						certificateBy.setUser_id(user.getId());
						certificateBy.setUser_account(user.getAccount());
						certificateBy.setUser_name(user.getName());
						certificateBy.setDeptId(user.getOrg().getOrgCode());
						certificateBy.setDept(user.getOrg().getOrgName());
					}
				}else{
					certificateBy.setUser_id(user.getId());
					certificateBy.setUser_account(user.getAccount());
					certificateBy.setUser_name(user.getName());
					certificateBy.setDeptId(user.getOrg().getOrgCode());
					certificateBy.setDept(user.getOrg().getOrgName());
				}
				certificateBy.setStatus(userData.getString("status"));
				certificateBy.setIs_vacation(userData.getString("is_vacation"));*/
				list.add(certificateBy);
			}
		}else{
			list=null;
		}

		CertificateRule rule = new CertificateRule();
		rule.setId(id);
		rule.setDevice_classify_id(device_classify_id);
		rule.setDevice_classify_name(device_classify_name);
		rule.setDept_id(dept_id);
		rule.setDept(dept);
		rule.setReport_id(report_id);
		rule.setReport_name(report_name);
		rule.setIs_same_person(is_same_person);
		rule.setIs_allow_self(is_allow_self);
		rule.setIs_same_unit(is_same_unit);
		rule.setCertificate_rule(certificate_rule);
		rule.setList(list);
		try{
			CertificateRule rule1 = this.saves(request,rule);
			if(rule1!=null){
				map.put("success", true);
				map.put("rule", rule1);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return map;
		
	}

}
