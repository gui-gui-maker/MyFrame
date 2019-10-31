package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppOutsour;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppOutsourDao")
public class TzsbAppOutsourDao extends EntityDaoImpl<TzsbAppOutsour>{
	public List<TzsbAppOutsour> listByAppId(String appId){
		String hql="from TzsbAppOutsour where fk_tzsb_application_id=?";
		List<TzsbAppOutsour> list=createQuery(hql, appId).list();
		return list;
		
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppOutsour t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
