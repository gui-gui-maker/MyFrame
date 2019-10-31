package com.lsts.equipment2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.bean.Employee;
import com.lsts.equipment2.bean.EquipmentSupplier;

/**
 * 供货商信息数据处理对象
 * @ClassName BaseEquipmentSupplierDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午10:30:00
 */
@Repository("baseEquipment2SupplierDao")
public class EquipmentSupplierDao extends EntityDaoImpl<EquipmentSupplier>{

	/**
	 * 删除供货商信息
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-18 上午10:30:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	// 删除供货商信息
        }
    }
    
    /**
	 * 删除设备信息
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-19 下午01:42:00
	 */
    public void deleteEquipment(String id) {
    	this.createQuery("delete from Equipment b where b.baseEquipmentSupplier.id=?", id).executeUpdate();   
    }
    /** 根据厂商名称或电话号码获取厂商信息
	 * @param q
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
    public List<EquipmentSupplier> suggest(String q) {
        String hql = "from EquipmentSupplier t where t.supplier_name like '%" +q+"%'"+" or t.supplier_tel like '"+q+"%'";
        List<EquipmentSupplier> list = createQuery(hql).list();
        return list;

    }
}
