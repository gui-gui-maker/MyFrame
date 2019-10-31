package com.lsts.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.CwBank2Pay;
import com.lsts.finance.dao.CwBank2PayDao;


/**
 * 银 银行转账与收费业务关联信息表业务逻辑对象
 * @ClassName CwBank2PayManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-18 上午10:21:00
 */
@Service("cwBank2PayManager")
public class CwBank2PayManager extends EntityManageImpl<CwBank2Pay,CwBank2PayDao>{
    @Autowired
    CwBank2PayDao cwBank2PayDao;

    /**
	 * 根据银行转账id、收费业务id获取关联信息
	 * @param fk_cw_bank_id -- 银行转账id
	 * @param fk_inspection_pay_id -- 收费业务id
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午10:22:00
	 */
	@SuppressWarnings("unchecked")
	public CwBank2Pay queryCwBank2Pay(String fk_cw_bank_id, String fk_inspection_pay_id){
		return cwBank2PayDao.queryCwBank2Pay(fk_cw_bank_id, fk_inspection_pay_id);
	}
	
	/**
	 * 根据收费业务id获取关联信息
	 * @param fk_inspection_pay_id -- 收费业务id
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午11:51:00
	 */
	@SuppressWarnings("unchecked")
	public List<CwBank2Pay> queryCwBank2Pays(String fk_inspection_pay_id){
		return cwBank2PayDao.queryCwBank2Pays(fk_inspection_pay_id);
	}
}
