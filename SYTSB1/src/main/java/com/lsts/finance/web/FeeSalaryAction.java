package com.lsts.finance.web;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.ImportSalary;
import com.lsts.finance.bean.Salary;
import com.lsts.finance.service.FeeSalaryManager;
import com.lsts.finance.service.ImportSalaryManager;
import com.lsts.finance.service.SalaryManager;


/**
 * <p>
 * web控制器组件。该组件继承自泛型类SpringSupportAction，并提供了运行时的bean和manager对象。
 * 由此获得了对bean的crund操作能力，SpringSupportAction类中已经定义了对bean的 save,detail,delete方法。
 * 
 * </p>
 * <p>
 * 注解@Controller标识该类为web 控制器；
 * </p>
 * <p>
 * 注解@RequestMapping定义该控制器的web访问路径
 * </p>
 * @param <Ayou>
 */
@Controller
@RequestMapping("finance/feeSalaryAction")
public class FeeSalaryAction<Ayou> extends SpringSupportAction<Salary, FeeSalaryManager> {

	
	@Autowired
	private FeeSalaryManager feeSalaryManager;
	
	/**
	 * 工资统计个人
	 * author pingZhou
	 * @param start
	 * @param end
	 * @param unit
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="salaryFee")
	@ResponseBody
	public HashMap<String, Object> statisticsFee(HttpServletRequest request,String start,String end,String unit,String dept) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String gzItem = request.getParameter("gzItem");
			String peoId = request.getParameter("peoId");
			map = feeSalaryManager.statisticsFee(start.substring(0,7), end.substring(0,7),unit,dept,gzItem,peoId);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 *  按月份统计个人工资
	 * @param request
	 * @param start
	 * @param end
	 * @param unit
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="statisticsSalary")
	@ResponseBody
	public HashMap<String, Object> statisticsSalary(HttpServletRequest request,String start,String end,String unit,String dept) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = feeSalaryManager.statisticsSalary(start, end,unit,dept);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 工资统计部门
	 * author pingZhou
	 * @param start
	 * @param end
	 * @param unit
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="salaryFeeUnit")
	@ResponseBody
	public HashMap<String, Object> salaryFeeUnit(HttpServletRequest request,String start,String end,String unit,String dept) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String gzItem = request.getParameter("gzItem");
			map = feeSalaryManager.statisticsFeeUnit(start.substring(0,7), end.substring(0,7),unit,dept,gzItem);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 导出工资统计个人
	 * author pingZhou
	 * @param start
	 * @param end
	 * @param unit
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="exportSalaryFee")
	public String exportSalaryFee(HttpServletRequest request,String startDate,String endDate,String unit,String dept,String items) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String gzItem = request.getParameter("gzItemV");
			String peoId = request.getParameter("peoId");
			map = feeSalaryManager.statisticsFee(startDate.substring(0,7), endDate.substring(0,7),unit,dept,gzItem,peoId);
			request.getSession().setAttribute("items", items);
			request.getSession().setAttribute("start", startDate);
			request.getSession().setAttribute("end", endDate);
			request.getSession().setAttribute("data",map);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return "app/finance/salary/salary_fee_exp";
	}
	
	
	/**
	 * 导出工资统计部门
	 * author pingZhou
	 * @param start
	 * @param end
	 * @param unit
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="exportSalaryFeeUnit")
	public String exportSalaryFeeUnit(HttpServletRequest request, String startDate,String endDate,String unit,String dept,String items) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String gzItem = request.getParameter("gzItemV");
			System.out.println(gzItem);
			map = feeSalaryManager.statisticsFeeUnit(startDate.substring(0,7), endDate.substring(0,7),unit,dept,gzItem);
			request.getSession().setAttribute("items", items);
			request.getSession().setAttribute("start", startDate);
			request.getSession().setAttribute("end", endDate);
			request.getSession().setAttribute("data",map);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return "app/finance/salary/salary_fee_unit_exp";
	}
	
		@RequestMapping(value="exportSalaryFeeU")
		public String exportSalaryFeeU(HttpServletRequest request, String start,String end,String unit,String dept) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				String data = request.getParameter("data");
				request.getSession().setAttribute("start", start);
				request.getSession().setAttribute("end", end);
				request.getSession().setAttribute("data",data);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
			return "app/finance/salary/salary_fee_unit_exp";
		}
}
