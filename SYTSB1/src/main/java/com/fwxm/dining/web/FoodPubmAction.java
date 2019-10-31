package com.fwxm.dining.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodPubm;
import com.fwxm.dining.bean.Recorder;
import com.fwxm.dining.service.FoodOrderService;
import com.fwxm.dining.service.FoodPubmService;
import com.fwxm.dining.service.FoodService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@Controller
@RequestMapping("dining/pubm")
public class FoodPubmAction extends SpringSupportAction<FoodPubm, FoodPubmService>{
	@Autowired
	private FoodPubmService foodPubmService;
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CodeTableCache codeTableCache;
	@Autowired
	private FoodOrderService foodOrderService;
	
	@RequestMapping(value = "savePubm")
	@ResponseBody
	public HashMap<String, Object> savePubm(HttpServletRequest request,String fpo_id,String ids) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String username  = user.getName();
			List<FoodPubm> list = foodPubmService.savePubs(fpo_id,ids,username); 
			wrapper.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("菜品发布失败，请重试！");
		}
		return wrapper;
	}

	/**
	 * 根据就餐时间获取发布菜单
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getPubmByFpo(String id) throws Exception {
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<FoodPubm> list=foodPubmService.getPubmByFpo(id);
				if(!list.isEmpty()){
					for (FoodPubm pubm : list) {
						Map<String,Object> map = pubm.to_Map();
						datalist.add(map);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;

	}
	@RequestMapping(value = "getQmOs")
	@ResponseBody
	public HashMap<String,Object> getQmOs(HttpServletRequest request,String id) throws Exception{
		HashMap<String,Object> wrapper = new HashMap<String, Object>();
		List<Object[]> quantumCount = foodOrderService.getQuantumCount(id);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (Object[] objs : quantumCount) {
			String qm = objs[0].toString();
			int count = Integer.parseInt(objs[1].toString());
			map = new HashMap<String,Object>();
			map.put("qm", qm);
			map.put("count", count);
			list.add(map);
		}
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;
	}
	@RequestMapping(value = "getFoods")
	@ResponseBody
	public HashMap<String,Object> getFoods(HttpServletRequest request,String id) throws Exception{
		HashMap<String,Object> wrapper = new HashMap<String, Object>();
		List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
		HashMap<String,Object> fmap = null;
		try {
			//获取当前各时间段订餐人数
			List<Object[]> quantumCount = foodOrderService.getQuantumCount(id);
			
			
			
			if (StringUtil.isNotEmpty(id)) {
				List<FoodPubm> fps = foodPubmService.getPubmByFpo(id);
				
				if(!fps.isEmpty()){
					for (FoodPubm pubm : fps) {
						fmap = new HashMap<String,Object>();
						String fid = pubm.getFood_name();
						Food f = foodService.get(fid);
						fmap.put("id", pubm.getId());
						fmap.put("text", f.getName());
						datalist.add(fmap);
						
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
				wrapper.put("datalist2", quantumCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	public List<Map<String,Object>> getCode(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		CodeTable foodTree = null;
		if(codeTableCache != null){
			foodTree = codeTableCache.getCodeTable("food_tree");
		}
		List<CodeTableValue>  ctg = null;
		if(foodTree!=null){
			ctg = foodTree.getCodeTableValues();
			if(!ctg.isEmpty()){
				for(CodeTableValue c : ctg){
					CodeTable ct = c.getCodeTable();
					List<CodeTableValue> fds = ct.getCodeTableValues();
					if(!fds.isEmpty()){
						for(CodeTableValue fd : fds){
							map = new HashMap<String,Object>();
							map.put("id", fd.getValue());
							map.put("text",fd.getName());
							list.add(map);
						}
					}
				}
			}
		}
		return list;
	}
	
}
