package com.lsts.archives.web;

import com.khnt.base.Factory;
//import com.khnt.base.SysParaInf;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentTsManager;
import com.khnt.pub.fileupload.service.IAttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.FileUtil;
import com.khnt.utils.LogUtil;
import com.lsts.archives.bean.Batch;
//import com.lsts.archives.bean.MediaFileRes;
import com.lsts.archives.bean.Uploads;
//import com.lsts.archives.dao.ArchivesFileDao;
import com.lsts.archives.service.ArchivesBoxManager;
//import com.lsts.archives.service.MediaFileResService;
import com.lsts.archives.service.UploadsManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import util.FileMd5Util;
//import util.NameUtil;
//import util.PathUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping({"uploadsAction/a","/uploadsAction/a/"})
public class UploadsAction extends SpringSupportAction<Uploads, UploadsManager> {
	public static final Integer ONE = 1;
	public static final Integer ZERO = 0;
	
    @Autowired
    private UploadsManager  uploadManager;
    @Autowired
	private AttachmentTsManager attachmentTsManager;
    @Autowired
    private ArchivesBoxManager  archivesBoxManager;
    @Autowired
	private IAttachmentManager attachmentManager;
    /*@Autowired
    MediaFileResService mediaFileResService;*/
    
	//保存员工上传文件
	@RequestMapping(value = "saveUpload")
	@ResponseBody
	public HashMap<String, Object> saveUpload(HttpServletRequest request,String uploadType,String fileId,String files)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try{
			String uploadId="";
			String uploadName="";
			String uploadPath="";
			JSONArray array = JSONArray.fromObject(files);
			for (int i = 0; i < array.length(); i++) {
				uploadId=array.getJSONObject(i).getString("id");	//附件id
				uploadName=array.getJSONObject(i).getString("name");
				uploadPath=attachmentManager.download(uploadId).getFilePath();
				Uploads uploads=new Uploads();
				uploads.setUploadName(uploadName);
				String type=uploadName.substring(uploadName.lastIndexOf(".")+1,uploadName.length());
				if(type.equals("doc")||type.equals("txt")){
					uploads.setParentId("10000");
				}else if(type.equals("rm")||type.equals("rmvb")||type.equals("wmv")||type.equals("avi")||
						type.equals("mp4")||type.equals("3gp")||type.equals("mkv")||type.equals("rm")||type.equals("rm")){
					uploads.setParentId("10001");
				}else if(type.equals("jpg")||type.equals("gif")||type.equals("bmp")||type.equals("jpeg")
						||type.equals("jpe")||type.equals("psd")||type.equals("eps")||type.equals("png")){
					uploads.setParentId("10002");
				}else if(type.equals("mp3")||type.equals("wma")||type.equals("flac")||type.equals("aac")||type.equals("mmf")||type.equals("amr")
						||type.equals("m4a")||type.equals("m4r")||type.equals("ogg")||type.equals("mp2")||type.equals("wav")||type.equals("wv")){
					uploads.setParentId("10003");
				}
				uploads.setType("0");
				uploads.setUploadPath(uploadPath);
				uploads.setUploadId(uploadId);
				uploads.setUploadType(uploadType);
				uploads.setFileId(fileId);
				
				uploadManager.saveAndUpdateAttachmentBusinessId(uploads,uploadId, fileId);
			}
			archivesBoxManager.updateArchivesBox(fileId);
			wrapper.put("success", true);
		}catch(Exception e){
			log.error("保存单位信息：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "保存单位信息出错！");
			e.printStackTrace();	
		}
		return wrapper;
	}
	/**
	 * 保存
	 * 
	 * @param request
	 * @param baseEquipment
	 * @throws Exception
	 */
	@RequestMapping(value = "updateArchivesBox")
	@ResponseBody
	public HashMap<String, Object> updateArchivesBox(String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			archivesBoxManager.updateArchivesBox(id);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", e.getMessage());
		}
		return wrapper;
	}
	
	
	 	/**
		 * 删除
		 * @param request
		 * @param baseEquipment
		 * @throws Exception
		 */
		@RequestMapping(value = "deleteUpload")
		@ResponseBody
		public HashMap<String, Object> deleteUpload(HttpServletRequest request,String uploadPath,String uploadId,String id) throws Exception {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			uploadManager.deleteBusiness(id);
			attachmentTsManager.deleteAttach(uploadId, uploadPath);
			wrapper.put("success", true);
			return wrapper;
		}
		/**
		 * 删除多个
		 * 
		 * @param request
		 * @param baseEquipment
		 * @throws Exception
		 */
		@RequestMapping(value = "deleteUploads")
		@ResponseBody
		public HashMap<String, Object> deleteUploads(HttpServletRequest request,String rows) throws Exception {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				String reportId = uploadManager.dels(rows);
				archivesBoxManager.updateArchivesBox(reportId);
				wrapper.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				wrapper.put("success", false);
				wrapper.put("msg", e.getMessage());
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
		Uploads uploads1=uploadManager.get(id);
		List<Attachment> list = attachmentTsManager.getBusAttachment(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", uploads1);
		wrapper.put("attachs", list);
		return wrapper;
	}
	/**
	 * 查询档案树结构
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFileTree")
	@ResponseBody
	public void getFileTree(HttpServletResponse response, String fileId) throws Exception{
		List<Object[]> list = new ArrayList<Object[]>();
		try{
		
			list = uploadManager.getDevice(fileId);
		
		StringBuffer sb=new StringBuffer();
		StringBuffer sb1=new StringBuffer();
		StringBuffer sb2=new StringBuffer();
		StringBuffer sb3=new StringBuffer();
		StringBuffer sb4=new StringBuffer();
		StringBuffer sb5=new StringBuffer();
		StringBuffer sb6=new StringBuffer();
		StringBuffer sb7=new StringBuffer();
		StringBuffer sb8=new StringBuffer();
		StringBuffer sb9=new StringBuffer();
		StringBuffer sb10=new StringBuffer();
		StringBuffer sb11=new StringBuffer();
		int b=0;
		int b1=0;
		int b2=0;
		int b3=0;
		int b4=0;
		int b5=0;
		int b6=0;
		int c=0;
		for(int i=0;i<list.size();i++){
			//分类文档
			if("10000".equals((list.get(i)[1]))&&"0".equals((list.get(i)[2]))){
				sb1
				.append((b == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b++;
			}
			if("10000".equals((list.get(i)[1]))&&"1".equals((list.get(i)[2]))){
				sb2
				.append((b1 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b1++;
			}
			if("10000".equals((list.get(i)[1]))&&"2".equals((list.get(i)[2]))){
				sb6
				.append((b5 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b5++;
			}
			//分类图片
			if("10002".equals((list.get(i)[1]))&&"0".equals((list.get(i)[2]))){
				sb3
				.append((b2 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b2++;
			}
			if("10002".equals((list.get(i)[1]))&&"1".equals((list.get(i)[2]))){
				sb4
				.append((b3 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b3++;
			}
			if("10002".equals((list.get(i)[1]))&&"2".equals((list.get(i)[2]))){
				sb7
				.append((b6 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b6++;
			}
			//视频和音乐
			/*if("10001".equals((list.get(i)[1]))){
				sb6
				.append((b5 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b5++;
			}
			if("10003".equals((list.get(i)[1]))){
				sb7
				.append((b == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[3])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b6++;
			}
			*/
			
		
			/*if("10000".equals((list.get(i)[1]))){
				sb1
				.append((b == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b++;
			}
			if("10001".equals((list.get(i)[1]))){
				sb2
				.append((b1 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b1++;
			}
			if("10002".equals((list.get(i)[1]))){
				sb3
				.append((b2 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
				
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b2++;
			}
			if("10003".equals((list.get(i)[1]))){
				sb4
				.append((b3 == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
					
				.append("\",")
				.append("\"level\":")
				.append("\"3\",\"isexpand\":true,\"children\":null}");
				b3++;
			}
			if("0".equals((list.get(i)[1]))&&"10000".equals((list.get(i)[2]))){
				sb5
				.append("{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0]+"("+b+")")
				
				.append("\",")
				.append("\"level\":")
				.append("\"2\",\"isexpand\":false,\"children\":["+sb1+"]}");
				
			}
			if("0".equals((list.get(i)[1]))&&"10001".equals((list.get(i)[2]))){
				sb6
				.append("{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0]+"("+b1+")")
					
				.append("\",")
				.append("\"level\":")
				.append("\"2\",\"isexpand\":false,\"children\":["+sb2+"]}");
			}
			if("0".equals((list.get(i)[1]))&&"10002".equals((list.get(i)[2]))){
				sb7
				.append("{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0]+"("+b2+")")
					
				.append("\",")
				.append("\"level\":")
				.append("\"2\",\"isexpand\":false,\"children\":["+sb3+"]}");
			}
			if("0".equals((list.get(i)[1]))&&"10003".equals((list.get(i)[2]))){
				sb8
				.append(",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0]+"("+b3+")")
					
				.append("\",")
				.append("\"level\":")
				.append("\"2\",\"isexpand\":false,\"children\":["+sb4+"]}");
			}
			if("00".equals((list.get(i)[1]))){
				sb
				.append((c == 0) ? "{" : ",{")
				.append("\"id\":\"")
				.append(list.get(i)[2])
				.append("\",")
				.append("\"text\":\"")
				.append(list.get(i)[0])
					
				.append("\",")
				.append("\"level\":")
					.append("1".equals(list.get(i)[2]) ? "\"1\",\"isexpand\":false,\"children\":["+sb5+"]}"
						:"0".equals(list.get(i)[2])  ? "\"1\",\"isexpand\":false,\"children\":["+sb7+"]}"
							:"\"1\",\"isexpand\":false,\"children\":["+sb6+sb8+"]}");

				c++;
			}			*/
		}
		   //检验报告
					sb8
					.append("{")
					.append("\"id\":\"")
					.append("0")
					.append("\",")
					.append("\"text\":\"")
					.append("文档"+"("+b+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb1+"]}");
					sb8
					.append(",{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("图片"+"("+b2+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb3+"]}");
					//原始记录
					sb9
					.append("{")
					.append("\"id\":\"")
					.append("0")
					.append("\",")
					.append("\"text\":\"")
					.append("文档"+"("+b1+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb2+"]}");
					sb9
					.append(",{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("图片"+"("+b3+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb4+"]}");
					//自检报告
					sb10
					.append("{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("文档"+"("+b5+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb6+"]}");
					sb10
					.append(",{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("图片"+"("+b6+")")
						
					.append("\",")
					.append("\"level\":")
					.append("\"2\",\"isexpand\":false,\"children\":["+sb7+"]}");
					
					//报告3大类
					sb11
					.append("{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("检验报告")
						
					.append("\",")
					.append("\"level\":")
					.append("\"1\",\"isexpand\":false,\"children\":["+sb8+"]}");
					sb11
					.append(",{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("原始记录")
						
					.append("\",")
					.append("\"level\":")
					.append("\"1\",\"isexpand\":false,\"children\":["+sb9+"]}");
					sb11
					.append(",{")
					.append("\"id\":\"")
					.append("1")
					.append("\",")
					.append("\"text\":\"")
					.append("自检报告")
						
					.append("\",")
					.append("\"level\":")
					.append("\"1\",\"isexpand\":false,\"children\":["+sb10+"]}");
		System.out.println("--------------"+sb7);
		System.out.println("--------------"+sb8);
		response.getWriter().write("["+sb11+"]");
		} catch (Exception e) {
			
		}
	}
	/**
	 * 查询档案树结构
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFileTree2")
	@ResponseBody
	public HashMap<String,Object> getFileTree2(HttpServletResponse response, String fileId) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		List<Object[]> list = new ArrayList<Object[]>();
		try{
		
			list = uploadManager.getDevice2(fileId);
			map.put("success", true);
			map.put("data",list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 取得合同文档
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFile")
	public void getFile(String id,HttpServletResponse response) throws Exception {
		Uploads file = uploadManager.getFile(id);
		//下载文档
		FileUtil.download(response, file.getUploadDoc(), file.getUploadName(), "application/octet-stream");
	}
	 //保存员工扫描上传文件
  	@RequestMapping(value = "saveUploads")
  	@ResponseBody
  	public HashMap<String, Object> saveUploads(HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		List<JSONObject> list =  (List<JSONObject>) request.getAttribute("list");
  		String reportId =  (String) request.getAttribute("reportId");
  		if(null!=list&&list.size()>0){
  			//获取批次标识
  			ServletContext context = (ServletContext) request.getSession().getServletContext();
  			Batch bat = (Batch)context.getAttribute("batch");
	  		try{
	  			String uploadId = "";
	  			String uploadName = "";
	  			String uploadPath = "";
	  			String uploadFileId = "";//关联businessId
	  			String uploadType = "";//0：报告1：原始文件2：自检报告
	  			String batch = null;//null清楚原来数据，否则不清除
	  			String folder = null;
	  			JSONArray array = JSONArray.fromObject(list);
	  			for (int i = 0; i < array.length(); i++) {
		  				uploadId=array.getJSONObject(i).getString("id");
		  				uploadName=array.getJSONObject(i).getString("name");
		  				uploadPath=array.getJSONObject(i).getString("path");
		  				uploadFileId = array.getJSONObject(i).getString("file_id");
		  				uploadType = array.getJSONObject(i).getString("uploadType");
		  				batch = array.getJSONObject(i).getString("batch");
		  				folder = array.getJSONObject(i).getString("folder");
		  				if(bat.getTotal()==1){
							//批次的第一张删除
		  					uploadManager.deleteByBusinessAndUploadType(uploadFileId, uploadType,folder);
		  					System.out.println("删除原文件*******************");
		  					context.setAttribute("batch", batch);
						}
		  				
		  			Uploads uploads=new Uploads();
		  			uploads.setUploadName(uploadName);
		  			String type=uploadName.substring(uploadName.lastIndexOf(".")+1,uploadName.length());
		  			System.out.println("------------------------------"+type);
		  			if(type.equals("doc")){
		  				uploads.setParentId("10000");
		  			}else if(type.equals("rm")||type.equals("rmvb")||type.equals("wmv")||type.equals("avi")||
		  					type.equals("mp4")||type.equals("3gp")||type.equals("mkv")||type.equals("rm")||type.equals("rm")){
		  				uploads.setParentId("10001");
		  			}else if(type.equals("jpg")||type.equals("gif")||type.equals("tif")
		  					||type.equals("bmp")||type.equals("jpeg")||type.equals("jpe")
		  					||type.equals("psd")||type.equals("eps")||type.equals("png")){
		  				uploads.setParentId("10002");
		  			}else if(type.equals("mp3")||type.equals("wma")||type.equals("flac")||type.equals("aac")||type.equals("mmf")||type.equals("amr")
		  					||type.equals("m4a")||type.equals("m4r")||type.equals("ogg")||type.equals("mp2")||type.equals("wav")||type.equals("wv")){
		  				uploads.setParentId("10003");
		  			}
		  			uploads.setUploadType(uploadType);
		  			uploads.setType("0");
		  			uploads.setUploadPath(uploadPath);
		  			uploads.setUploadId(uploadId);
		  			uploads.setFileId(uploadFileId);
		  			uploadManager.save(uploads);
		  			int success = bat.getSuccess();
		  			success++;
		  			bat.setSuccess(success);
				}
	  			wrapper.put("success", true);
	  		}catch(Exception e){
	  			e.printStackTrace();
	  			int failure = bat.getFailure();
				failure++;
				bat.setFailure(failure);
	  			wrapper.put("success", false);
	  			wrapper.put("message", e.getMessage());
	  		}finally{
	  			context.setAttribute("batch", bat);
	  		}
  		}
  		return wrapper;
  	}
	/**
	 * 检查文件类型合法性
	 * 
	 * @param fileName
	 */
	private boolean checkUploadFileValid(String fileName) {
		String notAllowTypes = Factory.getSysPara().getProperty("attachmentNotAllowTypes", ",exe,js,msi,jsp,html,jsf,bat,");

		int idx = fileName.lastIndexOf(".");
		if (idx >= 0 && idx != fileName.length() - 1 && notAllowTypes.indexOf("," + fileName.substring(idx + 1) + ",") > -1)
			return false;
		else
			return true;
	}
	//查询扫描上传结果详情
		@ResponseBody
		@RequestMapping("scanUploadDetail")
		public HashMap<String, Object> scanUploadDetail(HttpServletRequest request,
				String id,String businessId,String userId,String userName){
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			//更新上传人
			attachmentTsManager.addScanUploader(userId,userName,businessId);
			//返回统计数据
			ServletContext context = request.getSession().getServletContext();
			Batch bat = (Batch) context.getAttribute("batch");
			if(bat!=null&&bat.getId().equals(id)){
				wrapper.put("success", true);
				wrapper.put("data", bat);
			}else{
				wrapper.put("success", false);
				wrapper.put("msg", "上传出错！！");
			}
			
			return wrapper;
		}
		 //保存员工扫描上传文件
		@ResponseBody
	  	@RequestMapping(value = "saveUploadsFiles")
	  	public String saveUploads2(HttpServletRequest request)
	  			throws Exception {
	  		String params=null;//所有参数的字符串，传
			String batch = null;//批次
			String businessId=null;//业务id
			String workItem=null;//
			String attType=null;
			Boolean saveDB=null;//默认false
	        String busUniqueName=null;//业务唯一编号名称
	        String folder=null;//文件夹
	        String uploadType=null;//上传类型0：检验报告，1原始记录，2自检报告
	        String fname=null;
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        Map<String, MultipartFile> files = multipartRequest.getFileMap();
	        
	        if(files.size()>0){
	        	//标识
	        	ServletContext context = (ServletContext) request.getSession().getServletContext();
	        	Batch bat = (Batch)context.getAttribute("batch");
	        	
				Iterator<String> fileNames = multipartRequest.getFileNames();//file.getFileItem().getFieldName();
				while(fileNames.hasNext()) {
					Attachment attachment = new Attachment();
					String fileName = (String) fileNames.next();
					CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
					//原文件名称
					fname= file.getOriginalFilename();
					//取出参数
					if(null==params){
						params = file.getFileItem().getFieldName();
						JSONObject obj = JSONObject.fromObject(params);
						businessId = obj.getString("businessId");
						busUniqueName = obj.getString("busUniqueName");
						uploadType = obj.getString("uploadType");
						folder = uploadType+busUniqueName;
						batch = obj.getString("batch");
					}
					
					
					if (!this.checkUploadFileValid(file.getOriginalFilename())) {
						throw new Exception("不合法的文件类型");
					}
					attachment.setFileSize(file.getSize());
					attachment.setFileType(file.getFileItem().getContentType());
					attachment.setFileName(file.getOriginalFilename());
					attachment.setBusinessId(businessId);
					attachment.setBusUniqueName(busUniqueName);
		
					log.debug("上传的文件：" + file.getOriginalFilename());
					try {
						CurrentSessionUser user = super.getCurrentUser();
						if (user != null) {
							attachment.setUploader(user.getId());
							attachment.setUploaderName(user.getName());
						}
					} catch (Exception e) {
						log.warn("当前请求未包含有效的安全凭证！");
					}
					attachment.setWorkItem(workItem);
					
					try {
						if(bat!=null && bat.getId().equals(batch)){
							//总上传数加1
							int total = bat.getTotal();
							total++;
							bat.setFailure(total);
							//有，并且等
							//不删除方法上传attachment
							attachmentTsManager.saveBatchAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
									folder, fname,false);
						}else{
							//第一次为空
							//有，但是不等
							bat = new Batch();
							bat.setId(batch);
							//总上传数加1
							int total = bat.getTotal();
							total++;
							bat.setFailure(total);
							//删除方法上传attachment
							attachmentTsManager.saveBatchAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
									folder, fname,true);
							//删除uploads
							uploadManager.deleteByBusinessAndUploadType(businessId, uploadType,folder);
						}
						//保存uploads
						Uploads uploads=new Uploads();
						String uploadId = attachment.getId();
						String uploadName= attachment.getFileName();
			  			String uploadPath = attachment.getFilePath();;
			  			String uploadFileId = attachment.getBusinessId();//关联businessId
			  			uploads.setUploadName(uploadName);
			  			String type=uploadName.substring(uploadName.lastIndexOf(".")+1,uploadName.length());
			  			System.out.println("------------------------------"+type);
			  			if(type.equals("doc")){
			  				uploads.setParentId("10000");
			  			}else if(type.equals("rm")||type.equals("rmvb")||type.equals("wmv")||type.equals("avi")||
			  					type.equals("mp4")||type.equals("3gp")||type.equals("mkv")||type.equals("rm")||type.equals("rm")){
			  				uploads.setParentId("10001");
			  			}else if(type.equals("jpg")||type.equals("gif")||type.equals("bmp")||type.equals("jpeg")
			  					||type.equals("jpe")||type.equals("psd")||type.equals("eps")||type.equals("png")){
			  				
			  				uploads.setParentId("10002");
			  			}else if(type.equals("mp3")||type.equals("wma")||type.equals("flac")||type.equals("aac")||type.equals("mmf")||type.equals("amr")
			  					||type.equals("m4a")||type.equals("m4r")||type.equals("ogg")||type.equals("mp2")||type.equals("wav")||type.equals("wv")){
			  				uploads.setParentId("10003");
			  			}
			  			uploads.setUploadType(uploadType);
			  			uploads.setType("0");
			  			uploads.setUploadPath(uploadPath);
			  			uploads.setUploadId(uploadId);
			  			uploads.setFileId(uploadFileId);
			  			uploadManager.save(uploads);
			  			//保存成功
			  			int success = bat.getSuccess();
			  			success++;
			  			bat.setSuccess(success);
		
					} catch (Exception e) {
						//保存失败
						int failure = bat.getFailure();
						failure++;
						bat.setFailure(failure);
						e.printStackTrace();
						LogUtil.logError(log, e);
						return "";
					}finally{
						context.setAttribute("batch", bat);
					}
				}
			}
	  		return "success";
	  	}
    //查询员工上传文件
  	@RequestMapping(value = "detailUpload")
  	@ResponseBody
  	public HashMap<String, Object> detailUpload(String fileId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  		    List<Uploads> list=uploadManager.getByEmpId(fileId);
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
  	//统计档案类别数量
  	@RequestMapping(value = "queryCountByUploadType")
  	@ResponseBody
  	public HashMap<String, Object> queryCountByUploadType(String fileId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			List<Object[]> list = uploadManager.queryCountByUploadType(fileId);
  			wrapper.put("data", list);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			wrapper.put("success", false);
  			wrapper.put("msg", e.getMessage());
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	
	@RequestMapping(value = "downloadByUploadType")
	public void downloadByUploadType(HttpServletResponse response, HttpServletRequest request, String id,
			String upload_type, String sn) throws Exception {
		try {
			// 图片basepath
			String path = request.getSession().getServletContext().getRealPath("/") + "upload\\";
			System.out.println(path);
			//
			List<Object[]> list = uploadManager.getFiles(id, upload_type);

			byte[] buffer = new byte[1024];
			// 生成的ZIP文件名为Demo.zip
			String fileName =  upload_type + sn + ".zip";
			// 创建压缩文件
			File fileZip = new File(path + fileName);
			// 文件输出流
			FileOutputStream outStream = new FileOutputStream(fileZip);
			// 压缩流
			ZipOutputStream toClient = new ZipOutputStream(outStream);

			// 需要同时下载的两个文件result.txt ，source.txt

			List<File> files = new ArrayList<File>();
			for (Object[] obj : list) {
				String absolutePath = obj[2].toString();
				File f = new File(absolutePath);
				files.add(f);
			}
			for (int i = 0; i < files.size(); i++) {

				FileInputStream fis = new FileInputStream(files.get(i));

				toClient.putNextEntry(new ZipEntry(files.get(i).getName()));

				int len;

				// 读入需要下载的文件的内容，打包到zip文件

				while ((len = fis.read(buffer)) > 0) {
					toClient.write(buffer, 0, len);
				}

				toClient.closeEntry();

				fis.close();

			}

			toClient.close();

			downloadFile(fileZip, response, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  	public void downloadFile(File file,HttpServletResponse response,boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8")));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if(isDelete)
            {
                file.delete();        //是否将生成的服务器端文件删除
            }
         } 
         catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
  	
 /* //删除员工上传文件
  	@RequestMapping(value = "deleteUpload")
  	@ResponseBody
  	public HashMap<String, Object> delateUpload(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			uploadManager.deleteBusiness(id);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("查询单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "查询单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}*/

	/**
	 * 详情,包括这一类的所有文件
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "folderDetail")
	@ResponseBody
	public HashMap<String, Object> folderDetail(HttpServletRequest request, String id) throws Exception {
		Uploads uploads1=uploadManager.get(id);
		//通过上面的uploads找这一类的所有文件
		String uploadType = uploads1.getUploadType();//文件类型
		String businessId =uploads1.getFileId();
		List<Uploads> list = uploadManager.getByEmpIdAndUploadType(businessId,uploadType);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;

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
		if (files.size() > 0)
		{//获取文档的信息
			Iterator fileNames = multipartRequest.getFileNames();
			Uploads uploads = new Uploads();
			String fileName = (String)fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile)files.get(fileName);
			//sorder.setDocumentName(documentName);
			//order.setDocumentId(documentId);
			if(id!=null){
				uploads.setId(id);
			}
			log.debug((new StringBuilder("上传的文件：")).append(file.getOriginalFilename()).toString());
			//保存文档和记录内容
			uploadManager.saveO(file.getInputStream(),uploads);
			
			wrapper.put("success", true);
		}
		
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}
	/**
     * 上传文件
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     *//*
    @RequestMapping(value = "/uploadBigFile")
    @ResponseBody
    public Map<String, Object> upload(
            HttpServletRequest request, @RequestParam(value = "data",required = false) MultipartFile multipartFile) throws IllegalStateException, IOException, Exception {

        String action = request.getParameter("action");
        String uuid = request.getParameter("uuid");
        String fileName = request.getParameter("name");
        String size = request.getParameter("size");//总大小
        int total = Integer.valueOf(request.getParameter("total"));//总片数
        int index = Integer.valueOf(request.getParameter("index"));//当前是第几片
        String fileMd5 = request.getParameter("filemd5"); //整个文件的md5
        String date = request.getParameter("date"); //文件第一个分片上传的日期(如:20170122)
        String md5 = request.getParameter("md5"); //分片的md5
        String businessId = request.getParameter("businessId");
        String reportSn = request.getParameter("reportSn");

        //生成上传文件的路径信息，按天生成
        String savePath = "report_document_media" + File.separator + date;
        String saveDirectory = PathUtil.getAppRootPath(request) + savePath + File.separator + uuid;
        //验证路径是否存在，不存在则创建目录
        File path = new File(saveDirectory);
        if (!path.exists()) {
            path.mkdirs();
        }
        //文件分片位置
        File file = new File(saveDirectory, uuid + "_" + index);

        //根据action不同执行不同操作. check:校验分片是否上传过; upload:直接上传分片
        Map<String, Object> map = null;
        if("check".equals(action)){
            String md5Str = FileMd5Util.getFileMD5(file);
            if (md5Str != null && md5Str.length() == 31) {
                System.out.println("check length =" + md5.length() + " md5Str length" + md5Str.length() + "   " + md5 + " " + md5Str);
                md5Str = "0" + md5Str;
            }
            if (md5Str != null && md5Str.equals(md5)) {
                //分片已上传过
                map = new HashMap<>();
                map.put("flag", "2");
                map.put("fileId", uuid);
                map.put("status", true);
                return map;
            } else {
                //分片未上传
                map = new HashMap<>();
                map.put("flag", "1");
                map.put("fileId", uuid);
                map.put("status", true);
                return map;
            }
        }else if("upload".equals(action)){
            //分片上传过程中出错,有残余时需删除分块后,重新上传
            if (file.exists()) {
                file.delete();
            }
            multipartFile.transferTo(new File(saveDirectory, uuid + "_" + index));
        }

        if (path.isDirectory()) {
            File[] fileArray = path.listFiles();
            if (fileArray != null) {
                if (fileArray.length == total) {
                    //分块全部上传完毕,合并
                    String suffix = NameUtil.getExtensionName(fileName);

                    File newFile = new File(PathUtil.getAppRootPath(request) + savePath, uuid + "." + suffix);
                    FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
                    byte[] byt = new byte[10 * 1024 * 1024];
                    int len;

                    FileInputStream temp = null;//分片文件
                    for (int i = 0; i < total; i++) {
                        int j = i + 1;
                        temp = new FileInputStream(new File(saveDirectory, uuid + "_" + j));
                        while ((len = temp.read(byt)) != -1) {
                            System.out.println("-----" + len);
                            outputStream.write(byt, 0, len);
                        }
                    }
                    //关闭流
                    temp.close();
                    outputStream.close();
                    //修改FileRes记录为上传成功
                    mediaFileResService.updateSucess(fileMd5);
                }else if(index == 1){
                    //文件第一个分片上传时记录到数据库
                    MediaFileRes fileRes = new MediaFileRes();
                    String name = NameUtil.getFileNameNoEx(fileName);
                    if (name.length() > 50) {
                        name = name.substring(0, 50);
                    }
                    fileRes.setName(name);
                    fileRes.setSuffix(NameUtil.getExtensionName(fileName));
                    fileRes.setUuid(uuid);
                    fileRes.setPath(savePath + File.separator + uuid + "." + fileRes.getSuffix());
                    fileRes.setSize(Integer.parseInt(size));
                    fileRes.setMd5(fileMd5);
                    fileRes.setStatus(ZERO);
                    fileRes.setCreateTime(new Date());
                    fileRes.setBusinessId(businessId);
                    fileRes.setReportSn(reportSn);
                    mediaFileResService.save(fileRes);
                }
            }
        }

        map = new HashMap<>();
        map.put("flag", "3");
        map.put("fileId", uuid);
        map.put("status", true);
        return map;
    }

    *//**
     * 上传文件前校验
     *
     * @param request
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "/isUpload")
    @ResponseBody
    public Map<String, Object> isUpload(HttpServletRequest request) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	try{
	        String md5 = request.getParameter("md5");
	
	        List<MediaFileRes> list = mediaFileResService.listByMd5(md5);
	
	        if (list == null || list.size() == 0) {
	            //没有上传过文件
	            String uuid = UUID.randomUUID().toString();
	          
	            map.put("flag", "1");
	            map.put("fileId", uuid);
	            map.put("date", new SimpleDateFormat("YYYYMMDD").format(new Date()));
	            map.put("status", true);
	        } else {
	        	MediaFileRes fileRes = list.get(0);
	            //求文件上传日期
	            SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDD");
	            //Date date=new Date();
	            String strDate=sdf.format(fileRes.getCreateTime());
	            if(fileRes.getStatus()==0){
	                //文件上传部分
	                map.put("flag", "2");
	                map.put("fileId", fileRes.getUuid());
	                map.put("date",strDate);
	                map.put("status", true);
	            }else if(fileRes.getStatus()==1){
	                //文件上传成功
	                map.put("flag", "3");
	                map.put("fileId", fileRes.getUuid());
	                map.put("date",strDate);
	                map.put("status", true);
	            }
	
	        }

    	}catch(Exception e){
    		e.printStackTrace();
    		map.put("msg",e.getMessage());
            map.put("success",false);
    	}
        return map;
    }*/
}