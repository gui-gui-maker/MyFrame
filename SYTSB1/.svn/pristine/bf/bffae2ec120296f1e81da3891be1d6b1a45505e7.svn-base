package com.lsts.qualitymanage.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityXybzFile;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityXybzFileDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityXybzFileDao")
public class QualityXybzFileDao extends EntityDaoImpl<QualityXybzFile> {

	
	/**
	 * 根据设备类别、检验类别获取在用的检验依据文件
	 * @param device_sort -- 设备类别（二类）
	 * @param device_sort_code -- 设备类别（三类）
	 * @param check_type -- 检验类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-21 上午10:35:00
	 */
	@SuppressWarnings("unchecked")
	public List<QualityXybzFile> getFileInfos(String device_sort, String device_sort_code, String check_type) {
		List<QualityXybzFile> list = new ArrayList<QualityXybzFile>();
		String big_class = device_sort.substring(0,1)+"000";
		String hql = "from QualityXybzFile t where t.status = '1' and ( t.tzEquipmentType like '%"+device_sort+"%' or t.tzEquipmentType like '%"+device_sort_code+"%' or t.tzEquipmentType like '%"+big_class+"%')";
		if (StringUtil.isNotEmpty(check_type)) {
			hql += " and t.checkoutType like '%"+check_type+"%'";
		}
		list = this.createQuery(hql).list();
		return list;
	}
}