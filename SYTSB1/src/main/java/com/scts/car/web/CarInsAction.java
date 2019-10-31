package com.scts.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.car.bean.CarIns;
import com.scts.car.service.CarInsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
@RequestMapping("carInsAction")
public class CarInsAction extends SpringSupportAction<CarIns, CarInsManager> {

    @Autowired
    private CarInsManager  carInsManager;
	
}