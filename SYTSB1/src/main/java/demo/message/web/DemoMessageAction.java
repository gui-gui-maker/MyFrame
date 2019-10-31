package demo.message.web;

import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.message.service.SysMessageManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@Controller
@RequestMapping("/demo/message/")
public class DemoMessageAction {

	@Autowired
	SysMessageManager messageManager;

	@RequestMapping("sendSms")
	public Map<String, Object> sendSms(String content, String json) throws Exception {
		if (StringUtil.isEmpty(content) || StringUtil.isEmpty(json))
			return JsonWrapper.failureWrapperMsg("短信内容、接受人不能为空！");
		JSONArray ja = JSONArray.fromString(json);
		CurrentSessionUser u = SecurityUtil.getSecurityUser();
		messageManager.sendMessage(u.getId(), u.getName(), content, SysMessageManager.TYPE_SMS, ja);
		return JsonWrapper.successWrapper();
	}

	@RequestMapping("sendEmail")
	public Map<String, Object> sendEmail(String content, String json) throws Exception {
		if (StringUtil.isEmpty(content) || StringUtil.isEmpty(json))
			return JsonWrapper.failureWrapperMsg("短信内容、接受人不能为空！");
		JSONArray ja = JSONArray.fromString(json);
		CurrentSessionUser u = SecurityUtil.getSecurityUser();
		messageManager.sendMessage(u.getId(), u.getName(), content, SysMessageManager.TYPE_EMAIL, ja);
		return JsonWrapper.successWrapper();
	}
}
