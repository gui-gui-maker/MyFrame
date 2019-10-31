package com.lsts.equipment2.dao;


import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.equipment2.bean.EquipOutstockRecord;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipOutstockRecordDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("EquipOutstockRecordDao")
public class EquipOutstockRecordDao extends EntityDaoImpl<EquipOutstockRecord> {
	// 添加出库记录
	EquipOutstockRecordDao equipOutstockRecordDao;
	// 删除记录
	public void deleteById(String recordId){
		String sql = "delete from TJY2_EQUIP_OUTSTOCK t where t.id= ?";
		this.createSQLQuery(sql, recordId).executeUpdate();
	}
}