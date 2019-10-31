package com.lsts.inspection.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportPageCheckInfo;


/**
 * 报告页单独审核数据处理对象
 * @ClassName ReportPageCheckInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-10-17 下午04:05:00
 */
@Repository("reportPageCheckInfoDao")
public class ReportPageCheckInfoDao extends EntityDaoImpl<ReportPageCheckInfo> {

	/**
	 * 获取报告分页单独审核信息
	 * @param infoId -- 业务信息ID
	 * @param report_id -- 报告ID
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-18 下午01:29:00
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryPageInfo(String info_id, String report_id,
			String userId) {
		String sql = "select t.page_index "
				+ " from tzsb_report_page_check t"
				+ " where t.fk_inspection_info_id = ?"
				+ " and t.fk_report_id = ?"
				+ " and t.audit_user_id = ?"
				+ " and t.data_status='0'";
		return this.createSQLQuery(sql, info_id, report_id, userId).list();
	}
	
	/**
	 * 获取报告分页审核记录信息表（待审核）
	 * @param infoId -- 业务信息ID
	 * @param report_id -- 报告ID
	 * @param user_id -- 单独审核人ID
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-19 上午11:09:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportPageCheckInfo> queryUnCheckPageInfos(String infoId, String report_id, String user_id){
		List<ReportPageCheckInfo> list = new ArrayList<ReportPageCheckInfo>();
    	try {
    		if (StringUtil.isNotEmpty(infoId) && StringUtil.isNotEmpty(report_id)) {
    			String hql = "from ReportPageCheckInfo r where r.fk_inspection_info_id=? and r.fk_report_id=? and r.audit_user_id=? and r.data_status='0'";
    			list = this.createQuery(hql, infoId, report_id, user_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取报告分页审核记录信息表
	 * @param infoId -- 业务信息ID
	 * @param report_id -- 报告ID
	 * @param user_id -- 单独审核人ID
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-19 上午11:09:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportPageCheckInfo> queryPageInfos(String infoId, String report_id, String user_id){
		List<ReportPageCheckInfo> list = new ArrayList<ReportPageCheckInfo>();
    	try {
    		if (StringUtil.isNotEmpty(infoId) && StringUtil.isNotEmpty(report_id)) {
    			String hql = "from ReportPageCheckInfo r where r.fk_inspection_info_id=? and r.fk_report_id=? and r.audit_user_id=? and r.data_status!='99'";
    			list = this.createQuery(hql, infoId, report_id, user_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取报告分页审核记录信息表
	 * @param infoId -- 业务信息ID
	 * @param report_id -- 报告ID
	 * @param user_id -- 单独审核人ID
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-19 上午11:09:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportPageCheckInfo> queryPage(String infoId, String report_id, String page_index){
		List<ReportPageCheckInfo> list = new ArrayList<ReportPageCheckInfo>();
    	try {
    		if (StringUtil.isNotEmpty(infoId) && StringUtil.isNotEmpty(report_id)) {
    			String hql = "from ReportPageCheckInfo r where r.fk_inspection_info_id=? and r.fk_report_id=? and r.page_index=? and r.data_status!='99'";
    			list = this.createQuery(hql, infoId, report_id, page_index).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取报告分页审核记录信息表
	 * @param infoId -- 业务信息ID
	 * @param report_id -- 报告ID
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-19 上午11:09:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportPageCheckInfo> queryPageInfos(String infoId, String report_id){
		List<ReportPageCheckInfo> list = new ArrayList<ReportPageCheckInfo>();
    	try {
    		if (StringUtil.isNotEmpty(infoId) && StringUtil.isNotEmpty(report_id)) {
    			String hql = "from ReportPageCheckInfo r where r.fk_inspection_info_id=? and r.fk_report_id=? and r.data_status!='99'";
    			list = this.createQuery(hql, infoId, report_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
}
