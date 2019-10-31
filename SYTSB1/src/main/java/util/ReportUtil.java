/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.khnt.base.Factory;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.ming.webreport.DataRecord;

/** 
 * @author 作者 Jack Rio
 * @JDK 1.6
 * @version 创建时间：2014年12月18日 上午10:57:26 
 * 类说明 
 */
/**
 * @author Jack
 *
 */
public class ReportUtil {

	public static void assembleDataset(DataRecord rec, Map<String, Object> map) {

	}

	public static Object dealContent(String content)
			throws UnsupportedEncodingException {
		byte[] b = null;
		if (Factory.getSysPara().getProperty("DEVELOP").equals("true")) {
			b = StringUtil.transformNull(content).getBytes("GB2312");
			return b;
		} else {
			return content;
		}
	}

	public static void setRecodeFromStringMap(DataRecord rec,
			Map<String, String> map) throws Exception {
		for (String key : map.keySet()) {
			if (StringUtil.isNotEmpty(map.get(key)))
				rec.setValue(key, ReportUtil.dealContent(map.get(key)));
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!"+key+"##########3"+ReportUtil.dealContent(map.get(key)));
		}
	}

	
	/**
	 * 获取受理方式
	 * 
	 * @param slfs
	 * @return
	 */
	public static String getReturn(String slfs) {

		if (!slfs.equals("") && slfs != null) {
			if (slfs.equals("1")) {
				slfs = "书面受理";
			} else if (slfs.equals("2")) {
				slfs = "电话受理";
			} else if (slfs.equals("3")) {
				slfs = "网上受理";
			} else {
				slfs = "";
			}

		}

		return slfs;
	}
	
	public static String mergeCheckOps(String item , String check_op){
		if(check_op==null || "".equals(check_op)){
			return item ;
		}
		String[] ops = check_op.split(",");
		for(String op : ops ){
			if(item.equals(op)){
				return check_op;
			}
		}
		
		return item+","+check_op;
	}
	public static JSONArray getCheckOps(InspectionInfo info) {
		JSONArray personArray = new JSONArray();

		// 检验负责人
		JSONObject itemObj = new JSONObject();
		itemObj.put("id", info.getItem_op_id());
		itemObj.put("name", info.getItem_op_name());
		personArray.put(itemObj);

		// 参检人员
		String[] checkops = null;
		String[] checkopnames = null;
		if (StringUtil.isNotEmpty(info.getCheck_op_id())) {
			checkops = info.getCheck_op_id().split(",");
			checkopnames = info.getCheck_op_name().split(",");
		}
		for (int i = 0; checkops != null && i < checkops.length; i++) {
			if (!checkops[i].equals(info.getItem_op_id())) {
				JSONObject checkObj = new JSONObject();
				checkObj.put("id", checkops[i]);
				checkObj.put("name", checkopnames[i]);
				personArray.put(checkObj);
			}
		}
		return personArray;
	}
//	public static JSONArray getCheckOps(InspectionInfo info) {
//		JSONArray personArray = new JSONArray();
//
//		// 检验负责人
//		JSONObject itemObj = new JSONObject();
//		itemObj.put("id", info.getItemOpId());
//		itemObj.put("name", info.getItemOpName());
//		personArray.put(itemObj);
//
//		// 参检人员
//		String[] checkops = null;
//		String[] checkopnames = null;
//		if (StringUtil.isNotEmpty(info.getCheckOpId())) {
//			checkops = info.getCheckOpId().split(",");
//			checkopnames = info.getCheckOpName().split(",");
//		}
//		for (int i = 0; checkops != null && i < checkops.length; i++) {
//			if (!checkops[i].equals(info.getItemOpId())) {
//				JSONObject checkObj = new JSONObject();
//				checkObj.put("id", checkops[i]);
//				checkObj.put("name", checkopnames[i]);
//				personArray.put(checkObj);
//			}
//		}
//		//拥有报告录入权限的用户
//		Connection conn = Factory.getDB().getConnetion();
//		String  sql = "select t.id,t.name from SYS_USER t,SYS_USER_ROLE t1,SYS_ROLE t2,SYS_ROLE_PERMISSION t3, SYS_PERMISSION t4 " +
//				" where t.id = t1.user_id and t1.role_id = t2.id and t2.id = t3.role_id and t3.permission_id = t4.id and t4.p_name='报告录入' ";
//		
//		Statement queryStatement = null ;
//		ResultSet executSet = null ;
//		try{
//			queryStatement = conn.createStatement();
//			executSet = queryStatement.executeQuery(sql);
//		
//			while (executSet.next()) {
//				JSONObject checkObj = new JSONObject();
//				checkObj.put("id",executSet.getString("id"));
//				checkObj.put("name",executSet.getString("name"));
//				personArray.put(checkObj);
//			}
//		} catch (Exception e){
//			throw new KhntException(e);
//		} finally {
//			Factory.getDB().freeConnetion(conn);
//		}
//		
//		return personArray;
//	}
	
