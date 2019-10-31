package com.fwxm.ret.dao;

import java.util.List;
import java.util.Map;

import com.fwxm.ret.bean.Tjy2ChReturn;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class Tjy2ChReturnDao extends EntityDaoImpl<Tjy2ChReturn> {
	
	public Map<String, Object>  getBeanByYear( String year){
		String sql="SELECT * FROM ( SELECT to_number(substr(tr_Bh,11)) as tr_Bh FROM TJY2_CH_RETURN WHERE tr_Bh LIKE  '"+year+"%'    ) order by tr_Bh desc";
		List<Map<String, Object>> list=this.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
}
