package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.RlMessage;
import com.lsts.humanresources.dao.RlMessageDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("rlMessageManager")
public class RlMessageManager extends EntityManageImpl<RlMessage,RlMessageDao>{
    @Autowired
    RlMessageDao messageDao;
}
