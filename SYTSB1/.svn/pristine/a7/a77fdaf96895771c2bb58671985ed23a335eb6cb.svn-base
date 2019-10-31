package com.scts.payment.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.scts.payment.bean.InspectionPayDetail;


/**
 * 报检收费详细信息数据处理对象
 * @ClassName InspectionPayDetailDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:24:00
 */
@Repository("inspectionPayDetailDao")
public class InspectionPayDetailDao extends EntityDaoImpl<InspectionPayDetail> {

	/**
	 * 根据收费ID查询收费详细信息
	 * @param fk_pay_info_id
	 * @return 
	 * @author GaoYa
	 * @date 2014-05-07 下午03:55:00
	 */
	@SuppressWarnings("unchecked")
    public InspectionPayDetail queryByPayInfoID(String fk_pay_info_id) {
		InspectionPayDetail inspectionPayDetail = null;
    	try {
    		if (StringUtil.isNotEmpty(fk_pay_info_id)) {
    			String hql = "from InspectionPayDetail where fk_pay_info_id = ?";
    			inspectionPayDetail = (InspectionPayDetail)this.createQuery(hql, fk_pay_info_id).uniqueResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return inspectionPayDetail;
    }
	
	//删除
	public void delete(String id) {
	    String hql="delete from InspectionPayDetail where fk_pay_info_id = ?";
		this.createQuery(hql, id).executeUpdate();
	}
}
