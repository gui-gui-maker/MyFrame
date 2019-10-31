package com.fwxm.scientific.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwxm.scientific.bean.Instruction;
import com.fwxm.scientific.bean.InstructionInfo;
import com.fwxm.scientific.bean.InstructionProject;
import com.fwxm.scientific.service.InstructionManager;
import com.fwxm.scientific.service.InstructionProjectManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.rbac.manager.UserManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName InstructionProject
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-24 10:48:10
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/instructionProject")
public class InstructionProjectAction extends
		SpringSupportAction<InstructionProject, InstructionProjectManager> {

	@Autowired
	private InstructionProjectManager instructionProjectManager;
	@Autowired
	private InstructionManager instructionManager;
	@Autowired
	UserManager userManager;
	
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
		try {
			String instruction=request.getParameter("instruction").toString();
			JSONObject json= JSON.parseObject(instruction);
			System.out.println(json);
			String opinion="";
			if(json.containsKey("opinion")){
				opinion=json.getString("opinion");
			}
			InstructionProject entity=JSON.parseObject(instruction,InstructionProject.class);
			
			if(entity.getId()==null||entity.getId().equals("")||entity.getStatus().equals("0")){
				entity.setCreateDate(new Date());
				entity.setCreateId(user.getId());
				entity.setCreateMan(user.getName());
				entity.setStatus("0");
			}
			
			if("2".equals(json.getString("tjType"))){//提交
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
						
						//表Instruction添加值
						Instruction bean=new Instruction();
						bean.setStatus("0");
						InstructionInfo beanInfo=new InstructionInfo();
						beanInfo.setProjectName(entity.getProjectName());
						beanInfo.setProjectNameId(entity.getId());
						beanInfo.setReason(entity.getContent());
						beanInfo.setInstruction(bean);
						beanInfo.setForwardMan(entity.getCreateMan());
						beanInfo.setForwardManId(entity.getCreateId());
						User user1=userManager.getUser(entity.getCreateId());
						beanInfo.setForwardBmId(user1.getOrg().getId());
						beanInfo.setForwardBm(user1.getOrg().getOrgName());
						beanInfo.setType(entity.getType());
						List<InstructionInfo> list=new ArrayList<InstructionInfo>();
						list.add(beanInfo);
						bean.setInstructionInfo(list);
						instructionManager.save(bean);
					}
					
				}else{//不同意
					entity.setIsReturn("1");
					if(entity.getStatus().equals("1")){
						entity.setStatus("0");//退回编制
						entity.setAuditId(null);
						entity.setAuditMan(null);
					}else if(entity.getStatus().equals("2")){
						entity.setStatus("1");//提交审核
						entity.setAuditId(json.getString("tjUserId"));
						entity.setAuditMan(json.getString("tjUserName"));
						entity.setSignId(null);
						entity.setSignMan(null);
					}
				}
			}
			if(StringUtil.isEmpty(entity.getProjectNo())){
//				编号生成
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			     Date date = new Date();
			     sdf.format(date);
			     Map<String, String> mapBean=instructionProjectManager.getBeanByYear(sdf.format(date));
			     if(mapBean!=null){
				     int no=Integer.parseInt(mapBean.get("PROJECTNO"))+1;
				     entity.setProjectNo(sdf.format(date)+"-0"+no);
			     }else{
			    	 entity.setProjectNo(sdf.format(date)+"-01");
			     }
			}
		     
		    
		     
			instructionProjectManager.save(entity); // 保存信息
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
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
				InstructionProject instruction =instructionProjectManager.get(ids[i]);
				instruction.setStatus("99");//删除
				instructionProjectManager.save(instruction);
			}
			
			
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
//		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		try {
			InstructionProject instruction =instructionProjectManager.get(id);
			if(opinion==null&&instruction.getStatus().equals("0")){
				instruction.setStatus("1");//提交审核
				instruction.setAuditId(request.getParameter("tjUserId"));
				instruction.setAuditMan(request.getParameter("tjUserName"));
			}else if(opinion.equals("0")){
			if(instruction.getStatus().equals("1")){
				instruction.setStatus("2");//提交批准
				instruction.setAuditOpinion(remark);
				instruction.setAuditDate(new Date());
				instruction.setSignId(request.getParameter("tjUserId"));
				instruction.setSignMan(request.getParameter("tjUserName"));
			}else if(instruction.getStatus().equals("2")){
				instruction.setStatus("3");//批准完成
				instruction.setSignOpinion(remark);
				instruction.setSignDate(new Date());
			}
			}else if(opinion.equals("1")){
				if(instruction.getStatus().equals("1")){
					instruction.setStatus("0");//退回编制
					instruction.setAuditOpinion(remark);
					instruction.setAuditId(null);
					instruction.setAuditMan(null);
				}else if(instruction.getStatus().equals("2")){
					instruction.setStatus("1");//提交审核
					instruction.setSignOpinion(remark); 
					instruction.setAuditId(request.getParameter("tjUserId"));
					instruction.setAuditMan(request.getParameter("tjUserName"));
					instruction.setSignId(null);
					instruction.setSignMan(null);
					
				}
			}
			instructionProjectManager.save(instruction);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			// TODO: handle exception
		}
		
		return map;

	}
}
