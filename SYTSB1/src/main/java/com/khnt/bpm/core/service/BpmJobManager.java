package com.khnt.bpm.core.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.khnt.base.Factory;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.bean.Timer;
import com.khnt.bpm.core.dao.TimerDao;
import com.khnt.bpm.core.support.BpmTimeParser;
import com.khnt.bpm.core.support.BpmWorkdayTimeParser;
import com.khnt.bpm.core.support.DefaultLimitActionInf;
import com.khnt.bpm.core.support.DefaultLimitFlowInf;
import com.khnt.bpm.core.support.LimitActionInf;
import com.khnt.bpm.core.support.LimitFlowInf;
import com.khnt.bpm.core.support.RemindActionInf;
import com.khnt.bpm.core.support.RemindFlowInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;

/**
 * 工作流定时任务管理
 * 
 * @deprecated 
 *             已废弃的类，因为异步任务无法正确处理每个流程定时器的事务异常，从而取消此实现，改为bpm.core.BpmTimerTaskSchedule重新实现
 * 
 */
@Service("bpmJobManager")
@Deprecated
public class BpmJobManager extends EntityManageImpl<Timer, TimerDao> {

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
	TimerDao timerDao;

	private ApplicationContext appContext = null;

	/**
	 * 工作流定时任务执行入口方法。
	 * 
	 * @throws Exception
	 */
	@Transactional(noRollbackFor = { Exception.class, KhntException.class })
	public void executeJobs() {
		log.debug("#############工作流定时器开始开始执行#############");
		try {
			log.debug("#####执行默认的定时事件！#####");
			this.doDefaultLimitAction();

			log.debug("#####执行流程自定义的定时事件！#####");
			this.doBpmDefinedAction();
		} catch (Exception e) {
			log.error(e.getMessage());
			LogUtil.logError(log, e);
		}
		log.debug("#############工作流定时器任务执行完成#############");
	}

	/**
	 * 执行已经实例化的流程和环节定义定时任务
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected void doBpmDefinedAction() throws Exception {
		List<Timer> timers = timerDao
		        .createQuery(
		                "from Timer where triggeTime<? and currentstate=? and (timerKind=? or timerKind=?) and action is not null",
		                new Object[] { new Date(), TIMER_STATUS_ACTIVE, TIMER_KIND_FLOW, TIMER_KIND_ACTIVITY }).list();
		for (Timer t : timers) {
			try {
				if (TIMER_KIND_FLOW.equals(t.getTimerKind())) {// 流程注册的事件
					if (TIMER_TYPE_LIMIT.equals(t.getTimerName())) {
						Process process = (Process) timerDao.get(Process.class, t.getTimerKindID());
						LimitFlowInf lf = (LimitFlowInf) parseClassOrBean(t.getAction());
						lf.limitFlow(process, t.getTriggeTime());
					} else if (TIMER_TYPE_REMIND.equals(t.getTimerName())) {
						RemindFlowInf rf = (RemindFlowInf) parseClassOrBean(t.getAction());
						rf.remindFlow((Process) timerDao.get(Process.class, t.getTimerKindID()));
					}
				} else if (TIMER_KIND_ACTIVITY.equals(t.getTimerKind())) {// 活动环节注册的事件
					Activity activity = (Activity) timerDao.get(Activity.class, t.getTimerKindID());
					if (TIMER_TYPE_LIMIT.equals(t.getTimerName())) {
						LimitActionInf la = (LimitActionInf) parseClassOrBean(t.getAction());
						la.limiAction(activity, t.getTriggeTime());
					} else if (TIMER_TYPE_REMIND.equals(t.getTimerName())) {
						RemindActionInf ra = (RemindActionInf) parseClassOrBean(t.getAction());
						ra.remindAction((Activity) timerDao.get(Activity.class, t.getTimerKindID()));
					}
				}
				t.setCurrentstate(TIMER_STATUS_DONE);
			} catch (Exception e) {
				log.error("流程预设定时任务【任务名：" + t.getTimerName() + "，执行对象：" + t.getAction() + "】执行失败！" + e.getMessage());
				LogUtil.logError(log, e);
				continue;
			}
		}
	}

	/**
	 * 执行默认的业务流程未配置的超时事件
	 */
	@SuppressWarnings("unchecked")
	protected void doDefaultLimitAction() throws Exception {
		List<Timer> timers = timerDao.createQuery(
		        "from Timer where triggeTime<? and currentstate=? and (timerKind=? or timerKind=?) and action is null",
		        new Object[] { new Date(), TIMER_STATUS_ACTIVE, TIMER_KIND_FLOW, TIMER_KIND_ACTIVITY }).list();
		for (Timer t : timers) {
			try {
				if (TIMER_KIND_FLOW.equals(t.getTimerKind())) {// 流程注册的事件
					Process process = (Process) timerDao.get(Process.class, t.getTimerKindID());
					this.doDefaultLimitFlow(process, t.getTriggeTime());
				} else if (TIMER_KIND_ACTIVITY.equals(t.getTimerKind())) {// 活动环节注册的事件
					Activity activity = (Activity) timerDao.get(Activity.class, t.getTimerKindID());
					this.doDefaultLimitActivity(activity, t.getTriggeTime());
				}
				t.setCurrentstate(TIMER_STATUS_DONE);
			} catch (Exception e) {
				log.error("流程默认定时任务【任务ID：" + t.getId() + "】执行失败！" + e.getMessage());
				LogUtil.logError(log, e);
				continue;
			}
		}
	}

