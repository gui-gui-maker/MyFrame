package com.edu.business.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.business.bean.Enroll;
import com.edu.business.service.EnrollService;
import com.edu.user.domain.User;

@Controller
@RequestMapping("enroll")
public class EnrollAction {
	@Autowired
	private EnrollService enrollService;
	
	@RequestMapping(value="toList")
	public String toProfessionList(Model model) {
		
		return "enroll-list";
	}
	
	@RequestMapping(value="list")
	@ResponseBody
	public HashMap<String,Object> findByCondition(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,Enroll enroll) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		Sort sort = new Sort(Sort.Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(page-1, rows, sort);
		Page<Enroll> enrolls;
		try {
			enrolls = enrollService.findByCondition(enroll,pageable);
			map.put("total",enrolls.getTotalElements());
			map.put("rows",enrolls.getContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("total",0);
			map.put("rows",null);
		}
		return map;
	}
	
	@RequestMapping(value="saveOrUpdate")
	@ResponseBody
	public HashMap<String,Object> saveOrUpdate(Enroll enroll) throws Exception{
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			
			User user = (User)SecurityUtils.getSubject().getPrincipal();
			enroll.setLastUpdateTime(new Date());
			enroll.setLastUpdateBy(user.getUserName());
			enrollService.saveOrUpdate(enroll);
			map.put("success",true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success",false);
			map.put("msg",e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="listCatalog")
	@ResponseBody
	public HashMap<String,Object> listCatalog(@RequestParam(value = "yxdh", defaultValue = "") String yxdh) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			List<Object[]> catalog = enrollService.findCatalog(yxdh);
			map.put("data",catalog);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",false);
		}
		return map;
	}
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="deletes")
	@ResponseBody
	public HashMap<String,Object> deletes(String ids) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			String[] idss = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < idss.length; i++) {
				list.add(idss[i]);
			}
			enrollService.logicDelete(list);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",false);
		}
		return map;
	}
}
