package com.lsts.sinspection.web;

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
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.sinspection.bean.SupervisionInspection;
import com.lsts.sinspection.service.SupervisionInspectionService;

/**
 * 产品监检控制器
 * 
 * @ClassName SupervisionInspectionAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-26 下午01:15:00
 */
@Controller
@RequestMapping("supervision/inspection")
public class SupervisionInspectionAction extends
		SpringSupportAction<SupervisionInspection, SupervisionInspectionService> {
	@Autowired
	private SupervisionInspectionService supervisionInspectionService;
	@Autowired
	private AttachmentManager attachmentManager;

	// 保存
	@RequestMapping(value = "saveSupervisionInspection")
	@ResponseBody
	public HashMap<String, Object> saveSupervisionInspection(
			SupervisionInspection supervisionInspection) {
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
			if ("unit".equals(userType)) {	// 独立法人单位
				supervisionInspection.setEnter_user_id(user.getId());	// 录入人ID
				supervisionInspection.setEnter_user_name(user.getName());	// 录入人姓名
				supervisionInspection.setEnter_date(new Date());	// 录入时间
				supervisionInspection.setStatus("0");	// 状态（0：未提交，1：已提交，2：已生成报告）
			}else if ("dep".equals(userType)) {	// 内部职能部门
				supervisionInspection.setLast_modify_id(user.getId());	
				supervisionInspection.setLast_modify_name(user.getName());
				supervisionInspection.setLast_modify_date(new Date());
			}
			supervisionInspectionService.save(supervisionInspection);
			return JsonWrapper.successWrapper(supervisionInspection);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("产品监检保存失败！");
		}
	}

	// 删除
	@RequestMapping(value = "deleteSupervisionInspection")
	@ResponseBody
	public HashMap<String, Object> deleteSupervisionInspection(
			HttpServletRequest request, String ids) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				String attachmentId = supervisionInspectionService.queryAttachmentId(idArr[i]);
				if (StringUtil.isEmpty(attachmentId)) {
					continue;
				}
				Attachment attachment = attachmentManager.get(attachmentId);
				if (attachment!=null) {
					attachmentManager.deleteAttach(attachmentId, attachment.getFilePath());	// 删除附件以及本地磁盘文件
				}
			}
			supervisionInspectionService.delSupervisionInspection(ids);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			wrapper.put("error", true);
			return JsonWrapper.failureWrapper("产品监检删除失败！");
		}
		return wrapper;
	}

	// 详情
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			SupervisionInspection supervisionInspection = supervisionInspectionService.get(id);
			wrapper.put("success", true);
			wrapper.put("data", supervisionInspection);
			request.getSession().setAttribute("supervisionInspection", supervisionInspection);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("产品监检信息查询失败！");
		}
	}
	
	/**
	 * 提交
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(HttpServletRequest request) throws Exception {
		try {
			String id = request.getParameter("id");
			if(StringUtil.isNotEmpty(id)){
				String send_user_id = request.getParameter("send_user_id");
				String send_user_name = request.getParameter("send_user_name");
				SupervisionInspection supervisionInspection = supervisionInspectionService.get(id);
				if (supervisionInspection!=null) {
					supervisionInspection.setSend_user_id(send_user_id);		// 流转监检人员ID
					supervisionInspection.setSend_user_name(send_user_name);	// 流转监检人员姓名
					supervisionInspection.setStatus("1");	// 状态（0：未提交，1：已提交，2：已生成报告）
					supervisionInspectionService.save(supervisionInspection);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("产品监检提交失败，请重试！");
		}
		return JsonWrapper.successWrapper();
	}
}
