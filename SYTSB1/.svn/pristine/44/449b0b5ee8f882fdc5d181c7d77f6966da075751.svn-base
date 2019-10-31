package com.fwxm.certificate.dao;

import org.springframework.stereotype.Repository;

import com.fwxm.certificate.bean.Tjy2CertificateTrainSub;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2CertificateTrainSubDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2CertificateTrainSubDao")
public class Tjy2CertificateTrainSubDao extends EntityDaoImpl<Tjy2CertificateTrainSub> {
	/**
	 * 根据businessId删除关联数据
	 * @param businessId
	 * @throws Exception
	 */
	public void deleteTrainSub(String businessId) throws Exception {
   		String sql="delete from TJY2_CERTIFICATE_TRAIN_SUB t where t.fk_certificate_train_id=?";
   		this.createSQLQuery(sql, businessId).executeUpdate();
   	}
}