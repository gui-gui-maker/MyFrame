package com.khnt.bpm.core.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.dao.ProcessDao;

/**
 * <b>工作流核心引擎实现</b>
 * 
 * <p>
 * 核心引擎实现的主要能力如下：
 * </p>
 * <ul>
 * <li>实例化工作流；</li>
 * <li>工作流活动环节提交；</li>
 * <li>工作流活动环节后退；</li>
 * <li>工作流活动日志、进度跟踪；</li>
 * <li>结束流程实例。</li>
 * </ul>
 * 
 * @author hansel 2013-03-13 created
 * 
 */
@Service("bpmEngine")
public class BpmEngine {

	public static final String PAITICIPANT_TYPE_PERSON = "person";

	public static final String PAITICIPANT_TYPE_ROLE = "role";

	public static final String PAITICIPANT_TYPE_ORG = "org";

	public static final String PAITI_IN_TYPE_EXE = "EXE";

	public static final String PAITI_IN_TYPE_GET = "GET";

	@Autowired
	private ProcessDao processDao;

	/**
	 * 创建bpm实例
	 * 
	 * @param definitionId
	 *            流程定义ID
	 * @param businessId
	 *            业务ID
	 * @param bpmUser
	 *            bpm用户
	 * @param dataBus
	 *            数据总线，格式为json
	 * @param overClass
	 * 
	 * @return 流程实例
	 * 
	 * @throws Exception
	 */
	public Process createBpmProcess(String definitionId, String businessId, BpmUser bpmUser, String dataBus,
			String overClass) throws Exception {
		return null;
	}

	/**
	 * 启动bpm实例
	 * 
	 * @param processId
	 *            流程实例ID
	 * @param bpmUser
	 *            bpm用户
	 * @param dataBus
	 *            数据总线
	 * 
	 * @return 启动后的活动环节，指处于活动状态的需要处理的环节
	 * 
	 * @throws Exception
	 */
	public List<Activity> startBpmProcess(String processId, BpmUser bpmUser, String dataBus) throws Exception {
		return null;
	}

	/**
	 * 创建bpm实例，同时启动该实例
	 * 
	 * @param definitionId
	 *            流程定义ID
	 * @param businessId
	 *            业务ID
	 * @param bpmUser
	 *            bpm用户
	 * @param dataBus
	 *            数据总线，格式为json
	 * @param overClass
	 * 
	 * @return 启动后的活动环节，指处于活动状态的需要处理的环节
	 * 
	 * @throws Exception
	 */
	public List<Activity> createAndStartBpmProcess(String definitionId, String businessId, BpmUser bpmUser,
			String dataBus, String overClass) throws Exception {
		Process process = this.createBpmProcess(definitionId, businessId, bpmUser, dataBus, overClass);
		return this.startBpmProcess(process.getId(), bpmUser, dataBus);
	}

	/**
	 * 流程提交
	 * 
	 * @param activityId
	 *            当前活动ID
	 * @param bpmUser
	 *            提交人ID
	 * @param dataBus
	 *            提交人姓名
	 * 
	 * @return 提交后的活动环节，指处于活动状态的需要处理的环节
	 * 
	 * @throws Exception
	 */
	public List<Activity> submitActivity(String activityId, BpmUser bpmUser, String dataBus) throws Exception {
		return null;
	}

	/**
	 * 活动环节退回
	 * 
	 * @param activityId
	 *            当前活动ID
	 * @param bpmUser
	 *            bpmy用户
	 * @param dataBus
	 *            数据总线
	 * 
	 * @return 退回后的活动环节，指处于活动状态的需要处理的环节，这些环节是已经执行过的，但此时重启它们。
	 * 
	 * @throws Exception
	 */
	public List<Activity> returnedActivity(String activityId, BpmUser bpmUser, String dataBus) throws Exception {
		return null;
	}

	/**
	 * 结束流程，以指定的类型结束流程
	 * 
	 * @param processId
	 *            流程实例ID
	 * @param bpmUser
	 *            bpm用户
	 * @param finishType
	 *            结束类别（1=正常结束，0=强制结束）
	 * 
	 * @throws Exception
	 */
	public void finishFlow(String processId, BpmUser bpmUser, int finishType) throws Exception {

	}

	/**
	 * 流程日志、进度监控
	 * 
	 * @param processId
	 *            流程实例ID
	 * @return 日志描述XML
	 * @throws Exception
	 */
	public String trackProcess(String processId) throws Exception {
		return null;
	}

	/**
	 * 领取指定的活动事项
	 * 
	 * @param activityId
	 *            活动环节ID
	 * @param bpmUser
	 *            bpm用户
	 * @throws Exception
	 */
	public Activity receiveActivity(String activityId, BpmUser bpmUser) throws Exception {
		Activity activity = (Activity) processDao.get(Activity.class, activityId);
		activity.getParticipators().clear();
		Participator participator = new Participator(activity, PAITICIPANT_TYPE_PERSON, bpmUser.getId(),
				bpmUser.getName(), null, null, PAITI_IN_TYPE_EXE, null, false);
		activity.getParticipators().add(participator);
		this.processDao.save(activity);

		return activity;
	}
}
