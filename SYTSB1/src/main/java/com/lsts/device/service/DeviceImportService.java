package com.lsts.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.device.bean.DeviceDocument;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-04-02 下午02:25:00
 */
@Service("deviceImportService")
public class DeviceImportService{
	
    @Autowired
	private DeviceService deviceService;
    
    /**
	 * 保存
	 * @param deviceList -- 设备基础信息对象集合
	 * @return 
	 * @author GaoYa
	 * @date 2014-04-02 下午02:26:00
	 */
    @SuppressWarnings("unchecked")
    public void saveData(List<DeviceDocument> deviceList) throws Exception{
		// 保存设备基础信息
		for(DeviceDocument device : deviceList){
 	   		deviceService.save(device);	// save base_device_document      
		}
	}
}
