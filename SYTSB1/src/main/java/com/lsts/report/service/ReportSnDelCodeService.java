package com.lsts.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.report.bean.ReportSnDelCode;
import com.lsts.report.dao.ReportSnDelCodeDao;

/**
 * 报告书编号作废编号记录业务逻辑对象
 * @ClassName ReportSnDelCodeService
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-05-06 15:15:00
 */
@Service("reportSnDelCodeService")
public class ReportSnDelCodeService extends EntityManageImpl<ReportSnDelCode, ReportSnDelCodeDao> {
	@Autowired
	private ReportSnDelCodeDao reportSnDelCodeDao;

	
}
