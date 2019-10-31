package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.Food;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
@Repository("foodDao")
public class FoodDao extends EntityDaoImpl<Food>{

	public void deleteFood(String ids) {
		String[] idss = ids.split(",");
		for(String id : idss){
			this.removeById(id);
		}
		
	}

	
}
