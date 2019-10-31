package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.Tjy2ScientificFiles;
import com.fwxm.scientific.service.Tjy2ScientificFilesManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.lsts.archives.bean.Uploads;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityFiles;
import com.lsts.qualitymanage.dao.QualityAttachmentDao;
import com.lsts.qualitymanage.dao.QualityFilesDao;
import com.lsts.qualitymanage.service.QualityAttachmentManager;
import com.lsts.qualitymanage.service.QualityFilesManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





















import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("tjy2ScientificFilesAction")
public class Tjy2ScientificFilesAction extends SpringSupportAction<Tjy2ScientificFiles, Tjy2ScientificFilesManager
> {

    @Autowired
    private Tjy2ScientificFilesManager  tjy2ScientificFilesManager;
    @Autowired
    private QualityFilesDao  qualityFilesDao;
    @Autowired
   	private AttachmentManager attachmentManager;
    
    @Autowired
   	private QualityAttachmentManager qualityAttachmentManager;
    
    /**
     * 启用
     * */
    @RequestMapping(value = "wjqy")
   	@ResponseBody
   	public HashMap<String, Object> wjqy(String id){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try {
    		tjy2ScientificFilesManager.settxwj(id);
			map.put("success", true);
			map.put("msg", "启用成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
    
    /**
   	 * 保存
   	 * 
   	 * @param request
   	 * @param baseEquipment
   	 * @throws Exception
   	 */
   	@RequestMapping(value = "save1")
   	@ResponseBody
   	public HashMap<String, Object> save1(HttpServletRequest request,@RequestBody Tjy2ScientificFiles qualityFiles) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		String uploadFiles = request.getParameter("uploadFiles");
   		String wjid = request.getParameter("wjid");

//   		List<QualityFiles> qualityFilesList=qualityFilesDao.getQualityFiles(
//   				qualityFiles.getFileId(),qualityFiles.getTjyFile());
//		if (qualityFilesList.size() <= 0) {
//   		if(wjid.isEmpty()){
   		tjy2ScientificFilesManager.saveEquipments(qualityFiles, uploadFiles,wjid);
			map.put("success", true);
			map.put("msg", "保存成功！");
//		}else{
//			map.put("success", false);
//			map.put("msg", "此文件编号重复，请重新添加！");
//		}

   		//	return JsonWrapper.failureWrapperMsg("保存设备信息失败，请重试！");
   		//return JsonWrapper.successWrapper(qualityFiles);
   		return map;
   	}
    /**
     * 查询
     * */
  	@RequestMapping(value = "detailUploads")
  	@ResponseBody
  	public HashMap<String, Object> detailUploads(String businessId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  		    List<QualityAttachment> list=qualityAttachmentManager.getfs(businessId);
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
  	 //删除上传文件
  	@RequestMapping(value = "deleteUploads")
  	@ResponseBody
  	public HashMap<String, Object> delateUploads(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			qualityAttachmentManager.deleteBusiness(id);//删除业务
  			String yy=qualityFilesDao.getbusiness_id(id).toString();//业务id
  			String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(yy.toString()
  			    	.substring(1,yy.toString().length()-1)).replaceAll("'$1'");
  			tjy2ScientificFilesManager.deleteBusiness(ids);//删除上传文件
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("查询单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
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
		Tjy2ScientificFiles qualityFiles = tjy2ScientificFilesManager.get(id); 
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", qualityFiles);
		wrapper.put("attachs", list);
		return wrapper;
	}
	
	 /**
   	 * 获取日期
   	 * 
   	 * @param request
   	 * @param baseEquipment
   	 * @throws Exception
   	 */
   	@RequestMapping(value = "getTime")
   	@ResponseBody
   	public HashMap<String, Object> getTime(String implementDate) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
//		if (qualityFilesList.size() <= 0) {
    	String str = implementDate.substring(implementDate.length()-8,implementDate.length());
    	System.out.println(str);
    	Pattern pattern = Pattern.compile("[0-9]*");
	    Matcher isNum = pattern.matcher(str);
       	StringBuffer sb=new StringBuffer(str);
       	if(isNum.matches()) {
       		String a=implementDate.substring(implementDate.length()-2,implementDate.length());
       		String b=sb.substring(4,6);
       		String c=sb.substring(0,4);
       		String e=c+"-"+b+"-"+a;
       		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
       		java.util.Date date=sdf.parse(e);
       		map.put("msg", date);
       	}else{
       		map.put("msg", null);
       	}
    	map.put("success", true);
   		return map;
   	}
//   	public static void main(String[] args) throws ParseException{
//   	  String str = "20100107（20150911"; 
//   	  str = str.substring(str.length()-8,str.length());
//   	StringBuffer sb=new StringBuffer(str);
//	System.out.println(str);
//	String a = str.substring(str.length()-2,str.length());
//	String b=sb.substring(4,6);
//	String c=sb.substring(0,4);
//	String e=c+"-"+b+"-"+a;
//	System.out.println(e);
//   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
//   	java.util.Date date=sdf.parse(e);
//	System.out.println(date);
//
//   	}
}