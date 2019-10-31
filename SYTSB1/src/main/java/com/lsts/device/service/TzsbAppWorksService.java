package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.device.bean.TzsbAppWorks;
import com.lsts.device.dao.TzsbAppWorksDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppWorksService")
public class TzsbAppWorksService extends EntityManageImpl<TzsbAppWorks,TzsbAppWorksDao>{
	
    @Autowired
	private TzsbAppWorksDao tzsbAppWorksDao;
    
    
    public  void delete(String id){
    	tzsbAppWorksDao.removeById(id);
    }
    
    public void save(TzsbAppWorks entity){
    	tzsbAppWorksDao.save(entity);
    }
    public List<TzsbAppWorks> listByAppId(String appId){
    	return tzsbAppWorksDao.listByAppId(appId);
    	
    }
}
