package com.scts.cspace.fileupload.web;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.crud.web.support.QueryCondition;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;
import com.scts.cspace.file.bean.TjyptFileInfo;
import com.scts.cspace.file.dao.TjyptFileInfoDao;
import com.scts.cspace.fileupload.service.CloudAttachmentManager;
import com.scts.cspace.log.service.TjyptLogService;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.dao.TjyptResourceInfoDao;
import com.scts.cspace.resource.service.TjyptResourceInfoService;

import net.sf.json.JSONObject;
import util.FileUtil;
import util.TS_Util;

/***
 * 
 * 附件管理页面展现和交互类.
 * 
 * @author 2012-3-13
 * @author 邹洪平 2012-9-20
 */
@Controller
@RequestMapping({ "/fileupload2/", "pub/attachment2/" })
public class CloudAttachmentAction extends SpringSupportAction<Attachment, CloudAttachmentManager> {

	@Autowired
	private CloudAttachmentManager attachmentManager;
	@Autowired
	TjyptFileInfoDao tjyptFileInfoDao;
	@Autowired
	TjyptResourceInfoDao tjyptResourceInfoDao;
	@Autowired
	TjyptResourceInfoService tjyptResourceInfoService;
	@Autowired
	private TjyptLogService tjyptLogService;
	
