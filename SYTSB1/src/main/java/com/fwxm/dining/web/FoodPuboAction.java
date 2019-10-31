package com.fwxm.dining.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodExt;
import com.fwxm.dining.bean.FoodPubo;
import com.fwxm.dining.bean.FoodPuboExt;
import com.fwxm.dining.service.FoodPuboService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

@Controller
@RequestMapping("dining/pubo")
public class FoodPuboAction extends SpringSupportAction<FoodPubo, FoodPuboService>{
	@Autowired
	private FoodPuboService foodPuboService;
	@Autowired
	private AttachmentManager attachmentManager;
	
	@RequestMapping("saveUseTime")
	@ResponseBody
	public HashMap<String, Object> saveUseTime(@RequestBody FoodPubo po) throws Exception {
		HashMap<String, Object> map = new HashMap<String,Object>();
		try{
			Date useDate = po.getUse_time();//用餐时间
			Calendar ca=Calendar.getInstance();
			ca.setTime(useDate);
			int type = po.getMeal_name();
			ca.add(Calendar.HOUR_OF_DAY, type==0?10:type==1?15:20);
			useDate = ca.getTime();
			po.setUse_time(useDate);
			
			Calendar curr = Calendar.getInstance();
			Date currDate = curr.getTime();
			
			if(currDate.getTime() > useDate.getTime()){
				return JsonWrapper.failureWrapperMsg("发布过时菜单，发布信息失败！");
			}
			String time = new SimpleDateFormat("yyyyMMdd").format(useDate);
			String um = type + time;
			po.setMark(um);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String name = user.getName();
			po.setPub_man(name);
			foodPuboService.savePubo(po,name);
			map.put("success", true);
			map.put("data", po);
			
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("发布信息失败，请重试！");
		}
		
		return map;
	}
	
	@RequestMapping(value = "puboDetail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		FoodPubo foodUseTime = foodPuboService.getById(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", foodUseTime);
		wrapper.put("id", id);
		return wrapper;
	}
	
	/**
	 * 取消发布
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cancelos")
	@ResponseBody
	public HashMap<String, Object> cancelos(String ids) throws Exception {
		foodPuboService.cancelos(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 提交
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(String ids) throws Exception {
		foodPuboService.submit(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 物理删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteos")
	@ResponseBody
	public HashMap<String, Object> deleteos(String ids) throws Exception {
		foodPuboService.deleteos(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 查询一周之前以后的菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dailyMenu")
	@ResponseBody
	public HashMap<String,Object> getDailyMenu() throws Exception{
		HashMap<String,Object> wrapper = new HashMap<String,Object>();
		Calendar curr = Calendar.getInstance();
		//curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)-7);
		Date date=curr.getTime();
		List<FoodPuboExt> list = foodPuboService.getDailyMenu(date);
		for (FoodPuboExt foodPuboExt : list) {
			Set<FoodExt> fs = foodPuboExt.getFs();
			Iterator i = fs.iterator();
			while(i.hasNext()){
				FoodExt f = (FoodExt) i.next();
				List<Attachment> onefiles = attachmentManager.getBusAttachment(f.getId(), "one");
				f.setAtts(new HashSet<Attachment>(onefiles));
			}
		}
	    wrapper.put("success", true);
		wrapper.put("data", list);
		  
		return wrapper;
	}
	@RequestMapping("getQmData")
	@ResponseBody
	public HashMap<String, Object> getQmData(HttpServletRequest request) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			//int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			//int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			List<FoodPubo> list = foodPuboService.getQmData();
			map.put("success", true);
			map.put("data", list);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("data", "获取数据失败...");
		}
		return map;
	}
}
