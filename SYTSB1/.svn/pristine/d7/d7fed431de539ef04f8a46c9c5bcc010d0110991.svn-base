package com.lsts.weixin.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.weixin.bean.VoteRoundOne;
import com.lsts.weixin.service.VoteRoundOneService;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("voteRoundOneAction")
public class VoteRoundOneAction extends SpringSupportAction<VoteRoundOne, VoteRoundOneService> {
	@Autowired 
	private VoteRoundOneService voteRoundOneService ;
	
	
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
			voteRoundOneService.saveRoundOne(userId,name,phone, str_result);
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
			voteRoundOneService.reSetRoundOne(userId);
		} catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("111");
		}
		return JsonWrapper.successWrapper("111");
	}
	
	@RequestMapping(value = "checkVoted")
    @ResponseBody
	public HashMap<String, Object> checkVoted(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		try {
			int num = voteRoundOneService.checkVoted(userId);
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
		int num = voteRoundOneService.checkVoted((String)session.getAttribute("Userid"));
		if(num==0){
			//System.out.println("-----------------vote");
			return "app/weixin/index";
		} else {
			Map<String,Object> map = voteRoundOneService.countVote();
			request.setAttribute("resultMap", map);
			return "app/weixin/jg";
		}
	}
	
	@RequestMapping(value = "toResultPage")
	@OAuthRequired
	public String toResultPage(HttpServletRequest request,Model model){
		Map<String,Object> map = voteRoundOneService.countVote();
		request.setAttribute("resultMap", map);
		return "app/weixin/jg";
	}
	
	
	
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
		
		//后去当前投票轮次
		String step ="";
		try {
			step = voteRoundOneService.checkThisVoteRound();
		} catch (Exception e) {
			// TODO: handle exception
			Map<String,Object> map = voteRoundOneService.countVoteFinal();
			request.setAttribute("resultMap", map);
			return "app/weixin/jg_final";
		}
		
		request.setAttribute("step", step);
		//判断是否已经投票
		int num = voteRoundOneService.checkVotedFinal((String)session.getAttribute("Userid"));
		if(num==0){
			//System.out.println("-----------------vote");
			return "app/weixin/index_final";
		} else {
			Map<String,Object> map = voteRoundOneService.countVoteFinal();
			request.setAttribute("resultMap", map);
			return "app/weixin/jg_final";
		}
	}
	
	
	
	@RequestMapping(value = "checkVotedFinal")
    @ResponseBody
	public HashMap<String, Object> checkVotedFinal(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		String step = request.getParameter("step");
		try {
			int num = voteRoundOneService.checkVotedFinal(userId);
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
	
	@RequestMapping(value = "checkVotedFinalU")
    @ResponseBody
	public HashMap<String, Object> checkVotedFinalU(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String userId = request.getParameter("Userid");
		HttpSession session = request.getSession();
		String step = request.getParameter("step");
		try {
			int num = voteRoundOneService.checkVotedFinal(userId);
			if(num>0){
				map.put("success", false);
				map.put("msg", "您好，你已经参与了投票，马上为您跳转到投票结果页面。");
			} else {
				session.setAttribute("Userid", userId);
				session.setAttribute("Name", userId);
				map.put("success", true);
			}
		} catch(Exception e){
				map.put("success", false);
				map.put("msg", "您好，你已经参与了投票，马上为您跳转到投票结果页面。");
			return map;
		}
		return map;
	}
	
	
	@RequestMapping(value = "saveRoundFinal")
    @ResponseBody
	public HashMap<String, Object> saveRoundFinal(HttpServletRequest request) {
		//HashMap<String, Object> map = new 
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		String str_result = request.getParameter("result");
		String name = (String)session.getAttribute("Name");
		String phone = (String)session.getAttribute("Phone");
		String step = request.getParameter("step");
		try {
			//先判断是否投票轮数是不是当前投票轮数
			int canSave = voteRoundOneService.checkIsCanSave(step);
			if(canSave==1){
				//保存投票信息
				voteRoundOneService.saveRoundFinal(userId,name,phone, str_result,step);
			} else {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				map.put("msg", "本轮投票已经截止，本次投票信息将不会被系统计入。");
				return map;
			}
		} catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("111");
		}
		return JsonWrapper.successWrapper("111");
	}
	
	
	@RequestMapping(value = "reSetRoundFinal")
    @ResponseBody
	public HashMap<String, Object> reSetRoundFinal(HttpServletRequest request) {
		//HashMap<String, Object> map = new 
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("Userid");
		String step = (String)request.getParameter("step");
		try {
			voteRoundOneService.reSetRoundFinal(userId,step);
		} catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("111");
		}
		return JsonWrapper.successWrapper("111");
	}
	
	
	@RequestMapping(value = "toResultPageFinal")
	@OAuthRequired
	public String toResultPageFinal(HttpServletRequest request,Model model){
		Map<String,Object> map = voteRoundOneService.countVoteFinal();
		request.setAttribute("step", request.getParameter("step"));
		request.setAttribute("resultMap", map);
		return "app/weixin/jg_final";
	}
	
	@RequestMapping(value = "toResultPageScreen")
	@ResponseBody
	public HashMap<String, Object> toResultPageScreen(HttpServletRequest request,Model model){
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		//request.setAttribute("step", request.getParameter("step"));
		//request.setAttribute("resultMap", map);
		try {
			String[][] total = voteRoundOneService.countVoteByStep();
				//map.put("success", true);
				map.put("total",total);
				return JsonWrapper.successWrapper(map);
		} catch(Exception e){
				//map.put("success", false);
				//map.put("msg", "您好，你已经参与了投票，马上为您跳转到投票结果页面。");
				return JsonWrapper.failureWrapper("111");
		}
		
	}
	
	@RequestMapping("chooseStep")
	@ResponseBody
	public HashMap<String, Object> chooseStep(HttpServletRequest request,String step){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			boolean flag = voteRoundOneService.chooseStep(step);
			if(flag){
				map.put("success", true);
			}else{
				map.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	

	@RequestMapping("getStep")
	@ResponseBody
	public HashMap<String, Object> getStep(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String step = voteRoundOneService.getStep();
			String stepVoted = voteRoundOneService.getVotedByStep(step);
			map.put("step", step);
			map.put("stepVoted", stepVoted);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	
	@RequestMapping("checkUserName")
	@ResponseBody
	public HashMap<String, Object> checkUserName(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String name = request.getParameter("userName");
			map = voteRoundOneService.checkUserName(name,map);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	
	
}
