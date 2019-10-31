package com.lsts.qualitymanage.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.qualitymanage.bean.QualityFfhs;
import com.lsts.qualitymanage.service.QualityFfhsManager;


@Controller
@RequestMapping("quality/ffhs")
public class QualityFfhsAction extends SpringSupportAction<QualityFfhs, QualityFfhsManager> {

    @Autowired
    private QualityFfhsManager  qualityFfhsManager;
    @Autowired
   	private AttachmentManager attachmentManager;
    
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody QualityFfhs qualityFfhs) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		String uploadFiles = request.getParameter("uploadFiles");
   		HashMap<String, Object> map = new HashMap<String, Object>();
//   		if(qualityFfhs.getId()==null || qualityFfhs.getId().equals("")){
		qualityFfhsManager.saveQualityFfhs(qualityFfhs,user,uploadFiles);
		map.put("success", true);
		map.put("msg", "数据保存成功！");
//   		}else{
//   			
//   		}
   		
		return map;
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
   		QualityFfhs qualityFfhs=qualityFfhsManager.get(id);
   		HashMap<String, Object> wrapper = new HashMap<String, Object>();
   		List<Attachment> list = attachmentManager.getBusAttachment(id);
   		wrapper.put("success", true);
   		wrapper.put("data", qualityFfhs);
   		wrapper.put("attachs", list);
   		return wrapper;

   	}
   	 
     /**
 	 * 提交
 	 * 
 	 * @param request
 	 * @param id
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "submit")
 	@ResponseBody
 	public HashMap<String, Object> submit( String id) throws Exception {
 		QualityFfhs qualityFfhs=qualityFfhsManager.get(id);
 		HashMap<String, Object> wrapper = new HashMap<String, Object>();
 		qualityFfhs.setStatus("YTJ");
 		qualityFfhsManager.save(qualityFfhs);
 		wrapper.put("msg", "数据提交成功！");
 		wrapper.put("success", true);
 		return wrapper;

 	}
 	
 	/**
 	 * 接收
 	 * 
 	 * @param request
 	 * @param id
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "filejs")
 	@ResponseBody
 	public HashMap<String, Object> filejs( String id,String reclaimStatus) throws Exception {
 		HashMap<String, Object> wrapper = new HashMap<String, Object>();
 		QualityFfhs qualityFfhs=qualityFfhsManager.get(id);
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User)user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
 		qualityFfhs.setStatus("YJS");
 		String userId=emp.getId();
 		qualityFfhs.setReclaimName(user.getUserName());
 		qualityFfhs.setReclaimNameId(userId);
 		qualityFfhs.setReclaimTime(new Date());
 		qualityFfhs.setReclaimStatus(reclaimStatus);
 		qualityFfhsManager.save(qualityFfhs);
 		wrapper.put("msg", "数据接收成功！");
 		wrapper.put("success", true);
 		return wrapper;
 	}
}