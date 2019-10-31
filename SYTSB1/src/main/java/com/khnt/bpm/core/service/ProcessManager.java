package com.khnt.bpm.core.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.bean.Trask;
import com.khnt.bpm.core.dao.ProcessDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;

@Service("processManager")
public class ProcessManager extends EntityManageImpl<Process, ProcessDao> {

	@Autowired
	private ProcessDao processDao;

	/**
	 * 获取指定业务的流程，且该流程是非子流程
	 * 
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	public Process getServiceProcess(String businessId) {
		List<Process> processList = this.processDao
				.listQuery("from Process where businessId=? and parentActivity.id = null", businessId);
		if (processList.size() > 1)
			throw new KhntException("找到与该业务相关的多个流程实例");
		else if (processList.size() == 0)
			throw new KhntException("未找到与该业务相关流程实例");
		return processList.get(0);
	}

	/**
	 * 将实例流程的所有环节分类别解析出来，放入到Map返回给客户端。Map中包含：
	 * 
	 * start 启动节点; end 结束节点; task 聚合;
	 * 
	 * @param process
	 * @return
	 */
	protected Map<String, Object> parsePorcessActivity(Process process) {
		Map<String, Object> parseMap = new HashMap<String, Object>();
		List<Activity> taskActivities = new ArrayList<Activity>();
		for (Activity a : process.getActivitys()) {
			if ("start".equals(a.getType()))
				parseMap.put("start", a);
			else if ("end".equals(a.getType()))
				parseMap.put("end", a);
			else
				taskActivities.add(a);
		}
		parseMap.put("task", taskActivities);
		return parseMap;
	}

	public List<Object> getFlowTrask(String processId, String serviceId) {
		if (processId == null)
			processId = this.processDao.findUniqueBy("businessId", serviceId).getId();
		return this.processDao.findTraskes(processId);
	}

	/**
	 * 获取流程所有的子流程实例运行状态xml描述信息
	 * 
	 * @param processId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> traskSubProcess(String processId) throws Exception {
		Process process = processDao.get(processId);
		return this.traskSubProcess(process);
	}

	/**
	 * 获取流程所有的子流程实例运行状态xml描述信息
	 * 
	 * @param process
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> traskSubProcess(Process process) throws Exception {
		Map<String, String> trackXmls = new HashMap<String, String>();
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		for (Activity a : process.getActivitys()) {
			if (a.getSubProcess() != null && !a.getSubProcess().isEmpty()) {
				for (Process sp : a.getSubProcess()) {
					String key = sp.getParentActivity().getName() + "[" + sp.getCreator() + "]"
							+ (sp.getCreatorDate() != null ? df.format(sp.getCreatorDate()) : null);
					trackXmls.put(key, this.traskProcess(sp));
				}
			}
		}
		return trackXmls;
	}

	/**
	 * 获取流程实例运行状态xml描述信息
	 * 
	 * @param processId
	 * @return
	 * @throws Exception
	 */
	public String traskProcess(String processId) throws Exception {
		Process process = processDao.get(processId);
		return this.traskProcess(process);
	}

