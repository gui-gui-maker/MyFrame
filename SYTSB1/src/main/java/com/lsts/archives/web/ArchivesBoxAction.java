package com.lsts.archives.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.archives.bean.ArchivesBox;
import com.lsts.archives.service.ArchivesBoxManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;











import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("archives/box")
public class ArchivesBoxAction extends SpringSupportAction<ArchivesBox, ArchivesBoxManager> {

    @Autowired
    private ArchivesBoxManager  archivesBoxManager;
    
    //@RequestBody
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, ArchivesBox archivesBox) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	CurrentSessionUser user = this.getCurrentUser();
    	archivesBoxManager.saveTask(archivesBox, user);
    	map.put("success", true);
		return map;
	}
    
    
    @Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			return super.detail(request, id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getCause());
			return map;
		}
	}


	/**
     * 改版档案合保存方法
     * @param request
     * @param archivesBox
     * @return
     * @throws Exception
     */
    @RequestMapping(value="saveManul")
    @ResponseBody
    public HashMap<String, Object> saveManul(HttpServletRequest request, ArchivesBox archivesBox) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try {
    		archivesBoxManager.saveManul(archivesBox);
        	map.put("success", true);
        	map.put("data", archivesBox);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
        	map.put("msg", e.getMessage());
		}
    	return map;
    }
    
	@RequestMapping(value="del")
	@ResponseBody
	public HashMap<String, Object> del(String id) throws Exception{
		HashMap<String, Object> map=new HashMap<String,Object>();
		try{
		//ArchivesBox archivesBox=archivesBoxManager.get(id);
		//if(archivesBox.getReportNumberId()==null || archivesBox.getReportNumberId().equals("")){
			archivesBoxManager.delete(id);
			map.put("msg","档案盒删除成功！");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success", false);
		}
		//}else{
		//	map.put("msg","档案盒中已有档案不可删除！");
		//	map.put("success", false);
		//}
		return map;
		
	}
	
}