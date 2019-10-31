package com.khnt.oa.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.Repair;
import com.khnt.oa.car.service.RepairManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName OrgAction
 * @JDK 1.5
 * @author
 * @date
 */
@Controller
@RequestMapping("oa/car/repair/")
public class RepairAction extends SpringSupportAction<Repair, RepairManager> {

	@Autowired
	private RepairManager repairManager;
	
	@RequestMapping(value = "saveRepairInfo")
	@ResponseBody
	public HashMap<String, Object> saveRepairInfo(String id, Repair repair){
	  try{
		  repairManager.save(repair);
		  return JsonWrapper.successWrapper(repair);
	  }
	  catch(Exception e){
		  return JsonWrapper.failureWrapperMsg("保存车辆维修信息出错："+e.getMessage());
	  }
	}
}
