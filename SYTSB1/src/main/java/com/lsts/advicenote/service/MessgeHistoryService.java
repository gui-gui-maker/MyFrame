package com.lsts.advicenote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.scientific.bean.TjDTO;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.advicenote.dao.MessageHistoryDao;

/**
 * 提醒消息发送日志业务逻辑对象
 * @ClassName MessgeHistoryService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-01-22 上午11:21:00
 */
@Service("messageHistoryService")
public class MessgeHistoryService extends
		EntityManageImpl<MessageHistory, MessageHistoryDao> {
	@Autowired
	private MessageHistoryDao messageHistoryDao;

	  public  List<TjDTO>  getMessageLog(String name,String org_name,String sdate,String edate){
		  return messageHistoryDao.getMessageLog(name,org_name,sdate,edate);
	  }
}