	/**
	 * 上传文件到指定的文件夹，该文件夹位于系统统一配置的文件存储路径下。
	 * 
	 * @param request
	 * @param businessId
	 * @param workItem
	 * @param saveDB
	 * @param fileId
	 * @param busUniqueName
	 * @param folder
	 * @param fname
	 * @return
	 */
	@RequestMapping({ "uploadToFolder" })
	@ResponseBody
	public String doUploadToFolder(HttpServletRequest request, String businessId, String workItem, Boolean saveDB, String fileId,
	        String busUniqueName, String folder, String fname) {
		return this.uploadInternal(request, businessId, workItem, CloudAttachmentManager.SAVE_TYPE_DISK, saveDB, fileId, busUniqueName, folder,
		        fname);
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @param businessId
	 * @param workItem
	 * @param attType
	 * @param saveDB
	 * @param fileId
	 * @param busUniqueName
	 * @throws Exception
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public String doUpload(HttpServletRequest request, String businessId, String workItem, String attType, Boolean saveDB, String fileId,
	        String busUniqueName) {
		return this.uploadInternal(request, businessId, workItem, attType, saveDB, fileId, busUniqueName, null, null);
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

	/**
	 * 上传文件，可指定文件存储路径，但必须在attType==disk时有效
	 * 
	 * @param request
	 * @param businessId
	 * @param workItem
	 * @param attType
	 * @param saveDB
	 * @param fileId
	 * @param busUniqueName
	 * @param folder
	 * @param fname
	 *            业务自定义的文件保存名称，只在磁盘存储有效
	 * @return
	 */
	@RequestMapping({ "fileUp", "upload" })
	@ResponseBody
	public String uploadInternal(HttpServletRequest request, String businessId, String workItem, String attType, Boolean saveDB,
	        String fileId, String busUniqueName, String folder, String fname) {
		String pid=request.getParameter("pid");
		String spaceid=request.getParameter("spaceId");
		String spaceType=request.getParameter("spaceType");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		JSONObject info = new JSONObject();
		if (files.size() > 0) {
			Iterator<String> fileNames = multipartRequest.getFileNames();
			Attachment attachment = new Attachment();
			String fileName = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
			if (!this.checkUploadFileValid(file.getOriginalFilename())) {
				return "{\"success\":false,\"msg\":\"不合法的文件类型【" + file.getOriginalFilename() + "】\"}";
			}
			attachment.setFileSize(file.getSize());
			attachment.setFileType(file.getFileItem().getContentType());
			if(fname==null){
				String filename=file.getOriginalFilename();
				try {
					filename = URLDecoder.decode(file.getOriginalFilename(), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String name;
				try {
					name = tjyptResourceInfoService.checkInfoName(filename,0,pid,spaceType);
				} catch (Exception e) {
					name = filename;
					e.printStackTrace();
				}
				attachment.setFileName(name);
			}else{
				String name;
				try {
					name = tjyptResourceInfoService.checkInfoName(fname,0,pid,spaceType);
				} catch (Exception e) {
					name = fname;
					e.printStackTrace();
				}
				attachment.setFileName(name);
			}
		
			attachment.setBusinessId(businessId);
			attachment.setBusUniqueName(busUniqueName);
			attachment.setId(fileId);

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
				attachmentManager.saveAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
				        folder, fname,pid,spaceid,spaceType,request);
				

				JSONObject data = new JSONObject();
				data.put("id", attachment.getId());
				data.put("name", attachment.getFileName());

				String path = attachment.getFilePath();
				data.put("path", path);
				data.put("workItem", attachment.getWorkItem());

				info.put("data", data);
				info.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.logError(log, e);
				info.put("success", false);
				info.put("msg", e instanceof KhntException ? e.getMessage() : "上传文件失败");
			}
		} else {
			info.put("success", false);
			info.put("msg", "上传的文件为空");
		}
		return info.toString();
	}

	/**
	 * 根据业务ID得到附件列表
	 * 
	 * @param request
	 * @param businessId
	 *            业务id
	 * @param items
	 *            业务内容标识（1.0.4 版本添加）
	 * @throws Exception
	 */
	@RequestMapping(value = { "attachmentList", "busFiles" })
	public void attachmentList(HttpServletResponse response, String businessId, String item) throws Exception {
		if (!StringUtil.isEmpty(businessId)) {
			QueryCondition qc = new QueryCondition(Attachment.class);
			qc.addCondition("businessId", "=", businessId);
			List<Attachment> list = attachmentManager.query(qc);
			String str = "{\"success\":\"true\",\"data\":[";
			for (int i = 0; i < list.size(); i++) {
				Attachment attachment = list.get(i);
				String saveType = attachment.getSaveType();
				String relativePath = "";
				if (CloudAttachmentManager.SAVE_TYPE_DISK.equals(saveType)) {// 如果该附件保存到磁盘
					relativePath = attachment.getFilePath().replace(Factory.getWebRoot(), "");
				}
				str += i == 0 ? "{" : ",{";
				str += "\"id\":\"" + attachment.getId() + "\",\"name\":\"" + attachment.getFileName() + "\",\"filePath\":\""
				        + attachment.getFilePath() + "\",\"relativePath\":\"" + relativePath + "\",\"uploadTime\":\""
				        + DateUtil.getDate(attachment.getUploadTime()) + "\"" + ",\"workItem\":\"" + attachment.getWorkItem() + "\","
				        + "\"fileSize\":\"" + attachment.getFileSize() + "\"}";
			}
			str += "]}";
			response.getWriter().print(str);
		}
	}

	/**
	 * 根据资源ID下载,提供压缩功能
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param fileName
	 * @param proportion 压缩比例
	 * 
	 *            可指定下载时使用的文件名
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping({ "downloadByObjId", "downloadCompress" })
	public void downloadCompress(HttpServletRequest request,
			HttpServletResponse response, String id, String fileName,Double proportion)
			throws Exception {
		TjyptResourceInfo tjyptResourceInfo=tjyptResourceInfoDao.get(id);
		TjyptFileInfo tjyptFileInfo=tjyptFileInfoDao.get(tjyptResourceInfo.getFk_file_id());
		Attachment attachment = this.attachmentManager.download(tjyptFileInfo.getFk_attachment_id());
		if (attachment.getSaveType().equals("disk")) {
			SysParaInf sp = Factory.getSysPara();
			boolean relative = "cloud_relative".equals(sp
					.getProperty("cloud_attachmentPathType"));
			String uploadPath = sp.getProperty("cloud_attachmentPath");
			String filePath = ((relative) ? Factory.getWebRoot() + "/" : "")
					+ uploadPath + "/";
			if(proportion!=0 ){
				Attachment fileAtt = this.attachmentManager.downloadAsFile(id);
				File thumb = new File(fileAtt.getFilePath());
				File file=this.attachmentManager.downloadCompress(thumb, id, proportion);
				FileUtil.download(response, file, null, null);
			}else{
				try {
					
					File file1 = new File(filePath + attachment.getFilePath());                     
					if(!file1.exists()){
						Attachment fileAtt = this.attachmentManager.downloadAsFile(tjyptFileInfo.getFk_attachment_id());
						File thumb = new File(fileAtt.getFilePath());
						File file=this.attachmentManager.downloadCompress(thumb, tjyptFileInfo.getFk_attachment_id(), proportion);
						FileUtil.download(response, file, null, null);
					}else{
						FileUtil.download(response, filePath + attachment.getFilePath(),
								attachment.getFileName(), attachment.getFileType());	
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			
			}
		
		} else {
			FileUtil.download(response, attachment.getFileBody(),attachment.getFileName(), attachment.getFileType());
		}
	}
	
	
	/**
	 * 根据附件ID下载附件,添加水印功能
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param fileName
	 * 
	 *            可指定下载时使用的文件名
	 * @throws Exception
	 */
	@RequestMapping(value = { "downloadByObjId", "download" })
	public void downloadByObjId(HttpServletRequest request, HttpServletResponse response, String id, String fileName) throws Exception {
		TjyptResourceInfo tjyptResourceInfo=tjyptResourceInfoDao.get(id);
		TjyptFileInfo tjyptFileInfo=tjyptFileInfoDao.get(tjyptResourceInfo.getFk_file_id());
		Attachment attachment = this.attachmentManager.download(tjyptFileInfo.getFk_attachment_id());
		if (attachment.getSaveType().equals(CloudAttachmentManager.SAVE_TYPE_DISK)) {
			// 构建文件输入流
			SysParaInf sp = Factory.getSysPara();
			boolean relative = CloudAttachmentManager.CLOUD_SAVE_PATH_R.equals(sp.getProperty("cloud_attachmentPathType"));
			String uploadPath = sp.getProperty("cloud_attachmentPath");
			String filePath = (relative ? (Factory.getWebRoot() + "/") : "") + uploadPath + "/";
			previewSyImage(response,attachment,id,filePath,fileName,false);
		} else {
			previewSyImage(response,attachment,id,null,fileName,true);
		}
	}
	
	public void previewSyImage(HttpServletResponse response,Attachment attachment,String id,String filePath,String fileName,boolean fromdb) throws Exception{
		if(checkImage(attachment.getFileName())){
			String sy_open = Factory.getSysPara().getProperty("SY_OPEN", "0");//1启用
			String sy_style = Factory.getSysPara().getProperty("SY_STYLE", "0");//0文字 1 图片
			if("0".equals(sy_open)){
				//文字水印
				File sythumbFile = null;
				String pressText = Factory.getSysPara().getProperty("SY_TEXT", "四川省特种设备检验研究院");
				String fontName = Factory.getSysPara().getProperty("SY_FONT_NAME", "微软雅黑");
				int fontStyle = StringUtil.isEmpty(Factory.getSysPara().getProperty("SY_FONT_STYLE"))?Font.PLAIN:Integer.parseInt(Factory.getSysPara().getProperty("SY_FONT_STYLE"));
				//Color color =  StringUtil.isEmpty(Factory.getSysPara().getProperty("SY_FONT_COLOR"))?Color.BLUE:new Color(Integer.parseInt(Factory.getSysPara().getProperty("SY_FONT_COLOR")));
				Color color=new Color(145,184,0);
				String fs = Factory.getSysPara().getProperty("SY_FONT_SIZE","15");
				int fontSize = Integer.parseInt(fs);
				String al = Factory.getSysPara().getProperty("SY_FONT_ALPHA","0.2");
				float alpha = Float.parseFloat(al);
				Integer x = StringUtil.isEmpty(Factory.getSysPara().getProperty("SY_FONT_X"))?null:Integer.parseInt(Factory.getSysPara().getProperty("SY_FONT_X"));
				Integer y = StringUtil.isEmpty(Factory.getSysPara().getProperty("SY_FONT_Y"))?null:Integer.parseInt(Factory.getSysPara().getProperty("SY_FONT_Y"));
				String syimage = Factory.getWebRoot() + "/" + Factory.getSysPara().getProperty("SY_IMAGE_PATH","k/kui/public/images/logo.png");
				Integer degree = StringUtil.isEmpty(Factory.getSysPara().getProperty("SY_DEGREE"))?45:Integer.parseInt(Factory.getSysPara().getProperty("SY_DEGREE"));
				if("0".equals(sy_style)){
					if (StringUtil.isNotEmpty(id)){
						sythumbFile = this.attachmentManager.previewSYImage(id,"", pressText, fontName, fontStyle, color, fontSize,alpha, x,y,"0",degree);
					}
					if (sythumbFile != null) {
						try {
							FileUtil.download(response, sythumbFile, null, null);
						} catch (Exception e) {
							log.error("生成水印图图失败！");
							if(!fromdb)
								FileUtil.download(response, filePath + attachment.getFilePath(), StringUtil.isEmpty(fileName) ? attachment.getFileName(): fileName, attachment.getFileType());
							else
								FileUtil.download(response, attachment.getFileBody(), attachment.getFileName(), attachment.getFileType());
						}
					}
				}else if("1".equals(sy_style)){
					//图片预览水印
					if (StringUtil.isNotEmpty(id)){
						sythumbFile = this.attachmentManager.previewSYImage(id,syimage, pressText, fontName, fontStyle, color, fontSize,alpha, x,y,"1",degree);
					}
					if (sythumbFile != null) {
						try {
							FileUtil.download(response, sythumbFile, null, null);
						} catch (Exception e) {
							log.error("生成水印图图失败！");
							if(!fromdb)
								FileUtil.download(response, filePath + attachment.getFilePath(), StringUtil.isEmpty(fileName) ? attachment.getFileName(): fileName, attachment.getFileType());
							else
								FileUtil.download(response, attachment.getFileBody(), attachment.getFileName(), attachment.getFileType());
						}
					}
				}
			}else{
				if(!fromdb)
					FileUtil.download(response, filePath + attachment.getFilePath(), StringUtil.isEmpty(fileName) ? attachment.getFileName(): fileName, attachment.getFileType());
				else
					FileUtil.download(response, attachment.getFileBody(), attachment.getFileName(), attachment.getFileType());
			}
		}else{
			if(!fromdb)
				FileUtil.download(response, filePath + attachment.getFilePath(), StringUtil.isEmpty(fileName) ? attachment.getFileName(): fileName, attachment.getFileType());
			else
				FileUtil.download(response, attachment.getFileBody(), attachment.getFileName(), attachment.getFileType());
		}
	}
	
	public boolean checkImage(String fileName){
		String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fileName.toLowerCase());
        return matcher.find();
	}
	

	/**
	 * 根据附件路径下载附件
	 * 
	 * @param request
	 * @param response
	 * @param path
	 *            磁盘路径
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	@RequestMapping(value = { "downloadByFilePath", "downloadFile" })
	public void downloadByFilePath(HttpServletRequest request, HttpServletResponse response, String path, String fileName) throws Exception {
		SysParaInf sp = Factory.getSysPara();
		boolean relative = CloudAttachmentManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadPath = sp.getProperty("attachmentPath");

		String filePath = (relative ? (Factory.getWebRoot() + uploadPath + "/") : uploadPath + "/") + path;
		File file = new File(filePath);
		if (file.exists())// 如果文件存在
			FileUtil.download(response, file, fileName, null);
		else {// 尝试从数据库获取
			Attachment att = this.attachmentManager.getAttachmentByFilePath(path);
			if (att == null)
				throw new Exception("指定的文件不存在！");
			att = this.attachmentManager.download(att.getId());
			FileUtil.download(response, att.getFileBody(), att.getFileName(), null);
		}
	}

	/**
	 * 根据业务ID下载附件
	 * 
	 * @param response
	 * @param businessIds
	 * @throws Exception
	 */
	@RequestMapping("downloadByBusinessIds")
	public void downloadByBusinessIds(HttpServletResponse response, String businessIds) throws Exception {
		File zipFile = this.attachmentManager.downloadBusAttachments(businessIds);
		FileUtil.download(response, zipFile, businessIds + ".zip", null);
	}

	/**
	 * 下载单个业务附件，可指定业务位置标识，结果以压缩包形式下载
	 * 
	 * @param response
	 * @param sid
	 * @param item
	 * @throws Exception
	 */
	@RequestMapping("downloadBusinessFile")
	public void downloadBusinessFile(HttpServletResponse response, String sid, String item) throws Exception {
		File zipFile = this.attachmentManager.downloadBusAttachment(sid, item);
		FileUtil.download(response, zipFile, sid + ".zip", null);
	}

	/**
	 * 根据唯一业务名称下载附件
	 * 
	 * @param response
	 * @param businessIds
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadByBusUniqueName")
	public void downloadByBusUniqueName(HttpServletResponse response, String busUniqueName) throws Exception {
		Attachment attachment = this.attachmentManager.getBusUniqueAttachment(busUniqueName);
		attachment = this.attachmentManager.download(attachment.getId());
		FileUtil.download(response, attachment.getFileBody(), busUniqueName, null);
	}

	/**
	 * 根据附件ID删除附件
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = { "deleteAtt", "delete" })
	@ResponseBody
	public HashMap<String, Object> delete(String id, String path) throws Exception {
		attachmentManager.deleteAttach(id, path);
		return JsonWrapper.successWrapper();
	}

	/**
	 * 预览图片
	 * 
	 * @param response
	 * @param id
	 * @param path
	 */
	@RequestMapping("previewImage")
	public void previewImage(HttpServletResponse response, String id, String path) {
		File jthumbFile = null;
		if (StringUtil.isNotEmpty(path))
			jthumbFile = this.attachmentManager.previewImageWithPath(path);
		else if (StringUtil.isNotEmpty(id))
			jthumbFile = this.attachmentManager.previewImage(id);
		if (jthumbFile != null) {
			try {
				FileUtil.download(response, jthumbFile, null, null);
			} catch (Exception e) {
				log.error("下载预览图片失败！id=" + id + ";path=" + path);
			}
		}
	}
	
	/**
	 * 下载单个业务附件，可指定业务位置标识，结果以压缩包形式下载
	 * 
	 * @param response
	 * @param sid
	 * @param item
	 * @throws Exception
	 */
	@RequestMapping("downloadPack")
	public void downloadPack(HttpServletResponse response, String ids) throws Exception {
		try {
			File zipFile = this.attachmentManager.downloadBusAttachment1(ids);
			String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			FileUtil.download(response, zipFile, name + ".zip", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@RequestMapping("downloadByPath")
	public void downloadByPath(HttpServletRequest request,
			HttpServletResponse response, String path,String name,String id)
			throws Exception {
			try {
					if(id!=null){
						path = "\\"+attachmentManager.getPath(id);
					}
					
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					 tjyptLogService.saveLog("文件视频", user.getId(), user.getName(), new Date(),
							 path, "下载文件："+path, TS_Util.getIpAddress(request),"","");
				 	File root = new File(path);
			        if (!root.exists()) {
			            log.info(path + " not exist!");
			        }
			        
					FileUtil.download(response, path,
						name, "");
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
		
	}
	
/*	@RequestMapping("checkPath")
	public void checkPath(HttpServletRequest request,
			HttpServletResponse response, String path,String name)
			throws Exception {
			try {
				 	File root = new File(path);
			        if (!root.exists()) {
			            log.info(path + " not exist!");
			        }
			        
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
		
	}
	*/
	/**
	 * 下载单个业务附件，可指定业务位置标识，结果以压缩包形式下载
	 * 
	 * @param response
	 * @param sid
	 * @param item
	 * @throws Exception
	 */
	@RequestMapping("downloadPackDb")
	public void downloadPackDb(HttpServletResponse response, String path,String ids,HttpServletRequest request) throws Exception {
		try {
			File zipFile = this.attachmentManager.zipFiles2(path,ids,request);
			String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			FileUtil.download(response, zipFile, name + ".zip", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 大文件分割上传
	 * author pingZhou
	 * @param req
	 * @param resp
	 */
	@RequestMapping("uploadBigFile")
	protected HashMap<String, Object> uploadBigFile(HttpServletRequest req, HttpServletResponse resp,String folder){
		HashMap<String, Object> map = new HashMap<String, Object>();
		 String pid = req.getParameter("pid");
	 		String spaceid = req.getParameter("spaceId");
	 		String spaceType = req.getParameter("spaceType");
	 		String fileName = "";
	 		 try {
			 	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
				Map<String, MultipartFile> files = multipartRequest.getFileMap();
				
				JSONObject info = new JSONObject();
				if (files.size() > 0) {
					Iterator<String> fileNames = multipartRequest.getFileNames();
					Attachment attachment = new Attachment();
					fileName = (String) fileNames.next();
					CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
					  attachmentManager.uploadBigFile(req,resp,pid,spaceid,spaceType,folder,file);
				}
	
		
	      
	    	 
	            map.put("success", true);
	       } catch (Exception e) {
	           e.printStackTrace();
	           map.put("success", false);
	       }
	       
	       return map;
	   }
	
	@RequestMapping("downloadByPathWw")
	public void downloadByPathWw(HttpServletRequest request,
			HttpServletResponse response, String path,String name,String id)
			throws Exception {
			try {
					if(id!=null){
						path = "\\"+attachmentManager.getPath(id);
					}
					
				 	File root = new File(path);
			        if (!root.exists()) {
			            log.info(path + " not exist!");
			        }
			        
					FileUtil.download(response, path,
						name, "");
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
		
	}
	
	
	@RequestMapping("downloadByObjId2")
	public void downloadByObjId2(HttpServletRequest request,
			HttpServletResponse response, String id, String fileName,Double proportion)
			throws Exception {
		try {
			Attachment attachment = this.attachmentManager.download(id);
			if (attachment.getSaveType().equals("disk")) {

				/*String filePath = Factory.getWebRoot() + "/"  
						+ "upload" + "/";*/
				Attachment fileAtt = this.attachmentManager.downloadAsFile(id);
					File thumb = new File(fileAtt.getFilePath());
					File file=this.attachmentManager.downloadCompress(thumb, id, proportion);
					if(file==null){
						FileUtil.download(response, thumb, attachment.getFileName(), null);
					}else{
						FileUtil.download(response, file, attachment.getFileName(), null);
					}
					
				
			
			} else {
				FileUtil.download(response, attachment.getFileBody(),attachment.getFileName(), attachment.getFileType());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	/**
	 * upload下的文件打包下载
	 * author pingZhou
	 * @param response
	 * @param ids
	 * @throws Exception
	 */
	@RequestMapping("downloadPack2")
	public void downloadPack2(HttpServletResponse response, String ids,String name) throws Exception {
		try {
			File zipFile = this.attachmentManager.downloadBusAttachment2(ids);
			String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			/* String fileName = "文件打包.zip";  
			 fileName = new String(fileName.getBytes(), "ISO-8859-1"); // 必须转码，否则会丢失中文文件名。   
			 response.setContentType("APPLICATION/OCTET-STREAM");  
			 response.setHeader("Content-Disposition", "attachment; filename=" + fileName); // 设置文件名   
			 ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());   
			
			 try {
				byte[] zipFile = this.attachmentManager.downloadBusAttachment2(ids););  
			response.getOutputStream().write(zipFile); // 输出到页面
		  }   catch (Exception e) {  
		        e.printStackTrace();  
		  }  
		        finally {  // 必须释放以及关闭，否则可能会出现压缩的文件出现损坏。   
		           zos.flush();  
		           zos.close();  
	        }  */
			if(name!=null&&StringUtil.isNotEmpty(name)){
				FileUtil.download(response, zipFile,name+".zip", null);
			}else{
				FileUtil.download(response, zipFile,dateName+".zip", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
