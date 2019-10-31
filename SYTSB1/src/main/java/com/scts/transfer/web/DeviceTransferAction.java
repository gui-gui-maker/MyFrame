package com.scts.transfer.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.transfer.bean.DeviceTransfer;
import com.scts.transfer.service.DeviceTransferService;

/**
 * 设备数据传输控制器
 * 
 * @ClassName DeviceTransferAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-02-22 上午10:29:00
 */
@Controller
@RequestMapping("deviceTransfer")
public class DeviceTransferAction extends
		SpringSupportAction<DeviceTransfer, DeviceTransferService> {
	@Autowired
	private DeviceTransferService deviceTransferService;
	
	// 启动
    @RequestMapping(value = "start")
    @ResponseBody
    public HashMap<String, Object> start(HttpServletRequest request) throws Exception {	
        return deviceTransferService.start(request);
    }
	
	// 停止运行
    @RequestMapping(value = "stop")
    @ResponseBody
    public HashMap<String, Object> stop(HttpServletRequest request) throws Exception {	
        return deviceTransferService.stop(request);
    }

    
   
}
