package com.lsts.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.report.bean.ReportPrintLog;
import com.lsts.report.dao.ReportPrintLogDao;

/**
 * 报告打印记录表
 * @ClassName ReportPrintLogService
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-09-01 10:25:00
 */
@Service("reportPrintLogService")
public class ReportPrintLogService extends EntityManageImpl<ReportPrintLog, ReportPrintLogDao> {
	@Autowired
	private ReportPrintLogDao reportPrintLogDao;
	
	
	
}
