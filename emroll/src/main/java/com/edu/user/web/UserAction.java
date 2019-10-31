package com.edu.user.web;



import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edu.business.bean.Enroll;
import com.edu.business.service.EnrollService;
import com.edu.user.domain.User;
import com.edu.user.service.UserService;


@Controller
public class UserAction {
	@Autowired
	private UserService userService;
	
    @RequestMapping(value = {"/index","/"})
    public String index(Model model){
        return "/pages/index";
    }
    
    @RequestMapping(value = "toLogin")
    @ResponseBody
    public Map<String,Object> toLogin(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("result", "0");
    	map.put("msg", "toLogin");
    	return map;
    }
    
    @RequestMapping(value = "toUserList")
    public String toUserList(){
    	return "user/userList";
    }
    
    @RequestMapping(value = "userList")
    @ResponseBody
    public HashMap<String,Object> userlist(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,User user){
    	HashMap<String,Object> map = new HashMap<>();
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(page-1, rows, sort);
		Page<User> users;
		try {
			users = userService.findByCondition(user,pageable);
			map.put("total",users.getTotalElements());
			map.put("rows",users.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total",0);
			map.put("rows",null);
		}
    	return map;
    }
    
    @RequiresPermissions("user:add")
    @RequestMapping(value = "userAdd")
    @ResponseBody
    public HashMap<String,Object> userAdd(){
    	HashMap<String,Object> map = new HashMap<>();
    	
    	return map;
    }
    @RequiresPermissions("user:update")
    @RequestMapping(value = "userUpdate")
    @ResponseBody
    public HashMap<String,Object> userUpdate(){
    	HashMap<String,Object> map = new HashMap<>();
    	
    	return map;
    }
    
    @RequestMapping(value = "noAuth")
    public String toAuth(Model model){
    	
    	return "noAuth";
    }
    
    @RequestMapping(value = "login")
    public String login(String username,String password,Model model){
    	//1.获取Subjct
    	Subject subject = SecurityUtils.getSubject(); 
    	//2.封装用户数据
    	UsernamePasswordToken token = new UsernamePasswordToken(username,password);
    	//3.执行登陆方法
    	try {
    		subject.login(token);
    		model.addAttribute("username",username);
			//登陆成功
    		return "redirect:/index";
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			//登陆失败
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch(IncorrectCredentialsException e) {
			e.printStackTrace();
			//登陆失败
			model.addAttribute("msg","密码错误");
			return "login";
		}
    	
    }
    /**
     * 登录方法
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(User userInfo) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassWord());
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }


}
