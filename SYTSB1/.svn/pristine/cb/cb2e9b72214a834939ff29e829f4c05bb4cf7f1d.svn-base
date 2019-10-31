package com.lsts.webservice.cxf.client.device_transfer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.lsts.report.bean.SysOrg;
import com.lsts.webservice.cxf.client.device_transfer.server.TsDeivceService;
import com.lsts.webservice.cxf.client.device_transfer.server.impl.TsDeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DevicePassCity {
	
	private static final QName SERVICE_NAME = new QName("http://impl.device.ws.ts.zjpt/", "tsDeviceService");
	

	//获取市局接口类
	private static TsDeivceService getInterface(){
		
		
		
		
		 String url="http://192.168.1.35:9090/cxfws/tsDeviceService?wsdl"; 
	        URL wsdlURL = null; 
	        File wsdlFile = new File(url); 
	        try { 
	            if (wsdlFile.exists()) { 
	                wsdlURL = wsdlFile.toURL(); 
	            } else { 
	                wsdlURL = new URL(url); 
	            } 
	        } catch (MalformedURLException e) { 
	            e.printStackTrace(); 
	        } 
	        TsDeviceService ss = new TsDeviceService(wsdlURL, SERVICE_NAME); 
	        TsDeivceService port = ss.getTsDeivceServiceImplPort();  
	        
	        return port;
	}
	
	//根据电梯二维码获取市局数据
//	private void getDeviceInfo(String douberCode){
	public static void main(String args[]) throws java.lang.Exception {	
	String result = getInterface().geDeviceInfoByQrCode("fg54gsd","4yukjngf43", "123456", "3");
	
	System.out.println(result);
	
	JSONObject jsonObject = JSONObject.fromObject(result);
	
//	 JSONArray list=(JSONArray)jsonObject.getJSONArray("SUCCESS");
	 
		System.out.println((String)jsonObject.get("SUCCESS:"));
		
		
		
		
	}
}
