package com.scts.weixin.com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.utils.DateUtil;
import com.lsts.employee.dao.EmployeesDao;
import com.scts.weixin.com.WeiXinUtil;
import com.scts.weixin.com.bean.WeixinLoginLog;
import com.scts.weixin.com.dao.WeixinLoginDao;

@Service("weixinLoginService")
public class WeixinLoginService extends EntityManageImpl<WeixinLoginLog, WeixinLoginDao> {

	@Autowired
	private WeixinLoginDao weixinLoginDao;
	@Autowired
	EmployeesDao employeesDao;

	
	public void getUserAccont(HttpServletRequest request, HashMap<String, Object> map, String code) {
		
		ServletContext application = request.getServletContext();
		boolean getToken = true;
		//授权token
		String com_access_token = "";
		if(application.getAttribute("com_access_token_time")!=null) {
			Date com_access_token_time = (Date) application.getAttribute("com_access_token_time");
			int sec = calLastedTime(com_access_token_time);
			//两个小时是7200，减去5分钟是 6900
			if(sec<6900) {
				//没有过时，不用重新取
				com_access_token = (String) application.getAttribute("com_access_token");
			}else {
				getToken = false;
			}
		}else {
			getToken = false;
		}
		
		if(!getToken) {
			//没有access_token 需要重新获取
			WeiXinUtil.getAccessToken(request);
			com_access_token = (String) application.getAttribute("com_access_token");
		}
		//取的微信用户id
		HashMap<String, Object> userMap = WeiXinUtil.getUserInfo(request,com_access_token,code);
		com.alibaba.fastjson.JSONObject result = (JSONObject) userMap.get("result");
		System.out.println("-----------"+result.toJSONString());
		String userId = result.getString("UserId");
		String deviceId = result.getString("DeviceId");
		
		HashMap<String, Object> userInfoMap = WeiXinUtil.getUserInfoById(request, com_access_token, userId);
		com.alibaba.fastjson.JSONObject userResult = (JSONObject) userInfoMap.get("result");
		System.out.println("-----------"+userResult.toJSONString());
		//手机号码
		String userMobile = userResult.getString("mobile");
		
		List<Employee>  empL = employeesDao.suggest(userMobile);
		if(empL.size()>0){
			Employee emp = empL.get(0);
			String sql = "select u.account from sys_user u where u.employee_id = ? and u.status='1'";
			List<Object> list = employeesDao.createSQLQuery(sql, emp.getId()).list();
			if(list.size()>0){
				
				map.put("success", true);
				map.put("userName", list.get(0).toString());
				saveLog(userMobile, userId,com_access_token,code,deviceId,"1");
			}else{
				System.out.println("------------------没找到用户信息---------------------");
				map.put("success", false);
				saveLog(userMobile, userId,com_access_token,code,deviceId,"0");
				throw new KhntException("没有用户信息，请联系028-86607814！");
			}
			
			
		}else{
			System.out.println("------------------没找到人员信息---------------------");
			//手机号码信息不一致提示
			map.put("success", false);
			saveLog(userMobile, userId,com_access_token,code,deviceId,"0");
			throw new KhntException("用户名或密码错误，手机号信息不一致，请联系028-86607814！");
		}
		
	}
	
	public void saveLog(String mobile,String userId,String token,String code,String deviceId,String status) {
		
		WeixinLoginLog log = new WeixinLoginLog();
		log.setAccessToken(token);
		log.setCode(code);
		log.setDeviceId(deviceId);
		log.setMobile(mobile);
		log.setOpAction("扫码登录");
		log.setOpTime(new Date());
		log.setUserId(userId);
		log.setOpStatus(status);
		this.weixinLoginDao.save(log);
		
		
	}
	
	
	
	public int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}
	
}
