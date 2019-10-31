package com.lsts.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.report.bean.ReportYjsz;
/**
 * @ClassName ReportYjszDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("reportYjszDao")
@SuppressWarnings("rawtypes")
public class ReportYjszDao extends EntityDaoImpl<ReportYjsz> {


	public List queryInspectionInfo(String sysOrgId,String flowName) {
		//取出报告相关时间
		List list=new ArrayList();	
		String sql="select FLOW_NOTE_NAME,CHECK_UNIT_ID,PRINT_TIME,ISSUE_DATE,ENTER_TIME,EXAMINE_DATE"
				+ " from TZSB_INSPECTION_INFO where CHECK_UNIT_ID=? and flow_note_name in("+flowName+")";
		try {
			
			list = this.createSQLQuery(sql, sysOrgId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获得预警设置的数据
	 * */
	@SuppressWarnings("unchecked")
	public List<ReportYjsz> getReportYjsz(){
		String hql = "from ReportYjsz";
		List<ReportYjsz> list = this.createQuery(hql).list();

		return list;
	}
	
	public List getSysOrg(){
		String sql = "select id,ORG_NAME from SYS_ORG where STATUS='used' and PARENT_ID IS NOT NULL";
		List list = this.createSQLQuery(sql).list();

		return list;
	}
	/**
	 * 通过环节查询规则
	 * */
	@SuppressWarnings("unchecked")
	public List<ReportYjsz> getReportYjsz(String flow,String flows){
		String sql = "select flow from TJY2_REPORT_YJSZ where flow=? and flows=?";
		List list = this.createSQLQuery(sql, flow,flows).list();
		return list;
		
	}
	/**
	 * 通过环节查询规则
	 * */
	@SuppressWarnings("unchecked")
	public List<ReportYjsz> getReportYjsz1(String flow,String flows){
		String sql = "select flows from TJY2_REPORT_YJSZ where flow=? and flows=?";
		List list = this.createSQLQuery(sql, flow,flows).list();
		return list;
		
	}
	/**
	 * 通过环节查询id
	 * */
	@SuppressWarnings("unchecked")
	public List<ReportYjsz> getids(String flow,String flows){
		String sql = "select id from TJY2_REPORT_YJSZ where flow=? and flows=?";
		List list = this.createSQLQuery(sql, flow,flows).list();
		return list;
		
	}
	public List getUser(String orgId){
		String sql = "select e.id,e.name,e.mobile_tel from SYS_ROLE t ,sys_user_role r,sys_user u,employee e where t.name='部门负责人' and r.role_id=t.id and u.id=r.user_id and e.id=u.employee_id and u.org_id=?";
		List list = this.createSQLQuery(sql,orgId).list();
		return list;
	}
}