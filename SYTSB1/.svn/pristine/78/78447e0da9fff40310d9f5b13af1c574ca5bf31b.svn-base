package com.fwxm.scientific.service;


import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.fwxm.scientific.bean.Instruction;
import com.fwxm.scientific.bean.InstructionInfo;
import com.fwxm.scientific.bean.InstrumentDevice;
import com.fwxm.scientific.bean.InstrumentDeviceInfo;
import com.fwxm.scientific.dao.InstrumentDeviceDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstrumentDeviceManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@Service("instrumentDeviceManager")
public class InstrumentDeviceManager extends EntityManageImpl<InstrumentDevice, InstrumentDeviceDao> {
	@Autowired
	InstrumentDeviceDao instrumentDeviceDao;
	/**
	 * 
	 * @param id
	 * @param hall_id
	 * @return
	 * @throws Exception
	 */
	public InstrumentDevice getDetail(String id) throws Exception {

		InstrumentDevice hall = instrumentDeviceDao.get(id);

		String ids = "";
		// 判断是否多个id
		StringBuffer json = new StringBuffer();
		if (id.indexOf(",") != -1) {
			String temp[] = id.split(",");

			for (int i = 0; i < temp.length; i++) {

				json.append("'").append(temp[i]).append("'");
				if (i != temp.length - 1) {
					json.append(",");
				}

			}
			ids = json.toString();
		} else {
			ids = json.append("'").append(id).append("'").toString();
		}

		String sql = "select t.* from TJY2_INSTRUMENT_DEVICE_INFO t where  t.INSTRUMENT_DEVICE_ID ='"+id+"'";

		SQLQuery query = instrumentDeviceDao.getquery(sql);

		query.addEntity(InstrumentDeviceInfo.class);

		List list = query.list();

		hall.setInstrumentDeviceInfo(list);

		return hall;

	}
}
