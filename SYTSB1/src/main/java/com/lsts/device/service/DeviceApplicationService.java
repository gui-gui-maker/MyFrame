package com.lsts.device.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.dao.OrgDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.DeviceCategories;
import com.lsts.device.bean.TzsbAppConstrucationOrg;
import com.lsts.device.bean.TzsbAppEngineerSituations;
import com.lsts.device.bean.TzsbAppInfo;
import com.lsts.device.bean.TzsbApplication;
import com.lsts.device.dao.DeviceDao;
import com.lsts.device.dao.TzsbAppConsUnitDao;
import com.lsts.device.dao.TzsbAppConstrucationOrgDao;
import com.lsts.device.dao.TzsbAppDeviceDao;
import com.lsts.device.dao.TzsbAppDocumentFileDao;
import com.lsts.device.dao.TzsbAppEngineerSituationsDao;
import com.lsts.device.dao.TzsbAppOutsourDao;
import com.lsts.device.dao.TzsbAppSupervisionUnitDao;
import com.lsts.device.dao.TzsbAppWorksDao;
import com.lsts.device.dao.TzsbApplicationDao;

/**
 * 安全告知书 servier
 * 
 * @author liming 5.14
 */
@Service("deviceApplicationService")
public class DeviceApplicationService{ 
	@Autowired
	private TzsbApplicationDao tzsbAppcationDao;
	@Autowired
	private TzsbAppConstrucationOrgDao tzsbAppConstrucationOrgDao;
	@Autowired
	private TzsbAppConsUnitDao tzsbAppConsUnitDao;
	@Autowired
	private TzsbAppDeviceDao tzsbAppDeviceDao;
	@Autowired
	private TzsbAppDocumentFileDao tzsbAppDocumentFileDao;
	@Autowired
	private TzsbAppEngineerSituationsDao tzsbAppEngineerSituationsDao;
	@Autowired
	private TzsbAppOutsourDao tzsbAppOutsourDao;
	@Autowired
	private TzsbAppSupervisionUnitDao tzsbAppSupervisionUnitDao;
	@Autowired
	private TzsbAppWorksDao tzsbAppWorksDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private OrgDao orgDao;
	
	public String getApplicationCode() {
		String code=tzsbAppcationDao.getApplicationCode();
		return code;
	}
	/**
	 * 保存告知书
	 * @param info
	 */
	@Transactional
	public void saveApplication(TzsbAppInfo info){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String appid=info.getTzsbApplication().getId();
		TzsbApplication  appbase=info.getTzsbApplication();
		if(StringUtil.isEmpty(appid)){//表示添加
			appbase.setCreated_by(user.getName());
			appbase.setCreated_date(new Date());
		}
		appbase.setLast_upd_date(new Date());
		appbase.setLast_upd_by(user.getName());
		tzsbAppcationDao.save(appbase);
		
		appid=info.getTzsbApplication().getId();
		 TzsbAppConstrucationOrg tcorg=info.getTzsbAppConstrucationOrg();
		 tcorg.setFk_tzsb_application_id(appid);
		tzsbAppConstrucationOrgDao.save(tcorg);
		
		TzsbAppEngineerSituations tes=info.getTzsbAppEngineerSituations();
		tes.setFk_tzsb_application_id(appid);
		tzsbAppEngineerSituationsDao.save(tes);
		
	}
	/**
	 * 删除告知书
	 * @param appId 告知书id
	 */
	@Transactional
	public void deleteApp(String appIds){
		  String arr[] = appIds.split(",");
	        for (int i = 0; i < arr.length; i++) {
	        	String appId=arr[i];
	    		tzsbAppConstrucationOrgDao.removeByAppId(appId);
	    		tzsbAppEngineerSituationsDao.removeByAppId(appId);
	    		tzsbAppConsUnitDao.removeByAppId(appId);
	    		tzsbAppDeviceDao.removeByAppId(appId);
	    		tzsbAppDocumentFileDao.removeByAppId(appId);
	    		tzsbAppOutsourDao.removeByAppId(appId);
	    		tzsbAppSupervisionUnitDao.removeByAppId(appId);
	    		tzsbAppWorksDao.removeByAppId(appId);
	    		
	    		tzsbAppcationDao.removeById(appId);
//	            this.createQuery("delete from EmployeeCert where id=?", arr[i]).executeUpdate();     
	        }
		
	}
	public TzsbAppInfo queryByAppId(String appId){
		TzsbApplication	tzsbApplication=tzsbAppcationDao.get(appId);
		TzsbAppConstrucationOrg tzsbAppConstrucationOrg =tzsbAppConstrucationOrgDao.getByAppId(appId);
		TzsbAppEngineerSituations tzsbAppEngineerSituations = tzsbAppEngineerSituationsDao.getByAppId(appId);
		
		TzsbAppInfo appInfo=new TzsbAppInfo();
		appInfo.setTzsbApplication(tzsbApplication);
		appInfo.setTzsbAppEngineerSituations(tzsbAppEngineerSituations);
		appInfo.setTzsbAppConstrucationOrg(tzsbAppConstrucationOrg);
		return appInfo;
	}
	public DeviceCategories getDeviceSort(String id){
		return tzsbAppcationDao.getDeviceSort(id);
	}
	public Org getOrgByCode(String code){
		Org org=orgDao.findUniqueBy("orgCode", code);
		return org;
	}
}
