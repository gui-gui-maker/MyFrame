package com.scts.cspace.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.cspace.file.bean.TjyptFileInfo;
import com.scts.cspace.file.dao.TjyptFileInfoDao;

/**
 * 物理资源属性业务逻辑对象
 * @ClassName TjyptFileInfoService
 * @JDK 1.7
 * @author xcb
 * @date 2016-10-24 下午04:40:00
 */
@Service("tjyptFileInfoService")
public class TjyptFileInfoService extends
		EntityManageImpl<TjyptFileInfo, TjyptFileInfoDao> {
	
	@Autowired
	private TjyptFileInfoDao tjyptFileInfoDao;
	
	public void updateFilePath(String fileId,String filePath) throws Exception {
	
		TjyptFileInfo  fileInfo= this.get(fileId);	//获取物理资源对象
		
		fileInfo.setFile_path(filePath);//修改物理资源路径
		
		tjyptFileInfoDao.save(fileInfo);//保存修改后对象
		
		
	}
	public void updateFileUploadUserId(String fileId,String fileUploadUserId) throws Exception {
		
		TjyptFileInfo  fileInfo= this.get(fileId);	//获取物理资源对象
		
		fileInfo.setFile_path(fileUploadUserId);//修改上传人
		
		tjyptFileInfoDao.save(fileInfo);//保存修改后对象
		
		
	}
	public void updateFileOpenCount(String fileId,String fileOpenCount) throws Exception {
		
		TjyptFileInfo  fileInfo= this.get(fileId);	//获取物理资源对象
		
		fileInfo.setFile_open_count(fileOpenCount);//修改访问次数
		
		tjyptFileInfoDao.save(fileInfo);//保存修改后对象
		
		
	}
	public void updateFileDownloadCount(String fileId,String fileDownloadCount) throws Exception {
		
		TjyptFileInfo  fileInfo= this.get(fileId);	//获取物理资源对象
		
		fileInfo.setFile_download_count(fileDownloadCount);//修改下载次数
		
		tjyptFileInfoDao.save(fileInfo);//保存修改后对象
		
		
	}


	
}
