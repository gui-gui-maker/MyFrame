package com.lsts.humanresources.Tjy2Ywfwbgzqrb.web;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.bean.Picture;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.bean.Tjy2Ywfwbgzqrb;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.service.Tjy2YwfwbgzqrbManager;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.dao.Tjy2YwfwbgzqrbDao;
import com.lsts.humanresources.bean.BgLeave;
import com.lsts.humanresources.bean.Tjy2Gjj;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.office.bean.MeetingRoomInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("tjy2YwfwbgzqrbAction")
public class Tjy2YwfwbgzqrbAction extends SpringSupportAction<Tjy2Ywfwbgzqrb, Tjy2YwfwbgzqrbManager> {

	@Autowired
   	private EmployeeBaseManager employeeBaseManager;
    @Autowired
    private Tjy2YwfwbgzqrbManager  tjy2YwfwbgzqrbManager;
    @Autowired
    private Tjy2YwfwbgzqrbDao tjy2YwfwbgzqrbDao;
    
	
		@Override
	public HashMap<String, Object> save(HttpServletRequest request, Tjy2Ywfwbgzqrb tjy2Ywfwbgzqrb){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	CurrentSessionUser user = SecurityUtil.getSecurityUser(); 	// 获取当前用户登录信息
    	tjy2YwfwbgzqrbManager.saveyw(request,user,tjy2Ywfwbgzqrb);
		
    map.put("success", true);
    	return map;
    }
    
		 @RequestMapping(value = "submit")
			@ResponseBody
			public HashMap<String, Object> submit(HttpServletRequest request,String Id) throws Exception{
		    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
		    	Tjy2Ywfwbgzqrb tjy2Ywfwbgzqrb = tjy2YwfwbgzqrbManager.get(Id);
		    	
		    	tjy2Ywfwbgzqrb.setYesNo("已确认");//个人确认
		    	
		    	tjy2YwfwbgzqrbManager.save(tjy2Ywfwbgzqrb);
		    	wrapper.put("success", true);
				return wrapper;
		    }
		 
   //微信确认
		 @RequestMapping(value = "queren")
			@ResponseBody
			public HashMap<String, Object> queren(HttpServletRequest request,String Id) throws Exception{
		    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
		    	Tjy2Ywfwbgzqrb tjy2Ywfwbgzqrb = tjy2YwfwbgzqrbManager.get(Id);
		    	
		    	tjy2Ywfwbgzqrb.setYesNo("已确认");//个人确认
		    	
		    	tjy2YwfwbgzqrbManager.save(tjy2Ywfwbgzqrb);
		    	wrapper.put("success", true);
				return wrapper;
		    }
		
		//微信查询
		 
			@RequestMapping(value = "detail1")
			@ResponseBody
			public HashMap<String, Object> detail1(HttpServletRequest request, String id)
					throws Exception {
				return super.detail(request, id);
			}
			
			
			 @RequestMapping(value = "getids")
				@ResponseBody
				public HashMap<String, Object> getids(HttpServletRequest request,String ids) throws Exception{
			    	HashMap<String, Object> map = new HashMap<String, Object>();
			    	String idss = tjy2YwfwbgzqrbManager.GetIDs(ids);
			    	
			    	map.put("success", true);
			    	map.put("idss", idss);
					return map;
			    }
		/**
		 * 根据员工手机号获取工资业务ID
		 * @param request
		 * @param phone
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "getBusinessId")
		@ResponseBody
		public HashMap<String, Object> getBusinessId(HttpServletRequest request,String phone) throws Exception{
			String nameId = employeeBaseManager.getEmpByPhone(phone).getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			String ids=tjy2YwfwbgzqrbDao.getIdByNameId(nameId).toString();
			String idss = Pattern.compile("\\b([\\w\\W])\\b").matcher(ids.toString()
					.substring(1,ids.toString().length()-1)).replaceAll("'$1'");
			
			map.put("success", true);
			map.put("idss", idss);
			return map;
		}
			//删除工资信息
				/**
				 * 删除
				 * */
			    @RequestMapping(value = "delete")
				@Override
				public HashMap<String, Object> delete(String id) throws Exception {
					HashMap<String, Object> map = new HashMap<String, Object>();
					try {
						tjy2YwfwbgzqrbManager.delete(id);
						map.put("success", true);
					} catch (Exception e) {
						e.printStackTrace();
						map.put("success", true);
						map.put("msg", "删除失败！");
					}
				return map;
				}
}