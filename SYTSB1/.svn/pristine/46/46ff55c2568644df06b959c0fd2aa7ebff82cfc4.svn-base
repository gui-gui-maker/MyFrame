package com.lsts.qualitymanage.service;

import java.util.ArrayList;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.dao.QualityAttachmentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("qualityAttachmentManager")
public class QualityAttachmentManager extends EntityManageImpl<QualityAttachment,QualityAttachmentDao>{
    @Autowired
    QualityAttachmentDao qualityAttachmentDao;
    
    @SuppressWarnings("unchecked")
	public List<QualityAttachment>getfs(String id){
		List<QualityAttachment> list=new ArrayList<QualityAttachment>();
    	String hql="from QualityAttachment where businessId=? order by uploadTime desc";
    	list=qualityAttachmentDao.createQuery(hql, id).list();
    	return list;
	}
}
