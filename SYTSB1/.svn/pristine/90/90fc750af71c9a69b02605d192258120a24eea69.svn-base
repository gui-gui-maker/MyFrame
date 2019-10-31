package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppSupervisionUnit;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppSupervisionUnitDao")
public class TzsbAppSupervisionUnitDao extends EntityDaoImpl<TzsbAppSupervisionUnit>{
	public List<TzsbAppSupervisionUnit> listByAppId(String appId){
		String hql="from TzsbAppSupervisionUnit where fk_tzsb_application_id=?";
		List<TzsbAppSupervisionUnit> list=createQuery(hql, appId).list();
		return list;
		
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppSupervisionUnit t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
