package com.khnt.oa.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.CarDriver;
import com.khnt.oa.car.dao.CarDriverDao;


@Service("carDriverManager")
public class CarDriverManager extends EntityManageImpl<CarDriver,CarDriverDao>{
    @Autowired
    CarDriverDao carDriverDao;
}
