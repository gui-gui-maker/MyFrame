package com.khnt.bpm.ext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.bean.CommonOpinion;
import com.khnt.bpm.ext.dao.CommonOpinionDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service
public class CommonOpinionManager extends EntityManageImpl<CommonOpinion, CommonOpinionDao> {

	@Autowired
	CommonOpinionDao commonOpinionDao;

	public void setDefault(String id, String isDefault) {
		commonOpinionDao.createQuery("update CommonOpinion set isDefault = '0' where id!=?", id).executeUpdate();
		commonOpinionDao
				.createQuery("update CommonOpinion set isDefault = ? where id=?", new Object[] { isDefault, id })
				.executeUpdate();
	}
}