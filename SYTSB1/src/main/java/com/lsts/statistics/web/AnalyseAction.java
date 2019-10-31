package com.lsts.statistics.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.statistics.bean.DeviceCountDTO;
import com.lsts.statistics.service.AnalyseService;

@Controller
@RequestMapping("sta/analyse")
public class AnalyseAction {
	 private Log log = LogFactory.getLog(getClass());
	@Autowired
	private AnalyseService analyseService;
	
	/**
	 * 根据区划代码和设备类型统计
	 * 
	 */
	@RequestMapping(value = "deviceCountByArea")
	@ResponseBody
	public HashMap<String, Object> deviceCountByArea(HttpServletRequest request) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			
			Date startD = null;
			Date endD = null;
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtil.isEmpty(startDate)){
				/*Calendar cd=Calendar.getInstance();
				int year=cd.get(Calendar.YEAR);
				startDate=year+"-01-01";*/
			}else{
				startD = format.parse(startDate);
			}
			if(StringUtil.isEmpty(endDate)){
				endDate=DateUtil.getCurrentDateTime();
			}else{
				endD = format.parse(endDate);
			}
			wrapper.put("data", analyseService.deviceCountByArea(startD,endD));
			wrapper.put("success", true);
		} catch (Exception e) {
			log.error(e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", e.getMessage());
			e.printStackTrace();
		}
         return wrapper;
	}
	
	/**
	 * 各检验部门检验业务统计
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2014-10-21
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceCheckedCount")
	@ResponseBody
	public HashMap<String, Object> deviceCheckedCount(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser(); // 获取当前用户登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String device_type = request.getParameter("device_type");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper.put("success", true);
			wrapper.put("data", analyseService.queryCount(startDate, endDate, device_type.substring(0,1),curUser.getDepartment().getId()));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取各部门检验业务统计信息失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 各部门人员业务统计表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2014-11-21
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceCountByUser")
	@ResponseBody
	public HashMap<String, Object> deviceCountByUser(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String org_id = request.getParameter("org_id");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper.put("success", true);
			wrapper.put("data", analyseService.deviceCountByUser(startDate, endDate, org_id));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取各部门人员业务统计表失败，请重试！");
		}
		return wrapper;
	}
	
	
	
	/**
	 * 各部门人员业务统计表导出
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportCountByUser")
	public String exportCountByUser(HttpServletRequest request){	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String org_id = request.getParameter("org_id1");
		String org_name = request.getParameter("org_id");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			List<DeviceCountDTO> list = analyseService.exportCountByUser(startDate, endDate, org_id);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("org_name", org_name);
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("各部门人员业务统计表导出失败！");
		}
		return "app/statistics/export_countbyuser";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportCheckedCount")
	public String exportCheckedCount(HttpServletRequest request){	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String device_type = request.getParameter("device_type");
		String device_name = request.getParameter("device_name");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			
			List<DeviceCountDTO> list = analyseService.exportCount(startDate, endDate, device_type.substring(0,1));	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("device_name", device_name);
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("各检验部门检验业务统计表导出失败！");
		}
		return "app/statistics/export_checked_count";
	}
	
	/**
	 * 按设备类别（电梯）统计各检验部门已打印的定检、监检报告数量
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2014-11-12
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "devicePrintedCount")
	@ResponseBody
	public HashMap<String, Object> devicePrintedCount(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper.put("success", true);
			wrapper.put("data", analyseService.exportPrintedCount(startDate, endDate));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取各部门已打印的电梯定检、监检报告统计表失败，请重试！");
		}
		return wrapper;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportPrintedCount")
	public String exportPrintedCount(HttpServletRequest request){	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			
			List<DeviceCountDTO> list = analyseService.exportPrintedCount(startDate, endDate);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("各部门已打印的电梯定检、监检报告统计表导出失败！");
		}
		return "app/statistics/export_printed_count";
	}
	
	/**
	 * 导出检验资料综合统计数据表
	 * 
	 * @param request
	 * @return
	 * @author GaoYa
	 * @date 2017-12-15 11:33:00
	 */
	@RequestMapping(value="exportAllCount")
	public String exportAllCount(HttpServletRequest request){	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			
			List<DeviceCountDTO> list = analyseService.all_count(startDate, endDate);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("检验资料综合统计数据表导出失败！");
		}
		return "app/statistics/export_report_count";
	}
	
	/**
	 * 查询个人的年度通过数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2014-11-21
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "annualStaCountByUser")
	@ResponseBody
	public HashMap<String, Object> annualStaCountByUser(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String user_id = request.getParameter("user_id");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper = analyseService.annualStaCountByUser(startDate, endDate, user_id);
			wrapper.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取人员业务统计数据失败，请重试！");
		}
		return wrapper;
	}
	
	*/
	/**
	 * 按设备类别统计机电六部已打印的定检、监检、委检报告数量
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2014-11-12
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "devicePrintedCount_JD6")
	@ResponseBody
	public HashMap<String, Object> devicePrintedCount_JD6(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String equipmentVariety = request.getParameter("equipmentVariety");
		String equipmentVariety_name = request.getParameter("equipmentVariety_name");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper.put("success", true);
			wrapper.put("data", analyseService.exportPrintedCount_JD6(equipmentVariety,startDate, endDate));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取各部门已打印的电梯定检、监检报告统计表失败，请重试！");
		}
		return wrapper;
	}
	/**
	 * 按设备类别导出机电六部已打印的定检、监检、委检报告数量统计
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportPrintedCount_JD6")
	public String exportPrintedCount_JD6(HttpServletRequest request){	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String equipmentVariety = request.getParameter("equipmentVariety");
		String equipmentVariety_name = request.getParameter("equipmentVariety_name");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			
			List<DeviceCountDTO> list = analyseService.exportPrintedCount_JD6(equipmentVariety,startDate, endDate);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("equipmentVariety", equipmentVariety);
			request.getSession().setAttribute("equipmentVariety_name", equipmentVariety_name);
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("机电六部已打印的"+equipmentVariety_name+"定检、监检报告统计表导出失败！");
		}
		return "app/statistics/export_printed_count_jd6";
	}
	
	/**
	 * 业务服务部综合统计（含打印、领取报告、盖章、借阅、纠错等等统计）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @since 2017-12-11 上午11:12:00
	 */
	@RequestMapping(value = "all_count")
	@ResponseBody
	public HashMap<String, Object> all_count(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			wrapper.put("success", true);
			wrapper.put("data", analyseService.all_count(startDate, endDate));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取综合统计表数据失败，请重试！");
		}
		return wrapper;
	}
}
