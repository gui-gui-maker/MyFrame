package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.EquipmentBuyRelation;

/**
 * 设备申请关联数据处理对象
 * @ClassName EquipmentBuyRelationDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:28:00
 */
@Repository("equipmentBuyRelationDao")
public class EquipmentBuyRelationDao extends EntityDaoImpl<EquipmentBuyRelation>{

	/**
	 * 根据申请ID查询申请关联详情
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-19 上午10:49:00
	 */
	@SuppressWarnings("unchecked")
    public List<EquipmentBuyRelation> queryBaseEquipmentApplyRelationByApplyId(String id) {
    	List<EquipmentBuyRelation> list = new ArrayList<EquipmentBuyRelation>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from EquipmentBuyRelation r where r.equipmentBuy.id=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	/**
	 * 查询已归还数量
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-19 上午10:49:00
	 */
    public int queryReturn_count(String id) {
    	int return_count = 0;
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "select r.return_count from EquipmentBuyRelation r where r.id=?";
    			List list = this.createQuery(hql, id).list();
    			if (!list.isEmpty()) {
					return Integer.valueOf(String.valueOf(list.get(0)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return return_count;
    }
	
	
	/**
	 * 删除设备申请关联记录
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-18 下午03:19:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	//删除设备申请关联记录
        }
    }
}