	public static String getNews(String name)
	{
		JSONObject result = new JSONObject();
		String strResult= null;
		try
        {
			//http://www.scsei.org.cn/news-service.php?search=
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", "http://192.168.3.5/news-service.php?search="+strSign);
	       
        	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	strResult=reader.readLine(); 
        	
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		return strResult;
	}
	
	public static String getNotice(String name)
	{
		JSONObject result = new JSONObject();
		String strResult= null;
		try
        {
			
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");//+"&image=1"
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", "http://192.168.3.5/news-service.php?category="+strSign);
	       
        	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	strResult=reader.readLine(); 
        	
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		return strResult;
	}
	
	public static String getNoticeImg(String name)
	{
		JSONObject result = new JSONObject();
		String strResult= null;
		try
        {
			
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");//
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", "http://192.168.3.5/news-service.php?category="+strSign+"&image=1");
	       
        	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	strResult=reader.readLine(); 
        	
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		return strResult;
	}
	
	
	public static String getMainOther(String name)
	{
		JSONObject result = new JSONObject();
		String strResult= null;
		try
        {
			
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", "http://192.168.3.5/news-service.php?category="+strSign+"&image=0");
	       
	       	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	strResult=reader.readLine(); 
        	
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		return strResult;
	}
	
	public static HashMap<String, Object> getNewsN(String name,String per_page,String offset)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject result = new JSONObject();
		String strResult= null;
		int sumC = 0;
		boolean flag = false;
		try
        {
			//http://www.scsei.org.cn/news-service.php?search=
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");
			String url1 = "http://192.168.3.5/news-service.php?search="+strSign;
			if(per_page!=null&&StringUtil.isNotEmpty(per_page)){
				url1 = url1+"&per-page="+per_page;
			}
			if(offset!=null&&StringUtil.isNotEmpty(offset)){
				url1 = url1+"&offset="+offset;
			}
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	       //&per-page=4
        	java.net.URL url = new URL(strURL);
        	
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	  Map headers = httpConnection.getHeaderFields();
              Set<String> keys = headers.keySet();
              for( String key : keys ){
                  String val = httpConnection.getHeaderField(key);
                  if("TotalRecords".equals(key)){
                	  flag = true;
                	  System.out.println(key+"    "+val);
                	  sumC = Integer.parseInt(val)-0-1;
                  }
                  
              }
              System.out.println( "-----头信息------"+httpConnection.getLastModified() );
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	strResult=reader.readLine(); 
        	System.out.println("------------------------"+strResult);
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		if(flag){
			map.put("data", strResult);
			map.put("sumC", sumC);
		}else{
			map.put("data", "[]");
			map.put("sumC", 0);
		}
		
		return map;
	}
	
	public static String getBaiDuGPS(String addr)
	{
	JSONObject result = new JSONObject();
	String strResult= null;
	try
	        {
		//http://api.map.baidu.com/geocoder?address=地址&output=输出格式类型&key=用户密钥&city=城市名
	       	//strSign = java.net.URLEncoder.encode(strSign,"GB2312");
	       	String recUrl = Factory.getSysPara().getProperty("GPS_URL", "http://api.map.baidu.com/geocoder?address=");
	       	String ak = Factory.getSysPara().getProperty("GPS_AK", "8HUyITWvYaasBRbK3ivsrYii");
	       	String strURL = recUrl+addr+"&output=json&key="+ak;// "http://"+rtxServerIp+":8012/SignAuth.cgi?user=" + strUser + "&sign=" + strSign;
	       	//String strURL="http://api.map.baidu.com/geoconv/v1/?coords="+lat+","+lng+"&from=1&to=5&ak=Pb6XLAxVqR89fSn28Pn67yyz";
	       		java.net.URL url = new URL(strURL+"&city=成都市");
	        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	        	//BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
	        	//strResult=reader.readLine(); 
	        	//String b=null;
	        	byte[] data = readInputStream(httpConnection.getInputStream());  
	            String json = new String(data);  
	            System.out.println("返回的数据。。。。。。。"+json);
	            //构建JSON数组对象  
	            JSONObject item = JSONObject.fromString(json);  
	            //从JSON数组对象读取数据  
	           //获取各个属性的值   
	                JSONObject result1 = item.getJSONObject("result");
	                JSONObject location = result1.getJSONObject("location");
	                String lng = location.getString("lng");
	                String lat = location.getString("lat");
	                System.out.println("---------"+addr+"--------------"+lng+","+lat);
	                //构造的对象加入集合当中   
	                strResult=lng+","+lat;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("系统出错"+e);
	        }
	return strResult;
	}
	
	/** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
}
