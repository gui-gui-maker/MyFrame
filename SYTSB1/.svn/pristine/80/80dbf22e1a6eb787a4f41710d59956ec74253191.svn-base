package com.lsts.gis.quality.web;

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
import com.lsts.gis.quality.service.QualityTjManager;

/**
 *统计Action
 *
 * @author zpl
 *
 * @date 
 */
@Controller
@RequestMapping("qualityTjAction/tj")
public class QualityTjAction {
	 @Autowired QualityTjManager qualityTjManager;
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
				Map<String, Object> count = qualityTjManager.initCount();
				map.put("success", true);
				map.put("data", count);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);// TODO: handle exception
				map.put("msg", e.getMessage());
			}
			return map;
		}
		//获取统计信息
	  	@RequestMapping(value = "getList")
	  	@ResponseBody
	  	public HashMap<String, Object> getTj(String unit_id)
	  			throws Exception {
	  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
	  		//wrapper.put("list", deviceTjManager.getQnMoney(unit_id));//全年收入统计
	  		String file_name="";
	  		wrapper.put("list1", qualityTjManager.initRwsList(file_name));//任务书
	  		wrapper.put("list2", qualityTjManager.initNkdqList(file_name));//内控到期
	  		wrapper.put("list3", qualityTjManager.initBhgList(file_name));//不合格报告
	  		wrapper.put("list4", qualityTjManager.initRjrwsList(file_name));//软件任务书
	  		wrapper.put("list5", qualityTjManager.initJcbgList(file_name));//纠错报告
	  		wrapper.put("success",true);
	  		return wrapper;
	  	}
	  //根据报告编号查询报告
	  	@RequestMapping(value = "getByReportNo")
	  	@ResponseBody
	  	public HashMap<String, Object> getByReportNo(String report_sn)
	  			throws Exception {
	  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
	  		if(qualityTjManager.getByReportNo(report_sn)==null||qualityTjManager.getByReportNo(report_sn).size()<=0){
	  			wrapper.put("list1", qualityTjManager.initRwsList(report_sn));//任务书
		  		wrapper.put("list2", qualityTjManager.initNkdqList(report_sn));//内控到期
		  		wrapper.put("list3", qualityTjManager.initBhgList(report_sn));//不合格报告
		  		wrapper.put("list4", qualityTjManager.initRjrwsList(report_sn));//软件任务书
		  		wrapper.put("list5", qualityTjManager.initJcbgList(report_sn));//纠错报告
		  		wrapper.put("type","1");
	  		}else{
	  			wrapper.put("list1", qualityTjManager.getByReportNo(report_sn));//
	  			wrapper.put("type","2");
	  		}
	  		
	  		wrapper.put("success",true);
	  		return wrapper;
	  	}
	  	
	  	
	  	 //根据报告编号查询报告不合格和修订记录
	  	@RequestMapping(value = "getByReportNoJl")
	  	@ResponseBody
	  	public HashMap<String, Object> getByReportNoJl(String report_sn)
	  			throws Exception {
	  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
	  		wrapper.put("list1", qualityTjManager.getByReportNoYSJL(report_sn));//
	  		wrapper.put("list2", qualityTjManager.getByReportNoBhg(report_sn));//
	  		wrapper.put("success",true);
	  		return wrapper;
	  	}
}