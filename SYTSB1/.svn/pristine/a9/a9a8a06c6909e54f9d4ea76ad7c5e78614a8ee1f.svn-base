package com.lsts.inspection.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentTsManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.ReportPicValue;
import com.lsts.inspection.service.ReportPicValueService;

import net.sf.json.JSONObject;



@Controller
@RequestMapping("reportpic")
public class ReportPicAction extends
		SpringSupportAction<ReportPicValue, ReportPicValueService> {
	@Autowired
	private ReportPicValueService reportPicValueService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private AttachmentTsManager attachmentTsManager;

	@RequestMapping(value = "fileUp")
	public void doUpload(HttpServletRequest request, HttpServletResponse response, String ins_info_id, String item_name, String type, String pic_type) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		String str = "{\"success\":\"false\",\"msg\":\"上传失败\"}";
		if (files.size() > 0) {
			Iterator<String> fileNames = multipartRequest.getFileNames();
			//Attachment attachment = new Attachment();
			String fileName = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
			try {
	            String id = reportPicValueService.fileUp(file,  item_name,  ins_info_id,  type) ;
	            if("record".equals(pic_type)){
	            	String pic_category = "";
	            	if(StringUtil.isNotEmpty(ins_info_id)){
						List<Attachment> attachments = attachmentTsManager.getAttachment(ins_info_id, "GC_");
						for(Attachment attachment: attachments){
							pic_category = attachment.getJy_pic_category();
							employeesService.deleteAttach(attachment);
						}
					}
	            	
	            	CurrentSessionUser user = SecurityUtil.getSecurityUser();	            	
	            	Attachment attachment = new Attachment();
					attachment.setFileSize(file.getSize());
					attachment.setFileType(file.getFileItem().getContentType());
					attachment.setFileName(file.getOriginalFilename());
					attachment.setBusinessId(ins_info_id);			
					attachment.setUploader(user.getId());
					attachment.setUploaderName(user.getName());				
					if ("GC_8_CHDBWT".equals(pic_category) || "GC_17_QXWZSYT".equals(pic_category)
							|| "GC_18_QXWZSYT".equals(pic_category) || "GC_19_QXWZSYT".equals(pic_category)){
						employeesService.saveAttachment(file.getInputStream(), attachment, "", true, pic_category);
					}
	            }
	            
	            /*ZpUser zpUser= null;
	            if(StringUtil.isNotEmpty(name)){
	                zpUser= zpUserManager.getUserName(name);
	            }*/
				/*attachment.setFileSize(file.getSize());
				attachment.setFileType(file.getFileItem().getContentType());
				attachment.setFileName(file.getOriginalFilename());
				attachment.setBusinessId(businessId);
				if (zpUser != null) {
					attachment.setUploader(zpUser.getId());
					attachment.setUploaderName(zpUser.getName());
				}
				attachment.setWorkItem(workItem);
				attachmentManager.saveAttached(file.getInputStream(), attachment, attType);*/
				/*str = "{\"success\":\"true\",\"msg\":\"上传成功\",\"data\":{\"id\":\"" + attachment.getId()
						+ "\",\"name\":\"" + attachment.getFileName() + "\",\"filesize\":\"" + attachment.getFileSize() + "\"}}";*/
	            str = "{\"success\":\"true\",\"msg\":\"上传成功\",\"data\":{\"id\":\"" + id
						+ "\",\"filesize\":\"" + file.getSize() + "\"}}";
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		} else {
			str = "{\"success\":\"false\",\"msg\":\"没有上传文件!\"}";
		}
		response.getWriter().write(str);
	}
	
	@RequestMapping(value = "saveDraw")
	public void saveDraw(HttpServletRequest request, HttpServletResponse response, String ins_info_id, String item_name, String type) throws Exception {
		
		String vgxml = request.getParameter("vgxml");
		String base64 = request.getParameter("base64");
		String jpg = base64.split("base64,")[1];
		String id = reportPicValueService.saveDraw(ins_info_id, item_name, vgxml, jpg);
		
		
		String str = "{\"success\":\"true\",\"msg\":\"上传成功\",\"data\":{\"id\":\"" + id
						+ "\",\"filesize\":\"" + 0 + "\"}}";
		response.getWriter().write(str);
	}
	
	@RequestMapping(value = "getPicXml")
	@ResponseBody
	public void getPicXml(HttpServletResponse res,String ins_info_id, String item_name) throws Exception {
		
		String xml = reportPicValueService.getPicXml(ins_info_id, item_name);
		
		xml = xml.split("</vg>")[0]+"</vg>";
		JSONObject json  = new JSONObject();
		json.put("success", true);
		json.put("msg", "获取成功");
		json.put("xml", xml);
		res.getWriter().print(json.toString());
	}
	
	
}
