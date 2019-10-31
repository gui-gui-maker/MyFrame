package com.khnt.bpm.core.support;

import com.khnt.bpm.core.bean.Activity;

/**
 * 工作流环节退回事件监听器
 */
public interface TurnbackActionInf {

	/**
	 * 退回环节事件触发方法
	 * 
	 * @param from
	 *            退回源环节
	 * @param to
	 *            目标环节
	 */
	public void turnbackAction(Activity from, Activity to);
}
