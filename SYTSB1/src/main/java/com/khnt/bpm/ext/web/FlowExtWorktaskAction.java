package com.khnt.bpm.ext.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.bean.FlowProcessWorktask;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.worktask.bean.Worktask;
import com.khnt.pub.worktask.service.WorktaskManager;
import com.khnt.security.support.CurrentBpmSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 工作流引擎任务扩展-工作任务处理controller
 * 
 * 此controller提供了流程工作任务处理的入口
 * 
 * @author 邹洪平 2013-5-15
 * 
 */
@Controller
@RequestMapping("/bpm/ext/worktask/")
public class FlowExtWorktaskAction {

	public transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ActivityManager activityManager;

	@Autowired
	private FlowEngine flowEngine;

	@Autowired
	FlowServiceConfigManager flowServiceConfigManager;

	@Autowired
	WorktaskManager worktaskManager;

	/**
	 * 工作流任务集中处理分派器。
	 * 
	 * 这是处理来自Worktsk的工作任务，将会优先查询环节表单配置，如果环节未配置表单, 则通过查找工作流任务处理配置来识别不同业务流程的业务URL地址，
	 * 然后自动跳转到业务URL执行业务操作。
	 * 
	 * @param request
	 * @param response
	 * @param serviceId
	 *            工作任务中的业务ID，在这里其代表activityId
	 * @param status
	 *            任务状态
	 * @throws Exception
	 * @throws ServletException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("doBpmWorktask")
	public void doBpmWorktask(HttpServletRequest request, HttpServletResponse response, String serviceId, String status)
			throws Exception {
		String activityId = serviceId;
		boolean isDeal = StringUtil.isEmpty(status) ? true : WorktaskManager.STATUS_PROCESSED.equals(status);
		Map<String, Object> dataMap = this.getBpmDataMap(activityId);
		Object urlObj = isDeal ? dataMap.get("viewUrl") : dataMap.get("dealUrl");

		if (urlObj == null || StringUtil.isEmpty(urlObj.toString())) {
			log.error("该流程未配置业务" + (isDeal ? "浏览" : "处理") + "表单！");
			throw new KhntException("未找到合适的业务操作页面！");
		}

		for (String key : dataMap.keySet()) {
			request.setAttribute(key, dataMap.get(key));
		}

		String url = urlObj.toString();

		// 将总线数据放入request供业务处理中使用
		if (dataMap.get("dataBus") != null) {
			Map<String, Object> dataBus = (Map<String, Object>) dataMap.get("dataBus");
			for (String key : dataBus.keySet()) {
				request.setAttribute(key, dataBus.get(key));
			}
		}

		if (!url.startsWith("/"))
			url = "/" + url;

		if (dataMap.get("parameter") != null) {
			Map<String, Object> parameter = (Map<String, Object>) dataMap.get("parameter");
			StringBuffer paramStr = new StringBuffer();
			for (String key : parameter.keySet()) {
				paramStr.append("&").append(key).append("=").append(parameter.get(key));
			}
			if (paramStr.length() > 0) {
				paramStr.setCharAt(0, url.contains("?") ? '&' : '?');
				url += paramStr.toString();
			}
		}

		CurrentBpmSessionUser user = (CurrentBpmSessionUser) SecurityUtil.getSecurityUser();

		// 浏览状态下，检查是否可撤回
		if (isDeal) {
			boolean recallable = this.flowEngine.checkActivityRecallEnable(activityId, user.getBpmUser());
			request.setAttribute("recallable", recallable);
			
		}

		// 将状态返给业务
		request.setAttribute("worktaskStatus", status);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * 获取指定工作流任务的实际业务数据
	 * 
	 * @param worktaskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBpmData")
	@ResponseBody
	public Map<String, Object> getBpmData(String worktaskId) throws Exception {
		Worktask worktask = this.worktaskManager.get(worktaskId);
		if (worktask == null)
			return JsonWrapper.failureWrapperMsg("未找到工作任务！");
		Map<String, Object> dataMap = this.getBpmDataMap(worktask.getServiceId());
		// 环节上未设置对应表单的则从流程整体设置中获取表单
		return JsonWrapper.successWrapper(dataMap);
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> getBpmDataMap(String activityId) throws Exception {
		Activity activity = activityManager.get(activityId);
		if (activity == null) {
			log.error("找不到指定的流程环节(ID=" + activityId + ")");
			throw new KhntException("无法获取流程环节！");
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 将环节功能、环节名称暴露给业务
		dataMap.put("function", activity.getFunction());
		dataMap.put("activityName", activity.getName());

		Process processImpl = activity.getProcess();

		// 获取环节处理表单配置，根据任务状态不同，分别取得办理表单(未处理)和浏览表单(已处理)
		// 环节上未设置对应表单的则从流程整体设置中获取表单
		String viewUrl = StringUtil.isNotEmpty(activity.getViewUrl()) ? activity.getViewUrl()
				: StringUtil.isNotEmpty(processImpl.getViewForm()) ? processImpl.getViewForm() : null;
		String dealUrl = StringUtil.isNotEmpty(activity.getProcessUrl()) ? activity.getProcessUrl()
				: StringUtil.isNotEmpty(processImpl.getDealForm()) ? processImpl.getDealForm() : null;

		dataMap.put("viewUrl", viewUrl);
		dataMap.put("dealUrl", dealUrl);

		// 将环节ID，流程实例ID，业务ID放入request供业务处理使用
		dataMap.put("activityId", activity.getId());
		dataMap.put("processId", processImpl.getId());

		dataMap.put("serviceId", processImpl.getBusinessId());
		// 流程业务id使用businessId代替servcieId，避免和worktask的serviceId混淆
		dataMap.put("businessId", processImpl.getBusinessId());
		String dataBus = processImpl.getDataBus();

		// 将总线数据放入request供业务处理中使用
		if (!StringUtil.isEmpty(dataBus)) {
			JSONObject jsonDataBus = JSONObject.fromString(dataBus);
			Map<String, Object> processDataBus = new HashMap<String, Object>();
			Iterator<Object> jdbi = jsonDataBus.keys();
			while (jdbi.hasNext()) {
				Object key = jdbi.next();
				processDataBus.put(key.toString(), jsonDataBus.get(key.toString()));
			}
			dataMap.put("dataBus", processDataBus);
		}
		if ("end".equals(activity.getType()))
			dataMap.put("endFlow", "true");

		String processParameter = null;
		try {
			FlowProcessWorktask flowProcessWorktask = this.flowServiceConfigManager
					.getWorktaskType(processImpl.getId());
			processParameter = flowProcessWorktask.getParameter();
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

		if (StringUtil.isNotEmpty(processParameter)) {
			JSONObject jsonParam = JSONObject.fromString(processParameter);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Iterator<Object> itr = jsonParam.keys();
			while (itr.hasNext()) {
				Object key = itr.next();
				paramMap.put(key.toString(), jsonParam.get(key.toString()));
			}
			dataMap.put("parameter", paramMap);
		}
		return dataMap;
	}
}
