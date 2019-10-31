package com.fwxm.dining.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.DiningWindow;
import com.fwxm.dining.service.DiningWindowService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
@Controller
@RequestMapping("dining/diningWindow")
public class DiningWindowAction extends SpringSupportAction<DiningWindow, DiningWindowService>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DiningWindowService diningWindowService;
	
	@RequestMapping(value = "openOrCloseWindow")
	@ResponseBody
	public HashMap<String,Object> openOrCloseWindow(HttpServletRequest request,int windowStatus) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User u = (User) user.getSysUser();
		Employee e = u.getEmployee();
		DiningWindow diningWindow = diningWindowService.updatewindowStatus(windowStatus,e.getId());
		//对象保存到session中
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(10*60);
		session.setAttribute("diningWindow", diningWindow);
		session.setAttribute("employee", e);
		logger.setLevel(Level.INFO);
		logger.info("SessionId:"+session.getId()+",窗口打开了");
		
		return JsonWrapper.successWrapper(diningWindow);
	}
}
