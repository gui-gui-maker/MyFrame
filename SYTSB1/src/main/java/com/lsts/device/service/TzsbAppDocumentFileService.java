package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.device.bean.TzsbAppDocumentFile;
import com.lsts.device.dao.TzsbAppDocumentFileDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppDocumentFileService")
public class TzsbAppDocumentFileService{
	
    @Autowired
	private TzsbAppDocumentFileDao tzsbAppDocumentFileDao;
    
    
    public  void delete(String id){
    	tzsbAppDocumentFileDao.removeById(id);
    }
    
    public void save(TzsbAppDocumentFile entity){
    	tzsbAppDocumentFileDao.save(entity);
    }
    public List<TzsbAppDocumentFile> listByAppId(String appId){
    	return tzsbAppDocumentFileDao.listByAppId(appId);
    	
    }
}
