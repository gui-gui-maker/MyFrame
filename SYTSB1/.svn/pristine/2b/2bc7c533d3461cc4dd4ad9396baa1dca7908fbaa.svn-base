package com.fwxm.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.SeatOrder;
import com.fwxm.dining.dao.SeatOrderDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("seatOrderService")
public class SeatOrderService extends EntityManageImpl<SeatOrder, SeatOrderDao>{
	@Autowired
	private SeatOrderDao seatOrderDao;

	public void deleteSeatOrders(String ids) {
		seatOrderDao.deleteSeatOrders(ids);
		
	}

	
	
}
