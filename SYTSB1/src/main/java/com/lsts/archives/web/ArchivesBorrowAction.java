package com.lsts.archives.web;

import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesBorrowSub;
import com.lsts.archives.bean.ArchivesYijina;
import com.lsts.archives.dao.ArchivesBorrowDao;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.archives.service.ArchivesBorrowSubManager;
import com.lsts.archives.service.ArchivesRzManager;
import com.lsts.archives.service.ArchivesYijinaManager;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.service.SysLogService;
import com.lsts.office.bean.OfficeMessage;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.service.QualitySgcjjybgManager;
import com.lsts.qualitymanage.service.QualityUpdateFileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("archives/borrow")
public class ArchivesBorrowAction extends SpringSupportAction<ArchivesBorrow, ArchivesBorrowManager> {

    @Autowired
    private ArchivesBorrowManager  archivesBorrowManager;
    @Autowired
    private ArchivesBorrowDao  archivesBorrowDao;
    @Autowired
    private ArchivesRzManager archivesRzManager;
    @Autowired
    private ArchivesYijinaManager  archivesYijinaManager;
    @Autowired
	private SysLogService logService;
    @Autowired
    private ArchivesBorrowSubManager archivesBorrowSubManager;
    @Autowired
	private UserManagerImpl userManagerImpl;
    @Autowired
	private MessageService messageService;
    @Autowired
	private EmployeesService employeesService;
    
    DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request, @RequestBody ArchivesBorrow archivesBorrow) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		String isSg = request.getParameter("isSg");
   		if(archivesBorrow.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_YTJ) ||
   		   archivesBorrow.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_SHZ) || 
   		   archivesBorrow.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_PASS) || 
		   archivesBorrow.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_NO_PASS)){
   			map.put("msg", "此条数据不可修改！");
			map.put("success", false);
   		}else{
   			if(StringUtil.isNotEmpty(isSg)) {
   				if("0".equals(isSg)) {
   					archivesBorrowManager.saveTask(archivesBorrow,user);
   		   			map.put("success", true);
   		   			map.put("msg", "数据保存成功！");
   				}else if("1".equals(isSg)) {
   					String report_sns = archivesBorrowManager.checkReportIsSg(request, archivesBorrow);
   					if(StringUtil.isNotEmpty(report_sns)) {
   						map.put("success", false);
   	   		   			map.put("msg", report_sns+"不为手工报告，请核对！");
   					}else {
   						archivesBorrowManager.saveTask(archivesBorrow,user);
   	   		   			map.put("success", true);
   	   		   			map.put("msg", "数据保存成功！");
   					}
   				}
   			}else {
   				map.put("success", false);
   	   			map.put("msg", "检验报告类型参数传输失败，请重试！");
   			}
   		}
   		return map;
   	}
    /**
     * 获取详细信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getDetail")
   	@ResponseBody
   	public HashMap<String, Object> getDetail(HttpServletRequest request)
   			throws Exception {
   		String id = request.getParameter("id");
   		try {
   			return archivesBorrowManager.getDetail(id);
   		} catch (Exception e) {
   			log.debug(e.toString());
   			return JsonWrapper.failureWrapperMsg("获取信息失败！");
   		}
   	}
       
   	/**
      	 * 获取流程id
      	 * */
   	@RequestMapping(value = "getLcid")
      	@ResponseBody
      	public HashMap<String, Object> getLcid(HttpServletRequest request, String id) throws Exception{
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		String a=archivesBorrowDao.getLcid(id,request.getParameter("uId")).toString();
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
      		String a=archivesBorrowDao.getprocess_id(id).toString();
      		String process_id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
       			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
      		map.put("process_id", process_id);
      		map.put("success", true);
   		return map;
   	}
       
    
    /**
     * 验证报告编号
     * */
    @RequestMapping(value = "bgbh")
    @ResponseBody
  	public HashMap<String, Object> bgbh(String reportNumber) throws Exception {
	   	HashMap<String, Object> map = new HashMap<String, Object>();
	   	List list=archivesBorrowDao.getBgbh(reportNumber);
    	if (list != null && list.size() > 0) {	
    		map.put("success", true);
    	}else{
    		map.put("success", false);
			map.put("msg", "此报告编号不存在，请重新输入！！");
    	}
	   	return map;
  	}
       /**
        * 计算日期
        * */
    @RequestMapping(value = "qx")
   	@ResponseBody
   	public HashMap<String, Object> qx(String d1,String d2) throws Exception {
	   	HashMap<String, Object> map = new HashMap<String, Object>();
	     int a=86400000*15;
	     Date date = sdf.parse(d1);
	     Date date1 = sdf.parse(d2);
	     int q=(int) (date1.getTime()-date.getTime());
	     if(date1.getTime()>date.getTime()){
		     if(q>a){
		    	 map.put("success", false);
				 map.put("msg", "返还时间大于十五天");
		     }else{
		    	 map.put("success", true);
		     }
	     }else{
	    	 map.put("success", false);
			 map.put("msg", "返还时间不能小于申请时间");
	     }
	   		return map;
   		
   	}
       /**
        * 计算日期
        * */
