package com.khnt.mobile.version.dao;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.mobile.version.bean.AppUpdate;


@Repository("appUpdateDao")
public class AppUpdateDao extends EntityDaoImpl<AppUpdate> {

	public void updateResourceBusinessId(String businessId, String carpicId) {
		if (StringUtils.isNotEmpty(carpicId)
				&& StringUtils.isNotEmpty(businessId)) {
			String hql = "update Attachment s set s.businessId=:businessId where s.id in(:id)";
			this.createQuery(hql).setParameter("businessId", businessId)
					.setParameter("id", carpicId).executeUpdate();
		}
	}

	public AppUpdate checkUpdateByOsName(String osName, String appid){
		String sql = "from AppUpdate where type = ? and packageName = ?  order by cdate desc";
		List<AppUpdate> list = this.createQuery(sql,osName.toUpperCase(),appid).list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public AppUpdate getLastAppVersionUpdate(String osName,String pubType, String appid ){
		String sql = "from AppUpdate where type = ? and packageName = ? and pubType in :pubtype order by cdate desc";
		List<AppUpdate> list = this.createQuery(sql,osName.toUpperCase(), appid).setParameterList("pubtype", pubType.split(",")).list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public AppUpdate checkUpdate() {
		String sql = "select a.ID,a.VERSION,a.DESCRIPTION,a.CDATE,a.CNAME from sys_app_update a ORDER BY VERSION DESC";
		List<AppUpdate> list = this.getSession().createSQLQuery(sql)
				.addEntity(AppUpdate.class).list();
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}