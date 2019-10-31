package com.scts.transfer.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.inspection.bean.InspectionInfo;
import com.scts.transfer.bean.DeviceTransfer;


/**
 * 设备数据传输控制器数据处理对象
 * @ClassName DeviceTransferDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-02-22 上午10:23:00
 */
@Repository("deviceTransferDao")
public class DeviceTransferDao extends EntityDaoImpl<DeviceTransfer> {

	@SuppressWarnings("unchecked")
	public List<DeviceTransfer> getDeviceTransfer() {
		List<DeviceTransfer> list = new ArrayList<DeviceTransfer>();
		String hql = "from DeviceTransfer t where t.data_status != '99'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	public List<DeviceDocument> queryDeviceInfo() {
		List<DeviceDocument> list = new ArrayList<DeviceDocument>();
		String hql = "from DeviceDocument t where t.data_status != '99'";
		list = this.createQuery(hql).list();
		return list;
	}
	public List<InspectionInfo> queryCheckInfo() {
		List<InspectionInfo> list = new ArrayList<InspectionInfo>();
		
		String hql = "from InspectionInfo t where t.send_status = '0' and t.is_issue='1' and ( t.report_sn like 'CO-TA%')"; 
//				"from InspectionInfo t where t.send_status = '0' and t.is_issue='1' and t.data_status<>'99' and t.report_sn in ('CO-TD201450135','CO-TD201450366','CO-TA201450014')";
			//	+ "t.fk_tsjc_device_document_id<>'11111111111111111111111111111111'";
//				+ "( t.report_sn like 'CO-TD%' or t.report_sn like 'CO-TA%' or t.report_sn like 'CO-TW%') ";
		list = this.createQuery(hql).list();
		return list;
	}
}
