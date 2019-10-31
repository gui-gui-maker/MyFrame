package com.fwxm.scientific.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fwxm.scientific.bean.Instruction;
import com.fwxm.scientific.bean.InstructionInfo;
import com.fwxm.scientific.bean.InstrumentDevice;
import com.fwxm.scientific.bean.InstrumentDeviceInfo;
import com.fwxm.scientific.service.InstrumentDeviceInfoManager;
import com.fwxm.scientific.service.InstrumentDeviceManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName InstrumentDevice
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/instrumentDevice")
public class InstrumentDeviceAction extends
		SpringSupportAction<InstrumentDevice, InstrumentDeviceManager> {

	@Autowired
	private InstrumentDeviceManager instrumentDeviceManager;
	@Autowired
	private InstrumentDeviceInfoManager instrumentDeviceInfoManager;
	/**
	 * 保存
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		String instruction=request.getParameter("instruction").toString();
		InstrumentDevice entity=JSON.parseObject(instruction, InstrumentDevice.class);
		try {
		
			if(entity.getId()==null||entity.getId().equals("")||entity.getStatus().equals("0")){
				entity.setCreateDate(new Date());
				entity.setCreateId(user.getId());
				entity.setCreateMan(user.getName());
				entity.setStatus("0");
			}
			
			instrumentDeviceManager.save(entity); // 保存信息
             for (InstrumentDeviceInfo info :entity.getInstrumentDeviceInfo()) {
            	 info.setInstrumentDevice(entity);
            	 instrumentDeviceInfoManager.save(info);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return map;
	}
	
	/** 获取信息 **/
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			InstrumentDevice instruction = instrumentDeviceManager.getDetail(id);
			map.put("data", instruction);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
	/** 提交审核**/
	@RequestMapping(value = "subAudit")
	@ResponseBody
	public HashMap<String, Object> subAudit(HttpServletRequest request,String id,String opinion,String remark)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			InstrumentDevice instruction =instrumentDeviceManager.get(id);
				instruction.setStatus("1");//提交
				instrumentDeviceManager.save(instruction);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
	/** 项目负责人审核**/
	@RequestMapping(value = "audit")
	@ResponseBody
	public HashMap<String, Object> audit (HttpServletRequest request,String id,String result,String remark)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			InstrumentDevice instruction =instrumentDeviceManager.get(id);
			if(result.equals("1")){
				instruction.setStatus("2");//审核通过
			}else{
				instruction.setStatus("0");//审核通过
			}
			    instruction.setRemark(remark);
				instrumentDeviceManager.save(instruction);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
	
	/** 删除**/
	@RequestMapping(value = "deleteBase")
	@ResponseBody
	public HashMap<String, Object> deleteBase(HttpServletRequest request,String id)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			String[] ids=id.split(",");
			for (int i = 0; i < ids.length; i++) {
				InstrumentDevice instruction =instrumentDeviceManager.get(ids[i]);
				instruction.setStatus("99");//删除
				instrumentDeviceManager.save(instruction);
			}
			
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
}
