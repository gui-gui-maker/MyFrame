package com.lsts.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.device.bean.DeviceWarningDeal;
import com.lsts.device.dao.DeviceWarningDealDao;
import com.lsts.device.dao.DeviceWarningStatusDao;




/**
 * 设备处理记录   servier
 * 
 * @author liming
 */
@Service("deviceWarningService")
public class DeviceWarningService  extends EntityManageImpl<DeviceWarningDeal, DeviceWarningDealDao>{

	@Autowired
	private DeviceWarningDealDao deviceWarningDealDao;
	@Autowired
	private DeviceWarningStatusDao deviceWarningStatusDao;
	
	@Transactional
	public void saveDealRecord(DeviceWarningDeal record){
		deviceWarningDealDao.save(record);
	}
  public int countBook(String documentId){
	  return deviceWarningDealDao.countBook(documentId);
  }
}
