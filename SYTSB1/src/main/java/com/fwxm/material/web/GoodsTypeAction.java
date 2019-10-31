package com.fwxm.material.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.material.bean.GoodsType;
import com.fwxm.material.service.GoodsTypeManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/goodsType")
public class GoodsTypeAction extends SpringSupportAction<GoodsType, GoodsTypeManager>{
	@Autowired
	private GoodsTypeManager goodsTypeManager;
 

  	@RequestMapping(value = "save")
  	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,GoodsType entity){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  	  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
  	  		if(StringUtil.isEmpty(entity.getId())){
  	  			entity.setCreateTime(new Date());
  	  			entity.setCreateId(curUser.getId());
  	  			entity.setCreateName(curUser.getName());
  	  			entity.setCreateOrgId(curUser.getDepartment().getId());
  	  			entity.setCreateOrgName(curUser.getDepartment().getOrgName());
  	  			entity.setCreateUnitId(curUser.getUnit().getId());
  	  			entity.setCreateUnitName(curUser.getUnit().getOrgName());
  	  		}
  	  		entity.setState("1");
  	  		
  	  		goodsTypeManager.save(entity);
			
  	  		map.put("success", true);
  	  		map.put("data", entity);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！");
		}
  		
  		return map;
	}

  	@RequestMapping(value = "deleteByIds")
  	@ResponseBody
  	public HashMap<String, Object> deleteByIds(HttpServletRequest request){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			String ids=request.getParameter("ids");
  	  		if(StringUtil.isNotEmpty(ids)){
  	  			String[] id=ids.split(",");
  	  			for (String delById : id) {
  	  	  			GoodsType entity=goodsTypeManager.get(delById);
  	  	  			entity.setState("99");//删除
  	  	  			goodsTypeManager.save(entity);
				}
  	  			map.put("success", true);
  	  		}
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除失败！");
			log.info(e.getMessage());
			e.printStackTrace();
		}
  		return map;
  	}
}
