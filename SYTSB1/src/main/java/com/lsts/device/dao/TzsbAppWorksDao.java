package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppSupervisionUnit;
import com.lsts.device.bean.TzsbAppWorks;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppWorksDao")
public class TzsbAppWorksDao extends EntityDaoImpl<TzsbAppWorks>{
	public List<TzsbAppWorks> listByAppId(String appId){
		String hql="from TzsbAppWorks where fk_tzsb_application_id=?";
		List<TzsbAppWorks> list=createQuery(hql, appId).list();
		return list;
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppWorks t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
