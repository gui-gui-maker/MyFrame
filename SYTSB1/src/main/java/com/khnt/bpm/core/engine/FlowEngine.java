package com.khnt.bpm.core.engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.core.bean.Action;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.bean.Transition;
import com.khnt.bpm.core.bean.Trask;
import com.khnt.bpm.core.dao.ActivityDao;
import com.khnt.bpm.core.dao.ProcessDao;
import com.khnt.bpm.core.dao.TimerDao;
import com.khnt.bpm.core.service.TimerManager;
import com.khnt.bpm.core.support.FinishActionInf;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.core.support.FlowServiceResult;
import com.khnt.bpm.core.support.StartActionInf;
import com.khnt.bpm.core.support.StartFlowInf;
import com.khnt.bpm.ext.bean.FlowDefinition;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.bean.Position;
import com.khnt.rbac.manager.IPositionManager;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 工作流引擎对外接口服务类，重新定义的
 */
@Service("flowEngine")
@SuppressWarnings("unchecked")
@Transactional
public class FlowEngine extends OldFlowEngine {
	/**
	 * 会签设置：不会签
	 */
	public static final String SIGNATURE_SINGLE = "0";
	/**
	 * 会签设置：顺签
	 */
	public static final String SIGNATURE_COUNTER = "1";
	/**
	 * 会签设置：选签
	 */
	public static final String SIGNATURE_CHOOSE = "2";

	/**
	 * 数据总线参与者KEY
	 */
	public static final String DATA_BUS_PARTICIPANT_KEY_DEFAULT = "paticipator";

	private String dataBusParticipantKey = DATA_BUS_PARTICIPANT_KEY_DEFAULT;

	/**
	 * 数据总线中，角色参与者范围限定机构KEY
	 */
	public static final String DATA_BUS_ROLE_RANGE_KEY_DEFAULT = "roleRange";

	private String dataBusRoleRangeKey = DATA_BUS_ROLE_RANGE_KEY_DEFAULT;

	public static final String DATA_BUS_NEXT_ACTIVITY_ID = "nextActivityId";

	@Autowired
	private ProcessDao processDao;

	@Autowired
	ActivityDao activityDao;

	@Autowired
	TimerDao timerDao;

	@Autowired
	TimerManager timerManager;

	@Autowired
	IPositionManager positionManager;

	public String getDataBusParticipantKey() {
		return dataBusParticipantKey;
	}

	public void setDataBusParticipantKey(String dataBusParticipantKey) {
		this.dataBusParticipantKey = dataBusParticipantKey;
	}

	public String getDataBusRoleRangeKey() {
		return dataBusRoleRangeKey;
	}

	public void setDataBusRoleRangeKey(String dataBusRoleRangeKey) {
		this.dataBusRoleRangeKey = dataBusRoleRangeKey;
	}

	/**
	 * 创建流程实例/启动流程
	 * 
	 * @param businessid
	 *            业务ID
	 * @param creator
	 *            创建人
	 * @param creatorId
	 *            创建人姓名
	 * @param remark
	 *            流程描述
	 * @param parameter
	 *            参数(放入数据区域中)
	 * @param overClass
	 *            流程终止回调类
	 * @param flowDefinitionId
	 *            流程定义ID
	 * @return 返回流程环节处理环节集合（即流程待处理节点集合）
	 * @throws Exception
	 *             流程实例失败
	 */
	public Activity createFlowProcess(String businessId, BpmUser bpmUser, String remark, String dataBus,
			String overClass, String flowDefinitionId) throws Exception {
		// 首先检查该业务是否已经启动流程了
		int existSize = this.processDao.findBy("businessId", businessId).size();
		if (existSize >= 1)
			throw new KhntException("该业务已经启动了一个流程实例，相同的业务不能重复启动流程！");
		return createProcess(null, businessId, bpmUser, remark, dataBus, overClass, flowDefinitionId);
	}

	protected Activity createProcess(Activity parentActivity, String businessId, BpmUser bpmUser, String remark,
			String dataBus, String overClass, String flowDefinitionId) throws Exception {

		log.debug("查找流程定义类");
		FlowDefinition flowDefinition = (FlowDefinition) processDao.get(FlowDefinition.class, flowDefinitionId);
		InputStream inputStream = new ByteArrayInputStream(flowDefinition.getFlowxml().getBytes("UTF-8"));
		SAXReader saxReader = new SAXReader();
		Document xml = saxReader.read(inputStream);
		// 解析流程定义XML，创建流程实例，获取第一个活动环节
		Activity startActivity = this.parseXml(xml);
		Process processImpl = startActivity.getProcess();
		// 继续设置流程实例的参数
		processImpl.setDefinitionId(flowDefinition.getId());
		processImpl.setOverClass(overClass);
		processImpl.setCreator(bpmUser.getName());
		processImpl.setCreatorId(bpmUser.getId());
		setProcessDataBus(processImpl, dataBus);// 设置总线参数
		processImpl.setRemark(remark);
		processImpl.setFlowTypeCode(flowDefinition.getFlowtype());
		processImpl.setFlowxml(flowDefinition.getFlowxml());

		// 这是一个子流程，需要设置父流程
		if (parentActivity != null) {
			processImpl.setParentActivity(parentActivity);
			processImpl.setBusinessId(parentActivity.getProcess().getBusinessId());
		} else if (StringUtil.isNotEmpty(businessId)) {
			processImpl.setBusinessId(businessId);
		}

		log.debug("流程定义XML文件解析完成");
		// 启动流程
		this.startFlow(processImpl);
		// 启动流程开始环节
		this.startActivity(startActivity, bpmUser);
		// 把开始节点作为虚节点,启动时第一个活动节点为起始节点
		List<Activity> activities = this.submitActivity(startActivity, bpmUser, null).getResultActivity();
		log.debug("流程实例化成功");
		return activities.get(0);
	}

	/**
	 * 启动子流程
	 * 
	 * @param activity
	 * @param bpmUser
	 */
	protected List<Activity> startSubProcess(Activity activity, BpmUser user, ActivityParticipatorBean participatorBean)
			throws Exception {
		List<Activity> resultActivities = new ArrayList<Activity>();
		Set<Process> subProcess = new HashSet<Process>();
		for (Participator p : participatorBean.participators) {
			// 子流程的原理是当前处理人启动本环节相应的流程
			Trask trask = new Trask();
			trask.setAction("启动【" + activity.getName() + "】子流程，首要处理人【" + participatorBean.names.toString() + "】");
			trask.setExeDate(new Date());
			trask.setPerson(user.getName());
			trask.setPersonId(user.getId());
			trask.setActivitId(activity.getId());
			trask.setProcessId(activity.getProcess().getId());
			this.processDao.save(trask);

			BpmUser subflowCreater = new BpmUserImpl(p.getParticipantId(), p.getParticipantName(), null, null, null);
			Activity sfa = this.createProcess(activity, null, subflowCreater, "【子流程】" + activity.getName(),
					activity.getProcess().getDataBus(), null, activity.getSubFlowId());
			subProcess.add(sfa.getProcess());

			// 清除子流程预定义的第一环节参与者
			this.processDao.createQuery("delete Participator where activity.id=?", sfa.getId()).executeUpdate();

			// 将每一个子流程的第一个任务环节参与人设置为其父流程环节的参与人,每一个参与人启动一个子流程实例
			// 重新设置第一环节的参与者为父流程环节的参与者
			Participator sfap = new Participator(sfa, p.getParticipantType(), p.getParticipantId(),
					p.getParticipantName(), p.getParticipantRange(), p.getParticipantRangeType(), p.getPartiInType(),
					p.getChooseRolePerson(), p.getDefined());
			sfa.getParticipators().clear();
			sfa.getParticipators().add(sfap);
			this.processDao.save(sfap);
			resultActivities.add(sfa);
			// p.setExecuted(true);
			// this.processDao.save(p);
		}
		activity.setSubProcess(subProcess);
		return resultActivities;
	}

	/**
	 * 提交流程环节
	 * 
	 * @param activityId
	 * @param bpmUser
	 * @param dataBus
	 * @return
	 * @throws Exception
	 */
	public FlowServiceResult submitActivity(String activityId, BpmUser bpmUser, String dataBus) throws Exception {
		Activity activity = (Activity) processDao.get(Activity.class, activityId);

		// 返回操作结果，包含了目标环节列表，如果流程结束，将会为空
		return this.submitActivity(activity, bpmUser, dataBus);
	}

