package com.khnt.bpm.ext.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowDefinitionManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtParam;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.manager.IPositionManager;
import com.khnt.rbac.manager.UserManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.support.CurrentBpmSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 工作流WEB处理接口Controller
 * 
 * 此接口对外提供的方法：
 * <ol>
 * <li>流程监控</li>
 * </ol>
 * 
 */
@Controller
@RequestMapping("/bpm/flowExt/")
public class FlowExtAction {

	public transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private ProcessManager processManager;
	@Autowired
	private FlowEngine flowEngine;
	@Autowired
	private FlowExtManager flowExtManager;
	@Autowired
	private FlowDefinitionManager definitionManager;
	@Autowired
	UserManager userManager;
	@Autowired
	IPositionManager positionManager;

	/**
	 * 实时监控工作流，以图形方式查看流程进度
	 * 
	 * @param request
	 * @param processId
	 * @param serviceId
	 * @return
	 */
	@RequestMapping(value = "trackProcess")
	public ModelAndView trackProcess(String processId, String serviceId) throws Exception {
		Process process;
		if (StringUtil.isEmpty(processId) && StringUtil.isNotEmpty(serviceId))
			process = processManager.getServiceProcess(serviceId);
		else if (StringUtil.isEmpty(processId))
			throw new KhntException("参数错误，流程实例ID和业务ID必须提供至少一个");
		else
			process = processManager.get(processId);

		String flowXml = this.processManager.traskProcess(process);
		Map<String, String> subFlowXmls = this.processManager.traskSubProcess(process);
		Map<String, Object> model = new HashMap<String, Object>();
		List<Object> trask = this.processManager.getFlowTrask(process.getId(), serviceId);
		model.put("flowxml", flowXml);
		model.put("subFlowXml", subFlowXmls);
		model.put("trask", trask);
		model.put("serviceId", process.getBusinessId());
		return new ModelAndView("pub/bpm/flexflow/FlowTrask", model);
	}

	/**
	 * 获取流程运行记录
	 * 
	 * @param processId
	 * @param serviceId
	 */
	@RequestMapping(value = "listTrack")
	@ResponseBody
	public Map<String, Object> listTrack(String processId, String serviceId) {
		List<Object> tks = this.processManager.getFlowTrask(processId, serviceId);
		return JsonWrapper.successWrapper(tks);
	}

