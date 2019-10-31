package com.lsts.qualitymanage.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityManagerFiles;
import com.lsts.qualitymanage.bean.QualityManagerFilesUpdate;
import com.lsts.qualitymanage.dao.QualityManagerFilesDao;
import com.lsts.qualitymanage.dao.QualityManagerFilesUpdateDao;


/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityFilesManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@Service("qualityManagerFilesService")
public class QualityManagerFilesService extends EntityManageImpl<QualityManagerFiles, QualityManagerFilesDao> {
	@Autowired
	QualityManagerFilesDao qualityManagerFilesDao;
	@Autowired
	QualityManagerFilesUpdateDao qualityManagerFilesUpdateDao;
	@Autowired
	private QualityAttachmentManager qualityAttachmentManager;
	@Autowired
	private AttachmentManager attachmentManager;

	/**
	 * 修改体系文件信息状态
	 * author pingZhou
	 * @param ids
	 * @param status
	 */
	public void changeStatus(String ids, String status) throws KhntException{
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		String []id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			
			QualityManagerFiles file = qualityManagerFilesDao.get(id[i]);
			file.setStatus(status);
			
			if("9".equals(status)){
				//作废
				file.setDelDate(new Date());
				file.setDelManId(user.getId());
				file.setDelManName(user.getName());
				
			}
			
			
			qualityManagerFilesDao.save(file);
			
		}

		
	}

	/**
	 * 申请修改启用的体系文件
	 * author pingZhou
	 * @param id
	 * @param remark 说明
	 * @param audit_op 
	 * @param audit_id 
	 */
	public void applyEdit(String id, String remark, String audit_id, String audit_op) throws KhntException{

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		QualityManagerFiles file = qualityManagerFilesDao.get(id);
		//文件状态改为修订中
		file.setStatus("2");
		
		QualityManagerFilesUpdate  filesUpdate = new QualityManagerFilesUpdate();
		
		filesUpdate.setFkQfilesOldId(file.getId());
		filesUpdate.setFileNameOld(file.getFileName());
		filesUpdate.setUpdateType("1");
		filesUpdate.setRegistrant(user.getId());
		filesUpdate.setRegistrantName(user.getName());
		filesUpdate.setRegistrantTime(new Date());
		filesUpdate.setUpdateReasons(remark);
		filesUpdate.setHandle_id(audit_id);
		filesUpdate.setHandle_name(audit_op);
		filesUpdate.setHandle_status("1");
		
		qualityManagerFilesDao.save(filesUpdate);
				
		
	}

	public void update(QualityManagerFiles entity, String u_id) {
		QualityManagerFilesUpdate qfu = qualityManagerFilesUpdateDao.get(u_id);
		QualityManagerFiles qf = qualityManagerFilesDao.get(qfu.getFkQfilesOldId());
		String modifyContent="";
		if(!(entity.getFileNum().equals(qf.getFileNum()))){
			modifyContent="将文件编号："+qf.getFileNum()+"改为"+entity.getFileNum()+"。";
		}
		if(!(entity.getFileName().equals(qf.getFileName()))){
			modifyContent="将文件名字："+qf.getFileName()+"改为"+entity.getFileName()+"。";
		}
		if(!(entity.getImplementDate().equals(qf.getImplementDate()))){
			modifyContent="实施日期："+qf.getImplementDate()+"改为"+entity.getImplementDate()+"。";
		}
		//保存行后的新文件
		qualityManagerFilesDao.save(entity);
		String fileIds=entity.getFileId();
		String[] fileId=fileIds.split(",");
		//更新附件业务id
		for (int i = 0; i < fileId.length; i++) {
			Attachment attach = attachmentManager.get(fileId[i]);
			attach.setBusinessId(entity.getId());
			attachmentManager.save(attach);	
			//将新文件id记录到更新记录里
			if("new".equals(attach.getWorkItem())){
				qfu.setFile_id_new(attach.getId());
			}
		}
		//将旧文件设置为已替换
		qf.setStatus("5");
		qualityManagerFilesDao.save(qf);
		//保存更新记录
		qfu.setFunctionDateOld(qf.getImplementDate());
		qfu.setFunctionDate(entity.getImplementDate());
		qfu.setFileTypeOld(qf.getFileType());
		qfu.setFileTypeNew(entity.getFileType());
		qfu.setFileNameNew(entity.getFileName());
		qfu.setFileNumNew(entity.getFileNum());
		qfu.setFileNumOld(qf.getFileNum());
		qfu.setFkQfilesNewId(entity.getId());
		qfu.setUpdateOcntent(modifyContent);
		qualityManagerFilesUpdateDao.save(qfu);
		
	}

	/**
	 * 保存业务ID至附件信息中
	 * @param entity
	 */
	public void saveBasic(QualityManagerFiles entity) {
		qualityManagerFilesDao.save(entity);
		String files=entity.getFileId();
		if(StringUtil.isNotEmpty(files)){
			String[]fileArr = files.split(",");
			for(String file : fileArr) {
				Attachment attach = attachmentManager.get(file);
				attach.setBusinessId(entity.getId());
				attachmentManager.save(attach);
			}
		}
		
	}

}
