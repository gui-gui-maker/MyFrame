package com.lsts.humanresources.web;


import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintWriter;  

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.apache.commons.io.IOUtils;  

import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.humanresources.aes.AesException;
import com.lsts.humanresources.aes.WXBizMsgCrypt;
import com.lsts.humanresources.service.WeixinManager;


/** 
 * 注解方式打开链接 
 *  
 * @author Sunlight 
 * 
 */  
@Controller  
@RequestMapping("weixin")
public class WeixinAction {  
    private String token = "sunlight";  
    private String encodingAESKey = "s8vFF4f6AWay3uAdJh79WD6imaam4BV6Kl4eL4UzgfM";  
    private String corpId = "此处修改为你的企业ID";  
  
    @RequestMapping(value = { "/coreJoinGet.do" }, method = RequestMethod.GET)  
    public void coreJoinGet(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {  
        // 微信加密签名  
        String msg_signature = request.getParameter("msg_signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
  
        System.out.println("request=" + request.getRequestURL());  
  
        PrintWriter out = response.getWriter();  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        String result = null;  
        try {  
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,  
                    corpId);  
            result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);  
        } catch (AesException e) {  
            e.printStackTrace();  
        }  
        if (result == null) {  
            result = token;  
        }  
        out.print(result);  
        out.close();  
        out = null;  
    }  
  
    @RequestMapping(value = { "/coreJoinPost.do" }, method = RequestMethod.POST)  
    public void coreJoinPost(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 微信加密签名  
        String msg_signature = request.getParameter("msg_signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
          
        //从请求中读取整个post数据  
        InputStream inputStream = request.getInputStream();  
        String postData = IOUtils.toString(inputStream, "UTF-8");  
        System.out.println(postData);  
          
        String msg = "";  
        WXBizMsgCrypt wxcpt = null;  
        try {  
            wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);  
            //解密消息  
            msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);  
        } catch (AesException e) {  
            e.printStackTrace();  
        }  
        System.out.println("msg=" + msg);  
          
        // 调用核心业务类接收消息、处理消息  
        String respMessage = WeixinManager.processRequest(msg);  
        System.out.println("respMessage=" + respMessage);  
          
        String encryptMsg = "";  
        try {  
            //加密回复消息  
            encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);  
        } catch (AesException e) {  
            e.printStackTrace();  
        }  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(encryptMsg);  
        out.close();  
  
    }  
    
  
}  