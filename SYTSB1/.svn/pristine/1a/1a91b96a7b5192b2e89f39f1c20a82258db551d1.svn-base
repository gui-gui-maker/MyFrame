package com.lsts.expert.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.expert.bean.ExpertPre;

@Repository("expertPreDao")
public class ExpertPreDao extends EntityDaoImpl<ExpertPre> {

	
	public boolean hasSet(String personType,String opId){
		String hql = "from ExpertPre t where t.person_type=? and t.person_id=? and t.data_status<>'99'";
		List<ExpertPre> list = this.createQuery(hql, personType,opId).list();
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
