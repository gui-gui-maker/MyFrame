package com.khnt.oa.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.service.CarInfoManager;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName OrgAction
 * @JDK 1.5
 * @author
 * @date
 */
@Controller
@RequestMapping("oa/car/info/")
public class CarInfoAction extends
		SpringSupportAction<CarInfo, CarInfoManager> {

	@Autowired
	private CarInfoManager carInfoManager;
	
	@Autowired
	private AttachmentManager attachmentManager;

	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			CarInfo info = carInfoManager.get(id);
			map.put("data", info);
			List<Attachment> files = new ArrayList<Attachment>();
			if(info.getCarpicId()!=null){
				Attachment attachment = attachmentManager.get(info.getCarpicId());
				files.add(attachment);
			}
			map.put("files", files);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			log.error(e.getMessage());
			return JsonWrapper.failureWrapper();
		}
	}

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, CarInfo car){
		try{
			if(StringUtil.isEmpty(car.getCarState())){
				car.setCarState("1");
				car.setCarStateDate(new Date());
			}
			return carInfoManager.saveCarInfo(car);
		}
		catch(Exception e){
			return JsonWrapper.failureWrapperMsg("保存车辆信息出错："+e.getMessage());
		}
	}
	
	//登记节假日收车
	@RequestMapping(value="saveUsedState")
	@ResponseBody
	public HashMap<String, Object> saveUsedState(String id,CarInfo app) throws Exception{
		if(this.carInfoManager.saveUsedState(id, app.getCarState())){
			return JsonWrapper.successWrapper();
		}else{
			return JsonWrapper.failureWrapper();
		}
	}
	

	/**
	 * 获取租用车辆的相关价格信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getPrice")
	@ResponseBody
	public HashMap<String, Object> getPrice(String carid){
	   try{
		   CarInfo carInfo=carInfoManager.get(carid);
		   return JsonWrapper.successWrapper(carInfo);
	   }
	   catch(Exception e){
		   return JsonWrapper.failureWrapperMsg("获取租用车辆的价格信息出错："+e.getMessage());
	   }
	}
	
	/**
	 * 初始化车辆基本信息
	 * @param request
	 * @return
	 */
	@RequestMapping("queryCarType")
	@ResponseBody
	public ModelAndView queryCarType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/app/oa/car/car_type_table");
		//查询一类车辆
		List<Map<String, Object>> list1=carInfoManager.queryCarType("1");
		//查询尔二类车辆
		List<Map<String, Object>> list2=carInfoManager.queryCarType("2");
		//查询三类车辆
		List<Map<String, Object>> list3=carInfoManager.queryCarType("3");
		//查询其他类车辆
		List<Map<String, Object>> list4=carInfoManager.queryCarType("4");
		//查询维修车辆
		List<Map<String, Object>> list5=carInfoManager.queryCarType("5");
		request.setAttribute("list1",list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3",list3 );
		request.setAttribute("list4", list4);
		request.setAttribute("list5",list5 );
		return mav;
	}
	
	@RequestMapping(value = "repairCar")
	@ResponseBody
	public HashMap<String, Object> repairCar(String ids) throws Exception {
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		for(String id:ids.split(",")){
			//方法二
			CarInfo entity=carInfoManager.get(id);
			entity.setRepairType("1");
			carInfoManager.save(entity);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	@RequestMapping(value = "repairGood")
	@ResponseBody
	public HashMap<String, Object> repairGood(String ids) throws Exception {
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		for(String id:ids.split(",")){
			//方法二
			CarInfo entity=carInfoManager.get(id);
			entity.setRepairType("");
			carInfoManager.save(entity);
		}
		return JsonWrapper.successWrapper(ids);
	}
}
