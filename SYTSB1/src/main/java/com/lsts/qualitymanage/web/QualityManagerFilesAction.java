package com.lsts.qualitymanage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityManagerFiles;
import com.lsts.qualitymanage.service.QualityAttachmentManager;
import com.lsts.qualitymanage.service.QualityManagerFilesService;


/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName QualityManagerFiles
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("qualityManagerFilesAction")
public class QualityManagerFilesAction extends
		SpringSupportAction<QualityManagerFiles, QualityManagerFilesService> {

	@Autowired
	private QualityManagerFilesService qualityManagerFilesService;
	@Autowired
	private QualityAttachmentManager qualityAttachmentManager;
	@Autowired
	private AttachmentManager attachmentManager;

	
	
	/**
	 * 保存体系文件信息状态
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveBasic")
	public HashMap<String, Object> saveBasic(HttpServletRequest request, QualityManagerFiles entity) throws Exception {
		qualityManagerFilesService.saveBasic(entity);	
		return JsonWrapper.successWrapper(entity);
	}
	
	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			QualityManagerFiles order = qualityManagerFilesService.get(id);
			
			String signPicture = order.getFileId();
			List<Attachment> newFileList = attachmentManager.getBusAttachment(order.getId(), "new");
			List<Attachment> oldFileList = attachmentManager.getBusAttachment(order.getId(), "old");
			/*List<Object> list = new ArrayList<Object>();
			if(StringUtil.isNotEmpty(signPicture)){
				String [] attchNames = signPicture.split(",");
				for (int i = 0; i < attchNames.length; i++) {
					Attachment signPicturefile = attachmentManager.get(attchNames[i],"");
					list.add(signPicturefile);
				}
				
			}*/
			wrapper.put("data", order);
			wrapper.put("newFileList", newFileList);
			wrapper.put("oldFileList", oldFileList);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	/**
	 * 旧体系文件信息
	 * @param request
	 * @param id 信息id
	 * @param file_id_old 旧体系文件附件id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getOldDetail")
	public HashMap<String, Object> getOldDetail(HttpServletRequest request, String id,String file_id_old) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			QualityManagerFiles order = qualityManagerFilesService.get(id);
			Attachment FileList = attachmentManager.get(file_id_old);
			List<Attachment> newFileList = new ArrayList<Attachment>();
			newFileList.add(FileList);
			wrapper.put("data", order);
			wrapper.put("newFileList", newFileList);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("数据获取失败！");
		}
		
		return wrapper;
	}

	/**
	 * 修改体系文件信息状态
	 * author pingZhou
	 * @param request
	 * @param ids 信息id
	 * @param status 状态码
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("changeStatus")
	public HashMap<String, Object> changeStatus(HttpServletRequest request, String ids,String status) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			qualityManagerFilesService.changeStatus(ids,status);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 申请修改启用的体系文件
	 * author pingZhou
	 * @param request
	 * @param id
	 * @param remark 申请说明
	 * @param audit_id 审核人id
	 * @param audit_op 审核人姓名
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("applyEdit")
	public HashMap<String, Object> applyEdit(HttpServletRequest request, String id,String remark,String audit_id,String audit_op) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			qualityManagerFilesService.applyEdit(id,remark,audit_id,audit_op);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	@ResponseBody
	@RequestMapping("update")
	public HashMap update(HttpServletRequest request, QualityManagerFiles entity) throws Exception {
		String u_id=request.getParameter("u_id");
		qualityManagerFilesService.update(entity,u_id);

		return JsonWrapper.successWrapper(entity);
	}
	
}
