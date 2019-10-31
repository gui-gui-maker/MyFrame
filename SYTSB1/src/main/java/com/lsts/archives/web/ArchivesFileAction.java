package com.lsts.archives.web;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.dao.ArchivesFileDao;
import com.lsts.archives.service.ArchivesFileManager;

@Controller
@RequestMapping("archives/file")
public class ArchivesFileAction extends SpringSupportAction<ArchivesFile, ArchivesFileManager> {

    @Autowired
    private ArchivesFileManager  archivesFileManager;
    @Autowired
	private AttachmentManager attachmentManager;
    @Autowired
    private ArchivesFileDao  archivesFileDao;
    /**
	 * 保存
	 * 
	 * @param request
	 * @param baseEquipment
	 * @throws Exception
	 */
	@RequestMapping(value = "save1")
	@ResponseBody
	public HashMap<String, Object> save1(HttpServletRequest request, ArchivesFile archivesFile) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		
		archivesFileManager.saveEquipment(archivesFile, uploadFiles);
		//	return JsonWrapper.failureWrapperMsg("保存设备信息失败，请重试！");
		return JsonWrapper.successWrapper(archivesFile);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail1")
	@ResponseBody
	public HashMap<String, Object> detail1(HttpServletRequest request, String id) throws Exception {
//		ArchivesFile archivesFile = archivesFileManager.get(id); 
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
//		List<ArchivesFile> archivesFile =archivesFileDao.getFj(id);
//		for (int i=0;i<archivesFile.size();i++){
//			String a=archivesFile.get(i).getId();
//			List<Attachment> list = attachmentManager.getBusAttachment(a);
//			wrapper.put("success", true);
//			wrapper.put("attachs", list);
//			wrapper.put("data", archivesFile);
//		}
		return wrapper;
	}
	/**
	 * 查找id
	 * @param response
     * @throws Exception 
	 */
	@RequestMapping(value = "getIds")
	@ResponseBody
	public HashMap<String, Object> getIds(String ids) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	String a=archivesFileDao.getIds(ids).toString();
    	String id = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'");
    	map.put("message", id);
//    	map.put("success", true);
		return map;

	}
	 /**
		 * 添加
		 * @param response
	     * @throws Exception 
		 */
	    @Override
		public HashMap<String, Object> save(HttpServletRequest request,ArchivesFile archivesFile) throws Exception {
	    	HashMap<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			archivesFile.setRegistrant(user.getName());
	 		archivesFile.setRegistrantTime(new Date());
			archivesFileManager.saveTask1(archivesFile,user);
	    	map.put("success", true);
			return map;

		}
	
	
	 //保存档案正文和附件
	    /* 	@RequestMapping(value = "saveUpload")
  	@ResponseBody
  	public HashMap<String, Object> saveUpload(String empId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
	
  	 //查询员工上传文件
  	@RequestMapping(value = "detailUpload")
  	@ResponseBody
  	public HashMap<String, Object> detailUpload(String empId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  		    List<Upload> list=archivesFileManager.getByEmpId(empId);
  		    wrapper.put("list", list);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("查询单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	
  //查询员工上传文件
  	@RequestMapping(value = "deleteUpload")
  	@ResponseBody
  	public HashMap<String, Object> delateUpload(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			archivesFileManager.deleteBusiness(id);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("查询单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}*/
}