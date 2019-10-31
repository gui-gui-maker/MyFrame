package com.khnt.pub.fileupload.web;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
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
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.pub.fileupload.service.AttachmentTsManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.DateUtil;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.Batch;
import com.lsts.archives.service.ArchivesBoxManager;

import net.sf.json.JSONObject;
import util.FileUtil;

/***
 * 
 * 附件管理页面展现和交互类.
 * 
 * @author 2012-3-13
 * @author 邹洪平 2012-9-20
 */
@Controller
@RequestMapping({ "/fileuploadTs/", "pub/attachmentTs/" })
public class AttachmentTsAction extends SpringSupportAction<Attachment, AttachmentTsManager> {

	@Autowired
	private AttachmentTsManager attachmentTsManager;
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
		return this.uploadInternal(request, businessId, workItem, attachmentTsManager.SAVE_TYPE_DISK, saveDB, fileId, busUniqueName, folder,
		        fname);
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
	 * 扫描上传文件
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("scanTsUpload")
	public String uploadFiles(HttpServletRequest request) throws Exception {
		//标识
		ServletContext context = (ServletContext)request.getSession().getServletContext();
		String params=null;//所有参数的字符串，传
		String batch = null;//批次
		String businessId=null;//业务id
		String userId = null;
		String userName = null;
		String workItem=null;//
		String attType=null;
		Boolean saveDB=null;//默认false
        String busUniqueName=null;//业务唯一编号名称
        String folder=null;//文件夹
        String uploadType=null;//上传类型0：检验报告，1原始记录，2自检报告3其他
        String fname=null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		if(files.size()>0){
			Iterator<String> fileNames = multipartRequest.getFileNames();//file.getFileItem().getFieldName();
			while(fileNames.hasNext()) {
				System.out.println("*************扫描上传图片**************");
				Attachment attachment = new Attachment();
				String fileName = (String) fileNames.next();
				System.out.println("*************图片名称**************"+fileName);
				CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
				//原文件名称
				fname= file.getOriginalFilename();
				Batch bat=null;
				try {
					//取出参数
					if(null==params){
						
						params = file.getFileItem().getFieldName();
						JSONObject obj = JSONObject.fromObject(params);
						businessId = obj.getString("businessId");
						busUniqueName = obj.getString("busUniqueName");
						uploadType = obj.getString("uploadType");
						folder = uploadType+busUniqueName;
						batch = obj.getString("batch");
						userId = obj.getString("userId");
						userName = obj.getString("userName");
					}
					
					bat = (Batch)context.getAttribute("batch");
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				}
				if (!this.checkUploadFileValid(file.getOriginalFilename())) {
					System.out.println("*************不合法的文件类型**************");
					request.setAttribute("msg", "不合法的文件类型"+ file.getOriginalFilename());
					return "uploadFaided";
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
					System.out.println("*************当前请求未包含有效的安全凭证！**************");
					log.warn("当前请求未包含有效的安全凭证！");
				}
				attachment.setWorkItem(workItem);
				try {
					System.out.println("*************保存文件前0**************");
					if(bat!=null && bat.getId().equals(batch)){
						System.out.println("*************保存文件前1**************");
						int total = bat.getTotal();
						total++;
						bat.setTotal(total);
						//有，并且等
						attachmentTsManager.saveBatchAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
								folder, fname,false);
						System.out.println("*************保存文件第一张图片**************");
					}else{
						System.out.println("*************保存文件前1**************");
						//第一次为空
						//有，但是不等
						attachmentTsManager.saveBatchAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
								folder, fname,true);
						System.out.println("*************保存文件剩余图片**************");
						bat = new Batch();
						bat.setId(batch);
						int total = bat.getTotal();
						total++;
						bat.setTotal(total);
					}
					
					JSONObject data = new JSONObject();
					data.put("id", attachment.getId());
					data.put("name", attachment.getFileName());
	
					String path = attachment.getFilePath();
					data.put("path", path);
					data.put("workItem", attachment.getWorkItem());
					data.put("file_id", attachment.getBusinessId());
					data.put("uploadType", uploadType);
					data.put("batch", batch);
					data.put("folder", folder);
					list.add(data);
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.logError(log, e);
					request.setAttribute("msg", "文件上传出错！");
					int failure = bat.getFailure();
					failure++;
					bat.setFailure(failure);
					return "uploadFaided";
				}finally{
					context.setAttribute("batch", bat);
				}
			}
		} else {
			String time = new SimpleDateFormat("yyMMdd HH:mm:ss").format(new Date());
			System.out.println(time+"-------------没有上传的文件");
			request.setAttribute("msg", "没有上传的文件");
			return "app/archives/uploadFailed";
		}
		attachmentTsManager.addScanUploader(userId,userName,businessId);
		request.setAttribute("list", list);
		request.setAttribute("reportId", businessId);
		return "forward:../uploadsAction/a/saveUploads.do";
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
			attachment.setFileName(file.getOriginalFilename());
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
				attachmentTsManager.saveAttached(file.getInputStream(), attachment, attType, null == saveDB ? true : saveDB.booleanValue(),
				        folder, fname);

				JSONObject data = new JSONObject();
				data.put("id", attachment.getId());
				data.put("name", attachment.getFileName());

				String path = attachment.getFilePath();
				data.put("path", path);
				data.put("workItem", attachment.getWorkItem());

				info.put("data", data);
				info.put("success", true);
			} catch (Exception e) {
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
	 * @param item
	 *            业务内容标识（1.0.4 版本添加）
	 * @throws Exception
	 */
	@RequestMapping(value = { "attachmentList", "busFiles" })
	public void attachmentList(HttpServletResponse response, String businessId, String item) throws Exception {
		if (!StringUtil.isEmpty(businessId)) {
			QueryCondition qc = new QueryCondition(Attachment.class);
			qc.addCondition("businessId", "=", businessId);
			List<Attachment> list = attachmentTsManager.query(qc);
			String str = "{\"success\":\"true\",\"data\":[";
			for (int i = 0; i < list.size(); i++) {
				Attachment attachment = list.get(i);
				String saveType = attachment.getSaveType();
				String relativePath = "";
				if (AttachmentManager.SAVE_TYPE_DISK.equals(saveType)) {// 如果该附件保存到磁盘
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
	 * 根据附件ID下载附件,提供压缩功能
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
	@RequestMapping({ "downloadByObjId", "downloadCompress" })
	public void downloadCompress(HttpServletRequest request,
			HttpServletResponse response, String id, String fileName,Double proportion)
			throws Exception {
		Attachment attachment = this.attachmentTsManager.download(id);
		if (attachment.getSaveType().equals("disk")) {
			SysParaInf sp = Factory.getSysPara();
			boolean relative = "relative".equals(sp
					.getProperty("attachmentPathType"));
			String uploadPath = sp.getProperty("attachmentPath");
			String filePath = ((relative) ? Factory.getWebRoot() + "/" : "")
					+ uploadPath + "/";
			if(proportion!=0 ){
				Attachment fileAtt = this.attachmentTsManager.downloadAsFile(id);
				File thumb = new File(fileAtt.getFilePath());
				File file=this.attachmentTsManager.downloadCompress(thumb, id, proportion);
				FileUtil.download(response, file, null, null);
			}else{
			FileUtil.download(response, filePath + attachment.getFilePath(),
					(StringUtil.isEmpty(fileName)) ? attachment.getFileName()
							: fileName, attachment.getFileType());
			}
		
		} else {
			FileUtil.download(response, attachment.getFileBody(),
					attachment.getFileName(), attachment.getFileType());
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
		Attachment attachment = this.attachmentTsManager.download(id);
		if (attachment.getSaveType().equals(AttachmentManager.SAVE_TYPE_DISK)) {
			// 构建文件输入流
			SysParaInf sp = Factory.getSysPara();
			boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
			String uploadPath = sp.getProperty("attachmentPath");
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
						sythumbFile = this.attachmentTsManager.previewSYImage(id,"", pressText, fontName, fontStyle, color, fontSize,alpha, x,y,"0",degree);
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
						sythumbFile = this.attachmentTsManager.previewSYImage(id,syimage, pressText, fontName, fontStyle, color, fontSize,alpha, x,y,"1",degree);
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
		boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadPath = sp.getProperty("attachmentPath");

		String filePath = (relative ? (Factory.getWebRoot() + uploadPath + "/") : uploadPath + "/") + path;
		
		File file = new File(filePath);
		if (file.exists())// 如果文件存在
			FileUtil.download(response, file, fileName, null);
		else {// 尝试从数据库获取
			Attachment att = this.attachmentTsManager.getAttachmentByFilePath(path);
			if (att == null)
				throw new Exception("指定的文件不存在！");
			att = this.attachmentTsManager.download(att.getId());
			FileUtil.download(response, att.getFileBody(), att.getFileName(), null);
		}
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
	@RequestMapping(value = { "downloadByFilePath2"})
	public void downloadByFilePath2(HttpServletRequest request, HttpServletResponse response, String path, String fileName) throws Exception {
		SysParaInf sp = Factory.getSysPara();
		boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadPath = sp.getProperty("attachmentPath");

		String filePath = (relative ? (Factory.getWebRoot() + uploadPath + "/") : uploadPath + "/") + path;
		String sufName = path.substring(path.indexOf("."));
		
		File file = new File(filePath);
		if (file.exists())// 如果文件存在
			FileUtil.download(response, file, fileName, null, sufName);
		else {// 尝试从数据库获取
			Attachment att = this.attachmentTsManager.getAttachmentByFilePath(path);
			if (att == null)
				throw new Exception("指定的文件不存在！");
			att = this.attachmentTsManager.download(att.getId());
			FileUtil.download(response, att.getFileBody(), att.getFileName(), null);
		}
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
		Attachment attachment = this.attachmentTsManager.getBusUniqueAttachment(busUniqueName);
		attachment = this.attachmentTsManager.download(attachment.getId());
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
		attachmentTsManager.deleteAttach(id, path);
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
			jthumbFile = this.attachmentTsManager.previewImageWithPath(path);
		else if (StringUtil.isNotEmpty(id))
			jthumbFile = this.attachmentTsManager.previewImage(id);
		if (jthumbFile != null) {
			try {
				FileUtil.download(response, jthumbFile, null, null);
			} catch (Exception e) {
				log.error("下载预览图片失败！id=" + id + ";path=" + path);
			}
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
	 * @author GaoYa
	 * @date 2017-02-17 17:36
	 */
	@RequestMapping({ "downloadByObjId", "downloadCompress2" })
	public void downloadCompress2(HttpServletRequest request,
			HttpServletResponse response, String id, String fileName,Double proportion)
			throws Exception {
		Attachment attachment = this.attachmentTsManager.download(id);

		if (attachment.getSaveType().equals("disk")) {
			SysParaInf sp = Factory.getSysPara();
			boolean relative = "relative".equals(sp.getProperty("attachmentPathType"));
			// String uploadPath = sp.getProperty("attachmentPath");
			String filePath = ((relative) ? Factory.getWebRoot() + "/" : "") + "/";

			String pic_type = attachment.getJy_pic_category();
			String attachmentPath = sp.getProperty("mo_attachmentPath");
			if ("bhg_pic".equals(pic_type)) {
				attachmentPath = sp.getProperty("mo_bhg_attachmentPath");
			} else if ("zzjy_pic".equals(pic_type)) {
				attachmentPath = sp.getProperty("mo_zzjy_attachmentPath");
			} else if ("EXAMINE_NAME".equals(pic_type)) {
				attachmentPath = sp.getProperty("mo_sign_attachmentPath");
			}
			filePath += attachmentPath + "/";

			if (proportion != 0) {
				Attachment fileAtt = this.attachmentTsManager.downloadAsFile(id);
				File thumb = new File(fileAtt.getFilePath());
				File file = this.attachmentTsManager.downloadCompress(thumb, id, proportion);
				FileUtil.download(response, file, null, null);
			} else {
				try {
					FileUtil.download(response, filePath + attachment.getFilePath(),
							(StringUtil.isEmpty(fileName)) ? attachment.getFileName() : fileName,
							attachment.getFileType());
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		} else {
			FileUtil.download(response, attachment.getFileBody(), attachment.getFileName(), attachment.getFileType());
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
	@RequestMapping("downloadPackDb")
	public void downloadPackDb(HttpServletResponse response, String path,String ids,HttpServletRequest request) throws Exception {
		try {
			File zipFile = this.attachmentTsManager.zipFiles2(path,ids,request);
			String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			FileUtil.download(response, zipFile, name + ".zip", null);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
