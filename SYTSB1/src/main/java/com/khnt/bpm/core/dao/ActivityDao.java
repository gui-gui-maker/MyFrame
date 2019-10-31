package com.khnt.bpm.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("activityDao")
public class ActivityDao extends EntityDaoImpl<Activity> {

	@SuppressWarnings("unchecked")
	public List<Activity> getCurrentServiceActivity(String serviceId) {
		return createQuery("from Activity where process.businessId=? and state='300'", serviceId).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getCurrentProcessActivity(String pid) {
		return createQuery("from Activity where process.id=? and state='300'", pid).list();
	}
	
	/**
	 * 根据环节ID，获取对应的流程启动环节
	 * 
	 * @param activityId
	 * @return
	 */
	public Activity getStartActivity(String processId) {
		return (Activity) createQuery("from Activity where process=? and type=?", processId, "start").uniqueResult();
	}
}