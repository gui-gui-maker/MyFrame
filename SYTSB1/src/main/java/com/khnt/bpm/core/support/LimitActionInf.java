package com.khnt.bpm.core.support;

import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.bpm.core.bean.Activity;

/**
 * <p>
 * 流程活动对应的事件接口类-------------超时活动事件
 * </p>
 */
public interface LimitActionInf {
	/**
	 * 超时活动环节事件
	 * 
	 * @param activity
	 *            活动对象
	 * @param limitDate
	 *            超时时间
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void limiAction(Activity activity, Date limitDate) throws Exception;
}
