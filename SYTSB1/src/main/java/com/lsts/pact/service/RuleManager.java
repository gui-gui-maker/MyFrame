package com.lsts.pact.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.pact.bean.PactInfo;
import com.lsts.pact.bean.RuleInfo;
import com.lsts.pact.dao.PactDao;
import com.lsts.pact.dao.RuleDao;

@Service("ruleManager")
public class RuleManager  extends EntityManageImpl<RuleInfo,RuleDao>{
	 	
	 	@Autowired
	 	RuleDao ruleDao;
	    
	    @Autowired
	   	private AttachmentManager attachmentManager;
	       
	    
	    	 // 保存信息（含附件）
	    	public void saveRule(RuleInfo entity, String uploadFiles){
	    		CurrentSessionUser user = SecurityUtil.getSecurityUser();
	    		
	    		entity.setCreate_date(new Date());
	    		entity.setCreate_name(user.getName());
	    		
	    		ruleDao.save(entity);
	    		if(StringUtil.isNotEmpty(uploadFiles)){
	    			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
	    			for(String file : files){
	    				if (StringUtil.isNotEmpty(file)) {
	    					attachmentManager.setAttachmentBusinessId(file, entity.getId());
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	    	public void delRule(String ids) {
	    		try {
	    			CurrentSessionUser user = SecurityUtil.getSecurityUser();
	    			// TODO Auto-generated method stub
	    			//判断是否多个ID
	    			if(ids.indexOf(",")!=-1){
	    				String id[] =ids.split(",");	
	    				for(int i=0;i<id.length;i++){
	    					//DeviceDocument device =deviceDao.get(id);
	    					//device.setDevice_status("99");	// 99：已删除
	    					//device.setLast_upd_by(user.getName());
	    					//device.setLast_upd_date(new Date());
	    					ruleDao.createSQLQuery("delete  BASE_RULE_SYSTEM   where id =?", id[i] ).executeUpdate();
	    					//deviceDao.save(device);
	    				}	
	    			}else{
	    				//DeviceDocument device =deviceDao.get(ids);
	    				//device.setDevice_status("99");	
	    				//device.setLast_upd_by(user.getName());
	    				//device.setLast_upd_date(new Date());
	    				ruleDao.createSQLQuery("delete  BASE_RULE_SYSTEM  where id =?", ids ).executeUpdate();
	    				//deviceDao.save(device);
	    				//deviceDao.save(device);
	    			}
	    		} catch (Exception e) {
	    			log.debug(e.toString());
	    			e.printStackTrace();
	    		}
	    		
	    	}
}
