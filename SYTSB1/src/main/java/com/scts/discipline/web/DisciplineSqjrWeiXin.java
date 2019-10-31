package com.scts.discipline.web;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.scts.discipline.bean.DisciplineZdsxSqjr;
import com.scts.discipline.service.DisciplineZdjrService;
import com.scts.discipline.service.DisciplineZdsxSqjrService;
import com.scts.weixin.app.service.WeixinAppInfoManager;

/**
 * 申请介入微信端
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/disciplineSqjrAction/wx/")
public class DisciplineSqjrWeiXin extends SpringSupportAction<DisciplineZdsxSqjr, DisciplineZdsxSqjrService>{
	@Autowired
	DisciplineZdsxSqjrService disciplineZdsxSqjrService;
	@Autowired
	DisciplineZdjrService disciplineZdjrService;
    @Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
    /**
	 * 流程结束
	 * @param request
	 * @return
	 */
	@RequestMapping("flowEnd")
	@ResponseBody
	public HashMap<String, Object> flowEnds(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			String type=request.getParameter("type");
			String process_id=request.getParameter("processId");
        	String bmfzryj=URLDecoder.decode(request.getParameter("bmfzryj"), "UTF-8");
        	String bmfgyyj=URLDecoder.decode(request.getParameter("bmfgyyj"), "UTF-8");
        	String jjfgyyj=URLDecoder.decode(request.getParameter("jjfgyyj"), "UTF-8");
        	DisciplineZdsxSqjr bean=disciplineZdsxSqjrService.get(id);
        	bean.setBmfzryj(bmfzryj);
        	bean.setBmfgyyj(bmfgyyj);
        	bean.setJjfgyyj(jjfgyyj);
        	disciplineZdsxSqjrService.sqjrFlowEnd(bean,type,process_id);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapper("操作失败！");
		}
	}
	
    @RequestMapping(value = "sqjrTg")
    @ResponseBody
    private HashMap<String, Object> sqjrTg(HttpServletRequest request){
    	try {
    		String id=request.getParameter("id");
        	String activity_id=request.getParameter("activity_id");
        	String bmfzryj=URLDecoder.decode(request.getParameter("bmfzryj"), "UTF-8");
        	String bmfgyyj=URLDecoder.decode(request.getParameter("bmfgyyj"), "UTF-8");
        	String jjfgyyj=URLDecoder.decode(request.getParameter("jjfgyyj"), "UTF-8");
        	DisciplineZdsxSqjr bean=disciplineZdsxSqjrService.get(id);
        	bean.setBmfzryj(bmfzryj);
        	bean.setBmfgyyj(bmfgyyj);
        	bean.setJjfgyyj(jjfgyyj);
        	disciplineZdsxSqjrService.sqjrTg(bean,activity_id);
    		return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
    }
    

    @RequestMapping(value = "chekcCanProcess")
    @ResponseBody
    public HashMap<String, Object> checkCanProcess(HttpServletRequest request) throws Exception {
        String serviceId = request.getParameter("serviceId");
        System.out.println("serviceId-----------------------"+serviceId);
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        List<Map<String, Object>> list= disciplineZdjrService.getFlowByserviceIdAndHandlerId(serviceId,user.getId(),"ZDJC_SQJR_FLOW");
        for (Map<String, Object> map : list) {
        	System.out.println("HANDLER_ID-----------------------"+map.get("HANDLER_ID"));
        	if(user.getId().equals(map.get("HANDLER_ID").toString())){
        		return JsonWrapper.successWrapper("");
        	}
		}
        return JsonWrapper.failureWrapper();
    }
    
    @RequestMapping(value = "saveAndSubmit1")
 	@ResponseBody
    public Map<String, Object> saveAndSubmit1(HttpServletRequest request,@RequestBody DisciplineZdsxSqjr entity){
    	try {
			String flowId=request.getParameter("flowId");
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			disciplineZdsxSqjrService.saveAndSubmit(user,entity,flowId);
    		return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
    }
    /**
     * 已办理
     * @param request
     * @return
     */
    @RequestMapping(value = "querychecked")
    @ResponseBody
    public Map<String, Object> querychecked(HttpServletRequest request){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	try {
    		List<Map<String, Object>> list=disciplineZdsxSqjrService.querychecked(user.getId());
    		return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
    }
    /**
     * 我的申请
     * @param request
     * @return
     */
    @RequestMapping(value = "querymy")
    @ResponseBody
    public Map<String, Object> querymy(HttpServletRequest request){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	try {
    		List<DisciplineZdsxSqjr> list=disciplineZdsxSqjrService.querymy(user.getId());
    		return JsonWrapper.successWrapper(list);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return JsonWrapper.failureWrapper();
    	}
    }
    /**
     * 待处理
     * @param request
     * @return
     */
    @RequestMapping(value = "querycheck")
    @ResponseBody
    public Map<String, Object> querycheck(HttpServletRequest request){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	try {
    		List<Map<String, Object>> list=disciplineZdsxSqjrService.querycheck(user.getId());
    		return JsonWrapper.successWrapper(list);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return JsonWrapper.failureWrapper();
    	}
    }


    @RequestMapping(value = "weixinUserInfo")
    @OAuthRequired
    public String weixinUserInfo(HttpServletRequest request, Model model) {
        System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("state = " + request.getParameter("state")); //微信接口会传过来的东西。
        System.out.println("businessId = " + request.getParameter("businessId")); //业务id
        System.out.println("processId = " + request.getParameter("process_id")); //业务id
        System.out.println("activityId = " + request.getParameter("activity_id")); //业务id
        HttpSession session = request.getSession();
        if (session.getAttribute("Userid") == null || ((String) session.getAttribute("Userid")).equals("noUserId")) {
            String token = WxUtil.getAccessTokenString();
            if (StringUtil.isNotEmpty(token) && request.getParameter("code") != null) {
                Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + result.getObj();
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
        session.setAttribute("businessId", request.getParameter("businessId"));
        session.setAttribute("state", request.getParameter("state"));
        session.setAttribute("process_id", request.getParameter("process_id"));
        session.setAttribute("activity_id", request.getParameter("activity_id"));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + session.getAttribute("Userid"));
        model.addAttribute("Userid", session.getAttribute("Userid"));
        return "app/discipline/wx/sqjr_wx_index";
    }
}
