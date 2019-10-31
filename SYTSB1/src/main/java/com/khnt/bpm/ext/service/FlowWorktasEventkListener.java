package com.khnt.bpm.ext.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.support.DefaultLimitActionInf;
import com.khnt.bpm.core.support.DefaultLimitFlowInf;
import com.khnt.pub.worktask.bean.Worktask;
import com.khnt.pub.worktask.dao.WorktaskDao;
import com.khnt.pub.worktask.service.WorktaskManager;

/**
 * 工作流/活动环节超时事件处理监听器.
 * 
 * 本监听器实现了流程和环节超时默认处理接口的规范，在流程和活动环节超时发生的时候，会响应执行。
 * 
 * 本监听器的执行目标是将所有超时环节的工作任务状态变更为超时。
 * 
 */
@Service
public class FlowWorktasEventkListener implements DefaultLimitActionInf, DefaultLimitFlowInf {

	@Autowired
	private WorktaskDao worktaskDao;

	/**
	 * 环节超时处理。目的在于把超时任务状态同时变更为“逾期”
	 * 
	 * @see com.khnt.bpm.core.support.LimitActionInf#limiAction(com.khnt.bpm.core.bean.Activity)
	 */
	public void limiAction(Activity activity, Date limitDate) throws Exception {
		List<Worktask> ws = this.worktaskDao.findServiceWorktask(activity.getId(), WorktaskManager.STATUS_NO_PEND);
		for (Worktask w : ws) {
			w.setOverdueTime(limitDate);
			w.setStatus(WorktaskManager.STATUS_OVREDUE);
		}
	}

	/**
	 * 流程超时默认处理方法
	 */
	public void limitFlow(Process process, Date limitDate) throws Exception {

	}
}
