package com.lsts.device.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.lsts.common.service.CodeTablesService;
import com.lsts.common.service.InspectionCommonService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.Accessory;
import com.lsts.device.bean.BoilerPara;
import com.lsts.device.bean.CablewayDTO;
import com.lsts.device.bean.CablewayPara;
import com.lsts.device.bean.CranePara;
import com.lsts.device.bean.DeviceDTO;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.bean.ElevatorPara;
import com.lsts.device.bean.EnginePara;
import com.lsts.device.bean.PressurevesselsPara;
import com.lsts.device.bean.RidesPara;
import com.lsts.device.dao.DeviceDao;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.log.service.SysLogService;
import com.lsts.org.service.EnterService;

/**
 * 特种设备信息管理 web controller
 * 
 * @author 肖慈边 2014-1-8
 */

@Controller
@RequestMapping("device/basic")
public class DeviceAction extends
		SpringSupportAction<DeviceDocument, DeviceService> {


	@Autowired
	private EnterService enterService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CodeTablesService codeTablesService;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private InspectionCommonService inspectionCommonService;
	@Autowired
	private SysLogService logService;

	
	//保存基础信息和参数信息
	@RequestMapping(value = "changeInfo")
	@ResponseBody
	public HashMap<String, Object> changeInfo(@RequestBody InspectionInfo entity,HttpServletRequest request)
				throws Exception {
			
				String id = request.getParameter("id");
				
				deviceService.changeInfo(entity,id);
			
			return JsonWrapper.successWrapper(id);
	 
     
}
	//保存基础信息和参数信息
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(@RequestBody DeviceDocument entity,String type,HttpServletRequest request)
			throws Exception {
		boolean canSave = true;
		HashMap<String, Object>  map = new HashMap<String, Object>();

		if("3".equals(type)){
			if(!"540100".equals(entity.getDevice_area_code())){
				// 1、验证电梯二维码编号是否重复
				List<DeviceDocument> list = deviceService.getDeviceInfosByQRCode(entity.getId(), entity.getDevice_qr_code());
				for (DeviceDocument device : list) {
					if (device != null) {
						if (StringUtil.isNotEmpty(entity.getId())) {
							if (StringUtil.isNotEmpty(device.getId())) {
								if (!device.getId().equals(entity.getId())) {
									canSave = false;
									map.put("success", false);
									map.put("msg", "亲，二维码编号与设备（" + device.getDevice_registration_code() + "）重复了，请纠正再保存！");
								}
							}
						} else {
							if (StringUtil.isNotEmpty(device.getId())) {
								canSave = false;
								map.put("success", false);
								map.put("msg", "亲，二维码编号与设备（" + device.getDevice_registration_code() + "）重复了，请纠正再保存！");
							}
						}
					}
				}
				
				// 2、验证电梯二维码编号是否重复
				if(StringUtil.isEmpty(entity.getRegistration_num())){
					/*canSave = false;
					map.put("success", false);
					map.put("msg", "亲，使用登记证号是必填项目，请补充完整再保存！");*/
				} else {
					if (!entity.getRegistration_num().contains("/") && !entity.getRegistration_num().contains("\\")
							&& !entity.getRegistration_num().contains("-") && !entity.getRegistration_num().contains("——")) {
						List<DeviceDocument> list2 = deviceService.getDeviceInfosByRegistrationNum(entity.getId(),
								entity.getRegistration_num());
						for (DeviceDocument device : list2) {
							if (device != null) {
								if (StringUtil.isNotEmpty(entity.getId())) {
									if (StringUtil.isNotEmpty(device.getId())) {
										if (!device.getId().equals(entity.getId())) {
											canSave = false;
											map.put("success", false);
											map.put("msg", "亲，使用登记证号与设备（" + device.getDevice_registration_code()
													+ "）重复了，请纠正再保存！");
										}
									}
								} else {
									if (StringUtil.isNotEmpty(device.getId())) {
										canSave = false;
										map.put("success", false);
										map.put("msg",
												"亲，使用登记证号与设备（" + device.getDevice_registration_code() + "）重复了，请纠正再保存！");
									}
								}
							}
						}
					}
				}
			}
			

			// 3、验证电梯设备所在区域
			if (StringUtil.isNotEmpty(entity.getDevice_area_code())) {
				if ("510100".equals(entity.getDevice_area_code())) {
					canSave = false;
					map.put("success", false);
					map.put("msg", "亲，设备所在区域不能选择成都市哦，请选择区县！");
				}
			} else {
				canSave = false;
				map.put("success", false);
				map.put("msg", "亲，设备所在区域是必填项，请选择！");
			}
		}
		// 4、验证相同使用单位的设备，单位内部编号是否重复
		if (StringUtil.isNotEmpty(entity.getFk_company_info_use_id()) && StringUtil.isNotEmpty(entity.getInternal_num())
				&& StringUtil.isNotEmpty(entity.getDevice_sort_code())) {
			List<String> list = deviceService.queryByInternal_num(entity.getFk_company_info_use_id(),
					entity.getInternal_num(), entity.getDevice_sort_code());
			if (!list.isEmpty()) {
				if (StringUtil.isEmpty(entity.getId())) {
					if (list.size() > 0) {
						canSave = false;
						map.put("success", false);
						map.put("msg", "亲，相同使用单位的设备，单位内部编号不能重复哦，请重新输入！");
					}
				} else {
					if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							if (!list.get(i).equals(entity.getId())) {
								canSave = false;
								map.put("success", false);
								map.put("msg", "亲，相同使用单位的设备，单位内部编号不能重复哦，请重新输入！");
							}
						}
					}
				}
			}
		}

		if (canSave) {
			deviceService.saveBasic(entity, type);
			return JsonWrapper.successWrapper(entity);
		} else {
			return map;
		}
	}
	
	//保存基础信息和参数信息
	@RequestMapping(value = "saveBasic1")
	@ResponseBody
	public HashMap<String, Object> saveBasic1(CablewayDTO entity,String type,HttpServletRequest request)
			throws Exception {
			DeviceDocument device = null;			
			List<CablewayPara> cablewayParaList = new ArrayList<CablewayPara>();
			if (StringUtil.isNotEmpty(entity.getId())) {
				device = deviceService.get(entity.getId());
				for(CablewayPara cablewayPara : device.getCableway()){
					String id = cablewayPara.getId();
					BeanUtils.copyProperties(cablewayPara, entity);
					cablewayPara.setId(id);
					cablewayParaList.add(cablewayPara);
				}
			}else{
				device = new DeviceDocument();
				CablewayPara cablewayPara = new CablewayPara();
				BeanUtils.copyProperties(cablewayPara, entity);
				cablewayPara.setId(null);
				cablewayParaList.add(cablewayPara);
			}
			device.setDevice_registration_code(entity.getDevice_registration_code());
			device.setDevice_sort(entity.getDevice_sort());
			device.setDevice_sort_code(entity.getDevice_sort_code());
			device.setDevice_name(entity.getDevice_name());
			device.setFk_company_info_use_id(entity.getFk_company_info_use_id());
			device.setCom_code(entity.getCom_code());
			device.setCompany_name(entity.getCompany_name());
			device.setFk_company_info_make_id(entity.getFk_company_info_make_id());
			device.setMake_units_name(entity.getMake_units_name());
			device.setSecurity_op(entity.getSecurity_op());
			device.setSecurity_tel(entity.getSecurity_tel());
			device.setDevice_use_place(entity.getDevice_use_place());
			device.setDevice_area_code(entity.getDevice_area_code());
			device.setDevice_area_name(entity.getDevice_area_name());
			device.setRegistration_num(entity.getRegistration_num());
			device.setRegistration_agencies(entity.getRegistration_agencies());
			
			for(CablewayPara cablewayPara : cablewayParaList){
				if (cablewayPara!=null) {
					device.addCableway(cablewayPara);
				}
			}
			
			deviceService.saveBasic(device,type);
		
		return JsonWrapper.successWrapper(type);
	}

	
	//保存基础信息和参数信息
	@RequestMapping(value = "saveSimple")
	@ResponseBody
	public HashMap<String, Object> saveSimple(@RequestBody DeviceDocument entity,HttpServletRequest request)
			throws Exception {

		

			deviceService.saveSimple(entity);
		
		return JsonWrapper.successWrapper();
	}
	
	
	@RequestMapping(value = "getDevice")
    @ResponseBody
    public HashMap<String, Object> getDevice(String id,HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		DeviceDocument doc = deviceService.get(id);
		if(doc.getEnterInfo()==null){
			if(doc.getFk_company_info_use_id()!=null){
				doc.setEnterInfo(enterService.get(doc.getFk_company_info_use_id()));	// 使用单位
			}
		}
		if (StringUtil.isNotEmpty(type) && "9".equals(type)) {
			CablewayDTO cablewayDTO = new CablewayDTO();
			for(CablewayPara cablewayPara : doc.getCableway()){
				BeanUtils.copyProperties(cablewayDTO, cablewayPara);
			}
			BeanUtils.copyProperties(cablewayDTO, doc);
			return JsonWrapper.successWrapper(cablewayDTO);
		}else{
			return JsonWrapper.successWrapper(doc);
		}
        
    }
	// 删除
    @RequestMapping(value = "del")
    @ResponseBody
    public HashMap<String, Object> del(String ids) throws Exception {
    	
    	deviceService.del(ids);
        return JsonWrapper.successWrapper(ids);
    }
    
    // 查询是否有注册代码
    @RequestMapping(value = "getNum")
    @ResponseBody
    public HashMap<String, Object> getNum(String device_code) throws Exception {
    	
    	
        return deviceService.getNum(device_code);
    }
    
    /*// 查询是否已存在二维码编号
    @RequestMapping(value = "getQRCode")
    @ResponseBody
    public HashMap<String, Object> getQRCode(String device_qr_code) throws Exception {	
        return deviceService.getQRCode(device_qr_code);
    }*/
    
    // 批量修改设备的使用单位、维保单位、维保单位联系人、维保单位联系人电话、安全管理员、安全管理员联系电话
    @RequestMapping(value = "changeCom")
    @ResponseBody
    public HashMap<String, Object> changeCom(@RequestBody DeviceDTO entity,HttpServletRequest request) throws Exception {	
    	HashMap<String, Object>  map = new HashMap<String, Object>();	
    	Map<String, String> infoMap = new HashMap<String, String>();	
    	infoMap.put("table_code", "base_device_document");
		infoMap.put("table_name", "设备基础信息表");
		infoMap.put("op_action", "修改设备基础信息");
		infoMap.put("op_remark", "修改设备基础信息");
    	try {
    		List<Map<String, String>> oldList = deviceService.changeCom(request, entity);
    		for (int i = 0; i < oldList.size(); i++) {
				Map<String, String> old_Map = oldList.get(i);
				String device_id = old_Map.get("id").toString();
				Map<String, String> new_Map = deviceService.queryDeviceById(device_id);
				infoMap.put("business_id", device_id);
				inspectionCommonService.compareMapOfBean(infoMap, old_Map, new_Map, request);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
        return map;
    }
    
    // 批量修改设备信息（区县局修改设备使用单位、使用登记证号、登记日期、登记人员、设备所在区域、设备所在街道、设备使用地点）
    @RequestMapping(value = "modifyDevice")
    @ResponseBody
    public HashMap<String, Object> modifyDevice(@RequestBody DeviceDTO entity,HttpServletRequest request) throws Exception {	
    	HashMap<String, Object>  map = new HashMap<String, Object>();	
    	Map<String, String> infoMap = new HashMap<String, String>();	
    	infoMap.put("table_code", "base_device_document");
		infoMap.put("table_name", "设备基础信息表");
		infoMap.put("op_action", "修改设备基础信息");
		infoMap.put("op_remark", "区县局修改设备基础信息");
    	try {
			List<Map<String, String>> oldList = deviceService.modifyDevice(request, entity);
			for (int i = 0; i < oldList.size(); i++) {
				Map<String, String> old_Map = oldList.get(i);
				String device_id = old_Map.get("id").toString();
				Map<String, String> new_Map = deviceService.queryDeviceById(device_id);
				infoMap.put("business_id", device_id);
				inspectionCommonService.compareMapOfBean(infoMap, old_Map, new_Map, request);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
        return map;
    }
    
    /**
	 * 设备补录时，复制设备
	 * 设备注册代码由用户手动输入
	 * 
	 * @param request
	 * @param id --
	 *            设备ID
	 * @param count --
	 *            台数（复制数量）
	 * @author GaoYa
	 * @since 2014-05-09 15:25
	 * @return
	 */
    @RequestMapping(value = "copyDevice")
    @ResponseBody
    public HashMap<String, Object> copyDevice(HttpServletRequest request,
			@RequestBody
			DeviceDTO deviceDTO) throws Exception {  
    	try {
    		String id = deviceDTO.getId();
    		String count = deviceDTO.getCount();
    		if (StringUtil.isNotEmpty(id)) {
        		DeviceDocument deviceDocument = deviceService.get(id);
        		int copyCount = 1;
        		if (StringUtil.isNotEmpty(count)) {
        			copyCount = Integer.parseInt(count);
    			}
        		
        		List<DeviceDocument> codeList = deviceDTO.getDevice_registration_codes();
            	if(copyCount!=codeList.size()){
            		return JsonWrapper.failureWrapperMsg("您填写的设备注册代码个数（"+codeList.size()+"）不等于复制台数（"+copyCount+"），请检查！");
            	}
        		
        		for (int i = 0; i < copyCount; i++) {
        			DeviceDocument device = new DeviceDocument();
        			BeanUtils.copyProperties(device, deviceDocument);
        			device.setId(null);
        			// 因为相同的使用单位，单位内部编号必须是唯一的，所以复制设备时，不复制单位内部编号
        			device.setInternal_num("");	
        			// 二维码编号是唯一的，复制设备时，不复制二维码编号
        			device.setDevice_qr_code("");     	
        			// 使用登记证号是唯一的，复制设备时，不复制使用登记证号
        			device.setRegistration_num("");   
        			/*
        			 * 设备补录时，复制设备不再进行设备注册代码自动生成，改由用户手动填写
        			 * update by GaoYa
        			 * date 2016-10-18
        			 * */
        			String registration_code = "";

        			boolean existcode = deviceService.existCode(codeList.get(i).getDevice_registration_code());
            		if(existcode){
            			return JsonWrapper.failureWrapperMsg("您填写的设备注册代码（"+codeList.get(i).getDevice_registration_code()+"）系统已存在，请检查！");
            		}else{
            			registration_code = codeList.get(i).getDevice_registration_code();
            		}
            		device.setDevice_registration_code(registration_code);
        			
        			String device_type = deviceDocument.getDevice_sort_code().substring(0,1);	// 设备类别
        			if ("1".equals(device_type)) {	// 锅炉
        				Collection<BoilerPara> boilerParaCollection = deviceDocument.getBoiler();	// 锅炉参数
        				List<BoilerPara> boilerParaList = new ArrayList<BoilerPara>();
        				for(BoilerPara boilerPara : boilerParaCollection){
        					BoilerPara entity = new BoilerPara();
        					BeanUtils.copyProperties(entity, boilerPara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					boilerParaList.add(entity);
        				}
        				device.setBoiler(boilerParaList);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.addCableway(null);
					}else if ("2".equals(device_type)) {	// 压力容器
						Collection<PressurevesselsPara> pressurevesselsParaCollection = deviceDocument.getPressurevessels();	// 压力容器参数
						List<PressurevesselsPara> pressurevesselsParaList = new ArrayList<PressurevesselsPara>();
        				for(PressurevesselsPara pressurevesselsPara : pressurevesselsParaCollection){
        					PressurevesselsPara entity = new PressurevesselsPara();
        					BeanUtils.copyProperties(entity, pressurevesselsPara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					pressurevesselsParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(pressurevesselsParaList);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.addCableway(null);
					}else if ("3".equals(device_type)) {	// 电梯
						Collection<ElevatorPara> elevatorParaCollection = deviceDocument.getElevatorParas();	// 电梯参数
						List<ElevatorPara> elevatorParaList = new ArrayList<ElevatorPara>();
        				for(ElevatorPara elevatorPara : elevatorParaCollection){
        					ElevatorPara entity = new ElevatorPara();
        					BeanUtils.copyProperties(entity, elevatorPara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					elevatorParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(elevatorParaList);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.addCableway(null);
					}else if ("4".equals(device_type)) {	// 起重机械
						Collection<CranePara> craneParaCollection = deviceDocument.getCrane();	// 起重参数
						List<CranePara> craneParaList = new ArrayList<CranePara>();
        				for(CranePara cranePara : craneParaCollection){
        					CranePara entity = new CranePara();
        					BeanUtils.copyProperties(entity, cranePara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					craneParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(craneParaList);
        				device.addCableway(null);
        				// 起重机的使用登记证编号自动生成（用设备注册代码），2015-06-18省院机电六部彭宇辉需求
        				device.setRegistration_num(registration_code);	
					}else if ("5".equals(device_type)) {	// 厂内机动车
						Collection<EnginePara> engineParaCollection = deviceDocument.getEngine();	// 厂内机动车参数
						List<EnginePara> engineParaList = new ArrayList<EnginePara>();
        				for(EnginePara enginePara : engineParaCollection){
        					EnginePara entity = new EnginePara();
        					BeanUtils.copyProperties(entity, enginePara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					engineParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(engineParaList);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.addCableway(null);
					}else if ("6".equals(device_type)) {	// 游乐设施
						Collection<RidesPara> ridesParaCollection = deviceDocument.getRidesPara();	// 游乐设施参数
						List<RidesPara> ridesParaList = new ArrayList<RidesPara>();
        				for(RidesPara ridesPara : ridesParaCollection){
        					RidesPara entity = new RidesPara();
        					BeanUtils.copyProperties(entity, ridesPara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					ridesParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(ridesParaList);
        				device.setCrane(null);
        				device.addCableway(null);
					}else if ("9".equals(device_type)) {	// 客运索道
						Collection<CablewayPara> cablewayParaCollection = deviceDocument.getCableway();	// 客运索道参数
						List<CablewayPara> cablewayParaList = new ArrayList<CablewayPara>();
        				for(CablewayPara cablewayPara : cablewayParaCollection){
        					CablewayPara entity = new CablewayPara();
        					BeanUtils.copyProperties(entity, cablewayPara);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					cablewayParaList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(null);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.cableway = cablewayParaList;
        				/*device.cableway.clear();
        				for(CablewayPara cablewayPara : cablewayParaList){
        					if (cablewayPara!=null) {
        						device.addCableway(cablewayPara);
							}
        				}*/
					}else if ("7310".equals(deviceDocument.getDevice_sort_code())) {	// 安全阀
						Collection<Accessory> accessoryCollection = deviceDocument.getAccessory();	// 安全阀参数
						List<Accessory> accessoryList = new ArrayList<Accessory>();
        				for(Accessory accessory : accessoryCollection){
        					Accessory entity = new Accessory();
        					BeanUtils.copyProperties(entity, accessory);
        					entity.setId(null);
        					entity.setDeviceDocument(device);
        					accessoryList.add(entity);
        				}
        				device.setBoiler(null);
        				device.setAccessory(accessoryList);
        				device.setElevatorParas(null);
        				device.setEngine(null);
        				device.setPressurevessels(null);
        				device.setRidesPara(null);
        				device.setCrane(null);
        				device.addCableway(null);
					}      
        			
        			device.setInspect_date(null);
        			device.setInspect_next_date(null);
        			device.setInspect_conclusion(null);
        			device.setSend_status("0");		// 记录数据被修改
        			device.setIs_cur_check("1");	// 当前是否报检（1：未报检 2：当前报检中）
        			deviceService.save(device);	// 保存
        			// 写入系统日志
        			try {
        				CurrentSessionUser user = SecurityUtil.getSecurityUser();
        				logService.setLogs(device.getId(), "设备复制", "设备复制（复制后的设备注册代码："+device.getDevice_registration_code()+")", user.getId(), user.getName(),
        						new Date(),request.getRemoteAddr());
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        		}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("复制失败！");
		}
        return JsonWrapper.successWrapper("");
    }
    
    /**
	 * 设备注册时，复制设备
	 * 设备注册代码由系统自动生成
	 * 
	 * @param request
	 * @param id --
	 *            设备ID
	 * @param count --
	 *            台数（复制数量）
	 * @author GaoYa
	 * @since 2014-05-09 15:25
	 * @return
	 */
    @RequestMapping(value = "copyDevice2")
    @ResponseBody
	public HashMap<String, Object> copyDevice2(HttpServletRequest request) throws Exception {
		try {
			String id = request.getParameter("id");
			String count = request.getParameter("count");
			if (StringUtil.isNotEmpty(id)) {
				DeviceDocument deviceDocument = deviceService.get(id);
				int copyCount = 1;
				if (StringUtil.isNotEmpty(count)) {
					copyCount = Integer.parseInt(count);
				}

				for (int i = 0; i < copyCount; i++) {
					DeviceDocument device = new DeviceDocument();
					BeanUtils.copyProperties(device, deviceDocument);
					device.setId(null);
					// 因为相同的使用单位，单位内部编号必须是唯一的，所以复制设备时，不复制单位内部编号
					device.setInternal_num("");
					// 二维码编号是唯一的，复制设备时，不复制二维码编号
					device.setDevice_qr_code("");
					// 使用登记证号是唯一的，复制设备时，不复制使用登记证号
        			device.setRegistration_num("");   
					
					String device_type = deviceDocument.getDevice_sort_code().substring(0, 1); // 设备类别
					/*
					 * 设备注册时，复制设备由系统自动生成设备注册代码 update by GaoYa date 2016-10-18
					 * 2017-08-25质量部谢方要求修改，除电梯外不得自行产生设备注册代码并出具检验报告，默认为“/”（电梯除外）
					 * 2017-09-07根据9.6省院市局协调会纪要和成质监特[2017]48号文件要求，停止检验软件内所有设备注册代码的新生成
					 */
					String registration_code = "/";
					/*if ("3".equals(device_type)) {
						if (StringUtil.isNotEmpty(deviceDocument.getDevice_sort_code())
							&& StringUtil.isNotEmpty(deviceDocument.getDevice_area_code())) {
						
							// registration_code =
							// TS_Util.getRegistrationCode(deviceDocument.getDevice_sort_code(),deviceDocument.getDevice_area_code());
							registration_code = deviceDao.getRegistrationCode(deviceDocument.getDevice_sort_code(),
									deviceDocument.getDevice_area_code());
						}
					}else{
						registration_code = "/";
					}*/
					device.setDevice_registration_code(registration_code); // 复制设备时，不复制设备注册代码，重新自动生成

					
					if ("1".equals(device_type)) { // 锅炉
						Collection<BoilerPara> boilerParaCollection = deviceDocument.getBoiler(); // 锅炉参数
						List<BoilerPara> boilerParaList = new ArrayList<BoilerPara>();
						for (BoilerPara boilerPara : boilerParaCollection) {
							BoilerPara entity = new BoilerPara();
							BeanUtils.copyProperties(entity, boilerPara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							boilerParaList.add(entity);
						}
						device.setBoiler(boilerParaList);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(null);
						device.addCableway(null);
					} else if ("2".equals(device_type)) { // 压力容器
						Collection<PressurevesselsPara> pressurevesselsParaCollection = deviceDocument
								.getPressurevessels(); // 压力容器参数
						List<PressurevesselsPara> pressurevesselsParaList = new ArrayList<PressurevesselsPara>();
						for (PressurevesselsPara pressurevesselsPara : pressurevesselsParaCollection) {
							PressurevesselsPara entity = new PressurevesselsPara();
							BeanUtils.copyProperties(entity, pressurevesselsPara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							pressurevesselsParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(pressurevesselsParaList);
						device.setRidesPara(null);
						device.setCrane(null);
						device.addCableway(null);
					} else if ("3".equals(device_type)) { // 电梯
						Collection<ElevatorPara> elevatorParaCollection = deviceDocument.getElevatorParas(); // 电梯参数
						List<ElevatorPara> elevatorParaList = new ArrayList<ElevatorPara>();
						for (ElevatorPara elevatorPara : elevatorParaCollection) {
							ElevatorPara entity = new ElevatorPara();
							BeanUtils.copyProperties(entity, elevatorPara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							elevatorParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(elevatorParaList);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(null);
						device.addCableway(null);
					} else if ("4".equals(device_type)) { // 起重机械
						Collection<CranePara> craneParaCollection = deviceDocument.getCrane(); // 起重参数
						List<CranePara> craneParaList = new ArrayList<CranePara>();
						for (CranePara cranePara : craneParaCollection) {
							CranePara entity = new CranePara();
							BeanUtils.copyProperties(entity, cranePara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							craneParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(craneParaList);
						device.addCableway(null);

						device.setRegistration_num(registration_code); // 起重机的使用登记证编号自动生成（用设备注册代码），2015-06-18省院机电五部检验员需求
					} else if ("5".equals(device_type)) { // 厂内机动车
						Collection<EnginePara> engineParaCollection = deviceDocument.getEngine(); // 厂内机动车参数
						List<EnginePara> engineParaList = new ArrayList<EnginePara>();
						for (EnginePara enginePara : engineParaCollection) {
							EnginePara entity = new EnginePara();
							BeanUtils.copyProperties(entity, enginePara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							engineParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(engineParaList);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(null);
						device.addCableway(null);
					} else if ("6".equals(device_type)) { // 游乐设施
						Collection<RidesPara> ridesParaCollection = deviceDocument.getRidesPara(); // 游乐设施参数
						List<RidesPara> ridesParaList = new ArrayList<RidesPara>();
						for (RidesPara ridesPara : ridesParaCollection) {
							RidesPara entity = new RidesPara();
							BeanUtils.copyProperties(entity, ridesPara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							ridesParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(ridesParaList);
						device.setCrane(null);
						device.addCableway(null);
					} else if ("9".equals(device_type)) { // 客运索道
						Collection<CablewayPara> cablewayParaCollection = deviceDocument.getCableway(); // 客运索道参数
						List<CablewayPara> cablewayParaList = new ArrayList<CablewayPara>();
						for (CablewayPara cablewayPara : cablewayParaCollection) {
							CablewayPara entity = new CablewayPara();
							BeanUtils.copyProperties(entity, cablewayPara);
							entity.setId(null);
							entity.setDeviceDocument(device);
							cablewayParaList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(null);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(null);
						device.cableway = cablewayParaList;
						/*
						 * device.cableway.clear(); for(CablewayPara
						 * cablewayPara : cablewayParaList){ if
						 * (cablewayPara!=null) {
						 * device.addCableway(cablewayPara); } }
						 */
					} else if ("7310".equals(deviceDocument.getDevice_sort_code())) { // 安全阀
						Collection<Accessory> accessoryCollection = deviceDocument.getAccessory(); // 安全阀参数
						List<Accessory> accessoryList = new ArrayList<Accessory>();
						for (Accessory accessory : accessoryCollection) {
							Accessory entity = new Accessory();
							BeanUtils.copyProperties(entity, accessory);
							entity.setId(null);
							entity.setDeviceDocument(device);
							accessoryList.add(entity);
						}
						device.setBoiler(null);
						device.setAccessory(accessoryList);
						device.setElevatorParas(null);
						device.setEngine(null);
						device.setPressurevessels(null);
						device.setRidesPara(null);
						device.setCrane(null);
						device.addCableway(null);
					}

					device.setInspect_date(null);
					device.setInspect_next_date(null);
					device.setInspect_conclusion(null);
					device.setSend_status("0"); // 记录数据被修改
					device.setIs_cur_check("1"); // 当前是否报检（1：未报检 2：当前报检中）
					deviceService.save(device); // 保存
					// 写入系统日志
					try {
						CurrentSessionUser user = SecurityUtil.getSecurityUser();
						logService.setLogs(device.getId(), "设备复制",
								"设备复制（复制后的设备注册代码：" + device.getDevice_registration_code() + ")", user.getId(),
								user.getName(), new Date(), request.getRemoteAddr());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("复制失败！");
		}
		return JsonWrapper.successWrapper("");
	}

    /**
	 * 获取检验通知书打印内容
	 * 
	 * @param request
	 * @param id --
	 *            设备ID
	 * @return
	 */
	@RequestMapping(value = "getPrintContent")
	@ResponseBody
	public HashMap<String, Object> getPrintContent(HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				String[] ids = id.split(",");
				DeviceDocument device = deviceService.get(ids[0]);		
				String device_type = codeTablesService.getValueByCode(Constant.CT_BASE_DEVICE_TYPE,
						device.getDevice_sort_code());			
				// 获取检验通知书打印内容
				String printContent = Constant.getDeviceCheckNoticePrintContent(
						device.getCompany_name(), device_type.trim(),
						ids.length);
				wrapper.put("success", true);
				wrapper.put("printContent", printContent);
			}
		} catch (Exception e) {
			log.error("获取检验通知书打印内容出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "获取检验通知书打印内容出错！");
			e.printStackTrace();
		}
		return wrapper;
	}

	
	// 延迟设备
    @RequestMapping(value = "toLate")
    @ResponseBody
    public HashMap<String, Object> toLate(HttpServletRequest request,String deviceId) throws Exception {
    	
    	String ids = request.getParameter("deviceId");
    	
    	deviceService.toLate(ids);
    	
        return JsonWrapper.successWrapper(ids);
    }

    // 启用设备
    @RequestMapping(value = "enable")
    @ResponseBody
    public HashMap<String, Object> enable(HttpServletRequest request) throws Exception {	
        return deviceService.enable(request);
    }
    
    // 停用设备
    @RequestMapping(value = "disable")
    @ResponseBody
    public HashMap<String, Object> disable(HttpServletRequest request) throws Exception {	
        return deviceService.disable(request);
    }
    
    // 报废设备
    @RequestMapping(value = "useless")
    @ResponseBody
    public HashMap<String, Object> useless(HttpServletRequest request) throws Exception {	
        return deviceService.useless(request);
    }
}
