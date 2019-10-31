package com.scts.discipline.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.khnt.base.Factory;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.engine.FlowEngine;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.log.service.SysLogService;
import com.scts.discipline.bean.DisciplineZdjr;
import com.scts.discipline.bean.DisciplineZdsxSqjr;
import com.scts.discipline.dao.DisciplineZdjrDao;

@Service("disciplineZdjrService")
public class DisciplineZdjrService  extends EntityManageImpl<DisciplineZdjr, DisciplineZdjrDao>{
	@Autowired
	DisciplineZdjrDao disciplineZdjrDao;
	@Autowired
	FlowExtManager flowExtManager;
    @Autowired
    SysLogService logService;
    @Autowired
    UserDao userDao;
    @Autowired
    ActivityManager activityManager;
	@Autowired
	CodeTableCache codeTableCache;
    @Autowired
    FlowServiceConfigManager flowServiceConfigManager;
    @Autowired
    MessageService messageService;
    String MSG_CODE="zdsx_zdjr";
    private static String URL=Factory.getSysPara().getProperty("ZDJR_WX_SEND_URL");
    
    
	public synchronized  DisciplineZdjr   saveZdjr(CurrentSessionUser user,DisciplineZdjr bean){
		if(StringUtil.isEmpty(bean.getId())){
			bean.setCreate_org_id(user.getDepartment().getId());
			bean.setCreate_org_name(user.getDepartment().getOrgName());
			bean.setCreate_user_id(user.getId());
			bean.setCreate_user_name(user.getName());  
			bean.setCreate_time(new Date());
			//ZDJR-20181012-00001

			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		     Date date = new Date();
		     String bh= "ZDJR-"+sdf.format(date);
			 Map<String,String> mapBean=disciplineZdjrDao.getBeanByMaxSn(bh);
			 if(mapBean!=null){
			     int no=Integer.parseInt(mapBean.get("SN"))+1;
			     bean.setSn(bh+"-"+no);
		     }else{
		    	 bean.setSn(bh+"-1");
		     }
			bean.setState("0");
		}
		 disciplineZdjrDao.save(bean);
		 return bean;
	}
	/**
	 * 启动流程
	 * @param entity
	 * @param nextPersonId
	 * @param nextPersonName
	 * @param flowId
	 */
	public void subFlow(DisciplineZdjr entity,String flowId){
		try {
			// 设置状态为审批中
			entity.setState("1");
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			Map<String, Object> map = new HashMap<String, Object>();
	        //--------------必要参数---------------------
	        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
	        map.put(FlowExtWorktaskParam.SERVICE_ID, entity.getId());
	        // 业务类型(最好提供)
	        map.put(FlowExtWorktaskParam.SERVICE_TYPE, "ZDJC_ZDJR_FLOW");
	        //---------------可选参数---------------------------------
	        // 设置数据总线值
	        //查询部门负责人
//	        List<User> list= disciplineZdsxSqjrDao.getBmfzr(bean.getSzbm_id());
	        
	        JSONObject databus = new JSONObject();

	        String createOrgId=entity.getCreate_org_id();
	        JSONArray ptr = new JSONArray();
	        String nextUserName="";
	        String tel="";
	        if ("100025".equals(createOrgId) || "100042".equals(createOrgId)) {
	            //部门100025,100048的部门负责人是孙宇艺
	        	 User nextUser = userDao.findLoginUser("孙宇艺", "1");
	             JSONObject pt = new JSONObject();
	             pt.put("id", nextUser.getId());
	             pt.put("name", nextUser.getName());
	             nextUserName=nextUser.getName();
	             ptr.put(pt);
	             tel=nextUser.getEmployee().getMobileTel();
	        }else if ("100030".equals(createOrgId)) {
	            //部门100030的部门负责人是韩绍义
	        	 User nextUser = userDao.findLoginUser("韩绍义", "1");
	             JSONObject pt = new JSONObject();
	             pt.put("id", nextUser.getId());
	             pt.put("name", nextUser.getName());
	             ptr.put(pt);
	             nextUserName=nextUser.getName();
	             tel=nextUser.getEmployee().getMobileTel();
	        }else if("100044".equals(createOrgId)){//司法鉴定中心部门负责人是李山桥
	       	 	User nextUser = userDao.findLoginUser("李山桥", "1");
	             JSONObject pt = new JSONObject();
	             pt.put("id", nextUser.getId());
	             pt.put("name", nextUser.getName());
	             ptr.put(pt);
	             nextUserName=nextUser.getName();
	             tel=nextUser.getEmployee().getMobileTel();
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
            databus.put("paticipator", ptr);
//	        	全局数据参数（数据总线）
	        map.put(FlowExtWorktaskParam.DATA_BUS, databus);
	        // 业务标题，给工作任务提供自定义的标题
	        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "重大事项监督-申请介入-" + user.getName());
	        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	        // 启动审批流程
	       Map<String, Object> startFlow= this.flowExtManager.startFlowProcess(map);
	       //{result_type=2, process_id=4028801268980a240168980bdf950001, 
	       //result_activity_list=[com.khnt.bpm.core.bean.Activity@63ff48d0], bpm_user_next_activity=null, process_name=主动介入}
	       List<Activity> activitys = (List<Activity>) startFlow.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
	       for (Activity activity : activitys) {
	    	 //发送信息
		        sendMessage(entity,activity.getId(),startFlow.get(FlowExtWorktaskParam.PROCESS_ID),ptr);
	       }
	       
	        logService.setLogs(entity.getId(), "提交申请", "提交申请到下一步处理人（"+nextUserName+"）。", user.getId(), user.getName(), new Date(),
	                null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * 主动介入-通过
	 * @param entity
	 * @param activity_id
	 */
	public void zdjrTg(DisciplineZdjr entity,String activity_id,String jjfgy){
		try {

			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			if(Boolean.parseBoolean(jjfgy)){
				entity.setState("3");
				entity.setJjgzyj_fzr(user.getName());
				entity.setJjgzyj_time(new Date());
			}else{
				entity.setState("2");
				entity.setBmyj_time(new Date());
		        entity.setBmyj_fzr(user.getName());
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// ------------- 必要参数 -----------------------
	        paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activity_id);//环节id
	        paramMap.put(FlowExtWorktaskParam.SERVICE_ID, entity.getId());
	        paramMap.put(FlowExtWorktaskParam.SERVICE_TITLE, "重大事项监督-主动介入");
	        paramMap.put(FlowExtWorktaskParam.SERVICE_TYPE, "ZDJC_ZDJR_FLOW");
	        paramMap.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	        JSONObject dataBus = new JSONObject();
//	        dataBus.put("jjfgyld", jjfgy);
//	        paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
	        paramMap.put(FlowExtWorktaskParam.DATA_BUS, dataBus.put("jjfgyld", jjfgy));
	      //调用流程引擎接口方法执行提交
	        Map<String, Object> resultMap=flowExtManager.submitActivity(paramMap);
	        List<Activity> activitys = (List<Activity>) resultMap.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
	        System.out.println(resultMap);
	        String nextName="";
	        JSONArray ptr = new JSONArray();
	        for (Activity activity : activitys) {
                //获取下一步处理人信息
                List<BpmUser> bpmUsers = activityManager.getBpmUserPaticipator(activity.getId());
                for (BpmUser bpmUser : bpmUsers) {
                	nextName=bpmUser.getName();
                	JSONObject jo = new JSONObject();
                    jo.put("id", bpmUser.getId());
                    jo.put("name", bpmUser.getName());
                    ptr.put(jo);
                }
                
    	        //发送信息
    	        sendMessage(entity,activity.getId(),resultMap.get(FlowExtWorktaskParam.PROCESS_ID),ptr);
	        }
	        logService.setLogs(entity.getId(), "审核主动介入", "部门负责人，同意。提交到下一步处理人（"+nextName+"）", user.getId(), user.getName(), new Date(),
	                null);
	        this.save(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
        
	}
	public void sendMessage( DisciplineZdjr entity ,String activity_id,Object PROCESS_ID,JSONArray user) throws Exception{
		HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("org", entity.getCreate_org_name());
        map1.put("sn", entity.getSn());
        for (int i = 0; i < user.length(); i++) {
        	HashMap<String, Object> map2 = new HashMap<String, Object>();
        	JSONObject jo=(JSONObject)user.get(i);
        	List<Map<String, Object>> usert = disciplineZdjrDao.getUserById(jo.getString("id"));
        	System.out.println("申请介入电话号码------------------------"+String.valueOf(usert.get(0).get("MOBILE_TEL")));
            map2.put("url", URL + "?businessId=" + entity.getId() +"%26state=" + entity.getState()+ "%26activity_id=" + activity_id + "%26process_id=" + PROCESS_ID + "&response_type=code&scope=snsapi_base&state=STATE");
   	        messageService.sendMassageByConfig(null, entity.getId(), String.valueOf(usert.get(0).get("MOBILE_TEL")), null, MSG_CODE,
   	        		null, map1, map2, "2", Constant.CHLQ_CORPID, Constant.CHLQ_PWD);
		}
	}
	public void zdjrFlowEnd(DisciplineZdjr entity,String type,String process_id){
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// ------------- 必要参数 -----------------------
	        paramMap.put(FlowExtWorktaskParam.PROCESS_ID, process_id);
	       // ------------- 可选参数 -----------------------
	        String czsm="";
	        if("1".equals(type)){//正常结束流程
	        	paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_COMMON);
	        	czsm="纪检工作安排，同意。";
				entity.setState("4");//标记结束
				entity.setJjgzyj_time(new Date());
		        entity.setJjgzyj_fzr("操伟平");
	        }
	        if("2".equals(type)){//异常(强制)结束
	        	if("1".equals(entity.getState())){//部门意见
	        		entity.setBmyj_fzr(user.getName());
	        		entity.setBmyj_time(new Date());
	        	}
	        	if("2".equals(entity.getState())){//纪检负责人
	        		entity.setJjgzyj_fzr(user.getName());
	        		entity.setJjgzyj_time(new Date());
	        	}
	        	paramMap.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
	        	czsm="流程结束，审核不通过！";
				entity.setState("5");//标记不通过
	        }
			flowExtManager.finishProcess(paramMap);
			disciplineZdjrDao.save(entity);
			 logService.setLogs(entity.getId(), "审核主动介入", czsm, user.getId(), user.getName(), new Date(),
		                null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	 public List<com.khnt.rbac.bean.User> findPermissioneUser(String pcode, String orgId) {
	        SQLQuery query = (SQLQuery) disciplineZdjrDao.createSQLQuery("select u.id,u.name from sys_org o join sys_user u on u.org_id=o.id join sys_user_role ur on ur.user_id = u.id join sys_role_permission rp on rp.role_id = ur.role_id join sys_permission p on p.id = rp.permission_id where p.p_code=:pcode" + (orgId != null ? " and (o.id=:oid or o.parent_id=:oid)" : ""), new Object[0]);
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
	 
	 public DisciplineZdjr getBeanById(String id){
		 DisciplineZdjr entity=this.get(id);
		 CodeTable ct = this.codeTableCache.getCodeTable("zdsx_zdjr_jdlb");//监督类别
			List<CodeTableValue> codeValues=ct.getCodeTableValues();
			for (CodeTableValue codeTableValue : codeValues) {
				if(entity.getJdlb().equals(codeTableValue.getValue())){
					entity.setJdlb(codeTableValue.getName());
				}
			}
			CodeTable ctfs = this.codeTableCache.getCodeTable("zdsx_zdjr_jdfs");//监督方式
			List<CodeTableValue> codeValuesfz=ctfs.getCodeTableValues();
			for (CodeTableValue codeTableValue : codeValuesfz) {
				if(entity.getJdfs().equals(codeTableValue.getValue())){
					entity.setJdfs(codeTableValue.getName());
				}
			}
		 return entity;
	 }
	// 删除
	public void del(String id) {
			try {
				if (StringUtil.isNotEmpty(id)) {
					// 1、删除
					disciplineZdjrDao.createSQLQuery("update TJY2_DISCIPLINE_ZDSX_ZDJR set state='99' where id = ? ", id)
							.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.toString());
			}
	}
	
	public List<Map<String, Object>> getDsh(String userId){
		return disciplineZdjrDao.getDsh(userId);
	}
	
	public List<Map<String, Object>> getFlowByserviceIdAndHandlerId(String serviceId,String userId,String work_type){
		return disciplineZdjrDao.getFlowByserviceIdAndHandlerId(serviceId, userId, work_type);
	}
	/**
	 * 已办理
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getYbl(String userId){
		return disciplineZdjrDao.getYbl(userId);
	}
	/**
	 * 我的申请
	 * @param UserId
	 * @return
	 */
	public List<DisciplineZdjr> getMySq(String userId){
		return disciplineZdjrDao.getDisciplineZdjrsByCreateUserId(userId);
	}
	
	public void saveAndSubmit(CurrentSessionUser user,DisciplineZdjr entity,String flowId) throws Exception{
		DisciplineZdjr bean=this.saveZdjr(user, entity);
		this.subFlow(bean, flowId);
	}
}
