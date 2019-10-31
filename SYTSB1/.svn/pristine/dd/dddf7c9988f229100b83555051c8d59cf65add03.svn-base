package com.lsts.webservice.cxf.client.device_transfer;

import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsts.webservice.cxf.server.IQueryData;
import com.scts.transfer.bean.DeviceTransfer;
import com.scts.transfer.dao.DeviceTransferDao;


/**
 * 调用市局数据接口类
 * 
 * @ClassName DeviceTransferClient
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-02-22 下午15:40:00
 */
public class DeviceTransferClient {

	@Autowired
	private DeviceTransferDao deviceTransferDao;
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//创建WebService客户端代理工厂  
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
		//注册WebService接口  
		factory.setServiceClass(IQueryData.class);  
		
		//设置WebService地址  
		//factory.setAddress("http://localhost:8081/SYTS/ws/QueryData");  
		factory.setAddress("http://kh.scsei.org.cn/ws/QueryData");  
		IQueryData queryService = (IQueryData)factory.create();  
		System.out.println("invoke webservice...");  		
		try {
			// 4、测试根据查询条件（设备ID：device_id）查询罐车检验报告信息 		
            System.out.println("测试根据查询条件（设备device_id）查询罐车检验报告信息，正在查询中，请稍后...");  
            String res1 = queryService.queryGCInfos("device_id", "402883a052166c5b01521a057f486a76");
			System.out.println("返回结果（罐车检验报告信息）：" + res1);  
			// 解析返回结果字符串
            Document document1 = DocumentHelper.parseText(res1);
            Element root1 = document1.getRootElement(); 
            Element set1 = (Element) root1.selectSingleNode("/root");
            Iterator<Element> iterator1 = set1.elementIterator("set");
            List list1 = set1.elements();
            System.out.println("返回结果数量（罐车检验报告信息）====" + list1.size());
            Element element1 = null;
            while (iterator1.hasNext()) {
                element1 = iterator1.next();
                Iterator iter = element1.attributeIterator();
                while (iter.hasNext()) {
                    Attribute attribute = (Attribute) iter.next();
                    System.out.println(attribute.getName() + ":" + element1.attributeValue(attribute.getName()));                  
                } 
            }
            System.out.println("测试根据查询条件（设备ID：device_id）查询罐车检验报告信息，查询结束。");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * 设备数据传输
	 * @param  
	 * @return 
	 * @author GaoYa
	 * @date 2017-02-22 下午16:27:00
	 */
	public void transferDeviceInfos() {
		try {
			boolean transfer = true;	// 是否传输数据
			
			// 1、检查设备数据传输控制器是否已启动
			List<DeviceTransfer> transferList = deviceTransferDao.getDeviceTransfer();
			for(DeviceTransfer deviceTransfer : transferList){
				// 数据传输状态（0：未启动 1：已启动 2：已停止运行 99：已作废）
				if(!"1".equals(deviceTransfer.getData_status())){
					transfer = false;
				}
			}
			
			if(transfer){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