	@RequestMapping("nextActivities")
	@ResponseBody
	public String nextActivities(String activityId, boolean forward, String dataBus) {
		if (StringUtil.isEmpty(activityId))
			throw new KhntException("参数错误，未提供当前环节！");

		JSONObject result = new JSONObject();
		result.put("success", true);

		Activity ca = this.activityManager.get(activityId);

		CurrentSessionUser user = SecurityUtil.getSecurityUser();

		if (forward) {
			boolean canFinish = flowEngine.checkActivityCanFinish(ca, ((CurrentBpmSessionUser) user).getBpmUser());
			if (!canFinish) {
				result.put("isMultiSign", true);
				return result.toString();
			}
		}

		// 如果环节是子流程节点，不可直接提交下一步，需要执行子流程
		if ("subFlow".equals(ca.getType())) {
			result.put("subFlow", true);
			return result.toString();
		}

		JSONObject dataBusJSON = StringUtil.isEmpty(dataBus) ? null : JSONObject.fromBean(dataBus);
		List<Activity> as = this.flowEngine.findNextActivities(activityId, forward, dataBus);
		JSONArray nt = new JSONArray();
		if (as != null && !as.isEmpty()) {
			for (Activity a : as) {
				JSONObject na = new JSONObject();
				na.put("name", a.getName());
				na.put("id", a.getActivityId());
				na.put("aid", a.getId());
				na.put("type", a.getType());
				na.put("signature", a.getSignature());
				na.put("function", a.getFunction());
				na.put("innerFlow", a.getInnerFlow());
				StringBuilder nextPersonNames = new StringBuilder();
				Set<Participator> aps = a.getParticipators();
				if (!aps.isEmpty()) {
					Iterator<Participator> ps = aps.iterator();
					Participator ap = null;
					while (ps.hasNext()) {
						ap = ps.next();
						if ((forward && ap.getDefined() || (!forward && !ap.getDefined())))
							break;
					}
					String ptype = ap.getParticipantType();
					na.put("participateType", ptype);
					na.put("participateRange", ap.getParticipantRange());
					na.put("chooseRolePerson", forward?ap.getChooseRolePerson():"0");
					na.put("participantId", ap.getParticipantId());
					if ("person".equals(ap.getParticipantType())) {
						nextPersonNames.append(",").append(ap.getParticipantName());
						while (ps.hasNext()) {
							Participator apt = ps.next();
							if ((forward && apt.getDefined() || (!forward && !apt.getDefined())))
								nextPersonNames.append(",").append(apt.getParticipantName());
						}
					} else if ("role".equals(ptype) || "position".equals(ptype)) {
						String role = ap.getParticipantId();
						String rangeType = ap.getParticipantRangeType();
						String range = null;
						if (forward && StringUtil.isEmpty(ap.getParticipantRange())) {
							if ("dep".equals(rangeType) || "dept".equals(rangeType)) {
								range = user.getDepartment().getId();
							} else if ("unit".equals(rangeType)) {
								range = user.getUnit().getId();
							} else if ("sunit".equals(rangeType)) {
								if (user.getUnit().getParent() != null) {
									range = user.getUnit().getParent().getId();
								}
							} else if ("databus".equals(rangeType)) {
								if (dataBusJSON != null && dataBusJSON.has(flowEngine.getDataBusRoleRangeKey())) {
									range = dataBusJSON.getString(flowEngine.getDataBusRoleRangeKey());
								} else if (a.getProcess().getDataBusJson().has(flowEngine.getDataBusRoleRangeKey())) {
									range = a.getProcess().getDataBusJson()
											.getString(flowEngine.getDataBusRoleRangeKey());
								}
							}
						} else {
							range = ap.getParticipantRange();
						}
						List<User> roleUsers;
						if ("role".equals(ptype))
							roleUsers = userManager.findRoleUser(role, range);
						else
							roleUsers = positionManager.getPositionUser(range);
						JSONArray nextPersons = new JSONArray();
						for (User u : roleUsers) {
							nextPersonNames.append(",").append(u.getName());
							JSONObject np = new JSONObject();
							np.put("id", u.getId());
							np.put("name", u.getName());
							nextPersons.put(np);
						}
						// 可直接从角色人员列表选择人员
						if ("1".equals(ap.getChooseRolePerson())) {
							na.remove("chooseRolePerson");
							na.put("chooseRolePerson", "1");
							na.put("nextPerson", nextPersons);
						}
					} else if ("databus".equals(ptype)) {
						if (dataBusJSON != null && dataBusJSON.has(flowEngine.getDataBusParticipantKey())) {
							nextPersonNames.append(dataBusJSON.getString(flowEngine.getDataBusParticipantKey()));
						} else if (a.getProcess().getDataBusJson().has(flowEngine.getDataBusParticipantKey())) {
							nextPersonNames.append(
									a.getProcess().getDataBusJson().getString(flowEngine.getDataBusParticipantKey()));
						}
					} else if ("startFlow".equals(ptype)) {
						nextPersonNames.append(a.getProcess().getCreator());
					}
				}
				if (nextPersonNames.length() > 0 && ",".equals(String.valueOf(nextPersonNames.charAt(0)))) {
					nextPersonNames.deleteCharAt(0);
				}
				na.put("nextPersonNames", nextPersonNames.toString());
				JSONObject pjson = new JSONObject();
				pjson.put("id", a.getProcess().getId());
				pjson.put("name", a.getProcess().getFlowName());
				if (a.getProcess().getParentActivity() != null) {
					pjson.put("paid", a.getProcess().getParentActivity().getActivityId());
					pjson.put("pid", a.getProcess().getParentActivity().getId());
					pjson.put("pname", a.getProcess().getParentActivity().getName());
				}
				na.put("process", pjson);
				nt.put(na);
			}
			JSONObject newDataBus = as.get(0).getProcess().getDataBusJson();
			result.put("dataBus", newDataBus);
		}
		result.put("data", nt);
		return result.toString();
	}

