package com.lsts.inspection.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.InspectionInfoPay;



/**
 * 检验检测收费标准配置表数据处理对象
 * @ClassName InspectionInfoPayDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-12-30 上午09:40:00
 */
@Repository("inspectionInfoPayDao")
public class InspectionInfoPayDao extends EntityDaoImpl<InspectionInfoPay> {

	/**
	 * 根据device_sort_code获取收费标准配置信息
	 * @param device_sort_code -- 设备名称代码
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-05 下午14:29:00
	 */
	@SuppressWarnings("unchecked")
	public List<InspectionInfoPay> getByDeviceSortCodes(String device_sort_code) {
		List<InspectionInfoPay> list = new ArrayList<InspectionInfoPay>();
		String hql = "from InspectionInfoPay t where t.device_sort_code=? and t.data_status = '0'";
		list = this.createQuery(hql, device_sort_code).list();
		return list;
	}
}
