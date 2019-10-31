package com.lsts.office.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.office.bean.MeetingNotes;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoDao
 * @JDK 1.5
 * @author 
 * @date 2011-5-4 下午03:25:06
 */
@Repository("meetingNotesDao")
public class MeetingNotesDao extends EntityDaoImpl<MeetingNotes> {
	//删除会议记录表信息
	public void deleteMeetingNotes(String reqId){
		String sql="delete from TJY2_OFFICE_MEETING_MINUTES b where b.FKREQID=?";
		this.createSQLQuery(sql, reqId).executeUpdate();
	}
}
