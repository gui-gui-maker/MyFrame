package com.lsts.qualitymanage.web;

import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.qualitymanage.bean.QualityUpdateDestroy;
import com.lsts.qualitymanage.service.QualityUpdateDestroyManager;
import com.lsts.qualitymanage.service.QualityUpdateFileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;












import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("update/destroy")
public class QualityUpdateDestroyAction extends SpringSupportAction<QualityUpdateDestroy, QualityUpdateDestroyManager> {

    @Autowired
    private QualityUpdateDestroyManager  qualityUpdateDestroyManager;
    @Autowired
    private QualityUpdateFileManager  qualityUpdateFileManager;
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @Override
   	public HashMap<String, Object> save(HttpServletRequest request,QualityUpdateDestroy qualityUpdateDestroy) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		//获取当前登录人姓名
   		qualityUpdateDestroy.setRegistrant(user.getName());
   		qualityUpdateDestroy.setRegistrantId(user.getId());
   		qualityUpdateDestroy.setRegistrantTime(new Date());
   		//改变状态
   		qualityUpdateDestroy.setStatus(qualityUpdateDestroyManager.ZL_XHSQ_WTJ);
   		return super.save(request, qualityUpdateDestroy);

   	}
    
    
    /**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "xhtj")
	@ResponseBody
	public HashMap<String, Object> xhtj(ServletRequest request,String id, String flowId,
			String typeCode, String status,String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//流程传参
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 启动流程
			if (StringUtil.isNotEmpty(flowId)) {
				qualityUpdateFileManager.doStartPress(map);
				//改变状态
				QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
				qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_FGYLD);
				qualityUpdateDestroyManager.save(qualityUpdateDestroy);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
	
	/**审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @param httpServletRequest 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "xhsh")
	@ResponseBody
	public HashMap<String, Object> xhsh(String areaFlag,String id,
			String typeCode, String status,String activityId,ServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("1")){
					qualityUpdateFileManager.doProcess(map);
					QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
					qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_DWFZR);
					qualityUpdateDestroy.setFgyld(user.getName());
					qualityUpdateDestroy.setFgyldTime(new Date());
					qualityUpdateDestroy.setFgyldyj("通过");
					qualityUpdateDestroyManager.save(qualityUpdateDestroy);
				}else if(areaFlag.equals("2")){
					QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
					qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_PASS);
					qualityUpdateDestroy.setDwfzr(user.getName());
					qualityUpdateDestroy.setDwfzrTime(new Date());
					qualityUpdateDestroy.setDwfzryj("通过");
					qualityUpdateDestroyManager.save(qualityUpdateDestroy);
					qualityUpdateFileManager.doProcess(map);
				}else{
					QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
					//qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_NO_PASS);
					qualityUpdateDestroyManager.save(qualityUpdateDestroy);
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

	}
	/**退回审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 * 已登记，已提交，审核中，审核通过，审核不通过
	 */
	@RequestMapping(value = "noxhsh")
	@ResponseBody
	public HashMap<String, Object> noxhsh(String areaFlag,String id,
			String typeCode, String status,String activityId,String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();

		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

   		CurrentSessionUser user = SecurityUtil.getSecurityUser();

		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 结束流程
			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("1")){
					qualityUpdateFileManager.stop(map1);
					QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
					qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_NO_PASS);
					qualityUpdateDestroy.setFgyld(user.getName());
					qualityUpdateDestroy.setFgyldTime(new Date());
					qualityUpdateDestroy.setFgyldyj("未通过");
					qualityUpdateDestroyManager.save(qualityUpdateDestroy);
				}else if(areaFlag.equals("2")){
					qualityUpdateFileManager.stop(map1);
					QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
					qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_NO_PASS);
					qualityUpdateDestroy.setDwfzr(user.getName());
					qualityUpdateDestroy.setDwfzrTime(new Date());
					qualityUpdateDestroy.setDwfzryj("未通过");
					qualityUpdateDestroyManager.save(qualityUpdateDestroy);
				}
//				qualityUpdateFileManager.stop(map1);
//				QualityUpdateDestroy qualityUpdateDestroy=qualityUpdateDestroyManager.get(id);
//				qualityUpdateDestroy.setStatus(QualityUpdateDestroyManager.ZL_XHSQ_NO_PASS);
//				qualityUpdateDestroyManager.save(qualityUpdateDestroy);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
	
}