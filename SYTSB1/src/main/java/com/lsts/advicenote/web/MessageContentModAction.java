package com.lsts.advicenote.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.advicenote.bean.MessageContentMod;
import com.lsts.advicenote.service.MessageContentModService;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;


@Controller
@RequestMapping("messageContentModAction")
public class MessageContentModAction extends SpringSupportAction<MessageContentMod,MessageContentModService> {

	@Autowired
	private MessageContentModService contentModService;
	@Autowired
	MessageService messageService;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, MessageContentMod entity) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			contentModService.save(entity);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			if("编号重复，请修改！".equals(e.getMessage())) {
				map.put("msg", "编号重复，请修改！");
			}else {
				map.put("msg", "保存失败！");
			}
		}
		
		return map;
		
	}
	/**
	 * 删除配置信息
	 * author pingZhou
	 * @param request
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("del")
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			contentModService.del(ids);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
			
		}
		
		return map;
		
	}
	
	
	@ResponseBody
	@RequestMapping("test")
	public HashMap<String, Object> test(HttpServletRequest request, String ids) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("applyOp", "周定萍");
			map1.put("applyTime", "");
			messageService.sendMassageByConfig(request,"","13551844107","消息测试！","overWork","",map1,null,"1",Constant.PEOPLE_CORPID,Constant.PEOPLE_PWD);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
			
		}
		
		return map;
		
	}
	
	
	
}
