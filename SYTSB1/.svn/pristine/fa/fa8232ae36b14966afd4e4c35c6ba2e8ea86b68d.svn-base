package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.EquipOutstockRecord;
import com.lsts.equipment2.service.EquipOutstockRecordManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 设备出库记录控制器
 * 
 * @ClassName EquipOutstockRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:24:00
 */
@Controller
@RequestMapping("equipOutstockRecordAction")
public class EquipOutstockRecordAction extends SpringSupportAction<EquipOutstockRecord, EquipOutstockRecordManager> {

	@Autowired
	private EquipOutstockRecordManager EquipOutstockRecordManager;
		// 设备出库
		@RequestMapping(value = "outstock")
		@ResponseBody
		public HashMap<String, Object> outstock(HttpServletRequest request,@RequestBody EquipOutstockRecord equipOutstockRecord) {
			String apply_id = request.getParameter("apply_id");
			return EquipOutstockRecordManager.outstock(apply_id,equipOutstockRecord);
		}
		//删除出库记录
		@RequestMapping(value = "delete1")
		@ResponseBody
		/*public void delete1(String ids) {
			 EquipOutstockRecordManager.delete1(ids);
		}*/
		public HashMap<String, Object> delete(String ids) throws Exception {
			if(!ids.equals(null)){
				EquipOutstockRecordManager.delete1(ids);
			}
			return JsonWrapper.successWrapper(ids);
		}
}