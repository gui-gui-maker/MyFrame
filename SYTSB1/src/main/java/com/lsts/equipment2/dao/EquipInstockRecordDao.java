package com.lsts.equipment2.dao;


import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.equipment2.bean.EquipInstockRecord;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipInstockRecordDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("EquipInstockRecordDao")
public class EquipInstockRecordDao extends EntityDaoImpl<EquipInstockRecord> {
	// 添加出入库记录
	EquipInstockRecordDao equipInstockRecordDao;
	// 删除记录
	public void deleteById(String recordId){
		String sql = "delete from TJY2_EQUIP_INSTOCK t where t.id= ?";
		this.createSQLQuery(sql, recordId).executeUpdate();
	}
}