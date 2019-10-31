package com.lsts.advicenote.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.advicenote.bean.MsgLinkAudit;
import com.lsts.advicenote.dao.MessageHistoryDao;
import com.lsts.advicenote.dao.MsgLinkAuditDao;


@Service("msgLinkAuditService")
public class MsgLinkAuditService extends EntityManageImpl<MsgLinkAudit, MsgLinkAuditDao> {

	@Autowired
	private MsgLinkAuditDao msgLinkAuditDao;
	@Autowired
	private MessageHistoryDao messageHistoryDao;
	
	//查询待审核的配置信息
	public HashMap<String,Object> getAuditDetail(String id){
		HashMap<String,Object> map = new HashMap<String, Object>();
		
		MsgLinkAudit audit = msgLinkAuditDao.get(id);
		JSONObject json = new JSONObject();
		
		if(audit.getDatas()!=null&&StringUtil.isNotEmpty(audit.getDatas())) {
			
			json = JSONObject.parseObject(audit.getDatas());
			
		}
		map.put("data", audit);
		map.put("datas", json);
		
		return map;
	}
	
	/**
	 * 标记待审核信息已经审核
	 * author pingZhou
	 * @param id
	 */
	public void setAuditStatus(String id){
		
		MsgLinkAudit audit = msgLinkAuditDao.get(id);
		audit.setStatus("1");
		audit.setOp_date(new Date());
		
		msgLinkAuditDao.save(audit);
		
	}
	/**
	 * 标记定时任务待审核信息已经审核
	 * author zpl
	 * @param id 业务id
	 * @param phone 电话号码
	 */
	public void setAuditStatusDel(String id){
		String sql="update MESSAGE_INFO set status='99' where business_id='"+id+"'  and status='1'";
       messageHistoryDao.createSQLQuery(sql).executeUpdate();
       
		
	}
	
	/**
	 * 标记定时任务待审核信息已经作废
	 * author 
	 * @param id 业务id
	 * @param phone 电话号码
	 */
	public void setAuditStatus(String id,String phone){
		String hql="from MessageHistory where business_ids='"+id+"' and mobile='"+phone+"' and status='1'";
        List<MessageHistory> list=messageHistoryDao.createQuery(hql).list();
        if(list.size()>0){
        	MessageHistory mess=list.get(0);
        	mess.setStatus("3");//标记该定时任务已审核 不再发送
        	messageHistoryDao.save(mess);
        }
		
	}

	/**
	 * 添加待审核配置信息
	 * author pingZhou
	 * @param title 业务模块标题
	 * @param businessId 业务id
	 * @param businessTable 业务表
	 * @param sql sql
	 * @param datas 展示数据详情
	 * @param action 处理方法
	 * @param nextOpSql 提交选择人员sql
	 * @param handle_id 处理人id
	 * @param handle_op 处理人
	 * @param next_op 
	 * @return 
	 */
	public String addNeedAudit(String title,String businessId, String businessTable, String sql, 
			String datas, String action, String nextOpSql,
			String handle_id, String handle_op) {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		MsgLinkAudit audit = new MsgLinkAudit();
		
		audit.setTitle(title);
		audit.setStatus("0");
		audit.setCreate_date(new Date());
		audit.setAction(action);
		audit.setBusiness_id(businessId);
		audit.setBusiness_table(businessTable);
		audit.setCreate_op(user.getName());
		audit.setCreate_op_id(user.getId());
		audit.setDatas(datas);
		audit.setNext_op_sql(nextOpSql);
		audit.setHandle_id(handle_id);
		audit.setHandle_op(handle_op);
		audit.setSql(sql);
		
		msgLinkAuditDao.save(audit);
	
		return audit.getId();
	}

	public HashMap<String, Object> getSqltabl(String sql) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> data  = new ArrayList<Map<String,Object>>();
		
		List<Object> list = msgLinkAuditDao.createSQLQuery(sql).list();
		
		for (int i = 0; i < list.size(); i++) {
			Object [] obj = (Object[]) list.get(i);
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("id", obj[0].toString());
			map.put("name", obj[1].toString());
			data.add(map);
			
		}
		
		resultMap.put("data", data);
		
		return resultMap;
	}
}
