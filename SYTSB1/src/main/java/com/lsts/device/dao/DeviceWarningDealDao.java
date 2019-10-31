package com.lsts.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.device.bean.DeviceWarningDeal;
import com.lsts.device.bean.DeviceWarningStatus;

/**
 * 
 * @author liming 
 *
 */
@Repository("deviceWarningDealDao")
public class DeviceWarningDealDao extends EntityDaoImpl<DeviceWarningDeal>{

	/**
	 * 统计问题报告书
	 * @return
	 */
	public int countBook(String documentId){
		String sql="select count(1) from base_reports_add t ,base_reports_re t1 where t.id = t1.fk_repotts_add_id   and t.created_date >(sysdate-10) and  t1.fk_document_id=?";
		Query query=createSQLQuery(sql, documentId);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	public List<DeviceWarningDeal> queryDealfo() {
		List<DeviceWarningDeal> list = new ArrayList<DeviceWarningDeal>();
		String hql = "from DeviceWarningDeal  where send_status = '0'";
		list = this.createQuery(hql).list();
		return list;
	}
}
