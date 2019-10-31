package com.lsts.device.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppConstrucationOrg;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppConstrucationOrgDao")
public class TzsbAppConstrucationOrgDao extends EntityDaoImpl<TzsbAppConstrucationOrg>{

	public TzsbAppConstrucationOrg getByAppId(String appId){
		String hql="from TzsbAppConstrucationOrg where fk_tzsb_application_id=? ";
		TzsbAppConstrucationOrg r=(TzsbAppConstrucationOrg)this.createQuery(hql, appId).uniqueResult();
		return r;
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppConstrucationOrg t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
