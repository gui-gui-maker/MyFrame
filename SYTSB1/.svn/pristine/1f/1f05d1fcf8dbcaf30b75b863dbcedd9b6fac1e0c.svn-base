package com.lsts.device.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.device.bean.DeviceWarningStatus;
import com.lsts.device.dao.DeviceWarningStatusDao;




/**
 * 设备处理记录   servier
 * 
 * @author liming
 */
@Service("deviceWarningStatusService")
public class DeviceWarningStatusService  extends EntityManageImpl<DeviceWarningStatus, DeviceWarningStatusDao>{
	@Autowired
	private DeviceWarningStatusDao deviceWarningStatusDao;

	public void update(DeviceWarningStatus entity){
		deviceWarningStatusDao.getSessionFactory().getCurrentSession().update(entity);
//		deviceWarningStatusDao.createSQLQuery("update  tzsb_warning_status  set deal_status=?,deal_time=?,active_flag=?,remark=?,inspection_date=?,fk_base_device_document_id=? where id = ?", 
//				entity.getDeal_status(),entity.getDeal_time(),entity.getActive_flag(),entity.getRemark(),new Date(),entity.getFk_base_device_document_id(),entity.getId()).executeUpdate();
	}
}
