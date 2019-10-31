package com.khnt.oa.car.service;


import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.CarCost;
import com.khnt.oa.car.dao.CarCostDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarCostManager
 * @JDK 1.5
 * @author 
 * @date 
 */
@Service("carCostManager")
public class CarCostManager extends EntityManageImpl<CarCost, CarCostDao> {
	@Autowired
	CarCostDao carCostDao;

}
