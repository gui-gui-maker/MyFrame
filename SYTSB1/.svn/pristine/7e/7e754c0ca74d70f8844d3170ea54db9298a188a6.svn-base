package com.fwxm.dining.dao;


import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.FoodCard;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("foodCardDao")
public class FoodCardDao extends EntityDaoImpl<FoodCard>{


	public void deleteCards(String ids) {
		String[] idss = ids.split(",");
		
		for(String id: idss)
		{
			this.removeById(id);
		}
	}
	
}
