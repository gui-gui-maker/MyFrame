package com.khnt.bpm.ext.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.khnt.bpm.ext.bean.FlowProcessWorktask;
import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;

/**
 * 业务工作流配置DAO
 * 
 * @author hansel 2013-02-21
 */
@Repository("flowServiceConfigDao")
public class FlowServiceConfigDao extends EntityDaoImpl<FlowServiceConfig> {

	/**
	 * 获取指定业务的流程配置
	 * 
	 * @param serviceCode
	 *            业务编码，允许为空
	 * @param orgId
	 *            单位编号，允许为空
	 * @return 业务流程配置
	 */
	@SuppressWarnings("unchecked")
	public List<FlowServiceConfig> queryServiceConfig(String serviceCode, String orgId, String state) {
		Criteria criteria = this.createCriteria();
		if (!StringUtil.isEmpty(serviceCode))
			criteria.add(Restrictions.eq("serviceCode", serviceCode));
		if (!StringUtil.isEmpty(orgId))
			criteria.add(Restrictions.in("orgId", orgId.split(",")));
		// 流程必须为已发布的
		List<Object> flowIds = super.createQuery("select id from FlowDefinition where state=?", state).list();
		if (flowIds.isEmpty())
			return new ArrayList<FlowServiceConfig>();
		criteria.add(Restrictions.in("flowId", flowIds));
		return criteria.list();
	}

	/**
	 * 根据业务ID获取任务类别代码
	 * 
	 * @param serviceId
	 * @return
	 */
	public FlowProcessWorktask getWorktaskType(String processId) {
		Query query = super.createQuery("from FlowProcessWorktask where processId=:pid");
		Object obj = query.setString("pid", processId).uniqueResult();
		if (obj == null) {
			Object pid = super.createSQLQuery(
					"select a.process from flow_activity a join flow_process p on p.fk_parent_activity=a.id where p.id=?",
					processId).uniqueResult();
			if (pid != null)
				obj = query.setString("pid", pid.toString()).uniqueResult();
		}
		return obj == null ? null : (FlowProcessWorktask) obj;
	}
}
