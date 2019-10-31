package com.lsts.humanresources.web;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.service.CodeTableManager;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.WxLeave;
import com.lsts.humanresources.dao.WxLeaveDao;
import com.lsts.humanresources.service.BgLeaveManager;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.OrgidLeaderidManager;
import com.lsts.humanresources.service.WxLeaveManager;
import com.lsts.log.service.SysLogService;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("WxLeaveAction")
public class WxLeaveAction extends SpringSupportAction<WxLeave, WxLeaveManager> {

	@Autowired
    private EmployeeBaseManager  employeeBaseManager;
    @Autowired
    private WxLeaveManager  wxLeaveManager;
    @Autowired
	private CodeTableManager codeTableManager;
	@Autowired
	FlowServiceConfigManager flowServiceConfigManager;
	@Autowired
	private WxLeaveDao wxLeaveDao;
	@Autowired
	private EmployeesDao employeesDao;
	@Autowired
	private UserManagerImpl userManager;
	@Autowired
    private BgLeaveManager  bgLeaveManager;
	@Autowired
	private BgLeaveAction bgLeaveAction;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SysLogService logService;
	@Autowired
    private OrgidLeaderidManager  orgidLeaderidManager;
	@Autowired
	private UserDao userDao;
	@Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;

   /**已请假种类及天数
     * @param peopleId
     * @throws Exception 
     */
    @RequestMapping(value = "queryLeave")
	@ResponseBody
	public HashMap<String, Object> queryLeave(HttpServletRequest request,String peopleId,String startDate) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	String peopleId1 = request.getParameter("peopleId");
    	String leaveInfo = wxLeaveManager.queryLeave(request, peopleId1,startDate);
    	System.out.println(leaveInfo);
    	if(leaveInfo==null){
    		wrapper.put("success", false);
    		wrapper.put("leaveInfo", "请向人事部确认你的可休年假天数！");
    	}else if(leaveInfo=="1"){
    		wrapper.put("success", false);
    		wrapper.put("leaveInfo", "请向人事部确认你的基本信息是否正确！");
    	}else{
    		wrapper.put("success", true);
    		wrapper.put("leaveInfo", leaveInfo);
    	}
		return wrapper;
    }

    /**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(ServletRequest request, String id, String people_id,String leave_count1,String flowId,
		String typeCode, String status, String activityId) throws Exception {
		
		User sysuser = wxLeaveManager.getUser(people_id);//根据员工ID获取User
		com.khnt.rbac.impl.bean.User user = (com.khnt.rbac.impl.bean.User)employeesDao.getUser(sysuser.getId());
		List<?> list = wxLeaveManager.getUserPower(people_id);//获取申请人的权限
		Map<String, Object> map = new HashMap<String, Object>();
		//流程传参
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"休假请假申请-"+user.getName());
		/*map.put(FlowExtWorktaskParam.SERVICE_TITLE,"休假请假申请-"+employeeBaseManager.get(people_id).getEmpName());*/
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			if (StringUtil.isNotEmpty(flowId)) {
				//选择分支
 				JSONObject dataBus = new JSONObject();
  	   	   		if(!list.contains("部门负责人")&&!list.contains("院领导")&&!list.contains("院长")){
  	   	   			dataBus.put("org", "1");
 	   			}else if(list.contains("部门负责人")||list.contains("院领导")){
 	   				dataBus.put("org", "2");
 	   			}
  	   	   		WxLeave wxLeave =  wxLeaveManager.get(id);
 	   			map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
 	   			/*BpmOrgImpl unit = new BpmOrgImpl(flowId, user.getName(), null, null, null);
 	   			BpmOrgImpl department = new BpmOrgImpl(flowId, user.getName(), null, null, null);*/
 	   			BpmUserImpl BpmUser=new BpmUserImpl(flowId, user.getName(), 
 	   				null, null, null);
 	   			//String id, String name, BpmOrg unit, BpmOrg department, Map<String, String> role
 	   			map.put(FlowExtWorktaskParam.BPM_USER, BpmUser);
 	   			// 启动流程
				try {
					wxLeaveManager.doStartPress(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//改变状态
				wxLeave.setApplyStatus(WxLeaveManager.LEAVE_FLOW_YTJ);
				wxLeaveManager.save(wxLeave);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}
	}
	/**
  	 * 审核通过
  	 * 
  	 * */
    @RequestMapping(value = "subPass")
 	@ResponseBody
 	public HashMap<String, Object> subPass(HttpServletRequest request,String areaFlag,String leave_id, String flowId,
 			String activityId,String typeCode) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		String sign="0";
 		String checkName="";
    	/*String opinion1=request.getParameter("opinion");*/
		String opinion = "同意";
    	WxLeave wxLeave = wxLeaveManager.get(leave_id);//获取休假请假申请信息
 		String leaveCount1 = wxLeave.getLeaveCount1();//获取请假人的请假天数
 		Map<String, Object> map = new HashMap<String, Object>();
 		activityId=bgLeaveManager.queryActivityId(request,leave_id);
		List<?> list = wxLeaveManager.getUserPower(wxLeave.getPeopleId());//获取申请人的权限
		String remark=wxLeaveManager.getRemark(leave_id,user.getId());
		String fgldName=orgidLeaderidManager.getInfoByOrgid(wxLeave.getDepId()).getEmpName();//获取分管领导姓名
 		fgldName=StringUtil.isNotEmpty(fgldName)?fgldName:"";
		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, leave_id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-"+wxLeave.getPeopleName());
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, "TJY2_RL_LEAVE");
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		//获取流程环节
		if(remark.equals("工作流：【休假请假审批流程】，环节：【部门负责人确认签字】")){
			areaFlag="2";
		}else if(remark.equals("工作流：【休假请假审批流程】，环节：【人事意见】")){
			areaFlag="3";
		}else if(remark.equals("工作流：【休假请假审批流程】，环节：【分管领导意见】")){
			areaFlag="4";
		}else if(remark.equals("工作流：【休假请假审批流程】，环节：【院长意见】")){
			areaFlag="5";
		}
 		if (StringUtil.isEmpty(leave_id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				//改变状态并保存审核意见
 				wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHZ);
 				if(areaFlag.equals("2")){
 					wxLeaveManager.doProcess(map);
 					wxLeave.setKsfzryj(opinion);//部门负责人意见
 					wxLeave.setKsfzryjSing(user.getName());
 					wxLeave.setKsfzryjDate(new Date());
					checkName = bgLeaveManager.getCheckName(wxLeave.getId(), wxLeave.getDepId());
					checkName = StringUtil.isNotEmpty(checkName)?checkName:"";
					request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
	 				request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
	 				
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(wxLeave.getId(), 
	  		  				"审批休假请假申请", 
	  		  				"部门负责人审批通过，并提交至"+checkName+"审批，操作结果：审批中", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("3")){
 					//选择分支,当休假请假天数小于等于一天
 					JSONObject dataBus = new JSONObject();
 					if(!list.contains("部门负责人")&&!list.contains("院领导")&&!list.contains("院长")){
 						if(leaveCount1.equals("0.5") || leaveCount1.equals("1")){
 	 						dataBus.put("org", "12");
 	 						map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
 	 	 					wxLeaveManager.doProcess(map);
 	 						wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHTG);
 	 						request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
 	 						
 	 						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 	 		  		  		logService.setLogs(wxLeave.getId(), 
 	 		  		  				"审批休假请假申请", 
 	 		  		  				"人事审批通过，操作结果：审批通过", 
 	 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
 	 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 	 		  						request != null ? request.getRemoteAddr() : "");
 						}else if(!leaveCount1.equals("0")&&!leaveCount1.equals("0.5")&&!leaveCount1.equals("1")){
 							sign="1";
 							dataBus.put("org", "11");
 	 						map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
 	 	 					wxLeaveManager.doProcess(map);
 	 						request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
 	 		 				request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
 	 		 				
 							//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 	 		  		  		logService.setLogs(wxLeave.getId(), 
 	 		  		  				"审批休假请假申请", 
 	 		  		  				"人事审批通过并提交至"+fgldName+"审批，操作结果：审批中", 
 	 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
 	 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 	 		  						request != null ? request.getRemoteAddr() : "");
 						}
		 	   		}else if(list.contains("部门负责人")&&!list.contains("院领导")){
		 	   			sign="1";
		 	   			dataBus.put("org", "22");
			 	   		map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	 					wxLeaveManager.doProcess(map);
	 					//审核流程未结束时将当下一步审核人的姓名作为参数
	 		 			request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
	 		 			request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
	 		 			
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 		  		  		logService.setLogs(wxLeave.getId(), 
 		  		  				"审批休假请假申请", 
 		  		  				"人事审批通过并提交至"+fgldName+"审批，操作结果：审批中", 
 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 		  						request != null ? request.getRemoteAddr() : "");
		 	   		}else if(list.contains("院领导")){
		 	   			dataBus.put("org", "21");
			 	   		map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	 					wxLeaveManager.doProcess(map);
	 					request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
	 		 			request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
	 		 			
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 		  		  		logService.setLogs(wxLeave.getId(), 
 		  		  				"审批休假请假申请", 
 		  		  				"人事审批通过并提交至蒋青审批，操作结果：审批中", 
 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 		  						request != null ? request.getRemoteAddr() : "");
		 	   		}
					wxLeave.setRsyj(opinion);//人事意见
 					wxLeave.setRsyjSign(user.getName());//人事签字
 					wxLeave.setRsyjDate(new Date());//审批日期
 				}else if(areaFlag.equals("4")){
 					//选择分支,当休假请假天数小于等于一天
 					JSONObject dataBus = new JSONObject();
 					if(!list.contains("部门负责人")&&!list.contains("院领导")&&!list.contains("院长")){
 						bgLeaveManager.doProcess(map);
 						wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHTG);
 						request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 	 				
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 		  		  		logService.setLogs(wxLeave.getId(), 
 		  		  				"审批休假请假申请", 
 		  		  				"分管领导审批通过，操作结果：审批通过", 
 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 		  						request != null ? request.getRemoteAddr() : "");
 					}else if(list.contains("部门负责人")&&!list.contains("院领导")){
	 	 	   			if(leaveCount1.equals("0.5") || leaveCount1.equals("1")){
	 						dataBus.put("org", "221");
	 						map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	 						wxLeaveManager.doProcess(map);
	 						wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHTG);
	 						request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
 	 	 					
	 						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	 		  		  		logService.setLogs(wxLeave.getId(), 
	 		  		  				"审批休假请假申请", 
	 		  		  				"分管领导审批通过，操作结果：审批通过", 
	 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
	 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	 		  						request != null ? request.getRemoteAddr() : "");
	 	 	   			}else if(!leaveCount1.equals("0")&&!leaveCount1.equals("0.5")&&!leaveCount1.equals("1")){
	 						dataBus.put("org", "222");
	 						map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	 						wxLeaveManager.doProcess(map);
	 						request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
 	 		 				request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
 	 		 				
							//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	 		  		  		logService.setLogs(wxLeave.getId(), 
	 		  		  				"审批休假请假申请", 
	 		  		  				"分管领导审批通过并提交至蒋青审批，操作结果：审批中", 
	 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
	 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	 		  						request != null ? request.getRemoteAddr() : "");
	 	 	   			}
 	 	   			}
 					wxLeave.setFgyldyj(opinion);;//分管领导意见
 					wxLeave.setFgyldyjSign(user.getName());//分管领导签字
 					wxLeave.setFgyldyjDate(new Date());//审批时间
 				}else if(areaFlag.equals("5")){
 					wxLeaveManager.doProcess(map);
 					wxLeave.setYzyj(opinion);//院长意见
 					wxLeave.setYzyjSign(user.getName());//院长签字
 					wxLeave.setYzyjDate(new Date());//审批时间
 					wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHTG);
 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 				
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(wxLeave.getId(), 
	  		  				"审批休假请假申请", 
	  		  				"院长审批通过，操作结果：审批通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}
 				wxLeaveManager.save(wxLeave);
 				//发送提醒消息
				try {
					sendMsg(request, wxLeave);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
				}
 			} else {
 				return JsonWrapper.failureWrapper("流程ID为空！");
 			}
 			return JsonWrapper.successWrapper(leave_id);
 		}
 	}
    
    /**
	 * @param request
	 * @param leave_id//请休假ID
	 * @param id//当前员工ID
	 * 审核不通过
	 **/
	  @RequestMapping(value = "shbtg")
	  @ResponseBody
	  public HashMap<String, Object> shbtg(HttpServletRequest request,String areaFlag,String leave_id, String flowId,
	 			String activityId,String typeCode,String processId)throws Exception {
		    /*String opinion1=request.getParameter("opinion");*/
			String opinion = "不同意";
			Map<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
	    	WxLeave wxLeave = wxLeaveManager.get(leave_id);//获取休假请假申请信息
	 		activityId=bgLeaveManager.queryActivityId((HttpServletRequest) request,leave_id);
	 		processId=bgLeaveManager.queryProcessId((HttpServletRequest) request, leave_id);
			String remark=wxLeaveManager.getRemark(leave_id,user.getId());
			String fgldName=orgidLeaderidManager.getInfoByOrgid(wxLeave.getDepId()).getEmpName();//获取分管领导姓名
	 		fgldName=StringUtil.isNotEmpty(fgldName)?fgldName:"";
			//流程传参
	 		map.put(FlowExtWorktaskParam.SERVICE_ID, leave_id);
	 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-"+wxLeave.getPeopleName());
	 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, "TJY2_RL_LEAVE");
	 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
			//获取流程环节
			if(remark.equals("工作流：【休假请假审批流程】，环节：【部门负责人确认签字】")){
				areaFlag="2";
			}else if(remark.equals("工作流：【休假请假审批流程】，环节：【人事意见】")){
				areaFlag="3";
			}else if(remark.equals("工作流：【休假请假审批流程】，环节：【分管领导意见】")){
				areaFlag="4";
			}else if(remark.equals("工作流：【休假请假审批流程】，环节：【院长意见】")){
				areaFlag="5";
			}
	    	
	  		map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
			map.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
	  		if (StringUtil.isEmpty(leave_id)) {
	  			return JsonWrapper.failureWrapper("参数错误！");
	  		} else {
	  			// 结束流程
	  			if (StringUtil.isNotEmpty(activityId)) {
	  				//改变状态并保存审核意见
	  				wxLeaveManager.stop(map);
	  				wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHBTG);
	 				if(areaFlag.equals("2")){
	 					wxLeave.setKsfzryj(opinion);//部门负责人意见
	 					wxLeave.setKsfzryjSing(user.getName());//部门负责人签字
	 					wxLeave.setKsfzryjDate(new Date());//审批时间
	 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 					
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"部门主任审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}else if(areaFlag.equals("3")){
	 					wxLeave.setRsyj(opinion);//人事意见
	 					wxLeave.setRsyjSign(user.getName());//人事签字
	 					wxLeave.setRsyjDate(new Date());//审批时间
	 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 					
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"人事审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}else if(areaFlag.equals("4")){
	 					wxLeave.setFgyldyj(opinion);//分管领导意见
	 					wxLeave.setFgyldyjSign(user.getName());//分管领导签字
	 					wxLeave.setFgyldyjDate(new Date());//审批时间
	 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 					
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"分管领导审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}else if(areaFlag.equals("5")){
	 					wxLeave.setYzyj(opinion);//院长意见
	 					wxLeave.setYzyjSign(user.getName());//院长签字
	 					wxLeave.setYzyjDate(new Date());//审批时间
	 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
	 					
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"院长审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}
	 				wxLeaveManager.save(wxLeave);
	 				//发送提醒消息
					try {
						request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
						sendMsg(request, wxLeave);
					} catch (Exception e) {
						e.printStackTrace();
						KhntException kh = new KhntException(e);
						log.error(kh.getMessage());
					}
	  			} else {
	  				return JsonWrapper.failureWrapper("流程ID为空！");
	  			}
	  			return JsonWrapper.successWrapper(leave_id);
	  		}
	}
	
   /**
	* 手机端提交流程，并改变业务状态
	* @param id
	* @param flowId
	* @param typeCode
	* @param status
	* HashMap<String,Object>   
	* @return    
	* @throws
	 */
	@RequestMapping("mobileSubmit")
	@ResponseBody
	public HashMap<String, Object> mobileSubmit(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		String leaveType=request.getParameter("leaveType");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String leaveCount1=request.getParameter("leaveCount1");
		String leaveReason1=request.getParameter("leaveReason");
		String leaveReason = URLDecoder.decode(leaveReason1,"utf-8");
		String total1=request.getParameter("total");
		String total = URLDecoder.decode(total1,"utf-8");
		//String flowId=request.getParameter("flowId");		
		SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1=ss.parse(startDate);
		Date endDate1=ss.parse(endDate);
		WxLeave wxLeave = new WxLeave();
		wxLeave.setLeaveType(leaveType);
		wxLeave.setStartDate(startDate1);
		wxLeave.setEndDate(endDate1);
		wxLeave.setLeaveCount1(leaveCount1);
		wxLeave.setLeaveCount2(leaveCount1);
		wxLeave.setLeaveReason(leaveReason);
		wxLeave.setTotal(total);
		String leave_id=wxLeaveManager.saveLeaveMessageWX(wxLeave,request,id);//业务id
		if(leaveType.equals("GWWC")){
			List<FlowServiceConfig> listresult= flowServiceConfigManager.queryServiceConfig("TJY2_RL_GWWC", "");
			String flowId=null; 
			if(listresult.size()==1){
				try {
					FlowServiceConfig fc = listresult.get(0);
					flowId = fc.getFlowId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String status = null;
				String activityId = null;
				String typeCode="TJY2_RL_GWWC";
				bgLeaveAction.subFolwGwwc(request,leave_id,id,leaveCount1,flowId,typeCode,status,activityId);
				return JsonWrapper.successWrapper(leave_id);		
			 }else{
				 return JsonWrapper.failureWrapper("msg","错误！");	
			 }
		}else{
			List<FlowServiceConfig> listresult= flowServiceConfigManager.queryServiceConfig("TJY2_RL_LEAVE", "");
			String flowId=null; 
			if(listresult.size()==1){
				try {
					FlowServiceConfig fc = listresult.get(0);
					flowId = fc.getFlowId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String status = null;
				String activityId = null;
				String typeCode="TJY2_RL_LEAVE";
				bgLeaveAction.subFolw(request,leave_id,id,leaveCount1,flowId,typeCode,status,activityId);
				return JsonWrapper.successWrapper(leave_id);		
			 }else{
				 return JsonWrapper.failureWrapper("msg","错误！");	
			 }
		}
	}	
	/**
	 * 获取码表
	 * 
	 * 
	 */
	@RequestMapping(value = "getcodetabl")
	@ResponseBody
	public HashMap<String, Object> getcodetabl(HttpServletRequest request,String tablname)
			throws Exception {
		    CodeTable  code= codeTableManager.getByCode(tablname);	
		 	return JsonWrapper.successWrapper(code.getCodeTableValues());
	}
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "weixinUserInfo")
	@OAuthRequired
	public String weixinUserInfo(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        System.out.println("businessId = " + request.getParameter("businessId"));
        HttpSession session = request.getSession();
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User start");
        if(session.getAttribute("Userid")==null || ((String)session.getAttribute("Userid")).equals("noUserId")) {
        	/*AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);*/
        	String token = WxUtil.getAccessTokenString();
        	log.debug(this.getClass().getName() + " method weixinUserInfo 获取到code："+request.getParameter("code"));
        	log.debug(this.getClass().getName() + " method weixinUserInfo 获取到token："+token);
            if (StringUtil.isNotEmpty(token) && request.getParameter("code")!=null) {
            	Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+result.getObj() ;
                log.debug(this.getClass().getName() + " method weixinUserInfo menuUrl："+menuUrl);
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                if(jo!=null){
                	if(jo.has("mobile")) {
						User user;
						try {
							user = weixinAppInfoManager.getUser(jo.getString("mobile"));
							if(user!=null) {
								session.setAttribute("Name", user.getName());
								session.setAttribute("Phone", jo.getString("mobile"));
								session.setAttribute("Account",user.getAccount());
								log.debug(this.getClass().getName() + " method weixinUserInfo 获取到用户信息，用户姓名："+user.getName()+"，用户手机号：" + jo.getString("mobile")+"，用户Account:"+user.getAccount());
							}else {
								session.setAttribute("error_msg", "亲，检验平台中未找到与该手机号匹配的用户信息");
								log.debug("亲，检验平台中未找到与该手机号匹配的用户信息");
								return "app/weixininfo/app_info/weixin_error_page";
							}
						} catch (Exception e) {
							log.debug(e.getMessage());
							return "app/weixininfo/app_info/weixin_error_page";
						}
					}else {
						session.setAttribute("error_msg", "亲，微信接口服务出现异常，未找到手机号");
						log.debug("亲，微信接口服务出现异常，未找到手机号");
						return "app/weixininfo/app_info/weixin_error_page";
					}
				}else {
					session.setAttribute("error_msg", "亲，微信接口服务出现异常，未获取到微信企业用户信息");
					log.debug("亲，微信接口服务出现异常，未获取到微信企业用户信息");
					return "app/weixininfo/app_info/weixin_error_page";
				}
                session.setAttribute("Userid", result.getObj());
                //session.setAttribute("AccessToken", accessToken.getToken());
                
            } else {
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
                //session.setAttribute("AccessToken", "");
            }
        }
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User end");
        session.setAttribute("businessId", request.getParameter("businessId"));
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return "app/humanresources/wx_leave/transfer_page";
	}
    /**
     * 获取当前登录人姓名和部门
     */
    @RequestMapping(value = "loadUser")
    @ResponseBody
    public HashMap<String, Object>loadUser(HttpServletRequest request,String id)throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	try {
    		EmployeeBase employeeBase = employeeBaseManager.get(id);
    		if(employeeBase!=null){
    			String peopleName=employeeBase.getEmpName();
            	String workDepartmentName=employeeBase.getWorkDepartmentName();
            	wrapper.put("peopleName", peopleName);
            	wrapper.put("workDepartmentName", workDepartmentName);
            	System.out.println("获取当前登录人姓名和部门================="+workDepartmentName+"=============="+peopleName);
    		}
		} catch (Exception e) {
			wrapper.put("msg", "系统繁忙，未获取到个人信息，请稍后再试！");
		}
    	return wrapper;
    }
    
    /**撤销请休假申请
     * @param Id
     * @throws Exception 
     */
    @RequestMapping(value = "revokeLeave")
	@ResponseBody
	public HashMap<String, Object> revokeLeave(HttpServletRequest request,String id) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	WxLeave wxLeave = wxLeaveManager.get(id);
    	wxLeave.setApplyStatus("YCX");
    	try {
    		wxLeaveManager.save(wxLeave);
			System.out.println("@@@@@@@@@@@@@@@@@@撤销成功@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			wrapper.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			wrapper.put("success", false);
		}
		return wrapper;
    }
    /**
  	 * 审核通过
  	 * 
  	 * */
    @RequestMapping(value = "subPassGwwc")
 	@ResponseBody
 	public HashMap<String, Object> subPassGwwc(HttpServletRequest request,String areaFlag,String leave_id, String flowId,
 			String activityId,String typeCode) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		String sign="0";
 		String checkName="";
    	/*String opinion1=request.getParameter("opinion");*/
		String opinion = "同意";
    	WxLeave wxLeave = wxLeaveManager.get(leave_id);//获取休假请假申请信息
 		Map<String, Object> map = new HashMap<String, Object>();
 		activityId=bgLeaveManager.queryActivityId((HttpServletRequest) request,leave_id);
 		String remark=wxLeaveManager.getRemarkGwwc(leave_id,user.getId());
 		String fgldName=orgidLeaderidManager.getInfoByOrgid(wxLeave.getDepId()).getEmpName();//获取分管领导姓名
 		fgldName=StringUtil.isNotEmpty(fgldName)?fgldName:"";
		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, leave_id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-"+wxLeave.getPeopleName());
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, "TJY2_RL_GWWC");
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
 		//获取流程环节
		if(remark.equals("工作流：【公务外出审批流程】，环节：【部长审核】")){
			areaFlag="2";
		}else if(remark.equals("工作流：【公务外出审批流程】，环节：【分管领导审核】")){
			areaFlag="4";
		}else if(remark.equals("工作流：【公务外出审批流程】，环节：【院长审核】")){
			areaFlag="5";
		}
 		if (StringUtil.isEmpty(leave_id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				//改变状态并保存审核意见
 				wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHZ);
 				if(areaFlag.equals("2")){
 					sign="1";
 					wxLeaveManager.doProcess(map);
 					wxLeave.setKsfzryj(opinion);//部门负责人意见
 					wxLeave.setKsfzryjSing(user.getName());
 					wxLeave.setKsfzryjDate(new Date());
					checkName = bgLeaveManager.getCheckName(wxLeave.getId(), wxLeave.getDepId());
					checkName = StringUtil.isNotEmpty(checkName)?checkName:"";
					request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
 		 			request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
 		 			
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(wxLeave.getId(), 
	  		  				"审批休假请假申请", 
	  		  				"部门负责人审批通过，并提交至"+checkName+"审批，操作结果：审批中", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("4")){
 					wxLeaveManager.doProcess(map);
 					wxLeave.setFgyldyj(opinion);;//分管领导意见
 					wxLeave.setFgyldyjSign(user.getName());//分管领导签字
 					wxLeave.setFgyldyjDate(new Date());//审批时间
					checkName = bgLeaveManager.getCheckName(wxLeave.getId(), wxLeave.getDepId());
					checkName = StringUtil.isNotEmpty(checkName)?checkName:"";
					request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
 		 			request.setAttribute("sign", sign);//审核流程未结束时传递参数sign
 		 			
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(wxLeave.getId(), 
	  		  				"审批休假请假申请", 
	  		  				"分管领导审批通过，并提交至"+checkName+"审批，操作结果：审批中", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("5")){
 					wxLeaveManager.doProcess(map);
 					wxLeave.setYzyj(opinion);//院长意见
 					wxLeave.setYzyjSign(user.getName());//院长签字
 					wxLeave.setYzyjDate(new Date());//审批时间
 					wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHTG);
 					request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
 					
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(wxLeave.getId(), 
	  		  				"审批休假请假申请", 
	  		  				"院长审批通过，操作结果：审批通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}
 				wxLeaveManager.save(wxLeave);
 				//发送提醒消息
				try {
					sendMsg(request, wxLeave);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
				}
 			} else {
 				return JsonWrapper.failureWrapper("流程ID为空！");
 			}
 			return JsonWrapper.successWrapper(leave_id);
 		}
 	}
    /**
	 * @param request
	 * @param leave_id//请休假ID
	 * @param id//当前员工ID
	 * 审核不通过
	 **/
	  @RequestMapping(value = "shbtgGwwc")
	  @ResponseBody
	  public HashMap<String, Object> shbtgGwwc(HttpServletRequest request,String areaFlag,String leave_id, String flowId,
	 			String activityId,String typeCode,String processId)throws Exception {
		    /*String opinion1=request.getParameter("opinion");*/
			String opinion = "不同意";
			Map<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
	    	WxLeave wxLeave = wxLeaveManager.get(leave_id);//获取休假请假申请信息
	 		activityId=bgLeaveManager.queryActivityId((HttpServletRequest) request,leave_id);
	 		processId=bgLeaveManager.queryProcessId((HttpServletRequest) request, leave_id);
			String remark=wxLeaveManager.getRemarkGwwc(leave_id,user.getId());
			//流程传参
	 		map.put(FlowExtWorktaskParam.SERVICE_ID, leave_id);
	 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-"+wxLeave.getPeopleName());
	 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, "TJY2_RL_GWWC");
	 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	    	
	  		map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
			map.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
			String fgldName=orgidLeaderidManager.getInfoByOrgid(wxLeave.getDepId()).getEmpName();//获取分管领导姓名
	 		fgldName=StringUtil.isNotEmpty(fgldName)?fgldName:"";
			//获取流程环节
			if(remark.equals("工作流：【公务外出审批流程】，环节：【部长审核】")){
				areaFlag="2";
			}else if(remark.equals("工作流：【公务外出审批流程】，环节：【分管领导审核】")){
				areaFlag="4";
			}else if(remark.equals("工作流：【公务外出审批流程】，环节：【院长审核】")){
				areaFlag="5";
			}
	  		if (StringUtil.isEmpty(leave_id)) {
	  			return JsonWrapper.failureWrapper("参数错误！");
	  		} else {
	  			// 结束流程
	  			if (StringUtil.isNotEmpty(activityId)) {
	  				//改变状态并保存审核意见
	  				wxLeaveManager.stop(map);
	  				wxLeave.setApplyStatus(wxLeaveManager.LEAVE_FLOW_SHBTG);
	 				if(areaFlag.equals("2")){
	 					wxLeave.setKsfzryj(opinion);//部门负责人意见
	 					wxLeave.setKsfzryjSing(user.getName());//部门负责人签字
	 					wxLeave.setKsfzryjDate(new Date());//审批时间
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"部门主任审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}else if(areaFlag.equals("4")){
	 					wxLeave.setFgyldyj(opinion);//分管领导意见
	 					wxLeave.setFgyldyjSign(user.getName());//分管领导签字
	 					wxLeave.setFgyldyjDate(new Date());//审批时间
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"分管领导审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}else if(areaFlag.equals("5")){
	 					wxLeave.setYzyj(opinion);//院长意见
	 					wxLeave.setYzyjSign(user.getName());//院长签字
	 					wxLeave.setYzyjDate(new Date());//审批时间
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(wxLeave.getId(), 
		  		  				"审批休假请假申请", 
		  		  				"院长审批不通过，操作结果：审批不通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
	 				}
	 				wxLeaveManager.save(wxLeave);
	 				//发送提醒消息
					try {
						request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
						sendMsg(request, wxLeave);
					} catch (Exception e) {
						e.printStackTrace();
						KhntException kh = new KhntException(e);
						log.error(kh.getMessage());
					}
	  			} else {
	  				return JsonWrapper.failureWrapper("流程ID为空！");
	  			}
	  			return JsonWrapper.successWrapper(leave_id);
	  		}
	}
	  @RequestMapping(value = "getCheckName")
	  @ResponseBody
	  public HashMap<String, Object> getCheckName(ServletRequest request,String id,String orgId)throws Exception {
		  HashMap<String, Object> map = new HashMap<String, Object>();
		  String name = bgLeaveManager.getCheckName(id, orgId);
		  map.put("name", name);
		  System.out.println("获取该申请当前处理人================="+name);
		  return map;
	  }
	
	  @RequestMapping(value = "getCurDealName")
	  @ResponseBody
	  public HashMap<String, Object> getCurDealName(ServletRequest request,String id)throws Exception {
		  HashMap<String, Object> map = new HashMap<String, Object>();
		  String name = bgLeaveManager.getCurDealName(id);
		  map.put("name", name);
		  System.out.println("获取该申请当前处理人================="+name);
		  return map;
	  }
	/**
	 * 请休假申请消息提醒
	 * 
	 * @param request
	 *            -- 请求对象
	 * @param bgLeave
	 *            -- 请休假申请对象           
	 * @return
	 * @throws Exception 
	 */
	public void sendMsg(HttpServletRequest request, WxLeave wxLeave) throws Exception {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		String sign = (String) request.getAttribute("sign");
		String wx_check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3&redirect_uri=http://kh.scsei.org.cn/WxLeaveAction/weixinUserInfo.do?businessId=" + wxLeave.getId()
		+ "&response_type=code&scope=snsapi_base&state=STATE";
		String leaveType="";
		if(wxLeave.getLeaveType().equals("GWWC")){
 			leaveType="公务外出";
 		}else if(wxLeave.getLeaveType().equals("NJ")){
 			leaveType="年假";
 		}else if(wxLeave.getLeaveType().equals("SHIJ")){
 			leaveType="事假";
 		}else if(wxLeave.getLeaveType().equals("HJ")){
 			leaveType="婚假";
 		}else if(wxLeave.getLeaveType().equals("CJ")){
 			leaveType="产假";
 		}else if(wxLeave.getLeaveType().equals("TQJ")){
 			leaveType="探亲假";
 		}else if(wxLeave.getLeaveType().equals("BJ")){
 			leaveType="病假";
 		}else if(wxLeave.getLeaveType().equals("SANGJ")){
 			leaveType="丧假";
 		}else if(wxLeave.getLeaveType().equals("PCJ")){
 			leaveType="陪产假";
 		}else if(wxLeave.getLeaveType().equals("OTHER")){
 			leaveType="其他";
 		}
		
		map1.put("leaveType", leaveType);
		map1.put("startDate", new SimpleDateFormat("yyyy-MM-dd").format(wxLeave.getStartDate()));
		map1.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(wxLeave.getEndDate()));
		try {
			if("YTJ".equals(wxLeave.getApplyStatus()) || "SPZ".equals(wxLeave.getApplyStatus())) {
				//获取下一步审核人电话
				String phoneTemp=bgLeaveManager.getAuditor(wxLeave.getId(),sign);
				//获取下一步审核人姓名
				String checkName=(String) request.getAttribute("checkName");
				map1.put("peopleName", wxLeave.getPeopleName());
				map1.put("depName", wxLeave.getDepName());
				map1.put("leaveCount1", wxLeave.getLeaveCount1());
				map1.put("checkName", checkName);
				map2.put("url", wx_check_url);
				//发送消息给下一步审核人
				messageService.sendMassageByConfig(request, wxLeave.getId(), phoneTemp, null, "leave_apply_auditor",
						null, map1, map2, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
				System.out.println("this auditor's phone is :"+phoneTemp);
				//发送消息给请假人
				messageService.sendMassageByConfig(request, wxLeave.getId(), employeeBaseManager.get(wxLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_user",
						null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
			}else if("SPTG".equals(wxLeave.getApplyStatus())) {
				//最后审核人
				String checkName=(String) request.getAttribute("checkName");
				map1.put("checkName", checkName);
				//发送消息给请假人
				messageService.sendMassageByConfig(request, wxLeave.getId(), employeeBaseManager.get(wxLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_pass",
						null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
			}else if("SPBTG".equals(wxLeave.getApplyStatus())) {
				//最后审核人
				String checkName=(String) request.getAttribute("checkName");
				map1.put("checkName", checkName);
				//发送消息给请假人
				messageService.sendMassageByConfig(request, wxLeave.getId(), employeeBaseManager.get(wxLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_fail",
						null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
			}else if("YCX".equals(wxLeave.getApplyStatus())) {
				
			}else if("YXJ".equals(wxLeave.getApplyStatus())) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
}