package com.lsts.inspection.dao;


import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.InspectionHall;



/**
 * 报检大厅管理   dao
 * 
 * @author 肖慈边 2014-1-26
 */

@Repository("inspectionHallDao")
public class InspectionHallDao extends EntityDaoImpl<InspectionHall> {



	/**
	 * 获取根节点
	 * 
	 * @return
	 */

	public SQLQuery getquery(String sql){
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query;
		
	}

}
