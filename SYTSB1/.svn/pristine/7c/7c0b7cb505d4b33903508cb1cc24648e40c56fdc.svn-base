package com.lsts.employee.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.Position;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.PositionManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.KHSecuritySaltSource;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.Md5Util;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.bean.EmployeePrinter;
import com.lsts.employee.service.EmployeePrinterService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.service.EmployeeBaseManager;

import util.FileUtil;
import util.TS_Util;

/**
 * 人员信息控制器
 * 
 * @ClassName EmployeesAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-03 下午03:15:00
 */
@Controller
@RequestMapping("employee/basic/")
public class EmployeesAction extends
		SpringSupportAction<Employee, EmployeesService> {

	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private EmployeePrinterService employeePrinterService;
	@Autowired
	private EmployeeBaseManager employeeBaseManager;
	@Autowired
	private MessageService messageService;
	@Autowired
	private PositionManager positionManager;
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param employee
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			Employee employee) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {			
			// 1、保存人员的基本信息（含电子签名、系统登录帐号）
			employeesService.saveEmployee(employee, uploadFiles); 
			
			// 2、保存人员的岗位信息	
			String positionIds = employee.getPositionIds();		// 岗位ID
			if(StringUtil.isNotEmpty(positionIds)){
				// 2、1 先删除原岗位信息
				employeesService.delEmpPosition(employee.getId());
				// 2.2、保存新岗位信息
				String[] positions = positionIds.split(",");
				for (int i = 0; i < positions.length; i++) {
					employeesService.addEmpPosition(employee.getId(), positions[i]);
				}
			}else{
				// 2、3 删除原岗位信息
				employeesService.delEmpPosition(employee.getId());
			}
			
			
			// 3、保存人员的打印机信息
			String printer_name = request.getParameter("printer_name");
			String printer_name_tags = request
					.getParameter("printer_name_tags");
			if (StringUtil.isNotEmpty(printer_name)
					|| StringUtil.isNotEmpty(printer_name_tags)) {
				EmployeePrinter employeePrinter = employeePrinterService.queryByEmployeeID(employee.getId());
				if (employeePrinter == null) {
					employeePrinter = new EmployeePrinter();
				}
			    //employeePrinter.setEmployee(employee);
				employeePrinter.setPrinter_name(StringUtil
						.isNotEmpty(printer_name) ? printer_name : "");
				employeePrinter
						.setPrinter_name_tags(StringUtil
								.isNotEmpty(printer_name_tags) ? printer_name_tags
								: "");
				employeePrinterService.save(employeePrinter);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存人员信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(employee);
	}

	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		// 1、获取人员基本信息
		Employee employee = employeesService.get(id);
		// 2、获取人员打印机信息
		EmployeePrinter employeePrinter = employeePrinterService
				.queryByEmployeeID(id);
		/*if (employeePrinter != null) {
			request.getSession()
					.setAttribute(
							"emp_printer_name",
							StringUtil.isNotEmpty(employeePrinter
									.getPrinter_name()) ? employeePrinter
									.getPrinter_name() : "");
			request.getSession().setAttribute(
					"emp_printer_name_tags",
					StringUtil.isNotEmpty(employeePrinter
							.getPrinter_name_tags()) ? employeePrinter
							.getPrinter_name_tags() : "");
		}*/
		// 3、获取人员附件信息
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		// 4、获取人员岗位信息
		List<String> positionList = employeesService.getPositionIDs(id);
		String positionIds = "";
		String positionNames = "";
		for (int i = 0; i < positionList.size(); i++) {
			if(positionIds.length() > 0){
				positionIds += ",";
			}
			positionIds +=  positionList.get(i);
			
			Position position = positionManager.get(positionList.get(i));
			if(positionNames.length() > 0){
				positionNames += ",";
			}
			positionNames +=  position.getPosName();
		}
		employee.setPositionIds(positionIds);
		employee.setPositionNames(positionNames);
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", employee);
		wrapper.put("attachs", list);
		wrapper.put("employeePrinter", employeePrinter);
		return wrapper;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		employeesService.delete(ids);
		employeeBaseManager.deleteByEmp(ids);
		return JsonWrapper.successWrapper(ids);
	}

	/**
	 * 验证身份证号是否已存在
	 * 
	 * @param request
	 * @param idNo
	 *            身份证号
	 * @return
	 */
	@RequestMapping(value = "validateID")
	@ResponseBody
	public HashMap<String, Object> validateID(HttpServletRequest request,
			String idNo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(idNo)) {
				Employee employee = employeesService.queryEmployeeByIdNo(idNo);
				if (employee == null) {
					wrapper.put("success", true);
					wrapper.put("message", "该身份证号可用！");
				} else {
					wrapper.put("error", true);
					wrapper.put("message", "已经存在该身份证号【" + idNo + "】的用户！");
				}
			}
		} catch (Exception e) {
			log.error("查询身份证号是否存在出错：" + e.getMessage());
			wrapper.put("error", true);
			wrapper.put("message", "查询身份证号出错了！");
			e.printStackTrace();
		}
		return wrapper;
	}
	
	
	/**通过姓名或电话号码自动检索
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchEmployee")
	@ResponseBody
	public HashMap<String, Object> suggest(String q) throws Exception {
		String data = new String(q.getBytes("iso8859-1"),"UTF-8");
		List<Employee> list = employeesService.sugguest(data);
		ArrayList al = new ArrayList();
		for (Employee employee : list) {
			HashMap hm = new HashMap();
			hm.put("id", employee.getId());
			hm.put("name", employee.getName());
			hm.put("mobileTel", employee.getMobileTel());
			hm.put("orgname", employee.getOrg());
			al.add(hm);
		}
        return JsonWrapper.successWrapper(al);
	}
	
	/**通过机构名称自动检索
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchOrg")
	@ResponseBody
	public HashMap<String, Object> searchOrg(String q) throws Exception {
		String data = new String(q.getBytes("iso8859-1"),"UTF-8");
		List<Org> list = employeesService.getOrg(data);
		ArrayList al = new ArrayList();
		for (Org org : list) {
			HashMap hm = new HashMap();
			hm.put("orgId", org.getId());
			hm.put("orgName", org.getOrgName());
			al.add(hm);
		}
        return JsonWrapper.successWrapper(al);
	}
	
	public static String hashMapToJson(HashMap map) {  
        String string = "{";  
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {  
            Entry e = (Entry) it.next();  
            string += "'" + e.getKey() + "':";  
            string += "'" + e.getValue() + "',";  
        }
        string = string.substring(0, string.lastIndexOf(","));  
        string += "}";  
        return string;  
    } 
	
	/**
	 * 验证财务独立密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @author GaoYa
	 * @date 2015-12-31 上午11:15:00
	 */
	@RequestMapping(value = "validSecondPwd")
	@ResponseBody
	public HashMap<String, Object> validSecondPwd(HttpServletRequest request, String second_pwd, String request_uri) throws Exception {
		if (StringUtil.isEmpty(second_pwd)) {
			return JsonWrapper.failureWrapperMsg("密码不能为空！");
		}
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		boolean result = employeesService.validSecondPwd(emp.getId(), second_pwd);
		if (result) {
			return JsonWrapper.successWrapper(request_uri);
		}else{
			return JsonWrapper.failureWrapperMsg("密码错误！");
		}
	}
	
	/**
	 * 修改财务独立密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @author GaoYa
	 * @date 2015-12-31 上午11:15:00
	 */
	@RequestMapping(value = "mdySecondPwd")
	@ResponseBody
	public HashMap<String, Object> mdySecondPwd(String oldPwd, String newPwd) throws Exception {
		if (StringUtil.isEmpty(oldPwd)) {
			return JsonWrapper.failureWrapperMsg("原密码不能为空！");
		}
		if(StringUtil.isEmpty(newPwd)){
			return JsonWrapper.failureWrapperMsg("新密码不能为空！");
		}
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		employeesService.mdySecondPwd(emp.getId(), oldPwd, newPwd);
		return JsonWrapper.successWrapper();
	}
	
	/**
	 * 重置财务独立密码，并发送微信
	 * 
	 * @param request

	 * @return
	 * @author GaoYa
	 * @date 2016-01-02 下午17:27:00
	 */
	@RequestMapping(value = "resetSecondPwd")
	@ResponseBody
	public HashMap<String, Object> resetSecondPwd(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee employee = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}
		
		if(employee!=null){
			String mobile = employee.getMobileTel();
			if(StringUtil.isNotEmpty(mobile)){
				if(mobile.length()==11){
					String pwd = TS_Util.getRanddom(6)+"";	// 获取6位随机数
					Object salt = KHSecuritySaltSource.getSalt();
					String second_pwd = Md5Util.encodeCode(pwd, salt);
					employeesService.updateSecondPwd(employee.getId(), second_pwd);
					
					// 获取发送内容
					String content =  Constant.getSecondPwdNoticeContent(pwd);
					
					if("1".equals(send_msg_type)){
						// 发送微信
						messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);
					}else if("2".equals(send_msg_type)){
						// 发送短信
						messageService.sendMoMsg(request, employee.getId(), content, mobile);	
					}else if("3".equals(send_msg_type)){
						// 发送微信
						messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);	
						// 发送短信
						messageService.sendMoMsg(request, employee.getId(), content, mobile);	
					}
				}else{
					return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
			}
		}
		return JsonWrapper.successWrapper(employee);
	}
	
	/**
	 * 财务独立密码重置为初始密码，用于用户丢失密码后由系统超级管理员处理
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "initSecondPwd")
	@ResponseBody
	public HashMap<String, Object> initSecondPwd(String ids) {
		if (StringUtil.isEmpty(ids))
			return JsonWrapper.failureWrapperMsg("用户ID不能为空");
		employeesService.initSecondPwd(ids);
		return JsonWrapper.successWrapper("123456");
	}
	
	/**
	 * 验证印章独立密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @author GaoYa
	 * @date 2015-12-31 上午11:15:00
	 */
	@RequestMapping(value = "validPrintPwd")
	@ResponseBody
	public HashMap<String, Object> validPrintPwd(HttpServletRequest request, String print_pwd, String request_uri) throws Exception {
		if (StringUtil.isEmpty(print_pwd)) {
			return JsonWrapper.failureWrapperMsg("密码不能为空！");
		}
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		boolean result = employeesService.validPrintPwd(emp.getId(), print_pwd);
		if (result) {
			return JsonWrapper.successWrapper(request_uri);
		}else{
			return JsonWrapper.failureWrapperMsg("密码错误！");
		}
	}
	
	/**
	 * 修改印章独立密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @author GaoYa
	 * @date 2015-12-31 上午11:15:00
	 */
	@RequestMapping(value = "mdyPrintPwd")
	@ResponseBody
	public HashMap<String, Object> mdyPrintPwd(String oldPwd, String newPwd) throws Exception {
		if (StringUtil.isEmpty(oldPwd)) {
			return JsonWrapper.failureWrapperMsg("原密码不能为空！");
		}
		if(StringUtil.isEmpty(newPwd)){
			return JsonWrapper.failureWrapperMsg("新密码不能为空！");
		}
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		employeesService.mdyPrintPwd(emp.getId(), oldPwd, newPwd);
		return JsonWrapper.successWrapper();
	}
	
	/**
	 * 重置印章独立密码，并发送微信
	 * 
	 * @param request

	 * @return
	 * @author GaoYa
	 * @date 2016-01-02 下午17:27:00
	 */
	@RequestMapping(value = "resetPrintPwd")
	@ResponseBody
	public HashMap<String, Object> resetPrintPwd(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		Employee employee = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}
		
		if(employee!=null){
			String mobile = employee.getMobileTel();
			if(StringUtil.isNotEmpty(mobile)){
				if(mobile.length()==11){
					String pwd = TS_Util.getRanddom(6)+"";	// 获取6位随机数
					Object salt = KHSecuritySaltSource.getSalt();
					String print_pwd = Md5Util.encodeCode(pwd, salt);
					employeesService.updatePrintPwd(employee.getId(), print_pwd);
					
					// 获取发送内容
					String content =  Constant.getPrintPwdNoticeContent(pwd);
					
					if("1".equals(send_msg_type)){
						// 发送微信
						messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);
					}else if("2".equals(send_msg_type)){
						// 发送短信
						messageService.sendMoMsg(request, employee.getId(), content, mobile);	
					}else if("3".equals(send_msg_type)){
						// 发送微信
						messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);	
						// 发送短信
						messageService.sendMoMsg(request, employee.getId(), content, mobile);	
					}
				}else{
					return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
			}
		}
		return JsonWrapper.successWrapper(employee);
	}
	
	/**
	 * 财务独立密码重置为初始密码，用于用户丢失密码后由系统超级管理员处理
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "initPrintPwd")
	@ResponseBody
	public HashMap<String, Object> initPrintPwd(String ids) {
		if (StringUtil.isEmpty(ids))
			return JsonWrapper.failureWrapperMsg("用户ID不能为空");
		employeesService.initPrintPwd(ids);
		return JsonWrapper.successWrapper("123456");
	}
	
	/**
	 * 单点登陆密码验证
	 * author pingZhou
	 * @param name 用户名
	 * @param password 密码MD5
	 * @return
	 */
	@RequestMapping(value = "checkPass")
	@ResponseBody
	public HashMap<String, Object> checkPass(String name,String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = employeesService.checkPass(name,password);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
			
		}
				
		return map;
	}
	
	/**
	 * 生成桌面登陆快捷方式
	 * author pingZhou
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "createLoginFile")
	@ResponseBody
	public void createLoginFile(String name,String password,HttpServletResponse response) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			employeesService.createLoginFile(name,password);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			
		}
		FileUtil.download(response, "D:\\TEMP"+ File.separator +"四川特检平台.html", "四川特检平台.html", "");
	}
	
	/**
	 * 单点登陆修改密码
	 * author pingZhou
	 * @param oldPwd 旧密码MD5
	 * @param newPwd 新密码MD5
	 * @return
	 */
	@RequestMapping(value = "updatePassword")
	@ResponseBody
	public HashMap<String, Object> updatePassword(String oldPwd,String newPwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = employeesService.updatePassword(oldPwd,newPwd);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
			
		}
				
		return map;
	}
	
	/**
	 * 单点登陆重置密码
	 * author pingZhou
	 * @param oldPwd 旧密码MD5
	 * @param newPwd 新密码MD5
	 * @return
	 */
	@RequestMapping(value = "resetPassword")
	@ResponseBody
	public HashMap<String, Object> resetPassword(String userId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = employeesService.resetPassword(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
			
		}
				
		return map;
	}
	
	/**
	 * 取人员签名
	 * author pingZhou
	 * @param userId
	 * @param type user 用户表iD 其他 employee_id
	 * @return
	 */
	@RequestMapping(value = "getEmpSignId")
	@ResponseBody
	public HashMap<String, Object> getEmpSignId(String id,String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String pictureID = employeesService.getEmpSignId(id,type);
			map.put("pictureID", pictureID);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
			
		}
				
		return map;
	}
	
}
