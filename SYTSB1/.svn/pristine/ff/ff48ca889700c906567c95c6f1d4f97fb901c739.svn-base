package com.lsts.office.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.bean.Org;
import com.khnt.utils.StringUtil;
import com.lsts.office.bean.MeetUpdateYijina;
import com.lsts.office.bean.MeetingNotes;
import com.lsts.office.bean.MeetingOrg;
import com.lsts.office.bean.MeetingReq;
import com.lsts.office.bean.MeetingUser;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoDao
 * @JDK 1.6
 * @author 
 * @date 2011-5-4 下午03:25:06
 */
@Repository("meetingReqDao")
public class MeetingReqDao extends EntityDaoImpl<MeetingReq> {
	
	
	/**
	 * 取会议室指定时间段的会议申请信息
	 * @param roomId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getMeetingReq(String reqId,String roomId,String startTime,String endTime){
		String hql="";
		if(StringUtil.isNotEmpty(reqId)){
			hql="from MeetingReq where fkOaMeetingRoom='"+roomId+"' and id<> '"+reqId+"'and ("+
					"(startTime between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "+
					"or "+
					"(endTime between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "+
	                ") " +
	                " or " +
	                "(startTime<=to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and endTime>= to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "+
	                " and (status!='6' or status!='5')";
		}else{
			hql="from MeetingReq where fkOaMeetingRoom='"+roomId+"' and ("+
					"(startTime between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "+
					"or "+
					"(endTime between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "+
	                ") " +
					" or " +
	                " (startTime<=to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and endTime>= to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss'))"+
	                " and (status!='6' or status!='5')";
		}
		List<MeetingReq> lMeetingReq=this.createQuery(hql).list();
		return lMeetingReq.size();
	}
	
	
	//保存会议申请信息
	public void saveMeetingReqInfo(MeetingReq meetingReq){
		/*String reqId = meetingReq.getId();
		String userSql="delete from OA_MEETING_REQ_USER where req_id='"+reqId+"'";
		createSQLQuery(userSql);
		String orgSql="delete from OA_MEETING_REQ_ORG where req_id='"+reqId+"'";
		createSQLQuery(orgSql);
		*/
		
//		List<CurrentSessionUser> users = meetingReq.getlUser();
//		List<SysOrg> orgs = meetingReq.getlOrg();
//		
//		//保存参会人员
//		for(CurrentSessionUser user:users){
//			 String userId=user.getId();
//			 String sql="INSERT INTO OA_MEETING_REQ_USER(req_id,user_id)  VALUES('"+reqId+"','"+userId+"');";
//			 createSQLQuery(sql);
//		}
//		
//		//保存参会单位
//		for(SysOrg org:orgs){
//			String orgId=org.getOrgid();
//			String sql="INSERT INTO OA_MEETING_REQ_ORG(req_id,org_id)  VALUES('"+reqId+"','"+orgId+"');";
//			createSQLQuery(sql);
//		}
	}
	
	/**获取单条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MeetUpdateYijina> getOne(String fileId) {
		String hql = "from MeetUpdateYijina where fileId=?";
		List<MeetUpdateYijina> list = createQuery(hql,fileId).list();
		return list;

	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> selectTEL(String name) {
		String Sql = "select MOBILE_TEL from  Employee  where name=?";
		List list = this.createSQLQuery(Sql,name).list();
		return list;
	}
	/**
	 * 通过申请表id获取意见表表id
	 * @param Id
	 * @return
	 */
	public List<MeetUpdateYijina>  getYjbID(String fileId){
		String sql = "select B.ID from TJY2_OFFCIE_MEET_YIJINA b, v_pub_worktask t where b.FILE=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_OFFICE_MEETING_FLOW%' and t.STATUS='0' and b.FILE_ID=?";
		List list = this.createSQLQuery(sql,fileId).list();
	    return  list;
	}
	/**
	 * 通过申请表id获取流程表表id
	 * @param Id
	 * @return
	 */
	public List getFlowId(String id){
		String sql = "select t.activity_id from TJY2_OFFICE_MEETING_REQ b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_OFFICE_MEETING_FLOW%' and t.STATUS='0' and b.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 通过申请表id查询申请表信息
	 * @param activityId
	 * @return
	 */
	public List<MeetingReq> getMeetingMassage(String id) {
		String hql = "from MeetingReq t where t.id=?";
		List<MeetingReq> list = this.createQuery(hql, id).list();
		return list;
	}
	
	/**
	 * 通过申请表id删除参会人信息
	 * @param Id
	 * @return
	 */
	public void  deleteReqUser(String reqId){
		String sql = "delete from TJY2_OFFICE_MEETING_REQ_USER b where b.REQ_ID=?";
		this.createSQLQuery(sql,reqId).executeUpdate();
	}
	/**
	 * 通过申请表id删除参会单位信息
	 * @param Id
	 * @return
	 */
	public void  deleteReqOrg(String reqId){
		String sql = "delete from TJY2_OFFICE_MEETING_REQ_ORG b where b.REQ_ID=?";
		this.createSQLQuery(sql,reqId).executeUpdate();
	}
	/**
	 * 刪除申请表信息
	 * @param Id
	 * @return
	 */
	public void  deleteReq(String Id){
		String sql = "delete from TJY2_OFFICE_MEETING_REQ b where b.ID=?";
		this.createSQLQuery(sql,Id).executeUpdate();
	}
	/**
	 * 通过申请表id查询会议记录id
	 * @param Id
	 * @return
	 */
	public List<MeetingNotes> selectReqNotes(String Id){
		String sql = "select * from TJY2_OFFICE_MEETING_MINUTES b where b.FKREQID=?";
		List<MeetingNotes> list = this.createSQLQuery(sql, Id).list();
		return list;
	}
	/**
	 * 通过sys_user表id查询employee表的id
	 * @param Id
	 * @return
	 */
	public List selectEmployeeId(String Id){
		String sql = "select EMPLOYEE_ID from SYS_USER b where b.id=?";
		List list = this.createSQLQuery(sql, Id).list();
		return list;
	}
		//通过申请表id查询出参会人员id
	public List<MeetingUser> getUerId(String id) {
		String hql = "from MeetingUser t where t.reqId=?";
		List<MeetingUser> list = this.createQuery(hql, id).list();
		return list;
	}

	// 通过申请表id查询出参会单位id
	public List<MeetingOrg> getOrgId(String id) {
		String hql = "from MeetingOrg t where t.reqId=?";
		List<MeetingOrg> list = this.createQuery(hql, id).list();
		return list;
	}
	// 通过申请部门 名称查询出部门id
		public List selectOrgId(String name) {
			String hql = "from Org t where t.orgName=?";
			List list = this.createQuery(hql, name).list();
			return list;
		}

	// 通过人员id查询出人员信息
	public Employee getUerMessage(String id){
			String hql = "from Employee t where t.id=?";
			List<Employee> list = this.createQuery(hql,id).list();
			if(list!=null && list.size()>0){
				return list.get(0);
			}
			return null;
		}
	// 通过单位id查询出单位信息
		public Org getOrgMessage(String id){
				String hql = "from Org t where t.id=?";
				List<Org> list = this.createQuery(hql,id).list();
				if(list!=null && list.size()>0){
					return list.get(0);
				}
				return null;
			}
		/**
		 * 通过参会人id获取参会人电话
		 * @param Id
		 * @return
		 */
		public List<Employee> getTel(String userId) {
			String Sql = "select MOBILE_TEL from  Employee  where id=?";
			List list = this.createSQLQuery(Sql,userId).list();
			return list;
		}
		/**
		 * 通过参会人id获取参会人微信
		 * @param Id
		 * @return
		 */
		public List<Employee> getWeixin(String userId) {
			String Sql = "select WECHAT from  TJY2_RL_EMPLOYEE_BASE  where id=?";
			List list = this.createSQLQuery(Sql,userId).list();
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
		//通过填入时间统计此时间段的会议室申请以及使用情况
		@SuppressWarnings("unchecked")
		public  int countReq(String reqId,String roomId,String startTime,String endTime) throws Exception {
			String sql="";
			if(StringUtil.isNotEmpty(reqId)){
				sql="select * from ("
						+"select * from TJY2_OFFICE_MEETING_REQ t "
						+"where t.fk_office_meeting_room='"+roomId+"' and "
						+"(t.start_time between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) or "
						+"(t.end_time between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) or "
						+"(t.start_time<=to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and t.end_time>= to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "
						+") where id <> '"+reqId+"' and  (status!='6' or status!='5')";
			}else{
				sql="select * from ("
						+"select * from TJY2_OFFICE_MEETING_REQ t "
						+"where t.fk_office_meeting_room='"+roomId+"' and "
						+"(t.start_time between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) or "
						+"(t.end_time between to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) or "
						+"(t.start_time<=to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') and t.end_time>= to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss')) "
						+") where status!='6' or status!='5'";
			}
			List<MeetingReq> lMeetingReq = this.createSQLQuery(sql).list();
			return lMeetingReq.size();
		}
}
