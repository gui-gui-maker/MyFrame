package com.lsts.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.dao.EmployeeCertDao;

/**
 * 人员持证情况业务逻辑对象
 * @ClassName EmployeeCertService
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-03-03 下午05:15:00
 */
@Service("employeeCertService")
public class EmployeeCertService extends EntityManageImpl<EmployeeCert, EmployeeCertDao> {

	@Autowired
	private EmployeeCertDao employeeCertDao;
	@Autowired
	private AttachmentManager attachmentManager;

	@Override
	public void save(EmployeeCert entity) throws Exception {
		super.save(entity);
	}
	
	// 根据基本信息ID查询持证情况
    public List<EmployeeCert> queryEmployeeCertByBasicId(String id){
    	return employeeCertDao.queryEmployeeCertByBasicId(id);
    }
    public EmployeeCert detailCert(String id){
    	return (EmployeeCert)employeeCertDao.createQuery("from EmployeeCert c where c.id=?", id).list().get(0);
    }
    // 保存人员持证情况（含附件）
	public void saveEmployeeCert(EmployeeCert entity, String uploadFiles){
		entity.setStatus("0");
		employeeCertDao.save(entity);
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, entity.getId());
				}
			}
		}
	}
    
    // 删除持证情况
    public void delete(String ids) {
    	employeeCertDao.delete(ids);
    }
}
