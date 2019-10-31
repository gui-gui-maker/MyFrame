package com.lsts.finance.web;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.ImportSalary;
import com.lsts.finance.bean.Salary;
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
@RequestMapping("finance/salaryAction")
public class SalaryAction<Ayou> extends SpringSupportAction<Salary, SalaryManager> {

	// 必须提供Demo实体的manager对象，使用注解@Autowired标识为自动装配
	@Autowired
	private SalaryManager salaryManager;
	@Autowired
	private ImportSalaryManager importSalaryManager;
	
	
	@RequestMapping(value = "savegz")
	@ResponseBody
	public HashMap<String,Object> savegz(HttpServletRequest request ,String month, String etime,ImportSalary importSalary,String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
	//	java.text.SimpleDateFormat formatter = new SimpleDateFormat("\"yyyy-MM\"");
	String etime1 =	etime.replace( "\"", " ");
	String month1 = month.replace("\"", " ");
		System.out.println(etime1);
		try {
			//Date date =  formatter.parse(etime);
			String imid =	importSalaryManager.saveim(importSalary,month1,etime1);
			String result = salaryManager.saveBankData(imid,files,getCurrentUser());
			if(StringUtil.isNotEmpty(result)) {
				data.put("result", result);
				data.put("success", false);
			}else {
				data.put("success", true);
			}
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportCount")
	public String exportCountByUser(HttpServletRequest request){
		String id = request.getParameter("imid");
		
		try {
			List<Salary> sal =	salaryManager.getsal(id);
			List<ImportSalary>  imp = importSalaryManager.getImsal(id); //导入时间
			String tmo = imp.get(0).getSalaryYear() ;
			String year = imp.get(0).getSalaryTmonth();
			request.getSession().setAttribute("year", year);
			request.getSession().setAttribute("tmo", tmo);
			request.getSession().setAttribute("data", sal);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("各部门人员业务统计表导出失败！");
		}
	
		
		
		return "app/finance/salary_export";
	}
	/**
	 * 根据id获取工资信息
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "getSalary")
	@ResponseBody
   	public HashMap<String, Object> getSalary(HttpServletRequest request,String id) throws Exception {
    	Salary salary=salaryManager.getSalary(id);
   		return JsonWrapper.successWrapper(salary);
   	}

}
