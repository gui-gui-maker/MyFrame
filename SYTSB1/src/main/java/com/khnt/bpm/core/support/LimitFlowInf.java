package com.khnt.bpm.core.support;

import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.core.bean.Process;

/**
 * <p>
 * 流程对应的事件接口类-------------流程超时事件
 * </p>
 */
public interface LimitFlowInf {
	/**
	 * 超时流程事件
	 * 
	 * @param process
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void limitFlow(Process process, Date limitTime) throws Exception;
}
