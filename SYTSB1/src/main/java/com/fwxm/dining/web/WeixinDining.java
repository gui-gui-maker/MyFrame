package com.fwxm.dining.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.service.WxLeaveManager;

import net.sf.json.JSONObject;
/**
 * 就餐管理系统微信入口
 * @author Guido
 *
 */
@Controller
@RequestMapping("weixinDining")
public class WeixinDining {
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private WxLeaveManager wxLeaveManager;
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "diningClient")
	@OAuthRequired
	public String diningClient(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        HttpSession session = request.getSession();
        if(session.getAttribute("Userid")==null || ((String)session.getAttribute("Userid")).equals("noUserId")) {
           AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);
            if (accessToken != null && accessToken.getToken() != null && request.getParameter("code")!=null) {
                Result<String> result = WxUtil.oAuth2GetUserByCode(accessToken.getToken(), request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken.getToken()+"&userid="+result.getObj() ;
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                if(jo!=null){
                	session.setAttribute("Name", jo.getString("name"));
                	session.setAttribute("Phone", jo.getString("mobile"));
                	List<Employee> emps = null;
                	try {
                		//根据phone获取user
						emps = employeesService.sugguest(jo.getString("mobile"));
						if(emps!=null&&emps.size()>0){
		                	Employee emp = emps.get(0);
		                	session.setAttribute("employee", emp);
		                	String id  = emp.getId();
		                	User u = wxLeaveManager.getUser(id);
		                	if(u!=null){
		                		String username = u.getAccount();
		                		String password = u.getPassword();
		                		session.setAttribute("username", username);
		                		session.setAttribute("password", password);
		                	}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
                }
                session.setAttribute("Userid", result.getObj());
            } else {
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
            	session.setAttribute("employee", employeesService.get("402883a047a0374e0147a04b22220005"));
            }
        }
		model.addAttribute("Userid", session.getAttribute("Userid"));
		return "app/weixin/dining/wxdining/transform-dining-main";
		//return "app/weixin/dining/wxdining/dining-main";
	}
	
	@RequestMapping(value = "transformWindow")
	@OAuthRequired
	public String transformWindow(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		session.setAttribute("username", "dengww");
		session.setAttribute("password", "1111");
		return "app/weixin/dining/wxdining/transform-window";
	}
	@RequestMapping(value = "transformComming")
	@OAuthRequired
	public String transformComming(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		session.setAttribute("username", "dengww");
		session.setAttribute("password", "1111");
		return "app/weixin/dining/wxdining/transform-comming";
	}
}
