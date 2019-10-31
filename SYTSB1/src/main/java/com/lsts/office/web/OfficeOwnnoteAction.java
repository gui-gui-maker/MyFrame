package com.lsts.office.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.office.bean.OfficeOwnnote;
import com.lsts.office.service.OfficeOwnnoteManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("officeOwnnoteAction")
public class OfficeOwnnoteAction extends SpringSupportAction<OfficeOwnnote, OfficeOwnnoteManager> {

    @Autowired
    private OfficeOwnnoteManager  officeOwnnoteManager;
    /**
	 * 保存个人工作记录
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOwnnote")
	@ResponseBody
	public HashMap<String, Object> saveOwnnote(HttpServletRequest request,OfficeOwnnote officeOwnnote){
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");
		if(pageStatus.equals("add")){
			officeOwnnote.setCreateDate(new Date());
			officeOwnnote.setCreateId(curUser.getId());
			officeOwnnote.setCreateBy(curUser.getName());
		}else if(pageStatus.equals("modify")){
			officeOwnnote.setLastModifyDate(new Date());
			officeOwnnote.setLastModifyId(curUser.getId());
			officeOwnnote.setLastModifyBy(curUser.getName());
		}
		try {
			officeOwnnoteManager.save(officeOwnnote);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper("保存成功！");
		
	}
	/**
	 * 标记为已完成
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "signFinish")
	@ResponseBody
	public HashMap<String, Object> signFinish(HttpServletRequest request,String ids){
		try {
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				OfficeOwnnote officeOwnnote=officeOwnnoteManager.get(idsArr[i]);
				officeOwnnote.setWorkStatus("2");//标记为已完成
				officeOwnnoteManager.save(officeOwnnote);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败，请重试！");
		}
		return JsonWrapper.successWrapper("标记成功！");
		
	}
	/**
	 * 重启工作记事
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "restart")
	@ResponseBody
	public HashMap<String, Object> restart(HttpServletRequest request,String ids){
		try {
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				OfficeOwnnote officeOwnnote=officeOwnnoteManager.get(idsArr[i]);
				officeOwnnote.setWorkStatus("1");//标记为进行中
				officeOwnnoteManager.save(officeOwnnote);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败，请重试！");
		}
		return JsonWrapper.successWrapper("重启记事成功！");
		
	}
}