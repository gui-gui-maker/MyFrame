package com.khnt.bpm.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.core.bean.Timer;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("timeDao")
public class TimerDao extends EntityDaoImpl<Timer> {

	/**
	 * 变更定时器状态
	 * 
	 * @param id
	 * @param state
	 */
	public void changeTimerState(String id, String state) {
		this.createQuery("update Timer set currentstate=? where id=?", state, id).executeUpdate();
	}

	/**
	 * 变更指定业务的定时器状态
	 * 
	 * @param id
	 * @param state
	 */
	public void changeServiceTimerState(String serviceId, String state) {
		this.createQuery("update Timer set currentstate=? where timerKindID=?", state, serviceId).executeUpdate();
	}

	/**
	 * 清除定时任务
	 * 
	 * @param bindingId
	 */
	public void clearTimer(String bindingId) {
		this.createQuery("update Timer set currentstate=? where timerKindID=?", "3", bindingId).executeUpdate();
	}

	/**
	 * 查找定时器，根据绑定对象，状态，类别
	 * 
	 * @param bindingId
	 * @param status
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Timer> findBindingTimer(String bindingId, String status, String name, String type) {
		return createQuery(
				"from Timer t where action is null and t.timerKindID=? and t.timerKind=? and currentstate=? and timerName=?",
				bindingId, type, status, name).list();

	}
}
