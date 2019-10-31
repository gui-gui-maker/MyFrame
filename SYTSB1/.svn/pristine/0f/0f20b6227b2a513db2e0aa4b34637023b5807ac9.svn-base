package com.lsts.finance.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.weixin.service.MsgService;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.service.MessageCheckManager;
import com.lsts.humanresources.service.EmployeeBaseManager;

/**
 
 */
@Controller
@RequestMapping("finance/messageCheckAction")
public class MessageCheckAction extends
		SpringSupportAction<MessageCheck2, MessageCheckManager> {

	@Autowired
	private MessageCheckManager messageCheckManager;
	@Autowired
	private   EmployeeBaseManager employeeBaseManager;
	@Autowired
	private MessageService messageservice;
	// 短信
	@RequestMapping(value = "saveyz")
	@ResponseBody
	public HashMap<String, Object> saveyz(HttpServletRequest request,@RequestBody MessageCheck2 messageCheck2) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Date d = new Date();
		String number =	messageCheck2.getAccount();
		String number1 = messageCheckManager.getsjh(emp.getId());//查询出来的
		if(!number.equals(number1)){
			map.put("a", "手机号码不是当前登录用户无法查询！！");
			return map;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		messageCheck2.setSendTime(d);
		// 10分钟后验证码失效
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 10);
		messageCheck2.setEndTime(nowTime.getTime());
		messageCheck2.setSendType("DX");
		messageCheck2.setStatus("disable");
		double num1=Math.random()*8+1;
        double number2=Math.random()*99999;
        int num=(int)num1*100000+(int)number2;
        String vag="您的工资查询码为："+num;
		messageservice.sendMoMsg(request,messageCheck2.getId(),vag, messageCheck2.getAccount());
        messageCheck2.setFkMsg(num+"");
		messageCheckManager.save(messageCheck2);
		map.put("yyy", num);
		return map;
	}

	// 微信调用
	@RequestMapping(value = "savewx")
	@ResponseBody
	public HashMap<String, Object> savewx(HttpServletRequest request,
			@RequestBody MessageCheck2 messageCheck2) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Date d = new Date();
		String number =	messageCheck2.getAccount();
		String number1 = messageCheckManager.getsjh(emp.getId());//查询出来的
		if(!number.equals(number1)){
			map.put("a", "手机号码不是当前登录用户无法查询！！");
			return map;
		}
		String ids =	user.getId();
		String mid = messageCheckManager.getmid(ids);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		messageCheck2.setSendTime(d);
		double num1=Math.random()*8+1;
        double number2=Math.random()*99999;
        int num=(int)num1*100000+(int)number2;
		String msg ="您的工资查询码为："+num;
		String wx = messageCheck2.getAccount();
		System.out.println(msg);
		/*
		 * wechat  微信号
		 * 查找对应企业号
		 */
		messageservice.sendWxMsg(request,messageCheck2.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, msg,wx);

		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 10);
		messageCheck2.setEndTime(nowTime.getTime());
		messageCheck2.setSendType("WX");
		//messageCheck2.setStatus("enable");
		messageCheck2.setStatus("disable");
		messageCheck2.setFkMsg(num+"");
		
		messageCheckManager.save(messageCheck2);
     
		map.put("yyy", num);
		return map;
	}

	/**
	 * 
	 * @param request
	 * @param messageCheck2
	 * @return
	 * @throws Exception
	 */
	//工资管理
	@RequestMapping(value = "savetime")
	@ResponseBody
	public HashMap<String, Object> savetime(HttpServletRequest request,
			@RequestBody MessageCheck2 messageCheck2) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Date d = new Date();
			String  dhhm = messageCheck2.getAccount();
		String yzm = messageCheck2.getFkMsg();
		
		MessageCheck2 nowid = messageCheckManager.messageid(dhhm, yzm);

		Date endtim = nowid.getEndTime();
		//验证是否超时
		if (d.compareTo(endtim) == -1) {
			nowid.setStatus("enable");
			nowid.setCheckTime(d);
			messageCheckManager.save(nowid);
		
					 
			map.put("sc123", true);
			HttpSession session=request.getSession();
			session.setAttribute("edcrfv", 9999);
		}else{
			nowid.setStatus("disable");
			super.save(request, nowid);
			map.put("sc123", false);
		}
		return map;

	}
	//个人工资查询
	@RequestMapping(value = "savetime1")
	@ResponseBody
	public HashMap<String, Object> savetime1(HttpServletRequest request,
			@RequestBody MessageCheck2 messageCheck2) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Date d = new Date();
			String  dhhm = messageCheck2.getAccount();
		String yzm = messageCheck2.getFkMsg();
		
		MessageCheck2 nowid = messageCheckManager.messageid(dhhm, yzm);

		Date endtim = nowid.getEndTime();
		//验证是否超时
		if (d.compareTo(endtim) == -1) {
			nowid.setStatus("enable");
			nowid.setCheckTime(d);
			messageCheckManager.save(nowid);
		
					 
			map.put("sc123", true);
			HttpSession session=request.getSession();
			session.setAttribute("edcrfvg", 8888);
		}else{
			nowid.setStatus("disable");
			super.save(request, nowid);
			map.put("sc123", false);
		}
		return map;

	}
}
