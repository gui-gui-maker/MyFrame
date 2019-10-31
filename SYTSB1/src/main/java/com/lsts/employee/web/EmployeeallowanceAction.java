package com.lsts.employee.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.employee.bean.Employeeallowance;
import com.lsts.employee.service.EmployeeallowanceService;


@Controller
@RequestMapping("employeeallowanceAction")
public class EmployeeallowanceAction extends SpringSupportAction<Employeeallowance, EmployeeallowanceService> {

    @Autowired
    private EmployeeallowanceService  employeeallowanceService;



    @ResponseBody
  	@RequestMapping("savebasic")
  	public HashMap<String, Object> savebasic(HttpServletRequest request,  @RequestBody Employeeallowance entity) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		try {
  			employeeallowanceService.save(entity);
  			map.put("success", true);
  			map.put("data", entity);
  		} catch (Exception e) {
  			e.printStackTrace();
  			if(e.getMessage().contains("补助超过限额")) {
  				map.put("msg", e.getMessage());
  			}else {
  				map.put("msg", "保存失败！");
  			}
  			
  			map.put("success", false);
  			
  		}
  		
  		return map;
  	}
    
    @RequestMapping(value = "subAuditOp")
	@ResponseBody
	public HashMap<String, Object> subAuditOp(HttpServletRequest request,String applicantsId, String flow_step,String ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String name = employeeallowanceService.subAuditOp(applicantsId);
			if("depart_manager".equals(name)) {
				flow_step = "1";
				map.put("flow_step", flow_step);
			}
			else if("personnel_manager".equals(name)) {
				flow_step = "3";
				map.put("flow_step", flow_step);
			}
			else if("leader_manager".equals(name)) {
				flow_step = "3";
				map.put("flow_step", flow_step);
			}
			map.put("success", true);
			map.put("name", name);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "失败！");
		}
		return map;
	}
    
    @RequestMapping(value = "subOvertime")
	@ResponseBody
	public HashMap<String, Object> subOvertime(HttpServletRequest request,String ids,String step) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			employeeallowanceService.subOvertime(request, ids,step);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "提交失败！");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			employeeallowanceService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除失败！");
		}
		return map;
	}
    
    
    
}


