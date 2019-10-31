package com.lsts.qualitymanage.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.TaskAllotFk;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName TaskAllotFkDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("taskAllotFkDao")
public class TaskAllotFkDao extends EntityDaoImpl<TaskAllotFk> {
	/**
	 * 查询延期时间
	 * @param delay
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskAllotFk> Detail(Date delay,String id){
		List<TaskAllotFk> list = new ArrayList<TaskAllotFk>();
		String sql = "select t.delay from TJY2_QUALTTY_ALLOT_FK t,TJY2_QUALTTY_ALLOT a "
				+ " where a.id='"+id+"' "  //t.delay=?
				+ " and a.id=t.PK_FEEDBACK_ID";
		list = createSQLQuery(sql).list();
		return list;
	}
}