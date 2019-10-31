package com.lsts.advicenote.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.advicenote.bean.MessageInfo;
import com.lsts.advicenote.service.MessageService;

/**
 * 通知书管理控制器
 * 
 * @ClassName MessageAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:45:00
 */
@Controller
@RequestMapping("message")
public class MessageAction extends
		SpringSupportAction<MessageInfo,MessageService> {
	
	
	@Autowired
	private MessageService messageService;
	

		// 保存
		@RequestMapping(value = "saveBasic")
		@ResponseBody
		public HashMap<String, Object> saveBasic(
				MessageInfo message ,HttpServletRequest request) {
			
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				CurrentSessionUser user = super.getCurrentUser();
				
				message.setBy_op(user.getName());	// 录入人姓名
				message.setTime(new Date());	// 录入时间
				
				messageService.save(message);
				
//				messageService.sendDrawMessage();
				wrapper.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				KhntException kh = new KhntException(e);
				wrapper.put("success", false);
				log.error(kh.getMessage());
				
			}
			return wrapper;
		}
		
		
	
				
					
					
					
					
					
					
					
					

					
					




				
	
}
