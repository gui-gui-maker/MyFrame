package com.lsts.report.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportPrint;
import com.lsts.report.bean.ReportPrintRecord;
import com.lsts.report.bean.ReportPrintRecordDTO;
import com.lsts.report.dao.ReportPrintDao;
import com.lsts.report.dao.ReportPrintRecordDao;

/**
 * 检验资料报送打印签收明细业务逻辑对象
 * @ClassName ReportPrintRecordService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-06 13:25:00
 */
@Service("reportPrintRecordService")
public class ReportPrintRecordService extends EntityManageImpl<ReportPrintRecord, ReportPrintRecordDao> {
	@Autowired
	private ReportPrintRecordDao reportPrintRecordDao;
	@Autowired
	private ReportPrintDao reportPrintDao;
	@Autowired
	private SysLogService logService;

	// 根据明细ID查询明细列表
	public List<ReportPrintRecord> queryInfos(String ids) throws Exception {
		return reportPrintRecordDao.queryInfos(ids);
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			ReportPrintRecord reportPrintRecord = reportPrintRecordDao.get(id);
			if (reportPrintRecord != null) {
				// 1、删除检验资料报送打印签收明细表（TZSB_REPORT_PRINT_RECORD）
				reportPrintRecordDao
						.createSQLQuery("update TZSB_REPORT_PRINT_RECORD set data_status='99' where id = ? ", id)
						.executeUpdate();
				// 2、写入日志
				logService.setLogs(reportPrintRecord.getId(), "报送打印明细删除", "保存报送打印记录时，删除明细", user.getId(),
						user.getName(), new Date(), request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}

	}

	// 批量签收
	@SuppressWarnings("unchecked")
	public boolean receives(String ids, String report_print_id, HttpServletRequest request) {
		boolean isSuccess = true;
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			List<ReportPrintRecord> list = reportPrintRecordDao.getInfos(ids);
			if (!list.isEmpty()) {
				for (ReportPrintRecord reportPrintRecord : list) {
					// 1、保存报送打印明细表（TZSB_REPORT_PRINT_RECORD）
					reportPrintRecord.setData_status("2"); // 2：已签收
					reportPrintRecord.setReceive_user_id(user.getSysUser().getId());
					reportPrintRecord.setReceive_user_name(user.getSysUser().getName());
					reportPrintRecord.setReceive_time(new Date());
					reportPrintRecordDao.save(reportPrintRecord);
					// 写入日志
					logService.setLogs(reportPrintRecord.getId(), "批量签收报送打印", "批量签收检验资料报送打印", user.getId(),
							user.getName(), new Date(), request.getRemoteAddr());
				}
			}
			
			List<ReportPrintRecord> recordlist = reportPrintRecordDao.getReportPrintRecords(report_print_id);
			if (!recordlist.isEmpty()) {
				boolean allReceive = true;
				for (ReportPrintRecord reportPrintRecord : recordlist) {
					// 1：已提交未签收
					if("1".equals(reportPrintRecord.getData_status())){
						allReceive = false;
					}
				}
				if(allReceive){
					ReportPrint reportPrint = reportPrintDao.get(report_print_id);
					reportPrint.setData_status("2");	// 2：已签收
					reportPrint.setReceive_user_id(user.getSysUser().getId());
					reportPrint.setReceive_user_name(user.getSysUser().getName());
					reportPrint.setReceive_time(new Date()); // 签收时间
					reportPrintDao.save(reportPrint);	
					// 写入日志
					logService.setLogs(reportPrint.getId(), "签收报送打印", "签收检验资料报送打印", user.getId(),
							user.getName(), new Date(), request.getRemoteAddr());
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			log.debug(e.toString());
		}
		return isSuccess;
	}
	
	// 签收
	public HashMap<String, Object> receive(HttpServletRequest request, String ids, String report_print_id,
			ReportPrintRecordDTO entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReportPrintRecord> list = reportPrintRecordDao.getInfos(ids);
			if (!list.isEmpty()) {
				for (ReportPrintRecord reportPrintRecord : list) {
					// 1、保存报送打印明细表（TZSB_REPORT_PRINT_RECORD）
					reportPrintRecord.setData_status("2"); // 2：已签收
					reportPrintRecord.setCommit_user_id(entity.getCommit_user_id());
					reportPrintRecord.setCommit_user_name(entity.getCommit_user_name());
					reportPrintRecord.setCommit_time(new Date());
					reportPrintRecord.setReceive_user_id(user.getSysUser().getId());
					reportPrintRecord.setReceive_user_name(user.getSysUser().getName());
					reportPrintRecord.setReceive_time(new Date());
					if (StringUtil.isNotEmpty(entity.getRemark())) {
						String remark = StringUtil.isNotEmpty(reportPrintRecord.getRemark())
								? ("检验部门：" + reportPrintRecord.getRemark() + "；业务服务部：" + entity.getRemark())
								: ("业务服务部：" + entity.getRemark());
						reportPrintRecord.setRemark(remark);
					}
					reportPrintRecordDao.save(reportPrintRecord);
					
					// 2、写入日志
					if(StringUtil.isNotEmpty(reportPrintRecord.getInfo_id())){
						logService.setLogs(reportPrintRecord.getInfo_id(), "报送签收", "检验资料报送签收", user.getId(),
								user.getName(), new Date(), request.getRemoteAddr());
					}
					logService.setLogs(reportPrintRecord.getId(), "批量签收报送打印", "批量签收检验资料报送打印", user.getId(),
							user.getName(), new Date(), request.getRemoteAddr());
				}
			}

			List<ReportPrintRecord> recordlist = reportPrintRecordDao.getReportPrintRecords(report_print_id);
			if (!recordlist.isEmpty()) {
				boolean allReceive = true;
				for (ReportPrintRecord reportPrintRecord : recordlist) {
					// 1：已提交未签收
					if ("1".equals(reportPrintRecord.getData_status())) {
						allReceive = false;
					}
				}
				if (allReceive) {
					ReportPrint reportPrint = reportPrintDao.get(report_print_id);
					reportPrint.setData_status("2"); // 2：已签收
					reportPrint.setCommit_user_id(entity.getCommit_user_id());
					reportPrint.setCommit_user_name(entity.getCommit_user_name());
					reportPrint.setCommit_date(new Date());
					reportPrint.setReceive_user_id(user.getSysUser().getId());
					reportPrint.setReceive_user_name(user.getSysUser().getName());
					reportPrint.setReceive_time(new Date()); // 签收时间
					reportPrintDao.save(reportPrint);
					// 写入日志
					logService.setLogs(reportPrint.getId(), "签收报送打印", "签收检验资料报送打印", user.getId(), user.getName(),
							new Date(), request.getRemoteAddr());
				}
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 签收
	public HashMap<String, Object> batch_receive(HttpServletRequest request, String ids,
			ReportPrintRecordDTO entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String[] idList = ids.split(",");
			for (int i = 0; i < idList.length; i++) {
				List<ReportPrintRecord> list = reportPrintRecordDao.querys(idList[i]);
				if (!list.isEmpty()) {
					for (ReportPrintRecord reportPrintRecord : list) {
						// 1、保存报送打印明细表（TZSB_REPORT_PRINT_RECORD）
						reportPrintRecord.setData_status("2"); // 2：已签收
						reportPrintRecord.setCommit_user_id(entity.getCommit_user_id());
						reportPrintRecord.setCommit_user_name(entity.getCommit_user_name());
						reportPrintRecord.setCommit_time(new Date());
						reportPrintRecord.setReceive_user_id(user.getSysUser().getId());
						reportPrintRecord.setReceive_user_name(user.getSysUser().getName());
						reportPrintRecord.setReceive_time(new Date());
						if (StringUtil.isNotEmpty(entity.getRemark())) {
							String remark = StringUtil.isNotEmpty(reportPrintRecord.getRemark())
									? ("检验部门：" + reportPrintRecord.getRemark() + "；业务服务部：" + entity.getRemark())
									: ("业务服务部：" + entity.getRemark());
							reportPrintRecord.setRemark(remark);
						}
						reportPrintRecordDao.save(reportPrintRecord);
						// 2、写入日志
						if(StringUtil.isNotEmpty(reportPrintRecord.getInfo_id())){
							logService.setLogs(reportPrintRecord.getInfo_id(), "报送签收", "检验资料报送签收", user.getId(),
									user.getName(), new Date(), request.getRemoteAddr());
						}
						logService.setLogs(reportPrintRecord.getId(), "批量签收报送打印", "批量签收检验资料报送打印", user.getId(),
								user.getName(), new Date(), request.getRemoteAddr());
					}
				}
				
				ReportPrint reportPrint = reportPrintDao.get(idList[i]);
				reportPrint.setData_status("2"); // 2：已签收
				reportPrint.setCommit_user_id(entity.getCommit_user_id());
				reportPrint.setCommit_user_name(entity.getCommit_user_name());
				reportPrint.setCommit_date(new Date());
				reportPrint.setReceive_user_id(user.getSysUser().getId());
				reportPrint.setReceive_user_name(user.getSysUser().getName());
				reportPrint.setReceive_time(new Date()); // 签收时间
				reportPrintDao.save(reportPrint);
				// 写入日志
				logService.setLogs(reportPrint.getId(), "批量签收报送打印", "批量签收检验资料报送打印", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
}
