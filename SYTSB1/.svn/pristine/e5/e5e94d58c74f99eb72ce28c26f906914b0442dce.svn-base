package com.lsts.office.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.impl.dao.OrgDao;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.utils.StringUtil;
import com.lsts.office.bean.MeetingNotes;
import com.lsts.office.bean.MeetingReq;
import com.lsts.office.dao.MeetingNotesDao;
import com.lsts.office.dao.MeetingReqDao;


/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoManager
 * @JDK 1.5
 * @author 
 * @date 
 */
@Service("meetingNotesManager")
public class MeetingNotesManager extends EntityManageImpl<MeetingNotes, MeetingNotesDao> {
	@Autowired
	private MeetingNotesDao meetingNotesDao;
	
	@Autowired
	private MeetingReqDao meetingReqDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrgDao orgDao;
	public void save(MeetingNotes meetingNotes,HttpServletRequest request) throws Exception {
		//更新会议申请表数据的状态
		String id=request.getParameter("id");
			meetingNotesDao.save(meetingNotes);
		String reqId=meetingNotes.getFkreqid();
		MeetingReq meetingReq=meetingReqDao.get(reqId);
		meetingReq.setMeeting_status("2");
		meetingReq.setContent(meetingNotes.getContent());
		meetingReqDao.save(meetingReq);
		
		//删除会议申请时的参会人员及参会单位
	    /*String sql="delete from oa_meeting_req_user where req_id='"+reqId+"'";
	    meetingReqDao.getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	    sql="delete from oa_meeting_req_org where req_id='"+reqId+"'";
	    meetingReqDao.getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();*/
	    
		//更新参会单位参会人员信息
//		String meetingUser=meetingNotes.getMeetingUser();
//		String[] userIds=meetingUser.split(",");
//		for(String userId:userIds){
//			 sql="insert into oa_meeting_req_user (req_id,user_id)  values('"+reqId+"','"+userId+"')";
//			 meetingReqDao.getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
//		}
//		
//		String meetingOrg=meetingNotes.getMeetingOrg();
//		String[] orgIds=meetingOrg.split(",");
//		for(String orgId:orgIds){
//			 sql="insert into oa_meeting_req_org (req_id,org_id)  values('"+reqId+"','"+orgId+"')";
//			 meetingReqDao.getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
//		}
//		this.meetingNotesDao.save(meetingNotes);
	}
	//删除会议记录信息
	public void deleteMeetingNotes(String reqId) throws Exception {
		
		List<MeetingReq> list=meetingReqDao.getMeetingMassage(reqId);
		if(list.size()>0){
			meetingNotesDao.deleteMeetingNotes(reqId);
			MeetingReq meetingReq=meetingReqDao.get(reqId);
			meetingReqDao.save(meetingReq);
			
		}else{
			meetingNotesDao.deleteMeetingNotes(reqId);
			meetingReqDao.deleteReqOrg(reqId);
			meetingReqDao.deleteReqUser(reqId);
		}
		}
	public HashMap<String, Object> getMeetingNotesInfo(String notesId) throws Exception{
		   HashMap<String, Object> notesInfo = new HashMap<String, Object>();
			MeetingNotes meetingNotes=meetingNotesDao.get(notesId);
			notesInfo.put("success", true);
			if(meetingNotes!=null){
				notesInfo.put("data", meetingNotes);
			}
			return notesInfo;
	}
}
