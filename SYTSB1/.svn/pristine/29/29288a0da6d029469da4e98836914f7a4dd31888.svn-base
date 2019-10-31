package com.lsts.qualitymanage.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.qualitymanage.bean.QualityFiles;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityFilesDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("qualityFilesDao")
public class QualityFilesDao extends EntityDaoImpl<QualityFiles> {
	
	/**查找是否存在此编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityFiles> getQualityFiles(String fileId,String TjyFile) {
		String sql = "select * from TJY2_QUALITY_FILES where file_Id='"+fileId+"'"+"and Tjy_File='"+TjyFile+"'";
		List<QualityFiles> list = createSQLQuery(sql).list();
		return list;

	}
	
	@SuppressWarnings("rawtypes")
	public List getwj(String id){
		String sql = "select * from PUB_ATTACHMENT t where t.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getbusiness_id(String id){
		String sql = "select business_id from TJY2_QUALITY_ATTACHMENT t where t.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
	@SuppressWarnings("rawtypes")
	public List getfileName(String id){
		String sql = "select FILE_NAME from TJY2_QUALITY_ATTACHMENT t where t.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
}