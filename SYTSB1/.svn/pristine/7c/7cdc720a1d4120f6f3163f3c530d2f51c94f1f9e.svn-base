package demo.bpm.service;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;

import demo.bpm.bean.BpmServiceBean;
import demo.bpm.dao.BpmServiceDao;

@Service("testServiceManager")
public class BpmServiceManager extends EntityManageImpl<BpmServiceBean, BpmServiceDao> {

	@Autowired
	FlowExtManager flowExtManager;

	@Autowired
	BpmServiceDao testServiceDao;

	/**
	 * 启动审批流程
	 */
	public void startProecess(String id, String flowId, String personType, String nextPersonId, String nextPerson,
			String deptId) throws Exception {
		BpmServiceBean serviceBean = this.testServiceDao.get(id);
		startProecess(serviceBean, flowId, personType, nextPersonId, nextPerson, deptId);
	}

	/**
	 * 保存请假单，并启动审批流程
	 */
	public void saveAndStartProecess(BpmServiceBean serviceBean, String flowId, String personType, String nextPersonId,
			String nextPerson, String deptId) throws Exception {
		this.testServiceDao.save(serviceBean);

		// 启动审批流程
		startProecess(serviceBean, flowId, personType, nextPersonId, nextPerson, deptId);
	}

	/**
	 * 启动审批流程
	 */
	public void startProecess(BpmServiceBean serviceBean, String flowId, String personType, String nextPersonId,
			String nextPerson, String deptId) throws Exception {
		// 设置状态为审批中
		serviceBean.setState("1");

		// 流程提交参数
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// ---------------------------必要参数---------------------------
		paramMap.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		paramMap.put(FlowExtWorktaskParam.SERVICE_ID, serviceBean.getId());

		// ---------------------------可选参数---------------------------

		// 设置数据总线值：申请人职位类别、请假总天数
		JSONObject databus = new JSONObject();
		databus.put("personType", personType);
		databus.put("totalDay", serviceBean.getTotalDay());

		// 如果指定了nextPerson，表明这是部门负责人请假，需要指定一个人来处理下一步审批
		if (StringUtil.isNotEmpty(nextPersonId)) {
			JSONArray ptr = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("id", nextPersonId);
			jo.put("name", nextPerson);
			ptr.put(jo);
			databus.put(FlowEngine.DATA_BUS_PARTICIPANT_KEY_DEFAULT, ptr);
		}

		// 当为请假人普通人员时，需要指定其部门ID，传给流程引擎处理，以指定其部门负责人
		if (personType.equals("worker")) {
			// 当这是一次代理请假时,获取请假人的部门ID,否则只需要获取当前用户的部门ID
			if (serviceBean.getPersonId().equals(serviceBean.getCreaterId())) {
				deptId = this.testServiceDao.findUserOrgId(serviceBean.getPersonId());
			}
			databus.put(FlowEngine.DATA_BUS_ROLE_RANGE_KEY_DEFAULT, deptId);
		}

		// 全局数据参数（数据总线）
		paramMap.put(FlowExtWorktaskParam.DATA_BUS, databus);

		// 处理表单parameters
		// paramMap.put(FlowExtWorktaskParam.WORKTASK_PARAMETER,
		// JSONObject.fromString("{\"testParam1\":\"123123123\"}"));

		// 业务类型（尽可能提交此参数，便于工作任务分类）
		paramMap.put(FlowExtWorktaskParam.SERVICE_TYPE, "demo-bpm-leave");

		// 备注信息
		// paramMap.put(FlowExtWorktaskParam.REMARK, null);

		// 给第一个节点生成已处理的工作任务，是否使用当前登录用户，否则是否用流程定义的配置
		paramMap.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, false);

		// 业务标题，给工作任务提供自定义的标题
		paramMap.put(FlowExtWorktaskParam.SERVICE_TITLE, serviceBean.getPerson() + "请假申请");

		// 启动审批流程
		this.flowExtManager.startFlowProcess(paramMap);
	}

	/**
	 * 批量提交
	 * 
	 * @param activityId
	 */
	public void submitBatch(String activityId) {
		for (int i = 0; i < 10; i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			paramMap.put(FlowExtWorktaskParam.BPM_USER, null);
			paramMap.put(FlowExtWorktaskParam.WORKTASK_PARAMETER,
					JSONObject.fromString("{\"testParam1\":\"submitparam\",\"submitparam2\":\"submitparamValue\"}"));
			try {
				this.flowExtManager.submitActivity(paramMap);
			}
			catch (Exception e) {
				throw new KhntException(e);
			}
		}
	}

	/**
	 * 检查审批结果，并决定是提交下一步还是结束审批。
	 * 
	 * @param activityId
	 *            审批流程环节ID
	 * @param dataBus
	 *            数据总线参数JSON格式
	 * @param signature
	 *            审批意见
	 * @param processId
	 *            流程实例ID
	 * 
	 * @throws Exception
	 */
	public void submitApprove(String activityId, String dataBus, String signature, String processId) throws Exception {

		// 如果审批通过，提交下一步
		if ("1".equals(signature)) {
			this.submitApprove(activityId, dataBus);
		}
		else {// 否则结束审批
			this.finish(processId);
		}
	}

	/**
	 * 提交审批
	 * 
	 * @param activityId
	 *            审批流程环节ID
	 * @param dataBus
	 *            数据总线参数JSON格式
	 * @throws Exception
	 */
	public void submitApprove(String activityId, String dataBus) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// ------------- 必要参数 -----------------------
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);

		// ------------- 可选参数 -----------------------
		// JSONObject databus = new JSONObject();
		// paramMap.put(FlowExtWorktaskParam.DATA_BUS, databus);
		// paramMap.put(FlowExtWorktaskParam.WORKTASK_PARAMETER,JSONObject.fromString("{\"testParam1\":\"submitparam\",\"submitparam2\":\"submitparamValue\"}"));

		this.flowExtManager.submitActivity(paramMap);
	}

	/**
	 * 环节退回
	 * 
	 * @param activityId
	 *            审批环节ID
	 * @param dataBus
	 *            数据总线参数json格式
	 * @throws Exception
	 */
	public void turnback(String activityId, String dataBus) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		paramMap.put(FlowExtWorktaskParam.DATA_BUS, JSONObject.fromString(dataBus));
		paramMap.put(FlowExtWorktaskParam.WORKTASK_PARAMETER,
				JSONObject.fromString("{\"testParam1\":\"returnparam\",\"returnParam2\":\"returnparamValue\"}"));
		this.flowExtManager.returnedActivity(paramMap);
	}

	/**
	 * 结束流程
	 * 
	 * @param processId
	 *            要结束的流程实例ID
	 * @throws Exception
	 */
	public void finish(String processId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// ------------- 必要参数 -----------------------
		paramMap.put(FlowExtWorktaskParam.PROCESS_ID, processId);

		// ------------- 可选参数 -----------------------
		// 流程结束类别：
		// FinishFlowInf.FINISH_TYPE_TERMINATE = 异常(强制)结束
		// FinishFlowInf.FINISH_TYPE_COMMON = 正常结束
		// 这里在业务处理中设定当审批不通过时，统统设定为异常强制结束
		paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
		this.flowExtManager.finishProcess(paramMap);
	}
}
