package com.lsts.equipment2.web;

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
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.DocimasyFk;
import com.lsts.equipment2.bean.DocimasyFks;
import com.lsts.equipment2.service.EquipmentManager;
import com.lsts.equipment2.service.DocimasyFkManager;
import com.lsts.equipment2.service.DocimasyFksManager;



@Controller
@RequestMapping("eq/docimasyFksAction")
public class DocimasyFksAction extends SpringSupportAction<DocimasyFks, DocimasyFksManager> {

    @Autowired
    private DocimasyFksManager  docimasyFksManager;
    @Autowired
    private DocimasyFkManager  docimasyFkManager;
    @Autowired
    private EquipmentManager baseEquipment2Service;
    @Autowired
	private AttachmentManager attachmentManager;
    
    
    
    @RequestMapping(value="fdetail1")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{
    	List<DocimasyFks> list = docimasyFksManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
    
    @Override
	public HashMap<String, Object> save(HttpServletRequest request,DocimasyFks docimasyFks ) throws Exception {
    	String uploadFiles = request.getParameter("uploadFiles");
    	String  id = docimasyFks.getDocimasyFk().getId();//获取到父类的id
    	String status = docimasyFks.getStatus();//子类的状态
    	DocimasyFk  docimasyFk = docimasyFkManager.get(id);//父类的集合
    	docimasyFk.setResult(docimasyFks.getResult());//将父类结果回写为最新的结果
    	docimasyFk.setRemark(docimasyFks.getRemark());
    	String ids =  docimasyFk.getEquipmentId();//获得设备id
    	Date practicalTime = docimasyFks.getPracticalTime();
    	Date nexttime = docimasyFkManager.setStatus(uploadFiles,docimasyFk,status,practicalTime);//进行状态和实际检定日期回写 回写父类
    	//如果是已完成就回写设备状态（并根据实际检定日期判断是否回写档案信息里面的实际检定日期与下次检定日期）
    	if(status.equals("ywc")){
    		baseEquipment2Service.saveDocimasyFks(uploadFiles,ids, practicalTime, nexttime);
    	}
			return super.save(request, docimasyFks);
	}
    
}