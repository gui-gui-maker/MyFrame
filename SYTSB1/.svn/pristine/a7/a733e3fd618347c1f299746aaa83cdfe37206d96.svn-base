package com.scts.payment.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.bean.InspectionChangeMoney;

/**
 * 金额修改审批流程明细表数据处理对象
 * @ClassName InspectionChangeMoneyDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-16 15:18:00
 */
@Repository("inspectionChangeMoneyDao")
public class InspectionChangeMoneyDao extends EntityDaoImpl<InspectionChangeMoney> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集

	@SuppressWarnings("unchecked")
	public List<InspectionChangeMoney> getInfos(
			String change_money_id) {
		List<InspectionChangeMoney> list = new ArrayList<InspectionChangeMoney>();
		String hql = "from InspectionChangeMoney i where i.inspectionChange.id=? and i.data_status != '99'";
		list = this.createQuery(hql, change_money_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<InspectionChangeMoney> queryInfos(
			String info_id) {
		List<InspectionChangeMoney> list = new ArrayList<InspectionChangeMoney>();
		String hql = "from InspectionChangeMoney i where i.fk_inspection_info_id=? and i.data_status != '99'";
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
