package com.scts.discipline;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.utils.StringUtil;

public class PhoneSysUtil {

	
	private static String phone_sys_url = Factory.getSysPara().getProperty("phone_sys_url");
	private static String phone_call_pre = Factory.getSysPara().getProperty("phone_call_pre");
	private static String key = "E97424A4EFA7411E667800DB7848360C46D2A1708BF8FD7E0B2E70167E870EA376FFFCEF1227C1BE09A8F5F98D4E864DEED5CD183287455B";
	
	/**
	 * 
	 * author pingZhou
	 * @param phoneNum 电话号码
	 * @param phone 分机号
	 * @return
	 */
	public static HashMap<String, Object> call(String phoneNum,String phone)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			//String param = "autoc=&callee=913548199448&calleec=913548199448&chid=1&cmd=call&id=162723533-03-001&phone=600&ret=2&sret=";
			
			String url1 = phone_sys_url+"/call";

			System.out.println(url1);

	       	Map<String, String> map1 = new HashMap<String, String>();
	       	map1.put("phone", phone);
	       	map1.put("callee", phone_call_pre+phoneNum);
	       	map1.put("key", key);
	       	
	       	String params = getStringFromMap(map1);
	      
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	       }
	      
        }
        catch(Exception e)
        {
        	map.put("data", "");
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }

		return map;
	}
	
	public static HashMap<String, Object> callOther(String phoneNum,String phone)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			//String param = "autoc=&callee=913548199448&calleec=913548199448&chid=1&cmd=call&id=162723533-03-001&phone=600&ret=2&sret=";
			
			String url1 = phone_sys_url+"/callswitch";

			System.out.println(url1);

	       	Map<String, String> map1 = new HashMap<String, String>();
	       	map1.put("phone", phone);
	       	map1.put("callee", phone_call_pre+phoneNum);
	       	map1.put("key", key);
	       	
	       	String params = getStringFromMap(map1);
	      /* 返回 reset 0成功 phone 转接呼出的分机号码 callee 呼出的号码 sret 失败描述
	       *   */
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	       }
	      
        }
        catch(Exception e)
        {
        	map.put("data", "");
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }

		return map;
	}
	
	
	/**
	 * 挂断电话
	 * author pingZhou
	 * @param phone 分机号
	 * @return
	 */
	public static HashMap<String, Object> shutDown(String phone)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			String url1 = phone_sys_url+"/hangup";

			System.out.println(url1);


	       	Map<String, String> map1 = new HashMap<String, String>();
	       	map1.put("phone", phone);
	       	map1.put("key", key);
	       	
	       String params = getStringFromMap(map1);
	       
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	       }
	      
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }
		
		return map;
	}
	
	/**
	 * 查询电话状态
	 * author pingZhou
	 * @param phone 分机号
	 * @return
	 */
	public static HashMap<String, Object> state(String phone)
	{
		/*ret	结果大于0代表成功，其它均为失败
		state	分机状态(空闲:挂机:悬空:测试:呼入通话中:呼出通话中:外呼转入通话中)
		user	分机登录的座席帐号
		r_fsk	对端通话号码
		r_fsk_addr	对端通话号码地址
		timetalk	通话时长(毫秒)
		timefade	计费时长(毫秒)
		dtmf	按键
		url	录音文件相对地址
		timestart	记录开始时间
		id	记录序号(所有记录中的唯一编号,可以通过此编号查询唯一的记录)
		sret	如果失败的错误描述*/
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			String url1 = phone_sys_url+"/state";

			System.out.println(url1);


	       	Map<String, String> map1 = new HashMap<String, String>();
	       	map1.put("phone", phone);
	       	map1.put("key", key);
	       	
	       String params = getStringFromMap(map1);
	       
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	       }
	      
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }
		
		return map;
	}
	
	
	
	public static HashMap<String, Object> queryRec(String id,String phone,String timebegin,String timeend,
			String page,String fsk,String type)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			/*输入 

			id 记录ID,可以为空,如果不为空,忽略后面所有参数. 
			phone 要查询记录的分机号码(如果该项设置为空，则服务器取当前IP所对应设置的分机 
			timebegin 时间起点,可以为空,如果为空则无下限时间起点. 
			timeend 时间终点,可以为空,如果为空则无无上限时间终点. 
			page 页面,可以为空，如果为空则为1 
			fsk 号码过滤，可以模糊查询 
			type 电话类型（呼入:呼出:自动外呼） 
			key 当前服务器的注册码片断，至60个字符。 

			输出 (json字符串) 

			ret 返回该条件下的所有记录条数,负数代表错误 
			page 当前查询出的页面 
			page_max 页面最大数量 
			count 当前查询出来的记录条数 
			rowNum 在所有条件记录中的序号 
			caller 主叫 
			caller_addr 主叫地址 
			caller_user 主叫座席 
			callee 被叫 
			callee_addr 被叫地址 
			callee_user 被叫座席 
			timetalk 通话时长(毫秒) 
			timefade 计费时长(毫秒) 
			dtmf 按键 
			url 录音文件相对地址 
			timestart 记录开始时间 
			id 记录序号(所有记录中的唯一编号,可以通过此编号查询唯一的记录) 
			sret 如果失败的错误描述 */

			
			String url1 = phone_sys_url+"/queryrec";

			System.out.println(url1);


	       	Map<String, String> map1 = new HashMap<String, String>();
	       	
	       	map1.put("id", id);
	       	map1.put("phone", phone);
	       	map1.put("key", key);
	       	
	       	map1.put("timebegin", timebegin);
	       	map1.put("timeend", timeend);
	       	map1.put("page", page);
	       	
	     	map1.put("fsk", fsk);
	       	map1.put("type", type);
	       	
	       String params = getStringFromMap(map1);
	       
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	    	   if(id!=null&&StringUtil.isNotEmpty(id)) {
	    		   HashMap<String, Object> downMap = batchquerydown(id);
		    	   map.put("filePath", downMap.get("filePath"));
	    	   }
	    	   
	       }
	      
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }
		
		return map;
	}
	
	public static HashMap<String, Object> queryRecHr(String phone,String timebegin,String timeend,
			String page,String fsk,String type)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			String url1 = phone_sys_url+"/queryrec";
	       	Map<String, String> map1 = new HashMap<String, String>();
	       	
	       	map1.put("id", null);
	       	map1.put("phone", phone);
	       	map1.put("key", key);
	       	
	       	map1.put("timebegin", timebegin);
	       	map1.put("timeend", timeend);
	       	map1.put("page", page);
	       	
	     	map1.put("fsk", null);
	       	map1.put("type", type);
	       	
	       String params = getStringFromMap(map1);
	       
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	    	   
	    	   String result=resultMap.get("result").toString();
				JSONObject json = JSONObject.parseObject(result);

				JSONArray attr = json.getJSONArray("list");
				
				if(attr!=null){
					for (int i = 0; i < attr.size(); i++) {
						JSONObject obj=attr.getJSONObject(i);
						String id=obj.getString("id");
						if(StringUtil.isNotEmpty(id)){
							HashMap<String, Object> downMap = batchquerydown(id);
							map.put(id+"filePath", downMap.get("filePath"));
						}
					}
				}
				
				
	       }
	      
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }
		
		return map;
	}
	
	public static HashMap<String, Object> batchquerydown(String ids)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
        {
			
			
			String url1 = phone_sys_url+"/batchquerydown";

			System.out.println(url1);


	       	Map<String, String> map1 = new HashMap<String, String>();
	       	
	       	map1.put("ids", ids);
	       	map1.put("key", key);
	       	
	       String params = getStringFromMap(map1);
	       
	       HashMap<String, Object> resultMap = sendPost(url1,params);
	       if("true".equals(resultMap.get("success").toString())) {
	    	   map.put("data", resultMap.get("result").toString());
	    	   JSONObject json = (JSONObject) JSONObject.parse(resultMap.get("result").toString());
	    	   String url = json.getString("url");
	    	   if(StringUtil.isNotEmpty(url)) {
	    		   String fileurl = phone_sys_url+"/"+url;

	   			System.out.println(fileurl);
	   			String fileName = url.substring(5, url.length());
	   			map.put("filePath", "/"+Factory.getSysPara().getProperty("cloud_attachmentPath")+"/call/"+fileName);
	   			saveUrlAs(fileurl,Factory.getWebRoot()+"/"+Factory.getSysPara().getProperty("cloud_attachmentPath")+"/call/"+fileName);
	    	   }
	    	   
	       }
	      
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("系统出错"+e);
        }
		
		return map;
	}
	
	
	
	public static HashMap<String, Object> sendPost(String url,String param){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			 URL sendURL = new URL(url);

		       	System.out.println(param);
		       	
		    

	        	  HttpURLConnection connection = (HttpURLConnection) sendURL.openConnection();
	        	  connection.setUseCaches(false);
	        	  connection.setDoOutput(true);
	        	  connection.setRequestMethod("POST");
	              
	              DataOutputStream out = new DataOutputStream(
	                      connection.getOutputStream());
	              System.out.println(param);
	               byte[] b = param.getBytes();
	           out.write(b, 0, b.length);
	              // out.write(reqMsg.toString().getBytes());
	            //  out.write(json.toString().getBytes("UTF-8"));
	              out.flush();
	              out.close();
	              //获取响应状态
	              if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
	                  System.out.println("connect failed!");
	              }
	              connection.connect();
	        	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));   
	        	//strResult=reader.readLine();
	        	  StringBuffer result = new StringBuffer();
	        	 while (true) {
	                 String line = in.readLine();
	                 if (line == null) {
	                   break;
	                 }
	                 else {
	                     result.append(line);
	                 }
	               }
	        	 map.put("success", true);
	        	 map.put("result", result.toString());
	        	System.out.println("----------"+url+"----------"+result.toString());
		} catch (Exception e) {
			map.put("success", false);
			System.err.println("----------"+url+"----------请求失败");
			e.printStackTrace();
			// TODO: handle exception
		}
		return map;
	}
	
	public static String getStringFromMap(Map<String, String> map) {
		 StringBuilder reqData = new StringBuilder();
		  for (Map.Entry<String, String> entry : map.entrySet()) {
	       	   try {
	       		   if(entry.getValue()!=null) {
	       			 reqData.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
	       		   }
	       	   
	       	   } catch (UnsupportedEncodingException e) {
	       	    System.out.println("编码字符串[" + entry.getValue() + "]时发生异常:系统不支持该字符集[" + "UTF-8" + "]");
	       	    reqData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
	       	   }
		  }
	      if (reqData.length() > 0) {
	       	reqData.setLength(reqData.length() - 1); //删除最后一个&符号
	       }
      
      return reqData.toString();
	}
	
	/**
	 * 从网络下载资源存在本地
	 * author pingZhou
	 * @param fileUrl
	 * @param savePath
	 * @return
	 */
	 public static boolean saveUrlAs(String fileUrl, String savePath)/*fileUrl网络资源地址*/
	  {

	  try
	  {
	  URL url = new URL(fileUrl);/*将网络资源地址传给,即赋值给url*/
	  /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
	  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	  DataInputStream in = new DataInputStream(connection.getInputStream());
	  /*此处也可用BufferedInputStream与BufferedOutputStream*/
	  
	  File outFile = new File(savePath);
	  boolean flag = false;
	  if(!outFile.getParentFile().exists()) {
		  flag = true;
	  }
	  File poutFile = outFile.getParentFile();
	  while (flag) {
		  poutFile.mkdirs();
		  poutFile = poutFile.getParentFile();
		  if(poutFile.exists()) {
			  flag = false;
		  }
	  }
	  
	  if(!outFile.exists()) {
		  outFile.createNewFile();
	  }
		  
		  
	  DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
	  
	  /*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
	  byte[] buffer = new byte[4096];
	  int count = 0;
	  while ((count = in.read(buffer)) > 0)/*将输入流以字节的形式读取并写入buffer中*/
	  {
	  out.write(buffer, 0, count);
	  }
	  out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
	  in.close();
	  connection.disconnect();
	  return true;/*网络资源截取并存储本地成功返回true*/

	  }
	  catch (Exception e)
	  {
	  System.out.println(e + fileUrl + savePath);
	  return false;
	  }
	  }

}
