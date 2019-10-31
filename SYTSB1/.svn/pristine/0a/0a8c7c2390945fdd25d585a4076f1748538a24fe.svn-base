package com.lsts.office.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.office.bean.Ywhbsgz;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ywhbsgzDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("ywhbsgzDao")
public class YwhbsgzDao extends EntityDaoImpl<Ywhbsgz> {
	/**
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Ywhbsgz> Ywhbsgz(String ids) {
		String hql = "from Ywhbsgz t where t.id=? ";
		return createQuery(hql,ids).list();
		}
	
	
	//EMPLOYEE 责任人表
	//查询出任务内容 过期时间 责任人 id  name 电话号码
	public List getUser(String taskLv,String id){
		String sql="";
		if(taskLv == "1" ){
			sql = "select  t.department,t.mission_content,t.end_tim,f.name,f.mobile_tel,f.id  from (select * from TJY2_YWHBSGZ  where id='"+id+"') t  left join EMPLOYEE f  on t.responsible_person=f.name";
		}else if(taskLv == "2"){
			sql = "select  t.department,t.mission_content,t.end_tim,f.name,f.mobile_tel,f.id  from (select * from TJY2_YWHBSGZ  where id='"+id+"') t  left join EMPLOYEE f  on t.responsible_person=f.name";
		}else{
			sql = "select  t.department,t.mission_content,t.end_tim,f.name,f.mobile_tel,f.id  from (select * from TJY2_YWHBSGZ  where status='JXZ') t  left join EMPLOYEE f  on t.responsible_person=f.name";
		}
		List list = this.createSQLQuery(sql).list();
		return list;
	}


	@SuppressWarnings("rawtypes")
	public List getmid(String ids) {
		String sql = "select * from SYS_USER where id=?";
		List list = this.createSQLQuery(sql,ids).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getsjh(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,userId).list();
		return list;

	}

}