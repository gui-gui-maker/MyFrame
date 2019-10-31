package com.khnt.bpm.core.engine;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.bpm.communal.BpmOrg;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Action;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.bean.Timer;
import com.khnt.bpm.core.bean.Trask;
import com.khnt.bpm.core.dao.ProcessDao;
import com.khnt.bpm.core.service.TimerManager;
import com.khnt.bpm.core.support.StartActionInf;
import com.khnt.bpm.core.support.StartFlowInf;
import com.khnt.bpm.ext.bean.FlowDefinition;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 工作流引擎对外接口服务类
 * </p>
 */
@SuppressWarnings("unchecked")
@Transactional
public class OldFlowEngine {

	public transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ProcessDao processDao;

	@Autowired
	TimerManager timerManager;

	/***************************************************************************
	 * 根据流程分类编号得到定义流程
	 * 
	 * @param flowTypeCode
	 * @return
	 * @throws Exception
	 */
	public JSONArray selectWorkFlow(String flowTypeCode) throws Exception {
		String hql = "from FlowDefinition a where a.state=? and a.flowtype in (select b.id  from FlowType b where b.typeCode=?) ";
		Query q = processDao.createQuery(hql);
		q.setParameter(0, "已发布");
		q.setParameter(1, flowTypeCode);
		List<FlowDefinition> flowDefinitionList = q.list();
		JSONArray jsonArray = new JSONArray();
		for (FlowDefinition flowDefinition : flowDefinitionList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", flowDefinition.getId());
			jsonObject.put("flowname", flowDefinition.getFlowname());
			jsonObject.put("remark", flowDefinition.getRemark());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	/***************************************************************************
	 * 领取指定的活动事项
	 * 
	 * @param activityId
	 * @param participantId
	 * @param participantName
	 * @throws Exception
	 */
	public Activity receiveActivity(String activityId, BpmUser bpmUser) throws Exception {
		Activity activity = (Activity) processDao.get(Activity.class, activityId);
		activity.getParticipators().clear();
		Participator participator = new Participator();
		participator.setActivity(activity);
		participator.setParticipantId(bpmUser.getId());
		participator.setParticipantName(bpmUser.getName());
		participator.setPartiInType("EXE");
		participator.setParticipantType("person");
		activity.getParticipators().add(participator);
		this.processDao.save(activity);
		return activity;
	}

	/***************************************************************************
	 * 得到指定人员的所有待领取任务
	 * 
	 * @param personId
	 * @return
	 */
	public List<Activity> receiveActivitys(BpmUser bpmUser) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?";
		Query q = processDao.createQuery(hql, "300", bpmUser.getId(), "GET");
		return q.list();
	}

	/***************************************************************************
	 * 得到指定业务中指定人员的待领取任务
	 * 
	 * @param personId
	 * @param businessId
	 * @return
	 */
	public List<Activity> receiveActivitys(BpmUser bpmUser, String businessId) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?  and  a.process.businessId=? ";
		Query q = processDao.createQuery(hql, "300", bpmUser.getId(), "GET", businessId);
		return q.list();
	}

