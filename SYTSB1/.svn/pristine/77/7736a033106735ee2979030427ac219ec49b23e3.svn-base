package com.lsts.humanresources.service;

import java.util.Date;  
import java.util.Map;  

import com.lsts.humanresources.util.MessageUtil;
import com.lsts.humanresources.util.TextMessage;
   
  
/** 
 * 处理微信发来的信息 
 * @author Sunlight 
 * 
 */  
public class WeixinManager {  
  
    public static String processRequest(String msg) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(msg);  
  
            System.out.println("Event=="+requestMap.get("Event"));  
              
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
              
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                String content = requestMap.get("Content");   
                respContent = "Sunlight提示：您发送的是文本消息！内容是："+content;  
            }  

            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(e);  
            respMessage="有异常了。。。";  
        }  
        return respMessage;  
    }  
  
}  