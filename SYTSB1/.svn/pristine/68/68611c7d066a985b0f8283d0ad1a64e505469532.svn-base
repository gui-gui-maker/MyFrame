package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.lsts.humanresources.bean.Upload;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.service.UploadManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("uploadAction")
public class UploadAction extends SpringSupportAction<Upload, UploadManager> {

    @Autowired
    private UploadManager  uploadManager;
    @Autowired
	private AttachmentManager attachmentManager;
    //保存员工上传文件
  	@RequestMapping(value = "saveUpload")
  	@ResponseBody
  	public HashMap<String, Object> saveUpload(Upload entity,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			if(entity.getFkEmployeeId()==null){
  				System.out.println("--------------jin");
  				wrapper.put("success", false);
  	  			wrapper.put("msg", "请先保存基础信息！");
  				return wrapper;
  			}
  			if(entity.getUploadName()==null){
  				System.out.println("--------------jin");
  				wrapper.put("success", false);
  	  			wrapper.put("msg", "请先选择要上传的文件！");
  				return wrapper;
  			}
  			uploadManager.save(entity);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("msg", "保存单位信息出错！");
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
  		    List<Upload> list=uploadManager.getByEmpId(empId);
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
  	
  //删除员工上传文件
  	@RequestMapping(value = "deleteUpload")
  	@ResponseBody
  	public HashMap<String, Object> delateUpload(String id,String uploadId,String uploadPath,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper=uploadManager.deleteUpload(id, uploadId, uploadPath);
  		return wrapper;
  	}
	
}