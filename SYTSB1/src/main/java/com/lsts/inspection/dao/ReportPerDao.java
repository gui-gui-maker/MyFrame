package com.lsts.inspection.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportPerAudit;



/**
 * 报告配置   dao
 * 
 * @author 肖慈边 2014-2-21
 */

@Repository("reportPerDao")
public class ReportPerDao extends EntityDaoImpl<ReportPerAudit> {

	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
    
	/**
	 * 获取报告配置
	 * @param inspectionInfoId -- 报检信息ID
	 * @param report_id -- 报告ID
	 * @param item_name -- 项目名称
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-07 上午10:16:00
	 */
	@SuppressWarnings("unchecked")
	public String queryByInspectionInfoId(String inspectionInfoId, String report_id, String item_name){
		String item_value_id = "";
    	try {
    		if (StringUtil.isNotEmpty(inspectionInfoId) && StringUtil.isNotEmpty(report_id) && StringUtil.isNotEmpty(item_name)) {
    			conn = getConn();
    			String sql = "select r.item_value_id from base_reports_per_audit r where r.fk_inspection_info_id='" + inspectionInfoId + "' and r.fk_report_id='" + report_id + "' and r.item_name = '" + item_name + "'";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			while (rs.next()){
    				String itemValID = rs.getString("ITEM_VALUE_ID");
    				item_value_id = StringUtil.isNotEmpty(itemValID)?itemValID:"";
    			}
    			closeConn();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return item_value_id;
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
