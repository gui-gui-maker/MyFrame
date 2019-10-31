package com.lsts.qualitymanage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.qualitymanage.bean.TaskConvey;
import com.lsts.qualitymanage.service.TaskConveyManager;

@Controller
@RequestMapping("taskConvey/convey")
public class TaskConveyAction extends SpringSupportAction<TaskConvey, TaskConveyManager> {

	@Autowired
	TaskConveyManager taskConveyManager;
	
	
	
	
	
}
