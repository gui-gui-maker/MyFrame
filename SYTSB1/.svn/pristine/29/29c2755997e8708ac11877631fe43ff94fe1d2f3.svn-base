package com.lsts.nk_message.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.nk_message.bean.MobileMessage;
import com.lsts.nk_message.dao.MobileMessageDao;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("mobileMessageManager")
public class MobileMessageManager extends EntityManageImpl<MobileMessage,MobileMessageDao>{
    @Autowired
    MobileMessageDao mobileMessageDao;
    
    
	public void sendDrawMessage1(HttpServletRequest request)throws Exception {
		URL url = null;
		String apiIdentity = "kh.scsei.org.cn";// 账户名
		String apiKey = "2774ab4e730554c8a0b097d610fefe16";// 密码
		
//		//获取要发送短信数据
		String ll=request.getParameter("fkMsg");
//		//号码
		String hm=request.getParameter("account");
//		if (list != null && list.size() > 0) {
			
//			String[] obj = (String[]) list.toArray();
//			for (int i = 0; i < obj.length-obj.length+1; i++) {
//				Object[] oo = (Object[]) obj[i];
//				String temp="AAA";
//				
//				String dt=oo[0]==null?null:oo[0].toString();//时间
//				String aa="";
//				if(dt.indexOf(" ")!=-1){
//					String ot=dt.split(" ")[0];
//					aa = temp.replace("AAA",ot);
//				}else{
//					aa = temp.replace("AAA",dt );
//				}
				
//				String bb = aa.replace("BBB", oo[0]==null?null:oo[0].toString());
//				String cc = bb.replace("CCC", oo[4]==null?null:oo[4].toString());
//				String tel =oo[3]==null?null:oo[3].toString();
				String send_content =URLEncoder.encode(ll.replaceAll("<br/>", ""), "UTF-8");// 发送内容
				String str = "http://sso.scsei.org.cn/sms_service.php?action=send&apiIdentity="
						+ apiIdentity + "&apiKey=" +apiKey+ "&destNumbers="+hm+"&exNumber=30"+"&content=" +send_content;
				
				url = new URL(str.trim());
				
				BufferedReader in = null;
				String inputLine = "";
				String flag="";
				
				System.out.println("`````````````````````````````````" + url);
				try {
					// System.out.println("开始发送短信手机号码为 ："+Mobile);
//					in = new BufferedReader(new InputStreamReader(url.openStream()));
//					System.out.println("`````````````````````````````````" + in);
//					inputLine = in.readLine();
//					
//					JSONObject jo = JSONObject.fromObject(inputLine);
//					 flag = jo.getString("errorcode");
					
				} catch (Exception e) {
					System.out.println("结束发送短信返回值:"+e.getMessage());
					inputLine = "-12";
				}
				// 0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；
				System.out.println("结束发送短信返回值:" + inputLine+flag);
				
				//放入MAP 准备回写 存到日志
//				MobileMessage mobileMessage = new MobileMessage();
				
//				mobileMessage.setFkMsg(ll);
//				mobileMessage.setAccount(hm);
//				mobileMessage.setSend_time(new Date());
//				mobileMessage.setType("1");
//				mobileMessage.setStatus(flag);
//				mobileMessage.setCreate_date(new Date());
				
//				saveHistroy(mobileMessage);
			}
			
//		}
//	}
	public void  saveHistroy(MobileMessage mobileMessage) throws Exception
    {	
		mobileMessageDao.save(mobileMessage);
    }

}
