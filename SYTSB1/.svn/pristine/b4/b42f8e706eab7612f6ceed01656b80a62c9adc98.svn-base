package com.fwxm.personnelTraining.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

import com.fwxm.personnelTraining.bean.PersonalTraining;
import com.fwxm.personnelTraining.service.PersonalTrainingService;

@Controller
@RequestMapping("personalTrainingAction")
public class PersonalTrainingAction extends SpringSupportAction<PersonalTraining, PersonalTrainingService>{
	
	@Autowired 
	PersonalTrainingService personalTrainingService;

	@ResponseBody
	@RequestMapping("initData")
	public HashMap<String, Object> initData(HttpServletRequest request){
		HashMap<String, Object> map= new HashMap<String, Object>();
		HashMap<String, Object> data = personalTrainingService.initData(request);
		map.put("data", data);
		map.put("success", true);
		return map;
		
	}
	/**
	 * 保存申请信息
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(@RequestBody PersonalTraining pt,
			HttpServletRequest request) throws Exception {
		try {
			personalTrainingService.saveBasic(pt);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 提交下一步流程
	 * @author jiangjin
	 * @param ids 
	 * @param op_id 下一步操作人id
	 * @param op 下一步操作人名字
	 * @param step 下一步步骤
	 */
	@RequestMapping(value = "sub")
	@ResponseBody
	public HashMap<String, Object> sub(HttpServletRequest request,String ids,String op_id,String op,String step) throws Exception {
		try {
			personalTrainingService.sub(ids,op_id,op,step);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 退回
	 * @author jiangjin
	 * @param ids 
	 * @param step 下一步步骤
	 */
	@RequestMapping(value = "back")
	@ResponseBody
	public HashMap<String, Object> back(HttpServletRequest request,String ids,String step) throws Exception {
		try {
			personalTrainingService.back(ids,step);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/** 作废信息 **/
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(String ids) throws Exception {
		personalTrainingService.del(ids);
		return JsonWrapper.successWrapper(ids);

	}
}

