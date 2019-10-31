package com.lsts.equipment2.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.service.EquipmentManager;
import com.lsts.equipment2.service.EquipmentBoxManager;
import com.lsts.report.bean.Report;




@Controller
@RequestMapping("equipment/box")
public class EquipmentBoxAction extends SpringSupportAction<EquipmentBox, EquipmentBoxManager> {

    @Autowired
    private EquipmentBoxManager  equipmentBoxManager;
	@Autowired
    private EquipmentManager baseEquipment2Service;
    /**
     * 保存信息
     * @param equipmentBox
     * @param request
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @RequestMapping(value="saveEqui")
    @ResponseBody
    public HashMap<String, Object> saveEqui(@RequestBody 
    		EquipmentBox equipmentBox) throws IllegalAccessException, InvocationTargetException{
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	equipmentBoxManager.saveEqui(equipmentBox,user);
    	
    	 
    	return JsonWrapper.successWrapper();
    }
    
    /**
     * 报告类型获取,手动输入后自动检索
     * @param q
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value ="getBaseReports")
    @ResponseBody
    public HashMap<String, Object> getBaseReports(String q) throws Exception{
    	String data = new String(q.getBytes("iso8859-1"),"UTF-8");
    	List<Report> list = equipmentBoxManager.getBaseReports(data);
    	JSONArray jsonArray = new JSONArray();
    	ArrayList al = new ArrayList();
    	int i=0;
    	for (Report report :list){
    		HashMap hm = new HashMap();
//			JSONObject jsonObject = new JSONObject();
			hm.put("id",report.getId());
			hm.put("text",report.getReport_name());
			hm.put("report", report);
			al.add(hm);
//			jsonArray.put(i, hm);
//			i=i+1;
		}
    	return JsonWrapper.successWrapper(al);
//    	try {
//			response.getWriter().write(jsonArray.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }
    /**
	 * 删除
	 * */
    @RequestMapping(value="delete")
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		equipmentBoxManager.delete(ids);
		return JsonWrapper.successWrapper();
	}
    
    /**
	 * 根据设备箱id获取设备箱信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBoxByid")
	@ResponseBody
	public HashMap<String, Object> getBoxByid(HttpServletRequest request, String id)
			throws Exception {
		String ids[] = id.split(",");
		List<Equipment> baseEquipment2List = new ArrayList<Equipment>();
		List<EquipmentBox> equipmentBoxList = new ArrayList<EquipmentBox>();
		//循环获取箱子信息
		for(int i=0;i<ids.length;i++){
			EquipmentBox equipmentBox = equipmentBoxManager.get(ids[i]);
			if(equipmentBox!=null) {
				if(equipmentBox.getBaseEquipment2s()!=null && equipmentBox.getBaseEquipment2s().size()>0)
					//循环获取箱子里的设备信息
					for(int j=0;j<equipmentBox.getBaseEquipment2s().size();j++){
						baseEquipment2List.add(equipmentBox.getBaseEquipment2s().get(j));
						
				}
				equipmentBoxList.add(equipmentBox);
			}
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", baseEquipment2List);
		wrapper.put("data1", equipmentBoxList);
		return wrapper;
	}
	/**
	 * 同步设备箱预警时间
	 * @return
	 */
	@RequestMapping(value = "synNextCheckTime")
	@ResponseBody
	public HashMap<String,Object> synNextCheckTime() {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	equipmentBoxManager.synNextCheckTime();
    	hashMap.put("success", true);
		return hashMap;
	}
	@RequestMapping(value = "queryBox")
	@ResponseBody
	public HashMap<String,Object> queryBox(String boxNumber) {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	hashMap=equipmentBoxManager.getEquipmentBox(boxNumber);
		return hashMap;
	}
	/*
	 * 
	 */
	@RequestMapping(value="addBoxInfo")
    @ResponseBody
    public HashMap<String, Object> addBoxInfo(HttpServletRequest request,EquipmentBox equipmentBox) throws Exception{
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			EquipmentBox box = equipmentBoxManager.addBoxInfo(request,equipmentBox);
			wrapper.put("success", true);
			wrapper.put("equipmentBox", box);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
		}
		return wrapper;
    }
}