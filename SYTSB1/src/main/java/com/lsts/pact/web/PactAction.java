package com.lsts.pact.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.lsts.pact.bean.PactInfo;
import com.lsts.pact.bean.RuleInfo;
import com.lsts.pact.service.PactManager;
import com.lsts.pact.service.RuleManager;

@Controller
@RequestMapping("pact/action")
public class PactAction extends SpringSupportAction<PactInfo, PactManager> {
	  @Autowired
	    private PactManager  pactManager;
	  @Autowired
	    private RuleManager  ruleManager;
	  @Autowired
	   	private AttachmentManager attachmentManager;
	/**
  	 * 保存上传文件
  	 *
  	 */
  	@ResponseBody
	@RequestMapping("saveFile")
	public HashMap<String, Object> saveFile(HttpServletRequest request,PactInfo pactInfo) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		String uploadFiles = request.getParameter("uploadFiles");
   		
   			

   			pactManager.saveInfo(pactInfo, uploadFiles);
			map.put("success", true);
			map.put("msg", "保存成功！");

   		return map;
   	}
  	
  	
	/**
  	 * 保存上传文件
  	 *
  	 */
  	@ResponseBody
	@RequestMapping("saveRule")
	public HashMap<String, Object> saveRule(HttpServletRequest request,RuleInfo ruleInfo) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		String uploadFiles = request.getParameter("uploadFiles");
   		
   			

   			ruleManager.saveRule(ruleInfo, uploadFiles);
			map.put("success", true);
			map.put("msg", "保存成功！");

   		return map;
   	}
  	
  	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public HashMap<String, Object> getInfo(HttpServletRequest request, String id) throws Exception {
		PactInfo pact = pactManager.get(id); 
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", pact);
		wrapper.put("attachs", list);
		return wrapper;
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRule")
	@ResponseBody
	public HashMap<String, Object> getRule(HttpServletRequest request, String id) throws Exception {
		RuleInfo rule = ruleManager.get(id); 
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", rule);
		wrapper.put("attachs", list);
		return wrapper;
	}
	
	// 删除
    @RequestMapping(value = "del")
    @ResponseBody
    public HashMap<String, Object> del(String ids) throws Exception {
    	
    	pactManager.del(ids);
        return JsonWrapper.successWrapper(ids);
    }
    
 // 删除
    @RequestMapping(value = "delRule")
    @ResponseBody
    public HashMap<String, Object> delRule(String ids) throws Exception {
    	
    	ruleManager.delRule(ids);
        return JsonWrapper.successWrapper(ids);
    }
}
