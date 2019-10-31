package com.lsts.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.DeviceWarningStatus;
import com.lsts.inspection.bean.InspectionInfo;

/**
 * 
 * @author liming 
 *
 */
@Repository("deviceWarningStatusDao")
public class DeviceWarningStatusDao extends EntityDaoImpl<DeviceWarningStatus>{

	public List<DeviceWarningStatus> queryWaringInfo() {
		List<DeviceWarningStatus> list = new ArrayList<DeviceWarningStatus>();
		String hql = "from DeviceWarningStatus  where send_status = '0'";
		list = this.createQuery(hql).list();
		return list;
	}
}
