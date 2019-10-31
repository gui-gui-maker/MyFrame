package com.scts.task.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.scts.task.bean.ContractTaskInfo;
import com.scts.task.dao.ContractTaskInfoDao;

import util.TS_Util;

/**
 * 合同检验任务单业务逻辑对象
 * @ClassName ContractTaskInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-04-02 15:22:00
 */
@Service("contractTaskInfoService")
public class ContractTaskInfoService extends
		EntityManageImpl<ContractTaskInfo, ContractTaskInfoDao> {
	@Autowired
	private ContractTaskInfoDao contractTaskInfoDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private AttachmentManager attachmentManager;
    @Autowired
    MessageService messageService;
    @Autowired
    UserDao userDao;
    @Autowired
    EmployeesDao employeesDao;
    
    

	private static Connection conn = null; // 数据库连接

	// 保存
	public void saveInfo(HttpServletRequest request, ContractTaskInfo info,String uploadFiles) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		//User user = (User) curUser.getSysUser();
		//Employee emp = (Employee) user.getEmployee();
		// Org org = (Org) curUser.getDepartment();
		String status = request.getParameter("status");

		// 1、保存
		if (StringUtil.isEmpty(info.getSn())) {
			String sn_pre = TS_Util.getCurYear() + "-";
			info.setSn(sn_pre + queryNextSn(sn_pre));
		}

		if (info.getInspection_date() == null) {
			info.setInspection_date(DateUtil.convertStringToDate(Constant.defaultDatePattern, DateUtil.getCurrentDateTime()));
		}

		if ("add".equals(status)) {
			info.setData_status("0"); 				// 数据状态（0：创建）
			info.setCreate_user_id(curUser.getId()); 	
			info.setCreate_user_name(curUser.getName());
			info.setCreate_date(
					DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime()));
		} else {
			info.setData_status("1"); 					// 数据状态（1：修改）
			info.setMdy_user_id(curUser.getId()); 		// 最后修改人ID
			info.setMdy_user_name(curUser.getName()); 	// 最后修改人姓名
			info.setMdy_date(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime())); // 最后修改时间
		}
		contractTaskInfoDao.save(info);
		if(StringUtil.isNotEmpty(uploadFiles)){//有附件
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, info.getId());
				}
			}
			
		}
		
		if("add".equals(status)){
			//通知承办和参检部门的负责人
			int b= info.getCjry_id()==null ? 0:info.getCjry_id().split(",").length;
			String[] bum=new String[b+1];
			if(StringUtil.isNotEmpty(info.getCjry_id())){
				
				String[] cjbm=info.getCjry_id().split(",");
				for (int i = 0; i < cjbm.length; i++) {
					bum[i]=cjbm[i];
				}
			}
			bum[b]=info.getOrg_id();
			Object[] sendbm=array(bum);//数组去重复
			sendMessage(sendbm,request,info);
		}
		
		
		
		// 2、记录日志
		String status_text = "创建";
		if ("modify".equals(status)) {
			status_text = "修改";
		}
		logService.setLogs(info.getId(), status_text + "检验任务单", status_text + "检验任务单", curUser.getId(), curUser.getName(),
				new Date(), request.getRemoteAddr());
	}
	/**
	 * 给部门负责人发送消息
	 * @param sendbm 需要发送消息的部门id
	 * @throws Exception 
	 */
	private void sendMessage(Object[] sendbm,HttpServletRequest request,ContractTaskInfo info) throws Exception{
		String tel="";
		for (int i = 0; i < sendbm.length; i++) {
			if ("100025".equals(sendbm[i]) || "100042".equals(sendbm[i])) {
	            //部门100025（财务管理部）,100048（四川省特种设备检验研究院工会委员会）的部门负责人是孙宇艺
	        	User user = userDao.findLoginUser("孙宇艺", "1");
            	String usertel=employeesDao.getMobileByUserId(user.getId());
            	tel=tel+usertel+",";
	        }else if ("100030".equals(sendbm[i])) {
	        	//部门100030(科研技术管理部)的部门负责人是韩绍义
	        	User user = userDao.findLoginUser("韩绍义", "1");
            	String usertel=employeesDao.getMobileByUserId(user.getId());
            	tel=tel+usertel+",";
	        }else if("100044".equals(sendbm[i])){//司法鉴定中心部门负责人是李山桥
	        	User user = userDao.findLoginUser("李山桥", "1");

            	String usertel=employeesDao.getMobileByUserId(user.getId());
            	tel=tel+usertel+",";
	        } else {
	            //其他部门的部门负责人在他们各自的部门里按权限查找。
	            List<com.khnt.rbac.bean.User> users = this.findPermissioneUser("TJY2_BMFZR", sendbm[i].toString());
	            for (com.khnt.rbac.bean.User user : users) {
	            	String usertel=employeesDao.getMobileByUserId(user.getId());
	            	tel=tel+usertel+",";
				}
	        }
		}
		tel=tel.substring(0,tel.length()-1);
		HashMap<String, Object> map1 = new HashMap<String, Object>();
	     map1.put("com_name", info.getCom_name());
	     map1.put("sn", info.getSn());
		
		//发消息
	     String[] tels=tel.split(",");
	     for (int i = 0; i < tels.length; i++) {
	    	 if(StringUtil.isNotEmpty(tels[i])){
	    		 messageService.sendMassageByConfig(request, info.getId(), tels[i], null, "jyrwd",
		                 null, map1, null, "2", Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
	    	 }
	    	 
		}
	}
	//去重复
	 public Object[] array(Object [] arr){
	        //实例化一个set集合
	        Set set = new HashSet();
	        //遍历数组并存入集合,如果元素已存在则不会重复存入
	        for (int i = 0; i < arr.length; i++) {
	            set.add(arr[i]);
	        }
	        //返回Set集合的数组形式
	        return set.toArray();
	    }

	    public List<com.khnt.rbac.bean.User> findPermissioneUser(String pcode, String orgId) {
	        SQLQuery query = (SQLQuery) contractTaskInfoDao.createSQLQuery("select u.id,u.name from sys_org o join sys_user u on u.org_id=o.id join sys_user_role ur on ur.user_id = u.id join sys_role_permission rp on rp.role_id = ur.role_id join sys_permission p on p.id = rp.permission_id where p.p_code=:pcode" + (orgId != null ? " and (o.id=:oid or o.parent_id=:oid)" : ""), new Object[0]);
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
	public String getContractTasks() throws Exception {
		String jsonstring = "";
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			Org org = TS_Util.getCurOrg(curUser);
			
			String sql = "select t.id as id,t.sn as text from CONTRACT_TASK_INFO t where t.data_status !='99' and t.org_id='"+org.getId()+"'";
			List list = contractTaskInfoDao.createSQLQuery(sql).list();
			Object[] objArr = list.toArray();
			for (int i = 0; i < objArr.length; i++) {
				Object[] oo = (Object[]) objArr[i];
				jsonstring += "{\"id\":\"" + oo[0] + "\",\"text\":\"" + oo[1] + "\"},";
			}
			if (jsonstring.endsWith(",")) {
				jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
			}
			jsonstring = "[" + jsonstring + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonstring;
	}
	
	public String getContractTasks(String check_type,String com_name){
		String jsonstring = "";
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			
//			String sql = "select t.id as id,t.sn as text from CONTRACT_TASK_INFO t where t.data_status !='99' and t.check_type=? and t.SBLX in ('"+sblx.trim().replaceAll(",", "','")+"')";
			String sql = "select t.id as id,t.sn as text from CONTRACT_TASK_INFO t where t.data_status !='99' and t.check_type=? and t.com_id =? and ORG_ID=? or CJRY_ID like '%"+curUser.getDepartment().getId()+"%'";
			List list = contractTaskInfoDao.createSQLQuery(sql,check_type,com_name,curUser.getDepartment().getId()).list();
			Object[] objArr = list.toArray();
			for (int i = 0; i < objArr.length; i++) {
				Object[] oo = (Object[]) objArr[i];
				jsonstring += "{\"id\":\"" + oo[0] + "\",\"text\":\"" + oo[1] + "\"},";
			}
			if (jsonstring.endsWith(",")) {
				jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
			}
			jsonstring = "[" + jsonstring + "]";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonstring;
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			if (StringUtil.isNotEmpty(id)) {
				// 1、删除
				contractTaskInfoDao.createSQLQuery("update CONTRACT_TASK_INFO set data_status='99' where id = ? ", id)
						.executeUpdate();
				// 2、写入日志
				logService.setLogs(id, "作废检验任务单", "作废检验任务单", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	// 获取流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getFlowStep(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = contractTaskInfoDao
				.createQuery(" from SysLog where business_id ='" + id + "' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", contractTaskInfoDao.get(id).getSn());
		map.put("success", true);
		return map;
	}

	// 获取任务书编号后三位序号
	public String queryNextSn(String sn_pre) {
		return contractTaskInfoDao.queryNextSn(sn_pre);
	}

	// 获取数据库连接
	public Connection getConn() {
		try {
			conn = Factory.getDB().getConnetion();
		} catch (Exception e) {
			log.error("获取数据库连接失败：" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	public void closeConn() {
		try {
			/*
			 * if (null != rs) rs.close(); if (null != pstmt) pstmt.close(); if
			 * (null != conn) conn.close();
			 */
			Factory.getDB().freeConnetion(conn); // 释放连接
		} catch (Exception e) {
			log.error("关闭数据库连接错误：" + e.getMessage());
			e.printStackTrace();
		}
	}
	public List<Map<String, Object>> getCjsb(String ids){
		return contractTaskInfoDao.getCjsb(ids);
	}
	
	public List<Map<String, Object>> getbgxx(String id){
		return contractTaskInfoDao.getbgxx(id);
	}
}
