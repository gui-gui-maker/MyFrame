package com.scts.cspace.resource.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.service.TjyptResourceInfoService;

/**
 * 资源属性
 * 
 * @ClassName TjyptResourceInfoAction
 * @JDK 1.7
 * @author XCB
 * @date 2016-10-24 下午04:49:00
 */
@Controller
@RequestMapping("resourceInfo")
public class TjyptResourceInfoAction extends
		SpringSupportAction<TjyptResourceInfo, TjyptResourceInfoService> {
	

	@Autowired
	private TjyptResourceInfoService tjyptResourceInfoService;
	

	/**
	 * 修改资源名称
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceName")
	@ResponseBody
	public HashMap<String, Object> updateResourceName(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceName = request.getParameter("resourceName");//获取资源名称
			
			String file_type = request.getParameter("file_type");//获取类型
			
			tjyptResourceInfoService.updateResourceName(file_type,resourceId,resourceName);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源名称失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源自定义分类（路径）
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourcePath")
	@ResponseBody
	public HashMap<String, Object> updateResourcePath(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourcePath = request.getParameter("resourcePath");//获取资源自定义分类（路径）
			
			String file_type = request.getParameter("file_type");//获取类型
			tjyptResourceInfoService.updateResourcePath(file_type,resourceId,resourcePath);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源自定义分类（路径）失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 修改资源拥有者
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceOwnerId")
	@ResponseBody
	public HashMap<String, Object> updateResourceOwnerId(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceOwnerId = request.getParameter("resourceOwnerId");//获取资源拥有者
			
			tjyptResourceInfoService.updateResourceOwnerId(resourceId,resourceOwnerId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源拥有者失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 添加资源属性
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "addResourceAttribute")
	@ResponseBody
	public HashMap<String, Object> addResourceAttribute(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			String parentPathId=request.getParameter("parentPathId");
			String event_name = request.getParameter("event_name");//名称
			String event_date = request.getParameter("event_date");//发生时间
			String event_man = request.getParameter("event_man");//参与人员
			String event = request.getParameter("event");//事件
			String event_describe = request.getParameter("event_describe");//描述
			TjyptResourceInfo info= tjyptResourceInfoService.get(resourceId);
			info.setEvent_name(event_name);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(event_date);
			info.setResource_name(event_name);
			info.setEvent_date(date);
			info.setEvent_man(event_man);
			info.setEvent(event);
			info.setEvent_describe(event_describe);
			tjyptResourceInfoService.save(info);
			tjyptResourceInfoService.resourceShareAccept(resourceId,parentPathId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源拥有者失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源分享人
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceShareUserId")
	@ResponseBody
	public HashMap<String, Object> updateResourceShareUserId(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceShareUserId = request.getParameter("resourceShareUserId");//获取资源分享人
			
			tjyptResourceInfoService.updateResourceShareUserId(resourceId,resourceShareUserId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源分享人失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 资源分享
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "resourceShare")
	@ResponseBody
	public HashMap<String, Object> resourceShare(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String userId = request.getParameter("userId");//获取分享对象
			String spaceId = request.getParameter("spaceId");//获取分享对象
			
			
			tjyptResourceInfoService.resourceShare(resourceId,userId,request,spaceId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("分享失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 接受资源分享状态
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceShareStatus")
	@ResponseBody
	public HashMap<String, Object> updateResourceShareStatus(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String parentPathId = request.getParameter("parentPathId");//获取资源分享状态
			
			tjyptResourceInfoService.resourceShareAccept(resourceId,parentPathId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源分享状态失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源大小
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceSize")
	@ResponseBody
	public HashMap<String, Object> updateResourceSize(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceSize = request.getParameter("resourceSize");//获取资源大小
			
			tjyptResourceInfoService.updateResourceSize(resourceId,resourceSize);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源大小失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源类型
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceType")
	@ResponseBody
	public HashMap<String, Object> updateResourceType(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceType = request.getParameter("resourceType");//获取资源类型
			
			tjyptResourceInfoService.updateResourceType(resourceId,resourceType);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源类型失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 修改资源备注
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceRemark")
	@ResponseBody
	public HashMap<String, Object> updateResourceRemark(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceRemark = request.getParameter("resourceRemark");//获取资源备注
			
			tjyptResourceInfoService.updateResourceRemark(resourceId,resourceRemark);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源备注失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 设置资源隐藏
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "hiddenResource")
	@ResponseBody
	public HashMap<String, Object> hiddenResource(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			tjyptResourceInfoService.hiddenResource(resourceId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("设置资源隐藏失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 设置资源显示
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "showResource")
	@ResponseBody
	public HashMap<String, Object> showResource(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			tjyptResourceInfoService.showResource(resourceId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("设置资源显示失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源重要等级
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceImportantFlag")
	@ResponseBody
	public HashMap<String, Object> updateResourceImportantFlag(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceImportantFlag = request.getParameter("resourceImportantFlag");//获取资源重要等级
			
			tjyptResourceInfoService.updateResourceImportantFlag(resourceId,resourceImportantFlag);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源重要等级失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源自定义排序
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceOrderNo")
	@ResponseBody
	public HashMap<String, Object> updateResourceOrderNo(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceOrderNo = request.getParameter("resourceOrderNo");//获取资源自定义排序
			
			tjyptResourceInfoService.updateResourceOrderNo(resourceId,resourceOrderNo);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源自定义排序失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源访问验证密码
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceAccessPassword")
	@ResponseBody
	public HashMap<String, Object> updateResourceAccessPassword(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceAccessPassword = request.getParameter("resourceAccessPassword");//获取资源访问验证密码
			
			tjyptResourceInfoService.updateResourceAccessPassword(resourceId,resourceAccessPassword);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源访问验证密码失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 设置资源在回收站
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "resourceInRecyclebin")
	@ResponseBody
	public HashMap<String, Object> resourceInRecyclebin(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			tjyptResourceInfoService.resourceInRecyclebin(resourceId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("设置资源在回收站失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 设置资源不在回收站
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "resourceOutRecyclebin")
	@ResponseBody
	public HashMap<String, Object> resourceOutRecyclebin(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			tjyptResourceInfoService.resourceOutRecyclebin(resourceId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("设置资源不在回收站失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改资源状态
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateResourceStatus")
	@ResponseBody
	public HashMap<String, Object> updateResourceStatus(HttpServletRequest request
			)  {
		
		try {	
			
			String resourceId = request.getParameter("id");//获取资源对象ID
			
			String resourceStatus = request.getParameter("resourceStatus");//获取资源状态
			
			tjyptResourceInfoService.updateResourceStatus(resourceId,resourceStatus);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改资源状态失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	
	@ResponseBody
	@RequestMapping("queryResource")
	public HashMap<String, Object>  queryResource(String name,String spaceType){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryResource(name,spaceType);
			wrapper.put("name", name);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	@ResponseBody
	@RequestMapping("queryRecycle")
	public HashMap<String, Object>  queryRecycle(String spaceType){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryRecycle(spaceType);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 预览下载日志
	 *@author PingZhou
	 *@param spaceType
	 *@return
	 */
	@ResponseBody
	@RequestMapping("openDownLog")
	public HashMap<String, Object>  openDownLog(String logType,HttpServletRequest request,String file_id,String file_type){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			 tjyptResourceInfoService.openDownLog(logType,file_id,file_type,request);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	@ResponseBody
	@RequestMapping("searchResouceDb")
	public HashMap<String, Object>  searchResouceDb(String name,HttpServletRequest request,String path,String type){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			List list = tjyptResourceInfoService.searchResouceDbPath(name,path,type);
			wrapper.put("queryInfo", list);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 下载中心搜索
	 * author pingZhou
	 * @param name
	 * @param spaceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryDownResource")
	public ModelAndView  queryDownResource(String name,String spaceType,String mo){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryDownResource(name,spaceType);
			wrapper.put("name", name);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		String view = "app/cloud_platform/down/search_result_list";
		if(mo!=null){
			view = "app/cloud_platform/down/search_result_list_mo";
		}
		
		return new ModelAndView(view,wrapper);
	}
	
	/**
	 * 外网获取下载中心文件
	 * author pingZhou
	 * @param name
	 * @param spaceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryDownFile")
	public ModelAndView  queryDownFile(String spaceType,String name){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryDownFile(spaceType,name);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return new ModelAndView("app/cloud_platform/down/down_file_list",wrapper);
	}
	
	
	/**
	 * 下载中心搜索 另外一个系统只取数据
	 * author pingZhou
	 * @param name
	 * @param spaceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryDownResourceW")
	public HashMap<String, Object>  queryDownResourceW(String name,String spaceType){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryDownResource(name,spaceType);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 外网获取下载中心文件 另外一个系统只取数据
	 * author pingZhou
	 * @param name
	 * @param spaceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryDownFileW")
	public HashMap<String, Object>  queryDownFileW(String spaceType,String name){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		try {
			wrapper = tjyptResourceInfoService.queryDownFile(spaceType,name);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	
}