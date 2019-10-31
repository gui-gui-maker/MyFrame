package com.lsts.humanresources.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lsts.humanresources.service.BgLeaveManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.BgLeave;
import com.lsts.humanresources.bean.RemindMessage;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2BgLeaveDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("BgLeaveDao")
public class BgLeaveDao extends EntityDaoImpl<BgLeave> {
	private Logger logger = LoggerFactory.getLogger(BgLeaveManager.class);
	//已请假种类及天数
	public List<?> queryLeave(HttpServletRequest request,String peopleId,String startDate)throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start=sdf.parse(startDate);
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(start);
		int year = calendar.get(Calendar.YEAR);
		String dateCycles = Factory.getSysPara().getProperty("TJY2_RL_ANNUAL_LEAVE_CYCLE_"+year);
		if(StringUtil.isEmpty(dateCycles)) {
			if(year==2018) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2018;
			}else if(year==2019) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2019;
			}else if(year==2020) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2020;
			}
		}
		String[] dateCycle = dateCycles.split(",");
		String lastDate = dateCycle[0];//去年指定的日期
		String date = dateCycle[1];//今年指定的日期
		String nextDate = dateCycle[2];//明年指定的日期
		java.util.Date givenDate=sdf.parse(date);  
	    String sql=null;
		if(start.getTime() < givenDate.getTime()){
			sql = "select sum(t.leave_count2),t.leave_type from TJY2_RL_LEAVE t where t.people_id=? "+ 
					"and  t.apply_status in ('SPTG','YXJ') "+
					"and  to_char(t.start_date,'yyyy-MM-dd') > '"+lastDate+"' "+
			        "and  to_char(t.start_date,'yyyy-MM-dd') <='"+date+"' "+
					"group by t.leave_type";
		}else if(start.getTime() >= givenDate.getTime()){
			sql = "select sum(t.leave_count2),t.leave_type from TJY2_RL_LEAVE t where t.people_id=? "+ 
					"and  t.apply_status in ('SPTG','YXJ') "+
					"and  to_char(t.start_date,'yyyy-MM-dd') > '"+date+"' "+
			        "and  to_char(t.start_date,'yyyy-MM-dd') <='"+nextDate+"' "+
					"group by t.leave_type";
		}
		List<?> list = createSQLQuery(sql,peopleId).list();
		return list;
    }
	//查询角色所拥有的权限
	public List<?> getUserPower(String peopleId){
		String sql = "select t.name from SYS_ROLE t,sys_user_role r,sys_user u,employee e "+
					"where  r.role_id=t.id and u.id=r.user_id and e.id=u.employee_id and u.employee_id = ?";
		List<?> list = this.createSQLQuery(sql,peopleId).list();
		return list;
	}
	//通过请休假ID查询流程主键ID
	public  String queryMainId(HttpServletRequest request, String id) throws Exception {
		String mainId = null;
		String sql = "select t.ID from v_pub_worktask t where t.SERVICE_ID = ? and t.STATUS='0'";
		List<?> list = this.createSQLQuery(sql,id).list();
		if(list!=null && !list.isEmpty()){
			mainId = list.get(0).toString();
		}
		return mainId;
	}
	//通过请休假ID查询PROCESS_ID
	public  String queryProcessId(HttpServletRequest request, String id) throws Exception {
		String processId = null;
		String sql = "select DISTINCT t.PROCESS_ID from v_pub_worktask t where t.SERVICE_ID = ? and t.STATUS='3'";
		List<?> list = this.createSQLQuery(sql,id).list();
		if(list!=null && !list.isEmpty()){
			processId = list.get(0).toString();
		}
		return processId;
	}
	//通过请休假ID查询ACTIVITY_ID
	public  String queryActivityId(HttpServletRequest request, String id) throws Exception {
		String activityId = null;
		String sql = "select DISTINCT t.ACTIVITY_ID from v_pub_worktask t where t.SERVICE_ID = ? and t.STATUS='0'";
		List<?> list = this.createSQLQuery(sql,id).list();
		if(list.size()<=0){
			sql = "select DISTINCT t.ACTIVITY_ID from v_pub_worktask_add_position t where t.SERVICE_ID = ? and t.STATUS='0'";
			list = this.createSQLQuery(sql,id).list();
		}
		if(list!=null && !list.isEmpty()){
			activityId = list.get(0).toString();
		}
		return activityId;
	}
	//通过ID查询图片
	public  void yzImage(HttpServletRequest request, String id) throws Exception {
		String sql = "select t.sealpicture from  bcoa.seals t where t.id=?";
		List<?> list = this.createSQLQuery(sql,id).list();
		if(list!=null && !list.isEmpty()){
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println((list.get(0)).getClass().getSimpleName());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
	//计算段时间不含周末及法定节假日的天数
	public List<?> countDays(HttpServletRequest request, String startTime,String endTime) throws Exception {
		String sql = "select count(1) from (select * from PUB_GZR where to_char(rq,'yyyy-MM-dd') between '"+startTime+"' and '"+endTime+"') a where SFGZR='1'";
		System.out.println(sql.toString());
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}
	/**
  	 * 根据系统时间获取将要发送销假提醒的对象集合
  	 */
	public List<?> sendResumeRemind(){
  		List<?> list = new ArrayList<BgLeave>();
  		try {
			String sql="select * from TJY2_RL_LEAVE t where t.apply_status='SPTG' and to_char(t.end_date,'yyyy-MM-dd')<to_char(sysdate,'yyyy-MM-dd')"
					+ " order by t.dep_name";
			list = this.createSQLQuery(sql).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    }
	
	/**
	 * 今年已请休年假天数
	 * @param request
	 * @param peopleId
	 * @return
	 * @throws Exception
	 */
	public String queryYearDays(HttpServletRequest request,String peopleId)throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		int year = calendar.get(Calendar.YEAR);
		String dateCycles = Factory.getSysPara().getProperty("TJY2_RL_ANNUAL_LEAVE_CYCLE_"+year);
		if(StringUtil.isEmpty(dateCycles)) {
			if(year==2018) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2018;
			}else if(year==2019) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2019;
			}else if(year==2020) {
				dateCycles = Constant.TJY2_RL_ANNUAL_LEAVE_CYCLE_2020;
			}
		}
		String[] dateCycle = dateCycles.split(",");
		String lastDate = dateCycle[0];//去年指定的日期
		String date = dateCycle[1];//今年指定的日期
		String nextDate = dateCycle[2];//明年指定的日期
		
		String yearDay1=null;
		String sql = "";
		// 判断当前日期在那个计算周期中
		if(new Date().getTime()>new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()) {
			sql = "select NVL(sum(t.leave_count2), 0)" + 
					"  from TJY2_RL_LEAVE t" + 
					" where t.people_id = '"+peopleId+"'" + 
					"   and t.leave_type = 'NJ'" + 
					"   and t.apply_status in ('SPTG', 'YXJ')" + 
					"   and t.start_date > to_date('"+date+"', 'yyyy-mm-dd')" + 
					"   and t.start_date <= to_date('"+nextDate+"', 'yyyy-mm-dd')";
		}else {
			sql = "select NVL(sum(t.leave_count2), 0)" + 
					"  from TJY2_RL_LEAVE t" + 
					" where t.people_id = '"+peopleId+"'" + 
					"   and t.leave_type = 'NJ'" + 
					"   and t.apply_status in ('SPTG', 'YXJ')" + 
					"   and t.start_date <= to_date('"+date+"', 'yyyy-mm-dd')" + 
					"   and t.start_date > to_date('"+lastDate+"', 'yyyy-mm-dd')";
		}
		if(StringUtil.isNotEmpty(sql)) {
			List<?> list = this.createSQLQuery(sql).list();
			yearDay1=list.get(0).toString();
		}else {
			yearDay1="0";
		}
		return yearDay1;
    }
	//获取下一步审核人电话
	public String getAuditor(BgLeave bgLeave,String sign)throws Exception{
		String telphone=null;
		String sql = "";
		if(sign.equals("0")){
			//未到党政领导环节审核时获取信息的sql
			sql = "select r.emp_phone "+
					 "from sys_user s, tjy2_rl_employee_base r "+
					"where s.ID in (select distinct (t.handler_id) "+
					                 "from TJY2_RL_LEAVE b, v_pub_worktask t "+
					                "where b.id = t.SERVICE_ID "+
					                 // "and t.WORK_TYPE like '%TJY2_RL_%' "+
					                  "and t.STATUS = '0' "+
					                  "and b.id = '"+bgLeave.getId()+"') "+
					  "and r.id = s.employee_id and s.status='1'";
			if(bgLeave.getDepId().equals("100025") || bgLeave.getDepId().equals("100030") || bgLeave.getDepId().equals("100048")
					|| bgLeave.getDepId().equals("100044")){
				sql = "select r.emp_phone "+
						 "from sys_user s, tjy2_rl_employee_base r "+
						"where s.ID in (select distinct (t.handler_id) "+
						                 "from TJY2_RL_LEAVE b, v_pub_worktask_add_position t "+
						                "where b.id = t.SERVICE_ID "+
						                  //"and t.WORK_TYPE like '%TJY2_RL_%' "+
						                  "and t.STATUS = '0' "+
						                  "and b.id = '"+bgLeave.getId()+"') "+
						  "and r.id = s.employee_id and s.status='1'";
			}
		}else if(sign.equals("1")){
			//到党政领导环节审核时获取信息的sql
			sql = "select r.emp_phone "+
					"from sys_user s, tjy2_rl_employee_base r "+
						"where s.ID in (select distinct (t.handler_id) "+
										"from TJY2_RL_LEAVE b, v_pub_worktask t "+
											"where b.id = t.SERVICE_ID "+
												//"and t.WORK_TYPE like '%TJY2_RL_%' "+
												"and t.STATUS = '0' "+
												"and b.id = '"+bgLeave.getId()+"') "+
												"and r.id = s.employee_id "+
												"and s.status='1' "+
												"and r.id in (select t.fk_rl_emplpyee_id "+
																"from TJY2_RL_ORGID_LEADERID t "+
																	"where instr(t.fk_sys_org_id, '"+bgLeave.getDepId()+"') > 0)";
		}
		List<?> list = this.createSQLQuery(sql).list();
		telphone=list.get(0).toString();
		return telphone;
    }
	/**
	 * 统计请休假
	 * @param peopleId
	 * @return
	 */
	public List<BgLeave> count(String peopleId,String startDate,String lastDate){
		List<BgLeave> list=new ArrayList<BgLeave>();
		String hql="from BgLeave where peopleId='"+peopleId+"' and applyStatus in('SPTG','YXJ') "+
				"and startDate>=to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS') "+
				"and endDate<=to_date('"+lastDate+"','YYYY-MM-DD HH24:MI:SS') ";
		list = this.createQuery(hql).list();
        return list;
	}
	//判断此段时间是否已有申请
	public List<BgLeave> getLeave(HttpServletRequest request,String peopleId, String startTime,String endTime) throws Exception {
		List<BgLeave> list=new ArrayList<BgLeave>();
		String hql="from BgLeave where peopleId='"+peopleId+"' and applyStatus not in ('YCX','SPBTG') "+
				"and ((to_date('"+startTime+"','YYYY-MM-DD HH24:MI:SS') >=startDate and to_date('"+startTime+"','YYYY-MM-DD HH24:MI:SS') <=endDate) "+
				"or (to_date('"+endTime+"','YYYY-MM-DD HH24:MI:SS') >= startDate and to_date('"+endTime+"','YYYY-MM-DD HH24:MI:SS') <=endDate)) ";
		list = this.createQuery(hql).list();
		return list;
	}
	/**
	 * 统计公务外出
	 * @param peopleId
	 * @return
	 */
	public List<?> countGwwc(String org_id,String people_name,String startDate,String lastDate){
		List<?> list=new ArrayList<BgLeave>();
		String sql="select t.people_id, "+
			       "o.org_name, "+
			       "t.people_name, "+
			       "r.work_title, "+
			       "r.emp_phone, "+
			       "t.start_date, "+
			       "t.end_date, "+
			       "t.leave_reason "+
			  "from TJY2_RL_LEAVE t, TJY2_RL_EMPLOYEE_BASE r, SYS_ORG o "+
			 "where t.leave_type = 'GWWC' "+
			   "and t.apply_status in ('SPTG','YXJ') "+
			   "and t.start_date>=to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS') "+
			   "and t.end_date<=to_date('"+lastDate+"','YYYY-MM-DD HH24:MI:SS') "+
			   "and t.people_id = r.id(+) "+
			   "and r.work_department = o.id(+) ";
		if (StringUtil.isNotEmpty(people_name)) {
			sql += "and t.people_name = '"+people_name+"' ";
		}
		if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
			sql += "and r.work_department = '"+org_id+"' ";
		}
		sql += "order by o.orders,r.sort";
		list = this.createSQLQuery(sql).list();
		return list;
	}
	//根据业务ID获取当前审核环节信息
	public String getProcessInfo(String id,String orgId)throws Exception{
		String remark=null;
		try {
			//部门负责人、人事、分管领导、院长
			String sql = "select distinct (t.REMARK) "+
							"from TJY2_RL_LEAVE b, v_pub_worktask t "+
							"where b.id = t.SERVICE_ID "+
							"and t.WORK_TYPE like '%TJY2_RL_%' "+
							"and t.STATUS = '0' "+
							"and b.id = '"+id+"'";
			List<?> list = this.createSQLQuery(sql).list();
			if(list.size()<=0){
				sql = "select distinct (t.REMARK) "+
						"from TJY2_RL_LEAVE b, v_pub_worktask_add_position t "+
						"where b.id = t.SERVICE_ID "+
						"and t.WORK_TYPE like '%TJY2_RL_%' "+
						"and t.STATUS = '0' "+
						"and b.id = '"+id+"'";
				list = this.createSQLQuery(sql).list();
			}
			remark=list.get(0).toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KhntException("根据业务ID获取当前审核环节信息：业务ID：" + id);
		}
		return remark;
    }
	
	//根据业务ID获取当前审核环节信息
		public String getProcessInfo(String id)throws Exception{
			String remark=null;
			try {
				//部门负责人、人事、分管领导、院长
				String sql = "select distinct (t.REMARK) "+
								"from TJY2_RL_LEAVE b, v_pub_worktask t "+
								"where b.id = t.SERVICE_ID "+
								//"and t.WORK_TYPE like '%TJY2_RL_%' "+
								"and t.STATUS = '0' "+
								"and b.id = '"+id+"'";
				List<?> list = this.createSQLQuery(sql).list();
				if(list.size()<=0){
					sql = "select distinct (t.REMARK) "+
							"from TJY2_RL_LEAVE b, v_pub_worktask_add_position t "+
							"where b.id = t.SERVICE_ID "+
							//"and t.WORK_TYPE like '%TJY2_RL_%' "+
							"and t.STATUS = '0' "+
							"and b.id = '"+id+"'";
					list = this.createSQLQuery(sql).list();
				}
				if(!list.isEmpty()) {
					remark=list.get(0).toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new KhntException("根据业务ID获取当前审核环节信息：业务ID：" + id);
			}
			return remark;
	    }
	
	//获取当前审核人姓名
	public String getAuditorName(BgLeave bgLeave,String sign)throws Exception{
		String name=null;
		String sql = "";
		if(sign.equals("0")){
			//未到党政领导环节审核时获取信息的sql
			sql = "select r.emp_name "+
					 "from sys_user s, tjy2_rl_employee_base r "+
					"where s.ID in (select distinct (t.handler_id) "+
					                 "from TJY2_RL_LEAVE b, v_pub_worktask t "+
					                "where b.id = t.SERVICE_ID "+
					                  //"and t.WORK_TYPE like '%TJY2_RL_%' "+
					                  "and t.STATUS = '0' "+
					                  "and b.id = '"+bgLeave.getId()+"') "+
					  "and r.id = s.employee_id and s.status='1'";
			if(bgLeave.getDepId().equals("100025") || bgLeave.getDepId().equals("100030") || bgLeave.getDepId().equals("100048")){
				sql = "select r.emp_name "+
						 "from sys_user s, tjy2_rl_employee_base r "+
							"where s.ID in (select distinct (t.handler_id) "+
							                 "from TJY2_RL_LEAVE b, v_pub_worktask_add_position t "+
							                "where b.id = t.SERVICE_ID "+
							                  //"and t.WORK_TYPE like '%TJY2_RL_%' "+
							                  "and t.STATUS = '0' "+
							                  "and b.id = '"+bgLeave.getId()+"') "+
							  "and r.id = s.employee_id and s.status='1'";
			}
		}else if(sign.equals("1")){
			//到党政领导环节审核时获取信息的sql
			sql = "select r.emp_name "+
					"from sys_user s, tjy2_rl_employee_base r "+
						"where s.ID in (select distinct (t.handler_id) "+
										"from TJY2_RL_LEAVE b, v_pub_worktask t "+
											"where b.id = t.SERVICE_ID "+
												//"and t.WORK_TYPE like '%TJY2_RL_%' "+
												"and t.STATUS = '0' "+
												"and b.id = '"+bgLeave.getId()+"') "+
												"and r.id = s.employee_id "+
												"and s.status='1' "+
												"and r.id in (select t.fk_rl_emplpyee_id "+
																"from TJY2_RL_ORGID_LEADERID t "+
																	"where instr(t.fk_sys_org_id, '"+bgLeave.getDepId()+"') > 0)";
		}
		List<?> list = this.createSQLQuery(sql).list();
		if(!list.isEmpty()) {
			name=list.get(0).toString();
		}
		
		return name;
    }

	/**
	 * 获取已经过了假期的请假信息
     *
	 * @return List
	 */
	public List<?> getResumeInfo() {
		String sql="select l.id,l.PEOPLE_ID,l.PEOPLE_NAME,b.EMP_PHONE,l.START_DATE,l.END_DATE\n" +
				"  from TJY2_RL_LEAVE L,\n" +
				"       TJY2_RL_EMPLOYEE_BASE B\n" +
				" where L.PEOPLE_ID = B.ID\n" +
				"   and L.APPLY_STATUS = 'SPTG'\n" +
				"   and END_DATE < sysdate";
		try {
			return this.createSQLQuery(sql).list();
		} catch (Exception e) {
			this.logger.debug(sql);
			this.logger.error(e.getMessage(),e);
			return null;
		}
	}
}