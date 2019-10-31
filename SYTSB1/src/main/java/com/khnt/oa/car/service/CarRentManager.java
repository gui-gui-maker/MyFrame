package com.khnt.oa.car.service;


import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.CarRent;
import com.khnt.oa.car.dao.ApplyDao;
import com.khnt.oa.car.dao.CarRentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarRentManager
 * @JDK 1.5
 * @author 
 * @date 
 */
@Service("carRentManager")
public class CarRentManager extends EntityManageImpl<CarRent, CarRentDao> {
	@Autowired
	CarRentDao carRentDao;
	
	@Autowired
	ApplyDao applyDao;



	public boolean backRentCar(String id, CarRent backCarRent) throws Exception {
		try {
			CarRent carRent = this.get(id);
			carRent.setEndKm(backCarRent.getEndKm());
			carRent.setReturnTime(backCarRent.getReturnTime());
	     
			Long endKm = Long.valueOf(carRent.getEndKm());
			Long startKm = Long.valueOf(carRent.getStartKm());
			Date returnTime=carRent.getReturnTime();
			Date startTime=carRent.getLeaseTime();
			Long allKm = endKm - startKm;
			float runTime=(returnTime.getTime()-startTime.getTime())/(1000*60*60) ;
		
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
