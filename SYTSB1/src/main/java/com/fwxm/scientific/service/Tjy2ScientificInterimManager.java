package com.fwxm.scientific.service;

import java.util.List;

import com.fwxm.scientific.bean.Tjy2ScientificInterim;
import com.fwxm.scientific.dao.Tjy2ScientificInterimDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tjy2ScientificInterim")
public class Tjy2ScientificInterimManager extends EntityManageImpl<Tjy2ScientificInterim,Tjy2ScientificInterimDao>{
    @Autowired
    Tjy2ScientificInterimDao tjy2ScientificInterimDao;
    
    public Tjy2ScientificInterim detailInterim(String fk_scientific_id){
    	Tjy2ScientificInterim base=new Tjy2ScientificInterim();
    	String hql="from Tjy2ScientificInterim where fk_scientific_id=?";
    	List<Tjy2ScientificInterim> list= tjy2ScientificInterimDao.createQuery(hql, fk_scientific_id).list();
    	if(list.size()>0){
    		base=list.get(0);
    	}
    	return base;
    }
    public  Object[] detailBase(String id){
		Object[] o=null;
		String sql="select t.TEMPLATE,t.id from TJY2_SCIENTIFIC_INTERIM t where t.id = ?";
		List<Object> list=tjy2ScientificInterimDao.createSQLQuery(sql,id).list();
		if(list.size()>0){
			o=(Object[])list.get(0);
			
		}
		return o;
		
	}
}
