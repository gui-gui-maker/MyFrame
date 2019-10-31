package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.device.bean.TzsbAppSupervisionUnit;
import com.lsts.device.dao.TzsbAppSupervisionUnitDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppSupervisionUnitService")
public class TzsbAppSupervisionUnitService{
	
    @Autowired
	private TzsbAppSupervisionUnitDao tzsbAppSupervisionUnitDao;
    
    
    public  void delete(String id){
    	tzsbAppSupervisionUnitDao.removeById(id);
    }
    
    public void save(TzsbAppSupervisionUnit entity){
    	tzsbAppSupervisionUnitDao.save(entity);
    }
    public List<TzsbAppSupervisionUnit> listByAppId(String appId){
    	return tzsbAppSupervisionUnitDao.listByAppId(appId);
    	
    }
}