//    @RequestMapping(value = "rq")
//   	@ResponseBody
//   	public HashMap<String, Object> rq(String d1,String d2) throws Exception {
//   	HashMap<String, Object> map = new HashMap<String, Object>();
//     int a=86400000*15;
//     Date date = sdf.parse(d1);
//     Date date1 = sdf.parse(d2);
//     int q=(int) (date1.getTime()-date.getTime());
//     if(date1.getTime()>date.getTime()){
//	     if(q>a){
//	    	 map.put("success", false);
//			 map.put("msg", "返还时间大于十五天");
//	     }else{
//	    	 map.put("success", true);
//	     }
//     }else{
//    	 map.put("success", false);
//		 map.put("msg", "返还时间不能小于申请时间");
//     }
//   		return map;
//   		
//   	}
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
      		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借阅申请 ");
      		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
      		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
      		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
      		
      		
      		if (StringUtil.isEmpty(id)) {
      			return JsonWrapper.failureWrapper("参数错误！");
      		} else {
      			// 启动流程
      			if (StringUtil.isNotEmpty(flowId)) {
      				archivesBorrowManager.doStartPress(map);
      				//改变状态
      				ArchivesBorrow archivesBorrow=archivesBorrowManager.get(id);
      				archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_YTJ);
      				archivesBorrowManager.save(archivesBorrow);
      				List<ArchivesBorrowSub> archivesBorrowSubs = archivesBorrowSubManager.getArchivesBorrowSubs(id);
      				String applyType=archivesBorrow.getApplyType();
  					if(applyType.equals("1")){
  						applyType="借阅";
  					}else if(applyType.equals("2")){
  						applyType="查阅";
  					}else if(applyType.equals("3")){
  						applyType="复制";
  					}else{
  						applyType="";
  					}
      				if(archivesBorrowSubs!=null&&archivesBorrowSubs.size()>0){
      					for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
      						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  			  		  		logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId())?archivesBorrowSub.getFkReportId():"未获取到报告ID", 
  			  		  				"提交档案借阅申请", 
  			  		  				"提交档案借阅申请，申请类型为："+applyType+"。操作结果：已提交", 
  			  		  				user != null ? user.getId() : "未获取到操作用户编号",
  			  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  			  						request != null ? request.getRemoteAddr() : "");
      						}
      				}else{
      					if(StringUtil.isNotEmpty(archivesBorrow.getReportNumberId())){
          					String[] reportNumberIds=archivesBorrow.getReportNumberId().split(",");
          					for(String reportNumberId:reportNumberIds){
          						if(StringUtil.isNotEmpty(reportNumberId)){
          						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
      			  		  		logService.setLogs(reportNumberId, 
      			  		  				"提交档案借阅申请", 
      			  		  				"提交档案借阅申请，申请类型为："+applyType+"。操作结果：已提交", 
      			  		  				user != null ? user.getId() : "未获取到操作用户编号",
      			  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
      			  						request != null ? request.getRemoteAddr() : "");
          						}
          					}
          					
          				}
      				}
      			} else {
      				return JsonWrapper.failureWrapper("流程ID为空！");
      			}

      			return JsonWrapper.successWrapper(id);
      		}

      	}
    /**
     * 领取
     * */   
    @RequestMapping(value = "getlq")
 	@ResponseBody
 	public Map<String, Object> getlq(ServletRequest request,String id) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();
 		String reportReceiptorId = request.getParameter("reportReceiptorId");
 		User usertemp = userManagerImpl.get(reportReceiptorId);
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		ArchivesBorrow archivesBorrow=archivesBorrowManager.get(id);
 		archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYLQ);
 		//领取报告人
 		archivesBorrow.setReportReceiptor(usertemp.getName());
 		archivesBorrow.setReportReceiptorId(reportReceiptorId);
 		archivesBorrow.setReportReceiptDate(new Date());
 		//交付报告人
 		archivesBorrow.setJfbgr(user.getName());
 		archivesBorrow.setJfbgrId(user.getId());
 		archivesBorrow.setJfbgsj(new Date());
 		archivesBorrowManager.save(archivesBorrow);
 		List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrowSubManager.getArchivesBorrowSubs(id);
 		if(archivesBorrowSubs!=null&&archivesBorrowSubs.size()>0){
				for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId())?archivesBorrowSub.getFkReportId():"未获取到报告ID", 
	  		  				"档案借阅领取", 
	  		  				"档案借阅领取，操作结果：已领取", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
					}
		}else{
			if(StringUtil.isNotEmpty(archivesBorrow.getReportNumberId())){
				String[] reportNumberIds=archivesBorrow.getReportNumberId().split(",");
				for(String reportNumberId:reportNumberIds){
					if(StringUtil.isNotEmpty(reportNumberId)){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(reportNumberId, 
	  		  				"档案借阅领取", 
	  		  				"档案借阅领取，操作结果：已领取", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
					}
				}
				
			}
		}
		//保存到日志
//		archivesRzManager.setJyrz("借阅申请", archivesBorrow.getIdentifier(),archivesBorrow.getApplyUnit(),
//				archivesBorrow.getApplyUnitId(),user.getName(),user.getId(),
//				archivesBorrow.getApplyTime(),archivesBorrow.getReportNumber(),new Date(),id,"报告已领取");
		
		ArchivesYijina archivesYijina=new ArchivesYijina();
		archivesYijina.setFileId(id);
		archivesYijina.setAuditMan(user.getName());
   		archivesYijina.setAuditTime(new Date());
   		archivesYijina.setAuditManId(user.getId());
		archivesYijina.setAss("");//签字id
		archivesYijina.setSeal("");//盖章id
		archivesYijina.setReturnName("");//退回环节名称
		archivesYijina.setAuditStep("报告已领取");
		//archivesYijina.setAuditResult("通过");
		//archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
		archivesYijina.setBusinessName("借阅申请");
		archivesYijinaManager.save(archivesYijina);
		
 		map.put("success", true);
 		return map;
	}
   /**
    * 归还
    * */   
   	@RequestMapping(value = "getgh")
	@ResponseBody
	public Map<String, Object> getgh(ServletRequest request,String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();   
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		ArchivesBorrow archivesBorrow=archivesBorrowManager.get(id);
		List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrowSubManager.getArchivesBorrowSubs(id);
 		if(archivesBorrowSubs!=null&&archivesBorrowSubs.size()>0){
				for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
					if(archivesBorrowSub.getIsBack().equals("0")){
						archivesBorrowSub.setIsBack("1");//修改子表归还状态为已归还
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId())?archivesBorrowSub.getFkReportId():"未获取到报告ID", 
		  		  				"档案借阅归还", 
		  		  				"档案借阅归还，操作结果：已归还", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
						}
					}
				if(archivesBorrowSubManager.checkAllBack(id)){
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYGH);
				}else{
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYBFGH);
				}
		}else{
			if(StringUtil.isNotEmpty(archivesBorrow.getReportNumberId())){
				String[] reportNumberIds=archivesBorrow.getReportNumberId().split(",");
				for(String reportNumberId:reportNumberIds){
					if(StringUtil.isNotEmpty(reportNumberId)){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(reportNumberId, 
	  		  				"档案借阅归还", 
	  		  				"档案借阅归还，操作结果：已归还", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
					}
				}
				
			}
			archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYGH);
		}
		archivesBorrow.setFhbgr(user.getName());
		archivesBorrow.setFhbgsj(new Date());
		archivesBorrowManager.save(archivesBorrow);
		//保存到日志
