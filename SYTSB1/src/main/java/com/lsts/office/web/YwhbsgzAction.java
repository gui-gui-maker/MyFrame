package com.lsts.office.web;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.bcel.generic.DADD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.web.EmployeeAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.service.ClfbxdManager;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.web.EmployeeBaseAction;
import com.lsts.nk_message.bean.MobileMessage;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.bean.YwhbsgzFk;
import com.lsts.office.service.YwhbsgzManager;
import com.lsts.office.service.Ywhbsgz_fkManager;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;


/**
 * <p>
 * web控制器组件。该组件继承自泛型类SpringSupportAction，并提供了运行时的bean和manager对象。
 * 由此获得了对bean的crund操作能力，SpringSupportAction类中已经定义了对bean的 save,detail,delete方法。
 * 
 * </p>
 * <p>
 * 注解@Controller标识该类为web 控制器；
 * </p>
 * <p>
 * 注解@RequestMapping定义该控制器的web访问路径
 * </p>
 * @param <Ayou>
 */
@Controller
@RequestMapping("office/ywhbsgzAction")
public class YwhbsgzAction<Ayou> extends SpringSupportAction<Ywhbsgz, YwhbsgzManager> {

	// 必须提供Demo实体的manager对象，使用注解@Autowired标识为自动装配
	@Autowired
	private YwhbsgzManager ywhbsgzManager;
	@Autowired
	private Ywhbsgz_fkManager ywhbsgzManagerFk;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		ywhbsgzManager.delete(ids);
		return JsonWrapper.successWrapper();
	}
	
	
	public HashMap<String, Object> savesta(HttpServletRequest request,String ids,String str) throws Exception{
    	
		Ywhbsgz sgz = ywhbsgzManager.ywhbsgzid(ids);
		sgz.setStatus(str);
		
			 return super.save(request, sgz);
			
	}
	
	  
	
	    
	
	
	
	
	 @RequestMapping(value = "taskSend")
		@ResponseBody
		public HashMap<String, Object> taskSend(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Ywhbsgz ywhbsgz = ywhbsgzManager.get(id);
				if(ywhbsgz!=null){
					ywhbsgzManager.taskSend(ywhbsgz);
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
			return map;
		}
	
	 @Override
	  public HashMap<String, Object> save(HttpServletRequest request,Ywhbsgz ywhbsgz) throws Exception{
		  /*CurrentSessionUser user = SecurityUtil.getSecurityUser();
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			String ss = user.getId();
			if(user.getId().equals(ywhbsgz.getResponsiblePersonid())){
				String userid = ywhbsgzManager.getmid(ywhbsgz.getResponsiblePersonid());
			ywhbsgz.setResponsiblePersonid(userid);
			}*/
			HashMap<String, Object> map = new HashMap<String, Object>();	
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User uu = (User)curUser.getSysUser();
			com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
			String eId=e.getId();
			String eName=e.getName();
			ywhbsgz.setCreaterId(eId);
			ywhbsgz.setCreater(eName);
			ywhbsgz.setCreaterTime(new Date());
			ywhbsgzManager.save(ywhbsgz);
		    map.put("success", true);
			return map;
	    	
	    }
	 
	 
	  /**
		 * 已接收任务并更新状态
		 * @param ids
		 * @return
		 * @throws Exception
		 */
	    @RequestMapping(value = "receive")
		@ResponseBody
		public HashMap<String, Object> receive(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Ywhbsgz ywhbsgz = ywhbsgzManager.get(id);
				if(ywhbsgz!=null){
					ywhbsgzManager.receive(ywhbsgz);
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
			return map;
		}
	    
	    
	    @RequestMapping(value = "savefk")
		@ResponseBody
		public HashMap<String, Object> savefk(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Ywhbsgz ywhbsgz = ywhbsgzManager.get(id);
				if(ywhbsgz!=null){
					ywhbsgzManager.receive(ywhbsgz);
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
			return map;
		}
	    
	    
	
	@RequestMapping(value = "savebsgz")
	@ResponseBody
	public HashMap<String,Object> saveBankData(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		try {
			String repData = ywhbsgzManager.saveBankData(files,getCurrentUser());
			data.put("success", true);
	    	data.put("repData",repData);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	
	
	@RequestMapping(value = "setjs")
	@ResponseBody
	public HashMap<String,Object> setjs(String id,String eId) throws Exception {
		HashMap<String,Object> map=new HashMap<String,Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			if(eId.isEmpty()){
				map.put("success", false);
				map.put("msg", "人员ID为空！");
			}else{
				Ywhbsgz ywhbsgz = ywhbsgzManager.get(id);
				Employee employee = employeesService.get(eId);
				if(ywhbsgz!=null&&employee!=null){
					ywhbsgz.setStatus(YwhbsgzManager.BG_RWZT_YJS);
					ywhbsgzManager.WXreceive(ywhbsgz,employee);
					map.put("success", true);
					map.put("ywhbsgz", ywhbsgz);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
		}
    	return  map;
	}
	
	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "weixinUserInfo")
	@OAuthRequired
	public String weixinUserInfo(HttpServletRequest request,Model model){
		System.out.println("Load a User!");
        System.out.println("getRequestURL = " + request.getSession().getId());
        System.out.println("getRequestURL = " + request.getRequestURL());
        System.out.println("getRequestURI = " + request.getRequestURI());
        System.out.println("code = " + request.getParameter("code"));
        HttpSession session = request.getSession();
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User start");
        if(session.getAttribute("Userid")==null || ((String)session.getAttribute("Userid")).equals("noUserId")) {
        	String token = WxUtil.getAccessTokenString();
        	log.debug(this.getClass().getName() + " method weixinUserInfo 获取到code："+request.getParameter("code"));
        	log.debug(this.getClass().getName() + " method weixinUserInfo 获取到token："+token);
            if (StringUtil.isNotEmpty(token) && request.getParameter("code")!=null) {
            	Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+result.getObj() ;
                log.debug(this.getClass().getName() + " method weixinUserInfo menuUrl："+menuUrl);
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                if(jo!=null){
                	if(jo.has("mobile")) {
						User user;
						try {
							user = weixinAppInfoManager.getUser(jo.getString("mobile"));
							if(user!=null) {
								session.setAttribute("Name", user.getName());
								session.setAttribute("Phone", jo.getString("mobile"));
								session.setAttribute("Account",user.getAccount());
								session.setAttribute("id", user.getEmployee().getId());
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
                //session.setAttribute("AccessToken", accessToken.getToken());
                
            } else {
            	//System.out.println("-----------------------------out--------------");
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
            	/*String id = null;
            	try {
					id = this.suggest("18750939263");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
            	session.setAttribute("id", "noId");
            	/*session.setAttribute("Phone", "18750939263");
            	session.setAttribute("Name", "许飞");*/
                //session.setAttribute("AccessToken", "");
            }
        }
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return "app/weixin/task/gzrw_list";
	}
	

	//根据电话获取员工ID
	public String suggest(String q) throws Exception {
		String data = new String(q.getBytes("iso8859-1"),"UTF-8");
		List<Employee> list = employeesService.sugguest(data);
		/*ArrayList al = new ArrayList();
		for (Employee employee : list) {
			HashMap hm = new HashMap();
			hm.put("id", employee.getId());
			hm.put("name", employee.getName());
			hm.put("mobileTel", employee.getMobileTel());
			hm.put("orgname", employee.getOrg());
			al.add(hm);
		}*/
		String id = list.get(0).getId();
        return id;
	}
}
