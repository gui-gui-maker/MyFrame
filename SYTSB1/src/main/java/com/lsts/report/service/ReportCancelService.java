package com.lsts.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.report.bean.ReportCancel;
import com.lsts.report.dao.ReportCancelDao;

/**
 * 报告作废记录业务逻辑对象
 * @ClassName ReportDrawService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-07-10 12:24:00
 */
@Service("reportCancelService")
public class ReportCancelService extends EntityManageImpl<ReportCancel, ReportCancelDao> {
	@Autowired
	private ReportCancelDao reportCancelDao;
	
	// 获取作废编号后三位序号
	public String queryNextSn(String sn_pre){
		return reportCancelDao.queryNextSn(sn_pre);
	}

}
