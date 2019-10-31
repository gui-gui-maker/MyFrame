package com.lsts.mobileapp.input.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.mobileapp.input.bean.InspectionNotice;

@Repository("inspectionNoticeDao")
public class InspectionNoticeDao extends EntityDaoImpl<InspectionNotice> {

	public InspectionNotice getByInspectInfoId(String inspectInfoId) {
		String hql = "from InspectionNotice where fk_inspect_info_id = ?";
		List<InspectionNotice> list = this.createQuery(hql, inspectInfoId).list();
		if(list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}
	
}
