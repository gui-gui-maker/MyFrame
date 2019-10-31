package com.lsts.humanresources.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.humanresources.bean.Overtime;
import com.lsts.humanresources.bean.Subsidy;
import com.lsts.humanresources.service.OvertimeManager;
import com.lsts.humanresources.service.SubsidyManager;


@Controller
@RequestMapping("subsidyAction")
public class SubsidyAction extends SpringSupportAction<Subsidy, SubsidyManager> {

    @Autowired
    private SubsidyManager  subsidyManager;
    @Autowired
    private OvertimeManager  overtimeManager;
    
    /**
     * 根据加班申请生成加班补助通知单
     * @param request
     * @param fkOvertimeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "createSubsidy")
	@ResponseBody
	public HashMap<String, Object> createSubsidy(ServletRequest request,String fkOvertimeId) throws Exception {
    	CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
    	Subsidy subsidy = subsidyManager.getDetail(fkOvertimeId);
    	Overtime overtime = overtimeManager.get(fkOvertimeId);
    	try {
    		if(StringUtil.isEmpty(subsidy.getFkOvertimeId())){
    			subsidy.setSubsidyStatus("WTJ");
    		}
			subsidy.setFkOvertimeId(fkOvertimeId);
			subsidy.setOvertimeWorkerId(overtime.getApplicantId());
			subsidy.setOvertimeWorker(overtime.getApplicant());
			subsidy.setOvertimeType(overtime.getOvertimeType());
			subsidy.setCreateById(curUser.getId());
			subsidy.setCreateBy(curUser.getName());
			subsidy.setCreateDate(new Date());
			subsidyManager.save(subsidy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
    	return JsonWrapper.successWrapper(subsidy);
    }
    
    /**
     * 根据加班申请外键获取加班补助通知单详情
     * @param request
     * @param fkOvertimeId
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(ServletRequest request,String fkOvertimeId) throws Exception {
    	Subsidy subsidy = new Subsidy();
    	try {
    		subsidy = subsidyManager.getDetail(fkOvertimeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取加班补助通知单信息失败，请重试！");
		}
    	return JsonWrapper.successWrapper(subsidy);
    }
	
	/**
	 * 提交加班补助通知单
	 * @param request
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(ServletRequest request,String id, String flowId,
	   		String typeCode, String status,String activityId,Subsidy subsidy) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		//流程传参
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "加班补助通知单");
   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 启动流程
   			if (StringUtil.isNotEmpty(flowId)) {
   				subsidyManager.doStartPress(map);
   				//改变状态
   				subsidy.setSubsidyStatus(subsidyManager.OVERTIME_FLOW_BMFZR);
   				subsidyManager.save(subsidy);
   			} else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}
   			return JsonWrapper.successWrapper(id);
   		}
	}
	
}