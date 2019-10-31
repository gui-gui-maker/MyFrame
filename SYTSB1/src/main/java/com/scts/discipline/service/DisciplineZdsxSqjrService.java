package com.scts.discipline.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;








import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.khnt.base.Factory;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.OrgidLeaderid;
import com.lsts.humanresources.service.OrgidLeaderidManager;
import com.lsts.log.service.SysLogService;
import com.scts.discipline.bean.DisciplineZdsxSqjr;
import com.scts.discipline.dao.DisciplineZdjrDao;
import com.scts.discipline.dao.DisciplineZdsxSqjrDao;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

@Service("disciplineZdsxSqjrService")
public class DisciplineZdsxSqjrService extends EntityManageImpl<DisciplineZdsxSqjr, DisciplineZdsxSqjrDao> {

	@Autowired
	DisciplineZdsxSqjrDao disciplineZdsxSqjrDao;
	@Autowired
	DisciplineZdjrDao disciplineZdjrDao;
	@Autowired
	private FlowExtManager flowExtManager;
    @Autowired
    SysLogService logService;
    @Autowired
    OrgidLeaderidManager orgidLeaderidManager;
    @Autowired
    UserDao userDao;
    @Autowired
    ActivityManager activityManager;
    @Autowired
    MessageService messageService;
    
    private String MSG_CODE="zdsx_sqjr";
    private static String URL=Factory.getSysPara().getProperty("SQJR_WX_SEND_URL");
    
	public synchronized DisciplineZdsxSqjr   savebasic(DisciplineZdsxSqjr bean,CurrentSessionUser user){
		if(StringUtil.isEmpty(bean.getId())){
			bean.setCreate_org_id(user.getDepartment().getId());
			bean.setCreate_org_name(user.getDepartment().getOrgName());
			bean.setCreate_user_id(user.getId());
			bean.setCreate_user_name(user.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		     Date date = new Date();
		     String bh= "SQJR-"+sdf.format(date);
			 Map<String,String> mapBean=disciplineZdsxSqjrDao.getBeanByMaxSn(bh);
			 if(mapBean!=null){
			     int no=Integer.parseInt(mapBean.get("SN"))+1;
			     bean.setSn(bh+"-"+no);
		     }else{
		    	 bean.setSn(bh+"-1");
		     }
			bean.setCreate_time(new Date());
			bean.setState("0");
		}
		
		disciplineZdsxSqjrDao.save(bean);
		return bean;
	}
	
	public HashMap<String, Object> subFlow(DisciplineZdsxSqjr bean,String flowId) throws Exception{
		// 设置状态为审批中
        bean.setState("1");
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, Object> map = new HashMap<String, Object>();
        //--------------必要参数---------------------
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
        map.put(FlowExtWorktaskParam.SERVICE_ID, bean.getId());
        // 业务类型(最好提供)
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, "ZDJC_SQJR_FLOW");
        //---------------可选参数---------------------------------
        // 设置数据总线值
        //查询部门负责人
        JSONObject databus = new JSONObject();
        
        //提交到部门负责人
        String createOrgId=bean.getCreate_org_id();
        JSONArray ptr = new JSONArray();
        String nextUserName="";
        if ("100025".equals(createOrgId) || "100042".equals(createOrgId)) {
            //部门100025（财务管理部）,100048（四川省特种设备检验研究院工会委员会）的部门负责人是孙宇艺
        	 User nextUser = userDao.findLoginUser("孙宇艺", "1");
             JSONObject pt = new JSONObject();
             pt.put("id", nextUser.getId());
             pt.put("name", nextUser.getName());
             nextUserName=nextUser.getName();
             ptr.put(pt);
        }else if ("100030".equals(createOrgId)) {
            //部门100030(科研技术管理部)的部门负责人是韩绍义
        	 User nextUser = userDao.findLoginUser("韩绍义", "1");
             JSONObject pt = new JSONObject();
             pt.put("id", nextUser.getId());
             pt.put("name", nextUser.getName());
             ptr.put(pt);
             nextUserName=nextUser.getName();
        }else if("100044".equals(createOrgId)){//司法鉴定中心部门负责人是李山桥
       	 	User nextUser = userDao.findLoginUser("李山桥", "1");
             JSONObject pt = new JSONObject();
             pt.put("id", nextUser.getId());
             pt.put("name", nextUser.getName());
             ptr.put(pt);
             nextUserName=nextUser.getName();
        } else {
            //其他部门的部门负责人在他们各自的部门里按权限查找。
            List<com.khnt.rbac.bean.User> users = this.findPermissioneUser("TJY2_BMFZR", createOrgId);
            for (int i = 0; i < users.size(); i++) {
                com.khnt.rbac.bean.User nextUser = users.get(i);
                JSONObject pt = new JSONObject();
                pt.put("id", nextUser.getId());
                pt.put("name", nextUser.getName());
                nextUserName=nextUser.getName();
                ptr.put(pt);
            }
        }
//         全局数据参数（数据总线）
        databus.put(FlowEngine.DATA_BUS_PARTICIPANT_KEY_DEFAULT, ptr);
        map.put(FlowExtWorktaskParam.DATA_BUS, databus);
        
        // 业务标题，给工作任务提供自定义的标题
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "重大事项监督-申请介入-" + user.getName());
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        // 启动审批流程
        Map<String, Object>  resultMap=this.flowExtManager.startFlowProcess(map);
        //发微信
        List<Activity> activitys = (List<Activity>) resultMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
        for (Activity activity : activitys) {
        	sendMessage(bean,activity.getId(),resultMap.get(FlowExtWorktaskParam.PROCESS_ID),ptr);
		}
        
