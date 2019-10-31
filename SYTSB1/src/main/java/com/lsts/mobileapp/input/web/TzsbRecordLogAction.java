package com.lsts.mobileapp.input.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.mobileapp.input.bean.TzsbRecordLog;
import com.lsts.mobileapp.input.service.TzsbRecordLogService;

@Controller
@RequestMapping("tzsbRecordLogAction")
public class TzsbRecordLogAction extends SpringSupportAction<TzsbRecordLog, TzsbRecordLogService> {

	@Autowired
	private TzsbRecordLogService recordLogService;
	
	
}
