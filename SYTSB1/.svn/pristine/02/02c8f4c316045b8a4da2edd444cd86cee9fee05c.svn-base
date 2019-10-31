package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.device.bean.TzsbAppConsUnit;
import com.lsts.device.dao.TzsbAppConsUnitDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppConsUnitService")
public class TzsbAppConsUnitService{
	
    @Autowired
	private TzsbAppConsUnitDao tzsbAppConsUnitDao;
    
    
    public  void delete(String id){
    	tzsbAppConsUnitDao.removeById(id);
    }
    
    public void save(TzsbAppConsUnit entity){
    	tzsbAppConsUnitDao.save(entity);
    }
    public List<TzsbAppConsUnit> listByAppId(String appId){
    	return tzsbAppConsUnitDao.listByAppId(appId);
    	
    }
}
