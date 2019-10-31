package com.lsts.qualitymanage.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualityStaffTrain;
import com.lsts.qualitymanage.dao.QualityStaffTrainDao;
import com.lsts.qualitymanage.service.QualityStaffTrainManager;

import java.util.regex.Pattern;


@Controller
@RequestMapping("quality/staff/train")
public class QualityStaffTrainAction extends SpringSupportAction<QualityStaffTrain, QualityStaffTrainManager> {

    @Autowired
    private QualityStaffTrainManager  qualityStaffTrainManager;
    @Autowired
    private QualityStaffTrainDao  qualityStaffTrainDao;
    @Autowired
	private SysLogService logService;
    /**
   	 * 获取流程id  
   	 * */
	@RequestMapping(value = "getLcid")
   	@ResponseBody
   	public HashMap<String, Object> getLcid(String id) throws Exception{
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		String a=qualityStaffTrainDao.getLcid(id).toString();
   		String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
//   		String[] userId = idsa.split(",");
//   		String ids;
		CurrentSessionUser user = SecurityUtil.getSecurityUser();

//		for(String userIds:userId){  //循环人员ID
//			if(user.getId()==userIds){
//				ids=userIds;
				map.put("ids", ids);
//			}
//		}
   	
   		map.put("success", true);
		return map;
	}
	
	/**
   	 * 获取流程process_id
   	 * */
	@RequestMapping(value = "getprocess_id")
   	@ResponseBody
   	public HashMap<String, Object> getprocess_id(String id) throws Exception{
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		String a=qualityStaffTrainDao.getprocess_id(id).toString();
   		String process_id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
   		map.put("process_id", process_id);
   		map.put("success", true);
		return map;
	}

    
    
