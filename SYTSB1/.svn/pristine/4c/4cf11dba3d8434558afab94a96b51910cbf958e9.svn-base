package com.lsts.mobileapp.input.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.mobileapp.input.bean.ReportItemRecordHis;

@Repository("reportItemRecordHisDao")
public class ReportItemRecordHisDao extends EntityDaoImpl<ReportItemRecordHis> {

	public String getMaxStatus(String infoId) {
		String sql = "select max(to_number(t.data_status)) status from TZSB_REPORT_ITEM_RECORD_HIS t where t.fk_inspection_info_id=?";
		List<Object> list = this.createSQLQuery(sql, infoId).list();
		if(list.size()>0&&list.get(0)!=null) {
			return list.get(0).toString();
		}
		return "0";
	}
	
	
	
}
