package com.scts.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.payment.bean.InspectionPayBack;
import com.scts.payment.dao.InspectionPayBackDao;

/**
 * 报检业务退款业务逻辑对象
 * @ClassName InspectionPayBackService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:39:00
 */
@Service("inspectionPayBackService")
public class InspectionPayBackService extends
		EntityManageImpl<InspectionPayBack, InspectionPayBackDao> {
	@Autowired
	private InspectionPayBackDao inspectionPayBackDao;
	

}