	public boolean checkActivityCanFinish(Activity activity, BpmUser bpmUser) {
		if (!"300".equals(activity.getState()))
			return false;

		String signature = activity.getSignature();

		// 在环节处理活动状态下，只需要检查会签时是否全部参与者已经处理完成
		// 未处理完成不能提交到下一环节
		if (SIGNATURE_COUNTER.equals(signature)) {
			Set<Participator> participators = activity.getParticipators();
			for (Participator participator : participators) {
				if (!participator.getDefined() && !participator.getExecuted()
						&& !bpmUser.getId().equals(participator.getParticipantId())) {
					// 尚有未处理的参与者，不能提交
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 流程提交
	 * 
	 * @param activityId
	 *            当前活动ID
	 * @param submitId
	 *            提交人ID
	 * @param submitName
	 *            提交人姓名
	 * @param parameter
	 *            提交参数
	 * @throws Exception
	 */
	public FlowServiceResult submitActivity(Activity activity, BpmUser bpmUser, String dataBus) throws Exception {
		if ("500".equals(activity.getState()))
			throw new KhntException("该环节已经处理过了，不能重复提交");

		Process processImpl = activity.getProcess();
		checkProcessState(processImpl);

		// 将传入的数据总线参数放入实例
		setProcessDataBus(processImpl, dataBus);
		List<Activity> targetActivities = new ArrayList<Activity>();
		int resultType = FlowServiceResult.RESULT_TYPE_SUBMIT_ACTIVITY;
		if ("join".equals(activity.getType())) {
			log.debug("当前环节为聚合环节");
			// 查看当前环节的聚合机制，好设定下一步走向
			//
		}
		// 子流程的启动与主流程环节同步启动
		/*
		 * else if ("subFlow".equals(activity.getType())) { log.debug("当前环节为子流程环节");
		 * Activity fsa = startSubProcess(activity, bpmUser); resultType =
		 * FlowServiceResult.RESULT_TYPE_START_SUB_PROCESS; targetActivities.add(fsa); }
		 */
		else if ("foreach".equals(activity.getType())) {
			log.debug("当前环节为分流环节");
			this.finishActivity(activity, bpmUser);
			String hql = "from Transition a where a.fromId=:fromId and a.process.id=:process and type=:type";
			Query q = processDao.createQuery(hql);
			q.setParameter("fromId", activity.getActivityId());
			q.setParameter("processImpl", activity.getProcess().getId());
			q.setParameter("type", "default");// 提交
			List<Transition> list = q.list();
			for (Transition transition : list) {
				if (transition != null) {
					hql = "from Activity a where a.activityId=? and a.process.id=?";
					q = processDao.createQuery(hql, transition.getToid(), transition.getProcess().getId());
					Activity tpactivity = (Activity) q.uniqueResult();
					this.startActivity(tpactivity, bpmUser);
					targetActivities.add(tpactivity);
					if ("end".equals(tpactivity.getType())) {// F结束活动
						this.finishActivity(tpactivity, bpmUser);
					}
				}
			}
		} else {
			log.debug("当前环节为任务环节");

			// 根据环节定义的会签设置处理提交
			// 1、如果设置顺签，则执行会签提交
			// 2、如果禁止会签或者选签，则正常提交环节
			String signature = activity.getSignature();

			// 标志位：本环节是否完成
			boolean isFinishActivity = true;
			// 顺签
			if (SIGNATURE_COUNTER.equals(signature)) {
				if (!this.counterSignSubmitActivity(activity, bpmUser)) {
					isFinishActivity = false;
					targetActivities.add(activity);
					resultType = FlowServiceResult.RESULT_TYPE_SUBMIT_COUNTER_SIGN;
					// 写入流程跟踪
					Trask trask = new Trask();
					trask.setAction("完成【" + activity.getName() + "】");
					trask.setExeDate(new Date());
					trask.setPerson(bpmUser.getName());
					trask.setPersonId(bpmUser.getId());
					trask.setActivitId(activity.getId());
					trask.setProcessId(activity.getProcess().getId());
					this.processDao.save(trask);
				}
			}

			// 本环节完成，启动后续 环节
			if (isFinishActivity) {
				this.finishActivity(activity, bpmUser);
				// 路由选择,获取下个活动环节
				Activity nextActivity = doTransition(activity, true);
				// 如果目标环节是流程结束，则直接完成工作流和目标活动环节
				if ("end".equals(nextActivity.getType())) {
					this.finishActivity(nextActivity, bpmUser);
					Activity parentNextActvity = this.finishFlow(nextActivity.getProcess(), bpmUser,
							FinishFlowInf.FINISH_TYPE_COMMON);

					// 如果是子流程结束，并且启动了父流程的下一步骤，则响应类别设置为环节提交
					if (parentNextActvity == null) {
						resultType = FlowServiceResult.RESULT_TYPE_FINISH_PROCESS;
						targetActivities.add(nextActivity);
					} else {
						resultType = FlowServiceResult.RESULT_TYPE_SUBMIT_ACTIVITY;
						targetActivities.add(parentNextActvity);
					}
				} else {
					// 启动目标环节
					targetActivities.addAll(this.startActivity(nextActivity, bpmUser));
				}
			}
		}

		// 清除数据总线中的临时数据
		JSONObject jo = processImpl.getDataBusJson();
		jo.remove(DATA_BUS_NEXT_ACTIVITY_ID);
		processImpl.setDataBus(jo.toString());

		// if(true)throw new KhntException("test");
		// 准备返回给调用者本次操作的结果信息
		FlowServiceResult flowServiceResult = new FlowServiceResult(resultType, activity.getProcess().getId(),
				activity.getProcess().getFlowName(), null);
		flowServiceResult.setResultActivity(targetActivities);
		// 返回目标环节，如果流程结束，将会为空
		return flowServiceResult;
	}

	/**
	 * 顺签提交环节
	 * 
	 * @param activity
	 * @param user
	 * @return 是否完成本环节
	 */
	protected boolean counterSignSubmitActivity(Activity activity, BpmUser user) {
		Set<Participator> participators = activity.getParticipators();
		// 计数器，用于记录该环节有多少人尚未处理
		int exccutedCount = 0;
		if (participators == null)
			return true;
		for (Participator participator : participators) {
			if (participator.getDefined())
				continue;
			if (participator.getParticipantId() != null && participator.getParticipantId().equals(user.getId()))
				participator.setExecuted(true);
			else if (!participator.getExecuted()) {
				exccutedCount++;// 计数器加1
			}
		}
		if (exccutedCount == participators.size()) {
			throw new KhntException("错误的操作人，该人员不是合法的参与者！");
		}
		if (exccutedCount == 0)
			return true;
		else {
			// 会签提交，环节未完成，写入当前用户处理记录
			Trask trask = new Trask();
			trask.setAction("完成【" + activity.getName() + "】");
			trask.setExeDate(new Date());
			trask.setPerson(user.getName());
			trask.setPersonId(user.getId());
			trask.setActivitId(activity.getId());
			trask.setProcessId(activity.getProcess().getId());
			return false;
		}
	}

	/**
	 * 结束流程，以指定的类型结束流程
	 * 
	 * @param processId
	 * @param bpmUser
	 * @param finishType
	 * @throws Exception
	 */
	public void finishFlow(String processId, BpmUser bpmUser, int finishType) throws Exception {
		Process process = this.processDao.get(processId);
		this.finishFlow(process, bpmUser, finishType);
	}

	/**
	 * 路由选择，这里是对每个路由设置的条件表达式进行计算而选择出合适的路由路径。
	 * 
	 * 当路由路径只有一个时，无需选择，直接返回。
	 * 
	 * 路由条件使用的表达式必须符合javascript语法规范。
	 * 
	 * @param activity
	 *            当前环节
	 * @param forward
	 *            方向
	 * @return 经各种条件筛选后的唯一可用目标环节
	 */
	private Activity doTransition(Activity activity, Boolean forward) {
		// 2018-01-10
		// 首先检查当前环节是否是内部流转,并且数据总线制定下一环节为当前环节
		JSONObject dataBus = activity.getProcess().getDataBusJson();
		if ("1".equals(activity.getInnerFlow()) && dataBus.has(DATA_BUS_NEXT_ACTIVITY_ID)
				&& activity.getActivityId().equals(dataBus.get(DATA_BUS_NEXT_ACTIVITY_ID))) {
			return activity;
		}

		List<Transition> enableTransitions = this.findNextEnableTransitions(activity, forward);

		if (enableTransitions.isEmpty()) {
			log.error("找不到环节【" + activity.getName() + "】可" + (forward ? "提交" : "退回")
					+ "的目标环节，可能的原因是未设置多个备选环节的路由条件表达式，或者提供的参数不正确");
			throw new KhntException(forward ? "找不到可提交的目标环节" : "该节点不允许退回");
		}
		Transition resultTransition = enableTransitions.get(0);
		boolean sameOrder = false;// 排序相同标志
		if (enableTransitions.size() >= 1) {
			for (int i = 1; i < enableTransitions.size(); i++) {
				Transition transition = enableTransitions.get(i);
				if (StringUtil.isNotEmpty(transition.getOrderby())) {
					if (resultTransition.getOrderby() == null) {
						resultTransition = transition;
					} else {
						int c = transition.getOrderby().compareTo(resultTransition.getOrderby());
						if (c < 0) {
							resultTransition = transition;
							sameOrder = false;
						} else if (c == 0)
							sameOrder = true;
					}
				}
			}
		}
		if (sameOrder) {
			log.error(
					"找到环节【" + activity.getName() + "】有多个符合条件的可" + (forward ? "提交" : "退回") + "目标环节，现有条件不足以确定一个唯一可用目标环节");
			throw new KhntException(forward ? "提交失败" : "退回失败");
		}

		return this.chooseNextActivity(resultTransition.getToid(), activity.getProcess().getActivitys());
	}

	/**
	 * 查找可用的目标环节集合
	 * 
	 * @param activity
	 * @param forward
	 * @return
	 */
	private List<Transition> findNextEnableTransitions(Activity activity, Boolean forward) {
		List<Transition> resultTransitions = new ArrayList<Transition>();
		Process process = activity.getProcess();
		JSONObject dataBus = process.getDataBusJson();

		// 首先检查数据总线中是否存在目标环节ID值,如果存在需要立即从数据总线清除该值
		String nextActId = null;
		if (dataBus.has(DATA_BUS_NEXT_ACTIVITY_ID)) {
			nextActId = dataBus.getString(DATA_BUS_NEXT_ACTIVITY_ID);
			// 此参数仅在本次提交过程有效
			// dataBus.remove(DATA_BUS_NEXT_ACTIVITY_ID);
			// process.setDataBus(dataBus.toString());
		}

		// 根据来源和实例id并按照优先顺序查询路由
		String hql = "from Transition a where a.fromId=:fromId and a.process.id=:process and type=:type order by a.orderby";
		Query q = processDao.createQuery(hql);
		q.setParameter("fromId", activity.getActivityId());
		q.setParameter("process", activity.getProcess().getId());
		q.setParameter("type", forward ? "default" : "back");// 路由类型：提交/回退
		List<Transition> transitions = q.list();
		if (transitions.isEmpty())
			return resultTransitions;

		// 如果路由路径唯一
		if (transitions.size() == 1) {
			resultTransitions.add(transitions.get(0));
			return resultTransitions;
		}

		// 路由路径不唯一,需要计算路由条件表达式
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("js");
		Iterator<String> dataKeys = dataBus.keys();
		log.debug("路由表达式执行环境数据总线：" + dataBus.toString());
		while (dataKeys.hasNext()) {
			String key = dataKeys.next();
			engine.put(key, dataBus.get(key));
		}

		// 空条件节点集合，如果所有路由条件都为空，则表示所有路由均可用
		List<Transition> emptyConditionTransitions = new ArrayList<Transition>();
		boolean isUseEmptyCondition = true;

		for (Transition transition : transitions) {
			String condition = transition.getCondition();
			if (nextActId != null) {
				if (nextActId.equals(transition.getToid())) {
					resultTransitions.clear();
					resultTransitions.add(transition);
					isUseEmptyCondition = false;
					break;
				}
			} else {
				// 如果路由条件为空，当其他路由条件均不满足且该路由的优先级最高，此为默认路径
				// 优先级的选择是根据 orderby属性决定的
				if (StringUtil.isEmpty(condition)) {
					// 不允许使用空条件路由，说明有其他路由折值了条件
					if (!isUseEmptyCondition)
						continue;
					emptyConditionTransitions.add(transition);
				} else {
					isUseEmptyCondition = false;

					// 如果路由脚本表达式异常，抛出明确的提示.
					Object result;
					try {
						result = engine.eval(condition);
					} catch (ScriptException e) {
						log.error("执行路由【" + transition.getName() + "】的条件表达式【" + condition + "】失败！");
						LogUtil.logError(log, e);
						throw new KhntException("系统错误，无法找到目标环节");
					}
					// 当路由条件满足，加入目标环节到结果集
					if (result instanceof Boolean && (Boolean) result) {
						resultTransitions.add(transition);
					}
				}
			}
		}

		if (isUseEmptyCondition)
			return emptyConditionTransitions;
		else
			return resultTransitions;
	}

	private Activity chooseNextActivity(String toId, Collection<Activity> activities) {
		Activity result = null;
		for (Activity a : activities) {
			if (a.getActivityId().equals(toId)) {
				result = a;
				break;
			}
		}
		return result;
	}

	/**
	 * 流程退回
	 * 
	 * @param activityId
	 *            当前活动ID
	 * @param submitId
	 *            退回人ID
	 * @param submitName
	 *            退回人姓名
	 * @param parameter
	 *            退回参数
	 * @throws Exception
	 */
	public Activity returnedActivity(String activityId, BpmUser bpmUser, String dataBus) throws Exception {
		Activity activity = (Activity) processDao.get(Activity.class, activityId);
		log.debug(">> 准备将工作流处理状态从活动环节【" + activity.getName() + "】退回到前置环节");
		Process processImpl = activity.getProcess();
		checkProcessState(processImpl);

		this.finishActivity(activity, bpmUser);
		setProcessDataBus(processImpl, dataBus);
		Activity backActivity = this.doTransition(activity, false);
		log.debug("要退回的目标环节【" + backActivity.getName() + "】");

		// 检查目标环节是否已经完成，如果尚未处理，则是意外退回至一个尚未处理过的环节
		if (!"500".equals(backActivity.getState())) {
			log.error("尝试从环节【" + activity.getName() + ";ID=" + activityId + "】退回到环节【" + backActivity.getName() + ";ID="
					+ backActivity.getId() + "】失败，因为退回的目标环节尚未被执行，流程引擎不允许退回到未执行过的环节。");
			throw new KhntException("要退回的目标环节尚未被执行，流程引擎不允许退回到未执行过的环节。");
		}
		backActivity.setState("300");
		backActivity.setStartTime(new Date());
		// 将历史实际参与者处理状态设为false
		this.processDao.createQuery("update Participator set executed=false where activity.id=? and defined=false",
				backActivity.getId()).executeUpdate();

		JSONObject processDatabus = processImpl.getDataBusJson();

		// 注册默认执行事件
		if (null != backActivity.getIsLimit() && backActivity.getIsLimit()
				&& StringUtil.isNotEmpty(backActivity.getLimitDesc())) {
			String limitDesc = backActivity.getLimitDesc();
			log.debug("注册活动环节限时默认事件，限时规则：" + limitDesc);
			String[] timeLimit = parseTimerArray(limitDesc, processDatabus);
			timerManager.createTimer(null, timeLimit, TimerManager.TIMER_KIND_ACTIVITY, TimerManager.TIMER_TYPE_LIMIT,
					backActivity.getId());
		}

		String hql = "from Action where bindingId=? and actionTime in ('启动','提醒','超时')";
		List<Action> actions = processDao.createQuery(hql, backActivity.getId()).list();
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			if ("启动".equals(action.getActionTime())) {
				log.debug("执行环节启动事件监听者方法【" + action.getActionDo() + ".startAction】");
				StartActionInf sa = (StartActionInf) parseClassOrBean(action.getActionDo().trim());
				sa.startAction(backActivity);
			} else {
				if (!(backActivity.getIsLimit() || backActivity.getIsRemind()))
					continue;
				String timerType = "超时".equals(action.getActionTime()) ? TimerManager.TIMER_TYPE_LIMIT
						: TimerManager.TIMER_TYPE_REMIND;

				String timerDesc = timerType == TimerManager.TIMER_TYPE_LIMIT ? backActivity.getLimitDesc()
						: activity.getRemindDesc();
				if (StringUtil.isEmpty(timerDesc))
					continue;
				log.debug("注册流程环节定时任务【" + action.getActionDo() + "】，类别【" + action.getActionTime() + "】");
				String[] timeLimit = parseTimerArray(timerDesc, processDatabus);
				timerManager.createTimer(action.getActionDo().trim(), timeLimit, TimerManager.TIMER_KIND_ACTIVITY,
						timerType, backActivity.getId());
			}
		}

		// 清除数据总线中的临时数据
		JSONObject jo = processImpl.getDataBusJson();
		jo.remove(DATA_BUS_NEXT_ACTIVITY_ID);
		processImpl.setDataBus(jo.toString());

		log.debug("写入流程退回记录");
		Trask trask = new Trask();
		trask.setAction("从【" + activity.getName() + "】退回到【" + backActivity.getName() + "】");
		trask.setExeDate(new Date());
		trask.setPerson(bpmUser.getName());
		trask.setPersonId(bpmUser.getId());
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());
		this.processDao.save(trask);

		log.debug(">> 退回成功，目标环节【" + backActivity.getName() + "】被重新激活。");
		return backActivity;
	}

	/**
	 * 将XML转换为流程实例
	 * 
	 * @param xmlDoc
	 *            流程描述XML
	 * @return 返回start activity
	 */
	public Activity parseXml(Document xmlDoc) {
		Element processElement = xmlDoc.getRootElement();
		log.debug("创建流程实例");
		// 创建流程实例
		Process processImpl = new Process();
		processImpl.setCreatorDate(new Date());
		processImpl.setFlowName(processElement.attributeValue("name"));
		String dform = processElement.attributeValue("dealForm");
		String vform = processElement.attributeValue("viewForm");
		processImpl.setDealForm(StringUtil.isEmpty(dform) ? null : dform);
		processImpl.setViewForm(StringUtil.isEmpty(vform) ? null : vform);
		processImpl.setIsLimit(Boolean.valueOf(processElement.attributeValue("isLimit")));
		processImpl.setLimitDesc(processElement.attributeValue("limitDesc"));
		processImpl.setIsRemind(Boolean.valueOf(processElement.attributeValue("isRemind")));
		processImpl.setRemindDesc(processElement.attributeValue("remindDesc"));

		processDao.save(processImpl);
		// 设置事件
		String actionStr = processElement.attributeValue("actions");
		if (!StringUtil.isEmpty(actionStr)) {
			for (String str : actionStr.split(";")) {
				String listener[] = str.split(",", -1);
				if (StringUtil.isEmpty(listener[0]) || StringUtil.isEmpty(listener[1]))
					continue;
				Action action = new Action();
				action.setId(null);
				action.setActionTime(listener[0]);
				action.setActionDo(listener[1]);
				action.setRemark(listener[2]);
				action.setBindingId(processImpl.getId());
				processDao.save(action);
			}
		}

		Set<Activity> activities = new HashSet<Activity>();
		// 开始环节
		log.debug("流程定义XML文件解析，开始环节");
		Element startElement = (Element) processElement.elements("start").get(0);
		Activity startActivity = new Activity();
		startActivity.setProcess(processImpl);
		startActivity.setType("start");
		startActivity.setActivityId(startElement.attributeValue("id"));
		startActivity.setName(startElement.attributeValue("name"));
		startActivity.setOpinion(startElement.attributeValue("process"));
		startActivity.setIsLimit(Boolean.valueOf(startElement.attributeValue("isLimit")));
		startActivity.setLimitDesc(startElement.attributeValue("limitDesc"));
		startActivity.setIsRemind(Boolean.valueOf(startElement.attributeValue("isRemind")));
		startActivity.setRemindDesc(startElement.attributeValue("remindDesc"));
		startActivity.setState("500");
		startActivity.setEndTime(new Date());
		processDao.save(startActivity);
		actionStr = startElement.attributeValue("actions");
		if (!StringUtil.isEmpty(actionStr)) {
			for (String str : actionStr.split(";")) {
				String tp[] = str.split(",", -1);
				if (StringUtil.isEmpty(tp[0]) || StringUtil.isEmpty(tp[1]))
					continue;
				Action action = new Action();
				action.setId(null);
				action.setActionTime(tp[0]);
				action.setActionDo(tp[1]);
				action.setRemark(tp[2]);
				action.setBindingId(startActivity.getId());
				processDao.save(action);
			}
		}

		activities.add(startActivity);

		// 开始环节流转方向
		log.debug("流程定义XML文件解析[开始环节流转方向]");
		List<Element> startTransitionList = startElement.elements("transition");
		for (Element element : startTransitionList) {
			Transition flowTransition = new Transition();
			flowTransition.setProcess(processImpl);
			flowTransition.setName(element.attributeValue("name"));
			flowTransition.setFromId(element.attributeValue("fromId"));
			flowTransition.setToid(element.attributeValue("toId"));
			flowTransition.setType(element.attributeValue("type"));
			flowTransition.setOrderby(element.attributeValue("orderby"));
			Element conditionElement = element.element("condition");
			if (null != conditionElement)
				// conditionElement.attributeValue("expr") 表达式为属性，改为了文本元素
				flowTransition.setCondition(conditionElement.getTextTrim());// 将条件表达式改为了为文本内容
			processDao.save(flowTransition);
		}
		log.debug("流程定义XML文件解析[任务环节]");

		// 任务环节
		List<Element> taskList = processElement.elements("task");
		for (Element element : taskList) {
			Activity activity = new Activity();
			activity.setProcess(processImpl);
			activity.setType("task");
			activity.setActivityId(element.attributeValue("id"));
			activity.setName(element.attributeValue("name"));
			activity.setSignature(element.attributeValue("signature"));
			activity.setFunction(element.attributeValue("functions"));
			activity.setOpinion(element.attributeValue("process"));
			String adform = element.attributeValue("dealForm");
			String avform = element.attributeValue("viewForm");
			activity.setProcessUrl(StringUtil.isEmpty(adform) ? null : adform);
			activity.setViewUrl(StringUtil.isEmpty(avform) ? null : avform);
			activity.setIsLimit(Boolean.valueOf(element.attributeValue("isLimit")));
			activity.setLimitDesc(element.attributeValue("limitDesc"));
			activity.setIsRemind(Boolean.valueOf(element.attributeValue("isRemind")));
			activity.setRemindDesc(element.attributeValue("remindDesc"));

			// 内部流转属性
			Attribute innerFlowAttr = element.attribute("innerFlow");
			activity.setInnerFlow(innerFlowAttr == null ? "0" : innerFlowAttr.getValue());

			Set<Participator> activityParticipator = new HashSet<Participator>();
			processDao.save(activity);
			log.debug("流程定义XML文件解析[任务环节设置事件]");
			// 设置事件
			actionStr = element.attributeValue("actions");
			if (!StringUtil.isEmpty(actionStr)) {
				for (String str : actionStr.split(";")) {
					String tp[] = str.split(",", -1);
					if (StringUtil.isEmpty(tp[0]) || StringUtil.isEmpty(tp[1]))
						continue;
					Action action = new Action();
					action.setId(null);
					action.setActionTime(tp[0]);
					action.setActionDo(tp[1]);
					action.setRemark(tp[2]);
					action.setBindingId(activity.getId());
					processDao.save(action);
				}
			}
			// 设置参与人
			log.debug("流程定义XML文件解析[任务环节设置参与人]");
			String participators = element.attributeValue("participators");
			List<Participator> plist = parseActivityDefinedParticipator(activity, participators);
			if (plist.size() > 0) {
				activity.setParticipantType(plist.get(0).getParticipantType());
				activityParticipator.addAll(plist);
			}

			log.debug("流程定义XML文件解析[任务环节 流转方向]");
			// 流转方向
			List<Element> transitionList = element.elements("transition");
			for (Element elTransition : transitionList) {
				Transition flowTransition = new Transition();
				flowTransition.setProcess(processImpl);
				flowTransition.setName(elTransition.attributeValue("name"));
				flowTransition.setToid(elTransition.attributeValue("toId"));
				flowTransition.setType(elTransition.attributeValue("type"));
				flowTransition.setFromId(elTransition.attributeValue("fromId"));
				flowTransition.setOrderby(elTransition.attributeValue("orderby"));
				Element conditionElement = elTransition.element("condition");
				if (null != conditionElement)
					flowTransition.setCondition(conditionElement.getTextTrim());
				processDao.save(flowTransition);
			}
			activity.setParticipators(activityParticipator);
			activities.add(activity);
		}
		log.debug("流程定义XML文件解析[分流环节]");
		// 分流环节
		List<Element> dforeachList = processElement.elements("subFlow");
		for (Element element : dforeachList) {
			Activity activity = new Activity();
			activity.setProcess(processImpl);
			activity.setType("subFlow");
			activity.setActivityId(element.attributeValue("id"));
			activity.setName(element.attributeValue("name"));
			activity.setSignature(element.attributeValue("signature"));
			activity.setFunction(element.attributeValue("functions"));
			activity.setOpinion(element.attributeValue("process"));

			String adform = element.attributeValue("dealForm");
			String avform = element.attributeValue("viewForm");
			activity.setProcessUrl(StringUtil.isEmpty(adform) ? null : adform);
			activity.setViewUrl(StringUtil.isEmpty(avform) ? null : avform);

			activity.setIsLimit(Boolean.valueOf(element.attributeValue("isLimit")));
			activity.setLimitDesc(element.attributeValue("limitDesc"));
			activity.setIsRemind(Boolean.valueOf(element.attributeValue("isRemind")));
			activity.setRemindDesc(element.attributeValue("remindDesc"));
			activity.setSubFlowId(element.attributeValue("subFlowId"));
			activity.setSubFlowName(element.attributeValue("subFlowName"));
			processDao.save(activity);
			// 设置事件
			actionStr = element.attributeValue("actions");
			if (!StringUtil.isEmpty(actionStr)) {
				for (String str : actionStr.split(";")) {
					String tp[] = str.split(",", -1);
					if (StringUtil.isEmpty(tp[0]) || StringUtil.isEmpty(tp[1]))
						continue;
					Action action = new Action();
					action.setId(null);
					action.setActionTime(tp[0]);
					action.setActionDo(tp[1]);
					action.setRemark(tp[2]);
					action.setBindingId(activity.getId());
					processDao.save(action);
				}
			}
			// 设置参与人
			String participators = element.attributeValue("participators");
			Set<Participator> activityParticipator = new HashSet<Participator>();
			activityParticipator.addAll(parseActivityDefinedParticipator(activity, participators));
			activity.setParticipators(activityParticipator);

			log.debug("流程定义XML文件解析[分流环节流转方向]");
			List<Element> transitionList = element.elements("transition");
			for (Element elTransition : transitionList) {
				Transition flowTransition = new Transition();
				flowTransition.setProcess(processImpl);
				flowTransition.setName(elTransition.attributeValue("name"));
				flowTransition.setFromId(elTransition.attributeValue("fromId"));
				flowTransition.setToid(elTransition.attributeValue("toId"));
				flowTransition.setType(elTransition.attributeValue("type"));
				flowTransition.setOrderby(elTransition.attributeValue("orderby"));
				Element conditionElement = elTransition.element("condition");
				if (null != conditionElement)
					flowTransition.setCondition(conditionElement.getTextTrim());
				processDao.save(flowTransition);
			}
			activities.add(activity);
		}
		log.debug("流程定义XML文件解析[分流环节]");
		// 分流环节
		List<Element> foreachList = processElement.elements("foreach");
		for (Element element : foreachList) {
			Activity activity = new Activity();
			activity.setProcess(processImpl);
			activity.setType("foreach");
			activity.setActivityId(element.attributeValue("id"));
			activity.setName(element.attributeValue("name"));
			activity.setSignature(element.attributeValue("signature"));
			activity.setFunction(element.attributeValue("functions"));
			activity.setOpinion(element.attributeValue("process"));
			String adform = element.attributeValue("dealForm");
			String avform = element.attributeValue("viewForm");
			activity.setProcessUrl(StringUtil.isEmpty(adform) ? null : adform);
			activity.setViewUrl(StringUtil.isEmpty(avform) ? null : avform);
			activity.setIsLimit(Boolean.valueOf(element.attributeValue("isLimit")));
			activity.setLimitDesc(element.attributeValue("limitDesc"));
			activity.setIsRemind(Boolean.valueOf(element.attributeValue("isRemind")));
			activity.setRemindDesc(element.attributeValue("remindDesc"));
			processDao.save(activity);
			// 设置事件
			actionStr = element.attributeValue("actions");
			if (!StringUtil.isEmpty(actionStr)) {
				for (String str : actionStr.split(";")) {
					String tp[] = str.split(",", -1);
					if (StringUtil.isEmpty(tp[0]) || StringUtil.isEmpty(tp[1]))
						continue;
					Action action = new Action();
					action.setId(null);
					action.setActionTime(tp[0]);
					action.setActionDo(tp[1]);
					action.setRemark(tp[2]);
					action.setBindingId(activity.getId());
					processDao.save(action);
				}
			}
			// 设置参与人
			String participators = element.attributeValue("participators");
			Set<Participator> activityParticipator = new HashSet<Participator>();
			activityParticipator.addAll(parseActivityDefinedParticipator(activity, participators));
			activity.setParticipators(activityParticipator);

			log.debug("流程定义XML文件解析[分流环节流转方向]");
			List<Element> transitionList = element.elements("transition");
			for (Element elTransition : transitionList) {
				Transition flowTransition = new Transition();
				flowTransition.setProcess(processImpl);
				flowTransition.setName(elTransition.attributeValue("name"));
				flowTransition.setFromId(elTransition.attributeValue("fromId"));
				flowTransition.setToid(elTransition.attributeValue("toId"));
				flowTransition.setType(elTransition.attributeValue("type"));
				flowTransition.setOrderby(elTransition.attributeValue("orderby"));
				Element conditionElement = elTransition.element("condition");
				if (null != conditionElement)
					flowTransition.setCondition(conditionElement.getTextTrim());
				processDao.save(flowTransition);
			}
			activities.add(activity);
		}
		log.debug("流程定义XML文件解析[聚合环节]");
		// 聚合环节
		List<Element> joinList = processElement.elements("join");
		for (Element element : joinList) {
			Activity activity = new Activity();
			activity.setProcess(processImpl);
			activity.setType("join");
			activity.setActivityId(element.attributeValue("id"));
			activity.setName(element.attributeValue("name"));
			activity.setSignature(element.attributeValue("signature"));
			activity.setFunction(element.attributeValue("functions"));
			activity.setOpinion(element.attributeValue("process"));
			String adform = element.attributeValue("dealForm");
			String avform = element.attributeValue("viewForm");
			activity.setProcessUrl(StringUtil.isEmpty(adform) ? null : adform);
			activity.setViewUrl(StringUtil.isEmpty(avform) ? null : avform);
			activity.setIsLimit(Boolean.valueOf(element.attributeValue("isLimit")));
			activity.setLimitDesc(element.attributeValue("limitDesc"));
			activity.setIsRemind(Boolean.valueOf(element.attributeValue("isRemind")));
			activity.setRemindDesc(element.attributeValue("remindDesc"));
			processDao.save(activity);
			// 设置事件
			actionStr = element.attributeValue("actions");
			if (!StringUtil.isEmpty(actionStr)) {
				for (String str : actionStr.split(";")) {
					String tp[] = str.split(",", -1);
					if (StringUtil.isEmpty(tp[0]) || StringUtil.isEmpty(tp[1]))
						continue;
					Action action = new Action();
					action.setId(null);
					action.setActionTime(tp[0]);
					action.setActionDo(tp[1]);
					action.setRemark(tp[2]);
					action.setBindingId(activity.getId());
					processDao.save(action);
				}
			}
			// 设置参与人
			String participators = element.attributeValue("participators");
			Set<Participator> activityParticipator = new HashSet<Participator>();
			activityParticipator.addAll(parseActivityDefinedParticipator(activity, participators));
			activity.setParticipators(activityParticipator);
			log.debug("流程定义XML文件解析[聚合环节流转方向]");
			// 流转方向
			List<Element> transitionList = element.elements("transition");
			for (Element elTransition : transitionList) {
				Transition flowTransition = new Transition();
				flowTransition.setProcess(processImpl);
				flowTransition.setName(elTransition.attributeValue("name"));
				flowTransition.setFromId(elTransition.attributeValue("fromId"));
				flowTransition.setToid(elTransition.attributeValue("toId"));
				flowTransition.setType(elTransition.attributeValue("type"));
				flowTransition.setOrderby(elTransition.attributeValue("orderby"));
				Element conditionElement = elTransition.element("condition");
				if (null != conditionElement)
					flowTransition.setCondition(conditionElement.getTextTrim());
				processDao.save(flowTransition);
			}
			activities.add(activity);
		}
		// 结束环节
		log.debug("流程定义XML文件解析[结束环节]");
		List<Element> endList = processElement.elements("end");
		for (Element element : endList) {
			Activity activity = new Activity();
			activity.setProcess(processImpl);
			activity.setType("end");
			activity.setActivityId(element.attributeValue("id"));
			activity.setName(element.attributeValue("name"));
			processDao.save(activity);
			activities.add(activity);
		}

		processImpl.setActivitys(activities);
		return startActivity;
	}

	protected List<Participator> parseActivityDefinedParticipator(Activity activity, String pStr) {
		List<Participator> activityParticipator = new ArrayList<Participator>();
		if (!StringUtil.isEmpty(pStr)) {
			String par[] = pStr.split(";");
			if (par[0].equals("startFlow")) {
				Participator participator = new Participator(activity, par[0], null, null, null, null, "GET", null,
						true);
				activityParticipator.add(participator);
				processDao.save(participator);
			} else if (par[0].equals("databus")) {
				Participator participator = new Participator(activity, par[0], null, null, null, null, "GET", null,
						true);
				activityParticipator.add(participator);
				processDao.save(participator);
			} else if (par[0].equals("role")) {
				Participator participator = new Participator(activity, par[0], par[1], par[2], null,
						par.length > 3 ? par[3] : null, "GET", par.length > 4 ? par[4] : null, true);
				activityParticipator.add(participator);
				processDao.save(participator);
			} else if (par[0].equals("position")) {
				Participator participator = new Participator(activity, par[0], par[1], par[2], null,
						par.length > 3 ? par[3] : null, "GET", null, true);
				activityParticipator.add(participator);
				processDao.save(participator);
			} else {
				String[] ids = par[1].split(",");
				String[] name = par[2].split(",");
				for (int i = 0; i < ids.length; i++) {
					Participator participator = new Participator(activity, par[0], ids[i], name[i], null, null, "GET",
							null, true);
					activityParticipator.add(participator);
					processDao.save(participator);
				}
			}
		}
		return activityParticipator;
	}

	protected StringBuilder parseActivityDatabusParticipator(Activity activity, Set<Participator> resultParticipators,
			String chooseRolePerson) {
		JSONArray dataBusParticipant;
		StringBuilder participatorsName = new StringBuilder();
		try {
			JSONObject dataBus = activity.getProcess().getDataBusJson();
			dataBusParticipant = dataBus.getJSONArray(this.dataBusParticipantKey);
			dataBus.remove(this.dataBusParticipantKey);// 从数据总线移除参与者数据
			activity.getProcess().setDataBus(dataBus.toString());// 重设数据总线
		} catch (Exception e) {
			throw new KhntException("不能从数据总线中获取到下一步环节参与者。");
		}
		if (dataBusParticipant.length() < 1)
			throw new KhntException("下一步的参与者为空。");

		log.debug("数据总线获取的参与者总数：" + dataBusParticipant.length());
		// 余下的参与者逐个新增，并加入到环节参与者列表
		for (int index = 0; index < dataBusParticipant.length(); index++) {
			JSONObject participant = (JSONObject) dataBusParticipant.get(index);
			Participator personParticipator = new Participator(activity, "person", participant.getString("id"),
					participant.getString("name"), null, null, "EXE", chooseRolePerson, false);
			resultParticipators.add(personParticipator);
			participatorsName.append(participant.getString("name")).append(';');
		}
		return participatorsName;
	}

	protected ActivityParticipatorBean parseActivityRuntimeParticipator(Activity activity, BpmUser user) {
		ActivityParticipatorBean participatorBean = new ActivityParticipatorBean();
		Set<Participator> existps = activity.getParticipators();
		Set<Participator> definedParticipators = new HashSet<>();
		for (Participator ep : existps) {
			if (ep.getDefined())
				definedParticipators.add(ep);
		}
		if (definedParticipators.isEmpty() && "person".equals(activity.getParticipantType())) {
			// 人员参与者预定义为空，历史数据
			definedParticipators = existps;
			for (Participator ep : definedParticipators) {
				ep.setDefined(true);
			}
		} else {
			// 清除原有的实际参与人数据
			Iterator<Participator> ips = existps.iterator();
			while (ips.hasNext()) {
				Participator p = ips.next();
				if (!p.getDefined()) {
					this.processDao.remove(p);
					ips.remove();
				}
			}
		}

		String participantType = activity.getParticipantType();
		Participator firstParticipator = null;
		if (definedParticipators != null && definedParticipators.size() > 0) {
			firstParticipator = definedParticipators.iterator().next();
		}
		if (StringUtil.isEmpty(participantType) && firstParticipator != null) {
			participantType = firstParticipator.getParticipantType();
		}
		log.debug("当前环节参与者类型:" + participantType + "\n------->> 开始处理参与者信息");
		JSONObject dataBus = activity.getProcess().getDataBusJson();
		if ("startFlow".equals(participantType)) {
			Participator starter = new Participator(activity, "person", activity.getProcess().getCreatorId(),
					activity.getProcess().getCreator(), null, null, "EXE", null, false);
			participatorBean.names.append("流程发起人：" + starter.getParticipantName());
			log.debug("环节参与者为流程启动者【" + starter.getParticipantName() + "】");
			participatorBean.participators.add(starter);
		} else if ("databus".equals(participantType)
				|| ("1".equals(activity.getInnerFlow()) && dataBus.has(dataBusParticipantKey))) {
			log.debug("环节参与者为数据总线动态指定，或者参与者为环节内部流转且数据总线有参与人数据");
			participatorBean.names
					.append(parseActivityDatabusParticipator(activity, participatorBean.participators, "0"));
		} else if ("person".equals(participantType)) {
			log.debug("环节参与者为预设人员");
			for (Participator participator : definedParticipators) {
				participator.setPartiInType("EXE");
				Participator personParticipator = new Participator(activity, "person", participator.getParticipantId(),
						participator.getParticipantName(), null, null, "EXE", null, false);
				participatorBean.participators.add(personParticipator);
				participatorBean.names.append(participator.getParticipantName()).append(';');
			}
		} else if ("role".equals(participantType) || "position".equals(participantType)) {
			String rolePosId = firstParticipator.getParticipantId();
			if (StringUtil.isEmpty(rolePosId)) {
				log.error("环节参与者定义为角色或岗位，但并未指定对应的角色或岗位");
				throw new KhntException("未配置角色或岗位");
			}
			boolean isRole = "role".equals(participantType);
			if ("system".equals(user.getId())) {
				log.debug("【注】当前环节的启动操作可能是由定时任务发起的");
			}

			boolean chooseRolePerson = "1".equals(firstParticipator.getChooseRolePerson());
			// 角色参与者在启动环节时被定义为动态转换为人员，实际执行的参与者都有用户指定并通过dataBus传递过来
			if (isRole && chooseRolePerson) {
				if (!dataBus.has(dataBusParticipantKey)) {
					log.error("环节参与者定义为角色，并需要动态转换为人员，但数据总线中未找到目标人员数据");
					throw new KhntException("未指定下一环节角色人员");
				}
				// 从数据总线中提取参与者
				participatorBean.names
						.append(parseActivityDatabusParticipator(activity, participatorBean.participators, "1"));
			} else {
				StringBuilder loggerMsg = new StringBuilder();
				loggerMsg.append(isRole ? "角色：" : "岗位：").append(firstParticipator.getParticipantName()).append("，范围：");
				firstParticipator.setPartiInType("EXE");
				String rangeType = firstParticipator.getParticipantRangeType();
				String range = null, rangeName = null;
				if ("dept".equals(rangeType)) {
					if (user.getDepartment() != null) {
						range = user.getDepartment().getId();
						rangeName = user.getDepartment().getName();
						loggerMsg.append("当前操作人【").append(user.getName())
								.append("】所在部门【" + user.getDepartment().getName()).append("】");
					}
				} else if ("unit".equals(rangeType)) {
					if (user.getUnit() != null) {
						range = user.getUnit().getId();
						rangeName = user.getUnit().getName();
						loggerMsg.append("当前操作人【").append(user.getName()).append("】所在单位【")
								.append(user.getUnit().getName()).append("】");
					}
				} else if ("sunit".equals(rangeType) || "super".equals(rangeType)) {
					if (user.getParentUnit() != null) {
						range = user.getParentUnit().getId();
						rangeName = user.getParentUnit().getName();
						loggerMsg.append("当前操作人【").append(user.getName()).append("】所在单位的上级单位【")
								.append(user.getParentUnit().getName()).append("】");
					}
				} else if ("databus".equals(rangeType)) {
					if (!dataBus.has(this.dataBusRoleRangeKey)) {
						log.error("无法从数据总线获取参与者范围设置！");
						throw new KhntException("无法从数据总线获取参与者范围设置！");
					}
					String databusRange = dataBus.get(this.dataBusRoleRangeKey).toString();
					if (databusRange.indexOf(",") == -1) {
						range = databusRange;
					} else {
						String[] dbr = databusRange.split(",");
						range = dbr[0];
						rangeName = dbr[1];
					}
					loggerMsg.append("数据总线动态获取值【").append(databusRange + "】");
				} else {
					loggerMsg.append("无限制");
				}
				log.debug(loggerMsg.toString());

				if (isRole) {
					// 将解析出的范围更新到参与者信息中，方便回退到本环节时自动寻找目标人员
					firstParticipator.setParticipantRange(range);
					participatorBean.names.append("角色：");
					if (rangeName != null)
						participatorBean.names.append(rangeName).append("-");
					participatorBean.names.append(firstParticipator.getParticipantName());

					Participator personParticipator = new Participator(activity, participantType,
							firstParticipator.getParticipantId(), firstParticipator.getParticipantName(),
							firstParticipator.getParticipantRange(), firstParticipator.getParticipantRangeType(), "EXE",
							firstParticipator.getChooseRolePerson(), false);
					participatorBean.participators.add(personParticipator);

				} else {
					// 参与者为岗位，需要从多个岗位中筛选出符合指定范围的岗位
					String[] posIds = rolePosId.split(",");
					participatorBean.names.append("岗位：");

					for (String pid : posIds) {
						Position pos = positionManager.getPosition(pid);
						if (StringUtil.isEmpty(range) || range.equals(pos.getOrg().getId())) {
							participatorBean.names.append(pos.getPosName()).append(";");
							Participator personParticipator = new Participator(activity, participantType, pos.getId(),
									pos.getPosName(), null, null, "EXE", firstParticipator.getChooseRolePerson(),
									false);
							participatorBean.participators.add(personParticipator);
						}
					}
				}
			}
		} else {
			log.error("无法识别的参与者类型[" + participantType + "]，无法提交下一环节");
			throw new KhntException("参与者设置错误，不能识别的参与者配置");
		}
		log.debug("---------->> 参与者信息处理完成");

		// 仅对非子流程环节
		if (!"subFlow".equals(activity.getType())) {
			// 设置实际的参与人
			// 将动态解析出来的参与人作为实际参与者加入参与者列表
			activity.getParticipators().addAll(participatorBean.participators);
			// activity.setParticipators(participatorBean.participators);
		}
		return participatorBean;
	}

	/**
	 * 启动指定的流程环节
	 * 
	 * @param activity
	 * @param parameter
	 * @throws Exception
	 */
	protected List<Activity> startActivity(Activity activity, BpmUser user) throws Exception {
		if ("300".equals(activity.getState()))
			throw new KhntException("此环节已经启动了，不能重复启动！");

		log.debug("准备启动工作流环节任务【" + activity.getProcess().getFlowName() + "：" + activity.getName() + "】");

		// 设置环节状态为300表示启动
		activity.setState("300");
		activity.setStartTime(new Date());
		Process process = activity.getProcess();

		log.trace("当前流程实例中的数据总线:\n" + process.getDataBus());
		JSONObject dataBus = process.getDataBusJson();

		// 更新环节
		processDao.save(activity);

		// 处理环节参与者,将所有目标参与者名称返回
		ActivityParticipatorBean participatorBean = null;
		if (!"start".equals(activity.getType()) && !"end".equals(activity.getType()))
			participatorBean = this.parseActivityRuntimeParticipator(activity, user);

		log.debug("流程环节启动事件处理【：" + activity.getName() + "】");

		// 注册默认执行事件
		if (null != activity.getIsLimit() && activity.getIsLimit()) {
			String limitDesc = activity.getLimitDesc();
			if (StringUtil.isNotEmpty(limitDesc)) {
				log.debug("注册环节限时默认事件，限时规则：" + limitDesc);
				String[] timeLimit = parseTimerArray(limitDesc, dataBus);
				timerManager.createTimer(null, timeLimit, TimerManager.TIMER_KIND_ACTIVITY,
						TimerManager.TIMER_TYPE_LIMIT, activity.getId());
			}
		}

		String hql = "from Action where bindingId=? and actionTime in ('启动','提醒','超时')";
		List<Action> actions = processDao.createQuery(hql, activity.getId()).list();
		log.debug("流程环节启动事件监听者总数：" + actions.size());
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			if (StringUtil.isEmpty(action.getActionDo()) || StringUtil.isEmpty(action.getActionTime()))
				continue;
			log.debug("监听者：" + action.getActionDo() + "，监听类别：" + action.getActionTime());
			if ("启动".equals(action.getActionTime())) {
				StartActionInf sa = (StartActionInf) parseClassOrBean(action.getActionDo().trim());
				sa.startAction(activity);
			} else {
				if (!(activity.getIsLimit() || activity.getIsRemind()))
					continue;
				String timerType = "超时".equals(action.getActionTime()) ? TimerManager.TIMER_TYPE_LIMIT
						: TimerManager.TIMER_TYPE_REMIND;
				// 注册环节超时或者提醒定时任务
				String timerDesc = timerType == TimerManager.TIMER_TYPE_LIMIT ? activity.getLimitDesc()
						: activity.getRemindDesc();
				if (StringUtil.isEmpty(timerDesc))
					continue;
				String[] timeLimit = parseTimerArray(timerDesc, dataBus);
				timerManager.createTimer(action.getActionDo().trim(), timeLimit, TimerManager.TIMER_KIND_ACTIVITY,
						timerType, activity.getId());
			}
		}
		// 检查当前被启动的环节是否为子流程环节
		if (!"subFlow".equals(activity.getType())) {
			if (!"start".equals(activity.getType()) && !"end".equals(activity.getType())) {
				// 普通环节写入流程记录
				Trask trask = new Trask();
				trask.setAction("启动环节【" + activity.getName() + "】，参与者【" + participatorBean.names + "】");
				trask.setExeDate(new Date());
				trask.setPerson(user.getName());
				trask.setPersonId(user.getId());
				trask.setActivitId(activity.getId());
				trask.setProcessId(process.getId());
				processDao.save(trask);
			}
			List<Activity> resultActivities = new ArrayList<Activity>();
			resultActivities.add(activity);
			return resultActivities;
		}
		if (StringUtil.isEmpty(activity.getSubFlowId()))
			throw new KhntException("启动子流程失败，子流程环节【" + activity.getName() + "】定义为子流程环节，但是并未提供子流程对应的流程定义对象");

		// 子流程，同步启动所有子流程实例
		return startSubProcess(activity, user, participatorBean);
	}

	private String[] parseTimerArray(String timerDesc, JSONObject dataBus) {
		String[] timeLimit;
		if (timerDesc.startsWith("related;")) {
			String[] limitDescArr = timerDesc.split(";");
			String dataBusKey = limitDescArr[1];
			if (!dataBus.has(dataBusKey)) {
				throw new KhntException("解析环节限时描述失败，无法从数据总线获取时限信息");
			}
			timerDesc = dataBus.getString(dataBusKey);
			if (StringUtil.isEmpty(timerDesc)) {
				throw new KhntException("解析环节限时描述失败，从数据总线获取的时限配置为空");
			}
			timeLimit = timerDesc.split(",", -1);
		} else {
			String[] desginTimerDesc = timerDesc.split(",", -1);
			timeLimit = new String[] { desginTimerDesc[1], desginTimerDesc[2], desginTimerDesc[3] };
		}
		return timeLimit;
	}

	/**
	 * 结束指定的流程环节
	 * 
	 * @param activity
	 * @param parameter
	 * @throws Exception
	 */
	protected void finishActivity(Activity activity, BpmUser bpmUser) throws Exception {
		log.debug(">> 准备完成工作流环节任务【" + activity.getProcess().getFlowName() + "：" + activity.getName() + "】");
		activity.setState("500");
		activity.setEndTime(new Date());
		processDao.save(activity);
		String hql = "from Action where bindingId=? and actionTime=? and actionDo is not null";
		List<Action> actions = processDao.createQuery(hql, activity.getId(), "完成").list();
		log.debug("本环节有" + actions.size() + "个完成事件监听者，准备执行监听方法");
		for (Action action : actions) {
			if (StringUtil.isEmpty(action.getActionDo()))
				continue;
			FinishActionInf impl = (FinishActionInf) this.parseClassOrBean(action.getActionDo().trim());
			log.debug(">>开始执行监听方法【" + action.getActionDo() + ".finishAction】");
			impl.finishAction(activity, bpmUser);
			log.debug("<<监听方法【" + action.getActionDo() + ".finishAction】执行完成");
		}

		log.debug("写入流程运转纪录");
		Trask trask = new Trask();
		trask.setAction("完成【" + activity.getName() + "】");
		trask.setExeDate(new Date());
		trask.setPerson(bpmUser.getName());
		trask.setPersonId(bpmUser.getId());
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());

		log.debug("清除本环节的定时任务");
		timerManager.clearTimer(activity.getId());

		log.debug("将环节参与者状态更改为已执行");
		hql = "update Participator set executed=true where activity.id=? and defined=false";
		this.processDao.createQuery(hql, activity.getId()).executeUpdate();

		log.debug(">> 工作流环节任务【" + activity.getProcess().getFlowName() + "：" + activity.getName() + "】已完成");
		processDao.save(trask);
	}

