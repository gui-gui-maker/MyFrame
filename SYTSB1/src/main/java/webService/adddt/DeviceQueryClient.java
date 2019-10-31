package webService.adddt;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import webService.dqjy.JyDataJyService;

import com.khnt.utils.StringUtil;

public class DeviceQueryClient {
	
	private static final String ADDRESS = "http://182.150.40.154:9014/JyDataNewJy"; 
	//private static final String ADDRESS = "http://119.6.254.76:9014/JyDataNewJy"; 
	/**
	 * 
	 * @param barcode 条码
	 * @return
	 */
	public static HashMap<String, Object> queryDevice(String barcode,String user,String serialNumber,JyDataNewJyService service){
		HashMap<String, Object> map = new HashMap<String, Object>();
		JaxWsProxyFactoryBean wsf = new JaxWsProxyFactoryBean();
        wsf.setAddress(ADDRESS);
        //添加拦截器
        wsf.getInInterceptors().add(new LoggingInInterceptor());
        wsf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
        wsf.setServiceClass(JyDataNewJyService.class);
        //JyDataNewJyService service = (JyDataNewJyService) wsf.create();
        service = (JyDataNewJyService) wsf.create();
		try {
			 System.out.println("________________测试开始--------------------------");
			 String data = "<info>"
					 			+ "<userName>tjy20170913</userName>"
						 		+ "<password>tjy20170913</password>"
						 		+ "<registNumber>"+barcode+"</registNumber>"
						 		+ "<jyCompanyId >98</jyCompanyId>"
						 		+ "<jyUserName >"+user+"</jyUserName>"
						 		+ "<serialNumber>"+serialNumber+"</serialNumber>"
					 		+ "</info>";

			 String result = service.jyDataNewJy(data);
			 map = parseXml(result);
			 System.out.println("________________"+result+"--------------------------");
			 System.out.println("________________测试结束--------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg",e.getMessage());
		}
		return map;
	}
	
	public static HashMap<String, Object> parseXml(String xmlStr) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
        //字符串 转换成Document  
        Document document = DocumentHelper.parseText(xmlStr);  
        //获取根节点元素对象  
        Element root = document.getRootElement();  
        //迭代当前节点下面的所有子节点  
        @SuppressWarnings("unchecked")
		Iterator<Element> iterator = root.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
        	if(!StringUtil.isEmpty(e.getTextTrim())){  
        		map.put(e.getName(), e.getText());
            } 
        }   
		return map;
	}
}
