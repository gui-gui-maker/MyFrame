package com.fwxm.material.web;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.bean.GoodsType;
import com.fwxm.material.service.GoodsAndOrderManager;
import com.fwxm.material.service.GoodsManager;
import com.fwxm.material.service.GoodsTypeManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/goods")
public class GoodsController extends SpringSupportAction<Goods, GoodsManager> {
    @Autowired
    private GoodsManager goodsManager;
    @Autowired
    private GoodsTypeManager goodsTypeManager;
    @Autowired
    private GoodsAndOrderManager goodsAndOrderManager;

    /**
     * 通过参数查询goods
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getgoodsbysearch")
    @ResponseBody
    public HashMap<String, Object> getGoodsBySearch(HttpServletRequest request) throws Exception {
        return goodsManager.getGoodsBySearch(request);
    }
    

    @RequestMapping(value = "saveGoodsBean")
    @ResponseBody
    public HashMap<String, Object> saveGoodsBean(HttpServletRequest request,Goods entity){
    	try {

  	  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
  	  		if(StringUtil.isEmpty(entity.getId())){
  	  			entity.setCreate_time(new Date());
  	  	  		entity.setCreate_user_id(curUser.getId());
  	  	  		entity.setCreate_user_name(curUser.getName());
  	  	  		entity.setRk_time(new Date());
  	  	  		entity.setState("1");
  	  		}
  	  		goodsManager.save(entity);
  	  		//修改GoodsAndOrder对应的入库信息
  	  		goodsAndOrderManager.updateGoodsAndOrderByOrderIdAndGoodsIs(entity.getFk_warehousing_id(),entity.getId(),entity.getSl());
  	  		return JsonWrapper.successWrapper(entity);
  	  		
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapper();
		}
    	
    }
    
}
