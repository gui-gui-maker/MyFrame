package com.lsts.inspection.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportBHGRecord;



/**
 * 报告检验项目不合格问题来源记录表数据处理对象
 * @ClassName ReportBHGRecordDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-01-06 上午11:23:00
 */
@Repository("reportBHGRecordDao")
public class ReportBHGRecordDao extends EntityDaoImpl<ReportBHGRecord> {
	
	/**
	 * 获取报告检验项目不合格问题来源
	 * @param inspectionInfoId -- 检验业务信息ID
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-06 上午11:29:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportBHGRecord> queryByInfoId(String inspectionInfoId){
		List<ReportBHGRecord> list = new ArrayList<ReportBHGRecord>();
    	try {
    		if (StringUtil.isNotEmpty(inspectionInfoId)) {
    			String hql = "from ReportBHGRecord r where r.fk_inspection_info_id=?";
    			list = this.createQuery(hql, inspectionInfoId).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取报告检验项目不合格问题来源
	 * @param inspectionInfoId -- 检验业务信息ID
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-06 上午11:29:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportBHGRecord> queryBHGRecords(String inspectionInfoId){
		List<ReportBHGRecord> list = new ArrayList<ReportBHGRecord>();
    	try {
    		if (StringUtil.isNotEmpty(inspectionInfoId)) {
    			String hql = "from ReportBHGRecord r where r.fk_inspection_info_id=? and r.data_status!='99'";
    			list = this.createQuery(hql, inspectionInfoId).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public Object insertReportBHGRecord(final String id,
			final String item_name, final String item_value,
			final String info_id, final String user_id, final String user_name) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					// @Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						session.doWork(new Work() {
							// @Override
							public void execute(Connection conn)
									throws SQLException {
								String sql = "insert into tzsb_report_bhg_record "
										+ "(id,bhg_name,bhg_value,item_type,fk_inspection_info_id,last_mdy_uid,last_mdy_uname,last_mdy_time) "
										+ " values(?,?,?,?,?,?,?,?,?)";
								PreparedStatement statement = conn
										.prepareStatement(sql);
								statement.setString(1, id); // id
								statement.setString(2, item_name); 	// 问题来源key
								statement.setString(3, item_value); // 问题来源值
								statement.setString(4, "string"); 	// 数据类型
								statement.setString(5, info_id); 	// 检验业务信息ID
								statement.setString(6, user_id); 	// 最后修改人ID
								statement.setString(7, user_name); 	// 最后修改人姓名
								statement.setString(8,
										DateUtil.getCurrentDateTime()); // 最后修改时间
								statement.setString(9, "0"); 	// 数据状态，默认0（0：新建 1：修改 99：删除）
								statement.execute();
								statement.close();
							}
						});
						return null;
					}
				});
	}

	public void deleteAllByInfoid(String id) {
		String hql = "update ReportBHGRecord r set r.data_status='99' where r.fk_inspection_info_id = ?";
		this.createQuery(hql, id).executeUpdate();
		
	}

	public List<ReportBHGRecord> queryResourceByInfoId(String info_id) {
		List<ReportBHGRecord> list = new ArrayList<ReportBHGRecord>();
    	try {
    		if (StringUtil.isNotEmpty(info_id)) {
    			String hql = "from ReportBHGRecord r where r.fk_inspection_info_id=? and r.data_status='0'";
    			list = this.createQuery(hql, info_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public void delete(String id) {
		String hql = "update ReportBHGRecord r set r.data_status='99' where r.id = ?";
		this.createQuery(hql, id).executeUpdate();
		
	}
}
