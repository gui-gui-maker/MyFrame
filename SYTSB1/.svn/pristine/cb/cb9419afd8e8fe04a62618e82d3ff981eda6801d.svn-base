package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.EquipInstockRecord;
import com.lsts.equipment2.service.EquipInstockRecordManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 设备入库记录控制器
 * 
 * @ClassName EquipInstockRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:24:00
 */
@Controller
@RequestMapping("equipInstockRecordAction")
public class EquipInstockRecordAction extends SpringSupportAction<EquipInstockRecord, EquipInstockRecordManager> {

	@Autowired
	private EquipInstockRecordManager equipInstockRecordManager;
	// 设备入库
	@RequestMapping(value = "instock")
	@ResponseBody
	public HashMap<String, Object> instock(@RequestBody EquipInstockRecord equipInstockRecord, HttpServletRequest request) {
		return equipInstockRecordManager.instock(equipInstockRecord, request);
	}
	//删除入库记录
	@RequestMapping(value = "delete1")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		if(!ids.equals(null)){
			equipInstockRecordManager.delete1(ids);
		}
		return JsonWrapper.successWrapper(ids);
	}
}