    /**
	 * 提交审核
	 * 
	 * */
    @RequestMapping(value = "subFolws")
   	@ResponseBody
   	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
   			String typeCode, String status,String activityId) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		//流程传参
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "职工培训申请 ");
   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 启动流程
   			if (StringUtil.isNotEmpty(flowId)) {
   				qualityStaffTrainManager.doStartPress(map);
   				//改变状态
   				QualityStaffTrain qualityStaffTrain = qualityStaffTrainManager.get(id);
   				qualityStaffTrain.setStatus(QualityStaffTrainManager.ZL_SH_YTJ);
   				qualityStaffTrainManager.save(qualityStaffTrain);
   				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"提交职工培训申请", 
  		  				"提交职工培训申请至部门负责人审核。操作结果：已提交", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
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
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submitsh")
	@ResponseBody
	public HashMap<String, Object> submitsh(String areaFlag,String id,
			String typeCode, String status,String activityId,HttpServletRequest request,@RequestBody QualityStaffTrain qualityStaffTrain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "职工培训申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK,true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		}else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				Date date=new Date();
				QualityStaffTrain  qualityStaffTrain2 = qualityStaffTrainManager.get(id);
			    qualityStaffTrain.setStatus(QualityStaffTrainManager.ZL_SH_SHZ);
			    qualityStaffTrain2.setStatus(QualityStaffTrainManager.ZL_SH_SHZ);
				if(areaFlag.equals("1")){
					qualityStaffTrainManager.doProcess(map);
					//保存业务表数据
					qualityStaffTrain2.setUserDepLeaderId(user.getId());
					qualityStaffTrain2.setUserDepLeader(user.getName());
					qualityStaffTrain2.setUserDepIdea(qualityStaffTrain.getUserDepIdea());
					qualityStaffTrain2.setUserDepIdeaTime(date);
					qualityStaffTrainManager.save(qualityStaffTrain2);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"部门负责人审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("2")){
					qualityStaffTrainManager.doProcess(map);
					//保存业务表数据
					qualityStaffTrain2.setSkillManageLeaderId(user.getId());
					qualityStaffTrain2.setSkillManageLeader(user.getName());
					qualityStaffTrain2.setSkillManageDepIdea(qualityStaffTrain.getSkillManageDepIdea());
					qualityStaffTrain2.setSkillManageIdeaTime(date);
					qualityStaffTrainManager.save(qualityStaffTrain2);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"科研技术管理部审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("3")){
					qualityStaffTrainManager.doProcess(map);
					//保存业务表数据
					qualityStaffTrain2.setBranchedPassageLeaderIdea(qualityStaffTrain.getBranchedPassageLeaderIdea());
					qualityStaffTrain2.setBranchedPassageIdeaTime(date);
					qualityStaffTrainManager.save(qualityStaffTrain2);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"部门分管领导审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("4")){
					qualityStaffTrainManager.doProcess(map);
					qualityStaffTrain2.setTrainFgLeaderIdea(qualityStaffTrain.getTrainFgLeaderIdea());
					qualityStaffTrain2.setTrainFgIdeaTime(date);
					qualityStaffTrainManager.save(qualityStaffTrain2);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"培训工作分管院领导审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");	
				}else if(areaFlag.equals("5")){
					qualityStaffTrainManager.doProcess(map);
					qualityStaffTrain2.setDeanExamineIdea(qualityStaffTrain.getDeanExamineIdea());
					qualityStaffTrain2.setDeanExamineIdeaTime(date);
					qualityStaffTrain2.setStatus(QualityStaffTrainManager.ZL_SH_PASS);
					qualityStaffTrainManager.save(qualityStaffTrain2);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"院长审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");	
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

	}
	/**审核不通过，撤销审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "nosubmitSh")
	@ResponseBody
	public HashMap<String, Object> nosubmitSh(ServletRequest request,String areaFlag,String id,
			String typeCode, String status,String activityId,String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 结束流程
			if (StringUtil.isNotEmpty(activityId)) {
				qualityStaffTrainManager.stop(map1);
				QualityStaffTrain qualityStaffTrain=qualityStaffTrainManager.get(id);
				qualityStaffTrain.setStatus(QualityStaffTrainManager.ZL_SH_NO_PASS);
				qualityStaffTrainManager.save(qualityStaffTrain);
				if(areaFlag.equals("1")){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"部门负责人审核。审核结果：不通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("2")){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"科研技术管理部审核。审核结果：不通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("3")){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"部门分管院领导审核。审核结果：不通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("4")){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"培训工作分管院领导审核。审核结果：不通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("5")){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"职工培训申请审核", 
	  		  				"院长审核。审核结果：不通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");	
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
    
    /**
     * 保存
     * @param request
     * @param qualityStaffTrain
     * @return
     */
    @RequestMapping(value = "saveTrain")
    @ResponseBody
    public HashMap<String, Object> saveTrain(HttpServletRequest request, @RequestBody QualityStaffTrain qualityStaffTrain){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	qualityStaffTrainManager.saveTrain(qualityStaffTrain, user);
    	return JsonWrapper.successWrapper(qualityStaffTrain);
    }
    
    @Override
    public HashMap<String, Object> delete(String ids) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	QualityStaffTrain qualityStaffTrain  = qualityStaffTrainManager.get(ids);
    	if(qualityStaffTrain.getStatus().equals(QualityStaffTrainManager.ZL_SH_YTJ)){
			map.put("success", false);
			map.put("msg", "此条数据已改变不可删除！");
    	}else{
    		qualityStaffTrainManager.delete(ids);
    		map.put("success", true);
    		map.put("msg", "删除成功");
    	}
    	return map;
    }
    // 查询流程步骤信息
 	@RequestMapping(value = "getFlowStep")
 	@ResponseBody
 	public ModelAndView getFlowStep(HttpServletRequest request)
 			throws Exception {

 		Map<String, Object> map = new HashMap<String, Object>();

 		map = qualityStaffTrainManager.getFlowStep(request.getParameter("id"));

 		ModelAndView mav = new ModelAndView("app/qualitymanage/flow_card", map);

 		return mav;

 	}
    
    
    
    
}