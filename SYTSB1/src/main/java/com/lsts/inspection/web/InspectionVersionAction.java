package com.lsts.inspection.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.inspection.bean.InspectionVersion;
import com.lsts.inspection.service.InspectionVersionService;

@Controller
@RequestMapping("inspectionVersionAction")
public class InspectionVersionAction extends SpringSupportAction<InspectionVersion, InspectionVersionService> {

	@Autowired
	InspectionVersionService inspectionVersionService;
	
}
