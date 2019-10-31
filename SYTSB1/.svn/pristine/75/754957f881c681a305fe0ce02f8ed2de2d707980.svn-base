package com.lsts.weixin.web;

import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 需要验证OAuth2控制器
 * *
 */
@Controller
@RequestMapping("weixin")
public class WeixinUserAction {
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "wUserInfo")
	@OAuthRequired
	public String load(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        HttpSession session = request.getSession();
        if(session.getAttribute("Userid")==null) {
           AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);
            if (accessToken != null && accessToken.getToken() != null && request.getParameter("code")!=null) {
                Result<String> result = WxUtil.oAuth2GetUserByCode(accessToken.getToken(), request.getParameter("code"), 7);
                System.out.println("result=" + result);
                session.setAttribute("Userid", result.getObj());
            }
        } else {
        	
        }
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return "app/weixin/index";
	}
}
