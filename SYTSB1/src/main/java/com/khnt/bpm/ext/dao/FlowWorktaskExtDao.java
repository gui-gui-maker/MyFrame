package com.khnt.bpm.ext.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class FlowWorktaskExtDao extends HibernateDaoSupport {

	/**
	 * 查找指定环节的处理人，该环节是已经完成处理的
	 * 
	 * @param activityId
	 * @return
	 */
	public List<?> findReturnedHandlers(String activityId) {
		Query q = getSession().createSQLQuery(
				"select distinct v.HANDLER_ID,v.HANDLER_NAME from v_pub_worktask v where v.ACTIVITY_ID = :aid and v.STATUS='3'");
		q.setParameter("aid", activityId);
		return q.list();
	}

	public void suspendProcessWorktask(String procId) {
		getSession()
				.createSQLQuery(
						"update pub_worktask set status='4' where service_id in(select id from flow_activity a where a.process=:pid) and (status='0' or status='2')")
				.setString("pid", procId).executeUpdate();
	}

	public void restartProcessWorktask(String procId) {
		getSession()
				.createSQLQuery(
						"update pub_worktask set status='0' where service_id in(select id from flow_activity a where a.process=:pid) and status='4'")
				.setString("pid", procId).executeUpdate();
	}
}
