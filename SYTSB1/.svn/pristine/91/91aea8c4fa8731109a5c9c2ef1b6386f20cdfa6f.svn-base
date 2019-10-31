package demo.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;

import demo.fileupload.bean.TestUploadDemo;
import demo.fileupload.dao.TestUploadDemoDao;

/**
 * 上传下载测试manager
 * 
 * @author jyl
 * 
 */
@Service("TestUploadDemo")
public class TestUploadDemoManager extends
		EntityManageImpl<TestUploadDemo, TestUploadDemoDao> {
	@Autowired
	TestUploadDemoDao testUploadDemoDao;

	@Autowired
	private AttachmentManager attachmentManager;

	/**
	 * 保存业务数据表以及相应附件
	 * 
	 * @param testUploadDemo
	 *            业务实体
	 * @param uploadFiles
	 *            附件对应的id集
	 * @return 返回业务实体
	 * @throws Exception
	 *             抛出异常
	 */
	public TestUploadDemo saveTestUpload(TestUploadDemo testUploadDemo,
			String uploadFiles, String uploadFile) throws Exception {
		testUploadDemoDao.save(testUploadDemo);
		// 向附件中增加业务id
		if (!StringUtil.isEmpty(uploadFiles)) {
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for (String fid : files) {
				if (!StringUtil.isEmpty(fid))
					attachmentManager.setAttachmentBusinessId(fid,
							testUploadDemo.getId());
			}
			// attachmentManager.setAttachmentBusinessId(files,testUploadDemo.getId());
		}
		// 向附件中增加业务id
		if (!StringUtil.isEmpty(uploadFile)) {
			String[] files = uploadFile.replaceAll("/^,/", "").split(",");
			for (String fid : files) {
				if (!StringUtil.isEmpty(fid))
					attachmentManager.setAttachmentBusinessId(fid,
							testUploadDemo.getId());
			}
			// attachmentManager.setAttachmentBusinessId(files,testUploadDemo.getId());
		}
		return testUploadDemo;
	}
}
