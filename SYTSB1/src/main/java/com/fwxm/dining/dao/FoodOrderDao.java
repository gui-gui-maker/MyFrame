package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.FoodOrder;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("foodOrderDao")
public class FoodOrderDao extends EntityDaoImpl<FoodOrder>{

	
	//删除订单并删除详情
	 public void deleteOrders(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	//删除订单信息
        }
    }
}
