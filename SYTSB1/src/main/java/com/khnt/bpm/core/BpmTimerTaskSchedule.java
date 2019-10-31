package com.khnt.bpm.core;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.bean.Timer;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.core.service.TimerManager;
import com.khnt.bpm.core.support.DefaultLimitActionInf;
import com.khnt.bpm.core.support.DefaultLimitFlowInf;
import com.khnt.bpm.core.support.LimitActionInf;
import com.khnt.bpm.core.support.LimitFlowInf;
import com.khnt.bpm.core.support.RemindActionInf;
import com.khnt.bpm.core.support.RemindFlowInf;
import com.khnt.core.exception.KhntException;
import com.khnt.core.utils.BeanClassUtil;
import com.khnt.utils.LogUtil;

/**
 * 新的工作流定时任务管理,此类存在于通过quartz存在于spring
 * context中，单不被spring事务所涵盖，因此能够正确的捕获每个tiimer的异常
 * ，以便能够保证每个timer都被执行。并且各timer间不会因为异常而相互影响。
 */
@Component("bpmTimerTaskSchedule")
public class BpmTimerTaskSchedule implements ApplicationContextAware {

	@Autowired
	TimerManager timerManager;

	@Autowired
	ProcessManager processManager;

	@Autowired
	ActivityManager activityManager;
	
	Logger log = LoggerFactory.getLogger(getClass());

	private ApplicationContext appContext = null;

	/**
	 * 工作流定时任务执行入口方法。
	 * 
	 * @throws Exception
	 */
	public void execute() {
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
	protected void doBpmDefinedAction() throws Exception {
		List<Timer> timers = this.timerManager.getAllServiceDualTimer();
		for (Timer t : timers) {
			try {
				Object impler = BeanClassUtil.getSpringBeanOrClassObject(t.getAction());
				if (TimerManager.TIMER_KIND_FLOW.equals(t.getTimerKind())) {// 流程注册的事件
					if (TimerManager.TIMER_TYPE_LIMIT.equals(t.getTimerName())) {
						Process process = processManager.get(t.getTimerKindID());
						LimitFlowInf lf = (LimitFlowInf) impler;
						lf.limitFlow(process, t.getTriggeTime());
					} else if (TimerManager.TIMER_TYPE_REMIND.equals(t.getTimerName())) {
						RemindFlowInf rf = (RemindFlowInf) impler;
						rf.remindFlow(processManager.get(t.getTimerKindID()));
					}
				} else if (TimerManager.TIMER_KIND_ACTIVITY.equals(t.getTimerKind())) {// 活动环节注册的事件
					Activity activity = activityManager.get(t.getTimerKindID());
					if (TimerManager.TIMER_TYPE_LIMIT.equals(t.getTimerName())) {
						LimitActionInf la = (LimitActionInf) impler;
						la.limiAction(activity, t.getTriggeTime());
					} else if (TimerManager.TIMER_TYPE_REMIND.equals(t.getTimerName())) {
						RemindActionInf ra = (RemindActionInf) impler;
						ra.remindAction(activityManager.get(t.getTimerKindID()));
					}
				}
				timerManager.markTimerExecuted(t);
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
	protected void doDefaultLimitAction() throws Exception {
		List<Timer> timers = this.timerManager.getAllDefaultDualTimer();
		for (Timer t : timers) {
			try {
				if (TimerManager.TIMER_KIND_FLOW.equals(t.getTimerKind())) {// 流程注册的事件
					Process process = (Process) processManager.get(t.getTimerKindID());
					this.doDefaultLimitFlow(process, t.getTriggeTime());
				} else if (TimerManager.TIMER_KIND_ACTIVITY.equals(t.getTimerKind())) {// 活动环节注册的事件
					Activity activity = activityManager.get(t.getTimerKindID());
					this.doDefaultLimitActivity(activity, t.getTriggeTime());
				}
				timerManager.markTimerExecuted(t);
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
	 * 初始化appcontext，本地保留一个app context的引用，方便调用。
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
	}
}
