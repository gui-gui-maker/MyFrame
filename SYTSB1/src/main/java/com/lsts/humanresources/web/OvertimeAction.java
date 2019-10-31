package com.lsts.humanresources.web;

import com.alibaba.fastjson.JSON;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.humanresources.bean.Overtime;
import com.lsts.humanresources.service.OvertimeManager;
import com.lsts.log.service.SysLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("overtimeAction")
public class OvertimeAction extends SpringSupportAction<Overtime, OvertimeManager> {

    @Autowired
    private OvertimeManager  overtimeManager;
    @Autowired
    private SysLogService logService;
    @Override
    public HashMap<String, Object> save(HttpServletRequest request, Overtime overtime) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				overtime.setCreateDate(new Date());
				overtime.setCreateById(curUser.getId());
				overtime.setCreateBy(curUser.getName());
			}else if(pageStatus.equals("modify")) {
				overtime.setLastmodifyDate(new Date());
				overtime.setLastmodifyById(curUser.getId());
				overtime.setLastmodifyBy(curUser.getName());
			}
			overtimeManager.save(overtime);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(overtime);
	}
    /**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
	   		String typeCode, String status,String activityId) throws Exception {
	    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	   		Map<String, Object> map = new HashMap<String, Object>();
	   		//流程传参
	   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "加班申请 -"+user.getName());
	   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
	   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	   		if (StringUtil.isEmpty(id)) {
	   			return JsonWrapper.failureWrapper("参数错误！");
	   		} else {
	   			// 启动流程
	   			if (StringUtil.isNotEmpty(flowId)) {
	   				overtimeManager.doStartPress(map);
	   				//改变状态
	   				Overtime overtime=overtimeManager.get(id);
	   				overtime.setStatus(overtimeManager.OVERTIME_FLOW_BMFZR);
	   				overtimeManager.save(overtime);
	   			} else {
	   				return JsonWrapper.failureWrapper("流程ID为空！");
	   			}
	   			return JsonWrapper.successWrapper(id);
	   		}

	   	}
	/**
  	 * 审核通过
  	 * @param id
	 * @param typeCode
	 * @param activityId
	 * @param areaFlag
	 * @return
	 * @throws Exception
  	 * */
    @RequestMapping(value = "shtg")
 	@ResponseBody
 	public HashMap<String, Object> shtg(ServletRequest request,String areaFlag,String id, String flowId,
			String activityId,String typeCode) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		Map<String, Object> map = new HashMap<String, Object>();
 		Overtime overtime=overtimeManager.get(id);
 		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "加班申请");
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
 		if (StringUtil.isEmpty(id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				try {
					overtimeManager.doProcess(map);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 				//改变状态
 				if(areaFlag.equals("2")){
	   				overtime.setStatus(overtimeManager.OVERTIME_FLOW_FGLD);
 				}else if(areaFlag.equals("3")){
 					overtime.setStatus(overtimeManager.OVERTIME_FLOW_SHTG);
 				}
 				overtimeManager.save(overtime);
 			} else {
 				return JsonWrapper.failureWrapper("流程ID为空！");
 			}
 			return JsonWrapper.successWrapper(id);
 		}
 	}
    /**
  	 * 审核不通过
  	 **/
  	  @RequestMapping(value = "shbtg")
  	  @ResponseBody
  	  public HashMap<String, Object> shbtg(ServletRequest request,String areaFlag,String id, String flowId,
  			String activityId,String typeCode,String processId)throws Exception {
	  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
	 		Map<String, Object> map = new HashMap<String, Object>();
	 		Map<String, Object> map1 = new HashMap<String, Object>();
	 		Overtime overtime=overtimeManager.get(id);
	 		//流程传参
	 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "加班申请");
	 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
     		
     		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
  			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
     		if (StringUtil.isEmpty(id)) {
     			return JsonWrapper.failureWrapper("参数错误！");
     		} else {
     			// 结束流程
     			if (StringUtil.isNotEmpty(activityId)) {
     				overtimeManager.stop(map1);
     				//改变状态
     				overtime.setStatus(overtimeManager.OVERTIME_FLOW_SHBTG);
     				overtimeManager.save(overtime);
     			} else {
     				return JsonWrapper.failureWrapper("流程ID为空！");
     			}

     			return JsonWrapper.successWrapper(id);
     		}
  	}
}