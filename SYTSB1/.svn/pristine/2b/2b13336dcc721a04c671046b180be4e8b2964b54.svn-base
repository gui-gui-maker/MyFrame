/*package tzsb.deviceClassify.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tzsb.deviceClassify.bean.DeviceClassify;
import tzsb.deviceClassify.service.DeviceClassifyService;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

import demo.fileupload.bean.TestUploadDemo;


*//** 
 * @author 作者 幸鑫
 * @JDK 1.6
 * @version 创建时间：2014年12月17日 上午10:30:33 
 * 类说明 
 *//*

@Controller
@RequestMapping("deviceClassify/modify")
public class DeviceClassifyAction extends SpringSupportAction<DeviceClassify,DeviceClassifyService>{
	
	@Autowired
	private DeviceClassifyService deviceClassifyService;
	*//**
	 * 给种类数据插入树级区别代码
	 *//*
	@RequestMapping(value = "input")
	@ResponseBody
	public HashMap<String, Object> input(HttpServletRequest request,
			DeviceClassify deviceClassify) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			System.out.println("#############################");
			deviceClassifyService.saveClassifyEntity();
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "插入数据信息失败，请重试！");
		}
		wrapper.put("data", deviceClassify);
		return wrapper;
	}
	
	@RequestMapping(value = "savefiles")
	@ResponseBody
	public HashMap<String, Object> saveFiles(HttpServletRequest request,
			TestUploadDemo entity, String uploadFiles, String uploadFile)
			throws Exception {

		System.out.println("#############################");
		return JsonWrapper.successWrapper(entity);
	}
}*/
package demo.deviceClassify.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import demo.deviceClassify.bean.DeviceClassify;
import demo.deviceClassify.service.DeviceClassifyService;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.task.Task;
import com.khnt.task.ThreadPool;

import demo.fileupload.bean.TestUploadDemo;
import demo.fileupload.service.TestUploadDemoManager;


/**
 * 上传下载测试Action
 * 
 * @author jyl
 * 
 */
@Controller
@RequestMapping("demo/modify")
public class DeviceClassifyAction extends
		SpringSupportAction<DeviceClassify, DeviceClassifyService> {



	@Autowired
	private DeviceClassifyService deviceClassifyService;



	/**
	 * 插入树级标记的数据
	 */
	@RequestMapping(value = "input")
	@ResponseBody
	public void input()throws Exception {

		deviceClassifyService.saveClassifyEntity();
	}

	/**
	 * 自行处理异常保存业务数据及附件
	 * 
	 * @param request
	 *            页面对象
	 * @param entity
	 *            业务实体
	 * @param uploadFiles
	 *            附件id集
	 * @return 业务数据map
	 */
	@RequestMapping(value = "savefiles1")
	@ResponseBody
	public HashMap<String, Object> saveFiles1(HttpServletRequest request,
			TestUploadDemo entity, String uploadFiles, String uploadFile) {
		try {
		System.out.println("#$%@#$^@#$^@#$%@#$@#$%@#$%@#%$");
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return JsonWrapper.failureWrapper();
		}
	}
	
	
}
