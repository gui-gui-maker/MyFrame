package com.lsts.qualitymanage.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.TaskAllot;
import com.lsts.qualitymanage.dao.TaskAllotDao;



@Service("taskAllotManager")
public class TaskAllotManager extends EntityManageImpl<TaskAllot,TaskAllotDao>{
    @Autowired
    TaskAllotDao taskAllotDao;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
  	private MessageService messageservice;
    @Autowired
	private EmployeesService employeesService;
    @Autowired
	private MessageService messageService;
    @Autowired
	private SysLogService logService;
    
    public final static String ZLGL_RWXF_WTJ="WTJ"; //未提交
    public final static String ZLGL_RWXF_WKS="WKS"; //未开始
    public final static String ZLGL_RWXF_JXZ="JXZ"; //进行中
    public final static String ZLGL_RWXF_YWC="YWC"; //已完成
    public final static String ZLGL_RWXF_WWC="WWC"; //未完成
    public final static String ZLGL_RWXF_DSH="DSH"; //待审核
    public final static String ZLGL_RWXF_BTG="BTG"; //审核不通过
 
    
    /**
  	 * 下发流程更新状态，并向部门负责人发送微信提醒
  	 * @param map
  	 * @throws Exception
  	 */
  	public void doLctj(HttpServletRequest request,TaskAllot taskAllot) throws Exception{
  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User)user.getSysUser();
		Org org = (Org)uu.getOrg();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_DSH);//待审核 
		taskAllotDao.save(taskAllot);
		// 获取发送内容
		String content = Constant.getQualityTaskContent(taskAllot.getTaskSn(),
				DateUtil.getDateTime(Constant.defaultDatePattern, taskAllot.getItemDate()));
		// 获取部门主任用户信息ID
		String employee_id = employeesService.getEmpIDByRoleName(Constant.ROLE_NAME_BMZR, org.getId());
		if (StringUtil.isNotEmpty(employee_id)) {
			// 获取部门主任用户信息
			Employee employee = employeesService.get(employee_id);
			if (employee != null) {
				// 获取发送目标（部门主任）手机号码
				String destNumber = employee.getMobileTel();
				if (StringUtil.isNotEmpty(destNumber)) {
					// 发送微信
					messageService.sendWxMsg(request, taskAllot.getId(), Constant.INSPECTION_CORPID,
							Constant.INSPECTION_PWD, content, destNumber);
				}
			}
		}
		logService.setLogs(taskAllot.getId(), "提交任务书", "【质量部】提交任务书", user.getId(), user.getName(),
				new Date(), request.getRemoteAddr());
		
  	}
  	
  	
  	/**
	 * 领取任务表并更新状态
	 * @param ids
	 * @throws Exception
	 */
	public void getTask(TaskAllot taskAllot)throws Exception{
		taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_JXZ);//进行中
		taskAllotDao.save(taskAllot);
  	}
      
	
  	/**
  	 * 完成任务并更新状态,获取签收人及时间
  	 * @param ids
  	 * @throws Exception 
  	 */
  	public void getFinish(String ids ,TaskAllot taskAllot,CurrentSessionUser user) throws Exception{
  		for(String id : ids.split(",")){
  			taskAllot = taskAllotDao.get(id);
  			taskAllotDao.getHibernateTemplate().update(taskAllot);
  		}
  		taskAllot.setPrincipalName(user.getName()); //获取签收人
  		taskAllot.setPrincipalDate(new Date());
  		taskAllot.setSendTime(new Date());//获取完成时间
  		
  	}
	  	/**
	  	 * 保存
	  	 * @param taskAllot
	  	 * @param user
	  	 */
    	public synchronized void saveTask(TaskAllot taskAllot,CurrentSessionUser user){
    		//新增保存时，生成新编号，修改功能不需要重新生成编号
    		if(null==taskAllot.getId() || "".equals(taskAllot.getId())){
    			String docNum = "";
    			Calendar newYear=Calendar.getInstance();
        		int nowYear = newYear.get(Calendar.YEAR);
        		List<TaskAllot> TaskAllotlist = taskAllotDao.getTaskAllot();
        		if(TaskAllotlist==null || TaskAllotlist.size()==0) {
        			docNum = nowYear+"-"+"001";
        		} else {
        			int[] docNumArray = new int[TaskAllotlist.size()];
        			for (int i=0;i<TaskAllotlist.size();i++) {
        				//将编号去掉“-”，转换成int型存入数组
        				if(TaskAllotlist.get(i).getTaskSn()!=null && !"".equals(TaskAllotlist.get(i).getTaskSn())){
        					docNumArray[i] = Integer.parseInt(TaskAllotlist.get(i).getTaskSn().replaceAll("-", ""));
        				}
        			}
        			int max = docNumArray[0];
        			//获取数组中最大的值
        			for (int i : docNumArray) {
        				max = max>i?max:i;
        			}
        			String docNum1 = String.valueOf(max+1);
        			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
         	 			docNum = nowYear+"-"+"001";
         	 		}else{
         	 			//编号加1后重新组
         	 			docNum = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 7);
         	 		}
        		}
        		taskAllot.setTaskSn(docNum);
    		}
    		taskAllot.setRegisterId(user.getId());
    		taskAllot.setRegisterName(user.getName());
    		taskAllot.setRegisterDate(new Date());
    		if(taskAllot.getStatus()== null || "".equals(taskAllot.getStatus())){
    			taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_WTJ);
			}
    		taskAllotDao.save(taskAllot);
    	}
    	


    	/**
    	 * 业务改变状态发送信息
    	 * @param Id
    	 * @throws Exception
    	 */
		public void TaskPush(HttpServletRequest request,String id) throws Exception{
				TaskAllot taskAllot = taskAllotDao.get(id);
				//用","号拆分负责人ID
				String[] userId = taskAllot.getDutyId().split(",");
				for(String userIds:userId){  //循环人员ID
					//获取电话
					String user=taskAllotDao.getsjh(userIds).toString();
					String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
							.substring(1,user.toString().length()-1)).replaceAll("'$1'");
					//发送短信Account
					messageservice.sendMoMsg(request,id,"【质量管理】您收到质量管理部门的任务书！项目名称:"+taskAllot.getItemName()+"；编号:"+taskAllot.getTaskSn(),Account);
					//发送微信
					messageservice.sendWxMsg(request,id,Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, "【质量管理】您收到质量管理部门的任务书！项目名称:"+taskAllot.getItemName()+"；编号:"+taskAllot.getTaskSn(),Account);
				}
    	}
    	
    	
		/**
		 * 删除
		 * */
	  	public void delete(String ids)throws Exception{
	      	for(String id:ids.split(",")){
	      		TaskAllot taskAllot = taskAllotDao.get(id);
	      		taskAllotDao.getHibernateTemplate().delete(taskAllot);
	      	}
	      }
    	
    	
    	
}