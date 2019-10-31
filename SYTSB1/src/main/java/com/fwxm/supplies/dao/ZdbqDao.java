package com.fwxm.supplies.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fwxm.supplies.bean.Zdbq;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("ZdbqDao")
public class ZdbqDao extends EntityDaoImpl<Zdbq>{
	
	public List<Zdbq> getZdbqList(String org,String type){
		String hql="from Zdbq where createOrgId=? and type=? ORDER BY createTime DESC";
		List<Zdbq> list=this.createQuery(hql, org,type).list();
		return list;
	}
	
	public List<Zdbq> getDelText(String text,String org,String type){
		String hql="from Zdbq where createOrgId=? and type=? and TEXT=?";
		List<Zdbq> list=this.createQuery(hql, org,type,text).list();
		return list;
	}
}
