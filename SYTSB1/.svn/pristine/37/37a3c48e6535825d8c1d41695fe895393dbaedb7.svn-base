package com.lsts.sinspection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.sinspection.bean.SupervisionInspection;
import com.lsts.sinspection.dao.SupervisionInspectionDao;

/**
 * 产品监检业务逻辑对象
 * @ClassName SupervisionInspectionService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-26 下午01:14:00
 */
@Service("supervisionInspectionService")
public class SupervisionInspectionService extends
		EntityManageImpl<SupervisionInspection, SupervisionInspectionDao> {
	@Autowired
	private SupervisionInspectionDao supervisionInspectionDao;
	
	// 根据产品监检ID查询附件ID
	public String queryAttachmentId(String id) {
		return supervisionInspectionDao.queryAttachmentId(id);
	}

	// 删除产品监检信息
	public void delSupervisionInspection(String ids) throws Exception {
		supervisionInspectionDao.delSupervisionInspection(ids);
	}
}
