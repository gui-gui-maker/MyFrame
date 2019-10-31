package com.lsts.report.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportTransferRecord;
import com.lsts.report.dao.ReportTransferRecordDao;

/**
 * 业务服务部前后台报告交接明细业务逻辑对象
 * @ClassName ReportTransferRecordService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-03 14:30:00
 */
@Service("reportTransferRecordService")
public class ReportTransferRecordService extends EntityManageImpl<ReportTransferRecord, ReportTransferRecordDao> {
	@Autowired
	private ReportTransferRecordDao reportTransferRecordDao;
	@Autowired
	private SysLogService logService;
	
	// 根据明细ID查询明细列表
	public List<ReportTransferRecord> queryInfos(String ids) throws Exception{
		return reportTransferRecordDao.queryInfos(ids);
	}
	
	// 根据明细ID查询明细列表
	public List<ReportTransferRecord> getInfos(String com_name, String report_sn) throws Exception{
			return reportTransferRecordDao.getInfos(com_name, report_sn);
	}
	
	// 删除
	public void del(HttpServletRequest request, String id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			ReportTransferRecord reportTransferRecord = reportTransferRecordDao.get(id);
			if (reportTransferRecord!=null) {
				// 1、删除业务服务部前后台报告交接明细表
				reportTransferRecordDao
						.createSQLQuery(
								"update TZSB_REPORT_TRANSFER_RECORD set data_status='99' where id = ? ",
								id).executeUpdate();
				// 2、写入日志
				logService.setLogs(reportTransferRecord.getId(), "业务服务部前后台报告交接明细删除", "保存业务服务部前后台报告交接明细时，删除明细", user.getId(), user
						.getName(), new Date(), request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		
	}

}
