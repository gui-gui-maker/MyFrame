package com.khnt.rbac.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.EmployeeExt;
import com.khnt.rbac.impl.dao.EmployeeExtDao;
import com.khnt.utils.StringUtil;
@Service
public class EmployeeExtService extends EntityManageImpl<EmployeeExt, EmployeeExtDao>{
	@Autowired
	EmployeeExtDao employeeExtDao;
	@Autowired
	AttachmentManager attachmentManager;

	public void saveEmployeeExt(EmployeeExt entity, String uploadFiles) {
		employeeExtDao.save(entity);	// 1、保存人员信息
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, entity.getId());	// 2、保存人员电子签名
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeExt> getExtByEmpId(String id) {
		
		return employeeExtDao.createQuery("from EmployeeExt where fk_emp_id=?", id).list();
	}
}
