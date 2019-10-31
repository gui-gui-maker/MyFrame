package com.khnt.bpm.core.support;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.core.bean.Activity;

/*******************************************************************************
 * 
 * <p>
 * 流程活动对应的事件接口类-------------创建活动事件
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-2-28 下午04:14:36
 */
public interface CreateActionInf {
	/***************************************************************************
	 * 创建活动环节事件
	 * 
	 * @param activity
	 *            活动对象
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void createAction(Activity activity) throws Exception;
}
