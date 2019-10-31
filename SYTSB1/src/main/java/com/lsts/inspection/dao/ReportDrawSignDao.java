package com.lsts.inspection.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.ReportDrawSign;

@Repository
public class ReportDrawSignDao extends EntityDaoImpl<ReportDrawSign>{

	public List<ReportDrawSign> getReportDrawSignByInspectionInfo(String inspectionInfoId){
		String sql="select t.* from REPORT_DRAW_SIGN t where t.fk_inspection_info_id like ?";
		SQLQuery query=((SQLQuery) this.createSQLQuery(sql,"%"+inspectionInfoId+"%")).addEntity(ReportDrawSign.class);
		return query.list();
	}
}
