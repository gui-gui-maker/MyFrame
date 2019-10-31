package com.lsts.report.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.Report;



/**
 * 报告管理   dao
 * 
 * @author 肖慈边 2014-1-23
 */

@Repository("reportDao")
public class ReportDao extends EntityDaoImpl<Report> {
	
	public Report getReportByRecordCode(String recordCode) {
	        String hql = "from Report r where r.recordModelCode = ?";
	        List<Report> list = this.createQuery(hql, recordCode).list();
	        if(list.size()>0) {
	            return list.get(0);
	        }
	        return null;
	}

	/**
	 * 根据报告类型编号获取报告ID
	 * @param report_code -- 报告类别编号
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryReportId(String report_code) {
		String report_id = "";
		String sql = "select r.id  "
				+ " from base_reports r where r.report_code = '"+report_code+"' and r.flag = '1' ";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			report_id = String.valueOf(list.get(0));
		}
		return report_id;
	}
	
	/**
	 * 根据报告类别编号、原始记录版本号（为空代表老版本，"1"代表新版本）获取报告ID
	 * @param report_code -- 报告类别编号
	 * @param report_version -- 原始记录版本号
	 * 
	 * @return
	 */
	public String queryReportId(String report_code, String report_version) {
		String report_id = "";
		String sql = "select r.id  "
				+ " from base_reports r where r.report_code = '"+report_code+"' and r.flag = '1' ";
		if(StringUtil.isNotEmpty(report_version) && "1".equals(report_version)){
			sql += " and r.report_name like '%2号修改单%'";
		}else if(StringUtil.isNotEmpty(report_version) && "2".equals(report_version)){
			sql += " and r.report_name like '%西藏%'";
		}else if(StringUtil.isNotEmpty(report_version) && "3".equals(report_version)){
			sql += " and r.report_name like '%新疆%'";
		}else if(StringUtil.isNotEmpty(report_version) && "4".equals(report_version)){
			sql += " and r.report_name like '%九寨%'";
		}else{
			sql += " and r.report_name not like '%2号修改单%' and r.report_name not like '%西藏%' and r.report_name not like '%新疆%' and r.report_name not like '%九寨%'";
		}
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			report_id = String.valueOf(list.get(0));
		}
		return report_id;
	}
	
	/**
	 * 根据报告类别编号获取罐车报告ID
	 * @param report_code -- 报告类别编号
	 * 
	 * @return
	 */
	public String queryGcReportId(String report_code) {
		String report_id = "";
		String sql = "select r.id  "
				+ " from base_reports r where r.report_code = ? and r.report_name like '%罐%' and r.flag = '1' ";
		List list = this.createSQLQuery(sql, report_code).list();
		if (!list.isEmpty()) {
			report_id = String.valueOf(list.get(0));
		}
		return report_id;
	}
}
