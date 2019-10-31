package com.lsts.gis.device.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.util.SecurityUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.gis.device.bean.InspectionQueryHistory;
import com.lsts.gis.device.service.GisDeviceService;
import com.lsts.gis.device.service.InspectionQueryHistoryService;

@RequestMapping("gis/device")
@Controller
public class GisDeviceAction extends SpringSupportAction<DeviceDocument, GisDeviceService>{

	@Autowired
	private GisDeviceService gisDeviceService;
	@Autowired
	private InspectionQueryHistoryService inspectionQueryHistoryService;
	
	@RequestMapping(value="getDevice")
	@ResponseBody
	public HashMap<String, Object> getDevice(String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			DeviceDocument device = gisDeviceService.get(id);
			map.put("success", true);
			map.put("data", device);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="static/getDevice")
	@ResponseBody
	public HashMap<String, Object> staticGetDevice(String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			DeviceDocument device = gisDeviceService.get(id);
			map.put("success", true);
			map.put("data", device);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 查询最新检验的设备列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryTodayList")
	@ResponseBody
	public HashMap<String, Object> queryTodayList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.queryTodayList();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "static/queryTodayList")
	@ResponseBody
	public HashMap<String, Object> staticQueryTodayList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.queryTodayList();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 查询设备
	 * @param code
	 * @param device_qr_codee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryDevices")
	@ResponseBody
	public HashMap<String, Object> queryDevices(String code,String device_qr_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List list = gisDeviceService.queryDevices(code,device_qr_code);
			User user = (User) SecurityUtil.getSecurityUser().getSysUser();
			inspectionQueryHistoryService.log(user,code,device_qr_code,list.size());
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 查询设备
	 * @param code
	 * @param device_qr_codee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryByType")
	@ResponseBody
	public HashMap<String, Object> queryByType(String type,String content) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) SecurityUtil.getSecurityUser().getSysUser();
			List list = gisDeviceService.queryByType(type,content);
			inspectionQueryHistoryService.log(user,type,content,list.size());
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/queryDevices")
	@ResponseBody
	public HashMap<String, Object> staticQueryDevices(String code,String device_qr_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List list = gisDeviceService.queryDevices(code,device_qr_code);
			InspectionQueryHistory his = inspectionQueryHistoryService.log(code,device_qr_code,list.size());
			map.put("success", true);
			map.put("data", list);
			map.put("history", his);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据使用单位查询设备
	 * @param company
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryByCompany")
	@ResponseBody
	public HashMap<String, Object> queryByCompany(String company) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List list = gisDeviceService.queryByCompany(company);
			User user = (User) SecurityUtil.getSecurityUser().getSysUser();
			inspectionQueryHistoryService.log(user,company,list.size());
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/queryByCompany")
	@ResponseBody
	public HashMap<String, Object> staticQueryByCompany(String company) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List list = gisDeviceService.queryByCompany(company);
			User user = (User) SecurityUtil.getSecurityUser().getSysUser();
			InspectionQueryHistory his = inspectionQueryHistoryService.log(user,company,list.size());
			map.put("success", true);
			map.put("data", list);
			map.put("history", his);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 查询N天的设备列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "xwQuery")
	@ResponseBody
	public HashMap<String, Object> xwQuery() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.xwQuery();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/xwQuery")
	@ResponseBody
	public HashMap<String, Object> staticWXQuery() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.xwQuery();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 查询总数（new）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "initCount")
	@ResponseBody
	public HashMap<String, Object> initCount() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> count = gisDeviceService.initCount();
			map.put("success", true);
			map.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/initCount")
	@ResponseBody
	public HashMap<String, Object> staticInitCount() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> count = gisDeviceService.initCount();
			map.put("success", true);
			map.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "queryWxCount")
	@ResponseBody
	public HashMap<String, Object> queryWxCount() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.initWxCount();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "static/queryWxCount")
	@ResponseBody
	public HashMap<String, Object> staticQueryWxCount() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.initWxCount();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 查询5分钟内被领取报告的设备
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rtQuery")
	@ResponseBody
	public HashMap<String, Object> rtQuery(int minute) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.rtQuery(minute);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 查询5分钟内被领取报告的设备
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryLastRt")
	@ResponseBody
	public HashMap<String, Object> queryLastRt() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List list = gisDeviceService.queryLastRt();
			map.put("data", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/rtQuery")
	@ResponseBody
	public HashMap<String, Object> staticRtQuery(int minute) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.rtQuery(minute);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 查询流程
	 * @param id 报告id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryFlow")
	@ResponseBody
	public HashMap<String, Object> queryFlow(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object> ds = gisDeviceService.queryFlow(id);
			map.put("success", true);
			map.put("data", ds);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/queryFlow")
	@ResponseBody
	public HashMap<String, Object> staticQueryFlow(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object> ds = gisDeviceService.queryFlow(id);
			map.put("success", true);
			map.put("data", ds);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "writePointToDocument")
	@ResponseBody
	public HashMap<String, Object> writePointToDocument(String id,String longitude,String latitude) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			gisDeviceService.writePointToDocument(id,longitude,latitude);
			map.put("success", true);
			map.put("data", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/writePointToDocument")
	@ResponseBody
	public HashMap<String, Object> staticWritePointToDocument(String id,String longitude,String latitude) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			gisDeviceService.writePointToDocument(id,longitude,latitude);
			map.put("success", true);
			map.put("data", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "queryLastFlow")
	@ResponseBody
	public HashMap<String, Object> queryLastFlow(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> list = gisDeviceService.queryLastFlow(ids);
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/queryLastFlow")
	@ResponseBody
	public HashMap<String, Object> staticQueryLastFlow(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> list = gisDeviceService.queryLastFlow(ids);
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "rtCache")
	@ResponseBody
	public HashMap<String, Object> rtCache() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.rtCache();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 为切换部门显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rtCache2")
	@ResponseBody
	public HashMap<String, Object> rtCache2() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.rtCache2();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/rtCache")
	@ResponseBody
	public HashMap<String, Object> staticRtCache() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.rtCache();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "wxCache")
	@ResponseBody
	public HashMap<String, Object> wxCache() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.wxCache();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "static/wxCache")
	@ResponseBody
	public HashMap<String, Object> staticWxCache() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = gisDeviceService.wxCache();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value = "timer")
	@ResponseBody
	public HashMap<String, Object> timer() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Double d = Math.random()*5000;
			long l = Math.round(d);  
			Thread.sleep(l);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
}
