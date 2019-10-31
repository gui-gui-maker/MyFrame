package com.lsts.weixin.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.weixin.bean.VoteFavoriteIco;
import com.lsts.weixin.service.VoteFavoriteIcoManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** 
 * @author 作者 QuincyXu
 * @JDK 1.6
 * @version 创建时间：2016年8月13日10:02:11
 * 类说明 
 */
@Controller
@RequestMapping("voteFavoriteIcoAction")
public class VoteFavoriteIcoAction extends SpringSupportAction<VoteFavoriteIco, VoteFavoriteIcoManager> {

    @Autowired
    private VoteFavoriteIcoManager  voteFavoriteIcoManager;
	
    
    /**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "wUserInfoFinal")
	@OAuthRequired
	public String loadFinal(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        HttpSession session = request.getSession();
        //System.out.println("1111111111111111111111111111111111111");
        if(session.getAttribute("Userid")==null || ((String)session.getAttribute("Userid")).equals("noUserId")) {
           AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);
            if (accessToken != null && accessToken.getToken() != null && request.getParameter("code")!=null) {
            	//System.out.println("-----------------------------in--------------");
                Result<String> result = WxUtil.oAuth2GetUserByCode(accessToken.getToken(), request.getParameter("code"), 7);
                //System.out.println("result=" + result);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken.getToken()+"&userid="+result.getObj() ;
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                //jo.
                if(jo!=null){
                	session.setAttribute("Name", jo.getString("name"));
                	session.setAttribute("Phone", jo.getString("mobile"));

                    System.out.println("------------------------Name is :"+jo.getString("name"));
                    System.out.println("------------------------Phone is :"+jo.getString("mobile"));
                }
                session.setAttribute("Userid", result.getObj());
                //session.setAttribute("AccessToken", accessToken.getToken());
                
            } else {
            	//System.out.println("-----------------------------out--------------");
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
                //session.setAttribute("AccessToken", "");
            }
        }
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		//判断是否已经投票
		int num = voteFavoriteIcoManager.checkVotedFinal((String)session.getAttribute("Userid"));
		if(num==0){
			//System.out.println("-----------------vote");
			return "app/weixin/index_favoriteico";
		} else {
			Map<String,Object> map = voteFavoriteIcoManager.countVoteFinal();
			request.setAttribute("resultMap", map);
			return "app/weixin/jg_favoriteico";
		}
	}
	
	
	@RequestMapping(value = "saveRoundOne")
    @ResponseBody
	public HashMap<String, Object> saveRoundOne(HttpServletRequest request) {
		//HashMap<String, Object> map = new 
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		String str_result = request.getParameter("result");
		String name = (String)session.getAttribute("Name");
		String phone = (String)session.getAttribute("Phone");
		
		try {
			voteFavoriteIcoManager.saveRoundOne(userId,name,phone, str_result);
		} catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("111");
		}
		return JsonWrapper.successWrapper("111");
	}
	

	@RequestMapping(value = "reSetRoundOne")
    @ResponseBody
	public HashMap<String, Object> reSetRoundOne(HttpServletRequest request) {
		//HashMap<String, Object> map = new 
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		try {
			voteFavoriteIcoManager.reSetRoundOne(userId);
		} catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("111");
		}
		return JsonWrapper.successWrapper("111");
	}
	

	@RequestMapping(value = "checkVotedFinal")
    @ResponseBody
	public HashMap<String, Object> checkVotedFinal(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		try {
			int num = voteFavoriteIcoManager.checkVotedFinal(userId);
			if(num>0){
				map.put("success", false);
				map.put("msg", "您好，你已经参与了投票，马上为您跳转到投票结果页面。");
			} else {
				map.put("success", true);
			}
		} catch(Exception e){
				map.put("success", false);
				map.put("msg", "您好，你已经参与了投票，马上为您跳转到投票结果页面。");
			return map;
		}
		return map;
	}
	
	@RequestMapping(value = "toResultPageFinal")
	@OAuthRequired
	public String toResultPageFinal(HttpServletRequest request,Model model){
		Map<String,Object> map = voteFavoriteIcoManager.countVoteFinal();
		request.setAttribute("resultMap", map);
		return "app/weixin/jg_favoriteico";
	}
}