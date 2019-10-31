package com.lsts.archives.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.bean.Uploads;
import com.lsts.archives.dao.UploadsDao;
import com.lsts.humanresources.bean.Contract;
import com.lsts.humanresources.bean.Upload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service("uploadsManager")
public class UploadsManager extends EntityManageImpl<Uploads,UploadsDao>{
    @Autowired
    private UploadsDao uploadsDao;
    @Autowired
	private AttachmentManager attachmentManager;
    
    
    public UploadsDao getUploadsDao() {
		return uploadsDao;
	}

	@SuppressWarnings("unchecked")
	public List<Uploads> getByEmpId(String id){
    	List<Uploads> list=new ArrayList<Uploads>();
    	String hql="from Uploads where FILE_ID=? order by uploadType,uploadName";
    	list=uploadsDao.createQuery(hql, id).list();
    	return list;
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> queryCountByUploadType(String id){
    	String sql = "select UPLOAD_TYPE, count(*) from TJY2_ARCHIVES_UPLOAD "
    			+ "where FILE_ID = ? group by upload_Type";
    	List<Object[]> list = uploadsDao.createSQLQuery(sql, id).list();
    	return list;
    }
	//删除，根据文档类型，和businessId
	public void deleteByBusinessAndUploadType(String uploadFileId,String uploadType,String folder){
		List<Uploads> oUploads = this.getByEmpIdAndUploadType(uploadFileId,uploadType);
			if(oUploads.size()>0){
				for(Uploads up : oUploads){
					if(up.getUploadPath().contains(folder)){
						this.getUploadsDao().remove(up);
					}
				}
			}
	}
	@SuppressWarnings("unchecked")
	public List<Uploads> getByEmpIdAndUploadType(String id,String uploadType){
    	List<Uploads> list=new ArrayList<Uploads>();
    	String hql="from Uploads where fileId=? and uploadType=? order by uploadName";
    	list=uploadsDao.createQuery(hql, id, uploadType).list();
    	return list;
    }
    public List<Object[]> getDevice(String id) {
    		List<Object[]> list = new ArrayList<Object[]>();
    		/*list = uploadsDao.createSQLQuery(
			"select upload_name,parent_id,id,upload_path from TJY2_ARCHIVES_UPLOAD 　　start with id = '00' 　　connect by prior id = parent_id order by levels DESC")
			.list();*/
    		list = uploadsDao.createSQLQuery(
    				"select upload_name,parent_id,upload_type,id,upload_path from TJY2_ARCHIVES_UPLOAD where file_id=? order by upload_name",id)
    				.list();

		return list;
	}
    /**
	 * 获取子层设备
	 * @param id 父节点编号
	 * @return
	 */
	public List<Uploads> getDevicesByPid(String id)  throws KhntException{
		List<Uploads> deviceList =  new ArrayList<Uploads>();
		deviceList = uploadsDao.createQuery(
				"from Uploads where parent_id = '" + id
						+ "' order by UPLOAD_NAME").list();
		return deviceList;
	}
    
 // 保存信息（含附件）
 	public void saveEquipment(Uploads entity, String uploadFiles){
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		//获取当前登录人姓名
 		entity.setUploadmanName(user.getName());
 		entity.setUploadTime(new Date());
// 					file.getBytes();
 		if(StringUtil.isNotEmpty(uploadFiles)){
 			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
 			for(String file : files){
 				if (StringUtil.isNotEmpty(file)) {
 					attachmentManager.setAttachmentBusinessId(file, entity.getId());
 				}
 			}
 		}
 		
 		uploadsDao.save(entity);
 	}
	
 	 /**
 		 * 取出合同文档
 		 * @param id
 		 * @return
 		 * @throws KhntException
 		 */
 		public Uploads getFile(String id) throws KhntException{
 			//取得文档
 			byte[] order = uploadsDao.getFile(id);
 			//取得表记录
 			Uploads uploads = uploadsDao.get(id);
 			uploads.setUploadDoc(order);
 			return uploads;
 		}
 		/**
 		 * 保存合同文档
 		 * @param inputStream
 		 * @param order
 		 */
 		public String saveO(InputStream inputStream, Uploads uploads) {
 			String orderId = uploadsDao.saveO(inputStream,uploads);
 			return orderId;
 		}

		public String dels(String rows) throws Exception{
			String fileId ="";
			JSONArray arr = JSONArray.fromString(rows);
			for (int i=0;i<arr.length();i++) {
				JSONObject obj = arr.getJSONObject(i);
				String id = (String)obj.get("id");
				String uploadId = (String)obj.get("uploadId");
				String uploadPath = (String)obj.get("uploadPath");
				if("".equals(fileId)){
					fileId = (String)obj.get("fileId");
				}
				this.deleteBusiness(id);
				attachmentManager.deleteAttach(uploadId, uploadPath);
			}
			return fileId;
		}

		public List<Object[]> getDevice2(String fileId) {
			List<Object[]> list = new ArrayList<Object[]>();
    		list = uploadsDao.createSQLQuery(
    				"select parent_id,id,upload_type,upload_name,upload_path,file_id from TJY2_ARCHIVES_UPLOAD "
    				+ "where file_id= ? order by upload_type,upload_name",fileId)
    				.list();
    		return list;
		}

		public List<Object[]> getFiles(String id, String upload_type) throws Exception{
			String sql = "select a.file_name,a.file_type,a.file_path "
  					+ "from PUB_ATTACHMENT a,TJY2_ARCHIVES_UPLOAD b "
  					+ "where a.id = b.upload_id and  a.business_id = ? and b.upload_type=?";
			
			return (List<Object[]>) uploadsDao.createSQLQuery(sql, id,upload_type).list();
		}
		/**
		 * 
		 * @param attachment_ids 文件id
		 * @param business_id 业务id
		 */
		public void saveAndUpdateAttachmentBusinessId(Uploads uploads, String attachment_ids, String business_id) {
			uploadsDao.save(uploads);
			attachmentManager.setAttachmentBusinessId(attachment_ids,business_id);
		}
}
