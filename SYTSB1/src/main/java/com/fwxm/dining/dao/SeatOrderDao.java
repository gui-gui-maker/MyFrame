package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.dining.bean.SeatOrder;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
@Repository("seatOrderDao")
public class SeatOrderDao extends EntityDaoImpl<SeatOrder>{

	public void deleteSeatOrders(String ids) {
		for(String id : ids.split(",")){
			super.removeById(id);
		}
	}

}
