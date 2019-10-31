package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.utils.StringUtil;

public class DownYFileUtil {

	public static HashMap<String, Object> getListByName(String name)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String strResult= null;
		try
        {
			//kh.scsei.org.cn
			//192.168.3.34http://kh.scsei.org.cn/resourceInfo/queryDownResourceW.do?name=402883a05b0e3035015b0e5b93d01505&spaceType=9
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");
			String url1 = "http://192.168.3.34/resourceInfo/queryDownResourceW.do?name="+strSign+"&spaceType=9";
		
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);

        	java.net.URL url = new URL(strURL);
        	
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
        	strResult=reader.readLine(); 
        	
        	System.out.println("------------1------------"+strResult);
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		map.put("data", strResult);
		map.put("name", name);
		return map;
	}
	
	public static HashMap<String, Object> getAllDownFile()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String strResult= null;
		try
        {
			//kh.scsei.org.cn
			String url1 = "http://192.168.3.34/resourceInfo/queryDownFileW.do?spaceType=9";
		
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);

        	java.net.URL url = new URL(strURL);
        	
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
        	strResult=reader.readLine(); 
        	JSONObject result = JSON.parseObject(strResult);
        	map.put("sumC",result.get("sumC"));
        	map.put("queryInfoC",result.get("queryInfoC"));
        	map.put("queryInfo",result.get("queryInfo"));
        	map.put("orglist",result.get("orglist"));
        	System.out.println("-----------1-------------"+strResult);
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }

		return map;
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
			//192.168.3.4
			//192.168.3.5
			//http://www.scsei.org.cn/news-service.php?search=
			String strSign = java.net.URLEncoder.encode(name,"UTF-8");//192.168.3.4
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
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
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
	
}
