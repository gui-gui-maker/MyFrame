package demo.fileupload.web;

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
@RequestMapping("demo/upload")
public class TestUploadDemoAction extends
		SpringSupportAction<TestUploadDemo, TestUploadDemoManager> {

	@Autowired
	private TestUploadDemoManager testUploadDemoManager;

	@Autowired
	private AttachmentManager attachmentManager;

	/**
	 * 获取详情 以及附件列表
	 */
	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = super.detail(request, id);
			List<Attachment> manyfiles = this.attachmentManager.getBusAttachment(id,"many");
			List<Attachment> onefiles = attachmentManager.getBusAttachment(id, "one");
			map.put("onefiles", onefiles);
			map.put("manyfiles", manyfiles);
			return map;
		} catch (Exception e) {
			log.error(e.getMessage());
			return JsonWrapper.failureWrapper();
		}
	}

	/**
	 * 将异常抛出由框架自行处理
	 * 
	 * @param request
	 *            页面对象
	 * @param entity
	 *            业务实体
	 * @param uploadFiles
	 *            附件id集
	 * @return 业务数据map json对象
	 * @throws Exception
	 *             抛出异常
	 */
	@RequestMapping(value = "savefiles")
	@ResponseBody
	public HashMap<String, Object> saveFiles(HttpServletRequest request,
			TestUploadDemo entity, String uploadFiles, String uploadFile)
			throws Exception {

		entity = testUploadDemoManager.saveTestUpload(entity, uploadFiles,
				uploadFile);
		return JsonWrapper.successWrapper(entity);
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
			entity = testUploadDemoManager.saveTestUpload(entity, uploadFiles,
					uploadFile);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return JsonWrapper.failureWrapper();
		}
	}
	
}