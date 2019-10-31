package com.lsts.qualitymanage.web;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityManagerFiles;
import com.lsts.qualitymanage.bean.QualityManagerFilesUpdate;
import com.lsts.qualitymanage.service.QualityManagerFilesUpdateService;


/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName QualityFilesUpdate
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("qualityFilesUpdateAction")
public class QualityManagerFilesUpdateAction extends
		SpringSupportAction<QualityManagerFilesUpdate, QualityManagerFilesUpdateService> {

	@Autowired
	private QualityManagerFilesUpdateService qualityManagerFilesUpdateService;
	@Autowired
	private AttachmentManager attachmentManager;
	
	@RequestMapping(value = "saveOpinion")
	@ResponseBody
	public HashMap<String, Object> saveOpinion(String ids, String opinion,String userId,String userName,
			String status,String auditDate,String next_op_name,String next_op_id){
	  try{
		  status= URLDecoder.decode(status, "UTF-8");
		  userName= URLDecoder.decode(userName, "UTF-8");
		  opinion=URLDecoder.decode(opinion, "UTF-8");
		  Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(auditDate);
		  if(StringUtil.isNotEmpty(next_op_name)){
			  next_op_name= URLDecoder.decode(next_op_name, "UTF-8");
		  }
		  qualityManagerFilesUpdateService.saveOpinion(ids,opinion,userId,userName,status,date,next_op_name,next_op_id);
		  return JsonWrapper.successWrapper();
	  }
	  catch(Exception e){
		  return JsonWrapper.failureWrapperMsg("保存意见信息出错："+e.getMessage());
	  }
	}
	@RequestMapping(value = "updateFiles")
	@ResponseBody
	public HashMap<String, Object> updateFiles(String id){
	  try{
		  String qf_id =qualityManagerFilesUpdateService.updateFiles(id);
		  return JsonWrapper.successWrapper(qf_id);
	  }
	  catch(Exception e){
		  return JsonWrapper.failureWrapperMsg("修改出错："+e.getMessage());
	  }
	}
	@RequestMapping(value = "finishuUpdateFiles")
	@ResponseBody
	public HashMap<String, Object> finishuUpdateFiles(String ids){
	  try{
		  qualityManagerFilesUpdateService.finishuUpdateFiles(ids);
		  return JsonWrapper.successWrapper();
	  }
	  catch(Exception e){
		  return JsonWrapper.failureWrapperMsg("修改出错："+e.getMessage());
	  }
	}
	/**
	 * 文件更新详情
	 * @param request
	 * @param id 信息id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getLogDetail")
	public HashMap<String, Object> getLogDetail(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			QualityManagerFilesUpdate order = qualityManagerFilesUpdateService.get(id);
			Attachment FileList = attachmentManager.get(order.getFile_id_new());
			List<Attachment> newFileList = new ArrayList<Attachment>();
			newFileList.add(FileList);
			Attachment FileList2 = attachmentManager.get(order.getFile_id_old());
			List<Attachment> oldFileList = new ArrayList<Attachment>();
			oldFileList.add(FileList2);
			wrapper.put("data", order);
			wrapper.put("newFileList", newFileList);
			wrapper.put("oldFileList", oldFileList);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("数据获取失败！");
		}
		
		return wrapper;
	}
	/**
	 * 申请修改启用的体系文件
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
	public HashMap<String, Object> applyEdit(HttpServletRequest request, QualityManagerFilesUpdate entity) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			qualityManagerFilesUpdateService.applyEdit(entity);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
}
