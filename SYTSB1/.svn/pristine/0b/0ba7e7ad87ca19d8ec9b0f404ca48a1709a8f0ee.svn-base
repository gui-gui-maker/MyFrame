package com.lsts.equipment2.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipAttachment;
import com.lsts.equipment2.dao.EquipAttachmentDao;
import com.lsts.humanresources.bean.Upload;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("EquipAttachmentManager")
public class EquipAttachmentManager extends EntityManageImpl<EquipAttachment,EquipAttachmentDao>{
    @Autowired
    EquipAttachmentDao equipAttachmentDao;
    @SuppressWarnings("unchecked")
	public List<EquipAttachment> getFjByfkEquipmentId(String fkEquipmentId){
    	List<EquipAttachment> list=new ArrayList<EquipAttachment>();
    	String hql="from EquipAttachment where fkEquipmentId=?";
    	try {
			list=equipAttachmentDao.createQuery(hql, fkEquipmentId).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
}
