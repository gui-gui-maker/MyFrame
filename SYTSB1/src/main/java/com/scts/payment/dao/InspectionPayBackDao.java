package com.scts.payment.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.bean.InspectionPayBack;


/**
 * 报检业务退款数据处理对象
 * @ClassName InspectionPayBackDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:25:00
 */
@Repository("inspectionPayBackDao")
public class InspectionPayBackDao extends EntityDaoImpl<InspectionPayBack> {

}
