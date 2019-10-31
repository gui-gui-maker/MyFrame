package com.khnt.bpm.ext.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;

@Repository
public class FlowExtDao extends HibernateDaoSupport {

	/**
	 * 清除业务流程
	 * 
	 * @param serviceId
	 * @throws Exception
	 */
	public void clearServiceProcess(String serviceId) throws Exception {
		String[] sqls = new String[] {
		        "DELETE FROM FLOW_TIMER T WHERE T.TIMER_KIND_ID IN(SELECT ID FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='"
		                + serviceId
		                + "')  OR T.TIMER_KIND_ID IN (SELECT A.ID FROM FLOW_ACTIVITY A, FLOW_PROCESS P WHERE P.ID=A.PROCESS AND P.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_ACTION T WHERE T.BINDING_ID IN(SELECT ID FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='"
		                + serviceId
		                + "')  OR T.BINDING_ID IN (SELECT A.ID FROM FLOW_ACTIVITY A, FLOW_PROCESS P WHERE P.ID=A.PROCESS AND P.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_TRASK T WHERE T.PROCESS_ID IN (SELECT ID FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_TRANSITION T WHERE T.PROCESS IN (SELECT ID FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_PARTICIPATOR T WHERE T.ACTIVITY_ID IN (SELECT A.ID FROM FLOW_ACTIVITY A, FLOW_PROCESS P WHERE P.ID=A.PROCESS AND P.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_OPINION T WHERE T.SERVICE_ID='" + serviceId + "'",
		        "DELETE FROM PUB_WORKTASK T WHERE T.SERVICE_ID IN (SELECT A.ID FROM FLOW_ACTIVITY A, FLOW_PROCESS P WHERE P.ID=A.PROCESS AND P.BUSINESS_ID='"
		                + serviceId + "')",
		        "DELETE FROM FLOW_ACTIVITY T WHERE T.PROCESS IN (SELECT ID FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='"
		                + serviceId + "')", "DELETE FROM FLOW_PROCESS T WHERE T.BUSINESS_ID='" + serviceId + "'" };

		Connection conn = Factory.getDB().getConnetion();
		boolean preAutoCommit = conn.getAutoCommit();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		for (String sql : sqls)
			stmt.addBatch(sql);
		stmt.executeBatch();
		conn.setAutoCommit(preAutoCommit);
	}

	public List<?> findTraskParticiptors(String processId, String cuid) {
		Query q = getSession()
		        .createQuery(
		                "select distinct personId, person from Trask t where processId=:pid and personId!=:uid and personId!=null");
		q.setParameter("pid", processId);
		q.setParameter("uid", cuid);
		return q.list();
	}
}
