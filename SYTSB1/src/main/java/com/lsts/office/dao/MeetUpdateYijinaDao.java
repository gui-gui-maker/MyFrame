package com.lsts.office.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.office.bean.MeetUpdateYijina;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2MeetUpdateYijinaDao
 * @JDK 1.6
 * @author 
 * @date  
 */
@Repository("meetUpdateYijinaDao")
public class MeetUpdateYijinaDao extends EntityDaoImpl<MeetUpdateYijina> {

	/**获取单条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MeetUpdateYijina> getOne(String fileId) {
		String hql = "from MeetUpdateYijina where fileId=?";
		List<MeetUpdateYijina> list = createQuery(hql,fileId).list();
		return list;

	}
	
	
	
	/**
	 * 通过申请表id获取意见表表id
	 * @param Id
	 * @return
	 */
	public List<MeetUpdateYijina>  getYjbID(String fileId){
		String sql = "select B.ID from TJY2_OFFCIE_MEET_YIJINA b, v_pub_worktask t where b.FILE=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_OFFICE_MEETREQ_FLOW%' and t.STATUS='0' and b.FILE_ID=?";
		List list = this.createSQLQuery(sql,fileId).list();
	    return  list;
	}
	/**
	 * 通过申请表id获取流程表表id
	 * @param Id
	 * @return
	 */
	public List getFlowId(String id){
		String sql = "select t.activity_id from TJY2_OFFICE_MEETING_REQ b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_OFFICE_MEETREQ_FLOW%' and t.STATUS='0' and b.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 通过流程id设置处理状态
	 * @param activityId
	 * @return
	 */
	
	public List getProcessId(String id){
		String sql = "select t.process_id from TJY2_OFFICE_MEETING_REQ b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_OFFICE_MEETREQ_FLOW%' and t.STATUS='0' and b.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
}

