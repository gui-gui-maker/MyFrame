package com.fwxm.scientific.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.fwxm.scientific.bean.InstrumentDeviceInfo;
import com.fwxm.scientific.dao.InstrumentDeviceInfoDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstrumentDeviceInfoManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@Service("instrumentDeviceInfoManager")
public class InstrumentDeviceInfoManager extends EntityManageImpl<InstrumentDeviceInfo, InstrumentDeviceInfoDao> {
	@Autowired
	InstrumentDeviceInfoDao instrumentDeviceInfoDao;

}
