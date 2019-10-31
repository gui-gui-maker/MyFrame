package com.lsts.org.web;


import java.util.Date;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.service.EnterService;

/**
 * 单位信息信息管理 web controller
 * 
 * @author 肖慈边 2014-1-21
 */

@Controller
@RequestMapping("enter/basic")
public class EnterAction extends
		SpringSupportAction<EnterInfo, EnterService> {


	
	@Autowired
	private EnterService enterService;
	@Autowired
	private DeviceService deviceService;

	//保存单位基础信息
		@RequestMapping(value = "saveBasic")
		@ResponseBody
		public HashMap<String, Object> saveBasic(EnterInfo entity,HttpServletRequest request)
				throws Exception {

			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User)curUser.getSysUser();
			Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
				//设置设备状态为可使用
//				entity.setCom_status("01");
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try{
				
				entity.setCreated_date(new Date());
				
				entity.setSend_status("0");
				
				entity.setCreated_by(emp.getName());
				
				enterService.save(entity);
				
				//把单位类型放入中间表
				String com_id = entity.getId();
				String com_type = entity.getCom_type();
				
				enterService.saveRelation(com_id,com_type);
					
					wrapper.put("success", true);
					wrapper.put("id",entity.getId());
					wrapper.put("com_name",entity.getCom_name());
					wrapper.put("com_address",entity.getCom_address());
					
				}catch(Exception e){
					log.error("保存单位信息：" + e.getMessage());
					wrapper.put("success", false);
					wrapper.put("message", "保存单位信息出错！");
					e.printStackTrace();	
				}
			
			return wrapper;
		}
	
	@RequestMapping(value = "getDetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id, HttpServletRequest request) throws Exception {  
		EnterInfo enterInfo = enterService.get(id);
		List<DeviceDocument>  deviceList = deviceService.queryDevicesByComId(id);		
		enterInfo.setDeviceList(deviceList);
        return JsonWrapper.successWrapper(enterInfo);
    }


		// 删除
	    @RequestMapping(value = "del")
	    @ResponseBody
	    public HashMap<String, Object> del(String ids) throws Exception {
	    	
	    	enterService.del(ids);
	        return JsonWrapper.successWrapper(ids);
	    }
		/**
		 * 根据组织结构代码查询单位信息
		 * @param code
		 * @return
		 */
	    @RequestMapping(value = "getEnterByCode")
	    @ResponseBody
		public HashMap<String, Object>  getEnterByCode(String code){
	    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				   EnterInfo enterinfo= enterService.queryEnterByCode(code);
				   if(enterinfo==null){
					   wrapper.put("success", false);
						wrapper.put("message", "没有获取到单位信息！");
				   }else{
					   wrapper.put("content", enterinfo);
					wrapper.put("success", true);
				   }
			} catch (Exception e) {
				log.error("获取单位信息：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取单位信息出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		/**
		 * 根据主键查询单位信息
		 * @param code
		 * @return
		 */
	    @RequestMapping(value = "getEnterById")
	    @ResponseBody
		public HashMap<String, Object>  getEnterById(String id){
	    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				   EnterInfo enterinfo= enterService.queryEnterById(id);
				   wrapper.put("content", enterinfo);
					wrapper.put("success", true);
			} catch (Exception e) {
				log.error("获取单位信息：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取单位信息出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
    
	    
	    /**
		 * 根据组织机构代码查询信息
		 * @param code
		 * @return
		 */
	    @RequestMapping(value = "getInfoByOrgID")
	    @ResponseBody
		public HashMap<String, Object>  getInfoByOrgID(HttpServletRequest request){
	    	String orgId=request.getParameter("id");
	    //	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
	    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				wrapper= enterService.getInfoByOrgID(orgId);
				wrapper.put("success", true);
			} catch (Exception e) {
				log.error("获取单位信息：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "没有相应的组织机构代码信息！");
				e.printStackTrace();
			}
			
			return wrapper;
		}
}
