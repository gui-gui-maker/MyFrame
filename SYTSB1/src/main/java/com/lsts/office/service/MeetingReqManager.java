package com.lsts.office.service;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.manager.OrgManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.service.MsgService;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.WxLeave;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.office.bean.MeetingNotes;
import com.lsts.office.bean.MeetingOrg;
import com.lsts.office.bean.MeetingReq;
import com.lsts.office.bean.MeetingUser;
import com.lsts.office.dao.MeetUpdateYijinaDao;
import com.lsts.office.dao.MeetingNotesDao;
import com.lsts.office.dao.MeetingOrgDao;
import com.lsts.office.dao.MeetingReqDao;
import com.lsts.office.dao.MeetingUserDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoManager
 * @JDK 1.6
 * @author
 * @date
 */
@Service("meetingReqManager")
public class MeetingReqManager extends
		EntityManageImpl<MeetingReq, MeetingReqDao> {
	@Autowired
	private MeetingReqDao meetingReqDao;
	@Autowired
	private FlowExtManager flowExtManager;
	@Autowired
	private MessageXinxiService messageXinxiService;
	@Autowired
	EmployeesDao employeesDao;
	@Autowired
	MeetUpdateYijinaDao meetUpdateYijinaDao;
	@Autowired
	private MeetingUserDao meetingUserDao;
	@Autowired
	private MeetingOrgDao meetingOrgDao;
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private MessageService messageservice;
	@Autowired
    private EmployeeBaseManager  employeeBaseManager;
	@Autowired
	FlowServiceConfigManager flowServiceConfigManager;
	@Autowired
    private SysLogDao sysLogDao;
//	@Autowired
//	private NoticeManager noticeManager;
	
	@Autowired
	private MeetingNotesDao meetingNotesDao;
	    /**状态常量*/
	    public final static String MEET_FLOW_WTJ = "1";// 未提交
	    public final static String MEET_FLOW_DSH="2"; //待审核
		public final static String MEET_FLOW_SHZ = "3";// 申请单审核中
		public final static String MEET_FLOW_SHTG = "4"; // 审核通过
		public final static String MEET_FLOW_SHBTG = "5"; // 审核不通过
		public final static String MEET_FLOW_YCX = "6"; // 已撤销


	@Override
	public void save(MeetingReq meetingReq) throws Exception {
		String reqId=meetingReq.getId();
		//新增
		if(StringUtil.isEmpty(reqId)){
			meetingReq.setStatus(MEET_FLOW_WTJ);
			this.meetingReqDao.save(meetingReq);
			meetingReqDao.saveMeetingReqInfo(meetingReq);
		}
		//修改
		else{
			this.meetingReqDao.save(meetingReq);
			meetingReqDao.saveMeetingReqInfo(meetingReq);
		}
	}
	//保存申请表信息
	 public HashMap<String,Object> saveMeetingMessage(MeetingReq meetingReq,HttpServletRequest request,String userId) throws Exception{
		 HashMap<String,Object> wrapper = new HashMap<String,Object>(); 
		 String date1=request.getParameter("startTime");
		 String date2=request.getParameter("endTime");
		 String content=request.getParameter("content");
		 String remarks=request.getParameter("remark");
		 String remark=URLDecoder.decode(URLDecoder.decode(remarks,"UTF-8"),"tuf-8"); 
		 String comperes=request.getParameter("compere");
		 String compere=URLDecoder.decode(URLDecoder.decode(comperes,"UTF-8"),"tuf-8");
		 SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date startTime=ss.parse(date1);
		 Date endTime=ss.parse(date2);
		 String id=request.getParameter("Id");
		 List list=meetingReqDao.selectOrgId(meetingReq.getSqDepartment());
		   if(list.size()>0){
			 //修改申请表信息
			   if(StringUtil.isNotEmpty(id)){
				   MeetingReq   meetingReq1=meetingReqDao.get(id);
				   meetingReq1.setRoomCode(meetingReq.getRoomCode());
				   meetingReq1.setFkOaMeetingRoom(meetingReq.getFkOaMeetingRoom());
				   meetingReq1.setName(meetingReq.getName());
				   meetingReq1.setCompere(compere);
				   meetingReq1.setStartTime(startTime);
				   meetingReq1.setEndTime(endTime);
				   meetingReq1.setContent(content);
				   meetingReq1.setRemarks(remark);
				   meetingReq1.setSqDepartment(meetingReq.getSqDepartment());
				   meetingReq1.setHyAdress(meetingReq.getHyAdress());
				   meetingReq1.setHysbzxs(meetingReq.getHysbzxs());
				   meetingReq1.setIfFlowers(meetingReq.getIfFlowers());
				   meetingReq1.setIfFruits(meetingReq.getIfFruits());
				   meetingReq1.setIfHyby(meetingReq.getIfHyby());
				   meetingReq1.setIfZzhb(meetingReq.getIfZzhb());
				   meetingReq1.setIfZzhyzp(meetingReq.getIfZzhyzp());
				   meetingReq1.setIfZzjczp(meetingReq.getIfZzjczp());
				   meetingReq1.setPnumber(meetingReq.getPnumber());
				   meetingReq1.setOfficeOtherPz(meetingReq.getOfficeOtherPz());
				   meetingReqDao.save(meetingReq1);
			   }else{
				   meetingReq.setStatus(MEET_FLOW_WTJ);
				   CurrentSessionUser user = SecurityUtil.getSecurityUser();
				   String name=user.getName();
				   meetingReq.setDjPeopleId(userId);
				   meetingReq.setDjPeople(name);
				   meetingReq.setDjDate(new Date());
				   meetingReq.setStartTime(startTime);
				   meetingReq.setEndTime(endTime);
				   meetingReq.setCompere(compere);
				   this.meetingReqDao.save(meetingReq);
			   }
			   if(StringUtil.isNotEmpty(id)){
					if(meetingReqDao.getUerId(id).size()>0){
						meetingReqDao.deleteReqUser(id);
					}
					if(meetingReqDao.getOrgId(id).size()>0){
						meetingReqDao.deleteReqOrg(id);
					}
				}
			   String str=request.getParameter("userId");
			   String [] strArr= str.split(","); 
			   for(int i=0;i<strArr.length;i++){
				   saveUserId(meetingReq.getId(),strArr[i],id);
			   }
			   String str1=request.getParameter("orgId");
			   String[] strArr1 = str1.split(",");
			   for (int i = 0; i < strArr1.length; i++) {
				   saveOrgId(meetingReq.getId(), strArr1[i],id);
			   }
			   wrapper.put("success",true);
			   wrapper.put("msg","保存成功！" );
		   }else{
			   wrapper.put("success",false);
			   wrapper.put("msg","申请部门不存在！" );
		   }
		   return wrapper;
		 
	 }
	//保存参会人信息
	public void saveUserId(String str1, String str2,String id) throws Exception {
		MeetingUser meetingUser=new MeetingUser();
		if(StringUtil.isNotEmpty(id)){
			meetingUser.setReqId(id);
			meetingUser.setUserId(str2);
		}else{
			meetingUser.setReqId(str1);
			meetingUser.setUserId(str2);
		}
		 meetingUserDao.save(meetingUser);
		
	}
	//保存参会单位信息
		public void saveOrgId(String str1, String str2,String id) throws Exception {
			MeetingOrg meetingOrg=new MeetingOrg();
			if(StringUtil.isNotEmpty(id)){
				meetingOrg.setReqId(id);
				meetingOrg.setOrgId(str2);
			}else{
				meetingOrg.setReqId(str1);
				meetingOrg.setOrgId(str2);
			}
			meetingOrgDao.save(meetingOrg);
			
			
		}
		//删除会议申请表信息
		public void deleteMeetingMessage(String reqId) throws Exception {
			
			List<MeetingNotes> list=meetingReqDao.selectReqNotes(reqId);
			if(list.size()>0){
				meetingReqDao.deleteReq(reqId);
			}else{
				meetingReqDao.deleteReq(reqId);
				meetingReqDao.deleteReqOrg(reqId);
				meetingReqDao.deleteReqUser(reqId);
			}
			}
   
	/**
	 * 判断会议室在指定的时间是否被使用
	 * @param meetingReq
	 * @return
	 * @throws Exception 
	 */
	public boolean useValidate(String reqId,String roomId,String startTime,String endTime) throws Exception{
		boolean isUse=false;
		int result=meetingReqDao.countReq(reqId,roomId,startTime,endTime);
		if(result>0){
			isUse=true;
		}
		return isUse;
	}
	
	//删除会议申请信息包含参会人员和单位的关联信息
	/*@Override
	public void deleteBusiness(Serializable id) throws Exception {
		super.deleteBusiness(id);
		String[] reqIds=id.toString().split(",");
		String reqId="";
		for(int i=0;i<reqIds.length;i++){
			reqId+=(i!=0?",":"")+"'"+reqIds[i]+"'";
		}
		String sql="delete from TJY2_OFFICE_MEETING_MINUTES where fkreqid in ("+reqId+")";
		meetingNotesDao.getSessionFactory().getCurrentSession().createSQLQuery(sql);
	}*/

	//查询会议详情
	public MeetingReq detailMeeting(String reqId) throws Exception{
		 MeetingReq meetingReq=meetingReqDao.get(reqId);
		
		 //判断会议是否已召开
		 if(meetingReq.getMeeting_status().equals("2")){
			 String hql="from MeetingNotes where fkreqid='"+reqId+"'";
			 List<MeetingNotes> lMeetingNotes=meetingNotesDao.getSessionFactory().getCurrentSession().createQuery(hql).list();
			 //会议记录
			 if(lMeetingNotes.size()>0){
				 meetingReq.setMeetingNotes(lMeetingNotes.get(0));
			 }
		 }
		 else{
			 meetingReq.setMeetingNotes(null);
		 }
		 return meetingReq;
	}
	
	//发送会议通知
	/*public void sendMeetingNotice(MeetingReq meetingReq,CurrentSessionUser currentUser) throws Exception{
//		 List<CurrentSessionUser> lUser=meetingReq.getlUser();
//		 List<SysOrg> lOrg=meetingReq.getlOrg();
//		 String userId="";
//		 String userName="";
//		 String orgId="";
//		 String orgName="";
//		 int i=0;
//		 for(CurrentSessionUser user:lUser){
//			 userId+=(i==0?"":",")+user.getId();
//			 userName+=(i==0?"":",")+user.getName();
//			 i++;
//		 }
//		 
//		 i=0;
//		 for(SysOrg org:lOrg){
//			 orgId+=(i==0?"":",")+org.getOrgid();
//			 orgName+=(i==0?"":",")+org.getOrgName();
//			 i++;
//		 }
//		 
//		 Notice notice=new Notice();
//		 notice.setRelease("1");//已经发布
//		 notice.setWriteId(currentUser.getCode());
//		 notice.setWriteName(currentUser.getName());
//		 notice.setWriteTime(new Date());
//		 notice.setType("3");//会议通知
//		 notice.setTitle("【会议通知】"+meetingReq.getName());
//		 notice.setAcceptManId(userId);
//		 notice.setAcceptManName(userName);
//		 notice.setAcceptOrgId(orgId);
//		 notice.setAcceptOrgName(orgName);
//		 notice.setContent("【主讲人】"+meetingReq.getCompere()+"【会议简介】"+meetingReq.getContent());
//		 
//		 noticeManager.saveNotice(notice, null);
		  meetingReq.setMeeting_status("1");
		 meetingReqDao.save(meetingReq);
	}*/
	 /**
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		try {
			flowExtManager.startFlowProcess(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
  	


  	
	//通过申请表id获取参会人员信息
  	 public List<Employee> getUser(String id) throws Exception{
		 List<Employee> employeeList=new ArrayList<Employee>();
		 List<MeetingUser> list1=meetingReqDao.getUerId(id);
		 if(list1!=null && list1.size()>0){
			for(int j=0;j<list1.size();j++){
				try {
					Employee employee = meetingReqDao.getUerMessage(list1.get(j).getUserId());
					employeeList.add(employee);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
		 return  employeeList;
  	 }
  	//通过申请表id获取参会单位信息
  	 public List<Org> getOrg(String id) throws Exception{
		 List<Org> orgList=new ArrayList<Org>();
		 List<MeetingOrg> list1=meetingReqDao.getOrgId(id);
		 if(list1!=null && list1.size()>0){
			for(int j=0;j<list1.size();j++){
				Org org = meetingReqDao.getOrgMessage(list1.get(j).getOrgId());
				orgList.add(org);
			 }
		 }
		 return  orgList;
  	 }
  	 public HashMap<String, Object> sendMessage(HttpServletRequest request) throws Exception{
  		  String  sendType=request.getParameter("sendType");
  		  String reqId=request.getParameter("reqId");
  		  
  		  MeetingReq meetingReq=new MeetingReq();
  	    	meetingReq=meetingReqDao.get(reqId);
  	    	if(sendType!=null && sendType!=""){
  	    		
  	    		if(sendType.equals("0")){
  	    			//短信通知参会人
  	    			List<MeetingUser> list1=meetingReqDao.getUerId(reqId);
  	    			if(list1!=null && list1.size()>0){
  	    				for(int j=0;j<list1.size();j++){
  	    					String reqUserId=list1.get(j).getUserId();
  	    					String employee=meetingReqDao.getTel(reqUserId).toString();
  	    					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
  	    							.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
  	    					String msg ="您好，请在"+meetingReq.getStartTime()+"参加"+meetingReq.getSqDepartment()+"的会议！";
  	    					messageXinxiService.setSaveMessageXinxi(reqUserId, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
  	    					messageservice.sendMoMsg(request, reqId, msg, mobileTel);
  	    				}
  	    			}
  	    		}else if(sendType.equals("1")){
  	    			//微信通知参会人
  	    			List<MeetingUser> list1=meetingReqDao.getUerId(reqId);
  	    			if(list1!=null && list1.size()>0){
  	    				for(int j=0;j<list1.size();j++){
  	    					String reqUserId=list1.get(j).getUserId();
  	    					String employee=meetingReqDao.getTel(reqUserId).toString();
  	    					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
  	    							.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
  	    					String msg ="您好，请在"+meetingReq.getStartTime()+"参加"+meetingReq.getSqDepartment()+"的会议！";
  	    					messageXinxiService.setSaveMessageXinxi(reqUserId, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
  	    					messageservice.sendWxMsg(request, reqId, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, msg, mobileTel);
  	    				}
  	    			}
  	    		}else{
  	    			List<MeetingUser> list1=meetingReqDao.getUerId(reqId);
  	    			if(list1!=null && list1.size()>0){
  	    				for(int j=0;j<list1.size();j++){
  	    					String reqUserId=list1.get(j).getUserId();
  	    					String employee=meetingReqDao.getTel(reqUserId).toString();
  	    					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
  	    							.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
  	    					String msg ="您好，请在"+meetingReq.getStartTime()+"参加"+meetingReq.getSqDepartment()+"的会议！";
  	    					messageXinxiService.setSaveMessageXinxi(reqUserId, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
  	    					messageservice.sendMoMsg(request, reqId, msg, mobileTel);
  	    				}
  	    			}
  	    			if(list1!=null && list1.size()>0){
  	    				for(int j=0;j<list1.size();j++){
  	    					String reqUserId=list1.get(j).getUserId();
  	    					String employee=meetingReqDao.getTel(reqUserId).toString();
  	    					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
  	    							.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
  	    					String msg ="您好，请在"+meetingReq.getStartTime()+"参加"+meetingReq.getSqDepartment()+"的会议！";
  	    					messageXinxiService.setSaveMessageXinxi(reqUserId, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
  	    					messageservice.sendWxMsg(request, reqId, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, msg, mobileTel);
  	    				}
  	    			}
  	    		}
  	    	}
		return JsonWrapper.successWrapper(meetingReq);
  		
  		 
  	 }
  	 
  	/**
 	 * 通过请休假ID查询流程主键ID
 	 * 
 	 * @param request
 	 * @param id
   	 * @return 
   	 * @return 
 	 * @return
 	 * @throws Exception
 	 */
 	public  String queryMainId(HttpServletRequest request, String id) throws Exception {
 		String mainId = meetingReqDao.queryMainId(request, id);
 		return mainId;
 	}
 	/**
 	 * 通过请休假ID查询PROCESS_ID
 	 * 
 	 * @param request
 	 * @param id
   	 * @return 
   	 * @return 
 	 * @return
 	 * @throws Exception
 	 */
 	public  String queryProcessId(HttpServletRequest request, String id) throws Exception {
 		String processId = meetingReqDao.queryProcessId(request, id);
 		return processId;
 	}
 	/**
	 * 通过填入时间统计此时间段的会议室申请以及使用情况
	 * 
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
 	/*public List<MeetingReq> countReq(HttpServletRequest request, String startTime, String endTime, String id) throws Exception {
 		List<MeetingReq> list= meetingReqDao.countReq(request,startTime,endTime,id);
 		return list;
 	}*/
 	//微信流程提交
   	public HashMap<String, Object> subFolwsWX(ServletRequest request,String id, String flowId,
   		String typeCode, String activityId,String userId) throws Exception {
		 EmployeeBase employeeBase=employeeBaseManager.get(userId);
   		Map<String, Object> map = new HashMap<String, Object>();
   		//流程传参
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "会议室申请 -"+(employeeBase.getEmpName()));
   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 启动流程
   			if (StringUtil.isNotEmpty(flowId)) {
   				   doStartPress(map);
   				//改变状态
   				MeetingReq meetingReq=meetingReqDao.get(id);
   				meetingReq.setStatus(MEET_FLOW_DSH);
   				meetingReqDao.save(meetingReq);
   			} else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}

   			return JsonWrapper.successWrapper(id);
   		}

   	}
   	/**
  	 * 保存微信会议室申请并返回申请ID
  	 * */
	 public String saveWXMeetReq(MeetingReq meetingReq,HttpServletRequest request,String userId) throws Exception{
		 EmployeeBase employeeBase = employeeBaseManager.get(userId);
		 //登记人ID、登记人、登记时间
		 meetingReq.setDjPeopleId(employeeBase.getId());
		 meetingReq.setDjPeople(employeeBase.getEmpName());;
		 meetingReq.setDjDate(new Date());
		 //申请状态
		 meetingReq.setStatus("1");
		 meetingReqDao.save(meetingReq);
		 String id=meetingReq.getId();
		 return id;
	 }
	 public HashMap<String, Object>  getFlowStep(String meeting_req_id) throws Exception
	    {	
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<SysLog> list = sysLogDao.getBJLogs(meeting_req_id);
			map.put("flowStep", list);
			map.put("size", list.size());
			map.put("name", meetingReqDao.get(meeting_req_id).getName());
			map.put("success", true);
			
			return map;
	    }
}
