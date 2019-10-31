package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.Taskbook;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualityTaskbookDao
 * @JDK 1.5
 * @author Jonny
 * @date  2016年3月1日 10:55:39
 */
@Repository("TaskbookDao")
public class TaskbookDao extends EntityDaoImpl<Taskbook> {
	
	
	@SuppressWarnings("unchecked")
	public List<Taskbook> getTaskAllot() {
		String hql = "from Taskbook where identifier is not null";
		List<Taskbook> list = createQuery(hql).list();
		return list;
	}

}