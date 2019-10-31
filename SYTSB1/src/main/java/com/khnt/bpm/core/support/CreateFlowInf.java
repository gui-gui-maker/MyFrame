package com.khnt.bpm.core.support;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.core.bean.Process;

/*******************************************************************************
 * 
 * <p>
 * 流程对应的事件接口类-------------创建流程事件
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-2-28 下午04:14:36
 */
public interface CreateFlowInf {
	/***************************************************************************
	 * 创建流程
	 * 
	 * @param process
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void createFlow(Process process) throws Exception;
}
