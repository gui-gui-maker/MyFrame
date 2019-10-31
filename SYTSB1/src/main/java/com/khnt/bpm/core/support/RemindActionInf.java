package com.khnt.bpm.core.support;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.core.bean.Activity;

/*******************************************************************************
 * 
 * <p>
 * 流程活动对应的事件接口类-------------提醒活动事件
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-2-28 下午04:14:36
 */
public interface RemindActionInf {
	/***************************************************************************
	 * 提醒活动环节事件
	 * 
	 * @param activity
	 *            活动对象
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void remindAction(Activity activity) throws Exception;
}
