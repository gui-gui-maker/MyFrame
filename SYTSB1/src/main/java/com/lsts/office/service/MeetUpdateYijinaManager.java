package com.lsts.office.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.service.MsgService;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.office.bean.MeetUpdateYijina;
import com.lsts.office.bean.MeetingReq;
import com.lsts.office.bean.MeetingUser;
import com.lsts.office.dao.MeetUpdateYijinaDao;
import com.lsts.office.dao.MeetingReqDao;



@Service("meetUpdateYijinaManager")
public class MeetUpdateYijinaManager extends EntityManageImpl<MeetUpdateYijina,MeetUpdateYijinaDao>{
    @Autowired
    private MeetUpdateYijinaDao meetUpdateYijinaDao;
    @Autowired
    private FlowExtManager flowExtManager;
    @Autowired
    private MessageXinxiService messageXinxiService;
    @Autowired
    private EmployeesDao employeesDao;
    @Autowired
    private MeetingReqDao meetingReqDao;
    @Autowired
    private MessageService messageservice;
    @Autowired
    private EmployeeBaseManager employeeBaseManager;
    /**状态常量*/
    public final static String MEET_FLOW_WTJ = "1";// 未提交
    public final static String MEET_FLOW_DSH="2"; //待审核
	public final static String MEET_FLOW_SHZ = "3";// 申请单审核中
	public final static String MEET_FLOW_SHTG = "4"; // 审核通过
	public final static String MEET_FLOW_SHBTG = "5"; // 审核不通过
	public final static String MEET_FLOW_YCS = "6"; // 撤销


	
    /**
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
  	

    /**
  	 * 审核
  	 * */
  	
  	public void doProcess(Map<String, Object> map)throws Exception{
  		flowExtManager.submitActivity(map);
      }
  	
  	/**
  	 * 退回
  	 * */
  	
  	public void doreturn(Map<String, Object> map)throws Exception{
  		flowExtManager.returnedActivity(map);
      }
	/**
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
      }
    
    public void saveRet(HttpServletRequest request,String id,String cxyy,CurrentSessionUser user,String typeCode) throws Exception{
    	MeetingReq meetingReq=meetingReqDao.get(id);
    	String status = meetingReq.getStatus();
		//修改业务状态为“已撤销”
    	meetingReq.setStatus(MEET_FLOW_YCS);
    	meetingReqDao.save(meetingReq);
    	if(status.equals("4") || status.equals("5")){
    		
    	}else{
    		try {
    			//根据业务id查询出流程id
    			String actId=meetUpdateYijinaDao.getFlowId(id).get(0).toString();
    			/*String activityId=Pattern.compile("\\b([\\w\\W])\\b").matcher(actId.toString()
    					.substring(1,actId.toString().length()-1)).replaceAll("'$1'");*/
    			String proId=meetUpdateYijinaDao.getProcessId(id).get(0).toString();
    			/*String processId=Pattern.compile("\\b([\\w\\W])\\b").matcher(proId.toString()
    					.substring(1,proId.toString().length()-1)).replaceAll("'$1'");*/
    			//撤销流程
    			HashMap<String, Object> map=new HashMap<String, Object>();
    			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
    			map.put(FlowExtWorktaskParam.ACTIVITY_ID, actId);
    			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "会议申请单"+user.getName());
    			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
    			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, false);
    			map.put(FlowExtWorktaskParam.PROCESS_ID, proId);
    			
    			if (StringUtil.isEmpty(id)) {
    			} else {
    				
    				if (StringUtil.isNotEmpty(actId)) {
    					flowExtManager.finishProcess(map);
    				}else {
    					JsonWrapper.failureWrapper("流程ID为空！");
    					
    				}
    				
    				 JsonWrapper.successWrapper(id);
    			}
//    		}
    			}catch (Exception e) {
    			 e.printStackTrace();
    		}
    	}
		String employee=meetingReqDao.getTel(meetingReq.getDjPeopleId()).toString();
		String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
    			.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
		 String msg = "很抱歉，您的会议室申请("+meetingReq.getName()+")已被撤销！撤销原因："+cxyy;
		 messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
		 
		 messageservice.sendMoMsg(request, id, msg, mobileTel);
		 String msg1 = "很抱歉，您的会议室申请("+meetingReq.getName()+")已被撤销！撤销原因："+cxyy;
		 messageservice.sendWxMsg(request, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, msg1, mobileTel);
		 
		
  
    }
    
    /**
     * 微信
  	 * 撤销流程
  	 * */
    public boolean saveRetWX(HttpServletRequest request,String id,String eId,String typeCode) throws Exception{
    	Boolean boo=false;
    	EmployeeBase employeeBase=employeeBaseManager.get(eId);
    	MeetingReq meetingReq=meetingReqDao.get(id);
    	//获取状态
    	String status = meetingReq.getStatus();
		//修改业务状态为“已撤销”
    	meetingReq.setStatus(MEET_FLOW_YCS);
    	meetingReqDao.save(meetingReq);
    	/*if(status.equals("4") || status.equals("5")){
    		
    	}else{
    		try {
    			//根据业务id查询出流程id
    			String actId=meetUpdateYijinaDao.getFlowId(id).get(0).toString();
    			String activityId=Pattern.compile("\\b([\\w\\W])\\b").matcher(actId.toString()
    					.substring(1,actId.toString().length()-1)).replaceAll("'$1'"); 
    			String proId=meetUpdateYijinaDao.getProcessId(id).get(0).toString();
    			String processId=Pattern.compile("\\b([\\w\\W])\\b").matcher(proId.toString()
    					.substring(1,proId.toString().length()-1)).replaceAll("'$1'"); 
    			BpmUserImpl bpmUser = new BpmUserImpl(proId, employeeBase.getEmpName(), null, null, null);
    			//撤销流程
    			HashMap<String, Object> map=new HashMap<String, Object>();
    			map.put(FlowExtWorktaskParam.BPM_USER, bpmUser);
    			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
    			map.put(FlowExtWorktaskParam.ACTIVITY_ID, actId);
    			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "会议申请单"+ employeeBase.getEmpName());
    			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
    			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, false);
    			map.put(FlowExtWorktaskParam.PROCESS_ID, proId);
    			
    			if (StringUtil.isEmpty(id)) {
    			} else {
    				if (StringUtil.isNotEmpty(actId)) {
    					flowExtManager.finishProcess(map);
    					boo=true;
    				}else {
    					JsonWrapper.failureWrapper("流程ID为空！");
    				}
    			}
//    		}
    			}catch (Exception e) {
    			 e.printStackTrace();
    		} 
    	}*/
		
		//发送消息
		String employee=meetingReqDao.getTel(meetingReq.getDjPeopleId()).toString();
		String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
    			.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
		 String msg = "很抱歉，您的会议室申请("+meetingReq.getName()+")已被撤销！";
		 messageXinxiService.setSendMessage(mobileTel, msg);//短信接口
		 messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, mobileTel, msg);//发送微信
		 /*messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
		 
		 messageservice.sendMoMsg(request, id, msg, mobileTel);
		 String msg1 = "很抱歉，您的会议室申请("+meetingReq.getName()+")已被撤销！";
		 messageservice.sendWxMsg(request, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, msg1, mobileTel);*/
		 
		return boo;
  
    }
    } 
    