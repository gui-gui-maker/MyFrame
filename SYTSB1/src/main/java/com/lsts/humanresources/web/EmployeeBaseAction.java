package com.lsts.humanresources.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.service.CodeTableManager;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.rbac.manager.OrgManager;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.pojo.AccessToken;
import com.khnt.weixin.util.Constants;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.bean.EmployeePrinter;
import com.lsts.employee.service.EmployeePrinterService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.RsSummaryDTO;
import com.lsts.humanresources.bean.Tjy2Gjj;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.bean.WorktitleRecord;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.WorkExperienceManager;
import com.lsts.humanresources.service.WorktitleRecordManager;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("employeeBaseAction")
public class EmployeeBaseAction extends SpringSupportAction<EmployeeBase, EmployeeBaseManager> {

    @Autowired
    private EmployeeBaseManager  employeeBaseManager;
    @Autowired
    private WorkExperienceManager workExperienceManager;
	@Autowired
	private AttachmentManager attachmentManager;
    @Autowired
    private OrgManager orgManager;
    @Autowired
	private EmployeesService employeesService;
	@Autowired
	private EmployeePrinterService employeePrinterService;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private CodeTableManager codeTableManager;
	@Autowired
	private MessageService messageService;
	@Autowired
	private WorktitleRecordManager worktitleRecordManager;
	@Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
  //保存员工基础信息
  	@RequestMapping(value = "saveBasic")
  	@ResponseBody
  	public HashMap<String, Object> saveBasic(String permission,String status,HttpServletRequest request,String name_old,String org_id_old )
  			throws Exception {
  		
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper=employeeBaseManager.saveEmp(request, status,name_old,org_id_old );
  		return wrapper;
  	}
    //查询员工基础信息
  	@RequestMapping(value = "detailBasic")
  	@ResponseBody
  	public HashMap<String, Object> detailBasic(String id,HttpServletRequest request,HttpSession session)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			EmployeeBase emp= employeeBaseManager.get(id);
  			List<WorkExperience> work= workExperienceManager.getByEmpId(id);
  			EmployeePrinter employeePrinter = employeePrinterService
  					.queryByEmployeeID(id);
  			List<Attachment> list = attachmentManager.getBusAttachment(id);
  			//session.setAttribute("photo", emp.getEmpPhoto());
  			wrapper.put("emp", emp);
  			wrapper.put("work",work);
  			wrapper.put("attachs", list);
  			wrapper.put("employeePrinter", employeePrinter);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	 //提交应聘审核
  	@RequestMapping(value = "addAudit")
  	@ResponseBody
  	public HashMap<String, Object> addAudit(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		EmployeeBase emp=new EmployeeBase();
  		try{
  			emp=employeeBaseManager.get(id);
  			emp.setEmpStatus("2");
  			employeeBaseManager.save(emp);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	 //提交面试情况
  	@RequestMapping(value = "addInterview")
  	@ResponseBody
  	public HashMap<String, Object> addInterview(String testScorePicture,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		EmployeeBase emp=new EmployeeBase();
  		try{
  			String id=request.getParameter("id");
  			emp=employeeBaseManager.get(id);
  			emp.setEmpStatus("3");
  			emp.setPositiveDate(new Date());
  			emp.setTestScorePicture(testScorePicture);
  			employeeBaseManager.save(emp);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	//员工转正
	@RequestMapping(value = "addPositive")
  	@ResponseBody
  	public HashMap<String, Object> addPositive(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		EmployeeBase emp=new EmployeeBase();
  		try{
  			emp=employeeBaseManager.get(id);
  			emp.setEmpStatus("4");
  			emp.setIsCheck("0");//员工确认状态改为未确认，等待再次确认
  			emp.setPositiveDate(new Date());
  			employeeBaseManager.save(emp);
  			String selfMobile = emp.getEmpPhone();
  			String positiveRemind="您已从试用转为正式员工，请在“微信企业号【四川省特检院】——【人力资源】——个人中心——我的档案——基本信息”处确认个人基本信息！";
			messageService.sendWxMsg(request,
					id, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD, positiveRemind, selfMobile);
			messageService.sendMoMsg(request, id, positiveRemind, selfMobile);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
	//应聘不通过
		@RequestMapping(value = "noPass")
	  	@ResponseBody
	  	public HashMap<String, Object> noPass(String id,HttpServletRequest request)
	  			throws Exception {
	  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
	  		EmployeeBase emp=new EmployeeBase();
	  		try{
	  			emp=employeeBaseManager.get(id);
	  			emp.setEmpStatus("0");
	  			employeeBaseManager.save(emp);
	  			wrapper.put("success", true);
	  		}catch(Exception e){
	  			log.error("保存信息：" + e.getMessage());
	  			wrapper.put("success", false);
	  			wrapper.put("message", "保存信息出错！");
	  			e.printStackTrace();	
	  		}
	  		return wrapper;
	  	}
	//员工删除
		@RequestMapping(value = "deleteBase")
	  	@ResponseBody
	  	public HashMap<String, Object> deleteBase(String ids,HttpServletRequest request)
	  			throws Exception {
	  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
	  		try{
	  			String[] id=ids.split(",");
	  			for (int i = 0; i < id.length; i++) {
	  				EmployeeBase employeeBase=	employeeBaseManager.get(id[i]);
                  //逻辑删除
	  				employeeBase.setEmpStatus("7");
	  				employeeBaseManager.save(employeeBase);
	  				//删除employee
	  				employeesService.delete(ids);
				}
	  			wrapper.put("success", true);
	  		}catch(Exception e){
	  			log.error("保存信息：" + e.getMessage());
	  			wrapper.put("success", false);
	  			wrapper.put("message", "保存信息出错！");
	  			e.printStackTrace();	
	  		}
	  		return wrapper;
	  	}
		/**
		 * 按部门统计
		 * 
		 */
	    @RequestMapping(value = "queryList")
	   public String   queryList(HttpServletRequest request,String area_code,String area_code_name)throws Exception{
	    	Map<String,List>  map=new HashMap<String,List>();
	    	map=employeeBaseManager.queryList();
	    	request.setAttribute("map", map);
		return "app/humanresources/employee_statistical";
	    }
	    /**
		 * 按部门统计当前员工状态
		 * 
		 */
	    @RequestMapping(value = "empCurrStatus")
	   public String   empCurrStatus(HttpServletRequest request,String area_code,String area_code_name)throws Exception{
	    	Map<String,List>  map=new HashMap<String,List>();
	    	map=employeeBaseManager.empCurrStatus();
	    	request.setAttribute("map", map);
		return "app/humanresources/employee_currststus";
	    }
	    /**
		 * 员工信息确认
		 * 
		 */
	    @RequestMapping(value = "check")
	  	@ResponseBody
	  	public HashMap<String, Object> check(HttpServletRequest request, String phone)throws Exception {
	    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
			HashMap<String, Object> map = new HashMap<String, Object>();
			//String userId=meetingReqAction.load(request, id);
			EmployeeBase employeeBase=employeeBaseManager.getEmpByPhone(phone);
			employeeBase.setIsCheck("1");
			employeeBaseManager.save(employeeBase);
			map.put("success", true);
			map.put("msg", "数据保存成功！");
			return map;
	  		
	  	}
	    
	    /**
		 * 通过手机号获取员工信息
		 * 
		 */
	    @RequestMapping(value = "empByPhone")
	  	@ResponseBody
	  	public HashMap<String, Object> getEmpByPhone(HttpServletRequest request, String phone)throws Exception {
	    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
			HashMap<String, Object> map = new HashMap<String, Object>();
			//String userId=meetingReqAction.load(request, id);
			EmployeeBase employeeBase=employeeBaseManager.getEmpByPhone(phone);
			map.put("success", true);
			map.put("employeeBase", employeeBase);
			return map;
	  		
	  	}
	    
	    /**
	     * 微信端获取码表值
	     * @param request
	     * @param tablname
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "getcodetabl")
		@ResponseBody
		public HashMap<String, Object> getcodetabl(HttpServletRequest request,String tablname)
				throws Exception {
			    CodeTable  code= codeTableManager.getByCode(tablname);	
			 	return JsonWrapper.successWrapper(code.getCodeTableValues());
		}
	    
	    /**
		 * 在微信端获取获取用户电话
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "wUserInfo")
		@OAuthRequired
		public HashMap<String, Object> loadFinal(HttpServletRequest request,String code){
			HashMap<String, Object> map = new HashMap<String, Object>();
			URL url = null;
			String inputLine = "";
			String wx_user_id = null;
			String Tel = null;
			try {
				String str = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code="+code;
				url = new URL(str.trim());
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(url.openStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					inputLine = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				JSONObject jo = JSONObject.fromObject(inputLine);
				wx_user_id = jo.get("UserId ").toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tel = this.getTel(request,wx_user_id);
			map.put("success", true);
			map.put("wx_user_id", wx_user_id);
			map.put("Tel", Tel);
			return map;
		}
		
		
		
		
	    //通过微信userid获取电话
		public String getTel(HttpServletRequest request, String wx_user_id){
			URL url = null;
			String corpID = "people.kh";	// 账户名（检验报告书专用收发帐号，与企业号检验报告应用对应收发）
			String pwd = "16a61256d01a80cbecaaf38627dd6940";// 密码	
			String inputLine = "";
			String Tel = null;
			try {
				String str = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid="+wx_user_id;
				url = new URL(str.trim());
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(url.openStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					inputLine = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				JSONObject jo = JSONObject.fromObject(inputLine);
				Tel = jo.get("mobile").toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Tel;
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
	                //System.out.println("result=" + result);
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
	            	/*//以下姓名及电话供测试微信端基本信息、公积金、工资表确认使用
	            	session.setAttribute("Name", "李梦婕");
	            	session.setAttribute("Phone", "18200380020");*/
	                //session.setAttribute("AccessToken", "");
	            }
	        }
			model.addAttribute("Userid", session.getAttribute("Userid"));
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
			return "app/weixin/rs/index";
		}
		
		/**
		 * 加载个人信息，此处添加了@OAuthRequired注解
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "weixinUserInfo1")
		@OAuthRequired
		public String weixinUserInfo1(HttpServletRequest request,Model model){
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
	                //session.setAttribute("AccessToken", "");
	            }
	        }
			model.addAttribute("Userid", session.getAttribute("Userid"));
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
			return "app/weixin/rs/index_rs";
		}
		/**
		 * 微信端通过员工ID获取员工基本信息
		 * @param id
		 * @return
		 * @author Quincy Xu
		 */
		@RequestMapping(value = "getEmpOnWXById")
	  	@ResponseBody
		public HashMap<String, Object> getEmpOnWXById(String id){
			HashMap<String, Object> map = new HashMap<String, Object>();
			EmployeeBase employeeBase = employeeBaseManager.get(id);
			map.put("success", true);
			map.put("employeeBase", employeeBase);
			return map;
		}
		
		/**
		 * 微信端通过员工姓名获取员工基本信息
		 * @param name
		 * @return
		 * @author Quincy Xu
		 * @throws Exception 
		 */
		@RequestMapping(value = "getEmpOnWXByName")
	  	@ResponseBody
		public HashMap<String, Object> getEmpOnWXByName(String name) throws Exception{
			String empName = URLDecoder.decode(name,"utf-8");
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<EmployeeBase> emplist=new ArrayList<EmployeeBase>();
			emplist = employeeBaseManager.getEmpOnWXByName(empName);
			map.put("success", true);
			map.put("emplist", emplist);
			return map;
		}
		/**
		 * 微信端人力资源全院概况统计
		 * @author Quinc
		 */
		@RequestMapping(value = "weixinHRCount")
	  	@ResponseBody
		public HashMap<String, Object> weixinHRCount() throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<RsSummaryDTO> list=new ArrayList<RsSummaryDTO>();
			list = employeeBaseManager.weixinHRCount();
			map.put("success", true);
			map.put("list", list);
			return map;
		}
		@RequestMapping(value = "getEmpByNameAndStatus")
	  	@ResponseBody
		public HashMap<String, Object> getEmpByNameAndStatus(HttpServletRequest request,String empName,String empStatus) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<EmployeeBase> list=new ArrayList<EmployeeBase>();
			list = employeeBaseManager.getEmpByNameAndStatus(empName,empStatus);
			map.put("success", true);
			map.put("list", list);
			return map;
		}
		/**
		 * 
		 * @param request
		 * @param employeeBase
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "saveDateAndDays")
	  	@ResponseBody
		public HashMap<String, Object> saveDateAndDays(HttpServletRequest request,EmployeeBase employeeBase) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(!employeeBase.getId().isEmpty()){
				EmployeeBase employeeBase1=employeeBaseManager.get(employeeBase.getId());
				employeeBase1.setSeniorityDate(employeeBase.getSeniorityDate());
				employeeBase1.setLeaveDays(employeeBase.getLeaveDays());
				employeeBase1.setExtraDays(employeeBase.getExtraDays());
				employeeBase1.setTotalDays(employeeBase.getTotalDays());
				employeeBaseManager.save(employeeBase1);
				map.put("success", true);
			}else{
				map.put("success", false);
			}
			return map;
		}
		/**
		 * 学历、专业统计
		 * @param request
		 * @param employeeBase
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "edumajorCount")
		@ResponseBody
		public HashMap<String, Object> edumajorCount(HttpServletRequest request) throws Exception {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			String condition = request.getParameter("condition");;
			try {
				wrapper.put("data", employeeBaseManager.edumajorCount(condition));
				wrapper.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("统计失败，请重试！");
			}
			return wrapper;
		}
		/**
		 * 
		 * @param request
		 * @param worktitleRecord
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "saveWorkTitle")
	  	@ResponseBody
		public HashMap<String, Object> saveWorkTitle(HttpServletRequest request,WorktitleRecord worktitleRecord) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String pageStatus=request.getParameter("pageStatus");
			if(!worktitleRecord.getEmpId().isEmpty()){
				EmployeeBase employeeBase=employeeBaseManager.get(worktitleRecord.getEmpId());
				employeeBase.setWorkTitle(worktitleRecord.getWorktitleName());
				employeeBase.setWorkTitleDate(worktitleRecord.getEndDate());
				if(pageStatus.equals("modify")){
					WorktitleRecord worktitleRecord1=worktitleRecordManager.get(worktitleRecord.getId());
					worktitleRecord1.setWorktitleId(worktitleRecord.getWorktitleId());
					worktitleRecord1.setWorktitleName(worktitleRecord.getWorktitleName());
					worktitleRecord1.setStartDate(worktitleRecord.getStartDate());;
					worktitleRecord1.setEndDate(worktitleRecord.getEndDate());;
					worktitleRecord1.setUploadFiles(worktitleRecord.getUploadFiles());;
					map=employeeBaseManager.saveWorkTitle(employeeBase,worktitleRecord1);
				}else if(pageStatus.equals("add")){
					map=employeeBaseManager.saveWorkTitle(employeeBase,worktitleRecord);
				}
			}else{
				map.put("success", false);
			}
			return map;
		}
		/**
		 *获取职务信息
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "getWorkTitle")
	  	@ResponseBody
		public HashMap<String, Object> getWorkTitle(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String empId=request.getParameter("empId");
			String recordId=request.getParameter("recordId");
			if(recordId!=null&&recordId.length()>0){
				WorktitleRecord worktitleRecord = worktitleRecordManager.get(recordId);
				List<Attachment> list = attachmentManager.getBusAttachment(recordId);
				map.put("success", true);
		   		map.put("attachs", list);
				map.put("worktitleRecord", worktitleRecord);
			}else{
				if(!empId.isEmpty()){
					WorktitleRecord worktitleRecord = new WorktitleRecord();
					worktitleRecord=worktitleRecordManager.getWorkTitle(empId);
					List<Attachment> list = attachmentManager.getBusAttachment(worktitleRecord.getId());
					map.put("success", true);
			   		map.put("attachs", list);
					map.put("worktitleRecord", worktitleRecord);
				}else{
					map.put("success", false);
				}
			}
			return map;
		}
		/**
		 * 获取职务记录
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "getWorkTitles")
	  	@ResponseBody
		public HashMap<String, Object> getWorkTitles(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String empId=request.getParameter("empId");
			if(!empId.isEmpty()){
				List<WorktitleRecord> list = new ArrayList<WorktitleRecord>();
				list=worktitleRecordManager.getWorkTitles(empId);
				map.put("worktitleRecords", list);
				map.put("success", true);
			}else{
				map.put("success", false);
			}
			return map;
		}
		/**
	     * 保存导入的员工可休假天数文件
	     * @param files
	     * @return
	     */
	    @RequestMapping(value = "saveTask")
		@ResponseBody
		public HashMap<String,Object> saveTask(String files) {
			HashMap<String,Object> data=new HashMap<String,Object>();
			String[] contents = new String[2];
			
			try {
				contents = employeeBaseManager.saveTaskData(files);
				data.put("success", true);
				data.put("total",contents[0]);
		    	data.put("repData",contents[1]);
			} catch(Exception e) {
				data.put("success", false);
			}
	    	return  data;
		}
	    /**
	     * 添加职务预警例外人员
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "addWorkTitleWarnExcept")
	    @ResponseBody
		public HashMap<String, Object> addWorkTitleWarnExcept(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			/*String jsonStr=request.getParameter("jsonStr");

			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i);
			jsonJ.getInt("name");
			jsonJ.getString("age");}*/
			String[] ids=request.getParameter("ids").trim().split(",");
			if(ids.length>0&&ids!=null){
				for(String id:ids){
					EmployeeBase employeeBase=employeeBaseManager.get(id);
					employeeBase.setWorkTitleWarnExcept("1");
					employeeBaseManager.save(employeeBase);
				}
				map.put("success", true);
				map.put("msg", "添加成功！");
			}else{
				map.put("success", false);
				map.put("msg", "人员为空！请选择人员！");
			}
			return map;
		}
	    /**
	     * 移除职务预警例外人员
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "removeWorkTitleWarnExcept")
	  	@ResponseBody
		public HashMap<String, Object> removeWorkTitleWarnExcept(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] ids=request.getParameter("ids").split(",");
			if(ids.length>0&&ids!=null){
				for(String id:ids){
					EmployeeBase employeeBase=employeeBaseManager.get(id);
					employeeBase.setWorkTitleWarnExcept("0");
					employeeBaseManager.save(employeeBase);
				}
				map.put("success", true);
				map.put("msg", "移除成功！");
			}else{
				map.put("success", false);
				map.put("msg", "没有选择要移除的人员！请选择人员！");
			}
			return map;
		}
	    /**
	     * 添加退休预警例外人员
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "addRetiredWarnExcept")
	  	@ResponseBody
		public HashMap<String, Object> addRetiredWarnExcept(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] ids=request.getParameter("ids").trim().split(",");
			if(ids.length>0&&ids!=null){
				for(String id:ids){
					EmployeeBase employeeBase=employeeBaseManager.get(id);
					employeeBase.setRetiredWarnExcept("1");
					employeeBaseManager.save(employeeBase);
				}
				map.put("success", true);
				map.put("msg", "添加成功！");
			}else{
				map.put("success", false);
				map.put("msg", "人员为空！请选择人员！");
			}
			return map;
		}
	    /**
	     * 移除退休预警例外人员
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "removeRetiredWarnExcept")
	  	@ResponseBody
		public HashMap<String, Object> removeRetiredWarnExcept(HttpServletRequest request) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] ids=request.getParameter("ids").split(",");
			if(ids.length>0&&ids!=null){
				for(String id:ids){
					EmployeeBase employeeBase=employeeBaseManager.get(id);
					employeeBase.setRetiredWarnExcept("0");
					employeeBaseManager.save(employeeBase);
				}
				map.put("success", true);
				map.put("msg", "移除成功！");
			}else{
				map.put("success", false);
				map.put("msg", "没有选择要移除的人员！请选择人员！");
			}
			return map;
		}
	    /**
	     * 更新可休年假天数
	     */
	    @RequestMapping(value = "synTotalDays")
	  	@ResponseBody
	  	public void synTotalDays(){
	    	employeeBaseManager.checkTotalDays();
	    }
	    /**
	     * 获取人员职务信息
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping(value = "getWorkTitle1")
	  	@ResponseBody
	  	public HashMap<String,Object> getWorkTitle1(HttpServletRequest request,String id){
	    	String workTitle = employeeBaseManager.getWorkTitle(id);
	    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    	hashMap.put("workTitle", workTitle);
			return hashMap;
	    }
	    /**
	     * 保存员工排序设置
	     * @param request
	     * @param employeeBasetemp
	     * @return
	     */
	    @RequestMapping(value = "saveSort")
	  	@ResponseBody
	  	public HashMap<String,Object> saveSort(HttpServletRequest request,EmployeeBase employeeBasetemp){
	    	try {
				EmployeeBase employeeBase = employeeBaseManager.get(employeeBasetemp.getId());
				employeeBase.setSort(employeeBasetemp.getSort());
				employeeBaseManager.save(employeeBase);
				return JsonWrapper.successWrapper(employeeBase);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("保存失败！");
			}
	    }
	    /**
	     * 验证填写的身份证号，联系电话，职称证书编号是否 已经填写在其他人员信息中
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "checkInfo")
	  	@ResponseBody
	  	public HashMap<String,Object> checkInfo(HttpServletRequest request){
	    	try {
	    		HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    		String empPhone = request.getParameter("empPhone");
	    		String empIdCard = request.getParameter("empIdCard"); 
	    		String empTitleNum = request.getParameter("empTitleNum");
	    		List<EmployeeBase> list0=new ArrayList<EmployeeBase>();
	    		List<EmployeeBase> list1=new ArrayList<EmployeeBase>();
	    		List<EmployeeBase> list2=new ArrayList<EmployeeBase>();
	    		if(StringUtil.isNotEmpty(empPhone)) {
	    			list0 = employeeBaseManager.checkInfoByPhone(empPhone);
	    		}
	    		if(StringUtil.isNotEmpty(empIdCard)) {
	    			list1 = employeeBaseManager.checkInfoByIdCard(empIdCard);
	    		}
				if(StringUtil.isNotEmpty(empTitleNum)) {
					list2 = employeeBaseManager.checkInfoByTitleNum(empTitleNum);
				}
				if((list0!=null && list0.size()>0) || (list1!=null && list1.size()>0) || (list2!=null && list2.size()>0)) {
					String msg = null;
					if(list0!=null && list0.size()>0) {
						String emp_name=null;
						for(int i=0;i<list0.size();i++) {
							emp_name = (emp_name==null)?list0.get(i).getEmpName():(emp_name+","+list0.get(i).getEmpName());
						}
				    	msg = (msg==null)?"联系电话已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中":(msg+",</br>"+"联系电话已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中");
					}
					if(list1!=null && list1.size()>0) {
						String emp_name=null;
						for(int i=0;i<list1.size();i++) {
							emp_name = (emp_name==null)?list1.get(i).getEmpName():(emp_name+","+list1.get(i).getEmpName());
						}
				    	msg = (msg==null)?"身份证号已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中":(msg+",</br>"+"身份证号已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中");
					}
					if(list2!=null && list2.size()>0) {
						String emp_name=null;
						for(int i=0;i<list0.size();i++) {
							emp_name = (emp_name==null)?list2.get(i).getEmpName():(emp_name+","+list2.get(i).getEmpName());
						}
				    	msg = (msg==null)?"职称证书编号已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中":(msg+",</br>"+"职称证书编号已被填写在&nbsp;<span style='color:red;'>"+emp_name+"</span>&nbsp的信息中");
					}
					hashMap.put("msg", msg);
					hashMap.put("success", false);
					return hashMap;
				}else {
					return JsonWrapper.successWrapper();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("验证联系电话、身份证号、职称证书编号失败！");
			}
	    }
}