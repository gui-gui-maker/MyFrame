package webService.dqjy;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



public class Test {
	private static final String ADDRESS = "http://119.6.254.76:9014/JyDataJy"; 
	
	public static void main(String[] args) {
		//DeviceRegularInspectClient.inspect("190625","31305101812011110017");
	}
	/*public static void main(String[] args) {
		new Test().test();
	}*/
	public void test(){
		
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
						 		+ "<registCode >31305101812011110017</registCode >"
						 		+ "<area>锦江</area>"
						 		+ "<address>红牌楼</address>"
						 		+ "<buildingName>蔚蓝天地</buildingName>"
						 		+ "<building>2</building>"
						 		+ "<unit>1</unit>"
						 		+ "<useNumber>1</useNumber>"
						 		+ "<jyCompanyId >98</jyCompanyId>"
					 		+ "</info>";

			 String result = service.jyDataJy(data);
			 System.out.println("________________"+result+"--------------------------");
			 System.out.println("________________测试结束--------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
