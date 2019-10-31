package com.lsts.employee.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.bean.EmployeePermissions;
import com.lsts.employee.service.EmployeePermissionsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("employeePermissionsAction")
public class EmployeePermissionsAction extends SpringSupportAction<EmployeePermissions, EmployeePermissionsManager> {

    @Autowired
    private EmployeePermissionsManager  employeePermissionsManager;
    
    /**
	 * 详情
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "detailPermissions")
	@ResponseBody
	public HashMap<String, Object> detailPermissions(HttpServletRequest request, String id) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			EmployeePermissions permissions= employeePermissionsManager.detailPermissions(id); // 
			wrapper.put("success", true);
			wrapper.put("data", permissions);

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		return wrapper;
	}
	
}