package com.lsts.device.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.khnt.rbac.bean.Org;
import com.lsts.device.bean.DeviceCategories;
import com.lsts.device.bean.TzsbAppConsUnit;
import com.lsts.device.bean.TzsbAppConstrucationOrg;
import com.lsts.device.bean.TzsbAppDevice;
import com.lsts.device.bean.TzsbAppDocumentFile;
import com.lsts.device.bean.TzsbAppEngineerSituations;
import com.lsts.device.bean.TzsbAppInfo;
import com.lsts.device.bean.TzsbAppOutsour;
import com.lsts.device.bean.TzsbAppSupervisionUnit;
import com.lsts.device.bean.TzsbAppWorks;
import com.lsts.device.bean.TzsbApplication;
import com.lsts.device.service.DeviceApplicationService;
import com.lsts.device.service.TzsbAppConsUnitService;
import com.lsts.device.service.TzsbAppDeviceService;
import com.lsts.device.service.TzsbAppDocumentFileService;
import com.lsts.device.service.TzsbAppOutsourService;
import com.lsts.device.service.TzsbAppSupervisionUnitService;
import com.lsts.device.service.TzsbAppWorksService;

/**
 * 
 * @author liming
 *
 */
@Controller
@RequestMapping("device/tzsbapp")
public class TzsbApplicationAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private TzsbAppConsUnitService tzsbAppConsUnitService;
	@Autowired
	private TzsbAppDeviceService tzsbAppDeviceService;
	@Autowired
	private TzsbAppDocumentFileService tzsbAppDocumentFileService;
	@Autowired
	private TzsbAppOutsourService tzsbAppOutsourService;
	@Autowired
	private TzsbAppSupervisionUnitService tzsbAppSupervisionUnitService;
	@Autowired
	private TzsbAppWorksService tzsbAppWorksService;
	@Autowired
	private DeviceApplicationService deviceApplicationService;
	 /**
		 * 添加告知书
		 * 
		 * @param request
		 * @param id --
		 * @return
		 */
		@RequestMapping(value = "saveApp")
		@ResponseBody
		public HashMap<String, Object> saveApp(HttpServletRequest request, String data) {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				TzsbAppInfo info=getTzsbAppInfo(request);
				deviceApplicationService.saveApplication(info);
				
					wrapper.put("success", true);
					wrapper.put("id", info.getTzsbApplication().getId());
			} catch (Exception e) {
				log.error("保存安全告知书：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存安全告知书出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "getApplicationCode")
		@ResponseBody
		public String getApplicationCode(){
			return deviceApplicationService.getApplicationCode();
		}
		@RequestMapping(value = "delete")
		@ResponseBody
		public HashMap<String, Object> delete(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
			    	String appIds=request.getParameter("appIds");
			    	deviceApplicationService.deleteApp(appIds);
					wrapper.put("success", true);
					wrapper.put("printContent","");
			} catch (Exception e) {
				log.error("删除安全告知书：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除安全告知书出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "detial")
		@ResponseBody
		public HashMap<String, Object>  detial(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
			    	String appId=request.getParameter("appId");
			    	TzsbAppInfo  info=deviceApplicationService.queryByAppId(appId);
			    	
			    	HashMap<String, Object> params = new HashMap<String, Object>();
			    	String construct_sort=info.getTzsbApplication().getConstruct_sort();
	   			    String tk_construct_units_id = info.getTzsbAppEngineerSituations().getFk_construct_units_id(); //建设单位（使用单位ID）
	   		        String fk_construction_units_id = info.getTzsbApplication().getFk_construction_units_id(); //施工单位（安装单位ID）
	   		        String construction_units_name=info.getTzsbAppConstrucationOrg().getConstruction_units_name();
					params.put("construct_sort", construct_sort);
					params.put("tk_construct_units_id", tk_construct_units_id);
					params.put("fk_construction_units_id", fk_construction_units_id);
					params.put("construction_units_name", construction_units_name);
					params.put("appId", info.getTzsbApplication().getId());
					
					
			    	String json=JSON.toJSONString(info);
					wrapper.put("success", true);
					wrapper.put("content", json);
			} catch (Exception e) {
				log.error("删除安全告知书：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除安全告知书出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		public TzsbAppInfo getTzsbAppInfo(HttpServletRequest request){
			TzsbAppInfo info= new TzsbAppInfo();
			
			String tzsbApplication=request.getParameter("tzsbApplication").toString();
			TzsbApplication o1=JSON.parseObject(tzsbApplication, TzsbApplication.class);
			info.setTzsbApplication(o1);
			
			String tzsbAppEngineerSituations=request.getParameter("tzsbAppEngineerSituations").toString();
			TzsbAppEngineerSituations o2=JSON.parseObject(tzsbAppEngineerSituations, TzsbAppEngineerSituations.class);
			info.setTzsbAppEngineerSituations(o2);
			
			String tzsbAppConstrucationOrg=request.getParameter("tzsbAppConstrucationOrg").toString();
			TzsbAppConstrucationOrg o3=JSON.parseObject(tzsbAppConstrucationOrg, TzsbAppConstrucationOrg.class);
			info.setTzsbAppConstrucationOrg(o3);
			
			return info;
			
		}
		@RequestMapping(value = "getDeviceSort")
		@ResponseBody
		public HashMap<String, Object>   getDeviceSort(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				 DeviceCategories dc= deviceApplicationService.getDeviceSort(id);
				 wrapper.put("success", true);
				 wrapper.put("content", dc);
			} catch (Exception e) {
				log.error("获取设备类别异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取设备类别出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "delAppConsUnit")
		@ResponseBody
		public HashMap<String, Object>   delAppConsUnit(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppConsUnitService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("获取设备类别异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppConsUnit")
		@ResponseBody
		public HashMap<String, Object>   saveAppConsUnit(HttpServletRequest reques,TzsbAppConsUnit  tzsbAppConsUnit){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				  tzsbAppConsUnitService.save(tzsbAppConsUnit);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存土建工程施工单位异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppConsUnit")
		@ResponseBody
		public HashMap<String, Object>   listAppConsUnit(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String appId=request.getParameter("appId").toString();
				  List<TzsbAppConsUnit> list=tzsbAppConsUnitService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取土建工程施工单位异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取土建工程施工单位异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "delAppDevice")
		@ResponseBody
		public HashMap<String, Object>   delAppDevice(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppDeviceService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("删除设备信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppDevice")
		@ResponseBody
		public HashMap<String, Object>   saveAppDevice(HttpServletRequest request,TzsbAppDevice  tzsbAppDevice){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				HashMap<String, String> params = new HashMap<String,String>();
				params.put("construct_sort", request.getParameter("construct_sort"));
				params.put("fk_construct_units_id",  request.getParameter("fk_construct_units_id"));
				params.put("fk_construction_units_id",  request.getParameter("fk_construction_units_id"));
				params.put("construction_units_name",  request.getParameter("construction_units_name"));
				tzsbAppDeviceService.save(tzsbAppDevice,params);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存设备信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppDevice")
		@ResponseBody
		public HashMap<String, Object>   listAppDevice(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String appId=request.getParameter("appId").toString();
				  List<TzsbAppDevice> list=tzsbAppDeviceService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取设备信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取设备异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "delAppDocumentFile")
		@ResponseBody
		public HashMap<String, Object>   delAppDocumentFile(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppDocumentFileService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("删除上传文件资料信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppDocumentFile")
		@ResponseBody
		public HashMap<String, Object>   saveAppDocumentFile(HttpServletRequest reques,TzsbAppDocumentFile  tzsbAppDocumentFile){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				tzsbAppDocumentFileService.save(tzsbAppDocumentFile);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存上传文件资料信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppDocumentFile")
		@ResponseBody
		public HashMap<String, Object>   listAppDocumentFile(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String appId=request.getParameter("appId").toString();
				  List<TzsbAppDocumentFile> list=tzsbAppDocumentFileService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取上传文件资料信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取上传文件资料异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		
		@RequestMapping(value = "delAppOutsour")
		@ResponseBody
		public HashMap<String, Object>   delAppOutsour(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppOutsourService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("删除告知书施工分包信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppOutsour")
		@ResponseBody
		public HashMap<String, Object>   saveAppOutsour(HttpServletRequest request,TzsbAppOutsour  tzsbAppOutsour){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				tzsbAppOutsourService.save(tzsbAppOutsour);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存告知书施工分包信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppOutsour")
		@ResponseBody
		public HashMap<String, Object>   listAppOutsour(HttpServletRequest request,String appId){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				  List<TzsbAppOutsour> list=tzsbAppOutsourService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取告知书施工分包信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取告知书施工分包异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		
		@RequestMapping(value = "delAppSupervisionUnit")
		@ResponseBody
		public HashMap<String, Object>   delAppSupervisionUnit(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppSupervisionUnitService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("删除告知书土建工程监理或验收单位信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppSupervisionUnit")
		@ResponseBody
		public HashMap<String, Object>   saveAppSupervisionUnit(HttpServletRequest reques,TzsbAppSupervisionUnit  tzsbAppSupervisionUnit){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				tzsbAppSupervisionUnitService.save(tzsbAppSupervisionUnit);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存告知书土建工程监理或验收单位信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppSupervisionUnit")
		@ResponseBody
		public HashMap<String, Object>   listAppSupervisionUnit(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String appId=request.getParameter("appId").toString();
				  List<TzsbAppSupervisionUnit> list=tzsbAppSupervisionUnitService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取告知书土建工程监理或验收单位信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取告知书土建工程监理或验收单位异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "delAppWorks")
		@ResponseBody
		public HashMap<String, Object>   delAppWorks(HttpServletRequest request ){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String id=request.getParameter("id").toString();
				  tzsbAppWorksService.delete(id);
				 wrapper.put("success", true);
				 wrapper.put("message", "删除成功！");
			} catch (Exception e) {
				log.error("删除告知书现场管理、专业、作业人员情况信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "删除出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "saveAppWorks")
		@ResponseBody
		public HashMap<String, Object>   saveAppWorks(HttpServletRequest reques,TzsbAppWorks  tzsbAppWorks){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				tzsbAppWorksService.save(tzsbAppWorks);
				 wrapper.put("success", true);
				 wrapper.put("message", "保存成功！");
			} catch (Exception e) {
				log.error("保存告知书现场管理、专业、作业人员情况信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "listAppWorks")
		@ResponseBody
		public HashMap<String, Object>   listAppWorks(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String appId=request.getParameter("appId").toString();
				  List<TzsbAppWorks> list=tzsbAppWorksService.listByAppId(appId);
				 wrapper.put("success", true);
				 wrapper.put("list",list);
				 
			} catch (Exception e) {
				log.error("获取告知书现场管理、专业、作业人员情况信息异常：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "获取告知书现场管理、专业、作业人员情况异常");
				e.printStackTrace();
			}
			return wrapper;
		}
		@RequestMapping(value = "getOrgByCode")
		@ResponseBody
		public HashMap<String, Object>   getOrgByCode(HttpServletRequest request){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				 String code=request.getParameter("orgCode").toString();
				 Org org=deviceApplicationService.getOrgByCode(code);
				 wrapper.put("success", true);
				 wrapper.put("org",org);
				 
			} catch (Exception e) {
				wrapper.put("success", false);
				e.printStackTrace();
			}
			return wrapper;
		}
		@InitBinder  
	    protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {  
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	            CustomDateEditor editor = new CustomDateEditor(df, false);  
	            binder.registerCustomEditor(Date.class, editor);  
	    }
}
