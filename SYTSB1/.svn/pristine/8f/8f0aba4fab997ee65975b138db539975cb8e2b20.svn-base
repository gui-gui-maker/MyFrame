package com.lsts.equipment2.web;

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
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.DocimasyFk;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.service.EquipmentManager;
import com.lsts.equipment2.service.DocimasyFkManager;



@Controller
@RequestMapping("eq/docimasyFkAction")
public class DocimasyFkAction extends SpringSupportAction< DocimasyFk,  DocimasyFkManager> {

    @Autowired
    private DocimasyFkManager  docimasyFkManager;
    @Autowired
    private EquipmentManager baseEquipment2Service;
    @Autowired
	private AttachmentManager attachmentManager;
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		//如果删除就恢复状态
		for(String id:ids.split(",")){
			DocimasyFk docimasyFk = docimasyFkManager.get(ids);
			String ide = docimasyFk.getEquipmentId();
			baseEquipment2Service.deleteDocimasyFks(ide);
		}
		docimasyFkManager.delete(ids);
		return JsonWrapper.successWrapper();
	}
	//下发
	 @RequestMapping(value = "xf")
		@ResponseBody
		public HashMap<String, Object> xf(String ids) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (ids.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				String[] idsArr = ids.split(",");
				for(int i=0;i<idsArr.length;i++){
					try {
						DocimasyFk docimasyFk = docimasyFkManager.get(idsArr[i]);
						docimasyFkManager.addxf(docimasyFk);
						map.put("success", true);
					} catch (Exception e) {
						// TODO: handle exception
						map.put("success", false);
						map.put("msg", "数据获取失败！");
					}
				}
			}
			return map;
		}
	 //接受
	 @RequestMapping(value = "js")
		@ResponseBody
		public HashMap<String, Object> js(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				DocimasyFk docimasyFk = docimasyFkManager.get(id);
				if(docimasyFk!=null){
					docimasyFkManager.addjs(docimasyFk);
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				}
			}
			return map;
		}
	//保存设备检定
	@Override
	public HashMap<String, Object> save(HttpServletRequest request,DocimasyFk docimasyFk ) throws Exception {
		 CurrentSessionUser user = SecurityUtil.getSecurityUser();
		 Date sendToDocimasy = docimasyFk.getEquipmentTime();//送检日期
		 Date executeDate = baseEquipment2Service.get(docimasyFk.getEquipmentId()).getEq_execute_date();//信息表中的实际检定日期
		 if(StringUtil.isEmpty(docimasyFk.getId())) {
			 docimasyFk.setCreateId(user.getId());
			 docimasyFk.setCreateName(user.getName());
			 docimasyFk.setCreateTime(new Date());
			 docimasyFk.setStatus(docimasyFkManager.JD_WXF);
		 }
		 //判断送检日期是否大于信息表里面的实际检定日期
		 if(executeDate==null || sendToDocimasy.getTime()>executeDate.getTime()){
			 String ids  = docimasyFk.getEquipmentId();
			 baseEquipment2Service.saveDocimasyFk(ids); 
		 }
		 return super.save(request, docimasyFk);
	}
	/**
	 * 保存并下发
	 * @param request
	 * @param docimasyFk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(HttpServletRequest request,DocimasyFk docimasyFk ) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		 Date sendToDocimasy = docimasyFk.getEquipmentTime();//送检日期
		 Date executeDate = baseEquipment2Service.get(docimasyFk.getEquipmentId()).getEq_execute_date();//信息表中的实际检定日期
		 docimasyFk.setCreateId(user.getId());
		 docimasyFk.setCreateName(user.getName());
		 docimasyFk.setCreateTime(new Date());
		 //判断送检日期是否大于信息表里面的实际检定日期
		 if(executeDate==null || sendToDocimasy.getTime()>executeDate.getTime()){
			 String ids  = docimasyFk.getEquipmentId();
			 baseEquipment2Service.saveDocimasyFk(ids); 
		 }
		 docimasyFk.setStatus(docimasyFkManager.JD_WKS);
		 return super.save(request, docimasyFk);
	}
	 /**
	  * 获取检定证书
	  * @param id
	  * @return
	  * @throws Exception
	  */
		@RequestMapping(value = "getPicture")
		@ResponseBody
		public HashMap<String, Object> getPicture(String id) throws Exception {
			List<Attachment> list = attachmentManager.getBusAttachment(id);
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			wrapper.put("success", true);
			wrapper.put("attachs", list);
			return wrapper;
		}
		@RequestMapping(value = "loadBybarcode")
		@ResponseBody
		public HashMap<String, Object> loadBybarcode(String barcode) throws Exception {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    	Equipment equipment = baseEquipment2Service.searchByBarcode(barcode);
	    	if(equipment==null){
	    		hashMap.put("success", false);
	    		hashMap.put("message", "没查到相应的设备信息！");
	    	}else{
	    		hashMap.put("success", true);
	    		hashMap.put("equipment", equipment);
	    	}
			return hashMap;
		}
}