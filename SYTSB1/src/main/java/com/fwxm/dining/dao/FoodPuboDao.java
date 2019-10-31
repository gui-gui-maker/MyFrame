package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.FoodPubo;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("foodPuboDao")
public class FoodPuboDao extends EntityDaoImpl<FoodPubo>{

	public void delete(String id) {
		String sql="delete from food_pubo where id = ?";
		this.createSQLQuery(sql, id).executeUpdate();
	}
	
}
