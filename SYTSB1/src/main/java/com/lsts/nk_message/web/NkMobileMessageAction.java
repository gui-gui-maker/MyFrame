package com.lsts.nk_message.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.message.service.SysMessageManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.nk_message.bean.MobileMessage;
import com.lsts.nk_message.dao.MobileMessageDao;
import com.lsts.nk_message.service.MobileMessageManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;












import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("mobileMessage/nk")
public class NkMobileMessageAction extends SpringSupportAction<MobileMessage, MobileMessageManager> {

	 @Autowired
	private MobileMessageDao  mobileMessageDao;
    @Autowired
    private MobileMessageManager  mobileManager;
	@Autowired
	private SysMessageManager messageManager;
	
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(
			MobileMessage mobileMessage ,HttpServletRequest request) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			mobileManager.save(mobileMessage);
			mobileManager.sendDrawMessage1(request);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
//			wrapper.put("success", false);
//			log.error(kh.getMessage());
			
		}
		return wrapper;
	}
}