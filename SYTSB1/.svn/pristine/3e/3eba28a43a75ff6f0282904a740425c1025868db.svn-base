package com.lsts.gis.inspect.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.gis.inspect.service.DeviceTjManager;

/**
 *财务统计Action
 *
 * @author 
 *
 * @date 
 */
@Controller
@RequestMapping("deviceTjAction/tj")
public class DeviceTjAction {
	 @Autowired DeviceTjManager deviceTjManager;
	
	//获取统计信息
  	@RequestMapping(value = "getTj")
  	@ResponseBody
  	public HashMap<String, Object> getTj(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
  		wrapper.put("list1", deviceTjManager.getMtMoney(unit_id));//每天对比统计
  		wrapper.put("list2", deviceTjManager.getYfMoney(unit_id));//月份对比统计
  		wrapper.put("list3", deviceTjManager.getSnYfMoney(unit_id));//月份对比统计
  		//wrapper.put("list3", deviceTjManager.getNdMoney(unit_id));//年度对比统计
  		//wrapper.put("list4", deviceTjManager.getJcTj(unit_id));//纠错报告统计
  		wrapper.put("list5", deviceTjManager.getZjTj(unit_id));//证件统计
  		wrapper.put("list6", deviceTjManager.getBmBg(unit_id));//检验报告统计
  		wrapper.put("list7", deviceTjManager.getBmFy(unit_id));//费用统计
  		wrapper.put("list8", deviceTjManager.getByUnitIdXl(unit_id));//学历
  		wrapper.put("list9", deviceTjManager.statisticsFee(unit_id));//今年支出月份对比
  		wrapper.put("list10", deviceTjManager.statisticsFeeQn(unit_id));//去年支出月份对比
  		wrapper.put("list11", deviceTjManager.getMtMoneyZc(unit_id));//每天支出对比
  		wrapper.put("list12", deviceTjManager.getBmQxj(unit_id));//请休假
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取月份收入/支出统计信息
  	@RequestMapping(value = "getTjYF")
  	@ResponseBody
  	public HashMap<String, Object> getTjYF(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
  		wrapper.put("list2", deviceTjManager.getYfMoney(unit_id));//月份对比统计
  		wrapper.put("list3", deviceTjManager.getSnYfMoney(unit_id));//月份对比统计
  		wrapper.put("list9", deviceTjManager.statisticsFee(unit_id));//今年支出月份对比
  		wrapper.put("list10", deviceTjManager.statisticsFeeQn(unit_id));//去年支出月份对比
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取周收入/支出统计信息
  	@RequestMapping(value = "getTjZb")
  	@ResponseBody
  	public HashMap<String, Object> getTjZb(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper.put("list1", deviceTjManager.getMtMoney(unit_id));//每天对比统计
  		wrapper.put("list11", deviceTjManager.getMtMoneyZc(unit_id));//每天支出对比
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取证件/学历统计信息
  	@RequestMapping(value = "getTjZj")
  	@ResponseBody
  	public HashMap<String, Object> getTjZj(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper.put("list2", deviceTjManager.getYfMoney(unit_id));//月份对比统计
  		if(unit_id.equals("100030")){
  			wrapper.put("list5", deviceTjManager.getZjTjJd());//机电证件统计
  			wrapper.put("list6", deviceTjManager.getZjTjCy());//承压证件统计
  			wrapper.put("wusun", deviceTjManager.getZjTjWs());//无损证件统计
  		}else{
  			wrapper.put("list5", deviceTjManager.getZjTj(unit_id));//证件统计
  		}
  		if(unit_id.equals("100041")){
  			wrapper.put("list8", deviceTjManager.getByUnitIdXl(""));//学历
  		}else{
  			wrapper.put("list8", deviceTjManager.getByUnitIdXl(unit_id));//学历
  		}
  		
  		wrapper.put("list13", deviceTjManager.getKytj());//科研统计
  		wrapper.put("list14", deviceTjManager.getRyzc());//人员职称
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取检验报告/请休假统计信息
  	@RequestMapping(value = "getTjJq")
  	@ResponseBody
  	public HashMap<String, Object> getTjJq(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper.put("list6", deviceTjManager.getBmBg(unit_id));//检验报告统计
  		wrapper.put("list12", deviceTjManager.getBmQxj(unit_id));//请休假
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取统计信息
  	@RequestMapping(value = "getTjFy")
  	@ResponseBody
  	public HashMap<String, Object> getTjFy(String unit_id)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		wrapper.put("list7", deviceTjManager.getBmFy(unit_id));//费用统计
  		wrapper.put("success",true);
  		return wrapper;
  	}
  	/**
	 * 查询总数（new）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "initCount")
	@ResponseBody
	public HashMap<String, Object> initCount(String unit_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> count = deviceTjManager.initCount(unit_id);
			map.put("success", true);
			map.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);// TODO: handle exception
			map.put("msg", e.getMessage());
		}
		return map;
	}
	//获取月份详细统计信息 收入
  	@RequestMapping(value = "getYf")
  	@ResponseBody
  	public HashMap<String, Object> getYf(String unit_id,String name)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
  		wrapper.put("list", deviceTjManager.getYfMoneyXX(unit_id,name));//每天对比统计
  		
  		wrapper.put("success",true);
  		return wrapper;
  	}
  //获取月份详细统计信息  支出
  	@RequestMapping(value = "getYfZc")
  	@ResponseBody
  	public HashMap<String, Object> getYfZc(String unit_id,String name)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
  		wrapper.put("list", deviceTjManager.getYfMoneyZc(unit_id,name));//每天对比统计
  		
  		wrapper.put("success",true);
  		return wrapper;
  	}
	//按时间查询统计信息
  	@RequestMapping(value = "getDateMtMoney")
  	@ResponseBody
  	public HashMap<String, Object> getDateMtMoney(String unit_id,String startDate,String endDate)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
  		wrapper.put("list", deviceTjManager.getDateMtMoney(unit_id,startDate,endDate));//每天对比统计
  		wrapper.put("success",true);
  		return wrapper;
  	}
}