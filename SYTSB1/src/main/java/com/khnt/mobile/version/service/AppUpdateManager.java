package com.khnt.mobile.version.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.mobile.version.bean.AppUpdate;
import com.khnt.mobile.version.dao.AppUpdateDao;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;


@Service("appUpdateManager")
public class AppUpdateManager extends EntityManageImpl<AppUpdate, AppUpdateDao> {
    @Autowired
    AppUpdateDao sdyAppUpdateDao;
    @Autowired
    AttachmentManager attachmentManager;

    public static void main(String args[]) {
        String str = "7.0.0";
        str = str.replace(".", "");
        System.out.println(str);
    }

    public void saveSdyAppUpdateData(AppUpdate update, String carpicId) throws Exception{
        CurrentSessionUser securityUser = SecurityUtil.getSecurityUser();
        update.setCdate(new Date());
        update.setCname(securityUser.getName());
        this.sdyAppUpdateDao.save(update);
        // 回写资源业务id
        updateResourceBusinessId(update.getId(), carpicId);
    }

    public void updateResourceBusinessId(String businessId, String carpicId) throws Exception{
        this.sdyAppUpdateDao.updateResourceBusinessId(businessId, carpicId);
    }

    public Map<String, Object> checkUpdate(String appid, String version, String appVersion,String osName) throws Exception {
        Map<String, Object> wrapper = new HashMap<String, Object>();
        boolean needupdate = false;
        boolean mustupdate = false;
        String note = "";
        String fileurl ="";
        String url = "";
        String updateType = "";
        AppUpdate update = sdyAppUpdateDao.checkUpdateByOsName(osName,appid);
        if(update!=null){
        	//判断应用版本号，再判断资源版本号
        	int updateAppVersion = Integer.parseInt(update.getAppVersion().replace(".", ""));
        	int clientAppVersion = Integer.parseInt(appVersion.replace(".", ""));
        	if(clientAppVersion<updateAppVersion){
        		//升级应用
        		AppUpdate lastAppVersionUpdate = sdyAppUpdateDao.getLastAppVersionUpdate(osName,"2,3",appid);
        		if(lastAppVersionUpdate!=null){
        			//必须升级大版本号
        			needupdate = true;
        			if("2".equals(update.getPubType())||"3".equals(update.getPubType())){
        				mustupdate = "1".equals(lastAppVersionUpdate.getNeedUpdate())?true:false;
        				System.out.println("===================="+mustupdate);
                	}else {
						mustupdate = true;
					}
        			//返回前台。。。
        			note = lastAppVersionUpdate.getDescription();
                    List<Attachment> busAttachment = attachmentManager.getBusAttachment(new String[] { lastAppVersionUpdate.getId() });
                    if (!busAttachment.isEmpty() && busAttachment != null) {
                    	fileurl= "/fileupload/download.do?id=" + busAttachment.get(0).getId();
                    }
                    url = lastAppVersionUpdate.getUrl();
                    updateType = lastAppVersionUpdate.getPubType();
        		}else{
        			needupdate = false;
        			mustupdate = false;
        		}
        	}else if(clientAppVersion==updateAppVersion){
        		//此时再判断资源版本号升级
        		int clientVersion  =  Integer.parseInt(version.replace(".", ""));
        		int updateVersion = Integer.parseInt(update.getVersion().replace(".", ""));
        		if(clientVersion<updateVersion){
        			//获取最新的资源升级包版本号
        			//SdyAppUpdate lastVersionUpdate = sdyAppUpdateDao.getLastAppVersionUpdate(osName,"1");
        			needupdate = true;
        			mustupdate = "1".equals(update.getNeedUpdate())?true:false;;
        			//返回前台。。。
        			note = update.getDescription();
                    List<Attachment> busAttachment = attachmentManager.getBusAttachment(new String[] { update.getId() });
                    if (!busAttachment.isEmpty() && busAttachment != null) {
                    	fileurl= "/fileupload/download.do?id=" + busAttachment.get(0).getId();
                    }
                    url = update.getUrl();
                    updateType = update.getPubType();
        		}else{
        			needupdate = false;
        		}
        	}
        }else{
        	needupdate = false;
        }
        wrapper.put("version", update==null?"":update.getVersion());
        wrapper.put("appVersion", update==null?"":update.getAppVersion());
        wrapper.put("needupdate", needupdate);
        wrapper.put("mustupdate", mustupdate);
        wrapper.put("note", note);
        wrapper.put("fileurl", fileurl);
        wrapper.put("url", url);
        wrapper.put("updateType", updateType);
        return wrapper;
    }
}
