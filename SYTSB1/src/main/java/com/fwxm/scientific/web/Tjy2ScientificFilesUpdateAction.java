package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.Tjy2ScientificFilesUpdate;
import com.fwxm.scientific.dao.Tjy2ScientificFilesUpdateDao;
import com.fwxm.scientific.service.Tjy2ScientificFilesUpdateManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityFiles;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.bean.QualityUpdateYijina;
import com.lsts.qualitymanage.service.QualityFilesManager;
import com.lsts.qualitymanage.service.QualityUpdateFileManager;
import com.lsts.qualitymanage.service.QualityUpdateYijinaManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

@Controller
@RequestMapping("tjy2ScientificFilesUpdateAction")
public class Tjy2ScientificFilesUpdateAction extends SpringSupportAction<Tjy2ScientificFilesUpdate, Tjy2ScientificFilesUpdateManager> {

    @Autowired
    private Tjy2ScientificFilesUpdateManager  tjy2ScientificFilesUpdateManager;
    @Autowired
    private Tjy2ScientificFilesUpdateDao tjy2ScientificFilesUpdateDao;
    @Autowired
    private QualityUpdateYijinaManager  qualityUpdateYijinaManager;
    @Autowired
    private QualityFilesManager  qualityFilesManager;
    @Autowired
   	private AttachmentManager attachmentManager;
	/**
	 * 提交审核
	 * 
	 * */
    @RequestMapping(value = "subFolws")
   	@ResponseBody
   	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
   			String typeCode, String status,String activityId) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		//流程传参
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改申请 ");
   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 启动流程
   			if (StringUtil.isNotEmpty(flowId)) {
   				tjy2ScientificFilesUpdateManager.doStartPress(map);
   				//改变状态
   				Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   				Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_AUDIT);
   				tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   			} else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}

   			return JsonWrapper.successWrapper(id);
   		}

   	}
   /**
    * 获取activityId
    * */
    @RequestMapping(value = "getactivityId")
   	@ResponseBody
    public Map<String, Object> getactivityId(ServletRequest request,String id) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		String a=tjy2ScientificFilesUpdateDao.getFlowId(id).toString();
		String activityId = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
	    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
		map.put("activityId", activityId);
		return map;
   	}
    /**审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "zltj")
   	@ResponseBody
   	public HashMap<String, Object> zltj(QualityUpdateYijina qualityUpdateYijina,String areaFlag,String id,
   			String typeCode, String status,String activityId) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		if(activityId.isEmpty()){
   			String a=tjy2ScientificFilesUpdateDao.getFlowId(id).toString();
   			 activityId = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
   	    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
   		}
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改申请");
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
   			// 审批流程
   			if (StringUtil.isNotEmpty(activityId)) {
   				if(areaFlag.equals("1") ){//申请单审核
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_AUDIT);
   					Tjy2ScientificFilesUpdate.setSh_man(user.getName());//设置审核人
					Tjy2ScientificFilesUpdate.setSh_mandate(new Date());
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   					//保存修改单id
//   					String cc = Tjy2ScientificFilesUpdate.getId();
//   					qualityUpdateYijina.setFileId(cc);
//   					qualityUpdateYijinaManager.save(qualityUpdateYijina);
   				}else if(areaFlag.equals("5")){//传递单审核
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_SI);
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   					
//   					qualityUpdateYijina=qualityUpdateYijinaManager.get(id);
//   					qualityUpdateYijina.setAuditOpinion(tjy2ScientificFilesUpdateManager.ZL_XGSQ_SI);
//   					qualityUpdateYijinaManager.save(qualityUpdateYijina);
   					
   				}else if(areaFlag.equals("4")){//申请单批准
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_SAN);
   					Tjy2ScientificFilesUpdate.setPz_man(user.getName());
					Tjy2ScientificFilesUpdate.setPz_mandate(new Date());
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   					
   				}else if(areaFlag.equals("3")){//传递单审批
   					
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_JS);
   			   		Tjy2ScientificFilesUpdate.setPassTime(new Date());
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   					
   				}else if(areaFlag.equals("6")){//提交复审
   					
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_FSZ);
   			   		Tjy2ScientificFilesUpdate.setPassTime(new Date());
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   				}else if(areaFlag.equals("7")){//传递单审核复审
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_FSZ);
   			   		//Tjy2ScientificFilesUpdate.setCddJsdate(new Date());
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   				}else if(areaFlag.equals("8")){//传递单审批复审
   					tjy2ScientificFilesUpdateManager.doProcess(map);
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_PASS);
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
   				}else{
   					Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
   					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_NO_PASS);
   					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);

   				}
   			} else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}

   			return JsonWrapper.successWrapper(id);
   		}

   	}
    
	 /**传递单审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
		@RequestMapping(value = "cddtj")
		@ResponseBody
	public HashMap<String, Object> cddtj(String id,String typeCode, String status) throws Exception {
		String a=tjy2ScientificFilesUpdateDao.getFlowId(id).toString();
		String activityId = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "传递单申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);		
		
		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   		// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				tjy2ScientificFilesUpdateManager.doProcess(map);
				Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
				Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_SI);
				tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
			}else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}

   			return JsonWrapper.successWrapper(id);
   		}
	}
		/**退回审批流程并改变状态
		 * @param id
		 * @param flowId
		 * @param typeCode
		 * @param status
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "xgth")
		@ResponseBody
		public HashMap<String, Object> xgth(QualityUpdateYijina qualityUpdateYijina,String areaFlag,String id,
				String typeCode, String status,String activityId,String processId) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map1 = new HashMap<String, Object>();

			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
			map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改流程");
			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
			
			map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

			Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
			Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_NO_PASS);
			tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
			
			if (StringUtil.isEmpty(id)) {
				return JsonWrapper.failureWrapper("参数错误！");
			} else {
				// 退回流程
				if (StringUtil.isNotEmpty(activityId)) {
					tjy2ScientificFilesUpdateManager.stop(map1);
					qualityUpdateYijinaManager.save(qualityUpdateYijina);
				} else {
					return JsonWrapper.failureWrapper("流程ID为空！");
				}
				return JsonWrapper.successWrapper(id);
			}

		}
		
		/**退回审批流程并改变状态
		 * @param id
		 * @param flowId
		 * @param typeCode
		 * @param status
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "Cddreturn")
		@ResponseBody
		public HashMap<String, Object> Cddreturn(String areaFlag,String id,
				String typeCode, String status,String activityId,String processId) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map1 = new HashMap<String, Object>();

			map1.put(FlowExtWorktaskParam.SERVICE_ID, id);
			map1.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			map1.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改流程");
			map1.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
			map1.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
			
			map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

			Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
			Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_YTH);
			tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
			
			if (StringUtil.isEmpty(id)) {
				return JsonWrapper.failureWrapper("参数错误！");
			} else {
				// 退回流程
				if (StringUtil.isNotEmpty(activityId)) {
					tjy2ScientificFilesUpdateManager.doreturn(map1);
					//qualityUpdateYijinaManager.save(qualityUpdateYijina);
				} else {
					return JsonWrapper.failureWrapper("流程ID为空！");
				}
				return JsonWrapper.successWrapper(id);
			}

		}
		
		/**启用
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "abolish")
		@ResponseBody
		public HashMap<String, Object> abolish(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate = tjy2ScientificFilesUpdateManager.get(id);
				if(Tjy2ScientificFilesUpdate==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_FC);//已启用
					tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
					
					map.put("success", true);
				}
			}
			return map;
		}
	
		
		
	  
     /**
   	 * 添加
   	 * @param response
     * @throws Exception 
   	 */
    @RequestMapping(value = "save1")
   	@ResponseBody
    public HashMap<String, Object> save1(HttpServletRequest request,Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			tjy2ScientificFilesUpdateManager.saveTask(Tjy2ScientificFilesUpdate,user);
//	    	QualityFiles qualityFiles=new QualityFiles();
//	    	qualityFilesManager.
	    	map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;

   	}
    /**
   	 * 传递单添加
   	 * @param response
     * @throws Exception 
   	 */
    @RequestMapping(value = "save2")
   	@ResponseBody
    public HashMap<String, Object> save2(HttpServletRequest request,Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		String uploadFiles = request.getParameter("uploadFiles");

		tjy2ScientificFilesUpdateManager.savetwo(request,Tjy2ScientificFilesUpdate,user,uploadFiles);
    	map.put("success", true);
		return map;

   	}
    /**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "detail1")
	@ResponseBody
	public HashMap<String, Object> detail1(HttpServletRequest request, String id) throws Exception {
		Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate=tjy2ScientificFilesUpdateManager.get(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		wrapper.put("success", true);
		wrapper.put("data", Tjy2ScientificFilesUpdate);
		wrapper.put("attachs", list);
		return wrapper;

	}
	
	/**替换文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "thwj")
	@ResponseBody
	public HashMap<String, Object> thwj(String id,String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (ids.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			@SuppressWarnings("unused")
			HashMap<String, Object> map1=tjy2ScientificFilesUpdateManager.getfs(id,ids);
			map.put("success", true);
//			Tjy2ScientificFilesUpdate Tjy2ScientificFilesUpdate = tjy2ScientificFilesUpdateManager.get(id);
//			if(Tjy2ScientificFilesUpdate==null){
//				map.put("success", false);
//				map.put("msg", "数据获取失败！");
//			} else {
//				Tjy2ScientificFilesUpdate.setStatus(tjy2ScientificFilesUpdateManager.ZL_XGSQ_FC);//已启用
//				tjy2ScientificFilesUpdateManager.save(Tjy2ScientificFilesUpdate);
//				
//				map.put("success", true);
//			}
		}
		return map;
	}
	/**
	 * 保存合同文档
	 * @param request 请求
	 * @param deviceId 设备ID
	 * @param orderDoc 文档名字
	 * @param id ID
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveO")
	public HashMap<String, Object> saveO(HttpServletRequest request,
			String id,String documentId,String documentName) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			//所有文件
			Map files = multipartRequest.getFileMap();
			if (files.size() > 0){//获取文档的信息
				Iterator fileNames = multipartRequest.getFileNames();
				
				QualityAttachment qualityAttachment=new QualityAttachment();
				String fileName = (String)fileNames.next();
				CommonsMultipartFile file = (CommonsMultipartFile)files.get(fileName);
				if(id!=null){
					qualityAttachment.setId(id);
				}
				log.debug((new StringBuilder("上传的文件：")).append(file.getOriginalFilename()).toString());
				tjy2ScientificFilesUpdateManager.saveO(file.getInputStream(),qualityAttachment);
				
				wrapper.put("success", true);
			}
		} catch (Exception e) {
			wrapper.put("success", false);
		}
	return wrapper;
	}
	  
   
}