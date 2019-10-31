package com.fwxm.scientific.web;

import com.alibaba.fastjson.JSON;
import com.fwxm.scientific.bean.Tjy2ScientificOpinion;
import com.fwxm.scientific.bean.Tjy2ScientificRemark;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.fwxm.scientific.service.Tjy2ScientificRemarkManager;
import com.fwxm.scientific.service.Tjy2ScientificResearchManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.FileUtil;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.report.bean.SysOrg;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("tjy2ScientificResearchAction")
public class Tjy2ScientificResearchAction
		extends SpringSupportAction<Tjy2ScientificResearch, Tjy2ScientificResearchManager> {

	@Autowired
	private Tjy2ScientificResearchManager tjy2ScientificResearchManager;
	@Autowired
	private Tjy2ScientificRemarkManager tjy2ScientificRemarkManager;


	
	
	/**
	 * 获取对应项目类型的编号
	 * @param request
	 * @param typeTag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getProjectNo")
	@ResponseBody
	public HashMap<String, Object> getProjectNo(HttpServletRequest request,String typeTag) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String projectNo = tjy2ScientificResearchManager.getProjectNo(typeTag);
			wrapper.put("success", true);
			wrapper.put("projectNo", projectNo);
		} catch (Exception e) {
			wrapper.put("success", false);
			e.printStackTrace();
		}
		return wrapper;
	}
	
	


    /**
  	 * 保存
  	 * 
  	 * @param request
  	 * @param employeeCert
  	 * @throws Exception
  	 */
  	@RequestMapping(value = "saveScientific")
  	@ResponseBody
  	public HashMap<String, Object> saveScientific(HttpServletRequest request) throws Exception {
  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try {
  			 String base=request.getParameter("base").toString();
  	  		Tjy2ScientificResearch entity=JSON.parseObject(base,Tjy2ScientificResearch.class);
  			if(entity.getId()==null||entity.getId().equals("")){
  				entity.setCreateDate(new Date());
  				entity.setCreateMan(user.getName());
  				entity.setStatus("0");
  				
  			}else{
  				entity.setLastModifyDate(new Date());
  				entity.setLastModifyMan(user.getName());
  			}
  			//判断项目模块类型,为空表示新申报项目，不为空标识已经生成的项目
  			if(entity.getProjectFlag()==null||"".equals(entity.getProjectFlag())){
  				entity.setProjectFlag("1");//表示项目申报模块
  			}
  			//判断是否 修改金额
  			String type=request.getParameter("type");
  			if(type!=null&&!"".equals(type)){
  				if("xg".equals(type)){
  					entity.setStatus("3");//表示修改金额完成
  				}
  				
  			}
  			
  			
            String P100001= request.getParameter("P100001");
            String P200001= request.getParameter("P100002");
            String P300001= request.getParameter("P100003");
            String P400001= request.getParameter("P100004");
            String P500001= request.getParameter("P100005");
            String P600001= request.getParameter("P100006");
            String P700001= request.getParameter("P100007");
           
  			tjy2ScientificResearchManager.save(entity);
  			tjy2ScientificResearchManager.saveBase(entity.getId(), P100001, P200001, P300001, P400001, P500001, P600001, P700001);
  			wrapper.put("success", true);
  		/*	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日"); 
  			String date1=sdf.format(entity.getFillDate());
  			String date2=sdf.format(entity.getStartDate());
  			String date3=sdf.format(entity.getEndDate());*/
  			wrapper.put("data", entity); 
  		/*	wrapper.put("fillDate", date1);
  			wrapper.put("startDate", date2);
  			wrapper.put("endDate", date3);*/


			wrapper.put("id", entity.getId());

  		} catch (Exception e) {
  			log.error("保存信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}

//	//分配审核人审核
//  	@RequestMapping(value = "auditPass")
//  	@ResponseBody
//  	public HashMap<String, Object> auditPass(HttpServletRequest request, String id)  {
//  		
//  		CurrentSessionUser user=SecurityUtil.getSecurityUser();
//  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
//  		try{
//  		
//  		String remark=request.getParameter("remark");
//  		String result=request.getParameter("result");
//  		String ids[]=id.split(",");
//  		for (int i = 0; i < ids.length; i++) {
//  				Tjy2ScientificRemark entity=new Tjy2ScientificRemark();
//  				Tjy2ScientificResearch research=tjy2ScientificResearchManager.get(ids[i]);
//  				research.setRemark(remark);
////  				research.setAuditId(user.getId());
////  				research.setAuditName(user.getName());
//  				
//  				
//  				entity.setCreate_date(new Date());
//				entity.setCreate_man(user.getName());
//				entity.setRemark(remark);
//			
//				if("2".equals(result)){
//					entity.setStatus("0");//退回录入
//					entity.setProcess("审核不通过");
//					research.setAuditStatus("2");
//					research.setStatus("0");//状态改成申请状态
//				}else {
//					entity.setStatus("1");//审核通过
//					entity.setProcess("审核通过");
//					research.setAuditStatus("1");
//					research.setStatus("3");//状态改为通过
//					if(research.getProjectFlag().equals("1")){//判断如果是申报通过时自动生产一条项目管理数据
//						tjy2ScientificResearchManager.reportCopy(research);
//					}
//、
//		} catch (Exception e) {
//			log.error("保存信息：" + e.getMessage());
//			wrapper.put("success", false);
//			wrapper.put("message", "保存信息出错！");
//			e.printStackTrace();
//		}
//		return wrapper;
//	}

	// 分配多个审核人
	@RequestMapping(value = "distribution")
	@ResponseBody
	public HashMap<String, Object> distribution(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String audit_name = request.getParameter("audit_name");// 用户姓名数组
		String audit_id = request.getParameter("audit_id");// 用户id
		try {
		if (audit_name != null && audit_name.length() > 0) {
			String[] ids = id.split(",");// 分配项目id
			String[] audit_names = audit_name.split(",");
			String[] audit_ids = audit_id.split(",");
				for (int i = 0; i < ids.length; i++) {
	
						//分配评分人员
					    tjy2ScientificResearchManager.createGrade(ids[i], audit_names, audit_ids);
					    //分配评分人员成功改变项目流程状态
						Tjy2ScientificResearch research = tjy2ScientificResearchManager.get(ids[i]);
						research.setStatus("2");
						tjy2ScientificResearchManager.save(research);
						wrapper.put("success", true);
				}
			}

			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				wrapper.put("success", false);

				wrapper.put("message", "保存信息出错！");
				return wrapper;
			}
			
		
		return wrapper;

		/*
		 * HashMap<String, Object> wrapper = new HashMap<String, Object>();
		 * String audit_name=request.getParameter("audit_name"); String
		 * audit_id=request.getParameter("audit_id"); String
		 * ids[]=id.split(","); for (int i = 0; i < ids.length; i++) {
		 * Tjy2ScientificResearch
		 * research=tjy2ScientificResearchManager.get(ids[i]);
		 * research.setAuditName(audit_name); research.setAuditId(audit_id);
		 * research.setAuditStatus("0"); research.setStatus("2");
		 * tjy2ScientificResearchManager.save(research);
		 * 
		 * 
		 * } wrapper.put("success", true); return wrapper;
		 */
	}

	// 分配审核人审核
	@RequestMapping(value = "auditDetails")
	@ResponseBody
	public HashMap<String, Object> auditDetails(HttpServletRequest request, String id, String userid) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		Tjy2ScientificResearch tjy2ScientificResearch = tjy2ScientificResearchManager.get(id);
		wrapper.put("peoject_name", tjy2ScientificResearch.getProjectName());
		wrapper.put("project_head", tjy2ScientificResearch.getProjectHead());
		wrapper.put("project_money", tjy2ScientificResearch.getProjectMoney());
		try {
			if (tjy2ScientificResearch.getAuditStatus().equals("2")) {// 审核中状态，分配的审核人可见，可查询操作自己的评分表。
				List<Tjy2ScientificOpinion> list = tjy2ScientificResearchManager.getaudit(id, userid);
				if(list!=null&&list.size()>0){
					for (Tjy2ScientificOpinion tjy2ScientificOpinion : list) {
						wrapper.put("project_audit", tjy2ScientificOpinion.getProject_audit());
						wrapper.put("project_audit_date", tjy2ScientificOpinion.getProject_audit_date());
					}
				}
			}
			List<Tjy2ScientificOpinion> listOpinion = tjy2ScientificResearchManager.getaudit(request.getParameter("id"),userid);
			if(listOpinion!=null&&listOpinion.size()>0){
				for(Tjy2ScientificOpinion tjy2ScientificOpinion : listOpinion){
					if(tjy2ScientificOpinion.getProject_audit_state().equals("1")){
						wrapper.put("grade1", tjy2ScientificOpinion.getProject_grade1());
						wrapper.put("grade2", tjy2ScientificOpinion.getProject_grade2());
						wrapper.put("grade3", tjy2ScientificOpinion.getProject_grade3());
						wrapper.put("grade4", tjy2ScientificOpinion.getProject_grade4());
						wrapper.put("grade5", tjy2ScientificOpinion.getProject_grade5());
						wrapper.put("grade6", tjy2ScientificOpinion.getProject_grade6());
						wrapper.put("grade7", tjy2ScientificOpinion.getProject_grade7());
						wrapper.put("grade8", tjy2ScientificOpinion.getProject_grade8());
						wrapper.put("grade8", tjy2ScientificOpinion.getProject_grade9());
						wrapper.put("grade10", tjy2ScientificOpinion.getProject_grade10());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wrapper;
	}

	@RequestMapping(value = "auditPass")
	@ResponseBody
	public HashMap<String, Object> auditPass(HttpServletRequest request) {

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();

		List<Tjy2ScientificOpinion> list = tjy2ScientificResearchManager.getaudit(request.getParameter("id"),user.getId());// 分配的审核人提交自己的评分表
		List<Tjy2ScientificOpinion> lists = tjy2ScientificResearchManager.getaudit(request.getParameter("id"));// 获取项目的所有评分表
		if (list != null && list.size() > 0) {
			for (Tjy2ScientificOpinion tjy2ScientificOpinion : list) {
				tjy2ScientificOpinion.setProject_grade1(request.getParameter("item1"));
				tjy2ScientificOpinion.setProject_grade2(request.getParameter("item2"));
				tjy2ScientificOpinion.setProject_grade3(request.getParameter("item3"));
				tjy2ScientificOpinion.setProject_grade4(request.getParameter("item4"));
				tjy2ScientificOpinion.setProject_grade5(request.getParameter("item5"));
				tjy2ScientificOpinion.setProject_grade6(request.getParameter("item6"));
				tjy2ScientificOpinion.setProject_grade7(request.getParameter("item7"));
				tjy2ScientificOpinion.setProject_grade8(request.getParameter("item8"));
				tjy2ScientificOpinion.setProject_grade9(request.getParameter("item9"));
				tjy2ScientificOpinion.setProject_grade10(request.getParameter("item10"));
				tjy2ScientificOpinion.setProject_opinion(request.getParameter("project_opinion"));
				tjy2ScientificOpinion.setProject_grade_total(request.getParameter("total"));
				tjy2ScientificOpinion.setProject_audit(request.getParameter("project_audit"));
				tjy2ScientificOpinion.setProject_audit_date(request.getParameter("project_audit_date"));
				tjy2ScientificOpinion.setProject_audit_state("1");
				try {
					tjy2ScientificResearchManager.saveOpinion(tjy2ScientificOpinion);
					if (lists != null && lists.size() > 0) {
						boolean flag = true;
						for (Tjy2ScientificOpinion tjy2ScientificOpinions : lists) {
							if (tjy2ScientificOpinions.getProject_audit_state().equals("0")) {
								flag = false;
							}
						}
						if (flag) {
							Tjy2ScientificResearch research = tjy2ScientificResearchManager.get(request.getParameter("id"));
							research.setStatus("3");
							tjy2ScientificResearchManager.save(research);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			wrapper.put("success", true);
			return wrapper;
		}
		wrapper.put("msg", "保存失败！");
		return wrapper;
	}
	// 确认审批
	@RequestMapping(value = "updateConfirm")
	@ResponseBody
	public HashMap<String, Object> updateConfirm(HttpServletRequest request, String id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		// String result=request.getParameter("result");
		// String back=request.getParameter("back");
		// String remark=request.getParameter("remark");
		//
		// String status=request.getParameter("status");
		 String code=request.getParameter("code");
		try {
			String ids[] = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				// Tjy2ScientificRemark entity=new Tjy2ScientificRemark();
				Tjy2ScientificResearch research = tjy2ScientificResearchManager.get(ids[i]);
				// int status
				// =Integer.parseInt(research.getStatus());//获取申请书当前步骤
				// if(result.equals("1")){
				// entity.setProcess("审核通过");
				// research.setStatus(status);//已审批
				// }else{
				// entity.setProcess("审核不通过");
				// if(back.equals("1")){
				// research.setStatus("0");//退回录入
				// research.setProjectDepartment("1");//表示退回申请书
				// }else{
				// research.setStatus("1");//退回到待审核
				// research.setProjectDepartment("1");//表示退回申请书
				// }
				// }
				// if(remark!=null&&!remark.equals("")){
				// research.setRemark(remark);
				// }
				research.setStatus("1");// 申报提交
				research.setProjectNo(code);//保存分配的项目编号
				tjy2ScientificResearchManager.save(research);

				// entity.setCreate_date(new Date());
				// entity.setCreate_man(user.getName());
				// entity.setRemark(remark);
				// entity.setProject_name(research.getProjectName());
				// entity.setFk_scientific_id(research.getId());
				// if(remark!=null&&!remark.equals("")){
				// tjy2ScientificRemarkManager.save(entity);
				// }
			}

			wrapper.put("success", true);
		} catch (Exception e) {
			log.error("保存信息：" + e.getMessage());
			e.printStackTrace();
			wrapper.put("success", false);
		}
		return wrapper;
	}

	// 提交前判断是否已填完整
	@RequestMapping(value = "getScientific")
	@ResponseBody
	public HashMap<String, Object> getScientific(HttpServletRequest request, String id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			wrapper.put("flag", true);
			// 先判断富文本框
			Object[] o = tjy2ScientificResearchManager.detailBase(id);
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					if (i != 6) {
						Clob clob = (Clob) o[i];
						if (clob == null) {
							wrapper.put("flag", false);
						}
					}
				}
			}
			Tjy2ScientificResearch research = tjy2ScientificResearchManager.get(id);

			if (research.getP800001() == null || research.getP800006() == null || research.getP1100006() == null) {
				wrapper.put("flag", false);
			}
			wrapper.put("success", true);
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}

	// 保存项目完成成果
	@RequestMapping(value = "updateResults")
	@ResponseBody
	public HashMap<String, Object> updateResults(HttpServletRequest request, String id, String projectResultsType,
			String projectResults, String remark) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {

			Tjy2ScientificResearch research = tjy2ScientificResearchManager.get(id);
			research.setStatus("2");// 已完成
			research.setProjectResults(projectResults);
			research.setProjectResultsType(projectResultsType);
			research.setRemark(remark);
			tjy2ScientificResearchManager.save(research);

			wrapper.put("success", true);
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}

	// 判断值是否更改
	@RequestMapping(value = "judgeBase")
	@ResponseBody
	public HashMap<String, Object> judgeBase(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			// 判断实体类
			String base = request.getParameter("base").toString();
			Tjy2ScientificResearch entity = JSON.parseObject(base, Tjy2ScientificResearch.class);
			Tjy2ScientificResearch entity1 = tjy2ScientificResearchManager.get(entity.getId());
			String P100001 = request.getParameter("P100001");
			String P200001 = request.getParameter("P100002");
			String P300001 = request.getParameter("P100003");
			String P400001 = request.getParameter("P100004");
			String P500001 = request.getParameter("P100005");
			String P600001 = request.getParameter("P100006");
			String P700001 = request.getParameter("P100007");
			// 先判断富文本框是否更改
			Object[] o = tjy2ScientificResearchManager.detailBase(entity.getId());
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					Clob clob = (Clob) o[i];
					if (clob != null) {
						if (i == 0) {
							if (!P100001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								System.out.println(clob.getSubString((long) 1, (int) clob.length()));
								wrapper.put("judge", true);
							}
						} else if (i == 1) {
							if (!P200001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						} else if (i == 2) {
							if (!P300001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						} else if (i == 3) {
							if (!P400001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						} else if (i == 4) {
							if (!P500001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						} else if (i == 5) {
							if (!P600001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						} else if (i == 6) {
							if (!P700001.equals(clob.getSubString((long) 1, (int) clob.length()))) {
								wrapper.put("judge", true);
							}
						}

					} else {
						if (i == 0 && !P100001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 1 && !P200001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 2 && !P300001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 3 && !P400001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 4 && !P500001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 5 && !P600001.equals("")) {
							wrapper.put("judge", true);
						} else if (i == 6 && !P700001.equals("")) {
							wrapper.put("judge", true);
						}
					}
				}
			}
			Field[] field = entity.getClass().getDeclaredFields();
			Field[] field1 = entity1.getClass().getDeclaredFields();
			for (int j = 0; j < field.length; j++) {// 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (!name.equals("LastModifyDate") && !name.equals("LastModifyMan")) {
					if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																	// "，后面跟类名
						Method m = entity.getClass().getMethod("get" + name);
						String value = (String) m.invoke(entity); // 调用getter方法获取属性值
						Method m1 = entity1.getClass().getMethod("get" + name);
						String value1 = (String) m1.invoke(entity1); // 调用getter方法获取属性值
						if (value != null && !value.equals("")) {
							if (!value.equals(value1)) {
								System.out.println("进啦");
								wrapper.put("judge", true);
							}
						} else {
							value = null;
							if (value != value1) {
								System.out.println("进啦1");
								wrapper.put("judge", true);
							}
						}

					}
					if (type.equals("class java.util.Date")) {
						Method m = entity.getClass().getMethod("get" + name);
						Date value = (Date) m.invoke(entity);
						Method m1 = entity1.getClass().getMethod("get" + name);
						Date value1 = (Date) m1.invoke(entity1);
						if (value != null && !value.equals("")) {
							if (!value.equals(value1)) {
								System.out.println("进啦2");
								wrapper.put("judge", true);
							}
						} else {
							value = null;
							if (value != value1) {
								System.out.println("进啦3");
								wrapper.put("judge", true);
							}
						}

					}
				}
			}

			wrapper.put("success", true);
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}

	/**
	 * 
	 * @param request
	 *            请求
	 * @param deviceId
	 *            设备ID
	 * @param orderDoc
	 *            文档名字
	 * @param id
	 *            ID
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveO")
	public HashMap<String, Object> saveO(HttpServletRequest request, String deviceId, String orderDoc, String id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 所有文件
			Map files = multipartRequest.getFileMap();
			if (files.size() > 0) {// 获取监察指令文档的信息
				Iterator fileNames = multipartRequest.getFileNames();
				Tjy2ScientificResearch research = new Tjy2ScientificResearch();
				String fileName = (String) fileNames.next();
				CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);

				if (id != null) {
					research = tjy2ScientificResearchManager.get(id);
				}
				research.setFileName(file.getOriginalFilename());
				log.debug((new StringBuilder("上传的文件：")).append(file.getOriginalFilename()).toString());
				// 保存文档和记录内容
				tjy2ScientificResearchManager.saveO(file.getInputStream(), research);

				wrapper.put("success", true);
			}

		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}

	/**
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFile")
	public void getFile(String id, HttpServletResponse response) throws Exception {
		byte[] order = tjy2ScientificResearchManager.getFile(id);
		Tjy2ScientificResearch file = tjy2ScientificResearchManager.get(id);
		// 下载文档
		FileUtil.download(response, order, file.getFileName(), "application/octet-stream");
	}

	/**
	 * 获取详情
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "detailBase")
	@ResponseBody
	public HashMap<String, Object> detailBase(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			Tjy2ScientificResearch file = tjy2ScientificResearchManager.get(id);
			Object[] o = tjy2ScientificResearchManager.detailBase(id);
			if (o != null) {

				for (int i = 0; i < o.length; i++) {
					Clob clob = (Clob) o[i];
					if (clob != null) {
						int j = i + 1;
						wrapper.put("P" + j + "00001", clob.getSubString((long) 1, (int) clob.length()));
					}
				}
			}
			wrapper.put("data", file);
			wrapper.put("success", true);

		} catch (Exception e) {
			log.error("保存信息：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "保存信息出错！");
			e.printStackTrace();
		}

		return wrapper;
	}

	@RequestMapping(value = "myInput")
	@SuppressWarnings("all")
	public void myInput(HttpServletRequest request, HttpServletResponse response, String id, String types)
			throws Exception {
		Tjy2ScientificResearch entity = tjy2ScientificResearchManager.get(id);
		Object[] o = tjy2ScientificResearchManager.detailBase(id);// 获取1-7页内容
		Clob clob1 = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (int i1 = 0; i1 < o.length; i1++) {
			Clob clob = (Clob) o[i1];
			if (clob != null) {
				String content = clob.getSubString((long) 1, (int) clob.length());
				Map<String, String> map = new HashMap<String, String>();
				if (i1 == 0) {
					map.put("number", "一、");
					map.put("title", "项目研究的目的、意义");
				}
				if (i1 == 1) {
					map.put("number", "二、");
					map.put("title", "国内外同类研究现状分析及存在的问题（含查新结果）");
				}
				if (i1 == 2) {
					map.put("number", "三、");
					map.put("title", "项目研究内容");
				}
				if (i1 == 3) {
					map.put("number", "四、");
					map.put("title", "项目研究预期达到的最终目标（包含拟解决的关键问题、研究结果、技术指标、经济及社会效益）");
				}
				if (i1 == 4) {
					map.put("number", "五、");
					map.put("title", "项目研究采用的研究方法、技术路线、实验方案、创新之处及可行性分析");
				}
				if (i1 == 5) {
					map.put("number", "六、");
					map.put("title", "项目研究基础（课题组人员情况、已有的工作基础、仪器设备、研究条件、组织保障措施）");
				}
				if (i1 == 6) {
					map.put("number", "七、");
					map.put("title", "项目合作研究必要性说明（有合作单位时说明）");
				}
				String newContent = "";
				if (content.indexOf("<img") > 0) {
					String[] stringSplit_img = content.split("<img");// 分割<img>标签
					for (int i = 0; i < stringSplit_img.length; i++) {
						if (stringSplit_img[i].indexOf("src") > 0) {
							String img = "<img " + stringSplit_img[i];
							int strat = img.indexOf("src=");
							String noSrcImg = img.substring(strat + 5);
							int end = noSrcImg.indexOf("\"");
							String img_src = noSrcImg.substring(0, end);
							// System.out.println(img_src);
							String fileType = img_src.substring(0, img_src.indexOf(";"));
							if ((fileType.indexOf("jpg") >= 0 || fileType.indexOf("jpe") >= 0)) {
								fileType = "jpg";
							} else if (fileType.indexOf("png") >= 0) {
								fileType = "png";
							}
							Thread.sleep(1000);
							String path = tjy2ScientificResearchManager
									.inputFile(img_src.substring(22, img_src.length()), fileType);
							// System.out.println(path);
							String newsrc = img.replace(img_src, path);
							newContent += newsrc;
						} else {
							newContent += stringSplit_img[i];
						}
					}
				} else {
					newContent = content;
				}
				map.put("content", newContent.replace("</p>", "</p><br/>"));
				list.add(map);
			}
		}

		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("data", entity);

		String html = ServletUtils.forward(request, response, "/app/fwxm/scientific/scientific_dc.jsp"); // 转发请求到jsp，返回解析之后的内容而不是输出到浏览器
		// System.out.println(html);
		String fileName = "";
		if (types != null && types.equals("rw")) {
			fileName = "科研项目任务书-" + entity.getProjectName();
		} else {
			fileName = "科研项目申请书-" + entity.getProjectName();
		}
		request.setCharacterEncoding("utf-8");

		Html2PdfUtil.convertHtml2PDF(response, html, fileName);
		// return "/app/fwxm/scientific/scientific_dc";
	}

	/**
	 * 导出word
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "input")
	@ResponseBody
	public void input(HttpServletRequest request, HttpServletResponse response, String id, String types)
			throws Exception {
		Tjy2ScientificResearch entity = tjy2ScientificResearchManager.get(id);
		Object[] o = tjy2ScientificResearchManager.detailBase(id);// 获取每页内容
		Object[] o1 = tjy2ScientificResearchManager.detailBase("10000");// 获取申请书导出模板
		Clob clob1 = null;
		if (types != null && types.equals("rw")) {
			clob1 = (Clob) o1[1];
		} else {
			clob1 = (Clob) o1[0];
		}

		String fileMb = clob1.getSubString((long) 1, (int) clob1.length());
		String content = "";// 装载每页内容
		byte data[] = null;
		for (int i1 = 0; i1 < o.length; i1++) {
			Clob clob = (Clob) o[i1];
			if (clob != null) {
				int j1 = i1 + 1;
				// tjy2ScientificResearchManager.input(clob.getSubString((long)1,(int)clob.length()));
				try {
					content = clob.getSubString((long) 1, (int) clob.length());
					String newContent = "";
					if (content.indexOf("<img") > 0) {
						String[] stringSplit_img = content.split("<img");// 分割<img>标签
						for (int i = 0; i < stringSplit_img.length; i++) {
							if (stringSplit_img[i].indexOf("src") > 0) {
								String img = "<img " + stringSplit_img[i];
								int strat = img.indexOf("src=");
								String noSrcImg = img.substring(strat + 5);
								int end = noSrcImg.indexOf("\"");
								String img_src = noSrcImg.substring(0, end);
								// System.out.println(img_src);
								String fileType = img_src.substring(0, img_src.indexOf(";"));
								if ((fileType.indexOf("jpg") >= 0 || fileType.indexOf("jpe") >= 0)) {
									fileType = "jpg";
								} else if (fileType.indexOf("png") >= 0) {
									fileType = "png";
								}
								Thread.sleep(1000);
								String path = tjy2ScientificResearchManager
										.inputFile(img_src.substring(22, img_src.length()), fileType);
								// System.out.println(path);

								String newsrc = img.replace(img_src, path);
								newContent += newsrc;
							} else {
								newContent += stringSplit_img[i];
							}
						}
						fileMb = fileMb.replace("${P" + j1 + "00001}", newContent);
						// for (int i = 0; i < stringSplit_img.length; i++) {
						// if(i!=0){
						// String[]
						// stringSplit_p=("<img"+stringSplit_img[i]).split("</p>");//分割<p>标签
						// for (int j = 0; j < stringSplit_p.length; j++) {
						// if(j!=0){
						// content="</p>"+stringSplit_p[j];
						// }else{
						// //判断是否为img标签
						// if(stringSplit_p[j].indexOf("data")>-1){
						// //此时stringSplit_p[j]已是完整的<img/>标签字符串
						// //截取标签base64
						// for(String s:stringSplit_p[j].split(" ")){
						// if(s.startsWith("src=")){
						// s=s.replace("src=\"", "");
						// s=s.replace("\"", "");
						// String[] type=s.split(",");//将base64分开解析
						// String typeFile="";
						// if((type[0].substring(11,14).equals("jpg")||type[0].substring(11,14).equals("jpe"))){
						// typeFile="jpg";
						// }else if(type[0].substring(11,14).equals("png")){
						// typeFile="png";
						// }
						// Thread.sleep(1000);
						// String
						// path=tjy2ScientificResearchManager.inputFile(type[1],
						// typeFile);
						// //将解码后的图片替换到数据中
						// String imgPath="";
						// if(stringSplit_p[j].indexOf("style")>0){
						// imgPath=stringSplit_p[j].replace(stringSplit_p[j].substring(stringSplit_p[j].indexOf("src=")+5,stringSplit_p[j].indexOf("style")-2),
						// path);
						// }else{
						// imgPath=stringSplit_p[j].replace(stringSplit_p[j].substring(stringSplit_p[j].indexOf("src=")+5,stringSplit_p[j].indexOf("/>")-2),
						// path);
						// }
						//
						// content=content.replace(stringSplit_p[j], imgPath);
						//
						// }
						//
						//
						// }
						//
						// fileMb=fileMb.replace("${P"+j1+"00001}", content);
						// }
						// }
						// }
						// }
						// }
					} else {
						newContent = content;
						fileMb = fileMb.replace("${P" + j1 + "00001}", newContent);
					}
				} catch (Exception e) {
					log.error("保存信息：" + e.getMessage());
					e.printStackTrace();
				}

			} else {
				i1 = i1 + 1;
				fileMb = fileMb.replace("${P" + i1 + "00001}", "");
			}
		}
		// 替换模板中的占位符

		fileMb = fileMb.replace("${projectName}", entity.getProjectName() == null ? "" : entity.getProjectName());
		fileMb = fileMb.replace("${projectType}", entity.getProjectType() == null ? "" : entity.getProjectType());
		fileMb = fileMb.replace("${professionalType}",
				entity.getProfessionalType() == null ? "" : entity.getProfessionalType());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String date1 = sdf.format(entity.getFillDate());
		String date2 = sdf.format(entity.getStartDate());
		String date3 = sdf.format(entity.getEndDate());
		fileMb = fileMb.replace("${startEndDate}", date2 + "-" + date3);
		fileMb = fileMb.replace("${projectHead}", entity.getProjectHead() == null ? "" : entity.getProjectHead());
		fileMb = fileMb.replace("${fillDate}", date1);
		if (types != null && types.equals("rw")) {// 判断是否为任务书
			fileMb = fileMb.replace("${projectNo}", entity.getProjectNo() == null ? "" : entity.getProjectNo());
			fileMb = fileMb.replace("${projectNos}", entity.getProjectNo() == null ? "" : entity.getProjectNo());
			fileMb = fileMb.replace("${money}", entity.getP900001() == null ? "" : entity.getP900001());
			fileMb = fileMb.replace("${startEnd}", date1.substring(0, 5) + "-" + date3.substring(0, 5));
		}

		fileMb = fileMb.replace("${P800001}", entity.getP800001() == null ? "" : entity.getP800001());
		fileMb = fileMb.replace("${P800002}", entity.getP800002() == null ? "" : entity.getP800002());
		fileMb = fileMb.replace("${P800003}", entity.getP800003() == null ? "" : entity.getP800003());
		fileMb = fileMb.replace("${P800006}", entity.getP800006() == null ? "" : entity.getP800006());
		fileMb = fileMb.replace("${P800007}", entity.getP800007() == null ? "" : entity.getP800007());
		fileMb = fileMb.replace("${P800008}", entity.getP800008() == null ? "" : entity.getP800008());
		fileMb = fileMb.replace("${P900001}", entity.getP900001() == null ? "" : entity.getP900001());
		fileMb = fileMb.replace("${P900002}", entity.getP900002() == null ? "" : entity.getP900002());
		fileMb = fileMb.replace("${P900003}", entity.getP900003() == null ? "" : entity.getP900003());
		fileMb = fileMb.replace("${P900004}", entity.getP900004() == null ? "" : entity.getP900004());
		fileMb = fileMb.replace("${P900005}", entity.getP900005() == null ? "" : entity.getP900005());
		fileMb = fileMb.replace("${P900006}", entity.getP900006() == null ? "" : entity.getP900006());
		fileMb = fileMb.replace("${P900007}", entity.getP900007() == null ? "" : entity.getP900007());
		fileMb = fileMb.replace("${P900008}", entity.getP900008() == null ? "" : entity.getP900008());
		fileMb = fileMb.replace("${P900009}", entity.getP900009() == null ? "" : entity.getP900009());
		fileMb = fileMb.replace("${P9000010}", entity.getP9000010() == null ? "" : entity.getP9000010());
		fileMb = fileMb.replace("${P9000011}", entity.getP9000011() == null ? "" : entity.getP9000011());
		fileMb = fileMb.replace("${P9000012}", entity.getP9000012() == null ? "" : entity.getP9000012());
		fileMb = fileMb.replace("${P1000001}", entity.getP1000001() == null ? "" : entity.getP1000001());
		fileMb = fileMb.replace("${P1000002}", entity.getP1000002() == null ? "" : entity.getP1000002());
		fileMb = fileMb.replace("${P1000003}", entity.getP1000003() == null ? "" : entity.getP1000003());
		fileMb = fileMb.replace("${P1000004}", entity.getP1000004() == null ? "" : entity.getP1000004());
		fileMb = fileMb.replace("${P1000005}", entity.getP1000005() == null ? "" : entity.getP1000005());
		fileMb = fileMb.replace("${P1000006}", entity.getP1000006() == null ? "" : entity.getP1000006());
		fileMb = fileMb.replace("${P1000007}", entity.getP1000007() == null ? "" : entity.getP1000007());
		fileMb = fileMb.replace("${P1000008}", entity.getP1000008() == null ? "" : entity.getP1000008());
		fileMb = fileMb.replace("${P1000009}", entity.getP1000009() == null ? "" : entity.getP1000009());
		fileMb = fileMb.replace("${P10000010}", entity.getP10000010() == null ? "" : entity.getP10000010());
		fileMb = fileMb.replace("${P10000011}", entity.getP10000011() == null ? "" : entity.getP10000011());
		fileMb = fileMb.replace("${P10000012}", entity.getP10000012() == null ? "" : entity.getP10000012());
		fileMb = fileMb.replace("${P10000013}", entity.getP10000013() == null ? "" : entity.getP10000013());
		fileMb = fileMb.replace("${P10000014}", entity.getP10000014() == null ? "" : entity.getP10000014());
		fileMb = fileMb.replace("${P10000015}", entity.getP10000015() == null ? "" : entity.getP10000015());
		fileMb = fileMb.replace("${P10000016}", entity.getP10000016() == null ? "" : entity.getP10000016());
		fileMb = fileMb.replace("${P10000017}", entity.getP10000017() == null ? "" : entity.getP10000017());
		fileMb = fileMb.replace("${P10000018}", entity.getP10000018() == null ? "" : entity.getP10000018());
		fileMb = fileMb.replace("${P10000019}", entity.getP10000019() == null ? "" : entity.getP10000019());
		fileMb = fileMb.replace("${P10000020}", entity.getP10000020() == null ? "" : entity.getP10000020());
		fileMb = fileMb.replace("${P1100001}", entity.getP1100001() == null ? "" : entity.getP1100001());
		fileMb = fileMb.replace("${P1100002}", entity.getP1100002() == null ? "" : entity.getP1100002());
		fileMb = fileMb.replace("${P1100003}", entity.getP1100003() == null ? "" : entity.getP1100003());
		fileMb = fileMb.replace("${P1100004}", entity.getP1100004() == null ? "" : entity.getP1100004());
		fileMb = fileMb.replace("${P1100005}", entity.getP1100005() == null ? "" : entity.getP1100005());
		fileMb = fileMb.replace("${P1100006}", entity.getP1100006() == null ? "" : entity.getP1100006());
		fileMb = fileMb.replace("${P1100007}", entity.getP1100007() == null ? "" : entity.getP1100007());
		fileMb = fileMb.replace("${P1100008}", entity.getP1100008() == null ? "" : entity.getP1100008());
		fileMb = fileMb.replace("${P1100009}", entity.getP1100009() == null ? "" : entity.getP1100009());
		fileMb = fileMb.replace("${P11000010}", entity.getP11000010() == null ? "" : entity.getP11000010());
		fileMb = fileMb.replace("${P11000011}", entity.getP11000011() == null ? "" : entity.getP11000011());
		fileMb = fileMb.replace("${P11000016}", entity.getP11000016() == null ? "" : entity.getP11000016());
		fileMb = fileMb.replace("${P11000017}", entity.getP11000017() == null ? "" : entity.getP11000017());
		fileMb = fileMb.replace("${P11000018}", entity.getP11000018() == null ? "" : entity.getP11000018());
		fileMb = fileMb.replace("${P11000019}", entity.getP11000019() == null ? "" : entity.getP11000019());
		fileMb = fileMb.replace("${P11000020}", entity.getP11000020() == null ? "" : entity.getP11000020());
		fileMb = fileMb.replace("${P11000021}", entity.getP11000021() == null ? "" : entity.getP11000021());
		fileMb = fileMb.replace("${P11000026}", entity.getP11000026() == null ? "" : entity.getP11000026());
		fileMb = fileMb.replace("${P11000027}", entity.getP11000027() == null ? "" : entity.getP11000027());
		fileMb = fileMb.replace("${P11000028}", entity.getP11000028() == null ? "" : entity.getP11000028());
		fileMb = fileMb.replace("${P11000029}", entity.getP11000029() == null ? "" : entity.getP11000029());
		fileMb = fileMb.replace("${P11000030}", entity.getP11000030() == null ? "" : entity.getP11000030());
		fileMb = fileMb.replace("${P11000031}", entity.getP11000031() == null ? "" : entity.getP11000031());
		fileMb = fileMb.replace("${P11000036}", entity.getP11000036() == null ? "" : entity.getP11000036());
		fileMb = fileMb.replace("${P11000037}", entity.getP11000037() == null ? "" : entity.getP11000037());
		fileMb = fileMb.replace("${P11000038}", entity.getP11000038() == null ? "" : entity.getP11000038());
		fileMb = fileMb.replace("${P11000039}", entity.getP11000039() == null ? "" : entity.getP11000039());
		fileMb = fileMb.replace("${P11000040}", entity.getP11000040() == null ? "" : entity.getP11000040());
		fileMb = fileMb.replace("${P11000041}", entity.getP11000041() == null ? "" : entity.getP11000041());

		if (entity.getP800004() != null && !entity.getP800004().equals("")) {
			String talbe8 = " <tr style='mso-yfti-irow:2;mso-yfti-lastrow:yes;height:49.05pt'>"
					+ " <td width=121 style='width:90.45pt;border:solid windowtext 1.0pt;border-top:"
					+ " none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;"
					+ " padding:0cm 5.4pt 0cm 5.4pt;height:49.05pt'>"
					+ " <p class=MsoNormal align=right style='text-align:right;layout-grid-mode:char'><span"
					+ " style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + entity.getP800004()
					+ "年度<span" + " lang=EN-US><o:p></o:p></span></span></p>" + " </td>"
					+ " <td width=448 style='width:335.65pt;border-top:none;border-left:none;"
					+ " border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;"
					+ " mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;"
					+ " mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:49.05pt'>"
					+ " <p class=MsoNormal style='layout-grid-mode:char'><span lang=EN-US"
					+ " style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + entity.getP800009()
					+ "<o:p>&nbsp;</o:p></span></p>" + "  </td>" + "</tr>";
			String table9_1 = " <td width=93 style='width:70.05pt;border:solid windowtext 1.0pt;border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt; "
					+ "  padding:0cm 5.4pt 0cm 5.4pt'><p class=MsoNormal align=right style='text-align:right;layout-grid-mode:char'><span style='font-size:14.0pt;font-family:方正仿宋简体'>"
					+ entity.getP9000013() + "年<span lang=EN-US><o:p></o:p></span></span></p></td> ";
			String table9_2 = "<td width=93 style='width:70.05pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt; "
					+ " mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'> "
					+ " <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:char'><span lang=EN-US style='font-size:14.0pt;font-family:方正仿宋简体'>"
					+ entity.getP9000014() + "<o:p>&nbsp;</o:p></span></p></td>";
			String table9_3 = "<td width=93 style='width:70.05pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt; "
					+ " mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'><p class=MsoNormal align=center style='text-align:center;layout-grid-mode:char'><span lang=EN-US style='font-size:14.0pt;font-family:方正仿宋简体'>"
					+ entity.getP9000015() + "<o:p>&nbsp;</o:p></span></p></td>";
			if (entity.getP800005() != null && !entity.getP800005().equals("")) {
				talbe8 = talbe8 + "<tr style='mso-yfti-irow:2;mso-yfti-lastrow:yes;height:49.05pt'>"
						+ "<td width=121 style='width:90.45pt;border:solid windowtext 1.0pt;border-top:"
						+ " none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;"
						+ "padding:0cm 5.4pt 0cm 5.4pt;height:49.05pt'>"
						+ "<p class=MsoNormal align=right style='text-align:right;layout-grid-mode:char'><span"
						+ " style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>"
						+ entity.getP800005() + "年度<span" + " lang=EN-US><o:p></o:p></span></span></p>" + " </td>"
						+ " <td width=448 style='width:335.65pt;border-top:none;border-left:none;"
						+ " border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;"
						+ " mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;"
						+ " mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:49.05pt'>"
						+ " <p class=MsoNormal style='layout-grid-mode:char'><span lang=EN-US"
						+ " style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>"
						+ entity.getP8000010() + "<o:p>&nbsp;</o:p></span></p>" + "  </td>" + "</tr>";
				table9_1 = table9_1
						+ " <td width=93 style='width:70.05pt;border:solid windowtext 1.0pt;border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt; "
						+ "  padding:0cm 5.4pt 0cm 5.4pt'><p class=MsoNormal align=right style='text-align:right;layout-grid-mode:char'><span style='font-size:14.0pt;font-family:方正仿宋简体'>"
						+ entity.getP9000016() + "<span lang=EN-US><o:p></o:p></span></span></p></td> ";
				table9_2 = table9_2
						+ "<td width=93 style='width:70.05pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt; "
						+ " mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'> "
						+ " <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:char'><span lang=EN-US style='font-size:14.0pt;font-family:方正仿宋简体'>"
						+ entity.getP9000017() + "<o:p>&nbsp;</o:p></span></p></td>";
				table9_3 = table9_3
						+ "<td width=93 style='width:70.05pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt; "
						+ " mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'><p class=MsoNormal align=center style='text-align:center;layout-grid-mode:char'><span lang=EN-US style='font-size:14.0pt;font-family:方正仿宋简体'>"
						+ entity.getP9000018() + "<o:p>&nbsp;</o:p></span></p></td>";
			}
			fileMb = fileMb.replace("${table8_1}", talbe8);
			fileMb = fileMb.replace("${table9_1}", table9_1);
			fileMb = fileMb.replace("${table9_2}", table9_2);
			fileMb = fileMb.replace("${table9_3}", table9_3);
		} else {
			fileMb = fileMb.replace("${table8_1}", "");
			fileMb = fileMb.replace("${table9_1}", "");
			fileMb = fileMb.replace("${table9_2}", "");
			fileMb = fileMb.replace("${table9_3}", "");
		}
		if (entity.getP11000012() != null && !entity.getP11000012().equals("")) {
			String table11_1 = table11(entity.getP11000012(), entity.getP11000022(), entity.getP11000032(),
					entity.getP11000042());
			fileMb = fileMb.replace("rowspan=7", "rowspan=8");
			if (entity.getP11000013() != null && !entity.getP11000013().equals("")) {
				table11_1 = table11_1 + table11(entity.getP11000013(), entity.getP11000023(), entity.getP11000033(),
						entity.getP11000043());
				fileMb = fileMb.replace("rowspan=8", "rowspan=9");
				if (entity.getP11000014() != null && !entity.getP11000014().equals("")) {
					table11_1 = table11_1 + table11(entity.getP11000014(), entity.getP11000024(), entity.getP11000034(),
							entity.getP11000044());
					fileMb = fileMb.replace("rowspan=9", "rowspan=10");
					if (entity.getP11000015() != null && !entity.getP11000015().equals("")) {
						table11_1 = table11_1 + table11(entity.getP11000015(), entity.getP11000025(),
								entity.getP11000035(), entity.getP11000045());
						fileMb = fileMb.replace("rowspan=10", "rowspan=11");
						if (entity.getP11000046() != null && !entity.getP11000046().equals("")) {
							table11_1 = table11_1 + table11(entity.getP11000046(), entity.getP11000051(),
									entity.getP11000056(), entity.getP11000061());
							fileMb = fileMb.replace("rowspan=11", "rowspan=12");
							if (entity.getP11000047() != null && !entity.getP11000047().equals("")) {
								table11_1 = table11_1 + table11(entity.getP11000047(), entity.getP11000052(),
										entity.getP11000057(), entity.getP11000062());
								fileMb = fileMb.replace("rowspan=12", "rowspan=13");
								if (entity.getP11000048() != null && !entity.getP11000048().equals("")) {
									table11_1 = table11_1 + table11(entity.getP11000048(), entity.getP11000053(),
											entity.getP11000058(), entity.getP11000063());
									fileMb = fileMb.replace("rowspan=13", "rowspan=14");
									if (entity.getP11000049() != null && !entity.getP11000049().equals("")) {
										table11_1 = table11_1 + table11(entity.getP11000049(), entity.getP11000054(),
												entity.getP11000059(), entity.getP11000064());
										fileMb = fileMb.replace("rowspan=14", "rowspan=15");
										if (entity.getP11000050() != null && !entity.getP11000050().equals("")) {
											table11_1 = table11_1
													+ table11(entity.getP11000050(), entity.getP11000055(),
															entity.getP11000060(), entity.getP11000065());
											fileMb = fileMb.replace("rowspan=15", "rowspan=16");

										}
									}
								}
							}
						}
					}
				}
			}
			fileMb = fileMb.replace("${table11_1}", table11_1);
		} else {
			fileMb = fileMb.replace("${table11_1}", "");
		}

		String content1 = "<html>" + fileMb + "</html>";
		byte b[] = content1.getBytes("utf-8"); // 这里是必须要设置编码的，不然导出中文就会乱码。
		ByteArrayInputStream bais = new ByteArrayInputStream(b);// 将字节数组包装到流中

		// 关键地方
		// 生成word格式

		POIFSFileSystem poifs = new POIFSFileSystem();
		DirectoryEntry directory = poifs.getRoot();
		DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		// 输出文件
		String fileName = "";
		if (types != null && types.equals("rw")) {
			fileName = "科研项目任务书-" + entity.getProjectName();
		} else {
			fileName = "科研项目申请书-" + entity.getProjectName();
		}

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/msword");// 导出word格式
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".doc").getBytes(), "iso-8859-1"));

		OutputStream ostream = response.getOutputStream();
		poifs.writeFilesystem(ostream);
		bais.close();
		ostream.close();

	}

	public String table11(String value, String value1, String value2, String value3) {
		String table = "<tr style='mso-yfti-irow:9;page-break-inside:avoid;height:30.65pt'> "
				+ " <td width=128 style='width:95.9pt;border-top:none;border-left:none; "
				+ " border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt; "
				+ " mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt; "
				+ " mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.65pt'> "
				+ " <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt; "
				+ " mso-line-height-rule:exactly;layout-grid-mode:char'><span style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>课题组成员"
				+ "<span lang=EN-US><o:p></o:p></span></span></p></td> "
				+ " <td width=67 style='width:50.1pt;border-top:none;border-left:none;border-bottom: "
				+ " solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt: "
				+ " solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt: "
				+ " solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.65pt'> "
				+ "  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt; "
				+ " mso-line-height-rule:exactly;layout-grid-mode:char'><span lang=EN-US "
				+ " style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + value
				+ "<o:p>&nbsp;</o:p></span></p></td> "
				+ "  <td width=74 style='width:55.75pt;border-top:none;border-left:none; "
				+ "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt; "
				+ "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt; "
				+ "  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.65pt'> "
				+ "  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt; "
				+ "  mso-line-height-rule:exactly;layout-grid-mode:char'><span lang=EN-US "
				+ "  style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + value1
				+ "<o:p>&nbsp;</o:p></span></p> " + "  </td> "
				+ "  <td width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom: "
				+ "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt: "
				+ "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt: "
				+ "  solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;padding: "
				+ "  0cm 5.4pt 0cm 5.4pt;height:30.65pt'> "
				+ "  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt; "
				+ "  mso-line-height-rule:exactly;layout-grid-mode:char'><span lang=EN-US "
				+ "  style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + value2
				+ "<o:p>&nbsp;</o:p></span></p> " + " </td> "
				+ " <td width=175 style='width:131.3pt;border-top:none;border-left:none; "
				+ "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt; "
				+ "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt; "
				+ "  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt; "
				+ "  padding:0cm 5.4pt 0cm 5.4pt;height:30.65pt'> "
				+ "  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt; "
				+ "  mso-line-height-rule:exactly;layout-grid-mode:char'><span lang=EN-US "
				+ "  style='font-size:14.0pt;mso-bidi-font-size:12.0pt;font-family:方正仿宋简体'>" + value3
				+ "<o:p>&nbsp;</o:p></span></p></td></tr>";
		return table;
	}

	/**
	 * 修改添加按钮权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editeAddType")
	@ResponseBody
	public HashMap<String, Object> editeAddType(HttpServletRequest request) {
		try {
			String type = request.getParameter("type");
			tjy2ScientificResearchManager.updateAddType(type);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper();
		}
	}
	/**
	 * 查询科研报销金额
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryMoney")
  	@ResponseBody
	public HashMap<String, Object> queryMoney(HttpServletRequest request,String scientificId,String fyType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			
			String temp = tjy2ScientificResearchManager.queryMoney(scientificId,fyType);
			map.put("success", true);
			map.put("queryText", temp);
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("queryText", e.getMessage());
		}
		
		
		return map;
	}
	/**
	 * 查询科研报销金额
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryMoneyOnly")
  	@ResponseBody
	public HashMap<String, Object> queryMoneyOnly(HttpServletRequest request,String bxId,String scientificId,String fyType,String money) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			
			String temp = tjy2ScientificResearchManager.queryMoneyOnly(scientificId,fyType,money,bxId);
			map.put("success", true);
			map.put("queryText", temp);
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("queryText", e.getMessage());
		}
		
		
		return map;
	}
	
}