package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBuy;

/**
 * 设备申请数据处理对象
 * 
 * @ClassName EquipmentBuyDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:19:00
 */
@Repository("equipmentBuyDao")
public class EquipmentBuyDao extends EntityDaoImpl<EquipmentBuy> {
	/**
	 * 删除申请
	 */
	public void delete(String id) {
		String deleteSql = "delete TJY2_EQUIP_APPLY  where ID = ?";
		this.createSQLQuery(deleteSql,id).executeUpdate();
	}
	/**
  	 * 根据申请ID查找申请信息
  	 */
  	public EquipmentBuy queryBaseEquipment2Apply(String id){
  		List<EquipmentBuy> list = new ArrayList<EquipmentBuy>();
  		EquipmentBuy baseEquipment2Apply = new EquipmentBuy();
  		try {
			String hql = "from EquipmentBuy  where ID = ?";
			list = this.createQuery(hql,id).list();
			baseEquipment2Apply = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseEquipment2Apply;
    }
  	/**
  	 * 查询流程id
  	 * */
	@SuppressWarnings("rawtypes")
	public List getFlowId(String id){
		String sql = "select t.activity_id from TJY2_EQUIP_APPLY b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_EQUIPMENT_BUY%' and t.STATUS='0' and b.id=?";

		List list = this.createSQLQuery(sql,id).list();

		return list;
	}
}
