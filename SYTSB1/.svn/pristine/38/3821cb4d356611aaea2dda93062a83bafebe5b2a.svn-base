package webService.regularInspect;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



public class Test {
	private static final String ADDRESS = "http://119.6.254.76:9014/JyDataJy"; 
	
	public static void main(String[] args) {
		DeviceRegularInspectClient.inspect("190625","31305101812011110017");
	}
	public void test(){
		
		JaxWsProxyFactoryBean wsf = new JaxWsProxyFactoryBean();
        wsf.setAddress(ADDRESS);
        //添加拦截器
        wsf.getInInterceptors().add(new LoggingInInterceptor());
        wsf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
        wsf.setServiceClass(JyDataJyService.class);
        JyDataJyService service = (JyDataJyService) wsf.create();
		try {
			 System.out.println("________________测试开始--------------------------"+System.currentTimeMillis());
			 String data = "<info>"
					 			+ "<userName>tjy20170913</userName>"
						 		+ "<password>tjy20170913</password>"
						 		+ "<registNumber>182744</registNumber>"
						 		+ "<registCode >35005101002013090042</registCode >"
						 		+ "<area>金牛</area>"
						 		+ "<address>九里堤街道办</address>"
						 		+ "<buildingName></buildingName>"
						 		+ "<building></building>"
						 		+ "<unit></unit>"
						 		+ "<useNumber>301</useNumber>"
						 		+ "<jyCompanyId>98</jyCompanyId>"
					 		+ "</info>";

			 String result = service.jyDataJy(data);
			 System.out.println("________________"+result+"--------------------------");
			 System.out.println("________________测试结束--------------------------"+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
