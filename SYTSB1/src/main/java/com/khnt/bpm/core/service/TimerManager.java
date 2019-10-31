package com.khnt.bpm.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Timer;
import com.khnt.bpm.core.dao.ActivityDao;
import com.khnt.bpm.core.dao.TimerDao;
import com.khnt.bpm.core.support.BpmTimeParser;
import com.khnt.bpm.core.support.BpmWorkdayTimeParser;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.core.utils.BeanClassUtil;
import com.khnt.utils.StringUtil;

@Service("timerManager")
public class TimerManager extends EntityManageImpl<Timer, TimerDao> {

	/** 定时器状态：激活 */
	public static final String TIMER_STATUS_ACTIVE = "1";

	/** 定时器状态：挂起 */
	public static final String TIMER_STATUS_SUSPEND = "2";

	/** 定时器状态：已执行 */
	public static final String TIMER_STATUS_DONE = "3";

	/** 事件类别：超时 */
	public static final String TIMER_TYPE_LIMIT = "limit";

	/** 事件类别：提醒 */
	public static final String TIMER_TYPE_REMIND = "remind";

	/** 流程事件对象类别：流程实例 */
	public static final String TIMER_KIND_FLOW = "flow";

	/** 流程事件对象类别：活动环节 */
	public static final String TIMER_KIND_ACTIVITY = "activity";

	public static final String BPM_TIME_PARSER = "bpm_time_parser";

	@Autowired
	private TimerDao timerDao;
	@Autowired
	private ActivityDao activityDao;

	/**
	 * 清除定时器
	 * 
	 * @param bindingId
	 */
	public void clearTimer(String bindingId) {
		this.timerDao.clearTimer(bindingId);
	}

	/**
	 * 获取指定流程业务当前活动节点的定时器
	 * 
	 * @param serviceId
	 *            流程业务ID
	 * @return
	 */
	public List<Timer> getFlowServiceTimer(String serviceId) {
		List<Activity> activities = activityDao.getCurrentServiceActivity(serviceId);
		if (activities.isEmpty())
			return null;
		String bindingId = activities.get(0).getId();
		return this.timerDao.findBindingTimer(bindingId, "1", "limit", "activity");
	}

	/**
	 * 创建一个定时器任务，此任务需要指定一个目标类的全路径名称，一旦触发时间一到，定时管理服务会将此类反射实例化并根据其类别执行相应的方法。
	 * 
	 * @param action
	 *            任务执行类，用全路径名称表达
	 * @param timeLimit
	 *            时限描述，用数组表示，array[0]表示天，array[1]表示时，array[2]表示分
	 * @param timerKind
	 *            流程事件对象类别，参考
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_KIND_FLOW}
	 *            和
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_KIND_ACTIVITY}
	 * @param timerName
	 *            定时器名，限定为“超时”
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_TYPE_LIMIT}
	 *            和“提醒”
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_TYPE_REMIND}
	 * @param bindId
	 *            任务绑定的对象ID
	 * @return
	 */
	public Timer createTimer(String action, String[] timeLimit, String timerKind, String timerName, String bindId)
	        throws KhntException {
		BpmTimeParser timeParser = null;
		String parserClass = Factory.getSysPara().getProperty(BPM_TIME_PARSER);
		if (StringUtil.isEmpty(parserClass))
			timeParser = new BpmWorkdayTimeParser();
		else {
			try {
				timeParser = (BpmTimeParser) BeanClassUtil.getSpringBeanOrClassObject(parserClass);
			} catch (Exception e) {
				log.error("获取工作流定时时间解析器失败,错误信息：" + e.getLocalizedMessage());
				throw new KhntException("获取工作流定时时间解析器失败!");
			}
		}
		Timer timer = new Timer();
		timer.setAction(action);
		timer.setCurrentstate(TIMER_STATUS_ACTIVE);
		timer.setRegisteTime(new Date());
		timer.setTriggeTime(timeParser.parseDateTime(timeLimit));
		timer.setTimerKind(timerKind);
		timer.setTimerName(timerName);
		timer.setTimerKindID(bindId);
		timerDao.save(timer);
		return timer;
	}

	/**
	 * 创建一个定时器任务，此任务需要指定一个目标类的全路径名称，一旦触发时间一到，定时管理服务会将此类反射实例化并根据其类别执行相应的方法。
	 * 
	 * @param action
	 *            任务执行类，用全路径名称表达
	 * @param timeLimitDesc
	 *            时限描述，用字符','分隔的时间描述字符串，格式为"时,分,秒"
	 * @param timerKind
	 *            流程事件对象类别，参考
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_KIND_FLOW}
	 *            和
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_KIND_ACTIVITY}
	 * @param timerName
	 *            定时器名，限定为“超时”
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_TYPE_LIMIT}
	 *            和“提醒”
	 *            {@link com.khnt.bpm.core.service.TimerManager.TIMER_TYPE_REMIND}
	 * @param bindId
	 *            任务绑定的对象ID
	 * @return
	 */
	public Timer createTimer(String action, String timeLimitDesc, String timerKind, String timerName, String bindId)
	        throws KhntException {
		if (StringUtil.isEmpty(timeLimitDesc))
			throw new KhntException("定时时间描述不能为空");
		return this.createTimer(action, timeLimitDesc.split(","), timerKind, timerName, bindId);
	}

	@SuppressWarnings("unchecked")
	public List<Timer> getAllServiceDualTimer() {
		return timerDao
		        .createQuery(
		                "from Timer where triggeTime<? and currentstate=? and (timerKind=? or timerKind=?) and action is not null",
		                new Object[] { new Date(), TIMER_STATUS_ACTIVE, TIMER_KIND_FLOW, TIMER_KIND_ACTIVITY }).list();
	}

	@SuppressWarnings("unchecked")
	public List<Timer> getAllDefaultDualTimer() {
		return timerDao.createQuery(
		        "from Timer where triggeTime<? and currentstate=? and (timerKind=? or timerKind=?) and action is null",
		        new Object[] { new Date(), TIMER_STATUS_ACTIVE, TIMER_KIND_FLOW, TIMER_KIND_ACTIVITY }).list();
	}

	public void markTimerExecuted(Timer timer) {
		timer.setCurrentstate(TIMER_STATUS_DONE);
		this.timerDao.save(timer);
	}

}
