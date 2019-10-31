package com.lsts.advicenote.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.advicenote.bean.Affiche;
import com.lsts.advicenote.service.AdviceNoteService;
import com.lsts.advicenote.service.AfficheService;

/**
 * 通知书管理控制器
 * 
 * @ClassName AdviceNoteAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:45:00
 */
@Controller
@RequestMapping("affiche")
public class AfficheAction extends
		SpringSupportAction<Affiche, AfficheService> {
	
	
	@Autowired
	private AfficheService afficheService;
	

	// 保存
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(
			Affiche affiche ,HttpServletRequest request) {
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			CurrentSessionUser user = super.getCurrentUser();
			
			affiche.setCreated_by(user.getName());	// 录入人姓名
			affiche.setCreated_date(new Date());	// 录入时间
			
			afficheService.save(affiche);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			wrapper.put("success", false);
			log.error(kh.getMessage());
			
		}
		return wrapper;
	}
	
	

		@RequestMapping(value = "getAffiches")
		@ResponseBody
		public HashMap<String, Object> getAffiches(
				HttpServletRequest request) {
			
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				
				
				wrapper = afficheService.getAffiche();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				KhntException kh = new KhntException(e);
				wrapper.put("success", false);
				log.error(kh.getMessage());
				
			}
			return wrapper;
		}
		
	
		
		
	
}
