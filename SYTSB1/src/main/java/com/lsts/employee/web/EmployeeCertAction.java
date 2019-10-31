package com.lsts.employee.web;

import java.text.SimpleDateFormat;
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
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.service.EmployeeCertService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.service.EmployeeBaseManager;

/**
 * 人员持证情况控制器
 * @ClassName EmployeeCertAction
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-03-03 下午05:20:00
 */
@Controller
@RequestMapping("employee/cert")
public class EmployeeCertAction extends SpringSupportAction<EmployeeCert, EmployeeCertService> {

	@Autowired
	private EmployeeCertService employeeCertService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private AttachmentManager attachmentManager;
	/**
	 * 保存
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCert")
	@ResponseBody
	public HashMap<String, Object> saveCert(HttpServletRequest request, EmployeeCert employeeCert) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {
			employeeCertService.saveEmployeeCert(employeeCert, uploadFiles); // 保存持证情况
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存持证情况失败，请重试！");
		}
		return JsonWrapper.successWrapper(employeeCert);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "detailCert")
	@ResponseBody
	public HashMap<String, Object> detailCert(HttpServletRequest request, String id,String empId) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			EmployeeCert cert= employeeCertService.detailCert(id); // 保存持证情况
			List<Attachment> list = attachmentManager.getBusAttachment(id);
			wrapper.put("success", true);
			wrapper.put("data", cert);
			wrapper.put("employeeBase",employeesService.get(empId));
			wrapper.put("attachs", list);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		return wrapper;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<EmployeeCert> list = employeeCertService.queryEmployeeCertByBasicId(id);
				if(!list.isEmpty()){
					for (EmployeeCert employeeCert : list) {
						System.out.println("-------"+employeeCert.getFirst_get_date());
						
						Map map = employeeCert.to_Map();
						datalist.add(map);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	
	/**
	 * 删除持证情况
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteCerts")
	@ResponseBody
	public HashMap<String, Object> deleteCerts(String ids) throws Exception {
		employeeCertService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	/**
	 * 证件注销
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "certCancellation")
	@ResponseBody
	public HashMap<String, Object> certCancellation(HttpServletRequest request,String id) throws Exception {
		try {
			String ids[]=id.split(",");
		for (int i = 0; i < ids.length; i++) {
			EmployeeCert cert=employeeCertService.get(id);
			cert.setStatus("99");
			employeeCertService.save(cert);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("证件注销失败，请重试！");
		}
		return JsonWrapper.successWrapper("1");
	}
	
	
	/**
	 * 证件删除
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteCert")
	@ResponseBody
	public HashMap<String, Object> deleteCert(HttpServletRequest request,String id) throws Exception {
		try {
			String ids[]=id.split(",");
		for (int i = 0; i < ids.length; i++) {
			EmployeeCert cert=employeeCertService.get(ids[i]);
			cert.setStatus("9");
			employeeCertService.save(cert);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("证件删除失败，请重试！");
		}
		return JsonWrapper.successWrapper("1");
	}
	/**
	 * 批量处理
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "batchCert")
	@ResponseBody
	public HashMap<String, Object> batchCert(HttpServletRequest request,String cert_issue_org,String first_get_date,String cert_end_date,String id) throws Exception {
		try {
			String ids[]=id.split(",");
			for (int i = 0; i < ids.length; i++) {
				EmployeeCert cert=employeeCertService.get(ids[i]);
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
				if(first_get_date!=null){
					cert.setFirst_get_date(sdf.parse(first_get_date));
				}
				if(cert_end_date!=null){
					cert.setCert_end_date(sdf.parse(cert_end_date));
				}
				
				if(cert_issue_org!=null && cert_issue_org.trim().length()>0){
					cert.setCert_issue_org(cert_issue_org);
				}
				employeeCertService.save(cert);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("证件删除失败，请重试！");
		}
		return JsonWrapper.successWrapper("1");
	}
}
