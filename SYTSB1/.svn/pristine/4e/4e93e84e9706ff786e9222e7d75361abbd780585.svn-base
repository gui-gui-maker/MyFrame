package com.lsts.archives.web;

import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesDestroy;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.archives.service.ArchivesDestroyManager;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;








import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("archives/destroy")
public class ArchivesDestroyAction extends SpringSupportAction<ArchivesDestroy, ArchivesDestroyManager> {

    @Autowired
    private ArchivesDestroyManager  archivesDestroyManager;
    @Autowired
    private ArchivesBorrowManager  archivesBorrowManager;
    @Autowired
	private InspectionInfoService infoService;
    
    
    @RequestMapping(value = "archivesHf")
	@ResponseBody
   	public HashMap<String, Object> archivesHf(HttpServletRequest request,String id) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		InspectionInfo inspectionInfo=infoService.get(id);
   		inspectionInfo.setArchives_status("1");
   		infoService.save(inspectionInfo);
       	map.put("success", true);
   		return map;
   	}
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
       @Override
   	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody ArchivesDestroy archivesDestroy) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		if(archivesDestroy.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_YTJ) ||
   				archivesDestroy.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_SHZ) || 
   				archivesDestroy.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_PASS) || 
   				archivesDestroy.getStatus().equals(ArchivesBorrowManager.DA_JYSQ_NO_PASS)){
   	   			map.put("msg", "此条数据不可修改！");
   				map.put("success", false);
   		}else{
	   		archivesDestroyManager.saveTask(archivesDestroy,user);
	       	map.put("success", true);
   			map.put("msg", "数据保存成功！");

   		}
   		return map;
   	}
       
	 /**
	 * 提交审核
	 * 
	 * */
	@RequestMapping(value = "subFolws")
	@ResponseBody
	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
			String typeCode, String status,String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//流程传参
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请 ");
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
		// 启动流程
			if (StringUtil.isNotEmpty(flowId)) {
				archivesBorrowManager.doStartPress(map);
				//改变状态
				ArchivesDestroy archivesDestroy=archivesDestroyManager.get(id);
				archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_YTJ);
				archivesDestroyManager.save(archivesDestroy);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}
	
	}   
	
}