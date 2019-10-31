package com.lsts.advicenote.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.advicenote.bean.MessageInfo;


/**
 * 短信
 * @ClassName messageDao
 * @JDK 1.7

 */
@Repository("messageDao")
public class MessageDao extends EntityDaoImpl<MessageInfo> {
	
	
}
