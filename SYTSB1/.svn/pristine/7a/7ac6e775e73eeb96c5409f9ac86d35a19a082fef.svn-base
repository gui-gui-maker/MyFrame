package com.lsts.qualitymanage.service;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityManagerFiles;
import com.lsts.qualitymanage.bean.QualityManagerFilesUpdate;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.dao.QualityManagerFilesDao;
import com.lsts.qualitymanage.dao.QualityManagerFilesUpdateDao;


/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityFilesUpdateManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@Service("qualityFilesUpdateManager")
public class QualityManagerFilesUpdateService extends EntityManageImpl<QualityManagerFilesUpdate, QualityManagerFilesUpdateDao> {
	@Autowired
	QualityManagerFilesUpdateDao qualityManagerFilesUpdateDao;
	@Autowired
	QualityManagerFilesDao qualityManagerFilesDao;
	@Autowired
	private AttachmentManager attachmentManager;

	public void saveOpinion(String ids, String opinion, String userId,
			String userName, String status, Date date,String next_op_name,String next_op_id) throws Exception {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			QualityManagerFilesUpdate qfu = qualityManagerFilesUpdateDao.get(id[i]);
			if(StringUtil.isNotEmpty(next_op_id)){
				qfu.setHandle_id(next_op_id);
				qfu.setHandle_name(next_op_name);
			}			
			if("audit".equals(status)){
				qfu.setAuditManId(userId);
				qfu.setAuditManName(userName);
				qfu.setAuditTime(date);
				qfu.setAuditDesc(opinion);
				if("不同意".equals(opinion)){
					qfu.setHandle_status("3");
					QualityManagerFiles qf = qualityManagerFilesDao.get(qfu.getFkQfilesOldId());
					qf.setStatus("1");
					qualityManagerFilesDao.save(qf);
				}else{
					qfu.setHandle_status("2");
				}
			}
			if("sign".equals(status)){
				qfu.setSignManId(userId);
				qfu.setSignManName(userName);
				qfu.setSignTime(date);
				qfu.setSignDesc(opinion);
				if("不同意".equals(opinion)){
					qfu.setHandle_status("3");
					QualityManagerFiles qf = qualityManagerFilesDao.get(qfu.getFkQfilesOldId());
					qf.setStatus("1");
					qualityManagerFilesDao.save(qf);
				}
				if("同意".equals(opinion)){
					//将现有体系文件设置为旧体系文件
					List<Attachment> list = attachmentManager.getBusAttachment(qfu.getFkQfilesOldId(), "new");
					for (int j = 0; j < list.size(); j++) {
						Attachment att = list.get(j);
						att.setWorkItem("old");
						attachmentManager.save(att);
						qfu.setFile_id_old(att.getId());
					}
					qfu.setHandle_status("4");
					
				}
			}
			qualityManagerFilesUpdateDao.save(qfu);
		}
		
	}

	public String  updateFiles(String id) throws IllegalAccessException, InvocationTargetException {

		QualityManagerFilesUpdate qfu = qualityManagerFilesUpdateDao.get(id);
		QualityManagerFiles qf = qualityManagerFilesDao.get(qfu.getFkQfilesOldId());
		QualityManagerFiles newqf = new QualityManagerFiles();
		BeanUtils.copyProperties(newqf,qf);
		newqf.setId(null);	
		qualityManagerFilesDao.save(newqf);
		qfu.setFkQfilesNewId(newqf.getId());
		qfu.setFileNameNew(newqf.getFileName());
		qualityManagerFilesUpdateDao.save(qfu);
		qf.setStatus("5");
		qualityManagerFilesDao.save(qf);
		return newqf.getId();
	}

	public void finishuUpdateFiles(String ids) {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			QualityManagerFilesUpdate qfu = qualityManagerFilesUpdateDao.get(id[i]);
			qfu.setHandle_status("5");
			QualityManagerFiles qf = qualityManagerFilesDao.get(qfu.getFkQfilesNewId());
			qf.setStatus("1");
			qualityManagerFilesDao.save(qf);	
			QualityManagerFiles qfo = qualityManagerFilesDao.get(qfu.getFkQfilesOldId());
			qfo.setStatus("9");
			qualityManagerFilesDao.save(qfo);
		}
		
	}

	public void applyEdit(QualityManagerFilesUpdate entity) {
		entity.setHandle_id(entity.getAuditManId());
		entity.setHandle_name(entity.getAuditManName());
		qualityManagerFilesUpdateDao.save(entity);
		QualityManagerFiles qfo = qualityManagerFilesDao.get(entity.getFkQfilesOldId());
		qfo.setStatus("2");
		qualityManagerFilesDao.save(qfo);
	}

}
