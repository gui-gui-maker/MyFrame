package com.scts.cspace.file.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.cspace.file.bean.TjyptFileInfo;
import com.scts.cspace.file.service.TjyptFileInfoService;

/**
 * 物理资源属性
 * 
 * @ClassName TjyptFileInfoAction
 * @JDK 1.7
 * @author XCB
 * @date 2016-10-24 下午04:49:00
 */
@Controller
@RequestMapping("fileInfoSpace")
public class TjyptFileInfoAction extends
		SpringSupportAction<TjyptFileInfo, TjyptFileInfoService> {

	@Autowired
	private TjyptFileInfoService tjyptFileInfoService;

	/**
	 * 修改物理资源路径
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateFilePath")
	@ResponseBody
	public HashMap<String, Object> updateFilePath(HttpServletRequest request
			)  {
		
		try {	
			
			String fileId = request.getParameter("id");//获取资源对象ID
			
			String filePath = request.getParameter("filePath");//获取物理资源路径
			
			tjyptFileInfoService.updateFilePath(fileId,filePath);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改物理资源路径失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改上传人
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateFileUploadUserId")
	@ResponseBody
	public HashMap<String, Object> updateFileUploadUserId(HttpServletRequest request
			)  {
		
		try {	
			
			String fileId = request.getParameter("id");//获取资源对象ID
			
			String fileUploadUserId = request.getParameter("fileUploadUserId");//获取上传人
			
			tjyptFileInfoService.updateFilePath(fileId,fileUploadUserId);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改上传人失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改访问次数
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateFileOpenCount")
	@ResponseBody
	public HashMap<String, Object> updateFileOpenCount(HttpServletRequest request
			)  {
		
		try {	
			
			String fileId = request.getParameter("id");//获取资源对象ID
			
			String fileOpenCount = request.getParameter("fileOpenCount");//获取访问次数
			
			tjyptFileInfoService.updateFileOpenCount(fileId,fileOpenCount);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改访问次数失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}
	/**
	 * 修改下载次数
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "updateFileDownloadCount")
	@ResponseBody
	public HashMap<String, Object> updateFileDownloadCount(HttpServletRequest request
			)  {
		
		try {	
			
			String fileId = request.getParameter("id");//获取资源对象ID
			
			String fileDownloadCount = request.getParameter("fileDownloadCount");//获取下载次数
			
			tjyptFileInfoService.updateFileDownloadCount(fileId,fileDownloadCount);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return JsonWrapper.failureWrapperMsg("修改下载次数失败，请重试！");
		}
		
		return JsonWrapper.successWrapper(1);
	}

}