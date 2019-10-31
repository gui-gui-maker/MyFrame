package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.humanresources.bean.OrgidLeaderid;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.service.OrgidLeaderidManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("orgidLeaderidAction")
public class OrgidLeaderidAction extends SpringSupportAction<OrgidLeaderid, OrgidLeaderidManager> {

    @Autowired
    private OrgidLeaderidManager  orgidLeaderidManager;
    
    /**
	 * 保存
	 * 
	 * @param request
	 * @param orgidLeaderid
	 * @throws Exception
	 */
    @RequestMapping(value = "saveRelate")
	@ResponseBody
    public HashMap<String, Object> saveRelate(HttpServletRequest request, OrgidLeaderid orgidLeaderid) throws Exception{
    	CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
    	String pageStatus = request.getParameter("status");
    	if(pageStatus.equals("add")){
    		orgidLeaderid.setCreateDate(new Date());
    		orgidLeaderid.setCreateId(curUser.getId());
    		orgidLeaderid.setCreateBy(curUser.getName());
    	}else if(pageStatus.equals("modify")){
    		orgidLeaderid.setLastModifyDate(new Date());
    		orgidLeaderid.setLastModifyId(curUser.getId());
    		orgidLeaderid.setLastModifyBy(curUser.getName());
    	}
    	try {
    		orgidLeaderidManager.save(orgidLeaderid);
		} catch (Exception e) {
			// TODO: handle exception
			return JsonWrapper.failureWrapper("保存失败！请重试！");
		}
		return JsonWrapper.successWrapper("保存成功！");
    }
	
}