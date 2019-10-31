package com.scts.weixin.app.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.log.service.SysLogService;
import com.scts.weixin.app.bean.WeixinAppInfo;
import com.scts.weixin.app.dao.WeixinAppInfoDao;

/**
 * 微信应用基础信息业务逻辑对象
 * 
 * @ClassName WeixinAppInfoManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-08-20 上午09:51:00
 */
@Service("weixinAppInfoManager")
public class WeixinAppInfoManager extends EntityManageImpl<WeixinAppInfo, WeixinAppInfoDao> {
	@Autowired
	WeixinAppInfoDao weiXinAppInfoDao;
	@Autowired
	private SysLogService logService;

	/**
	 * 根据应用编号查询微信应用
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-23 下午02:15:00
	 */
	public WeixinAppInfo getInfo(String app_code) {
		return weiXinAppInfoDao.getInfo(app_code);
	}

	/**
	 * 根据微信应用编号查询首页响应地址
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-20 上午10:32:00
	 */
	public String getAppIndexUrl(String app_code) {
		return weiXinAppInfoDao.getAppIndexUrl(app_code);
	}

	/**
	 * 根据微信应用编号查询微信应用
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-27 上午09:51:00
	 */
	public String getAppInfo(String app_code) {
		return weiXinAppInfoDao.getAppInfo(app_code);
	}

	/**
	 * 根据手机号码查询系统在用的登录用户account
	 * 
	 * @param phone -- 用户手机号码
	 * @return
	 * @author GaoYa
	 * @date 2018-08-17 下午05:12:00
	 */
	public String getAccount(String phone) {
		String account = null;
		List<?> list = weiXinAppInfoDao.getAccount(phone);
		log.debug(this.getClass().getName() + " method getAccount 获取到account个数：" + list.size());
		if (list != null && list.size() > 0) {
			account = list.get(0).toString();
		}
		return account;
	}

	/**
	 * 根据手机号码查询系统在用User
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public User getUser(String phone) throws Exception {
		List<User> userList = weiXinAppInfoDao.getUser(phone);
		log.debug(this.getClass().getName() + " method getUser 获取到User个数：" + userList.size());
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}

	// 保存
	public void saveInfo(HttpServletRequest request, WeixinAppInfo info) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		// User user = (User) curUser.getSysUser();
		// Employee emp = (Employee) user.getEmployee();
		// Org org = (Org) curUser.getDepartment();
		String status = request.getParameter("status");

		// 1、保存
		if ("add".equals(status)) {
			info.setCreate_user_id(curUser.getId());
			info.setCreate_user_name(curUser.getName());
			info.setCreate_date(
					DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime()));
		} else {
			info.setMdy_user_id(curUser.getId()); // 最后修改人ID
			info.setMdy_user_name(curUser.getName()); // 最后修改人姓名
			info.setMdy_date(DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime())); // 最后修改时间
		}
		info.setData_status("0"); // 数据状态（0：已启用）
		weiXinAppInfoDao.save(info);

		// 2、记录日志
		String status_text = "创建";
		if ("modify".equals(status)) {
			status_text = "修改";
		}
		logService.setLogs(info.getId(), status_text + "微信应用配置", status_text + "微信应用配置", curUser.getId(),
				curUser.getName(), new Date(), request.getRemoteAddr());
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			if (StringUtil.isNotEmpty(id)) {
				// 1、删除
				weiXinAppInfoDao.createSQLQuery("update WEIXIN_APP_INFO set data_status='99' where id = ? ", id)
						.executeUpdate();
				// 2、写入日志
				logService.setLogs(id, "作废微信应用配置", "作废微信应用配置", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 启用微信应用配置
	 * 
	 * @param request -- 请求对象
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 上午09:59:00
	 */
	public HashMap<String, Object> enable(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids"); // 对应关系记录ID
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				WeixinAppInfo weixinAppInfo = weiXinAppInfoDao.get(temp[i]);
				// 数据状态（0：启用 98：停用 99：已作废）
				weixinAppInfo.setData_status("0");
				weixinAppInfo.setMdy_user_id(user.getId()); // 最后修改人id
				weixinAppInfo.setMdy_user_name(user.getName()); // 最后修改人姓名
				weixinAppInfo.setMdy_date(new Date()); // 最后修改时间
				weiXinAppInfoDao.save(weixinAppInfo);

				// 记录日志
				logService.setLogs(temp[i], "启用微信应用配置", "启用微信应用配置", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}

	/**
	 * 停用微信应用配置
	 * 
	 * @param request -- 请求对象
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 上午10:04:00
	 */
	public HashMap<String, Object> disable(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids"); // 对应关系记录ID
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				WeixinAppInfo weixinAppInfo = weiXinAppInfoDao.get(temp[i]);
				// 数据状态（0：启用 98：停用 99：已作废）
				weixinAppInfo.setData_status("98");
				weixinAppInfo.setMdy_user_id(user.getId()); // 最后修改人id
				weixinAppInfo.setMdy_user_name(user.getName()); // 最后修改人姓名
				weixinAppInfo.setMdy_date(new Date()); // 最后修改时间
				weiXinAppInfoDao.save(weixinAppInfo);

				// 记录日志
				logService.setLogs(temp[i], "停用微信应用配置", "停用微信应用配置", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
}
