package com.lsts.qualitymanage.web;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.khnt.base.Factory;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.worktask.bean.Worktask;
import com.khnt.pub.worktask.service.WorktaskManager;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualitySug;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.QualityZssqSub;
import com.lsts.qualitymanage.dao.QualityZssqDao;
import com.lsts.qualitymanage.service.QualitySgcjjybgManager;
import com.lsts.qualitymanage.service.QualitySugManager;
import com.lsts.qualitymanage.service.QualityZssqManager;
import com.lsts.qualitymanage.service.QualityZssqSubManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("qualitymanage/QualityZssqAction")
public class QualityZssqAction extends SpringSupportAction<QualityZssq, QualityZssqManager> {
	
    @Autowired
    private QualityZssqManager  qualityZssqManager;
    @Autowired
    private QualitySugManager  qualitySugManager;
    @Autowired
    private QualityZssqSubManager  qualityZssqSubManager;
    @Autowired
    private QualityZssqDao  qualityZssqDao;
    @Autowired
	private MessageService messageService;
    @Autowired
	private EmployeesDao employeesDao;
    @Autowired
	private SysLogService logService;
    @Autowired
	private WorktaskManager worktaskManager;
    @Autowired
	private ActivityManager activityManager;
    @Autowired
	private ProcessManager processManager;
    /**
   	 * 获取流程id
   	 * */
	@RequestMapping(value = "getLcid")
   	@ResponseBody
   	public HashMap<String, Object> getLcid(String id) throws Exception{
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		String a=qualityZssqDao.getLcid(id).toString();
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
   		String a=qualityZssqDao.getprocess_id(id).toString();
   		String process_id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
   		map.put("process_id", process_id);
   		map.put("success", true);
		return map;
	}
    
