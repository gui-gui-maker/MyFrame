package com.lsts.advicenote.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.Picture;
import com.lsts.advicenote.dao.PictureDao;

/**
 * 通知书业务逻辑对象
 * @ClassName AdviceNoteService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:43:00
 */
@Service("pictureService")
public class PictureService extends
		EntityManageImpl<Picture, PictureDao> {
	@Autowired
	private PictureDao pictureDao;
	
	@Autowired
	private AttachmentManager attachmentManager;
	
	public void savePic(Picture entity, String uploadFiles){
		
		
		
		pictureDao.save(entity);
		
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, entity.getId());	// 2、保存人员电子签名
				}
			}
		}
	
	}
	
	public HashMap<String, Object>  showPic() throws Exception
    {	
		HashMap<String, Object> map = new HashMap<String, Object>();
		
	
		

		List list = pictureDao.createSQLQuery("select t.pic_title,t1.file_path,t1.file_name from TSJY_PICTURE t,pub_attachment t1 where t.id=t1.business_id and t.flag='1' and t.pic_status='1' and rownum<9 ").list();
		
		

		
	
		map.put("flowData", list);
		map.put("success", true);
		
		
		
		return map;
    }
}
