package com.scts.weixin.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.constant.Constant;
import com.scts.weixin.app.bean.WeixinAppInfo;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;

/**
 * 微信应用基础信息业务管理控制器
 * 
 * @ClassName WeixinAppInfoAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-08-20 上午09:53:00
 */
@Controller
@RequestMapping("weixin/app/info")
public class WeixinAppInfoAction extends
		SpringSupportAction<WeixinAppInfo, WeixinAppInfoManager> {
	@Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
	
 	
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解 此方法为微信端通用请求登录方法，用于获取微信用户信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserInfo")
	@OAuthRequired
	public String getUserInfo(HttpServletRequest request, Model model)  {
		HttpSession session = request.getSession();
		String app_code = request.getParameter("app_code");
		String app_index_url = "";	// 应用首页跳转地址
		String app_deal_url = ""; 	// 业务处理响应地址

		if (StringUtil.isEmpty(app_code)) {
			session.setAttribute("error_msg", Constant.WEIXIN_APP_MSG_UNDEFINED);
			return Constant.WEIXIN_ERROR_URL;
		} else {
			WeixinAppInfo info = weixinAppInfoManager.getInfo(app_code);
			if(info == null) {
				session.setAttribute("error_msg", Constant.WEIXIN_APP_MSG_UNDEFINED);
				return Constant.WEIXIN_ERROR_URL;
			}
			app_index_url = info.getApp_index_url();
			app_deal_url = info.getApp_deal_url();
		}

		if (StringUtil.isEmpty(app_index_url)) {
			session.setAttribute("error_msg", Constant.WEIXIN_APP_MSG_NOT_INDEX_URL);
			return Constant.WEIXIN_ERROR_URL;
		}
		
		if (StringUtil.isEmpty(app_deal_url)) {
			session.setAttribute("error_msg", Constant.WEIXIN_APP_MSG_NOT_DEAL_URL);
			return Constant.WEIXIN_ERROR_URL;
		}
		log.debug(this.getClass().getName() + " method getUserInfo Load a User start");
		if (session.getAttribute("Userid") == null || ((String) session.getAttribute("Userid")).equals("noUserId")) {
			// AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID,
			// Constants.SECRET);
			String token = WxUtil.getAccessTokenString();
			log.debug(this.getClass().getName() + " method getUserInfo 获取到code："+request.getParameter("code"));
			log.debug(this.getClass().getName() + " method getUserInfo 获取到token："+token);
			if (StringUtil.isNotEmpty(token) && request.getParameter("code") != null) {
				Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
				String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid="
						+ result.getObj();
				log.debug(this.getClass().getName() + " method getUserInfo menuUrl："+menuUrl);
				JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
				if (jo != null) {
					if(jo.has("mobile")) {
						User user;
						try {
							user = weixinAppInfoManager.getUser(jo.getString("mobile"));
							if(user!=null) {
								session.setAttribute("Name", user.getName());
								session.setAttribute("Phone", jo.getString("mobile"));
								session.setAttribute("Account",user.getAccount());
								log.debug(this.getClass().getName() + " method weixinUserInfo 获取到用户信息，用户姓名："+user.getName()+"，用户手机号：" + jo.getString("mobile")+"，用户Account:"+user.getAccount());
							}else {
								session.setAttribute("error_msg", "亲，检验平台中未找到与该手机号匹配的用户信息");
								log.debug("亲，检验平台中未找到与该手机号匹配的用户信息");
								return "app/weixininfo/app_info/weixin_error_page";
							}
						} catch (Exception e) {
							log.debug(e.getMessage());
							return "app/weixininfo/app_info/weixin_error_page";
						}
					}else {
						session.setAttribute("error_msg", "亲，微信接口服务出现异常，未找到手机号");
						log.debug("亲，微信接口服务出现异常，未找到手机号");
						return "app/weixininfo/app_info/weixin_error_page";
					}
				}else {
					session.setAttribute("error_msg", "亲，微信接口服务出现异常，未获取到微信企业用户信息");
					log.debug("亲，微信接口服务出现异常，未获取到微信企业用户信息");
					return "app/weixininfo/app_info/weixin_error_page";
				}
				session.setAttribute("Userid", result.getObj());
				// session.setAttribute("AccessToken", accessToken.getToken());

			} else {
				session.setAttribute("Userid", "noUserId");
				session.setAttribute("Name", "noUserName");
				session.setAttribute("Phone", "noUserPhone");
				// session.setAttribute("Account",
				// wxLeaveManager.getAccount("18980021021"));//明子涵，本地测试用
				// session.setAttribute("Account",
				// wxLeaveManager.getAccount("13980717755"));//张展彬，本地测试用
				// session.setAttribute("Account",
				// wxLeaveManager.getAccount("18190738531"));//纪刚，本地测试用
				// session.setAttribute("Account",
				// wxLeaveManager.getAccount("13060002978"));//阳晓薇，本地测试用
				// session.setAttribute("Account",
				// wxLeaveManager.getAccount("13308036073"));//贺飞峙，本地测试用
			}
		}
		log.debug(this.getClass().getName() + " method getUserInfo Load a User end");
		session.setAttribute("businessId", request.getParameter("businessId"));
		session.setAttribute("businessStatus", request.getParameter("businessStatus"));
		session.setAttribute("app_code", app_code);
		model.addAttribute("Userid", session.getAttribute("Userid"));

		return "app/weixininfo/app_info/weixin_app_login";
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param weixinAppInfo
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-08-21 上午10:23:00
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			WeixinAppInfo weixinAppInfo) throws Exception {
		try {
			// 1、保存前先验证应用编号是否已存在，如果重复则进行提醒
			String app_info_id = weixinAppInfoManager.getAppInfo(weixinAppInfo.getApp_code());
			String status = request.getParameter("status");
			if ("add".equals(status)) {
				if (StringUtil.isNotEmpty(app_info_id)) {
					return JsonWrapper
							.failureWrapperMsg("亲，您填写的微信应用编号" + weixinAppInfo.getApp_code() + "已存在，不能重复，请重新填写！");
				}
			} else {
				if (StringUtil.isNotEmpty(app_info_id)) {
					if (!weixinAppInfo.getId().equals(app_info_id)) {
						return JsonWrapper
								.failureWrapperMsg("亲，您填写的微信应用编号" + weixinAppInfo.getApp_code() + "已存在，不能重复，请重新填写！");
					}
				}
			}

			// 2、保存
			weixinAppInfoManager.saveInfo(request, weixinAppInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(weixinAppInfo);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		WeixinAppInfo weixinAppInfo = weixinAppInfoManager.get(id);
		wrapper.put("success", true);
		wrapper.put("data", weixinAppInfo);
		return wrapper;
	}
	
	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			weixinAppInfoManager.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	/**
	 * 启用微信应用配置
	 * 
	 * @param request
	 *            -- 请求对象
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 上午09:59:00
	 */
    @RequestMapping(value = "enable")
    @ResponseBody
    public HashMap<String, Object> enable(HttpServletRequest request) throws Exception {	
        return weixinAppInfoManager.enable(request);
    }
    
    /**
	 * 停用微信应用配置
	 * 
	 * @param request
	 *            -- 请求对象
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 上午10:04:00
	 */
    @RequestMapping(value = "disable")
    @ResponseBody
    public HashMap<String, Object> disable(HttpServletRequest request) throws Exception {	
        return weixinAppInfoManager.disable(request);
    }
}
