package com.lsts.report.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportErrorInfo;
import com.lsts.report.dao.ReportErrorInfoDao;

/**
 * 不符合报告明细表业务逻辑对象
 * @ClassName ReportErrorInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 上午10:18:00
 */
@Service("reportErrorInfoService")
public class ReportErrorInfoService extends
		EntityManageImpl<ReportErrorInfo, ReportErrorInfoDao> {
	@Autowired
	private ReportErrorInfoDao reportErrorInfoDao;
	@Autowired
	private SysLogService logService;

	/**
	 * 根据不符合报告主表ID查询明细数据列表
	 * 
	 * @param fk_report_error_id -- 不符合报告主表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-09-28 下午03:26:00
	 */
	public List<ReportErrorInfo> getInfos(
			String fk_report_error_id) {
		return reportErrorInfoDao.getInfos(fk_report_error_id);
	}
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			if (StringUtil.isNotEmpty(id)) {
				// 1、删除不符合报告明细表（TZSB_REPORT_ERROR_INFO2）
				reportErrorInfoDao
							.createSQLQuery(
									"update TZSB_REPORT_ERROR_INFO2 set data_status='99' where id = ? ",
									id).executeUpdate();
				// 2、写入日志
				logService.setLogs(id, "删除不符合报告明细表", "删除不符合报告明细表",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
}
