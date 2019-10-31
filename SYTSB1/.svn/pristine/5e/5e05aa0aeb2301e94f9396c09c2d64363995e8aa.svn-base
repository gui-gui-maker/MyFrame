package com.lsts.advicenote.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.bean.MessageContentCon;
import com.lsts.advicenote.service.MessageContentConService;


@Controller
@RequestMapping("messageContentConAction")
public class MessageContentConAction extends SpringSupportAction<MessageContentCon, MessageContentConService> {

	@Autowired
	private MessageContentConService contentConService;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, MessageContentCon entity) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			contentConService.save(entity);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","保存失败！");
		}
		
		
		return map;
	}
	
	public HashMap<String, Object> del(HttpServletRequest request,String ids) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			contentConService.del(ids);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","删除失败！");
		}
		
		
		return map;
	}
	//启用禁用
	@RequestMapping(value = "disable")
	@ResponseBody
     public HashMap<String, Object> disable(HttpServletRequest request,String id,String status) throws Exception {
    	 CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			/*String id[]=ids.split(",");
			for (int i = 0; i < id.length; i++) {*/
				MessageContentCon con=contentConService.get(id);
				if(status.equals("1")){
					con.setData_status("1");
				}
				if(status.equals("0")){
					con.setData_status("0");
				}
				con.setLast_update_op_id(user.getId());
				con.setLast_update_op(user.getName());
				con.setLast_update_time(new Date());
				contentConService.save(con);
			/*}*/
			
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","删除失败！");
		}
		
		
		return map;
	}
  
	
	
}
