package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.EquipAttachment;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.service.EquipAttachmentManager;
import com.lsts.humanresources.bean.Upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("EquipAttachmentAction")
public class EquipAttachmentAction extends SpringSupportAction<EquipAttachment, EquipAttachmentManager> {

    @Autowired
    private EquipAttachmentManager  equipAttachmentManager;
    
    
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, EquipAttachment equipAttachment) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				equipAttachment.setCreateDate(new Date());
				equipAttachment.setCreateId(curUser.getId());
				equipAttachment.setCreateBy(curUser.getName());
			}else if(pageStatus.equals("modify")) {
				if(equipAttachment.getCreateDate()==null){
					equipAttachment.setCreateDate(new Date());
					equipAttachment.setCreateId(curUser.getId());
					equipAttachment.setCreateBy(curUser.getName());
				}else{
					equipAttachment.setLastModifyDate(new Date());
					equipAttachment.setLastModifyId(curUser.getId());
					equipAttachment.setLastModifyBy(curUser.getName());
				}
			}
			equipAttachmentManager.save(equipAttachment);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("设备附件信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(equipAttachment);
	}
    
    /**
     * 根据设备外键ID获取设备附件列表
     * @param request
     * @param fkEquipmentId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getFjByfkEquipmentId")
  	@ResponseBody
  	public HashMap<String, Object> getFjByfkEquipmentId(HttpServletRequest request,String fkEquipmentId)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  		    List<EquipAttachment> list=equipAttachmentManager.getFjByfkEquipmentId(fkEquipmentId);
  		    wrapper.put("list", list);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("获取设备附件信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取设备附件信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
    
}