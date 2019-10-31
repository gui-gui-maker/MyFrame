package com.fwxm.recipients.web;

import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.recipients.service.Tjy2ChLqManager;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信端处理领取申请
 */
@Controller
@RequestMapping("/chlq/wx")
public class Tjy2ChLqWexinAction extends
        SpringSupportAction<Tjy2ChLq, Tjy2ChLqManager> {
    @Autowired
    private Tjy2ChLqManager tjy2ChLqManager;
    @Autowired
    ActivityManager activityManager;
    @Autowired
    UserDao userDao;
    @Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;

    @RequestMapping({"saveAndSubmit"})
    @ResponseBody
    public HashMap<String, Object> usualSave(HttpServletRequest request, @RequestBody Tjy2ChLq entity) throws Exception {
        synchronized (this) {
            return tjy2ChLqManager.saveAndSubmit(request, entity);
        }
    }

    @RequestMapping(value = "getFlowStep")
    @ResponseBody
    public HashMap<String, Object> getFlowStep(HttpServletRequest request)
            throws Exception {
        Map<String, Object> map = tjy2ChLqManager.getFlowStep(request.getParameter("id"));
        return JsonWrapper.successWrapper(map);
    }

    @RequestMapping(value = "shbtg")
    @ResponseBody
    public HashMap<String, Object> shbtg(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.shbtg(request);
    }

    @RequestMapping(value = "shtg")
    @ResponseBody
    public HashMap<String, Object> shtg(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.shtg(request);
    }

    @RequestMapping(value = "weixinUserInfo")
    @OAuthRequired
    public String weixinUserInfo(HttpServletRequest request, Model model) {
        System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("code = " + request.getParameter("code")); //微信接口会传过来的东西。
        System.out.println("businessId = " + request.getParameter("businessId")); //业务id
        System.out.println("processId = " + request.getParameter("processId")); //业务id
        System.out.println("activityId = " + request.getParameter("activityId")); //业务id
        HttpSession session = request.getSession();
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User start");
        if (session.getAttribute("Userid") == null || ((String) session.getAttribute("Userid")).equals("noUserId")) {
            String token = WxUtil.getAccessTokenString();
            log.debug(this.getClass().getName() + " method weixinUserInfo 获取到code："+request.getParameter("code"));
        	log.debug(this.getClass().getName() + " method weixinUserInfo 获取到token："+token);
            if (StringUtil.isNotEmpty(token) && request.getParameter("code") != null) {
                Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + result.getObj();
                log.debug(this.getClass().getName() + " method weixinUserInfo menuUrl："+menuUrl);
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
            } else {
                session.setAttribute("Userid", "noUserId");
                session.setAttribute("Name", "noUserName");
                session.setAttribute("Phone", "noUserPhone");
            }
        }
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User end");
        session.setAttribute("businessId", request.getParameter("businessId"));
        session.setAttribute("businessStatus", request.getParameter("businessStatus"));
        session.setAttribute("processId", request.getParameter("processId"));
        session.setAttribute("activityId", request.getParameter("activityId"));
        model.addAttribute("Userid", session.getAttribute("Userid"));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + session.getAttribute("Userid"));
        return "app/fwxm/recipients/wx/ch_wx_lq_index";
    }

    /**
     * 获取待办理
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "querycheck")
    @ResponseBody
    public HashMap<String, Object> queryCheck(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.queryCheck(request);
    }

    @RequestMapping(value = "querychecked")
    @ResponseBody
    public HashMap<String, Object> queryChecked(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.queryChecked(request);
    }

    @RequestMapping(value = "querymy")
    @ResponseBody
    public HashMap<String, Object> queryCheckMy(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.queryCheckMy(request);
    }

    /**
     * 检查是否可处理
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "chekcCanProcess")
    @ResponseBody
    public HashMap<String, Object> checkCanProcess(HttpServletRequest request) throws Exception {
		
        //String activityId = request.getParameter("activityId");
        String serviceId = request.getParameter("serviceId");
        //String createorgId = request.getParameter("createorgId");
        Tjy2ChLq lq=tjy2ChLqManager.get(serviceId);
        lq.getCreateOrgId();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        List<Map<String, Object>> list= tjy2ChLqManager.getFlowByserviceIdAndHandlerId(serviceId,user.getId());
        for (Map<String, Object> map : list) {
        	
        	if(user.getId().equals(map.get("HANDLER_ID").toString())){
        		return JsonWrapper.successWrapper("");
        	}
		}
//        List<Activity> activitys = activityManager.getCurrentActivity(serviceId);
//        CurrentSessionUser user = SecurityUtil.getSecurityUser();
//        for (Activity activity : activitys) {
//            if (activity.getId().equals(activityId)) {
//            	//环节参与者
//                List<BpmUser> list = activityManager.getBpmUserPaticipator(activityId);
//                for (BpmUser u : list) {
//                    if (u.getId().equals(user.getId())) {
//                    	return JsonWrapper.successWrapper("");
//                    }
//                }
//                User users =null;
//                if ("100025".equals(createorgId) || "100042".equals(createorgId)) {
//            		//部门100025(财务管理部),100042(四川省特种设备检验检测协会)的部门负责人是孙宇艺
//                	users = userDao.findLoginUser("孙宇艺", "1");
//            	}else if ("100030".equals(createorgId)) {
//                    //部门100030（科研技术管理部）的部门负责人是韩绍义
//            		users = userDao.findLoginUser("韩绍义", "1");
//            	}else if("100044".equals(createorgId)){
//                	//100044(司法鉴定中心)的部门负责人是李山桥
//            		users = userDao.findLoginUser("李山桥", "1");
//            	}
//                if(users!=null && user.getId().equals(users.getId())){
//                	return JsonWrapper.successWrapper("");
//                }
//                
//            }
//        }
    	return JsonWrapper.failureWrapper();
    }
}
