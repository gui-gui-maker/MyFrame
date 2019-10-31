package com.lsts.inspection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.inspection.bean.InspectionHallPara;
import com.lsts.inspection.dao.InspectionHallParaDao;

/**
 * 报检大厅流转管理   servier
 * 
 * @author 肖慈边 2014-1-27
 */
@Service("inspectionHallParaService")
public class InspectionHallParaService extends EntityManageImpl<InspectionHallPara, InspectionHallParaDao> {
	@Autowired
	private InspectionHallParaDao inspectionHallParaDao;

	public List<InspectionHallPara> queryInspectionHallParaByHallId(String id) {
		return inspectionHallParaDao.queryInspectionHallParaByHallId(id);
	}
}
