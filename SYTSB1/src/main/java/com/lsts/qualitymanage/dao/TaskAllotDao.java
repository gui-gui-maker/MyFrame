package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.qualitymanage.bean.TaskAllot;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName TaskAllotDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("taskAllotDao")
public class TaskAllotDao extends EntityDaoImpl<TaskAllot> {
	
	
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskAllot> getTaskAllot() {
		String hql = "from TaskAllot";
		List<TaskAllot> list = createQuery(hql).list();
		return list;
	}
	/**
	 * 获取任务内容
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskAllot> getAllots(){
		String hql = "from TaskAllot t where t.status = ?";
		return createQuery(hql, "YXF").list();
	}
	
	/**查找申请人手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getsjh(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,userId).list();
		return list;

	}
	
	
}