package com.lsts.office.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.office.bean.MeetingRoomInfo;
import com.lsts.office.dao.MeetingRoomInfoDao;


/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoManager
 * @JDK 1.6
 * @author 
 * @date 
 */
@Service("meetingInfoManager")
public class MeetingRoomInfoManager extends EntityManageImpl<MeetingRoomInfo, MeetingRoomInfoDao> {
	@Autowired
	private MeetingRoomInfoDao meetingInfoDao;
	public boolean saveUsedState(String id){
		try{
			MeetingRoomInfo a=this.get(id);
			a.setCreate_time(new Date());
		    return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
	//判断会议室是否存在
	public boolean existMeetingRoom(MeetingRoomInfo meeting){
		String roomCode=meeting.getCode();
		if(StringUtil.isNotEmpty(roomCode)){
			String orgId=SecurityUtil.getSecurityUser().getUnit().getId();
			String hql=" from MeetingRoomInfo where code='"+roomCode+"' and unit_id='"+orgId+"' and id<>'"+meeting.getId()+"'";
			List list=meetingInfoDao.createQuery(hql).list();
			if(list.size()>0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//删除会议室信息
	public String delMeetingRoom(String ids){
		String info="";
		String roomIds=IdFormat.formatIdStr(ids);
		String sql=" select room.code from  TJY2_OFFICE_MEETING_ROOM room,TJY2_OFFICE_MEETING_REQ req \n"+
				   " where room.id=req.FK_OFFICE_MEETING_ROOM \n"+
				   " and room.id in ("+roomIds+") \n"+
				   " group by code";
		List list=meetingInfoDao.createSQLQuery(sql).list();
	    for(int i=0;i<list.size();i++){
	    	if(i>0){
	    		info+=",";
	    	}
	    	info+=list.get(i);
	    }
		
       sql=" delete from TJY2_OFFICE_MEETING_ROOM room \n"+
           " where room.id not in (select FK_OFFICE_MEETING_ROOM from TJY2_OFFICE_MEETING_REQ group by FK_OFFICE_MEETING_ROOM ) \n"+
		   " and room.id in ("+roomIds+") ";
       meetingInfoDao.createSQLQuery(sql).executeUpdate();
		return info;
	}
}
