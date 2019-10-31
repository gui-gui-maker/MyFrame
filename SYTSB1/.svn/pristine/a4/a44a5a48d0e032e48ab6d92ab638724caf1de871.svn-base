package com.lsts.inspection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.InspectionVersion;

@Repository("inspectionVersionDao")
public class InspectionVersionDao extends EntityDaoImpl<InspectionVersion> {

	/**
	 * 查询最大版本
	 * author pingZhou
	 * @param infoId
	 * @return
	 */
	public int getMaxVersion(String infoId) {
		
		String hql = "select max(version) from InspectionVersion where businessId = ?";
		List<Object> list = this.createQuery(hql, infoId).list();
		if(list.size()>0&&list.get(0)!=null) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * 查询最大历史版本日志
	 * author pingZhou
	 * @param infoId
	 * @param version
	 * @return
	 */
	public InspectionVersion getMaxVersionBean(String infoId,Integer version) {
			
			String hql = " from InspectionVersion where businessId = ? and version = ?";
			List<InspectionVersion> list = this.createQuery(hql, infoId,version).list();
			if(list.size()>0&&list.get(0)!=null) {
				return list.get(0);
			}
			return null;
		}
		
	}