	/***************************************************************************
	 * 得到指定业务中指定活动名称与指定人员的待领取任务
	 * 
	 * @param personId
	 * @param activityName
	 * @return
	 */
	public List<Activity> receiveActivitys(String businessId, String activityName, BpmUser bpmUser) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?  and  a.process.businessId=? and name=?";
		Query q = processDao.createQuery(hql, "300", bpmUser.getId(), "GET", businessId, activityName);
		return q.list();
	}

	/***************************************************************************
	 * 得到指定人员的所有待处理任务
	 * 
	 * @param personId
	 * @return
	 */
	public List<Activity> exeActivity(String personId) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?";
		Query q = processDao.createQuery(hql, "300", personId, "EXE");
		return q.list();
	}

	/***************************************************************************
	 * 得到指定业务中指定人员的待处理任务
	 * 
	 * @param personId
	 * @param businessId
	 * @return
	 */
	public List<Activity> exeActivity(String personId, String businessId) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?  and  a.process.businessId=? ";
		Query q = processDao.createQuery(hql, "300", personId, "EXE", businessId);
		return q.list();
	}

	/***************************************************************************
	 * 得到指定业务中指定活动名称与指定人员的待处理任务
	 * 
	 * @param personId
	 * @param activityName
	 * @return
	 */
	public List<Activity> exeActivity(String businessId, String activityName, String personId) throws Exception {
		String hql = "select a from Participator p ,Activity a where p.activity.id=a.id  and a.state=? and p.participantId=? and p.partiInType=?  and  a.process.businessId=? and name=?";
		Query q = processDao.createQuery(hql, "300", personId, "EXE", businessId, activityName);
		return q.list();
	}


	/***************************************************************************
	 * 启动指定的流程活动
	 * 
	 * @param activity
	 * @param parameter
	 * @throws Exception
	 */
	protected void startActivity(Activity activity) throws Exception {
		log.debug("启动流程活动");
		activity.setState("300");
		activity.setStartTime(new Date());
		processDao.save(activity);
		log.debug("处理流程活动环节人员");
		String hql = "from Participator where activity.id=?";
		List<Participator> participators = processDao.createQuery(hql, activity.getId()).list();
		for (int i = 0; i < participators.size(); i++) {
			Participator participator = (Participator) participators.get(i);
			if ("startFlow".equals(participator.getParticipantType())) {
				log.debug("参与者为流程启动者!");
				Process process = activity.getProcess();
				participator.setParticipantType("person");
				participator.setParticipantId(process.getCreatorId());
				participator.setParticipantName(process.getCreator());
				participator.setPartiInType("EXE");
				processDao.save(participator);
				break;
			}
			else if ("databus".equals(participator.getParticipantType())) {
				log.debug("参与者为自定义，数据总线获取!");
				Process process = activity.getProcess();
				JSONObject jo = process.getDataBusJson();
				String participant = jo.getString(participator.getParticipantName());
				if (StringUtil.isEmpty(participant)) {
					throw new Exception("数据总线中不能读取流程参与者" + participator.getParticipantName());
				}
				participator.setParticipantType("person");
				participator.setParticipantId(participant);
				participator.setParticipantName(participant);
				participator.setPartiInType("EXE");
				processDao.save(participator);
				break;
			}
			else if ("person".equals(participator.getParticipantType())) {
				log.debug("参与者为人员!");
				participator.setPartiInType("EXE");
				processDao.save(participator);
			}
			else if ("role".equals(participator.getParticipantType())) {
				log.debug("参与者为角色!");
				SysParaInf inf = Factory.getSysPara();
				BpmOrg org = (BpmOrg) Class.forName(inf.getProperty("bpm.org")).newInstance();
				List<BpmUser> list = org.getBpmUserByRole(participator.getParticipantId());
				if (list.size() == 1) {
					BpmUser bpmUserInf = list.get(0);
					log.debug("角色对应的只有一人");
					participator.setParticipantType("person");
					participator.setParticipantId(bpmUserInf.getId());
					participator.setParticipantName(bpmUserInf.getName());
					participator.setPartiInType("EXE");
					processDao.save(participator);
				}
			}
			else if ("org".equals(participator.getParticipantType())) {
				log.debug("参与者为机构!");
				SysParaInf inf = Factory.getSysPara();
				BpmOrg org = (BpmOrg) Class.forName(inf.getProperty("bpm.org")).newInstance();
				List<BpmUser> list = org.getBpmUserByOrg(participator.getParticipantId());
				if (list.size() == 1) {
					BpmUser bpmUserInf = list.get(0);
					log.debug("机构对应的只有一人");
					participator.setParticipantType("person");
					participator.setParticipantId(bpmUserInf.getId());
					participator.setParticipantName(bpmUserInf.getName());
					participator.setPartiInType("EXE");
					processDao.save(participator);
				}
			}
		}
		log.debug("开始执行流程活动事件");
		hql = "from Action where bindingId=? and actionTime in ('启动','提醒','超时')";
		List<Action> actions = processDao.createQuery(hql, activity.getId()).list();
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			if ("启动".equals(action.getActionTime())) {
				log.debug("开始执行流程活动启动事件");
				StartActionInf sa = (StartActionInf) Class.forName(action.getActionDo().trim()).newInstance();
				sa.startAction(activity);
			}
			else if ("提醒".equals(action.getActionTime())) {
				if (activity.getIsRemind()) {
					log.debug("注册流程的提醒事件");
					String tp[] = new String[3];
					tp = activity.getRemindDesc().split(",", -1);
					if ("design".equals(tp[0])) {// 指定时间
						log.debug("从设置超时中获取提醒设置");
						long time = (new Date()).getTime();
						time += Integer.valueOf(tp[1]) * 24 * 60 * 60 * 1000; // 天
						time += Integer.valueOf(tp[2]) * 60 * 60 * 1000; // 小时
						time += Integer.valueOf(tp[2]) * 60 * 1000;// 分
						Timer timer = new Timer();
						timer.setAction(action.getActionDo().trim());
						timer.setCurrentstate("1");
						timer.setRegisteTime(new Date());
						timer.setTriggeTime(new Date(time));
						timer.setTimerKind("activity");
						timer.setTimerKindID(activity.getId());
						timer.setTimerName("提醒");
						processDao.save(timer);
					}
					else if ("related".equals(tp[0])) {// 从数据总线中获取
						log.debug("从数据总线中获取提醒设置");
					}
				}
			}
			else if ("超时".equals(action.getActionTime())) {
				if (activity.getIsLimit()) {
					log.debug("注册流程的超时事件");
					String tp[] = new String[3];
					tp = activity.getLimitDesc().split(",", -1);
					if ("design".equals(tp[0])) {// 指定时间
						log.debug("从设置超时中获取超时设置");
						long time = (new Date()).getTime();
						time += Integer.valueOf(tp[1]) * 24 * 60 * 60 * 1000; // 天
						time += Integer.valueOf(tp[2]) * 60 * 60 * 1000; // 小时
						time += Integer.valueOf(tp[2]) * 60 * 1000;// 分
						Timer timer = new Timer();
						timer.setAction(action.getActionDo().trim());
						timer.setCurrentstate("1");
						timer.setRegisteTime(new Date());
						timer.setTriggeTime(new Date(time));
						timer.setTimerKind("activity");
						timer.setTimerKindID(activity.getId());
						timer.setTimerName("超时");
						processDao.save(timer);
					}
					else if ("related".equals(tp[0])) {// 从数据总线中获取
						log.debug("从数据总线中获取超时设置");
					}
				}
			}
		}
		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction("启动【" + activity.getName() + "】活动事项");
		trask.setExeDate(new Date());
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());
		processDao.save(trask);
	}

	/***************************************************************************
	 * 启动流程
	 * 
	 * @param process
	 * @param parameter
	 * @throws Exception
	 */
	protected void startFlow(Process process) throws Exception {
		log.debug("启动流程");
		log.debug("开始执行流程事件");
		String hql = "from Action where bindingId=? and actionTime in ('创建','启动','提醒','超时')";
		List<Action> actions = processDao.createQuery(hql, process.getId()).list();
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			if ("启动".equals(action.getActionTime())) {
				log.debug("开始执行流程启动事件");
				StartFlowInf sf = (StartFlowInf) Class.forName(action.getActionDo().trim()).newInstance();
				sf.startFlow(process);
			}
			else if ("提醒".equals(action.getActionTime())) {
				if (process.getIsRemind()) {
					log.debug("注册流程的提醒事件");
					String tp[] = new String[3];
					tp = process.getRemindDesc().split(",", -1);
					if ("design".equals(tp[0])) {// 指定时间
						log.debug("从设置超时中获取提醒设置");
						long time = (new Date()).getTime();
						time += Integer.valueOf(tp[1]) * 24 * 60 * 60 * 1000; // 天
						time += Integer.valueOf(tp[2]) * 60 * 60 * 1000; // 小时
						time += Integer.valueOf(tp[2]) * 60 * 1000;// 分
						Timer timer = new Timer();
						timer.setAction(action.getActionDo().trim());
						timer.setCurrentstate("1");
						timer.setRegisteTime(new Date());
						timer.setTriggeTime(new Date(time));
						timer.setTimerKind("flow");
						timer.setTimerKindID(process.getId());
						timer.setTimerName("提醒");
						processDao.save(timer);
					}
					else if ("related".equals(tp[0])) {// 从数据总线中获取
						log.debug("从数据总线中获取提醒设置");
					}
				}
			}
			else if ("超时".equals(action.getActionTime())) {
				if (process.getIsLimit()) {
					log.debug("注册流程的超时事件");
					String tp[] = new String[3];
					tp = process.getLimitDesc().split(",", -1);
					if ("design".equals(tp[0])) {// 指定时间
						log.debug("从设置超时中获取超时设置");
						long time = (new Date()).getTime();
						time += Integer.valueOf(tp[1]) * 24 * 60 * 60 * 1000; // 天
						time += Integer.valueOf(tp[2]) * 60 * 60 * 1000; // 小时
						time += Integer.valueOf(tp[2]) * 60 * 1000;// 分
						Timer timer = new Timer();
						timer.setAction(action.getActionDo().trim());
						timer.setCurrentstate("1");
						timer.setRegisteTime(new Date());
						timer.setTriggeTime(new Date(time));
						timer.setTimerKind("flow");
						timer.setTimerKindID(process.getId());
						timer.setTimerName("超时");
						processDao.save(timer);
					}
					else if ("related".equals(tp[0])) {// 从数据总线中获取
						log.debug("从数据总线中获取超时设置");
					}
				}
			}
		}
	}
}
