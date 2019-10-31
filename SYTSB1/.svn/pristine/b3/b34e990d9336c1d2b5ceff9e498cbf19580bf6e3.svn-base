package com.fwxm.scientific.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwxm.scientific.bean.Instruction;
import com.fwxm.scientific.bean.InstructionInfo;
import com.fwxm.scientific.bean.InstructionRw;
import com.fwxm.scientific.service.InstructionInfoManager;
import com.fwxm.scientific.service.InstructionManager;
import com.fwxm.scientific.service.InstructionRwManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Instruction
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-10 13:42:45
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/instruction")
public class InstructionAction extends
		SpringSupportAction<Instruction, InstructionManager> {

	@Autowired
	private InstructionManager instructionManager;
	
	@Autowired
	private InstructionInfoManager instructionInfoManager;

	@Autowired
	private InstructionRwManager instructionRwManager;
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
		JSONObject json= JSON.parseObject(instruction);
		String opinion="";
		if(json.containsKey("opinion")){
			opinion=json.getString("opinion");
		}
		Instruction entity=JSON.parseObject(instruction, Instruction.class);
		try {
			if(entity.getId()==null||entity.getId().equals("")||entity.getStatus().equals("0")){
				entity.setCreateDate(new Date());
				entity.setCreateId(user.getId());
				entity.setCreateMan(user.getName());
				entity.setStatus("0");
				entity.setIsReturn("0");
			}
			if("2".equals(json.getString("type"))){//提交
				entity.setIsReturn("0");
				if(opinion.equals("0")|| "".equals(opinion)){//同意
					if(entity.getStatus().equals("0")){
						entity.setStatus("1");//提交审核
						entity.setAuditId(json.getString("tjUserId"));
						entity.setAuditMan(json.getString("tjUserName"));
						}else if(entity.getStatus().equals("1")){
						entity.setStatus("2");//提交批准
						entity.setAuditDate(new Date());
						entity.setSignId(json.getString("tjUserId"));
						entity.setSignMan(json.getString("tjUserName"));
					}else if(entity.getStatus().equals("2")){
						entity.setStatus("3");//批准完成
						entity.setSignDate(new Date());
					}
					
				}else{//不同意
					entity.setIsReturn("1");
					if(entity.getStatus().equals("1")){
						entity.setStatus("0");//退回编制
//						entity.setAuditOpinion(remark);
						entity.setAuditId(null);
						entity.setAuditMan(null);
					}else if(entity.getStatus().equals("2")){
						entity.setStatus("1");//提交审核
//						entity.setSignOpinion(remark); 
						entity.setAuditId(json.getString("tjUserId"));
						entity.setAuditMan(json.getString("tjUserName"));
						entity.setSignId(null);
						entity.setSignMan(null);
					}
				}
			}
				
		
			
			instructionManager.save(entity); // 保存信息
             for (InstructionInfo info :entity.getInstructionInfo()) {
            	 info.setInstruction(entity);
            	 instructionInfoManager.save(info);
            	 
            	 
            	 if(entity.getStatus().equals("3") && opinion.equals("0")){//审批完成并且审批同意
 					//作业指导任务书表添加值
 					InstructionRw rw=new InstructionRw();
// 					instructionRwManager
 					rw.setProjectName(info.getProjectName());
 					rw.setTjy2InstructionInfoId(info.getId());
 					rw.setType(info.getType());
 					rw.setDevelopContent(info.getReason());
 					rw.setRequirements(info.getRequirements());
 					rw.setStatus("0");
 					rw.setIsReturn("0");
 					instructionRwManager.save(rw);
            	 }

					
					
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
			Instruction instruction = instructionManager.getDetail(id);
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
			Instruction instruction =instructionManager.get(id);
			if(opinion==null&&instruction.getStatus().equals("0")){
				instruction.setStatus("1");//提交审核
			}else if(opinion.equals("0")){
			if(instruction.getStatus().equals("1")){
				instruction.setStatus("2");//提交批准
				instruction.setAuditOpinion(remark);
				instruction.setAuditDate(new Date());
				instruction.setAuditId(user.getId());
				instruction.setAuditMan(user.getName());
			}else if(instruction.getStatus().equals("2")){
				instruction.setStatus("3");//批准完成
				instruction.setSignOpinion(remark);
				instruction.setSignDate(new Date());
				instruction.setSignId(user.getId());
				instruction.setSignMan(user.getName());
			}
			}else if(opinion.equals("1")){
				if(instruction.getStatus().equals("1")){
					instruction.setStatus("0");//退回编制
					instruction.setAuditOpinion(remark);
				}else if(instruction.getStatus().equals("2")){
					instruction.setStatus("1");//提交审核
					instruction.setSignOpinion(remark); 
				}
			}
			instructionManager.save(instruction);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
	/** 获取人员签名**/
	@RequestMapping(value = "getEmpSignId")
	@ResponseBody
	public HashMap<String, Object> getEmpSignId(HttpServletRequest request,String id)
			throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("id", instructionManager.getEmpSignId(id));
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
				Instruction instruction =instructionManager.get(ids[i]);
				instruction.setStatus("99");//删除
				instructionManager.save(instruction);
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
