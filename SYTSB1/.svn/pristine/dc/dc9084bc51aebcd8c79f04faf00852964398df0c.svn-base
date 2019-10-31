package com.lsts.humanresources.web;

import com.alibaba.fastjson.JSON;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.humanresources.bean.MultipleDeclare;
import com.lsts.humanresources.service.MultipleDeclareManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("multiple/declare")
public class MultipleDeclareAction extends SpringSupportAction<MultipleDeclare, MultipleDeclareManager> {

	private Logger logger = LoggerFactory.getLogger(MultipleDeclareAction.class);
    @Autowired
    private MultipleDeclareManager  multipleDeclareManager;
    @Autowired
	private AttachmentManager attachmentManager;
    @Autowired
	private ActivityManager activityManager;
    @Autowired
	private ProcessManager processManager;
    /**
	 * 保存
	 * 
	 * @param request
	 * @param multipleDeclare
	 * @throws Exception
	 */
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, MultipleDeclare multipleDeclare) throws Exception {
    	String uploadFiles = request.getParameter("uploadFiles");
    	String [] uploadFilesArr = null;
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				multipleDeclare.setCreateId(curUser.getId());
				multipleDeclare.setCreateBy(curUser.getName());
				multipleDeclare.setCreateDate(new Date());
			}else if(pageStatus.equals("modify")) {
				multipleDeclare.setLastModifyId(curUser.getId());
				multipleDeclare.setLastModifyBy(curUser.getName());
				multipleDeclare.setLastModifyDate(new Date());
			}
			multipleDeclareManager.save(multipleDeclare);
			if(StringUtil.isNotEmpty(uploadFiles)) {
	    		uploadFilesArr = uploadFiles.split(",");
	    		this.attachmentManager.setAttachmentBusinessId(uploadFilesArr,multipleDeclare.getId());
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(multipleDeclare);
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
	   		if (StringUtil.isEmpty(id)) {
	   			return JsonWrapper.failureWrapper("参数错误！");
	   		} else {
	   			MultipleDeclare multipleDeclare=multipleDeclareManager.get(id);
	   			if(multipleDeclare.getStatus().equals("YTH")) {
	   				List<Activity> currentServiceActivitys = activityManager.getCurrentServiceActivity(id);
	   				map.put(FlowExtWorktaskParam.ACTIVITY_ID, currentServiceActivitys.get(0).getId());
	   				multipleDeclareManager.doProcess(map);
	   				multipleDeclare.setStatus(multipleDeclareManager.DECLARE_FLOW_YTJ);
	   				multipleDeclareManager.save(multipleDeclare);
	   			}else {
		   			if (StringUtil.isNotEmpty(flowId)) {
		   				//流程传参
		   		   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		   		   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		   		   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "个人岗位级别申报 -"+user.getName());
		   		   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		   		   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		   		   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		   		   		// 启动流程
		   				multipleDeclareManager.doStartPress(map);
		   				//改变状态
		   				multipleDeclare.setStatus(multipleDeclareManager.DECLARE_FLOW_YTJ);
		   				multipleDeclareManager.save(multipleDeclare);
		   			} else {
		   				return JsonWrapper.failureWrapper("流程ID为空！");
		   			}
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
 		String multipleDeclare=request.getParameter("multipleDeclare").toString();
 		MultipleDeclare multipleDeclare1=JSON.parseObject(multipleDeclare, MultipleDeclare.class);
 		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "个人岗位级别申报 -"+multipleDeclare1.getEmpName());
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
 		if (StringUtil.isEmpty(id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				multipleDeclareManager.doProcess(map);
 				//改变状态
 				if(areaFlag.equals("2") || areaFlag.equals("3")){
 					
 					multipleDeclare1.setStatus(multipleDeclareManager.DECLARE_FLOW_SHZ);
 				}else if(areaFlag.equals("4")){
 					multipleDeclare1.setStatus(multipleDeclareManager.DECLARE_FLOW_SHTG);
 				}
 				multipleDeclareManager.save(multipleDeclare1);
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
	 		String multipleDeclare=request.getParameter("multipleDeclare").toString();
	 		MultipleDeclare multipleDeclare1=JSON.parseObject(multipleDeclare, MultipleDeclare.class);
	 		//流程传参
	 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "个人岗位级别申报 -"+multipleDeclare1.getEmpName());
	 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
     		
     		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
  			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
     		if (StringUtil.isEmpty(id)) {
     			return JsonWrapper.failureWrapper("参数错误！");
     		} else {
     			// 结束流程
     			if (StringUtil.isNotEmpty(activityId)) {
     				multipleDeclareManager.stop(map1);
     				//改变状态
     				multipleDeclare1.setStatus(multipleDeclareManager.DECLARE_FLOW_SHBTG);
     				multipleDeclareManager.save(multipleDeclare1);
     			} else {
     				return JsonWrapper.failureWrapper("流程ID为空！");
     			}

     			return JsonWrapper.successWrapper(id);
     		}
  	}
  	
  	/**
  	 * 退回申请
  	 * @param request
  	 * @param activityId
  	 * @param businessId
  	 * @return
  	 */
  	@RequestMapping("turnback")
	@ResponseBody
  	public Map<String,Object> turnback(HttpServletRequest request,String activityId, String id) {
  	  	try {
  	  		Map<String, Object> paramMap = new HashMap<String, Object>();
  	  
  	  		// ------------- 必要参数 -----------------------
  	  		paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
  	  		
  	  		multipleDeclareManager.returnedActivity(paramMap);
  	  		MultipleDeclare multipleDeclare = multipleDeclareManager.get(id);
  	  		multipleDeclare.setDirectorDate(null);
  	  		multipleDeclare.setDirectorId(null);
  	  		multipleDeclare.setDirectorName(null);
  	  		multipleDeclare.setDirectorOpinion(null);
  	  		multipleDeclare.setOfficeDirectorDate(null);
  	  		multipleDeclare.setOfficeDirectorId(null);
  	  		multipleDeclare.setOfficeDirectorName(null);
  	  		multipleDeclare.setOfficeDirectorOpinion(null);
  	  		multipleDeclare.setStatus("YTH");
  	  		multipleDeclareManager.save(multipleDeclare);
			return JsonWrapper.successWrapper(multipleDeclare);
		} catch (Exception e) {
  	  		return JsonWrapper.failureWrapperMsg("退回失败!");
		}
	}
  	
  	/**
  	 * 获取附件信息
  	 * @param businessId
  	 * @return
  	 */
  	@RequestMapping("getAttachment")
	@ResponseBody
  	public Map<String,Object> getAttachment(String businessId) {
  	  	try {
  	  		this.logger.info("获取综合部门岗位申报附件...");
			List<Attachment> list = this.attachmentManager.getBusAttachment(businessId);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
  	  		return JsonWrapper.failureWrapperMsg("获取附件失败");
		}
	}
  	
  	/**
  	 * 人事审核批量处理
  	 * @param request
  	 * @param id
  	 * @return
  	 * @throws Exception
  	 */
	@RequestMapping(value = "bathDeal")
	@ResponseBody
	public HashMap<String, Object> bathDeal(ServletRequest request,String ids,String dealResult) throws Exception {
	   		try {
				if (StringUtil.isEmpty(ids)) {
					return JsonWrapper.failureWrapper("参数错误！");
				} else {
					String[] idArr = ids.split(",");
					for(String id : idArr) {
						Map<String, Object> map = new HashMap<String, Object>();
						MultipleDeclare multipleDeclare=multipleDeclareManager.get(id);
						if("Y".contentEquals(dealResult)) {
							List<Activity> currentServiceActivitys = activityManager.getCurrentServiceActivity(id);
							map.put(FlowExtWorktaskParam.ACTIVITY_ID, currentServiceActivitys.get(0).getId());
							multipleDeclareManager.doProcess(map);
							multipleDeclare.setStatus(multipleDeclareManager.DECLARE_FLOW_SHTG);
						}else if("R".contentEquals(dealResult)) {
							List<Activity> currentServiceActivitys = activityManager.getCurrentServiceActivity(id);
							map.put(FlowExtWorktaskParam.ACTIVITY_ID, currentServiceActivitys.get(0).getId());
							multipleDeclareManager.returnedActivity(map);
							multipleDeclare.setStatus("YTH");
						}else if("N".contentEquals(dealResult)){
							Process process = processManager.getServiceProcess(id);
							map.put(FlowExtWorktaskParam.PROCESS_ID, process.getId());
							multipleDeclareManager.stop(map);
							multipleDeclare.setStatus(multipleDeclareManager.DECLARE_FLOW_SHBTG);
						}
						multipleDeclareManager.save(multipleDeclare);
					}
					return JsonWrapper.successWrapper("操作成功！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapper("操作失败！");
			}

	   	}
}