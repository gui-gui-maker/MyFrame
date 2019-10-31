package com.lsts.inspection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.dao.ReportDrawSignDao;
@Service
public class ReportDrawSignService extends EntityManageImpl<ReportDrawSign, ReportDrawSignDao>{
	@Autowired
	ReportDrawSignDao reportDrawSignDao;

	public List<ReportDrawSign> getByFkInspectionInfoId(String fkInspectionInfoId) {
		String hql = "from ReportDrawSign where fkInspectionInfoId = ?";
		return reportDrawSignDao.createQuery(hql, fkInspectionInfoId).list();
	}

}
