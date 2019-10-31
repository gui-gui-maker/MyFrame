package demo.bpm.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

import demo.bpm.bean.BpmServiceBean;
import demo.bpm.service.BpmServiceManager;

@Controller
@RequestMapping("/demo/bpm/bussiness")
public class BpmServiceContoller extends SpringSupportAction<BpmServiceBean, BpmServiceManager> {

	@Autowired
	private BpmServiceManager testServiceManager;
	@Autowired
	UserManagerImpl userManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, BpmServiceBean entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		entity.setCreaterId(user.getId());
		entity.setCreater(user.getName());
		entity.setCreateTime(new Date());
		entity.setState("0");
		return super.save(request, entity);
	}

	@RequestMapping("readyStart")
	@ResponseBody
	public Map<String, Object> readyStart(String personId) throws Exception {
		Map<String, String> ur = userManager.getUserRoles(personId);
		String personType;
		if (ur.containsValue("DeputyGeneralManager")) {
			personType = "dgm";
		}
		else if (ur.containsValue("DepartmentManager")) {
			personType = "dm";
		}
		else {
			personType = "worker";
		}
		Map<String, Object> rm = JsonWrapper.successWrapper();
		rm.put("personType", personType);
		return rm;
	}

	/**
	 * 启动审批流程
	 * 
	 * @param id
	 * @param flowId
	 * @param personType
	 * @param nextPersonId
	 * @param nextPerson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("start")
	@ResponseBody
	public Map<String, Object> startProcess(String id, String flowId, String personType, String nextPersonId,
			String nextPerson) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		this.testServiceManager.startProecess(id, flowId, personType, nextPersonId, nextPerson, user.getDepartment()
				.getId());
		return JsonWrapper.successWrapper();
	}

	/**
	 * 
	 * 保存表单数据，并提交审批流程。
	 * 
	 * @param serviceBean
	 * @param flowId
	 * @param personType
	 * @param nextPersonId
	 * @param nextPerson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAndSubmit")
	@ResponseBody
	public Map<String, Object> saveAndSubmit(BpmServiceBean serviceBean, String flowId, String personType,
			String nextPersonId, String nextPerson) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		serviceBean.setCreaterId(user.getId());
		serviceBean.setCreater(user.getName());
		serviceBean.setCreateTime(new Date());
		this.testServiceManager.saveAndStartProecess(serviceBean, flowId, personType, nextPersonId, nextPerson, user
				.getDepartment().getId());
		return JsonWrapper.successWrapper();
	}

	/**
	 * 提交审批
	 * 
	 * @param id
	 *            业务id
	 * @param activityId
	 *            审批环节id
	 * @param dataBus
	 *            数据总线参数json格式
	 * @param signature
	 *            审批意见
	 * @param processId
	 *            流程实例id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submit")
	@ResponseBody
	public Map<String, Object> submit(String id, String activityId, String dataBus, String signature, String processId)
			throws Exception {
		this.testServiceManager.submitApprove(activityId, dataBus, signature, processId);
		return JsonWrapper.successWrapper();
	}

	/**
	 * 退回申请
	 * 
	 * @param dataBus数据总线参数json格式
	 * @param activityId
	 *            审批环节id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("turnback")
	@ResponseBody
	public Map<String, Object> turnback(String dataBus, String activityId) throws Exception {
		testServiceManager.turnback(activityId, dataBus);
		return JsonWrapper.successWrapper();
	}

	@RequestMapping("finish")
	@ResponseBody
	public Map<String, Object> finish(String flowId, String activityId) throws Exception {
		testServiceManager.finish(flowId);
		return JsonWrapper.successWrapper();
	}

}
