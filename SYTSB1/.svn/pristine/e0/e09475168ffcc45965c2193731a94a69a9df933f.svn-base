package com.lsts.finance.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBank2Pay;



/**
 * 银行转账与收费业务关联信息表数据处理对象
 * @ClassName CwBank2PayDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-18 上午10:14:00
 */
@Repository("cwBank2PayDao")
public class CwBank2PayDao extends EntityDaoImpl<CwBank2Pay> {

	/**
	 * 根据银行转账id、收费业务id获取关联信息
	 * @param fk_cw_bank_id -- 银行转账id
	 * @param fk_inspection_pay_id -- 收费业务id
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午10:15:00
	 */
	@SuppressWarnings("unchecked")
	public CwBank2Pay queryCwBank2Pay(String fk_cw_bank_id, String fk_inspection_pay_id){
		CwBank2Pay cwBank2Pay = null;
    	try {
    		if (StringUtil.isNotEmpty(fk_cw_bank_id) && StringUtil.isNotEmpty(fk_inspection_pay_id)) {
    			String hql = "from CwBank2Pay where fk_cw_bank_id=? and fk_inspection_pay_id=?";
    			cwBank2Pay = (CwBank2Pay) this.createQuery(hql, fk_cw_bank_id, fk_inspection_pay_id).uniqueResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return cwBank2Pay;
	}
	
	/**
	 * 根据收费业务id获取关联信息
	 * @param fk_inspection_pay_id -- 收费业务id
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午11:52:00
	 */
	@SuppressWarnings("unchecked")
	public List<CwBank2Pay> queryCwBank2Pays(String fk_inspection_pay_id){
		List<CwBank2Pay> list = new ArrayList<CwBank2Pay>();
    	try {
    		if (StringUtil.isNotEmpty(fk_inspection_pay_id)) {
    			String hql = "from CwBank2Pay where fk_inspection_pay_id=?";
    			list = this.createQuery(hql, fk_inspection_pay_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
}
