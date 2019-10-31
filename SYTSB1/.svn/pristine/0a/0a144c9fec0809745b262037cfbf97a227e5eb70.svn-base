package com.fwxm.outstorage.dao;


import java.util.List;
import java.util.Map;

import com.fwxm.outstorage.bean.Tjy2ChCk;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class Tjy2ChCkDao extends EntityDaoImpl<Tjy2ChCk> {
	
	public Map<String, Object>  getBeanByYear( String year){
		String sql= "SELECT * FROM ( SELECT to_number(substr(ckdbh,11)) as ckdbh FROM TJY2_CH_OUT WHERE ckdbh LIKE  '"+year+"%'   ) order by ckdbh desc";
	    List<Map<String, Object>> list=this.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
}