package com.scts.cspace.file.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.cspace.file.bean.FileCache;
import com.scts.cspace.file.service.FileCacheService;

/**
 * 物理资源属性
 * 
 * @ClassName TjyptFileInfoAction
 * @JDK 1.7
 * @author XCB
 * @date 2016-10-24 下午04:49:00
 */
@Controller
@RequestMapping("fileCache")
public class FileCacheAction extends
		SpringSupportAction<FileCache, FileCacheService> {

	@Autowired
	private FileCacheService fileCacheService;

	/**
	 * 根据父ID查询文件
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "queryById")
	@ResponseBody
	public HashMap<String, Object> queryById(HttpServletRequest request
			)  {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {	
			
			String fileId = request.getParameter("parent_id");//获取资源对象ID
			
			
			
			wrapper=fileCacheService.queryById(fileId);
			wrapper.put("success", true);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			wrapper.put("success", false);
		}
		
		return wrapper;
	}


}