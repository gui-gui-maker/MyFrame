package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppConsUnit;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppConsUnitDao")
public class TzsbAppConsUnitDao extends EntityDaoImpl<TzsbAppConsUnit>{

	public List<TzsbAppConsUnit> listByAppId(String appId){
		String hql="from TzsbAppConsUnit where fk_tzsb_application_id=?";
		List<TzsbAppConsUnit> list=createQuery(hql, appId).list();
		return list;
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppConsUnit  t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
