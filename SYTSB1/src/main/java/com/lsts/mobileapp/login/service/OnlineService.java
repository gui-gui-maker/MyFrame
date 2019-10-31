package com.lsts.mobileapp.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.mobileapp.login.bean.Online;
import com.lsts.mobileapp.login.dao.OnlineDao;

@Service
public class OnlineService extends EntityManageImpl<Online, OnlineDao>{
	
	@Autowired
	OnlineDao onlineDao;

	public void online(String userId, String clientId,String orgId) {
		@SuppressWarnings("unchecked")
		List<Online> list = onlineDao.createQuery("from Online where userId=? and clientId=? and orgId=? ",userId, clientId,orgId).list();
		if(null!=list && list.size()>0) {
			for (Online online : list) {
				online.setIsOnline("1");
			}
		}else {
			Online online = new Online();
			online.setClientId(clientId);
			online.setUserId(userId);
			online.setOrgId(orgId);
			online.setIsOnline("1");
			onlineDao.save(online);
		}
	}

	public void offline(String userId, String clientId, String orgId) {
		onlineDao.createQuery("update Online set isOnline = '0' where userId=? and clientId=? and orgId=? ",userId, clientId,orgId).executeUpdate();
	}
}