	/**
	 * 检查流程是否可被操作。当流程被锁定后不允许进行除解锁之外的任何操作。
	 * 
	 * @param process
	 */
	protected void checkProcessState(Process process) {
		if ("100".equals(process.getState()))
			throw new KhntException("该流程已被锁定，无法进行此操作!");
	}

	/**
	 * 结束流程
	 * 
	 * @param process
	 * @param bpmUser
	 * @param finishType
	 *            0:业务审批时因为不同意而结束流程，1:正常结束
	 * 
	 * @return 如果是子流程结束，返回父流程的下一步
	 * @throws Exception
	 */
	public Activity finishFlow(Process process, BpmUser bpmUser, int finishType) throws Exception {
		checkProcessState(process);

		// 更改流程状态为【完成】
		process.setState("500");
		process.setOverDate(new Date());
		processDao.save(process);

		// 更改活动节点(任务所处节点)状态为完成
		for (Activity activity : process.getActivitys()) {
			if ("300".equals(activity.getState()) || "end".equals(activity.getType()))
				activity.setState("500");
		}

		// 开始执行流程[完成事件]
		List<Action> actions = processDao.findActions(process.getId(), "完成");
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			FinishFlowInf ff = (FinishFlowInf) parseClassOrBean(action.getActionDo().trim());
			ff.finishFlow(process, finishType, bpmUser);
		}

