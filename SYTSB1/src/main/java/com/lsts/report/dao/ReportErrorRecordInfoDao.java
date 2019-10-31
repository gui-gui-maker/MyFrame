package com.lsts.report.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.ReportErrorRecordInfo;

/**
 * 检验报告/证书不符合纠正流转明细表数据处理对象
 * @ClassName ReportErrorRecordInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-24 09:52:00
 */
@Repository("reportErrorRecordInfoDao")
public class ReportErrorRecordInfoDao extends EntityDaoImpl<ReportErrorRecordInfo> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集

    /**
	 * 根据不符合纠正流转主表ID查询明细数据列表
	 * 
	 * @param fk_report_error_record_id -- 不符合纠正流转主表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-09-28 下午03:26:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> getInfos(
			String fk_report_error_record_id) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.reportErrorRecord.id=? and i.data_status != '99'";
		list = this.createQuery(hql, fk_report_error_record_id).list();
		return list;
	}
	
	/**
	 * 根据不符合纠正流转明细表ID查询详细明细数据
	 * 
	 * @param info_id -- 不符合纠正流转明细表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-01-28 下午01:26:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> queryInfos(
			String info_id) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.id=? and i.data_status != '99'";
		list = this.createQuery(hql, info_id).list();
		return list;
	}
	
	/**
	 * 根据不符合纠正流转明细表ID查询详细明细数据
	 * 
	 * @param info_id -- 不符合纠正流转明细表ID
	 * @return
	 * @author GaoYa
	 * @date 2015-01-28 下午01:26:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> queryInfoByInfoIds(
			String info_id) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.info_id=? and i.data_status != '99'";
		list = this.createQuery(hql, info_id).list();
		return list;
	}
	
	/**
	 * 根据纠错报告编号查询详细明细数据
	 * @param info_id -- 纠错报告ID
	 * @param report_sn -- 纠错报告编号
	 * @return
	 * @author GaoYa
	 * @date 2016-12-15 上午10:58:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> queryByReportSn(String info_id, 
			String report_sn, String error_record_id) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.report_sn=? and i.info_id = ? and i.data_status != '99'";
		if(StringUtil.isNotEmpty(error_record_id)) {
			hql += " and i.reportErrorRecord.id != '"+error_record_id+"'";
		}
		list = this.createQuery(hql, report_sn, info_id).list();
		return list;
	}
	
	/**
	 * 根据新报告编号查询详细明细数据
	 * @param new_info_id -- 新报告ID 
	 * @param new_report_sn -- 新报告编号
	 * @return
	 * @author GaoYa
	 * @date 2016-12-15 上午10:58:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> queryByNewReportSn(String new_info_id, 
			String new_report_sn) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.new_report_sn=? and i.new_info_id != ? and i.data_status != '99'";
		list = this.createQuery(hql, new_report_sn, new_info_id).list();
		return list;
	}
	
	/**
	 * 根据报告书编号查询新报告编号未填写的不符合纠正记录
	 * @param report_sn -- 报告书编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-05-10 上午11:34:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordInfo> getErrorInfos(String info_id, String report_sn) {
		List<ReportErrorRecordInfo> list = new ArrayList<ReportErrorRecordInfo>();
		String hql = "from ReportErrorRecordInfo i where i.info_id = ? and i.report_sn like '%"+report_sn+"%' and i.new_report_sn is null and i.data_status!='99'";
		list = this.createQuery(hql, info_id).list();
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
