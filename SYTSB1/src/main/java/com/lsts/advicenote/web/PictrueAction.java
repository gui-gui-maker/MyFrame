package com.lsts.advicenote.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.Affiche;
import com.lsts.advicenote.bean.Picture;
import com.lsts.advicenote.service.PictureService;
import com.lsts.employee.bean.EmployeePrinter;

/**
 * 通知书管理控制器
 * 
 * @ClassName AdviceNoteAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:45:00
 */
@Controller
@RequestMapping("picture")
public class PictrueAction extends
		SpringSupportAction<Picture,PictureService> {
	
	
	@Autowired
	private PictureService pictureService;
	@Autowired
	private AttachmentManager attachmentManager;

	// 保存
	@RequestMapping(value = "savePic")
	@ResponseBody
	public HashMap<String, Object> savePic(HttpServletRequest request,
			Picture picture) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {
			CurrentSessionUser user = super.getCurrentUser();
			
			picture.setCreated_by(user.getName());	// 录入人姓名
			picture.setCreated_date(new Date());	// 录入时间
			
			pictureService.savePic(picture, uploadFiles); // 保存人员信息（含电子签名、系统登录帐号）
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存图片失败，请重试！");
		}
		return JsonWrapper.successWrapper(picture);
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
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		Picture picture = pictureService.get(id);
		
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", picture);
		wrapper.put("attachs", list);
		
		return wrapper;
	}
	
	// 
	@RequestMapping(value = "showPic")
	@ResponseBody
	public Map showPic(HttpServletRequest request)
			throws Exception {



		Map<String, Object> outMap = new HashMap<String, Object>();

		outMap = pictureService.showPic();

	

		 
		return outMap;

	}	
	
		
		
	
}
