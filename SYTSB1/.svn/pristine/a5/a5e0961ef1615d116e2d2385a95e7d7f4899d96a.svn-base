package com.lsts.org.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.service.EnterSearchService;

@Controller
@RequestMapping("enterSearchAction")
public class EnterSearchAction extends SpringSupportAction<EnterInfo, EnterSearchService> {

	@Autowired
	private EnterSearchService enterSearchService;
	/**
	 * 根据名字查询单位信息
	 * author pingZhou
	 * @param com_name
	 * @return
	 */
	@RequestMapping("searchAllByComname")
	@ResponseBody
	public HashMap<String, Object> searchAllByComname(String com_name){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchUseByComname(com_name);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}
	
	/**
	 * 总查询入口方法
	 * author pingZhou
	 * @param name
	 * @return
	 */
	@RequestMapping("searchAll")
	@ResponseBody
	public ModelAndView searchAll(HttpServletRequest request,String name,String view){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			System.out.println("-----------------------"+name+"----------------------");
			map = enterSearchService.searchAll(name,map,view);
			
			map.put("name", name);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		if("".equals(view)||view==null){
			view = map.get("view").toString();
			if("".equals(view)){
				view = "app/research/ser_com_list";
			}
		}
		
		return new ModelAndView(view,map);
	}
	/**
	 * 总查询入口方法，用于微信
	 * author pingZhou
	 * @param name
	 * @return
	 */
	@RequestMapping("wSearchAll")
	@ResponseBody
	public ModelAndView wSearchAll(HttpServletRequest request,String name,String view){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			String years = request.getParameter("years");
			String value = request.getParameter("value");
			System.out.println("-----------------------"+name+"----------------------"+years+"----------------------"+value);
			map = enterSearchService.searchAll(name,map,view);
			
			map.put("name", name);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		/*if("".equals(view)||view==null){
			view = map.get("view").toString();
			if("".equals(view)){
				view = "app/weixin/research/app/w-ser_com_list";
			}
		}*/
		if("".equals(view)||view==null){		
			view = "app/weixin/research/app/w-ser_com_list";
		}
		
		return new ModelAndView(view,map);
	}
	
	/**
	 * 查询设备的检验信息
	 * author pingZhou
	 * @param device_id
	 * @return
	 */
	@RequestMapping("searchInspectionByDevice")
	@ResponseBody
	public ModelAndView searchInspectionByDevice(String device_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchInspectionByDevice(device_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/research/device_insp",map);
	}
	/**
	 * 查询设备的检验信息
	 * author pingZhou
	 * @param device_id
	 * @return
	 */
	@RequestMapping("wSearchInspectionByDevice")
	@ResponseBody
	public ModelAndView wSearchInspectionByDevice(String device_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchInspectionByDevice(device_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/weixin/research/app/w-device_insp",map);
	}
	
	/**
	 * 根据单位查询设备信息
	 * author pingZhou
	 * @param com_id
	 * @return
	 */
	@RequestMapping("searchDeviceByCom")
	@ResponseBody
	public ModelAndView searchDeviceByCom(String com_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchDeviceByCom(com_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/research/com_device",map);
	}
	/**
	 * 根据单位查询设备信息,微信
	 * author pingZhou
	 * @param com_id
	 * @return
	 */
	@RequestMapping("wSearchDeviceByCom")
	@ResponseBody
	public ModelAndView wSearchDeviceByCom(String com_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchDeviceByCom(com_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/weixin/research/app/w-com_device",map);
	}
	
	/**
	 * 根据单位查询设备信息
	 * author pingZhou
	 * @param com_id
	 * @return
	 */
	@RequestMapping("searchDeviceByMCom")
	@ResponseBody
	public ModelAndView searchDeviceByMCom(String com_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchDeviceByMCom(com_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/research/com_device",map);
	}
	/**
	 * 根据单位查询设备信息,微信
	 * author pingZhou
	 * @param com_id
	 * @return
	 */
	@RequestMapping("wSearchDeviceByMCom")
	@ResponseBody
	public ModelAndView wSearchDeviceByMCom(String com_id){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			map = enterSearchService.searchDeviceByMCom(com_id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return new ModelAndView("app/weixin/research/app/w-com_device",map);
	}
	
	/**
	 * 根据单位查询设备信息,微信
	 * author pingZhou
	 * @param com_id
	 * @return
	 */
	@RequestMapping("sendWeixinStaUrl")
	@ResponseBody
	public HashMap<String, Object> sendWeixinStaUrl(HttpServletRequest request,String text){
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		try {
			enterSearchService.sendWeixinStaUrl(request,text);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}
}
