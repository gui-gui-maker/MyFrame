package com.khnt.bpm.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.bpm.core.bean.Action;
import com.khnt.bpm.core.bean.Process;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("processDao")
public class ProcessDao extends EntityDaoImpl<Process> {

	/**
	 * 获取绑定事件
	 * 
	 * @param bid
	 *            帮定的ID
	 * @param type
	 *            事件类别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Action> findActions(String bid, String type) {
		String hql = "from Action where bindingId=? and actionTime=?";
		return createQuery(hql, bid, type).list();
	}

	/**
	 * 获取流程实例所有日志
	 * 
	 * @param processId
	 *            流程实例ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findTraskes(String processId) {
		String sql = "select tt.* from (\n" +
				"select t.person, t.exe_date, t.action, null as fpa\n" + 
				"  from flow_trask t\n" + 
				"  left join flow_activity fa\n" + 
				"    on fa.id = t.activity_id\n" + 
				" where fa.type != 'start'\n" + 
				"   and fa.type != 'end'\n" + 
				"   and fa.process = :pid\n" + 
				"union all\n" + 
				"select t.person, t.exe_date, t.action, fp.fk_parent_activity as fpa\n" + 
				"  from flow_trask t\n" + 
				"  left join flow_activity fa\n" + 
				"    on fa.id = t.activity_id\n" + 
				"  join flow_process fp\n" + 
				"    on fp.id = t.process_id\n" + 
				"  join flow_activity pa\n" + 
				"    on pa.id=fp.fk_parent_activity\n" + 
				" where fa.type != 'start'\n" + 
				"   and fa.type != 'end'\n" + 
				"   and pa.process = :pid\n" + 
				") tt order by tt.exe_date";
		SQLQuery q = (SQLQuery) createSQLQuery(sql);
		q.setString("pid", processId);
		List<Object> list = q.list();
		for (Object r : list) {
			Object[] tr = (Object[]) r;
			if (tr[3] != null)
				tr[2] = String.valueOf(tr[2]) + "【子流程】";
		}
		return list;
	}
}
