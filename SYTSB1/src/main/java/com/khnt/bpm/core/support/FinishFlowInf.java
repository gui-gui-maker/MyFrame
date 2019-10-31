package com.khnt.bpm.core.support;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Process;

/**
 * *****************************************************************************
 * 
 * <p>
 * 流程对应的事件接口类-------------流程结束事件
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-2-28 下午04:14:36
 */
public interface FinishFlowInf {

	/** 结束类别：正常结束 */
	public static final int FINISH_TYPE_COMMON = 1;

	/** 结束类别：被终止 */
	public static final int FINISH_TYPE_TERMINATE = 0;

	/**
	 * *************************************************************************
	 * 
	 * @param process 流程对象
	 * @param 0:业务审批时因为不同意而结束流程，1:正常结束
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void finishFlow(Process process, int finishType, BpmUser bpmUser) throws Exception;
}
