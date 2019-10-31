package com.khnt.bpm.ext.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.ext.bean.FlowProcessWorktask;
import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.bpm.ext.dao.FlowServiceConfigDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;

/**
 * 业务流程配置service
 * 
 * @author hansel 2013-02-22
 * 
 */
@Service("flowServiceConfigManager")
@Transactional
public class FlowServiceConfigManager extends EntityManageImpl<FlowServiceConfig, FlowServiceConfigDao> {

	@Autowired
	private FlowServiceConfigDao flowServiceConfigDao;

	/**
	 * 按照业务编码、单位ID获取业务流程配置
	 * 
	 * @param serviceCode
	 *            业务编码，允许为空
	 * @param orgId
	 *            单位机构ID，允许为空
	 * @return 业务流程配置
	 */
	public List<FlowServiceConfig> queryServiceConfig(String serviceCode, String orgId) throws Exception {
		if (StringUtil.isEmpty(serviceCode) && StringUtil.isEmpty(orgId))
			throw new Exception("业务编码和机构ID必须至少提供一个");
		return this.flowServiceConfigDao.queryServiceConfig(serviceCode, orgId, FlowDefinitionManager.FLOW_STATE_ISSUE);
	}

	/**
	 * 覆写保存配置方法。检查是否重复配置
	 * 
	 * @see com.khnt.core.crud.manager.impl.EntityManageImpl#save(com.khnt.core.crud.bean.BaseEntity)
	 */
	public void save(FlowServiceConfig serviceConfig) throws Exception {
		int count = this.flowServiceConfigDao
				.createCriteria(Restrictions.eq("serviceCode", serviceConfig.getServiceCode()))
				.add(Restrictions.eq("flowId", serviceConfig.getFlowId()))
				.add(Restrictions.eq("orgId",serviceConfig.getOrgId()))
				.add(Restrictions.not(StringUtil.isEmpty(serviceConfig.getId()) ? Restrictions.isNull("id")
						: Restrictions.idEq(serviceConfig.getId()))).list().size();
		if (count > 0)
			throw new KhntException("该配置已经存在!");
		super.save(serviceConfig);
	}

	/**
	 * 获取待办事宜的业务类别编码
	 * 
	 * @param serviceId业务编码
	 * @return
	 * @throws Exception
	 */
	public FlowProcessWorktask getWorktaskType(String processId) throws Exception {
		FlowProcessWorktask FlowProcessWorktask = this.flowServiceConfigDao.getWorktaskType(processId);
		if (FlowProcessWorktask == null)
			throw new KhntException("尚未为流程[" + processId + "]设定工作任务类别");
		return FlowProcessWorktask;
	}
}
