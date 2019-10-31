package com.scts.weixin.com.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.weixin.com.bean.WeixinLoginLog;
import com.scts.weixin.com.service.WeixinLoginService;

@Controller
@RequestMapping("weixinLoginAction")
public class WeixinLoginAction extends SpringSupportAction<WeixinLoginLog,WeixinLoginService> {

	@Autowired
	private WeixinLoginService weixinLoginService;
	
	/**
	 * 扫码登录
	 * 通过授权吗获取用户信息
	 * @param code
	 * @return
	 */
	@RequestMapping("getUserAccont")
	@ResponseBody
	public HashMap<String, Object> getUserAccont(HttpServletRequest request,String code){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			weixinLoginService.getUserAccont(request,map,code);
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			if(!(boolean) map.get("success")) {
				map.put("msg", e.getMessage());
			}
			
			map.put("success", false);
		}
		
		return map;
	}
	
}
