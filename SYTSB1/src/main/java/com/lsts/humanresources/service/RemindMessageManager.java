package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.advicenote.dao.MessageHistoryDao;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.humanresources.dao.RemindMessageDao;
import com.lsts.log.service.SysLogService;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("remindMessageManager")
public class RemindMessageManager extends EntityManageImpl<RemindMessage,RemindMessageDao>{
    @Autowired
    RemindMessageDao remindMessageDao;
    @Autowired
	private MessageHistoryDao messageHistoryDao;
    @Autowired
	private SysLogService logService;
    @Autowired
	private EmployeeBaseDao employeeBaseDao;
    @Autowired
	private MessageService messageService;
    // 根据员工ID获取消息提醒信息
    public RemindMessage getRMessage(String fkRlEmplpyeeId){
    	return remindMessageDao.getRMessage(fkRlEmplpyeeId);
    }
    /**
     * 生日提醒信息定时发送
     * @return
	 * @throws Exception 
     */
    public void sendBirthdayRemind(){
    	System.out.println("------------系统定时发送生日提醒------------");
    	HttpServletRequest request = null;
    	Calendar now = Calendar.getInstance();
    	int day=now.get(Calendar.DAY_OF_MONTH);//获取当前时间取到几号
    	if(day==1){
    		List<?> list = remindMessageDao.getBirthdayRemind(1);//如果是当月一号则获取当月生日提醒对象集合以及前半个月或前一天生日提醒对象集合
    		//遍历获取发送内容并发送信息
    		if(list!=null&&list.size()>0){
    			for(int i=0;i<list.size();i++){
    				Object[] objs = (Object[]) list.get(i);
    				String sendType=(String) objs[13];//发送方式
    				String selfId=(String) objs[1];//当事人ID
    				String[] othersId=((String) objs[11]).split(",");//其他人ID
    				String selfRemind=(String) objs[10];//提醒当事人内容
    				String othersRemind=(String) objs[9];//提醒其他人内容
    				if(sendType.equals("1")){//微信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendWxMsg(request,
        							selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
    					}
      	    			//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendWxMsg(request,
    									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
    						}
    					}
    				}else if(sendType.equals("2")){//短信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);	
    					}
      	    			//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
    						}
    					}
    				}else if(sendType.equals("3")){//微信、短信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendWxMsg(request,
        							selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
    						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);
    					}
    					//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendWxMsg(request,
    									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
    							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
    						}
    					}
    				}
    			}
    		}
    	}else{
    		//如果不是当月一号则获取前半个月或前一天生日提醒对象集合
    		List<?> list = remindMessageDao.getBirthdayRemind(2);
    		//遍历获取发送内容并发送信息
    		if(list!=null&&list.size()>0){
    			for(int i=0;i<list.size();i++){
    				Object[] objs = (Object[]) list.get(i);
    				String sendType=(String) objs[13];//发送方式
    				String selfId=(String) objs[1];//当事人ID
    				String[] othersId=((String) objs[11]).split(",");//其他人ID
    				String selfRemind=(String) objs[10];//提醒当事人内容
    				String othersRemind=(String) objs[9];//提醒其他人内容
    				if(sendType.equals("1")){//微信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendWxMsg(request,
        							selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
    					}
      	    			//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendWxMsg(request,
    									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
    						}
    					}
    				}else if(sendType.equals("2")){//短信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);	
    					}
      	    			//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
    						}
    					}
    				}else if(sendType.equals("3")){//微信、短信发送
    					//发送给当事人
    					if(!selfId.isEmpty()){
    						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
    						messageService.sendWxMsg(request,
        							selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
    						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);
    					}
    					//发送给其他人
    					if(othersId.length>0&&othersId!=null){
    						for(int j=0;j<othersId.length;j++){
    							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
    							messageService.sendWxMsg(request,
    									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
    							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    /**
     * 退休提醒信息定时发送
     * @return
	 * @throws Exception 
     */
    public void sendRetireRemind(){
    	System.out.println("------------系统定时发送退休提醒------------");
    	HttpServletRequest request = null;
		List<?> list = remindMessageDao.getRetireRemind();//获取退休提醒对象集合
		//遍历获取发送内容并发送信息
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				String sendType=(String) objs[19];//发送方式
				String selfId=(String) objs[1];//当事人ID
				String[] othersId=((String) objs[17]).split(",");//其他人ID
				String selfRemind=(String) objs[16];//提醒当事人内容
				String othersRemind=(String) objs[15];//提醒其他人内容
				if(sendType.equals("1")){//微信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendWxMsg(request,
								selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
					}
  	    			//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendWxMsg(request,
									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
						}
					}
				}else if(sendType.equals("2")){//短信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);	
					}
  	    			//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
						}
					}
				}else if(sendType.equals("3")){//微信、短信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendWxMsg(request,
								selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);
					}
					//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendWxMsg(request,
									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
						}
					}
				}
			}
		}
    }
    /**
     * 聘用到期提醒信息定时发送
     * @return
	 * @throws Exception 
     */
    public void sendStopRemind(){
    	System.out.println("------------系统定时发送聘用到期提醒------------");
    	HttpServletRequest request = null;
		List<?> list = remindMessageDao.getStopRemind();//获取聘用到期提醒对象集合
		//遍历获取发送内容并发送信息
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				String sendType=(String) objs[7];//发送方式
				String selfId=(String) objs[1];//当事人ID
				String[] othersId=((String) objs[5]).split(",");//其他人ID
				String selfRemind=(String) objs[4];//提醒当事人内容
				String othersRemind=(String) objs[3];//提醒其他人内容
				if(sendType.equals("1")){//微信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendWxMsg(request,
								selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
					}
  	    			//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendWxMsg(request,
									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
						}
					}
				}else if(sendType.equals("2")){//短信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);	
					}
  	    			//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
						}
					}
				}else if(sendType.equals("3")){//微信、短信发送
					//发送给当事人
					if(!selfId.isEmpty()){
						String selfMobile = employeeBaseDao.get(selfId).getEmpPhone();
						messageService.sendWxMsg(request,
								selfId, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, selfRemind, selfMobile);
						messageService.sendMoMsg(request, selfId, selfRemind, selfMobile);
					}
					//发送给其他人
					if(othersId.length>0&&othersId!=null){
						for(int j=0;j<othersId.length;j++){
							String otherMobile = employeeBaseDao.get(othersId[j]).getEmpPhone();
							messageService.sendWxMsg(request,
									othersId[j], Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, othersRemind, otherMobile);
							messageService.sendMoMsg(request, othersId[j], othersRemind, otherMobile);	
						}
					}
				}
			}
		}
    }
}
