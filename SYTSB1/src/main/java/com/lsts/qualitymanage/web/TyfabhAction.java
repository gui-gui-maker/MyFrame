package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.Tyfabh;
import com.lsts.qualitymanage.service.QualitySgcjjybgManager;
import com.lsts.qualitymanage.service.TyfabhManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("Tyfabh/a")
public class TyfabhAction extends SpringSupportAction<Tyfabh, TyfabhManager> {

    @Autowired
    private TyfabhManager  tyfabhManager;
    @Autowired
	private SysLogService logService;
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody Tyfabh tyfabh) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		
   		HashMap<String, Object> map = new HashMap<String, Object>();
   		if(tyfabh.getId()==null || tyfabh.getId().equals("")){
   			tyfabhManager.saveTyfabh(tyfabh,user);
   			map.put("success", true);
   			map.put("msg", "数据保存成功！");
   		}else{
   			//CwBorrowMoney cwMoney1=cwBorrowMoneyManager.get(cwMoney.getId());
   			if(tyfabh.getStatus().equals(TyfabhManager.ZL_TYFABH_POSS)){
   				map.put("msg", "此条数据已通过不可修改！");
   				map.put("success", false);
   	  		}else if(tyfabh.getStatus().equals(TyfabhManager.ZL_TYFABH_JBR)){
   				map.put("msg", "此条数据审核中不可修改！");
   				map.put("success", false);
   	 		}else{
   	 		tyfabhManager.saveTyfabh(tyfabh,user);
   				map.put("success", true);
   				map.put("msg", "数据保存成功！");
   			}
   		}
   		return map;
   }
       
       /**
      	 * 删除
      	 * */
      	@Override
      	public HashMap<String, Object> delete(String ids) throws Exception {
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		Tyfabh tyfabh=tyfabhManager.get(ids);
      		if(tyfabh.getStatus().equals(TyfabhManager.ZL_TYFABH_POSS)){
   				map.put("msg", "此条数据已通过不可修改！");
   				map.put("success", false);
   	  		}else if(tyfabh.getStatus().equals(TyfabhManager.ZL_TYFABH_JBR)){
   				map.put("msg", "此条数据审核中不可修改！");
   				map.put("success", false);
   	 		}else{
    			tyfabhManager.delete(ids);
	   			map.put("msg", "数据删除成功！");
	   			map.put("success", true);
      		}
      		return map;
      		//return JsonWrapper.successWrapper();
      	}
      	/**
      	 * 提交
      	 * */
      	@RequestMapping(value = "setzt")
       	@ResponseBody
       	public HashMap<String, Object> setzt(ServletRequest request,String id) throws Exception{
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		CurrentSessionUser user = SecurityUtil.getSecurityUser();
      		Tyfabh tyfabh=tyfabhManager.get(id);
      		tyfabh.setStatus(TyfabhManager.ZL_TYFABH_JBR);
      		tyfabhManager.save(tyfabh);
      		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(id, 
	  				"提交通用方案编号申请", 
	  				"提交通用方案编号申请至业务服务部经办人审核。操作结果：经办人审核中", 
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
   			map.put("success", true);
      		return map;
       	}
      	
      	/**
      	 * 审核通过
      	 * */
      	@RequestMapping(value = "submitsh")
       	@ResponseBody
       	public HashMap<String, Object> submitsh(ServletRequest request,String id,String fabhId,String fabh,String a) throws Exception{
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		CurrentSessionUser user = SecurityUtil.getSecurityUser();
      		Tyfabh tyfabh=tyfabhManager.get(id);
      		if(a.equals("2")){//通过
      			tyfabh.setStatus(TyfabhManager.ZL_TYFABH_POSS);
      			tyfabh.setFabhId(fabhId);
      			tyfabh.setFabh(fabh);
      			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
    	  		logService.setLogs(id, 
    	  				"通用方案编号申请审核", 
    	  				"业务服务部经办人审核。审核结果：审核通过", 
    	  				user != null ? user.getId() : "未获取到操作用户编号",
    					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
    					request != null ? request.getRemoteAddr() : "");
      		}else{//未通过
      			tyfabh.setStatus(TyfabhManager.ZL_TYFABH_NO_POSS);
      			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
    	  		logService.setLogs(id, 
    	  				"通用方案编号申请审核", 
    	  				"业务服务部经办人审核。操作结果：审核未通过", 
    	  				user != null ? user.getId() : "未获取到操作用户编号",
    					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
    					request != null ? request.getRemoteAddr() : "");
      		}
      		tyfabh.setYwfwbjbr(user.getName());
      		tyfabh.setYwfwbjbrTime(new Date());
      		tyfabhManager.save(tyfabh);
   			map.put("success", true);
      		return map;
       	}
      	/**
      	 * 作废申请提交
      	 */
      	@RequestMapping(value = "cancelsub")
       	@ResponseBody
       	public HashMap<String, Object> cancelsub(ServletRequest request,String id,String cancelReason) throws Exception{
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		CurrentSessionUser user = SecurityUtil.getSecurityUser();
      		Tyfabh tyfabh=tyfabhManager.get(id);
      		tyfabh.setCancelReason(URLDecoder.decode(URLDecoder.decode(cancelReason,"UTF-8"),"tuf-8"));
      		tyfabh.setStatus(TyfabhManager.ZL_TYFABH_CANCELLING);
      		tyfabhManager.save(tyfabh);
      		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(id, 
	  				"提交通用方案编号作废申请", 
	  				"提交通用方案编号作废申请至业务服务部经办人审核。操作结果：作废审核中", 
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
   			map.put("success", true);
      		return map;
       	}
      	/**
      	 * 作废申请审
      	 */
      	@RequestMapping(value = "cancelsh")
       	@ResponseBody
       	public HashMap<String, Object> cancelsh(ServletRequest request,String id) throws Exception{
      		HashMap<String, Object> map = new HashMap<String, Object>();
      		CurrentSessionUser user = SecurityUtil.getSecurityUser();
      		Tyfabh tyfabh=tyfabhManager.get(id);
      		tyfabh.setStatus(TyfabhManager.ZL_TYFABH_CANC);
      		tyfabhManager.save(tyfabh);
      		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(id, 
	  				"通用方案编号作废申请审核", 
	  				"业务服务部经办人审核。操作结果：已作废", 
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
   			map.put("success", true);
      		return map;
       	}
      	// 查询流程步骤信息
     	@RequestMapping(value = "getFlowStep")
     	@ResponseBody
     	public ModelAndView getFlowStep(HttpServletRequest request)
     			throws Exception {

     		Map<String, Object> map = new HashMap<String, Object>();

     		map = tyfabhManager.getFlowStep(request.getParameter("id"));

     		ModelAndView mav = new ModelAndView("app/qualitymanage/flow_card", map);

     		return mav;

     	}
}