package com.khnt.bpm.ext.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.bean.FlowFunction;
import com.khnt.bpm.ext.bean.FlowType;
import com.khnt.bpm.ext.dao.FlowFunctionDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * 流程功能管理manager
 * 
 */
@Service("flowFunctionManager")
public class FlowFunctionManager extends EntityManageImpl<FlowFunction, FlowFunctionDao> {

	/**
	 * 流程扩展功能项-发送短信
	 */
	public static final String FLOW_FUNCTION_SEND_SMS = "pub_send_sms";
	/**
	 * 流程扩展功能项-发送消息
	 */
	public static final String FLOW_FUNCTION_SEND_MSG = "pub_send_msg";

	/**
	 * 流程扩展功能项-写工作任务
	 */
	public static final String FLOW_FUNCTION_WORKTASK = "pub_worktask";

	@Autowired
	FlowFunctionDao flowFunctionDao;

	@Autowired
	FlowTypeManager flowTypeManager;

	/***************************************************************************
	 * 根据流程分类代码 得到该分类下面的流程功能
	 * 
	 * @param flowtype
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getFunctions(String flowtype) throws Exception {
		List<FlowType> fts = flowTypeManager.getAllParentFlowTypes(flowtype);
		List<Object> typeIds = new ArrayList<Object>();
		for (FlowType ft : fts)
			typeIds.add(ft.getId());

		StringBuffer functions = new StringBuffer();

		List<FlowFunction> flowfunctions = flowFunctionDao.createCriteria(Restrictions.in("flowType", typeIds)).list();
		int j = flowfunctions.size();
		for (int i = 0; i < j; i++) {
			FlowFunction flowFunction = (FlowFunction) flowfunctions.get(i);
			if (i != 0)
				functions.append(";");
			functions.append(flowFunction.getCode()).append(",").append(flowFunction.getName()).append(",")
			        .append(flowFunction.getChecked()).append(",")
			        .append("top".equals(flowFunction.getFlowType()) ? "pub" : "buss");
		}
		return functions.toString();
	}

	/***************************************************************************
	 * 判断当前流程功能的编号是否合法
	 * 
	 * @param user
	 * @return
	 */
	public Boolean functionLegitimate(FlowFunction flowFunction) throws Exception {
		if (StringUtil.isEmpty(flowFunction.getId())) {// 新增功能
			String hql = "from  FlowFunction where code=?";
			Query q = flowFunctionDao.createQuery(hql);
			q.setParameter(0, flowFunction.getCode());
			if (q.list().size() == 0)
				return true;
		} else {// 修改功能
			String hql = "from  FlowFunction where code=? and id <> ? ";
			Query q = flowFunctionDao.createQuery(hql);
			q.setParameter(0, flowFunction.getCode());
			q.setParameter(1, flowFunction.getId());
			if (q.list().size() == 0)
				return true;
		}
		return false;
	}
}
