package com.lsts.weixin.web;

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
import com.lsts.org.service.EnterSearchService;
import com.lsts.statistics.service.AnalyseService;

import net.sf.json.JSONObject;
/**
 * 微信年度统计
 * @author pingZhou
 *
 */
@Controller
@RequestMapping("weiXinAnnualSta")
public class WeiXinAnnualStaAction {

	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private EnterSearchService enterSearchService;
	@Autowired
	private WxLeaveManager wxLeaveManager;
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "weiXinStaQuery")
	@OAuthRequired
	public String weiXinContractQuery(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        HttpSession session = request.getSession();
        String view = "app/statistics/weixin/annual-statistical";
        String code = "co597feed934c32f4ec4b5d1363a8475";
        if(request.getParameter("code")!=null){
        	code = request.getParameter("code").toString();
        }
        //System.out.println("1111111111111111111111111111111111111");
        String userName  = request.getParameter("userName");
         AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);
           	System.out.println("-----------------accessToken:--------"+accessToken);
         if(userName==null){
           	
            if (accessToken != null && accessToken.getToken() != null && code!=null) {
            	System.out.println("-----------------------------in--------------");
                Result<String> result = WxUtil.oAuth2GetUserByCode(accessToken.getToken(), code, 7);
                //System.out.println("result=" + result);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken.getToken()+"&userid="+result.getObj() ;
                System.out.println("---------token---------"+accessToken.getToken());
                System.out.println("---------userId---------"+result.getObj());
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                System.out.println("---------jo---------"+jo.toString());
                //jo.
                if(jo!=null){
	                	if(jo.getInt("errcode")==60111){
	                		view = "app/statistics/weixin/no_user";
	                	}else{
	                		session.setAttribute("Name", jo.getString("name"));
	                    	session.setAttribute("Phone", jo.getString("mobile"));
	                    	List<Employee> emps = null;
	                    	try {
	                    		//根据phone获取user
	    						emps = employeesService.sugguest(jo.getString("mobile"));
	    						if(emps!=null&&emps.size()>0){
	    		                	Employee emp = emps.get(0);
	    		                	String id  = emp.getId();
	    		                	User u = wxLeaveManager.getUser(id);
	    		                	String username = u.getAccount();
	    		                	String password = u.getPassword();
	    		                	request.setAttribute("username", username);
	    		                	request.setAttribute("password", password);
	    		                	request.setAttribute("empIdCard", emp.getIdNo());
	    		                	request.setAttribute("name", username);
	    		                	String userId = u.getId();
	    		                	request.setAttribute("data",enterSearchService.annualStaCountByUser("2016-01-01", "2016-12-31", userId, request));
	    						}else{
	    							view = "app/statistics/weixin/no_user";
	    						}
	                    	} catch (IllegalArgumentException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    						view = "app/statistics/weixin/no_user";
	    					} catch (IllegalAccessException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    						view = "app/statistics/weixin/no_user";
	    					}
	                    	
	                    	
	                        System.out.println("------------------------Name is :"+jo.getString("name"));
	                        System.out.println("------------------------Phone is :"+jo.getString("mobile"));
	                	}
                	
					
                }else{
                	view = "app/statistics/weixin/no_user";
                }
                session.setAttribute("Userid", result.getObj());
                //session.setAttribute("AccessToken", accessToken.getToken());
                
            	}else{
            		view = "app/statistics/weixin/no_user";
            	}
           	}else {
            	//System.out.println("-----------------------------out--------------");
            	try {
	            	if(userName!=null){
	            		List<Employee> emps;
						
							emps = employeesService.sugguest(userName);
						
						if(emps!=null){
		                	Employee emp = emps.get(0);
		                	String id  = emp.getId();
		                	User u = wxLeaveManager.getUser(id);
		                	String username = u.getAccount();
		                	String password = u.getPassword();
		                	request.setAttribute("username", username);
		                	request.setAttribute("password", password);
		                	request.setAttribute("empIdCard", emp.getIdNo());
		                	request.setAttribute("name", username);
		                	String userId = u.getId();
		                	request.setAttribute("data",enterSearchService.annualStaCountByUser("2016-01-01", "2016-12-31", userId, request));
						}else{
							view = "app/statistics/weixin/no_user";
						}
	            	}
            	} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					view = "app/statistics/weixin/no_user";
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					view = "app/statistics/weixin/no_user";
				}
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
                //session.setAttribute("AccessToken", "");
            }
         request.setAttribute("preUrl",request.getHeader("Referer"));
        System.out.println("-----------------"+ request.getHeader("Referer")+"=========");
       /* String userId = "402883a047873b7101478b1f338c011b";
    	request.setAttribute("data",enterSearchService.annualStaCountByUser("2016-01-01", "2016-12-31", userId, request));
	*/
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return view;
	}
}
