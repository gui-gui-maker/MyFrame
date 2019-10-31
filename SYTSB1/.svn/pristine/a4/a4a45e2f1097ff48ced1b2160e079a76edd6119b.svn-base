package com.lsts.report.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.ReportSnDelCode;

/**
 * 报告书编号作废编号记录表数据处理对象
 * @ClassName ReportSnDelCodeDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-06-06 15:13:00
 */
@Repository("reportSnDelCodeDao")
public class ReportSnDelCodeDao extends EntityDaoImpl<ReportSnDelCode> {

	/**
	 * 获取已作废的报告书编号（最小值）
	 * 
	 * @param report_sn
	 *            -- 报告书编号前缀
	 * @return
	 */
	public synchronized String queryDelReport_sn(String report_sn, String org_id) {
		String minReportSn = "";
		String hql = "select min(t.report_sn) from TZSB_DEL_CODE t where t.report_sn is not null ";
		if (StringUtil.isNotEmpty(report_sn)) {
			hql += " and t.report_sn like '%" + report_sn + "%'";
		}
		if(report_sn.startsWith("CO-T")){
			if(!"100091".equals(org_id) && !"100090".equals(org_id)){
				hql += " and t.report_sn not like '%" + report_sn + "8%'";
			}
			if(!"100069".equals(org_id)){
				hql += " and t.report_sn not like '%" + report_sn + "9%'";
			}
		}
		
		List list = this.createSQLQuery(hql).list();
		if (!list.isEmpty()) {
			minReportSn = String.valueOf(list.get(0));
		}
		return minReportSn;
	}

	// 删除
	public synchronized void delReport_sn(String report_sn) {
		List<ReportSnDelCode> list = new ArrayList<ReportSnDelCode>();
		String hql = "from ReportSnDelCode t where t.report_sn = ? ";
		list = this.createQuery(hql, report_sn).list();
		if (!list.isEmpty()) {
			for (ReportSnDelCode reportSnDelCode : list) {
				String sql="delete from TZSB_DEL_CODE where id = ?";
				this.createSQLQuery(sql, reportSnDelCode.getId()).executeUpdate();
			}
		}
	}
}
