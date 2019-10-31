package com.khnt.oa.car.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.oa.car.bean.CarDriverplan;
import com.khnt.oa.car.service.CarDriverplanManager;
import com.khnt.utils.DateUtil;


@Controller
@RequestMapping("oa/car/driverplan")
public class CarDriverplanAction extends SpringSupportAction<CarDriverplan, CarDriverplanManager> {

    @Autowired
    private CarDriverplanManager  carDriverplanManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request,
			CarDriverplan entity) throws Exception {
		entity.setPlanWeek(DateUtil.getWeek(entity.getPlanDate()));
		System.out.println("");
		return super.save(request, entity);
	}
	
}