		// 清除定时器
		this.timerManager.clearTimer(process.getId());

		Activity parentNextActivity = null;

		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction(bpmUser.getName() + "完成业务流程");
		trask.setExeDate(new Date());
		trask.setPerson(bpmUser.getName());
		trask.setPersonId(bpmUser.getId());
		trask.setProcessId(process.getId());
		processDao.save(trask);

		// 如果是子流程，检查父流程连接环节的所有子流程是否已经执行完成
		if (process.getParentActivity() != null) {
			boolean isFinishAllSubProcess = true;

			// 父节点的子流程是否全部完成
			for (Process sp : process.getParentActivity().getSubProcess()) {
				if (!"500".equals(sp.getState())) {
					isFinishAllSubProcess = false;
					break;
				}
			}

			// 父节点的参与者是否处理完成
			// 不再检查父流程环节参与者状态，因为子流程是自动发起，父流程环节无需人工处理，默认全部为已处理
			// 从而只需要检查子流程是否全部完成
			/*
			 * for (Participator ppt : process.getParentActivity().getParticipators()) { if
			 * (!ppt.getExecuted()) { isFinishAllSubProcess = false; break; } }
			 */

			// 如果已经执行完成，完成父节点
			if (isFinishAllSubProcess) {
				this.finishActivity(process.getParentActivity(), bpmUser);
				Activity nextActivity = this.doTransition(process.getParentActivity(), true);
				if ("end".equals(nextActivity.getType())) {
					this.finishActivity(nextActivity, bpmUser);
					this.finishFlow(nextActivity.getProcess(), bpmUser, FinishFlowInf.FINISH_TYPE_COMMON);
				} else {
					this.startActivity(nextActivity, bpmUser);
					parentNextActivity = nextActivity;
				}
			}
		}
		return parentNextActivity;
	}

	/**
	 * 启动流程
	 * 
	 * @param process
	 * @param parameter
	 * @throws Exception
	 */
	protected void startFlow(Process process) throws Exception {
		log.debug("启动流程");
		log.debug("开始执行流程事件");

		// 注册默认执行事件
		if (null != process.getIsLimit() && process.getIsLimit()) {
			String limitDesc = process.getLimitDesc();
			if (StringUtil.isNotEmpty(limitDesc)) {
				String[] timeLimit = parseTimerArray(limitDesc, process.getDataBusJson());
				timerManager.createTimer(null, timeLimit, TimerManager.TIMER_KIND_FLOW, TimerManager.TIMER_TYPE_LIMIT,
						process.getId());
			}
		}

		String hql = "from Action where bindingId=? and actionTime in ('创建','启动','提醒','超时')";
		List<Action> actions = processDao.createQuery(hql, process.getId()).list();
		for (int i = 0; i < actions.size(); i++) {
			Action action = (Action) actions.get(i);
			if ("启动".equals(action.getActionTime())) {
				log.debug("开始执行流程启动事件");
				StartFlowInf sf = (StartFlowInf) parseClassOrBean(action.getActionDo().trim());
				sf.startFlow(process);
			} else {
				if (!(process.getIsLimit() || process.getIsRemind()))
					continue;
				String timerType = "超时".equals(action.getActionTime()) ? TimerManager.TIMER_TYPE_LIMIT
						: TimerManager.TIMER_TYPE_REMIND;
				// 注册流程的提醒事件
				String timerDesc = timerType == TimerManager.TIMER_TYPE_LIMIT ? process.getLimitDesc()
						: process.getRemindDesc();
				if (StringUtil.isEmpty(timerDesc))
					continue;
				String timeLimit[];
				try {
					timeLimit = this.parseTimerArray(process.getRemindDesc(), process.getDataBusJson());
				} catch (KhntException e) {
					log.error(e.getMessage());
					throw new KhntException(
							new StringBuilder("流程启动定时事件设置失败，获取定时描述信息失败，合法的格式应该为“[天],[时],[分]”，此处的定时描述信息为【")
									.append(process.getRemindDesc()).append("】当前流程【").append(process.getFlowName())
									.append("】流程定义ID【").append(process.getDefinitionId()).append("】").toString());
				}
				timerManager.createTimer(action.getActionDo().trim(), timeLimit, TimerManager.TIMER_KIND_FLOW,
						timerType, process.getId());

			}
		}
	}

	/**
	 * 检查环节是否可以撤回
	 * 
	 * @param activityId
	 * @param bpmUser
	 * @return
	 */
	public boolean checkActivityRecallEnable(String activityId, BpmUser bpmUser) {
		Activity activity = (Activity) this.processDao.get(Activity.class, activityId);
		if (activity == null)
			throw new KhntException("操作错误，未找到环节信息");
		if ("300".equals(activity.getState()) || !"500".equals(activity.getState()))
			return false;
		List<Activity> currents = activityDao.getCurrentProcessActivity(activity.getProcess().getId());
		List<Transition> enableTransitions = this.findNextEnableTransitions(activity, true);
		for (Activity current : currents) {
			for (Transition transition : enableTransitions) {
				if (current.getActivityId().equals(transition.getToid())) {
					// 可达环节,检查是否为当前人员提交
					Trask trask = (Trask) this.processDao
							.createQuery("from Trask t where activitId=? order by exeDate desc", current.getId())
							.setMaxResults(1).uniqueResult();

					if (trask == null || !trask.getPersonId().equals(bpmUser.getId())) {
						return false;
					}
					// 如果目标环节是子流程，需要继续撤销所有子流程
					if ("subFlow".equals(current.getType()) && !this.checkSubProcessCecallable(current)) {
						return false;
					}
					// 为非活动状态，不可撤回
					if (!"300".equals(current.getState())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 撤回环节
	 * 
	 * @param activityId
	 * @param bpmUser
	 */
	public List<Activity> recallActivity(String activityId, BpmUser bpmUser) {
		Activity activity = (Activity) this.processDao.get(Activity.class, activityId);
		if (activity == null)
			throw new KhntException("操作错误，未找到环节信息");

		this.checkProcessState(activity.getProcess());

		if ("300".equals(activity.getState()) || !"500".equals(activity.getState()))
			throw new KhntException("操作错误，无法在此环节执行撤回操作");
		List<Activity> currents = activityDao.getCurrentProcessActivity(activity.getProcess().getId());
		List<Transition> enableTransitions = this.findNextEnableTransitions(activity, true);
		List<Activity> targetActivities = new ArrayList<>();
		for (Activity current : currents) {
			for (Transition transition : enableTransitions) {
				if (current.getActivityId().equals(transition.getToid())) {
					// 可达环节,检查是否为当前人员提交
					Trask trask = (Trask) this.processDao
							.createQuery("from Trask t where activitId=? order by exeDate desc", current.getId())
							.setMaxResults(1).uniqueResult();

					// 可撤回
					if (trask != null && trask.getPersonId().equals(bpmUser.getId())) {
						// 如果目标环节是子流程，需要继续撤销所有子流程
						if ("subFlow".equals(current.getType())) {
							List<Activity> sas = recallSubflowProcess(current);
							if (sas == null) {
								// 子流程无法撤回，操作失败
								throw new KhntException("操作错误，无法在此环节执行撤回操作");
							}
							targetActivities.addAll(sas);
						}

						// 状态置0
						current.setState("0");
						log.debug("清除环节的定时任务");
						timerManager.clearTimer(current.getId());
						// 清除原有的实际参与人
						this.processDao.createQuery("delete Participator where activity.id=? and defined=false",
								current.getId()).executeUpdate();
						targetActivities.add(current);
					}
				}
			}
		}
		if (targetActivities.isEmpty()) {
			throw new KhntException("下一环节已被处理，无法撤回！");
		}

		// 当前环节设为活动可执行状态
		activity.setState("300");

		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction("撤回从【" + activity.getName() + "】提交的任务");
		trask.setExeDate(new Date());
		if (bpmUser != null) {
			trask.setPerson(bpmUser.getName());
			trask.setPersonId(bpmUser.getId());
		}
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());
		processDao.save(trask);

		return targetActivities;
	}

	/**
	 * 检查子流程内部是否可以被撤回
	 * 
	 * @param parentNode
	 * @return
	 */
	protected boolean checkSubProcessCecallable(Activity pActivity) {
		boolean enableRecall = true;
		for (Process p : pActivity.getSubProcess()) {
			if (!"300".equals(p.getState())) {
				enableRecall = false;
				break;
			}

			// 找到子流程第一个环节
			Activity firstTask = null;
			for (Activity a : p.getActivitys()) {
				if ("start".equals(a.getType())) {
					for (Transition ft : p.getTransitions()) {
						if (a.getActivityId().equals(ft.getFromId())) {
							for (Activity na : p.getActivitys()) {
								if (ft.getToid().equals(na.getActivityId())) {
									firstTask = na;
									break;
								}
							}
							break;
						}
					}
					break;
				}
			}

			// 子流程第一个环节处于非活动状态，不可撤回
			if (!"300".equals(firstTask.getState())) {
				enableRecall = false;
				break;
			}
		}
		return enableRecall;
	}

	/**
	 * 撤销某个环节下的所有子流程，需要检查子流程内部是否已经在处理了
	 * 
	 * @param parentNode
	 * @return
	 */
	protected List<Activity> recallSubflowProcess(Activity pActivity) {
		List<Activity> activities = new ArrayList<>();
		Set<Process> subProcs = pActivity.getSubProcess();
		boolean enableRecall = true;
		for (Process p : subProcs) {
			if (!"300".equals(p.getState())) {
				enableRecall = false;
				break;
			}
			Activity firstTask = null;
			for (Activity a : p.getActivitys()) {
				if ("start".equals(a.getType())) {
					for (Transition ft : p.getTransitions()) {
						if (a.getActivityId().equals(ft.getFromId())) {
							for (Activity na : p.getActivitys()) {
								if (ft.getToid().equals(na.getActivityId())) {
									firstTask = na;
									break;
								}
							}
							break;
						}
					}
					break;
				}
			}

			// 子流程第一个环节处于非活动状态
			if (!"300".equals(firstTask.getState())) {
				enableRecall = false;
				break;
			}
			activities.add(firstTask);

			// 清除事件
			timerManager.clearTimer(firstTask.getId());
			timerManager.clearTimer(p.getId());
			processDao.remove(p);// 直接删除子流程
		}

		// 不可撤回
		if (!enableRecall)
			return null;
		else
			return activities;
	}

	/**
	 * 暂挂指定的环节
	 * 
	 * @param activityId
	 */
	public void suspendActivity(String activityId, BpmUser bpmUser) throws KhntException {
		Activity activity = (Activity) this.processDao.get(Activity.class, activityId);
		if (activity == null)
			throw new KhntException("未找到要暂挂的环节！");
		log.debug("暂挂流程环节！" + activity.getName());

		if (!"300".equals(activity.getState()))
			throw new KhntException("此环节并不处于活动状态，不能将其挂起(暂停)！");

		checkProcessState(activity.getProcess());

		activity.setState("400");

		// 将该环节的定时器设置为挂起,状态码：2
		this.timerDao.changeServiceTimerState(activityId, "2");
		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction("暂停【" + activity.getName() + "】");
		trask.setExeDate(new Date());
		if (bpmUser != null) {
			trask.setPerson(bpmUser.getName());
			trask.setPersonId(bpmUser.getId());
		}
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());
		processDao.save(trask);
	}

	/**
	 * 重启指定的被暂挂的环节
	 * 
	 * @param activityId
	 */
	public void restartActivity(String activityId, BpmUser bpmUser) throws KhntException {
		Activity activity = (Activity) this.processDao.get(Activity.class, activityId);
		if (activity == null)
			throw new KhntException("未找到要重启的环节！");
		log.debug("重启暂挂的环节！" + activity.getName());
		if (!"400".equals(activity.getState()))
			throw new KhntException("此环节并未挂起(暂停)，不能使用重启方法启动该环节！");

		checkProcessState(activity.getProcess());

		activity.setState("300");

		// 将该环节的定时器设置为运行,状态码：1
		this.timerDao.changeServiceTimerState(activityId, "1");

		Trask trask = new Trask();
		trask.setAction("重启环节【" + activity.getName() + "】");
		trask.setExeDate(new Date());
		if (bpmUser != null) {
			trask.setPerson(bpmUser.getName());
			trask.setPersonId(bpmUser.getId());
		}
		trask.setActivitId(activity.getId());
		trask.setProcessId(activity.getProcess().getId());
		processDao.save(trask);
	}

	/**
	 * 获取指定环节的后续步骤，可以是提交也可以是回退的目标待选
	 * 
	 * @param aid
	 * @param forward
	 * @param dataBus
	 * @return
	 */
	public List<Activity> findNextActivities(String aid, boolean forward, String dataBus) {
		Activity a = (Activity) this.processDao.get(Activity.class, aid);
		List<Activity> rst;
		try {
			// 尝试直接路由
			this.setProcessDataBus(a.getProcess(), dataBus);
			List<Transition> ts = this.findNextEnableTransitions(a, forward);
			rst = new ArrayList<Activity>();
			for (Transition t : ts) {
				rst.add(this.chooseNextActivity(t.getToid(), a.getProcess().getActivitys()));
			}
		} catch (Exception e) {
			// 无法直接路由，获取所有可达环节返回给客户选择
			String hql = "select a from Transition t, Activity a, Activity b where t.fromId=b.activityId and t.toid=a.activityId and a.process.id=b.process.id and a.process.id=t.process.id and b.id=:aid and t.type=:type"
					+ (forward ? "" : " and a.state='500'") + " order by a.activityId";
			Query q = processDao.createQuery(hql);
			q.setParameter("aid", aid);
			q.setParameter("type", forward ? "default" : "back");// 路由类型：提交/回退
			rst = q.list();
		}
		// 流程动作为提交，并且当前环节允许内部流转，允许提交到当前环节
		if ("1".equals(a.getInnerFlow()) && forward)
			rst.add(0, a);
		return rst;
	}

	/**
	 * 锁定流程实例，使其挂起，不能进行任何运转，除非解锁
	 * 
	 * @param pid
	 */
	public void suspendProcess(String pid, BpmUser bpmUser) {
		Process proc = this.processDao.get(pid);
		if (proc == null) {
			throw new KhntException("参数错误");
		}

		proc.setState("100");// 挂起状态

		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction("锁定业务流程，操作人【" + bpmUser.getName() + "】");
		trask.setExeDate(new Date());
		trask.setPerson(bpmUser.getName());
		trask.setPersonId(bpmUser.getId());
		trask.setProcessId(proc.getId());
		processDao.save(trask);
	}

	/**
	 * 重启流程实例，解锁。
	 * 
	 * @param pid
	 * @param bpmUser
	 */
	public void restartProcess(String pid, BpmUser bpmUser) {
		Process proc = this.processDao.get(pid);
		if (proc == null) {
			throw new KhntException("参数错误");
		}

		if (!"100".equals(proc.getState()))
			throw new KhntException("非法的操作，该流程未被锁定，不能进行解锁操作");

		proc.setState("300");// 运行状态

		// 写入流程跟踪
		Trask trask = new Trask();
		trask.setAction("解锁业务流程，操作人【" + bpmUser.getName() + "】");
		trask.setExeDate(new Date());
		trask.setPerson(bpmUser.getName());
		trask.setPersonId(bpmUser.getId());
		trask.setProcessId(proc.getId());
		processDao.save(trask);
	}

	// ================================ 辅助工具 =================================

	/**
	 * 重设数据总线，目的是将业务执行时即时传进来的数据总线参数合并到工作流实例已有的数据总线。
	 * 
	 * 合并过程中，将会使用新的数据覆盖先前已存在的数据。
	 * 
	 * @param process
	 * @param dataBus
	 */
	protected void setProcessDataBus(Process process, String dataBus) {
		if (StringUtil.isEmpty(dataBus))
			return;
		JSONObject processDataBus = process.getDataBusJson();
		JSONObject inputDataBus = JSONObject.fromString(dataBus);
		Iterator<String> keys = inputDataBus.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			// 移除原有的数据，新的数据将会覆盖原来的。
			if (processDataBus.has(key))
				processDataBus.remove(key);
			processDataBus.put(key, inputDataBus.get(key));
		}
		process.setDataBus(processDataBus.toString());
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
			object = ContextLoader.getCurrentWebApplicationContext().getBean(classOrBeanName);
			if (object == null)
				throw new KhntException("给定的事件执行者类路径或bean name无效！");
		}

		return object;
	}

	class ActivityParticipatorBean {
		StringBuilder names = new StringBuilder();
		Set<Participator> participators = new HashSet<Participator>();
	}
}
