package com.khnt.bpm.core.support;

import java.util.List;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.utils.StringUtil;

/**
 * 工作流程操作结束后，返回给业务的结果
 * 
 * @author hansel
 * 
 */
public class FlowServiceResult {

	/**
	 * 操作类别：启动流程
	 */
	public static final int RESULT_TYPE_START_PROCESS = 1;
	/**
	 * 操作类别：环节提交
	 */
	public static final int RESULT_TYPE_SUBMIT_ACTIVITY = 2;
	/**
	 * 操作类别：环节会签提交
	 */
	public static final int RESULT_TYPE_SUBMIT_COUNTER_SIGN = 5;
	/**
	 * 操作类别：环节退回
	 */
	public static final int RESULT_TYPE_RETURNED_ACTIVITY = 3;
	/**
	 * 操作类别：结束流程
	 */
	public static final int RESULT_TYPE_FINISH_PROCESS = 4;
	
	/**
	 * 操作类别 ：启动子流程
	 */
	public static final int RESULT_TYPE_START_SUB_PROCESS = 6;
	/**
	 * 操作类别 ：完成子流程
	 */
	public static final int RESULT_TYPE_FINISH_SUB_PROCESS = 7;

	/**
	 * 操作类别:启动流程,提交环节,退回流程,结束流程
	 */
	private int resultType;

	/**
	 * 流程ID
	 */
	private String processFlowId;

	/**
	 * 流程名称
	 */
	private String processFlowName;

	private List<Activity> resultActivity;

	public FlowServiceResult() {
		super();
	}

	public FlowServiceResult(int resultType, String processFlowId, String processFlowName, List<Activity> resultActivity) {
		super();
		this.resultType = resultType;
		this.processFlowId = processFlowId;
		this.processFlowName = processFlowName;
		this.resultActivity = resultActivity;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getProcessFlowId() {
		return processFlowId;
	}

	public void setProcessFlowId(String processFlowId) {
		this.processFlowId = processFlowId;
	}

	public String getProcessFlowName() {
		return processFlowName;
	}

	public void setProcessFlowName(String processFlowName) {
		this.processFlowName = processFlowName;
	}

	public List<Activity> getResultActivity() {
		return resultActivity;
	}

	public void setResultActivity(List<Activity> resultActivity) {
		this.resultActivity = resultActivity;
	}

	/**
	 * 查找的定参与者是否存在于本次流程操作结果中的后续环节中.
	 * 
	 * 可用于检查该参与者是否是后续环节的参与者.
	 * 
	 * @param bpmUser
	 * @return
	 */
	public Activity findPersonParticipator(BpmUser bpmUser) {
		if (this.getResultActivity() == null || bpmUser == null)
			return null;

		for (Activity activity : this.getResultActivity()) {
			if (activity.getParticipators() == null)
				continue;
			for (Participator participator : activity.getParticipators()) {
				if (bpmUser.getId().equals(participator.getParticipantId()))
					return activity;
				else if ("role".equals(participator.getParticipantType()) && bpmUser.getRole() != null
						&& bpmUser.getRole().containsKey(participator.getParticipantId())) {
					String range = participator.getParticipantRange();
					String rangeType = participator.getParticipantRangeType();
					if ("all".equals(rangeType) || StringUtil.isEmpty(range))
						return activity;
					else if (("databus".equals(rangeType) || "dep".equals(rangeType))
							&& bpmUser.getDepartment().getId().equals(range))
						return activity;
					else if ("unit".equals(rangeType) && bpmUser.getUnit().getId().equals(range))
						return activity;
					else if ("sunit".equals(rangeType) && bpmUser.getUnit().getParent() != null
							&& bpmUser.getUnit().getParent().getId().equals(range))
						return activity;
				}
			}
		}
		return null;
	}
}
