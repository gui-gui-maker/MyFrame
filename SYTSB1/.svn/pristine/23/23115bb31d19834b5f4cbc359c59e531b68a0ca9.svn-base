package com.lsts.qualitymanage.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.TxwjspSealRegSug;
import com.lsts.qualitymanage.bean.TxwjspSealreg;
import com.lsts.qualitymanage.dao.TxwjspSealregDao;
import com.lsts.qualitymanage.service.TxwjspSealRegSugManager;
import com.lsts.qualitymanage.service.TxwjspSealregManager;


@Controller
@RequestMapping("seal/regist")
public class TxwjspSealregAction extends SpringSupportAction<TxwjspSealreg, TxwjspSealregManager> {

    @Autowired
    private TxwjspSealregManager  txwjspSealregManager;
    @Autowired
    private TxwjspSealregDao  txwjspSealregDao;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private TxwjspSealRegSugManager txwjspSealRegSugManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private SysLogService logService;
	 @RequestMapping(value="saves")
	 @ResponseBody
	 /**
	  * 保存
	  * @throws Exception 
	  * 
	  * */
		public HashMap<String, Object> saves(HttpServletRequest request,
				@RequestBody TxwjspSealreg txwjspSealreg) throws Exception {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String userId=curUser.getId();
    	String userName=curUser.getName();
    	txwjspSealregManager.save(txwjspSealreg,userId,userName,request);
    	
    	// 向附件中增加业务id
    			map.put("success", true);
    	   		return map;
		}
     
	 	/**
	   	 * 获取流程id
	   	 * */
		@RequestMapping(value = "getLcid")
	   	@ResponseBody
	   	public HashMap<String, Object> getLcid(String id) throws Exception{
	   		HashMap<String, Object> map = new HashMap<String, Object>();
	   		String a=txwjspSealregDao.getLcid(id).toString();
	   		String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
	    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
	   		map.put("ids", ids);
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
	   		String a=txwjspSealregDao.getprocess_id(id).toString();
	   		String process_id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
	    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
	   		map.put("process_id", process_id);
	   		map.put("success", true);
			return map;
		}
      
