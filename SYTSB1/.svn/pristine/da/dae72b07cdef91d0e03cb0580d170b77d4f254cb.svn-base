package com.fwxm.library.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.library.bean.Book;
import com.fwxm.library.bean.Cupboard;
import com.fwxm.library.service.CupboardManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Cupboard
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("cupboard")
public class CupboardAction extends
		SpringSupportAction<Cupboard, CupboardManager> {

	@Autowired
	private CupboardManager cupboardManager;
	
	@Override
	public HashMap<String, Object> save(HttpServletRequest request, Cupboard entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			cupboardManager.save(entity);
			map.put("success", true);
			map.put("data",entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			cupboardManager.delete(ids);
			map.put("success", true);
			map.put("data",1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
}
