package com.scts.cspace.space.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.TS_Util;

import com.alibaba.fastjson.JSONArray;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.service.TjyptResourceSpaceService;

/**
 * 资源空间属性
 * 
 * @ClassName TjyptResourceSpaceAction
 * @JDK 1.7
 * @author XCB
 * @date 2016-10-24 下午04:49:00
 */
@Controller
@RequestMapping("resourceSpace")
public class TjyptResourceSpaceAction extends
		SpringSupportAction<TjyptResourceSpace, TjyptResourceSpaceService> {

	@Autowired
	private TjyptResourceSpaceService tjyptResourceSpaceService;
	

	/**
	 * 初始化个人资源空间
	 * 
	 * @param
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "initPersonalSpace")
	@ResponseBody
	public HashMap<String, Object> initPersonalSpace() {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			wrapper = tjyptResourceSpaceService.initPersonalSpace();
			
			wrapper.put("success", "true");
		

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("初始化个人资源空间失败，请重试！");
		}

		return wrapper;
	}

	/**
	 * 打开个人资源空间
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "openPersonalSpace")
	@ResponseBody
	public HashMap<String, Object> openPersonalSpace(HttpServletRequest request) throws Exception {
		CurrentSessionUser user  = SecurityUtil.getSecurityUser();
        
		String userId = request.getParameter("userId");// 获取用户ID

		String spaceType = request.getParameter("spaceType");// 空间类型
		
		if(spaceType.equals("1")){
			TjyptResourceSpace space = tjyptResourceSpaceService.openPersonalSpace(
					user.getId(), spaceType);
			if(space==null){
				initPersonalSpace();
				space = tjyptResourceSpaceService.openPersonalSpace(
						user.getId(), spaceType);
				return queryResourceInfo(space.getId());
			}else{
				
				return queryResourceInfo(space.getId());
			}
		}else if(spaceType.equals("9")){//院资源空间
			return queryResourceInfo("100000");
		}else if(spaceType.equals("7")){//部门资源空间
			return queryResourceInfo(TS_Util.getCurOrg(user).getId());
		}else if(spaceType.equals("5")){//群组资源空间
			return queryResourceInfo("");
		}else{
			return null;
				
		}
		

	}

	/**
	 * 查询空间资源
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */

	public HashMap<String, Object> queryResourceInfo(String spaceId) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			wrapper=tjyptResourceSpaceService.queryResourceInfo(spaceId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询空间资源出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "查询空间资源出错！");
		}

		return wrapper;
	}
	
	/**
	 * 根据类型查询空间资源
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping("queryResourceByType")
	@ResponseBody
	public HashMap<String, Object> queryResourceByType(HttpServletRequest request) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			String userId = request.getParameter("userId");// 获取用户ID

			String spaceType = request.getParameter("spaceType");// 空间类型
			
			String resourceType = request.getParameter("resourceType");// 空间资源
			
			wrapper = tjyptResourceSpaceService.queryResourceByType(
					userId, spaceType,resourceType);
			
			if(wrapper.get("flag").equals("1")){//表示没个人空间
				return initPersonalSpace();
					
				
			}else{
			   return wrapper;
			}

		} catch (Exception e) {
			e.printStackTrace();

			log.error("查询空间资源出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "查询空间资源出错！");
			return wrapper;
		}

		
	}
	/**
	 * 根据类型查询空间资源前20条
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping("queryResourceByTypes")
	@ResponseBody
	public HashMap<String, Object> queryResourceByTypes(HttpServletRequest request) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			String userId = request.getParameter("userId");// 获取用户ID

			String spaceType = request.getParameter("spaceType");// 空间类型
			
			String resourceType = request.getParameter("resourceType");// 空间资源
			
			wrapper = tjyptResourceSpaceService.queryResourceByType(
					userId, spaceType,resourceType);
			
			if(wrapper.get("flag").equals("1")){//表示没个人空间
				return initPersonalSpace();
					
				
			}else{
			   return wrapper;
			}

		} catch (Exception e) {
			e.printStackTrace();

			log.error("查询空间资源出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "查询空间资源出错！");
			return wrapper;
		}

		
	}


	/**
	 * 修改资源空间类型
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceType")
	@ResponseBody
	public HashMap<String, Object> updateSpaceType(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String spaceType = request.getParameter("spaceType");// 获取资源空间类型

			tjyptResourceSpaceService.updateSpaceType(spaceId, spaceType);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源空间类型失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源空间大小
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceMaxSize")
	@ResponseBody
	public HashMap<String, Object> updateSpaceMaxSize(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String spaceMaxSize = request.getParameter("spaceMaxSize");// 获取资源空间大小

			tjyptResourceSpaceService.updateSpaceMaxSize(spaceId, spaceMaxSize);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源空间大小失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源实际空间大小
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceUseSize")
	@ResponseBody
	public HashMap<String, Object> updateSpaceUseSize(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String spaceUseSize = request.getParameter("spaceUseSize");// 获取资源空间实际大小

			tjyptResourceSpaceService.updateSpaceUseSize(spaceId, spaceUseSize);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源实际空间大小失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源空间密码
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceAccessPassword")
	@ResponseBody
	public HashMap<String, Object> updateSpaceAccessPassword(
			HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String spaceAccessPassword = request
					.getParameter("spaceAccessPassword");// 获取资源空间密码

			tjyptResourceSpaceService.updateSpaceAccessPassword(spaceId,
					spaceAccessPassword);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("设置资源空间密码失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源空间拥有者
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceOwner")
	@ResponseBody
	public HashMap<String, Object> updateSpaceOwner(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String spaceOwner = request.getParameter("spaceOwner");// 获取资源空间拥有者ID

			tjyptResourceSpaceService.updateSpaceOwner(spaceId, spaceOwner);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("设置资源空间拥有者失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 更新访问隐藏模式密码
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceHiddenPassword2")
	@ResponseBody
	public HashMap<String, Object> updateSpaceHiddenPassword(HttpServletRequest request,String spaceId,String opassword,String password) {

		try {
			TjyptResourceSpace tjyptResourceSpace = tjyptResourceSpaceService.get(spaceId);
			//确认输入的九密码是否正确
			if(opassword!=null&&opassword.equals(tjyptResourceSpace.getSpace_hidden_password())){
				tjyptResourceSpaceService.updateSpaceHiddenPassword(spaceId, password);
			}else{
				return JsonWrapper.failureWrapperMsg("原密码不正确！");
			}
		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("更新访问隐藏模式密码失败，请重试！");
		}

		return JsonWrapper.successWrapper(111);
	}
	
	/**
	 * 更新访问隐藏模式密码
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceHiddenPassword")
	@ResponseBody
	public HashMap<String, Object> updateSpaceHiddenPassword(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String password = request.getParameter("password");// 获取资源空间拥有者ID

			tjyptResourceSpaceService.updateSpaceHiddenPassword(spaceId, password);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("更新访问隐藏模式密码失败，请重试！");
		}

		return JsonWrapper.successWrapper(111);
	}
	
	/**
	 * 设置空间为普通模式
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceNotHidden")
	@ResponseBody
	public HashMap<String, Object>  updateSpaceNotHidden(HttpServletRequest request) {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID
			
			String passWord = request.getParameter("passWord");
			
		
			wrapper = tjyptResourceSpaceService.updateSpaceHiddenType(spaceId,passWord, "0");
			
			if(wrapper.get("flag").equals("1")){
				
				return JsonWrapper.failureWrapperMsg("请输入空间访问密码，请重试！");
				
			}else if(wrapper.get("flag").equals("2")){
				return JsonWrapper.failureWrapperMsg("输入空间密码错误，请重试！");
			}else{
				return JsonWrapper.successWrapper(111);
			}

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("设置空间为隐藏模式失败，请重试！");
		}

		
	}
	
	/**
	 * 设置空间为隐藏模式
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSpaceHidden")
	@ResponseBody
	public HashMap<String, Object> updateSpaceHidden(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("id");// 获取资源空间对象ID

			String password = request.getParameter("password");// 获取资源空间拥有者ID

			//tjyptResourceSpaceService.VarifySpaceHiddenPassword(spaceId, password);
			tjyptResourceSpaceService.updateSpaceNotHidden(spaceId, "1");

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("设置空间为隐藏模式失败，请重试！");
		}

		return JsonWrapper.successWrapper(111);
	}
	
	
	/**
	 * 查询个人id返回个人空间界面 pingZhou
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "goUserSpace")
	@ResponseBody
	public ModelAndView goUserSpace(HttpServletRequest request,String spaceType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user  = SecurityUtil.getSecurityUser();
		try {
			
			if(spaceType==null||StringUtil.isEmpty(spaceType)){
				spaceType="1";
			}
			TjyptResourceSpace space = tjyptResourceSpaceService.openPersonalSpace(
					user.getId(), spaceType);

			if (space == null) { // 如果不存在，初始化空间
				 initPersonalSpace();
				 space = tjyptResourceSpaceService.openPersonalSpace(
							user.getId(), spaceType);
			}
			
			map.put("useSize", (Float.parseFloat(space.getSpace_use_size())/1024/1024));
			map.put("maxSize", (Float.parseFloat(space.getSpace_max_size())/1024/1024));
			map.put("bl", (Float.parseFloat(space.getSpace_use_size())/
					Float.parseFloat(space.getSpace_max_size())*100));
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("useSize", "0");
			map.put("maxSize","0");
			map.put("bl", 0);

		}
		map.put("userId", user.getId());
		return new ModelAndView("app/cloud_platform/owner/owner_main",map);
	}
	
	/**
	 * 检查空间密码是否正确
	 * @param userId 空间所属用户
	 * @param spaceType 空间类型
	 * @param password 空间隐藏密码
	 * @return value(正确：true,错误：false)
	 * @throws Exception
	 */
	@RequestMapping(value="checkPassWord")
	@ResponseBody
	public  HashMap<String, Object> checkPassWord(String userId,String spaceType,String password) throws Exception{
		String msg = ""; 
		TjyptResourceSpace tjyptResourceSpace = tjyptResourceSpaceService.queryResourceByUserAndType(userId, spaceType);
		//当空间为隐藏模式
		if(tjyptResourceSpace!=null){
			String oldPassWord = tjyptResourceSpace.getSpace_hidden_password();
			if(null!=oldPassWord&&oldPassWord.equals(password)){
				msg = "密码验证通过";
				return JsonWrapper.successWrapper(msg);
			}else{
				msg = "密码未验证通过";
				return JsonWrapper.failureWrapperMsg(msg);
			}
		}else{
			msg = "空间不是隐藏模式";
			return JsonWrapper.failureWrapperMsg(msg);
		}
	}
	
	/**
	 * 获取机构树节点
	 *@author PingZhou
	 *@param orgIds 机构ids
	 *@return
	 */
	@ResponseBody
	@RequestMapping("getOrgTree")
  public HashMap<String, Object> getOrgTree(String orgIds){
	  HashMap<String, Object> map = new HashMap<String, Object>();
	  try {
		  JSONArray orgs =  tjyptResourceSpaceService.getOrgTree(orgIds);
		  map.put("dataList", orgs);
		  map.put("success", true);
	} catch (Exception e) {
		e.printStackTrace();
		 map.put("success", true);
	}
	  
	  return map;
  }
	/**
	 * 查子级机构
	 *@author PingZhou
	 *@param orgId
	 *@return
	 */
	@ResponseBody
	@RequestMapping("getOrgByPid")
	 public HashMap<String, Object> getOrgByPid(String orgId){
		  HashMap<String, Object> map = new HashMap<String, Object>();
		  try {
			  List<Map<String, Object>> orgs =  tjyptResourceSpaceService.getOrgByPid(orgId);
			  map.put("orgList", orgs);
			  map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				 map.put("success", false);
				 map.put("msg", e.getMessage());
			}
		  
		  return map;
	  }
	 
	 /**
	  * 查机构下的人员
	  *@author PingZhou
	  *@param orgId
	  *@return
	  */
	@ResponseBody
	@RequestMapping("getUserByOrgid")
	 public HashMap<String, Object> getUserByOrgid(String orgId){
		  HashMap<String, Object> map = new HashMap<String, Object>();
		  try {
			  List<Map<String, Object>> users =  tjyptResourceSpaceService.getUserByOrgid(orgId);
			  map.put("userList", users);
			  map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				 map.put("success", false);
				 map.put("msg", e.getMessage());
			}
		  
		  return map;
	  }
	
	/**
	 * 查询个人id返回个人空间界面 pingZhou
	 * 
	 * @param request
	 * @param String id , String  password
	 * @throws Exception
	 */
	@RequestMapping(value = "goUserSpaceSize")
	@ResponseBody
	public HashMap<String, Object> goUserSpaceSize(HttpServletRequest request,String spaceType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user  = SecurityUtil.getSecurityUser();
		try {
			
			if(spaceType==null||StringUtil.isEmpty(spaceType)){
				spaceType="1";
			}

			TjyptResourceSpace space = tjyptResourceSpaceService.openPersonalSpace(
						user.getId(), spaceType);
			if(space==null){
				map.put("useSize", "0");
				map.put("maxSize","0");
				map.put("bl", 0);
				map.put("spaceId", null);
			}else{
				map.put("useSize", (Float.parseFloat(space.getSpace_use_size())/1024/1024));
				map.put("maxSize", (Float.parseFloat(space.getSpace_max_size())/1024/1024));
				map.put("bl", (Float.parseFloat(space.getSpace_use_size())/
						Float.parseFloat(space.getSpace_max_size())*100));
				map.put("spaceId", space.getId());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("useSize", "0");
			map.put("maxSize","0");
			map.put("bl", 0);
			map.put("success", false);
			map.put("spaceId", null);
			map.put("msg", "个人空间还没有初始化");

		}
		map.put("userId", user.getId());
		return map;
	}
	
	/**
	 * 检查空间大小
	 * author pingZhou
	 * @param request
	 * @param spaceType
	 * @return
	 */
	@RequestMapping("checkSpaceSize")
	@ResponseBody
	public HashMap<String, Object> checkSpaceSize(HttpServletRequest request,String spaceType,long size,String spaceId) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			boolean flag = tjyptResourceSpaceService.checkSpaceSize(spaceType,size,spaceId);
			wrapper.put("flag", flag);
			wrapper.put("success", true);

		} catch (Exception e) {
			e.printStackTrace();

			log.error("检查空间大小出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "检查空间大小出错！");
			
		}

		return wrapper;
	}
	
}