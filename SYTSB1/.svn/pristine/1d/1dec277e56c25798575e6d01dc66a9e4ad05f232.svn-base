package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppDevice;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppDeviceDao")
public class TzsbAppDeviceDao extends EntityDaoImpl<TzsbAppDevice>{

	public List<TzsbAppDevice> listByAppId(String appId){
		String hql="from TzsbAppDevice where fk_tzsb_application_id=?";
		List<TzsbAppDevice> list=createQuery(hql, appId).list();
		return list;
		
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppDevice  t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