	@RequestMapping(value = "saveZssq")
	@ResponseBody
    public HashMap<String, Object> saveZssq(HttpServletRequest request,@RequestBody QualityZssq qualityZssq) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_NO_NEXT);
		qualityZssqManager.saveTask(qualityZssq,user);
	    map.put("success", true);
	    
		return map;
    	
    }
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return qualityZssqManager.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取信息失败！");
		}
	}
	@Override
   	public HashMap<String, Object> delete(String ids) throws Exception {
   		HashMap<String, Object> map = new HashMap<String, Object>();
   			
   		QualityZssq qualitySgcjjybg=qualityZssqManager.get(ids);
	 		if(qualitySgcjjybg.getStatus().equals(QualitySgcjjybgManager.ZL_SGCJ_PASS)){
				map.put("msg", "此条数据已通过不可删除！");
				map.put("success", false);
	 		}else{
	 			qualityZssqManager.delete(ids);
				map.put("msg", "数据删除成功！");
				map.put("success", true);
	   		}
   		
   		return map;
   		//return JsonWrapper.successWrapper();
   	}
	/**
 	 * 批量审核
 	 * @param request
 	 * @return
	 * @throws Exception 
 	 */
	@RequestMapping(value = "zssq_bathCheck")
	@ResponseBody
 	public Map<String,Object> zssq_bathCheck(HttpServletRequest request) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		Map<String,Object> map = new HashMap<String,Object>();
 		String ids = request.getParameter("ids");
 		String result = request.getParameter("result");
 		String fileNumberTemp = "";
 		if(StringUtil.isNotEmpty(ids)) {
 			String[]id = ids.split(",");
 			for(int i=0;i<id.length;i++) {
 				QualityZssq qualityZssq = qualityZssqManager.get(id[i]);
 				String typeCode = "TJY2_ZL_ZSSQ1";
 				String worktaskId = getLcid(id[i]).get("ids").toString();
 				Worktask worktask = (Worktask)worktaskManager.get(worktaskId);
 				String activityId = worktask.getServiceId();
 				Activity activity = activityManager.get(activityId);
	 				String areaFlag = "";
	 				if(StringUtil.isNotEmpty(activity.getFunction())) {
	 					if(activity.getFunction().contains("TJY2_ZL_ZSSQ_SQSH")) {
	 						areaFlag = "1";
	 						qualityZssq.setApplyOpinion("1".equals(result)?"同意":"不同意");
	 					}else if(activity.getFunction().contains("TJY2_ZL_ZSSQ_FWSH")){
	 						areaFlag = "2";
	 						qualityZssq.setServiceOpinion("1".equals(result)?"同意":"不同意");
	 					}else if(activity.getFunction().contains("TJY2_ZL_ZSSQ_FWJSR")){
	 						areaFlag = "3";
	 						qualityZssq.setRecipientOpinion("1".equals(result)?"同意":"不同意");
	 					}else if(activity.getFunction().contains("TJY2_ZL_ZSSQ_FWBJSRJD")){
	 						areaFlag = "3";
	 						qualityZssq.setRecipientOpinion("1".equals(result)?"同意":"不同意");
	 					}
	 				}
	 				try {
						if("1".equals(result)) {
							sgcjjybgsh(request, areaFlag, id[i], typeCode, "", activityId, qualityZssq);
						}else if("0".equals(result)) {
							Process process = processManager.getServiceProcess(id[i]);
			 				String processId = process.getId();
							sgcjjybgth(request, areaFlag, id[i], typeCode, "", activityId, processId, qualityZssq);
						}
						if(StringUtil.isEmpty(fileNumberTemp)) {
							fileNumberTemp = qualityZssq.getFileNumber()+" 审核成功。";
						}else {
							fileNumberTemp += "<br/>"+qualityZssq.getFileNumber()+" 审核成功。";
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(StringUtil.isEmpty(fileNumberTemp)) {
							fileNumberTemp = qualityZssq.getFileNumber()+" 审核失败，请重试或进行单份审核。";
						}else {
							fileNumberTemp += "<br/>"+qualityZssq.getFileNumber()+" 审核失败，请重试或进行单份审核。";
						}
					}
 			}
 			map.put("success", true);
 			map.put("msg", fileNumberTemp);
 		}else {
 			map.put("success", false);
 			map.put("msg", "未获取到业务信息！");
 		}
		return map;
 		
 	}
    
    @RequestMapping(value = "sgcjjybgsh")
	@ResponseBody
   	public HashMap<String, Object> sgcjjybgsh(HttpServletRequest request,String areaFlag,String id,
   			String typeCode, String status,String activityId,@RequestBody QualityZssq qualityZssq) throws Exception {
    	String inspection_jd_org_id=Factory.getSysPara().getProperty("INSPECTION_JD_ORG_ID");//机电检验部门ID
		String inspection_cy_org_id=Factory.getSysPara().getProperty("INSPECTION_CY_ORG_ID");//承压检验部门ID
    	//流程记录表
   		QualitySug qualitySug=new QualitySug();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	String yijian1 = qualityZssq.getApplyOpinion();
    	String yijian2 = qualityZssq.getServiceOpinion();
    	String yijian3 = qualityZssq.getRecipientOpinion();
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "无原始资料打印检验报告/证书申请表");
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 审批流程
   			if (StringUtil.isNotEmpty(activityId)) {
   				if(areaFlag.equals("1") || areaFlag.equals("2")){//申请部门负责人,服务部门负责人
   					
   					QualityZssq qualitySgcjjybg=qualityZssqManager.get(id);
   	   				qualitySgcjjybg.setStatus(QualityZssqManager.ZL_SGCJ_SHZ);
   	   				String departmentId=qualitySgcjjybg.getDepartmentId();
   	   				if(areaFlag.equals("1")){
   	   					//审批记录
	   	   				qualitySug.setBusinessId(id);
	   	   				qualitySug.setBusinessName(qualitySugManager.ZL_WYSJL);
	   	   				qualitySug.setSug(qualityZssq.getApplyOpinion());
	   	   				qualitySug.setSpUserId(user.getId());
	   	   				qualitySug.setSpUserName(user.getName());
	   	   				qualitySug.setSpTime(new Date());
	   	   				qualitySug.setSpResult(qualitySugManager.ZL_WYSJL_TG);
	   	   			    qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_SQBSH);
	   	   			    qualitySug.setStatus(QualityZssqManager.ZL_SGCJ_SHZ);
	   	   			    qualitySugManager.save(qualitySug);
	   	   			    qualityZssqManager.doProcess(map);
	   	   			    qualitySgcjjybg.setBmfzr(user.getName());
	   					qualitySgcjjybg.setApplyOpinionTime(new Date());
	   					
	   					if(inspection_jd_org_id.indexOf(departmentId)!=-1){
	   						//申请部门是机电部门时，向杨莉、邓雯雯、马竺君、彭继庆发送微信
	   						for(String emp_id:Factory.getSysPara().getProperty("YUANSHI_JD_SHENHE_EMP_ID1").split(",")){
		   						//获取电话
		   						String tel=employeesDao.getMobileTel(emp_id).toString();
		   						String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
		   								.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
		   						System.out.println("电话号码为："+destNumber);
		   						messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
		   								"您部门有【"+qualitySgcjjybg.getDepartment()+"】"+qualitySgcjjybg.getApplyName()+" 提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）需要审核，请及时处理！", destNumber);
		   					}
	   					}else if(inspection_cy_org_id.indexOf(departmentId)!=-1){
	   						//申请部门是承压部门时，向杨莉、秦云云发送微信
	   						for(String emp_id:Factory.getSysPara().getProperty("YUANSHI_CY_SHENHE_EMP_ID1").split(",")){
		   						//获取电话
		   						String tel=employeesDao.getMobileTel(emp_id).toString();
		   						String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
		   								.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
		   						System.out.println("电话号码为："+destNumber);
		   						messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
		   								"您部门有【"+qualitySgcjjybg.getDepartment()+"】"+qualitySgcjjybg.getApplyName()+" 提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）需要审核，请及时处理！", destNumber);
		   					}
	   					}
	   					qualityZssqManager.setCheckLog(request,"申请部门负责人审核，审核结果：通过",id,user);
	   					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	   	  		  		logService.setLogs(id, 
	   	  		  				"无原始资料打印申请审核", 
	   	  		  				"申请部门负责人审核，审核结果：通过", 
	   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	   	  						request != null ? request.getRemoteAddr() : "");
   	   				}else if(areaFlag.equals("2")){
	   	   				JSONObject dataBus = new JSONObject();
	   	   				String orgid=	qualitySgcjjybg.getDepartmentId();
	   	   				if(orgid.equals("100020") || orgid.equals("100021")|| orgid.equals("100022")|| orgid.equals("100023")|| orgid.equals("100024")|| orgid.equals("100063")){
	   	   					dataBus.put("org", "1");
	   	   					}else{
	   	   						dataBus.put("org", "2");
	   	   					}
	   	   				map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	   	   				//审批记录
	   	   				qualitySug.setBusinessId(id);
	   	   				qualitySug.setBusinessName(qualitySugManager.ZL_WYSJL);
	   	   				qualitySug.setSug(qualityZssq.getServiceOpinion());
	   	   				qualitySug.setSpUserId(user.getId());
	   	   				qualitySug.setSpUserName(user.getName());
	   	   				qualitySug.setSpTime(new Date());
	   	   				qualitySug.setSpResult(qualitySugManager.ZL_WYSJL_TG);
	   	   			    qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_FWBSH);
	   	   			    qualitySug.setStatus(QualityZssqManager.ZL_SGCJ_SHZ);
	   	   			    qualitySugManager.save(qualitySug);
	   	   			    qualityZssqManager.doProcess(map);
	   	   			    qualitySgcjjybg.setFwbfzr(user.getName());
	   					qualitySgcjjybg.setServiceOpinionTime(new Date());
	   					if(inspection_jd_org_id.indexOf(departmentId)!=-1){
	   						//申请部门是机电部门时，向邓雯雯、马竺君、彭继庆发送微信
	   						for(String emp_id:Factory.getSysPara().getProperty("YUANSHI_JD_SHENHE_EMP_ID2").split(",")){
		   						//获取电话
		   						String tel=employeesDao.getMobileTel(emp_id).toString();
		   						String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
		   								.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
		   						System.out.println("电话号码为："+destNumber);
		   						messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
			 							"您部门有【"+qualitySgcjjybg.getDepartment()+"】"+qualitySgcjjybg.getApplyName()+" 提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）需要服务部接收，请及时处理！", destNumber);
		   					}
	   					}else if(inspection_cy_org_id.indexOf(departmentId)!=-1){
	   						//申请部门是承压部门时，向秦云云发送微信
	   						for(String emp_id:Factory.getSysPara().getProperty("YUANSHI_CY_SHENHE_EMP_ID2").split(",")){
		   						//获取电话
		   						String tel=employeesDao.getMobileTel(emp_id).toString();
		   						String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
		   								.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
		   						System.out.println("电话号码为："+destNumber);
		   						messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
			 							"您部门有【"+qualitySgcjjybg.getDepartment()+"】"+qualitySgcjjybg.getApplyName()+" 提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）需要服务部接收，请及时处理！", destNumber);
		   					}
	   					}
	   					qualityZssqManager.setCheckLog(request,"服务部部门负责人审核，审核结果：通过",id,user);
	   					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	   	  		  		logService.setLogs(id, 
	   	  		  				"无原始资料打印申请审核", 
	   	  		  				"服务部部门负责人审核，审核结果：通过", 
	   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	   	  						request != null ? request.getRemoteAddr() : "");
   	   				}
   	   				qualitySgcjjybg.setApplyOpinion(yijian1);
   	   				qualitySgcjjybg.setServiceOpinion(yijian2);
   	   				qualitySgcjjybg.setRecipientOpinion(yijian3);
   	   				qualityZssqManager.save(qualitySgcjjybg);
   				}else if(areaFlag.equals("3")){//服务部门经办人
   					QualityZssq qualitySgcjjybg=qualityZssqManager.get(id);
   	   				qualitySgcjjybg.setStatus(QualityZssqManager.ZL_SGCJ_PASS);
   	   				
   	   			    String tel=employeesDao.getMobileTel(qualitySgcjjybg.getPeopleConcernedId()).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
							.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
					System.out.println("电话号码为："+destNumber);
					messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
							"您提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）业务服务部已确认，望知晓！", destNumber);
   	   			    qualitySgcjjybg.setApplyOpinion(yijian1);
	   				qualitySgcjjybg.setServiceOpinion(yijian2);
	   				qualitySgcjjybg.setRecipientOpinion(yijian3);
	   				qualitySgcjjybg.setFwbjbr(user.getName());
	   				qualitySgcjjybg.setRecipientOpinionTime(new Date());
	   				qualityZssqManager.save(qualitySgcjjybg);
   	   			
	   	   			//审批记录
	   				qualitySug.setBusinessId(id);
	   				qualitySug.setBusinessName(qualitySugManager.ZL_WYSJL);
	   				qualitySug.setSug(qualityZssq.getRecipientOpinion());
	   				qualitySug.setSpUserId(user.getId());
	   				qualitySug.setSpUserName(user.getName());
	   				qualitySug.setSpTime(new Date());
	   				qualitySug.setSpResult(qualitySugManager.ZL_WYSJL_TG);
	   			    qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_FWBJS);
	   			    qualitySug.setStatus(QualityZssqManager.ZL_SGCJ_PASS);
	   			    qualitySugManager.save(qualitySug);
	   	   			
	  	   			qualityZssqManager.doProcess(map);
	  	   			qualityZssqManager.setCheckLog(request,"服务部承压经办人审核，审核结果：通过",id,user);
	  	   			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"服务部承压经办人审核，审核结果：通过", 
   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
   	  						request != null ? request.getRemoteAddr() : "");
   				}else if(areaFlag.equals("4")){//服务部门经办人
   					QualityZssq qualitySgcjjybg=qualityZssqManager.get(id);
   	   				qualitySgcjjybg.setStatus(QualityZssqManager.ZL_SGCJ_PASS);
   	   				
   	   				String tel=employeesDao.getMobileTel(qualitySgcjjybg.getPeopleConcernedId()).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(tel.toString()
							.substring(1,tel.toString().length()-1)).replaceAll("'$1'");
					System.out.println("电话号码为："+destNumber);
					messageService.sendWxMsg(request,qualitySgcjjybg.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
							"您提交的“无原始资料打印申请”（编号："+qualitySgcjjybg.getFileNumber()+"）业务服务部已确认，望知晓！", destNumber);
   	   				qualitySgcjjybg.setApplyOpinion(yijian1);
	   				qualitySgcjjybg.setServiceOpinion(yijian2);
	   				qualitySgcjjybg.setRecipientOpinion(yijian3);
	   				qualitySgcjjybg.setFwbjbr(user.getName());
	   				qualitySgcjjybg.setRecipientOpinionTime(new Date());
	   				qualityZssqManager.save(qualitySgcjjybg);
	   	   			
	   	   			//审批记录
	   				qualitySug.setBusinessId(id);
	   				qualitySug.setBusinessName(qualitySugManager.ZL_WYSJL);
	   				qualitySug.setSug(qualityZssq.getRecipientOpinion());
	   				qualitySug.setSpUserId(user.getId());
	   				qualitySug.setSpUserName(user.getName());
	   				qualitySug.setSpTime(new Date());
	   				qualitySug.setSpResult(qualitySugManager.ZL_WYSJL_TG);
	   			    qualitySug.setSpLevel("服务部机电接收");
	   			    qualitySug.setStatus(QualityZssqManager.ZL_SGCJ_PASS);
	   			    qualitySugManager.save(qualitySug);
	   	   			
	  	   			qualityZssqManager.doProcess(map);
	  	   			qualityZssqManager.setCheckLog(request,"服务部机电经办人审核，审核结果：通过",id,user);
	  	   			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"服务部机电经办人审核，审核结果：通过", 
   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
   	  						request != null ? request.getRemoteAddr() : "");
   				}else{
   					QualityZssq qualitySgcjjybg=qualityZssqManager.get(id);
   	   				qualitySgcjjybg.setStatus(QualityZssqManager.ZL_SGCJ_NO_PASS);
   	   				qualitySgcjjybg.setApplyOpinion(yijian1);
	   				qualitySgcjjybg.setServiceOpinion(yijian2);
	   				qualitySgcjjybg.setRecipientOpinion(yijian3);
	   				qualityZssqManager.save(qualitySgcjjybg);
	   				qualityZssqManager.setCheckLog(request,"未获取到审核环节，审核结果：不通过",id,user);
	   				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"未获取到审核环节，审核结果：不通过", 
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
     * 不通过
     * */
    @RequestMapping(value = "sgcjjybgth")
	@ResponseBody
	public HashMap<String, Object> sgcjjybgth(HttpServletRequest request,String areaFlag,String id,
			String typeCode, String status,String activityId,String processId,@RequestBody QualityZssq qualityZssq) throws Exception {
    	String yijian1 = qualityZssq.getApplyOpinion();
    	String yijian2 = qualityZssq.getServiceOpinion();
    	String yijian3 = qualityZssq.getRecipientOpinion();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "无原始资料打印检验报告/证书申请表");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				QualityZssq qualitySgcjjybg=qualityZssqManager.get(id);
   				qualitySgcjjybg.setStatus(QualitySgcjjybgManager.ZL_SGCJ_NO_PASS);
				//审批记录
				QualitySug qualitySug=new QualitySug();
		   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
				qualitySug.setBusinessId(id);
				qualitySug.setBusinessName(qualitySugManager.ZL_WYSJL);
				qualitySug.setSpUserId(user.getId());
				qualitySug.setSpUserName(user.getName());
				qualitySug.setSpTime(new Date());
				qualitySug.setSpResult(qualitySugManager.ZL_WYSJL_BTG);
			    qualitySug.setStatus(QualityZssqManager.ZL_SGCJ_NO_PASS);
					  
				if(areaFlag.equals("1")){
					qualitySgcjjybg.setBmfzr(user.getName());
					qualitySgcjjybg.setApplyOpinionTime(new Date());
					qualitySgcjjybg.setApplyOpinion(yijian1);
					
					qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_SQBSH);
					qualitySug.setSug(qualityZssq.getApplyOpinion());
					qualityZssqManager.setCheckLog(request,"申请部门负责人审核，审核结果：不通过",id,user);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"申请部门负责人审核，审核结果：不通过", 
   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
   	  						request != null ? request.getRemoteAddr() : "");
				} else if(areaFlag.equals("2")){
					qualitySgcjjybg.setFwbfzr(user.getName());
					qualitySgcjjybg.setServiceOpinionTime(new Date());
					qualitySgcjjybg.setServiceOpinion(yijian2);
					
					qualitySug.setSug(qualityZssq.getServiceOpinion());
					qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_FWBSH);
					qualityZssqManager.setCheckLog(request,"服务部部门负责人审核，审核结果：不通过",id,user);
				}else if(areaFlag.equals("3")){
					qualitySgcjjybg.setFwbjbr(user.getName());
					qualitySgcjjybg.setRecipientOpinionTime(new Date());
					qualitySgcjjybg.setRecipientOpinion(yijian3);
					
					qualitySug.setSpLevel(qualitySugManager.ZL_WYSJL_FWBJS);
					qualitySug.setSug(qualityZssq.getRecipientOpinion());
					qualityZssqManager.setCheckLog(request,"服务部承压经办人审核，审核结果：不通过",id,user);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"服务部承压经办人审核，审核结果：不通过", 
   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
   	  						request != null ? request.getRemoteAddr() : "");
				}else if(areaFlag.equals("4")){
					qualitySgcjjybg.setFwbjbr(user.getName());
					qualitySgcjjybg.setRecipientOpinionTime(new Date());
					qualitySgcjjybg.setRecipientOpinion(yijian3);
					
					qualitySug.setSpLevel("服务部机电接收");
					qualitySug.setSug(qualityZssq.getRecipientOpinion());
					qualityZssqManager.setCheckLog(request,"服务部机电经办人审核，审核结果：不通过",id,user);
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
   	  		  		logService.setLogs(id, 
   	  		  				"无原始资料打印申请审核", 
   	  		  				"服务部机电经办人审核，审核结果：不通过", 
   	  		  				user != null ? user.getId() : "未获取到操作用户编号",
   	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
   	  						request != null ? request.getRemoteAddr() : "");
				}
				try {
					qualitySugManager.save(qualitySug);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					qualityZssqManager.stop(map1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					qualityZssqManager.save(qualitySgcjjybg);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
    
    /**
     * 提交流程
     * @param request
     * @param id
     * @param flowId
     * @param typeCode
     * @param status
     * @param activityId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "subFolws")
  	@ResponseBody
  	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
  			String typeCode, String status,String activityId) throws Exception {
  		Map<String, Object> map = new HashMap<String, Object>();
  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
  		//流程传参
  		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
  		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
  		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "无原始资料打印检验报告/证书申请表");
  		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
  		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
  		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
  		

	    	
	    	
	    	
  		if (StringUtil.isEmpty(id)) {
  			return JsonWrapper.failureWrapper("参数错误！");
  		} else {
  			// 启动流程
  			if (StringUtil.isNotEmpty(flowId)) {
  	  		JSONObject dataBus = new JSONObject();
  				String next_sub_op = null; 
  				String next_op_name = null ;
//  			if (userList1 != null && userList1.size() > 0) {
//  		    	for (int u = 0; u < userList1.size(); u++) {
//  		    		Object user1[] = null;
//  		    		user1 = (Object[]) userList1.get(u);
//  		    		next_sub_op=user1[0].toString();
//  		    		next_op_name=user1[1].toString();
//  		    	}
//  			}
//  				next_sub_op="402884c4477c9bac01477ff4727e0063";
//  	   		next_op_name="谢方";
//  	   			JSONArray pts = new JSONArray();
//  	   			JSONObject pt = new JSONObject();
//  	   			pt.put("id", next_sub_op);
//  	   			pt.put("name", next_op_name);
//  	   			pts.put(pt);
//  		    	dataBus.put("paticipator", pts);
//  		    	map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
//  		    	给流程的指定下一步操作人为部门负责人
//  		    	txwjspSealregManager.doProcess(map);
  				qualityZssqManager.doStartPress(map);
  				//改变状态
  				QualityZssq qualityZssq=qualityZssqManager.get(id);
  				qualityZssq.setStatus(ArchivesBorrowManager.DA_JYSQ_YTJ);
  				qualityZssqManager.save(qualityZssq);
  				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"提交无原始资料打印申请", 
  		  				"提交无原始资料打印申请至部门负责人，操作结果：已提交", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
  				List<QualityZssqSub> qualityZssqSubList=qualityZssqSubManager.getQualityZssqSubs(id);
  				if(qualityZssqSubList!=null){
  					for(QualityZssqSub qualityZssqSub:qualityZssqSubList){
  	  	  	  			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  	  	  		  		logService.setLogs(StringUtil.isNotEmpty(qualityZssqSub.getReportFk()) ? qualityZssqSub.getReportFk() : "此为手动填写的报告，无ID外键", 
  	  	  		  				"提交无原始资料打印申请", 
  	  	  		  				"提交无原始资料打印申请，操作结果：已提交", 
  	  	  		  				user != null ? user.getId() : "未获取到操作用户编号",
  	  	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  	  	  						request != null ? request.getRemoteAddr() : "");
  	  	  	  		}
  				}
  			} else {
  				return JsonWrapper.failureWrapper("流程ID为空！");
  			}

  			return JsonWrapper.successWrapper(id);
  		}

  	}
    /**
     * 无 原 始 资 料 打 印 检 验 报 告 / 证 书 申 请 表  归还
     * @param request
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "gh")
  	@ResponseBody
  	public Map<String, Object> gh(ServletRequest request,String ids) throws Exception {
  		Map<String, Object> map = new HashMap<String, Object>();
  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
  		String[] id=ids.split(",");
  		for(int i=0;i<id.length;i++ ){
  			QualityZssq qualityZssq=qualityZssqManager.get(id[i]);
  			List<QualityZssqSub> qualityZssqSubList=qualityZssqSubManager.getQualityZssqSubs(id[i]);
  			if(qualityZssqSubList!=null&&qualityZssqSubList.size()>0){
  				for(QualityZssqSub qualityZssqSub:qualityZssqSubList){
  	  	  			if(StringUtil.isEmpty(qualityZssqSub.getIsBack()) || qualityZssqSub.getIsBack().equals("0")){
  	  	  			qualityZssqSub.setIsBack("1");//修改子表归还状态为已归还
  	  	  			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  	  		  		logService.setLogs(StringUtil.isNotEmpty(qualityZssqSub.getReportFk()) ? qualityZssqSub.getReportFk() : "此为手动填写的报告，无ID外键", 
  	  		  				"无原始资料打印申请报告归还", 
  	  		  				"无原始资料打印申请报告归还，操作结果：已归还", 
  	  		  				user != null ? user.getId() : "未获取到操作用户编号",
  	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  	  						request != null ? request.getRemoteAddr() : "");
  	  	  			}
  	  	  		}
  				if(qualityZssqSubManager.checkAllBack(id[i])){
  	  	  			qualityZssq.setNextPeople(user.getName());
  	  	  			qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_YES_NEXT);//修改主表归还状态为已归还
  	  	  			qualityZssq.setNextTime(new Date());
  	  	  			qualityZssq.setReturnTime(new Date());
  		  	  		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  	  		  		logService.setLogs(id[i], 
  	  		  			"无原始资料打印申请报告归还", 
  			  				"无原始资料打印申请报告归还，操作结果：已归还",  
  	  		  				user != null ? user.getId() : "未获取到操作用户编号",
  	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  	  						request != null ? request.getRemoteAddr() : "");
  	  	  		}else{
  	  	  			qualityZssq.setNextPeople(user.getName());
  	  	  			qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_BF_NEXT);//修改主表归还状态为部分归还
  	  	  			qualityZssq.setNextTime(new Date());
  	  	  			qualityZssq.setReturnTime(new Date());
  		  	  		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  	  		  		logService.setLogs(id[i], 
  	  		  			"无原始资料打印申请报告归还", 
  			  				"无原始资料打印申请报告归还，操作结果：部分归还",  
  	  		  				user != null ? user.getId() : "未获取到操作用户编号",
  	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  	  						request != null ? request.getRemoteAddr() : "");
  	  	  		}
  			}else{
  				qualityZssq.setNextPeople(user.getName());
	  	  		qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_YES_NEXT);//修改主表归还状态为已归还
	  	  		qualityZssq.setNextTime(new Date());
	  	  		qualityZssq.setReturnTime(new Date());
  			}
  	  		qualityZssqManager.save(qualityZssq);
  		}
  		map.put("success", true);
    return map;
    }
    /**
     * 归还确认
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "ghqr")
  	@ResponseBody
  	public Map<String, Object> ghqr(ServletRequest request,String id) throws Exception {
  		Map<String, Object> map = new HashMap<String, Object>();
  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
  		QualityZssq qualityZssq=qualityZssqManager.get(id);
  		qualityZssq.setNextStatus(QualityZssqManager.ZL_SGCJ_confirm_NEXT);
  		qualityZssqManager.save(qualityZssq);
  		map.put("success", true);
    return map;
    }
    // 查询流程步骤信息
 	@RequestMapping(value = "getFlowStep")
 	@ResponseBody
 	public ModelAndView getFlowStep(HttpServletRequest request)
 			throws Exception {

 		Map<String, Object> map = new HashMap<String, Object>();

 		map = qualityZssqManager.getFlowStep(request.getParameter("id"));

 		ModelAndView mav = new ModelAndView("app/qualitymanage/flow_card", map);

 		return mav;

 	}
 	
}