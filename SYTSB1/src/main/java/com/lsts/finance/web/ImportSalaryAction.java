package com.lsts.finance.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.ImportSalary;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.service.ImportSalaryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






@Controller
@RequestMapping("finance/importSalaryAction")
public class ImportSalaryAction extends SpringSupportAction<ImportSalary, ImportSalaryManager> {

    @Autowired
    private ImportSalaryManager  importSalaryManager;
    
   
    
//    @RequestMapping(value = "saveim")
//	@ResponseBody
//    public HashMap<String, Object> saveim(HttpServletRequest request, ImportSalary importSalary) throws Exception{
//    	
//    	importSalaryManager.saveim(importSalary,importSalary.getSalartTime());
//    	
//		
//    	HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("success", true);
//		return map;
//	}
    
    @RequestMapping(value = "imdelete")
 	@ResponseBody
     public HashMap<String, Object> imdelete(HttpServletRequest request, ImportSalary importSalary) throws Exception{
     String id =	importSalary.getId();
 		
     	return super.delete(id);
   
    }
    @Override
	public HashMap<String, Object> delete(String ids) throws Exception {
    	System.out.println(ids.split(","));
    	String[] a = ids.split(",");
    	for(int i=0;i<a.length;i++){
    		System.out.println(a[i]);
    		System.out.println(ids);
        	importSalaryManager.delete(a[i]);
    	}
    	
		return JsonWrapper.successWrapper();
	}
	
}