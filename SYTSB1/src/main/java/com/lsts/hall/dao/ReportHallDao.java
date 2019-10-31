package com.lsts.hall.dao;


import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.hall.bean.ReportHall;



/**
/**
 * @author Mr.Dawn
 * @date 2014-12-22
 * @summary 
 */

@Repository("reportHallDao")
public class ReportHallDao extends EntityDaoImpl<ReportHall> {
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