//		archivesRzManager.setJyrz("借阅申请", archivesBorrow.getIdentifier(),archivesBorrow.getApplyUnit(),
//				archivesBorrow.getApplyUnitId(),user.getName(),user.getId(),
//				archivesBorrow.getApplyTime(),archivesBorrow.getReportNumber(),new Date(),id,"报告已归还");
		
		ArchivesYijina archivesYijina=new ArchivesYijina();
		archivesYijina.setFileId(id);
		archivesYijina.setAuditMan(user.getName());
   		archivesYijina.setAuditTime(new Date());
   		archivesYijina.setAuditManId(user.getId());
		archivesYijina.setAss("");//签字id
		archivesYijina.setSeal("");//盖章id
		archivesYijina.setReturnName("");//退回环节名称
		archivesYijina.setAuditStep("报告已归还");
		//archivesYijina.setAuditResult("通过");
		//archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
		archivesYijina.setBusinessName("借阅申请");
		archivesYijinaManager.save(archivesYijina);
		map.put("success", true);
		return map;
	}
   	
   	/**
     * 撤销归还
     * */   
    	@RequestMapping(value = "cxgetgh")
 	@ResponseBody
 	public Map<String, Object> cxgetgh(ServletRequest request,String id) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();   
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		ArchivesBorrow archivesBorrow=archivesBorrowManager.get(id);
 		archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYLQ);
 		archivesBorrow.setFhbgr(null);
 		archivesBorrow.setFhbgsj(null);
 		archivesBorrowManager.save(archivesBorrow);
 		List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrowSubManager.getArchivesBorrowSubs(id);
 		if(archivesBorrowSubs!=null&&archivesBorrowSubs.size()>0){
				for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
					if(archivesBorrowSub.getIsBack().equals("1")){
						archivesBorrowSub.setIsBack("0");//修改子表归还状态为未归还
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		  		logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId())?archivesBorrowSub.getFkReportId():"未获取到报告ID", 
		  		  				"档案借阅撤销归还", 
		  		  				"档案借阅撤销归还，操作结果：已领取", 
		  		  				user != null ? user.getId() : "未获取到操作用户编号",
		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
		  						request != null ? request.getRemoteAddr() : "");
						}
					}
		}else{
			if(StringUtil.isNotEmpty(archivesBorrow.getReportNumberId())){
				String[] reportNumberIds=archivesBorrow.getReportNumberId().split(",");
				for(String reportNumberId:reportNumberIds){
					if(StringUtil.isNotEmpty(reportNumberId)){
					//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		  		logService.setLogs(reportNumberId, 
	  		  				"档案借阅撤销归还", 
	  		  				"档案借阅撤销归还，操作结果： 已领取", 
	  		  				user != null ? user.getId() : "未获取到操作用户编号",
	  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	  						request != null ? request.getRemoteAddr() : "");
					}
				}
				
			}
		}
 		//保存到日志