	/**
	 * 获取流程实例运行状态xml描述信息
	 * 
	 * @param process
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String traskProcess(Process process) throws Exception {
		InputStream inputStream = new ByteArrayInputStream(process.getFlowxml().getBytes("UTF-8"));
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(inputStream);
		Element tagProcess = doc.getRootElement();
		Element tagStart = (Element) tagProcess.elements("start").get(0);
		tagStart.addAttribute("info", "【" + process.getCreator() + "】于" + process.getCreatorDate() + "启动当前流程");
		tagStart.addAttribute("state", "500");
		// 普通环节
		log.debug("设置普通环节环节的提示信息");
		List<Element> taskList = tagProcess.elements("task");
		for (Element element : taskList) {
			Activity activity = getActivity(process, element.attributeValue("id"));
			String info = "";
			List<Trask> list = getTrask(process.getId(), activity.getId());
			if (list != null) {
				for (int i = 1; i <= list.size(); i++) {
					info += i + "、" + DateUtil.getDateTime(list.get(i - 1).getExeDate())
							+ (list.get(i - 1).getPerson() == null ? "" : ("【" + list.get(i - 1).getPerson()) + "】")
							+ list.get(i - 1).getAction() + "\\r";
				}
			}
			element.addAttribute("info", info);
			element.addAttribute("state", activity.getState());
		}
		// 分支环节
		log.debug("设置分支环节的提示信息");
		List<Element> foreachList = tagProcess.elements("foreach");
		for (Element element : foreachList) {
			Activity activity = getActivity(process, element.attributeValue("id"));
			String info = "";
			List<Trask> list = getTrask(process.getId(), activity.getId());
			if (list != null) {
				for (int i = 1; i <= list.size(); i++) {
					info += i + "、" + DateUtil.getDateTime(list.get(i - 1).getExeDate()) + " "
							+ (list.get(i - 1).getPerson() == null ? "" : ("【" + list.get(i - 1).getPerson()) + "】")
							+ list.get(i - 1).getAction() + "\\r";
				}
			}
			element.addAttribute("info", info);
			element.addAttribute("state", activity.getState());
		}
		// 聚合环节
		log.debug("设置聚合环节的提示信息");
		List<Element> joinList = tagProcess.elements("join");
		for (Element element : joinList) {
			Activity activity = getActivity(process, element.attributeValue("id"));
			String info = "";
			List<Trask> list = getTrask(process.getId(), activity.getId());
			if (list != null) {
				for (int i = 1; i <= list.size(); i++) {
					info += i + "、" + DateUtil.getDateTime(list.get(i - 1).getExeDate()) + " "
							+ (list.get(i - 1).getPerson() == null ? "" : ("【" + list.get(i - 1).getPerson()) + "】")
							+ list.get(i - 1).getAction() + "\\r";
				}
			}
			element.addAttribute("info", info);
			element.addAttribute("state", activity.getState());
		}
		// 子流程
		log.debug("设置普通环节环节的提示信息");
		List<Element> subflowList = tagProcess.elements("subFlow");
		for (Element element : subflowList) {
			Activity activity = getActivity(process, element.attributeValue("id"));
			String info = "";
			List<Trask> list = getTrask(process.getId(), activity.getId());
			if (list != null) {
				for (int i = 1; i <= list.size(); i++) {
					info += i + "、" + DateUtil.getDateTime(list.get(i - 1).getExeDate())
							+ (list.get(i - 1).getPerson() == null ? "" : ("【" + list.get(i - 1).getPerson()) + "】")
							+ list.get(i - 1).getAction() + "\\r";
				}
			}
			element.addAttribute("info", info);
			element.addAttribute("state", activity.getState());
		}
		// 结束环节
		log.debug("设置结束环节的提示信息");
		List<Element> endList = tagProcess.elements("end");
		for (Element element : endList) {
			Activity activity = getActivity(process, element.attributeValue("id"));
			String info = "";
			List<Trask> list = getTrask(process.getId(), activity.getId());
			if (list != null) {
				for (int i = 1; i <= list.size(); i++) {
					info += i + "、" + DateUtil.getDateTime(list.get(i - 1).getExeDate()) + " "
							+ (list.get(i - 1).getPerson() == null ? "" : ("【" + list.get(i - 1).getPerson()) + "】")
							+ list.get(i - 1).getAction() + "\\r";
				}
			}
			element.addAttribute("info", info);
			element.addAttribute("state", activity.getState());
		}

		/*
		 * Set<Transition> transitions = process.getTransitions(); for (Element
		 * tte : ttes) { for (Transition tt : transitions) { if (tt.getPassed()
		 * && tt.getFromId().equals(tte.attribute("fromId").getText()) &&
		 * tt.getToid().equals(tte.attribute("toId").getText())) {
		 * tte.addAttribute("passed", "true"); } } }
		 */

		String flowxml = doc.asXML();
		if (StringUtil.isEmpty(flowxml))
			return "";
		Pattern p = Pattern.compile("\r|\n");
		Matcher m = p.matcher(flowxml);
		return m.replaceAll("");
	}

	/**
	 * 根据流程实例ID 与流程环节定义ID得到对应流程实例活动环节
	 * 
	 * @param flowProcessId
	 *            流程实例ID
	 * @param nodeId
	 *            流程环节定义ID
	 * @return
	 * @throws Exception
	 */
	private Activity getActivity(Process process, String nodeId) throws Exception {
		Activity r = null;
		for (Activity a : process.getActivitys()) {
			if (a.getActivityId().equals(nodeId)) {
				r = a;
				break;
			}
		}
		return r;
	}

	/**
	 * 根据流程实例ID与对应活动事项的ID 得到当前活动的日志
	 * 
	 * @param processId
	 *            流程实例ID
	 * @param activityId
	 *            活动事项的ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Trask> getTrask(String processId, String activityId) {
		String hql = "from Trask where processId=? and  activitId=?  order by exeDate ";
		Query q = processDao.createQuery(hql, processId, activityId);
		return q.list();
	}

}
