package com.lsts.office.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.office.bean.WeightyDep;
import com.lsts.office.dao.WeightyTaskDao;
import com.lsts.office.service.WeightyDepManager;






@Controller
@RequestMapping("weighty/Dep")
public class WeightyDepAction extends SpringSupportAction<WeightyDep, WeightyDepManager> {

    @Autowired
    private WeightyDepManager  weightyDepManager;
    
    @Autowired
    private WeightyTaskDao weightyTaskDao;
    
    /**
     * 保存信息 并遍历重要任务信息获得ID
     * @param request
     * @param weightyDep
     * @return
     */
    @RequestMapping(value = "saveWei")
   	@ResponseBody
       public HashMap<String,Object> saveWei(HttpServletRequest request,WeightyDep weightyDep){
       		CurrentSessionUser user = SecurityUtil.getSecurityUser();
       		weightyDepManager.saveWei(weightyDep,user,null);
       		
       		return JsonWrapper.successWrapper(weightyDep);
       }
    
    @RequestMapping(value="getdetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{
    	List<WeightyDep> list = weightyDepManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
    
    /**
     * 删除配合部门信息
     * @param ids
     * @param weightyDep
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getDelete")
    @ResponseBody
    public HashMap<String, Object> getDelete(String ids,WeightyDep weightyDep) throws Exception {
    	weightyDep = weightyDepManager.get(ids);
    	weightyDepManager.delete(ids,weightyDep);
		return JsonWrapper.successWrapper();
	}
    	
    
    
}