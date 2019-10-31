package com.lsts.archives.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.dao.ArchivesFileDao;
import com.lsts.humanresources.bean.Upload;



@Service("archivesFile")
public class ArchivesFileManager extends EntityManageImpl<ArchivesFile,ArchivesFileDao>{
    @Autowired
    ArchivesFileDao archivesFileDao;
    @Autowired
	private AttachmentManager attachmentManager;
    
 // 保存信息（含附件）
 	public void saveEquipment(ArchivesFile archivesFile, String uploadFiles){
			
 		archivesFileDao.save(archivesFile);
 		if(StringUtil.isNotEmpty(uploadFiles)){
 			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
 			for(String file : files){
 				if (StringUtil.isNotEmpty(file)) {
 					attachmentManager.setAttachmentBusinessId(file, archivesFile.getId());
 				}
 			}
 		}
 	}
 	
 	 @SuppressWarnings("unchecked")
	public List<Upload> getByEmpId(String id){
	    	List<Upload> list=new ArrayList<Upload>();
	    	String hql="from ArchivesFile ID=?";
	    	list=archivesFileDao.createQuery(hql, id).list();
	    	return list;
	    }
 	 
 	/**
   	 * 生成修改单编号
   	 * */
   	public void saveTask1(ArchivesFile archivesFile,CurrentSessionUser user){
 		//新增保存时，生成新编号，修改功能不需要重新生成编号
 		if(null==archivesFile.getId() || "".equals(archivesFile.getId())){
 			String docNum = "";
 			Calendar a=Calendar.getInstance();
     		int nowYear = a.get(Calendar.YEAR);
     		List<ArchivesFile> archivesFilelist = archivesFileDao.getTaskAllot();
     		if(archivesFilelist==null || archivesFilelist.size()==0) {
     			docNum = nowYear+"-"+"001";
     		} else {
     			int[] docNumArray = new int[archivesFilelist.size()];
     			for (int i=0;i<archivesFilelist.size();i++) {
     				//将编号去掉“-”，转换成int型存入数组
     				docNumArray[i] = Integer.parseInt(archivesFilelist.get(i).getReportNumber().replaceAll("-", ""));
     			}
     			int max = docNumArray[0];
     			//获取数组中最大的值
     			for (int i : docNumArray) {
     				max = max>i?max:i;
     			}
     			String docNum1 = String.valueOf(max+1);
     			//编号加1后重新组
     			docNum = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 7);
     		}
     		archivesFile.setReportNumber(docNum);
 		}
 		archivesFile.setRegistrant(user.getName());
 		archivesFile.setRegistrantTime(new Date());
    		
 		archivesFileDao.save(archivesFile);
 	}
}
