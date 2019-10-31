package webService.regularInspect;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.khnt.utils.StringUtil;

public class DeviceRegularInspectClient {
	private static final String ADDRESS = "http://119.6.254.76:9014/JyDataJy"; 
	/**
	 * 
	 * @param barCode 条码
	 * @param registCode 设备注册代码
	 * @return
	 */
	public static HashMap<String, Object> inspect(String barCode,String registCode){
		HashMap<String, Object> map = new HashMap<String, Object>();
		JaxWsProxyFactoryBean wsf = new JaxWsProxyFactoryBean();
        wsf.setAddress(ADDRESS);
        //添加拦截器
        wsf.getInInterceptors().add(new LoggingInInterceptor());
        wsf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
        wsf.setServiceClass(JyDataJyService.class);
        JyDataJyService service = (JyDataJyService) wsf.create();
		try {
			 System.out.println("________________测试开始--------------------------");
			 String data = "<info>"
					 			+ "<userName>tjy20170913</userName>"
						 		+ "<password>tjy20170913</password>"
						 		+ "<registNumber>"+barCode+"</registNumber>"
						 		+ "<registCode >"+registCode+"</registCode >"
						 		+ "<area></area>"
						 		+ "<address></address>"
						 		+ "<buildingName></buildingName>"
						 		+ "<building></building>"
						 		+ "<unit></unit>"
						 		+ "<useNumber></useNumber>"
						 		+ "<jyCompanyId >98</jyCompanyId>"
					 		+ "</info>";

			 String result = service.jyDataJy(data);
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
