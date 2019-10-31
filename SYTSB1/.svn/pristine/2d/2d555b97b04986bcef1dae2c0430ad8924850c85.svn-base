package com.lsts.humanresources.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.lsts.humanresources.bean.Upload;
import com.lsts.humanresources.dao.UploadDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("upload")
public class UploadManager extends EntityManageImpl<Upload,UploadDao>{
    @Autowired
    UploadDao uploadDao;
    @Autowired
	private AttachmentManager attachmentManager;
    
    public List<Upload> getByEmpId(String id){
    	List<Upload> list=new ArrayList<Upload>();
    	String hql="from Upload where FK_EMPLOYEE_ID=?";
    	list=uploadDao.createQuery(hql, id).list();
    	return list;
    }
    public HashMap<String, Object> deleteUpload(String id,String uploadId,String uploadPath){
              HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	try{
    		String hql="from Upload where upload_id=?";
    		List<Upload> list=new ArrayList<Upload>();
    		list=uploadDao.createQuery(hql, uploadId).list();
    		uploadDao.removeById(list.get(0).getId());
  			attachmentManager.deleteAttach(uploadId, uploadPath);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("查询单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询单位信息出错！");
  			e.printStackTrace();	
  		}
    	return wrapper;
    }
}
