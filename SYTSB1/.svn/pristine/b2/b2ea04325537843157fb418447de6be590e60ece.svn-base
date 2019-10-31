package com.scts.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.payment.bean.InspectionPayDetail;
import com.scts.payment.dao.InspectionPayDetailDao;

/**
 * 报检收费详细信息业务逻辑对象
 * @ClassName InspectionPayDetailService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:40:00
 */
@Service("inspectionPayDetailService")
public class InspectionPayDetailService extends
		EntityManageImpl<InspectionPayDetail, InspectionPayDetailDao> {
	@Autowired
	private InspectionPayDetailDao inspectionPayDetailDao;
	
	// 根据收费ID查询收费详细信息
	public InspectionPayDetail queryByPayInfoID(String fk_pay_info_id) {
		return inspectionPayDetailDao.queryByPayInfoID(fk_pay_info_id);
	}
	
	public void delete(String id) {
		inspectionPayDetailDao.delete(id);
	}
}
