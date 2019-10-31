package com.lsts.archives.web;

import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesPrint;
import com.lsts.archives.dao.ArchivesPrintDao;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.archives.service.ArchivesPrintDyrzManager;
import com.lsts.archives.service.ArchivesPrintManager;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportYjsz;
import com.lsts.report.service.ReportYjszManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("archives/print")
public class ArchivesPrintAction extends SpringSupportAction<ArchivesPrint, ArchivesPrintManager> {

    @Autowired
    private ArchivesPrintManager  archivesPrintManager;
    @Autowired
    private ArchivesBorrowManager  archivesBorrowManager;
    @Autowired
    private ArchivesPrintDyrzManager archivesPrintDyrzManager;
    @Autowired
    ArchivesPrintDao archivesPrintDao;
    @Autowired
	private SysLogService logService;
    
    /**
  	 * 获取流程id
  	 * */
	@RequestMapping(value = "getLcid")
  	@ResponseBody
  	public HashMap<String, Object> getLcid(String id) throws Exception{
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		String a=archivesPrintDao.getLcid(id).toString();
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
  		String a=archivesPrintDao.getprocess_id(id).toString();
  		String process_id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
   			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
  		map.put("process_id", process_id);
  		map.put("success", true);
		return map;
	}
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request, @RequestBody ArchivesPrint archivesPrint) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		//设置当前登记人
//   		archivesPrint.setRegistrant(user.getName());
//   		archivesPrint.setRegistrantId(user.getId());
//   		archivesPrint.setRegistrantTime(new Date());
//   		archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_WTJ);
//   		this.subFolws(archivesPrint.getId(), flowId, typeCode, status, activityId)
   		if(archivesPrint.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_YTJ) ||
   				archivesPrint.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_SHZ) || 
   				archivesPrint.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_PASS) || 
   				archivesPrint.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_NO_PASS)){
   	   			map.put("msg", "此条数据不可修改！");
   				map.put("success", false);
   		}else{
	   		archivesPrintManager.saveTask(archivesPrint,user);
	       	map.put("success", true);
   			map.put("msg", "数据保存成功！");

   	   	}
   		return map;
   	}
     /**打印
   	 * @param id
   	 * @return
   	 * @throws Exception
   	 */
   	@RequestMapping(value = "dy")
   	@ResponseBody
   	public HashMap<String, Object> dy(ServletRequest request,String id) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		if (id.isEmpty()){
   			map.put("success", false);
   			map.put("msg", "所选业务ID为空！");
   		} else {
   			ArchivesPrint archivesPrint = archivesPrintManager.get(id);
   			if(archivesPrint==null){
   				map.put("success", false);
   				map.put("msg", "数据获取失败！");
   			} else {
   				archivesPrint.setStatus(ArchivesBorrowManager.DA_DYSQ);//已打印
   				archivesPrintManager.save(archivesPrint);
   				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"提交检验报告证书打印申请", 
  		  				"提交检验报告证书打印申请至部门负责人审核。操作结果：已提交", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
   				//保存到日志
   				archivesPrintDyrzManager.setDyrz(archivesPrint.getDocumentId(), 
   						archivesPrint.getIdentifier(),user.getName(),user.getId(),
   						new Date(),archivesPrint.getApplyReason(),archivesPrint.getApplyUnit(),
   						archivesPrint.getApplyUnitId(),archivesPrint.getProposer(),
   						archivesPrint.getProposerId(),archivesPrint.getApplyTime(),id);
   				map.put("success", true);
   			}
   		}
   		return map;
   	}   
   /**
    * 提交审核
    * 
    * */
    @RequestMapping(value = "subFolws")
 	@ResponseBody
 	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,String activityId,
 			String typeCode) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "打印申请");
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
 				ArchivesPrint archivesPrint=archivesPrintManager.get(id);
 				archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_YTJ);
 				archivesPrintManager.save(archivesPrint);
 				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"提交检验报告证书打印申请", 
  		  				"提交检验报告证书打印申请至部门负责人审核。操作结果：已提交", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
 			} else {
 				return JsonWrapper.failureWrapper("流程ID为空！");
 			}

 			return JsonWrapper.successWrapper(id);
 		}

 	}
    // 查询流程步骤信息
 	@RequestMapping(value = "getFlowStep")
 	@ResponseBody
 	public ModelAndView getFlowStep(HttpServletRequest request)
 			throws Exception {

 		Map<String, Object> map = new HashMap<String, Object>();

 		map = archivesPrintManager.getFlowStep(request.getParameter("id"));

 		ModelAndView mav = new ModelAndView("app/qualitymanage/flow_card", map);

 		return mav;

 	}
}