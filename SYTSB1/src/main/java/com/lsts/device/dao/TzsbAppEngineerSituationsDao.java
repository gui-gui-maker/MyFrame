package com.lsts.device.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppEngineerSituations;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppEngineerSituationsDao")
public class TzsbAppEngineerSituationsDao extends EntityDaoImpl<TzsbAppEngineerSituations>{

	public TzsbAppEngineerSituations getByAppId(String appId){
		String hql="from TzsbAppEngineerSituations where fk_tzsb_application_id=?";
		Object obj=createQuery(hql, appId).uniqueResult();
		TzsbAppEngineerSituations r=(TzsbAppEngineerSituations)obj;
		return r;
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppEngineerSituations t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
