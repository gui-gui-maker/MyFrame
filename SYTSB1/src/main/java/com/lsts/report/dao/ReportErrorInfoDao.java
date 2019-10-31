package com.lsts.report.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.report.bean.ReportErrorInfo;
import com.lsts.report.bean.ReportErrorRecordInfo;

/**
 * 不符合报告明细表数据处理对象
 * @ClassName ReportErrorInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 10:52:00
 */
@Repository("reportErrorInfoDao")
public class ReportErrorInfoDao extends EntityDaoImpl<ReportErrorInfo> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集

    /**
	 * 根据不符合报告主表ID查询明细数据列表
	 * 
	 * @param fk_report_error_record_id -- 不符合纠正流转主表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-11-04 11:22:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorInfo> getInfos(
			String fk_report_error_record_id) {
		List<ReportErrorInfo> list = new ArrayList<ReportErrorInfo>();
		String hql = "from ReportErrorInfo i where i.reportError.id=? and i.data_status != '99'";
		list = this.createQuery(hql, fk_report_error_record_id).list();
		return list;
	}
	
	/**
	 * 根据不符合报告明细表ID查询详细明细数据
	 * 
	 * @param info_id -- 不符合报告明细表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-11-04 12:52:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> queryInfos(
			String info_id) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorInfo i where i.id=? and i.data_status != '99'";
		list = this.createQuery(hql, info_id).list();
		return list;
	}
	
	/**
	 * 根据纠错报告编号查询详细明细数据
	 * @param info_id -- 纠错报告ID
	 * @param report_sn -- 纠错报告编号
	 * @return
	 * @author GaoYa
	 * @date 2016-12-15 上午11:25:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorInfo> queryByReportSn(String info_id, 
			String report_sn) {
		List<ReportErrorInfo> list = new ArrayList<ReportErrorInfo>();
		String sql = "from ReportErrorInfo i where i.report_sn=? and i.info_id != ? and i.data_status != '99'";
		list = this.createQuery(sql, report_sn, info_id).list();
		return list;
	}
	
	// 获取数据库连接
    public Connection getConn() {
        try {
            conn = Factory.getDB().getConnetion();
        } catch (Exception e) {
        	logger.error("获取数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public void closeConn() {
        try {
            /*if (null != rs)
                rs.close();
            if (null != pstmt)
                pstmt.close();
            if (null != conn)
                conn.close();*/
        	Factory.getDB().freeConnetion(conn);	// 释放连接
        } catch (Exception e) {
        	logger.error("关闭数据库连接错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
