package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.FoodEvaluation;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("foodEvaluationDao")
public class FoodEvaluationDao extends EntityDaoImpl<FoodEvaluation>{

	public void deleteEvals(String ids) {
		String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	//删除订单信息
        }
	}
	
}
