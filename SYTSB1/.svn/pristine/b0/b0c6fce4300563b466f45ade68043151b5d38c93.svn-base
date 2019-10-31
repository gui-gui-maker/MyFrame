package com.lsts.mobileapp.input.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.mobileapp.input.bean.InspectRecordDir;

@Repository("inspectRecordDirDao")
public class InspectRecordDirDao extends EntityDaoImpl<InspectRecordDir> {

	public void deleteDir(String id) {
		String hql = "update InspectRecordDir set dataStatus='99' where dataStatus='0' and fkRecordId = ?";
		
		this.createQuery(hql, id).executeUpdate();
		
	}
	
	public List<InspectRecordDir> getDirByBid(String id) {
		String hql = "from InspectRecordDir  where dataStatus='0' and fkRecordId = ?";
		
		@SuppressWarnings("unchecked")
		List<InspectRecordDir> list = this.createQuery(hql, id).list();
		
		return list;
	}
	
	/**
	 * 按照分页和报告取目录信息
	 * author pingZhou
	 * @param id
	 * @param pageCodes
	 * @return
	 */
	public List<InspectRecordDir> getDirByBidPage(String id,String pageCodes) {
		pageCodes = pageCodes.replace(",", "','");
		String hql = "from InspectRecordDir  where dataStatus='0' and fkRecordId = ? and pageCode  in ('"+pageCodes+"')";
		
		@SuppressWarnings("unchecked")
		List<InspectRecordDir> list = this.createQuery(hql, id).list();
		
		return list;
	}
	
}
