package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.BeanUtils;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.RemindMessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("remindMessageAction")
public class RemindMessageAction extends SpringSupportAction<RemindMessage, RemindMessageManager> {

    @Autowired
    private RemindMessageManager  remindMessageManager;
    @Autowired
    private EmployeeBaseManager  employeeBaseManager;
    /**
	 * 保存
	 * 
	 * @param request
	 * @param remindMessage
	 * @throws Exception
	 */
    @RequestMapping(value = "saveRMessage")
	@ResponseBody
    public HashMap<String, Object> saveRMessage(HttpServletRequest request, RemindMessage remindMessage) throws Exception{
    	CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
    	String messageType=request.getParameter("messageType");
    	String RMId=remindMessage.getId();
    	System.out.println(RMId);
		if(RMId==null){
			remindMessage.setCreateDate(new Date());
			remindMessage.setCreateId(curUser.getId());
			remindMessage.setCreateBy(curUser.getName());
			remindMessageManager.save(remindMessage);
		}else{
			RemindMessage remindMessage1 = remindMessageManager.get(RMId);
			remindMessage1.setLastModifyDate(new Date());
			remindMessage1.setLastModifyId(curUser.getId());
			remindMessage1.setLastModifyBy(curUser.getName());
			if(messageType.equals("stopRemind")){
				remindMessage1.setStopRemindTime(remindMessage.getStopRemindTime());
				remindMessage1.setStopSendType(remindMessage.getStopSendType());
				remindMessage1.setStopRemindId(remindMessage.getStopRemindId());
				remindMessage1.setStopRemindName(remindMessage.getStopRemindName());
				remindMessage1.setStopRemind(remindMessage.getStopRemind());
				remindMessage1.setStopRemindSelf(remindMessage.getStopRemindSelf());
				System.out.println("It's stopRemind!");
			}else if(messageType.equals("birthdayRemind")){
				remindMessage1.setBirthdayRemindTime(remindMessage.getBirthdayRemindTime());
				remindMessage1.setBirthdaySendType(remindMessage.getBirthdaySendType());
				remindMessage1.setBirthdayRemindId(remindMessage.getBirthdayRemindId());
				remindMessage1.setBirthdayRemindName(remindMessage.getBirthdayRemindName());
				remindMessage1.setBirthdayRemind(remindMessage.getBirthdayRemind());
				remindMessage1.setBirthdayRemindSelf(remindMessage.getBirthdayRemindSelf());
				System.out.println("It's birthdayRemind!");
			}else if(messageType.equals("retireRemind")){
				remindMessage1.setRetireRemindTime(remindMessage.getRetireRemindTime());
				remindMessage1.setRetireSendType(remindMessage.getRetireSendType());
				remindMessage1.setRetireRemindId(remindMessage.getRetireRemindId());
				remindMessage1.setRetireRemindName(remindMessage.getRetireRemindName());
				remindMessage1.setRetireRemind(remindMessage.getRetireRemind());
				remindMessage1.setRetireRemindSelf(remindMessage.getRetireRemindSelf());
				System.out.println("It's retireRemind!");
			}else{
				System.out.println("It's not stopRemind,birthdayRemind and retireRemind!Please check your type!");
			}
			remindMessageManager.save(remindMessage1);
		}
		return JsonWrapper.successWrapper(remindMessage);
    	
    }
    