	/**
	 * 执行已被实例化的流程环节超时处理任务。在流程设置中，只需设置超时时间。
	 * 
	 * @param activity
	 *            超时环节
	 */
	protected void doDefaultLimitActivity(Activity activity, Date triggerTime) throws KhntException {
		if (appContext == null)
			initAppContext();
		Map<String, DefaultLimitActionInf> listenerMaps = appContext.getBeansOfType(DefaultLimitActionInf.class);
		if (!listenerMaps.isEmpty()) {
			Collection<DefaultLimitActionInf> listeners = listenerMaps.values();
			for (DefaultLimitActionInf listener : listeners) {
				try {
					listener.limiAction(activity, triggerTime);
				} catch (Exception e) {
					log.error("工作流默认环节超时处理【环节ID：" + activity.getActivityId() + "" + "，名称：" + activity.getName()
					        + "，执行类：" + listener.getClass().getName() + "】执行失败！" + e.getMessage());
				}
			}
		}
	}

	/**
	 * 执行默认流程实例超时处理
	 * 
	 * @param process
	 *            超时流程实例
	 */
	protected void doDefaultLimitFlow(Process process, Date triggerTime) throws KhntException {
		if (appContext == null)
			initAppContext();
		Map<String, DefaultLimitFlowInf> listenerMaps = appContext.getBeansOfType(DefaultLimitFlowInf.class);
		if (!listenerMaps.isEmpty()) {
			Collection<DefaultLimitFlowInf> listeners = listenerMaps.values();
			for (DefaultLimitFlowInf listener : listeners) {
				try {
					listener.limitFlow(process, triggerTime);
				} catch (Exception e) {
					log.error("工作流默认实例超时处理【流程实例ID：" + process.getId() + "" + "，名称：" + process.getFlowName() + "，执行类："
					        + listener.getClass().getName() + "】执行失败！" + e.getMessage());
				}
			}
		}
	}

	/**
	 * 设置获取spring Bean上下文环境
	 */
	protected void initAppContext() {
		appContext = ContextLoader.getCurrentWebApplicationContext();
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
				timeParser = (BpmTimeParser) parseClassOrBean(parserClass);
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

	/**
	 * 用给的那个的类路径或spring bean名称获取该类的实例对象或者spring bean
	 * 
	 * @param classOrBeanName
	 * @return
	 * @throws KhntException
	 */
	protected Object parseClassOrBean(String classOrBeanName) throws KhntException {
		Object object;
		try {
			object = Class.forName(classOrBeanName).newInstance();
		} catch (Exception e) {
			if (appContext == null)
				initAppContext();
			object = appContext.getBean(classOrBeanName);
			if (object == null)
				throw new KhntException("给定的事件执行者类路径或bean name无效！");
		}

		return object;
	}
}