//		archivesRzManager.setJyrz("借阅申请", archivesBorrow.getIdentifier(),archivesBorrow.getApplyUnit(),
//				archivesBorrow.getApplyUnitId(),user.getName(),user.getId(),
//				archivesBorrow.getApplyTime(),archivesBorrow.getReportNumber(),new Date(),id,"撤销归还");
		
		ArchivesYijina archivesYijina=new ArchivesYijina();
		archivesYijina.setFileId(id);
		archivesYijina.setAuditMan(user.getName());
   		archivesYijina.setAuditTime(new Date());
   		archivesYijina.setAuditManId(user.getId());
		archivesYijina.setAss("");//签字id
		archivesYijina.setSeal("");//盖章id
		archivesYijina.setReturnName("");//退回环节名称
		archivesYijina.setAuditStep("撤销归还");
		//archivesYijina.setAuditResult("通过");
		//archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
		archivesYijina.setBusinessName("借阅申请");
		archivesYijinaManager.save(archivesYijina);
 		map.put("success", true);
 		return map;
 	}
	@Override
   	public HashMap<String, Object> delete(String ids) throws Exception {
   		HashMap<String, Object> map = new HashMap<String, Object>();
   			
   		ArchivesBorrow archivesBorrow=archivesBorrowManager.get(ids);
 		if(archivesBorrow.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_PASS)){
			map.put("msg", "此条数据已通过不可删除！");
			map.put("success", false);
 		}else{
 			archivesBorrowManager.delete(ids);
			map.put("msg", "数据删除成功！");
			map.put("success", true);
   		}
   		
   		return map;
   		//return JsonWrapper.successWrapper();
   	}
	/**
	 * 发送消息提醒
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "sendMessage")
	@ResponseBody
	public HashMap<String, Object> sendMessage(HttpServletRequest request, String ids){
		try {	
			String idsArr[]=ids.split(",");
			SimpleDateFormat formatData = new SimpleDateFormat("yyyy年MM月dd日");
			for(String id:idsArr) {
				ArchivesBorrow archivesBorrow=archivesBorrowManager.get(id);
				//获取未归还的借阅报告信息
				List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrowSubManager.getArchivesBorrowSubs(id,"0");
				//由于档案借阅中的申请人id保存的是employee的id故在此处查询员工手机号
				Employee employee = employeesService.get(archivesBorrow.getProposerId());
				String destNumber = employee.getMobileTel();
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("proposer", archivesBorrow.getProposer());
				map1.put("applyTime", formatData.format(archivesBorrow.getApplyTime().getTime()));
				map1.put("identifier", archivesBorrow.getIdentifier());
				map1.put("reportCount", archivesBorrowSubs.size());
				//发送消息
				messageService.sendMassageByConfig(request, archivesBorrow.getId(), destNumber,"", "archivesBorrow", archivesBorrow.getProposerId(), map1, null,
						null,Constant.INSPECTION_CORPID,Constant.INSPECTION_PWD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("发送消息失败，请重试！");
		}
		return JsonWrapper.successWrapper("消息发送成功!");
	}
}