      /**
    	 * 详情
       * @throws Exception 
    	 * 
    	 * */
    @RequestMapping(value = "details")
  	@ResponseBody
  	public HashMap<String,Object> getTxwjspSealreg(HttpServletRequest request) throws Exception{
  		try{
  			String Id=request.getParameter("id");
  			HashMap<String, Object> ret = txwjspSealregManager.getTxwjspSealregInfo(Id);
  			ret.put("files", attachmentManager.getBusAttachment(Id));
  			return ret;
  		}catch (Exception e) {
  			e.printStackTrace();
  			return null;
  		}
  	}
    /**
  	 * 提交审核
  	 * 
  	 * */
  	 @RequestMapping(value = "subFolws")	
  	 @ResponseBody
     	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
     		String typeCode, String status,String activityId) throws Exception {
      	    CurrentSessionUser user = SecurityUtil.getSecurityUser();
     		Map<String, Object> map = new HashMap<String, Object>();
     		//流程传参
     		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
     		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
     		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "非用章范围需盖章申请");
     		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
     		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
     		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

    		User uu = (User)user.getSysUser();
    		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
    		
     		String userName=emp.getName();
			String orgId=emp.getOrg().getId();
     		if (StringUtil.isEmpty(id)) {
     			return JsonWrapper.failureWrapper("参数错误！");
     		} else {
     			String activityId1=txwjspSealregDao.getFlowId(id).toString();
          	    String activityId2=Pattern.compile("\\b([\\w\\W])\\b").matcher(activityId1.toString()
    					 .substring(1,activityId1.toString().length()-1)).replaceAll("'$1'");
     			if(StringUtil.isNotEmpty(activityId2)){
     				Map<String, Object> paramMap = new HashMap<String, Object>();
     				 
     		        // ------------- 必要参数 -----------------------
     		        paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId2);
     		        try {
						txwjspSealregManager.doProcess(paramMap);
						//改变状态
         				TxwjspSealreg txwjspSealreg=txwjspSealregManager.get(id);
    					txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_BMDSH);//部门负责人待审核
    					txwjspSealreg.setGivePerson(userName);
    					txwjspSealreg.setGivePersonDate(new Date());
    					txwjspSealregManager.save(txwjspSealreg);
    					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
    	  		  		logService.setLogs(id, 
    	  		  				"提交非用章范围需盖章申请", 
    	  		  				"提交非用章范围需盖章申请至送件部门负责人审核。操作结果：已提交", 
    	  		  				user != null ? user.getId() : "未获取到操作用户编号",
    	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
    	  						request != null ? request.getRemoteAddr() : "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
     			}else{
     				// 启动流程
         			if (StringUtil.isNotEmpty(flowId)) {
         				/*List userList1=txwjspSealregManager.getUser(orgId);
     					//给流程的指定下一步操作人为部门负责人
     					JSONObject dataBus = new JSONObject();
     					String next_sub_op = null; 
     					String next_op_name = null ;
    					if (userList1 != null && userList1.size() > 0) {
    				    	for (int u = 0; u < userList1.size(); u++) {
    				    		Object user1[] = null;
    				    		user1 = (Object[]) userList1.get(u);
    				    		next_sub_op=user1[0].toString();
    				    		next_op_name=user1[1].toString();
    				    	}
    				    	
    			    	JSONArray  pts = new JSONArray();
    			    	JSONObject pt = new JSONObject();
    			    	pt.put("id", next_sub_op);
    			    	pt.put("name", next_op_name);
    			    	pts.put(pt);
    			    	dataBus.put("paticipator", pts);
    			    	map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);*/
     				    txwjspSealregManager.doStartPress(map);
         				//改变状态
         				TxwjspSealreg txwjspSealreg=txwjspSealregManager.get(id);
    					txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_BMDSH);//部门负责人待审核
    					txwjspSealreg.setGivePerson(userName);
    					txwjspSealreg.setGivePersonDate(new Date());
    					txwjspSealregManager.save(txwjspSealreg);
    					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
    	  		  		logService.setLogs(id, 
    	  		  				"提交非用章范围需盖章申请", 
    	  		  				"提交非用章范围需盖章申请至部门负责人审核。操作结果：已提交", 
    	  		  				user != null ? user.getId() : "未获取到操作用户编号",
    	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
    	  						request != null ? request.getRemoteAddr() : "");
         			} else {
         				return JsonWrapper.failureWrapper("流程ID为空！");
         			}
     			}
     		 }
     			return JsonWrapper.successWrapper(id);
     		}
  	/**审批流程并改变状态
    	 * @param id
    	 * @param flowId
    	 * @param typeCode
    	 * @param status
    	 * @return
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "zltj")
    	@ResponseBody
    	public HashMap<String, Object> zltj(String areaFlag,String id,String seal,
    			String typeCode,String activityId,HttpServletRequest request) throws Exception {
    		Map<String, Object> map = new HashMap<String, Object>();
    		CurrentSessionUser user = SecurityUtil.getSecurityUser();
    		User uu = (User)user.getSysUser();
    		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
    		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
    		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
    		Activity activity = activityManager.get(activityId);
    		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "非用章范围需盖章申请");
    		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
    		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK,true);
    		
    		if (StringUtil.isEmpty(id)) {
    			return JsonWrapper.failureWrapper("参数错误！");
    		}else {
    			// 审批流程
    			if (StringUtil.isNotEmpty(activityId)) {
    				Date date=new Date();
    			    String userName=emp.getName();
    			    String orgId=emp.getOrg().getId();
    			    TxwjspSealreg txwjspSealreg=txwjspSealregManager.get(id);
    			    TxwjspSealRegSug txwjspSealRegSug=new TxwjspSealRegSug();
 				if(areaFlag.equals("1")){
				    	txwjspSealregManager.doProcess(map);
				    	
				    	//保存业务表数据
				    	txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_FWBDSH);//服务部待审核
				    	txwjspSealreg.setDeliveryHead(userName);
				    	txwjspSealreg.setDeliveryHeadDate(date);
				    
				    	
				    	//保存审核意见、记录
				    	txwjspSealRegSug.setBusinessId(id);
				    	txwjspSealRegSug.setSpUserName(user.getName());
				    	txwjspSealRegSug.setSpName(activity.getName());
				    	txwjspSealRegSug.setSpUserId(user.getUserId());
				    	txwjspSealRegSug.setSpTime(date);
				    	txwjspSealRegSug.setSeal(seal);//印章id
				    	txwjspSealRegSug.setSpLevel(areaFlag);//审批层级
				    	//txwjspSealRegSug.setSpLevel("非用章范围需盖章登记记录表-"+user.getName());
				    	txwjspSealRegSug.setStatus(txwjspSealregManager.QUALITY_FLOW_FWBDSH);
				    	txwjspSealRegSug.setSpResult(txwjspSealregManager.QUALITY_FLOW_SHTG);
				    	
				    	txwjspSealregManager.saves(txwjspSealreg,txwjspSealRegSug);
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(id, 
		  		  				"非用章范围需盖章申请审核", 
		  		  				"送件部门负责人审核。审核结果：通过", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("2")){
 					
 					//保存业务表数据
					txwjspSealregManager.doProcess(map);
					txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_FGLDDSH);//分管领导待审核
 					txwjspSealreg.setServiceHead(userName);
 					txwjspSealreg.setServiceHeadDate(date);
 					//保存审核意见、记录
 					txwjspSealRegSug.setBusinessId(id);
 					txwjspSealRegSug.setSpUserName(user.getName());
			    	txwjspSealRegSug.setSpName("服务部 负责人签字");
			    	txwjspSealRegSug.setSpUserId(user.getUserId());
			    	txwjspSealRegSug.setSpTime(date);
			    	txwjspSealRegSug.setSeal(seal);//印章id
			    	txwjspSealRegSug.setSpLevel(areaFlag);
			    	txwjspSealRegSug.setStatus(txwjspSealregManager.QUALITY_FLOW_FGLDDSH);
			    	txwjspSealRegSug.setSpResult(txwjspSealregManager.QUALITY_FLOW_SHTG);
			    	
			    	txwjspSealregManager.saves(txwjspSealreg,txwjspSealRegSug);
			    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"非用章范围需盖章申请审核", 
	  		  				"服务部负责人审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("3")){
 					txwjspSealregManager.doProcess(map);
 					//保存审核意见、记录
 					txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_GZRDSH);//盖章人待审核
			    	txwjspSealRegSug.setBusinessId(id);
			    	txwjspSealRegSug.setSpUserName(user.getName());
			    	txwjspSealRegSug.setSpName("业务部门 分管院领导签字");
			    	txwjspSealRegSug.setSpUserId(user.getUserId());
			    	txwjspSealRegSug.setSpTime(date);
			    	txwjspSealRegSug.setSeal(seal);//印章id
			    	txwjspSealRegSug.setSpLevel(areaFlag);
			    	txwjspSealRegSug.setStatus(txwjspSealregManager.QUALITY_FLOW_GZRDSH);
			    	txwjspSealRegSug.setSpResult(txwjspSealregManager.QUALITY_FLOW_SHTG);
 					//保存业务表数据
 					txwjspSealreg.setOpertionalManagement(userName);
 					txwjspSealreg.setOpertionalManagementDate(date);
 					
			    	txwjspSealregManager.saves(txwjspSealreg,txwjspSealRegSug);
			    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"非用章范围需盖章申请审核", 
	  		  				"业务部门分管院领导审核。审核结果：通过", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
 				}else if(areaFlag.equals("4")){
 					txwjspSealregManager.doProcess(map);
 					//保存审核意见、记录
 					txwjspSealreg.setStatus(txwjspSealregManager.QUALITY_FLOW_YWC);//已完成
			    	txwjspSealRegSug.setBusinessId(id);
			    	txwjspSealRegSug.setSpUserName(user.getName());
			    	txwjspSealRegSug.setSpName("盖章人 签字");
			    	txwjspSealRegSug.setSpUserId(user.getUserId());
			    	txwjspSealRegSug.setSpTime(date);
			    	txwjspSealRegSug.setSeal(seal);//印章id
			    	txwjspSealRegSug.setSpLevel(areaFlag);
			    	txwjspSealRegSug.setStatus(txwjspSealregManager.QUALITY_FLOW_YWC);
			    	txwjspSealRegSug.setSpResult(txwjspSealregManager.QUALITY_FLOW_SHTG);
				    
			    	//保存业务表数据
 					txwjspSealreg.setSealer(userName);
 					txwjspSealreg.setSealDate(date);
			    	txwjspSealregManager.saves(txwjspSealreg,txwjspSealRegSug);
			    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(id, 
	  		  				"非用章范围需盖章申请审核", 
	  		  				"盖章人签字。操作结果：通过", 
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
    	@RequestMapping(value = "ret")
    	@ResponseBody
    	public HashMap<String, Object> ret(HttpServletRequest request,String id,String cxyy,String typeCode,String areaFlag,String processId) throws Exception {
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		txwjspSealregManager.saveRet(request,id, cxyy, getCurrentUser(),typeCode,areaFlag,processId);
    		map.put("success", true);
    		return map; 
    	}
    	/**
    	 * 流程退回
    	 * @param request
    	 * @param id
    	 * @return
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "turnback")
    	@ResponseBody
    	public HashMap<String, Object> turnback(HttpServletRequest request,String id,String activityId,String seal,String areaFlag) throws Exception {
    		CurrentSessionUser user = SecurityUtil.getSecurityUser();
    		txwjspSealregManager.turnback(request,user,id);
    		return JsonWrapper.successWrapper();
    	}
    	// 查询流程步骤信息
     	@RequestMapping(value = "getFlowStep")
     	@ResponseBody
     	public ModelAndView getFlowStep(HttpServletRequest request)
     			throws Exception {

     		Map<String, Object> map = new HashMap<String, Object>();

     		map = txwjspSealregManager.getFlowStep(request.getParameter("id"));

     		ModelAndView mav = new ModelAndView("app/qualitymanage/flow_card", map);

     		return mav;

     	}
}