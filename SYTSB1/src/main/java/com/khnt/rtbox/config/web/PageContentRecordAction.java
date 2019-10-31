package com.khnt.rtbox.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.rtbox.config.bean.PageContentRecord;
import com.khnt.rtbox.config.service.PageContentRecordManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName PageContentRecord
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/rt/pageContentRecord")
public class PageContentRecordAction extends
		SpringSupportAction<PageContentRecord, PageContentRecordManager> {

	@Autowired
	private PageContentRecordManager pageContentRecordManager;

}
