package com.fwxm.scientific.dao;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.fwxm.scientific.bean.InstrumentDevice;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstrumentDeviceDao
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@Repository("instrumentDeviceDao")
public class InstrumentDeviceDao extends EntityDaoImpl<InstrumentDevice> {
	public SQLQuery getquery(String sql){
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query;
	}
}
