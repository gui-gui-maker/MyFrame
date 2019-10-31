package com.scts.discipline.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.discipline.bean.DisciplineUser;
import com.scts.discipline.service.DisciplineUserService;

@Controller
@RequestMapping("disciplineUserAction")
public class DisciplineUserAction extends SpringSupportAction<DisciplineUser, DisciplineUserService> {

	@Autowired
	private DisciplineUserService disciplineUserService;
	
	public HashMap<String, Object> del(String ids){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			disciplineUserService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
		}
		
		return map;
	}
	
}
