package com.lsts.inspection.service;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.pub.fileupload.service.DiskAttachmentManagerImpl;
import com.khnt.pub.fileupload.service.IAttachmentManager;
import com.khnt.utils.BeanUtils;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionVersion;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.inspection.dao.InspectionVersionDao;

@Service("inspectionVersionService")
public class InspectionVersionService extends EntityManageImpl<InspectionVersion, InspectionVersionDao> {

	@Autowired
	InspectionVersionDao inspectionVersionDao;
	@Autowired
	InspectionInfoDao infoDao;
	@Autowired
	AttachmentDao attachmentDao;
	@Autowired
	private IAttachmentManager attachmentManager;
	
	public void copyOldAtt(String infoId,String type) throws Exception {
		
		InspectionInfo info = infoDao.get(infoId);
		int maxVersion = inspectionVersionDao.getMaxVersion(infoId);
		if("record".equals(type)) {
			//原始记录
			//历史原始记录附件
			String recordAtt = info.getPdfExportRecordAtt();
			if(recordAtt==null) {
				return;
			}
			Attachment attachment2 = attachmentDao.get(recordAtt);
			Attachment attachment = new Attachment();
			BeanUtils.copyProperties(attachment, attachment2);
			InputStream  inputStream = attachmentDao.getInputStreamFromAttachment(recordAtt);//new FileInputStream(file);
			String inspectDate = new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time());
			String folder =  inspectDate+ File.separator + info.getReport_sn()+File.separator+type+File.separator+maxVersion;
			
			System.out.println("folder:" + folder);
			String fname = info.getReport_sn() + ".pdf";
			
			Map<String, String> options = new HashMap<String, String>();
			options.put(DiskAttachmentManagerImpl.SAVE_FOLDER, folder);
			options.put(DiskAttachmentManagerImpl.SAVE_FILE_NAME, fname);
			attachment.setId(null);
			attachment.setFilePath(null);
			attachmentManager.saveAttached(inputStream, attachment, options);
			InspectionVersion inspectionVersion = inspectionVersionDao.getMaxVersionBean(infoId, maxVersion);
			if(inspectionVersion!=null) {
				inspectionVersion.setRecordAtt(attachment.getId());
			}
			
		}else {
			//报告
			String reportAtt = info.getPdfExportAtt();
			
			if(reportAtt==null) {
				return;
			}
			Attachment attachment2 = attachmentDao.get(reportAtt);
			Attachment attachment = new Attachment();
			BeanUtils.copyProperties(attachment, attachment2);
			InputStream  inputStream = attachmentDao.getInputStreamFromAttachment(reportAtt);//new FileInputStream(file);
			String inspectDate = new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time());
			String folder =  inspectDate+ File.separator + info.getReport_sn()+File.separator+"report"+File.separator+maxVersion;
			
			System.out.println("folder:" + folder);
			String fname = info.getReport_sn() + ".pdf";
			
			Map<String, String> options = new HashMap<String, String>();
			options.put(DiskAttachmentManagerImpl.SAVE_FOLDER, folder);
			options.put(DiskAttachmentManagerImpl.SAVE_FILE_NAME, fname);
			attachment.setId(null);
			attachment.setFilePath(null);
			attachmentManager.saveAttached(inputStream, attachment, options);
			
			InspectionVersion inspectionVersion = inspectionVersionDao.getMaxVersionBean(infoId, maxVersion);
			if(inspectionVersion!=null) {
				inspectionVersion.setReportAtt(attachment.getId());
			}
			
			
		}
	}
	
	
	
}
