package com.lsts.advicenote.web;

import java.util.Date;
import java.util.HashMap;

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
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.AdviceNote;
import com.lsts.advicenote.bean.Affiche;
import com.lsts.advicenote.service.AdviceNoteService;

/**
 * 通知书管理控制器
 * 
 * @ClassName AdviceNoteAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:45:00
 */
@Controller
@RequestMapping("advicenote")
public class AdviceNoteAction extends
		SpringSupportAction<AdviceNote, AdviceNoteService> {
	@Autowired
	private AdviceNoteService adviceNoteService;
	@Autowired
	private AttachmentManager attachmentManager;

	// 保存
	@RequestMapping(value = "saveAdviceNote")
	@ResponseBody
	public HashMap<String, Object> saveAdviceNote(
			AdviceNote adviceNote) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			adviceNote.setEnter_user_id(user.getId());	// 录入人ID
			adviceNote.setEnter_user_name(user.getName());	// 录入人姓名
			adviceNote.setEnter_date(new Date());	// 录入时间
			if ("2".equals(adviceNote.getAdvicenote_type()) || "6".equals(adviceNote.getAdvicenote_type()) || "7".equals(adviceNote.getAdvicenote_type())) {
				adviceNote.setNeed_check("0");	// 是否需要审核（0：不需要 1：需要）
			}else{
				adviceNote.setNeed_check("1");
				adviceNote.setCheck_result("0");	// 审核结果（0：未审核 1：通过 2：未通过）
			}
			
			adviceNote.setCheck_user_id("");	// 审核人ID
			adviceNote.setCheck_user_name("");	// 审核人姓名
			adviceNote.setCheck_content("");	// 审核意见
			adviceNote.setCheck_date(null);		// 审核时间
			adviceNoteService.save(adviceNote);
			return JsonWrapper.successWrapper(adviceNote);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("通知书保存失败！");
		}
	}

	// 删除
	@RequestMapping(value = "deleteAdviceNote")
	@ResponseBody
	public HashMap<String, Object> deleteAdviceNote(
			HttpServletRequest request, String ids) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				String attachmentId = adviceNoteService.queryAttachmentId(idArr[i]);
				if (StringUtil.isEmpty(attachmentId)) {
					continue;
				}
				Attachment attachment = attachmentManager.get(attachmentId);
				if (attachment!=null) {
					attachmentManager.deleteAttach(attachmentId, attachment.getFilePath());	// 删除附件以及本地磁盘文件
				}
			}
			adviceNoteService.delAdviceNote(ids);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			wrapper.put("error", true);
			return JsonWrapper.failureWrapper("通知书删除失败！");
		}
		return wrapper;
	}

	// 详情
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id) {
		try {
			return super.detail(request, id);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("通知书信息查询失败！");
		}
	}
	
	/**
	 * 审核
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "check")
	@ResponseBody
	public HashMap<String, Object> check(HttpServletRequest request) throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息		
			String id = request.getParameter("id");
			String check_result = request.getParameter("check_result");
			String check_content = request.getParameter("check_content");
			AdviceNote advicenote = adviceNoteService.get(id);
			if (advicenote!=null) {
				advicenote.setCheck_result(check_result);	// 审核结果
				advicenote.setCheck_content(check_content);	// 审核意见
				advicenote.setCheck_user_id(curUser.getId());	// 审核人ID
				advicenote.setCheck_user_name(curUser.getName());	// 审核人姓名
				advicenote.setCheck_date(new Date());	// 审核时间
				adviceNoteService.save(advicenote);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("通知书审核失败，请重试！");
		}
		return JsonWrapper.successWrapper();
	}
	
	

	
}
