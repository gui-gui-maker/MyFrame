package com.khnt.bpm.ext.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.khnt.base.Factory;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.dao.ProcessDao;
import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.core.support.FlowServiceResult;
import com.khnt.bpm.ext.bean.FlowProcessWorktask;
import com.khnt.bpm.ext.dao.FlowExtDao;
import com.khnt.bpm.ext.dao.FlowWorktaskExtDao;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.pub.message.service.SubscriptionMessageManager;
import com.khnt.pub.worktask.service.WorktaskManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.support.CurrentBpmSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***
 * 
 * 流程引擎业务扩展，实现工作流环节任务的自动化、集中化处理。
 * 
 * @author 邹洪平 2013-3-4
 * 
 */
public class FlowWorktaskManager implements FlowExtManager {

	Logger log = LoggerFactory.getLogger(FlowWorktaskManager.class);

	@Autowired
	private FlowEngine flowEngine;

	@Autowired
	private ProcessDao processDao;

	@Autowired
	private WorktaskManager worktaskManager;

	@Autowired
	private ActivityManager activityManager;

	@Autowired
	private FlowServiceConfigManager flowServiceConfigManager;

	@Autowired
	private FlowExtDao flowExtDao;

	@Autowired
	private FlowWorktaskExtDao worktaskExtDao;

	@Autowired
	private CodeTableCache codeTableCache;

	@Autowired
	OpinionManager opinionManager;

	@Autowired
	SubscriptionMessageManager subscriptionMessageManager;

	// =================================================辅助方法===============================================
	/**
	 * 从流程环节设置中获取到参与者，并将参与者转换为最终人员
	 * 
	 * @param activityId
	 * @return
	 */
	protected BpmUser getBpmUser(String activityId) {
		List<BpmUser> bpmUsers = this.activityManager.getBpmUserPaticipator(activityId);
		if (bpmUsers.size() > 0) {
			BpmUser bpmUser = bpmUsers.get(0);
			if (bpmUser != null) {
				BpmUser bu = new BpmUserImpl("system", "system", null, null, null);
				BeanUtils.copyProperties(bpmUser, bu, new String[] { "id", "name" });
				return bu;
			}
		}

		return null;
	}

