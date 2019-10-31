package com.lsts.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.TzsbAppDocumentFile;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppDocumentFileDao")
public class TzsbAppDocumentFileDao extends EntityDaoImpl<TzsbAppDocumentFile>{

	public List<TzsbAppDocumentFile> listByAppId(String appId){
		String hql="from TzsbAppDocumentFile where fk_tzsb_application_id=?";
		List<TzsbAppDocumentFile> list=createQuery(hql, appId).list();
		return list;
		
	}
	public int removeByAppId(String appId){
		String hql="delete TzsbAppDocumentFile  t where t.fk_tzsb_application_id=?";
		int r=getSession().createQuery(hql).setString(0, appId).executeUpdate();
		return r;
	}
}
