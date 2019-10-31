package com.lsts.humanresources.web;
import com.alibaba.fastjson.JSON;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.weixin.service.MsgService;
import com.lsts.advicenote.service.MessageService;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.RlMessage;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.RlMessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("rlMessageAction")
public class RlMessageAction extends SpringSupportAction<RlMessage, RlMessageManager> {

    @Autowired
    private RlMessageManager  rlMessageManager;
    @Autowired
    private EmployeeBaseManager employeeBaseManager;
    @Autowired
	private MessageService messageservice;
    
    //保存消息
  	@RequestMapping(value = "saveMessage")
  	@ResponseBody
  	public HashMap<String, Object> saveMessage(HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
  		try{
  			request.setCharacterEncoding("UTF-8");
  			String rlmessage=request.getParameter("rlMessage").toString();
  			String status=request.getParameter("status");
  			RlMessage entity=JSON.parseObject(rlmessage, RlMessage.class);
  		System.out.println("------------------"+entity.getMessage());
  			List<EmployeeBase> list=entity.getEmployee();
  			entity.setSendDate(new Date());
  			entity.setSendMan(user.getName());
  			entity.setEmployee(null);
  			rlMessageManager.save(entity);
  		/*	String message = new String(entity.getMessage().getBytes("iso8859-1"),"UTF-8");
  			entity.setMessage(message);*/
  			for (int i = 0; i < list.size(); i++) {
  				EmployeeBase emp=new EmployeeBase();
  				emp=employeeBaseManager.get(list.get(i).getId());
  				emp.setFkMessageId(entity.getId());
  				if(status.equals("1")){
  					messageservice.sendMoMsg(request,entity.getId(),entity.getMessage() , emp.getMobilePhone());
  				}
  				employeeBaseManager.save(emp);
			}
  			
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
    //查询信息
   	@RequestMapping(value = "detailMessage")
   	@ResponseBody
   	public HashMap<String, Object> detailMessage(String id,HttpServletRequest request)
   			throws Exception {
   		HashMap<String, Object> wrapper = new HashMap<String, Object>();
   		try{
   			RlMessage message=rlMessageManager.get(id);
   			List<EmployeeBase> employee= employeeBaseManager.getByMessageId(id);
   			wrapper.put("message", message);
   			wrapper.put("employee",employee);
   			wrapper.put("success", true);
   		}catch(Exception e){
   			log.error("获取信息：" + e.getMessage());
   			wrapper.put("success", false);
   			wrapper.put("message", "获取信息出错！");
   			e.printStackTrace();	
   		}
   		return wrapper;
   	}
   	//发送信息
   	@RequestMapping(value = "sendMessage")
   	@ResponseBody
   	public HashMap<String, Object> sendMessage(HttpServletRequest request)
   			throws Exception {
   		HashMap<String, Object> wrapper = new HashMap<String, Object>();
   		try{
   			EmployeeBase emp=new EmployeeBase();
   			String message=request.getParameter("message").toString();
  			String ids=request.getParameter("ids");
  			String[] id=ids.split(",");
  			for (int i = 0; i < id.length; i++) {
  				emp=employeeBaseManager.get(id[i]);
  				if(emp.getEmpPhone()!=null){
  					messageservice.sendMoMsg(request,id[i],message, emp.getEmpPhone());
  				}
  				
			}
   			wrapper.put("success", true);
   		}catch(Exception e){
   			log.error("获取信息：" + e.getMessage());
   			wrapper.put("success", false);
   			wrapper.put("message", "获取信息出错！");
   			e.printStackTrace();	
   		}
   		return wrapper;
   	}
	
}