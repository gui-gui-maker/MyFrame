package com.khnt.rbac.impl.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.EmployeeExt;
import com.khnt.rbac.impl.service.EmployeeExtService;
@Controller
@RequestMapping("employeeExtAction")
public class EmployeeExtAction extends SpringSupportAction<EmployeeExt, EmployeeExtService>{
	@Autowired
	EmployeeExtService employeeExtService;
	@Autowired
	AttachmentManager attachmentManager;
	
	@RequestMapping(value="saveExt")
	@ResponseBody
	public HashMap<String, Object> saveExt(HttpServletRequest request, EmployeeExt entity) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {			
			employeeExtService.saveEmployeeExt(entity, uploadFiles); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(entity);
	}
	@RequestMapping(value="getExtByEmpId")
	@ResponseBody
	public HashMap<String, Object> getExtByEmpId(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {			
			List<EmployeeExt> list = employeeExtService.getExtByEmpId(id);
			if(list.size()>0){
				String ext_id = list.get(0).getId();
				List<Attachment> pics = attachmentManager.getBusAttachment(ext_id); 
				map.put("data", list.get(0));
				map.put("attachments", pics);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
