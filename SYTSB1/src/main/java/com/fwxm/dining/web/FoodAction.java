package com.fwxm.dining.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.service.FoodService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;

@Controller
@RequestMapping("dining/food")
public class FoodAction extends SpringSupportAction<Food, FoodService>{
	@Autowired
	private FoodService foodService;
	@Autowired
	AttachmentManager attachmentManager;
	
	@RequestMapping(value = "saveFood")
	@ResponseBody
	public HashMap<String, Object> saveFood(HttpServletRequest request,
			Food food,String uploadFile) throws Exception {
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		try {	
				foodService.save(food,uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存信息失败，请重试！");
		}
		map.put("success", true);
		map.put("data", food);
		return map;
	}
	@RequestMapping(value = "detailFood")
	@ResponseBody
	public HashMap<String, Object> detailFood(HttpServletRequest request, String id)
			throws Exception {
		Food food = foodService.get(id);
		List<Attachment> onefiles = attachmentManager.getBusAttachment(id, "one");
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("onefiles", onefiles);
		wrapper.put("success", true);
		wrapper.put("data", food);
		return wrapper;
	}
	@RequestMapping(value = "detailFoods")
	@ResponseBody
	public HashMap<String, Object> detailFoods(HttpServletRequest request, String idstr)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> items = null;
		String[] ids = idstr.split(",");
		for(String id : ids){
			
			Food food = foodService.get(id);
			List<Attachment> onefiles = attachmentManager.getBusAttachment(id, "one");
			items = new HashMap<String, Object>();
			items.put("file", onefiles);
			items.put("food",food);
			list.add(items);
		}
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;
	}
	
	@RequestMapping(value = "deleteFood")
	@ResponseBody
	public HashMap<String, Object> deleteFood(String ids) throws Exception {
		foodService.deleteFood(ids);
		return JsonWrapper.successWrapper(ids);
	}
	@RequestMapping(value = "foodList")
	@ResponseBody
	public HashMap<String, Object> foodList(HttpServletRequest request)
			throws Exception {
		List<Object[]> foods = foodService.getFoodList();
		List<Map> listMap = new ArrayList<Map>();
		HashMap<String, Object> fmap = null;
		if(!foods.isEmpty()){
			for(Object[] obj : foods){
				fmap = new HashMap<String, Object>();
				fmap.put("id", obj[0]);
				fmap.put("text", obj[1]);
				listMap.add(fmap);
			}
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", listMap);
		return wrapper;
	}
	@RequestMapping(value = "getSeatFoodList")
	@ResponseBody
	public HashMap<String, Object> getSeatFoodList(HttpServletRequest request)
			throws Exception {
		List<Object[]> foods = foodService.getSeatFoodList();
		List<Map> listMap = new ArrayList<Map>();
		HashMap<String, Object> fmap = null;
		if(!foods.isEmpty()){
			for(Object[] obj : foods){
				fmap = new HashMap<String, Object>();
				fmap.put("id", obj[0]);
				fmap.put("text", obj[1]);
				listMap.add(fmap);
			}
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", listMap);
		return wrapper;
	}
}
