package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.device.bean.TzsbAppOutsour;
import com.lsts.device.dao.TzsbAppOutsourDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppOutsourService")
public class TzsbAppOutsourService{
	
    @Autowired
	private TzsbAppOutsourDao tzsbAppOutsourDao;
    
    
    public  void delete(String id){
    	  tzsbAppOutsourDao.removeById(id);
    }
    
    public void save(TzsbAppOutsour entity){
    	tzsbAppOutsourDao.save(entity);
    }
    public List<TzsbAppOutsour> listByAppId(String appId){
    	return tzsbAppOutsourDao.listByAppId(appId);
    	
    }
}
