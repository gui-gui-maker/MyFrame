package com.scts.discipline.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.discipline.bean.DisciplineMsg;
import com.scts.discipline.service.DisciplineMsgService;


@Controller
@RequestMapping("disciplineMsgAction")
public class DisciplineMsgAction extends SpringSupportAction<DisciplineMsg, DisciplineMsgService> {

	@Autowired
	private DisciplineMsgService disciplineMsgService;

	@RequestMapping("saveSendMsg")
	@ResponseBody
	public HashMap<String, Object> saveSendMsg(HttpServletRequest request, @RequestBody DisciplineMsg entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			disciplineMsgService.save(request,entity);
			map.put("success", true);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("msg","发送失败！");
			map.put("success", false);
			
		}
		
		return map;
	}
	
	
	
	
}
