package com.lsts.finance.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.dao.ClfbxdDao;
import com.lsts.finance.dao.FybxdDao;
import com.lsts.finance.dao.MessageCheckDao;
import com.lsts.humanresources.bean.EmployeeBase;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("messageCheckManager")
public class MessageCheckManager extends EntityManageImpl<MessageCheck2, MessageCheckDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	MessageCheckDao messageCheckDao;
	
	/**查找申请人手机号
	 * @return
	 */
	public String getsjh(String id){
		 List<String> number1 = messageCheckDao.getsjh(id);
	System.out.println(number1.get(0));
	System.out.println(number1.get(0)==null);
	String  number=	number1.get(0);
			
		return number;
		
	}

	public MessageCheck2 messageid(String dhhm, String yzm) throws ParseException {
		
		List<MessageCheck2> message =	messageCheckDao.MessageCheck2(dhhm, yzm);
		MessageCheck2 nowid= message.get(0);
		
		
		return nowid;
	}
	
	
	public MessageCheck2 messageid12(String yzm) throws ParseException {
		
		List<MessageCheck2> message =	messageCheckDao.MessageCheck212(yzm);
		MessageCheck2 nowid= message.get(0);
		
		
		return nowid;
	}
	
	public String getmid(String id) throws ParseException {
		
		List getmids =	messageCheckDao.getmid(id);
		
		Object user1[] = null;
		user1 = (Object[]) getmids.get(0);
		
		System.out.println(user1[7]);
		String mids =  user1[7].toString();
		return mids;
	}
	
	public void sets(String businessId,String sendType,String account,String wxNumber,Date sendTime,Date endTime,
			Date checkTime,String status,String ip ,String fkMsg){
		MessageCheck2 messageCheck2=new MessageCheck2();
		
		messageCheck2.setBusinessId(businessId);
		messageCheck2.setSendType(sendType);
		messageCheck2.setAccount(account);
		messageCheck2.setWxNumber(wxNumber);
		messageCheck2.setSendTime(sendTime);
		messageCheck2.setEndTime(endTime);
		messageCheck2.setCheckTime(checkTime);
		messageCheck2.setStatus(status);
		messageCheck2.setIp(ip);
		messageCheck2.setFkMsg(fkMsg);
		
		messageCheckDao.save(messageCheck2);
		
	}

	public void save2(MessageCheck2 messageCheck2) {
		// TODO Auto-generated method stub
		messageCheckDao.save(messageCheck2);
	}
	
	
	}