	/**
	 * 获取流程定义的第一环节的功能配置
	 * 
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFirstActivityConfig")
	@ResponseBody
	public Map<String, Object> getFlowFirstActivityConfig(String flowId) throws Exception {
		Activity a = definitionManager.parseFlowFirstActivity(flowId);
		return JsonWrapper.successWrapper(a);
	}

	@RequestMapping("startFlowProcess")
	@ResponseBody
	public Map<String, Object> startFlowProcess(String serviceId, String flowId, String serviceTitle, String typeCode,
			String dataBus) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.FLOW_DEFINITION_ID, flowId);
		paramMap.put(FlowExtParam.SERVICE_ID, serviceId);
		paramMap.put(FlowExtParam.SERVICE_TYPE, typeCode);
		paramMap.put(FlowExtParam.DATA_BUS, dataBus);
		paramMap.put(FlowExtParam.SERVICE_TITLE, serviceTitle);
		Map<String, Object> resultMap = this.flowExtManager.startFlowProcess(paramMap);
		return JsonWrapper.successWrapper(resultMap);
	}

	@RequestMapping("submitActivity")
	@ResponseBody
	public Map<String, Object> submitActivity(String activityId, String serviceTitle, String dataBus) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.ACTIVITY_ID, activityId);
		paramMap.put(FlowExtParam.DATA_BUS, dataBus);
		paramMap.put(FlowExtParam.SERVICE_TITLE, serviceTitle);
		Map<String, Object> resultMap = this.flowExtManager.submitActivity(paramMap);
		return JsonWrapper.successWrapper(resultMap);
	}

	@RequestMapping("returnedActivity")
	@ResponseBody
	public Map<String, Object> returnedActivity(String activityId, String serviceTitle, String dataBus)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.ACTIVITY_ID, activityId);
		paramMap.put(FlowExtParam.DATA_BUS, dataBus);
		paramMap.put(FlowExtParam.SERVICE_TITLE, serviceTitle);
		Map<String, Object> resultMap = this.flowExtManager.returnedActivity(paramMap);
		return JsonWrapper.successWrapper(resultMap);
	}

	@RequestMapping("recallActivity")
	@ResponseBody
	public Map<String, Object> recallActivity(String activityId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.ACTIVITY_ID, activityId);
		this.flowExtManager.recallActivity(paramMap);
		return JsonWrapper.successWrapper();
	}

	@RequestMapping("finishProcess")
	@ResponseBody
	public String finishProcess(String processId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.PROCESS_ID, processId);
		paramMap.put(FlowExtParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_COMMON);
		this.flowExtManager.finishProcess(paramMap);
		return "{\"success\":true}";
	}

	@RequestMapping("terminateProcess")
	@ResponseBody
	public String terminateProcess(String processId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(FlowExtParam.PROCESS_ID, processId);
		paramMap.put(FlowExtParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
		this.flowExtManager.finishProcess(paramMap);
		return "{\"success\":true}";
	}

	@RequestMapping("suspendActivity")
	@ResponseBody
	public String suspendActivity(String activityId) throws Exception {
		this.flowExtManager.suspendActivity(activityId);
		return "{\"success\":true}";
	}

	@RequestMapping("suspendProcess")
	@ResponseBody
	public String suspendProcess(String processId) throws Exception {
		this.flowExtManager.suspendProcess(processId);
		return "{\"success\":true}";
	}

	@RequestMapping("restartActivity")
	@ResponseBody
	public String restartActivity(String activityId) throws Exception {
		this.flowExtManager.restartActivity(activityId);
		return "{\"success\":true}";
	}

	@RequestMapping("restartProcess")
	@ResponseBody
	public String restartProcess(String processId) throws Exception {
		this.flowExtManager.restartProcess(processId);
		return "{\"success\":true}";
	}

}
