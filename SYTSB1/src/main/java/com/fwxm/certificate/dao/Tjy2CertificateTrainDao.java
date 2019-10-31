package com.fwxm.certificate.dao;


import org.springframework.stereotype.Repository; 

import com.fwxm.certificate.bean.Tjy2CertificateTrain;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2CertificateTrainDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2CertificateTrainDao")
public class Tjy2CertificateTrainDao extends EntityDaoImpl<Tjy2CertificateTrain> {
	/**
	 * 根据id删除数据
	 * @param id
	 * @throws Exception
	 */
	public void deleteTrain(String id) throws Exception {
   		String sql="delete from TJY2_CERTIFICATE_TRAIN t where t.id=?";
   		this.createSQLQuery(sql, id).executeUpdate();
   	}
}