    /**
	 * 批量保存
	 * 
	 * @param request
	 * @param remindMessage
	 * @throws Exception
	 */
    @RequestMapping(value = "saveRMessages")
	@ResponseBody
    public HashMap<String, Object> saveRMessages(HttpServletRequest request, RemindMessage remindMessage) throws Exception{
    	CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
    	String messageType=request.getParameter("messageType");//提醒类型
    	String[] ids = request.getParameter("ids").split(",");
    	System.out.println(ids);
    	for(int i=0;i<ids.length;i++){
    		RemindMessage remindMessage1 = remindMessageManager.getRMessage(ids[i]);
    		if(remindMessage1==null){
    			RemindMessage newRRemindMessage = new RemindMessage();
    			BeanUtils.copyProperties(newRRemindMessage, remindMessage);
    			newRRemindMessage.setFkRlEmplpyeeId(ids[i]);//当事人ID
    			newRRemindMessage.setCreateDate(new Date());
    			newRRemindMessage.setCreateId(curUser.getId());
    			newRRemindMessage.setCreateBy(curUser.getName());
    			if(messageType.equals("stopRemind")){
    				String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String stopRemind = remindMessage.getStopRemind().replace("XXX", name);//聘用到期提醒发送内容
    				newRRemindMessage.setStopRemind(stopRemind);
    				newRRemindMessage.setStopRemindSelf(remindMessage.getStopRemindSelf());
        			System.out.println("It's stopRemind!");
        		}else if(messageType.equals("birthdayRemind")){
        			String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String empIdCard = employeeBaseManager.get(ids[i]).getEmpIdCard();
        			String birthday = empIdCard.substring(10, 12)+"月"+empIdCard.substring(12, 14)+"日";
        			String birthdayRemind = remindMessage.getBirthdayRemind().replace("XXX", name).replaceAll("XX月XX日", birthday);//生日提醒发送内容
        			String birthdayRemindSelf = remindMessage.getBirthdayRemindSelf().replace("XX月XX日", birthday);//生日提醒当事人接收内容
        			newRRemindMessage.setBirthdayRemind(birthdayRemind);
        			newRRemindMessage.setBirthdayRemindSelf(birthdayRemindSelf);
        			System.out.println("It's birthdayRemind!");
        		}else if(messageType.equals("retireRemind")){
        			String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String retireRemind = remindMessage.getRetireRemind().replace("XXX", name);//退休提醒发送内容
        			newRRemindMessage.setRetireRemind(retireRemind);
        			newRRemindMessage.setRetireRemindSelf(remindMessage.getRetireRemindSelf());
        			System.out.println("It's retireRemind!");
        		}else{
        			System.out.println("It's not stopRemind,birthdayRemind and retireRemind!Please check your type!");
        		}
    			//发送内容
    			//当事人接收内容
    			
    			remindMessageManager.save(newRRemindMessage);
    		}else{
    			remindMessage1.setLastModifyDate(new Date());
        		remindMessage1.setLastModifyId(curUser.getId());
        		remindMessage1.setLastModifyBy(curUser.getName());
        		if(messageType.equals("stopRemind")){
        			String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String stopRemind = remindMessage.getStopRemind().replace("XXX", name);//聘用到期提醒发送内容
        			remindMessage1.setStopRemindTime(remindMessage.getStopRemindTime());
        			remindMessage1.setStopSendType(remindMessage.getStopSendType());
        			remindMessage1.setStopRemindId(remindMessage.getStopRemindId());
        			remindMessage1.setStopRemindName(remindMessage.getStopRemindName());
        			remindMessage1.setStopRemind(stopRemind);
        			remindMessage1.setStopRemindSelf(remindMessage.getStopRemindSelf());
        			System.out.println("It's stopRemind!");
        		}else if(messageType.equals("birthdayRemind")){
        			String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String empIdCard = employeeBaseManager.get(ids[i]).getEmpIdCard();
        			String birthday = empIdCard.substring(10, 12)+"月"+empIdCard.substring(12, 14)+"日";
        			String birthdayRemind = remindMessage.getBirthdayRemind().replace("XXX", name).replaceAll("XX月XX日", birthday);//生日提醒发送内容
        			String birthdayRemindSelf = remindMessage.getBirthdayRemindSelf().replace("XX月XX日", birthday);//生日提醒当事人接收内容
        			remindMessage1.setBirthdayRemindTime(remindMessage.getBirthdayRemindTime());
        			remindMessage1.setBirthdaySendType(remindMessage.getBirthdaySendType());
        			remindMessage1.setBirthdayRemindId(remindMessage.getBirthdayRemindId());
        			remindMessage1.setBirthdayRemindName(remindMessage.getBirthdayRemindName());
        			remindMessage1.setBirthdayRemind(birthdayRemind);
        			remindMessage1.setBirthdayRemindSelf(birthdayRemindSelf);
        			System.out.println("It's birthdayRemind!");
        		}else if(messageType.equals("retireRemind")){
        			String name = employeeBaseManager.get(ids[i]).getEmpName();
        			String retireRemind = remindMessage.getRetireRemind().replace("XXX", name);//退休提醒发送内容
        			remindMessage1.setRetireRemindTime(remindMessage.getRetireRemindTime());
        			remindMessage1.setRetireSendType(remindMessage.getRetireSendType());
        			remindMessage1.setRetireRemindId(remindMessage.getRetireRemindId());
        			remindMessage1.setRetireRemindName(remindMessage.getRetireRemindName());
        			remindMessage1.setRetireRemind(retireRemind);
        			remindMessage1.setRetireRemindSelf(remindMessage.getRetireRemindSelf());
        			System.out.println("It's retireRemind!");
        		}else{
        			System.out.println("It's not stopRemind,birthdayRemind and retireRemind!Please check your type!");
        		}
        		remindMessageManager.save(remindMessage1);
    		}
    	}
		return JsonWrapper.successWrapper(remindMessage);
    	
    }
    /**
	 * 根据员工ID获取员工消息提醒信息
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "getRMessage")
	@ResponseBody
	public HashMap<String, Object> getRMessage(HttpServletRequest request) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	String fkRlEmplpyeeId=request.getParameter("fkRlEmplpyeeId");
    	RemindMessage remindMessage=remindMessageManager.getRMessage(fkRlEmplpyeeId);
		wrapper.put("success", true);
		wrapper.put("data", remindMessage);
		return wrapper;
    }
    /**
	 * 生日提醒消息发送
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "sendBirthdayRemind")
	@ResponseBody
	public HashMap<String, Object> sendBirthdayRemind(HttpServletRequest request) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	remindMessageManager.sendBirthdayRemind();
		return wrapper;
    }
    /**
	 * 聘用到期提醒消息发送
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "sendStopRemind")
	@ResponseBody
	public HashMap<String, Object> sendStopRemind(HttpServletRequest request) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	remindMessageManager.sendStopRemind();
		return wrapper;
    }
    /**
	 * 退休提醒消息发送
	 * 
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "sendRetireRemind")
	@ResponseBody
	public HashMap<String, Object> sendRetireRemind(HttpServletRequest request) throws Exception{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	remindMessageManager.sendRetireRemind();
		return wrapper;
    }
}