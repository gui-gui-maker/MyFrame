package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.Taskbook;
import com.lsts.qualitymanage.service.TaskbookManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;








import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("Taskbook/a")
public class TaskbookAction extends SpringSupportAction<Taskbook, TaskbookManager> {

    @Autowired
    private TaskbookManager  tTaskbookManager;
	
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @Override
   	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody Taskbook taskbook) throws Exception {
	   CurrentSessionUser user = SecurityUtil.getSecurityUser();
	   HashMap<String, Object> map = new HashMap<String, Object>();
//	   taskbook.setRegistrant(user.getName());
//	   taskbook.setRegistrantId(user.getId());
//	   taskbook.setRegistrantDate(new Date());
	   taskbook.setStatus("未打印");
//	   taskbook.setYbfwbjbr(user.getName());
//	   taskbook.setJbsj(new Date());
	   try {
		   tTaskbookManager.save(taskbook);
		
	} catch (Exception e) {
e.printStackTrace();	}
	   map.put("success", true);
	   map.put("msg", "数据保存成功！");
	   return map;
   }
    /**
     * 设置打印状态
     * */
    @RequestMapping(value = "setdy")
   	@ResponseBody
   	public void setdy(String id) throws Exception{
    	Taskbook taskbook=tTaskbookManager.get(id);
    	taskbook.setStatus("已打印");
    	tTaskbookManager.save(taskbook);
    }
}