        logService.setLogs(bean.getId(), "提交申请", "提交申请到"+nextUserName, user.getId(), user.getName(), new Date(),
                null);
        return JsonWrapper.successWrapper(bean);
	}
	
	public HashMap<String, Object>  getFlowStep(String business_id) throws Exception{
		return disciplineZdsxSqjrDao.getFlowStep(business_id);
	}
	/**
	 * 提交审核
	 * @param bean
	 * @param activity_id
	 * @throws Exception
	 */
	public void sqjrTg(DisciplineZdsxSqjr bean,String activity_id) throws Exception{
		//部门分管领导
		OrgidLeaderid leader = orgidLeaderidManager.getInfoByOrgid(bean.getCreate_org_id());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// ------------- 必要参数 -----------------------
        paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activity_id);//环节id
        //--------------可选参数--------------
        User learderUser = (User) userDao.createQuery("from User where employee.id=?", leader.getFkRlEmplpyeeId()).uniqueResult();
        
        JSONObject databus = new JSONObject();
        String state=bean.getState();
        String czsm="";
        String nextUserName="";
        JSONArray ptr = new JSONArray();
        if("1".equals(state)){//部门负责人审核
        	//提交到部门分管院领导
        	bean.setState("2");
        	bean.setBmfzr_time(new Date());
            //设置部门分管院领导
            if (learderUser!=null) {
                JSONObject jo = new JSONObject();
                jo.put("id", learderUser.getId());
                jo.put("name", learderUser.getName());
                nextUserName=learderUser.getName();
                ptr.put(jo);
                databus.put(FlowEngine.DATA_BUS_PARTICIPANT_KEY_DEFAULT, ptr);
            }
            paramMap.put(FlowExtWorktaskParam.DATA_BUS, databus);
            czsm="部门负责人审核";
        }
        if("2".equals(state)){//部门分管院领导审核
        	bean.setState("3");
        	bean.setBmfgy_time(new Date());
        	czsm="部门分管院领导审核";
        	
        }
        paramMap.put(FlowExtWorktaskParam.SERVICE_ID, bean.getId());
        paramMap.put(FlowExtWorktaskParam.SERVICE_TITLE, "重大事项监督-申请介入");
        paramMap.put(FlowExtWorktaskParam.SERVICE_TYPE, "ZDJC_SQJR_FLOW");
        paramMap.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        //调用流程引擎接口方法执行提交
        Map<String, Object> resultMap=flowExtManager.submitActivity(paramMap);
        if("2".equals(state)){//部门分管院领导审核
    	List<Activity> activitys = (List<Activity>) resultMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
    	for (Activity activity : activitys) {
    		//获取下一步处理人信息
            List<BpmUser> bpmUsers = activityManager.getBpmUserPaticipator(activity.getId());
            for (BpmUser bpmUser : bpmUsers) {
            	nextUserName=bpmUser.getName();
            	JSONObject jo = new JSONObject();
                jo.put("id", bpmUser.getId());
                jo.put("name", bpmUser.getName());
                ptr.put(jo);
            }
            
            
    	}
    }
        List<Activity> activitys = (List<Activity>) resultMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
    	for (Activity activity : activitys) {
    		sendMessage(bean,activity.getId(),resultMap.get(FlowExtWorktaskParam.PROCESS_ID), ptr);
    	}
        
        
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
        logService.setLogs(bean.getId(), "审核申请介入", czsm+"，操作结果，同意。提交到下一步处理人（"+nextUserName+"）。", user.getId(), user.getName(), new Date(),null);
       
        this.save(bean);
	}
	
	public void sendMessage( DisciplineZdsxSqjr entity ,String activity_id,Object PROCESS_ID,JSONArray user) throws Exception{
		HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("org", entity.getCreate_org_name());
        map1.put("sn", entity.getSn());
        for (int i = 0; i < user.length(); i++) {
        	HashMap<String, Object> map2 = new HashMap<String, Object>();
        	JSONObject jo=(JSONObject)user.get(i);
        	List<Map<String, Object>> usert = disciplineZdsxSqjrDao.getUserById(jo.getString("id"));
        	System.out.println("申请介入电话号码------------------------"+String.valueOf(usert.get(0).get("MOBILE_TEL")));
            map2.put("url", URL + "?businessId=" + entity.getId() +"%26state=" + entity.getState()+ "%26activity_id=" + activity_id + "%26process_id=" + String.valueOf(PROCESS_ID) + "&response_type=code&scope=snsapi_base&state=STATE");
   	        messageService.sendMassageByConfig(null, entity.getId(), String.valueOf(usert.get(0).get("MOBILE_TEL")), null, MSG_CODE,
   	        		null, map1, map2, "2", Constant.CHLQ_CORPID, Constant.CHLQ_PWD);
		}
	}
	
	/**
	 * 结束流程
	 * @param entity
	 * @param type
	 * @param process_id
	 */

	public void sqjrFlowEnd(DisciplineZdsxSqjr entity,String type,String process_id){
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// ------------- 必要参数 -----------------------
	        paramMap.put(FlowExtWorktaskParam.PROCESS_ID, process_id);
	       // ------------- 可选参数 -----------------------
	        String czsm="";
	        if("1".equals(type)){//正常结束流程
	        	paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_COMMON);
				entity.setState("4");//标记结束
	        	entity.setJjfgy_time(new Date());
	        	czsm="纪检分管院领导，操作结果，同意。";
	        }
	        if("2".equals(type)){//异常(强制)结束
	        	paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
	        	czsm="流程结束，审核不通过！";
				entity.setState("5");//标记不通过
	        }
			flowExtManager.finishProcess(paramMap);
			
			disciplineZdsxSqjrDao.save(entity);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
	        logService.setLogs(entity.getId(), "审核申请介入", czsm, user.getId(), user.getName(), new Date(),null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

    public List<com.khnt.rbac.bean.User> findPermissioneUser(String pcode, String orgId) {
        SQLQuery query = (SQLQuery) disciplineZdsxSqjrDao.createSQLQuery("select u.id,u.name from sys_org o join sys_user u on u.org_id=o.id join sys_user_role ur on ur.user_id = u.id join sys_role_permission rp on rp.role_id = ur.role_id join sys_permission p on p.id = rp.permission_id where p.p_code=:pcode" + (orgId != null ? " and (o.id=:oid or o.parent_id=:oid)" : ""), new Object[0]);
        query.setString("pcode", pcode);
        if (orgId != null) {
            query.setString("oid", orgId);
        }

        List<Object[]> ql = query.list();
        List<com.khnt.rbac.bean.User> rus = new ArrayList();
        Iterator var7 = ql.iterator();

        while (var7.hasNext()) {
            Object[] o = (Object[]) var7.next();
            User u = new User();
            u.setId(String.valueOf(o[0]));
            u.setName(String.valueOf(o[1]));
            rus.add(u);
        }

        return rus;
    }
 // 删除
 	public void del(String id) {
 			try {
 				if (StringUtil.isNotEmpty(id)) {
 					// 1、删除
 					disciplineZdsxSqjrDao.createSQLQuery("update TJY2_DISCIPLINE_ZDSX_SQJR set state='99' where id = ? ", id)
 							.executeUpdate();
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 				log.debug(e.toString());
 			}
 	}
 	
 	public List<Map<String, Object>> querychecked(String userId){
 		return disciplineZdsxSqjrDao.querychecked(userId);
 	}
 	public List<Map<String, Object>> querycheck(String userId){
 		return disciplineZdsxSqjrDao.querycheck(userId);
 	}
 	public List<DisciplineZdsxSqjr> querymy(String userId){
 		return disciplineZdsxSqjrDao.querymy(userId);
 	}
 	public void saveAndSubmit(CurrentSessionUser user,DisciplineZdsxSqjr entity,String flowId){
 		try {
 			DisciplineZdsxSqjr bean=this.savebasic(entity,user);
 	 		this.subFlow(bean,flowId);
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
}