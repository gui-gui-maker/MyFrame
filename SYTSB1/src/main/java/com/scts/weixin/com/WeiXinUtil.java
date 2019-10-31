package com.scts.weixin.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.khnt.base.Factory;

public class WeiXinUtil {
	
	private static String appId="ww60c5a15a6e259d36";
	
	private static String corpsecret= "aUiTdmvske30RnLEMRqeShaIntgG9FF5Kwvt9PPsf0U";


	
////获取授权信息
//https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww60c5a15a6e259d36&corpsecret=aUiTdmvske30RnLEMRqeShaIntgG9FF5Kwvt9PPsf0U
//授权
//qfDJcmJ5PuHpUDBDZg-jPxuU5ROubC0wWBNda8XHZ-hvjTM8hNDv_VhQGLMY2dG0fC9HFyBikFFe-D30uCcCQikKGJehU6wGmI61OGFi1P_u5WmcDY9ykjcBk-HPnByKaYji7jZ0Rm2EQrde8EavAM7pn42cg5a1WzMiLL7GTiCQAIaoB5uhS50gRqoA1eb7eBB0BAQDrDTp6NvqInGTXA
//查询授权用户id
//https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=qfDJcmJ5PuHpUDBDZg-jPxuU5ROubC0wWBNda8XHZ-hvjTM8hNDv_VhQGLMY2dG0fC9HFyBikFFe-D30uCcCQikKGJehU6wGmI61OGFi1P_u5WmcDY9ykjcBk-HPnByKaYji7jZ0Rm2EQrde8EavAM7pn42cg5a1WzMiLL7GTiCQAIaoB5uhS50gRqoA1eb7eBB0BAQDrDTp6NvqInGTXA&code=EzMiribztQeMVXOfI6t5khvMdBknRMRS0rfpmMf7ce8
//查询授权用户名
//https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=qfDJcmJ5PuHpUDBDZg-jPxuU5ROubC0wWBNda8XHZ-hvjTM8hNDv_VhQGLMY2dG0fC9HFyBikFFe-D30uCcCQikKGJehU6wGmI61OGFi1P_u5WmcDY9ykjcBk-HPnByKaYji7jZ0Rm2EQrde8EavAM7pn42cg5a1WzMiLL7GTiCQAIaoB5uhS50gRqoA1eb7eBB0BAQDrDTp6NvqInGTXA&userid=ping



	//缓存access_token
    /**
         * 扫码登录
         *  缓存access_token
     * @return
     */
	public static HashMap<String, Object> getAccessToken(HttpServletRequest request)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String strResult= null;
		try
	    {
			//kh.scsei.org.cn
			String url1 = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+appId+"&corpsecret="+corpsecret;
			
			String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
			
	    	java.net.URL url = new URL(strURL);
	    	
	    	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
	    	strResult=reader.readLine(); 
	    	com.alibaba.fastjson.JSONObject result = JSON.parseObject(strResult);
	    	ServletContext application = request.getServletContext();
	    	application.setAttribute("com_access_token", result.get("access_token"));
	    	application.setAttribute("com_access_token_time", new Date());
	    	
	    	System.out.println("-----------1-------------"+result.get("access_token"));
	    }
	    catch(Exception e)
	    {
	    	System.out.println("系统出错"+e);
	    }
	
		return map;
	}

	/**
	 * 获取授权人员信息
	 * @param request
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public static HashMap<String, Object> getUserInfo(HttpServletRequest request,String accessToken,String code)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String strResult= null;
		try
	    {
			//kh.scsei.org.cn
			String url1 = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+accessToken+"&code="+code;
		
	       	
	    	/*errcode	返回码
			errmsg	对返回码的文本描述内容
			UserId	成员UserID。若需要获得用户详情信息，可调用通讯录接口：读取成员
			DeviceId	手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)*/
		
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	
	    	java.net.URL url = new URL(strURL);
	    	
	    	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
	    	strResult=reader.readLine(); 
	    	com.alibaba.fastjson.JSONObject result = JSON.parseObject(strResult);
	    	
	    	map.put("result", result);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("系统出错"+e);
	    }
	
		return map;
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param request
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public static HashMap<String, Object> getUserInfoById(HttpServletRequest request,String accessToken,String userId)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String strResult= null;
		try
	    {
			//kh.scsei.org.cn
			String url1 = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&userid="+userId;
		
			/*
			 *{"errcode":0,"errmsg":"ok","userid":"ping","name":"周定萍","department":[],"position":"","mobile":"1354xxxxxx48","gender":"2","email":"","avatar":"http://p.qlogo.cn/bizmail/U0fg6koEOeQYiaicJ854ic0jA4UucYoKUicChKiaARZ2K8O7h7jtAmEicz7g/0","status":1,"isleader":0,"extattr":{"attrs":[]},"telephone":"","enable":1,"hide_mobile":0,"order":[],"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc468563487e59123a","alias":"","is_leader_in_dept":[]}
			 */
		
	       	String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	
	    	java.net.URL url = new URL(strURL);
	    	
	    	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));   
	    	strResult=reader.readLine(); 
	    	com.alibaba.fastjson.JSONObject result = JSON.parseObject(strResult);
	    	
	    	map.put("result", result);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("系统出错"+e);
	    }
	
		return map;
	}
	
}
