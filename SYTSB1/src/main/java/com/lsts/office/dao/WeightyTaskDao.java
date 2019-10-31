package com.lsts.office.dao;


import java.util.List;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.office.bean.WeightyTask;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName WeightyTaskDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("weightyTaskDao")
public class WeightyTaskDao extends EntityDaoImpl<WeightyTask> {

	
	
	/**
	 * 获取推送信息
	 * @param Id
	 * @return
	 */
	public List getUser(String taskLv,String id){
		String sql = "";
		if(taskLv == "1" ){
			 sql = "select t.MAIN_DUTY_ID, t.MAIN_DUTY_NAME,e.MOBILE_TEL,t.TASK_CONTENT,t.FINISH_TIME from (select * from TJY2_BG_WEIGHTYTASK where  id='"+id+"')t left join EMPLOYEE e  on t.main_duty_name = e.name ";
		}else if(taskLv == "2"){
			sql = "select t.REGISTRANT_ID, t.REGISTRANT_NAME,e.MOBILE_TEL,t.TASK_CONTENT,t.FINISH_TIME from (select * from TJY2_BG_WEIGHTYTASK where  id='"+id+"')t left join EMPLOYEE e  on t.main_duty_name = e.name ";
		}else{
			sql = "select t.MAIN_DUTY_ID, t.MAIN_DUTY_NAME,e.MOBILE_TEL,t.TASK_CONTENT,t.FINISH_TIME from (select * from TJY2_BG_WEIGHTYTASK where  status='JXZ')t left join EMPLOYEE e  on t.main_duty_name = e.name ";
		}
		
		List list = this.createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 获取推送信息
	 * @param Id
	 * @return
	 */
	public List gettel(String id){
		String sql = "";
		sql = "select t1.mobile_tel from EMPLOYEE t1 where t1.id in(select t.employee_id from SYS_USER t where t.id='"+id+"')";
		
		List list = this.createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 获取员工的电话号码
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getsjh(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,userId).list();
		return list;

	}
	
}