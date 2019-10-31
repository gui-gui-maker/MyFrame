package com.fwxm.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.library.bean.BookFlowLog;
import com.fwxm.library.service.BookFlowLogService;
import com.khnt.core.crud.web.SpringSupportAction;

@Controller
@RequestMapping("lib/bookFlowLog")
public class BookFlowLogAction extends SpringSupportAction<BookFlowLog, BookFlowLogService>{
	
	@Autowired
	BookFlowLogService bookFlowLogService;
}
