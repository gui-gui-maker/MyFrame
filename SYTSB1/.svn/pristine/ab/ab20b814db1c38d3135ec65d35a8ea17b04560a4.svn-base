package com.lsts.office.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
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
import com.lsts.employee.service.EmployeesService;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.service.WeightyTaskManager;
import com.lsts.office.service.YwhbsgzManager;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("weighty/Task")
public class WeightyTaskAction extends SpringSupportAction<WeightyTask, WeightyTaskManager> {

    @Autowired
    private WeightyTaskManager  weightyTaskManager;
    @Autowired
	private EmployeesService employeesService;
    @Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
    /**
     * 下发并保存状态
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "taskSend")
	@ResponseBody
	public HashMap<String, Object> taskSend(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			WeightyTask weightyTask = weightyTaskManager.get(id); //得到ID
			if(weightyTask!=null){
				weightyTaskManager.taskSend(weightyTask); //任务下发
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			}
		}
		return map;
	}
    
    /**
	 * PC端已接收任务并更新状态
	 * @param ids
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "pcReceive")
	@ResponseBody
	public HashMap<String, Object> pcReceive(HttpServletRequest request,String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			WeightyTask weightyTask = weightyTaskManager.get(id);
			if(weightyTask!=null){
				weightyTaskManager.receive(request,weightyTask);
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			}
		}
		return map;
	}
    
    /**
	 * 微信端已接收任务并更新状态
	 * @param ids
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "receive")
	@ResponseBody
	public HashMap<String, Object> receive(HttpServletRequest request,String eId,String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			if(eId.isEmpty()){
				map.put("success", false);
				map.put("msg", "人员ID为空！");
			}else{
				WeightyTask weightyTask = weightyTaskManager.get(id);
				Employee employee = employeesService.get(eId);
				if(weightyTask!=null&&employee!=null){
					weightyTaskManager.WXreceive(request,weightyTask,employee);
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
		}
		return map;
	}
    /**
     * 保存导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveTask")
	@ResponseBody
	public HashMap<String,Object> saveTaskData(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		try {
			String repData = weightyTaskManager.saveTaskData(files,getCurrentUser());
			data.put("success", true);
	    	data.put("repData",repData);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	/**
	 * 保存信息 并遍历部门信息获得ID
	 * @param request
	 * @param weightyTask
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value = "saveWei")
	@ResponseBody
    public HashMap<String,Object> saveWei(HttpServletRequest request, @RequestBody WeightyTask weightyTask) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
	    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	    	weightyTaskManager.saveWei(weightyTask, user);
	    	return JsonWrapper.successWrapper(weightyTask);
	    	/*map.put("success", true);
	    	map.put("data", weightyTask);
	    	return map;*/
    }
    
    
    @RequestMapping(value="delete")
    @ResponseBody
    @Override
    public HashMap<String, Object> delete(String ids){
    	weightyTaskManager.delete(ids);
    	return JsonWrapper.successWrapper();
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
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+result.getObj();
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
            	String id = null;
            	try {
					id = this.suggest("18750939263");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	session.setAttribute("id", id);
                //session.setAttribute("AccessToken", "");
            }
        }
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return "app/weixin/task/zdrw_list";
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