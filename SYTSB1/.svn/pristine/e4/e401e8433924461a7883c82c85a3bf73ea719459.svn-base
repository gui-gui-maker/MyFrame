package com.lsts.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.report.bean.ReportItem;
import com.lsts.report.dao.ReportItemDao;

/**
 * 报告项目管理   servier
 * 
 * @author 肖慈边 2014-1-24
 */
@Service("reportItemService")
public class ReportItemService extends EntityManageImpl<ReportItem, ReportItemDao> {
	@Autowired
	private ReportItemDao reportItemDao;

	// 获取报告项目
	public List<ReportItem> queryByReportId(String report_id){
		return reportItemDao.queryByReportId(report_id);
	}
	// 获取报告固定必选目录
	public List<ReportItem> queryMustPageByReportId(String report_id) {
		return reportItemDao.queryMustPageByReportId(report_id);
	}

}
