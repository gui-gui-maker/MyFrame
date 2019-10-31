package com.lsts.equipment2.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.EquipmentContract;
import com.lsts.humanresources.bean.Contract;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipmentContractDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("EquipmentContractDao")
public class EquipmentContractDao extends EntityDaoImpl<EquipmentContract> {
	//根据申请单ID查询合同信息
    @SuppressWarnings("unchecked")
	public List<EquipmentContract> getByBuyApplyId(String buyApplyId){
    	String hql="from EquipmentContract where fkEquipApplyId=?";
    	List<EquipmentContract> list = new ArrayList<EquipmentContract>();
		try {
			list = this.createQuery(hql, buyApplyId).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return  list;
    }
    /**
	 * 取得合同文档
	 * 
	 * @param id
	 *            ID
	 * @return 文档Byte[]
	 */
	public byte[] getFile(String id) {
		String sqlString = "select t.document_doc from TJY2_EQUIPMENT_CONTRACT t where t.id = ?";
		List list = this.createSQLQuery(sqlString, id).list();
		Object obj = null;
		if (list.size() > 0) {
			obj = list.get(0);
		}
		if (obj instanceof byte[])
			return (byte[]) obj;
		if (obj instanceof Blob) {
			Blob blob = (Blob) obj;
			byte data[] = null;
			try {
				data = blob.getBytes(1L, (int) blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		} else {
			return null;
		}

	}
}