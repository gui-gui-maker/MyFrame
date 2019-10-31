package com.lsts.webservice.cxf.server;

import javax.xml.ws.Endpoint;

/**
 * 服务启动类（run this class等同于启动tomcat）
 * 
 * @ClassName HelloServer
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-09-16 上午11:38:00
 */
public class QueryDataServer {
	public static void main(String[] args) {
		 System.out.println("Web Service Server is starting..."); 
		 QueryDataImpl queryDataServiceImpl = new QueryDataImpl();
		 Endpoint.publish("http://localhost:8081/SYTS", queryDataServiceImpl);	// SYTS：工程名
		 System.out.println("Web Service Server is started.");  
	}
}