	protected BpmUser getCurrentBpmUser() {
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			if (user != null && user instanceof CurrentBpmSessionUser)
				return ((CurrentBpmSessionUser) user).getBpmUser();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("无法获取用户信息!");
		}
		return null;
	}

	/**
	 * 构造返回值MAP
	 * 
	 * @param result
	 * @return
	 */
	protected Map<String, Object> createFlowServiceResult(FlowServiceResult result, BpmUser bpmUser) {
		Map<String, Object> reaultMap = new HashMap<String, Object>();
		reaultMap.put(FlowExtWorktaskParam.PROCESS_ID, result.getProcessFlowId());
		reaultMap.put(FlowExtWorktaskParam.PROCESS_NAME, result.getProcessFlowName());
		reaultMap.put(FlowExtWorktaskParam.RESULT_TYPE, result.getResultType());
		reaultMap.put(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST, result.getResultActivity());
		reaultMap.put(FlowExtWorktaskParam.BPM_USER_NEXT_ACTIVITY, result.findPersonParticipator(bpmUser));
		return reaultMap;
	}

	/**
	 * 参数解析，将接口定义的操作方法中使用的流程参数解析为JSONObject。
	 * 
	 * @param obj
	 *            传入的参数，接受的类别是JSONObject和 json字符串
	 * @param paramName
	 *            传入的参数名称
	 * @return
	 */
	protected static JSONObject parseJSONObject(Object obj, String paramName) {
		JSONObject jsonObject = null;
		if (obj != null) {
			if (obj instanceof JSONObject)
				jsonObject = (JSONObject) obj;
			else if (obj instanceof String) {
				try {
					jsonObject = JSONObject.fromString(obj.toString());
				} catch (Exception e) {
					throw new KhntException("当参数[" + paramName + "]为String类型时，必须为json格式！");
				}
			} else
				throw new KhntException("参数[" + paramName + "]类型不正确，必须为JSONObject或者json String！");
		} else {
			jsonObject = JSONObject.fromString("{}");
		}
		return jsonObject;
	}

	// =================================================接口实现===============================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#startFlowProcessBatch(java.util
	 * .List)
	 */
	@Override
	public Map<String, Object> startFlowProcessBatch(List<Map<String, Object>> paramMapList) throws Exception {
		if (paramMapList == null || paramMapList.isEmpty())
			throw new KhntException("参数错误，批量参数为空！");

		// 循环启动
		for (Map<String, Object> paramMap : paramMapList) {
			this.startFlowProcess(paramMap);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#startFlowProcess(java.util.Map)
	 */
	public Map<String, Object> startFlowProcess(Map<String, Object> paramMap) throws Exception {
		Object bpmUserObj = paramMap.get(FlowExtWorktaskParam.BPM_USER);
		BpmUser bpmUser = null;
		if (bpmUserObj == null) {
			bpmUser = getCurrentBpmUser();
			if (bpmUser == null)
				throw new KhntException("启动流程失败，必须提供BpmUser！");
		} else
			bpmUser = (BpmUser) bpmUserObj;

		Object serviceId = paramMap.get(FlowExtWorktaskParam.SERVICE_ID);
		Object remark = paramMap.get(FlowExtWorktaskParam.REMARK);
		Object serviceTitle = paramMap.get(FlowExtWorktaskParam.SERVICE_TITLE);
		Object serviceType = paramMap.get(FlowExtWorktaskParam.SERVICE_TYPE);
		Object isCurrentUserTask = paramMap.get(FlowExtWorktaskParam.IS_CURRENT_USER_TASK);
		Object isWorktask = paramMap.get(FlowExtWorktaskParam.IS_WORKTASK);
		Object isMessage = paramMap.get(FlowExtWorktaskParam.IS_SEND_MESSAGE);
		Object flowDefinitionId = paramMap.get(FlowExtWorktaskParam.FLOW_DEFINITION_ID);
		Object dataBusObj = paramMap.get(FlowExtWorktaskParam.DATA_BUS);
		Object worktaskParamObj = paramMap.get(FlowExtWorktaskParam.WORKTASK_PARAMETER);
		JSONObject worktaskParam = parseJSONObject(worktaskParamObj, "FlowExtWorktaskParam.WORKTASK_PARAMETER");
		JSONObject dataBus = parseJSONObject(dataBusObj, "FlowExtWorktaskParam.DATA_BUS");

		FlowServiceResult result;
		try {
			result = this.startFlowProcessInner(null == serviceId ? null : serviceId.toString(), bpmUser,
					null == remark ? null : remark.toString(), dataBus,
					null == flowDefinitionId ? null : flowDefinitionId.toString(),
					null == serviceTitle ? null : serviceTitle.toString(),
					null == serviceType ? null : serviceType.toString(),
					null == isCurrentUserTask ? null : (Boolean) isCurrentUserTask,
					null == isWorktask ? null : (Boolean) isWorktask, null == isMessage ? null : (Boolean) isMessage,
					worktaskParam);
		} catch (KhntException e) {
			log.error("启动流程失败\n" + e.getUiMsage());
			e.setUiMsage("启动流程失败！错误信息：" + e.getUiMsage());
			throw e;
		} catch (Exception e) {
			log.error("启动流程失败\n" + e.getMessage());
			KhntException ke = new KhntException(e);
			ke.setUiMsage("启动流程失败！");
			throw e;
		}
		return this.createFlowServiceResult(result, bpmUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#submitActivityBatch(java.util
	 * .List)
	 */
	@Override
	public Map<String, Object> submitActivityBatch(List<Map<String, Object>> paramMapList) throws Exception {
		if (paramMapList == null || paramMapList.isEmpty())
			throw new KhntException("参数错误，批量参数为空！");

		// 循环提交
		for (Map<String, Object> paramMap : paramMapList) {
			this.submitActivity(paramMap);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#submitActivity(java.util.Map)
	 */
	public Map<String, Object> submitActivity(Map<String, Object> paramMap) throws Exception {
		Object activityId = paramMap.get(FlowExtWorktaskParam.ACTIVITY_ID);
		if (activityId == null)
			throw new KhntException("环节提交失败，必须提供环节ID【FlowExtWorktaskParam.ACTIVITY_ID】!");
		Object bpmUserObj = paramMap.get(FlowExtWorktaskParam.BPM_USER);
		BpmUser bpmUser = null;
		if (bpmUserObj == null) {
			bpmUser = getCurrentBpmUser();
			if (bpmUser == null)
				bpmUser = getBpmUser(activityId.toString());
		} else
			bpmUser = (BpmUser) bpmUserObj;

		Object isWorktask = paramMap.get(FlowExtWorktaskParam.IS_WORKTASK);
		Object isMessage = paramMap.get(FlowExtWorktaskParam.IS_SEND_MESSAGE);
		Object serviceTitle = paramMap.get(FlowExtWorktaskParam.SERVICE_TITLE);
		Object dataBusObj = paramMap.get(FlowExtWorktaskParam.DATA_BUS);
		Object worktaskParamObj = paramMap.get(FlowExtWorktaskParam.WORKTASK_PARAMETER);
		JSONObject worktaskParam = parseJSONObject(worktaskParamObj, "FlowExtWorktaskParam.WORKTASK_PARAMETER");
		JSONObject dataBus = parseJSONObject(dataBusObj, "FlowExtWorktaskParam.DATA_BUS");
		FlowServiceResult result;
		try {
			result = this.submitActivityInner(activityId.toString(), bpmUser, dataBus,
					null == serviceTitle ? null : serviceTitle.toString(),
					null == isWorktask ? null : (Boolean) isWorktask, null == isMessage ? null : (Boolean) isMessage,
					worktaskParam);
		} catch (KhntException e) {
			log.error("//-------------------------- 环节提交失败 ---------------------------\n");
			LogUtil.logError(log, e);
			e.setUiMsage("环节提交失败！错误信息：" + e.getUiMsage());
			throw e;
		} catch (Exception e) {
			log.error("//--------------------------环节提交失败 ---------------------------\n");
			LogUtil.logError(log, e);
			throw e;
		}
		return this.createFlowServiceResult(result, bpmUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#returnedActivity(java.util.Map)
	 */
	public Map<String, Object> returnedActivity(Map<String, Object> paramMap) throws Exception {
		Object bpmUserObj = paramMap.get(FlowExtWorktaskParam.BPM_USER);
		Object activityId = paramMap.get(FlowExtWorktaskParam.ACTIVITY_ID);
		if (activityId == null)
			throw new KhntException("未提供当前环节ID");

		BpmUser bpmUser = null;
		if (bpmUserObj == null) {
			bpmUser = getCurrentBpmUser();
			if (bpmUser == null)
				bpmUser = getBpmUser(activityId.toString());
		} else
			bpmUser = (BpmUser) bpmUserObj;

		Object isWorktask = paramMap.get(FlowExtWorktaskParam.IS_WORKTASK);
		Object isMessage = paramMap.get(FlowExtWorktaskParam.IS_SEND_MESSAGE);
		Object serviceTitle = paramMap.get(FlowExtWorktaskParam.SERVICE_TITLE);
		Object dataBusObj = paramMap.get(FlowExtWorktaskParam.DATA_BUS);
		Object worktaskParamObj = paramMap.get(FlowExtWorktaskParam.WORKTASK_PARAMETER);
		JSONObject worktaskParam = parseJSONObject(worktaskParamObj, "FlowExtWorktaskParam.WORKTASK_PARAMETER");
		JSONObject dataBus = parseJSONObject(dataBusObj, "FlowExtWorktaskParam.DATA_BUS");

		FlowServiceResult result;
		try {
			result = this.returnedActivityInner(null == activityId ? null : activityId.toString(), bpmUser, dataBus,
					null == serviceTitle ? null : serviceTitle.toString(),
					null == isWorktask ? null : (Boolean) isWorktask, null == isMessage ? null : (Boolean) isMessage,
					worktaskParam);
		} catch (KhntException e) {
			log.error("//--------------------------环节退回失败\n" + e.getUiMsage() + "//---------------------------\n");
			e.setUiMsage("环节退回失败！错误信息：" + e.getUiMsage());
			throw e;
		} catch (Exception e) {
			log.error("//--------------------------环节退回失败\n" + e.getMessage() + "//---------------------------\n");
			KhntException ke = new KhntException(e);
			ke.setUiMsage("启动流程失败！错误信息：");
			throw e;
		}
		return this.createFlowServiceResult(result, bpmUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#finishProcessBatchs(java.util
	 * .List)
	 */
	public void finishProcessBatchs(List<Map<String, Object>> paramMapList) throws Exception {
		if (paramMapList == null || paramMapList.isEmpty())
			throw new KhntException("参数错误，批量参数为空！");

		// 循环结束
		for (Map<String, Object> paramMap : paramMapList) {
			this.finishProcess(paramMap);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#finishProcess(java.util.Map)
	 */
	public void finishProcess(Map<String, Object> paramMap) throws Exception {
		Object processId = paramMap.get(FlowExtWorktaskParam.PROCESS_ID);
		Object finishType = paramMap.get(FlowExtWorktaskParam.FINISH_TYPE);
		BpmUser bpmUser = getCurrentBpmUser();

		this.finishProcessInner(null == processId ? null : processId.toString(),
				null == finishType ? FinishFlowInf.FINISH_TYPE_COMMON : (Integer) finishType, bpmUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.khnt.bpm.ext.service.FlowExtManager#suspendActivity(java.lang.String)
	 */
	public void suspendActivity(String activityId) throws Exception {
		BpmUser bpmUser = getCurrentBpmUser();
		this.flowEngine.suspendActivity(activityId, bpmUser);
		this.worktaskManager.changeWorktaskStatus(activityId, null, WorktaskManager.STATUS_SUSPEND);
		Activity a = (Activity) this.processDao.get(Activity.class, activityId);
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(a.getProcess().getId());

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】状态发生变化。【")
					.append(bpmUser == null ? "system" : bpmUser.getName()).append("】将环节【").append(a.getName())
					.append("】任务暂停！");
			notifyFlowParticiptor(processWorktask.getProcessId(), msg.toString(), bpmUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#startActivity(java.lang.String)
	 */
	public void restartActivity(String activityId) throws Exception {
		BpmUser bpmUser = getCurrentBpmUser();
		this.flowEngine.restartActivity(activityId, bpmUser);
		this.worktaskManager.changeWorktaskStatus(activityId, null, WorktaskManager.STATUS_NO_PEND);

		Activity a = (Activity) this.processDao.get(Activity.class, activityId);
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(a.getProcess().getId());

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】状态发生变化。【")
					.append(bpmUser == null ? "system" : bpmUser.getName()).append("】，重启被暂停的环节【").append(a.getName())
					.append("】任务");
			notifyFlowParticiptor(processWorktask.getProcessId(), msg.toString(), bpmUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#suspendProcess(java.lang.String)
	 */
	public void suspendProcess(String procId) throws Exception {
		BpmUser bpmUser = getCurrentBpmUser();
		this.flowEngine.suspendProcess(procId, bpmUser);
		this.worktaskExtDao.suspendProcessWorktask(procId);

		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(procId);

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】被【")
					.append(bpmUser == null ? "system" : bpmUser.getName()).append("】锁定，所有相关任务均被暂停。");
			notifyFlowParticiptor(processWorktask.getProcessId(), msg.toString(), bpmUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#restartProcess(java.lang.String)
	 */
	public void restartProcess(String procId) throws Exception {
		BpmUser bpmUser = getCurrentBpmUser();
		this.worktaskExtDao.restartProcessWorktask(procId);

		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(procId);

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】被【")
					.append(bpmUser == null ? "system" : bpmUser.getName()).append("】解除锁定，所有相关暂停的任务均被重启。");
			notifyFlowParticiptor(processWorktask.getProcessId(), msg.toString(), bpmUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#clearServiceProcess(java.lang
	 * .String)
	 */
	public void clearServiceProcess(String serviceId) throws Exception {
		this.flowExtDao.clearServiceProcess(serviceId);
	}

	// ======================================自定义具体实现==================================================

	/**
	 * 
	 * @param businessId
	 *            业务ID
	 * @param bpmUser
	 *            工作流用户，是接口{@link com.khnt.bpm.communal.BpmUser}的实现
	 * @param remark
	 *            备注描述
	 * @param dataBus
	 *            流程数据总线，以json字符串格式存在
	 * @param flowDefinitionId
	 *            流程定义ID
	 * @param serviceTitle
	 *            业务标题
	 * @param typeCode
	 *            业务类别编号
	 * @param isCurrentUserTask
	 *            是否使用给定的bpmUser发送任务，true：是，false：使用流程第一个环节设定的人发送任务，
	 *            如果流程第一环节没有设定参与者，则仍然给bpmUser发任务
	 * @param parameter
	 *            业务处理url参数，格式为url地址参数默认相同，这些参数在启动流程时会设置到业务流程全局过程中，供业务处理全局使用。
	 * @return 返回此次操作的结果信息
	 * 
	 * @throws Exception
	 */
	protected FlowServiceResult startFlowProcessInner(String businessId, BpmUser bpmUser, String remark,
			JSONObject dataBus, String flowDefinitionId, String serviceTitle, String typeCode,
			Boolean isCurrentUserTask, Boolean isWorktask, Boolean isMessage, JSONObject parameter) throws Exception {

		// 检查业务标题，如果未给定将从指定的业务编号获取业务配置名称，如果编号不存在，则标题为空
		if (StringUtil.isEmpty(serviceTitle)) {
			if (StringUtil.isNotEmpty(typeCode))
				// 业务编号存在，从数据字典加载业务名称，字典code通过系统参数配置
				serviceTitle = codeTableCache.getCodeTableValueNames(
						Factory.getSysPara().getProperty("bpm.wsconfig.codetable", "pub_workflow_service_code"),
						typeCode);
			else
				serviceTitle = "";
		}

		// 首先，创建工作流实例，获取第一个活动环节
		Activity fisrtActivity = this.flowEngine.createFlowProcess(businessId, bpmUser, remark,
				dataBus.isNullObject() ? null : dataBus.toString(), null, flowDefinitionId);

		String urgentLevel;
		if (dataBus.has(FlowExtWorktaskParam.WORKTASK_LEVEL)) {
			urgentLevel = dataBus.getString(FlowExtWorktaskParam.WORKTASK_LEVEL);
			dataBus.remove(FlowExtWorktaskParam.WORKTASK_LEVEL);
		} else
			urgentLevel = WorktaskManager.URGENT_LEVEL_COMMON;

		// 记录流程任务类别代码
		FlowProcessWorktask flowProcessWorktask = new FlowProcessWorktask(typeCode, serviceTitle,
				fisrtActivity.getProcess().getId(), null, urgentLevel, fisrtActivity.getProcess().getFlowName());

		// 设置全局工作任务URL参数
		flowProcessWorktask.setParameter(parameter.toString());
		this.processDao.save(flowProcessWorktask);

		// 来自数据总线的参与者信息
		JSONArray dataBusPerson = dataBus.has(flowEngine.getDataBusParticipantKey())
				? dataBus.getJSONArray(flowEngine.getDataBusParticipantKey())
				: null;

		// 从系统参数中获取是否自动提交第一个环节的配置，默认为否
		boolean autoSubmitFirst = Factory.getSysPara().getProperty("bpm_auto_submit_first_node", "yes").equals("yes");

		// 当自动提交第一个环节时
		// 设置第一个环节提交时的用户意见，以支持流程发起意见
		// 在流程未启动前用户意见意见保存，采用的是业务ID作为临时环节ID，此处需要更新为启动后的流程第一环节ID
		if (autoSubmitFirst)
			this.opinionManager.updateOpinionActivityId(businessId, businessId, fisrtActivity.getId());

		// 为第一个活动环节添加工作任务，状态根据是否提交第一环节来决定，如是则为已完成、否则未完成
		// 如果参数isCurrentUserTask值为true，表示给当前创建人创建待办任务
		// 如果为false则是为流程第一环节设置的参与者创建任务，若未设定参与者，仍然给创建人创建任务
		if ((isCurrentUserTask == null || !isCurrentUserTask) && null != fisrtActivity.getParticipators()
				&& !fisrtActivity.getParticipators().isEmpty()) {
			this.createWorktask(true, false, fisrtActivity, bpmUser, serviceTitle, flowProcessWorktask.getTypeCode(),
					autoSubmitFirst ? WorktaskManager.STATUS_PROCESSED : WorktaskManager.STATUS_NO_PEND, dataBusPerson,
					flowProcessWorktask.getUrgentLevel());
		} else {
			this.createBpmWorktask(
					new StringBuilder(serviceTitle).append("-【").append(fisrtActivity.getName()).append("】").toString(),
					typeCode, bpmUser.getId(), bpmUser.getName(), bpmUser.getId(), bpmUser.getName(), null,
					WorktaskManager.HANDLER_TYPE_PERSON, null, fisrtActivity.getId(),
					new StringBuilder("工作流：【").append(fisrtActivity.getProcess().getFlowName()).append("】，环节：【")
							.append(fisrtActivity.getName()).append("】").toString(),
					autoSubmitFirst ? WorktaskManager.STATUS_PROCESSED : WorktaskManager.STATUS_NO_PEND,
					flowProcessWorktask.getUrgentLevel(), false);
		}
		FlowServiceResult result;
		if (autoSubmitFirst) {
			// 默认地，实例化流程之后第一个活动环节即刻提交到下一步
			result = flowEngine.submitActivity(fisrtActivity, bpmUser, null);

			// 如果目标环节不为空且需要发送工作任务为每一个参与者发送工作任务
			if (!result.getResultActivity().isEmpty()) {
				for (Activity activity : result.getResultActivity()) {
					createWorktask(isWorktask, isMessage, activity, bpmUser, serviceTitle,
							flowProcessWorktask.getTypeCode(), null, dataBusPerson,
							flowProcessWorktask.getUrgentLevel());
				}
			}
		} else {
			List<Activity> activities = new ArrayList<>();
			activities.add(fisrtActivity);
			result = new FlowServiceResult(FlowServiceResult.RESULT_TYPE_START_PROCESS,
					fisrtActivity.getProcess().getId(), fisrtActivity.getProcess().getFlowName(), activities);
		}

		return result;
	}

	/**
	 * 工作流活动环节提交任务,扩展实现自动发送工作任务给下一步处理人，同时将当前人员的工作任务状态修改为已处理
	 * 
	 * @param activityId
	 *            环节id
	 * @param bpmUser
	 *            流程用户对象,是接口{@link com.khnt.bpm.communal.BpmUser}的实现
	 * @param dataBus
	 *            流程数据总线
	 * @param serviceTitle
	 *            业务标题
	 * @param parameter
	 *            业务处理url参数，格式为url地址参数默认相同，这些参数不同于启动流程的参数，他们只会在当前环节的任务url中存在，
	 *            不会影响全局的参数。
	 * @throws Exception
	 */
	protected FlowServiceResult submitActivityInner(String activityId, BpmUser bpmUser, JSONObject dataBus,
			String serviceTitle, Boolean isWorktask, Boolean isMessage, JSONObject parameter) throws Exception {
		if (StringUtil.isEmpty(activityId))
			throw new KhntException("流程环节ID必须提供！");

		// 获取BpmUser
		BpmUser participtor;
		if (bpmUser == null)
			participtor = new BpmUserImpl("system", "system", null, null, null);
		else
			participtor = bpmUser;

		// 提交流程引擎执行环节提交任务
		FlowServiceResult result = flowEngine.submitActivity(activityId, participtor,
				dataBus.isNullObject() ? null : dataBus.toString());

		// 流程实例工作任务配置
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(result.getProcessFlowId());

		// --------------------------------启动子流程后续处理-开始---------------------------
		// 当启动子流程时，自动为子流程保存一份业务类别工作任务配置
		if (result.getResultType() == FlowServiceResult.RESULT_TYPE_START_SUB_PROCESS
				&& result.getResultActivity().size() == 1) {
			Activity sfa = result.getResultActivity().get(0);
			FlowProcessWorktask sfpw = new FlowProcessWorktask(processWorktask.getTypeCode(),
					processWorktask.getServiceTitle(), sfa.getProcess().getId(), null, processWorktask.getUrgentLevel(),
					sfa.getProcess().getFlowName());
			this.processDao.save(sfpw);
		}
		// --------------------------------启动子流程后续处理-结束---------------------------

		// 设置url参数
		this.setWorktaskParameter(processWorktask, parameter);

		int resultType = result.getResultType();

		// 变更当前人员的工作任务状态为已处理
		// 如果bpmUser为非空，更改该bpmUser的工作任务状态为已完成
		// 否则bpmUser为空，将该环节的所有任务置为已完成
		// 如果bpmUser非空，检查本次操作的结果代码，如果为环节提交、流程结束、环节退回、子流程完成，
		// 则需要将环节全部待办任务状态更改为已处理
		boolean finishAll = FlowServiceResult.RESULT_TYPE_SUBMIT_ACTIVITY == resultType
				|| FlowServiceResult.RESULT_TYPE_FINISH_PROCESS == resultType
				|| FlowServiceResult.RESULT_TYPE_RETURNED_ACTIVITY == resultType
				|| FlowServiceResult.RESULT_TYPE_FINISH_SUB_PROCESS == resultType;
		this.worktaskManager.finishWorktask(activityId, bpmUser == null || finishAll ? null : participtor.getId());

		// 如果目标环节不为空且需要发送工作任务为每一个参与者发送工作任务
		if ((resultType == FlowServiceResult.RESULT_TYPE_SUBMIT_ACTIVITY
				|| resultType == FlowServiceResult.RESULT_TYPE_START_SUB_PROCESS)
				&& !result.getResultActivity().isEmpty()) {

			JSONArray dataBusPerson = dataBus.has(flowEngine.getDataBusParticipantKey())
					? dataBus.getJSONArray(flowEngine.getDataBusParticipantKey())
					: null;

			for (Activity activity : result.getResultActivity()) {
				createWorktask(isWorktask, isMessage, activity, participtor,
						StringUtil.isEmpty(serviceTitle) ? processWorktask.getServiceTitle() : serviceTitle,
						processWorktask.getTypeCode(), null, dataBusPerson, processWorktask.getUrgentLevel());
			}
		}

		// 获取消息推送开关设置
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		// 当启用推送功能时，推送消息给所有已参与者
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle());
			if (resultType == FlowServiceResult.RESULT_TYPE_FINISH_PROCESS)
				msg.append("】运转结束。最后操作人【").append(participtor.getName()).append("】");
			else
				msg.append("】状态发生变化。【").append(participtor.getName()).append("】将其提交到环节【")
						.append(result.getResultActivity().get(0).getName()).append("】");
			notifyFlowParticiptor(processWorktask.getProcessId(), msg.toString(), participtor);
		}

		// 2014-10-15，1.0.5版本
		// 锁定意见
		if (bpmUser != null)
			opinionManager.lockOpinion(activityId, bpmUser.getId());
		// 准备返回给调用者本次操作的结果信息
		return result;
	}

	/**
	 * 工作流活动环节退回扩展，实现给退回节点处理人发送工作任务，同时修改退回人的任务状态为已处理。
	 * 
	 * @param activityId
	 *            环节id
	 * @param bpmUser
	 *            流程用户对象,是接口{@link com.khnt.bpm.communal.BpmUser}的实现
	 * @param dataBus
	 *            流程数据总线
	 * @param serviceTitle
	 *            业务标题
	 * @param parameter
	 *            业务处理url参数，格式为url地址参数默认相同，这些参数不同于启动流程的参数，他们只会在当前环节的任务url中存在，
	 *            不会影响全局的参数。
	 * 
	 * @return 操作结果
	 * @throws Exception
	 */
	protected FlowServiceResult returnedActivityInner(String activityId, BpmUser bpmUser, JSONObject dataBus,
			String serviceTitle, Boolean isWorktask, Boolean isMessage, JSONObject parameter) throws Exception {
		if (StringUtil.isEmpty(activityId))
			throw new KhntException("流程环节ID必须提供！");

		// 获取BpmUser
		boolean nullBpmUser = bpmUser == null;
		if (nullBpmUser)
			bpmUser = new BpmUserImpl("system", "system", null, null, null);

		// 交与流程引擎执行退回操作
		Activity activity = this.flowEngine.returnedActivity(activityId, bpmUser,
				dataBus.isNullObject() ? null : dataBus.toString());

		// 获取流程实例工作任务配置
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager
				.getWorktaskType(activity.getProcess().getId());
		// 设置新的worktask url参数
		this.setWorktaskParameter(processWorktask, parameter);

		// 如果需要发送任务，则为被退回的目标环节参与者发送工作任务
		/*
		 * createWorktask(isWorktask, isMessage, activity, bpmUser,
		 * StringUtil.isEmpty(serviceTitle) ? processWorktask.getServiceTitle() :
		 * serviceTitle, processWorktask.getTypeCode(), WorktaskManager.STATUS_NO_PEND);
		 */
		// 发送工作任务到此前环节已经处理的人todo by jyl
		String function = activity.getFunction();
		boolean isSendMsg = function == null ? false : function.contains(FlowFunctionManager.FLOW_FUNCTION_SEND_MSG);
		JSONArray returnedHandlers = getReturnedActivityHandlers(activity.getId());
		for (int i = 0; i < returnedHandlers.length(); i++) {
			JSONObject handler = returnedHandlers.getJSONObject(i);
			String title = StringUtil.isEmpty(serviceTitle) ? processWorktask.getServiceTitle() : serviceTitle;
			this.createBpmWorktask(title + "-【" + activity.getName() + "】", processWorktask.getTypeCode(),
					bpmUser.getId(), bpmUser.getName(), handler.getString("personId"), handler.getString("personName"),
					null, "person", "person", activity.getId(),
					"工作流：【" + activity.getProcess().getFlowName() + "】，环节：【" + activity.getName() + "】",
					WorktaskManager.STATUS_NO_PEND, processWorktask.getUrgentLevel(), isSendMsg);
		}

		// 将当前环节的工作任务状态设为 处理完成
		this.worktaskManager.finishWorktask(activityId, null);

		// 2014-10-15，1.0.5版本
		// 锁定意见
		opinionManager.lockOpinion(activityId, bpmUser.getId());

		// --- 2013-05-02 添加
		// 设置返回对象的流程信息
		FlowServiceResult flowServiceResult = new FlowServiceResult(FlowServiceResult.RESULT_TYPE_RETURNED_ACTIVITY,
				activity.getProcess().getId(), activity.getProcess().getFlowName(), null);
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);
		flowServiceResult.setResultActivity(activities);

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】状态发生变化。【")
					.append(bpmUser.getName()).append("】将其退回到环节【")
					.append(flowServiceResult.getResultActivity().get(0).getName()).append("】");
			notifyFlowParticiptor(activity.getProcess().getId(), msg.toString(), bpmUser);
		}

		return flowServiceResult;
		// --
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.bpm.ext.service.FlowExtManager#recallActivity(java.util.Map)
	 */
	public void recallActivity(Map<String, Object> paramMap) throws Exception {
		Object bpmUserObj = paramMap.get(FlowExtWorktaskParam.BPM_USER);
		Object activityId = paramMap.get(FlowExtWorktaskParam.ACTIVITY_ID);
		if (activityId == null)
			throw new KhntException("未提供当前环节ID");

		BpmUser bpmUser = null;
		if (bpmUserObj == null) {
			bpmUser = getCurrentBpmUser();
			if (bpmUser == null)
				bpmUser = getBpmUser(activityId.toString());
		} else
			bpmUser = (BpmUser) bpmUserObj;

		List<Activity> tartgetActivities = this.flowEngine.recallActivity(activityId.toString(), bpmUser);

		// 将被撤回环节的工作任务状态置为取消
		Activity activity = this.activityManager.get(activityId.toString());
		this.worktaskManager.cancelWorktask(activity.getId(), null);
		// 取消所有被撤回环节的任务
		for (Activity a : tartgetActivities) {
			this.worktaskManager.cancelWorktask(a.getId(), null);
		}
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager
				.getWorktaskType(activity.getProcess().getId());

		// 推送消息给所有已参与者
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg) && processWorktask != null
				&& StringUtil.isNotEmpty(processWorktask.getServiceTitle())) {
			StringBuilder msg = new StringBuilder("业务流程【").append(processWorktask.getServiceTitle()).append("】状态发生变化。【")
					.append(bpmUser.getName()).append("】将其撤回到环节【").append(activity.getName()).append("】");
			notifyFlowParticiptor(activity.getProcess().getId(), msg.toString(), bpmUser);
		}

		// 给当前环节发送一个任务
		this.createBpmWorktask(processWorktask.getServiceTitle() + "-" + activity.getName(),
				processWorktask.getTypeCode(), bpmUser.getId(), bpmUser.getName(), bpmUser.getId(), bpmUser.getName(),
				null, "person", null, activity.getId(), "撤回的任务", WorktaskManager.STATUS_NO_PEND,
				processWorktask.getUrgentLevel(),
				activity.getFunction().indexOf(FlowFunctionManager.FLOW_FUNCTION_SEND_MSG) >= 0);
	}

	/**
	 * 完成流程实例。
	 * 
	 * @param processId
	 *            流程实例ID
	 * @param bpmUser
	 *            流程用户对象,是接口{@link com.khnt.bpm.communal.BpmUser}的实现
	 * @param finishType
	 *            流程结束类别：正常/强制
	 * @throws Exception
	 */
	protected void finishProcessInner(String processId, int finishType, BpmUser bpmUser) throws Exception {
		if (bpmUser == null)
			bpmUser = new BpmUserImpl("system", "system", null, null, null);

		com.khnt.bpm.core.bean.Process process = this.processDao.get(processId);

		// 交与流程引擎执行
		this.flowEngine.finishFlow(process, bpmUser, finishType);
		Set<Activity> activities = process.getActivitys();

		// 将所有流程环节的工作任务状态变更为已处理
		for (Activity act : activities)
			this.worktaskManager.finishWorktask(act.getId(), null);

		// 2014-10-15，1.0.5版本
		// 锁定业务所有意见
		opinionManager.lockOpinion(process.getBusinessId());

		//
		FlowProcessWorktask processWorktask = this.flowServiceConfigManager.getWorktaskType(processId);
		if (processWorktask == null || StringUtil.isEmpty(processWorktask.getServiceTitle()))
			return;
		// 获取消息推送开关设置
		String isNotifyMsg = Factory.getSysPara().getProperty("bpm.state.notify", "no");
		if ("yes".equals(isNotifyMsg)) {
			StringBuilder msg = new StringBuilder();
			msg.append("业务流程【").append(processWorktask.getServiceTitle()).append("】运转结束。最后操作人：【")
					.append(bpmUser.getName()).append("】");
			notifyFlowParticiptor(processId, msg.toString(), bpmUser);
		}
	}

	// ======================================================================================================
	//
	// 以下为添加的扩展
	// ======================================================================================================

	/**
	 * 更新工作任务url参数
	 * 
	 * @param processWorktask
	 * @param parameter
	 */
	@SuppressWarnings("unchecked")
	protected void setWorktaskParameter(FlowProcessWorktask processWorktask, JSONObject parameter) {
		// 将后续的参数追加或者覆盖之前的参数
		if (parameter != null && !parameter.isNullObject()) {
			String processParam = processWorktask.getParameter();
			if (null != processParam) {
				JSONObject json = JSONObject.fromString(processParam);
				Iterator<String> keys = parameter.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					if (json.has(key))
						json.remove(key);// 覆盖原来的
					json.put(key, parameter.get(key));
				}
				processWorktask.setParameter(json.toString());
			}
		}
	}

	/**
	 * 检查环节是否发送工作任务。如果业务明确标识了发送或者不发送任务，即采用业务的设置。如果业务未设置该选项，则根据流程预设决定。
	 * 如果流程预设发送任务，则执行发送，否则不发送.
	 * 
	 * @param activity
	 *            流程环节
	 * @param isWorktask
	 *            业务表示是否发送任务
	 * @return
	 */
	protected boolean isCreateWorktasks(Activity activity, Boolean isWorktask) {
		if (isWorktask != null) {
			if (isWorktask)
				return true;
			else
				return false;
		}
		if (null != activity.getFunction()
				&& activity.getFunction().contains(FlowFunctionManager.FLOW_FUNCTION_WORKTASK))
			return true;
		else
			return false;
	}

	/**
	 * 创建工作流程环节处理工作任务
	 * 
	 * @param title
	 *            任务标题
	 * @param type
	 *            任务类别编号
	 * @param createrId
	 *            创建人ID
	 * @param createrName
	 *            创建人名称
	 * @param handlerId
	 *            处理人ID
	 * @param handlerName
	 *            处理人名称
	 * @param serviceId
	 *            业务ID
	 * @param remark
	 *            备注
	 * @throws Exception
	 */
	protected void createBpmWorktask(String title, String type, String createrId, String createrName, String handlerId,
			String handlerName, Date ovewdueTime, String handlerType, String handlerRange, String serviceId,
			String remark, String status, String taskLevel, Boolean isSendMsg) throws Exception {
		String url = Factory.getSysPara().getProperty("bpm.worktask.handleUrl", "bpm/ext/worktask/doBpmWorktask.do");
		worktaskManager.createWorktask(title, type, createrId, createrName, handlerId, handlerName, ovewdueTime,
				handlerType, handlerRange, serviceId, remark, url, status, taskLevel, isSendMsg);
	}

	/**
	 * 为流程环节参与者创建工作任务
	 * 
	 * @param activity
	 *            流程环节
	 * @param bpmUser
	 *            流程用户
	 * @param serviceTitle
	 *            业务标题
	 * @param typeCode
	 *            业务类别代码
	 * @param status
	 *            状态
	 * @throws Exception
	 */
	protected void createWorktask(Activity activity, BpmUser bpmUser, String serviceTitle, String typeCode,
			String status, Boolean isSendMsg, JSONArray dataBusParticipator, String taskLevel) throws Exception {
		String function = activity.getFunction();
		if (isSendMsg == null)
			isSendMsg = function == null ? false : function.contains(FlowFunctionManager.FLOW_FUNCTION_SEND_MSG);

		for (Participator p : activity.getParticipators()) {
			// 不对预定义的参与人发消息，而只给动态产生的实时实际参与人发送
			if (p.getDefined())
				continue;

			// 业务流程环节参与者如果指定为databus，那么其实际参与者是动态地从数据总线中取得的具体人员
			// 这里为此做出一个检查，如果是databus类型，那么工作任务的参与者type应该是person
			// 1.0.5版本修复了之前由于数据总线指定人员在退回、撤回再提交后环节参与者永远是第一次设置的数据

			if ("databus".equals(p.getParticipantType())) {
				if (dataBusParticipator != null && !dataBusParticipator.isEmpty()) {
					for (int i = 0; i < dataBusParticipator.length(); i++) {
						JSONObject person = dataBusParticipator.getJSONObject(i);
						this.createBpmWorktask(serviceTitle + "-【" + activity.getName() + "】", typeCode,
								bpmUser.getId(), bpmUser.getName(), person.getString("id"), person.getString("name"),
								null, "person", null, activity.getId(),
								"工作流：【" + activity.getProcess().getFlowName() + "】，环节：【" + activity.getName() + "】",
								status, taskLevel, isSendMsg);
					}
				}
			} else {
				this.createBpmWorktask(serviceTitle + "-【" + activity.getName() + "】", typeCode, bpmUser.getId(),
						bpmUser.getName(), p.getParticipantId(), p.getParticipantName(), null, p.getParticipantType(),
						p.getParticipantRange(), activity.getId(),
						"工作流：【" + activity.getProcess().getFlowName() + "】，环节：【" + activity.getName() + "】", status,
						taskLevel, isSendMsg);
			}
		}
	}

	/**
	 * <p>
	 * 为参与者创建工作任务、发送系统消息
	 * </p>
	 * 
	 * @param isWorktask
	 * @param isMessage
	 * @param activity
	 * @param bpmUser
	 * @param serviceTitle
	 * @param typeCode
	 * @param status
	 * @throws Exception
	 */
	protected void createWorktask(Boolean isWorktask, Boolean isMessage, Activity activity, BpmUser bpmUser,
			String serviceTitle, String typeCode, String status, JSONArray dataBusParticipator, String taskLevel)
			throws Exception {
		if (isCreateWorktasks(activity, isWorktask)) {
			this.createWorktask(activity, bpmUser, serviceTitle, typeCode, status, isMessage, dataBusParticipator,
					taskLevel);
		}
	}

	/**
	 * 
	 * 流程发生状态变化，通知所有参与人（推送消息）
	 * 
	 * @param processId
	 * @param content
	 * @param cuid
	 */
	protected void notifyFlowParticiptor(String processId, String content, BpmUser cUser) {
		List<?> persons = this.flowExtDao.findTraskParticiptors(processId, cUser.getId());
		JSONArray receivers = new JSONArray();
		for (int i = 0; i < persons.size(); i++) {
			Object[] person = (Object[]) persons.get(i);
			JSONObject receiver = new JSONObject();
			receiver.put("personId", String.valueOf(person[0]));
			receiver.put("personName", String.valueOf(person[1]));
			receivers.put(receiver);
		}
		String trackLink = Factory.getSysPara().getProperty("bpm.process.track.link",
				"bpm/flowExt/trackProcess.do?status=track&processId=");
		// 给所有参与人发送系统消息
		subscriptionMessageManager.sendMessage(cUser.getId(), cUser.getName(),
				SubscriptionMessageManager.SYSTEM_DEFAULT, content, null, receivers, trackLink + processId, null, null);
	}

	protected JSONArray getReturnedActivityHandlers(String activityId) {
		List<?> persons = this.worktaskExtDao.findReturnedHandlers(activityId);
		JSONArray array = new JSONArray();
		for (int i = 0; i < persons.size(); i++) {
			Object[] person = (Object[]) persons.get(i);
			JSONObject handler = new JSONObject();
			handler.put("personId", String.valueOf(person[0]));
			handler.put("personName", String.valueOf(person[1]));
			array.put(handler);
		}
		return array;
	}
}