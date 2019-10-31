package com.lsts.common.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.common.bean.MessageXinxi;
import com.lsts.common.dao.MessageXinxiDao;
import com.lsts.constant.Constant;
@Service("messageXinxiService")
public class MessageXinxiService extends EntityManageImpl<MessageXinxi,MessageXinxiDao>{
	@Autowired
	private MessageXinxiDao messageXinxiDao;
	/**
	 * 短信借口
	 * */
	public void setSendMessage(String account, String fkMsg)throws Exception {
		URL url = null;
		String CorpID = Constant.MESSAGE_CORPID;// 账户名
		String Pwd = Constant.MESSAGE_PWD;// 密码

		String send_content =  URLEncoder.encode(fkMsg.replaceAll("<br/>", " "), "UTF-8");// 发送内容

		String str = "http://192.168.3.15/sms_service.php?action=send&apiIdentity="
				+ CorpID + "&apiKey=" + Pwd + "&destNumbers="+account+"&exNumber=30"+"&content=" + send_content;
		
		url = new URL(str.trim());
		System.out.println("短信发送地址：" + url);
		System.out.println("开始发送短信手机号码为 ："+account);
		
		BufferedReader in = null;
		String inputLine = "";
		String flag="";
		
		System.out.println("`````````````````````````````````" + url);
		try {
			// System.out.println("开始发送短信手机号码为 ："+Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			System.out.println("```````````````````````" + in);
			inputLine = in.readLine();
			
			JSONObject jo = JSONObject.fromObject(inputLine);
			 flag = jo.getString("errorcode");
			
		} catch (Exception e) {
			System.out.println("结束发送短信返回值:"+e.getMessage());
			inputLine = "-9";
		}
		// 0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；
		System.out.println("结束发送短信返回值:" + inputLine+flag);
		
	}
	/**
	 * 发送微信
	 * @param business_id --
	 *            业务id
	 * @param content --
	 *            发送内容
	 * @param destNumber --
	 *            发送目标手机号码
	 * @return flag --
	 * 			  发送状态（0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；）
	 * @author GaoYa
	 * @date 2015-11-25 16:35:00
	 * @throws Exception
	 */
	public String sendWxMsg(String corpID, String pwd, String account, String fkMsg){
		URL url = null;
		if(StringUtil.isEmpty(corpID)){
			corpID = Constant.INSPECTION_CORPID;	// 账户名（检验报告书专用收发帐号，与企业号检验报告应用对应收发）
		}
		if(StringUtil.isEmpty(pwd)){
			pwd = Constant.INSPECTION_PWD;// 密码	
		}
		
		String flag = "";
		String inputLine = "";
		String message = "";	// 返回的错误或成功提示
		try {
			String send_content = URLEncoder.encode(fkMsg.replaceAll("<br/>", " "), "UTF-8");// 发送内容
				
			String str = "http://192.168.3.15/sms_service.php?action=send&apiIdentity="
					+ corpID + "&apiKey=" + pwd + "&gateways=wechat-book&msgType=text&destNumbers="+account+"&content=" + send_content;
			
			url = new URL(str.trim());
			System.out.println("微信发送地址：" + url);
			System.out.println("开始发送微信手机号码为 ："+account);
				
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = in.readLine();
				
			JSONObject jo = JSONObject.fromObject(inputLine);
			flag = jo.getString("errorcode");
			message = jo.getString("message");
	
			// 0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；
			System.out.println("结束发送微信返回值:" + inputLine+flag);
			System.out.println("结束发送微信返回值:" + message);
	
			
		} catch (Exception e) {
			System.out.println("结束发送微信返回值:"+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}		
	
	/**短信日志
	 * @param personId 接收人id
	 * @param personName 接收人姓名
	 * @param account 接收人电话
	 * @param sendTime 发送时间
	 * @param fkMsg 发送内容
	 * @param businessType 业务类型
	 * @param transmitMode 发送方式
	 * @param sendType 发送类型（定时发送、实时发送）
	 * 
	 */
	public void setSaveMessageXinxi(String personId,String personName,String account,Date sendTime, String fkMsg,String businessType,String transmitMode,String sendType){
		MessageXinxi messageXinxi=new MessageXinxi();
		
		messageXinxi.setPersonId(personId);
		messageXinxi.setPersonName(personName);
		messageXinxi.setAccount(account);
		messageXinxi.setSendTime(sendTime);
		messageXinxi.setFkMsg(fkMsg);
		messageXinxi.setBusinessType(businessType);
		messageXinxi.setTransmitMode(transmitMode);
		messageXinxi.setSendType(sendType);
		
		messageXinxiDao.save(messageXinxi);

	}
	
}
