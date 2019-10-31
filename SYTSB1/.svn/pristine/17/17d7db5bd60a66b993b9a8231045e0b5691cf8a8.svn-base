package webService.devicesQuery;

import java.util.HashMap;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



public class Test {
	private static final String ADDRESS = "http://119.6.254.76:9014/JyDataJy"; 
	
	public static void main(String[] args) {
		HashMap<String, Object> map = DeviceQueryClient.queryDevice("190625");
		System.out.println(map);
	}
/*	public static void main(String[] args) {
		Test t= new Test();
		t.test();
	}
*/	public void test(){
		
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
						 		+ "<registNumber>190625</registNumber>"
						 		+ "<jyCompanyId >98</jyCompanyId>"
					 		+ "</info>";

			 String result = service.jyDataQuery(data);
			 System.out.println("________________"+result+"--------------------------");
			 System.out.println("________________测试结束--------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
