package com.lsts.expert.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.expert.bean.ExpertPre;
import com.lsts.expert.service.ExpertPreService;

@Controller
@RequestMapping("expertPreAction")
public class ExpertPreAction extends SpringSupportAction<ExpertPre, ExpertPreService> {

	@Autowired
	private ExpertPreService expertPreService;
	
	
	
	
	@Override
	public HashMap<String, Object> save(HttpServletRequest request, ExpertPre entity) throws Exception {
		
		HashMap<String, Object> map  = new HashMap<String, Object>();
		try {
			expertPreService.save(entity);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			if("保存失败，已经设置！".equals(e.getMessage())){
				map.put("msg", "保存失败，已经设置！");
			}else{
				map.put("msg", "保存失败！");
			}
			
		}
		return map;
	}



	@ResponseBody
	@RequestMapping("del")
	public HashMap<String, Object> del(String ids){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			expertPreService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
		}
		
		return map;
	}
	
}
